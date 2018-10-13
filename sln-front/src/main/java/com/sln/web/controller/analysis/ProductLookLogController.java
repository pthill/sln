package com.sln.web.controller.analysis;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConvertUtil;
import com.sln.core.CookieHelper;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.analysis.ProductLookLog;
import com.sln.service.analysis.IAnalysisService;
import com.sln.web.controller.BaseController;

/**
 * 
 * 记录用户浏览记录                      
 * @Filename: ProductLookLogController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductLookLogController extends BaseController {

    @Resource
    private IAnalysisService analysisService;

    /**
     * 记录用户浏览记录
     * @param request
     * @param responsed
     */
    @RequestMapping(value = "/product_look_log.html", method = RequestMethod.GET)
    public void productLookLog(HttpServletRequest request, HttpServletResponse responsed) {
        String memberId = request.getParameter("memberId");
        String productId = request.getParameter("productId");

        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getSLN_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();

        ProductLookLog productLookLog = new ProductLookLog();
        productLookLog.setSiteCookie(StringUtil.nullSafeString(cookieValue));
        productLookLog.setProductId(ConvertUtil.toInt(productId, ConvertUtil.toInt(productId, 0)));
        productLookLog.setMemberId(ConvertUtil.toInt(memberId, ConvertUtil.toInt(memberId, 0)));
        ServiceResult<Integer> servletResult = analysisService.saveProductLookLog(productLookLog);
        if (!servletResult.getSuccess()) {
            log.error("[AnalysisLogController][productLookLog]记录用户访问单品页日志出现异常");
        }
    }
}
