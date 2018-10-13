package com.sln.web.controller.mindex;

import java.util.HashMap;
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
import com.sln.entity.mindex.MIndexFloor;
import com.sln.entity.mindex.MIndexFloorData;
import com.sln.entity.product.Product;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.mindex.IMIndexService;
import com.sln.service.product.IProductService;

/**
 * 移动端首页楼层数据管理controller
 *                       
 * @Filename: MIndexFloorDataController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/mindex/floordata")
public class MIndexFloorDataController extends BaseController {

    @Resource
    private IMIndexService  mIndexService;

    @Resource
    private IProductService productService;

    /**
     * 移动端首页楼层数据列表页
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<MIndexFloor>> serviceResult = mIndexService.getMIndexFloors(queryMap,
            null);
        dataMap.put("floors", serviceResult.getResult());

        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/mindex/floordata/floordatalist";
    }

    /**
     * 分页取出数据
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MIndexFloorData>> list(HttpServletRequest request,
                                                                    HttpServletResponse response,
                                                                    Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);

        ServiceResult<List<MIndexFloorData>> serviceResult = mIndexService
            .getMIndexFloorDatas(queryMap, pager);
        pager = serviceResult.getPager();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<MIndexFloorData> list = serviceResult.getResult();

        HttpJsonResult<List<MIndexFloorData>> jsonResult = new HttpJsonResult<List<MIndexFloorData>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<MIndexFloor>> serviceResult = mIndexService.getMIndexFloors(queryMap,
            null);
        dataMap.put("floors", serviceResult.getResult());

        return "admin/mindex/floordata/floordataadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    public String create(MIndexFloorData mIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        Integer userId = adminUser.getId();
        mIndexFloorData.setCreateUserId(userId);
        mIndexFloorData.setCreateUserName(adminUser.getName());
        mIndexFloorData.setUpdateUserId(adminUser.getId());
        mIndexFloorData.setUpdateUserName(adminUser.getName());

        if (mIndexFloorData.getDataType() == MIndexFloorData.DATA_TYPE_1) {
            mIndexFloorData.setImage(null);
            mIndexFloorData.setLinkUrl(null);
        } else if (mIndexFloorData.getDataType() == MIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().mIndexUploadFile2ImageServer("imageFile",
                request);
            if (image != null && !"".equals(image)) {
                mIndexFloorData.setImage(image);
            }
            mIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = mIndexService.saveMIndexFloorData(mIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexFloorData", mIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floordata/floordataadd";
            }
        }
        return "redirect:/admin/mindex/floordata";
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int mIndexFloorDataId, Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<String, String>();
        ServiceResult<List<MIndexFloor>> floorResult = mIndexService.getMIndexFloors(queryMap,
            null);
        dataMap.put("floors", floorResult.getResult());

        ServiceResult<MIndexFloorData> serviceResult = mIndexService
            .getMIndexFloorDataById(mIndexFloorDataId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floordata/floordatalist";
            }
        }
        MIndexFloorData mIndexFloorData = serviceResult.getResult();

        if (mIndexFloorData.getDataType() == MIndexFloorData.DATA_TYPE_1) {
            ServiceResult<Product> productResult = productService
                .getProductById(mIndexFloorData.getRefId());
            mIndexFloorData.setProduct(productResult.getResult());
        }

        dataMap.put("mIndexFloorData", mIndexFloorData);
        return "admin/mindex/floordata/floordataedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(MIndexFloorData mIndexFloorData, HttpServletRequest request,
                         Map<String, Object> dataMap) {

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        mIndexFloorData.setUpdateUserId(adminUser.getId());
        mIndexFloorData.setUpdateUserName(adminUser.getName());

        if (mIndexFloorData.getDataType() == MIndexFloorData.DATA_TYPE_1) {
            mIndexFloorData.setImage("");
            mIndexFloorData.setLinkUrl("");
        } else if (mIndexFloorData.getDataType() == MIndexFloorData.DATA_TYPE_2) {
            // 上传图片
            String image = UploadUtil.getInstance().mIndexUploadFile2ImageServer("imageFile",
                request);
            if (image != null && !"".equals(image)) {
                mIndexFloorData.setImage(image);
            }
            mIndexFloorData.setRefId(0);
        }

        ServiceResult<Boolean> serviceResult = mIndexService.updateMIndexFloorData(mIndexFloorData);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("mIndexFloorData", mIndexFloorData);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/mindex/floordata/floordataedit";
            }
        }
        return "redirect:/admin/mindex/floordata";
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        ServiceResult<Boolean> serviceResult = mIndexService.deleteMIndexFloorData(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

}
