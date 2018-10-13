package com.sln.core.jd.util;

import java.io.Serializable;

/**
 * 京东接口调用结果统一封装类
 * @author li.biao
 */
public class JDApiResult<T> implements Serializable {

	private static final long serialVersionUID = 3157720967319047766L;
	
	/**接口调用是否成功*/
	private boolean success  = true ;
	
	/**接口调用信息*/
	private String message ;
	
	/**接口调用返回编码*/
	private String code ;
	
	/**接口调用结果*/
	private T result ;
	
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	
}
