package com.sln.service.order;

import java.security.Provider.Service;
import java.util.List;
import java.util.Map;

import com.sln.core.ServiceResult;
import com.sln.dto.ProductDayDto;
import com.sln.entity.order.OrdersProduct;

public interface IOrdersProductService {

    /**
     * 根据id取得网单表
     * @param  ordersProductId
     * @return
     */
    ServiceResult<OrdersProduct> getOrdersProductById(Integer ordersProductId);

    /**
     * 保存网单表
     * @param  ordersProduct
     * @return
     */
    ServiceResult<Integer> saveOrdersProduct(OrdersProduct ordersProduct);

    /**
    * 更新网单表
    * @param  ordersProduct
    * @return
    */
    ServiceResult<Integer> updateOrdersProduct(OrdersProduct ordersProduct);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 根据订单号获取对应的网单
     * @param orderId
     * @return
     */
    ServiceResult<List<OrdersProduct>> getOrdersProductByOId(Integer orderId);

    /**
     * 根据id 取得网单信息
     * @param request
     * @return
     */
    ServiceResult<OrdersProduct> getOrdersProductWithImgById(Integer orderProductId);

    /**
     * 统计商品每天销量
     * @param queryMap
     * @return
     */
    ServiceResult<List<ProductDayDto>> getProductDayDto(Map<String, String> queryMap);
    
    /**
     * 根据订单编号，供应商ID获取网单信息
     */
    ServiceResult<List<OrdersProduct>> getByOrderSn(String orderSn,Integer supplierId);
    
    /**
     * 根据订单ID 获取网单信息 包含图片信息
     */
    ServiceResult<List<OrdersProduct>> getOrdersProductListWithImgById(Integer ordersId);
    

}