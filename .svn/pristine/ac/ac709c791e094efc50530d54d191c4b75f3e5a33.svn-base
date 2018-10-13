package com.sln.dao.shop.write.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.order.OrdersProduct;

@Repository
public interface OrdersProductWriteDao {

    OrdersProduct get(Integer id);

    /**
     * 根据订单ID和商家ID查询网单
     * @param ordersId 订单ID
     * @param sellerId 商家ID
     * @return
     */
    List<OrdersProduct> getByOrderIdAndSellerId(@Param("ordersId") Integer ordersId,
                                                @Param("sellerId") Integer sellerId);

    /**
     * 根据订单ID获取网单
     * @param ordersId
     * @return
     */
    List<OrdersProduct> getByOrderId(@Param("ordersId") Integer ordersId);

    Integer insert(OrdersProduct ordersProduct);

    Integer update(OrdersProduct ordersProduct);

    Integer del(Integer id);

    /**
     * 修改网单评论状态
     * @param ordersProductId
     * @param isEvaluate1
     * @return
     */
    Integer updateIsEvaluate(@Param("id") Integer id, @Param("isEvaluate") Integer isEvaluate);

    /**
     * 修改网单的退货数量<br>
     * set `back_number`= `back_number` + #{backNumber}
     * @param id
     * @param backNumber
     * @return
     */
    Integer updateBackNumber(@Param("id") Integer id, @Param("backNumber") Integer backNumber);

    /**
     * 修改网单的换货数量<br>
     * set `exchange_number`= `exchange_number` + #{exchangeNumber}
     * @param id
     * @param exchangeNumber
     * @return
     */
    Integer updateExchangeNumber(@Param("id") Integer id,
                                 @Param("exchangeNumber") Integer exchangeNumber);
    
    /**
     * 根据订单号修改网单退货数量
     */
    Integer updateBackNumberByOrderSn(@Param("orderSn") String orderSn);
}
