package com.sln.web.controller.member;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.Md5;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberBalanceLogs;
import com.sln.service.member.IMemberBalanceLogsService;
import com.sln.service.member.IMemberService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.CommUtil;
import com.sln.web.util.WebFrontSession;

/**
 * 会员账户管理 ：提现、收支明细                      
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberAccountManageController extends BaseController {

    @Resource
    private IMemberBalanceLogsService memberBalanceLogsService;

    @Resource
    private IMemberService            memberService;

    private static String             baseUrl = "front/member/service";

    /**
     * 跳转到余额列表页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/balance.html", method = { RequestMethod.GET })
    public String balancelist(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,20);
        ServiceResult<List<MemberBalanceLogs>> serviceResult = memberBalanceLogsService
            .getMemberBalanceLogs(sessionMember.getId(), pager);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("info", serviceResult.getMessage());
                return "front/commons/error";
            }
        }

        dataMap.put("infoList", serviceResult.getResult());
        dataMap.put("page", pager);
        dataMap.put("member", memberResult.getResult());

        //return baseUrl + "/balancelist";
        return "front/portal/service/balancelist";
    }
    /**
     * 跳转到余额赠送页面
     */
    @RequestMapping(value = "/jumpBalanceGive.html", method = { RequestMethod.GET })
    public String jumpBalanceGive(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());
        
     // 支付随机码 避免重复提交
        String order_session = CommUtil.randomString(32);
        request.getSession(false).setAttribute("order_session", order_session);
    	
        dataMap.put("paySessionstr", order_session);
        
        dataMap.put("member", memberResult.getResult());

        return baseUrl + "/balanceGive";
    }
    

    /**
     * 跳转到设置支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/setbalancepassword.html", method = { RequestMethod.GET })
    public String balancepwdadd(HttpServletRequest request, HttpServletResponse response,
                                Map<String, Object> dataMap) {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return baseUrl + "/balancepwdadd";
    }

    @RequestMapping(value = "balancepwdreset/step2.html", method = { RequestMethod.GET })
    public String step2(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        return baseUrl + "/step2";
    }

    /**
     * 找回支付密码第一步验证
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/balancepwdreset/valid.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> valid(HttpServletRequest request,
                                                       HttpServletResponse response,
                                                       Map<String, Object> dataMap, String smscode,
                                                       String verifycode) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        try {
            //获得session中的验证码
            String verify_number = WebFrontSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifycode)) {
                return new HttpJsonResult<Boolean>("验证码不正确！");
            }

            HttpSession session = request.getSession();
            //校验验证码是否过期
            long time = session.getAttribute("blt_exp_time") != null
                ? (long) session.getAttribute("blt_exp_time") : 0l;
            long now = new Date().getTime();
            long diff = (((now - time) / 1000) / 60);
            if (diff > ConstantsEJS.SMS_MAX_WAIT_TIME) {
                session.removeAttribute("blt_smsCode");
                return new HttpJsonResult<Boolean>("验证码已过期,请重新获取");
            }

            if (session.getAttribute("blt_smsCode") == null
                || !smscode.equals(session.getAttribute("blt_smsCode"))) {
                return new HttpJsonResult<Boolean>("手机验证码错误");
            }
            session.setAttribute("blt_mobile_valid_success", true);
        } catch (Exception e) {
            jsonResult.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return jsonResult;
    }

    /**
     * 支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/savebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> addBalancePwdSumbit(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    @RequestParam(value = "password", required = true) String password) throws Exception {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.addBalancePassword(password, sessionMember);
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 跳转到 修改支付密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/editbalancepassword.html", method = { RequestMethod.GET })
    public String toEditBalancePwd(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {

    	this.head(0,dataMap,request);
    	
    	Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        //return baseUrl + "/balancepwdedit";
        return "front/portal/service/balancepwdedit";
    }

    @RequestMapping(value = "/balancepwdreset.html", method = { RequestMethod.GET })
    public String balancepwdreset(HttpServletRequest request, HttpServletResponse response,
                                  Map<String, Object> dataMap) {
    	this.head(0,dataMap,request);
    	
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        //查询用户信息
        ServiceResult<Member> memberResult = new ServiceResult<Member>();
        memberResult = memberService.getMemberById(sessionMember.getId());

        dataMap.put("member", memberResult.getResult());
        //return baseUrl + "/balancepwdreset";
        return "front/portal/service/balancepwdreset";
    }

    /**
     * 修改支付密码提交
     * @param request
     * @param response
     * @param map
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/updatebalancepassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> editBalancePasswordSumbit(HttpServletRequest request,
                                                                          HttpServletResponse response,
                                                                          @RequestParam(value = "oldPwd", required = true) String oldPwd,
                                                                          @RequestParam(value = "newPwd", required = true) String newPwd) throws Exception {

        Member sessionMember = WebFrontSession.getLoginedUser(request);

        ServiceResult<Member> serviceResult = new ServiceResult<Member>();
        serviceResult = memberService.editBalancePassword(oldPwd, newPwd, sessionMember.getId());
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    @RequestMapping(value = "/balancepwdreset/updatepwd.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> updatepwd(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          String newPwd, String repwd) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);

        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (request.getSession().getAttribute("blt_mobile_valid_success") == null) {
            throw new BusinessException("请先验证手机");
        }

        if (isNull(newPwd) || isNull(repwd)) {
            throw new BusinessException("数据错误，请稍后重试");
        }
        if (!newPwd.equals(repwd)) {
            throw new BusinessException("两次密码输入不一致，请重试");
        }

        ServiceResult<Member> sr = memberService.getMemberById(sessionMember.getId());
        Member member = sr.getResult();
        member.setBalancePwd(Md5.getMd5String(newPwd));
        ServiceResult<Boolean> serviceResult = memberService.updateMember(member);
        WebFrontSession.putMemberSession(request, member);

        jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }
        request.getSession().removeAttribute("blt_mobile_valid_success");
        return jsonResult;
    }

}
