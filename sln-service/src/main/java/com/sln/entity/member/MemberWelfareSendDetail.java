package com.sln.entity.member;

import java.io.Serializable;

/**
 * 福利积分发放详情
 * <p>Table: <strong>member_welfare_send_detail</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>welfareId</td><td>{@link java.lang.Integer}</td><td>welfare_id</td><td>int</td><td>福利积分发送的id</td></tr>
 *   <tr><td>staffNo</td><td>{@link java.lang.String}</td><td>staff_no</td><td>varchar</td><td>员工号</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>员工名称</td></tr>
 *   <tr><td>tel</td><td>{@link java.lang.String}</td><td>tel</td><td>varchar</td><td>员工电话</td></tr>
 *   <tr><td>money</td><td>{@link java.lang.Integer}</td><td>money</td><td>int</td><td>发送额度</td></tr>
 *   <tr><td>birthday</td><td>{@link java.lang.String}</td><td>birthday</td><td>varchar</td><td>出生日期</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>积分生效期</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>积分失效期</td></tr>
 * </table>
 *
 */
public class MemberWelfareSendDetail implements Serializable {

    private java.lang.Integer id;
    private java.lang.Integer welfareId;
    private java.lang.String staffNo;
    private java.lang.String name;
    private java.lang.String tel;
    private java.lang.Integer money;
    private java.lang.String birthday;
    private java.lang.String startTime;
    private java.lang.String endTime;
    private java.lang.String sellerName;


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
     * 获取福利积分发送的id
     */
    public java.lang.Integer getWelfareId(){
        return this.welfareId;
    }

    /**
     * 设置福利积分发送的id
     */
    public void setWelfareId(java.lang.Integer welfareId){
        this.welfareId = welfareId;
    }

    /**
     * 获取员工号
     */
    public java.lang.String getStaffNo(){
        return this.staffNo;
    }

    /**
     * 设置员工号
     */
    public void setStaffNo(java.lang.String staffNo){
        this.staffNo = staffNo;
    }

    /**
     * 获取员工名称
     */
    public java.lang.String getName(){
        return this.name;
    }

    /**
     * 设置员工名称
     */
    public void setName(java.lang.String name){
        this.name = name;
    }

    /**
     * 获取员工电话
     */
    public java.lang.String getTel(){
        return this.tel;
    }

    /**
     * 设置员工电话
     */
    public void setTel(java.lang.String tel){
        this.tel = tel;
    }

    /**
     * 获取发送额度
     */
    public java.lang.Integer getMoney(){
        return this.money;
    }

    /**
     * 设置发送额度
     */
    public void setMoney(java.lang.Integer money){
        this.money = money;
    }

    /**
     * 获取出生日期
     */
    public java.lang.String getBirthday(){
        return this.birthday;
    }

    /**
     * 设置出生日期
     */
    public void setBirthday(java.lang.String birthday){
        this.birthday = birthday;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
