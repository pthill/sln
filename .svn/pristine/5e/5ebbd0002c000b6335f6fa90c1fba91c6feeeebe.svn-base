package com.sln.model.message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.write.message.MessageRecordWriteDao;
import com.sln.dao.shop.write.message.MessageTypeWriteDao;
import com.sln.dao.shop.write.message.MessageWriteDao;
import com.sln.entity.member.Member;
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageRecord;
import com.sln.entity.message.MessageType;

@Component
public class MessageModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(MessageModel.class);
    
    @Resource
    private MessageWriteDao messageWriteDao;
    @Resource
    private MessageRecordWriteDao messageRecordWriteDao;
    @Resource
    private MessageTypeWriteDao messageTypeWriteDao;
    
    
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    /**
     * 根据id取得消息记录表
     * @param  messageId
     * @return
     */
    public Message getMessageById(Integer messageId) {
    	return messageWriteDao.get(messageId);
    }
    
    /**
     * 根据messageCode取得消息记录
     * @param  messageCode
     * @return
     */
    public Message getMessageByMessageCode(String messageCode) {
    	return messageWriteDao.getMessageByMessageCode(messageCode);
    }
    
    /**
     * 保存消息记录表
     * @param  message
     * @return
     */
     public Integer saveMessage(Message message) {
     	this.dbConstrains(message);
     	return messageWriteDao.insert(message);
     }
     
     /**
     * 更新消息记录表
     * @param  message
     * @return
     */
     public Integer updateMessage(Message message) {
     	this.dbConstrains(message);
     	return messageWriteDao.update(message);
     }
     
     private void dbConstrains(Message message) {
		message.setTitle(StringUtil.dbSafeString(message.getTitle(), false, 50));
		message.setContent(StringUtil.dbSafeString(message.getContent(), false, 65535));
     }
     
     public Integer count(Map<String, String> queryMap) {
    	 return messageWriteDao.count(queryMap);
     }
     
     public List<Message> page(Map<String, String> queryMap, PagerInfo pager) {
         Integer start = 0, size = 0;
         if (pager != null) {
             start = pager.getStart();
             size = pager.getPageSize();
         }
         return messageWriteDao.page(queryMap, start, size);
     }
     
     public Integer del(Integer id) {
         return messageWriteDao.delete(id);
     }

    
     public Boolean sendMessageToMember(Map<String, Object> queryMap,Integer receptionId,String messageCode) {
    	 try {
    		 Message message = messageWriteDao.getMessageByMessageCode(messageCode);
        	 if(null == message) {
        		 throw new BusinessException("消息模板有误！"); 
        	 }
        	 if(null == receptionId) {
        		 throw new BusinessException("接收人不能为空！");
        	 }
        	 
        	 MessageType messageType = messageTypeWriteDao.get(message.getMessageTypeId());
        	 
        	 if(null == messageType || messageType.getState() == MessageType.STATE1) {
        		 throw new BusinessException("消息类型错误或不是启用状态");
        	 }
        	 
        	 MessageRecord messageRecord = new MessageRecord(message.getId(),receptionId);
        	 String content = message.getContent();
        	 
        	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        	 
            	 
    		 switch (messageCode) {
	 			case Message.DDQXTZ:	//订单取消通知	
	 				 Date startTime = (Date)queryMap.get("startTime");
	    			 Date endTime = (Date)queryMap.get("endTime");
	    			 content = content.replace("${startTime}", sdf.format(startTime));
	    			 content = content.replace("${endTime}", sdf.format(endTime));
	    			 break;
	 			case Message.QHSPSHTG:   //缺货商品审核通过
	 				content = content.replace("${memberName}", queryMap.get("memberName").toString());
	 				content = content.replace("${productName}", queryMap.get("productName").toString());
	 				content = content.replace("${productCode}", queryMap.get("productCode").toString());
	 				content = content
	 						.replaceAll("${productAddress}", queryMap.get("productAddress").toString());
	 				break;
	 			case Message.QHSPSHSB:   //缺货商品审核失败
	 				content = content.replace("${memberName}", queryMap.get("memberName").toString());
	 				content = content.replace("${productName}", queryMap.get("productName").toString());
	 				content = content.replace("${productCode}", queryMap.get("productCode").toString());
	 				content = content.replace("${retroactionReason}",
	 						queryMap.get("retroactionReason").toString());
	 				break;
	 			case  Message.FLJFFF://福利积分发送
	 				content=content.replace("${staffNo}",queryMap.get("staffNo").toString());
	 				content=content.replace("${name}",queryMap.get("name").toString());
	 				content=content.replace("${costName}",queryMap.get("costName").toString());
	 				content=content.replace("${money}",queryMap.get("money").toString());
	 				break;
    		 }
    		 
    		 if(content.contains("${time}")) {
    			 Date time = (Date)queryMap.get("time");
        		 content = content.replace("${time}", sdf.format(time));
        	 }
    		 if(content.contains("${orderSn}")) {
    			 content = content.replace("${orderSn}", queryMap.get("orderSn").toString());
    		 }
    		 messageRecord.setContent(content);
        	 
        	 return messageRecordWriteDao.insert(messageRecord) > 0;
		} catch (Exception e) {
			log.error("发送消息到用户失败，失败原因"+e.getMessage());
			return false;
		}
    	 
     }
     
}