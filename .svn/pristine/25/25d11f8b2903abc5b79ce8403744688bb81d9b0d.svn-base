package com.sln.service.supplier;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.supplier.OrderDelivery;
import com.sln.entity.supplier.SupplierReturn;


public interface ISupplierReturnService {

	/**
     * 分页查询
     * @param queryMap
     * @param pager
     * @returns
     */
    ServiceResult<List<SupplierReturn>> page(Map<String, String> queryMap, PagerInfo pager);
    
    /**
     * 新增数据
     * @param supplierReturn
     * @return
     */
    ServiceResult<Integer> insertSupplierReturn(SupplierReturn supplierReturn);
    
    /**
     * 修改数据
     * @param supplierReturn
     * @return
     */
    ServiceResult<Integer> updateSupplierReturn(SupplierReturn supplierReturn);
    
    /**
     * 批量新增数据
     * @param list
     * @return
     */
    ServiceResult<Integer> batchInsertSupplierReturn(List<SupplierReturn> list);
    
    /**
     * 确认收货
     * @param supplierReturn
     */
    ServiceResult<Integer> cofimReceipt(SupplierReturn supplierReturn);
    
    
}