package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.PortalService;

import java.util.List;
import java.util.Map;

public interface IPortalServiceService {
    /**
     * 根据id取得portal_service对象
     * @param  portalServiceId
     * @return
     */
    ServiceResult<PortalService> getPortalServiceById(Integer portalServiceId);

    /**
     * 保存portal_service对象
     * @param  portalService
     * @return
     */
    ServiceResult<Integer> savePortalService(PortalService portalService);

    /**
     * 更新portal_service对象
     * @param  portalService
     * @return
     */
    ServiceResult<Integer> updatePortalService(PortalService portalService);

    ServiceResult<Integer> del(Integer id);


    ServiceResult<Integer> isShow(Integer id,String isShow);


    ServiceResult<Integer> onOrOff(Integer id,String state);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PortalService>> page(Map<String, String> queryMap, PagerInfo pager);
    /**
     * 分页查询只查询所有的服务类
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PortalService>> servicePage(Map<String, String> queryMap, PagerInfo pager);
    /**
     * 查询园区下未被禁用的服务项
     * @return
     */
    ServiceResult<List<PortalService>>ListService(Integer parkId);

    /*门户菜单获取菜单下的服务项和服务类状态为启用和(禁用且为显示)*/
    ServiceResult<List<PortalService>>getServicesByMenuId(Integer menuId);

}
