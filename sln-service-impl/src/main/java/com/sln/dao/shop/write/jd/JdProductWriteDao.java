package com.sln.dao.shop.write.jd;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.core.jd.bean.JdProductDto;


@Repository
public interface JdProductWriteDao {
	
	/**
	 * 批量插入商品详情
	 * @param list
	 * @return
	 */
	int batchInsertProductDetail(List<JdProductDto> list);
	
	/**
	 * 修改商品详情中的价格
	 * @return
	 */
	int updatePriceBySku(@Param("price") BigDecimal price,@Param("jdprice") BigDecimal jdprice,@Param("sku") String sku);
	
	/**
	 * 修改商品上下架状态
	 */
	int updateStuStateBySku(@Param("skuState") Integer skuState,@Param("sku")String sku);

	/**
	 * 批量修改商品价格
	 */
	int updateBatchPriceBySku(List<Map> list);
	
	/**
	 * 批量修改上下架状态
	 */
	int updateBatchStuStateBySku(List<Map> list);
}