package com.sln.dao.shop.write.operate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.operate.SystemNotice;

@Repository
public interface SystemNoticeWriteDao {

    SystemNotice get(java.lang.Integer id);

    Integer save(SystemNotice systemNotice);

    Integer update(SystemNotice systemNotice);

    Integer getCount(Map<String, Object> queryMap);

    List<SystemNotice> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    List<SystemNotice> getUnreadNotice(Map<String, Object> param);

    Integer getUnreadNoticeCount(Integer sellerId);

}