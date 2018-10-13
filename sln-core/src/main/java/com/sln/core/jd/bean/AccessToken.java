package com.sln.core.jd.bean;

import java.io.Serializable;
import java.util.Date;

public class AccessToken implements Serializable {
	
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;

	/**   请求access_token表  access_token状态：1、启用；*/
	public final static int  ACCESSTOKEN_STATE_1 = 1; 
	
	/**   请求access_token表  access_token状态：1、失效；*/
	public final static int  ACCESSTOKEN_STATE_2 = 2; 
	
	/**   请求access_token表  access_token状态：1、请求失败；*/
	public final static int  ACCESSTOKEN_STATE_3 = 3; 
	
	private  int         id;   				 		//ID
	private  String      uid; 					 	//UID
	private  String      access_token; 			 	//access_token
	private  String      refresh_token; 		 	//refresh_token
	private  Date        time;         			 	//当前时间
	private  long        expires_in;   		 		//过期时间秒级别
	private  long        refresh_token_expires; 	//过期时间毫秒级别
	private  int         state;        	     		//状态
	private  String      result_message;        	//失败原因
	private  Date        createTime;            	//请求时间  
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public long getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(long expires_in) {
		this.expires_in = expires_in;
	}
	public long getRefresh_token_expires() {
		return refresh_token_expires;
	}
	public void setRefresh_token_expires(long refresh_token_expires) {
		this.refresh_token_expires = refresh_token_expires;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getResult_message() {
		return result_message;
	}
	public void setResult_message(String result_message) {
		this.result_message = result_message;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	
	
}
