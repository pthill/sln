package com.sln.service.impl.supplier;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.supplier.SupplierReturn;
import com.sln.model.supplier.SupplierReturnModel;
import com.sln.service.supplier.ISupplierReturnService;

@Service(value = "supplierReturnService")
public class SupplierReturnServiceImpl implements ISupplierReturnService {
	private static Logger      log = LogManager.getLogger(SupplierReturnServiceImpl.class);
	
	@Resource
	private SupplierReturnModel supplierReturnModel;

	@Override
	public ServiceResult<List<SupplierReturn>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<SupplierReturn>> serviceResult = new ServiceResult<List<SupplierReturn>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(supplierReturnModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<SupplierReturn> list = supplierReturnModel.getPage(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierReturnServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SupplierReturnServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<Integer> insertSupplierReturn(
			SupplierReturn supplierReturn) {
		// TODO Auto-generated method stub
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierReturnModel.insertSupplierReturn(supplierReturn));
		}catch(Exception e){
			 log.error("新增供应商退货单失败" + e.getMessage());
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateSupplierReturn(
			SupplierReturn supplierReturn) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierReturnModel.updateSupplierReturn(supplierReturn));
		}catch(Exception e){
			 log.error("修改供应商退货单失败" + e.getMessage());
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> batchInsertSupplierReturn(
			List<SupplierReturn> list) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierReturnModel.batchInsertSupplierReturn(list));
		}catch(Exception e){
			 log.error("批量新增供应商退货单失败" + e.getMessage());
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> cofimReceipt(SupplierReturn supplierReturn) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierReturnModel.cofimReceipt(supplierReturn));
		}catch(Exception e){
			 log.error("供应商退货单发货失败" + e.getMessage());
			 serviceResult.setResult(0);
		}
		return serviceResult;
	}
    
}