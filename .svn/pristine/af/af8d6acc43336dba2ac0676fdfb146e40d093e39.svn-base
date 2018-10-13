package com.sln.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.entity.system.Regions;
import com.sln.service.system.IRegionsService;

/**
 * 区域controller
 *
 * @Filename: RegionController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class RegionController extends BaseController {

    private static Logger   log = LogManager.getLogger(RegionController.class);

    @Resource
    private IRegionsService regionsService;

    @RequestMapping(value = "getRegionByParentId", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Regions>> getRegionByParentId(Integer parentId,
                                                                           HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           Map<String, Object> dataMap) {

        ServiceResult<List<Regions>> serviceResult = regionsService.getRegionsByParentId(parentId);
        if (!serviceResult.getSuccess()) {
            log.error("[RegionController][getRegionByParentId]根据父ID获取区域信息失败："
                      + serviceResult.getMessage());
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Regions>> jsonResult = new HttpJsonResult<List<Regions>>();
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }
}
