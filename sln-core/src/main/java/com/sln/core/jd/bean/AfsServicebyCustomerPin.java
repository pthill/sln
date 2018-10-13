package com.sln.core.jd.bean;

import java.io.Serializable;

/**
 * 京东服务单实体
 * @author hlq
 *
 */
public class AfsServicebyCustomerPin implements Serializable{

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
	 * 服务单环节  申请阶段
	 */
	public static final int AFS_SERVICE_STEP_10 = 10;
	
	/**
	 * 服务单环节  审核不通过
	 */
	public static final int AFS_SERVICE_STEP_20 = 20;
	
	/**
	 * 服务单环节  客服审核
	 */
	public static final int AFS_SERVICE_STEP_21 = 21;
	
	/**
	 * 服务单环节  商家审核
	 */
	public static final int AFS_SERVICE_STEP_22 = 22;
	
	/**
	 * 服务单环节  京东收货
	 */
	public static final int AFS_SERVICE_STEP_31 = 31;
	
	/**
	 * 服务单环节  商家收货
	 */
	public static final int AFS_SERVICE_STEP_32 = 32;
	
	/**
	 * 服务单环节  京东处理
	 */
	public static final int AFS_SERVICE_STEP_33 = 33;
	
	/**
	 * 服务单环节  商家处理
	 */
	public static final int AFS_SERVICE_STEP_34 = 34;
	
	/**
	 * 服务单环节  用户确认
	 */
	public static final int AFS_SERVICE_STEP_40 = 40;
	
	/**
	 * 服务单环节  完成
	 */
	public static final int AFS_SERVICE_STEP_50 = 50;
	
	/**
	 * 服务单环节  取消
	 */
	public static final int AFS_SERVICE_STEP_60 = 60;
	
	/**
	 * 是否可取消 否
	 */
	public static final int CANCEL_0 = 0;
	
	/**
	 * 是否可取消 是
	 */
	public static final int CANCEL_1 = 1;
	
	
	
	private Integer afsServiceId; //服务单号
	private Integer customerExpect; //服务类型码  退货(10)、换货(20)、维修(30)
	private String customerExpectName; // 服务类型名称
	private String afsApplyTime; //服务单申请时间 格式为yyyy-MM-dd HH:mm:ss
	private long orderId;       //订单号
	private long wareId;        //商品编号
	private String wareName;    //商品名称
	private Integer afsServiceStep; //服务单环节 申请阶段(10),审核不通过(20),客服审核(21),商家审核(22),京东收货(31),商家收货(32), 京东处理(33),商家处理(34), 用户确认(40),完成(50), 取消 (60);
	private String afsServiceStepName; //服务单环节名称  申请阶段,客服审核,商家审核,京东收货,商家收货, 京东处理,商家处理, 用户确认,完成, 取消;
	private int cancel;             //0代表否，1代表是
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
	
	public String getCustomerExpectName() {
		return customerExpectName;
	}
	public void setCustomerExpectName(String customerExpectName) {
		this.customerExpectName = customerExpectName;
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
	public long getWareId() {
		return wareId;
	}
	public void setWareId(long wareId) {
		this.wareId = wareId;
	}
	public String getWareName() {
		return wareName;
	}
	public void setWareName(String wareName) {
		this.wareName = wareName;
	}
	public Integer getAfsServiceStep() {
		return afsServiceStep;
	}
	public void setAfsServiceStep(Integer afsServiceStep) {
		this.afsServiceStep = afsServiceStep;
	}
	public String getAfsServiceStepName() {
		return afsServiceStepName;
	}
	public void setAfsServiceStepName(String afsServiceStepName) {
		this.afsServiceStepName = afsServiceStepName;
	}
	public int getCancel() {
		return cancel;
	}
	public void setCancel(int cancel) {
		this.cancel = cancel;
	}
	
	
	
}
