package com.sln.core.jd.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 退款明细
 * @author hlq
 *
 */
public class ServiceFinanceDetailInfoDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int refundWay;  //退款方式
	private String refundWayName; //退款方式名称
	private int status;    //状态
	private String statusName; //状态名称
	private BigDecimal refundPrice; //退款金额
	private String wareName;   //商品名称
	private Integer wareId; //商品编号
	public int getRefundWay() {
		return refundWay;
	}
	public void setRefundWay(int refundWay) {
		this.refundWay = refundWay;
	}
	public String getRefundWayName() {
		return refundWayName;
	}
	public void setRefundWayName(String refundWayName) {
		this.refundWayName = refundWayName;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public BigDecimal getRefundPrice() {
		return refundPrice;
	}
	public void setRefundPrice(BigDecimal refundPrice) {
		this.refundPrice = refundPrice;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public Integer getWareId() {
		return wareId;
	}
	public void setWareId(Integer wareId) {
		this.wareId = wareId;
	}
	
	

}
