package com.sln.dao.shop.write.pcindex;

import org.springframework.stereotype.Repository;

import com.sln.entity.pcindex.PcIndexFloorData;

@Repository
public interface PcIndexFloorDataWriteDao {

    PcIndexFloorData get(java.lang.Integer id);

    Integer insert(PcIndexFloorData pcIndexFloorData);

    Integer update(PcIndexFloorData pcIndexFloorData);

    Integer delete(Integer id);

    Integer deleteByFloorClassId(Integer floorClassId);
}