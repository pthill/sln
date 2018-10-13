package com.sln.dao.shop.write.bidding;

import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBiddingPrice;

@Repository
public interface ActBiddingPriceWriteDao {

    ActBiddingPrice get(java.lang.Integer id);

    Integer insert(ActBiddingPrice actBiddingPrice);

    Integer update(ActBiddingPrice actBiddingPrice);

    Integer delActBiddingByIds(Integer id);

}