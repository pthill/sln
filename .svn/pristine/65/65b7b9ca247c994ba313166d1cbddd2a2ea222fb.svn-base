package com.sln.service.jd;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.entity.jd.JdCategory;


public interface IJdCategoryService {

	/**
	 * 批量插入JD分类信息
	 * @param token
	 * @return
	 */
	ServiceResult<Integer> batchInsertCategory(AccessToken token);
	
	/**
	 * 根据pid获取分类列表
	 * @param pid
	 * @return
	 */
	ServiceResult<List<JdCategory>> getByPid(Integer pid);
	
	/**
     * 查询京东商品分类列表
     * @param  map
     * @return
     */
	ServiceResult<List<JdCategory>> getJdCategoryList(Map<String, Object> queryMap, PagerInfo pager);
	
	/**
	 * 根据京东分类id取得分类信息
	 * @param catId
	 * @return
	 */
    ServiceResult<JdCategory> getByCatId(Integer catId);
}