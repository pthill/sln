package com.sln.service.seller;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.seller.SellerEliminate;


public interface ISellerEliminateService {

	/**
     * 根据id取得淘汰机制表
     * @param  sellerEliminateId
     * @return
     */
    ServiceResult<SellerEliminate> getSellerEliminateById(Integer sellerEliminateId);
    
    /**
     * 保存淘汰机制表
     * @param  sellerEliminate
     * @return
     */
     ServiceResult<Integer> saveSellerEliminate(SellerEliminate sellerEliminate);
     
     /**
     * 更新淘汰机制表
     * @param  sellerEliminate
     * @return
     */
     ServiceResult<Integer> updateSellerEliminate(SellerEliminate sellerEliminate);

     /**
      * 批量插入
      * @param sellerEliminatelist
      */
     ServiceResult<Integer> insertlist(List<SellerEliminate> sellerEliminatelist);

     /**
      * 获取总条数
      * @return
      */
	Integer getcount();

	/**
	 * 批量更新
	 * @param list
	 */
	ServiceResult<Integer> updateBatch(List<SellerEliminate> list);

	//获取淘汰表中四条记录
	List<SellerEliminate> getSellerEliminateList();
}