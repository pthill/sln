package com.sln.core.jd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 京东服务单分页实体
 * @author hlq
 *
 */
public class AfsServicebyCustomerPinPage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AfsServicebyCustomerPin> serviceInfoList;  //服务单列表 无记录返回为 空
	private int totalNum;                                  //总记录数
	private int pageSize;                                  //每页记录数
	private int pageNum;                                   //总页数
	private int pageIndex;                                 //当前页数
	public List<AfsServicebyCustomerPin> getServiceInfoList() {
		return serviceInfoList;
	}
	public void setServiceInfoList(List<AfsServicebyCustomerPin> serviceInfoList) {
		this.serviceInfoList = serviceInfoList;
	}
	public int getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	
}
