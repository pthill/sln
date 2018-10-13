package com.sln.dao.shop.read.seller;

import org.springframework.stereotype.Repository;

import com.sln.entity.seller.SellerUserLoginLog;

@Repository
public interface SellerUserLoginLogReadDao {

    SellerUserLoginLog get(java.lang.Integer id);

}