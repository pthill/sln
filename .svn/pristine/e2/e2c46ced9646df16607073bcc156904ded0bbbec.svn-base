package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.PortalMenuPark;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortalMenuParkWriteDao {
    /**
     * 批量新增
     * @param portalMenuParks
     * @return
     */
    Integer batchSave(@Param("list") List<PortalMenuPark> portalMenuParks);

    /**
     * 批量删除
     * @param
     * @return
     */
    Integer deleteById(@Param("portalMenuId")Integer portalMenuId);

}
