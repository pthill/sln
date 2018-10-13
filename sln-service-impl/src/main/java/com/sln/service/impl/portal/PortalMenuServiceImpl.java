package com.sln.service.impl.portal;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.portal.PortalMenuPark;
import com.sln.model.portal.PortalMenuModel;
import com.sln.service.portal.IPortalMenuService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "portalMenuService")
public class PortalMenuServiceImpl implements IPortalMenuService {
    private static Logger log = LogManager.getLogger(PortalMenuServiceImpl.class);

    @Resource
    private PortalMenuModel portalMenuModel;

    /**
     * 根据id取得portal_menu对象
     * @param  portalMenuId
     * @return
     */
    @Override
    public ServiceResult<PortalMenu> getPortalMenuById(Integer portalMenuId) {
        ServiceResult<PortalMenu> result = new ServiceResult<PortalMenu>();
        try {
            result.setResult(portalMenuModel.getPortalMenuById(portalMenuId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][getPortalMenuById]根据id["+portalMenuId+"]取得portal_menu对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][getPortalMenuById]根据id["+portalMenuId+"]取得portal_menu对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 保存portal_menu对象
     * @param  portalMenu
     * @return
     */
    @Override
    public ServiceResult<Boolean> savePortalMenu(PortalMenu portalMenu,String[] parkIds) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(portalMenuModel.savePortalMenu(portalMenu,parkIds));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][savePortalMenu]保存portal_menu对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][savePortalMenu]保存portal_menu对象时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 更新portal_menu对象
     * @param  portalMenu
     * @return
     */
    @Override
    public ServiceResult<Boolean> updatePortalMenu(PortalMenu portalMenu,String[] parkIds) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(portalMenuModel.updatePortalMenu(portalMenu,parkIds));
            result.setMessage("修改成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][updatePortalMenu]更新portal_menu对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][updatePortalMenu]更新portal_menu对象时出现未知异常：",
                    e);
        }
        return result;
    }


    @Override
    public ServiceResult<Integer> isCodeExist(String code,Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalMenuModel.isCodeExist(code,id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][isCodeExist]检查编号是否重复时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][isCodeExist]检查编号是否重复时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 排序是否重复
     * @param order
     * @return
     */
    @Override
    public ServiceResult<Integer> isOrderExist(Integer order,Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalMenuModel.isOrderExist(order,id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][isOrderExist]检查排序是否重复时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][isOrderExist]检查排序是否重复时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 简称是否重复
     * @param abbreviation
     * @return
     */

    @Override
    public ServiceResult<Integer> isAbbreviationExist(String abbreviation,Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(portalMenuModel.isAbbreviationExist(abbreviation,id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][isAbbreviationExist]检查简称是否重复时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][isAbbreviationExist]检查简称是否重复时出现未知异常：",
                    e);
        }
        return result;
    }

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    @Override
    public ServiceResult<List<PortalMenu>> page(Map<String, String> queryMap,
                                                          PagerInfo pager) {

        ServiceResult<List<PortalMenu>> serviceResult = new ServiceResult<List<PortalMenu>>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(portalMenuModel, "Property 'portalMenuModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(portalMenuModel.getPageCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(portalMenuModel.getPage(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[PortalMenuService][page]查询菜单管理列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[PortalMenuService][page]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[PortalMenuService][page]查询菜单管理列表发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            if(id==null){
                new BusinessException("id不能为空");
            }
            result.setResult(portalMenuModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][del]检查简称是否重复时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][del]检查简称是否重复时出现未知异常：",
                    e);
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
            PortalMenu portalMenu=portalMenuModel.getPortalMenuById(id);
            portalMenu.setIsShow(isShow);
            result.setResult(portalMenuModel.update(portalMenu));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][isShow]更新portal_menu显示状态时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][isShow]更新portal_menu显示状态时出现未知异常：",
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
            PortalMenu portalMenu=portalMenuModel.getPortalMenuById(id);
            if(state.equals("1")){//启用
                portalMenu.setIsShow("1");//启用一定为显示状态
            }
            portalMenu.setState(state);
            result.setResult(portalMenuModel.update(portalMenu));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][onOrOff]更新portal_menu启用禁用状态时对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][onOrOff]更新portal_menu启用禁用状态时对象时出现未知异常：",
                    e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<PortalMenuPark>> getParksByMenuId(Integer portalMenuId) {
        ServiceResult<List<PortalMenuPark>> serviceResult = new ServiceResult<List<PortalMenuPark>>();
        try {
            Assert.notNull(portalMenuModel, "Property 'portalMenuModel' is required.");
            serviceResult.setResult(portalMenuModel.getParksById(portalMenuId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[PortalMenuService][getParksByMenuId]查询菜单关联园区列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[PortalMenuService][getParksByMenuId]param1:" + JSON.toJSONString(portalMenuId));
            log.error("[PortalMenuService][getParksByMenuId]查询菜单关联园区列表时出现异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<PortalMenu>> getMenusByParkId(Integer parkId) {
        ServiceResult<List<PortalMenu>> result = new ServiceResult<List<PortalMenu>>();
        try {
            result.setResult(portalMenuModel.getMenusByParkId(parkId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IPortalMenuService][getMenusByParkId]根据id["+parkId+"]取得portal_menu集合时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IPortalMenuService][getMenusByParkId]根据id["+parkId+"]取得portal_menu集合时出现未知异常：",
                    e);
        }
        return result;
    }

}
