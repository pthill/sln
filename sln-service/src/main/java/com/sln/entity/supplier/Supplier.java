package com.sln.entity.supplier;

import java.io.Serializable;

/**
 * 供应商实体表
 * <p>Table: <strong>config</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>供应商名称</td></tr>
 *   <tr><td>adress</td><td>{@link java.lang.String}</td><td>adress</td><td>varchar</td><td>地址</td></tr>
 *   <tr><td>supplierType</td><td>{@link java.lang.String}</td><td>supplier_type</td><td>varchar</td><td>供应商类型</td></tr>
 *   <tr><td>contactsName</td><td>{@link java.lang.String}</td><td>contacts_name</td><td>varchar</td><td>负责人姓名</td></tr>
 *   <tr><td>contactsTel</td><td>{@link java.lang.String}</td><td>contacts_tel</td><td>varchar</td><td>负责人的电话</td></tr>
 *   <tr><td>bankOfAccounts</td><td>{@link java.lang.String}</td><td>bank_of_accounts</td><td>varchar</td><td>开户银行</td></tr>
 *   <tr><td>benkAccount</td><td>{@link java.lang.String}</td><td>benk_account</td><td>varchar</td><td>银行账号</td></tr>
 *   <tr><td>alipay</td><td>{@link java.lang.String}</td><td>alipay</td><td>varchar</td><td>支付宝</td></tr>
 *   <tr><td>weChat</td><td>{@link java.lang.String}</td><td>weChat</td><td>varchar</td><td>微信</td></tr>
 *   <tr><td>userId</td><td>{@link java.lang.String}</td><td>user_id</td><td>varchar</td><td>创建人</td></tr>
 *   <tr><td>state</td><td>{@link java.lang.String}</td><td>state</td><td>varchar</td><td>状态</td></tr>
 *   <tr><td>createTime</td><td>{@link java.lang.String}</td><td>create_time</td><td>varchar</td><td>创建时间</td></tr>
 * </table>
 *
 */
public class Supplier implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */

    private static final long serialVersionUID = -3264652571342200331L;

    /** 状态：1使用 */
    public static final int   STATUS_1         = 1;
    /** 状态：0禁言 */
    public static final int   STATUS_0         = 0;

    private java.lang.Integer id;                                      //编号
    private java.lang.String  name;                                    //名称
    private java.lang.String  adress;                                  //地址
    private java.lang.String  supplierType;                            //供应商类型
    private java.lang.String  contactsName;                            //负责人
    private java.lang.String  contactsTel;                             //联系电话
    private java.lang.String  bankOfAccounts;                          //开户银行
    private java.lang.String  benkAccount;                             //银行账号
    private java.lang.String  alipay;                                  //支付号
    private java.lang.String  weChat;                                  //微信
    private java.lang.Integer userId;                                  //用户
    private java.lang.Integer state;                                   //状态
    private java.util.Date    createTime;
    private java.lang.Integer sellerId;                                //商家id
    private java.lang.String  remark;                                   //备注信息

    public java.lang.Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(java.lang.Integer sellerId) {
        this.sellerId = sellerId;
    }

    /**
     * 设置状态
     * @param state
     */
    public void setState(java.lang.Integer state) {
        this.state = state;
    }

    /**
     * 获取状态
     * @return
     */
    public java.lang.Integer getState() {
        return state;
    }

    /**
     * 获取用户id
     * @return
     */
    public java.lang.Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     * @return
     */
    public void setUserId(java.lang.Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取编号
     * @return
     */
    public java.lang.Integer getId() {
        return id;
    }

    /**
     * 设置编号
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }

    /**
     * 获取供应商名称
     * @return
     */
    public java.lang.String getName() {
        return name;
    }

    /***
     * 设置供应商名称
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * 获取地址
     * @return
     */
    public java.lang.String getAdress() {
        return adress;
    }

    /**
     * 设置地址
     * @param adress
     */
    public void setAdress(java.lang.String adress) {
        this.adress = adress;
    }

    /***
     * 获取供应商类型
     * @return
     */

    public java.lang.String getSupplierType() {
        return supplierType;
    }

    /**
     * 设置供应商类型
     * @param supplierType
     */
    public void setSupplierType(java.lang.String supplierType) {
        this.supplierType = supplierType;
    }

    /**
     * 获取负责人名称
     * @return
     */
    public java.lang.String getContactsName() {
        return contactsName;
    }

    /**
     * 设置负责人名称
     * @param contactsName
     */
    public void setContactsName(java.lang.String contactsName) {
        this.contactsName = contactsName;
    }

    /**
     * 获取负责人电话
     * @return
     */
    public java.lang.String getContactsTel() {
        return contactsTel;
    }

    /**
     * 设置负责人电话
     * @param contactsTel
     */
    public void setContactsTel(java.lang.String contactsTel) {
        this.contactsTel = contactsTel;
    }

    /**
     * 获取开户银行
     * @return
     */
    public java.lang.String getBankOfAccounts() {
        return bankOfAccounts;
    }

    /**
     * 设置开户银行
     * @param bankOfAccounts
     */
    public void setBankOfAccounts(java.lang.String bankOfAccounts) {
        this.bankOfAccounts = bankOfAccounts;
    }

    /**
     * 获取银行账号
     * @return
     */
    public java.lang.String getBenkAccount() {
        return benkAccount;
    }

    /**
     * 设置银行账号
     * @param benkAccount
     */
    public void setBenkAccount(java.lang.String benkAccount) {
        this.benkAccount = benkAccount;
    }

    /**
     * 获取支付宝
     * @return
     */
    public java.lang.String getAlipay() {
        return alipay;
    }

    /**
     * 设置支付宝
     * @param alipay
     */
    public void setAlipay(java.lang.String alipay) {
        this.alipay = alipay;
    }

    /**
     * 获取微信
     * @return
     */
    public java.lang.String getWeChat() {
        return weChat;
    }

    /**
     * 设置微信
     * @param weChat
     */
    public void setWeChat(java.lang.String weChat) {
        this.weChat = weChat;
    }

    public java.util.Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
