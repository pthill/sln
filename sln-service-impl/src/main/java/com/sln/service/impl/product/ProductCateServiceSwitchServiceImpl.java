package com.sln.service.impl.product;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.product.ProductCateServiceSwitch;
import com.sln.model.product.ProductCateServiceSwitchModel;
import com.sln.service.product.IProductCateServiceSwitchService;

@Service(value = "productCateServiceSwitchService")
public class ProductCateServiceSwitchServiceImpl implements IProductCateServiceSwitchService {
	private static Logger      log = LogManager.getLogger(ProductCateServiceSwitchServiceImpl.class);
	
	@Resource
	private ProductCateServiceSwitchModel productCateServiceSwitchModel;
    
     /**
     * 根据id取得product_cate_service_switch对象
     * @param  productCateServiceSwitchId
     * @return
     */
    @Override
    public ServiceResult<ProductCateServiceSwitch> getProductCateServiceSwitchById(Integer productCateServiceSwitchId) {
        ServiceResult<ProductCateServiceSwitch> result = new ServiceResult<ProductCateServiceSwitch>();
        try {
            result.setResult(productCateServiceSwitchModel.getProductCateServiceSwitchById(productCateServiceSwitchId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductCateServiceSwitchService][getProductCateServiceSwitchById]根据id["+productCateServiceSwitchId+"]取得product_cate_service_switch对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductCateServiceSwitchService][getProductCateServiceSwitchById]根据id["+productCateServiceSwitchId+"]取得product_cate_service_switch对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 取得product_cate_service_switch对象
     * @return
     */
    @Override
    public ServiceResult<ProductCateServiceSwitch> get() {
        ServiceResult<ProductCateServiceSwitch> result = new ServiceResult<ProductCateServiceSwitch>();
        try {
            result.setResult(productCateServiceSwitchModel.get());
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductCateServiceSwitchService][get]取得product_cate_service_switch对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductCateServiceSwitchService][get]取得product_cate_service_switch对象时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存product_cate_service_switch对象
     * @param  productCateServiceSwitch
     * @return
     */
     @Override
     public ServiceResult<Boolean> saveProductCateServiceSwitch(ProductCateServiceSwitch productCateServiceSwitch) {
     	ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(productCateServiceSwitchModel.saveProductCateServiceSwitch(productCateServiceSwitch));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductCateServiceSwitchService][saveProductCateServiceSwitch]保存product_cate_service_switch对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductCateServiceSwitchService][saveProductCateServiceSwitch]保存product_cate_service_switch对象时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新product_cate_service_switch对象
     * @param  productCateServiceSwitch
     * @return
     * @see com.sln.service.ProductCateServiceSwitchService#updateProductCateServiceSwitch(ProductCateServiceSwitch)
     */
     @Override
     public ServiceResult<Boolean> updateProductCateServiceSwitch(ProductCateServiceSwitch productCateServiceSwitch) {
     	ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(productCateServiceSwitchModel.updateProductCateServiceSwitch(productCateServiceSwitch));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IProductCateServiceSwitchService][updateProductCateServiceSwitch]更新product_cate_service_switch对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IProductCateServiceSwitchService][updateProductCateServiceSwitch]更新product_cate_service_switch对象时出现未知异常：",
                e);
        }
        return result;
     }
}