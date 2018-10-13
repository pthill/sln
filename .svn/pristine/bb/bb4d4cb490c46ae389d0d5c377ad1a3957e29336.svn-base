package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.ParkAdvantage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface ParkAdvantageReadDao {

    ParkAdvantage get(java.lang.Integer id);

    List<ParkAdvantage> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer isTitleExist(@Param("title")String title,@Param("id")Integer id);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id);

    List<ParkAdvantage> getByParkId(@Param("parkId") Integer parkId);
}
