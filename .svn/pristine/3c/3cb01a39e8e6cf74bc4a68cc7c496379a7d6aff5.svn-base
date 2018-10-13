package com.sln.service.impl.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.system.SystemRoles;
import com.sln.model.system.SystemRolesModel;
import com.sln.service.system.ISystemRolesService;

@Service(value = "systemRolesService")
public class SystemRolesServiceImpl implements ISystemRolesService {
    private static Logger    log = LogManager.getLogger(SystemRolesServiceImpl.class);

    @Resource
    private SystemRolesModel systemRolesModel;

    /**
    * 根据id取得角色表
    * @param  systemRolesId
    * @return
    */
    @Override
    public ServiceResult<SystemRoles> getSystemRolesById(Integer systemRolesId) {
        ServiceResult<SystemRoles> result = new ServiceResult<SystemRoles>();
        try {
            result.setResult(systemRolesModel.getSystemRolesById(systemRolesId));
        } catch (Exception e) {
            log.error("根据id[" + systemRolesId + "]取得角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + systemRolesId + "]取得角色表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存角色表
     * @param  systemRoles
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSystemRoles(SystemRoles systemRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemRolesModel.saveSystemRoles(systemRoles));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        }
        return result;
    }

    /**
    * 更新角色表
    * @param  systemRoles
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSystemRoles(SystemRoles systemRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(systemRolesModel.updateSystemRoles(systemRoles));
            result.setMessage("修改成功");
        } catch (Exception e) {
            log.error("更新角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新角色表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SystemRoles>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SystemRoles>> serviceResult = new ServiceResult<List<SystemRoles>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(systemRolesModel.pageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SystemRoles> list = systemRolesModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemRolesServiceImpl][page] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SystemRolesServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(systemRolesModel.del(id));
            serviceResult.setMessage("删除成功");
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SystemRolesServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}