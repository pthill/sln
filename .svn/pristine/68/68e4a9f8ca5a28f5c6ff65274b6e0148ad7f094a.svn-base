package com.sln.web.controller.member;

import java.io.IOException;
import java.util.HashMap;
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

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberCollectionProduct;
import com.sln.entity.member.MemberCollectionSeller;
import com.sln.service.analysis.IAnalysisService;
import com.sln.service.member.IMemberCollectionProductService;
import com.sln.service.member.IMemberCollectionSellerService;
import com.sln.service.product.IProductAskService;
import com.sln.vo.member.FrontMemberCollectionProductVO;
import com.sln.vo.member.FrontMemberCollectionSellerVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 用户中心：我的收藏
 * 
 * @Filename: MemberCollectController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberCollectController extends BaseController {

    @Resource
    private IMemberCollectionSellerService  memberCollectionSellerService;

    @Resource
    private IMemberCollectionProductService memberCollectionProductService;

    @Resource
    private IProductAskService              productAskService;

    @Resource
    private IAnalysisService                analysisService;

    /**
     * 跳转到 收藏界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/collect.html", method = { RequestMethod.GET })
    public String collect(HttpServletRequest request, HttpServletResponse response,
                          Map<String, Object> dataMap) {

        String type = request.getParameter("type");
        if (StringUtil.isEmpty(type, true)) {
            type = "product";
        }
        dataMap.put("type", type);

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);

        ServiceResult<List<FrontMemberCollectionProductVO>> productResult = memberCollectionProductService
            .getCollectionProductByMemberId(queryMap, member.getId(), pager);
        dataMap.put("productList", productResult.getResult());
        dataMap.put("productCount", pager.getRowsCount());

        pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);

        ServiceResult<List<FrontMemberCollectionSellerVO>> sellerResult = memberCollectionSellerService
            .getCollectionSellerByMemberid(member.getId(), pager);
        dataMap.put("sellerList", sellerResult.getResult());
        dataMap.put("sellerCount", pager.getRowsCount());

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);
        return "h5/member/collect/collect";
    }

    /**
     * 加载更多收藏的商品
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/morecollectproduct.html", method = { RequestMethod.GET })
    public String moreCollectProduct(HttpServletRequest request, HttpServletResponse response,
                                     Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        String productPageIndexStr = request.getParameter("pageIndex");
        Integer productPageIndex = ConvertUtil.toInt(productPageIndexStr, 1);

        Map<String, Object> queryMap = new HashMap<String, Object>();
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, productPageIndex);

        ServiceResult<List<FrontMemberCollectionProductVO>> productResult = memberCollectionProductService
            .getCollectionProductByMemberId(queryMap, member.getId(), pager);
        dataMap.put("productList", productResult.getResult());

        return "h5/member/collect/collectproductlist";
    }

    /**
     * 加载更多收藏的商品
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/morecollectseller.html", method = { RequestMethod.GET })
    public String moreCollectSeller(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        String sellerPageIndexStr = request.getParameter("pageIndex");
        Integer sellerPageIndex = ConvertUtil.toInt(sellerPageIndexStr, 1);

        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, sellerPageIndex);

        ServiceResult<List<FrontMemberCollectionSellerVO>> sellerResult = memberCollectionSellerService
            .getCollectionSellerByMemberid(member.getId(), pager);
        dataMap.put("sellerList", sellerResult.getResult());

        return "h5/member/collect/collectsellerlist";
    }

    /**
     * 关注店铺
     * @param request
     * @param response
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/docollectshop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> collectionShop(HttpServletRequest request,
                                                                HttpServletResponse response,
                                                                @RequestParam(value = "sellerId", required = true) Integer sellerId) {
        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
            .collectionSeller(sellerId, member.getId());

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
     * 取消收藏商铺
     * @param request
     * @param response
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "/cancelcollectshop.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> cancelCollectionShop(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      @RequestParam(value = "sellerId", required = true) Integer sellerId) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
            .cancelCollectionSeller(sellerId, member.getId());

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
     * 关注商品
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/docollectproduct.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<MemberCollectionSeller> collectionProduct(HttpServletRequest request,
                                                                                  HttpServletResponse response,
                                                                                  @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();
        serviceResult = memberCollectionProductService.saveMemberCollectionProduct(productId,
            member.getId());

        HttpJsonResult<MemberCollectionSeller> jsonResult = new HttpJsonResult<MemberCollectionSeller>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<MemberCollectionSeller>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 取消收藏商品
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/cancelcollectproduct.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<MemberCollectionProduct> cancelCollectionProduct(HttpServletRequest request,
                                                                                         HttpServletResponse response,
                                                                                         @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();

        serviceResult = memberCollectionProductService.cancelCollectionProduct(productId,
            member.getId());

        HttpJsonResult<MemberCollectionProduct> jsonResult = new HttpJsonResult<MemberCollectionProduct>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<MemberCollectionProduct>(
                    serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

}
