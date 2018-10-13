package com.sln.dao.shop.write.mseller;

import org.springframework.stereotype.Repository;

import com.sln.entity.mseller.MSellerIndexFloor;

@Repository
public interface MSellerIndexFloorWriteDao {

    MSellerIndexFloor get(java.lang.Integer id);

    Integer insert(MSellerIndexFloor mIndexFloor);

    Integer update(MSellerIndexFloor mIndexFloor);

    Integer delete(java.lang.Integer id);
}