package com.sln.dao.shop.write.message;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.sln.entity.message.Message;

@Repository
public interface MessageWriteDao {
 
	Message get(java.lang.Integer id);
	
	Message getMessageByMessageCode(@Param("messageCode") String messageCode);
	
	Integer insert(Message message);
	
	Integer update(Message message);
	
	Integer delete(@Param("id") Integer id);
	
	Integer count(@Param("param1") Map<String, String> queryMap);
	
	List<Message> page(@Param("param1") Map<String, String> queryMap, @Param("start") Integer start,
            @Param("size") Integer size);
}