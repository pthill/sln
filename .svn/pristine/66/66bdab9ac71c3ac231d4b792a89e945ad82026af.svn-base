package com.sln.web.controller.settlement;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.excel.CellConfig;
import com.sln.core.excel.ExcelConfig;
import com.sln.core.excel.ExcelManager;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.order.Orders;
import com.sln.entity.seller.Seller;
import com.sln.entity.settlement.Settlement;
import com.sln.entity.settlement.SettlementOp;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.order.IOrdersService;
import com.sln.service.seller.ISellerService;
import com.sln.service.settlement.ISettlementOpService;
import com.sln.service.settlement.ISettlementService;

/**
 * 结算管理controller
 * @Filename: AdminSettlementController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/settlement")
public class AdminSettlementController extends BaseController {

    @Resource(name = "settlementService")
    private ISettlementService        settlementService;

    @Resource(name = "settlementOpService")
    private ISettlementOpService      settlementOpService;

    @Resource(name = "ordersService")
    private IOrdersService            ordersService;

    @Resource(name = "memberProductBackService")
    private IMemberProductBackService memberProductBackService;

    @Resource(name = "sellerService")
    private ISellerService      sellerService;
    
    /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request,Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("roleId", WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        queryMap.put("q_isContributing",Seller.IS_CONTRIBUTING1+"");
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellersByRoleId(queryMap, null);
        
        dataMap.put("contributingList", serviceResult.getResult());
        return "admin/settlement/settlementlist";
    }

    /**
     * 管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Settlement>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Settlement>> serviceResult = settlementService.getSettlements(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        
        serviceResult = settlementService.getSubjectNameBySellerId(serviceResult.getResult());
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Settlement>> jsonResult = new HttpJsonResult<List<Settlement>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 详情页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {

        ServiceResult<Settlement> settlementResult = settlementService.getSettlementById(id);
        if (!settlementResult.getSuccess()) {
            dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
            dataMap.put("message", settlementResult.getMessage());
            return "admin/settlement/settlementlist";
        }
        if (settlementResult.getResult() == null) {
            dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
            dataMap.put("message", "结算账单获取失败！");
            return "admin/settlement/settlementlist";
        }

        dataMap.put("settlement", settlementResult.getResult());

        return "admin/settlement/settlementdetail";
    }

    @RequestMapping(value = "orderlist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Orders>> orderlist(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                Map<String, Object> dataMap,
                                                                Integer settlementId) {

        ServiceResult<Settlement> settlementRlt = settlementService.getSettlementById(settlementId);
        if (!settlementRlt.getSuccess()) {
            return new HttpJsonResult<List<Orders>>();
        }

        Settlement settlement = settlementRlt.getResult();

        String settleCycle = settlement.getSettleCycle();
        // 周期开始时间，1号0时0分0秒
        String startTime = this.getStartTime(settleCycle);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        String endTime = this.getEndTime(settleCycle);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_sellerId", settlement.getSellerId() + "");
        queryMap.put("q_orderState", Orders.ORDER_STATE_5 + "");
        queryMap.put("q_finishStartTime", startTime);
        queryMap.put("q_finishEndTime", endTime);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Orders>> serviceResult = ordersService.getOrders(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Orders>> jsonResult = new HttpJsonResult<List<Orders>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "getSettlementOp", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SettlementOp>> getSettlementOp(HttpServletRequest request,
                                                                            HttpServletResponse response,
                                                                            Integer orderId) {
        ServiceResult<List<SettlementOp>> res = settlementOpService.getSettlementOpByOId(orderId);
        HttpJsonResult<List<SettlementOp>> json = new HttpJsonResult<List<SettlementOp>>();
        json.setRows(res.getResult());
        json.setTotal(res.getResult().size());
        return json;
    }

    @RequestMapping(value = "backlist", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductBack>> backlist(HttpServletRequest request,
                                                                          Map<String, Object> dataMap,
                                                                          Integer settlementId) {

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<Settlement> settlementRlt = settlementService.getSettlementById(settlementId);
        if (!settlementRlt.getSuccess()) {
            return new HttpJsonResult<List<MemberProductBack>>();
        }

        Settlement settlement = settlementRlt.getResult();

        String settleCycle = settlement.getSettleCycle();
        // 周期开始时间，1号0时0分0秒
        String startTime = this.getStartTime(settleCycle);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        String endTime = this.getEndTime(settleCycle);

        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService
            .getSettleBacks(settlement.getSellerId(), startTime, endTime, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductBack>> jsonResult = new HttpJsonResult<List<MemberProductBack>>();
        jsonResult.setRows((List<MemberProductBack>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    private String getStartTime(String settleCycle) {
        // 结算年
        String year = settleCycle.substring(0, 4);
        // 结算月
        String month = settleCycle.substring(4);
        // 周期开始时间，1号0时0分0秒
        String startTime = year + "-" + month + "-01 00:00:00";
        return startTime;
    }

    private String getEndTime(String settleCycle) {
        // 结算年
        String year = settleCycle.substring(0, 4);
        // 结算月
        String month = settleCycle.substring(4);
        // 周期结束时间，周期月最后一个天23时59分59秒
        // 周期最后一天
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, ConvertUtil.toInt(year, 1000));
        cal.set(Calendar.MONDAY, (ConvertUtil.toInt(month, 0) - 1));
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        String endTime = year + "-" + month + "-" + lastDay + " 23:59:59";
        return endTime;
    }

    /**
     * 商家核对后发起结算
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "settleStart", method = { RequestMethod.POST })
    public String settleStart(Settlement settlement, HttpServletRequest request,
                              Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        BigDecimal moneyOther = settlement.getMoneyOther();
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            if (settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_1
                && settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_2) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请选择其他金额类型！");
                return "admin/settlement/settlementdetail";
            }

            if (StringUtil.isEmpty(settlement.getMoneyOtherReason(), true)) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请填写其他金额的产生理由！");
                return "admin/settlement/settlementdetail";
            }
        }

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对刚生产的账单发起结算
        if (!settlementDb.getStatus().equals(Settlement.STATUS_1)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对刚生产的账单发起结算，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            settlementNew.setMoneyOther(settlement.getMoneyOther());
            settlementNew.setMoneyOtherType(settlement.getMoneyOtherType());
            settlementNew.setMoneyOtherReason(settlement.getMoneyOtherReason());
            //            if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_1)) {
            //                settlementNew.setPayable(settlementDb.getPayable().add(settlement.getMoneyOther()));
            //            } else if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_2)) {
            //                settlementNew
            //                    .setPayable(settlementDb.getPayable().subtract(settlement.getMoneyOther()));
            //            }
        }
        settlementNew.setStatus(Settlement.STATUS_2);
        settlementNew.setUpdateUserId(adminUser.getId());
        settlementNew.setUpdateUserName(adminUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "发起结算失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:/admin/settlement";
    }
    
    /**
     * 导出商家结算统计列表   ----待进一步优化，以下三个函数调用可链式编程简化创建单元格等重复工作（到后面再优化）
     * @param request
     * @param dataMap
     * @param response
     * @param userAgent 浏览器类型
     */
    @RequestMapping(value = "importlist",method = {RequestMethod.GET})
    @ResponseBody
    public void importlist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Settlement>> serviceResult = settlementService.getSettlements(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        serviceResult = settlementService.getSubjectNameBySellerId(serviceResult.getResult());
     
        for (Settlement st:serviceResult.getResult()) {
			if(st.getInvoiceStatus()==0){
				st.setFaStamp("无发票");
			}else{
				st.setFaStamp("有发票");
			}
			switch(st.getStatus()){
			case 1:
				//结算状态：1、账单生成；2、平台审核通过；3、商家核对通过；4、商家核对质疑；5、对账完成；6、支付完成
				st.setPaySwitch("账单生成");
				break;
			case 2:
				st.setPaySwitch("平台审核通过");
				break;
			case 3:
				st.setPaySwitch("商家核对通过");
				break;
			case 4:
				st.setPaySwitch("商家核对质疑");
				break;
			case 5:
				st.setPaySwitch("对账完成");
				break;
			case 6:
				st.setPaySwitch("支付完成");
				break;
			}
		}
    
        
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        
        this.export(response, userAgent, serviceResult.getResult());
    }
    
    
    private void export(HttpServletResponse response, String userAgent, List<Settlement> settlements) {
        ExcelConfig<Settlement> config = new ExcelConfig<Settlement>();
        config.setData(settlements);
        config.setExcelVersion(ExcelConfig.ExcelVersion.EXECL_VERSION_2007);
        config.setFileName("商家结算列表");
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        config.setLineConfig(getLineConfig());
        ExcelManager.export(response, config);
    }
    
    /**
     * 设置行参数  --链式调用传参  List<Map<String,String> map>依次遍历创建
     * @return
     */
    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig settleCycle=new CellConfig("结算周期");
        config.put("settleCycle",settleCycle);
        CellConfig sellerName=new CellConfig("店铺");
        config.put("sellerName",sellerName);
        CellConfig moneyOrder = new CellConfig("应收金额");
        config.put("moneyOrder", moneyOrder);
        CellConfig moneyPaidBalance = new CellConfig("余额支付总额");
        config.put("moneyPaidBalance", moneyPaidBalance);
        CellConfig moneyPaidReality = new CellConfig("现金支付总额");
        config.put("moneyPaidReality", moneyPaidReality);
        CellConfig moneyIntegral = new CellConfig("积分转换总额");
        config.put("moneyIntegral", moneyIntegral);
        CellConfig integral = new CellConfig("积分总额");
        config.put("integral", integral);
        CellConfig moneyBack = new CellConfig("退款总额");
        config.put("moneyBack", moneyBack);
        CellConfig moneyIntegralBack = new CellConfig("退回积分总额");
        config.put("moneyIntegralBack", moneyIntegralBack);
        CellConfig moneyOther = new CellConfig("其他金额");
        config.put("moneyOther", moneyOther);
        CellConfig moneyOtherType = new CellConfig("其他金额类型");
        config.put("moneyOtherType", moneyOtherType);
        CellConfig moneyOtherReason = new CellConfig("其他金额理由");
        config.put("moneyOtherReason", moneyOtherReason);
        CellConfig commision = new CellConfig("佣金");
        config.put("commision", commision);
        CellConfig payable = new CellConfig("系统核算应付金额");
        config.put("payable", payable);
        CellConfig paySwitch=new CellConfig("结算状态");
        config.put("paySwitch", paySwitch);
        CellConfig subjectName=new CellConfig("结算主体");
        config.put("subjectName", subjectName);
        CellConfig faStamp=new CellConfig("发票状态");
        config.put("faStamp", faStamp);
        return config;
    }
    
  /**
   * 跳转到上传发票uploadInvoice页面
   */
    @RequestMapping(value = "uploadInvoice", method = { RequestMethod.GET })
    public String uploadInvoice(HttpServletRequest request,Integer id,Map<String, Object> dataMap){
    	dataMap.put("id", id);
    	return "admin/settlement/uploadInvoice";
    }
    
    /**
     * 处理上传的发票，使用到文件上传工具
     * @param settlement
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value="doUploadImage",method = {RequestMethod.POST})
    public String  doUploadImage(Settlement settlement,HttpServletRequest request) throws IllegalStateException, IOException
    {
    	String image = UploadUtil.getInstance().mIndexUploadFile2ImageServer("douploadfile", request);
        if (image != null && !"".equals(image)) {
        	settlement.setUploadImages(image);
        	settlement.setInvoiceStatus(1);
            settlementService.updateSettlement(settlement);
        }
        return "redirect:/admin/settlement";
    }
   
    @RequestMapping(value = "platformExplain", method = { RequestMethod.POST })
    public String platformExplain(Settlement settlement, HttpServletRequest request,Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        if (StringUtil.isEmpty(settlement.getPlatformExplain(), true)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "请输入理由！");
            return "admin/settlement/settlementdetail";
        }

        BigDecimal moneyOther = settlement.getMoneyOther();
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            if (settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_1
                && settlement.getMoneyOtherType() != Settlement.MONEY_OTHER_TYPE_2) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请选择其他金额类型！");
                return "admin/settlement/settlementdetail";
            }

            if (StringUtil.isEmpty(settlement.getMoneyOtherReason(), true)) {
                dataMap.put("settlement", settlement);
                dataMap.put("message", "请填写其他金额的产生理由！");
                return "admin/settlement/settlementdetail";
            }
        }

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对商家质疑的账单进行回复
        if (!settlementDb.getStatus().equals(Settlement.STATUS_4)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对商家质疑的账单进行回复，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        if (moneyOther != null && moneyOther.compareTo(BigDecimal.ZERO) > 0) {
            settlementNew.setMoneyOther(settlement.getMoneyOther());
            settlementNew.setMoneyOtherType(settlement.getMoneyOtherType());
            settlementNew.setMoneyOtherReason(settlement.getMoneyOtherReason());
            //            if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_1)) {
            //                settlementNew.setPayable(settlementDb.getPayable().add(settlement.getMoneyOther()));
            //            } else if (settlement.getMoneyOtherType().equals(Settlement.MONEY_OTHER_TYPE_2)) {
            //                settlementNew
            //                    .setPayable(settlementDb.getPayable().subtract(settlement.getMoneyOther()));
            //            }
        } else {
            settlementNew.setMoneyOther(BigDecimal.ZERO);
            settlementNew.setMoneyOtherType(0);
            settlementNew.setMoneyOtherReason(settlement.getMoneyOtherReason());
        }
        settlementNew.setStatus(Settlement.STATUS_4);
        settlementNew.setPlatformExplain(settlement.getPlatformExplain());
        settlementNew.setUpdateUserId(adminUser.getId());
        settlementNew.setUpdateUserName(adminUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:/admin/settlement";
    }

    /**
     * 平台对商家通过的账单进行完成核对操作
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "checkOver", method = { RequestMethod.POST })
    public String checkOver(Settlement settlement, HttpServletRequest request,
                            Map<String, Object> dataMap) {

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对对账完成的账单进行支付完成
        if (!settlementDb.getStatus().equals(Settlement.STATUS_3)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对通过了商家审核的账单进行完成对账操作，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        SystemAdmin admin = WebAdminSession.getAdminUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_5);
        settlementNew.setUpdateUserId(admin.getId());
        settlementNew.setUpdateUserName(admin.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:/admin/settlement";
    }

    /**
     * 平台向商家打款后更改支付状态
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "payOver", method = { RequestMethod.POST })
    public String payOver(Settlement settlement, HttpServletRequest request,
                          Map<String, Object> dataMap) {

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对对账完成的账单进行支付完成
        if (!settlementDb.getStatus().equals(Settlement.STATUS_5)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对对账完成的账单进行支付完成操作，谢谢！");
            return "admin/settlement/settlementdetail";
        }

        SystemAdmin admin = WebAdminSession.getAdminUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_6);
        settlementNew.setUpdateUserId(admin.getId());
        settlementNew.setUpdateUserName(admin.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "admin/settlement/settlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "admin/settlement/settlementdetail";
        }
        return "redirect:/admin/settlement";
    }
}
