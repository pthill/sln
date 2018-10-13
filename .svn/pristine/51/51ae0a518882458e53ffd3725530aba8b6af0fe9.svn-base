package com.sln.web.controller.product;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.service.product.IProductGoodsService;
import com.sln.service.product.IProductService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商品管理列表controller
 *                       
 * @Filename: SellerProductListController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/product")
public class SellerProductListController extends BaseController {
    @Resource
    private IProductService      productService;
    @Resource
    private IProductGoodsService productGoodsService;
    private String               baseUrl = "seller/product/pdt/";

    //待售商品(刚创建、提交审核、审核通过、下架)
    @RequestMapping(value = "waitSale", method = { RequestMethod.GET })
    public String waitSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "1,2,3,4,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "listwaitsale";
    }
    //所有商品
    @RequestMapping(value = "saleAll", method = { RequestMethod.GET })
    public String saleAll(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("q_state", "1,2,3,4,5,6,7");//1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
        return baseUrl + "listall";
    }

    //在售商品(上架商品) 可以下架
    @RequestMapping(value = "onSale", method = { RequestMethod.GET })
    public String onSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "6");//6、上架；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "listonsale";
    }

    //已经删除商品(删除) 只读
    @RequestMapping(value = "delSale", method = { RequestMethod.GET })
    public String delSale(HttpServletRequest request, Map<String, Object> dataMap) {
        dataMap.put("q_useYn", "1");
        dataMap.put("q_state", "5");//5、商品删除；
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "listdelsale";
    }

    /**
     * 商品列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> list(HttpServletRequest request,
                                                            Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("id", WebSellerSession.getSellerUser(request).getId() + "");
        ServiceResult<List<Product>> serviceResult = productService.getProductByRoleForSeller(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 根据商品ID查询货品
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list_goods", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductGoods>> listGoods(Integer productId,
                                                                      HttpServletRequest request,
                                                                      Map<String, Object> dataMap) {
        ServiceResult<List<ProductGoods>> serviceResult = productGoodsService
            .getGoodSByProductId(productId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductGoods>> jsonResult = new HttpJsonResult<List<ProductGoods>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return jsonResult;
    }

    /**
     * 商品列表无分页
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "listnopage", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Product>> listNoPage(HttpServletRequest request,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        ServiceResult<List<Product>> serviceResult = productService.pageProduct(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }

}
