package com.sln.dao.shop.read.supplier;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.supplier.SupplierReturn;

@Repository
public interface SupplierReturnReadDao {
	
	/**
	 * 获取列表数据
	 * @param queryMap
	 * @param size
	 * @param start
	 * @return
	 */
	List<SupplierReturn> getPage(@Param("queryMap") Map<String, String> queryMap,@Param("size")Integer size,@Param("start")Integer start);
	
	/**
	 * 获取数据总数
	 * @param queryMap
	 * @return
	 */
	Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);
	
	/**
	 * 根据退货申请单id查询退货单信息和供应商信息
	 */
	List<SupplierReturn> getSupplierReturnByBckId(@Param("queryMap") Map<String, String> queryMap);
	
	/**
	 * 根据订单id查询需要插入的退货单信息
	 * @param orderId
	 * @return
	 */
	List<SupplierReturn> getSupplierReturnByOrderId(@Param("orderId") Integer orderId);
}