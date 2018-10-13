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

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.analysis.ProductLookLog;
import com.sln.entity.member.Member;
import com.sln.service.analysis.IAnalysisService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 我的浏览记录
 * 
 * @Filename: MemberViewController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "member")
public class MemberViewController extends BaseController {

    @Resource
    private IAnalysisService analysisService;

    /**
     * 跳转到 商品浏览界面
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/viewed.html", method = { RequestMethod.GET })
    public String viewed(HttpServletRequest request, HttpServletResponse response,
                         Map<String, Object> dataMap) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, 1);

        ServiceResult<List<ProductLookLog>> serviceResult = analysisService
            .getProductLookLogs(queryMap, pager);

        dataMap.put("lookLogList", serviceResult.getResult());
        dataMap.put("logCount", pager.getRowsCount());
        dataMap.put("pageSize", pager.getPageSize());

        return "h5/member/person/productlooklog";
    }

    /**
     * ajax异步加载更多
     * @param request
     * @param response
     * @param map
     * @return
     */
    @RequestMapping(value = "/moreviewed.html", method = { RequestMethod.GET })
    public String moreViewed(HttpServletRequest request, HttpServletResponse response,
                             Map<String, Object> dataMap, Integer pageIndex) {

        Member member = WebFrontSession.getLoginedUser(request);

        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_memberId", member.getId() + "");
        PagerInfo pager = new PagerInfo(ConstantsEJS.DEFAULT_PAGE_SIZE_10, pageIndex);

        ServiceResult<List<ProductLookLog>> serviceResult = analysisService
            .getProductLookLogs(queryMap, pager);

        dataMap.put("lookLogList", serviceResult.getResult());

        return "h5/member/person/productlookloglist";
    }
}
