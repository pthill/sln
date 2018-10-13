package com.sln.entity.message;

import java.io.Serializable;
/**
 * 消息记录表
 * <p>Table: <strong>message</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>title</td><td>{@link java.lang.String}</td><td>title</td><td>varchar</td><td>消息标题</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>消息内容</td></tr>
 *   <tr><td>message_code</td><td>{@link java.lang.String}</td><td>messageCode</td><td>varchar</td><td>唯一标识</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>消息发送状态 0:未发送 1:已发送</td></tr>
 *   <tr><td>messageTypeId</td><td>{@link java.lang.Integer}</td><td>message_type_id</td><td>int</td><td>消息类型ID</td></tr>
 *   <tr><td>sendId</td><td>{@link java.lang.Integer}</td><td>send_id</td><td>int</td><td>发送人id</td></tr>
 *   <tr><td>sendType</td><td>{@link java.lang.Integer}</td><td>send_type</td><td>int</td><td>发送人类型 0:平台 1:商户 2:供应商</td></tr>
 *   <tr><td>is_message_template</td><td>{@link java.lang.Integer}</td><td>isMessageTemplate</td><td>int</td><td>是否是消息模板 0:是  1:否</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class Message implements Serializable {
 
	/**
     * Comment for <code>serialVersionUID</code>
     */
	private static final long serialVersionUID = -6381583232954999808L;
	
	/**
     * 发送人类型   类型0 平台
     */
	public final static int SEND_TYPE0 = 0;
	/**
     * 发送人类型   类型1 商户
     */
	public final static int SEND_TYPE1 = 1;
	/**
     * 发送人类型   类型2 供应商
     */
	public final static int SEND_TYPE2 = 2;
	/**
     * 发送状态0 未发送
     */
	public final static int STATE0  = 0; 
	/**
     * 发送状态1 已发送
     */
	public final static int STATE1  = 1; 
	/**
     * 是否是消息模板 0:是
     */
	public final static int IS_MESSAGE_TEMPLATE0 = 0;
	/**
     * 是否是消息模板 1:否
     */
	public final static int IS_MESSAGE_TEMPLATE1 = 1;
	
	/**
	 * 消息标识符  订单提交通知
	 */
	public final static String DDTJTZ = "DDTJTZ";
	/**
	 * 消息标识符  订单发货通知
	 */
	public final static String DDFHTZ = "DDFHTZ";
	/**
	 * 消息标识符  订单取消通知
	 */
	public final static String DDQXTZ = "DDQXTZ";
	/**
	 * 消息标识符  订单签收通知
	 */
	public final static String DDQSTZ = "DDQSTZ";
	/**
	 * 消息标识符  退款申请通知
	 */
	public final static String TKSQTZ = "TKSQTZ";
	/**
	 * 消息标识符  退款成功通知
	 */
	public final static String TKCGTZ = "TKCGTZ";
	/**
	 * 消息标识符  退款失败通知
	 */
	public final static String TKSBTZ = "TKSBTZ";
	/**
	 * 消息标识符  换货申请通知
	 */
	public final static String HHSQTZ = "HHSQTZ";
	/**
	 * 消息标识符  换货成功通知
	 */
	public final static String HHCGTZ = "HHCGTZ";
	/**
	 * 消息标识符  换货失败通知
	 */
	public final static String HHSBTZ = "HHSBTZ";
	
	/**
	 * 消息标识符  缺货商品审核通过
	 */
	public final static String QHSPSHTG = "QHSPSHTG";
	/**
	 * 消息标识符  缺货商品审核失败
	 */
	public final static String QHSPSHSB = "QHSPSHSB";
	/**
	 * 消息标识符  商家淘汰提示信息
	 */
	public final static String SJTTTS_1 = "SJTTTS_1";
	/**
	 * 消息标识符  商家淘汰警告信息
	 */
	public final static String SJTTJG_1 = "SJTTJG_1";
	/**
	 * 消息标识符  商家淘汰信息
	 */
	public final static String SJTTTT_1 = "SJTTTT_1";
	
	/*
	* 消息标识符 福利积分发放
	* */
	public final static String FLJFFF="FLJFFF";

	
	private java.lang.Integer id;
 	private java.lang.String title;
 	private java.lang.String content;
 	private java.lang.String messageCode;
 	private java.lang.Integer state;
 	private java.lang.Integer messageTypeId;
 	private java.lang.Integer sendId;
 	private java.lang.Integer sendType;
 	private java.util.Date createTime;
 	private java.lang.Integer isMessageTemplate;
 	
 	/** 非数据库字段  **/
 	private String typeName;		//类型名称
 	
 	private String receptionType;	//接收对象 0:所有人 1:用户 2:商户 3:供应商
 	
 	private String createName;		//创建人名称
 		
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
     * 获取消息标题
     */
	public java.lang.String getTitle(){
		return this.title;
	}
 		
	/**
     * 设置消息标题
     */
	public void setTitle(java.lang.String title){
		this.title = title;
	}
 		
	/**
     * 获取消息内容
     */
	public java.lang.String getContent(){
		return this.content;
	}
 		
	/**
     * 设置消息内容
     */
	public void setContent(java.lang.String content){
		this.content = content;
	}
 	
	/**
     * 获取唯一标识
     */
	public java.lang.String getMessageCode() {
		return messageCode;
	}
	
	/**
     * 设置唯一标识
     */
	public void setMessageCode(java.lang.String messageCode) {
		this.messageCode = messageCode;
	}

	/**
     * 获取消息发送状态 0:未发送 1:已发送
     */
	public java.lang.Integer getState(){
		return this.state;
	}
 		
	/**
     * 设置消息发送状态 0:未发送 1:已发送
     */
	public void setState(java.lang.Integer state){
		this.state = state;
	}
 		
	/**
     * 获取消息类型id
     */
	public java.lang.Integer getMessageTypeId(){
		return this.messageTypeId;
	}
 		
	/**
     * 设置消息类型id
     */
	public void setMessageTypeId(java.lang.Integer messageTypeId){
		this.messageTypeId = messageTypeId;
	}
 		
	/**
     * 获取发送人id
     */
	public java.lang.Integer getSendId(){
		return this.sendId;
	}
 		
	/**
     * 设置发送人id
     */
	public void setSendId(java.lang.Integer sendId){
		this.sendId = sendId;
	}
 		
	/**
     * 获取发送人类型 0:平台 1:商户 2:供应商
     */
	public java.lang.Integer getSendType(){
		return this.sendType;
	}
 		
	/**
     * 设置发送人类型 0:平台 1:商户 2:供应商
     */
	public void setSendType(java.lang.Integer sendType){
		this.sendType = sendType;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getReceptionType() {
		return receptionType;
	}

	public void setReceptionType(String receptionType) {
		this.receptionType = receptionType;
	}
	
	public java.lang.Integer getIsMessageTemplate() {
		return isMessageTemplate;
	}

	public void setIsMessageTemplate(java.lang.Integer isMessageTemplate) {
		this.isMessageTemplate = isMessageTemplate;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}
	
 }