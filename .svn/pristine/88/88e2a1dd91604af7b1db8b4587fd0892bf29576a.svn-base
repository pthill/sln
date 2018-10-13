package com.sln.dao.shop.read.compain;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sln.entity.compain.ComplainRegister;

public interface ComplainRegisterReadDao {

	List<ComplainRegister> getSellEliminate(@Param("queryMap")Map<String, String> queryMap,@Param("start")Integer start,@Param("size")Integer size);
    //获取总条数
	int getSellersCount(Map<String, String> queryMap);
	//定时获取总条数
	Integer getCountBySellerId(Integer sellerId);
}
