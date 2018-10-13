package com.sln.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerCate;

@Repository
public interface SellerCateReadDao {

    SellerCate get(java.lang.Integer id);

    Integer queryCount(Map<String, Object> map);

    List<SellerCate> queryList(Map<String, Object> map);

    List<SellerCate> getByPid(@Param("pid") Integer pid, @Param("sellerId") Integer sellerId);

}
