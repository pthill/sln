package com.sln.dao.shop.read.jd;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.jd.JdCommoditypool;


@Repository
public interface JdCommoditypoolReadDao {
 
	/**
	 * 分页获取商品池子表
	 * @param start
	 * @param size
	 * @return
	 */
	List<JdCommoditypool> getJdCommoditypool(@Param("start") Integer start,@Param("size") Integer size);
	
	/**
	 * 分页获取临时sku表数据
	 */
	List<String> getSku(@Param("start") Integer start,@Param("size") Integer size,@Param("state") Integer state);
}