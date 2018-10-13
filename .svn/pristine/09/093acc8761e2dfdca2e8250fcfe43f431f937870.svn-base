package com.sln.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.ui.ModelMap;

import com.sln.entity.seller.SellerEliminate;

public interface SellerEliminateReadDao {

	/**
	 * 根据i,kindtypeone来判断是否有记录，如果有就更新，没有就插入
	 * @param i
	 * @param kindtypeone
	 * @return
	 */
	Integer getSellerEliminateByKindTypeAndItems(@Param("i")int i,@Param("kindtypeone")String kindtypeone);

	Integer getByKindTypeAndItems(@Param("modelmap")Map<String, Object> modelmap);

	/*
	 * 获取总记录
	 */
	Integer getcount();

	//获取集合
	List<SellerEliminate> getSellerEliminate();

}
