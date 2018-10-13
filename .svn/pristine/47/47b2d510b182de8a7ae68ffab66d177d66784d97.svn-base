package com.sln.service.impl.product;

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
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductGoodsStockRecord;
import com.sln.model.product.ProductGoodsStockRecordModel;
import com.sln.service.product.IProductGoodsStockRecordService;

@Service(value = "productGoodsStockRecordService")
public class ProductGoodsStockRecordServiceImpl implements IProductGoodsStockRecordService {
	private static Logger      log = LogManager.getLogger(ProductGoodsStockRecordServiceImpl.class);
	
	@Resource
	private ProductGoodsStockRecordModel productGoodsStockRecordModel;
    
     /**
     * 根据id取得货品库存记录表
     * @param  productGoodsStockRecordId
     * @return
     */
    @Override
    public ServiceResult<ProductGoodsStockRecord> getProductGoodsStockRecordById(Integer productGoodsStockRecordId) {
        ServiceResult<ProductGoodsStockRecord> result = new ServiceResult<ProductGoodsStockRecord>();
        try {
            result.setResult(productGoodsStockRecordModel.getProductGoodsStockRecordById(productGoodsStockRecordId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductGoodsStockRecordService][getProductGoodsStockRecordById]根据id["+productGoodsStockRecordId+"]取得货品库存记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductGoodsStockRecordService][getProductGoodsStockRecordById]根据id["+productGoodsStockRecordId+"]取得货品库存记录表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     */
     @Override
     public ServiceResult<Integer> saveProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productGoodsStockRecordModel.saveProductGoodsStockRecord(productGoodsStockRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductGoodsStockRecordService][saveProductGoodsStockRecord]保存货品库存记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductGoodsStockRecordService][saveProductGoodsStockRecord]保存货品库存记录表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新货品库存记录表
     * @param  productGoodsStockRecord
     * @return
     * @see com.slooong..service.ProductGoodsStockRecordService#updateProductGoodsStockRecord(ProductGoodsStockRecord)
     */
     @Override
     public ServiceResult<Integer> updateProductGoodsStockRecord(ProductGoodsStockRecord productGoodsStockRecord) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(productGoodsStockRecordModel.updateProductGoodsStockRecord(productGoodsStockRecord));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductGoodsStockRecordService][updateProductGoodsStockRecord]更新货品库存记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductGoodsStockRecordService][updateProductGoodsStockRecord]更新货品库存记录表时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<ProductGoodsStockRecord>> page(Map<String, String> queryMap,
			PagerInfo pager) {
		ServiceResult<List<ProductGoodsStockRecord>> serviceResult = new ServiceResult<List<ProductGoodsStockRecord>>();
        try {
            if (pager != null) {
                pager.setRowsCount(productGoodsStockRecordModel.count(queryMap));
                serviceResult.setPager(pager);
            }
            serviceResult.setResult(productGoodsStockRecordModel.pageProductGoodsStockRecord(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("ProductGoodsStockRecordServiceImpl page queryMap:"
                      + JSON.toJSONString(queryMap) + " pager:" + JSON.toJSONString(pager));
            log.error("ProductGoodsStockRecordServiceImpl page exception:", e);
        }
        return serviceResult;
	}
}