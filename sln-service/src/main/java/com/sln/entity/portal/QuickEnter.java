package com.sln.entity.portal;

import java.io.Serializable;

/**
 *
 * <p>Table: <strong>quick_enter</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>tinyint</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>order</td><td>{@link java.lang.Integer}</td><td>order</td><td>tinyint</td><td>排序</td></tr>
 *   <tr><td>url</td><td>{@link java.lang.Integer}</td><td>url</td><td>tinyint</td><td>链接</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class QuickEnter implements Serializable {

    private static final long serialVersionUID = -598081845424823821L;

    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.Integer order;
    private java.lang.Integer url;
    private java.lang.String state;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer parkId;

    //以下是非数据库字段
    private String serviceName;


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
     * 获取名称
     */
    public java.lang.String getName(){
        return this.name;
    }

    /**
     * 设置名称
     */
    public void setName(java.lang.String name){
        this.name = name;
    }

    /**
     * 获取排序
     */
    public java.lang.Integer getOrder(){
        return this.order;
    }

    /**
     * 设置排序
     */
    public void setOrder(java.lang.Integer order){
        this.order = order;
    }

    /**
     * 获取链接
     */
    public java.lang.Integer getUrl(){
        return this.url;
    }

    /**
     * 设置链接
     */
    public void setUrl(java.lang.Integer url){
        this.url = url;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }
}
