package com.sln.service.promotion;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.integral.ActIntegralBanner;

public interface IActIntegralBannerService {

    /**
     * 根据id取得积分商城首页轮播图
     * @param  pcIndexBannerId
     * @return
     */
    ServiceResult<ActIntegralBanner> getActIntegralBannerById(Integer pcIndexBannerId);

    /**
     * 保存积分商城首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> saveActIntegralBanner(ActIntegralBanner pcIndexBanner);

    /**
     * 更新积分商城首页轮播图
     * @param pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> updateActIntegralBanner(ActIntegralBanner pcIndexBanner);

    /**
     * 删除积分商城首页轮播图
     * @param  pcIndexBanner
     * @return
     */
    ServiceResult<Boolean> deleteActIntegralBanner(Integer pcIndexBannerId);

    /**
     * 根据条件取得积分商城首页轮播图
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActIntegralBanner>> getActIntegralBanners(Map<String, String> queryMap,
                                                                 PagerInfo pager);

    /**
     * 查询积分商城首页轮播图
     * @param pcMobilePc
     * @param channel2
     * @return
     */
    ServiceResult<List<ActIntegralBanner>> getActIntegralBannersPcMobile(int pcMobile);

}