package com.sln.service.member;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberBalancePayLog;

public interface IMemberBalancePayLogService {

    /**
     * 根据id取得会员充值记录
     * @param  memberBalancePayLogId
     * @return
     */
    ServiceResult<MemberBalancePayLog> getMemberBalancePayLogById(Integer memberBalancePayLogId);

    /**
     * 保存会员充值记录
     * @param  memberBalancePayLog
     * @return
     */
    ServiceResult<Integer> saveMemberBalancePayLog(MemberBalancePayLog memberBalancePayLog);

    /**
    * 更新会员充值记录
    * @param  memberBalancePayLog
    * @return
    */
    ServiceResult<Integer> updateMemberBalancePayLog(MemberBalancePayLog memberBalancePayLog);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberBalancePayLog>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 以订单号获取支付日志
     * @param orderNo
     * @return
     */
    ServiceResult<MemberBalancePayLog> getByOrderSn(String orderNo);

    /**
     * 支付后更改支付记录
     * @param orderNo
     * @param amount
     * @param tradeSn
     * @return
     */
    ServiceResult<Boolean> payAfter(String orderNo, String amount, String tradeSn);

    /**
     * 余额支付前操作：创建支付记录
     * @param optionsRadios
     * @param amount
     * @param ordersn
     * @param member
     * @return
     */
    ServiceResult<Boolean> payBefore(String optionsRadios, String amount, String ordersn,
                                     Member member);

}