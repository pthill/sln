package com.sln.service.member;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.member.MemberSpecialIntegral;
import com.sln.entity.order.Invoice;

public interface IMemberSpecialIntegralService {

    /**
     * 根据用户Id和商家id获取商家发放的专项积分
     * @param memberId
     * @param sellerId
     * @return
     */
    ServiceResult<List<MemberSpecialIntegral>> getMemberSpecialIntegralByMemberId(Integer memberId,Integer sellerId);

    ServiceResult<Integer> getValueByMemberId(Integer memberId);
}