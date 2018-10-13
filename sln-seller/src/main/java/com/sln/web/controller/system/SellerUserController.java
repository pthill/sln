package com.sln.web.controller.system;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.seller.SellerUser;
import com.sln.service.seller.ISellerRolesService;
import com.sln.service.seller.ISellerService;
import com.sln.service.seller.ISellerUserService;
import com.sln.service.supplier.ISupplierService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商家用户管理controller
 *
 * @Filename: SellerUserController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/system/sellerUser")
@Scope("prototype")
public class SellerUserController extends BaseController {
    Logger                      log = Logger.getLogger(this.getClass());
    @Resource
    private ISellerUserService  sellerUserService;
    @Resource
    private ISellerRolesService sellerRolesService;
    @Resource
    private ISellerService      sellerService;
    @Resource
    private ISupplierService supplierservice; 

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String sellerUser(HttpServletRequest request,
                             Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/system/selleruser/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "/list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerUser>> list(HttpServletRequest request,
                                                               Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        queryMap.put("sellerId", sellerUser.getSellerId() + "");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerUser>> serviceResult = sellerUserService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SellerUser>> jsonResult = new HttpJsonResult<List<SellerUser>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    @RequestMapping(value = "/add")
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        Map<String, String> map = new HashMap<String, String>();
        map.put("q_status", "1");
        map.put("q_sellerId", sellerUser.getSellerId() + "");
        dataMap.put("roles", sellerRolesService.getSellerRoles(map, null).getResult());
        map.clear();
        map.put("sellerId", sellerUser.getSellerId()+"");
        dataMap.put("suppliers",supplierservice.getSupplierBySellerId(sellerUser.getSellerId()).getResult());
        return "seller/system/selleruser/add";
    }

