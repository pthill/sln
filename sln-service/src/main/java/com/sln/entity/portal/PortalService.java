package com.sln.entity.portal;

import java.io.Serializable;

/**
 *
 * <p>Table: <strong>portal_service</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>tinyint</td><td>id</td></tr>
 *   <tr><td>serviceName</td><td>{@link java.lang.String}</td><td>service_name</td><td>varchar</td><td>服务名称</td></tr>
 *   <tr><td>order</td><td>{@link java.lang.Integer}</td><td>order</td><td>tinyint</td><td>排序</td></tr>
 *   <tr><td>abbreviation</td><td>{@link java.lang.String}</td><td>abbreviation</td><td>varchar</td><td>简称</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>编号</td></tr>
 *   <tr><td>type</td><td>{@link java.lang.String}</td><td>type</td><td>varchar</td><td>类型，服务项还是服务类</td></tr>
 *   <tr><td>pid</td><td>{@link java.lang.Integer}</td><td>pid</td><td>tinyint</td><td>服务项id</td></tr>
 *   <tr><td>menuId</td><td>{@link java.lang.Integer}</td><td>menu_id</td><td>tinyint</td><td>一级菜单id</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.String}</td><td>state</td><td>varchar</td><td>状态启用禁用</td></tr>
 *   <tr><td>isShow</td><td>{@link java.lang.String}</td><td>is_show</td><td>varchar</td><td>是否显示</td></tr>
 *   <tr><td>img</td><td>{@link java.lang.String}</td><td>img</td><td>varchar</td><td>图片</td></tr>
 *   <tr><td>highLight</td><td>{@link java.lang.String}</td><td>high_light</td><td>varchar</td><td>是否高亮</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>update_time</td><td>datetime</td><td>更新时间</td></tr>
 * </table>
 *
 */

public class PortalService implements Serializable {
    private static final long serialVersionUID = 6138967692443834874L;

    private java.lang.Integer id;
    private java.lang.String serviceName;
    private java.lang.Integer order;
    private java.lang.String abbreviation;
    private java.lang.String code;
    private java.lang.String type;                                 //服务类型 1:服务项 2:服务类
    private java.lang.Integer pid;
    private java.lang.Integer menuId;
    private java.lang.String state;
    private java.lang.String isShow;
    private java.lang.String img;
    private java.lang.String highLight;
    private java.util.Date createTime;
    private java.util.Date updateTime;
    private java.lang.String url;

    //以下是非数据库字段
    private String menuName;    //菜单名称
    private String parkName;   //园区名称
    private String belong;     //所属

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
     * 获取服务名称
     */
    public java.lang.String getServiceName(){
        return this.serviceName;
    }

    /**
     * 设置服务名称
     */
    public void setServiceName(java.lang.String serviceName){
        this.serviceName = serviceName;
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
     * 获取编号
     */
    public java.lang.String getCode(){
        return this.code;
    }

    /**
     * 设置编号
     */
    public void setCode(java.lang.String code){
        this.code = code;
    }

    /**
     * 获取类型，服务项还是服务类
     */
    public java.lang.String getType(){
        return this.type;
    }

    /**
     * 设置类型，服务项还是服务类
     */
    public void setType(java.lang.String type){
        this.type = type;
    }

    /**
     * 获取服务项id
     */
    public java.lang.Integer getPid(){
        return this.pid;
    }

    /**
     * 设置服务项id
     */
    public void setPid(java.lang.Integer pid){
        this.pid = pid;
    }

    /**
     * 获取一级菜单id
     */
    public java.lang.Integer getMenuId(){
        return this.menuId;
    }

    /**
     * 设置一级菜单id
     */
    public void setMenuId(java.lang.Integer menuId){
        this.menuId = menuId;
    }

    /**
     * 获取状态启用禁用
     */
    public java.lang.String getState(){
        return this.state;
    }

    /**
     * 设置状态启用禁用
     */
    public void setState(java.lang.String state){
        this.state = state;
    }

    /**
     * 获取是否显示
     */
    public java.lang.String getIsShow(){
        return this.isShow;
    }

    /**
     * 设置是否显示
     */
    public void setIsShow(java.lang.String isShow){
        this.isShow = isShow;
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
     * 获取是否高亮
     */
    public java.lang.String getHighLight(){
        return this.highLight;
    }

    /**
     * 设置是否高亮
     */
    public void setHighLight(java.lang.String highLight){
        this.highLight = highLight;
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

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
