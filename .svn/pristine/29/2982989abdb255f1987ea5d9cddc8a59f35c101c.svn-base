package com.sln.dao.shop.write.jd;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.jd.JdCommoditypool;

@Repository
public interface JdCommoditypoolWriteDao {
 
	/**
	 * 插入数据
	 * @param jdCommoditypool
	 * @return
	 */
	int batchInsert(List<Map> list);
	
	/**
	 * 批量插入零时sku表
	 */
	int batchInsertSku(Object[] skus);
	
	/**
	 * 批量修改临时sku表状态
	 */
	
	int updateStateBySku(@Param("state") Integer state,@Param("list") List list);
	
	/**
	 * 对临时sku表去重处理
	 */
	int duplicateRemoval();
}