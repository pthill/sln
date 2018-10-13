package com.sln.dao.shop.write.member;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberSignLogs;

@Repository
public interface MemberSignLogsWriteDao {

    //	MemberSignLogs get(java.lang.Integer id);

    Integer getCountByMemberIdAndDate(@Param("memberId") Integer memberId,
                                      @Param("start") String start, @Param("end") String end);

    Integer insert(MemberSignLogs memberSignLogs);

    //	Integer update(MemberSignLogs memberSignLogs);

    //	Integer delete(java.lang.Integer id);

}