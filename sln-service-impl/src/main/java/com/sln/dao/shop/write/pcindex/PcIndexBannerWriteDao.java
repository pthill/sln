package com.sln.dao.shop.write.pcindex;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcindex.PcIndexBanner;

@Repository
public interface PcIndexBannerWriteDao {

    PcIndexBanner get(java.lang.Integer id);

    Integer insert(PcIndexBanner pcIndexBanner);

    Integer update(PcIndexBanner pcIndexBanner);

    Integer delete(Integer id);
}