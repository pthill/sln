package com.sln.web.controller.member;

import java.math.BigDecimal;
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
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.sms.bean.VerifyCodeResult;
import com.sln.entity.member.Member;
import com.sln.service.member.IMemberService;
import com.sln.service.sms.ISenderService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 会员注册controller
 * 
 * @Filename: MemberRegisterController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MemberRegisterController extends BaseController {

    @Resource
    private IMemberService memberService;
    @Resource
    private ISenderService senderService;

    /**
     * 查看用户协议
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/service_protocol.html", method = { RequestMethod.GET })
    public String protocol(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> stack) {
        return "front/member/serviceprotocol";
    }

    /**
     * 跳转到注册页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/register.html", method = { RequestMethod.GET })
    public String signup(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> stack) {
        //return "front/member/membersignup";
    	// update by li.biao since 2018-2-8 15:20:29
    	// 使用门户的注册页面
    	return "front/portal/register" ;
    }

    /**
     * 判断用户名是否已存在
     * @param request
     * @param response
     * @param member
     * @throws Exception 
     */
    @RequestMapping(value = "/nameIsExist.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> verify_membername(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   @RequestParam(value = "name", required = true) String name) throws Exception {
        //判断用户名是否已存在
        ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(name);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Boolean>("用户名校验出错，请重试！");
        }

        if (serviceResult.getResult() != null && serviceResult.getResult().size() > 0) {
            return new HttpJsonResult<Boolean>("用户名已存在，请重新输入！");
        }

        return new HttpJsonResult<Boolean>();
    }

    /**
     * 获取手机验证码
     * @param request
     * @param response
     * @param dataMap
     * @param mob 手机号码
     * @param verifycode 验证码
     * @return
     */
    @RequestMapping(value = "/sendVerifySMS.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Integer> sendVerifySMS(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap,
                                                               String type, String mob,
                                                               String verifycode) throws Exception {
        HttpSession session = request.getSession();
        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
        String smsCode = type + "_smsCode";
        String exp_time = type + "_exp_time";
        String vc_count = type + "_vc_count";

        //注册时才需要校验
        if (!isNull(type) && "reg".equals(type)) {

            //获得session中的验证码
            String verify_number = WebFrontSession.getVerifyNumber(request);
            if (verify_number == null || !verify_number.equalsIgnoreCase(verifycode)) {
                return new HttpJsonResult<Integer>("验证码不正确");
            }
            //判断用户名是否已存在
            ServiceResult<Boolean> serviceResult = memberService.isMobExists(mob);
            if (!serviceResult.getSuccess()) {
                return new HttpJsonResult<Integer>("手机号校验出错，请重试");
            }
            if (serviceResult.getResult() != null && serviceResult.getResult() == Boolean.TRUE) {
                return new HttpJsonResult<Integer>("手机号重复，请重新输入");
            }
        }

        //--------------------------正式代码 开始------------------------
        ServiceResult<Object> result = senderService.sendVerifyCode(mob);
        //--------------------------正式代码 结束------------------------

        //TODO 
        //--------------------------测试代码 本地测试时放开本段代码 bg--------------------------//
        //            ServiceResult<Object> result = new ServiceResult<Object>();
        //            VerifyCodeResult vc = new VerifyCodeResult();
        //            vc.setVerifyCode("888888");
        //            result.setResult(vc);
        //--------------------------测试代码 ed--------------------------//

        if (result.getSuccess()) {
            VerifyCodeResult vcr = (VerifyCodeResult) result.getResult();

            //将信息放入当前会话
            session.setAttribute(smsCode, vcr.getVerifyCode());
            session.setAttribute(exp_time, new Date().getTime());
            session.setAttribute(vc_count, session.getAttribute(vc_count) == null ? 0
                : ((Integer) session.getAttribute(vc_count)) + 1);

            //当天只能获取5次
            if (session.getAttribute(exp_time) != null
                && isCur((long) session.getAttribute(exp_time))
                && ((Integer) session.getAttribute(vc_count)) >= ConstantsEJS.SMS_MAX_GIVEN_NUM) {
                return new HttpJsonResult<Integer>("今日获取验证码次数过多,请明日再试");
            }
            log.debug("短信发送完毕：验证码：" + vcr.getVerifyCode() + "|" + vcr.toString());
        } else {
            jsonResult.setMessage(result.getMessage());
        }
        return jsonResult;
    }

    /**
     * 是否当天
     * @param attribute
     * @return
     */
    private boolean isCur(long time) {
        long now = new Date().getTime();
        long diff = (now - time) / (1000 * 60 * 60 * 24);
        return diff == 0;
    }

    /**
     * 注册信息提交
     * @param request
     * @param response
     * @param stack
     * @param membervo
     * @throws Exception
     */
    @RequestMapping(value = "/doregister.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> signupSubmit(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Member member, String verifyCode,
                                                             String smsCode) throws Exception {
        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Member>("验证码不正确！");
        }

        HttpSession session = request.getSession();
        //校验验证码是否过期
        long time = session.getAttribute("reg_exp_time") != null
            ? (long) session.getAttribute("reg_exp_time") : 0l;
        long now = new Date().getTime();
        long diff = (((now - time) / 1000) / 60);
        if (diff > ConstantsEJS.SMS_MAX_WAIT_TIME) {
            session.removeAttribute("reg_smsCode");
            return new HttpJsonResult<Member>("验证码已过期,请重新获取");
        }

        if (session.getAttribute("reg_smsCode") == null
            || !smsCode.equals(session.getAttribute("reg_smsCode"))) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }

        // 初始化会员信息
        member.setPassword(Md5.getMd5String(member.getPassword()));
        member.setGrade(Member.GRADE_1);
        member.setGradeValue(0);
        //member.setMobile(member.getPhone());
        member.setIntegral(0);
        member.setLastLoginIp(WebUtil.getIpAddr(request));
        member.setLoginNumber(0);
        member.setPwdErrCount(0);
        member.setSource(ConstantsEJS.SOURCE_1_PC);
        member.setBalance(new BigDecimal(0.00));
        member.setBalancePwd("");
        member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
        member.setIsSmsVerify(ConstantsEJS.YES_NO_1);
        member.setSmsVerifyCode("");
        member.setEmailVerifyCode("");
        member.setCanReceiveSms(1);
        member.setCanReceiveEmail(1);
        member.setStatus(Member.STATUS_1);
        member.setEmail("");
        ServiceResult<Member> serviceResult = memberService.memberRegister(member);
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
            }
        }

        //注册时赠送经验值及积分
        memberService.memberRegistSendValue(member.getId(), member.getName());

        //注册成功后默认登录
        WebFrontSession.putMemberSession(request, serviceResult.getResult());

        return jsonResult;
    }
}