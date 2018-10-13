package com.sln.dao.shop.write.bidding;

import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBiddingBanner;

@Repository
public interface ActBiddingBannerWriteDao {

    ActBiddingBanner get(java.lang.Integer id);

    Integer insert(ActBiddingBanner actBiddingBanner);

    Integer update(ActBiddingBanner actBiddingBanner);

    Integer delete(Integer id);
}