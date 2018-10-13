package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.QuickEnter;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface QuickEnterReadDao {

    QuickEnter get(java.lang.Integer id);

    List<QuickEnter> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id);

    Integer isNameExist(@Param("name")String name,@Param("id")Integer id);
}
