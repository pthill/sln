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
import com.sln.entity.supplier.Supplier;
import com.sln.model.supplier.SupplierModel;
import com.sln.service.supplier.ISupplierService;

/***
 * 供应商的实现类
 *                       
 * @Filename: SupplierServiceImpl.java
 * @Version: 1.0
 * @Author: pangsm
 * @Email: shumin.pang@slooong.com
 *
 */
@Service
public class SupplierServiceImpl implements ISupplierService {

    private static Logger log = LogManager.getLogger(SupplierServiceImpl.class);

    @Resource
    private SupplierModel supplierModel;

    @Override
    public ServiceResult<List<Supplier>> getSupplierList(Map<String, String> queryMap,
                                                         PagerInfo pager) {
        ServiceResult<List<Supplier>> serviceResult = new ServiceResult<List<Supplier>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(supplierModel.getSupplierCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(supplierModel.getSupplierList(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][save] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SupplierServiceImpl][page] exception:", e);
        }
        return serviceResult;

    }

    @Override
    public ServiceResult<Boolean> save(Supplier supplier,Integer sellerId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();

        try {
            serviceResult.setResult(supplierModel.saveSupplier(supplier,sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][save] param:" + JSON.toJSONString(supplier));
            log.error("[SupplierServiceImpl][save] exception:", e);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> update(Supplier supplier) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(supplierModel.update(supplier));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][update] param:" + JSON.toJSONString(supplier));
            log.error("[SupplierServiceImpl][update] exception:", e);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> isNameExist(String name,Integer sellerId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(supplierModel.isNameExist(name,sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][isNameExist] param:" + JSON.toJSONString(name));
            log.error("[SupplierServiceImpl][isNameExist] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> isSellerTypeExist(Integer sellerId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(supplierModel.isSellerTypeExist(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][isSellerTypeExist] param:" + JSON.toJSONString(sellerId));
            log.error("[SupplierServiceImpl][isSellerTypeExist] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Supplier> getById(Integer id) {
        ServiceResult<Supplier> serviceResult = new ServiceResult<Supplier>();

        try {
            serviceResult.setResult(supplierModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][getById] param:" + JSON.toJSONString(id));
            log.error("[SupplierServiceImpl][getById] exception:", e);
        }

        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(supplierModel.del(id));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][del] param:" + id);
            log.error("[SupplierServiceImpl][del] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSupplier(Supplier supplier) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(supplierModel.updateSuppplier(supplier));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][updateSupplier] param:" + supplier);
            log.error("[SupplierServiceImpl][updateSupplier] exception:", e);
        }
        return serviceResult;

    }


    @Override
    public ServiceResult<List<Supplier>> getSupplierBySellerId(Integer sellerId) {
        ServiceResult<List<Supplier>> serviceResult = new ServiceResult<List<Supplier>>();
        try {

            serviceResult.setResult(supplierModel.getSupplierBySellerId(sellerId));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SupplierServiceImpl][getSupplierBySellerId] param:" + sellerId);
            log.error("[SupplierServiceImpl][getSupplierBySellerId] exception:", e);
        }
        return serviceResult;
    }

}
