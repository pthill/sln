package com.sln.web.controller.promotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.web.controller.BaseController;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.flash.ActFlashSaleProduct;
import com.sln.service.promotion.IActFlashSaleProductService;
import com.sln.service.promotion.IActFlashSaleService;

/**
 * 限时抢购活动商品表相关action
 *                       
 * @Filename: ActFlashSaleProductController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/flash")
public class ActFlashSaleProductController extends BaseController {
    @Resource
    private IActFlashSaleService        actFlashSaleService;
    @Resource
    private IActFlashSaleProductService actFlashSaleProductService;

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "product/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFlashSaleProduct>> productlist(HttpServletRequest request,
                                                                               ModelMap dataMap,
                                                                               Integer actFlashSaleId) {
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("actFlashSaleId", actFlashSaleId);
        ServiceResult<List<ActFlashSaleProduct>> serviceResult = actFlashSaleProductService
            .getActFlashSaleProduct(queryMap);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFlashSaleProduct> datalist = serviceResult.getResult();
        HttpJsonResult<List<ActFlashSaleProduct>> jsonResult = new HttpJsonResult<List<ActFlashSaleProduct>>();
        jsonResult.setRows(datalist);
        jsonResult.setTotal(datalist.size());

        return jsonResult;
    }

}
