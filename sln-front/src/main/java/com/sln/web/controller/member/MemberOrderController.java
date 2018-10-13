package com.sln.web.controller.member;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.SlnConfig;
import com.sln.core.HttpClientUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.JsonUtil;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberBalanceLogs;
import com.sln.entity.operate.CourierCompany;
import com.sln.entity.order.OrderLog;
import com.sln.entity.order.Orders;
import com.sln.model.order.OrdersModel;
import com.sln.service.member.IMemberBalanceLogsService;
import com.sln.service.member.IMemberService;
import com.sln.service.operate.ICourierCompanyService;
import com.sln.service.order.IOrderLogService;
import com.sln.service.order.IOrdersService;
import com.sln.vo.order.OrderCommitVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.CommUtil;
import com.sln.web.util.WebFrontSession;

/**
 * 会员订单中心controller
 *
 * @Filename: MemberOrderController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberOrderController extends BaseController {
    private static Logger          log = LogManager.getLogger(OrdersModel.class);

    @Resource
    private IOrdersService         ordersService;
    @Resource
    private IOrderLogService       orderLogService;
    @Resource
    private ICourierCompanyService courierCompanyService;
    @Resource
    private IMemberService        memberService;
    @Resource
    private IMemberBalanceLogsService memberBalanceLogsService;

    /**
     * 跳转到我的订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "order.html", method = { RequestMethod.GET })
    public String toUserCenter(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap, String orderState,
                               String evaluateNoState) {
    	this.head(0,dataMap,request);
    	
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,5);
        
        pager.setPageSize(ConstantsEJS.DEFAULT_ORDER_PAGE_SIZE);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_memberId", String.valueOf(member.getId()));
        if (!StringUtil.isEmpty(orderState, true)) {
            queryMap.put("q_orderState", String.valueOf(orderState));
        }
        if (!StringUtil.isEmpty(evaluateNoState, true)) {
            queryMap.put("q_evaluateNoState", String.valueOf(evaluateNoState));
        }
        //查询订单列表
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrderWithOrderProduct(queryMap,
            pager);

        //支付随机码,对于未付款的订单
        String order_session = CommUtil.randomString(32);
        request.getSession().setAttribute("order_session", order_session);
        dataMap.put("sessionRandomStr", order_session);

        dataMap.put("ordersList", serviceResult.getResult());
        dataMap.put("page", pager);

        //return "front/member/ordercenter/orderlist";
        return "front/portal/orders/orderlist";
    }

    /**
     * 跳转到 订单详情页面 显示物流、付款信息、网单信息
     * @param request
     * @param response
     * @param dataMap
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/orderdetail.html", method = { RequestMethod.GET })
    public String toOrdersDetail(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap,
                                 @RequestParam(value = "id", required = true) Integer id) {
    	this.head(0,dataMap,request);
    	
        String errorMsg = "";
        try {
            // 查询订单列表
            ServiceResult<Orders> serviceResult = ordersService.getOrderWithOPById(id);
            if (!serviceResult.getSuccess()) {
                dataMap.put("info", "订单获取失败，请联系管理员。");
                return "/front/commons/error";
            }
            // 查询订单日志
            ServiceResult<List<OrderLog>> orderLogResult = orderLogService.getOrderLogByOrderId(id);

            Orders orders = serviceResult.getResult();
            Integer userid = WebFrontSession.getLoginedUser(request).getId();
            if (orders == null) {
                dataMap.put("info", "订单不存在");
                return "/front/commons/error";
            } else if (userid.intValue() != orders.getMemberId().intValue()) {
                dataMap.put("info", "您无权查看他人信息");
                return "/front/commons/error";
            }

            List<OrderLog> logList = orderLogResult.getResult();
            if (orders != null && orders.getLogisticsId() > 0) {
                // 快递100查询物流信息
                ServiceResult<CourierCompany> courierResult = courierCompanyService
                    .getCourierCompanyById(orders.getLogisticsId());
                CourierCompany courierCompany = courierResult.getResult();
                if (courierCompany != null) {
                    String url = "http://api.kuaidi100.com/api?id=" + SlnConfig.KUAIDI100_KEY;
                    url += "&com=" + courierCompany.getCompanyMark();
                    url += "&nu=" + orders.getLogisticsNumber();
                    url += "&show=0";
                    url += "&muti=1";
                    url += "&order=asc";

                    String sendGet = HttpClientUtil.sendGet(url);

                    Map<String, Object> fromJson = JsonUtil.fromJson(sendGet);

                    Object status = null;
                    if (fromJson != null) {
                        status = fromJson.get("status");
                    }
                    // 查询结果状态： 0：物流单暂无结果， 1：查询成功， 2：接口出现异常
                    if (status != null && "1".equals(status.toString())) {
                        List<Map<String, String>> list = (List<Map<String, String>>) fromJson
                            .get("data");
                        for (Map<String, String> map : list) {
                            OrderLog orderLog = new OrderLog();
                            SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(
                                "yyyy-MM-dd HH:mm:ss");
                            try {
                                orderLog.setCreateTime(simpleDateFormat.parse(map.get("time")));
                                orderLog.setContent(map.get("context"));
                                orderLog.setOperatingName(courierCompany.getCompanyName());
                            } catch (ParseException e) {
                                log.error(e.getMessage(), e);
                            }
                            logList.add(orderLog);
                        }
                    } else {
                        log.error("物流信息查询错误：status=" + status.toString());
                        log.error("物流信息查询错误：message=" + fromJson.get("message"));
                        log.error("物流公司：" + courierCompany.getCompanyName());
                        log.error("物流单号：" + orders.getLogisticsNumber());
                    }

                    Collections.sort(logList, new Comparator<OrderLog>() {
                        public int compare(OrderLog arg0, OrderLog arg1) {
                            return arg0.getCreateTime().compareTo(arg1.getCreateTime());
                        }
                    });
                }
            }

            dataMap.put("orderLogList", logList);
            dataMap.put("order", orders);
        } catch (Exception e) {
            if (e instanceof BusinessException)
                errorMsg = e.getMessage();
            else
                e.printStackTrace();
        }

        dataMap.put("errorMsg", errorMsg);
        //return "front/member/ordercenter/orderdetail";
        return "front/portal/orders/orderdetail";
    }

    /**
     * 取消订单
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/cancalorder.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cancalOrder(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap,
                                                             @RequestParam(value = "id", required = true) Integer id) {
        Member member = WebFrontSession.getLoginedUser(request);
        //取消订单
      
        	//改造获取来源money_paid_balance
        	ServiceResult<Orders> ordersById = ordersService.getOrdersById(id);
        	if(ordersById.getResult().getPaymentStatus()==0){
        	member.setBalance(ordersById.getResult().getMoneyPaidBalance());//余额账户支付总金额
        	memberService.updateMember(member);
        	//获取订单编号，支付余额，
        	MemberBalanceLogs logs=new MemberBalanceLogs();
            logs.setMemberId(member.getId());
            logs.setMemberName(member.getName());
            logs.setCreateTime(new Date());
            logs.setMoneyBefore(ordersById.getResult().getMoneyPaidBalance());
            logs.setMoney(ordersById.getResult().getMoneyPaidBalance());
            logs.setMoneyAfter(new BigDecimal(0.00));
            logs.setState(MemberBalanceLogs.STATE_2);
            logs.setRemark("订单取消，订单号" + ordersById.getResult().getOrderSn());
            logs.setOptId(member.getId());
            logs.setOptName(member.getName());
        	memberBalanceLogsService.saveMemberBalan(logs);
        	}
        	ServiceResult<Boolean> serviceResult = ordersService.cancelOrder(id, member.getId(), member.getName());
        

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;

    }

    /**
     * 确认收货
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/goodreceive.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> goodsReceipt(HttpServletRequest request,
                                                              HttpServletResponse response,
                                                              @RequestParam(value = "ordersId", required = true) Integer ordersId) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> serviceResult = ordersService.goodsReceipt(member, ordersId);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
}
