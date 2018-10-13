package com.sln.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductAsk;
import com.sln.entity.product.ProductRegister;


@Repository
public interface ProductRegisterWriteDao {
 
	ProductRegister get(java.lang.Integer productRegisterId);
	
	Integer insert(ProductRegister productRegister);
	
	Integer update(ProductRegister productRegister);

	List<ProductRegister> getProductRegisterPageLis(@Param("queryMap") Map<String, String> queryMap,@Param("start")Integer start, @Param("size")Integer size);
	
	Integer getRegisterProductCoun(@Param("queryMap") Map<String, String> query);

	List<ProductRegister> getProductRegistert();

	//更改状态 ---通过2
	void updateProductRegister(Integer id);
    //更改状态 ----不通过3
	void updateProductStateByIdre(@Param("map")Map<String, Object> datamap);

}