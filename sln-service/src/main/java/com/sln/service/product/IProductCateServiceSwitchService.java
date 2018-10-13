package com.sln.service.product;

import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductCateServiceSwitch;

public interface IProductCateServiceSwitchService {

	/**
     * 根据id取得product_cate_service_switch对象
     * @param  productCateServiceSwitchId
     * @return
     */
    ServiceResult<ProductCateServiceSwitch> getProductCateServiceSwitchById(Integer productCateServiceSwitchId);
    
    /**
     * 取得product_cate_service_switch对象
     * @return
     */
    ServiceResult<ProductCateServiceSwitch> get();
    
    /**
     * 保存product_cate_service_switch对象
     * @param  productCateServiceSwitch
     * @return
     */
     ServiceResult<Boolean> saveProductCateServiceSwitch(ProductCateServiceSwitch productCateServiceSwitch);
     
     /**
     * 更新product_cate_service_switch对象
     * @param  productCateServiceSwitch
     * @return
     */
     ServiceResult<Boolean> updateProductCateServiceSwitch(ProductCateServiceSwitch productCateServiceSwitch);
}