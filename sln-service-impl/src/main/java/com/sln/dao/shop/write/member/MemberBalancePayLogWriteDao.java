package com.sln.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberBalancePayLog;

@Repository
public interface MemberBalancePayLogWriteDao {

    MemberBalancePayLog get(java.lang.Integer id);

    Integer save(MemberBalancePayLog memberBalancePayLog);

    Integer update(MemberBalancePayLog memberBalancePayLog);

    Integer getCount(Map<String, Object> queryMap);

    List<MemberBalancePayLog> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    MemberBalancePayLog getByOrderSn(String orderNo);

}