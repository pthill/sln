package com.sln.web.controller.member;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberGradeConfig;
import com.sln.entity.member.MemberGradeIntegralLogs;
import com.sln.entity.order.Orders;
import com.sln.service.member.IMemberService;
import com.sln.service.order.IOrdersService;
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
    private IOrdersService   ordersService;

    @Resource
    private IMemberSpecialIntegralService memberSpecialIntegralService;

    private final static int PAGE_NUMBER = 10;

    @RequestMapping(value = "/integral.html", method = { RequestMethod.GET })
    public String integral(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {
        Member member = WebFrontSession.getLoginedUser(request);
        Integer memberId = member.getId();
        ServiceResult<Member> result = memberService.getMemberById(memberId);
        dataMap.put("member", result.getResult());

        //待支付订单数
        ServiceResult<Integer> numResult = ordersService.getOrderNumByMIdAndState(memberId,
            Orders.ORDER_STATE_1);
        dataMap.put("toBepaidOrders", numResult.getResult());
        //待收货订单数
        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_4);
        dataMap.put("toBeReceivedOrders", numResult.getResult());

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

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, PAGE_NUMBER);

        ServiceResult<List<MemberGradeIntegralLogs>> resultMemberGradeIntegralLogs = memberService
            .getSpecialAndIntegralLogs(memberId,pager);
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
        dataMap.put("pagesize", PAGE_NUMBER);
        dataMap.put("memberGradeIntegralLogss", resultMemberGradeIntegralLogs.getResult());

        return "h5/member/person/integrallist";
    }

    /**
     * 返回余额 json 数据
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/integralJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<MemberGradeIntegralLogs>> integralJson(HttpServletRequest request,
                                                                                    HttpServletResponse response) {
        HttpJsonResult<List<MemberGradeIntegralLogs>> jsonResult = new HttpJsonResult<List<MemberGradeIntegralLogs>>();
        Member member = WebFrontSession.getLoginedUser(request);

        String pageNumStr = request.getParameter("pageNum");
        int pageNum = ConvertUtil.toInt(pageNumStr, 1);
        PagerInfo pager = new PagerInfo(PAGE_NUMBER, pageNum);

        ServiceResult<List<MemberGradeIntegralLogs>> resultMemberGradeIntegralLogs = memberService
            .getMemberGradeIntegralLogs(member.getId(),
                MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_2, pager);

        if (!resultMemberGradeIntegralLogs.getSuccess()) {
            return jsonResult;
        }

        jsonResult.setData(resultMemberGradeIntegralLogs.getResult());
        jsonResult.setTotal(resultMemberGradeIntegralLogs.getResult().size());
        return jsonResult;
    }

}
