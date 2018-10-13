package com.sln.dao.shop.write.full;

import org.springframework.stereotype.Repository;

import com.sln.entity.full.ActFull;

@Repository
public interface ActFullWriteDao {

    ActFull get(java.lang.Integer id);

    Integer insert(ActFull actFull);

    Integer update(ActFull actFull);

    /**
     * 只修改活动状态、审核意见、修改者信息
     * @param actFull
     * @return
     */
    Integer updateStatus(ActFull actFull);

    Integer delete(Integer id);
}