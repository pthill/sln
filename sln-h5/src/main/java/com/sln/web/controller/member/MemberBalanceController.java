package com.sln.web.controller.member;

import java.math.BigDecimal;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.service.member.IMemberBalancePayLogService;
import com.sln.service.member.IMemberService;
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
        return "h5/member/balancepay/recharge";
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
        return "h5/member/balancepay/selectpay";
    }

}
