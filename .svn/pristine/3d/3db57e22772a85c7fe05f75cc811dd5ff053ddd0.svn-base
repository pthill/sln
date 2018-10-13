package com.sln.dao.shop.write.integral;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.integral.ActIntegralType;

@Repository
public interface ActIntegralTypeWriteDao {

    ActIntegralType get(java.lang.Integer id);

    Integer insert(ActIntegralType actIntegralType);

    Integer update(ActIntegralType actIntegralType);

    Integer audit(@Param("id") Integer id, @Param("state") Integer state);

    Integer del(Integer id);
}