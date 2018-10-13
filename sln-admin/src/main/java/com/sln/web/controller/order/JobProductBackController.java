package com.sln.web.controller.order;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.JobProductBack;
import com.sln.entity.member.MemberProductBack;
import com.sln.service.member.IJobProductBackService;
import com.sln.service.member.IMemberProductBackService;
import com.sln.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin/order/jobProductBack")
public class JobProductBackController extends BaseController {
    @Resource
    private IJobProductBackService jobProductBackService;
    @Resource
    private IMemberProductBackService memberProductBackService;

    @RequestMapping(value = "",method = { RequestMethod.GET})
    public String init(Map<String,Object> dataMap){
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/job/list";
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<JobProductBack>> list(HttpServletRequest request,
                                                     Map<String, Object> dataMap){
        HttpJsonResult<List<JobProductBack>> list=new HttpJsonResult<>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<JobProductBack>> serviceResult = jobProductBackService.getPage(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        list.setRows(serviceResult.getResult());
        list.setTotal(pager.getRowsCount());
        return list;
    }

    @RequestMapping(value = "/detail",method = {RequestMethod.GET})
    public String detail(HttpServletRequest request,Map<String,Object>dataMap){
        String pc=request.getParameter("pc");
        dataMap.put("pc",pc);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/job/detail";
    }

    @RequestMapping(value = "/detailPage",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<MemberProductBack>> detailPage(HttpServletRequest request,Map<String, Object> dataMap){
        HttpJsonResult<List<MemberProductBack>> jsonResult = new HttpJsonResult<>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberProductBack>> serviceResult = memberProductBackService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;

    }
}
