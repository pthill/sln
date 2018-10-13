package com.sln.web.controller.system;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.SellerResourcesRoles;
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.system.ResourceTree;
import com.sln.entity.system.SystemResources;
import com.sln.service.seller.ISellerResourcesRolesService;
import com.sln.service.seller.ISellerRolesService;
import com.sln.service.system.ISystemResourcesService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 角色管理controller
 *                       
 * @Filename: RoleController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/system/role")
public class SellerRoleController extends BaseController {
    @Resource
    private ISellerRolesService          sellerRolesService;
    @Resource
    private ISystemResourcesService      resourcesService;
    @Resource
    private ISellerResourcesRolesService sellerResourcesRolesService;
    private List<Integer>                resRoleIds = new ArrayList<Integer>();
    private List<SystemResources>        allres;
    private byte[]                       token      = new byte[0];
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
        JSONObject json = new JSONObject();

        try {
            Map<String, String> map = new HashMap<String, String>();
            map.put("q_roleCode", roleCode);
            ServiceResult<List<SellerRoles>> serviceResult = sellerRolesService.getSellerRoles(map,
                null);
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
        json.put("valid", isValid);
        pw.print(json);
    }

    /**
     * 验证角色名称不可重复
     */
    /**
     * 验证角色编码不重复
     * @param request
     * @param response
     * @param roleCode
     */
    @RequestMapping(value = "validateRoleNames", method = { RequestMethod.POST })
    public void validateRoleNames(HttpServletRequest request, HttpServletResponse response,
                             String rolesName) {
        response.setContentType("text/plain");
        boolean isValid = true;
        PrintWriter pw = null;
        JSONObject json = new JSONObject();

        try {
            ServiceResult<List<SellerRoles>> serviceResult = sellerRolesService.getSellerRolesByRolesName(rolesName);
           
            isValid =  serviceResult.getResult().size()>0 ? false:true;

            pw = response.getWriter();
        } catch (IOException e) {
            isValid = false;
            e.printStackTrace();
        }
        json.put("valid", isValid);
        pw.print(json);
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
        return "seller/system/role/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerRoles>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("q_sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerRoles>> serviceResult = sellerRolesService.getSellerRoles(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SellerRoles>> jsonResult = new HttpJsonResult<List<SellerRoles>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "role2Res", method = { RequestMethod.GET })
    public String role2Res(HttpServletRequest request, Integer id,String roleType,
                           Map<String, Object> dataMap) throws IOException{
        String rolesName = URLDecoder.decode(request.getParameter("rolesName"),"UTF-8");//后台解码
        dataMap.put("id", id);
        dataMap.put("rolesName", rolesName);
        dataMap.put("roleType", roleType);
        return "seller/system/role/reswin";
    }

    @RequestMapping(value = "save", method = { RequestMethod.POST })
    public void save(HttpServletRequest request, HttpServletResponse response, SellerRoles role) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        role.setUserId(sellerUser.getId());
        role.setStatus(SellerRoles.STATUS_1);
        role.setSellerId(sellerUser.getSellerId());
       
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        try {
        	PrintWriter pw = response.getWriter();
             ServiceResult<Integer> serviceResult = null;
             if (role.getId() != null && role.getId() != 0) {
             	ServiceResult<SellerRoles> rolesService = sellerRolesService.getSellerRolesById(Integer.valueOf(role.getId()));
                 if(!rolesService.getSuccess()){
                 	msg = rolesService.getMessage();
                 }else if(rolesService.getResult() == null){
                 	msg = "获取数据失败，请重试";
                 }else if(!rolesService.getResult().getSellerId().equals(sellerUser.getSellerId())){
                 	msg = "您无权操作该数据";
                 }else{
                	//编辑
                     role.setUpdateTime(new Date());
                     serviceResult = sellerRolesService.updateSellerRoles(role);
                     if (!serviceResult.getSuccess()) {
                         if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                             throw new RuntimeException(serviceResult.getMessage());
                         } else {
                             throw new BusinessException(serviceResult.getMessage());
                         }
                     }
                     msg = serviceResult.getMessage();
                 }
                 
             } else {
                 //新增
                 serviceResult = sellerRolesService.saveSellerRoles(role);
                 if (!serviceResult.getSuccess()) {
                     if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                         throw new RuntimeException(serviceResult.getMessage());
                     } else {
                         throw new BusinessException(serviceResult.getMessage());
                     }
                 }
                 msg = serviceResult.getMessage();
             }
             
