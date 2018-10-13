package com.sln.dao.shop.write.member;

import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberGradeUpLogs;

/**
 * 会员等级升级日志表
 * 
 * @Filename: MemberGradeUpLogsWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberGradeUpLogsWriteDao {

    MemberGradeUpLogs get(java.lang.Integer id);

    Integer save(MemberGradeUpLogs memberGradeUpLogs);

    Integer update(MemberGradeUpLogs memberGradeUpLogs);
}
