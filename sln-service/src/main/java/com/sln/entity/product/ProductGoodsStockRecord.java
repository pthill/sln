package com.sln.entity.product;

import java.io.Serializable;
/**
 * 货品库存记录表
 * <p>Table: <strong>product_goods_stock_record</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>productGoodsId</td><td>{@link java.lang.Integer}</td><td>product_goods_id</td><td>int</td><td>货品id</td></tr>
 *   <tr><td>sku</td><td>{@link java.lang.String}</td><td>sku</td><td>varchar</td><td>货品SKU</td></tr>
 *   <tr><td>oldStock</td><td>{@link java.lang.Integer}</td><td>old_stock</td><td>int</td><td>修改前库存</td></tr>
 *   <tr><td>oldStockWarning</td><td>{@link java.lang.Integer}</td><td>old_stock_warning</td><td>int</td><td>修改前预警库存</td></tr>
 *   <tr><td>oldMallPcPrice</td><td>{@link java.math.BigDecimal}</td><td>old_mall_pc_price</td><td>decimal</td><td>修改前商城价</td></tr>
 *   <tr><td>oldMalMobilePrice</td><td>{@link java.math.BigDecimal}</td><td>old_mal_mobile_price</td><td>decimal</td><td>修改前商城价Mobile</td></tr>
 *   <tr><td>newStock</td><td>{@link java.lang.Integer}</td><td>new_stock</td><td>int</td><td>修改后库存</td></tr>
 *   <tr><td>newStockWarning</td><td>{@link java.lang.Integer}</td><td>new_stock_warning</td><td>int</td><td>修改后预警库存</td></tr>
 *   <tr><td>newMallPcPrice</td><td>{@link java.math.BigDecimal}</td><td>new_mall_pc_price</td><td>decimal</td><td>修改后商城价</td></tr>
 *   <tr><td>newMalMobilePrice</td><td>{@link java.math.BigDecimal}</td><td>new_mal_mobile_price</td><td>decimal</td><td>修改后商城价Mobile</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>修改时间</td></tr>
 *   <tr><td>updateUserName</td><td>{@link java.lang.String}</td><td>update_user_name</td><td>varchar</td><td>修改人</td></tr>
 * </table>
 *
 */
public class ProductGoodsStockRecord implements Serializable {
 
 	private java.lang.Integer id;
 	private java.lang.Integer productGoodsId;
 	private java.lang.String sku;
 	private java.lang.Integer oldStock;
 	private java.lang.Integer oldStockWarning;
 	private java.math.BigDecimal oldMallPcPrice;
 	private java.math.BigDecimal oldMalMobilePrice;
 	private java.lang.Integer newStock;
 	private java.lang.Integer newStockWarning;
 	private java.math.BigDecimal newMallPcPrice;
 	private java.math.BigDecimal newMalMobilePrice;
 	private java.util.Date updateTime;
 	private java.lang.String updateUserName;
 	
 	
 	/** 非数据库字段 */
 	
 	
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
     * 获取货品id
     */
	public java.lang.Integer getProductGoodsId(){
		return this.productGoodsId;
	}
 		
	/**
     * 设置货品id
     */
	public void setProductGoodsId(java.lang.Integer productGoodsId){
		this.productGoodsId = productGoodsId;
	}
 		
	/**
     * 获取货品SKU
     */
	public java.lang.String getSku(){
		return this.sku;
	}
 		
	/**
     * 设置货品SKU
     */
	public void setSku(java.lang.String sku){
		this.sku = sku;
	}
 		
	/**
     * 获取修改前库存
     */
	public java.lang.Integer getOldStock(){
		return this.oldStock;
	}
 		
	/**
     * 设置修改前库存
     */
	public void setOldStock(java.lang.Integer oldStock){
		this.oldStock = oldStock;
	}
 		
	/**
     * 获取修改前预警库存
     */
	public java.lang.Integer getOldStockWarning(){
		return this.oldStockWarning;
	}
 		
	/**
     * 设置修改前预警库存
     */
	public void setOldStockWarning(java.lang.Integer oldStockWarning){
		this.oldStockWarning = oldStockWarning;
	}
 		
	/**
     * 获取修改前商城价
     */
	public java.math.BigDecimal getOldMallPcPrice(){
		return this.oldMallPcPrice;
	}
 		
	/**
     * 设置修改前商城价
     */
	public void setOldMallPcPrice(java.math.BigDecimal oldMallPcPrice){
		this.oldMallPcPrice = oldMallPcPrice;
	}
 		
	/**
     * 获取修改前商城价Mobile
     */
	public java.math.BigDecimal getOldMalMobilePrice(){
		return this.oldMalMobilePrice;
	}
 		
	/**
     * 设置修改前商城价Mobile
     */
	public void setOldMalMobilePrice(java.math.BigDecimal oldMalMobilePrice){
		this.oldMalMobilePrice = oldMalMobilePrice;
	}
 		
	/**
     * 获取修改后库存
     */
	public java.lang.Integer getNewStock(){
		return this.newStock;
	}
 		
	/**
     * 设置修改后库存
     */
	public void setNewStock(java.lang.Integer newStock){
		this.newStock = newStock;
	}
 		
	/**
     * 获取修改后预警库存
     */
	public java.lang.Integer getNewStockWarning(){
		return this.newStockWarning;
	}
 		
	/**
     * 设置修改后预警库存
     */
	public void setNewStockWarning(java.lang.Integer newStockWarning){
		this.newStockWarning = newStockWarning;
	}
 		
	/**
     * 获取修改后商城价
     */
	public java.math.BigDecimal getNewMallPcPrice(){
		return this.newMallPcPrice;
	}
 		
	/**
     * 设置修改后商城价
     */
	public void setNewMallPcPrice(java.math.BigDecimal newMallPcPrice){
		this.newMallPcPrice = newMallPcPrice;
	}
 		
	/**
     * 获取修改后商城价Mobile
     */
	public java.math.BigDecimal getNewMalMobilePrice(){
		return this.newMalMobilePrice;
	}
 		
	/**
     * 设置修改后商城价Mobile
     */
	public void setNewMalMobilePrice(java.math.BigDecimal newMalMobilePrice){
		this.newMalMobilePrice = newMalMobilePrice;
	}
 		
	/**
     * 获取修改时间
     */
	public java.util.Date getUpdateTime(){
		return this.updateTime;
	}
 		
	/**
     * 设置修改时间
     */
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
 		
	/**
     * 获取修改人
     */
	public java.lang.String getUpdateUserName(){
		return this.updateUserName;
	}
 		
	/**
     * 设置修改人
     */
	public void setUpdateUserName(java.lang.String updateUserName){
		this.updateUserName = updateUserName;
	}
 }