package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.ShopActive;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ShopActiveReadDao {

    ShopActive get(java.lang.Integer id);

    List<ShopActive> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer isNameExist(@Param("name")String name,@Param("id")Integer id);

}
