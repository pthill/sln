package com.sln.web.controller.member;

import java.math.BigDecimal;
import java.security.Provider.Service;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.service.member.IMemberBalancePayLogService;
import com.sln.service.member.IMemberService;
import com.sln.vo.member.FrontCheckPwdVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 会员余额充值相关action
 *                       
 * @Filename: MemberBalanceController.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihailong@sln.com
 *
 */
@Controller
@RequestMapping(value = "member/balance/pay")
public class MemberBalanceController extends BaseController {
    Logger                              log = Logger.getLogger(this.getClass());
    @Resource
    private IMemberService              memberService;

    @Resource
    private IMemberBalancePayLogService memberBalancePayLogService;

    /**
     * 充值
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "recharge.html", method = { RequestMethod.GET })
    public String recharge(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {
        if (isNull(WebFrontSession.getLoginedUser(request))) {
            return "redirect:/login.html";
        }

        return "front/member/balancepay/recharge";
    }

    /**
     * 跳转至支付页面
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "selectpay.html", method = { RequestMethod.POST })
    public String selectpay(HttpServletRequest request, HttpServletResponse response,
                            BigDecimal amount, Map<String, Object> dataMap) {
        if (isNull(WebFrontSession.getLoginedUser(request))) {
            return "redirect:/login.html";
        }

        dataMap.put("amount", amount);
        return "front/member/balancepay/selectpay";
    }
    
    /**
     * 余额赠送
     */
    @RequestMapping(value = "balancegive.html", method = { RequestMethod.POST })
    public @ResponseBody ServiceResult<Boolean> balanceGive(HttpServletRequest request, HttpServletResponse response,
    		BigDecimal money,String smsCode,String balancePwd,
    		String mobile,@RequestParam(value = "orderSession", required = true) String orderSession){
    	ServiceResult<Boolean> result = new ServiceResult<Boolean>();
    	HttpSession session = request.getSession();
    	Member member = WebFrontSession.getLoginedUser(request);
    	/*//获得session中的验证码
        String verify_number = WebFrontSession.getVerifyNumber(request);
        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
        	result.setError("1001", "验证码错误");
            return result;
        }

        HttpSession session = request.getSession();
        //校验验证码是否过期
        long time = session.getAttribute("reg_exp_time") != null
            ? (long) session.getAttribute("reg_exp_time") : 0l;
        long now = new Date().getTime();
        long diff = (((now - time) / 1000) / 60);
        if (diff > ConstantsEJS.SMS_MAX_WAIT_TIME) {
            session.removeAttribute("reg_smsCode");
            result.setError("1002", "验证码已过期,请重新获取");
            return result;
        }*/
    	 // 支付随机码 避免重复提交
        //String order_session = CommUtil.randomString(32);
        //request.getSession(false).setAttribute("order_session", order_session);
    	
        //dataMap.put("paySessionstr", order_session);
    	//验证手机验证码
        if (!smsCode.equals(session.getAttribute("bal_smsCode"))) {
        	result.setError("1002", "手机验证码错误");
            return result;
        }
        //验证余额支付密码
        ServiceResult<FrontCheckPwdVO> serviceResult = new ServiceResult<FrontCheckPwdVO>();
        serviceResult = memberService.checkcheckBalancePwd(balancePwd, member.getId());
        if (!serviceResult.getSuccess()) {
        	result.setError("1003", serviceResult.getMessage());
            return result;
        }
        
        
        //校验支付随机码，避免重复提交
    	if(orderSession == null || "".equals(orderSession)){
    		result.setError("1004", "提交不合法，请合法提交");
            return result;
    	}
    	String order_session = request.getSession().getAttribute("order_session")!= null ? request.getSession().getAttribute("order_session").toString():null;
    	if(order_session == null || "".equals(order_session)){
    		result.setError("1005", "请勿重复提交");
            return result;
    	}
    	if(!orderSession.equals(order_session)){
    		result.setError("1005", "请勿重复提交");
            return result;
    	}else{
    		request.getSession().removeAttribute("order_session");
    	}
        //进行赠送操作
        result = memberService.balanceGive(member,mobile,money);
        
        //如果失败则继续把提交码
        if(!result.getSuccess()){
        	request.getSession().setAttribute("order_session", orderSession);
        }else{
        	 //操作完之后 删除手机验证码,避免重复
            session.removeAttribute("bal_smsCode");
        }
      
    	return result;
    }

}
