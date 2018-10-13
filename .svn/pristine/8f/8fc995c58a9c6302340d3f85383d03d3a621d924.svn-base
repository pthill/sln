package com.sln.web.controller.order;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.entity.member.Member;
import com.sln.entity.order.Invoice;
import com.sln.service.member.IInvoiceService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 购物流程-订单发票<br>
 * 本controller中得请求都需要登录才能访问
 * 
 * @Filename: OrdersInvoiceController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class OrdersInvoiceController extends BaseController {

    @Resource
    private IInvoiceService invoiceService;

    /**
     * 保存发票抬头
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/saveinvoice.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> saveInvoice(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Invoice invoice) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        serviceResult = invoiceService.saveInvoice(invoice, member.getId());

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 更新发票抬头
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/updateinvoice.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> updateInvoice(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Invoice invoice) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        serviceResult = invoiceService.updateInvoice(invoice);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

    /**
     * 删除发票
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/deleteinvoice.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> delInvoice(HttpServletRequest request,
                                                            HttpServletResponse response,
                                                            Invoice invoice,
                                                            @RequestParam(value = "invoiceId", required = true) Integer invoiceId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        serviceResult = invoiceService.delInvoice(invoiceId);

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

}
