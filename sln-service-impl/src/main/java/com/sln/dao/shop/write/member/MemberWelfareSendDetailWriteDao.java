package com.sln.dao.shop.write.member;

import com.sln.entity.member.MemberWelfareSendDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberWelfareSendDetailWriteDao {

    //Integer insert(@Param("list") List<MemberWelfareSendDetail> list);
    Integer insert(MemberWelfareSendDetail memberWelfareSendDetail);

    Integer del(@Param("welfareSendId")Integer welfareSendId);

}
