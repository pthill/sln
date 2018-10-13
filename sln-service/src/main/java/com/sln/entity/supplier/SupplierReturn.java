package com.sln.entity.supplier;

import java.io.Serializable;
/**
 * 供应商退货单
 * <p>Table: <strong>supplier_return</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>returnSn</td><td>{@link java.lang.String}</td><td>return_sn</td><td>varchar</td><td>退货单编号</td></tr>
 *   <tr><td>orderSn</td><td>{@link java.lang.String}</td><td>order_sn</td><td>varchar</td><td>订单编号</td></tr>
 *   <tr><td>sellerId</td><td>{@link java.lang.Integer}</td><td>seller_id</td><td>int</td><td>商家id</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>收货时间</td></tr>
 *   <tr><td>returnState</td><td>{@link java.lang.Integer}</td><td>return_state</td><td>int</td><td>退货状态</td></tr>
 *   <tr><td>supplierId</td><td>{@link java.lang.Integer}</td><td>supplier_id</td><td>int</td><td>供应商ID</td></tr>
 *    *   <tr><td>backId</td><td>{@link java.lang.Integer}</td><td>back_id</td><td>int</td><td>退货申请单id</td></tr>
 * </table>
 *
 */
/**
 * @author hlq
 *
 */
/**
 * @author hlq
 *
 */
public class SupplierReturn implements Serializable {
	
	/** 订单表  supplier_exchange  换货单状态：1、未收货；*/
    public final static int      RETURN_STATE_1           = 1;
    
    /** 订单表  supplier_exchange  换货单状态：1、未收货；*/
    public final static int      RETURN_STATE_2           = 2;
 
 	/**
	 * 
	 */
	private static final long serialVersionUID = 5053845127302890582L;
	private java.lang.Integer id;
 	private java.lang.String returnSn;
 	private java.lang.String orderSn;
 	private java.lang.Integer sellerId;
 	private Integer memberId;;
 	private String memberName;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	private java.lang.Integer returnState;
 	private java.lang.Integer supplierId;
 	private Integer backId;    
 	
 	/*  外部属性*/
 	private Integer supplierType;    //供应商类型  0:商家供应 1:供应商供应
 	private String  productName;     //商品名称
	private Integer number;          //退货数量
 	/*  外部属性*/
 		
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
     * 获取退货单编号
     */
	public java.lang.String getReturnSn(){
		return this.returnSn;
	}
 		
	/**
     * 设置退货单编号
     */
	public void setReturnSn(java.lang.String returnSn){
		this.returnSn = returnSn;
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
     * 获取商家id
     */
	public java.lang.Integer getSellerId(){
		return this.sellerId;
	}
 		
	/**
     * 设置商家id
     */
	public void setSellerId(java.lang.Integer sellerId){
		this.sellerId = sellerId;
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
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置收货时间
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 		
	/**
     * 获取退货状态
     */
	public java.lang.Integer getReturnState(){
		return this.returnState;
	}
 		
	/**
     * 设置退货状态
     */
	public void setReturnState(java.lang.Integer returnState){
		this.returnState = returnState;
	}
 		
	/**
     * 获取供应商ID
     */
	public java.lang.Integer getSupplierId(){
		return this.supplierId;
	}
 		
	/**
     * 设置供应商ID
     */
	public void setSupplierId(java.lang.Integer supplierId){
		this.supplierId = supplierId;
	}

	public Integer getBackId() {
		return backId;
	}

	public void setBackId(Integer backId) {
		this.backId = backId;
	}

	public Integer getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(Integer supplierType) {
		this.supplierType = supplierType;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	
	
 }