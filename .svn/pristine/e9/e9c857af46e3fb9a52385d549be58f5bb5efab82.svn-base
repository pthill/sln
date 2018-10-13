package com.sln.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
 * 用户中心，经验值日志
 *                       
 * @Filename: MemberIntegralController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberGradeController extends BaseController {

    @Resource
    private IMemberService   memberService;

    @Resource
    private IOrdersService   ordersService;

    private final static int PAGE_NUMBER = 10;

    @RequestMapping(value = "/grade.html", method = { RequestMethod.GET })
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
            .getMemberGradeIntegralLogs(memberId, MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_1,
                pager);

        dataMap.put("pagesize", PAGE_NUMBER);
        dataMap.put("memberGradeIntegralLogss", resultMemberGradeIntegralLogs.getResult());

        return "h5/member/person/gradelist";
    }

    /**
     * 返回余额 json 数据
     * @param cateId
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/gradeJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<MemberGradeIntegralLogs>> gradeJson(HttpServletRequest request,
                                                                                 HttpServletResponse response) {
        HttpJsonResult<List<MemberGradeIntegralLogs>> jsonResult = new HttpJsonResult<List<MemberGradeIntegralLogs>>();
        Member member = WebFrontSession.getLoginedUser(request);

        String pageNumStr = request.getParameter("pageNum");
        int pageNum = ConvertUtil.toInt(pageNumStr, 1);
        PagerInfo pager = new PagerInfo(PAGE_NUMBER, pageNum);

        ServiceResult<List<MemberGradeIntegralLogs>> resultMemberGradeIntegralLogs = memberService
            .getMemberGradeIntegralLogs(member.getId(),
                MemberGradeIntegralLogs.MEMBER_GRD_INT_LOG_T_1, pager);

        if (!resultMemberGradeIntegralLogs.getSuccess()) {
            return jsonResult;
        }

        jsonResult.setData(resultMemberGradeIntegralLogs.getResult());
        jsonResult.setTotal(resultMemberGradeIntegralLogs.getResult().size());
        return jsonResult;
    }

}
