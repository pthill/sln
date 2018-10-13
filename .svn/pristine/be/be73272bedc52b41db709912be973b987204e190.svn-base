package com.sln.model.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.PagerInfo;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.write.member.MemberWriteDao;
import com.sln.dao.shop.write.message.MessageRecordWriteDao;
import com.sln.dao.shop.write.message.MessageTypeWriteDao;
import com.sln.dao.shop.write.message.MessageWriteDao;
import com.sln.entity.member.Member;
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageRecord;
import com.sln.entity.message.MessageType;
import com.sln.entity.seller.Seller;

@Component
public class MessageRecordModel {

	private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
                                                   .getLogger(MessageRecordModel.class);
	@Resource
	private MessageWriteDao messageWriteDao;
	@Resource
	private MessageTypeWriteDao messageTypeWriteDao;
    @Resource
    private MessageRecordWriteDao messageRecordWriteDao;
    @Resource
    private MemberWriteDao memberWriteDao;
    @Resource
    private SellerReadDao sellerReadDao;
    
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    
    /**
     * 根据id取得消息发送记录表
     * @param  messageRecordId
     * @return
     */
    public MessageRecord getMessageRecordById(Integer messageRecordId) {
    	return messageRecordWriteDao.get(messageRecordId);
    }
    
    public Integer count(Map<String, String> queryMap) {
   	 return messageRecordWriteDao.count(queryMap);
    }
    
    public List<MessageRecord> page(Map<String, String> queryMap, PagerInfo pager) {
        Integer start = 0, size = 0;
        if (pager != null) {
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<MessageRecord> list = messageRecordWriteDao.page(queryMap, start, size);
        for(MessageRecord message:list) {
        	if(message.getReceptionType() == MessageType.RECEPTION_TYPE1) {
        		message.setReceptionName(memberWriteDao.get(message.getReceptionId()).getName());
        	}
        }
        return list;
    }
    
    public Boolean sendMessage(Message message) {
    	DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
        	message = messageWriteDao.get(message.getId());
        	MessageType messageType = messageTypeWriteDao.get(message.getMessageTypeId());
        	if(message.getState() == Message.STATE1) {
        		
        		throw new BusinessException("该消息已经发送过了,请勿重复发送！");
        		
        	}
        	if(messageType.getReceptionType() == MessageType.RECEPTION_TYPE1) {			//发送消息到用户
        		
        		sendMessageToMember(message);
        		
        	}else if(messageType.getReceptionType() == MessageType.RECEPTION_TYPE2) {	//发送消息到商户
        		
        		sendMessageToSeller(message);
        		
        	}else if(messageType.getReceptionType() == MessageType.RECEPTION_TYPE3){	//发送消息到供应商
        		
        		sendMessageToSupplier(message);
        		
        	}
        	Message newmessage = new Message();
        	newmessage.setId(message.getId());
        	newmessage.setState(Message.STATE1);
        	messageWriteDao.update(newmessage);
        	transactionManager.commit(status);
        }catch (Exception e) {
        	log.error("发送消息时出现未知异常：",e);
        	transactionManager.rollback(status);
		} 
    	return false;
    } 
    
    
    /**
     * 发送消息到所有用户
     * @param message
     */
    public void sendMessageToMember(Message message) {
   	 Map<String, String> queryMap = new HashMap<String,String>();
   	 queryMap.put("q_status", Member.STATUS_1_ON+"");
   	 Integer memberCount = memberWriteDao.getMembersCount(queryMap);
   	 Integer start = 0, size = 200;
   	 while(memberCount > size) {
   		 List<MessageRecord> mList = new ArrayList<MessageRecord>();
   		 List<Member> memberList = memberWriteDao.getMembers(queryMap, start, size);
   		 for(Member member:memberList) {
   			MessageRecord messager = new MessageRecord(message.getId(),member.getId());
   			messager.setContent(message.getContent());
   			mList.add(messager);
   		 }
   		messageRecordWriteDao.batchSendMessage(mList);
   		 start = start + 200;
   		 size  = size  + 200;
   	 } 
   	 
   	 	List<MessageRecord> mList = new ArrayList<MessageRecord>();
		List<Member> memberList = memberWriteDao.getMembers(queryMap, start, memberCount);
		for(Member member:memberList) {
			MessageRecord messager = new MessageRecord(message.getId(),member.getId());
			messager.setContent(message.getContent());
			mList.add(messager);
		}
		messageRecordWriteDao.batchSendMessage(mList);
   	 
    }
    /**
     * 发送消息到商户
     * @param message
     */
    public void sendMessageToSeller(Message message) {
    	List<MessageRecord> mList = new ArrayList<MessageRecord>();
    	
    	//获取所有审核通过的商家 发送消息
    	Map<String, String> queryMap = new HashMap<String,String>();
    	queryMap.put("q_auditStatus", String.valueOf(Seller.AUDIT_STATE_2_DONE));
    	
    	List<Seller> sellerList = sellerReadDao.getSellers(queryMap, null, null);
    	
    	for(Seller seller : sellerList) {
    		MessageRecord messager = new MessageRecord(message.getId(),seller.getId());
			messager.setContent(message.getContent());
			mList.add(messager);
    	}
    	int result = messageRecordWriteDao.batchSendMessage(mList);
    	System.out.println(result);
    	
    }
    /**
     * 发送消息到供应商
     * @param message
     */
    public void sendMessageToSupplier(Message message) {
   	 
    }
    
    public boolean setRead(Integer id) {
    	MessageRecord messageRecord = new MessageRecord();
    	messageRecord.setId(id);
    	messageRecord.setIsRead(messageRecord.ISREAD1);
    	return messageRecordWriteDao.update(messageRecord) > 0;
    }
    
    public boolean delete(Integer id) {
    	MessageRecord messageRecord = new MessageRecord();
    	messageRecord.setId(id);
    	messageRecord.setIsDel(messageRecord.ISREAD1);
    	return messageRecordWriteDao.update(messageRecord) > 0;
    }
    
    /**
     * 根据消息类型ID和用户ID获取未读数量
     * @param messageTypeId
     * @param memberId
     * @return
     */
    public Integer getUnreadNumByMessageTypeId(Integer messageTypeId,Integer memberId) {
    	return messageRecordWriteDao.getUnreadNumByMessageTypeId(messageTypeId,memberId);
    }
}