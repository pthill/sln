package com.sln.dao.shop.write.member;

import com.sln.entity.member.MemberSpecialIntegral;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberSpecialIntegralWriteDao {
    Integer insert(MemberSpecialIntegral memberSpecialIntegral);
    Integer update(MemberSpecialIntegral memberSpecialIntegral);
    Integer delete(MemberSpecialIntegral memberSpecialIntegral);
    Integer updateValue(MemberSpecialIntegral memberSpecialIntegral);
}
