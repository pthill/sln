package com.sln.service.supplier;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.supplier.OrderDelivery;

public interface IOrderDeliveryService {

	/**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<OrderDelivery>> page(Map<String, String> queryMap, PagerInfo pager);
    
    /**
     * 插入发货单数据
     */
    ServiceResult<Integer> insertOrderDelivery(OrderDelivery orderDelivery);
    
    /**
     * 修改发货单数据
     */
    ServiceResult<Integer> updateOrderDeliveryById(OrderDelivery orderDelivery);
    
    /**
     * 确认发货
     */
    ServiceResult<Integer> cofimDelivery(OrderDelivery orderDelivery);
}