            pw.print(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        response.setContentType("text/html;charset=utf-8");
        String msg = "";
        PrintWriter pw = null;

        String[] resArr = resIds.split(",");
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            pw = response.getWriter();
            ServiceResult<SellerRoles> rolesService = sellerRolesService.getSellerRolesById(Integer.valueOf(roleId));
            if(!rolesService.getSuccess()){
            	msg = rolesService.getMessage();
            }else if(rolesService.getResult() == null){
            	msg = "获取数据失败，请重试";
            }else if(!rolesService.getResult().getSellerId().equals(sellerUser.getSellerId())){
            	msg = "您无权操作该数据";
            }else{
            	serviceResult = sellerResourcesRolesService.save(roleId, resArr);
                if (!serviceResult.getSuccess()) {
                    if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                        throw new RuntimeException(serviceResult.getMessage());
                    } else {
                        throw new BusinessException(serviceResult.getMessage());
                    }
                }
                msg = serviceResult.getMessage();
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "del", method = { RequestMethod.GET,RequestMethod.POST })
    @ResponseBody
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) throws IOException {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
    	String msg = "操作成功";
    	ServiceResult<SellerRoles> rolesService = sellerRolesService.getSellerRolesById(id);
        if(!rolesService.getSuccess()){
        	msg = rolesService.getMessage();
        }else if(rolesService.getResult() == null){
        	msg = "获取数据失败，请重试";
        }else if(!rolesService.getResult().getSellerId().equals(sellerUser.getSellerId())){
        	msg = "您无权操作该数据";
        }else{
        	ServiceResult<Boolean> serviceResult = sellerRolesService.deleteSellerRole(id);
        	if (!serviceResult.getSuccess()) {
        		msg = serviceResult.getMessage();
            }
        }
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw = response.getWriter();
        pw.print(msg);
    }

    /*@RequestMapping(value = "roleResTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> roleResTree(HttpServletRequest request,
                                                        Map<String, Object> dataMap,
                                                        String roleId,String roleType) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<SystemResources>> serviceResult = resourcesService.getByPidSeller(ConstantsEJS.SELLER_RESOURCE_ROOT,roleType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("rolesId", roleId);
        List<SellerResourcesRoles> srrlist = sellerResourcesRolesService.page(map, null)
            .getResult();
        List<Integer> resRoleIds = new ArrayList<Integer>();
        for (SellerResourcesRoles sr : srrlist) {
            resRoleIds.add(sr.getResourcesId());
        }
        generateTree(tree, serviceResult.getResult(), resRoleIds,roleType);
        return tree;
    }*/

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     *//*
    private List<ResourceTree> generateTree(List<ResourceTree> treelist, List<SystemResources> data,
                                            List<Integer> resRoleIds,String roleType) {
        for (SystemResources sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getContent());
            if (resRoleIds != null && resRoleIds.contains(sr.getId()))
                tree.setChecked(true);
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(),
                resourcesService.getByPidSeller(sr.getId(),roleType).getResult(), resRoleIds,roleType));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }*/
    /***
     * 查询商家资源数
     * @param roleId
     * @param roleType
     * @return
     */
    
    @RequestMapping(value = "sellerTree", method = { RequestMethod.POST })
    @ResponseBody
    public  List<ResourceTree> Tree(Integer roleId,String roleType) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        resRoleIds=new ArrayList<Integer>();
        ServiceResult<List<SystemResources>> serviceResult = resourcesService.getByPidSeller(ConstantsEJS.SELLER_RESOURCE_ROOT,roleType);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        //resRoleIds里面存放的角色下资源的id
        ServiceResult<List<SystemResources>> srrlist = sellerRolesService.getResourceByRoleId(roleId);
        for (SystemResources sr : srrlist.getResult()) {
            this.resRoleIds.add(sr.getId());
        }
        synchronized (token) {
            this.allres = resourcesService.page(new HashMap<String, String>(), null).getResult();
            generateNewTree(tree, serviceResult.getResult(),roleType);
        }
        return tree;
    }
    //resRoleIds里面存放的角色下资源的id
    //allres里面存放所有的资源
    //data里面存放的是角色类型查询菜单的根节点
    private List<ResourceTree> generateNewTree(List<ResourceTree> treelist,List<SystemResources> data,String roleType) {
        for (SystemResources sr : data) {
            ResourceTree tree = new ResourceTree();
            if(sr.getScope().equals("3")){//商家和供应商的资源
                tree.setId(sr.getId());
                tree.setText(sr.getContent());
                if (this.resRoleIds != null && this.resRoleIds.contains(sr.getId()))
                    tree.setChecked(true);
                tree.setChildren(generateNewTree(new ArrayList<ResourceTree>(), getByPid(sr.getId()),roleType));
                tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
                treelist.add(tree);
            }else{
                if(roleType.equals("0")){//商家的角色
                    tree.setId(sr.getId());
                    tree.setText(sr.getContent());
                    if (this.resRoleIds != null && this.resRoleIds.contains(sr.getId()))
                        tree.setChecked(true);
                    tree.setChildren(generateNewTree(new ArrayList<ResourceTree>(), getByPid(sr.getId()),roleType));
                    tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
                    treelist.add(tree);
                }
            }
        }
        return treelist;
    }
    private List<SystemResources> getByPid(Integer pid) {
        if (this.allres == null || this.allres.size() < 1)
            return null;
        List<SystemResources> reslist = new ArrayList<SystemResources>();
        for (SystemResources res : this.allres) {
            if (res.getPid().intValue() == pid.intValue())
                reslist.add(res);
        }
        return reslist;
    }
}
