package com.sln.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.Md5;
import com.sln.core.RandomUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.service.member.IMemberService;
import com.sln.service.order.IOrdersService;
import com.sln.service.sms.ISenderService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 会员登录controller
 * 
 * @Filename: MemberLoginController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class MemberLoginController extends BaseController {

    @Resource
    private IMemberService memberService;

    @Resource
    private IOrdersService ordersService;
    
    @Resource
    private ISenderService senderService;

    /**
     * 跳转到登录页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/login.html", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> stack) {
        String toUrl = request.getParameter("toUrl");
        stack.put("toUrl", toUrl);
        return "h5/member/login";
    }

    /**
     * 登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "dologin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> loginSumbit(HttpServletRequest request,
                                                             HttpServletResponse response) {
        String verifyCode = request.getParameter("verifyCode");
        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Boolean>("验证码不正确！");
        }

        String ip = WebUtil.getIpAddr(request);
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        // 登录验证
        ServiceResult<Member> serviceResult = memberService.memberLogin(userName, password, ip,
            ConstantsEJS.SOURCE_2_H5);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
                return jsonResult;
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
                return jsonResult;
            }
        }

        Member member = serviceResult.getResult();
        // 登录送经验值积分
        //memberService.memberLoginSendValue(member.getId(), member.getName());
        // 存入session
        WebFrontSession.putMemberSession(request, member);

        return jsonResult;
    }

    /**
     * 退出登录
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "logout.html", method = { RequestMethod.GET })
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute("memberSession");
        }
        return "h5/member/login";
    }

    /**
     * 判断登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "isuserlogin.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> isUserLogin(HttpServletRequest request,
                                                             HttpServletResponse response) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(false);
        } else {
            jsonResult.setData(true);
        }
        return jsonResult;
    }

    /**
     * 获取登录用户（如果未登录返回null对象）
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "getloginuser.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Member> getLoginUser(HttpServletRequest request,
                                                             HttpServletResponse response) {
        HttpJsonResult<Member> jsonResult = new HttpJsonResult<Member>();
        Member loginedUser = WebFrontSession.getLoginedUser(request);
        if (loginedUser == null) {
            jsonResult.setData(null);
        } else {
            jsonResult.setData(loginedUser);
        }
        return jsonResult;
    }

    /**
     * 跳转到忘记密码页面
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/forgetpassword.html", method = { RequestMethod.GET })
    public String forgetPassword(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> stack) {
        return "h5/member/person/forgetpassword";
    }

    /**
     * 忘记密码发送邮件
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/doforgetpassword.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> doForgetpassword(HttpServletRequest request,
                                                                  HttpServletResponse response) throws Exception {

        String name = request.getParameter("name");
        if (StringUtil.isEmpty(name)) {
            return new HttpJsonResult<Boolean>("用户名不能为空！");
        }
        String mobile = request.getParameter("mobile");
        if (StringUtil.isEmpty(mobile)) {
            return new HttpJsonResult<Boolean>("手机号码不能为空！");
        }
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtil.isEmpty(verifyCode)) {
            return new HttpJsonResult<Boolean>("验证码不能为空！");
        }

        //获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
            return new HttpJsonResult<Boolean>("验证码输入错误，请重试！");
        }

        // 根据name取会员信息
        ServiceResult<List<Member>> memberResult = memberService.getMemberByName(name);
        if (!memberResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(memberResult.getMessage());
        }
        if (memberResult.getResult() == null || memberResult.getResult().size() == 0) {
            return new HttpJsonResult<Boolean>("您输入的用户名不存在，请重试！");
        }
        Member member = memberResult.getResult().get(0);

        if (member.getIsSmsVerify() == null
            || member.getIsSmsVerify().equals(Member.IS_SMS_VERIFY_0)
            || StringUtil.isEmpty(member.getMobile(), true)) {
            return new HttpJsonResult<Boolean>("对不起，您没有绑定手机！");
        }

        if (!mobile.equals(member.getMobile())) {
            return new HttpJsonResult<Boolean>("对不起，您输入的手机号码与您绑定的号码不一致，请输入正确的手机号码！");
        }

        String newPwd = RandomUtil.randomNumber(6);

        Member memberNew = new Member();
        memberNew.setId(member.getId());
        memberNew.setPassword(Md5.getMd5String(newPwd));

        ServiceResult<Boolean> updateMember = memberService.updateMember(memberNew);
        if (!updateMember.getSuccess()) {
            return new HttpJsonResult<Boolean>(updateMember.getMessage());
        }

        // 发送短信的方法，需要运营商提供请求URL，不要删除这段代码
        /*SendSms send = new SendSms();
        Boolean sendSms = send.sendSms(mobile, "感谢您使用密码找回功能，您的新密码是：" + newPwd + "，请及时更改您的密码。");
        if (!sendSms) {
            jsonResult = new HttpJsonResult<Boolean>("短信发送异常，请稍后重试！");
        }*/
        
        //发送重置密码的短信
        ServiceResult<Object> result = senderService.sendResetPwd(mobile,newPwd);
        if(!result.getSuccess()) {
        	return new HttpJsonResult<Boolean>("短信发送异常，请稍后重试！");
        }
        
        return new HttpJsonResult<Boolean>();
    }

    /**
     * 跳转到用户中心页面
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
    @RequestMapping(value = "/member/index.html", method = { RequestMethod.GET })
    public String memberIndex(HttpServletRequest request, HttpServletResponse response,
                              Map<String, Object> dataMap) {
        Member sessionMember = WebFrontSession.getLoginedUser(request);
        Integer memberId = sessionMember.getId();
        // 获取member对象
        ServiceResult<Member> result = memberService.getMemberById(memberId);
        dataMap.put("member", result.getResult());

        //待支付订单数
        ServiceResult<Integer> numResult = ordersService.getOrderNumByMIdAndState(memberId,
            Orders.ORDER_STATE_1);
        dataMap.put("toBepaidOrders", numResult.getResult());
        //待收货订单数
        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_4);
        dataMap.put("toBeReceivedOrders", numResult.getResult());
        //        //待评价订单数
        //        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_5);
        //        dataMap.put("toBeCommentedOrders", numResult.getResult());

        return "h5/member/memberindex";
    }
}
