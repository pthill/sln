package com.sln.dao.shop.read.member;

import com.sln.entity.member.MemberSpecialIntegral;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberSpecialIntegralReadDao {
    MemberSpecialIntegral get(Integer id);
    Integer pageCount(@Param("queryMap") Map<String,Object> queryMap);
    List<MemberSpecialIntegral> page(@Param("queryMap") Map<String,Object>queryMap, @Param("size")Integer size,@Param("start")Integer start);
    Integer getValueByMemberId(@Param("queryMap") Map<String,Object>queryMap);
}
