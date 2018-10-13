package com.sln.dao.shop.write.system;

import org.springframework.stereotype.Repository;

import com.sln.entity.system.Regions;

@Repository
public interface RegionsWriteDao {

    Regions get(Integer id);

}
