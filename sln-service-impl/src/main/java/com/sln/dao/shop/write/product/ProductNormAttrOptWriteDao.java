package com.sln.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductNormAttrOpt;

@Repository
public interface ProductNormAttrOptWriteDao {

    ProductNormAttrOpt get(java.lang.Integer id);

    Integer save(ProductNormAttrOpt productNormAttrOpt);

    Integer update(ProductNormAttrOpt productNormAttrOpt);

    Integer getCount(Map<String, Object> queryMap);

    List<ProductNormAttrOpt> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}