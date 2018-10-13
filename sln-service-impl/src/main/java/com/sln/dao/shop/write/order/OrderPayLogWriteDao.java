package com.sln.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.sln.entity.order.OrderPayLog;

@Repository
public interface OrderPayLogWriteDao {

    //    OrderPayLog get(java.lang.Integer id);

    Integer insert(OrderPayLog orderPayLog);

    //    Integer update(OrderPayLog orderPayLog);

    //    Integer queryCount(Map<String, Object> map);

    //    List<OrderPayLog> queryList(Map<String, Object> map);

}
