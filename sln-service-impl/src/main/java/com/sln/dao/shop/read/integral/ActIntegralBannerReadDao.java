package com.sln.dao.shop.read.integral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.integral.ActIntegralBanner;

@Repository
public interface ActIntegralBannerReadDao {

    ActIntegralBanner get(java.lang.Integer id);

    Integer getActIntegralBannersCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActIntegralBanner> getActIntegralBanners(@Param("queryMap") Map<String, String> queryMap,
                                                  @Param("start") Integer start,
                                                  @Param("size") Integer size);

    /**
     * 查询积分商城首页正在使用的轮播图
     * @param pcMobile
     * @return
     */
    List<ActIntegralBanner> getActIntegralBannersPcMobile(@Param("pcMobile") int pcMobile);

}