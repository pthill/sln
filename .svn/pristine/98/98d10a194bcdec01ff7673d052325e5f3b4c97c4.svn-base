package com.sln.dao.shop.write.seller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerResourcesRoles;
import com.sln.entity.system.SystemResources;

@Repository
public interface SellerResourcesRolesWriteDao {

    SellerResourcesRoles get(java.lang.Integer id);

    Integer insert(SellerResourcesRoles sellerResourcesRoles);

    Integer update(SellerResourcesRoles sellerResourcesRoles);

    //    Integer getCount(Map<String, String> queryMap);
    //
    //    List<SellerResourcesRoles> page(Map<String, String> queryMap);

    Integer del(Integer id);

    //    /**
    //     * 此角色下的资源
    //     * @param roleId
    //     * @return
    //     */
    //    List<SystemResources> getResourceByRoleId(@Param("roleId") Integer roleId,
    //                                              @Param("scope") Integer scope);
    //
    //    /**
    //     * 此资源下的有权限的子资源
    //     * @param queryMap
    //     * @return
    //     */
    //    List<SystemResources> getResourceByPid(Map<String, Object> queryMap);

    /**
     * 删除该角色下的资源关联
     * @param roleId
     * @return
     */
    Integer delByRole(String roleId);
    /***
     * 商家下的角色
     * @param queryMap
     * @return
     */
    List<SystemResources> getSellerResourceByRoleId(Map<String, Object> queryMap);
}