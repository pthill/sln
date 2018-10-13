package com.sln.core.jd.bean;

import java.io.Serializable;

/**
 * 京东请求售后服务参数实体
 * @author hlq
 *
 */
public class AfterSaleDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 客户预期 退货
	 */
	public static final int CUSTOMER_EXPECT_10 = 10;
	/**
	 * 客户预期 换货
	 */
	public static final int CUSTOMER_EXPECT_20 = 20;
	/**
	 * 客户预期 维修
	 */
	public static final int CUSTOMER_EXPECT_30 = 30;
	
	/**
	 *包装完整状态 无包装
	 */
	public static final int PACKAGE_DESC_0 = 0;

	/**
	 *包装完整状态 包装完整
	 */
	public static final int PACKAGE_DESC_10 = 10;
	
	/**
	 *包装完整状态 包装破损
	 */
	public static final int PACKAGE_DESC_20 = 20;
	private long jdOrderId; 		 //订单号
	private Integer customerExpect; //客户预期 必填，退货(10)、换货(20)、维修(30)
	private String  questionDesc;   //产品问题描述  最多1000字符
	private boolean isNeedDetectionReport; //是否需要检测报告
	private String  questionPic;           //问题描述图片
	private boolean isHasPackage;          //是否有包装
	private Integer packageDesc;           //0 无包装  10 包装完整  20 包装破损
	private AfterSaleCustomerDto asCustomerDto;  //客户信息实体
	private AfterSalePickwareDto asPickwareDto;  //取件信息实体
	private AfterSaleReturnwareDto asReturnwareDto; //返件信息实体
	private AfterSaleDetailDto   asDetailDto; //申请单明细
	public long getJdOrderId() {
		return jdOrderId;
	}
	public void setJdOrderId(long jdOrderId) {
		this.jdOrderId = jdOrderId;
	}
	public Integer getCustomerExpect() {
		return customerExpect;
	}
	public void setCustomerExpect(Integer customerExpect) {
		this.customerExpect = customerExpect;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	
	
	public boolean isNeedDetectionReport() {
		return isNeedDetectionReport;
	}
	public void setNeedDetectionReport(boolean isNeedDetectionReport) {
		this.isNeedDetectionReport = isNeedDetectionReport;
	}
	public String getQuestionPic() {
		return questionPic;
	}
	public void setQuestionPic(String questionPic) {
		this.questionPic = questionPic;
	}
	public boolean isHasPackage() {
		return isHasPackage;
	}
	public void setHasPackage(boolean isHasPackage) {
		this.isHasPackage = isHasPackage;
	}
	public Integer getPackageDesc() {
		return packageDesc;
	}
	public void setPackageDesc(Integer packageDesc) {
		this.packageDesc = packageDesc;
	}
	public AfterSaleCustomerDto getAsCustomerDto() {
		return asCustomerDto;
	}
	public void setAsCustomerDto(AfterSaleCustomerDto asCustomerDto) {
		this.asCustomerDto = asCustomerDto;
	}
	public AfterSalePickwareDto getAsPickwareDto() {
		return asPickwareDto;
	}
	public void setAsPickwareDto(AfterSalePickwareDto asPickwareDto) {
		this.asPickwareDto = asPickwareDto;
	}
	public AfterSaleReturnwareDto getAsReturnwareDto() {
		return asReturnwareDto;
	}
	public void setAsReturnwareDto(AfterSaleReturnwareDto asReturnwareDto) {
		this.asReturnwareDto = asReturnwareDto;
	}
	public AfterSaleDetailDto getAsDetailDto() {
		return asDetailDto;
	}
	public void setAsDetailDto(AfterSaleDetailDto asDetailDto) {
		this.asDetailDto = asDetailDto;
	}

	
}
