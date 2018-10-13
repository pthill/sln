package com.sln.web.controller.system;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.system.SystemRoles;
import com.sln.service.system.ISystemResourcesRolesService;
import com.sln.service.system.ISystemRolesService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色管理controller
 *                       
 * @Filename: AdminRoleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/role")
public class AdminRoleController extends BaseController {
    @Resource
    private ISystemRolesService          rolesService;
    @Resource
    private ISystemResourcesRolesService resourcesRolesService;

    Logger                               log = Logger.getLogger(this.getClass());

    /**
     * 验证角色编码不重复
     * @param request
     * @param response
     * @param roleCode
     */
    @RequestMapping(value = "validateRole", method = { RequestMethod.POST })
    public void validateRole(HttpServletRequest request, HttpServletResponse response,
                             String roleCode) {
        response.setContentType("text/plain");
        boolean isValid = true;
        PrintWriter pw = null;

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("roleCode", roleCode);
            ServiceResult<List<SystemRoles>> serviceResult = rolesService.page(map, null);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            isValid = serviceResult.getResult().size() == 0;

            pw = response.getWriter();
        } catch (IOException e) {
            isValid = false;
            e.printStackTrace();
        }
        pw.print(isValid);
    }

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "admin/system/role/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<List<SystemRoles>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SystemRoles>> serviceResult = rolesService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SystemRoles>> jsonResult = new HttpJsonResult<List<SystemRoles>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "role2Res", method = { RequestMethod.GET })
    public String role2Res(Integer id, Map<String, Object> dataMap) {
        ServiceResult<SystemRoles> role=rolesService.getSystemRolesById(id);
        if(!role.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(role.getCode())) {
                throw new RuntimeException(role.getMessage());
            } else {
                throw new BusinessException(role.getMessage());
            }
        }
        dataMap.put("id", id);
        dataMap.put("roleType",role.getResult().getRoleType());
        dataMap.put("rolesName", role.getResult().getRolesName());
        return "admin/system/role/reswin";
    }

    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, SystemRoles role) {
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;
        try {
            role.setUserId(WebAdminSession.getAdminUser(request).getId());
            role.setStatus(ConstantsEJS.SYSTEM_ROLE_STATUS_1);
            ServiceResult<Integer> serviceResult = null;
            pw = response.getWriter();
            if (role.getId() != null && role.getId() != 0) {
                //编辑
                role.setUpdateTime(new Date());
                serviceResult = rolesService.updateSystemRoles(role);
            } else {
                //新增
                serviceResult = rolesService.saveSystemRoles(role);
            }
            msg=serviceResult.getMessage();
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 保存角色资源
     * @param request
     * @param response
     * @param roleId
     * @param resIds
     */
    @RequestMapping(value = "saveRoleRes", method = { RequestMethod.POST })
    public void saveRoleRes(HttpServletRequest request, HttpServletResponse response, String roleId,
                            String resIds) {
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        String[] resArr = resIds.split(",");
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            pw = response.getWriter();

            serviceResult = resourcesRolesService.save(roleId, resArr);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
        ServiceResult<Boolean> serviceResult = rolesService.del(id);

        response.setContentType("text/html;charset=utf-8");
        try {
            PrintWriter pw = response.getWriter();
            pw.print(serviceResult.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
