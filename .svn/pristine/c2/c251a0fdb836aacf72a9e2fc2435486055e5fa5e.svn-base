package com.sln.web.controller.operate;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.operate.OperationManager;
import com.sln.service.operate.IOperationManagerService;
import com.sln.service.operate.IParkService;
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
@RequestMapping(value = "admin/operation/manager")
public class AdminManagerController extends BaseController {

    @Resource(name = "managerService")
    private IOperationManagerService managerService;
    @Resource(name = "parkService")
    private IParkService             parkService;

    @RequestMapping(value = "", method = { RequestMethod.GET})
    public String index(Map<String, Object> dataMap){
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/operate/manager/managerlist";
    }

    @RequestMapping(value = "list",method = {RequestMethod.GET})
    @ResponseBody
    public HttpJsonResult<List<OperationManager>> list(HttpServletRequest request, Map<String, Object> dataMap){
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<OperationManager>> serviceResult = managerService.getOperationManagers(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<List<OperationManager>> jsonResult = new HttpJsonResult<List<OperationManager>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }


    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap,HttpServletRequest request){
        dataMap.put("parks",parkService.getParkList());
        return "admin/operate/manager/manageradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(OperationManager manager, HttpServletRequest request, Map<String, Object> dataMap){
        ServiceResult<Integer> serviceResult = managerService.save(manager);
        if (!serviceResult.getSuccess()) {
            dataMap.put("manager", manager);
            dataMap.put("parks",parkService.getParkList());
            dataMap.put("message", serviceResult.getMessage());
            return "admin/operate/manager/manageradd";
        }
        return "redirect:/admin/operation/manager";
    }
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id,Map<String, Object> dataMap){
        ServiceResult<OperationManager> serviceResult=managerService.getOperationMemberById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("parks",parkService.getParkList());
        dataMap.put("manager",serviceResult.getResult());
        return "admin/operate/manager/manageredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(OperationManager manager, Map<String, Object> dataMap){
        ServiceResult<Boolean> serviceResult = managerService.update(manager);
        if (!serviceResult.getSuccess()) {
            dataMap.put("manager", manager);
            dataMap.put("parks",parkService.getParkList());
            dataMap.put("message", serviceResult.getMessage());
            return "admin/operate/manager/manageredit";
        }
        return "redirect:/admin/operation/manager";
    }

    @RequestMapping(value = "off",method = {RequestMethod.POST})
    @ResponseBody
    public  HttpJsonResult<Boolean> off(Integer id,String status) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = managerService.updateStatus(id,status);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>("更新状态出现错误，请重试");
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        jsonResult.setData(true);
        jsonResult.setMessage("禁用成功！");
        return jsonResult;
    }

    @RequestMapping(value = "on",method = {RequestMethod.POST})
    @ResponseBody
    public  HttpJsonResult<Boolean> on(Integer id,String status) throws Exception {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = managerService.updateStatus(id,status);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>("更新状态出现错误，请重试");
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        jsonResult.setData(true);
        jsonResult.setMessage("启用成功！");
        return jsonResult;
    }

}
