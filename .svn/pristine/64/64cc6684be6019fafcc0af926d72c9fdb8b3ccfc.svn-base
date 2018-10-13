package com.sln.dao.shop.read.supplier;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.supplier.Supplier;

/***
 * 
 *                       
 * @Filename: SupplierReadDao.java
 * @Version: 1.0
 * @Author: pangsm
 * @Email: anna_p@yeah.net
 *
 */
@Repository
public interface SupplierReadDao {
    /***
     * 数据过滤
     * 根据商家id查看商家下的供应商列表
     * @param start
     * @param size
     * @return
     */
    List<Supplier> getSupplierList(@Param("queryMap") Map<String, String> queryMap,
                                   @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 数据过滤
     * 统计商家id查看商家下的供应商
     * @param queryMap
     * @return
     */
    Integer getSupplierCount(@Param("queryMap") Map<String, String> queryMap);
    /**
     * 根据商家id获取供应商
     * @param sellerId
     * @return
     */
    List<Supplier> getSupplierBySellerId(Integer sellerId);

    /**
     * 通过id查询供应商
     * @param id
     * @return
     */
    Supplier getById(Integer id);

    /**
     * 供应商类型，同一商家家只允许一个商家供应的供应商类型
     * @return
     */
    Integer isSellerTypeExist(Integer sellerId);

    /**
     * 判断名称是否已经存在
     * @param name
     * @return
     */
    Integer isNameExist(@Param("name") String name,@Param("sellerId") Integer sellerId);

}