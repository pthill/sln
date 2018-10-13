package com.sln.dao.shop.write.mseller;

import org.springframework.stereotype.Repository;

import com.sln.entity.mseller.MSellerIndexBanner;

@Repository
public interface MSellerIndexBannerWriteDao {

    MSellerIndexBanner get(java.lang.Integer id);

    Integer insert(MSellerIndexBanner mIndexBanner);

    Integer update(MSellerIndexBanner mIndexBanner);

    Integer delete(Integer id);
}