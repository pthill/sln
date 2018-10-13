package com.sln.service.message;

import java.util.List;
import java.util.Map;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.message.MessageType;

public interface IMessageTypeService {

	/**
     * 根据id取得消息类型表
     * @param  messageTypeId
     * @return
     */
    ServiceResult<MessageType> getMessageTypeById(Integer messageTypeId);
    
    /**
     * 保存消息类型表
     * @param  messageType
     * @return
     */
     ServiceResult<Integer> saveMessageType(MessageType messageType);
     
     /**
     * 更新消息类型表
     * @param  messageType
     * @return
     */
     ServiceResult<Integer> updateMessageType(MessageType messageType);
     
     /**
      * 消息分页
      * @param queryMap
      * @param pager
      * @return
      */
     ServiceResult<List<MessageType>> page(Map<String, String> queryMap, PagerInfo pager);
}