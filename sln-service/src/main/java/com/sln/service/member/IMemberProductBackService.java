package com.sln.service.member;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberProductBack;

import java.util.List;
import java.util.Map;

public interface IMemberProductBackService {

    /**
     * 根据id取得用户退货
     * @param  memberProductBackId
     * @return
     */
    ServiceResult<MemberProductBack> getMemberProductBackById(Integer memberProductBackId);

    /**
     * 根据登录用户取得用户退货列表
     * @param request
     * @return
     */
    ServiceResult<List<MemberProductBack>> getMemberProductBackList(PagerInfo pager,
                                                                    Integer memberId);

    /**
     * 判断是否可以发起退换货申请
     * @param orderId
     * @param orderProductId
     * @param request
     * @return
     */
    public ServiceResult<Integer> canApplyProductBackOrExchange(Integer orderId,
                                                                Integer orderProductId,
                                                                Integer memberId);

    /**
     * 保存用户退货
     * @param memberProductBack
     * @param request
     * @return
     */
    ServiceResult<Boolean> saveMemberProductBack(MemberProductBack memberProductBack,
                                                 Member member);

    /**
    * 更新用户退货
    * @param  memberProductBack
    * @return
    */
    ServiceResult<Integer> updateMemberProductBack(MemberProductBack memberProductBack);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<MemberProductBack>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 用户退货时退款
     * @param memberProductBackId 退货申请ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     * @return
     */
    ServiceResult<Boolean> backMoney(Integer memberProductBackId, Integer optId, String optName);

    /**
     * 获取结算周期内的退货申请
     * @param sellerId
     * @param startTime
     * @param endTime
     * @param pager
     * @return
     */
    ServiceResult<List<MemberProductBack>> getSettleBacks(Integer sellerId, String startTime,
                                                          String endTime, PagerInfo pager);

    /**
     * 根据登录用户取得用户退货列表（封装商品对象和网单对象）
     * @param pager
     * @param memberId
     * @return
     */
    ServiceResult<List<MemberProductBack>> getBackListWithPrdAndOp(PagerInfo pager,
                                                                   Integer memberId);
    
    /*
     *@Author:yangmingxin
     *@Description:定时任务获取每天的退款的订单
     *@Date:2018/1/16 12:16
     */
    ServiceResult<Boolean> jobBackOrder();

    /**
     * 查询批次写的退货订单详情
     * @param pc
     * @return
     */
    ServiceResult<List<MemberProductBack>> queryByPc(String pc);
}