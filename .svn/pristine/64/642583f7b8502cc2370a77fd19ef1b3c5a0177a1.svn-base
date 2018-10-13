package com.sln.dao.shop.write.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberProductBack;

@Repository
public interface MemberProductBackWriteDao {

    MemberProductBack get(java.lang.Integer id);

    Integer save(MemberProductBack memberProductBack);

    Integer update(MemberProductBack memberProductBack);

    Integer JobUpdate(MemberProductBack memberProductBack);

    Integer del(Integer id);

    Integer queryCount(Map<String, Object> map);

    List<MemberProductBack> queryList(Map<String, Object> map);

    Integer upStateReturn(@Param("id") Integer id,@Param("state") Integer state);
}
