package com.sln.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.compain.ComplainRegister;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.member.MemberProductExchange;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerComplaint;
import com.sln.service.compain.IComplainRegisterService;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.member.IMemberProductExchangeService;
import com.sln.service.order.IAdminComplaintService;
import com.sln.service.seller.ISellerService;
import com.sln.vo.seller.SellerComplaintVO;

/**
 * 投诉管理相关action
 *                       
 *
 */
@Controller
@RequestMapping(value = "admin/order/complaint")
public class AdminComplaintController extends BaseController {
    @Resource(name = "adminComplaintServiceImpl")
    IAdminComplaintService        sellerComplaintService;
    @Resource
    IMemberProductBackService     memberProductBackService;
    @Resource
    IMemberProductExchangeService memberProductExchangeService;
    @Resource
    ISellerService sellerService;
    @Resource
	private IComplainRegisterService complainRegisterService;

    Logger                        log      = Logger.getLogger(this.getClass());

    /** 退货 */
    private static final int      SOURCE_1 = 1;
    /** 换货 */
    private static final int      SOURCE_2 = 2;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/order/complaint/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerComplaintVO>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        //        queryMap.put("q_state", String.valueOf(ConstantsEJS.SELLER_COMPLAINT_1));//1、买家投诉待审核
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerComplaintVO>> serviceResult = sellerComplaintService.page(queryMap,
            pager);
        List<SellerComplaintVO> list = serviceResult.getResult();

        //        queryMap.put("q_state", String.valueOf(ConstantsEJS.SELLER_COMPLAINT_4));//4、卖家申诉待审核
        //        serviceResult = sellerComplaintService.page(queryMap,
        //                pager);
        //        list.addAll(serviceResult.getResult());

