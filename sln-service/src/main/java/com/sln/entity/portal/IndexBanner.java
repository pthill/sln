package com.sln.entity.portal;

import java.io.Serializable;
/**
 *
 * <p>Table: <strong>index_banner</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>tinyint</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>order</td><td>{@link java.lang.Integer}</td><td>order</td><td>tinyint</td><td>排序</td></tr>
 *   <tr><td>abbreviation</td><td>{@link java.lang.String}</td><td>abbreviation</td><td>varchar</td><td>简称</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>类型</td></tr>
 *   <tr><td>url</td><td>{@link java.lang.String}</td><td>url</td><td>varchar</td><td>url</td></tr>
 *   <tr><td>startTime</td><td>{@link java.util.Date}</td><td>start_time</td><td>datetime</td><td>开始时间</td></tr>
 *   <tr><td>endTime</td><td>{@link java.util.Date}</td><td>end_time</td><td>datetime</td><td>结束时间</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.String}</td><td>state</td><td>varchar</td><td>状态</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>createTime</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>updateTime</td></tr>
 * </table>
 *
 */
public class IndexBanner implements Serializable {

    private static final long serialVersionUID = -324391457777649946L;

    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.Integer order;
    private java.lang.String abbreviation;
    private java.lang.String type;
    private java.lang.String url;
    private java.util.Date startTime;
    private java.util.Date endTime;
    private java.lang.String state;
    private java.lang.String img;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private Integer parkId;
    //非数据库字段
    private String parkName;


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
     * 获取简称
     */
    public java.lang.String getAbbreviation(){
        return this.abbreviation;
    }

    /**
     * 设置简称
     */
    public void setAbbreviation(java.lang.String abbreviation){
        this.abbreviation = abbreviation;
    }

    /**
     * 获取类型
     */
    public java.lang.String getType(){
        return this.type;
    }

    /**
     * 设置类型
     */
    public void setType(java.lang.String type){
        this.type = type;
    }

    /**
     * 获取url
     */
    public java.lang.String getUrl(){
        return this.url;
    }

    /**
     * 设置url
     */
    public void setUrl(java.lang.String url){
        this.url = url;
    }

    /**
     * 获取开始时间
     */
    public java.util.Date getStartTime(){
        return this.startTime;
    }

    /**
     * 设置开始时间
     */
    public void setStartTime(java.util.Date startTime){
        this.startTime = startTime;
    }

    /**
     * 获取结束时间
     */
    public java.util.Date getEndTime(){
        return this.endTime;
    }

    /**
     * 设置结束时间
     */
    public void setEndTime(java.util.Date endTime){
        this.endTime = endTime;
    }

    /**
     * 获取状态
     */
    public java.lang.String getState(){
        return this.state;
    }

    /**
     * 设置状态
     */
    public void setState(java.lang.String state){
        this.state = state;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }
}
