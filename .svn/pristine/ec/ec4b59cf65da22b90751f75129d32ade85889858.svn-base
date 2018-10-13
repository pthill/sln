package com.sln.dao.shop.read.bidding;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.bidding.ActBiddingType;

@Repository
public interface ActBiddingTypeReadDao {

    ActBiddingType get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<ActBiddingType> getActBiddingTypes(@Param("param1") Map<String, String> queryMap,
                                            @Param("start") Integer start,
                                            @Param("size") Integer size);

    /**
     * 查询所有可用的集合竞价分类
     * @return
     */
    List<ActBiddingType> getAll();

}