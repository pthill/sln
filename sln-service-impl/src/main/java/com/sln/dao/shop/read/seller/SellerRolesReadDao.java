package com.sln.dao.shop.read.seller;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.system.SystemResources;

@Repository
public interface SellerRolesReadDao {

    SellerRoles get(java.lang.Integer id);

    Integer getSellerRolesCount(@Param("queryMap") Map<String, String> queryMap);

    List<SellerRoles> getSellerRoles(@Param("queryMap") Map<String, String> queryMap,
                                     @Param("start") Integer start, @Param("size") Integer size);
    
    
    List<SystemResources>  getResourceByRoleId(Map<String, Object> queryMap);
    /***
     * 根据用户id获取用户类型
     * @param userId
     * @return
     */
    SellerRoles getSellerRolesByUserId(Integer userId);
    
    /**
     * 根据角色名称获取数据
     */
    List<SellerRoles> getSellerRolesByRolesName(@Param("rolesName") String rolesName);
    
    
}