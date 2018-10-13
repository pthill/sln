package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.RecommendService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface RecommendServiceReadDao {

    RecommendService get(java.lang.Integer id);

    List<RecommendService> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer isServiceExist(@Param("serviceId")Integer serviceId,@Param("id")Integer id);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id);
    /*获取当前园区下的推荐服务*/
    List<RecommendService> getByParkId(Integer parkId);
}
