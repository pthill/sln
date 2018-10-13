package com.sln.model.message;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import com.sln.core.ConvertUtil;
import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.dao.shop.write.message.MessageTypeWriteDao;
import com.sln.entity.message.MessageType;
import com.sln.util.RedisClient;

import redis.clients.jedis.Jedis;

@Component
public class MessageTypeModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(MessageTypeModel.class);
    
    @Resource
    private MessageTypeWriteDao messageTypeWriteDao;
    @Resource
    private RedisClient              redisClient;
    @Resource
    private MessageRecordModel		 messageRecordModel;
    
    /**
     * 根据id取得消息类型表
     * @param  messageTypeId
     * @return
     */
    public MessageType getMessageTypeById(Integer messageTypeId) {
    	return messageTypeWriteDao.get(messageTypeId);
    }
    
    /**
     * 保存消息类型表
     * @param  messageType
     * @return
     */
     public Integer saveMessageType(MessageType messageType) {
     	this.dbConstrains(messageType);
     	Integer  result = messageTypeWriteDao.insert(messageType);
     	redisClient.del("message_type_page");
     	return result;
     }
     
     /**
     * 更新消息类型表
     * @param  messageType
     * @return
     */
     public Integer updateMessageType(MessageType messageType) {
     	Integer result = messageTypeWriteDao.update(messageType);
     	redisClient.del("message_type_page");
     	return result;
     }
     
     private void dbConstrains(MessageType messageType) {
		messageType.setTypeName(StringUtil.dbSafeString(messageType.getTypeName(), false, 20));
     }
     
     public Integer count(Map<String, String> queryMap) {
    	 return messageTypeWriteDao.count(queryMap);
     }
     
     public List<MessageType> page(Map<String, String> queryMap, PagerInfo pager) {
    	 boolean isFont = true;
         Integer start = null, size = null;
         if (pager != null) {
             start = pager.getStart();
             size = pager.getPageSize();
             isFont = false;
         }
         List<MessageType> typeList  = messageTypeWriteDao.page(queryMap, start, size);
         /*if(isFont) {
        	 List<MessageType> list = redisClient.getBeanList("message_type_page", MessageType.class);
	         if(null == list && list.size() <= 0) {
	        	 typeList  = messageTypeWriteDao.page(queryMap, start, size);
	        	 redisClient.set("message_type_page", typeList);
	        	 
	         }else {
	        	 typeList = redisClient.getBeanList("message_type_page", MessageType.class);
	         }
         }else {
        	 typeList = messageTypeWriteDao.page(queryMap, start, size);
         }*/
         // 获取消息类型下 该用户未读消息的数量
         if(null != typeList && null != queryMap.get("memberId")) {
        	 Integer memberId = ConvertUtil.toInt(queryMap.get("memberId"), null);
        	 for(MessageType message:typeList) {
        		 message.setUnreadNum(messageRecordModel.getUnreadNumByMessageTypeId(message.getId(), memberId));
             }
         }
         return typeList;
     }
}