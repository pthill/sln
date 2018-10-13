package com.sln.dao.shop.read.order;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.order.OrderPayLog;

@Repository
public interface OrderPayLogReadDao {

    OrderPayLog get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<OrderPayLog> queryList(Map<String, Object> map);

}
