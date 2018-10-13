package com.sln.dao.shop.write.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductCate;
import com.sln.vo.product.ProductCateVO;

@Repository
public interface ProductCateWriteDao {

    Integer insert(ProductCate productCate);

    Integer update(ProductCate productCate);

    Integer del(Integer id);

    ProductCate get(Integer id);

    ProductCate getByTypeId(@Param("typeId") Integer typeId);

    Integer count(Map<String, String> queryMap);

    List<ProductCateVO> page(Map<String, Object> queryMap);

    List<ProductCate> getByPid(@Param("pid") Integer pid,@Param("type")Integer type);

    List<ProductCate> getBySellerId(Integer sellerId);
    
    int batchInsertProductCate(List<ProductCate> list);
}
