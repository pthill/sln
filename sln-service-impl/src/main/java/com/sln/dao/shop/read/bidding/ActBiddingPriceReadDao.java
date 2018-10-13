package com.sln.dao.shop.read.bidding;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBiddingPrice;

@Repository
public interface ActBiddingPriceReadDao {

    ActBiddingPrice get(java.lang.Integer id);

    List<ActBiddingPrice> getActBiddingByIds(Integer id);

}