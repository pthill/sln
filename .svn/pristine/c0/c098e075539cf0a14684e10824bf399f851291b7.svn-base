package com.sln.dao.shop.read.member;

import com.sln.entity.member.JobProductBack;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JobProductBackReadDao {
    JobProductBack getById(Integer id);
    List<JobProductBack> queryPage(@Param("queryMap") Map<String,String>queryMap,
                                   @Param("size") Integer size,
                                   @Param("start") Integer start);

    Integer queryCount(@Param("queryMap") Map<String,String>queryMap);
}
