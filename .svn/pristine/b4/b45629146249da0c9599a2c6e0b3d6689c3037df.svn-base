package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.PortalActive;
import com.sln.model.portal.PortalActiveModel;
import com.sln.service.portal.IPortalActiveService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class PortalActiveServiceImpl implements IPortalActiveService {

    private static Logger log = LogManager.getLogger(PortalActiveServiceImpl.class);

    @Resource
    private PortalActiveModel portalActiveModel;

    @Override
    public ServiceResult<PortalActive> getPortalActiveById(Integer portalActiveId) {
        ServiceResult<PortalActive> result = new ServiceResult<PortalActive>();
        try {
            result.setResult(portalActiveModel.getPortalActiveById(portalActiveId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalActiveService][getPortalActiveById]根据id["+portalActiveId+"]取得portal_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalActiveService][getPortalActiveById]根据id["+portalActiveId+"]取得portal_active对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> savePortalActive(PortalActive portalActive) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalActiveModel.savePortalActive(portalActive));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalActiveService][savePortalActive]保存portal_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalActiveService][savePortalActive]保存portal_active对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> updatePortalActive(PortalActive portalActive) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalActiveModel.updatePortalActive(portalActive));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalActiveService][updatePortalActive]更新portal_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalActiveService][updatePortalActive]更新portal_active对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> onOrOff(Integer id, String status) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            if(status==null||status.equals("")){
                new BusinessException("启用禁用状态不能为空");
            }
            PortalActive portalActive =portalActiveModel.getPortalActiveById(id);
            portalActive.setStatus(status);
            result.setResult(portalActiveModel.update(portalActive));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][onOrOff]更新portal_active启用禁用状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][onOrOff]更新portal_active启用禁用状态时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalActiveModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalActiveService][del]删除portal_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalActiveService][del]删除portal_active对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PortalActive>> page(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<PortalActive>> serviceResult = new ServiceResult<List<PortalActive>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(portalActiveModel, "Property 'portalMenuModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(portalActiveModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(portalActiveModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalMenuService][page]查询活动管理列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalMenuService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[PortalMenuService][page]查询活动管理列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PortalActive>> activeList(Integer parkId) {
        ServiceResult<List<PortalActive>> result = new ServiceResult<List<PortalActive>>();
        try {
            result.setResult(portalActiveModel.activeList(parkId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalActiveService][activeList]取得portal_active对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalActiveService][activeList]取得portal_active对象时出现未知异常：", e);
        }
        return result;
    }
}
