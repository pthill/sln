package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.PortalService;
import com.sln.model.portal.PortalServiceModel;
import com.sln.service.portal.IPortalServiceService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "portalServiceService")
public class PortalServiceServiceImpl implements IPortalServiceService {
    private static Logger log = LogManager.getLogger(PortalServiceServiceImpl.class);
    @Resource
    private PortalServiceModel portalServiceModel;
    @Override
    public ServiceResult<PortalService> getPortalServiceById(Integer portalServiceId) {
        ServiceResult<PortalService> result = new ServiceResult<PortalService>();
        try {
            result.setResult(portalServiceModel.getPortalServiceById(portalServiceId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][getPortalServiceById]根据id["+portalServiceId+"]取得portal_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][getPortalServiceById]根据id["+portalServiceId+"]取得portal_service对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> savePortalService(PortalService portalService) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalServiceModel.savePortalService(portalService));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][savePortalService]保存portal_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][savePortalService]保存portal_service对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> updatePortalService(PortalService portalService) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalServiceModel.updatePortalService(portalService));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][updatePortalService]更新portal_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][updatePortalService]更新portal_service对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalServiceModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][del]删除portal_service对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][del]删除portal_service对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> isShow(Integer id, String isShow) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            if(isShow==null||isShow.equals("")){
                new BusinessException("是否显示状态不能为空");
            }
            PortalService portalService=portalServiceModel.getPortalServiceById(id);
            portalService.setIsShow(isShow);
            result.setResult(portalServiceModel.update(portalService));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][isShow]更新portal_service显示状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][isShow]更新portal_service显示状态时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> onOrOff(Integer id, String state) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            if(state==null||state.equals("")){
                new BusinessException("启用禁用状态不能为空");
            }
            PortalService portalService=portalServiceModel.getPortalServiceById(id);
            if(state.equals("1")){//启用
                portalService.setIsShow("1");//启用一定为显示状态
            }
            portalService.setState(state);
            result.setResult(portalServiceModel.update(portalService));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalServiceService][onOrOff]更新portal_service启用禁用状态时对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalServiceService][onOrOff]更新portal_service启用禁用状态时对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<List<PortalService>> page(Map<String, String> queryMap,
                                                             PagerInfo pager) {
        ServiceResult<List<PortalService>> serviceResult = new ServiceResult<List<PortalService>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(portalServiceModel, "Property 'portalServiceModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(portalServiceModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(portalServiceModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalServiceService][page]查询活动管理列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalServiceService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPortalServiceService][page]查询活动管理列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PortalService>> servicePage(Map<String, String> queryMap,
                                                                    PagerInfo pager) {
        ServiceResult<List<PortalService>> serviceResult = new ServiceResult<List<PortalService>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(portalServiceModel, "Property 'portalServiceModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(portalServiceModel.getServicePageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(portalServiceModel.servicePage(queryMap, start, size));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalServiceService][servicePage]查询活动管理列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalServiceService][servicePage]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IPortalServiceService][servicePage]查询活动管理列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PortalService>> ListService(Integer parkId) {
        ServiceResult<List<PortalService>> serviceResult = new ServiceResult<List<PortalService>>();
        try {
            Assert.notNull(portalServiceModel, "Property 'portalServiceModel' is required.");
            serviceResult.setResult(portalServiceModel.serviceList(parkId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalServiceService][ListService]查询未被禁用服务项列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalServiceService][ListService]查询未被禁用服务项列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PortalService>> getServicesByMenuId(Integer menuId) {
        ServiceResult<List<PortalService>> serviceResult = new ServiceResult<List<PortalService>>();
        try {
            Assert.notNull(portalServiceModel, "Property 'getServicesByMenuId' is required.");
            serviceResult.setResult(portalServiceModel.getServicesByMenuId(menuId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IPortalServiceService][getServicesByMenuId]根据菜单查询服务项和服务类列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IPortalServiceService][getServicesByMenuId]根据菜单查询服务项和服务类列表发生异常:", e);
        }
        return serviceResult;
    }
}
