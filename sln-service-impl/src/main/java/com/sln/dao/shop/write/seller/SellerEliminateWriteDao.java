package com.sln.dao.shop.write.seller;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerEliminate;

@Repository
public interface SellerEliminateWriteDao {
 
	SellerEliminate get(java.lang.Integer id);
	
	Integer insert(SellerEliminate sellerEliminate);
	
	Integer update(SellerEliminate sellerEliminate);

	/**
	 * 批量插入
	 * @param sellerEliminatelist
	 * @return
	 */
	Integer insertSellerEliminate(@Param("list")List<SellerEliminate> sellerEliminatelist);

	/*
	 * 批量更新
	 */
	Integer updateBatch(@Param("list")List<SellerEliminate> list);

	
}