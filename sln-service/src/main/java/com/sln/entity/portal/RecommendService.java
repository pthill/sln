package com.sln.entity.portal;

import java.io.Serializable;
/**
 *
 * <p>Table: <strong>recommend_service</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>tinyint</td><td>id</td></tr>
 *   <tr><td>serviceId</td><td>{@link java.lang.Integer}</td><td>service_id</td><td>tinyint</td><td>serviceId</td></tr>
 *   <tr><td>order</td><td>{@link java.lang.Integer}</td><td>order</td><td>tinyint</td><td>order</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.String}</td><td>state</td><td>varchar</td><td>state</td></tr>
 *   <tr><td>img</td><td>{@link java.lang.String}</td><td>img</td><td>varchar</td><td>img</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class RecommendService implements Serializable {
    private static final long serialVersionUID = -7071005688334863357L;
    private java.lang.Integer id;
    private java.lang.Integer serviceId;
    private java.lang.Integer order;
    private java.lang.String state;
    private java.lang.String img;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer parkId;
    //以下是非数据库字段
    private String fwx;
    private String fwl;


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
     * 获取serviceId
     */
    public java.lang.Integer getServiceId(){
        return this.serviceId;
    }

    /**
     * 设置serviceId
     */
    public void setServiceId(java.lang.Integer serviceId){
        this.serviceId = serviceId;
    }

    /**
     * 获取order
     */
    public java.lang.Integer getOrder(){
        return this.order;
    }

    /**
     * 设置order
     */
    public void setOrder(java.lang.Integer order){
        this.order = order;
    }

    /**
     * 获取state
     */
    public java.lang.String getState(){
        return this.state;
    }

    /**
     * 设置state
     */
    public void setState(java.lang.String state){
        this.state = state;
    }

    /**
     * 获取img
     */
    public java.lang.String getImg(){
        return this.img;
    }

    /**
     * 设置img
     */
    public void setImg(java.lang.String img){
        this.img = img;
    }

    /**
     * 获取createTime
     */
    public java.util.Date getCreateTime(){
        return this.createTime;
    }

    /**
     * 设置createTime
     */
    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
    }

    /**
     * 获取updateTime
     */
    public java.util.Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置updateTime
     */
    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime;
    }

    public String getFwx() {
        return fwx;
    }

    public void setFwx(String fwx) {
        this.fwx = fwx;
    }

    public String getFwl() {
        return fwl;
    }

    public void setFwl(String fwl) {
        this.fwl = fwl;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
}
