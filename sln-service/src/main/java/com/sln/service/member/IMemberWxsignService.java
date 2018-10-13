package com.sln.service.member;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberWxsign;

public interface IMemberWxsignService {

    /**
     * 根据id取得微信联合登录
     * @param  memberWxsignId
     * @return
     */
    ServiceResult<MemberWxsign> getMemberWxsignById(Integer memberWxsignId);

    /**
     * 保存微信联合登录
     * @param  memberWxsign
     * @return
     */
    ServiceResult<Integer> saveMemberWxsign(MemberWxsign memberWxsign);

    /**
    * 更新微信联合登录
    * @param  memberWxsign
    * @return
    */
    ServiceResult<Integer> updateMemberWxsign(MemberWxsign memberWxsign);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberWxsign>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 以认证用户获取member信息<br>
     * 如果用户已绑定，返回原用户，否则创建新的用户
     * @param openid 用户标识
     * @param accessToken 访问令牌
     * @return
     */
    ServiceResult<Member> getWxUser(String openid, String accessToken);
}