package com.sln.service.order;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.dto.OrderDayDto;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.system.SystemAdmin;
import com.sln.vo.order.OrderCommitVO;
import com.sln.vo.order.OrderSuccessVO;

/**
 * 订单服务
 *                       
 * @Filename: IOrdersService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IOrdersService {

    /**
     * 根据id取得订单
     * @param  ordersId
     * @return
     */
    ServiceResult<Orders> getOrdersById(Integer ordersId);

    /**
     * 根据orderSn取得订单
     * @param  orderSn
     * @return
     */
    ServiceResult<Orders> getOrdersBySn(String orderSn);

    /**
     * 根据订单ID获取订单号
     * @param id 订单ID
     * @return
     */
    ServiceResult<String> getOrderSnById(Integer ordersId);

    /**
     * 根据订单ID删除订单
     * @param ordersId
     * @return
     */
    ServiceResult<Boolean> deleteOrder(Integer ordersId);

    /**
     * 根据条件查询订单，PagerInfo传null返回所有符合条件的数据
     * @param queryMap key以q_开头的属性名称，比如q_orderSn
     * @param pager
     * @return
     */
    ServiceResult<List<Orders>> getOrders(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 更新订单
     * @param orders
     * @param type
     * @param seller
     * @return
     */
    ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type, SellerUser sellerUser);

    /**
     * 商家确认订单
     * @param orderId
     * @param seller
     * @return
     */
    ServiceResult<Boolean> confirmOrdersBySeller(Integer orderId, SellerUser sellerUser);

    /**
     * 更新订单
     * @param orders
     * @param type
     * @param systemAdmin
     * @return
     */
    ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type, SystemAdmin systemAdmin);

    /**
     * 商家取消订单，目前只有订单状态为 1、2的可以由商家取消（其中3的只能用户自己取消，不能由商家取消）
     * @param orders
     * @param type
     * @param seller
     * @param updateStore 是否更新库存
     * @return
     */
    ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser);

    /**
     * 根据会员ID，订单状态获取 子订单 数量
     * @param memberId
     * @param orderState
     * @return
     */
    ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState);

    /**
     * 根据用户ID等条件获取用户订单，Order对象封装了该订单下的网单
     * <br>用户中心订单列表页用
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                         PagerInfo pager);

    /**
     * 取消订单 目前只有订单状态为 1、2、3的可以取消（其中3的只能用户自己取消，不能由商家取消）
     * @param ordersId 订单ID
     * @param optId 操作人ID
     * @param optName 操作人名称
     */
    public ServiceResult<Boolean> cancelOrder(Integer ordersId, Integer optId, String optName);

    /**
     * 根据订单id 取订单、网单及产品图片信息
     * @param orderId
     * @return
     */
    ServiceResult<Orders> getOrderWithOPById(Integer orderId);

    /**
     * 用户提交订单<br>
     * 1、判断是否使用余额、判断支付密码<br>
     * 2、按商家拆分订单<br>
     * 3、保存网单<br>
     * 4、清除购物车<br>
     * 5、如果使用余额，并且余额足够支付所有订单，修改支付状态、修改库存<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    ServiceResult<OrderSuccessVO> orderCommit(OrderCommitVO orderCommitVO);

    /**
     * 确认收货
     * @param member
     * @param orderId
     * @return
     */
    ServiceResult<Boolean> goodsReceipt(Member member, Integer ordersId);

    /**
     * 统计每天订单量
     * @param queryMap
     * @return
     */
    ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap);

    /**
     * 系统自动完成订单<br>
     * <li>对已发货状态的订单发货时间超过15个自然日的订单进行自动完成处理
     * @return
     */
    ServiceResult<Boolean> jobSystemFinishOrder();

    /**
     * 系统自动取消24小时没有付款订单<br>
     * @return
     */
    ServiceResult<Boolean> jobSystemCancelOrder();

    /**
     * 根据支付订单号查询订单信息，计算支付金额等
     * @param paySn
     * @param memberId
     * @return
     */
    ServiceResult<OrderSuccessVO> getOrdersByPaySn(String paySn, Integer memberId);

    /**
     * 根据订单号查询订单信息，计算支付金额等
     * @param orderSn
     * @param memberId
     * @return
     */
    ServiceResult<OrderSuccessVO> getOrdersByOrderSn(String orderSn, Integer memberId);

    /**
     * 支付之前的调用，获取订单列表，以及用余额支付等逻辑<br/>
     * 假如余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param isPaySn 是否是支付订单号，true则paySn是支付订单号，false则paySn为订单号
     * @param paySn 支付订单号或者订单号（根据isPaySn决定）
     * @param isBalancePay 是否用余额支付
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    ServiceResult<OrderSuccessVO> orderPayBefore(Boolean isPaySn, String paySn,
                                                 Boolean isBalancePay, String balancePassword,
                                                 Member member);
    
    /**
     * 支付之前的调用，获取订单列表，以及用积分和余额支付等逻辑<br/>
     * 假如积分和余额够支付，那么直接更改订单的状态，返回支付成功页面
     * @param isPaySn 是否是支付订单号，true则paySn是支付订单号，false则paySn为订单号
     * @param paySn 支付订单号或者订单号（根据isPaySn决定）
     * @param type  4:福利订单 积分足够  5:福利订单 积分不够 余额抵扣
     * @param balancePassword 余额密码，未加密
     * @param member
     * @return
     */
    ServiceResult<OrderSuccessVO> orderPayByIntegral(Boolean isPaySn, String paySn,Integer type,
                                                 String balancePassword,Member member);
    
    /**
     * 支付成功之后更改订单的状态
     * @param trade_no 订单
     * @param total_fee 金额
     * @param paycode 支付方式
     * @param payname 支付方式
     * @param tradeSn 交易流水号
     * @param tradeContent 交易返回信息
     * @return
     */
    ServiceResult<Boolean> orderPayAfter(String trade_no, String total_fee, String paycode,
                                         String payname, String tradeSn, String tradeContent);

    /**
     * 用户限时抢购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    ServiceResult<OrderSuccessVO> orderCommitForFlash(OrderCommitVO orderCommitVO);

    /**
     * 用户团购提交订单<br>
     * @param orderCommitVO
     * @return
     * @throws Exception
     */
    ServiceResult<OrderSuccessVO> orderCommitForGroup(OrderCommitVO orderCommitVO);

    /**
     * 用户提交集合竞价订单<br>
     * @param orderCommitVO
     * @return
     */
    ServiceResult<OrderSuccessVO> orderCommitForBidding(OrderCommitVO orderCommitVO);

    /**
     * 用户提交积分换购订单<br>
     * @param orderCommitVO
     * @return
     */
    ServiceResult<OrderSuccessVO> orderCommitForIntegral(OrderCommitVO orderCommitVO);
    
    /**
     * 用户提交福利积分订单<br>
     * @param orderCommitVO
     * @return
     */
    ServiceResult<OrderSuccessVO> orderCommitForWelfare(OrderCommitVO orderCommitVO);
    

    /**
     * 当天待确认订单数
     * @return
     */
    ServiceResult<Integer> getReconfOrdersCount();

    /**
     * 获取待评价订单数
     * @param memberId
     * @return
     */
    ServiceResult<Integer> getOrderNumByMIdAndEvaluateState(Integer memberId);
    /**
     * 验证订单发货
     */
    ServiceResult<Integer> verifiDelivery(String orderSn);
    
    /**
     * 订单发货
     */
    ServiceResult<Integer> doDelivery(Orders orders,Orders ordersDb,SellerUser sellerUser);
    
    /**
     * 处理京东订单相关推送消息
     * @param access_token 访问京东的令牌
     * @param messageList 推送消息
     * @return
     */
    ServiceResult<Boolean> handleJDOrderMessage(AccessToken token ,Map<Integer,List<JDMessage>> messageList);
    
    
}