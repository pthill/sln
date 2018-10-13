package com.sln.web.controller.operate;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.seller.SellerUser;
import com.sln.service.operate.INoticeClickSituationService;
import com.sln.service.system.ISystemNoticeService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 商城公告相关action
 *                       
 * @Filename: SystemNotice.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "/seller/systemNotice")
public class SystemNoticeController extends BaseController {
    @Resource
    private ISystemNoticeService         systemNoticeService;
    @Resource
    private INoticeClickSituationService noticeClickSituationService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "/seller/operate/systemnotice/systemnoticelist";
    }

    /**
     * 未读公告
     * @param dataMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getUnreadNotice", method = { RequestMethod.GET })
    public String getUnreadNotice(Map<String, Object> dataMap, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        SellerUser su = WebSellerSession.getSellerUser(request);
        if (isNull(su)) {
            return "redirect:/seller/login.html";
        }

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        pager.setPageSize(5);
        List<SystemNotice> noticelist = systemNoticeService
            .getUnreadNotice(WebSellerSession.getSellerUser(request).getId(), pager).getResult();
        dataMap.put("count", pager.getRowsCount());
        //概要显示前五个
        dataMap.put("noticelist", noticelist);
        return "seller/notice";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SystemNotice>> list(HttpServletRequest request,
                                                                 ModelMap dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SystemNotice>> serviceResult = systemNoticeService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SystemNotice>> jsonResult = new HttpJsonResult<List<SystemNotice>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 详情
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
            SystemNotice systemNotice = systemNoticeService
                .toDetail(id, WebSellerSession.getSellerUser(request).getId()).getResult();
            if (!isNull(systemNotice.getContent())) {
                systemNotice.setContent(
                    systemNotice.getContent().replace("${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}",
                        DomainUrlUtil.getSLN_IMAGE_RESOURCES()));
            }
            dataMap.put("obj", systemNotice);
        }
        return "/seller/operate/systemnotice/systemnoticedetail";
    }

}
