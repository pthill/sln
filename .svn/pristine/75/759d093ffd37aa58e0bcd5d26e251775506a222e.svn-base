package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.IndexBanner;
import com.sln.entity.portal.PortalMenu;

import java.util.List;
import java.util.Map;

public interface IPortalIndexBannerService {
    /**
     * 根据id取得index_banner对象
     * @param  indexBannerId
     * @return
     */
    ServiceResult<IndexBanner> getIndexBannerById(Integer indexBannerId);

    /**
     * 保存index_banner对象
     * @param  indexBanner
     * @return
     */
    ServiceResult<Integer> saveIndexBanner(IndexBanner indexBanner);

    /**
     * 更新index_banner对象
     * @param  indexBanner
     * @return
     */
    ServiceResult<Integer> updateIndexBanner(IndexBanner indexBanner);

    ServiceResult<Integer> del(Integer id);

    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<List<IndexBanner>> page(Map<String, String> queryMap, PagerInfo pager);
    /*获取门户的轮播数据*/
    ServiceResult<List<IndexBanner>> getBannerList(Integer parkId,String type);
}
