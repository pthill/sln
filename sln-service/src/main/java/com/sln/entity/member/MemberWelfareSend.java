package com.sln.entity.member;

import java.io.Serializable;
import java.util.List;

/**
 *
 * <p>Table: <strong>member_welfare_send</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>company</td><td>{@link java.lang.String}</td><td>company</td><td>varchar</td><td>公司名称</td></tr>
 *   <tr><td>dept</td><td>{@link java.lang.String}</td><td>dept</td><td>varchar</td><td>部门名称</td></tr>
 *   <tr><td>costName</td><td>{@link java.lang.String}</td><td>cost_name</td><td>varchar</td><td>费用名称</td></tr>
 *   <tr><td>countPerson</td><td>{@link java.lang.Integer}</td><td>count_person</td><td>int</td><td>发放人数</td></tr>
 *   <tr><td>createUser</td><td>{@link java.lang.Integer}</td><td>create_user</td><td>int</td><td>创建人</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>sendTime</td><td>{@link java.util.Date}</td><td>send_time</td><td>datetime</td><td>发送时间</td></tr>
 *   <tr><td>sendStatus</td><td>{@link java.lang.String}</td><td>send_status</td><td>varchar</td><td>发送状态</td></tr>
 * </table>
 *
 */
public class MemberWelfareSend implements Serializable {

    private static final long serialVersionUID = 7097360169903089688L;
    private java.lang.Integer id;
    private java.lang.String company;
    private java.lang.String dept;
    private java.lang.String costName;
    private java.lang.Integer countPerson;
    private java.lang.Integer createUser;
    private java.util.Date createTime;
    private java.util.Date sendTime;
    private java.lang.String sendStatus;
    private String path;
    private Integer sellerId;     //专项积分用于某个商家
    private String sellerName;

    //额外属性
    private List<MemberWelfareSendDetail> children;
    private String name;


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
     * 获取公司名称
     */
    public java.lang.String getCompany(){
        return this.company;
    }

    /**
     * 设置公司名称
     */
    public void setCompany(java.lang.String company){
        this.company = company;
    }

    /**
     * 获取部门名称
     */
    public java.lang.String getDept(){
        return this.dept;
    }

    /**
     * 设置部门名称
     */
    public void setDept(java.lang.String dept){
        this.dept = dept;
    }

    /**
     * 获取费用名称
     */
    public java.lang.String getCostName(){
        return this.costName;
    }

    /**
     * 设置费用名称
     */
    public void setCostName(java.lang.String costName){
        this.costName = costName;
    }

    /**
     * 获取发放人数
     */
    public java.lang.Integer getCountPerson(){
        return this.countPerson;
    }

    /**
     * 设置发放人数
     */
    public void setCountPerson(java.lang.Integer countPerson){
        this.countPerson = countPerson;
    }

    /**
     * 获取创建人
     */
    public java.lang.Integer getCreateUser(){
        return this.createUser;
    }

    /**
     * 设置创建人
     */
    public void setCreateUser(java.lang.Integer createUser){
        this.createUser = createUser;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    /**
     * 获取发送时间
     */
    public java.util.Date getSendTime(){
        return this.sendTime;
    }

    /**
     * 设置发送时间
     */
    public void setSendTime(java.util.Date sendTime){
        this.sendTime = sendTime;
    }

    /**
     * 获取发送状态
     */
    public java.lang.String getSendStatus(){
        return this.sendStatus;
    }

    /**
     * 设置发送状态
     */
    public void setSendStatus(java.lang.String sendStatus){
        this.sendStatus = sendStatus;
    }

    public List<MemberWelfareSendDetail> getChildren() {
        return children;
    }

    public void setChildren(List<MemberWelfareSendDetail> children) {
        this.children = children;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
