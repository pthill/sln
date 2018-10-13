package com.sln.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.dto.ProductDayDto;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.product.Product;

/**
 * 网单管理
 *                       
 * @Filename: OrdersProductReadDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface OrdersProductReadDao {

    OrdersProduct get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<OrdersProduct> queryList(Map<String, Object> map);

    /**
     * 根据订单ID获取网单
     * @param ordersId
     * @return
     */
    List<OrdersProduct> getByOrderId(@Param("ordersId") Integer ordersId);

    /**
     * 统计商品每天的销量
     * @param queryMap
     * @return
     */
    List<ProductDayDto> getProductDayDto(@Param("queryMap") Map<String, String> queryMap);
    
    /**
     * 根据订单编号和供应商id查询网单数据
     */
    List<OrdersProduct> getByOrderSn(@Param("orderSn") String orderSn,@Param("supplierId") Integer supplierId);
}
