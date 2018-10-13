package com.sln.model.member;

import com.sln.dao.shop.read.member.MemberWelfareSendDetailReadDao;
import com.sln.dao.shop.write.member.MemberWelfareSendDetailWriteDao;
import com.sln.entity.member.MemberWelfareSendDetail;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class MemberWelfareSendDetailModel {
    @Resource
    private MemberWelfareSendDetailReadDao memberWelfareSendDetailReadDao;
    @Resource
    private MemberWelfareSendDetailWriteDao memberWelfareSendDetailWriteDao;



    public Integer del(Integer id){
        return  memberWelfareSendDetailWriteDao.del(id);
    }

    public List<MemberWelfareSendDetail> getByWelfareSendId(Integer id,Integer start,Integer size){
        return memberWelfareSendDetailReadDao.page(id,size,start);
    }

    public Integer count(Integer id){
        return memberWelfareSendDetailReadDao.pageCount(id);
    }
}
