package com.sln.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberSignLogs;
import com.sln.model.member.MemberSignLogsModel;
import com.sln.service.member.IMemberSignLogsService;

@Service(value = "memberSignLogsService")
public class MemberSignLogsServiceImpl implements IMemberSignLogsService {
    private static Logger       log = LogManager.getLogger(MemberSignLogsServiceImpl.class);

    @Resource
    private MemberSignLogsModel memberSignLogsModel;

    /**
     * 保存会员签到日志
     * @param  memberSignLogs
     * @return
     */
    @Override
    public ServiceResult<Boolean> saveMemberSignLogs(MemberSignLogs memberSignLogs) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberSignLogsModel.saveMemberSignLogs(memberSignLogs));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IMemberSignLogsService][saveMemberSignLogs]保存会员签到日志时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberSignLogsService][saveMemberSignLogs]保存会员签到日志时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Boolean> isMemberSignToday(Integer memberId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberSignLogsModel.isMemberSignToday(memberId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IMemberSignLogsService][isMemberSignToday]判断会员当日是否签到时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberSignLogsService][isMemberSignToday]判断会员当日是否签到时出现未知异常：", e);
        }
        return result;
    }
}