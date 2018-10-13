package com.sln.dao.shop.write.mindex;

import org.springframework.stereotype.Repository;

import com.sln.entity.mindex.MIndexFloor;

@Repository
public interface MIndexFloorWriteDao {

    MIndexFloor get(java.lang.Integer id);

    Integer insert(MIndexFloor mIndexFloor);

    Integer update(MIndexFloor mIndexFloor);

    Integer delete(java.lang.Integer id);
}