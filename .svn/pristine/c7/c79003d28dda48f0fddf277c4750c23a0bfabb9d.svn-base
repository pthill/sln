package com.sln.dao.shop.read.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.product.ProductCate;

@Repository
public interface ProductCateReadDao {

    ProductCate get(Integer id);

    Integer queryCount(Map<String, Object> map);

    List<ProductCate> queryList(Map<String, Object> map);

    /**
     * 根据Pid获取分类列表
     * @param pid
     * @return
     */
    List<ProductCate> getByPid(Integer pid);

    /**
     * 根据分类的名称查询分类
     * @param name
     * @return
     */
    ProductCate getProductCateByName(@Param("name") String name);
    
    /**
     * 查询已经被取消的京东分类
     */
    List<ProductCate>getJdCateCancel();
    
    /**
     * 根据京东分类catId查询分类id
     * @return
     */
    ProductCate getCateByjdCatId(@Param("jdCatId") Integer jdCatId);
    
    
    /**
     * 查找未建立关系的京东类型的平台分类
     * @return
     */
    List<ProductCate>getUnCateJd();

}
