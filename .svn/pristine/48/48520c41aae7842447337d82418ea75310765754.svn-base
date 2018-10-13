package com.sln.core.jd.bean;

import java.io.Serializable;

import com.sln.core.exception.BusinessException;

/**
 * 返件信息实体，即商品如何返回客户手中
 * @author hlq
 *
 */
public class AfterSaleReturnwareDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 自营配送(10)
	 */
	public static final int RETURNWARETYPE_10 =10;
	
	/**
	 * 第三方配送(20)
	 */
	public static final int RETURNWARETYPE_20 =20;
	
	private Integer returnwareType;             //返件方式  自营配送(10),第三方配送(20);换、修这两种情况必填（默认值）
	private Integer returnwareProvince;         //返件省    换、修这两种情况必填
	private Integer returnwareCity;             //返件市
	private Integer returnwareCounty;           //返件县
	private Integer returnwareVillage;          //返件乡镇
	private String returnwareAddress;          //返件街道地址  最多500字符，换、修这两种情况必填
	public Integer getReturnwareType() {
		return returnwareType;
	}
	public void setReturnwareType(Integer returnwareType) {
		this.returnwareType = returnwareType;
	}
	public Integer getReturnwareProvince() {
		return returnwareProvince;
	}
	public void setReturnwareProvince(Integer returnwareProvince) {
		this.returnwareProvince = returnwareProvince;
	}
	public Integer getReturnwareCity() {
		return returnwareCity;
	}
	public void setReturnwareCity(Integer returnwareCity) {
		this.returnwareCity = returnwareCity;
	}
	public Integer getReturnwareCounty() {
		return returnwareCounty;
	}
	public void setReturnwareCounty(Integer returnwareCounty) {
		this.returnwareCounty = returnwareCounty;
	}
	public Integer getReturnwareVillage() {
		return returnwareVillage;
	}
	public void setReturnwareVillage(Integer returnwareVillage) {
		this.returnwareVillage = returnwareVillage;
	}
	public String getReturnwareAddress() {
		return returnwareAddress;
	}
	public void setReturnwareAddress(String returnwareAddress) {
		if(returnwareAddress.length() >500){
			throw new BusinessException("取件街道地址长度大于500");
		}else{
			this.returnwareAddress = returnwareAddress;
		}
	}
}
