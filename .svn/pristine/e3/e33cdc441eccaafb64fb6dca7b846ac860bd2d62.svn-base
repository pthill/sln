package com.sln.model.member;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.core.WXAccessTokenUtil;
import com.sln.dao.shop.write.member.MemberWriteDao;
import com.sln.dao.shop.write.member.MemberWxsignWriteDao;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberWxsign;

@Component
public class MemberWxsignModel {

    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(MemberWxsignModel.class);

    @Resource
    private MemberWxsignWriteDao           memberWxsignWriteDao;
    @Resource
    private MemberWriteDao                 memberWriteDao;
    @Resource
    private DataSourceTransactionManager   transactionManager;

    /**
     * 根据id取得微信联合登录
     * @param  memberWxsignId
     * @return
     */
    public MemberWxsign getMemberWxsignById(Integer memberWxsignId) {
        return memberWxsignWriteDao.get(memberWxsignId);
    }

    /**
     * 保存微信联合登录
     * @param  memberWxsign
     * @return
     */
    public Integer saveMemberWxsign(MemberWxsign memberWxsign) {
        this.dbConstrains(memberWxsign);
        return memberWxsignWriteDao.save(memberWxsign);
    }

    /**
    * 更新微信联合登录
    * @param  memberWxsign
    * @return
    */
    public Integer updateMemberWxsign(MemberWxsign memberWxsign) {
        this.dbConstrains(memberWxsign);
        return memberWxsignWriteDao.update(memberWxsign);
    }

    private void dbConstrains(MemberWxsign memberWxsign) {
        memberWxsign.setOpenid(StringUtil.dbSafeString(memberWxsign.getOpenid(), false, 32));
    }

    public List<MemberWxsign> page(Map<String, String> queryMap, PagerInfo pager) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(memberWxsignWriteDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<MemberWxsign> list = memberWxsignWriteDao.page(param);
        return list;
    }

    public Integer del(Integer id) {
        return memberWxsignWriteDao.del(id);
    }

    /**
     * 以openid获取用户信息
     * @param openid 用户标识
     * @param accessToken 访问令牌
     * @return
     */
    public Member getWxUser(String openid, String accessToken) {
        Member member = null;
        // 事务管理
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            member = memberWxsignWriteDao.getByOpenId(openid);
            if (member == null) {
                log.debug("没有绑定用户");
                //未绑定，创建新用户 
                Map<String, Object> usermap = WXAccessTokenUtil.getUserinfo(openid, accessToken);

                String nickname = usermap.get("nickname") == null ? "微信用户_" + openid
                    : (String) usermap.get("nickname");

                //查询该昵称是否被使用
                List<Member> members = memberWriteDao.getByName(nickname);
                if (members != null && members.size() > 0) {
                    //如果被使用，加上用户openid
                    nickname += "_" + openid;
                }

                //1、创建用户
                Member newmember = new Member();
                newmember.setName(nickname);
                newmember.setPassword("");
                newmember.setGrade(0);
                newmember.setGradeValue(0);
                newmember.setIntegral(0);
                newmember.setRegisterTime(new Date());
                newmember.setLastLoginTime(new Date());
                newmember.setLastLoginIp("");
                newmember.setLoginNumber(0);
                newmember.setLastAddressId(0);
                newmember.setLastPaymentCode(-1);
                newmember.setGender(usermap.get("sex") == null ? 0 : (Integer) usermap.get("sex"));
                newmember.setBirthday(null);

                newmember.setEmail("");
                newmember.setQq("");
                newmember.setMobile("");
                newmember.setPhone("");
                newmember.setPwdErrCount(0);
                newmember.setSource(ConstantsEJS.SOURCE_2_H5);
                newmember.setBalance(BigDecimal.ZERO);
                newmember.setBalancePwd("");
                newmember.setIsEmailVerify(Member.IS_EMAIL_VERIFY_0);
                newmember.setIsSmsVerify(Member.IS_SMS_VERIFY_0);
                newmember.setSmsVerifyCode("");
                newmember.setEmailVerifyCode("");
                newmember.setCanReceiveEmail(1);
                newmember.setCanReceiveSms(1);
                newmember.setStatus(Member.STATUS_1_ON);
                newmember.setUpdateTime(new Date());
                memberWriteDao.save(newmember);

                //2、保存微信绑定关系
                MemberWxsign wxsign = new MemberWxsign();
                wxsign.setMemberId(newmember.getId());
                wxsign.setOpenid(openid);
                memberWxsignWriteDao.save(wxsign);
                //返回用户
                member = newmember;

                // 3、提交事务
                transactionManager.commit(status);
            }
        } catch (Exception e) {
            transactionManager.rollback(status);
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return member;
    }

}