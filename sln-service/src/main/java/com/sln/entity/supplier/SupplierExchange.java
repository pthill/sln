package com.sln.entity.supplier;

import java.io.Serializable;
/**
 * 供应商换货单
 * <p>Table: <strong>supplier_exchange</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>exchangeSn</td><td>{@link java.lang.String}</td><td>exchange_sn</td><td>varchar</td><td>换货单号</td></tr>
 *   <tr><td>orderSn</td><td>{@link java.lang.String}</td><td>order_sn</td><td>varchar</td><td>订单编号</td></tr>
 *   <tr><td>productName</td><td>{@link java.lang.String}</td><td>product_name</td><td>varchar</td><td>商品名称</td></tr>
 *   <tr><td>productId</td><td>{@link java.lang.Integer}</td><td>product_id</td><td>int</td><td>商品ID</td></tr>
 *   <tr><td>memberName</td><td>{@link java.lang.String}</td><td>member_name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>remark</td><td>{@link java.lang.String}</td><td>remark</td><td>varchar</td><td>问题描述</td></tr>
 *   <tr><td>exchangeNumber</td><td>{@link java.lang.Integer}</td><td>exchange_number</td><td>int</td><td>换货数量</td></tr>
 *   <tr><td>exchangeState</td><td>{@link java.lang.Integer}</td><td>exchange_state</td><td>int</td><td>换货状态</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>receiptTime</td><td>{@link java.util.Date}</td><td>receipt_time</td><td>datetime</td><td>收货时间</td></tr>
 *   <tr><td>deliverTime</td><td>{@link java.util.Date}</td><td>deliver_time</td><td>datetime</td><td>发货时间</td></tr>
 *   <tr><td>returnTime</td><td>{@link java.util.Date}</td><td>return_time</td><td>datetime</td><td>退还时间</td></tr>
 *   <tr><td>exchangeId</td><td>{@link java.util.Date}</td><td>exchange_id</td><td>int</td><td>退货单申请id</td></tr>
  *   <tr><td>supplierId</td><td>{@link java.util.Date}</td><td>supplier_id</td><td>int</td><td>供应商ID</td></tr>
 * 
 * </table>
 *
 */
/**
 * @author hlq
 *
 */
public class SupplierExchange implements Serializable {
 
	
	/** 订单表  supplier_exchange  换货单状态：1、未收货；*/
    public final static int      EXCHANGE_STATE_1           = 1;
    
    /** 订单表  supplier_exchange  换货单状态：1、已收货；*/
    public final static int      EXCHANGE_STATE_2           = 2;
    
    /** 订单表  supplier_exchange  换货单状态：1、已发货；*/
    public final static int      EXCHANGE_STATE_3           = 3;
    
    /** 订单表  supplier_exchange  换货单状态：1、不予处理原件退还；*/
    public final static int      EXCHANGE_STATE_4           = 4;
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 2616705383144164679L;
	private java.lang.Integer id;
 	private java.lang.String exchangeSn;
 	private java.lang.String orderSn;
 	private java.lang.String productName;
 	private java.lang.Integer productId;
 	private java.lang.String memberName;
 	private java.lang.Integer memberId;
 	private java.lang.String remark;
 	private java.lang.Integer exchangeNumber;
 	private java.lang.Integer exchangeState;
 	private java.util.Date createTime;
 	private java.util.Date receiptTime;
 	private java.util.Date deliverTime;
 	private java.util.Date returnTime;
 	private java.lang.Integer exchangeId;  
 	private java.lang.Integer supplierId;
 	
 		
	/**
     * 获取id
     */
	public java.lang.Integer getId(){
		return this.id;
	}
 		
	/**
     * 设置id
     */
	public void setId(java.lang.Integer id){
		this.id = id;
	}
 		
	/**
     * 获取换货单号
     */
	public java.lang.String getExchangeSn(){
		return this.exchangeSn;
	}
 		
	/**
     * 设置换货单号
     */
	public void setExchangeSn(java.lang.String exchangeSn){
		this.exchangeSn = exchangeSn;
	}
 		
	/**
     * 获取订单编号
     */
	public java.lang.String getOrderSn(){
		return this.orderSn;
	}
 		
	/**
     * 设置订单编号
     */
	public void setOrderSn(java.lang.String orderSn){
		this.orderSn = orderSn;
	}
 		
	/**
     * 获取商品名称
     */
	public java.lang.String getProductName(){
		return this.productName;
	}
 		
	/**
     * 设置商品名称
     */
	public void setProductName(java.lang.String productName){
		this.productName = productName;
	}
 		
	/**
     * 获取商品ID
     */
	public java.lang.Integer getProductId(){
		return this.productId;
	}
 		
	/**
     * 设置商品ID
     */
	public void setProductId(java.lang.Integer productId){
		this.productId = productId;
	}
 		
	/**
     * 获取用户名
     */
	public java.lang.String getMemberName(){
		return this.memberName;
	}
 		
	/**
     * 设置用户名
     */
	public void setMemberName(java.lang.String memberName){
		this.memberName = memberName;
	}
 		
	/**
     * 获取用户ID
     */
	public java.lang.Integer getMemberId(){
		return this.memberId;
	}
 		
	/**
     * 设置用户ID
     */
	public void setMemberId(java.lang.Integer memberId){
		this.memberId = memberId;
	}
 		
	/**
     * 获取问题描述
     */
	public java.lang.String getRemark(){
		return this.remark;
	}
 		
	/**
     * 设置问题描述
     */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
 		
	/**
     * 获取换货数量
     */
	public java.lang.Integer getExchangeNumber(){
		return this.exchangeNumber;
	}
 		
	/**
     * 设置换货数量
     */
	public void setExchangeNumber(java.lang.Integer exchangeNumber){
		this.exchangeNumber = exchangeNumber;
	}
 		
	/**
     * 获取换货状态
     */
	public java.lang.Integer getExchangeState(){
		return this.exchangeState;
	}
 		
	/**
     * 设置换货状态
     */
	public void setExchangeState(java.lang.Integer exchangeState){
		this.exchangeState = exchangeState;
	}
 		
	/**
     * 获取创建时间
     */
	public java.util.Date getCreateTime(){
		return this.createTime;
	}
 		
	/**
     * 设置创建时间
     */
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
 		
	/**
     * 获取收货时间
     */
	public java.util.Date getReceiptTime(){
		return this.receiptTime;
	}
 		
	/**
     * 设置收货时间
     */
	public void setReceiptTime(java.util.Date receiptTime){
		this.receiptTime = receiptTime;
	}
 		
	/**
     * 获取发货时间
     */
	public java.util.Date getDeliverTime(){
		return this.deliverTime;
	}
 		
	/**
     * 设置发货时间
     */
	public void setDeliverTime(java.util.Date deliverTime){
		this.deliverTime = deliverTime;
	}
 		
	/**
     * 获取退还时间
     */
	public java.util.Date getReturnTime(){
		return this.returnTime;
	}
 		
	/**
     * 设置退还时间
     */
	public void setReturnTime(java.util.Date returnTime){
		this.returnTime = returnTime;
	}

	public java.lang.Integer getExchangeId() {
		return exchangeId;
	}

	public void setExchangeId(java.lang.Integer exchangeId) {
		this.exchangeId = exchangeId;
	}

	public java.lang.Integer getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(java.lang.Integer supplierId) {
		this.supplierId = supplierId;
	}
 }