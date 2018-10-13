package com.sln.service.impl.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberBalancePayLog;
import com.sln.model.member.MemberBalancePayLogModel;
import com.sln.service.member.IMemberBalancePayLogService;

@Service(value = "memberBalancePayLogService")
public class MemberBalancePayLogServiceImpl implements IMemberBalancePayLogService {
    private static Logger            log = LogManager
        .getLogger(MemberBalancePayLogServiceImpl.class);

    @Resource
    private MemberBalancePayLogModel memberBalancePayLogModel;

    /**
    * 根据id取得会员充值记录
    * @param  memberBalancePayLogId
    * @return
    */
    @Override
    public ServiceResult<MemberBalancePayLog> getMemberBalancePayLogById(Integer memberBalancePayLogId) {
        ServiceResult<MemberBalancePayLog> result = new ServiceResult<MemberBalancePayLog>();
        try {
            result.setResult(
                memberBalancePayLogModel.getMemberBalancePayLogById(memberBalancePayLogId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberBalancePayLogService][getMemberBalancePayLogById]根据id["
                      + memberBalancePayLogId + "]取得会员充值记录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberBalancePayLogService][getMemberBalancePayLogById]根据id["
                      + memberBalancePayLogId + "]取得会员充值记录时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存会员充值记录
     * @param  memberBalancePayLog
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberBalancePayLog(MemberBalancePayLog memberBalancePayLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberBalancePayLogModel.saveMemberBalancePayLog(memberBalancePayLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberBalancePayLogService][saveMemberBalancePayLog]保存会员充值记录时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberBalancePayLogService][saveMemberBalancePayLog]保存会员充值记录时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新会员充值记录
    * @param  memberBalancePayLog
    * @return
    * @see com.sln.service.MemberBalancePayLogService#updateMemberBalancePayLog(MemberBalancePayLog)
    */
    @Override
    public ServiceResult<Integer> updateMemberBalancePayLog(MemberBalancePayLog memberBalancePayLog) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result
                .setResult(memberBalancePayLogModel.updateMemberBalancePayLog(memberBalancePayLog));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberBalancePayLogService][updateMemberBalancePayLog]更新会员充值记录时出现未知异常："
                      + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberBalancePayLogService][updateMemberBalancePayLog]更新会员充值记录时出现未知异常：",
                e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberBalancePayLog>> page(Map<String, String> queryMap,
                                                         PagerInfo pager) {
        ServiceResult<List<MemberBalancePayLog>> serviceResult = new ServiceResult<List<MemberBalancePayLog>>();
        try {
            serviceResult.setResult(memberBalancePayLogModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberBalancePayLogServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberBalancePayLogModel.del(id) > 0);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberBalancePayLogServiceImpl][del] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<MemberBalancePayLog> getByOrderSn(String orderNo) {
        ServiceResult<MemberBalancePayLog> serviceResult = new ServiceResult<MemberBalancePayLog>();
        try {
            serviceResult.setResult(memberBalancePayLogModel.getByOrderSn(orderNo));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberBalancePayLogServiceImpl][getByOrderSn] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> payAfter(String orderNo, String amount, String tradeSn) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(memberBalancePayLogModel.payAfter(orderNo, amount, tradeSn));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberBalancePayLogServiceImpl][payAfter] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> payBefore(String optionsRadios, String amount, String ordersn,
                                            Member member) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                memberBalancePayLogModel.payBefore(optionsRadios, amount, ordersn, member));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberBalancePayLogServiceImpl][payBefore] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}