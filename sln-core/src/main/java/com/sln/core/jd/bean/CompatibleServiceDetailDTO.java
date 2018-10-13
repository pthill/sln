package com.sln.core.jd.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 服务单详情实体
 * @author hlq
 *
 */
public class CompatibleServiceDetailDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 服务类型码 退货
	 */
	public static final int CUSTOMER_EXPECT_10 = 10;
	
	/**
	 * 服务类型码 换货
	 */
	public static final int CUSTOMER_EXPECT_20 = 20;
	
	/**
	 * 服务类型码 维修
	 */
	public static final int CUSTOMER_EXPECT_30 = 30;
	
	/**
	 * 是否有发票 没有
	 */
	public static final int IS_HAS_INVOICE_0 = 0;
	
	/**
	 * 是否有发票 有
	 */
	public static final int IS_HAS_INVOICE_1 = 1;
	
	/**
	 * 是否有检验报告 没有
	 */
	public static final int IS_NEED_DELECTION_REPORT_0 = 0;
	
	/**
	 * 是否有检验报告 有
	 */
	public static final int IS_NEED_DELECTION_REPORT_1 = 1;
	
	/**
	 * 是否有包装 没有
	 */
	public static final int  IS_HAS_PACKAGE_0 = 0;
	
	/**
	 * 是否有包装 有
	 */
	public static final int  IS_HAS_PACKAGE_1 = 1;
	
	/**
	 * 审核结果 直赔积分
	 */
	public static final int APPROVED_RESULT_11 = 11;
	
	/**
	 * 审核结果 直赔余额
	 */
	public static final int APPROVED_RESULT_12 = 12;
	
	/**
	 * 审核结果 直赔优惠卷
	 */
	public static final int APPROVED_RESULT_13 = 13;
	
	/**
	 * 审核结果 直赔京豆
	 */
	public static final int APPROVED_RESULT_14 = 14;
	
	/**
	 * 审核结果 直赔商品
	 */
	public static final int APPROVED_RESULT_21 = 21;
	
	/**
	 * 审核结果 上门换新
	 */
	public static final int APPROVED_RESULT_22 = 22;
	
	/**
	 * 审核结果 自营取件
	 */
	public static final int APPROVED_RESULT_31 = 31;
	
	/**
	 * 审核结果 客户送货
	 */
	public static final int APPROVED_RESULT_32 = 32;
	
	/**
	 * 审核结果 客户发货
	 */
	public static final int APPROVED_RESULT_33 = 33;
	
	/**
	 * 审核结果 闪电退款
	 */
	public static final int APPROVED_RESULT_34 = 34;
	
	/**
	 * 审核结果 虚拟退款
	 */
	public static final int APPROVED_RESULT_35 = 35;
	
	/**
	 * 审核结果 大家电检测
	 */
	public static final int APPROVED_RESULT_80 = 80;
	
	/**
	 * 审核结果 大家电安装
	 */
	public static final int APPROVED_RESULT_81 = 81;
	
	/**
	 * 审核结果 大家电移机
	 */
	public static final int APPROVED_RESULT_82 = 82;
	
	/**
	 * 审核结果 大家电维修
	 */
	public static final int APPROVED_RESULT_83 = 83;
	
	/**
	 * 审核结果 大家电其它
	 */
	public static final int APPROVED_RESULT_84 = 84;
	
	/**
	 * 处理结果 返修换新
	 */
	public static final int PROCESS_RESULT_23 =23;
	
	/**
	 * 处理结果 退货
	 */
	public static final int PROCESS_RESULT_40 =40;
	
	/**
	 * 处理结果 换良
	 */
	public static final int PROCESS_RESULT_50 =50;
	
	/**
	 * 处理结果 原返
	 */
	public static final int PROCESS_RESULT_60 =60;
	
	/**
	 * 处理结果 病单
	 */
	public static final int PROCESS_RESULT_71 =71;
	
	/**
	 * 处理结果 出检
	 */
	public static final int PROCESS_RESULT_72 =72;
	
	/**
	 * 处理结果 维修
	 */
	public static final int PROCESS_RESULT_73 =73;
	
	/**
	 * 处理结果 强制关单
	 */
	public static final int PROCESS_RESULT_80 =80;
	
	/**
	 * 处理结果 线下换新 
	 */
	public static final int PROCESS_RESULT_90 =90;
	
	/**
	 *服务单允许的操作列表 取消
	 */
	public static final int ALLOW_OPERATION_1 =1;
	
	/**
	 *服务单允许的操作列表 允许填写或者修改客户发货信
	 */
	public static final int ALLOW_OPERATION_2 =2;
	
	private Integer afsServiceId;    //服务单号
	private Integer customerExpect;  //服务类型码
	private String  afsApplyTime;    //服务申请时间 格式为yyyy-MM-dd HH:mm:ss
	private long    orderId;        //订单号
	private int     isHasInvoice;   //是否有发票
	private int     isNeedDetectionReport; //是不是有检测报告
	private int     isHasPackage;   //是否有包装
	private String  questionPic;    //上传图片地址 不同图片逗号分割，可能为空
	private int     afsServiceStep; //服务单环节 同AfsServicebyCustomerPin类 afsServiceStep一致
	private String  afsServiceStepName; //服务单环节名称 同AfsServicebyCustomerPin类 afsServiceStepName一致
	private String  approveNotes;      //审核意见
	private String  questionDesc;     //问题描述
	private Integer approvedResult;   //审核结果
	private String approvedResultName; //审核结果名称
	private Integer processResult; //处理结果
	private ServiceCustomerInfoDTO serviceCustomerInfoDTO; //客户信息
	private ServiceAftersalesAddressInfoDTO serviceAftersalesAddressInfoDTO; //售后地址信息
	private ServiceExpressInfoDTO  serviceExpressInfoDTO;  //发货信息
	private List<ServiceFinanceDetailInfoDTO> serviceFinanceDetailInfoDTOs; //退款明细
	private List<ServiceTrackInfoDTO> serviceTrackInfoDTOs; //服务单追踪信息
	private List<ServiceDetailInfoDTO> serviceDetailInfoDTOs; //服务单商品明细
	/**
	 *  获取服务单允许的操作列表 列表为空代表不允许操作
		列表包含1代表取消
		列表包含2代表允许填写或者修改客户发货信息
	 */
	private List<Integer> allowOperations;
	public Integer getAfsServiceId() {
		return afsServiceId;
	}
	public void setAfsServiceId(Integer afsServiceId) {
		this.afsServiceId = afsServiceId;
	}
	public Integer getCustomerExpect() {
		return customerExpect;
	}
	public void setCustomerExpect(Integer customerExpect) {
		this.customerExpect = customerExpect;
	}
	public String getAfsApplyTime() {
		return afsApplyTime;
	}
	public void setAfsApplyTime(String afsApplyTime) {
		this.afsApplyTime = afsApplyTime;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public int getIsHasInvoice() {
		return isHasInvoice;
	}
	public void setIsHasInvoice(int isHasInvoice) {
		this.isHasInvoice = isHasInvoice;
	}
	public int getIsNeedDetectionReport() {
		return isNeedDetectionReport;
	}
	public void setIsNeedDetectionReport(int isNeedDetectionReport) {
		this.isNeedDetectionReport = isNeedDetectionReport;
	}
	public int getIsHasPackage() {
		return isHasPackage;
	}
	public void setIsHasPackage(int isHasPackage) {
		this.isHasPackage = isHasPackage;
	}
	public String getQuestionPic() {
		return questionPic;
	}
	public void setQuestionPic(String questionPic) {
		this.questionPic = questionPic;
	}
	public int getAfsServiceStep() {
		return afsServiceStep;
	}
	public void setAfsServiceStep(int afsServiceStep) {
		this.afsServiceStep = afsServiceStep;
	}
	public String getAfsServiceStepName() {
		return afsServiceStepName;
	}
	public void setAfsServiceStepName(String afsServiceStepName) {
		this.afsServiceStepName = afsServiceStepName;
	}
	public String getApproveNotes() {
		return approveNotes;
	}
	public void setApproveNotes(String approveNotes) {
		this.approveNotes = approveNotes;
	}
	public String getQuestionDesc() {
		return questionDesc;
	}
	public void setQuestionDesc(String questionDesc) {
		this.questionDesc = questionDesc;
	}
	public Integer getApprovedResult() {
		return approvedResult;
	}
	public void setApprovedResult(Integer approvedResult) {
		this.approvedResult = approvedResult;
	}
	public String getApprovedResultName() {
		return approvedResultName;
	}
	public void setApprovedResultName(String approvedResultName) {
		this.approvedResultName = approvedResultName;
	}
	public Integer getProcessResult() {
		return processResult;
	}
	public void setProcessResult(Integer processResult) {
		this.processResult = processResult;
	}
	public ServiceCustomerInfoDTO getServiceCustomerInfoDTO() {
		return serviceCustomerInfoDTO;
	}
	public void setServiceCustomerInfoDTO(
			ServiceCustomerInfoDTO serviceCustomerInfoDTO) {
		this.serviceCustomerInfoDTO = serviceCustomerInfoDTO;
	}
	public ServiceAftersalesAddressInfoDTO getServiceAftersalesAddressInfoDTO() {
		return serviceAftersalesAddressInfoDTO;
	}
	public void setServiceAftersalesAddressInfoDTO(
			ServiceAftersalesAddressInfoDTO serviceAftersalesAddressInfoDTO) {
		this.serviceAftersalesAddressInfoDTO = serviceAftersalesAddressInfoDTO;
	}
	public ServiceExpressInfoDTO getServiceExpressInfoDTO() {
		return serviceExpressInfoDTO;
	}
	public void setServiceExpressInfoDTO(ServiceExpressInfoDTO serviceExpressInfoDTO) {
		this.serviceExpressInfoDTO = serviceExpressInfoDTO;
	}
	public List<ServiceFinanceDetailInfoDTO> getServiceFinanceDetailInfoDTOs() {
		return serviceFinanceDetailInfoDTOs;
	}
	public void setServiceFinanceDetailInfoDTOs(
			List<ServiceFinanceDetailInfoDTO> serviceFinanceDetailInfoDTOs) {
		this.serviceFinanceDetailInfoDTOs = serviceFinanceDetailInfoDTOs;
	}
	public List<ServiceTrackInfoDTO> getServiceTrackInfoDTOs() {
		return serviceTrackInfoDTOs;
	}
	public void setServiceTrackInfoDTOs(
			List<ServiceTrackInfoDTO> serviceTrackInfoDTOs) {
		this.serviceTrackInfoDTOs = serviceTrackInfoDTOs;
	}
	public List<ServiceDetailInfoDTO> getServiceDetailInfoDTOs() {
		return serviceDetailInfoDTOs;
	}
	public void setServiceDetailInfoDTOs(
			List<ServiceDetailInfoDTO> serviceDetailInfoDTOs) {
		this.serviceDetailInfoDTOs = serviceDetailInfoDTOs;
	}
	public List<Integer> getAllowOperations() {
		return allowOperations;
	}
	public void setAllowOperations(List<Integer> allowOperations) {
		this.allowOperations = allowOperations;
	}   

	
}
