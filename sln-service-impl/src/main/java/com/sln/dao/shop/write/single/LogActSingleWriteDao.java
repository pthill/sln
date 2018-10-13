package com.sln.dao.shop.write.single;

import org.springframework.stereotype.Repository;

import com.sln.entity.single.LogActSingle;

@Repository
public interface LogActSingleWriteDao {

    // LogActSingle get(java.lang.Integer id);

    Integer insert(LogActSingle logActSingle);

    // Integer update(LogActSingle logActSingle);
}