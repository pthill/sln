package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.PortalActive;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PortalActiveReadDao {

    PortalActive get(java.lang.Integer id);

    List<PortalActive> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer isTitleExist(@Param("title")String title,@Param("id")Integer id,@Param("parkId")Integer parkId);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id,@Param("parkId")Integer parkId);

    List<PortalActive> getList(@Param("parkId") Integer parkId);
}
