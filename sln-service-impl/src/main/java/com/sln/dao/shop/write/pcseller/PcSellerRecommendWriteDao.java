package com.sln.dao.shop.write.pcseller;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcseller.PcSellerRecommend;

@Repository
public interface PcSellerRecommendWriteDao {

    PcSellerRecommend get(java.lang.Integer id);

    Integer insert(PcSellerRecommend pcSellerRecommend);

    Integer update(PcSellerRecommend pcSellerRecommend);

    Integer delete(Integer id);
}