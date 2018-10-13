package com.sln.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductRegister;
import com.sln.entity.product.ProductType;
import com.sln.entity.seller.SellerParkOperation;

@Repository
public interface ProductRegisterReadDao {

	ProductRegister get(Integer id);

    List<ProductRegister> getProductRegisterPageList(@Param("queryMap") Map<String, String> queryMap,@Param("start")Integer start, @Param("size")Integer size);
	
    /**
     * 获取总记录条数
     * @return
     */
	Integer getRegisterProductCount();

	/**
	 * 默认获取所有反馈信息集合
	 * @return
	 */
	List<ProductRegister> getProductRegistert();

	/**
	 * 根据会员id获取反馈信息
	 * @param currentId
	 * @return
	 */
	List<ProductRegister> getProductRegisterByCurrentId(Integer currentId);

	ProductRegister getMemberId(Integer id);

}
