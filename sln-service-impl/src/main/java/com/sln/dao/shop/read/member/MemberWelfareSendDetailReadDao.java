package com.sln.dao.shop.read.member;

import com.sln.entity.member.MemberWelfareSendDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MemberWelfareSendDetailReadDao {

    List<MemberWelfareSendDetail> page(@Param("WelfareSendId")Integer id,
                                       @Param("start") Integer start, @Param("size") Integer size);

    Integer pageCount(@Param("WelfareSendId")Integer id);



}
