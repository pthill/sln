package com.sln.dao.shop.write.jd;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.core.jd.bean.JdCategoryDto;


@Repository
public interface JdCategoryWriteDao {
 
	/**
	 * 批量新增分类数据
	 */
	int batchInsertCategory(@Param("list") List<Map> list);
}