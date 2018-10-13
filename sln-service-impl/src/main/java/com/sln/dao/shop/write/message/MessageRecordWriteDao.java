package com.sln.dao.shop.write.message;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sln.entity.message.MessageRecord;

@Repository
public interface MessageRecordWriteDao {
	
	MessageRecord get(java.lang.Integer id);
	
	Integer insert(MessageRecord messageRecord);
	
	Integer update(MessageRecord messageRecord);
	
	Integer batchSendMessage(List<MessageRecord> list);
	
	Integer count(@Param("param1") Map<String, String> queryMap);
	
	List<MessageRecord> page(@Param("param1") Map<String, String> queryMap, @Param("start") Integer start,
            @Param("size") Integer size);
	
	Integer getUnreadNumByMessageTypeId(@Param("messageTypeId") Integer messageTypeId,@Param("memberId") Integer memberId);
}