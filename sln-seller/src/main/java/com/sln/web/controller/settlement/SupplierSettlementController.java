package com.sln.web.controller.settlement;

import java.io.IOException;
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
import com.sln.entity.seller.SellerUser;
import com.sln.entity.settlement.Settlement;
import com.sln.entity.settlement.SettlementOp;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.order.IOrdersService;
import com.sln.service.settlement.ISettlementOpService;
import com.sln.service.settlement.ISettlementService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebSellerSession;

/**
 * 供应商结算controller
 *
 * @Filename: SupplierSettlementController.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
@Controller
@RequestMapping(value = "supplier/settlement")
public class SupplierSettlementController extends BaseController {

    @Resource(name = "settlementService")
    private ISettlementService        settlementService;

    @Resource(name = "settlementOpService")
    private ISettlementOpService      settlementOpService;

    @Resource(name = "ordersService")
    private IOrdersService            ordersService;

    @Resource(name = "memberProductBackService")
    private IMemberProductBackService memberProductBackService;

    /**
     * 初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/settlement/suppliersettlementlist";
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

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
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
            return "seller/settlement/suppliersettlementlist";
        }
        if (settlementResult.getResult() == null) {
            dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
            dataMap.put("message", "结算账单获取失败！");
            return "seller/settlement/suppliersettlementlist";
        }

        dataMap.put("settlement", settlementResult.getResult());

        return "seller/settlement/suppliersettlementdetail";
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
     * 供应商对账单质疑
     * @param settlement
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "checkDoubt", method = { RequestMethod.POST })
    public String checkDoubt(Settlement settlement, HttpServletRequest request,
                             Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        if (StringUtil.isEmpty(settlement.getSellerDoubt(), true)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "请填写质疑理由！");
            return "seller/settlement/suppliersettlementdetail";
        }

        ServiceResult<Settlement> settlementRlt = settlementService
            .getSettlementById(settlement.getId());

        if (!settlementRlt.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", settlementRlt.getMessage());
            return "seller/settlement/suppliersettlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "seller/settlement/suppliersettlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对商家审核通过或者已经质疑过的账单提交质疑
        if (!settlementDb.getStatus().equals(Settlement.STATUS_2)
            && !settlementDb.getStatus().equals(Settlement.STATUS_4)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算账单的状态不对，请核对结算状态！");
            return "seller/settlement/suppliersettlementdetail";
        }

        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_4);
        settlementNew.setSellerDoubt(settlement.getSellerDoubt());
        settlementNew.setUpdateUserId(sellerUser.getId());
        settlementNew.setUpdateUserName(sellerUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "seller/settlement/suppliersettlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "seller/settlement/suppliersettlementdetail";
        }
        return "redirect:/supplier/settlement";
    }

    /**
     * 商家核对通过账单
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
            return "seller/settlement/suppliersettlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "seller/settlement/suppliersettlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对商家审核通过或者已经质疑过的账单审核通过
        if (!settlementDb.getStatus().equals(Settlement.STATUS_2)
            && !settlementDb.getStatus().equals(Settlement.STATUS_4)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算账单的状态不对，请核对结算状态！");
            return "seller/settlement/suppliersettlementdetail";
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_3);
        settlementNew.setUpdateUserId(sellerUser.getId());
        settlementNew.setUpdateUserName(sellerUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "seller/settlement/suppliersettlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "seller/settlement/suppliersettlementdetail";
        }
        return "redirect:/supplier/settlement";
    }

    /**
     * 商家收到支付款后修改状态
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
            return "seller/settlement/suppliersettlementdetail";
        }
        if (settlementRlt.getResult() == null) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "结算信息获取失败，请重试！");
            return "seller/settlement/suppliersettlementdetail";
        }
        Settlement settlementDb = settlementRlt.getResult();
        // 只能对对账完成的账单进行支付完成
        if (!settlementDb.getStatus().equals(Settlement.STATUS_5)) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "只能对对账完成的账单进行支付完成操作，谢谢！");
            return "seller/settlement/suppliersettlementdetail";
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Settlement settlementNew = new Settlement();
        settlementNew.setId(settlement.getId());
        settlementNew.setStatus(Settlement.STATUS_6);
        settlementNew.setUpdateUserId(sellerUser.getId());
        settlementNew.setUpdateUserName(sellerUser.getName());
        settlementNew.setUpdateTime(new Date());

        ServiceResult<Boolean> updateSettlement = settlementService.updateSettlement(settlementNew);

        if (!updateSettlement.getSuccess()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", updateSettlement.getMessage());
            return "seller/settlement/suppliersettlementdetail";
        }
        if (updateSettlement.getResult() == null || !updateSettlement.getResult()) {
            dataMap.put("settlement", settlement);
            dataMap.put("message", "操作失败，请重试！");
            return "seller/settlement/settlementdetail";
        }
        return "redirect:/supplier/settlement";
    }
    
    //导出供应商列表
    @RequestMapping(value = "importlist",method = {RequestMethod.GET})
    @ResponseBody
    public void importlist(HttpServletRequest request,Map<String, Object> dataMap,HttpServletResponse response,@RequestHeader(value = "user-agent") String userAgent){
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Settlement>> serviceResult = settlementService.getSettlements(queryMap,pager);
        for (Settlement st:serviceResult.getResult()) {
			if(st.getInvoiceStatus()==1){
				st.setFaStamp("有发票");
			}else{
				st.setFaStamp("无发票");
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
        config.setFileName("供应商结算列表");
        config.setSheetName(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        config.setUserAgent(userAgent);
        config.setLineConfig(getLineConfig());
        ExcelManager.export(response, config);
    }
    private LinkedHashMap<String, CellConfig> getLineConfig() {
        LinkedHashMap<String, CellConfig> config = new LinkedHashMap<String, CellConfig>();
        CellConfig settleCycle=new CellConfig("结算周期");
        config.put("settleCycle",settleCycle);
        CellConfig sellerName=new CellConfig("供应商");
        config.put("sellerName",sellerName);
        CellConfig moneyOrder = new CellConfig("销售总额");
        config.put("moneyOrder", moneyOrder);
        CellConfig moneyBack = new CellConfig("退款总额");
        config.put("moneyBack", moneyBack);
        CellConfig payable = new CellConfig("系统计算总额");
        config.put("payable", payable);
        CellConfig moneyOther = new CellConfig("其他金额");
        config.put("moneyOther", moneyOther);
        CellConfig moneyOtherType = new CellConfig("其他金额类型");
        config.put("moneyOtherType", moneyOtherType);
        CellConfig moneyOtherReason = new CellConfig("其他金额理由");
        config.put("moneyOtherReason", moneyOtherReason);
        CellConfig commision = new CellConfig("应付金额");
        config.put("commision", commision);
        CellConfig paySwitch=new CellConfig("结算状态");
        config.put("paySwitch", paySwitch);
        CellConfig faStamp = new CellConfig("发票状态");
        config.put("faStamp", faStamp);
        return config;
    }
    
    @RequestMapping(value = "supplieruploadInvoice", method = { RequestMethod.GET })
    public String uploadInvoice(HttpServletRequest request,Integer id,Map<String, Object> dataMap){
    	dataMap.put("id", id);
    	return "seller/settlement/supplieruploadInvoice";
    }
    
   /**
    * 执行上传操作
    */
    @RequestMapping(value="doUploadImage",method = {RequestMethod.POST})
    public String  doUploadImage(Settlement settlement,HttpServletRequest request) throws IllegalStateException, IOException
    {
  	  String image=UploadUtil.getInstance().advUploadFile2ImageServer("uploadfile", request);
  	  if (image != null && !"".equals(image)) {
        	settlement.setUploadImages(image);
        	settlement.setInvoiceStatus(1);
            settlementService.updateSettlement(settlement);
        }
        return "redirect:/supplier/settlement";
    }
}