        HttpJsonResult<List<SellerComplaintVO>> jsonResult = new HttpJsonResult<List<SellerComplaintVO>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 审核页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "auditPage", method = { RequestMethod.GET })
    public String auditPage(HttpServletRequest request, Map<String, Object> dataMap, String id) {
        ServiceResult<SellerComplaintVO> serviceResult = sellerComplaintService
            .getById(Integer.valueOf(id));
        dataMap.put("obj", serviceResult.getResult());
        return "admin/order/complaint/audit";
    }

    /**
     * 投诉仲裁 卖家投诉通过或者商家申诉失败则商家投诉数量加一
     * @param request
     * @param dataMap
     * @param id
     * @param optContent
     * @return
     */
    @RequestMapping(value = "doAudit", method = { RequestMethod.POST })
    public String doAudit(HttpServletRequest request, Map<String, Object> dataMap, String id,
                          String optContent, String stateType) {
        ServiceResult<SellerComplaint> serviceResult = sellerComplaintService
            .getSellerComplaintById(Integer.valueOf(id));
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Assert.notNull(stateType);
        SellerComplaint sc = serviceResult.getResult();

        if (stateType.equals("agree")) {
            //管理员通过,判断是否是商家申拆
            if (sc.getSellerComplaintTime() != null&& sc.getState() == ConstantsEJS.SELLER_COMPLAINT_4){
                //商家申诉通过
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_6);
                }else{
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_3);
                ComplainRegister complainRegister = createComplaint(request, sc);
                complainRegisterService.saveComplainRegister(complainRegister);
                }
        } else {
            //判断是否是商家申拆
            if (sc.getSellerComplaintTime() != null&& sc.getState() == ConstantsEJS.SELLER_COMPLAINT_4){
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_5);
                ComplainRegister complainRegister = createComplaint(request, sc);
                complainRegisterService.saveComplainRegister(complainRegister);
            }else{
                sc.setState(ConstantsEJS.SELLER_COMPLAINT_2);
            }
        }
        sc.setOptContent(optContent);
        ServiceResult<Integer> serviceResult1 = sellerComplaintService.updateSellerComplaint(sc);
        if (!serviceResult1.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult1.getCode())) {
                throw new RuntimeException(serviceResult1.getMessage());
            } else {
                throw new BusinessException(serviceResult1.getMessage());
            }
        }
        return "redirect:/admin/order/complaint";
    }

    /**
     * 买家投诉通过或者卖家申诉失败，创建投诉记录插入到投诉登记表中
     * @param request
     * @param sc
     * @return
     */
	private ComplainRegister createComplaint(HttpServletRequest request,SellerComplaint sc) {
		String adminName = WebAdminSession.getAdminUser(request).getName();
		ComplainRegister complainRegister=new ComplainRegister();
		complainRegister.setCreatePerson(adminName);//创建人
		complainRegister.setComplainPerson(sc.getUserName());//投诉人
		complainRegister.setComplainType("5");//投诉类型
		complainRegister.setDesceinfo(sc.getOptContent());//投诉详情
		complainRegister.setSellerId(sc.getSellerId());//商家编号
		ServiceResult<SellerComplaintVO> sellCompaint = sellerComplaintService.getById(sc.getId());
		if(sellCompaint.getResult().getProductBackId()==0){
			complainRegister.setComplainType("6");//退货投诉
		}else{
			complainRegister.setComplainType("7");//换货投诉
		}
		String sellerName = sellCompaint.getResult().getSellerName();//获取商家名称
		complainRegister.setComplainSeller(sellerName);
		
		return complainRegister;
	}

    /**
     * 进入重置投诉信息页面
     * @return
     */
    @RequestMapping(value = "reset")
    public String toReset(HttpServletRequest request, HttpServletResponse response,
                          Map<String, Object> dataMap, Integer id) {

        //获取投诉信息
        ServiceResult<SellerComplaint> serviceResult = sellerComplaintService
            .getSellerComplaintById(id);
        if (!serviceResult.getSuccess()) {
            dataMap.put("info", serviceResult.getMessage());
            return "admin/500";
        }
        SellerComplaint sellerComplaint = serviceResult.getResult();
        if (sellerComplaint == null) {
            dataMap.put("info", "获取投诉信息失败");
            return "admin/500";
        }

        if (sellerComplaint.getState() == SellerComplaint.STATE_1
            || sellerComplaint.getState() == SellerComplaint.STATE_4) {
            dataMap.put("info", "当前投诉信息不能进行重置操作，请重试。");
            return "admin/500";
        }

        if ((sellerComplaint.getProductBackId() == null
             || sellerComplaint.getProductBackId().intValue() == 0)
            && (sellerComplaint.getProductExchangeId() == null
                || sellerComplaint.getProductExchangeId().intValue() == 0)) {
            dataMap.put("info", "投诉信息有误，请重试");
            return "admin/500";
        }

        if (sellerComplaint.getProductBackId() != null && sellerComplaint.getProductBackId() == 0) {
            //换货
            ServiceResult<MemberProductExchange> exchangeResult = memberProductExchangeService
                .getMemberProductExchangeById(sellerComplaint.getProductExchangeId());

            if (!exchangeResult.getSuccess()) {
                dataMap.put("info", exchangeResult.getMessage());
                return "admin/500";
            }
            MemberProductExchange memberProductExchange = exchangeResult.getResult();
            if (memberProductExchange == null) {
                dataMap.put("info", "获取换货信息失败，请重试");
                return "admin/500";
            }
            dataMap.put("source", SOURCE_2);
            dataMap.put("obj", memberProductExchange);
        } else {
            //换货
            ServiceResult<MemberProductBack> backResult = memberProductBackService
                .getMemberProductBackById(sellerComplaint.getProductBackId());

            if (!backResult.getSuccess()) {
                dataMap.put("info", backResult.getMessage());
                return "admin/500";
            }
            MemberProductBack memberProductBack = backResult.getResult();
            if (memberProductBack == null) {
                dataMap.put("info", "获取退货信息失败，请重试");
                return "admin/500";
            }
            if (memberProductBack.getStateMoney() == null
                || memberProductBack.getStateMoney() != MemberProductBack.STATE_MONEY_1) {
                dataMap.put("info", "该退货已经退款，不能重置");
                return "admin/500";
            }
            dataMap.put("source", SOURCE_1);
            dataMap.put("obj", memberProductBack);
        }
        dataMap.put("id", id);
        return "admin/order/complaint/reset";
    }

    /**
     * 执行重置申诉操作
     * @param request
     * @param response
     * @param id
     * @param source
     * @param state
     * @param backExchangeId
     * @return
     */
    @RequestMapping(value = "doreset")
    public @ResponseBody HttpJsonResult<Boolean> doReset(HttpServletRequest request,
                                                         HttpServletResponse response, Integer id,
                                                         Integer source, Integer state,
                                                         Integer backExchangeId) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        ServiceResult<Boolean> serviceResult = sellerComplaintService.resetState(id, source, state,
            backExchangeId);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        jsonResult.setData(serviceResult.getResult());

        return jsonResult;
    }

}
