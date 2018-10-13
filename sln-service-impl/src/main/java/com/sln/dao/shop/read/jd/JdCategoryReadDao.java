package com.sln.dao.shop.read.jd;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.jd.JdCategory;


@Repository
public interface JdCategoryReadDao {
 
	/**
	 * 获取未同步到平台的分类信息-
	 */
	List<JdCategory> getUnCategory();
	
	List<JdCategory> getErrorParentId(@Param("catClass") Integer catClass);
	
	/**
	 * 查询京东商品分类列表总行数
	 * @param queryMap
	 * @return
	 */
	Integer getCount(@Param("queryMap") Map<String, Object> queryMap);
	
	/**
	 * 查询京东商品分类列表
	 * @param map
	 * @return
	 */
	List<JdCategory> queryList(@Param("queryMap") Map<String, Object> map,@Param("start") Integer start, @Param("size") Integer size);
	
	/**
     * 根据pid获取分类列表
     * @param pid
     * @return
     */
    List<JdCategory> getByPid(Integer pid);
    
    /**
     * 根据京东分类id获取分类信息
     * @param catId
     * @return
     */
    JdCategory getByCatId(Integer catId);
}