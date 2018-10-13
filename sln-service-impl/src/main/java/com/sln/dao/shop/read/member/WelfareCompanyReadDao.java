package com.sln.dao.shop.read.member;

import com.sln.entity.member.WelfareCompany;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface WelfareCompanyReadDao {
    WelfareCompany get(Integer id);
    Integer pageCount(@Param("queryMap") Map<String,String>queryMap);
    List<WelfareCompany> page(@Param("queryMap") Map<String,String>queryMap,int pageSize,int pageNum);
}
