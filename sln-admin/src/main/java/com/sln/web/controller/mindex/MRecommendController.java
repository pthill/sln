package com.sln.web.controller.mindex;

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
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.mindex.MRecommend;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.mindex.IMRecommendService;

/**
 * M端推荐商品管理controller
 *
 * @Filename: MRecommendController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/mindex/recommend")
public class MRecommendController extends BaseController {

    @Resource
    private IMRecommendService mRecommendService;

    /**
     * M端推荐商品列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/mindex/recommend/mrecommendlist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MRecommend>> list(HttpServletRequest request,
                                                               HttpServletResponse response,
                                                               Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MRecommend>> serviceResult = mRecommendService.getMRecommends(queryMap,
            pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MRecommend> list = serviceResult.getResult();

        HttpJsonResult<List<MRecommend>> jsonResult = new HttpJsonResult<List<MRecommend>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "admin/mindex/recommend/mrecommendadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MRecommend mRecommend, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        mRecommend.setRecommendType(MRecommend.RECOMMEND_TYPE_1);
        mRecommend.setCreateUserId(userId);
        mRecommend.setCreateUserName(adminUser.getName());
        mRecommend.setUpdateUserId(adminUser.getId());
        mRecommend.setUpdateUserName(adminUser.getName());

        mRecommend.setStatus(MRecommend.STATUS_0);

        ServiceResult<Boolean> serviceResult = mRecommendService.saveMRecommend(mRecommend);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mRecommend", mRecommend);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/recommend/mrecommendadd";
            }
        }
        return "redirect:/admin/mindex/recommend";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mRecommendId, Map<String, Object> dataMap) {
        ServiceResult<MRecommend> serviceResult = mRecommendService.getMRecommendById(mRecommendId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/recommend/mrecommendlist";
            }
        }
        MRecommend mRecommend = serviceResult.getResult();

        dataMap.put("mRecommend", mRecommend);
        return "admin/mindex/recommend/mrecommendedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MRecommend mRecommend, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        mRecommend.setUpdateUserId(adminUser.getId());
        mRecommend.setUpdateUserName(adminUser.getName());

        ServiceResult<Boolean> serviceResult = mRecommendService.updateMRecommend(mRecommend);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mRecommend", mRecommend);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/recommend/mrecommendedit";
            }
        }
        return "redirect:/admin/mindex/recommend";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {

        ServiceResult<MRecommend> mRecommendResult = mRecommendService.getMRecommendById(id);
        if (!mRecommendResult.getSuccess()) {
            return new HttpJsonResult<Boolean>(mRecommendResult.getMessage());
        }
        if (mRecommendResult.getResult() == null) {
            return new HttpJsonResult<Boolean>("推荐商品信息获取失败");
        }
        if (mRecommendResult.getResult().getStatus().equals(MRecommend.STATUS_1)) {
            return new HttpJsonResult<Boolean>("正在使用的推荐商品不能删除");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mRecommendService.deleteMRecommend(id);
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
        MRecommend mRecommend = new MRecommend();
        mRecommend.setId(id);
        mRecommend.setStatus(MRecommend.STATUS_1);
        mRecommend.setUpdateUserId(adminUser.getId());
        mRecommend.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mRecommendService.updateMRecommend(mRecommend);
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

        MRecommend mRecommend = new MRecommend();
        mRecommend.setId(id);
        mRecommend.setStatus(MRecommend.STATUS_0);
        mRecommend.setUpdateUserId(adminUser.getId());
        mRecommend.setUpdateUserName(adminUser.getName());
        ServiceResult<Boolean> serviceResult = mRecommendService.updateMRecommend(mRecommend);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
