package com.sln.dao.shop.write.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberGradeDownLogs;

@Repository
public interface MemberGradeDownLogsWriteDao {

    //	MemberGradeDownLogs get(java.lang.Integer id);

    /**
     * 根据时间获取日志
     * @param excuteTime，形如yyyy-MM-dd
     * @return
     */
    List<MemberGradeDownLogs> getByExcuteTime(@Param("excuteTime") String excuteTime);

    Integer insert(MemberGradeDownLogs memberGradeDownLogs);

    //	Integer update(MemberGradeDownLogs memberGradeDownLogs);

    //	Integer delete(java.lang.Integer id);

}