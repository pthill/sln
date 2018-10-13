package com.sln.dao.shop.write.operate;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.operate.NoticeClickSituation;

@Repository
public interface NoticeClickSituationWriteDao {

    NoticeClickSituation get(java.lang.Integer id);

    Integer save(NoticeClickSituation noticeClickSituation);

    Integer update(NoticeClickSituation noticeClickSituation);

    Integer getCount(Map<String, Object> queryMap);

    List<NoticeClickSituation> page(Map<String, Object> queryMap);

    Integer del(Integer id);

}