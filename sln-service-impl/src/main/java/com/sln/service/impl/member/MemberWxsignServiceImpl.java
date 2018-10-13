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
import com.sln.entity.member.MemberWxsign;
import com.sln.model.member.MemberWxsignModel;
import com.sln.service.member.IMemberWxsignService;

@Service(value = "memberWxsignService")
public class MemberWxsignServiceImpl implements IMemberWxsignService {
    private static Logger     log = LogManager.getLogger(MemberWxsignServiceImpl.class);

    @Resource
    private MemberWxsignModel memberWxsignModel;

    /**
    * 根据id取得微信联合登录
    * @param  memberWxsignId
    * @return
    */
    @Override
    public ServiceResult<MemberWxsign> getMemberWxsignById(Integer memberWxsignId) {
        ServiceResult<MemberWxsign> result = new ServiceResult<MemberWxsign>();
        try {
            result.setResult(memberWxsignModel.getMemberWxsignById(memberWxsignId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberWxsignService][getMemberWxsignById]根据id[" + memberWxsignId
                      + "]取得微信联合登录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberWxsignService][getMemberWxsignById]根据id[" + memberWxsignId
                      + "]取得微信联合登录时出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 保存微信联合登录
     * @param  memberWxsign
     * @return
     */
    @Override
    public ServiceResult<Integer> saveMemberWxsign(MemberWxsign memberWxsign) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberWxsignModel.saveMemberWxsign(memberWxsign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMemberWxsignService][saveMemberWxsign]保存微信联合登录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberWxsignService][saveMemberWxsign]保存微信联合登录时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新微信联合登录
    * @param  memberWxsign
    * @return
    * @see com.sln.service.MemberWxsignService#updateMemberWxsign(MemberWxsign)
    */
    @Override
    public ServiceResult<Integer> updateMemberWxsign(MemberWxsign memberWxsign) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberWxsignModel.updateMemberWxsign(memberWxsign));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error(
                "[IMemberWxsignService][updateMemberWxsign]更新微信联合登录时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMemberWxsignService][updateMemberWxsign]更新微信联合登录时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberWxsign>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<MemberWxsign>> serviceResult = new ServiceResult<List<MemberWxsign>>();
        try {
            serviceResult.setResult(memberWxsignModel.page(queryMap, pager));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberWxsignServiceImpl][page] exception:" + e.getMessage());
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Member> getWxUser(String openid, String accessToken) {
        ServiceResult<Member> serviceResult = new ServiceResult<>();
        try {
            serviceResult.setResult(memberWxsignModel.getWxUser(openid, accessToken));
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
            log.error("[MemberWxsignServiceImpl][getByOpenId] exception:" + e.getMessage());
        }
        return serviceResult;
    }
}