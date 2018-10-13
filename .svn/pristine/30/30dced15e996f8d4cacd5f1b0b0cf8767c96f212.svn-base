package com.sln.service.supplier;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.supplier.Supplier;

public interface ISupplierService {

    ServiceResult<List<Supplier>> getSupplierList(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<Boolean> save(Supplier supplier,Integer sellerId);

    ServiceResult<Boolean> update(Supplier supplier);

    ServiceResult<Integer> isNameExist(String name,Integer sellerId);

    ServiceResult<Integer> isSellerTypeExist(Integer sellerId);

    ServiceResult<Supplier> getById(Integer id);

    ServiceResult<Boolean> del(Integer id);

    ServiceResult<Boolean> updateSupplier(Supplier supplier);

    ServiceResult<List<Supplier>> getSupplierBySellerId(Integer sellerId);



}
