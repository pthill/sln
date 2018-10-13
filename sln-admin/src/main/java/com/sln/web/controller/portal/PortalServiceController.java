package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.portal.PortalService;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;
import com.sln.service.portal.IPortalMenuService;
import com.sln.service.portal.IPortalServiceService;
import com.sln.service.system.ISystemRolesService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/admin/portal/service")
public class PortalServiceController extends BaseController {
     @Resource
     private IPortalServiceService portalServiceService;
     @Resource
     private IPortalMenuService    portalMenuService;
     @Resource
     private ISystemRolesService  systemRolesService;

     @RequestMapping(value = "",method = {RequestMethod.GET})
     public String init(Map<String,Object> dataMap){
         dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
         return "/admin/portal/service/list";
     }

     @RequestMapping(value = "/list",method = { RequestMethod.POST})
     @ResponseBody
     public HttpJsonResult list(HttpServletRequest request,Map<String,Object>dataMap){
         HttpJsonResult<List<PortalService>> jsonResult=new HttpJsonResult<List<PortalService>>();
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<PortalService>> serviceResult = portalServiceService.page(queryMap,pager);
         if (!serviceResult.getSuccess()) {
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             } else {
                 throw new BusinessException(serviceResult.getMessage());
             }
         }
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
     }


     @RequestMapping(value = "/add",method = {RequestMethod.GET})
     public String add(Map<String, Object> dataMap,HttpServletRequest request){
         Map<String,String>map=new HashMap<>();
         SystemAdmin admin=WebAdminSession.getAdminUser(request);
         map.put("adminId",admin.getId().toString());
         ServiceResult<SystemRoles> rolesResult=systemRolesService.getSystemRolesById(admin.getRoleId());
         if(!rolesResult.getSuccess()){
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(rolesResult.getCode())) {
                 throw new RuntimeException(rolesResult.getMessage());
             } else {
                 throw new BusinessException(rolesResult.getMessage());
             }
         }
         if(rolesResult.getResult().getRoleType().equals("0")){//当前是业务管理方
             dataMap.put("pid",portalServiceService.ListService(admin.getParkId()).getResult());
         }else{
             dataMap.put("pid",portalServiceService.ListService(null).getResult());
         }
         dataMap.put("menu",portalMenuService.page(map,null).getResult());
         return "admin/portal/service/add";
     }

     @RequestMapping(value = "/create",method = {RequestMethod.POST})
     @ResponseBody
     public HttpJsonResult create(Map<String,Object>dataMap,MultipartHttpServletRequest request,PortalService portalService){
         HttpJsonResult jsonResult=new HttpJsonResult();
         SystemAdmin user = WebAdminSession.getAdminUser(request);
         if (null == user) {
             jsonResult.setMessage("登录超时，请重新登录！");
             jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
             return jsonResult;
         }
         try{//获取上传图片
             MultipartFile serviceImg= request.getFile("serviceImg");
             String url = this.imgPath(serviceImg,request);
             portalService.setImg(url);
             log.info("门户服务管理上传路径"+url);
         }catch (IOException e){
             e.printStackTrace();
             log.info("门户服务管理上传图片失败");
             jsonResult.setMessage("门户服务管理上传图片失败");
             jsonResult.setData(0);
             return jsonResult;
         }
         ServiceResult<Integer> serviceResult =portalServiceService.savePortalService(portalService);
         if (!serviceResult.getSuccess()) {
             dataMap.put("portalService", portalService);
             jsonResult.setMessage(serviceResult.getMessage());
             jsonResult.setData(0);
             return jsonResult;
         }
         jsonResult.setData(1);
         jsonResult.setMessage(serviceResult.getMessage());
         return jsonResult;
     }

     @RequestMapping(value = "/edit",method = {RequestMethod.GET})
     public String edit(Integer id,Map<String,Object>dataMap,HttpServletRequest request){
         ServiceResult<PortalService> serviceResult=portalServiceService.getPortalServiceById(id);
         SystemAdmin admin=WebAdminSession.getAdminUser(request);
         if (!serviceResult.getSuccess()) {
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             }
         }
         Map<String,String>map=new HashMap<>();
         map.put("adminId",admin.getId().toString());
         ServiceResult<SystemRoles> rolesResult=systemRolesService.getSystemRolesById(admin.getRoleId());
         if(!rolesResult.getSuccess()){
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(rolesResult.getCode())) {
                 throw new RuntimeException(rolesResult.getMessage());
             } else {
                 throw new BusinessException(rolesResult.getMessage());
             }
         }
         if(rolesResult.getResult().getRoleType().equals("0")){//当前是业务管理方
             dataMap.put("pid",portalServiceService.ListService(admin.getParkId()).getResult());
         }else{
             dataMap.put("pid",portalServiceService.ListService(null).getResult());
         }
         dataMap.put("menu",portalMenuService.page(map,null).getResult());
         dataMap.put("service",serviceResult.getResult());
         return "admin/portal/service/edit";
     }

     @RequestMapping(value = "/update",method = {RequestMethod.POST})
     @ResponseBody
     public HttpJsonResult update(PortalService portalService,Map<String,Object>dataMap,MultipartHttpServletRequest request)throws IOException{
         HttpJsonResult jsonResult=new HttpJsonResult();
         MultipartFile  serviceImg= request.getFile("serviceImg");
         if(serviceImg!=null&& serviceImg.getSize() > 0){
             String imgUrl = this.imgPath(serviceImg,request);
             portalService.setImg(imgUrl);
         }
         ServiceResult<Integer> serviceResult=portalServiceService.updatePortalService(portalService);
         if(!serviceResult.getSuccess()){
             dataMap.put("service",portalService);
             dataMap.put("message",serviceResult.getMessage());
             jsonResult.setMessage(serviceResult.getMessage());
             jsonResult.setData(0);
             return jsonResult;
         }
         jsonResult.setData(1);
         dataMap.put("message",serviceResult.getMessage());
         jsonResult.setMessage(serviceResult.getMessage());
         return jsonResult;
     }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=portalServiceService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("服务管理修改状态失败");
        }
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/isShow",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> isShow(Integer id,String isShow){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalServiceService.isShow(id,isShow);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("服务管理更新显示状态失败");
        }
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer>  del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalServiceService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
        }
        jsonResult.setMessage(serviceResult.getMessage());
        jsonResult.setData(1);
        return jsonResult;
    }

    private String buildImgPath(HttpServletRequest request) {
        String path = "upload";
        SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
        path += "/" + formater.format(new Date());
        path = request.getRealPath(path);
        File dir = new File(path);
        if (!dir.exists()) {
            try {
                dir.mkdirs();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
        log.info("构建好的路径是"+path);
        return path;
    }
    private String imgPath(MultipartFile file,MultipartHttpServletRequest request) throws IOException{
        if (null != file && file.getSize() > 0) {
            String originalFilename = file.getOriginalFilename();
            File tmpFile = new File(
                    buildImgPath(request) + "/" + UUID.randomUUID() + originalFilename
                            .substring(originalFilename.lastIndexOf("."),
                                    originalFilename.length()));
            if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(tmpFile));
                stream.write(bytes);
                stream.close();
            }
            return UploadUtil.getInstance().sellerApplyUploaderImage(tmpFile, request, true);
        }else{
            return "";
        }
    }

}
