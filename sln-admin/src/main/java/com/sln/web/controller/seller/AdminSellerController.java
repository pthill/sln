package com.sln.web.controller.seller;

import java.util.HashMap;
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
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.Seller;
import com.sln.service.seller.ISellerService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 后台商家管理controller
 * 
 * @Filename: AdminSellerController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/seller/manage")
public class AdminSellerController extends BaseController {

    @Resource(name = "sellerService")
    private ISellerService sellerService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("roleId", WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        queryMap.put("q_isContributing",Seller.IS_CONTRIBUTING1+"");
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellersByRoleId(queryMap, null);
       
        dataMap.put("contributingList", serviceResult.getResult());
        
        return "admin/seller/manage/sellerlist";
    }

    /**
     * 商家列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Seller>> list(HttpServletRequest request,
                                                           Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("roleId", WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellersByRoleId(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<Seller>> jsonResult = new HttpJsonResult<List<Seller>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    //    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    //    public String edit(HttpServletRequest request, Integer id, Map<String, Object> dataMap) {
    //        ServiceResult<Seller> serviceResult = sellerService.getSellerById(id);
    //        if (!serviceResult.getSuccess()) {
    //            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
    //                throw new RuntimeException(serviceResult.getMessage());
    //            } else {
    //                throw new BusinessException(serviceResult.getMessage());
    //            }
    //        }
    //        Seller seller = serviceResult.getResult();
    //
    //        dataMap.put("seller", seller);
    //        return "admin/seller/manage/selleredit";
    //    }
    //
    //    @RequestMapping(value = "update", method = { RequestMethod.POST })
    //    public void update(HttpServletRequest request, HttpServletResponse response, Seller seller,
    //                       Map<String, Object> dataMap) {
    //        String msg = "修改成功";
    //        PrintWriter pw = null;
    //        response.setContentType("text/plain;charset=utf-8");
    //        try {
    //            if (seller == null || seller.getId() == null) {
    //                throw new BusinessException("数据错误");
    //            }
    //            ServiceResult<Integer> result = sellerService.updateSeller(seller);
    //            if (!result.getSuccess()) {
    //                msg = result.getMessage();
    //            }
    //            pw = response.getWriter();
    //        } catch (Exception e) {
    //            msg = e.getMessage();
    //        }
    //        pw.print(msg);
    //    }

    /**
     * 冻结商家  前台商家商品屏蔽
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "freeze", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> freeze(Integer awardId, HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Integer sellerId) {
        HttpJsonResult<Boolean> jsonResult = null;
        ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerId);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        }
        if (serviceResult.getResult() == null) {
            jsonResult = new HttpJsonResult<Boolean>("该商家不存在！");
            return jsonResult;
        }
        Seller seller = serviceResult.getResult();

        if (seller.getAuditStatus() == Seller.AUDIT_STATE_3_FREEZE) {
            jsonResult = new HttpJsonResult<Boolean>("该商家已经被冻结！");
            return jsonResult;
        }

        // 执行冻结操作
        ServiceResult<Boolean> freezeResult = sellerService.freezeSeller(sellerId);
        if (!freezeResult.getSuccess() || !freezeResult.getResult()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        } else {
            jsonResult = new HttpJsonResult<Boolean>();
        }

        return jsonResult;
    }

    /**
     * 解冻商家
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "unfreeze", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> unFreeze(Integer awardId,
                                                          HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          Integer sellerId) {
        HttpJsonResult<Boolean> jsonResult = null;
        ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerId);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        }
        if (serviceResult.getResult() == null) {
            jsonResult = new HttpJsonResult<Boolean>("该商家不存在！");
            return jsonResult;
        }
        Seller seller = serviceResult.getResult();

        if (seller.getAuditStatus() != Seller.AUDIT_STATE_3_FREEZE) {
            jsonResult = new HttpJsonResult<Boolean>("该商家没有被冻结，无需解冻！");
            return jsonResult;
        }

        ServiceResult<Boolean> freezeResult = sellerService.unFreezeSeller(sellerId);
        if (!freezeResult.getSuccess() || !freezeResult.getResult()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        } else {
            jsonResult = new HttpJsonResult<Boolean>();
        }

        return jsonResult;
    }
    
    /**
     * 设置商家为自营
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "updateIsSelf", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> updateIsSelf(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          Integer sellerId) {
        HttpJsonResult<Boolean> jsonResult = null;
        ServiceResult<Seller> serviceResult = sellerService.getSellerById(sellerId);
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        }
        if (serviceResult.getResult() == null) {
            jsonResult = new HttpJsonResult<Boolean>("该商家不存在！");
            return jsonResult;
        }
        Seller seller = serviceResult.getResult();
        
        if (seller.getAuditStatus() != Seller.AUDIT_STATE_2_DONE) {
            jsonResult = new HttpJsonResult<Boolean>("该商家未审核或冻结状态，不能设置为自营店铺！");
            return jsonResult;
        }
        
        if (seller.getIsSelf()!=null && seller.getIsSelf() == ConstantsEJS.YES_NO_1) {
            jsonResult = new HttpJsonResult<Boolean>("该商家已是自营店铺，无需再设置！");
            return jsonResult;
        }

        ServiceResult<Boolean> freezeResult = sellerService.updateIsSelf(sellerId);
        if (!freezeResult.getSuccess() || !freezeResult.getResult()) {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            return jsonResult;
        } else {
            jsonResult = new HttpJsonResult<Boolean>();
        }

        return jsonResult;
    }
    
    /**
     * 获取结算主体的商家列表
     * @param request
     * @param dataMap
     * @param sellerId
     * @return
     */
    @RequestMapping(value = "getSubjectList", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<Seller>> getSubjectList(HttpServletRequest request,
                                                           Map<String, Object> dataMap,Integer sellerId) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        
        queryMap.put("roleId", WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        queryMap.put("q_isContributing",Seller.IS_CONTRIBUTING1+"");
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellersByRoleId(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        boolean isExist = false;
        for(Seller s : serviceResult.getResult()) {
        	if(s.getId() == sellerId) {
        		isExist = true;
        	}
        }
        if(!isExist) {
        	Seller seller = sellerService.getSellerById(sellerId).getResult();
            serviceResult.getResult().add(seller);
        }

        HttpJsonResult<List<Seller>> jsonResult = new HttpJsonResult<List<Seller>>();
        jsonResult.setRows(serviceResult.getResult());
        return jsonResult;
    }
    
    @RequestMapping(value = "updateSubject", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> updateSubject(HttpServletRequest request,
                                                          HttpServletResponse response,
                                                          Seller seller) {
    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
    	ServiceResult<Integer> serviceResult = sellerService.updateSeller(seller);
    	if(!serviceResult.getSuccess()) {
    		throw new BusinessException(serviceResult.getMessage());
    	}
    	
    	return jsonResult;
    }
}
