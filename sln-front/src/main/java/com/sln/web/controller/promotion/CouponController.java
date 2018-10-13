package com.sln.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.coupon.Coupon;
import com.sln.service.promotion.ICouponService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 优惠券controller
 * 
 * @Filename: CouponController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class CouponController extends BaseController {

    @Resource
    private ICouponService  couponService;

    public static final int COUPON_PAGE_SIZE = 18;

    /**
     * 优惠券列表页
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/coupon.html", method = { RequestMethod.GET })
    public String coupon(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);
        Integer memberId = 0;
        if (member != null) {
            memberId = member.getId();
        }

        // 排序
        String sortStr = request.getParameter("s");
        Integer sort = ConvertUtil.toInt(sortStr, 0);
        dataMap.put("sort", sort);

        // 分页
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, COUPON_PAGE_SIZE);

        ServiceResult<List<Coupon>> couponResult = couponService.getCouponsForList(memberId, sort,
            ConstantsEJS.CHANNEL_2, pager);
        if (!couponResult.getSuccess()) {
            dataMap.put("info", couponResult.getMessage());
            return "front/commons/error";
        }

        dataMap.put("couponList", couponResult.getResult());

        String url = request.getRequestURI() + "";
        if (sort != 0) {
            url = url + "?s=" + sort;
        }
        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(), url);
        dataMap.put("page", pm);

        return "front/promotion/couponlist";
    }

}
