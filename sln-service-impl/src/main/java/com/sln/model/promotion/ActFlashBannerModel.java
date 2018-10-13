package com.sln.model.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.dao.shop.read.flash.ActFlashBannerReadDao;
import com.sln.dao.shop.write.flash.ActFlashBannerWriteDao;
import com.sln.entity.flash.ActFlashBanner;

@Component(value = "actFlashBannerModel")
public class ActFlashBannerModel {

    @Resource
    private ActFlashBannerReadDao  actFlashBannerReadDao;
    @Resource
    private ActFlashBannerWriteDao actFlashBannerWriteDao;

    /**
     * 根据id和渠道取得限时抢购首页轮播图
     * @param  ActFlashBannerId
     * @return
     */
    public ActFlashBanner getActFlashBannerById(Integer actFlashBannerId) {
        return actFlashBannerReadDao.get(actFlashBannerId);
    }

    /**
     * 保存限时抢购首页轮播图
     * @param  actFlashBanner
     * @return
     */
    public boolean saveActFlashBanner(ActFlashBanner actFlashBanner) {
        return actFlashBannerWriteDao.insert(actFlashBanner) > 0;
    }

    /**
     * 更新限时抢购首页轮播图
     * @param actFlashBanner
     * @return
     */
    public boolean updateActFlashBanner(ActFlashBanner actFlashBanner) {
        return actFlashBannerWriteDao.update(actFlashBanner) > 0;
    }

    /**
     * 删除限时抢购首页轮播图
     * @param actFlashBanner
     * @return
     */
    public boolean deleteActFlashBanner(Integer actFlashBannerId) {
        return actFlashBannerWriteDao.delete(actFlashBannerId) > 0;
    }

    public Integer getActFlashBannersCount(Map<String, String> queryMap) {
        return actFlashBannerReadDao.getActFlashBannersCount(queryMap);
    }

    public List<ActFlashBanner> getActFlashBanners(Map<String, String> queryMap, Integer start,
                                                   Integer size) {
        return actFlashBannerReadDao.getActFlashBanners(queryMap, start, size);
    }

    /**
     * 查询限时抢购首页轮播图
     * @param pcMobile
     * @return
     */
    public List<ActFlashBanner> getActFlashBannersPcMobile(int pcMobile) {
        return actFlashBannerReadDao.getActFlashBannersPcMobile(pcMobile);
    }

}