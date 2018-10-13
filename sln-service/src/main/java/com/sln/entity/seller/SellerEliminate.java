package com.sln.entity.seller;

import java.io.Serializable;
import java.util.List;
/**
 * 淘汰机制表
 * <p>Table: <strong>seller_eliminate</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>kindType</td><td>{@link java.lang.Integer}</td><td>kind_type</td><td>int</td><td>淘汰因素 1-完成订单量，2-综合评分，3-投诉，4-举报数量</td></tr>
 *   <tr><td>tipValue</td><td>{@link java.lang.Integer}</td><td>tip_value</td><td>int</td><td>提示值</td></tr>
 *   <tr><td>warnValue</td><td>{@link java.lang.Integer}</td><td>warn_value</td><td>int</td><td>警告值</td></tr>
 *   <tr><td>eliminateValue</td><td>{@link java.lang.Integer}</td><td>eliminate_value</td><td>int</td><td>淘汰值</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 * </table>
 *
 */
public class SellerEliminate implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer kindType;
 	private java.lang.Integer tipValue;
 	private java.lang.Integer warnValue;
 	private java.lang.Integer eliminateValue;
 	private java.util.Date createTime;
 	private java.util.Date updateTime;
 	
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
     * 获取淘汰因素 1-完成订单量，2-综合评分，3-投诉，4-举报数量
     */
	public java.lang.Integer getKindType(){
		return this.kindType;
	}
 		
	/**
     * 设置淘汰因素 1-完成订单量，2-综合评分，3-投诉，4-举报数量
     */
	public void setKindType(java.lang.Integer kindType){
		this.kindType = kindType;
	}
 		
	/**
     * 获取提示值
     */
	public java.lang.Integer getTipValue(){
		return this.tipValue;
	}
 		
	/**
     * 设置提示值
     */
	public void setTipValue(java.lang.Integer tipValue){
		this.tipValue = tipValue;
	}
 		
	/**
     * 获取警告值
     */
	public java.lang.Integer getWarnValue(){
		return this.warnValue;
	}
 		
	/**
     * 设置警告值
     */
	public void setWarnValue(java.lang.Integer warnValue){
		this.warnValue = warnValue;
	}
 		
	/**
     * 获取淘汰值
     */
	public java.lang.Integer getEliminateValue(){
		return this.eliminateValue;
	}
 		
	/**
     * 设置淘汰值
     */
	public void setEliminateValue(java.lang.Integer eliminateValue){
		this.eliminateValue = eliminateValue;
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
     * 获取更新时间
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置更新时间
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 }