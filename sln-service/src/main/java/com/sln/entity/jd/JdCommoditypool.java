package com.sln.entity.jd;

import java.io.Serializable;
/**
 * 京东Access Token表
 * <p>Table: <strong>jd_commoditypool</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>池子名称</td></tr>
 *   <tr><td>pageNum</td><td>{@link java.lang.Integer}</td><td>page_num</td><td>int</td><td>池子编号</td></tr>
 * </table>
 *
 */
public class JdCommoditypool implements Serializable {
 
	/**
	 * 新增
	 */
	public static final int SKUSTATE_1 =1;
	
	/**
	 * 已导入详情
	 */
	public static final int SKUSTATE_2 =2;
	
	/**
	 * 已导入价格
	 */
	public static final int SKUSTATE_3 =3;
	
	/**
	 * 已导入上下架状态
	 */
	public static final int SKUSTATE_4 =4;
	
 	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;
 	private java.lang.String name;
 	private java.lang.Integer pageNum;
 	
 		
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
     * 获取池子名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置池子名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取池子编号
     */
	public java.lang.Integer getPageNum(){
		return this.pageNum;
	}
 		
	/**
     * 设置池子编号
     */
	public void setPageNum(java.lang.Integer pageNum){
		this.pageNum = pageNum;
	}
 }