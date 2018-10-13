package com.sln.dao.shop.write.member;

import com.sln.entity.member.WelfareCompany;
import org.springframework.stereotype.Repository;

@Repository
public interface WelfareCompanyWriteDao {
    Integer insert(WelfareCompany welfareCompany);
    Integer delete(Integer id);
    Integer update(WelfareCompany welfareCompany);
}
