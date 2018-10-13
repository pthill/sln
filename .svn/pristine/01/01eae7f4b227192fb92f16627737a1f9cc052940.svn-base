package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.portal.PortalActive;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalActiveService;
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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "/admin/portal/active")
public class PortalActiveController extends BaseController {

    @Resource
    private IPortalActiveService portalActiveService;
    @Resource
    private IParkService parkService;

    @RequestMapping(value = "" ,method = { RequestMethod.GET})
    public String init(Map<String,Object> dataMap){
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("park",parkService.getParkList().getResult());
        return "/admin/portal/active/list";
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<PortalActive>> list(HttpServletRequest request,
                                                   Map<String, Object> dataMap){
        HttpJsonResult<List<PortalActive>> jsonResult=new HttpJsonResult<List<PortalActive>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<PortalActive>> serviceResult = portalActiveService.page(queryMap,pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return  jsonResult;
    }

    @RequestMapping(value = "/add",method = {RequestMethod.GET})
    public String add(Map<String ,Object> dataMap){
        dataMap.put("parks",parkService.getParkList());
        return "admin/portal/active/add";
    }

    @RequestMapping(value = "create",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult create(PortalActive portalActive,Map<String,Object>dataMap,MultipartHttpServletRequest request){
        HttpJsonResult jsonResult=new HttpJsonResult();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        portalActive.setAuthor(user.getName());
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        try{//获取上传图片
            MultipartFile activeImg= request.getFile("activeImg");
            String url = this.imgPath(activeImg,request);
            portalActive.setImg(url);
            log.info("上传路径"+url);
        }catch (IOException e){
            e.printStackTrace();
            log.info("门户活动管理上传图片失败");
            jsonResult.setMessage("门户活动管理上传图片失败");
            jsonResult.setData(0);
            return jsonResult;
        }
        ServiceResult<Integer> serviceResult =portalActiveService.savePortalActive(portalActive);
        if (!serviceResult.getSuccess()) {
            dataMap.put("portalActive", portalActive);
            dataMap.put("parks",parkService.getParkList());
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage("新增成功");
        log.info("保存成功");
        return jsonResult;
    }
    @RequestMapping(value = "/edit",method = {RequestMethod.GET})
    public String edit(Integer id,Map<String,Object>dataMap){
        ServiceResult<PortalActive> serviceResult=portalActiveService.getPortalActiveById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("active",serviceResult.getResult());
        dataMap.put("parks",parkService.getParkList().getResult());
        return "admin/portal/active/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult update(PortalActive portalActive,Map<String,Object>dataMap,MultipartHttpServletRequest request)throws IOException{
        HttpJsonResult jsonResult=new HttpJsonResult();
        MultipartFile  activeImg= request.getFile("activeImg");
        if(activeImg!=null&& activeImg.getSize() > 0){
            String imgUrl = this.imgPath(activeImg,request);
            portalActive.setImg(imgUrl);
        }
        ServiceResult<Integer> serviceResult=portalActiveService.updatePortalActive(portalActive);
        if(!serviceResult.getSuccess()){
            dataMap.put("active",portalActive);
            dataMap.put("message", serviceResult.getMessage());
            dataMap.put("park",serviceResult.getResult());
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage("修改成功");
        return jsonResult;
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=portalActiveService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
        }
        jsonResult.setMessage(serviceResult.getMessage());
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=portalActiveService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("活动管理更新启用或禁用状态失败");
            jsonResult.setData(0);
            return jsonResult;
        }
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
