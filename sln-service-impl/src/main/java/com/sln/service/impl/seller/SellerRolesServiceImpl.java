package com.sln.service.impl.seller;

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
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.system.SystemResources;
import com.sln.model.seller.SellerRolesModel;
import com.sln.service.seller.ISellerRolesService;

@Service(value = "sellerRolesService")
public class SellerRolesServiceImpl implements ISellerRolesService {
    private static Logger    log = LogManager.getLogger(SellerRolesServiceImpl.class);

    @Resource
    private SellerRolesModel sellerRolesModel;

    /**
    * 根据id取得角色表
    * @param  sellerRolesId
    * @return
    */
    @Override
    public ServiceResult<SellerRoles> getSellerRolesById(Integer sellerRolesId) {
        ServiceResult<SellerRoles> result = new ServiceResult<SellerRoles>();
        try {
            result.setResult(sellerRolesModel.getSellerRolesById(sellerRolesId));
        } catch (Exception e) {
            log.error("根据id[" + sellerRolesId + "]取得角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + sellerRolesId + "]取得角色表时出现未知异常");
        }
        return result;
    }

    /**
     * 保存角色表
     * @param  sellerRoles
     * @return
     */
    @Override
    public ServiceResult<Integer> saveSellerRoles(SellerRoles sellerRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerRolesModel.saveSellerRoles(sellerRoles));
            result.setMessage("保存成功");
        } catch (Exception e) {
            log.error("保存角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存角色表时出现未知异常");
        }
        return result;
    }

    /**
    * 更新角色表
    * @param  sellerRoles
    * @return
    */
    @Override
    public ServiceResult<Integer> updateSellerRoles(SellerRoles sellerRoles) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(sellerRolesModel.updateSellerRoles(sellerRoles));
            result.setMessage("修改成功！");
        } catch (Exception e) {
            log.error("更新角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新角色表时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<SellerRoles>> getSellerRoles(Map<String, String> queryMap,
                                                           PagerInfo pager) {
        ServiceResult<List<SellerRoles>> serviceResult = new ServiceResult<List<SellerRoles>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerRolesModel.getSellerRolesCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerRoles> list = sellerRolesModel.getSellerRoles(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerRolesServiceImpl][getSellerRoles] param1:"
                      + JSON.toJSONString(queryMap) + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerRolesServiceImpl][getSellerRoles] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteSellerRole(Integer id) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerRolesModel.deleteSellerRole(id));
            serviceResult.setMessage("删除成功！");
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerRolesServiceImpl][deleteSellerRoles] exception:" + e.getMessage());
            e.printStackTrace();
        }
        return serviceResult;
    }
    /****
     * 获取角色下的资源
     */
	@Override
	public ServiceResult<List<SystemResources>> getResourceByRoleId(
			Integer roleId) {
		 ServiceResult<List<SystemResources>> serRes = new ServiceResult<List<SystemResources>>();
	        try {
	            serRes.setResult(sellerRolesModel.getResourceByRoleId(roleId));
	        } catch (Exception e) {
	            serRes.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, e.getMessage());
	            e.printStackTrace();
	        }
	        return serRes;
	}
	/***
	 * 根据用户id获取类型
	 * @param roleId
	 * @return
	 * @see com.sln.service.seller.ISellerRolesService#getSellerRolesByUserId(java.lang.Integer)
	 */
    @Override
    public ServiceResult<SellerRoles> getSellerRolesByUserId(Integer roleId) {
        ServiceResult<SellerRoles> result = new ServiceResult<SellerRoles>();
        try {
            result.setResult(sellerRolesModel.getSellerRolesByUserId(roleId));
        } catch (Exception e) {
            log.error("根据id[" + roleId + "]取得角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + roleId + "]取得角色表时出现未知异常");
        }
        return result;
    }

	@Override
	public ServiceResult<List<SellerRoles>> getSellerRolesByRolesName(String rolesName) {
		ServiceResult<List<SellerRoles>> result = new ServiceResult<List<SellerRoles>>();
        try {
            result.setResult(sellerRolesModel.getSellerRolesByRolesName(rolesName));
        } catch (Exception e) {
            log.error("根据角色名称[" + rolesName + "]取得角色表时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据角色名称[" + rolesName + "]取得角色表时出现未知异常");
        }
        return result;
	}
	
	
}