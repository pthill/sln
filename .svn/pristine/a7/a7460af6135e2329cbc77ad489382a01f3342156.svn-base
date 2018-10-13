package com.sln.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberSpecialIntegral;
import com.sln.service.member.IMemberSpecialIntegralService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberGradeConfig;
import com.sln.entity.member.MemberGradeIntegralLogs;
import com.sln.service.member.IMemberService;
import com.sln.service.promotion.ICouponService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 用户中心，积分日志
 *                       
 * @Filename: MemberIntegralController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberIntegralController extends BaseController {

    @Resource
    private IMemberService   memberService;

    @Resource
    private ICouponService   couponService;

    @Resource
    private IMemberSpecialIntegralService memberSpecialIntegralService;

    private final static int PAGE_NUMBER = 10;

    @RequestMapping(value = "/integral.html", method = { RequestMethod.GET })
    public String integral(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member member = WebFrontSession.getLoginedUser(request);
        Integer memberId = member.getId();
        ServiceResult<Member> result = memberService.getMemberById(memberId);
        dataMap.put("member", result.getResult());

        ServiceResult<MemberGradeConfig> gradeConfigResult = memberService
            .getMemberGradeConfig(ConstantsEJS.MEMBER_GRADE_CONFIG_ID);
        MemberGradeConfig memberGradeConfig = gradeConfigResult.getResult();
        int gradeValue = 0;
        if (member.getGrade().intValue() == Member.GRADE_1) {
            gradeValue = memberGradeConfig.getGrade2().intValue() - member.getGradeValue();
        } else if (member.getGrade().intValue() == Member.GRADE_2) {
            gradeValue = memberGradeConfig.getGrade3().intValue() - member.getGradeValue();
        } else if (member.getGrade().intValue() == Member.GRADE_3) {
            gradeValue = memberGradeConfig.getGrade4().intValue() - member.getGradeValue();
        } else if (member.getGrade().intValue() == Member.GRADE_4) {
            gradeValue = memberGradeConfig.getGrade5().intValue() - member.getGradeValue();
        } else if (member.getGrade().intValue() == Member.GRADE_5) {
        }
        dataMap.put("gradeValue", gradeValue);

        //优惠劵
        ServiceResult<Integer> couResult = couponService.countCouponUserByMemberId(memberId);
        dataMap.put("couponNum", couResult.getResult());

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, 20);

        ServiceResult<List<MemberGradeIntegralLogs>> resultMemberGradeIntegralLogs = memberService
            .getSpecialAndIntegralLogs(memberId, pager);

        
        dataMap.put("page", pager);
        dataMap.put("memberGradeIntegralLogss", resultMemberGradeIntegralLogs.getResult());
        //专项积分
        ServiceResult<List<MemberSpecialIntegral>> serviceResult=memberSpecialIntegralService.
                getMemberSpecialIntegralByMemberId(member.getId(),null);
        if(!serviceResult.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        int specialIntegral=0;
        if(serviceResult.getResult()!=null && serviceResult.getResult().size()>0){
            for(MemberSpecialIntegral m:serviceResult.getResult()){
                specialIntegral+=m.getValue();
            }
        }
        dataMap.put("specialIntegral",specialIntegral);
        //return "front/member/account/integrallist";
        return "front/portal/account/integrallist";
    }
}
