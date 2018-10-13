package com.sln.service.member;

import com.sln.core.ServiceResult;
import com.sln.entity.member.MemberWelfareSendDetail;

public interface IMemberWelfareSendDetailService {

    /**
     * 根据id取得福利积分发放详情
     * @param  memberWelfareSendDetailId
     * @return
     */
    ServiceResult<MemberWelfareSendDetail> getMemberWelfareSendDetailById(Integer memberWelfareSendDetailId);

    /**
     * 保存福利积分发放详情
     * @param  memberWelfareSendDetail
     * @return
     */
    ServiceResult<Integer> saveMemberWelfareSendDetail(MemberWelfareSendDetail memberWelfareSendDetail);

    /**
     * 更新福利积分发放详情
     * @param  memberWelfareSendDetail
     * @return
     */
    ServiceResult<Integer> updateMemberWelfareSendDetail(MemberWelfareSendDetail memberWelfareSendDetail);
}
