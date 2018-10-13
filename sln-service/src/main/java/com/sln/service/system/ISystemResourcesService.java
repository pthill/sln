package com.sln.service.system;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.system.SystemResources;

/**
 * 系统资源管理
 *                       
 * @Filename: ISystemResourcesService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISystemResourcesService {

    /**
     * 根据id取得资源表
     * @param  systemResourcesId
     * @return
     */
    ServiceResult<SystemResources> getSystemResourcesById(Integer systemResourcesId);

    /**
     * 保存资源表
     * @param  systemResources
     * @return
     */
    ServiceResult<Integer> saveSystemResources(SystemResources systemResources);

    /**
    * 更新资源表
    * @param  systemResources
    * @return
    */
    ServiceResult<Integer> updateSystemResources(SystemResources systemResources);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemResources>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    ServiceResult<List<SystemResources>> getByPid(Integer pid);
    
    ServiceResult<List<SystemResources>> getByPidSeller(Integer pid,String roleType);

    ServiceResult<List<SystemResources>> getByPidAndType(Integer pid,String roleType);

    
    
}