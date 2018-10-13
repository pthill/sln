package com.sln.core.pay.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 一卡通支付订单信息实体类
 *                       
 * @Filename: Order.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            tranamt;              //交易金额(分)
    private String            actulamt;             //实际交易金额(分)
    private String            state;                //交易状态( 1 成功 0待支付 2取消)
    private String            orderid;              //支付平台订单号，支付平台支付成功以后的订单号 
    private String            account;              //员工账号 
    private String            sno;                  //员工工号
    private String            toaccount;            //商户账号
    private String            thirdsystem;          //系统注册ID，由支付平台分配给接入方的唯一一个ID
    private String            thirdorderid;         //第三方订单号，支付平台接入方生成唯一编号
    private String            ordertype;            //接入类型，如果是pc端则传入pc；如果是手机端则传入 phone
    private String            sign;                 //签名
    private String            orderdesc;            //商品名称
    private String            praram1;              //第三方系统备注字段，订单查询接口，对账原样返回
    private Timestamp         rzdate;               //支付平台入账时间，24小时制
    private Timestamp         jydate;               //交易日期 (支付平台与其他支付系统实际支付完成的时间)，24小时制

    private String            payname;              //支付方式名称
    private String            name;                 //支付人姓名

    private String            thirdurl;             //统一下单，第三方页面跳转 URL，第三方页面跳转的地址 如果为空，则跳转系统注册支付平台默认的 URL
    private String            querystate;           //单笔订单信息查询状态，all代表查询所有的支付 记录包括成功的和失败 的 success只包括支付成功的记录
    private String            opertype;             //对账交易类型，101消费流水 301退费流水

    private String            outrefundorderid;     //商户退款的订单号码
    private String            refundstate;          //退款状态( 1成功 2失败)
    private String            reforderid;           //支付平台退款订单号
    private String            prompt;               //提示信息
    private String            promptcode;           //提示代码
    private String            productBackSn;        //退款申请单号
    
    public Order(){
        
    }

    public String getTranamt() {
        return tranamt;
    }

    public void setTranamt(String tranamt) {
        this.tranamt = tranamt;
    }

    public String getActulamt() {
        return actulamt;
    }

    public void setActulamt(String actulamt) {
        this.actulamt = actulamt;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getToaccount() {
        return toaccount;
    }

    public void setToaccount(String toaccount) {
        this.toaccount = toaccount;
    }

    public String getThirdsystem() {
        return thirdsystem;
    }

    public void setThirdsystem(String thirdsystem) {
        this.thirdsystem = thirdsystem;
    }

    public String getThirdorderid() {
        return thirdorderid;
    }

    public void setThirdorderid(String thirdorderid) {
        this.thirdorderid = thirdorderid;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getOrderdesc() {
        return orderdesc;
    }

    public void setOrderdesc(String orderdesc) {
        this.orderdesc = orderdesc;
    }

    public String getPraram1() {
        return praram1;
    }

    public void setPraram1(String praram1) {
        this.praram1 = praram1;
    }

    public Timestamp getRzdate() {
        return rzdate;
    }

    public void setRzdate(Timestamp rzdate) {
        this.rzdate = rzdate;
    }

    public Timestamp getJydate() {
        return jydate;
    }

    public void setJydate(Timestamp jydate) {
        this.jydate = jydate;
    }

    public String getPayname() {
        return payname;
    }

    public void setPayname(String payname) {
        this.payname = payname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getThirdurl() {
        return thirdurl;
    }

    public void setThirdurl(String thirdurl) {
        this.thirdurl = thirdurl;
    }

    public String getQuerystate() {
        return querystate;
    }

    public void setQuerystate(String querystate) {
        this.querystate = querystate;
    }

    public String getOpertype() {
        return opertype;
    }

    public void setOpertype(String opertype) {
        this.opertype = opertype;
    }

    public String getOutrefundorderid() {
        return outrefundorderid;
    }

    public void setOutrefundorderid(String outrefundorderid) {
        this.outrefundorderid = outrefundorderid;
    }

    public String getRefundstate() {
        return refundstate;
    }

    public void setRefundstate(String refundstate) {
        this.refundstate = refundstate;
    }

    public String getReforderid() {
        return reforderid;
    }

    public void setReforderid(String reforderid) {
        this.reforderid = reforderid;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPromptcode() {
        return promptcode;
    }

    public void setPromptcode(String promptcode) {
        this.promptcode = promptcode;
    }

    public String getProductBackSn() {
        return productBackSn;
    }

    public void setProductBackSn(String productBackSn) {
        this.productBackSn = productBackSn;
    }
}
