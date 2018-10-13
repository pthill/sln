package com.sln.dao.shop.write.order;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.order.Orders;

@Repository
public interface OrdersWriteDao {

    Orders get(Integer id);

    Orders getByOrderSn(String orderSn);

    Integer insert(Orders orders);

    Integer update(Orders orders);

    Integer delete(Integer id);

    Integer updateMoneyBack(@Param("id") Integer id, @Param("moneyBack") String moneyBack);

    /**
     * 关闭所有没有付款的定金订单 
     * @param id
     * @return
     */
    Integer updateCloseActBidding(@Param("actBiddingId") Integer actBiddingId);

    /**
     * 查询所有已经付款的定金订
     * @param id
     * @return
     */
    List<Orders> getActBiddingState5(@Param("actBiddingId") Integer actBiddingId);

    /**
     * 修改支付订单号为订单号（当用户从会员中心订单列表页点击支付的时候执行此方法）
     * @param orderSn
     * @return
     */
    Integer updatePaySn(@Param("orderSn") String orderSn);

    /**
     * 根据支付订单号获取订单
     * @param paySn 支付订单号
     * @return
     */
    List<Orders> getByPaySn(@Param("paySn") String paySn);

    /**
     * 修改订单评价状态
     * @param ordersId
     * @param evaluateState2
     * @return
     */
    Integer updateEvaluateStateById(@Param("id") Integer id,
                                    @Param("evaluateState") Integer evaluateState);

    /**
     * 根据订单ID修改支付订单号
     * @param id
     * @param paySn
     * @return
     */
    Integer updatePaySnById(@Param("id") Integer id, @Param("paySn") String paySn);
    
    /**
     * 根据订单号修改发货状态
     */
    Integer updateDeliverByOrderSn(@Param("orderSn") String orderSn);
}
