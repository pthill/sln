package com.sln.service.impl.member;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberWelfareSend;
import com.sln.entity.portal.PortalActive;
import com.sln.model.member.MemberWelfareSendModel;
import com.sln.service.member.IMemberWelfareSendService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class MemberWelfareSendServiceImpl implements IMemberWelfareSendService {
    private static Logger log = LogManager.getLogger(MemberWelfareSendServiceImpl.class);

    @Resource
    private MemberWelfareSendModel memberWelfareSendModel;


    @Override
    public ServiceResult<MemberWelfareSend> getMemberWelfareSendById(Integer id) {
        ServiceResult<MemberWelfareSend> serviceResult = new ServiceResult<>();
        try {
            serviceResult.setResult(memberWelfareSendModel.getById(id));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IMemberWelfareSendService][getMemberWelfareSendById]根据id[" + id + "]取得福利积分发送表时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMemberWelfareSendService][getMemberWelfareSendById]根据id[" + id + "]取得福利积分时出现未知异常：", e);
        }
        return serviceResult;
    }


    @Override
    public ServiceResult<Boolean> saveMemberWelfareSend(MultipartFile file,Integer userId,Integer id,Integer sellerId) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberWelfareSendModel.save(file,userId,id,sellerId));
            result.setMessage("新增成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[memberWelfareSendModel][saveMemberWelfareSend]新增member_welfare_send对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[memberWelfareSendModel][saveMemberWelfareSend]新增member_welfare_send对象时出现未知异常：", e);
        }
        return result;
    }



    @Override
    public ServiceResult<Boolean> sendWelfareSend(String ip, Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(memberWelfareSendModel.sendWelfareSend(id,ip));
            result.setMessage("发送成功");
        }catch (BusinessException be){
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[memberWelfareSendModel][del]删除member_welfare_send对象时出现未知异常：" + be.getMessage());
        }catch (Exception e){
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[memberWelfareSendModel][del]删除member_welfare_send对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<Integer> del(Integer id) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(memberWelfareSendModel.del(id));
            result.setMessage("删除成功");
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[memberWelfareSendModel][del]删除member_welfare_send对象时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[memberWelfareSendModel][del]删除member_welfare_send对象时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<MemberWelfareSend>> queryPage(Map<String, String> queryMap,
                                                                      PagerInfo pager) {
        ServiceResult<List<MemberWelfareSend>> serviceResult = new ServiceResult<>();
        serviceResult.setPager(pager);
        try {
            Assert.notNull(memberWelfareSendModel, "Property 'memberWelfareSendModel' is required.");
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(memberWelfareSendModel.countPage(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            serviceResult.setResult(memberWelfareSendModel.page(queryMap,size,start));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[IMemberWelfareSendService][queryPage]查询福利积分列表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[IMemberWelfareSendService][queryPage]param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[IMemberWelfareSendService][queryPage]查询福利积分列表发生异常:", e);
        }
        return serviceResult;
    }

}
