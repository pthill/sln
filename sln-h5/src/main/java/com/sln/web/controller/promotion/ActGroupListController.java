package com.sln.web.controller.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.entity.product.Product;
import com.sln.entity.group.ActGroup;
import com.sln.entity.group.ActGroupBanner;
import com.sln.entity.group.ActGroupType;
import com.sln.service.product.IProductFrontService;
import com.sln.service.promotion.IActGroupBannerService;
import com.sln.service.promotion.IActGroupService;
import com.sln.web.controller.BaseController;

/**
 * 团购
 *                       
 * @Filename: ActGroupListController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ActGroupListController extends BaseController {

    @Resource
    private IActGroupService       actGroupService;

    @Resource
    private IActGroupBannerService actGroupBannerService;

    @Resource
    private IProductFrontService   productFrontService;

    private final static int       TUAN_PAGESIZE = 20;

    @RequestMapping(value = "/tuan.html", method = RequestMethod.GET)
    public String group(HttpServletRequest request, HttpServletResponse response,
                        Map<String, Object> dataMap) {
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, TUAN_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(pager,
            type, ConstantsEJS.CHANNEL_3);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productFrontService
                .getProductById(actGroup.getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }

        ServiceResult<List<ActGroupBanner>> serviceResult = actGroupBannerService
            .getActGroupBannersPcMobile(ConstantsEJS.PC_MOBILE_MOBILE);
        List<ActGroupBanner> actGroupBanners = serviceResult.getResult();

        ServiceResult<List<ActGroupType>> serviceResultType = actGroupService
            .getActGroupTypesFront();
        List<ActGroupType> actGroupTypes = serviceResultType.getResult();
        String typeName = "全部";
        if (type != 0) {
            ActGroupType actGroupType = null;
            for (int i = 0; i < actGroupTypes.size(); i++) {
                actGroupType = actGroupTypes.get(i);
                if (actGroupType.getId().intValue() == type) {
                    typeName = actGroupType.getName();
                }
            }
        }

        dataMap.put("actGroups", actGroups);
        dataMap.put("actGroupBanners", actGroupBanners);
        dataMap.put("actGroupTypes", actGroupTypes);
        dataMap.put("type", type);
        dataMap.put("typeName", typeName);
        dataMap.put("pagesize", TUAN_PAGESIZE);

        return "h5/promotion/actgrouplist";
    }

    /**
     * 返回json结果
     * @param request
     * @param stack
     * @return
     */
    @RequestMapping(value = "/tuanJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<ActGroup>> searchJson(HttpServletRequest request,
                                                                   Map<String, Object> dataMap) {
        HttpJsonResult<List<ActGroup>> jsonResult = new HttpJsonResult<List<ActGroup>>();
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap, TUAN_PAGESIZE);
        String typeStr = request.getParameter("type");
        int type = ConvertUtil.toInt(typeStr, 0);

        ServiceResult<List<ActGroup>> serviceResultGroup = actGroupService.getActGroupsFront(pager,
            type, ConstantsEJS.CHANNEL_3);
        List<ActGroup> actGroups = serviceResultGroup.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productFrontService
                .getProductById(actGroup.getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());
        }

        jsonResult.setRows(actGroups);
        jsonResult.setTotal(actGroups.size());
        return jsonResult;
    }
}
