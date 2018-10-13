package com.sln.web.controller.order;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.sln.entity.member.MemberProductExchange;
import com.sln.entity.order.Orders;
import com.sln.entity.product.Product;
import com.sln.entity.seller.SellerUser;
import com.sln.service.member.IMemberProductExchangeService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;
import com.google.gson.Gson;

/**
 * 卖家换货controller
 * 
 * @Filename: SellerProductExchangeController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/order/productExchange")
public class SellerProductExchangeController extends BaseController {

    @Resource
    private IMemberProductExchangeService memberProductExchangeService;
    @Resource
    private IProductService               productservice;
    @Resource
    private IOrdersService                ordersService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "seller/order/productexchange/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MemberProductExchange>> list(HttpServletRequest request,
                                                                          Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        ServiceResult<List<MemberProductExchange>> serviceResult = memberProductExchangeService
            .getMemberProductExchanges(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<MemberProductExchange>> jsonResult = new HttpJsonResult<List<MemberProductExchange>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 进入处理换货申请页面
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
    	SellerUser user = WebSellerSession.getSellerUser(request);
        ServiceResult<MemberProductExchange> result = memberProductExchangeService
            .getMemberProductExchangeById(id);
        if (!result.getSuccess()) {
            return "seller/order/productexchange/list";
        }
        MemberProductExchange exchange = result.getResult();
        if(exchange == null){
        	return "seller/404";
        }
        if(!exchange.getSeller().equals(user.getSellerId())){
        	return "seller/unauth";
        }
        ServiceResult<Product> prdResult = productservice.getProductById(exchange.getProductId());
        if (prdResult.getSuccess() && prdResult.getResult() != null) {
            exchange.setProductName(prdResult.getResult().getName1());
        }

        ServiceResult<Orders> orderResult = ordersService.getOrdersById(exchange.getOrderId());
        if (orderResult.getSuccess() && orderResult.getResult() != null) {
            exchange.setOrderSn(orderResult.getResult().getOrderSn());
        }
        dataMap.put("obj", exchange);
        return "seller/order/productexchange/edit";
    }

    /**
     * 处理换货申请
     * @param request
     * @param response
     * @param id
     * @param type
     * @param remark
     * @return
     */
    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> audit(HttpServletRequest request, HttpServletResponse response, Integer id,
                      Integer type, String remark) {
        	HttpJsonResult<Integer> result = new HttpJsonResult<Integer>();
        	SellerUser user = WebSellerSession.getSellerUser(request);
            ServiceResult<MemberProductExchange> serviceResult = memberProductExchangeService
                .getMemberProductExchangeById(id);
            if (!serviceResult.getSuccess()) {
                return new HttpJsonResult<Integer>(serviceResult.getMessage());
            }
            MemberProductExchange exchange = serviceResult.getResult();
            if(exchange == null || !exchange.getSeller().equals(user.getSellerId())){
            	return new HttpJsonResult<Integer>("修改失败，请重试");
            }

            //MemberProductExchange ex = new MemberProductExchange();
            //ex.setId(id);
            exchange.setRemark(remark);
            exchange.setState(type);
            
            result.setData(memberProductExchangeService.updateMemberProductExchange(exchange).getResult());
            return result;
    }
}
