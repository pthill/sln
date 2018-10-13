package com.sln.dao.shop.write.integral;

import org.springframework.stereotype.Repository;

import com.sln.entity.integral.ActIntegralBanner;

@Repository
public interface ActIntegralBannerWriteDao {

    ActIntegralBanner get(java.lang.Integer id);

    Integer insert(ActIntegralBanner actIntegralBanner);

    Integer update(ActIntegralBanner actIntegralBanner);

    Integer delete(Integer id);
}