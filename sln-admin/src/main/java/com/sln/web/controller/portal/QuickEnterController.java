package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.Park;
import com.sln.entity.portal.QuickEnter;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalServiceService;
import com.sln.service.portal.IQuickEnterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/portal/enter")
public class QuickEnterController {
    @Resource
    private IQuickEnterService quickEnterService;
    @Resource
    private IPortalServiceService portalServiceService;
    @Resource
    private IParkService parkService;

    @RequestMapping(value = "/list",method = { RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<QuickEnter>> list(HttpServletRequest request,
                                                 Map<String, Object> dataMap){
        HttpJsonResult<List<QuickEnter>> jsonResult=new HttpJsonResult<List<QuickEnter>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<QuickEnter>> serviceResult = quickEnterService.page(queryMap,pager);
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

    @RequestMapping(value = "/add",method = {RequestMethod.GET})
    public String add(Map<String,Object>dataMap,HttpServletRequest request){
        String index=request.getParameter("index");
        ServiceResult<List<Park>> parks=parkService.getParkList();
        dataMap.put("parks",parks.getResult());
        dataMap.put("index",index);
        dataMap.put("service",portalServiceService.ListService(null).getResult());
        return "admin/portal/index/enter/add";
    }
    @RequestMapping(value = "/create",method = {RequestMethod.POST})
    public String create(QuickEnter quickEnter,Map<String,Object>dataMap,HttpServletRequest request,RedirectAttributes attributes){
        String index=request.getParameter("index");
        ServiceResult<Integer> serviceResult = quickEnterService.saveQuickEnter(quickEnter);
        if (!serviceResult.getSuccess()) {
            ServiceResult<List<Park>> parks=parkService.getParkList();
            dataMap.put("parks",parks.getResult());
            dataMap.put("quickEnter", quickEnter);
            dataMap.put("index",index);
            dataMap.put("message",serviceResult.getMessage());
            dataMap.put("service",portalServiceService.ListService(null).getResult());
            return "admin/portal/index/enter/add";
        }
        attributes.addFlashAttribute("index",index);
        attributes.addFlashAttribute("message",serviceResult.getMessage());
        return "redirect:/admin/portal/index";
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.GET})
    public String edit(Integer id,Map<String,Object>dataMap,HttpServletRequest request){
        ServiceResult<QuickEnter> serviceResult=quickEnterService.getQuickEnterById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        ServiceResult<List<Park>> parks=parkService.getParkList();
        dataMap.put("parks",parks.getResult());
        String index=request.getParameter("index");
        dataMap.put("index",index);
        dataMap.put("enter",serviceResult.getResult());
        dataMap.put("service",portalServiceService.ListService(null).getResult());
        return "admin/portal/index/enter/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    public String update(QuickEnter quickEnter,Map<String,Object>dataMap,HttpServletRequest request,RedirectAttributes attributes){
        String index=request.getParameter("index");
        ServiceResult<Integer> serviceResult = quickEnterService.updateQuickEnter(quickEnter);
        if (!serviceResult.getSuccess()) {
            ServiceResult<List<Park>> parks=parkService.getParkList();
            dataMap.put("parks",parks.getResult());
            dataMap.put("enter", serviceResult.getResult());
            dataMap.put("message",serviceResult.getMessage());
            dataMap.put("service",portalServiceService.ListService(null).getResult());
            return "admin/portal/index/enter/edit";
        }
        attributes.addFlashAttribute("message",serviceResult.getMessage());
        return "redirect:/admin/portal/index?index="+index;
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=quickEnterService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("更新状态失败！！");
        }
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer>  del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=quickEnterService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
        }
        jsonResult.setMessage("删除成功!");
        jsonResult.setData(1);
        return jsonResult;
    }


}
