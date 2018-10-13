package com.sln.service.impl.member;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.model.member.MemberGradeDownLogsModel;
import com.sln.service.member.IMemberGradeDownLogsService;

@Service(value = "memberGradeDownLogsService")
public class MemberGradeDownLogsServiceImpl implements IMemberGradeDownLogsService {
    private static Logger            log = LogManager
        .getLogger(MemberGradeDownLogsServiceImpl.class);

    @Resource
    private MemberGradeDownLogsModel memberGradeDownLogsModel;

    @Override
    public ServiceResult<Boolean> jobGradeValueDown() {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberGradeDownLogsModel.jobGradeValueDown());
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberGradeDownLogsService][jobGradeValueDown]对每年当天注册的会员减去相应的经验值数量时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IMemberGradeDownLogsService][jobGradeValueDown]对每年当天注册的会员减去相应的经验值数量时出现未知异常：", e);
        }
        return result;
    }

}