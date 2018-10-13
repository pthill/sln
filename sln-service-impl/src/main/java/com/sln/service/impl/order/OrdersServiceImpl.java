package com.sln.service.impl.order;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.dto.OrderDayDto;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.system.SystemAdmin;
import com.sln.model.order.OrdersModel;
import com.sln.service.order.IOrdersService;
import com.sln.vo.order.OrderCommitVO;
import com.sln.vo.order.OrderSuccessVO;

@Service(value = "ordersService")
public class OrdersServiceImpl implements IOrdersService {
    private static Logger log = LogManager.getLogger(OrdersServiceImpl.class);

    @Resource
    private OrdersModel   ordersModel;

    @Override
    public ServiceResult<Orders> getOrdersById(Integer ordersId) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrdersById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersById]根据id[" + ordersId + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrdersBySn(String orderSn) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrdersBySn(orderSn));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrdersBySn]根据orderSn[" + orderSn + "]取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<String> getOrderSnById(Integer ordersId) {
        ServiceResult<String> serviceResult = new ServiceResult<String>();
        try {
            serviceResult.setResult(ordersModel.getOrderSnById(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderSnById]根据id[" + ordersId + "]取得订单号时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteOrder(Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.deleteOrder(ordersId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][deleteOrder]根据id[" + ordersId + "]删除订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getOrders(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<Orders> list = ordersModel.getOrders(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrders] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[OrdersService][getOrders]根据条件取得订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersBySeller(Orders orders, int type,
                                                       SellerUser sellerUser) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.updateOrdersBySeller(orders, type, sellerUser));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersBySeller]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> confirmOrdersBySeller(Integer orderId, SellerUser sellerUser) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.confirmOrdersBySeller(orderId, sellerUser));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][confirmOrdersBySeller]商家确认订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][confirmOrdersBySeller]商家确认订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> updateOrdersByAdmin(Orders orders, int type,
                                                      SystemAdmin systemAdmin) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.updateOrdersByAdmin(orders, type, systemAdmin));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][updateOrdersByAdmin]更新订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrderBySeller(Integer ordersId, SellerUser sellerUser) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.cancelOrderBySeller(ordersId, sellerUser));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][cancelOrderBySeller]取消订单表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getOrderNumByMIdAndState(Integer memberId, Integer orderState) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(ordersModel.getOrderNumByMIdAndState(memberId, orderState));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrdersService][getOrderNumByMIdAndState]根据会员ID，订单状态获取子订单数量时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<Orders>> getOrderWithOrderProduct(Map<String, String> queryMap,
                                                                PagerInfo pager) {
        ServiceResult<List<Orders>> serviceResult = new ServiceResult<List<Orders>>();
        serviceResult.setPager(pager);
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(ordersModel.getOrdersCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<Orders> returnList = ordersModel.getOrderWithOrderProduct(queryMap, start, size);
            serviceResult.setResult(returnList);
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOrderProduct]根据用户ID获取用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> cancelOrder(Integer ordersId, Integer optId, String optName) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.cancelOrder(ordersId, optId, optName));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][cancelOrder]根据ID取消用户订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][cancalOrder]根据ID取消用户订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Orders> getOrderWithOPById(Integer orderId) {
        ServiceResult<Orders> serviceResult = new ServiceResult<Orders>();
        try {
            serviceResult.setResult(ordersModel.getOrderWithOPById(orderId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][getOrderWithOPById]根据订单id取订单、网单及产品图片信息时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderWithOPById]根据订单id 取订单、网单及产品图片信息时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommit(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommit(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommit]会员下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommit]会员下单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 确认收货
     * @param ordersId
     * @param request
     * @return
     */
    @Override
    public ServiceResult<Boolean> goodsReceipt(Member member, Integer ordersId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.goodsReceipt(member, ordersId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][goodsReceipt]订单确认收货时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<OrderDayDto>> getOrderDayDto(Map<String, String> queryMap) {
        ServiceResult<List<OrderDayDto>> serviceResult = new ServiceResult<List<OrderDayDto>>();
        try {
            serviceResult.setResult(ordersModel.getOrderDayDto(queryMap));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrderDayDto]统计每天订单量时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemFinishOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.jobSystemFinishOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemFinishOrder]系统完成订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> jobSystemCancelOrder() {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.jobSystemCancelOrder());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][jobSystemCancelOrder]系统取消订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据支付订单号查询订单信息，计算支付金额等
     * @param paySn
     * @param memberId
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> getOrdersByPaySn(String paySn, Integer memberId) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.getOrdersByPaySn(paySn, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrdersByPaySn]查询订单时发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据订单号查询订单信息，计算支付金额等
     * @param orderSn
     * @param memberId
     * @return
     */
    @Override
    public ServiceResult<OrderSuccessVO> getOrdersByOrderSn(String orderSn, Integer memberId) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.getOrdersByOrderSn(orderSn, memberId));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][getOrdersByOrderSn]查询订单时发生异常:", e);
        }
        return serviceResult;
    }

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
    @Override
    public ServiceResult<OrderSuccessVO> orderPayBefore(Boolean isPaySn, String paySn,
                                                        Boolean isBalancePay,
                                                        String balancePassword, Member member) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(
                ordersModel.orderPayBefore(isPaySn, paySn, isBalancePay, balancePassword, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayBefore]支付之前的调用，获取订单列表，以及用余额支付等逻辑发生异常:", e);
            e.printStackTrace();
        }
        return serviceResult;
    }
    
	@Override
	public ServiceResult<OrderSuccessVO> orderPayByIntegral(Boolean isPaySn, String paySn,Integer type,
			String balancePassword,Member member) {
		ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(
                ordersModel.orderPayByIntegral(isPaySn, paySn,type, balancePassword, member));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error(
                "[OrderService][orderPayByIntegral]支付之前的调用，获取订单列表，以及用积分和余额支付等逻辑发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayByIntegral]支付之前的调用，获取订单列表，以及用积分和余额支付等逻辑发生异常:", e);
            e.printStackTrace();
        }
        return serviceResult;
	}

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
    @Override
    public ServiceResult<Boolean> orderPayAfter(String trade_no, String total_fee, String paycode,
                                                String payname, String tradeSn,
                                                String tradeContent) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(ordersModel.orderPayAfter(trade_no, total_fee, paycode, payname,
                tradeSn, tradeContent));
        } catch (BusinessException be) {
            be.printStackTrace();
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态发生异常:" + be.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderPayAfter]支付成功之后更改订单的状态辑发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForFlash(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForFlash(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForFlash]会员限时抢购下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForFlash]会员限时抢购下单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForGroup(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForGroup(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForGroup]会员团购下单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForGroup]会员团购下单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForBidding(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForBidding(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForBidding]会员提交集合竞价订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForBidding]会员提交集合竞价订单时发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<OrderSuccessVO> orderCommitForIntegral(OrderCommitVO orderCommitVO) {
        ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForIntegral(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForIntegral]会员提交积分换购订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForIntegral]会员提交积分换购订单时发生异常:", e);
        }
        return serviceResult;
    }
    
	@Override
	public ServiceResult<OrderSuccessVO> orderCommitForWelfare(OrderCommitVO orderCommitVO) {
		ServiceResult<OrderSuccessVO> serviceResult = new ServiceResult<OrderSuccessVO>();
        try {
            serviceResult.setResult(ordersModel.orderCommitForWelfare(orderCommitVO));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
            log.error("[OrderService][orderCommitForWelfare]会员提交福利积分订单时发生异常:" + be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[OrderService][orderCommitForWelfare]会员提交福利积分订单时发生异常:", e);
        }
        return serviceResult;
	}

    @Override
    public ServiceResult<Integer> getReconfOrdersCount() {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            Integer res = ordersModel.getReconfOrdersCount();
            serviceResult.setResult(res);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getOrderNumByMIdAndEvaluateState(Integer memberId) {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            Integer res = ordersModel.getOrderNumByMIdAndEvaluateState(memberId);
            serviceResult.setResult(res);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }

	@Override
	public ServiceResult<Integer> verifiDelivery(String orderSn) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(ordersModel.verifiDelivery(orderSn));
		}catch(Exception e){
			//如果异常则返回3
			serviceResult.setResult(3);
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
		                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Integer> doDelivery(Orders orders, Orders ordersDb, SellerUser sellerUser) {
		ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
		try{
			serviceResult.setResult(ordersModel.doDelivery(orders, ordersDb, sellerUser));
		}catch(Exception e){
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
		}
		return serviceResult;
	}

	@Override
	public ServiceResult<Boolean> handleJDOrderMessage(AccessToken token, Map<Integer, List<JDMessage>> messageList) {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
		try{
			serviceResult.setResult(ordersModel.handleJDOrderMessage(token,messageList));
			serviceResult.setSuccess(true);
		}catch (BusinessException be){
			serviceResult.setMessage(be.getMessage());
			serviceResult.setSuccess(false);;
		}catch (Exception e){
			e.printStackTrace();
			serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
			log.error("OrdersServiceImpl handleJDOrderMessage exception:", e);
		}
		return serviceResult;
	}


}