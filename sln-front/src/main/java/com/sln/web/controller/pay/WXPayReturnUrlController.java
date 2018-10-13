package com.sln.web.controller.pay;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.entity.member.Member;
import com.sln.entity.order.Orders;
import com.sln.model.order.OrdersModel;
import com.sln.service.order.IOrdersService;
import com.sln.vo.order.OrderSuccessVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

@Controller
@RequestMapping(value = "/wx")
public class WXPayReturnUrlController extends BaseController {

    private static Logger  log = LogManager.getLogger(OrdersModel.class);

    @Resource
    private IOrdersService ordersService;

    @RequestMapping(value = "/returnUrl.html")
    public @ResponseBody HttpJsonResult<Boolean> returnUrl(HttpServletResponse response,
                                                           HttpServletRequest request,
                                                           String orderNo) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        if (StringUtil.isEmpty(orderNo, true)) {
            log.error("微信支付，订单号为空");
            jsonResult.setData(false);
            return jsonResult;
        }
        Member member = WebFrontSession.getLoginedUser(request);
        if (member == null) {
            log.error("用户session过期");
            jsonResult.setData(false);
            return jsonResult;
        }

        ServiceResult<OrderSuccessVO> orderResult = ordersService.getOrdersByPaySn(orderNo,
            member.getId());
        if (!orderResult.getSuccess()) {
            log.error("获取订单失败");
            jsonResult.setData(false);
            return jsonResult;
        }

        OrderSuccessVO orderSuccessVO = orderResult.getResult();
        List<Orders> orderList = orderSuccessVO.getOrdersList();
        if (null == orderList || orderList.size() == 0) {
            log.error("获取订单失败");
            jsonResult.setData(false);
            return jsonResult;
        }
        for (Orders order : orderList) {
            if (order.getPaymentStatus().intValue() == Orders.PAYMENT_STATUS_1) {
                log.info("订单支付成功");
                jsonResult.setData(true);
                return jsonResult;
            }
        }

        jsonResult.setData(false);
        return jsonResult;
    }

    @RequestMapping(value = "/returnUrlSuccess.html")
    public String returnUrl() {
        return "front/order/linepay";
    }

}
