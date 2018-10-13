package com.sln.dao.shop.read.operate;

import com.sln.entity.operate.Park;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ParkReadDao {
 
	List<Park> getPage(@Param("queryMap") Map<String, String> queryMap,@Param("size") Integer size,@Param("start") Integer start);
	
	Integer getPageCount(@Param("queryMap") Map<String, String> queryMap);

	List<Park> getParkList();

	Integer countName(@Param("name")String name,@Param("id") Integer id);

	Integer countCode(@Param("code")String code,@Param("id")Integer id);

	Park getParkById(Integer id);

	List<Park> getOperationsGroupByParkId();

	List<Park>getParkByArea(String area);

	List<String> getArea();
}