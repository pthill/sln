package com.sln.web.controller.product;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConvertUtil;
import com.sln.core.CookieHelper;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.member.Member;
import com.sln.entity.search.SearchLogs;
import com.sln.entity.search.SearchRecord;
import com.sln.service.search.ISearchLogsService;
import com.sln.service.search.ISearchRecordService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 搜索关键字日志相关的处理
 *                       
 * @Filename: ProductSearchLogsController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductSearchLogsController extends BaseController {

    @Resource
    private ISearchLogsService   searchLogsService;

    @Resource
    private ISearchRecordService searchRecordService;

    /**
     * 根据keyword获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/get_search_record.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<SearchRecord>> getSearchRecord(HttpServletRequest request,
                                                                            HttpServletResponse responsed,
                                                                            String keyword) {
        HttpJsonResult<List<SearchRecord>> jsonResult = new HttpJsonResult<List<SearchRecord>>();

        if (keyword != null && !"".equals(keyword.trim())) {
            ServiceResult<List<SearchRecord>> servletResult = searchRecordService
                .getSearchRecordByKeyword(keyword, 8);
            if (!servletResult.getSuccess()) {
                log.error("[ProductSearchLogsController][getSearchLogs]根据cookie获取搜索最近N条记录出现异常");
            }

            List<SearchRecord> searchLogss = servletResult.getResult();
            jsonResult.setData(searchLogss);

            if (searchLogss != null) {
                jsonResult.setTotal(searchLogss.size());
            }

        }
        return jsonResult;
    }

    /**
     * 根据cookie获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/get_search_logs.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<SearchLogs>> getSearchLogs(HttpServletRequest request,
                                                                        HttpServletResponse responsed) {
        HttpJsonResult<List<SearchLogs>> jsonResult = new HttpJsonResult<List<SearchLogs>>();

        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getSLN_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();

        ServiceResult<List<SearchLogs>> servletResult = searchLogsService.getSearchLogsByCookie(
            cookieValue, 8);
        if (!servletResult.getSuccess()) {
            log.error("[ProductSearchLogsController][getSearchLogs]根据cookie获取搜索最近N条记录出现异常");
        }

        List<SearchLogs> searchLogss = servletResult.getResult();
        jsonResult.setData(searchLogss);

        if (searchLogss != null) {
            jsonResult.setTotal(searchLogss.size());
        }
        return jsonResult;
    }

    /**
     * 根据cookie获取搜索最近N条记录
     * @return
     */
    @RequestMapping(value = "/save_search_logs.html", method = RequestMethod.GET)
    public void saveSearchLogs(HttpServletRequest request, HttpServletResponse responsed,
                               String keyword) {
        Cookie cookie = CookieHelper.getCookieByName(request, DomainUrlUtil.getSLN_COOKIE_NAME());
        String cookieValue = cookie == null ? "" : cookie.getValue();

        String memberId = "0";
        Member member = WebFrontSession.getLoginedUser(request);
        if (member != null && member.getId() != null) {
            memberId = member.getId().toString();
        }

        if (keyword != null && !"".equals(keyword.trim())) {
            SearchLogs searchLogs = new SearchLogs();
            searchLogs.setKeyword(keyword);
            searchLogs.setSiteCookie(cookieValue);
            searchLogs.setIp(WebUtil.getIpAddr(request));
            searchLogs.setMemberId(ConvertUtil.toInt(memberId, ConvertUtil.toInt(memberId, 0)));
            searchLogsService.saveSearchLogs(searchLogs);
        }

    }
}
