package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.PortalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PortalServiceReadDao {

    PortalService get(@Param("id") java.lang.Integer id);

    Integer countName(@Param("id")Integer id,@Param("name")String name,@Param("type")String type);

    Integer countCode(@Param("id")Integer id,@Param("code")String code,@Param("type")String type);

    Integer countAbbreviation(@Param("id")Integer id,@Param("abbreviation")String abbreviation,@Param("type")String type);
    //平台端服务列表
    List<PortalService> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    //业务管理方服务列表
    List<PortalService> getPageForOperation(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    /*只查询所有的服务类*/
    List<PortalService> getServicePage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    /*统计所有的服务类*/
    Integer getServiceCount(@Param("queryMap")Map<String,String>queryMap);

    //业务管理方服务列表统计
    Integer getPageCountForOperation(@Param("queryMap") Map<String, String> queryMap);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    /*获取园区下所有的未被禁用服务项*/
    List<PortalService> getServices(@Param("parkId") Integer parkId);

    /*获取某一个服务项下的所有服务类*/
    List<PortalService> gerServicesByPid(@Param("pid") Integer pid);
    /*门户菜单获取菜单下的服务项和服务类状态为启用和禁用且为显示*/
    List<PortalService> getServicesByMenuId(@Param("menuId")Integer menuId);
}
