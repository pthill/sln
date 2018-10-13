package com.sln.service.impl.operate;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.OperationManager;
import com.sln.model.operate.OperationManagerModel;
import com.sln.service.operate.IOperationManagerService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "managerService")
public class OperationManagerServiceImpl implements IOperationManagerService {

    private static Logger log = LogManager.getLogger(OperationManagerServiceImpl.class);
    @Resource
    private OperationManagerModel managerModel;

    @Override
    public ServiceResult<OperationManager> getOperationMemberById(Integer id) {
        ServiceResult<OperationManager> serviceResult = new ServiceResult<OperationManager>();
        try {
            serviceResult.setResult(managerModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ManagerService][getById]根据id[" + id + "]取得业务管理方列表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ManagerService][getById]根据id[" + id + "]获取业务管理方列表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OperationManager> getByNameAndParkId(Integer parkId, String name) {
        ServiceResult<OperationManager> serviceResult = new ServiceResult<OperationManager>();
        try {
            serviceResult.setResult(managerModel.getByNameAndParkId(parkId,name));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ManagerService][getByNameAndParkId]根据园区和业务管理方名称取得业务管理方时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ManagerService][getByNameAndParkId]根据园区和业务管理方名称取得业务管理方出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> save(OperationManager manager) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            if(managerModel.isCodeExists(manager.getCode(),null)){
                serviceResult.setSuccess(false);
                serviceResult.setMessage("新增失败,业务管理方编码"+manager.getCode()+"存在重复");
                new BusinessException("新增失败,业务管理方code不能重复");
            }
            if(managerModel.isNameExists(manager.getName(),manager.getParkId(),null)){
                serviceResult.setSuccess(false);
                serviceResult.setMessage("新增失败,该园区已经存在该业务管理方");
                new BusinessException("新增失败,该园区已经存在该业务管理方");
            }else{
                serviceResult.setResult(managerModel.save(manager));
                serviceResult.setMessage("新增成功");
            }
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ManagerService][save]保存业务管理方列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ManagerService][save]保存业务管理方列表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> update(OperationManager manager) {
        ServiceResult<Boolean> serviceResult=new ServiceResult<Boolean>();
        try {
             Assert.notNull(managerModel, "Property 'managerModel' is required.");
            if(managerModel.isCodeExists(manager.getCode(),manager.getId())){
                serviceResult.setSuccess(false);
                serviceResult.setMessage("新增失败,业务管理方编码"+manager.getCode()+"存在重复");
                new BusinessException("新增失败,业务管理方code不能重复");
            }
            if(managerModel.isNameExists(manager.getName(),manager.getParkId(),manager.getId())){
                serviceResult.setSuccess(false);
                serviceResult.setMessage("修改失败,该园区已经存在该业务管理方");
                new BusinessException("修改失败,该园区已经存在该业务管理方");
            }else{
                serviceResult.setResult(managerModel.update(manager));
                serviceResult.setMessage("修改成功");
            }
        }catch (BusinessException be){
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[ManagerService][update]更新业务管理方列表时出现异常：" + be.getMessage());
        }catch (Exception e){
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][update]更新业务管理方列表时出现未知异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateStatus(Integer id,String status) {
        ServiceResult<Boolean> serviceResult=new ServiceResult<Boolean>();
        try {
            Assert.notNull(managerModel, "Property 'managerModel' is required.");
            OperationManager manager=new OperationManager();
            if(id==null){
                new BusinessException("业务管理方id不能为空");
            }
            manager.setId(id);
            manager.setStatus(status);
            serviceResult.setResult(managerModel.updateStatus(manager));
        }catch (BusinessException be){
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[ManagerService][updateStatus]更新业务管理方状态时出现异常：" + be.getMessage());
        }catch (Exception e){
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberService][updateStatus]更新业务管理方状态时出现异常出现未知异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OperationManager>> getOperationManagers(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<OperationManager>> serviceResult = new ServiceResult<List<OperationManager>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(managerModel, "Property 'managerModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(managerModel.getManagersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(managerModel.getManagers(queryMap, size, start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[ManagerService][getManagers]查询业务管理方列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ManagerService][getManagers]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[ManagerService][getManagers]查询业务管理方信息发生异常:", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<List<OperationManager>> getManagersByParkId(Integer parkId) {
        ServiceResult<List<OperationManager>>serviceResult=new ServiceResult<List<OperationManager>>();
        try {
            Assert.notNull(managerModel, "Property 'managerModel' is required.");
            if(parkId==null){
                new BusinessException("园区id不能为空");
            }
            serviceResult.setResult(managerModel.getManagersByParkId(parkId));
        }catch (BusinessException be){
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[ManagerService][getOperations]根据园区查询业务管理方列表时出现异常：" + be.getMessage());
        }catch (Exception e){
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[ManagerService][getOperations]根据园区查询业务管理方列表发生异常:", e);
        }
        return serviceResult;
    }

}
