package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.PortalActive;

import java.util.List;
import java.util.Map;

public interface IPortalActiveService {
    /**
     * 根据id取得portal_active对象
     * @param  portalActiveId
     * @return
     */
    ServiceResult<PortalActive> getPortalActiveById(Integer portalActiveId);

    /**
     * 保存portal_active对象
     * @param  portalActive
     * @return
     */
    ServiceResult<Integer> savePortalActive(PortalActive portalActive);

    /**
     * 更新portal_active对象
     * @param  portalActive
     * @return
     */
    ServiceResult<Integer> updatePortalActive(PortalActive portalActive);


    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<Integer> del(Integer id);

    ServiceResult<List<PortalActive>> page(Map<String, String> queryMap, PagerInfo pager);

    /*展示在门户的活动*/
    ServiceResult<List<PortalActive>> activeList(Integer parkId);
}