    @RequestMapping(value = "/edit")
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, Integer id) {
        ServiceResult<SellerUser> serviceResult = sellerUserService.getSellerUserById(id);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if(!serviceResult.getSuccess()){
        	return "seller/500";
        }
        if(serviceResult.getResult().getSupplierId()!=null){
            dataMap.put("suppliers",supplierservice.getSupplierBySellerId(sellerUser.getSellerId()).getResult());
        }
        dataMap.put("roleType",sellerRolesService.getSellerRolesById(serviceResult.getResult().getRoleId()).getResult().getRoleType());
        dataMap.put("roleName",sellerRolesService.getSellerRolesById(serviceResult.getResult().getRoleId()).getResult().getRolesName());
        dataMap.put("admin", serviceResult.getResult());
        return "seller/system/selleruser/edit";
    }


    /**
     * 冻结管理员账号
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "/freeze")
    public void freeze(HttpServletRequest request, HttpServletResponse response, Integer id) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
        	pw = response.getWriter();
            SellerUser admin = sellerUserService.getSellerUserById(id).getResult();
            if(admin == null){
            	msg = "获取数据失败，请重试";
            }else if(!admin.getSellerId().equals(sellerUser.getSellerId())){
            	msg = "您无权操作该数据";
            }else{
            	admin.setState(SellerUser.STATE_FREEZE);
                sellerUserService.updateSellerUser(admin);
                
                msg = "账号[" + admin.getName() + "]已被冻结";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 解冻管理员账号
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "/unfreeze")
    public void unFreeze(HttpServletRequest request, HttpServletResponse response, Integer id) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
        	pw = response.getWriter();
            SellerUser admin = sellerUserService.getSellerUserById(id).getResult();
            if(admin == null){
            	msg = "获取数据失败，请重试";
            }else if(!admin.getSellerId().equals(sellerUser.getSellerId())){
            	msg = "您无权操作该数据";
            }else{
            	admin.setState(SellerUser.STATE_NORM);
                sellerUserService.updateSellerUser(admin);
                
                msg = "账号[" + admin.getName() + "]已解冻";
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "/del")
    public void del(HttpServletRequest request, HttpServletResponse response, Integer id) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        response.setContentType("text/plain;charset=utf-8");
        PrintWriter pw = null;
        String msg = "";
        try {
        	pw = response.getWriter();
            SellerUser admin = sellerUserService.getSellerUserById(id).getResult();
            if(admin == null){
            	msg = "获取数据失败，请重试";
            }else if(!admin.getSellerId().equals(sellerUser.getSellerId())){
            	msg = "您无权操作该数据";
            }else{
            	admin.setState(SellerUser.STATE_DEL);
                sellerUserService.updateSellerUser(admin);
                
                msg = "已删除此账号,此账号将无法继续登录管理系统";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 保存
     * @param request
     * @param response
     * @param admin
     * @param dataMap
     */
    @RequestMapping(value = "/save",method = {RequestMethod.GET})
    public void save(HttpServletRequest request, HttpServletResponse response, SellerUser admin,
                     Map<String, Object> dataMap) {
        response.setContentType("text/plain;charset=utf-8");
        ServiceResult<Integer> serviceResult = null;
        String msg = "";
        PrintWriter pw = null;
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        try {
            admin.setCreateTime(new Date());
            admin.setCreateId(sellerUser.getId());
            admin.setUpdateId(sellerUser.getId());
            admin.setUpdateTime(new Date());
            //初始密码
            admin.setPassword(Md5.getMd5String("123456"));
            // 用户的name自动生成
            Seller sellerDb = sellerService.getSellerById(sellerUser.getSellerId()).getResult();
            Map<String, String> map = new HashMap<String, String>();
            map.put("sellerId", sellerUser.getSellerId() + "");
            List<SellerUser> result = sellerUserService.page(map, null).getResult();
            String name = sellerDb.getName() + "-";
            if (result == null || result.size() == 0) {
                name += "1";
            } else {
                name += (result.size() + 1);
            }
            admin.setName(name);
            admin.setSellerId(sellerDb.getId());
            serviceResult = sellerUserService.saveSellerUser(admin);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
            pw = response.getWriter();
        } catch (Exception e) {
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    @RequestMapping(value = "/update",method = {RequestMethod.GET})
    public void update(HttpServletRequest request, HttpServletResponse response, SellerUser admin,
                       Map<String, Object> dataMap){
        response.setContentType("text/plain;charset=utf-8");
        ServiceResult<Integer> serviceResult = null;
        String msg = "";
        PrintWriter pw = null;
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        try {
            admin.setUpdateId(sellerUser.getId());
            admin.setUpdateTime(new Date());
            if (!StringUtil.isEmpty(admin.getPassword(), true)) {
                admin.setPassword(Md5.getMd5String(admin.getPassword()));
            } else {
                admin.setPassword(null);
            }
            serviceResult = sellerUserService.updateSellerUser(admin);
            if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
            msg = serviceResult.getMessage();
            pw = response.getWriter();
        }catch (Exception e){
            e.printStackTrace();
            msg = e.getMessage();
        }
        pw.print(msg);
    }

    /**
     * 修改密码界面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "editpwd", method = { RequestMethod.GET })
    public String updatePassword(Map<String, Object> dataMap) throws Exception {
        return "seller/system/selleruser/editpwd";
    }

    /**
     * 修改密码
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "editpwd/update", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> configUpdate(HttpServletRequest request,
                                                              Map<String, Object> dataMap) {
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newPassword");
        String newPasswordCfm = request.getParameter("newPasswordCfm");
        if (StringUtil.isEmpty(oldPassword, true) || StringUtil.isEmpty(newPassword, true)
            || StringUtil.isEmpty(newPasswordCfm, true)) {
            return new HttpJsonResult<Boolean>("密码不能为空，请重新输入！");
        }
        if (!newPassword.equals(newPasswordCfm)) {
            return new HttpJsonResult<Boolean>("新密码与确认密码不一致，请重新输入！");
        }
        if (oldPassword.equals(newPassword)) {
            return new HttpJsonResult<Boolean>("新密码与旧密码不能相同，请重新输入！");
        }

        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();

        SellerUser sellerUser = WebSellerSession.getSellerUser(request);

        ServiceResult<SellerUser> sellerUserRlt = sellerUserService
            .getSellerUserById(sellerUser.getId());
        if (!sellerUserRlt.getSuccess()) {
            return new HttpJsonResult<Boolean>(sellerUserRlt.getMessage());
        }
        SellerUser sellerUserDb = sellerUserRlt.getResult();

        String oldPasswordMd5 = Md5.getMd5String(oldPassword);
        if (!oldPasswordMd5.equals(sellerUserDb.getPassword())) {
            return new HttpJsonResult<Boolean>("旧密码输入错误，请重新输入！");
        }

        SellerUser sellerUserNew = new SellerUser();
        sellerUserNew.setId(sellerUser.getId());
        sellerUserNew.setPassword(Md5.getMd5String(newPassword));

        ServiceResult<Integer> serviceResult = sellerUserService.updateSellerUser(sellerUserNew);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult.setMessage(serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(true);
        return jsonResult;
    }
    /****
     * 获取根据角色id获取资源
     * @param roleId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getRoleTypeById", method = { RequestMethod.POST })
    public @ResponseBody ServiceResult<SellerRoles> getRoleTypeById(Integer roleId) throws Exception {
    	ServiceResult<SellerRoles> serviceResult = null;
    	if(roleId!=null){
    		 serviceResult = sellerRolesService.getSellerRolesById(roleId);
    		if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
    	}else{
    		 throw new BusinessException(serviceResult.getMessage());
    	}
		return serviceResult;
    }

}
