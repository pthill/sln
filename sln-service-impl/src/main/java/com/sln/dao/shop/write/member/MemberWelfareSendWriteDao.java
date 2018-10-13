package com.sln.dao.shop.write.member;

import com.sln.entity.member.MemberWelfareSend;

public interface MemberWelfareSendWriteDao {
    Integer insert(MemberWelfareSend memberWelfareSend);
    Integer update(MemberWelfareSend memberWelfareSend);
    Integer del(Integer id);
}
