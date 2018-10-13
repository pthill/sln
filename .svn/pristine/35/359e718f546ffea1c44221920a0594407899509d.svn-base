package com.sln.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.coupon.CouponUser;
import com.sln.service.member.IMemberService;
import com.sln.service.promotion.ICouponService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 
 * 我的优惠劵                      
 * @Filename: CouponUserController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class CouponUserController extends BaseController {

    @Resource
    private ICouponService couponService;

    @Resource
    private IMemberService memberService;

    @RequestMapping(value = "/coupon-use.html", method = { RequestMethod.GET })
    public String couponUse(HttpServletRequest request, HttpServletResponse response,
                            Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, 8);
        ServiceResult<List<CouponUser>> serviceResult = couponService
            .getCouponUserByMemberIdAndUse(sessionMember.getId(), pager, 1);
        List<CouponUser> couponUsers = serviceResult.getResult();
        for (CouponUser couponUser : couponUsers) {
            if (couponUser.getUseEndTime().getTime() > new Date().getTime()) {
                couponUser.setTimeout(false);
            } else {
                couponUser.setTimeout(true);
            }
        }

        dataMap.put("couponUsers", couponUsers);
        dataMap.put("page", pager);

        //return "front/member/ordercenter/couponuser";
        return "front/portal/orders/couponuser";
    }

    @RequestMapping(value = "/coupon-use-yes.html", method = { RequestMethod.GET })
    public String couponUseYes(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, 5);
        ServiceResult<List<CouponUser>> serviceResult = couponService
            .getCouponUserByMemberIdAndUse(sessionMember.getId(), pager, 0);
        List<CouponUser> couponUsers = serviceResult.getResult();

        String url = request.getRequestURI() + "";

        dataMap.put("couponUsers", couponUsers);
        dataMap.put("page", pager);

        //return "front/member/ordercenter/couponuseryes";
        return "front/portal/orders/couponuseryes";
    }

    /**
     * 用户在线领取优惠券
     * @param request
     * @param response
     * @param couponId
     * @return
     */
    @RequestMapping(value = "/reveivecoupon.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> reveiveCoupon(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               @RequestParam(value = "couponId", required = true) Integer couponId) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        ServiceResult<Boolean> receiveCoupon = couponService.receiveCoupon(couponId,
            sessionMember.getId());
        if (!receiveCoupon.getSuccess()) {
            return new HttpJsonResult<Boolean>(receiveCoupon.getMessage());
        }
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        return jsonResult;
    }

}
