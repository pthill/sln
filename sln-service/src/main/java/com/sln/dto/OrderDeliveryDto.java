package com.sln.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询订单发货返回用
 * @author hlq
 *
 */
public class OrderDeliveryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3898457530286283194L;
	private String orderSn;    //订单编号
	private Integer supplierId; //供应商id
	private Integer sellerId;   //商家Id
	private String Name;  //收货人姓名
	private String mobile;      //收货人移动电话
	private String address;     //收货地址
	private Integer invoiceStatus;  //发票状态
	private String invoiceTitle;    //发票信息
	private Date orderTime;         //订单时间
	public String getOrderSn() {
		return orderSn;
	}
	public void setOrderSn(String orderSn) {
		this.orderSn = orderSn;
	}
	public Integer getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(Integer supplierId) {
		this.supplierId = supplierId;
	}
	public Integer getSellerId() {
		return sellerId;
	}
	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getInvoiceStatus() {
		return invoiceStatus;
	}
	public void setInvoiceStatus(Integer invoiceStatus) {
		this.invoiceStatus = invoiceStatus;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
