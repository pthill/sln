package com.sln.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sln.entity.member.Member;
import com.sln.entity.member.MemberWxsign;

@Repository
public interface MemberWxsignWriteDao {

    MemberWxsign get(java.lang.Integer id);

    Integer save(MemberWxsign memberWxsign);

    Integer update(MemberWxsign memberWxsign);

    Integer getCount(Map<String, Object> queryMap);

    List<MemberWxsign> page(Map<String, Object> queryMap);

    Integer del(Integer id);

    Member getByOpenId(String openid);

}