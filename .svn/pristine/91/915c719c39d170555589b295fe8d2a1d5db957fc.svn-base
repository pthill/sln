package com.sln.web.controller.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.coupon.CouponUser;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.promotion.ICouponService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 用户退货商家管理controller
 *
 * @Filename: SellerProductBackController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/order/productBack")
public class SellerProductBackController extends BaseController {

    Logger                            log = Logger.getLogger(this.getClass());

    @Resource
    private IMemberProductBackService memberProductBackService;
    @Resource
    private ICouponService            couponService;

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
        return "seller/order/productback/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductBack>> list(HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService
            .page(queryMap, pager);
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

    /**
     * 进入处理退货申请页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
    	SellerUser user = WebSellerSession.getSellerUser(request);
        MemberProductBack memberProductBack = this.memberProductBackService
            .getMemberProductBackById(id).getResult();
        if(memberProductBack == null){
        	return "seller/404";
        }
        if(!memberProductBack.getSellerId().equals(user.getSellerId())){
        	return "/seller/unauth";
        }
        dataMap.put("obj", memberProductBack);

        if (memberProductBack != null && memberProductBack.getBackCouponUserId() != null
            && memberProductBack.getBackCouponUserId() > 0) {
            ServiceResult<CouponUser> couponResult = couponService
                .getCouponUserById(memberProductBack.getBackCouponUserId());
            dataMap.put("couponUser", couponResult.getResult());
        }

        return "seller/order/productback/edit";
    }

    /**
     * 处理退货申请页面
     * @param request
     * @param response
     * @param id
     * @param type
     */
    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> audit(HttpServletRequest request, HttpServletResponse response, Integer id,
                      Integer type, String remark,Integer orderId) {
    	HttpJsonResult<Integer> result = new HttpJsonResult<Integer>();
    	SellerUser user = WebSellerSession.getSellerUser(request);
        MemberProductBack memberProductBack = this.memberProductBackService
            .getMemberProductBackById(id).getResult();
        if(memberProductBack == null||!memberProductBack.getSellerId().equals(user.getSellerId())){
        	result.setMessage("处理退货操作失败,请重试。");
        	return result;
        }

        MemberProductBack back = new MemberProductBack();
        back.setId(id);
        if (!StringUtil.isEmpty(remark, true)) {
            back.setRemark(remark);
        }
        if (type.equals(MemberProductBack.STATE_RETURN_2)) {
            back.setStateReturn(MemberProductBack.STATE_RETURN_2);
        } else if (type.equals(MemberProductBack.STATE_RETURN_4)) {
            back.setStateReturn(MemberProductBack.STATE_RETURN_4);
        }
        back.setOrderId(orderId);
        ServiceResult<Integer> upResult= memberProductBackService.updateMemberProductBack(back);
        if(!upResult.getSuccess()){
        	return new HttpJsonResult<Integer>(upResult.getMessage());
        }
        result.setData(upResult.getResult());
        return result;
    }

    /**
     * 确认收货操作
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "takeDeliver", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> takeDeliver(HttpServletRequest request, HttpServletResponse response, Integer id) {

    	SellerUser user = WebSellerSession.getSellerUser(request);
    	HttpJsonResult<Integer> result = new HttpJsonResult<Integer>();
        ServiceResult<MemberProductBack> serviceResult = memberProductBackService
            .getMemberProductBackById(id);
        if (!serviceResult.getSuccess()) {
        	result.setMessage(serviceResult.getMessage());
        	return result;
        }
        MemberProductBack backDB = serviceResult.getResult();
        if(backDB == null || !backDB.getSellerId().equals(user.getSellerId())){
        	result.setMessage("获取退货信息失败，请重试！");
        	return result;
        }
        if (backDB.getStateReturn() != MemberProductBack.STATE_MONEY_2) {
        	result.setMessage("该退货申请不是可收货状态！！");
        	return result;
        }
        MemberProductBack back = new MemberProductBack();
        back.setId(id);
        // 退货状态-已收货
        back.setStateReturn(MemberProductBack.STATE_RETURN_3);
        ServiceResult<Integer> upResult = memberProductBackService.updateMemberProductBack(back);
        if(!upResult.getSuccess()){
        	return new HttpJsonResult<Integer>(upResult.getMessage());
        }
        result.setData(upResult.getResult());
        return result;
    }

}
