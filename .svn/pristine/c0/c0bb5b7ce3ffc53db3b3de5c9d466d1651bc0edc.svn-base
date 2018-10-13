package com.sln.core.jd.bean;

import java.io.Serializable;

/**
 * 服务类型实体
 * @author hlq
 *
 */
public class ComponentExport implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/**
	 *  退货(10)
	 */
	public static final String CODE_10 ="10";
	/**
	 *  换货(20)
	 */
	public static final String CODE_20 ="20";
	/**
	 *  维修(30)
	 */
	public static final String CODE_30 ="30";
	
	/**
	 *  上门取件(4)
	 */
	public static final String CODE_4 ="4";
	
	/**
	 *  客户发货(40)
	 */
	public static final String CODE_40 ="40";
	
	/**
	 *  客户送货(7)
	 */
	public static final String CODE_7 ="7";
	
	private String code;  //服务类型码  退货(10)、换货(20)、维修(30)
	private String name;  // 服务类型名称 退货、换货、维修
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
	
	/**
	 * 根据code获取name
	 * @param code
	 * @return
	 */
	public static String getNameByCode(String code){
		if(code != null){
			if(code.equals(CODE_10)){
				return "退货";
			}else if(code.equals(CODE_20)){
				return "换货";
			}else if(code.equals(CODE_30)){
				return "维修";
			}else if(code.equals(CODE_4)){
				return "上门取件";
			}else if(code.equals(CODE_40)){
				return "客户发货";
			}else if(code.equals(CODE_7)){
				return "客户送货";
			}else{
				return "无服务类型";
			}
		}else{
			return null;
		}
	}
	
}
