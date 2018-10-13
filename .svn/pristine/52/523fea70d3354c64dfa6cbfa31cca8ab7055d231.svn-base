package com.sln.dao.shop.read.integral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.integral.ActIntegralType;

@Repository
public interface ActIntegralTypeReadDao {

    ActIntegralType get(java.lang.Integer id);

    int count(@Param("param1") Map<String, String> queryMap);

    List<ActIntegralType> getActIntegralTypes(@Param("param1") Map<String, String> queryMap,
                                              @Param("start") Integer start,
                                              @Param("size") Integer size);

    /**
     * 查询所有可用的积分商城分类
     * @return
     */
    List<ActIntegralType> getAll();

}