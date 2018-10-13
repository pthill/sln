package com.sln.model.seller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.seller.SellerRolesReadDao;
import com.sln.dao.shop.write.seller.SellerRolesWriteDao;
import com.sln.dao.shop.write.seller.SellerUserWriteDao;
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.system.SystemResources;

@Component(value = "sellerRolesModel")
public class SellerRolesModel {

    @Resource
    private SellerRolesWriteDao sellerRolesWriteDao;
    @Resource
    private SellerRolesReadDao  sellerRolesReadDao;
    @Resource
    private SellerUserWriteDao  sellerUserWriteDao;

    /**
    * 根据id取得角色表
    * @param  sellerRolesId
    * @return
    */
    public SellerRoles getSellerRolesById(Integer sellerRolesId) {
        return sellerRolesReadDao.get(sellerRolesId);
    }

    /**
     * 保存角色表
     * @param  sellerRoles
     * @return
     */
    public Integer saveSellerRoles(SellerRoles sellerRoles) {
        return sellerRolesWriteDao.insert(sellerRoles);
    }
    
    /**
     * 根据角色名称查询角色
     */
    public List<SellerRoles> getSellerRolesByRolesName(String rolesName) {
        return sellerRolesReadDao.getSellerRolesByRolesName(rolesName);
    }
    

    /**
    * 更新角色表
    * @param  sellerRoles
    * @return
    */
    public Integer updateSellerRoles(SellerRoles sellerRoles) {
        return sellerRolesWriteDao.update(sellerRoles);
    }

    public Integer getSellerRolesCount(Map<String, String> queryMap) {
        return sellerRolesReadDao.getSellerRolesCount(queryMap);
    }

    public List<SellerRoles> getSellerRoles(Map<String, String> queryMap, Integer start,
                                            Integer size) {
        return sellerRolesReadDao.getSellerRoles(queryMap, start, size);
    }

    public Boolean deleteSellerRole(Integer id) {
        if (id == null) {
            throw new BusinessException("删除角色表[" + id + "]时出错");
        }
        Integer count = sellerUserWriteDao.getCountByRoleId(id);
        if (count != null && count > 0) {
            throw new BusinessException("该角色关联了用户不能删除");
        }
        return sellerRolesWriteDao.delete(id) > 0;
    }
    /***
     *获取角色下的资源
     * @param roleId
     * @return
     */
    public List<SystemResources> getResourceByRoleId(Integer roleId) {
        if (roleId == null)
            throw new BusinessException("未指定角色");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roleId",roleId);
		return sellerRolesReadDao.getResourceByRoleId(param);
    }
    
    
    public SellerRoles  getSellerRolesByUserId(Integer userId){
        return sellerRolesReadDao.getSellerRolesByUserId(userId);
    }
}	
