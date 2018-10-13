package com.sln.dao.shop.read.supplier;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.supplier.SupplierExchange;


@Repository
public interface SupplierExchangeReadDao {
	/**
	 * 获取列表数据
	 * @param queryMap
	 * @param size
	 * @param start
	 * @return
	 */
	List<SupplierExchange> getPage(@Param("queryMap") Map<String, String> queryMap,@Param("size")Integer size,@Param("start")Integer start);
	
	/**
	 * 获取数据总数
	 * @param queryMap
	 * @return
	 */
	Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);
}