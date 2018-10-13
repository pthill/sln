package com.sln.web.controller.promotion;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.entity.seller.SellerUser;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 限时抢购活动管理controller
 *                       
 * @Filename: SellerActFlashSaleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/promotion/flash")
public class SellerActFlashSaleController extends BaseController {

    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    /**
     * 限时抢购活动列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/promotion/flash/actflashsalelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFlashSale>> list(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId().toString());

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActFlashSale>> serviceResult = actFlashSaleService
            .getActFlashSales(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFlashSale> list = serviceResult.getResult();

        HttpJsonResult<List<ActFlashSale>> jsonResult = new HttpJsonResult<List<ActFlashSale>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 进入活动详情页
     * @param actFlashSaleId
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "apply", method = { RequestMethod.GET })
    public String apply(int actFlashSaleId, HttpServletRequest request,
                        Map<String, Object> dataMap) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        dataMap.put("sellerId", sellerUser.getSellerId().toString());

        ServiceResult<ActFlashSale> serviceResult = actFlashSaleService
            .getActFlashSaleByIdAndSellerId(actFlashSaleId, sellerUser.getSellerId());

        if (!serviceResult.getSuccess()) {
            return "seller/500";
        }
        
        ActFlashSale actFlashSale = serviceResult.getResult();
        if(actFlashSale==null){
        	return "seller/404";
        }

        dataMap.put("actFlashSale", actFlashSale);
        dataMap.put("stageList", actFlashSale.getStageList());
        return "seller/promotion/flash/actflashsaleedit";
    }

    @RequestMapping(value = "saveproduct", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> saveProduct(HttpServletRequest request,
                                                             Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        String productIdStr = request.getParameter("productId");
        String priceStr = request.getParameter("price");
        String stockStr = request.getParameter("stock");
        String stageIdStr = request.getParameter("stageId");
        String actFlashSaleIdStr = request.getParameter("actFlashSaleId");

        if (StringUtil.isEmpty(productIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择商品");
        }
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        if (productId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择商品");
        }

        if (StringUtil.isEmpty(priceStr, true)) {
            return new HttpJsonResult<Boolean>("请填写商品的活动价格");
        }
        BigDecimal price = new BigDecimal(priceStr);
        if (price.compareTo(BigDecimal.ZERO) <= 0) {
            return new HttpJsonResult<Boolean>("商品价格必须大于0");
        }

        if (StringUtil.isEmpty(stockStr, true)) {
            return new HttpJsonResult<Boolean>("请填写商品的活动库存");
        }
        Integer stock = ConvertUtil.toInt(stockStr, 0);
        if (stock.equals(0)) {
            return new HttpJsonResult<Boolean>("商品库存必须大于0");
        }

        if (StringUtil.isEmpty(stageIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动阶段");
        }
        Integer stageId = ConvertUtil.toInt(stageIdStr, 0);
        if (stageId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动阶段");
        }

        if (StringUtil.isEmpty(actFlashSaleIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动");
        }
        Integer actFlashSaleId = ConvertUtil.toInt(actFlashSaleIdStr, 0);
        if (actFlashSaleId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动");
        }

        //当前活动当前阶段只能添加一次商品
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("productId", productId);
        param.put("sellerId", sellerUser.getSellerId());
        param.put("actFlashSaleId", actFlashSaleId);
        param.put("actFlashSaleStageId", stageId);
        ServiceResult<List<ActFlashSaleProduct>> activityProducts = actFlashSaleProductService
            .getActFlashSaleProduct(param);
        if (activityProducts.getResult().size() > 0) {
            //            return new HttpJsonResult<Boolean>("该商品在当前活动阶段已添加，请选择其它商品");
            //如果存在，更新价格和库存
            for (ActFlashSaleProduct asp : activityProducts.getResult()) {
                if (asp.getProductId().intValue() == productId) {
                    //如果值发生改变再更新
                    if (asp.getPrice().doubleValue() != price.doubleValue()
                        || asp.getStock().doubleValue() != stock.doubleValue()) {
                        asp.setPrice(price);
                        asp.setStock(stock);
                        actFlashSaleProductService.updateActFlashSaleProduct(asp);
                    }
                }
            }
        }

        ActFlashSaleProduct actFlashSaleProduct = new ActFlashSaleProduct();
        actFlashSaleProduct.setActFlashSaleId(actFlashSaleId);
        actFlashSaleProduct.setActFlashSaleStageId(stageId);
        actFlashSaleProduct.setSellerId(sellerUser.getSellerId());
        actFlashSaleProduct.setProductId(productId);
        actFlashSaleProduct.setPrice(price);
        actFlashSaleProduct.setStock(stock);
        actFlashSaleProduct.setActualSales(0);
        actFlashSaleProduct.setStatus(ActFlashSaleProduct.STATUS_1);
        actFlashSaleProduct.setCreateUserId(sellerUser.getId());
        actFlashSaleProduct.setCreateUserName(sellerUser.getName());
        actFlashSaleProduct.setCreateTime(new Date());
        actFlashSaleProduct.setUpdateUserId(sellerUser.getId());
        actFlashSaleProduct.setUpdateUserName(sellerUser.getName());
        actFlashSaleProduct.setUpdateTime(new Date());

        ServiceResult<Boolean> serviceResult = actFlashSaleProductService
            .saveActFlashSaleProduct(actFlashSaleProduct);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return new HttpJsonResult<Boolean>();
    }

    @RequestMapping(value = "applydelete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> applyDelete(HttpServletRequest request) {

        String productIdStr = request.getParameter("productId");
        String stageIdStr = request.getParameter("stageId");

        if (StringUtil.isEmpty(productIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择商品");
        }
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        if (productId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择商品");
        }

        if (StringUtil.isEmpty(stageIdStr, true)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动阶段");
        }
        Integer stageId = ConvertUtil.toInt(stageIdStr, 0);
        if (stageId.equals(0)) {
            return new HttpJsonResult<Boolean>("请选择商品参加的活动阶段");
        }

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<Boolean> serviceResult = actFlashSaleProductService
            .deleteActFlashSaleProductForSeller(stageId, sellerUser.getSellerId(), productId,
                sellerUser.getId(), sellerUser.getName());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
