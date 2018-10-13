package com.sln.dao.shop.read.bidding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBidding;

@Repository
public interface ActBiddingReadDao {

    ActBidding get(java.lang.Integer id);

    int getActBiddingsCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActBidding> getActBiddings(@Param("queryMap") Map<String, String> queryMap,
                                    @Param("start") Integer start, @Param("size") Integer size);

    int getActBiddingsFrontCount(@Param("queryMap") Map<String, String> queryMap);

    List<ActBidding> getActBiddingsFront(@Param("queryMap") Map<String, String> queryMap,
                                         @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 取得该团购所属分类下的前5个商品
     * @param type
     * @param topNum
     * @return
     */
    List<ActBidding> getActBiddingsByType(@Param("type") int type, @Param("topNum") int topNum);

}