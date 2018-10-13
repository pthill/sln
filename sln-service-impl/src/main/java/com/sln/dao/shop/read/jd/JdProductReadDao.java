package com.sln.dao.shop.read.jd;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.jd.JdProduct;



@Repository
public interface JdProductReadDao {
	
	/**
	 * 分页获取京东商品数据
	 */
	List<JdProduct> getJdProductList(@Param("start") Integer start,@Param("size") Integer size);
	
	/**
	 * 根据sku查询京东商品数据
	 * @param sku
	 * @return
	 */
	JdProduct getJdProductBySku(@Param("sku")String sku);
}