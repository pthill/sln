package com.sln.dao.shop.write.pcseller;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcseller.PcSellerRecommendData;

@Repository
public interface PcSellerRecommendDataWriteDao {

    PcSellerRecommendData get(java.lang.Integer id);

    Integer insert(PcSellerRecommendData pcSellerRecommendData);

    Integer update(PcSellerRecommendData pcSellerRecommendData);

    Integer delete(Integer id);

    Integer deleteByRecommendId(Integer recommendId);
}