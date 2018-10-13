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
import com.sln.entity.supplier.OrderDelivery;
import com.sln.entity.supplier.SupplierExchange;
import com.sln.model.supplier.SupplierExchangeModel;
import com.sln.service.supplier.ISupplierExchangeService;

@Service(value = "supplierExchangeService")
public class SupplierExchangeServiceImpl implements ISupplierExchangeService {
	private static Logger      log = LogManager.getLogger(SupplierExchangeServiceImpl.class);
	
	@Resource
	private SupplierExchangeModel supplierExchangeModel;

	@Override
	public ServiceResult<List<SupplierExchange>> page(
			Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<SupplierExchange>> serviceResult = new ServiceResult<List<SupplierExchange>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(supplierExchangeModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<SupplierExchange> list = supplierExchangeModel.getPage(queryMap, size, start);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[supplierExchangeServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[supplierExchangeServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<Integer> insertSupplierExchange(
			SupplierExchange supplierExchange) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierExchangeModel.insertSupplierExchange(supplierExchange));
		}catch(Exception e){
			 e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[supplierExchangeServiceImpl][insertSupplierExchange] param:" + JSON.toJSONString(supplierExchange));
	            log.error("[supplierExchangeServiceImpl][insertSupplierExchange] exception:", e);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> updateStateById(
			SupplierExchange supplierExchange) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(supplierExchangeModel.updateStateById(supplierExchange));
		}catch(Exception e){
			 e.printStackTrace();
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            log.error("[supplierExchangeServiceImpl][updateStateById] param:" + JSON.toJSONString(supplierExchange));
	            log.error("[supplierExchangeServiceImpl][updateStateById] exception:", e);
		}
		return serviceResult;
	}
    
}