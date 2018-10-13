package com.sln.dao.shop.read.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.member.Member;

/**
 * 会员表
 * 
 */
@Repository
public interface MemberReadDao {

    Member get(java.lang.Integer id);

    /**
     * 根据条件获取用户信息
     * @param queryMap
     * @param start
     * @param size
     * @return
     */
    List<Member> getMembers(@Param("queryMap") Map<String, String> queryMap,
                            @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据条件获取用户数量
     * @param queryMap
     * @return
     */
    Integer getMembersCount(@Param("queryMap") Map<String, String> queryMap);

    /**
     * 根据会员注册的月、日获取用户信息
     * @param day 已类似 -03-01 的形式传入月日
     * @param start
     * @param size
     * @return
     */
    List<Member> getMembersByDay(@Param("day") String day, @Param("start") Integer start,
                                 @Param("size") Integer size);

    Integer isMobExists(String mobile);
    
    /**
     * 根据电话号码查询会员信息
     */
    Member getByMobile(@Param("mobile") String mobile);

    /*
     * 获取会员账户和注册时间
     */
	List<Member> getMembersNameAndRegisterTime(@Param("queryMap")Map<String, String> queryMap,
			@Param("start")Integer start,@Param("size")Integer size);
}
