package com.sln.dao.shop.write.pcindex;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcindex.PcIndexFloor;

@Repository
public interface PcIndexFloorWriteDao {

    PcIndexFloor get(java.lang.Integer id);

    Integer insert(PcIndexFloor pcIndexFloor);

    Integer update(PcIndexFloor pcIndexFloor);

    Integer delete(Integer id);
}