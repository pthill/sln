package com.sln.core.jd.bean;

import java.io.Serializable;

import com.sln.core.exception.BusinessException;

/**
 * 取件信息实体类
 * @author hlq
 * 取件信息实体，即原商品如何返回京东或者卖家，如果不为取件方式，默认设置订单中省市县镇信息
 *
 */
public class AfterSalePickwareDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 上门取件
	 */
	public static final  int  PICKWARETYPE_4 = 4; 
	
	/**
	 * 客户送货
	 */
	public static final  int  PICKWARETYPE_7 = 7; 
	
	/**
	 * 客户发货
	 */
	public static final  int  PICKWARETYPE_40 = 40; 
	
	private Integer pickwareType;                //取件方式  4 上门取件、7 客户送货、40客户发货
	private Integer pickwareProvince;            //取件省
	private Integer pickwareCity;                //取件市
	private Integer pickwareCounty;              //取件县
	private Integer pickwareVillage;             //取件乡镇
	private String pickwareAddress;              //取件街道地址 最多500字符
	public Integer getPickwareType() {
		return pickwareType;
	}
	public void setPickwareType(Integer pickwareType) {
		this.pickwareType = pickwareType;
	}
	public Integer getPickwareProvince() {
		return pickwareProvince;
	}
	public void setPickwareProvince(Integer pickwareProvince) {
		this.pickwareProvince = pickwareProvince;
	}
	public Integer getPickwareCity() {
		return pickwareCity;
	}
	public void setPickwareCity(Integer pickwareCity) {
		this.pickwareCity = pickwareCity;
	}
	public Integer getPickwareCounty() {
		return pickwareCounty;
	}
	public void setPickwareCounty(Integer pickwareCounty) {
		this.pickwareCounty = pickwareCounty;
	}
	public Integer getPickwareVillage() {
		return pickwareVillage;
	}
	public void setPickwareVillage(Integer pickwareVillage) {
		this.pickwareVillage = pickwareVillage;
	}
	public String getPickwareAddress() {
		return pickwareAddress;
	}
	public void setPickwareAddress(String pickwareAddress) {
		if(pickwareAddress.length() >500){
			throw new BusinessException("取件街道地址长度大于500");
		}else{
			this.pickwareAddress = pickwareAddress;
		}
	
	}
	
	
}
