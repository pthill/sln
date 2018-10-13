package com.sln.dao.shop.write.product;

import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductCateServiceSwitch;

@Repository
public interface ProductCateServiceSwitchWriteDao {
 
	ProductCateServiceSwitch get(java.lang.Integer id);
	
	Integer insert(ProductCateServiceSwitch productCateServiceSwitch);
	
	Integer update(ProductCateServiceSwitch productCateServiceSwitch);
}