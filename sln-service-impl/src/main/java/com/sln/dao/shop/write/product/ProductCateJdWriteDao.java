package com.sln.dao.shop.write.product;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductCateJd;

@Repository
public interface ProductCateJdWriteDao {
	
	Integer insert(ProductCateJd productCateJd);
	
	Integer update(ProductCateJd productCateJd);
	
	int batchInsertProductCateJd(List<ProductCateJd> list);
	
	Integer delById(Integer id);
	
	Integer delByProductCateId(Integer productCateId);
}