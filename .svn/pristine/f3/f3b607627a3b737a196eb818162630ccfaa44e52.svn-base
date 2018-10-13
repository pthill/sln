package com.sln.service.member;

import com.sln.core.ServiceResult;

/**
 * 会员年度经验值递减service
 * 
 * @Filename: IMemberGradeDownLogsService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberGradeDownLogsService {

    /**
     * 每天执行一次，对每年当天注册的会员减去相应的经验值数量，影响会员等级（处理完成后检查前2天的执行情况防止服务器维护等原因导致的定时任务未执行）
     * @return
     */
    ServiceResult<Boolean> jobGradeValueDown();

}