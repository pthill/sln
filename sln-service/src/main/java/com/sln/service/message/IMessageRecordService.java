package com.sln.service.message;

import java.util.List;
import java.util.Map;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageRecord;

public interface IMessageRecordService {

	/**
     * 根据id取得消息发送记录表
     * @param  messageRecordId
     * @return
     */
    ServiceResult<MessageRecord> getMessageRecordById(Integer messageRecordId);
    
    /**
     * 消息分页
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MessageRecord>> page(Map<String, String> queryMap, PagerInfo pager);
    
     /**
      * 发送信息接口
      * @param message
      * @return
      */
     ServiceResult<Boolean> sendMessage(Message message);
     
     /**
      * 设置消息已读
      * @param id
      * @return
      */
     ServiceResult<Boolean> setRead(Integer id);
     
     /**
      * 删除消息
      * @param id
      * @return
      */
     ServiceResult<Boolean> delete(Integer id);
     
     /**
      * 根据消息类型ID和用户ID获取未读数量
      * @param messageTypeId
      * @param memberId
      * @return
      */
     ServiceResult<Integer> getUnreadNumByMessageTypeId(Integer messageTypeId,Integer memberId);
}