package com.sln.dao.shop.read.operate;

import org.springframework.stereotype.Repository;

import com.sln.entity.operate.Config;

@Repository
public interface ConfigReadDao {

    Config get(java.lang.Integer id);

}