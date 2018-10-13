package com.sln.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sln.core.HttpClientUtil;
import com.sln.core.ServiceResult;
import com.sln.core.TimeUtil;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.core.jd.util.JDApiResult;
import com.sln.core.jd.util.JDConfig;
import com.sln.core.jd.util.JDHttpResult;
import com.sln.core.jd.util.JDMessageType;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductService;

/**
 * 京东消息推送监控
 * @author li.biao
 *
 */
public class JDMessageMonitor {
	
	private static Logger logger = Logger.getLogger(JDMessageMonitor.class);
	private static Logger jdLogger = Logger.getLogger("admin.jd.message");
	
	@Resource
	private IProductService productService ;
	
	@Resource
	private IOrdersService ordersService ;
	
	/**
	 * 监控商品相关消息
	 */
	public void monitorProduct(){
		
		try{
			
			//监控的消息类型
			StringBuffer messageType = new StringBuffer();
			messageType.append(JDMessageType.PRODUCT_PRICE).append(",");
			messageType.append(JDMessageType.PRODUCT_STATE).append(",");
			messageType.append(JDMessageType.PRODUCT_UPDATE).append(",");
			messageType.append(JDMessageType.PRODUCT_PARAM);
			
			//获取token
			AccessToken token = JdApi.getAccessToken().getResult();
			
			//请求接口参数
			StringBuffer param = new StringBuffer();
			param.append("token=").append(token.getAccess_token());
			param.append("&").append("type=").append(messageType.toString());
			
			//请求接口，获得返回数据
			String requestResult = HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_URL, param.toString());
			//解析调用结果
			JDApiResult<List<JDMessage>> apiResult = JDHttpResult.parseToList(requestResult, JDMessage.class);
			
			//操作成功，处理数据
			if(apiResult.isSuccess() && JDHttpResult.Succ.CODE_0000.equals(apiResult.getCode())){
				if (apiResult.getResult()!=null && apiResult.getResult().size() > 0 ) {
					//将Message归类，分别处理
					List<JDMessage> messageList = apiResult.getResult();
					Map<Integer,List<JDMessage>> messageMap = new HashMap<Integer,List<JDMessage>>();
					for(JDMessage message : messageList){
						if(messageMap.containsKey(message.getType())){
							List<JDMessage> oldData = messageMap.get(message.getType());
							oldData.add(message);
							messageMap.put(message.getType(), oldData);// ? 不重新put，map里面的list对象会更新吗？
						}else{
							List<JDMessage> newData = new ArrayList<JDMessage>();
							newData.add(message);
							messageMap.put(message.getType(), newData);
						}
					}
					
					if(messageMap.keySet().size() > 0){
						//1.调用服务 ===============
						ServiceResult<Boolean> handleResult = productService.handleJDProductMessage(token,messageMap);
						//2.处理成功后，删除京东队列中的推送消息
						if(handleResult!=null && handleResult.getResult()){
							for(JDMessage message : messageList){
								try{
									StringBuffer delParam = new StringBuffer();
									delParam.append("token=").append(token.getAccess_token());
									delParam.append("&").append("id=").append(message.getId());
									HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_DEL_URL, delParam.toString());
								}catch(Exception ex){//单独做异常处理，如有异常不中断程序
									ex.printStackTrace();
									logger.info("[JDMessageMonitor][monitorProduct]删除京东消息异常："+ex.getMessage()+";messageId:"+message.getId());
								}
							}
						}
					}
					//3.将取到的消息记录到日志文件===============
					if(jdLogger!=null){
						jdLogger.info("[JDMessageMonitor][monitorProduct]京东推送处理完时间："+TimeUtil.getCurrentTime());
						jdLogger.info(JSONArray.toJSONString(messageList));
					}
				}
			}else{
				//操作失败
				logger.error("[JDMessageMonitor][monitorProduct]失败：[code="+apiResult.getCode()+"][message="+apiResult.getMessage()+"]");
			}
		
		}catch(Exception ex){
			ex.printStackTrace();
			logger.error("[JDMessageMonitor][monitorProduct]异常："+ex.getMessage());
		}
		
	}
	
	/**
	 * 监控订单相关消息
	 */
	public void monitorOrder(){
		//监控的消息类型
		StringBuffer messageType = new StringBuffer();
		messageType.append(JDMessageType.ORDER_SPLIT).append(",");
		messageType.append(JDMessageType.ORDER_CANCEL).append(",");
		messageType.append(JDMessageType.ORDER_AUTO_CANCEL);
		
		//获取token
		AccessToken token = JdApi.getAccessToken().getResult();
		
		//请求接口参数
		StringBuffer param = new StringBuffer();
		param.append("token=").append(token.getAccess_token());
		param.append("&").append("type=").append(messageType.toString());
		
		//请求接口，获得返回数据
		String requestResult = HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_URL, param.toString());
		//解析调用结果
		JDApiResult<List<JDMessage>> apiResult = JDHttpResult.parseToList(requestResult, JDMessage.class);
		
		//操作成功，处理数据
		if(apiResult.isSuccess() && JDHttpResult.Succ.CODE_0000.equals(apiResult.getCode())){
			if (apiResult.getResult()!=null && apiResult.getResult().size() > 0 ) {
				//将Message归类，分别处理
				List<JDMessage> messageList = apiResult.getResult();
				Map<Integer,List<JDMessage>> messageMap = new HashMap<>();
				for(JDMessage message : messageList){
					if(messageMap.containsKey(message.getType())){
						List<JDMessage> oldData = messageMap.get(message.getType());
						oldData.add(message);
						messageMap.put(message.getType(), oldData);// ? 不重新put，map里面的list对象会更新吗？
					}else{
						List<JDMessage> newData = new ArrayList<JDMessage>();
						newData.add(message);
						messageMap.put(message.getType(), newData);
					}
				}
				
				if(messageMap.keySet().size() > 0){
					//1.调用服务
					ServiceResult<Boolean> handleResult = ordersService.handleJDOrderMessage(token, messageMap) ;
					//2.处理成功后，删除京东队列中的推送消息
					if(handleResult!=null && handleResult.getResult()){
						for(JDMessage message : messageList){
							try{
								StringBuffer delParam = new StringBuffer();
								delParam.append("token=").append(token.getAccess_token());
								delParam.append("&").append("id=").append(message.getId());
								HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_DEL_URL, delParam.toString());
							}catch(Exception ex){//单独做异常处理，如有异常不中断程序
								ex.printStackTrace();
								logger.info("[JDMessageMonitor][monitorOrder]删除京东消息异常："+ex.getMessage()+";messageId:"+message.getId());
							}
						}
					}
				}
				
				//3.将取到的消息记录到日志文件===============
				if(jdLogger!=null){
					jdLogger.info("[JDMessageMonitor][monitorOrder]京东推送处理完时间："+TimeUtil.getCurrentTime());
					jdLogger.info(JSONArray.toJSONString(messageList));
				}
				
			}
		}else{
			//操作失败
			logger.error("[JDMessageMonitor][monitorOrder]失败：[code="+apiResult.getCode()+"][message="+apiResult.getMessage()+"]");
		}
	}
	
	/**
	 * 监控支付相关消息
	 */
	public void monitorPay(){
		//监控的消息类型
		StringBuffer messageType = new StringBuffer();
		messageType.append(JDMessageType.PAY_FAIL);
		
		//获取token
		AccessToken token = JdApi.getAccessToken().getResult();
		
		//请求接口参数
		StringBuffer param = new StringBuffer();
		param.append("token=").append(token.getAccess_token());
		param.append("&").append("type=").append(messageType.toString());
		
		//请求接口，获得返回数据
		String requestResult = HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_URL, param.toString());
		//解析调用结果
		JDApiResult<List<JDMessage>> apiResult = JDHttpResult.parseToList(requestResult, JDMessage.class);
		
		//操作成功，处理数据
		if(apiResult.isSuccess() && JDHttpResult.Succ.CODE_0000.equals(apiResult.getCode())){
			if (apiResult.getResult()!=null && apiResult.getResult().size() > 0 ) {
				List<JDMessage> messageList = apiResult.getResult();
				for(JDMessage message : messageList){
					JSONObject resultObj = message.getResult() ;
					if(resultObj == null || StringUtils.isEmpty(resultObj.getString("orderId"))){
						continue ;
					}
					//重新发起支付
					String jdOrderId = resultObj.getString("orderId");
					StringBuffer payParam = new StringBuffer();
					payParam.append("token=").append(token.getAccess_token());
					payParam.append("&").append("jdOrderId=").append(jdOrderId);
					String doPayResult = HttpClientUtil.httpPostRequest(JDConfig.DOPAY_URL, payParam.toString());
					//解析发起支付回调结果
					JDApiResult<Map> doResult = JDHttpResult.parse(doPayResult, Map.class);
					if(doResult != null && doResult.isSuccess()){
						try{
							//支付成功，删除京东队列中的推送消息
							StringBuffer delParam = new StringBuffer();
							delParam.append("token=").append(token.getAccess_token());
							delParam.append("&").append("id=").append(message.getId());
							HttpClientUtil.httpPostRequest(JDConfig.MESSAGE_DEL_URL, delParam.toString());
						}catch(Exception ex){//单独做异常处理，如有异常不中断程序
							ex.printStackTrace();
							logger.info("[JDMessageMonitor][monitorPay]删除京东消息异常："+ex.getMessage()+";messageId:"+message.getId());
						}
					}//京东那边因为某种原因支付失败的时候，不处理，等下一次队列中获取到消息再处理
				}
				
				//3.将取到的消息记录到日志文件===============
				if(jdLogger!=null){
					jdLogger.info("[JDMessageMonitor][monitorPay]京东推送处理完时间："+TimeUtil.getCurrentTime());
					jdLogger.info(JSONArray.toJSONString(messageList));
				}
			}
		}else{
			//操作失败
			logger.error("[JDMessageMonitor][monitorPay]失败：[code="+apiResult.getCode()+"][message="+apiResult.getMessage()+"]");
		}		
		
	}
	
	public static void main(String[] args) {
		//JDMessageMonitor.monitorProduct();
		//https://bizapi.jd.com/api/product/getPageNum
		//获取token
		String access_token = JdApi.getAccessToken().getResult().getAccess_token();
		
		//请求接口参数
		StringBuffer param = new StringBuffer();
		param.append("token=").append(access_token);
		param.append("pageNum=").append("1");
		
		//请求接口，获得返回数据
		String requestResult = HttpClientUtil.httpPostRequest("https://bizapi.jd.com/api/product/getSku", param.toString());
		System.out.println(requestResult);
	}
}
