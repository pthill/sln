package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalMenuService;
import com.sln.service.system.ISystemRolesService;
import com.sln.web.util.WebAdminSession;
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
@RequestMapping(value = "admin/portal/menu")
public class PortalMenuController {
    @Resource
    private IPortalMenuService portalMenuService;
    @Resource
    private IParkService parkService;
    @Resource
    private ISystemRolesService systemRolesService;

    @RequestMapping(value = "" ,method = { RequestMethod.GET })
    public String init(@ModelAttribute("message")String message,Map<String, Object> dataMap){
        dataMap.put("message",message);
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "/admin/portal/menu/list";
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<PortalMenu>>list(HttpServletRequest request,
                                                Map<String, Object> dataMap){
        HttpJsonResult<List<PortalMenu>> jsonResult=new HttpJsonResult<List<PortalMenu>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
        ServiceResult<List<PortalMenu>> serviceResult = portalMenuService.page(queryMap,pager);
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

    @RequestMapping(value = "/add", method = {RequestMethod.GET})
    public String add(Map<String, Object> dataMap,HttpServletRequest request){
        SystemAdmin admin=WebAdminSession.getAdminUser(request);
        ServiceResult<SystemRoles> rolesResult=systemRolesService.getSystemRolesById(admin.getRoleId());
        if(!rolesResult.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(rolesResult.getCode())) {
                throw new RuntimeException(rolesResult.getMessage());
            } else {
                throw new BusinessException(rolesResult.getMessage());
            }
        }
        if(rolesResult.getResult().getRoleType().equals("0")){
          dataMap.put("parkId",admin.getParkId());
        }
        dataMap.put("parks",parkService.getParkList());
        return "admin/portal/menu/add";
    }

    @RequestMapping(value = "/create",method = {RequestMethod.GET})
    public String create(PortalMenu portalMenu, Map<String,Object>dataMap, HttpServletRequest request, RedirectAttributes attributes){
        String[] parkIds=request.getParameterValues("parkIds");
        ServiceResult<Boolean> serviceResult = portalMenuService.savePortalMenu(portalMenu,parkIds);
        if (!serviceResult.getSuccess()) {
            dataMap.put("portalMenu", portalMenu);
            dataMap.put("parks",parkService.getParkList());
            dataMap.put("message",serviceResult.getMessage());
            return "admin/portal/menu/add";
        }
        attributes.addFlashAttribute("message",serviceResult.getMessage());
        return "redirect:/admin/portal/menu";
    }

    @RequestMapping(value = "/edit",method = {RequestMethod.GET})
    public String edit(Map<String,Object> dataMap,Integer id,HttpServletRequest request){
        ServiceResult<PortalMenu> serviceResult=portalMenuService.getPortalMenuById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        SystemAdmin admin=WebAdminSession.getAdminUser(request);
        ServiceResult<SystemRoles> rolesResult=systemRolesService.getSystemRolesById(admin.getRoleId());
        if(!rolesResult.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(rolesResult.getCode())) {
                throw new RuntimeException(rolesResult.getMessage());
            } else {
                throw new BusinessException(rolesResult.getMessage());
            }
        }
        if(rolesResult.getResult().getRoleType().equals("0")){
            dataMap.put("parkId",admin.getParkId());
        }
        dataMap.put("menu",serviceResult.getResult());
        dataMap.put("parks",parkService.getParkList());
        return "admin/portal/menu/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.GET})
    public String update(PortalMenu portalMenu,Map<String,Object> dataMap,
                         HttpServletRequest request,RedirectAttributes attributes){
        String[] parkIds=request.getParameterValues("parkIds");
        ServiceResult<Boolean> serviceResult = portalMenuService.updatePortalMenu(portalMenu,parkIds);
        if (!serviceResult.getSuccess()) {
            dataMap.put("menu", portalMenu);
            dataMap.put("parks",parkService.getParkList());
            dataMap.put("message",serviceResult.getMessage());
            return "admin/portal/menu/edit";
        }
        attributes.addFlashAttribute("message",serviceResult.getMessage());
        return "redirect:/admin/portal/menu";
    }
    /**
     * 检查编号是否重复
     * @param code
     * @return
     */
    @RequestMapping(value = "/isCodeExist",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult isCodeExit(String code,Integer id){
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<Integer>serviceResult=portalMenuService.isCodeExist(code,id);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult("检查排序是否重复出错，请重试");
            jsonResult.setData(-1);
            return jsonResult;
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
    /**
     * 检查排序是否重复
     * @param order
     * @return
     */
    @RequestMapping(value = "/isOrderExist",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult isOrderExit(Integer order,Integer id){
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<Integer>serviceResult=portalMenuService.isOrderExist(order,id);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Object>("检查排序是否重复出错，请重试");
            jsonResult.setData(-1);
            return jsonResult;
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 检查简称是否重复
     * @param abbreviation
     * @return
     */
    @RequestMapping(value = "/isAbbreviationExist",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult isAbbreviationExit(String abbreviation,Integer id){
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<Integer>serviceResult=portalMenuService.isAbbreviationExist(abbreviation,id);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Object>("检查简称是否重复出错，请重试");
            jsonResult.setData(-1);
            return jsonResult;
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalMenuService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
        }
        jsonResult.setMessage("删除成功");
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalMenuService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("更新启用或禁用状态失败");
        }
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/isShow",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> isShow(Integer id,String isShow){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalMenuService.isShow(id,isShow);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("更新是否显示状态失败");
        }
        jsonResult.setData(1);
        return jsonResult;
    }


}
