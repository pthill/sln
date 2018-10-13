package com.sln.web.controller.order;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.ResponseWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.entity.order.OrdersProduct;
import com.sln.service.order.IOrdersProductService;
import com.sln.web.controller.BaseController;

/**
 * 网单
 *                       
 */
@Controller
@RequestMapping(value = "seller/order/ordersProduct")
public class SellerOrdersProductController extends BaseController {
    @Resource
    private IOrdersProductService ordersProductService;

    @RequestMapping(value = "getOrdersProduct", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrdersProduct>> getOrdersProduct(HttpServletRequest request,
                                                                              HttpServletResponse response,
                                                                              Integer orderId) {
        ServiceResult<List<OrdersProduct>> res = ordersProductService
            .getOrdersProductByOId(orderId);
        HttpJsonResult<List<OrdersProduct>> json = new HttpJsonResult<List<OrdersProduct>>();
        json.setRows(res.getResult());
        json.setTotal(res.getResult().size());
        return json;
    }
    
    /**
     * 根据订单编号，供应商ID获取网单信息
     * @param request
     * @param response
     * @param orderSn
     * @param supplierId
     * @return
     */
    @RequestMapping(value="getByOrderSn", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<OrdersProduct>> getByOrderSn(HttpServletRequest request,
            HttpServletResponse response,String orderSn, Integer supplierId){
    	 ServiceResult<List<OrdersProduct>> res = ordersProductService.getByOrderSn(orderSn, supplierId);
    	 HttpJsonResult<List<OrdersProduct>> json = new HttpJsonResult<List<OrdersProduct>>();
         json.setRows(res.getResult());
         json.setTotal(res.getResult().size());
         return json;
    }
}
