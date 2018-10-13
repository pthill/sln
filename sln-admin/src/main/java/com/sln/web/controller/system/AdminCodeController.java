package com.sln.web.controller.system;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;
import com.sln.web.util.freemarkerutil.CodeManager;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.system.Code;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.system.ICodeService;

/**
 * 数据字典
 *                       
 * @Filename: AdminCodeController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wangpeng@sln.com
 *
 */
@Controller
@RequestMapping(value = "admin/system/code")
public class AdminCodeController extends BaseController {

    @Resource(name = "codeService")
    private ICodeService codeService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/system/code/codelist";
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Code>> list(HttpServletRequest request,
                                                         HttpServletResponse response,
                                                         Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<Code>> serviceResult = codeService.getCodes(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Code>> jsonResult = new HttpJsonResult<List<Code>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        Code code = new Code();
        //        code.setUseYn(ConstantsJM.USE_YN_Y);
        dataMap.put("code", code);
        return "admin/system/code/codeadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(Code code, HttpServletRequest request, Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        code.setCreateUser(adminUser.getName());
        code.setCreateUserId(adminUser.getId());

        ServiceResult<Integer> serviceResult = codeService.createCode(code);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("code", code);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/system/code/codeadd";
            }
        }
        CodeManager.init();
        return "redirect:/admin/system/code";
    }

    /**
     * 
     * @param codeDiv
     * @param codeCd
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(String codeDiv, String codeCd, Map<String, Object> dataMap) {
        ServiceResult<Code> serviceResult = codeService.getCode(codeDiv, codeCd);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("code", serviceResult.getResult());
        return "admin/system/code/codeedit";
    }

    /**
     * 更新字典表
     * @param code
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(Code code, HttpServletRequest request, Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        code.setUpdateUserId(adminUser.getId());
        code.setUpdateUser(adminUser.getName());

        ServiceResult<Boolean> serviceResult = codeService.updateCode(code);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("code", code);
                return "admin/system/code/codeedit";
            }
        }
        CodeManager.init();
        return "redirect:/admin/system/code";
    }
}
