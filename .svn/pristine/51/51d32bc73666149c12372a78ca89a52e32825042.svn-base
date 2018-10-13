package com.sln.dto;

import java.io.Serializable;

public class MemberDto implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 861955734294915073L;
	private Integer id;   //id
	private String name; //用户名
	private Integer  grade;    //会员等级
	private String gradeName;  //等级称呼
    private Integer  gradeValue;  //会员经验值
    private Integer  integral;     //会员积分
    private String   sessionId;   //设置返回APP登录的sessionId
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		if(grade == 1){
			setGradeName("注册会员");
		}else if(grade == 2){
			setGradeName("铜牌会员");
		}else if(grade == 3){
			setGradeName("银牌会员");
		}else if(grade == 4){
			setGradeName("金牌会员");
			
		}else if(grade == 5){
			setGradeName("砖石会员");
			
		}
		this.grade = grade;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public Integer getGradeValue() {
		return gradeValue;
	}
	public void setGradeValue(Integer gradeValue) {
		this.gradeValue = gradeValue;
	}
	public Integer getIntegral() {
		return integral;
	}
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
    
    
}
