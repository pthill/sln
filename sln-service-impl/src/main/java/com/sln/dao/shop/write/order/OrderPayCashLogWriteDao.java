package com.sln.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.sln.entity.order.OrderPayCashLog;

@Repository
public interface OrderPayCashLogWriteDao {

    //	OrderPayCashLog get(java.lang.Integer id);

    Integer insert(OrderPayCashLog orderPayCashLog);

    //	Integer update(OrderPayCashLog orderPayCashLog);

    //	Integer delete(java.lang.Integer id);

}