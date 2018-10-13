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
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.group.ActGroup;
import com.sln.entity.group.ActGroupType;
import com.sln.entity.product.Product;
import com.sln.entity.seller.Seller;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.product.IProductService;
import com.sln.service.promotion.IActGroupService;
import com.sln.service.seller.ISellerService;

@Controller
@RequestMapping(value = "admin/promotion/actgroup")
public class AdminActGroupController extends BaseController {

    @Resource
    private IActGroupService actGroupService;

    @Resource
    private IProductService  productService;

    @Resource
    private ISellerService   sellerService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        return "admin/promotion/group/grouplist";
    }

    /**
     * 团购列表页
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ActGroup>> list(HttpServletRequest request,
                                                             HttpServletResponse response,
                                                             Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ActGroup>> serviceResult = actGroupService.getActGroups(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ActGroup>> jsonResult = new HttpJsonResult<List<ActGroup>>();
        List<ActGroup> actGroups = serviceResult.getResult();
        for (ActGroup actGroup : actGroups) {
            ServiceResult<Product> resultProduct = productService.getProductById(actGroup
                .getProductId());
            actGroup.setProductName(resultProduct.getResult().getName1());

            ServiceResult<Seller> sellerResult = sellerService
                .getSellerById(actGroup.getSellerId());
            actGroup.setSellerName(sellerResult.getResult().getSellerName());

            ServiceResult<ActGroupType> resultActGroupType = actGroupService
                .getActGroupTypeById(actGroup.getType());
            actGroup.setTypeName(resultActGroupType.getResult().getName());
        }

        jsonResult.setRows(actGroups);
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(int id, Map<String, Object> dataMap) {
        ServiceResult<ActGroup> serviceResult = actGroupService.getActGroupById(id);

        ActGroup actGroup = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actGroup
            .getProductId());
        Product product = resultProduct.getResult();
        actGroup.setProductName(product.getName1());

        ServiceResult<ActGroupType> resultActGroupType = actGroupService
            .getActGroupTypeById(actGroup.getType());
        actGroup.setTypeName(resultActGroupType.getResult().getName());

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        ServiceResult<Seller> sellerResult = sellerService.getSellerById(actGroup.getSellerId());
        actGroup.setSellerName(sellerResult.getResult().getSellerName());

        dataMap.put("actGroup", actGroup);

        return "admin/promotion/group/groupedit";
    }

    /**
     * 查看
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "look", method = { RequestMethod.GET })
    public String look(int id, Map<String, Object> dataMap) {
        ServiceResult<ActGroup> serviceResult = actGroupService.getActGroupById(id);

        ActGroup actGroup = serviceResult.getResult();
        ServiceResult<Product> resultProduct = productService.getProductById(actGroup
            .getProductId());
        Product product = resultProduct.getResult();
        actGroup.setProductName(product.getName1());

        ServiceResult<List<ActGroupType>> result = actGroupService.getActGroupTypesAll();
        dataMap.put("actGroupTypes", result.getResult());

        ServiceResult<Seller> sellerResult = sellerService.getSellerById(actGroup.getSellerId());
        actGroup.setSellerName(sellerResult.getResult().getSellerName());

        dataMap.put("actGroup", actGroup);

        return "admin/promotion/group/grouplook";
    }

    /**
     * 更新
     * @param activityBidding
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    public String update(ActGroup actGroup, HttpServletRequest request, Map<String, Object> dataMap) {
        ServiceResult<ActGroup> serviceResult = actGroupService.getActGroupById(actGroup.getId());

        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);

        ActGroup actGroupNew = serviceResult.getResult();
        actGroupNew.setIsBest(actGroup.getIsBest());
        actGroupNew.setSortNum(actGroup.getSortNum());
        actGroupNew.setAuditId(adminUser.getId());
        actGroupNew.setAuditName(adminUser.getName());
        actGroupNew.setVirtualSaleNum(actGroup.getVirtualSaleNum());

        ServiceResult<Integer> result = actGroupService.updateActGroup(actGroupNew);
        if (!result.getSuccess()) {
            return "redirect:edit";
        }

        return "redirect:/admin/promotion/actgroup";
    }

    /**
     * 审核通过
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditYes", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditYes(HttpServletRequest request,
                                                         @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        String auditOpinion = request.getParameter("auditOpinion");
        if (StringUtil.isEmpty(auditOpinion, true)) {
            auditOpinion = "审核通过";
        }
        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupStateAndAuditOpinion(
            id, ActGroup.STATE_3, auditOpinion);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 审核撤回
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "auditNo", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> auditNo(HttpServletRequest request,
                                                        @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        String auditOpinion = request.getParameter("auditOpinion");
        if (StringUtil.isEmpty(auditOpinion, true)) {
            return new HttpJsonResult<Object>("请填写驳回原因。");
        }

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupStateAndAuditOpinion(
            id, ActGroup.STATE_4, auditOpinion);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 结束
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "releaseFinal", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> releaseFinal(HttpServletRequest request,
                                                             @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        ServiceResult<Boolean> serviceResult = actGroupService.updateActGroupActivityState(id,
            ActGroup.ACTIVITY_STATE_3);

        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
}
