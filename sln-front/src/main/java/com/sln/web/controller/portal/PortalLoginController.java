package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.sms.AlidayuSms;
import com.sln.core.sms.bean.VerifyCodeResult;
import com.sln.entity.member.Member;
import com.sln.service.member.IMemberService;
import com.sln.service.sms.ISenderService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/portal")
public class PortalLoginController extends BaseController {

    @Resource
    private IMemberService memberService;
    @Resource
    private ISenderService senderService;


    @RequestMapping(value = "/login.html",method = { RequestMethod.GET })
    public String Login(){
        return "front/portal/login";
    }

    @RequestMapping(value = "/doLogin.html",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Member> doLogin(HttpServletRequest request){
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        String name = request.getParameter("telphone");
        String password = request.getParameter("pass");
        String verifyCode = request.getParameter("QRcode");
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (name == null || "".equals(name)) {
            return new HttpJsonResult<Member>("用户名不能为空！");
        }
        if (password == null || "".equals(password)) {
            return new HttpJsonResult<Member>("密码不能为空！");
        }
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Member>("验证码不正确！");
        }
        String ip = WebUtil.getIpAddr(request);
        ServiceResult<Member> serviceResult = memberService.memberLogin(name, password, ip,
                ConstantsEJS.SOURCE_1_PC);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                return jsonResult;
            } else {
                jsonResult = new HttpJsonResult<Member>(serviceResult.getMessage());
                return jsonResult;
            }
        }
        Member member = serviceResult.getResult();
        WebFrontSession.putMemberSession(request, member);
        jsonResult.setBackUrl("/portal/index.html");
        return jsonResult;
    }


    @RequestMapping(value = "/register.html",method = {RequestMethod.GET})
    public String register(){
        return "front/portal/register";
    }
    
    //查看协议内容protocol.html
    @RequestMapping(value="/protocol.html",method={RequestMethod.GET})
    public String protocol(){
    	  return "front/portal/protocol";
    }

    //门户发送手机验证码
    @RequestMapping(value = "/sendSMS.html",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult sendSMS(HttpServletRequest request,String mob,String type){
        ServiceResult<Object> result=new ServiceResult<>();
        if(type!=null&&!type.equals("")){
            if(type.equals("reg")){
                result = senderService.sendVerifyCode(mob);
            }else{
                result=senderService.sendForGetPassWordCode(mob);
            }
        }else{
            return new HttpJsonResult<Integer>("验证码类型为空，请重试!");
        }
        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
        HttpSession session = request.getSession();
        String smsCode = type + "_smsCode";
        String exp_time = type + "_exp_time";
        String vc_count = type + "_vc_count";
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
                return new HttpJsonResult<Integer>("今日获取验证码次数过多,请明日再试！");
            }
            log.debug("短信发送完毕：验证码：" + vcr.getVerifyCode() + "|" + vcr.toString());
        } else {
            jsonResult.setMessage(result.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "/doRegister.html",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Member> doRegister(HttpServletRequest request){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String comfirmPass=request.getParameter("comfirmPass");
        //图片验证码
        String verifyCode = request.getParameter("QRcode");
        log.info("获取的图片验证码是"+verifyCode);
        //手机短信验证码
        String phoneQRcode = request.getParameter("phoneQRcode");
        log.info("获取的手机验证码的是"+phoneQRcode);
        //获得session中的图片验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        log.info("session的图片验证码="+verify_number);
        if(userName==null||userName.equals("")){
            return new HttpJsonResult<>("用户名不能为空");
        }
        if(password==null||password.equals("")){
            return new HttpJsonResult<>("密码不能为空");
        }
        if(comfirmPass==null||!password.equals(comfirmPass)){
            return new HttpJsonResult<>("两次输入的密码不一致！");
        }

        //判断用户名是否已存在
        ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(userName);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<Member>("用户名校验出错，请重试！");
        }
        if (serviceResult.getResult() != null && serviceResult.getResult().size() > 0) {
            return new HttpJsonResult<Member>("该手机号已被注册，请重新输入！");
        }

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
            return new HttpJsonResult<Member>("手机验证码已过期,请重新获取");
        }
        log.info("session里面获取的短信验证码是"+session.getAttribute("reg_smsCode"));
        String smsCode=session.getAttribute("reg_smsCode").toString();
        if (smsCode==null||!phoneQRcode.equals(smsCode)) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }
        // 初始化会员信息
        Member member=new Member();
        member.setName(userName);
        member.setPassword(Md5.getMd5String(password));
        member.setGrade(Member.GRADE_1);
        member.setGradeValue(0);
        member.setMobile(userName);
        member.setIntegral(0);
        member.setLastLoginIp(WebUtil.getIpAddr(request));
        member.setLoginNumber(0);
        member.setPwdErrCount(0);
        member.setSource(ConstantsEJS.SOURCE_1_PC);
        member.setBalance(new BigDecimal(0.00));
        member.setBalancePwd(Md5.getMd5String(password));
        member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
        member.setIsSmsVerify(ConstantsEJS.YES_NO_1);
        member.setSmsVerifyCode("");
        member.setEmailVerifyCode("");
        member.setCanReceiveSms(1);
        member.setCanReceiveEmail(1);
        member.setStatus(Member.STATUS_1);
        member.setEmail("");
        ServiceResult<Member> memberServiceResult = memberService.memberRegister(member);
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        if (!memberServiceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(memberServiceResult.getCode())) {
                return new HttpJsonResult<Member>(memberServiceResult.getMessage());
            } else {
                return new HttpJsonResult<Member>(memberServiceResult.getMessage());
            }
        }

        //注册成功后默认登录
        WebFrontSession.putMemberSession(request, memberServiceResult.getResult());
        jsonResult.setBackUrl("/portal/index.html");
        return jsonResult;
    }

    @RequestMapping(value = "logout.html", method = { RequestMethod.GET })
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("memberSession");
        }
        return "front/portal/login";
    }

    @RequestMapping(value = "/forPassword.html",method = {RequestMethod.GET})
    public String forPassword(){
        return "front/portal/forget-password";
    }

    @RequestMapping(value = "/doForPassword.html", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Member> doForPassword(HttpServletRequest request) {
        String userName = request.getParameter("telphone");
        String phoneQRcode = request.getParameter("QRcode");
        String password = request.getParameter("password");
        String comfirmPass = request.getParameter("confirmPass");
        if (userName == null || userName.equals("")) {
            return new HttpJsonResult<>("用户名不能为空");
        }
        if (password == null || password.equals("")) {
            return new HttpJsonResult<>("密码不能为空");
        }
        if (comfirmPass == null || !password.equals(comfirmPass)) {
            return new HttpJsonResult<>("两次输入的密码不一致！");
        }
        //判断用户名是否已存在
        ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(userName);
        if (!serviceResult.getSuccess()) {
            return new HttpJsonResult<>("用户名校验出错，请重试！");
        }
        if (serviceResult.getResult() == null || serviceResult.getResult().size() == 0) {
            return new HttpJsonResult<>("您输入的用户名不存在，请重试！");
        }
        HttpSession session = request.getSession();
        //校验验证码是否过期
        long time = session.getAttribute("forget_exp_time") != null
                ? (long) session.getAttribute("forget_exp_time") : 0l;
        long now = new Date().getTime();
        long diff = (((now - time) / 1000) / 60);
        if (diff > ConstantsEJS.SMS_MAX_WAIT_TIME) {
            session.removeAttribute("forget_smsCode");
            return new HttpJsonResult<Member>("手机验证码已过期,请重新获取");
        }
        log.info("session里面获取的短信验证码是"+session.getAttribute("forget_smsCode"));
        String smsCode=session.getAttribute("forget_smsCode").toString();
        if (smsCode==null||!phoneQRcode.equals(smsCode)) {
            return new HttpJsonResult<Member>("手机验证码错误");
        }
        // 根据name取会员信息
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        Member member = serviceResult.getResult().get(0);
        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(password));
        ServiceResult<Boolean> updateMember = memberService.updateMember(memberNew);
        if (!updateMember.getSuccess()) {
            return new HttpJsonResult<Member>(updateMember.getMessage());
        }
        Boolean state=AlidayuSms.sendResetPwd(userName,password);
        if(!state){
            return new HttpJsonResult<>("重置密码发送失败");
        }
        jsonResult.setBackUrl("/portal/login.html");
        return  jsonResult;
    }


    private boolean isCur(long time) {
        long now = new Date().getTime();
        long diff = (now - time) / (1000 * 60 * 60 * 24);
        return diff == 0;
    }
}
