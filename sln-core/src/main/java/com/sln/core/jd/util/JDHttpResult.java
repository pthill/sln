package com.sln.core.jd.util;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 京东接口调用结果及错误码统一处理
 * @author li.biao
 * @date 2017-12-18
 */
public class JDHttpResult {
	
	/**
	 * 0*** 成功
	 */
	public static class Succ{
		/** 操作成功  */
		public static final String CODE_0000 = "0000" ;
		/** 下单成功  */
		public static final String CODE_0001 = "0001";
		/** 取消订单成功 */
		public static final String CODE_0002 = "0002";
		/** 确认订单成功 */
		public static final String CODE_0003 = "0003";
		/** 重复提交  <li>同一三方订单号已经存在有效订单。此时下单结果 result会返回该三方订单号对应订单信息。</li>*/
		public static final String CODE_0008 = "0008";
		/** 返回数据为空 */
		public static final String CODE_0010 = "0010";
	}
	
	/**
	 * 1*** 参数问题
	 */
	public static class Fail_Param{
		/** 参数为空 <li>通用，请检查参数</li> */
		public static final String CODE_1001 = "1001";
		/** 参数格式不正确<li>通用，请检查参数</li> */
		public static final String CODE_1002 = "1002";
		/** 参数值不正确 <li>通用，请检查参数</li>*/
		public static final String CODE_1003 = "1003";
		/** 参数重复<li>通用，请检查参数</li> */
		public static final String CODE_1004 = "1004";
		/** 入参转化错误<li>通用，请检查参数</li> */
		public static final String CODE_1005 = "1005";
	}
	
	/**
	 *	2*** 权限问题 
	 */
	public static class Fail_Scope{
		/** 用户权限不足 <li>通用，请联系业务人员咨询对应权限问题</li> */
		public static final String CODE_2001 = "2001";
		/** token已过期 <li>请重新刷新或者获取token</li> */
		public static final String CODE_2007 = "2007";
		
	}
	
	/**
	 * 3*** 业务问题
	 */
	public static class Fail_Buss{
		//31** 下单业务问题
		/** 价格不存在 */
		public static final String CODE_3001 = "3001";
		/** 提交订单过快<li>1分钟后提交订单</li> */
		public static final String CODE_3002 = "3002";
		
		//3051开始的为下游接口异常
		/** 价格获取失败<li>接口调用失败，可重试</li> */
		public static final String CODE_3051 = "3051";
		/** 主数据接口业务异常<li>接口调用失败，可重试</li> */
		public static final String CODE_3052 = "3052";
		
		//31** 确认订单业务问题
		/** 确认下单最终失败，请重新确认订单<li>可重新确认</li> */
		public static final String CODE_3101 = "3101";
		/** jdOrderId不存在 */
		public static final String CODE_3102 = "3102";
		
		//32** 取消订单业务问题
		/** 取消订单失败，请重新取消订单<li>可重新取消</li> */
		public static final String CODE_3201 = "3201";
		/** jdOrderId不存在 */
		public static final String CODE_3202 = "3202";
		
		//34** 其余接口问题
		/** 订单不存在<li>没查询到对应订单</li> */
		public static final String CODE_3401 = "3401";
		/** 订单配送信息不存在 */
		public static final String CODE_3402 = "3402";
		
	}
	
	/**
	 *	5*** 系统异常
	 */
	public static class Fail_Exception{
		/** 服务异常，请稍后重试<li>可重试</li> */
		public static final String CODE_5001 = "5001";
		/** 未知错误  */
		public static final String CODE_5002 = "5002";
	}
	
	
	/**
	 * 解析京东接口回调结果，解析字符串成对象
	 * @param <T> 解析成的对象
	 * @param needparse 需解析的json格式的字符串
	 * @return 单个Bean对象
	 */
	public static <T> JDApiResult<T> parse(String needparse,Class<T> c){
		JDApiResult<T> result = new JDApiResult<T>();
		if(needparse == null || "".equals(needparse)){
			return result;
		}
		JSONObject jsonobj = JSON.parseObject(needparse);
		result.setSuccess(Boolean.valueOf(jsonobj.getString("success")));	//是否成功
		result.setCode(jsonobj.getString("resultCode"));					//接口错误编码
		result.setMessage(jsonobj.getString("resultMessage"));				//调用信息
		String res = jsonobj.getString("result");							//调用结果
		if(res!=null && !"".equals(res)){
			if(!res.startsWith("{") && !res.startsWith("[")){
				result.setResult((T)res);
			}else{
				result.setResult(JSONObject.toJavaObject(JSON.parseObject(res), c));
			}
			
		}
		return result ;
	}
	
	/**
	 * 解析京东接口回调结果，解析字符串成对象
	 * @param <T> 解析成的对象
	 * @param needparse 需解析的json格式的字符串
	 * @return 集合Bean对象
	 */
	public static <T> JDApiResult<List<T>> parseToList(String needparse,Class<T> c){
		JDApiResult<List<T>> result = new JDApiResult<List<T>>();
		if(needparse == null || "".equals(needparse)){
			return result;
		}
		List<T> list = new ArrayList<T>();
		JSONObject jsonobj = JSON.parseObject(needparse);
		result.setSuccess(Boolean.valueOf(jsonobj.getString("success")));	//是否成功
		result.setCode(jsonobj.getString("resultCode"));					//接口错误编码
		result.setMessage(jsonobj.getString("resultMessage"));				//调用信息
		String res = jsonobj.getString("result");							//调用结果
		if(res!=null && !"".equals(res)){
			if(res.startsWith("{")){//jsonObject对象
				list.add(JSONObject.toJavaObject(JSON.parseObject(res), c));
				result.setResult(list);
			}else if(res.startsWith("[")){//jsonArray对象
				JSONArray array = JSON.parseArray(res);
				for(int i = 0 ; i < array.size() ; i ++){
					list.add(JSONObject.toJavaObject(array.getJSONObject(i),c));
				}
				result.setResult(list);
			}else{
				
			}
		}
		return result ;
	}
	
	
	
}
