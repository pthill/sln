package com.sln.dao.shop.write.compain;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.compain.ComplainRegister;


@Repository
public interface ComplainRegisterWriteDao {
 
	ComplainRegister get(java.lang.Integer id);
	
	Integer insert(ComplainRegister complainRegister);
	
	Integer update(ComplainRegister complainRegister);

	//删除操作
	Boolean deleteById(@Param("id")Integer id);
}