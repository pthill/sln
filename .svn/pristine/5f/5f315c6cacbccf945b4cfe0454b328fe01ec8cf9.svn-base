package com.sln.dao.shop.write.supplier;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.supplier.SupplierReturn;

@Repository
public interface SupplierReturnWriteDao {
	
	/**
	 * 根据id修改退货单信息
	 * @param supplierReturn
	 * @return
	 */
	Integer updateSupplierReturn(SupplierReturn supplierReturn);
	
	/**
	 * 插入退货单数据
	 * @param supplierReturn
	 * @return
	 */
	Integer insertSupplierReturn(SupplierReturn supplierReturn);
	
	/**
	 * 批量插入退货单数据
	 * @param list
	 * @return
	 */
	Integer batchInsertSupplierReturn(List<SupplierReturn> list);
	
	Integer updateSupplierReturnByBackId(@Param("returnState") Integer returnState,@Param("backId") Integer backId);
}