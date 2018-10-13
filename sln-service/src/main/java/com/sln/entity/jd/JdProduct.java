package com.sln.entity.jd;

import java.io.Serializable;
/**
 * 京东商品表
 * <p>Table: <strong>jd_product</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>sku</td><td>{@link java.lang.String}</td><td>sku</td><td>varchar</td><td>sku</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>price</td><td>{@link java.math.BigDecimal}</td><td>price</td><td>decimal</td><td>协议价格</td></tr>
 *   <tr><td>jdprice</td><td>{@link java.math.BigDecimal}</td><td>jdprice</td><td>decimal</td><td>京东价格</td></tr>
 *   <tr><td>param</td><td>{@link java.lang.String}</td><td>param</td><td>varchar</td><td>规格参数</td></tr>
 *   <tr><td>introduction</td><td>{@link java.lang.String}</td><td>introduction</td><td>varchar</td><td>详细介绍</td></tr>
 *   <tr><td>imagePath</td><td>{@link java.lang.String}</td><td>imagePath</td><td>varchar</td><td>主图地址</td></tr>
 *   <tr><td>skuState</td><td>{@link java.lang.Integer}</td><td>skuState</td><td>int</td><td>上下架状态</td></tr>
 *   <tr><td>updateState</td><td>{@link java.lang.Integer}</td><td>updateState</td><td>int</td><td>修改状态 1:新增 2：更新 3删除 4同步</td></tr>
 * </table>
 *
 */
public class JdProduct implements Serializable {
 
	/**
	 * 新增
	 */
	public static final int UPDATESTATE_1 = 1;
	
	/**
	 * 更新
	 */
	public static final int UPDATESTATE_2 = 2;
	
	/**
	 * 删除
	 */
	public static final int UPDATESTATE_3 = 3;
	
	/**
	 * 同步
	 */
	public static final int UPDATESTATE_4 = 4;
	
	/**
	 * 上架
	 */
	public static final int SKUSTATE_1 =1;
	
	/**
	 * 下架
	 */
	public static final int SKUSTATE_0 = 0;
			
	
 	private java.lang.Integer id;
 	private java.lang.String sku;
 	private java.lang.String name;
 	private java.math.BigDecimal price;
 	private java.math.BigDecimal jdprice;
 	private java.lang.String param;
 	private java.lang.String introduction;
 	private java.lang.String imagePath;
 	private java.lang.Integer skuState;
 	private java.lang.Integer updateState;
 	private Integer catId; 
 	
 		
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
     * 获取sku
     */
	public java.lang.String getSku(){
		return this.sku;
	}
 		
	/**
     * 设置sku
     */
	public void setSku(java.lang.String sku){
		this.sku = sku;
	}
 		
	/**
     * 获取名称
     */
	public java.lang.String getName(){
		return this.name;
	}
 		
	/**
     * 设置名称
     */
	public void setName(java.lang.String name){
		this.name = name;
	}
 		
	/**
     * 获取协议价格
     */
	public java.math.BigDecimal getPrice(){
		return this.price;
	}
 		
	/**
     * 设置协议价格
     */
	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}
 		
	/**
     * 获取京东价格
     */
	public java.math.BigDecimal getJdprice(){
		return this.jdprice;
	}
 		
	/**
     * 设置京东价格
     */
	public void setJdprice(java.math.BigDecimal jdprice){
		this.jdprice = jdprice;
	}
 		
	/**
     * 获取规格参数
     */
	public java.lang.String getParam(){
		return this.param;
	}
 		
	/**
     * 设置规格参数
     */
	public void setParam(java.lang.String param){
		this.param = param;
	}
 		
	/**
     * 获取详细介绍
     */
	public java.lang.String getIntroduction(){
		return this.introduction;
	}
 		
	/**
     * 设置详细介绍
     */
	public void setIntroduction(java.lang.String introduction){
		this.introduction = introduction;
	}
 		
	/**
     * 获取主图地址
     */
	public java.lang.String getImagePath(){
		return this.imagePath;
	}
 		
	/**
     * 设置主图地址
     */
	public void setImagePath(java.lang.String imagePath){
		this.imagePath = imagePath;
	}
 		
	/**
     * 获取上下架状态
     */
	public java.lang.Integer getSkuState(){
		return this.skuState;
	}
 		
	/**
     * 设置上下架状态
     */
	public void setSkuState(java.lang.Integer skuState){
		this.skuState = skuState;
	}
 		
	/**
     * 获取修改状态 1:新增 2：更新 3删除 4同步
     */
	public java.lang.Integer getUpdateState(){
		return this.updateState;
	}
 		
	/**
     * 设置修改状态 1:新增 2：更新 3删除 4同步
     */
	public void setUpdateState(java.lang.Integer updateState){
		this.updateState = updateState;
	}

	public Integer getCatId() {
		return catId;
	}

	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	
 }