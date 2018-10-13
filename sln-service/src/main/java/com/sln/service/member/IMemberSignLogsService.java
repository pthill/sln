package com.sln.service.member;

import com.sln.core.ServiceResult;
import com.sln.entity.member.MemberSignLogs;

/**
 * 会员签到service
 *
 * @Filename: IMemberSignLogsService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberSignLogsService {

    //	/**
    //     * 根据id取得会员签到日志
    //     * @param  memberSignLogsId
    //     * @return
    //     */
    //    ServiceResult<MemberSignLogs> getMemberSignLogsById(Integer memberSignLogsId);

    /**
     * 保存会员签到日志
     * @param  memberSignLogs
     * @return
     */
    ServiceResult<Boolean> saveMemberSignLogs(MemberSignLogs memberSignLogs);

    /**
     * 会员当日是否签到
     * @param  memberId
     * @return true：当日已签到；false：当日未签到
     */
    ServiceResult<Boolean> isMemberSignToday(Integer memberId);

    //     /**
    //     * 更新会员签到日志
    //     * @param  memberSignLogs
    //     * @return
    //     */
    //     ServiceResult<Integer> updateMemberSignLogs(MemberSignLogs memberSignLogs);
}