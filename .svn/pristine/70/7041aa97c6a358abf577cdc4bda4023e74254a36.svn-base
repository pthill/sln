package com.sln.core.jd.bean;

import java.io.Serializable;

import com.sln.core.exception.BusinessException;

/**
 * 京东客户信息实体类
 * @author hlq
 *
 */
/**
 * @author hlq
 *
 */
public class AfterSaleCustomerDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String customerContactName;           //联系人         最多50字符，能获取到就必填
	private String customerTel;                   //联系电话     最多50字符，能获取到就必填
	private String customerMobilePhone;           //手机号         最多50字符
	private String customerEmail;                 //email     最多50字符
	private String customerPostcode;              //邮编             最多20字符
	public String getCustomerContactName() {
		return customerContactName;
	}
	public void setCustomerContactName(String customerContactName) {
		if(customerContactName.length() >50){
			throw new BusinessException("联系人长度不能超过50");
		}else{
			this.customerContactName = customerContactName;
		}
	}
	public String getCustomerTel() {
		return customerTel;
	}
	public void setCustomerTel(String customerTel) {
		if(customerTel.length() >50){
			throw new BusinessException("联系电话长度不能超过50");
		}else{
			this.customerTel = customerTel;
		}
	}
	public String getCustomerMobilePhone() {
		return customerMobilePhone;
	}
	public void setCustomerMobilePhone(String customerMobilePhone) {
		if(customerMobilePhone.length() >50){
			throw new BusinessException("手机号长度不能超过50");
		}else{
			this.customerMobilePhone = customerMobilePhone;
		}
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		if(customerEmail.length() >50){
			throw new BusinessException("eamil长度不能超过50");
		}else{
			this.customerEmail = customerEmail;
		}
	}
	public String getCustomerPostcode() {
		return customerPostcode;
	}
	public void setCustomerPostcode(String customerPostcode) {
		if(customerPostcode.length() >20){
			throw new BusinessException("邮编长度不能超过20");
		}else{
			this.customerPostcode = customerPostcode;
		}
		
	}
	
	
	
}
