package com.sln.model.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.dao.shop.read.group.ActGroupBannerReadDao;
import com.sln.dao.shop.write.group.ActGroupBannerWriteDao;
import com.sln.entity.group.ActGroupBanner;

@Component(value = "actGroupBannerModel")
public class ActGroupBannerModel {

    @Resource
    private ActGroupBannerReadDao  actGroupBannerReadDao;
    @Resource
    private ActGroupBannerWriteDao actGroupBannerWriteDao;

    /**
     * 根据id和渠道取得团购首页轮播图
     * @param  ActGroupBannerId
     * @return
     */
    public ActGroupBanner getActGroupBannerById(Integer actGroupBannerId) {
        return actGroupBannerReadDao.get(actGroupBannerId);
    }

    /**
     * 保存团购首页轮播图
     * @param  actGroupBanner
     * @return
     */
    public boolean saveActGroupBanner(ActGroupBanner actGroupBanner) {
        return actGroupBannerWriteDao.insert(actGroupBanner) > 0;
    }

    /**
     * 更新团购首页轮播图
     * @param actGroupBanner
     * @return
     */
    public boolean updateActGroupBanner(ActGroupBanner actGroupBanner) {
        return actGroupBannerWriteDao.update(actGroupBanner) > 0;
    }

    /**
     * 删除团购首页轮播图
     * @param actGroupBanner
     * @return
     */
    public boolean deleteActGroupBanner(Integer actGroupBannerId) {
        return actGroupBannerWriteDao.delete(actGroupBannerId) > 0;
    }

    public Integer getActGroupBannersCount(Map<String, String> queryMap) {
        return actGroupBannerReadDao.getActGroupBannersCount(queryMap);
    }

    public List<ActGroupBanner> getActGroupBanners(Map<String, String> queryMap, Integer start,
                                                   Integer size) {
        return actGroupBannerReadDao.getActGroupBanners(queryMap, start, size);
    }

    /**
     * 查询团购首页轮播图
     * @param pcMobile
     * @return
     */
    public List<ActGroupBanner> getActGroupBannersPcMobile(int pcMobile) {
        return actGroupBannerReadDao.getActGroupBannersPcMobile(pcMobile);
    }

}