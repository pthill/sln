package com.sln.dao.shop.write.order;

import org.springframework.stereotype.Repository;

import com.sln.entity.order.OrderLog;

@Repository
public interface OrderLogWriteDao {

    OrderLog get(Integer id);

    Integer save(OrderLog orderLog);

    //    Integer update(OrderLog orderLog);

    //    Integer del(Integer id);

}
