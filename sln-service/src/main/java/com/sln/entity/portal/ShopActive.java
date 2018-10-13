package com.sln.entity.portal;

import java.io.Serializable;

/**
 *
 * <p>Table: <strong>shop_active</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>tinyint</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>img</td><td>{@link java.lang.String}</td><td>img</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>活动类型</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.String}</td><td>state</td><td>varchar</td><td>状态</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.String}</td><td>status</td><td>varchar</td><td>启用禁用</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 * </table>
 *
 */
public class ShopActive implements Serializable{

    private static final long serialVersionUID = -5585977385837563797L;

    private java.lang.Integer id;
    private java.lang.String name;
    private java.lang.String img;
    private java.lang.String type;
    private java.lang.String state;             //即将开始，进行中，已结束
    private java.lang.String status;            //状态 1:启用 0:禁用
    private java.lang.String url;
    private java.util.Date createTime;
    private java.util.Date updateTime;


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
     * 获取图片
     */
    public java.lang.String getImg(){
        return this.img;
    }

    /**
     * 设置图片
     */
    public void setImg(java.lang.String img){
        this.img = img;
    }

    /**
     * 获取活动类型
     */
    public java.lang.String getType(){
        return this.type;
    }

    /**
     * 设置活动类型
     */
    public void setType(java.lang.String type){
        this.type = type;
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
     * 获取启用禁用
     */
    public java.lang.String getStatus(){
        return this.status;
    }

    /**
     * 设置启用禁用
     */
    public void setStatus(java.lang.String status){
        this.status = status;
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
     * 获取更新时间
     */
    public java.util.Date getUpdateTime(){
        return this.updateTime;
    }

    /**
     * 设置更新时间
     */
    public void setUpdateTime(java.util.Date updateTime){
        this.updateTime = updateTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
