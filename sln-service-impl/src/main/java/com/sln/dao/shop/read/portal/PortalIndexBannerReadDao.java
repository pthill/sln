package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.IndexBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PortalIndexBannerReadDao {

    IndexBanner get(java.lang.Integer id);

    List<IndexBanner> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    List<IndexBanner> getBannerList(@Param("parkId") Integer parkId,@Param("type")String type);

    Integer isNameExist(@Param("name") String name,@Param("id")Integer id,@Param("parkId")Integer parkId);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id,@Param("parkId")Integer parkId,@Param("type")String type);

    Integer isAbbreviationExist(@Param("abbreviation") String abbreviation,@Param("id")Integer id,@Param("parkId")Integer parkId);
}
