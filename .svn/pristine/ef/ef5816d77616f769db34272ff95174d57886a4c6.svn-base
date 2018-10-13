package com.sln.dao.shop.write.message;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sln.entity.message.MessageType;

@Repository
public interface MessageTypeWriteDao {
 
	MessageType get(java.lang.Integer id);
	
	Integer insert(MessageType messageType);
	
	Integer update(MessageType messageType);
	
	Integer count(@Param("param1") Map<String, String> queryMap);
	
	List<MessageType> page(@Param("param1") Map<String, String> queryMap, @Param("start") Integer start,
            @Param("size") Integer size);
}