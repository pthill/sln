package com.sln.dao.shop.write.flash;

import org.springframework.stereotype.Repository;

import com.sln.entity.flash.ActFlashBanner;

@Repository
public interface ActFlashBannerWriteDao {

    ActFlashBanner get(java.lang.Integer id);

    Integer insert(ActFlashBanner actFlashBanner);

    Integer update(ActFlashBanner actFlashBanner);

    Integer delete(Integer id);
}