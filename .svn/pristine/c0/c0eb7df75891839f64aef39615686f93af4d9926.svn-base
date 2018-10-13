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
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.analysis.ProductLookLog;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberCollectionProduct;
import com.sln.entity.member.MemberCollectionSeller;
import com.sln.entity.pcseller.PcSellerIndex;
import com.sln.service.analysis.IAnalysisService;
import com.sln.service.member.IMemberCollectionProductService;
import com.sln.service.member.IMemberCollectionSellerService;
import com.sln.service.pcseller.IPcSellerIndexService;
import com.sln.service.product.IProductAskService;
import com.sln.vo.member.FrontMemberCollectionProductVO;
import com.sln.vo.member.FrontMemberCollectionSellerVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 用户中心：我的收藏、我的咨询
 * 
 * @Filename: MyattentionController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MyattentionController extends BaseController {

    @Resource
    private IMemberCollectionSellerService  memberCollectionSellerService;

    @Resource
    private IMemberCollectionProductService memberCollectionProductService;

    @Resource
    private IProductAskService              productAskService;

    @Resource
    private IAnalysisService                analysisService;

    @Resource
    private IPcSellerIndexService           pcSellerIndexService;

    /**
     * 跳转到 收藏的商品界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/collectproduct.html", method = { RequestMethod.GET })
    public String toCollectionGoods(HttpServletRequest request, HttpServletResponse response,
                                    Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Map<String, Object> queryMap = WebUtil.handlerQueryMapNoQ(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,20);

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<List<FrontMemberCollectionProductVO>> serviceResult = new ServiceResult<List<FrontMemberCollectionProductVO>>();
        serviceResult = memberCollectionProductService.getCollectionProductByMemberId(queryMap,
            member.getId(), pager);

        dataMap.put("productList", serviceResult.getResult());
        dataMap.put("page", pager);

        //return "front/member/collection/collectiongoods";
        return "front/portal/collection/collectiongoods";
    }

    /**
     * 跳转到 收藏的店铺界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/collectshop.html", method = { RequestMethod.GET })
    public String toCollectionShop(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {
    	
    	this.head(0,dataMap,request);
        //Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,20);

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<List<FrontMemberCollectionSellerVO>> serviceResult = memberCollectionSellerService
            .getCollectionSellerByMemberid(member.getId(), pager);

        List<FrontMemberCollectionSellerVO> sellerList = serviceResult.getResult();
        for (FrontMemberCollectionSellerVO frontMemberCollectionSellerVO : sellerList) {
            // 店铺首页信息
            ServiceResult<PcSellerIndex> indexResult = pcSellerIndexService
                .getPcSellerIndexBySellerId(frontMemberCollectionSellerVO.getSellerId());
            PcSellerIndex pcSellerIndex = indexResult.getResult();
            if (pcSellerIndex != null) {
                frontMemberCollectionSellerVO.setSellerLogo(pcSellerIndex.getLogo());
            }
        }

        dataMap.put("sellerList", sellerList);

        dataMap.put("page", pager);

        //return "front/member/collection/collectionshop";
        return "front/portal/collection/collectionshop";
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

        ServiceResult<MemberCollectionProduct> serviceResult = memberCollectionProductService
            .cancelCollectionProduct(productId, member.getId());

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

    /**
     * 跳转到 商品浏览界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/viewed.html", method = { RequestMethod.GET })
    public String viewed(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,20);

        ServiceResult<List<ProductLookLog>> serviceResult = analysisService
            .getProductLookLogs(queryMap, pager);

        dataMap.put("lookLogList", serviceResult.getResult());
        dataMap.put("page", pager);

        //return "front/member/collection/productlooklog";
        return "front/portal/collection/productlooklog";
    }
}
