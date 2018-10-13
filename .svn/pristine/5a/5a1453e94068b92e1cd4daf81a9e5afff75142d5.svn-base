package com.sln.entity.jd;

import java.io.Serializable;
/**
 * 京东分类信息表
 * <p>Table: <strong>jd_category</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>catId</td><td>{@link java.lang.Integer}</td><td>catId</td><td>int</td><td>分类ID</td></tr>
 *   <tr><td>parentId</td><td>{@link java.lang.Integer}</td><td>parentId</td><td>int</td><td>父类ID</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>分类名称</td></tr>
 *   <tr><td>catClass</td><td>{@link java.lang.Integer}</td><td>catClass</td><td>int</td><td>0：一级分类；1：二级分类；2：三级分类</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.Integer}</td><td>state</td><td>int</td><td>1：有效；0：无效</td></tr>
 * </table>
 *
 */
public class JdCategory implements Serializable {
 
 	/**
	 *Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 4254118714443985158L;
	/**
	 * 一级分类
	 */
	public static final int CATCLASS_0 =0;
	/**
	 * 二级分类
	 */
	public static final int CATCLASS_1 =1;
	/**
	 * 三级分类
	 */
	public static final int CATCLASS_2 =2;
	
 	private java.lang.Integer id;
 	private java.lang.Integer catId;
 	private java.lang.Integer parentId;
 	private java.lang.String name;
 	private java.lang.Integer catClass;
 	private java.lang.Integer state;
 	
 	// --------额外属性（entity对应表结构之外的属性） start------------------------------
 	private JdCategory	  parent;	    // 父级分类
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------
    
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
     * 获取分类ID
     */
	public java.lang.Integer getCatId(){
		return this.catId;
	}
 		
	/**
     * 设置分类ID
     */
	public void setCatId(java.lang.Integer catId){
		this.catId = catId;
	}
 		
	/**
     * 获取父类ID
     */
	public java.lang.Integer getParentId(){
		return this.parentId;
	}
 		
	/**
     * 设置父类ID
     */
	public void setParentId(java.lang.Integer parentId){
		this.parentId = parentId;
	}
 		
	/**
     * 获取分类名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置分类名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取0：一级分类；1：二级分类；2：三级分类
     */
	public java.lang.Integer getCatClass(){
		return this.catClass;
	}
 		
	/**
     * 设置0：一级分类；1：二级分类；2：三级分类
     */
	public void setCatClass(java.lang.Integer catClass){
		this.catClass = catClass;
	}
 		
	/**
     * 获取1：有效；0：无效
     */
	public java.lang.Integer getState(){
		return this.state;
	}
 		
	/**
     * 设置1：有效；0：无效
     */
	public void setState(java.lang.Integer state){
		this.state = state;
	}

	public JdCategory getParent() {
		return parent;
	}

	public void setParent(JdCategory parent) {
		this.parent = parent;
	}
 }