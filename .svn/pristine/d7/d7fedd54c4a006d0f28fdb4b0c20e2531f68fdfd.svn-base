package com.sln.core.jd.bean;

import java.io.Serializable;

/**
 * 统一下单实体类
 *                       
 * @Filename: JDOrder.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
public class JDOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String            thirdOrder;           //第三方的订单单号
    private String            sku;                  //[{"skuId":商品编号, "num":商品数量,"bNeedAnnex":true, "bNeedGift":true, "price":100, "yanbao":[{"skuId":商品编号}]}]
    private String            name;                  //收货人
    private int               province;              //一级地址
    private int               city;                  //二级地址
    private int               county;                //三级地址
    private int               town;                  //四级地址
    private String            address;               //详细地址
    private String            zip;                   //邮编，可空
    private String            phone;                 //座机号，可空
    private String            mobile;               //手机号
    private String            email;                 //邮箱
    private String            remark;                //备注（少于100字），可空
    private int               invoiceState;          //开票方式(1为随货开票，0为订单预借，2为集中开票 )
    private int               invoiceType;           //1普通发票2增值税发票
    private int               selectedInvoiceTitle;  //发票类型：4个人，5单位
    private String            companyName;           //发票抬头  (如果selectedInvoiceTitle=5则此字段必须)
    private int               invoiceContent;        //1:明细，3：电脑配件，19:耗材，22：办公用品 备注:若增值发票则只能选1 明细
    private int               paymentType;           //支付方式 (1：货到付款，2：邮局付款，4：在线支付，5：公司转账，6：银行转账，7：网银钱包，101：金采支付)
    private int               isUseBalance;          //使用余额paymentType=4时，此值固定是1 其他支付方式0
    private int               submitState;           //是否预占库存，0是预占库存（需要调用确认订单接口），1是不预占库存
    private String            invoiceName;           //增值票收票人姓名 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private String            invoicePhone;          //增值票收票人电话 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private int               invoiceProvice;        //增值票收票人所在省(京东地址编码) 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private int               invoiceCity;           //增值票收票人所在市(京东地址编码) 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private int               invoiceCounty;         //增值票收票人所在区/县(京东地址编码) 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private String               invoiceAddress;        //增值票收票人所在地址 备注：当invoiceType=2 且invoiceState=1时则此字段必填
    private int               doOrderPriceMode;      //下单价格模式
    private String            orderPriceSnap;        //客户端订单价格快照
    private int               reservingDate;         //大家电配送日期
    private int               installDate;           //大家电安装日期
    private boolean           needInstall;           //大家电是否选择了安装
    private String            promiseDate;           //中小件配送预约日期
    private String            promiseTimeRange;      //中小件配送预约时间段
    private int               promiseTimeRangeCode;  //中小件预约时间段的标记

    public String getThirdOrder() {
        return thirdOrder;
    }

    public void setThirdOrder(String thirdOrder) {
        this.thirdOrder = thirdOrder;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getCounty() {
        return county;
    }

    public void setCounty(int county) {
        this.county = county;
    }

    public int getTown() {
        return town;
    }

    public void setTown(int town) {
        this.town = town;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getInvoiceState() {
        return invoiceState;
    }

    public void setInvoiceState(int invoiceState) {
        this.invoiceState = invoiceState;
    }

    public int getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(int invoiceType) {
        this.invoiceType = invoiceType;
    }

    public int getSelectedInvoiceTitle() {
        return selectedInvoiceTitle;
    }

    public void setSelectedInvoiceTitle(int selectedInvoiceTitle) {
        this.selectedInvoiceTitle = selectedInvoiceTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(int invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public int getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(int paymentType) {
        this.paymentType = paymentType;
    }

    public int getIsUseBalance() {
        return isUseBalance;
    }

    public void setIsUseBalance(int isUseBalance) {
        this.isUseBalance = isUseBalance;
    }

    public int getSubmitState() {
        return submitState;
    }

    public void setSubmitState(int submitState) {
        this.submitState = submitState;
    }

    public String getInvoiceName() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName = invoiceName;
    }

    public String getInvoicePhone() {
        return invoicePhone;
    }

    public void setInvoicePhone(String invoicePhone) {
        this.invoicePhone = invoicePhone;
    }

    public int getInvoiceProvice() {
        return invoiceProvice;
    }

    public void setInvoiceProvice(int invoiceProvice) {
        this.invoiceProvice = invoiceProvice;
    }

    public int getInvoiceCity() {
        return invoiceCity;
    }

    public void setInvoiceCity(int invoiceCity) {
        this.invoiceCity = invoiceCity;
    }

    public int getInvoiceCounty() {
        return invoiceCounty;
    }

    public void setInvoiceCounty(int invoiceCounty) {
        this.invoiceCounty = invoiceCounty;
    }

    public String getInvoiceAddress() {
        return invoiceAddress;
    }

    public void setInvoiceAddress(String invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public int getDoOrderPriceMode() {
        return doOrderPriceMode;
    }

    public void setDoOrderPriceMode(int doOrderPriceMode) {
        this.doOrderPriceMode = doOrderPriceMode;
    }

    public String getOrderPriceSnap() {
        return orderPriceSnap;
    }

    public void setOrderPriceSnap(String orderPriceSnap) {
        this.orderPriceSnap = orderPriceSnap;
    }

    public int getReservingDate() {
        return reservingDate;
    }

    public void setReservingDate(int reservingDate) {
        this.reservingDate = reservingDate;
    }

    public int getInstallDate() {
        return installDate;
    }

    public void setInstallDate(int installDate) {
        this.installDate = installDate;
    }

    public boolean isNeedInstall() {
        return needInstall;
    }

    public void setNeedInstall(boolean needInstall) {
        this.needInstall = needInstall;
    }

    public String getPromiseDate() {
        return promiseDate;
    }

    public void setPromiseDate(String promiseDate) {
        this.promiseDate = promiseDate;
    }

    public String getPromiseTimeRange() {
        return promiseTimeRange;
    }

    public void setPromiseTimeRange(String promiseTimeRange) {
        this.promiseTimeRange = promiseTimeRange;
    }

    public int getPromiseTimeRangeCode() {
        return promiseTimeRangeCode;
    }

    public void setPromiseTimeRangeCode(int promiseTimeRangeCode) {
        this.promiseTimeRangeCode = promiseTimeRangeCode;
    }
}
