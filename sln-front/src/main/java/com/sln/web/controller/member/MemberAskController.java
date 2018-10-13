package com.sln.web.controller.member;

import java.util.HashMap;
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
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.product.ProductAsk;
import com.sln.service.analysis.IAnalysisService;
import com.sln.service.member.IMemberCollectionProductService;
import com.sln.service.member.IMemberCollectionSellerService;
import com.sln.service.product.IProductAskService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 用户中心：我的咨询
 * 
 * @Filename: MemberAskController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberAskController extends BaseController {

    @Resource
    private IMemberCollectionSellerService  memberCollectionSellerService;

    @Resource
    private IMemberCollectionProductService memberCollectionProductService;

    @Resource
    private IProductAskService              productAskService;

    @Resource
    private IAnalysisService                analysisService;

    /**
     * 跳转到 我的咨询界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/question.html", method = { RequestMethod.GET })
    public String toMyConsultation(HttpServletRequest request, HttpServletResponse response,
                                   Map<String, Object> dataMap) {
    	
    	this.head(0,dataMap,request);
    	
        Map<String, String> queryMap = new HashMap<String, String>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,8);
        Member member = WebFrontSession.getLoginedUser(request);
        queryMap.put("q_userId", member.getId() + "");
        ServiceResult<List<ProductAsk>> serviceResult = productAskService
            .getProductAsksWithInfo(queryMap, pager);

        dataMap.put("askList", serviceResult.getResult());
        dataMap.put("page", pager);

        //return "front/member/ordercenter/myconsultation";
        return "front/portal/orders/myconsultation";
    }

    /**
     * 商品咨询提交
     * @param request
     * @param response
     * @param dataMap
     * @param productAsk
     * @return
     */
    @RequestMapping(value = "/savequestion.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<ProductAsk> askinfoSubmit(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  Map<String, Object> dataMap,
                                                                  ProductAsk productAsk) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<ProductAsk> serviceResult = productAskService.saveProductAsk(productAsk,
            member);
        HttpJsonResult<ProductAsk> jsonResult = new HttpJsonResult<ProductAsk>();

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<ProductAsk>(serviceResult.getMessage());
            }
        }
        return jsonResult;
    }

}
