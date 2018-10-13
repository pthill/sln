package com.sln.dao.shop.read.flash;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.flash.ActFlashBanner;

@Repository
public interface ActFlashBannerReadDao {

    ActFlashBanner get(java.lang.Integer id);

    Integer getActFlashBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActFlashBanner> getActFlashBanners(@Param("queryMap") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 查询限时抢购首页正在使用的轮播图
     * @param pcMobile
     * @return
     */
    List<ActFlashBanner> getActFlashBannersPcMobile(@Param("pcMobile") int pcMobile);

}