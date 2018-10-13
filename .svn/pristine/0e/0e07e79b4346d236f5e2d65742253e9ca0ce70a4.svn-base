package com.sln.dao.shop.read.portal;

import com.sln.entity.portal.PortalMenuPark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalMenuParkReadDao {


    /**
     * 查询当前门户菜单可以应用哪些园区
     * @param portalMenuId
     * @return
     */
    List<PortalMenuPark> getByportalMenuId(@Param("portalMenuId")Integer portalMenuId);
}
