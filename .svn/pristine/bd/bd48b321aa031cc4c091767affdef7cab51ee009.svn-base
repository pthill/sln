package com.sln.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.HttpJsonResultForAjax;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.group.ActGroupType;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.promotion.IActGroupService;

/**
 * 团购类型相关处理
 *                       
 * @Filename: AdminActGroupTypeController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/promotion/grouptype")
public class AdminActGroupTypeController extends BaseController {

    @Resource
    private IActGroupService actGroupService;

    /**
     * 团购类型列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/group/grouptypelist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActGroupType>> list(HttpServletRequest request,
                                                                 HttpServletResponse response,
                                                                 Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActGroupType>> serviceResult = actGroupService.getActGroupTypes(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActGroupType> list = serviceResult.getResult();

        HttpJsonResult<List<ActGroupType>> jsonResult = new HttpJsonResult<List<ActGroupType>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        return "admin/promotion/group/grouptypeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActGroupType actGroupType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actGroupType.setCreateId(user.getId());
        actGroupType.setCreateName(user.getName());

        actGroupType.setState(ActGroupType.ACTGROUPTYPE_STATE_0);

        ServiceResult<Integer> serviceResult = actGroupService.saveActGroupType(actGroupType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupType", actGroupType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/grouptypeadd";
            }
        }
        return "redirect:/admin/promotion/grouptype";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ActGroupType> serviceResult = actGroupService.getActGroupTypeById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("actGroupType", serviceResult.getResult());
        return "admin/promotion/group/grouptypeedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActGroupType actGroupType, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        actGroupType.setUpdateId(user.getId());
        actGroupType.setUpdateName(user.getName());

        ServiceResult<Integer> serviceResult = actGroupService.updateActGroupType(actGroupType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actGroupType", actGroupType);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/group/grouptypeadd";
            }
        }
        return "redirect:/admin/promotion/grouptype";
    }

    /**
     * 删除团购分类
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.delActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 团购分类启用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.auditYesActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 团购分类停用
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditNo", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditNo(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        ServiceResult<Boolean> serviceResult = actGroupService.auditNoActGroupType(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
