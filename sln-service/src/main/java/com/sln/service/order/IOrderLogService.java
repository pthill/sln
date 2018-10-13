package com.sln.service.order;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.order.OrderLog;

/**
 * 订单日志接口
 *
 * @Filename: IOrderLogService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IOrderLogService {

    /**
     * 根据id取得订单操作日志表
     * @param  orderLogId
     * @return
     */
    ServiceResult<OrderLog> getOrderLogById(Integer orderLogId);

    /**
     * 保存订单操作日志表
     * @param  orderLog
     * @return
     */
    ServiceResult<Integer> saveOrderLog(OrderLog orderLog);

    /**
     * 根据订单ID 取得日志
     * @param orderId
     * @return
     */
    ServiceResult<List<OrderLog>> getOrderLogByOrderId(Integer orderId);
}