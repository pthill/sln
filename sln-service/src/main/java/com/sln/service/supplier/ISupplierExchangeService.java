package com.sln.service.supplier;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.supplier.SupplierExchange;


public interface ISupplierExchangeService {

	/**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SupplierExchange>> page(Map<String, String> queryMap, PagerInfo pager);
    
    /**
     * 新增数据
     */
    ServiceResult<Integer> insertSupplierExchange(SupplierExchange supplierExchange);
    
    /**
     * 修改换货单状态,并且修改换货申请单状态
     * @param supplierExchange
     * @return
     */
    ServiceResult<Integer> updateStateById(SupplierExchange supplierExchange);
}