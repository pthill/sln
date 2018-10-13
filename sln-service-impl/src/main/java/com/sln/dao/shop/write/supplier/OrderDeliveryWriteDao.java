package com.sln.dao.shop.write.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.dto.OrderDeliveryDto;
import com.sln.entity.supplier.OrderDelivery;

@Repository
public interface OrderDeliveryWriteDao {
	/**
	 * 插入发货单数据
	 * @param orderDelivery
	 * @return
	 */
	Integer insertOrderDelivery(OrderDelivery orderDelivery);
	
	/**
	 * 根据id修改发货单
	 * @param orderDelivery
	 * @return
	 */
	Integer updateOrderDeliveryById(OrderDelivery orderDelivery);
	
	/**
	 * 批量插入发货单数据
	 */
	Integer batchInsertOrderDelivery(List<OrderDeliveryDto> deliveryList);
}