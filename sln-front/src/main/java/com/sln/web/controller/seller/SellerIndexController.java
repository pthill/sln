package com.sln.web.controller.seller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberCollectionSeller;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerCate;
import com.sln.entity.pcseller.PcSellerIndex;
import com.sln.entity.pcseller.PcSellerIndexBanner;
import com.sln.entity.pcseller.PcSellerRecommend;
import com.sln.service.member.IMemberCollectionSellerService;
import com.sln.service.pcseller.IPcSellerIndexBannerService;
import com.sln.service.pcseller.IPcSellerIndexService;
import com.sln.service.pcseller.IPcSellerRecommendService;
import com.sln.service.product.ISellerCateService;
import com.sln.service.seller.ISellerService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 商家controller
 * 
 * @Filename: FrontSellerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class SellerIndexController extends BaseController {

    @Resource
    private ISellerService                 sellerService;
    @Resource
    private ISellerCateService             sellerCateService;
    @Resource
    private IPcSellerIndexService          pcSellerIndexService;
    @Resource
    private IPcSellerIndexBannerService    pcSellerIndexBannerService;
    @Resource
    private IPcSellerRecommendService      pcSellerRecommendService;
    @Resource
    private IMemberCollectionSellerService memberCollectionSellerService;

    /**
     * 店铺公告
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/store/info-{sellerId}.html", method = { RequestMethod.GET })
    public String info(HttpServletRequest request, HttpServletResponse response,
                       Map<String, Object> dataMap, @PathVariable Integer sellerId) {
        // 查询商户基本信息
        ServiceResult<Seller> sellerResult = sellerService.getSellerById(sellerId);
        if (!sellerResult.getSuccess()) {
            throw new BusinessException(sellerResult.getMessage());
        }

        Seller seller = sellerResult.getResult();
        // 店铺不存在，或者店铺状态不是审核通过状态
        if (seller == null || seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
            return "redirect:/error500.html";
        }
        dataMap.put("sellerInfo", seller);

        // 计算平均分数
        BigDecimal sum = new BigDecimal(seller.getScoreService());
        sum = sum.add(new BigDecimal(seller.getScoreDeliverGoods()));
        sum = sum.add(new BigDecimal(seller.getScoreDescription()));
        BigDecimal avg = sum.divide(new BigDecimal(3), 1, BigDecimal.ROUND_HALF_UP);
        dataMap.put("sellerScoreAvg", avg);

        // 店铺首页信息
        ServiceResult<PcSellerIndex> indexResult = pcSellerIndexService
            .getPcSellerIndexBySellerId(sellerId);
        if (!indexResult.getSuccess()) {
            throw new BusinessException(indexResult.getMessage());
        }
        dataMap.put("sellerIndexInfo", indexResult.getResult());

        // 店铺轮播图
        ServiceResult<List<PcSellerIndexBanner>> bannerResult = pcSellerIndexBannerService
            .getPcSellerIndexBannerForView(sellerId, true);
        if (!bannerResult.getSuccess()) {
            throw new BusinessException(bannerResult.getMessage());
        }
        dataMap.put("bannerList", bannerResult.getResult());

        // 店铺分类
        ServiceResult<List<SellerCate>> serviceResult = sellerCateService
            .getOnuseSellerCate(sellerId);
        if (!serviceResult.getSuccess()) {
            throw new BusinessException(serviceResult.getMessage());
        }
        dataMap.put("cateList", serviceResult.getResult());

        // 用户是否收藏该店铺
        String collected = "false";
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser != null && loginedUser.getId() > 0) {
            ServiceResult<List<MemberCollectionSeller>> collectionRlt = memberCollectionSellerService
                .getBySellerIdAndMId(sellerId, loginedUser.getId());
            if (collectionRlt.getResult() != null) {
                for (MemberCollectionSeller collectionSeller : collectionRlt.getResult()) {
                    if (collectionSeller.getState() != MemberCollectionSeller.STATE_2) {
                        collected = "true";
                        break;
                    }
                }
            }
        }
        dataMap.put("collected", collected);

        return "front/seller/sellerinfo";
    }

    /**
     * 跳转到店铺首页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/store/{sellerId}.html", method = { RequestMethod.GET })
    public String toStoresIndex(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap, @PathVariable Integer sellerId) {
        return initIndex(request, dataMap, sellerId, false);
    }

    /**
     * 跳转到店铺首页(店铺预览调用)
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/storepreview/{sellerId}.html", method = { RequestMethod.GET })
    public String toStorePreviewIndex(HttpServletRequest request, HttpServletResponse response,
                                      Map<String, Object> dataMap, @PathVariable Integer sellerId) {
        return initIndex(request, dataMap, sellerId, true);
    }

    /**
     * 店铺首页初始化
     * @param request
     * @param dataMap
     * @param sellerId
     * @param isPreview
     * @return
     */
    private String initIndex(HttpServletRequest request, Map<String, Object> dataMap,
                             Integer sellerId, boolean isPreview) {
        // 查询商户基本信息
        ServiceResult<Seller> sellerResult = sellerService.getSellerById(sellerId);
        if (!sellerResult.getSuccess()) {
            throw new BusinessException(sellerResult.getMessage());
        }

        Seller seller = sellerResult.getResult();
        // 店铺不存在，或者店铺状态不是审核通过状态
        if (seller == null || seller.getAuditStatus().intValue() != Seller.AUDIT_STATE_2_DONE) {
            return "redirect:/error500.html";
        }
        dataMap.put("sellerInfo", seller);

        // 计算平均分数
        BigDecimal sum = new BigDecimal(seller.getScoreService());
        sum = sum.add(new BigDecimal(seller.getScoreDeliverGoods()));
        sum = sum.add(new BigDecimal(seller.getScoreDescription()));
        BigDecimal avg = sum.divide(new BigDecimal(3), 1, BigDecimal.ROUND_HALF_UP);
        dataMap.put("sellerScoreAvg", avg);

        // 店铺首页信息
        ServiceResult<PcSellerIndex> indexResult = pcSellerIndexService
            .getPcSellerIndexBySellerId(sellerId);
        if (!indexResult.getSuccess()) {
            throw new BusinessException(indexResult.getMessage());
        }
        dataMap.put("sellerIndexInfo", indexResult.getResult());

        // 店铺轮播图
        ServiceResult<List<PcSellerIndexBanner>> bannerResult = pcSellerIndexBannerService
            .getPcSellerIndexBannerForView(sellerId, isPreview);
        if (!bannerResult.getSuccess()) {
            throw new BusinessException(bannerResult.getMessage());
        }
        dataMap.put("bannerList", bannerResult.getResult());

        // 店铺推荐信息
        ServiceResult<List<PcSellerRecommend>> recommendResult = pcSellerRecommendService
            .getPcSellerRecommendForView(sellerId, isPreview);
        if (!recommendResult.getSuccess()) {
            throw new BusinessException(recommendResult.getMessage());
        }
        dataMap.put("recommendList", recommendResult.getResult());

        // 店铺分类
        ServiceResult<List<SellerCate>> serviceResult = sellerCateService
            .getOnuseSellerCate(sellerId);
        if (!serviceResult.getSuccess()) {
            throw new BusinessException(serviceResult.getMessage());
        }
        dataMap.put("cateList", serviceResult.getResult());

        // 用户是否收藏该店铺
        String collected = "false";
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser != null && loginedUser.getId() > 0) {
            ServiceResult<List<MemberCollectionSeller>> collectionRlt = memberCollectionSellerService
                .getBySellerIdAndMId(sellerId, loginedUser.getId());
            if (collectionRlt.getResult() != null) {
                for (MemberCollectionSeller collectionSeller : collectionRlt.getResult()) {
                    if (collectionSeller.getState() != MemberCollectionSeller.STATE_2) {
                        collected = "true";
                        break;
                    }
                }
            }
        }
        dataMap.put("collected", collected);

        return "front/seller/sellerindex";
    }
}
