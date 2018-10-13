package com.sln.entity.operate;

import java.io.Serializable;
import java.util.Date;
/**
 * 业务方管理表
 * <p>Table: <strong>Manager</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>code</td><td>{@link java.lang.String}</td><td>code</td><td>varchar</td><td>编码</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>名称</td></tr>
 *   <tr><td>status</td><td>{@link java.lang.String}</td><td>status</td><td>varchar</td><td>状态s是否启用1:启用0:禁用</td></tr>
 *   <tr><td>address</td><td>{@link java.lang.String}</td><td>address</td><td>varchar</td><td>地址</td></tr>
 *   <tr><td>parkId</td><td>{@link java.lang.Integer}</td><td>parkId</td><td>int</td><td>园区id</td></tr>
 *   <tr><td>description</td><td>{@link java.lang.String}</td><td>description</td><td>varchar</td><td>描述</td></tr>
 *   <tr><td>company</td><td>{@link java.lang.String}</td><td>company</td><td>varchar</td><td>公司</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>createTime</td><td>timestamp</td><td>创建时间</td></tr>
 *   <tr><td>updateTime</td><td>{@link java.util.Date}</td><td>updateTime</td><td>timestamp</td><td>更新时间</td></tr>
 * </table>
 *
 */

public class OperationManager implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID = -6447170387217331129L;
    private Integer id;
    private String  code;
    private String  name;
    private String  status;
    private String  address;
    private Integer  parkId;
    private String  description;
    private String  company;
    private Date    createTime;
    private Date    updateTime;
    //以下字段是非数据库字段
    private String parkName;
    private String operationName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getParkId() {
        return parkId;
    }

    public void setParkId(Integer parkId) {
        this.parkId = parkId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
