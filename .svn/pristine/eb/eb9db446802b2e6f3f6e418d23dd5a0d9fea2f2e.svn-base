package com.sln.dao.shop.read.member;

import com.sln.entity.member.MemberWelfareSend;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface MemberWelfareSendReadDao {

    MemberWelfareSend get(Integer id);

    List<MemberWelfareSend> page(@Param("queryMap")Map<String,String>queryMap,
                                 @Param("size")Integer size,@Param("start")Integer start);

    Integer pageCount(@Param("queryMap")Map<String,String>queryMap);
}
