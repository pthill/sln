package com.sln.entity.message;

import java.io.Serializable;
/**
 * 消息发送记录表
 * <p>Table: <strong>message_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>messageId</td><td>{@link java.lang.Integer}</td><td>message_id</td><td>int</td><td>消息id</td></tr>
 *   <tr><td>receptionId</td><td>{@link java.lang.Integer}</td><td>reception_id</td><td>int</td><td>接收人id</td></tr>
 *   <tr><td>content</td><td>{@link java.lang.String}</td><td>content</td><td>text</td><td>消息内容</td></tr>
 *   <tr><td>isRead</td><td>{@link java.lang.Integer}</td><td>is_read</td><td>int</td><td>是否已读  0:未读 1:已读</td></tr>
 *   <tr><td>isDel</td><td>{@link java.lang.Integer}</td><td>is_del</td><td>int</td><td>是否删除  0:正常 1:删除</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>发送时间</td></tr>
 * </table>
 *
 */
public class MessageRecord implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5669427341461534048L;
	
	/**
	 * 是否已读  0：未读
	 */
	public final static int ISREAD0  = 0; 
	/**
	 * 是否已读  1：已读
	 */
	public final static int ISREAD1  = 1; 
	
	private java.lang.Integer 	id;
 	private java.lang.Integer messageId;
 	private java.lang.Integer receptionId;
 	private java.lang.String content;
 	private java.lang.Integer isRead;
 	private java.lang.Integer isDel;
 	private java.util.Date createTime;
 	
 	
 	/**
 	 * 非数据库字段
 	 */
 	private Integer messageTypeId;			//类型id
 	
 	private String typeName;				//类型名称
 	
 	private String title;					//消息标题
 	
 	private Integer receptionType;			//接收人类型
 	
 	private String receptionName;			//接收人名称
 	
 	public MessageRecord() {
		super();
	}

	public MessageRecord(Integer messageId,Integer receptionId) {
 		this.messageId = messageId;
 		this.receptionId = receptionId;
 	}
 	
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
     * 获取消息id
     */
	public java.lang.Integer getMessageId(){
		return this.messageId;
	}
 		
	/**
     * 设置消息id
     */
	public void setMessageId(java.lang.Integer messageId){
		this.messageId = messageId;
	}
 		
	/**
     * 获取接收人id
     */
	public java.lang.Integer getReceptionId(){
		return this.receptionId;
	}
 		
	/**
     * 设置接收人id
     */
	public void setReceptionId(java.lang.Integer receptionId){
		this.receptionId = receptionId;
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
     * 获取是否已读  0:未读 1:已读
     */
	public java.lang.Integer getIsRead(){
		return this.isRead;
	}
 		
	/**
     * 设置是否已读  0:未读 1:已读
     */
	public void setIsRead(java.lang.Integer isRead){
		this.isRead = isRead;
	}

	public java.lang.Integer getIsDel() {
		return isDel;
	}

	public void setIsDel(java.lang.Integer isDel) {
		this.isDel = isDel;
	}

	public java.util.Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public Integer getMessageTypeId() {
		return messageTypeId;
	}

	public void setMessageTypeId(Integer messageTypeId) {
		this.messageTypeId = messageTypeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getReceptionType() {
		return receptionType;
	}

	public void setReceptionType(Integer receptionType) {
		this.receptionType = receptionType;
	}

	public String getReceptionName() {
		return receptionName;
	}

	public void setReceptionName(String receptionName) {
		this.receptionName = receptionName;
	}
	
 }