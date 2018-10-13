package com.sln.web.controller.index;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.sln.entity.mindex.MIndexBanner;
import com.sln.entity.mindex.MIndexFloor;
import com.sln.entity.mindex.MRecommend;
import com.sln.entity.product.Product;
import com.sln.service.mindex.IMIndexService;
import com.sln.service.mindex.IMRecommendService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.web.controller.BaseController;

/**
 * 首页controller
 * 
 * @Filename: MIndexController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MIndexController extends BaseController {

    @Resource
    private IMIndexService              mIndexService;
    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;
    @Resource
    private IProductFrontService        productFrontService;
    @Resource
    private IMRecommendService          mRecommendService;

    /**
     * 首页
     * @param request
     * @param response
     * @param stack
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "/", method = { RequestMethod.GET })
    public String indexRedirect(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> stack) throws IOException {

        // 取得定时任务存入ServletContext中的首页缓存html字符串
        Object indexObj = request.getServletContext().getAttribute(ConstantsEJS.M_INDEX_HTML_CACHE);
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
            initIndex(stack, true);
            return "h5/index/index";
        }

    }

    /**
     * 首页
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/index.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> stack) {
        return "redirect:/";
    }

    /**
     * 首页缓存调用
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/cacheIndex.html", method = { RequestMethod.GET })
    public String cacheIndex(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> stack) {
        initIndex(stack, false);
        return "h5/index/index";
    }

    /**
     * 首页预览
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/previewindex.html", method = { RequestMethod.GET })
    public String previewIndex(HttpServletRequest request, HttpServletResponse response,
                               Map<String, Object> stack) {
        initIndex(stack, true);
        return "h5/index/index";
    }

    /**
     * 首页初始化方法
     * @param dataMap
     * @param isPreview
     */
    private void initIndex(Map<String, Object> stack, boolean isPreview) {
        ServiceResult<List<MIndexBanner>> bannerResult = mIndexService
            .getMIndexBannerForView(isPreview);
        stack.put("banners", bannerResult.getResult());

        ServiceResult<List<MIndexFloor>> floorResult = mIndexService
            .getMIndexFloorsWithData(isPreview);
        stack.put("floors", floorResult.getResult());

        // 首页固定取6个
        PagerInfo pager = new PagerInfo(6, 1);
        // 首页推荐
        ServiceResult<List<MRecommend>> hotRecommendResult = mRecommendService
            .getMRecommendForView(MRecommend.RECOMMEND_TYPE_1, isPreview, pager);
        if (!hotRecommendResult.getSuccess()) {
            log.error(hotRecommendResult.getMessage());
        }
        stack.put("hotList", hotRecommendResult.getResult());
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
            TimeUtil.strToDate(today + " 00:00:00"), ConstantsEJS.CHANNEL_3);
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
        return "h5/index/flashsale";
    }

}
