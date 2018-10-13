package com.sln.service.promotion;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.flash.ActFlashBanner;

public interface IActFlashBannerService {

    /**
     * 根据id取得限时抢购首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    ServiceResult<ActFlashBanner> getActFlashBannerById(Integer pcIndexBannerId);

    /**
     * 保存限时抢购首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveActFlashBanner(ActFlashBanner pcIndexBanner);

    /**
     * 更新限时抢购首页轮播图
     * @param pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateActFlashBanner(ActFlashBanner pcIndexBanner);

    /**
     * 删除限时抢购首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteActFlashBanner(Integer pcIndexBannerId);

    /**
     * 根据条件取得限时抢购首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActFlashBanner>> getActFlashBanners(Map<String, String> queryMap,
                                                           PagerInfo pager);

    /**
     * 查询限时抢购首页轮播图
     * @param pcMobilePc
     * @param channel2
     * @return
     */
    ServiceResult<List<ActFlashBanner>> getActFlashBannersPcMobile(int pcMobile);

}