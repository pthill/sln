package com.sln.service.member;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.MemberWelfareSend;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface IMemberWelfareSendService {

    /**
     * 根据id取得member_welfare_send对象
     * @param
     * @return
     */
    ServiceResult<MemberWelfareSend> getMemberWelfareSendById(Integer id);

    /**
     * 保存member_welfare_send对象
     * @param  file
     * @return
     */
    ServiceResult<Boolean> saveMemberWelfareSend(MultipartFile file,Integer userId,Integer id,Integer sellerId);



    ServiceResult<Boolean> sendWelfareSend(String ip,Integer id);


    ServiceResult<Integer> del(Integer id);

    ServiceResult<List<MemberWelfareSend>> queryPage(Map<String, String> queryMap, PagerInfo pager);
}
