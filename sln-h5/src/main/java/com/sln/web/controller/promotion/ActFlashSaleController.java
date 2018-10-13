package com.sln.web.controller.promotion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.TimeUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.flash.ActFlashBanner;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.product.Product;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActFlashBannerService;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.web.controller.BaseController;

/**
 * 整点秒杀
 * @Filename: ActFlashSaleController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
public class ActFlashSaleController extends BaseController {

    @Resource
    private IActFlashSaleService        actFlashSaleService;

    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    @Resource
    private IProductFrontService        productFrontService;

    @Resource
    private IActFlashBannerService      actFlashBannerService;

    /**
     * 活动
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "act-flash-sale.html", method = RequestMethod.GET)
    public String sale(HttpServletRequest request, HttpServletResponse response, ModelMap dataMap) {
        String today = TimeUtil.getToday();
        try {
            ServiceResult<ActFlashSale> result = actFlashSaleService.getEffectiveActFlashSale(
                TimeUtil.strToDate(today + " 00:00:00"), ConstantsEJS.CHANNEL_3);

            if (result.getResult() == null) {
                throw new BusinessException("没有活动");
            }
            //当天活动
            ActFlashSale actFlashSale = result.getResult();
            int hour = TimeUtil.getHour();
            int minute = TimeUtil.getMinute();
            int second = TimeUtil.getSecond();

            //已经结束的活动阶段
            List<ActFlashSaleStage> stageListOver = new ArrayList<ActFlashSaleStage>();
            //正在进行的活动阶段
            ActFlashSaleStage actFlashSaleStageNow = null;

            //当前活动所有阶段
            List<ActFlashSaleStage> stageList = actFlashSale.getStageList();
            List<ActFlashSaleStage> allstage = new ArrayList<ActFlashSaleStage>(stageList);
            for (ActFlashSaleStage actFlashSaleStage : stageList) {
                //当前活动阶段的所有商品
                ServiceResult<List<ActFlashSaleProduct>> servletActFlashSaleProduct = actFlashSaleProductService
                    .getActFlashSaleProductsByStageId(actFlashSaleStage.getId());
                List<ActFlashSaleProduct> actFlashSaleProducts = servletActFlashSaleProduct
                    .getResult();

                for (ActFlashSaleProduct actFlashSaleProduct : actFlashSaleProducts) {
                    //关联商品
                    Product product = productFrontService
                        .getProductById(actFlashSaleProduct.getProductId()).getResult();
                    actFlashSaleProduct.setProduct(product);
                }
                //阶段商品
                actFlashSaleStage.setProductList(actFlashSaleProducts);
            }

            //遍历所有活动阶段
            for (Iterator<ActFlashSaleStage> iterator = stageList.iterator(); iterator.hasNext();) {
                ActFlashSaleStage actFlashSaleStage = iterator.next();
                int startTime = actFlashSaleStage.getStartTime();
                int endTime = actFlashSaleStage.getEndTime();
                if (hour >= startTime && hour < endTime) {
                    //正在进行的阶段
                    actFlashSaleStageNow = actFlashSaleStage;
                    break;
                } else if (hour >= endTime) {
                    //已结束阶段
                    stageListOver.add(actFlashSaleStage);
                    iterator.remove();
                }
            }

            if (actFlashSaleStageNow != null) {
                //计算倒计时
                int endTime = actFlashSaleStageNow.getEndTime();
                int hour1 = endTime - hour - 1;
                int minute1 = 60 - minute;
                int second1 = 60 - second;
                int countTime = hour1 * 3600 + minute1 * 60 + second1;
                dataMap.put("countTime", countTime);
            }

            //正在进行的活动阶段
            dataMap.put("actFlashSaleStageNow", actFlashSaleStageNow);
            //        //活动阶段,排除已结束的
            //        stack.put("stageList", stageList);
            //当前活动所有阶段
            dataMap.put("allstage", allstage);
            //已经结束的活动阶段
            dataMap.put("stageListOver", stageListOver);
            //当天活动
            dataMap.put("actFlashSale", actFlashSale);

            ServiceResult<List<ActFlashBanner>> resultActFlashBanner = actFlashBannerService
                .getActFlashBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
            List<ActFlashBanner> actFlashBanners = resultActFlashBanner.getResult();
            dataMap.put("actFlashBanners", actFlashBanners);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                dataMap.put("errorinfo", e.getMessage());
            } else {
                e.printStackTrace();
            }
        }
        return "h5/promotion/actflashsale";
    }
}
