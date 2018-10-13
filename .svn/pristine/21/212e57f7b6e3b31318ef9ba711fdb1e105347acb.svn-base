package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.ParkAdvantage;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IParkAdvantageService;
import com.sln.web.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/portal/parkAdvantage")
public class ParkAdvantageController extends BaseController{
    @Resource
    private IParkAdvantageService parkAdvantageService;
    @Resource
    private IParkService parkService;

    @RequestMapping(value = "",method = { RequestMethod.GET,RequestMethod.POST})
    public String init(@ModelAttribute("message")String message,Map<String,Object>dataMap){
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("message",message);
        return "admin/portal/parkAdvantage/list";
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<ParkAdvantage>> list(HttpServletRequest request,
                                                 Map<String, Object> dataMap){
        HttpJsonResult<List<ParkAdvantage>> jsonResult=new HttpJsonResult<List<ParkAdvantage>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ParkAdvantage>> serviceResult = parkAdvantageService.page(queryMap,pager);
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
    public String add(Map<String,Object>dataMap){
        dataMap.put("parks",parkService.getParkList().getResult());
        return "admin/portal/parkAdvantage/add";
    }

    @RequestMapping(value = "/create",method = { RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult create(ParkAdvantage parkAdvantage, Map<String,Object>dataMap, RedirectAttributes attributes){
    	HttpJsonResult<Integer> result = new HttpJsonResult<Integer>();
    	
    	ServiceResult<Integer> serviceResult = parkAdvantageService.saveParkAdvantage(parkAdvantage);
        if (!serviceResult.getSuccess()) {
            result.setMessage(serviceResult.getMessage());
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.GET})
    public String edit(Map<String,Object>dataMap,Integer id){
        ServiceResult<ParkAdvantage> serviceResult=parkAdvantageService.getParkAdvantageById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("parks",parkService.getParkList().getResult());
        dataMap.put("parkAdvantage",serviceResult.getResult());
        return "admin/portal/parkAdvantage/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    public String update(ParkAdvantage parkAdvantage,Map<String,Object>dataMap,RedirectAttributes attributes){
        ServiceResult<Integer> serviceResult = parkAdvantageService.updateParkAdvantage(parkAdvantage);
        if (!serviceResult.getSuccess()) {
            dataMap.put("parks",parkService.getParkList().getResult());
            dataMap.put("parkAdvantage", parkAdvantage);
            return "admin/portal/parkAdvantage/edit";
        }
        attributes.addFlashAttribute("message",serviceResult.getMessage());
        return "redirect:/admin/portal/parkAdvantage";
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=parkAdvantageService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("更新启用或禁用状态失败!");
        }
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=parkAdvantageService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }

}
