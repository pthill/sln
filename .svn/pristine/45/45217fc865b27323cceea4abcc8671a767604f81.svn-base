package com.sln.dao.shop.read.integral;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitIntegralReadDao {
	List getIntegralStatistics(@Param("queryMap") Map<String, String> queryMap,@Param("start") int start,@Param("size") int size);
	
	int getIntegralStatisticsCount(@Param("queryMap") Map<String, String> queryMap);
	
	List getIntegralDetail(@Param("queryMap") Map<String, String> queryMap,@Param("start") int start,@Param("size") int size);
	
	int getIntegralDetailCount(@Param("queryMap") Map<String, String> queryMap);
}
