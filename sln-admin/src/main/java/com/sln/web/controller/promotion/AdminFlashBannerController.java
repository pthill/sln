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
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.flash.ActFlashBanner;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.promotion.IActFlashBannerService;

/**
 * 限时抢购首页轮播图管理
 *                       
 * @Filename: AdminActFlashBannerController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
@RequestMapping(value = "admin/actflashbanner/banner")
public class AdminFlashBannerController extends BaseController {

    @Resource
    private IActFlashBannerService actFlashBannerService;

    /**
     * PC端首页轮播图列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/promotion/flash/actflashbannerlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActFlashBanner>> list(HttpServletRequest request,
                                                                   HttpServletResponse response,
                                                                   Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<ActFlashBanner>> serviceResult = actFlashBannerService
            .getActFlashBanners(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ActFlashBanner> list = serviceResult.getResult();

        HttpJsonResult<List<ActFlashBanner>> jsonResult = new HttpJsonResult<List<ActFlashBanner>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/promotion/flash/actflashbanneradd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(ActFlashBanner actFlashBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        actFlashBanner.setCreateUserId(userId);
        actFlashBanner.setCreateUserName(adminUser.getName());
        actFlashBanner.setUpdateUserId(adminUser.getId());
        actFlashBanner.setUpdateUserName(adminUser.getName());

        actFlashBanner.setState(ConstantsEJS.YES_NO_0);

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actFlashBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actFlashBannerService
            .saveActFlashBanner(actFlashBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFlashBanner", actFlashBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/flashbanneradd";
            }
        }
        return "redirect:/admin/actflashbanner/banner";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActFlashBanner> serviceResult = actFlashBannerService
            .getActFlashBannerById(id);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/flashbannerlist";
            }
        }
        ActFlashBanner actFlashBanner = serviceResult.getResult();

        dataMap.put("actFlashBanner", actFlashBanner);
        return "admin/promotion/flash/actflashbanneredit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActFlashBanner actFlashBanner, HttpServletRequest request,
                         Map<String, Object> dataMap) {
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        actFlashBanner.setUpdateUserId(adminUser.getId());
        actFlashBanner.setUpdateUserName(adminUser.getName());

        // 上传图片
        String image = UploadUtil.getInstance().advUploadFile2ImageServer("imageFile", request);
        if (image != null && !"".equals(image)) {
            actFlashBanner.setImage(image);
        }

        ServiceResult<Boolean> serviceResult = actFlashBannerService
            .updateActFlashBanner(actFlashBanner);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("actFlashBanner", actFlashBanner);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/promotion/flash/actflashbanneredit";
            }
        }
        return "redirect:/admin/actflashbanner/banner";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<ActFlashBanner> actFlashBannerResult = actFlashBannerService
            .getActFlashBannerById(id);
        if (!actFlashBannerResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(actFlashBannerResult.getMessage());
        }
        if (actFlashBannerResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("轮播图信息获取失败");
        }
        if (actFlashBannerResult.getResult().getState().equals(ConstantsEJS.YES_NO_1)) {
            return new HttpJsonResult<Boolean>("正在使用的轮播图不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = actFlashBannerService.deleteActFlashBanner(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "up", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> up(HttpServletRequest request,
                                                   @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        ActFlashBanner actFlashBanner = new ActFlashBanner();
        actFlashBanner.setId(id);
        actFlashBanner.setState(ConstantsEJS.YES_NO_1);
        actFlashBanner.setUpdateUserId(adminUser.getId());
        actFlashBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actFlashBannerService
            .updateActFlashBanner(actFlashBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> down(HttpServletRequest request,
                                                     @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActFlashBanner actFlashBanner = new ActFlashBanner();
        actFlashBanner.setId(id);
        actFlashBanner.setState(ConstantsEJS.YES_NO_0);
        actFlashBanner.setUpdateUserId(adminUser.getId());
        actFlashBanner.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = actFlashBannerService
            .updateActFlashBanner(actFlashBanner);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
