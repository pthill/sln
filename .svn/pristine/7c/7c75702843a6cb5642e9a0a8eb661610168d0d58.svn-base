package com.sln.service.impl.member;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberSpecialIntegral;
import com.sln.entity.order.Invoice;
import com.sln.model.member.InvoiceModel;
import com.sln.model.member.MemberSpecialIntegralModel;
import com.sln.service.member.IInvoiceService;
import com.sln.service.member.IMemberSpecialIntegralService;

@Service(value = "memberSpecialIntegralService")
public class MemberSpecialIntegralServiceImpl implements IMemberSpecialIntegralService {
    private static Logger log = LogManager.getLogger(MemberSpecialIntegralServiceImpl.class);

    @Resource
    private MemberSpecialIntegralModel  memberSpecialIntegralModel;

	@Override
	public ServiceResult<List<MemberSpecialIntegral>> getMemberSpecialIntegralByMemberId(Integer memberId,
			Integer sellerId) {
		ServiceResult<List<MemberSpecialIntegral>> serviceResult = new ServiceResult<List<MemberSpecialIntegral>>();
        try {
            serviceResult.setResult(memberSpecialIntegralModel.getMemberSpecialIntegralByMemberId(memberId,sellerId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberSpecialIntegralServiceImpl][getMemberSpecialIntegralByMemberId]根据用户ID获取商家发放的专项积分列表时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberSpecialIntegralServiceImpl][getMemberSpecialIntegralByMemberId]根据用户ID获取商家发放的专项积分列表时发生异常:", e);
        }
        return serviceResult;
	}

	@Override
	public ServiceResult<Integer> getValueByMemberId(Integer memberId) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(memberSpecialIntegralModel.getValueByMemberId(memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[MemberSpecialIntegralServiceImpl][getValueByMemberId]根据用户ID获取所有可用专项积分时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[MemberSpecialIntegralServiceImpl][getValueByMemberId]根据用户ID获取所有可用专项积分时发生异常:", e);
        }
        return serviceResult;
	}

   
}