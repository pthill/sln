package com.sln.vo.order;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.sln.entity.order.Orders;

public class OrderSuccessVO implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -968936602944172666L;

    private List<Orders>      ordersList;                             // 子订单列表
    private String            payForOrderSn;                          // 所有被支付的订单编号（拆分后订单的orderSn连接字符串），用英文逗号（,）连接
    private String            paySn;                                  // 第三方支付订单号
    private BigDecimal        payAmount;                              // 订单总金额  商品总额+总运费-账户支付金额-优惠金额-积分抵用金额
    private String            paymentCode;                            // 支付代码
    private String            paymentName;                            // 支付名称
    private String            paySessionstr;                          // 支付随机码，跳到支付页面时用到，防二次提交
    private Boolean           isPaid;                                 // 是否已支付（主要是在用余额支付的情况，余额足够支付所有订单的金额，下单直接扣除余额付款成功）
    private Boolean           goJumpPayfor;                           // 是否跳转到支付页面

    private BigDecimal        payOrderAllsVO;                         //订单需要支付总金额
    private BigDecimal        banlancePayMoneyVO;                     //使用余额支付的金额
    private BigDecimal		  integralPayMoneyVo;					  //使用积分支付的金额
    private Integer			  specialIntegralPay;					  //专项积分支付的金额
    private int               banlancePayVO;                          //1、未使用余额，2、使用余额支付够付款，3、余额不够付款  4、福利订单 积分够付款  5、福利订单 积分不够付款 余额抵扣 付款  6、福利订单 积分和余额都不够付款
    private String            productName;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * 1、未使用余额
     */
    public static final int BANLANCEPAYVO_1 = 1;

    /**
     * 2、使用余额支付够付款
     */
    public static final int BANLANCEPAYVO_2 = 2;

    /**
     * 3、余额不够付款
     */
    public static final int BANLANCEPAYVO_3 = 3;
    
    /**
     * 4、福利订单 积分够付款
     */
    public static final int BANLANCEPAYVO_4 = 4;
    
    /**
     * 5、福利订单 积分不够付款 余额抵扣 付款
     */
    public static final int BANLANCEPAYVO_5 = 5;
    
    /**
     * 6、福利订单 积分和余额都不够付款
     */
    public static final int BANLANCEPAYVO_6 = 6;
    

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     * 获取第三方支付订单号
     */
    public java.lang.String getPaySn() {
        return this.paySn;
    }

    /**
     * 设置第三方支付订单号
     */
    public void setPaySn(java.lang.String paySn) {
        this.paySn = paySn;
    }

    public String getPayForOrderSn() {
        return payForOrderSn;
    }

    public void setPayForOrderSn(String payForOrderSn) {
        this.payForOrderSn = payForOrderSn;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPaymentCode() {
        return paymentCode;
    }

    public void setPaymentCode(String paymentCode) {
        this.paymentCode = paymentCode;
    }

    public String getPaymentName() {
        return paymentName;
    }

    public void setPaymentName(String paymentName) {
        this.paymentName = paymentName;
    }

    public String getPaySessionstr() {
        return paySessionstr;
    }

    public void setPaySessionstr(String paySessionstr) {
        this.paySessionstr = paySessionstr;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Boolean getGoJumpPayfor() {
        return goJumpPayfor;
    }

    public void setGoJumpPayfor(Boolean goJumpPayfor) {
        this.goJumpPayfor = goJumpPayfor;
    }

    public BigDecimal getPayOrderAllsVO() {
        return payOrderAllsVO;
    }

    public void setPayOrderAllsVO(BigDecimal payOrderAllsVO) {
        this.payOrderAllsVO = payOrderAllsVO;
    }

    public BigDecimal getBanlancePayMoneyVO() {
        return banlancePayMoneyVO;
    }

    public void setBanlancePayMoneyVO(BigDecimal banlancePayMoneyVO) {
        this.banlancePayMoneyVO = banlancePayMoneyVO;
    }

    /**
     * 1、未使用余额，2、使用余额支付够付款，3、余额不够付款
     * @return
     */
    public int getBanlancePayVO() {
        return banlancePayVO;
    }

    /**
     * 1、未使用余额，2、使用余额支付够付款，3、余额不够付款
     * @param banlancePayVO
     */
    public void setBanlancePayVO(int banlancePayVO) {
        this.banlancePayVO = banlancePayVO;
    }

	public BigDecimal getIntegralPayMoneyVo() {
		return integralPayMoneyVo;
	}

	public void setIntegralPayMoneyVo(BigDecimal integralPayMoneyVo) {
		this.integralPayMoneyVo = integralPayMoneyVo;
	}

	public Integer getSpecialIntegralPay() {
		return specialIntegralPay;
	}

	public void setSpecialIntegralPay(Integer specialIntegralPay) {
		this.specialIntegralPay = specialIntegralPay;
	}
    
}
