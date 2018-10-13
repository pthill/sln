package com.sln.dao.shop.write.full;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.full.ActFullLog;

@Repository
public interface ActFullLogWriteDao {

    ActFullLog get(java.lang.Integer id);

    Integer insert(ActFullLog actFullLog);

    Integer update(ActFullLog actFullLog);

    /**
     * 批量新增订单满减参与日志
     * @param actFullLogList
     * @return
     */
    Integer batchInsertActFullLog(List<ActFullLog> actFullLogList);
}