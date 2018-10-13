package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.PortalMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface PortalMenuReadDao {

    PortalMenu get(java.lang.Integer id);
    //平台角色查询列表
    List<PortalMenu> getPage(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    //业务管理方角色查询列表
    List<PortalMenu> getPageForOperation(@Param("queryMap") Map<String, String> queryMap, @Param("size") Integer size, @Param("start") Integer start);

    Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

    Integer getPageCountForOperation(@Param("queryMap") Map<String, String> queryMap);

    Integer isCodeExist(@Param("code") String code,@Param("id")Integer id);

    Integer isOrderExist(@Param("order") Integer order,@Param("id") Integer id);

    Integer isAbbreviationExist(@Param("abbreviation") String abbreviation,@Param("id")Integer id);

    /*门户菜单根据园区获取菜单(状态:启用,(禁用并且显示))*/
    List<PortalMenu> getMenusByParkId(@Param("parkId") Integer parkId);
}
