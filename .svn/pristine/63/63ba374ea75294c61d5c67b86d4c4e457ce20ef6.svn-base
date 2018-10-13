package com.sln.model.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.write.system.SystemResourcesWriteDao;
import com.sln.entity.system.SystemResources;
import com.sln.entity.system.SystemRoles;

@Component(value = "systemResourcesModel")
public class SystemResourcesModel {

    @Resource
    private SystemResourcesWriteDao systemResourcesWriteDao;

    /**
     * 根据id取得资源表
     * 
     * @param systemResourcesId
     * @return
     */
    public SystemResources getSystemResourcesById(Integer systemResourcesId) {
        return systemResourcesWriteDao.get(systemResourcesId);
    }

    /**
     * 保存资源表
     * 
     * @param systemResources
     * @return
     */
    public Integer saveSystemResources(SystemResources systemResources) {
        return systemResourcesWriteDao.save(systemResources);
    }

    /**
     * 更新资源表
     * 
     * @param systemResources
     * @return
     */
    public Integer updateSystemResources(SystemResources systemResources) {
        return systemResourcesWriteDao.update(systemResources);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemResourcesWriteDao.getCount(queryMap);
    }

    public List<SystemResources> page(Map<String, String> queryMap) {
        return systemResourcesWriteDao.page(queryMap);
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除资源表[" + id + "]时出错");
        return systemResourcesWriteDao.del(id) > 0;
    }

    public List<SystemResources> getByPid(Integer pid) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pid", pid);
       
        return systemResourcesWriteDao.list(param);
    }
    /***
     * 商家菜单
     * @param pid
     * @param roleType
     * @return
     */
    public List<SystemResources> getByPidSeller(Integer pid, String roleType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pid", pid);
        	if (roleType.equals(SystemRoles.BUSINESS_TYPE)) {
	            param.put("scope", "2,3");
	        } else {
	            param.put("scope", "3");
	        }
       
        return systemResourcesWriteDao.getSystemResourceSlist(param);
    }
    public List<SystemResources> getByPidAndType(Integer pid, String roleType) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pid", pid);
        // 如果当前角色类型是平台,那么可以分配的资源是（平台）和（平台+业务管理方）
        if (roleType.equals(SystemRoles.ADMIN_TYPE)) {
            param.put("scope", "(0,1)");
        } else {
            // 如果当前角色类型是业务管理方,那么可以分配的资源是（平台+业务管理方）
            param.put("scope", "(1)");
        }
        return systemResourcesWriteDao.getByPidAndType(param);
    }

}
