package com.sln.web.controller.pay;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ServiceResult;
import com.sln.core.SlnConfig;
import com.sln.core.WebUtil;
import com.sln.core.pay.ECardPay;
import com.sln.core.pay.bean.Order;
import com.sln.entity.order.Orders;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductFrontService;
import com.sln.web.controller.BaseController;

/**
 * 一卡通支付接口Controller
 */
@Controller
@RequestMapping(value = "pay")
public class ECardPayController extends BaseController {
    
    /**
     * 日志对象
     */
    private static Logger log = Logger.getLogger(ECardPayController.class);
    
    @Resource
    private IOrdersService  ordersService;
    
    @Resource
    private IProductFrontService productFrontService;
    
    /**
     * 一卡通页面跳转同步通知
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/eCardPayResult.html", method = RequestMethod.POST)
    public String eCardPayResult(HttpServletRequest request, HttpServletResponse response, Map<String, Object> stack) {
        log.info("接收一卡通页面跳转同步通知");
        log.info(WebUtil.getRequestParameters(request));
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding(SlnConfig.ENCODE);
            response.setCharacterEncoding(SlnConfig.ENCODE);
            
            String sign = request.getParameter("sign");//签名
            
            //签名为空返回错误
            if(null == sign){
                log.error("签名错误！");
            } else {
                //存在+字符串说明是未经URL编码
                if(!(sign.indexOf("+") > -1)){
                    sign = URLDecoder.decode(sign, SlnConfig.ENCODE);//对签名进行解码
                }
                //获取一卡通支付平台POST过来的反馈信息
                Order order = new Order();
                order.setTranamt(request.getParameter("tranamt"));//交易金额(分)
                order.setOrderid(request.getParameter("orderid"));//支付平台订单号，支付平台支付成功以后的订单号
                order.setAccount(request.getParameter("account"));//员工账号             
                order.setSno(request.getParameter("sno"));//员工工号
                order.setToaccount(request.getParameter("toaccount"));//商户账号         
                order.setThirdsystem(request.getParameter("thirdsystem")); //系统注册ID，由支付平台分配给接入方的唯一一个ID
                order.setThirdorderid(request.getParameter("thirdorderid"));//第三方订单号，支付平台接入方生成唯一编号
                order.setState(request.getParameter("state"));//交易状态( 1 成功 0待支付 2取消)
                order.setOrderdesc(request.getParameter("orderdesc"));//商品名称
                order.setPraram1(request.getParameter("praram1"));//第三方系统备注字段，订单查询接口，对账原样返回
                order.setActulamt(request.getParameter("actulamt"));//实际交易金额(分)    
                order.setSign(sign);
                
                if(null != request.getParameter("rzdate")){
                    order.setRzdate(Timestamp.valueOf(request.getParameter("rzdate")));//支付平台入账时间，24小时制
                }
                if(null != request.getParameter("jydate")){
                    order.setJydate(Timestamp.valueOf(request.getParameter("jydate"))); //交易日期 (支付平 台与其他 支付系统 实际支付 完成的时间)，24小时制
                }            
                
                SortedMap<String, String> sort = new TreeMap<String, String>();
                sort.put("tranamt", String.valueOf(order.getTranamt()));
                sort.put("orderid", order.getOrderid());
                sort.put("account", String.valueOf(order.getAccount()));
                sort.put("sno", order.getSno());
                sort.put("toaccount", String.valueOf(order.getToaccount()));
                sort.put("thirdsystem", order.getThirdsystem());
                sort.put("thirdorderid", order.getThirdorderid());
                sort.put("state", order.getState());
                sort.put("orderdesc", order.getOrderdesc());
                sort.put("praram1", order.getPraram1());
                
                String signsString = ECardPay.sign(sort);//对参数进行拼接、加密和签名
                String total_fee = new BigDecimal(order.getTranamt()).divide(BigDecimal.valueOf(100)).toString();//交易金额
                String params = ECardPay.jointPara(sort).toString();//返回参数
                
                //验证签名
                if(signsString.contains(order.getSign())){
                  //交易成功
                    if("1".equals(order.getState())){
                        //处理第三方订单
                        //成功返回
                        log.info("处理成功");
                        
                        // 调用订单支付接口
                        ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(order.getThirdorderid(), total_fee, Orders.PAYMENT_CODE_H5ECARDPAY,Orders.PAYMENT_NAME_H5ECARDPAY, order.getOrderid(), params);
                        log.info(orderPayResult.getMessage());
                        if(orderPayResult.getSuccess()){
                            stack.put("msg", "感谢您，订单支付成功！订单号：" + order.getThirdorderid());
                        }else{
                            stack.put("msg", "抱歉，订单支付失败！原因：" + orderPayResult.getMessage());
                        }
                    }
                }else{
                    log.error("签名错误！");
                    stack.put("msg", "抱歉，订单支付失败！");
                }
            }            
        } catch (Exception e) {
            log.warn("一卡通页面跳转同步通知接口异常：" + e.getMessage());
            stack.put("msg", "抱歉，订单支付失败！");
        }
        
//        ServiceResult<List<Product>> resultProduct = productFrontService.getProductsListCart(0, 10);
//        stack.put("products", resultProduct.getResult());
        
        return "h5/order/linepay";
    }
}
