package com.sln.dao.shop.write.bidding;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBiddingType;

@Repository
public interface ActBiddingTypeWriteDao {

    ActBiddingType get(java.lang.Integer id);

    Integer insert(ActBiddingType actBiddingType);

    Integer update(ActBiddingType actBiddingType);

    Integer audit(@Param("id") Integer id, @Param("state") Integer state);

    Integer del(Integer id);
}