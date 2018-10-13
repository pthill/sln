package com.sln.web.controller.member;

import java.util.Date;
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
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.ProductAsk;
import com.sln.entity.seller.SellerUser;
import com.sln.service.product.IProductAskService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 产品咨询管理controller
 * 
 * @Filename: SellerProdcutAskController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/member/productask")
public class SellerProdcutAskController extends BaseController {

    @Resource
    private IProductAskService productAskService;

    /**
     * 产品咨询管理页面初始化controller接口
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/member/productasklist";
    }

    /**
     * 产品咨询管理页面查询按钮controller接口
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductAsk>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductAsk>> serviceResult = productAskService.getProductAsksWithInfo(
            queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductAsk>> jsonResult = new HttpJsonResult<List<ProductAsk>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "reply", method = { RequestMethod.GET })
    public String reply(HttpServletRequest request,Integer id, Map<String, Object> dataMap) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        ServiceResult<ProductAsk> serviceResult = productAskService.getProductAskById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "seller/member/productasklist";
            }
        }
        ProductAsk productAsk = serviceResult.getResult();
        if(productAsk == null){
        	return "seller/404";
        }
        if(!productAsk.getSellerId().equals(sellerUser.getSellerId())){
        	return "seller/unauth";
        }

        dataMap.put("productAsk", productAsk);
        return "seller/member/productaskedit";
    }

    @RequestMapping(value = "doreply", method = { RequestMethod.POST })
    public String doReply(ProductAsk productAsk, HttpServletRequest request,
                          Map<String, Object> dataMap) {

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        
        ServiceResult<ProductAsk> productAskResult = productAskService.getProductAskById(productAsk.getId());

        if (!productAskResult.getSuccess()) {
        	return "seller/500";
        }
        ProductAsk dbProductAskNew = productAskResult.getResult();
        if(dbProductAskNew == null){
        	return "seller/404";
        }
        if(!dbProductAskNew.getSellerId().equals(sellerUser.getSellerId())){
        	return "seller/unauth";
        }

        ProductAsk productAskNew = new ProductAsk();
        productAskNew.setId(productAsk.getId());
        productAskNew.setReplyContent(productAsk.getReplyContent());
        productAskNew.setReplyId(sellerUser.getId());
        productAskNew.setReplyName(sellerUser.getName());
        productAskNew.setReplyTime(new Date());

        ServiceResult<Boolean> serviceResult = productAskService.updateProductAsk(productAskNew);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("productAsk", productAsk);
                dataMap.put("message", serviceResult.getMessage());
                return "seller/member/productaskedit";
            }
        }
        return "redirect:/seller/member/productask";
    }
}
