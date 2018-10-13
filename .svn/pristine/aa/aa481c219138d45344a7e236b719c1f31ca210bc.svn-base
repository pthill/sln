package com.sln.web.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.TimeUtil;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.member.Member;
import com.sln.entity.pcindex.PcIndexBanner;
import com.sln.entity.pcindex.PcIndexFloor;
import com.sln.entity.pcindex.PcIndexImage;
import com.sln.entity.pcindex.PcRecommend;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductCate;
import com.sln.service.cart.ICartService;
import com.sln.service.pcindex.IPcIndexBannerService;
import com.sln.service.pcindex.IPcIndexFloorService;
import com.sln.service.pcindex.IPcIndexImageService;
import com.sln.service.pcindex.IPcRecommendService;
import com.sln.service.product.IProductCateService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.vo.cart.CartInfoVO;
import com.sln.vo.product.FrontProductCateVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 首页controller
 * 首页初始化，以及一些公共的url
 * @Filename: IndexController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class IndexController extends BaseController {

    Logger                              log = Logger.getLogger(this.getClass());

    @Resource
    private IProductCateService         productCateService;
    @Resource
    private ICartService                cartService;
    @Resource
    private IPcIndexBannerService       pcIndexBannerService;
    @Resource
    private IPcIndexFloorService        pcIndexFloorService;
    @Resource
    private IPcRecommendService         pcRecommendService;

    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;
    @Resource
    private IProductFrontService        productFrontService;

    @Resource
    private IPcIndexImageService        pcIndexImageService;

    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap)  {
        return "redirect:/portal/index.html";
    }

    @RequestMapping(value = "index.html", method = { RequestMethod.GET })
    public String def(HttpServletRequest request, HttpServletResponse response,
                      Map<String, Object> dataMap) throws IOException{
     // 取得定时任务存入ServletContext中的首页缓存html字符串
        Object indexObj = request.getServletContext()
            .getAttribute(ConstantsEJS.PC_INDEX_HTML_CACHE);
        if (indexObj != null && indexObj.toString().length() > 0) {
            log.info("-------------缓存取得首页html");
            // 如果对象不为空，直接打印内容
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(indexObj.toString());
            return null;
        } else {
            log.error("-------------直接打开页面");
            // 如果对象为空，说明ServletContext中还未缓存，则直接取数据库数据返回页面打开首页
            initIndex(dataMap, false);
            return "front/index/index";
        }
    }

    /**
     * 缓存时调用
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "cacheIndex.html", method = { RequestMethod.GET })
    public String cacheIndex(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap) {
        initIndex(dataMap, false);
        return "front/index/index";
    }

    @RequestMapping(value = "previewindex", method = { RequestMethod.GET })
    public String previewIndex(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> dataMap) {
        initIndex(dataMap, true);
        return "front/index/index";
    }

    /**
     * 首页初始化方法
     * @param dataMap
     * @param isPreview
     */
    private void initIndex(Map<String, Object> dataMap, boolean isPreview) {
        // 首页轮播图
        ServiceResult<List<PcIndexBanner>> bannerResult = pcIndexBannerService
            .getPcIndexBannerForView(isPreview);
        if (!bannerResult.getSuccess()) {
            log.error(bannerResult.getMessage());
        }
        dataMap.put("bannerList", bannerResult.getResult());

        // 首页固定取6个
        PagerInfo pager = new PagerInfo(6, 1);
        // 首页推荐
        ServiceResult<List<PcRecommend>> hotRecommendResult = pcRecommendService
            .getPcRecommendForView(PcRecommend.RECOMMEND_TYPE_1, isPreview, pager);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        dataMap.put("hotList", hotRecommendResult.getResult());

        //首页楼层
        ServiceResult<List<PcIndexFloor>> floorResult = pcIndexFloorService
            .getPcIndexFloorForView(isPreview);
        if (!floorResult.getSuccess()) {
            log.error(floorResult.getMessage());
        }
        dataMap.put("floorList", floorResult.getResult());

        //首页头部图片去符合条件的第一个图片
        ServiceResult<List<PcIndexImage>> pcIndexImageResultTop = pcIndexImageService
            .getPcIndexImageForView(isPreview, PcIndexImage.TYPE_1);
        if (!pcIndexImageResultTop.getSuccess()) {
            log.error(pcIndexImageResultTop.getMessage());
        }
        List<PcIndexImage> pcIndexImageTops = pcIndexImageResultTop.getResult();
        if (pcIndexImageTops != null && pcIndexImageTops.size() > 0) {
            dataMap.put("pcIndexImageTop", pcIndexImageTops.get(0));
        }

        //首页头部轮播图上浮动图片符合条件的第一个图片
        ServiceResult<List<PcIndexImage>> pcIndexImageResultFloat = pcIndexImageService
            .getPcIndexImageForView(isPreview, PcIndexImage.TYPE_2);
        if (!pcIndexImageResultFloat.getSuccess()) {
            log.error(pcIndexImageResultFloat.getMessage());
        }
        List<PcIndexImage> pcIndexImageFloats = pcIndexImageResultFloat.getResult();
        if (pcIndexImageFloats != null && pcIndexImageFloats.size() > 0) {
            dataMap.put("pcIndexImageFloat", pcIndexImageFloats.get(0));
        }

        //首页头部轮播图下面图片
        ServiceResult<List<PcIndexImage>> pcIndexImageResultDown = pcIndexImageService
            .getPcIndexImageForView(isPreview, PcIndexImage.TYPE_3);
        if (!pcIndexImageResultDown.getSuccess()) {
            log.error(pcIndexImageResultDown.getMessage());
        }
        List<PcIndexImage> pcIndexImageDowns = pcIndexImageResultDown.getResult();
        if (pcIndexImageDowns != null && pcIndexImageDowns.size() > 0) {
            dataMap.put("pcIndexImageDowns", pcIndexImageDowns);
        }

        // 分类
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("type", ProductCate.TYPE_PT);
        ServiceResult<List<FrontProductCateVO>> serviceResult = productCateService
            .getProductCateList(queryMap);

        dataMap.put("cateList", serviceResult.getResult());
    }

    /**
     * 导航所有商品分类 
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/cateList.html", method = { RequestMethod.GET })
    public String getProductCateList(HttpServletRequest request, HttpServletResponse response,
                                     Map<String, Object> dataMap) {

        Map<String, Object> queryMap = new HashMap<String, Object>();
        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
        serviceResult = productCateService.getProductCateList(queryMap);

        dataMap.put("cateList", serviceResult.getResult());

        return "front/commons/cateList";
    }

    /**
     * 右上角 我的购物车
     * @param request
     * @param response
     * @param dataMap
     * @param cartSource 购物车类型 1：正常商品、2积分商品
     * @return
     */
    @RequestMapping(value = "/previewMyCart.html", method = { RequestMethod.GET })
    public String previewMyCart(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap,Integer cartSource) {
        Member member = WebFrontSession.getLoginedUser(request);
        //取购物车信息  产品价格 按照商家来区分
        //查询购物车
        if (member != null) {
            ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(member.getId(),
                null, ConstantsEJS.SOURCE_1_PC, 1,cartSource);
            dataMap.put("cartInfoVO", serviceResult.getResult());
            dataMap.put("cartSource", cartSource);
        }
        return "front/cart/previewcart";
    }

    /**
     * 首页动态加载限时抢购
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/indexqianggou.html", method = { RequestMethod.GET })
    public String indexqianggou(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {
        //取限时抢购
        String today = TimeUtil.getToday();
        ServiceResult<ActFlashSale> result = actFlashSaleService.getEffectiveActFlashSale(
            TimeUtil.strToDate(today + " 00:00:00"), ConstantsEJS.CHANNEL_2);
        ActFlashSale actFlashSale = result.getResult();
        if (actFlashSale != null) {
            List<ActFlashSaleStage> stageList = actFlashSale.getStageList();
            int hour = TimeUtil.getHour();
            int minute = TimeUtil.getMinute();

            ActFlashSaleStage actFlashSaleStageNow = null;//正在开始的限时抢购
            for (Iterator<ActFlashSaleStage> iterator = stageList.iterator(); iterator.hasNext();) {
                ActFlashSaleStage actFlashSaleStage = (ActFlashSaleStage) iterator.next();
                int startTime = actFlashSaleStage.getStartTime();
                int endTime = actFlashSaleStage.getEndTime();
                if (hour >= startTime && hour < endTime) {
                    actFlashSaleStageNow = actFlashSaleStage;
                    break;
                }
            }

            if (actFlashSaleStageNow != null) {
                ServiceResult<List<ActFlashSaleProduct>> servletActFlashSaleProduct = actFlashSaleProductService
                    .getActFlashSaleProductsByStageId(actFlashSaleStageNow.getId());
                List<ActFlashSaleProduct> actFlashSaleProducts = servletActFlashSaleProduct
                    .getResult();
                for (ActFlashSaleProduct actFlashSaleProduct : actFlashSaleProducts) {
                    Product product = productFrontService
                        .getProductById(actFlashSaleProduct.getProductId()).getResult();
                    actFlashSaleProduct.setProduct(product);
                }
                actFlashSaleStageNow.setProductList(actFlashSaleProducts);

                //计算倒计时
                int endTime = actFlashSaleStageNow.getEndTime();
                int hour1 = endTime - hour - 1;
                int minute1 = 60 - minute;

                //                dataMap.put("hour", hour1);
                //                dataMap.put("minute", minute1);
                int countTime = hour1 * 3600 + minute1 * 60;
                dataMap.put("countTime", countTime);

                dataMap.put("actFlashSaleStage", actFlashSaleStageNow);
            }
        }
        return "front/index/flashsale";
    }

}
