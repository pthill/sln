package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.portal.PortalMenuPark;

import java.util.List;
import java.util.Map;

public interface IPortalMenuService {
    /**
     * 根据id取得portal_menu对象
     * @param  portalMenuId
     * @return
     */
    ServiceResult<PortalMenu> getPortalMenuById(Integer portalMenuId);

    /**
     * 保存portal_menu对象
     * @param  portalMenu
     * @return
     */
    ServiceResult<Boolean> savePortalMenu(PortalMenu portalMenu,String[] parkIds);

    /**
     * 更新portal_menu对象
     * @param  portalMenu
     * @return
     */
    ServiceResult<Boolean> updatePortalMenu(PortalMenu portalMenu,String[] parkIds);

    /**
     * 编号code是否重复
     * @param code
     * @return
     */
    ServiceResult<Integer> isCodeExist(String code,Integer id);
    /**
     * 排序是否重复
     * @param order
     * @return
     */
    ServiceResult<Integer> isOrderExist(Integer order,Integer id);

    /**
     * 简称是否重复
     * @param abbreviation
     * @return
     */
    ServiceResult<Integer> isAbbreviationExist(String abbreviation,Integer id);


    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<PortalMenu>> page(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<Integer> del(Integer id);

    ServiceResult<Integer> isShow(Integer id,String isShow);

    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<List<PortalMenuPark>> getParksByMenuId(Integer portalMenuId);

    /*查询当前园区下有哪些菜单*/
    ServiceResult<List<PortalMenu>> getMenusByParkId(Integer parkId);

}
