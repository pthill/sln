package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.operate.Park;
import com.sln.entity.portal.PortalService;
import com.sln.entity.portal.RecommendService;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalServiceService;
import com.sln.service.portal.IRecommendServiceService;
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
@RequestMapping("/admin/portal/recommendService")
public class RecommendServiceController extends BaseController{
    @Resource
    private  IRecommendServiceService recommendServiceService;
    @Resource
    private IPortalServiceService    portalServiceService;
    @Resource
    private IParkService parkService;
    @RequestMapping(value = "/list",method = { RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<RecommendService>> list(HttpServletRequest request,
                                                       Map<String, Object> dataMap){
        HttpJsonResult<List<RecommendService>> jsonResult=new HttpJsonResult<List<RecommendService>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<RecommendService>> serviceResult = recommendServiceService.page(queryMap,pager);
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
    public String add(Map<String,Object>dataMap,HttpServletRequest request){
        String index=request.getParameter("index");
        ServiceResult<List<Park>>serviceResult=parkService.getParkList();
        dataMap.put("parks",serviceResult.getResult());
        dataMap.put("index",index);
        return "admin/portal/index/service/add";
    }
    //获取园区下的服务项
    @RequestMapping(value = "/getFwx",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<PortalService>> getService(Integer parkId){
        HttpJsonResult<List<PortalService>> jsonResult=new HttpJsonResult<List<PortalService>>();
        ServiceResult<List<PortalService>> result=portalServiceService.ListService(parkId);
        if(!result.getSuccess()){
            log.error("[RecommendServiceController][getService]获取业务管理方列表信息失败："
                     + result.getMessage());
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(result.getCode())) {
                throw new RuntimeException(result.getMessage());
            }
        }
        jsonResult.setData(result.getResult());
        return jsonResult;
    }

    //获取服务项下的服务类
    @RequestMapping(value = "/getFwl",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<PortalService>> getFwl(Integer fwx){
        HttpJsonResult<List<PortalService>> jsonResult=new HttpJsonResult<List<PortalService>>();
        Map<String,String>query=new HashMap<>();
        query.put("q_pid",fwx.toString());
        ServiceResult<List<PortalService>> result=portalServiceService.servicePage(query,null);
        if(!result.getSuccess()){
            log.error("[RecommendServiceController][getService]列表信息失败："
                      + result.getMessage());
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(result.getCode())) {
                throw new RuntimeException(result.getMessage());
            }
        }
        jsonResult.setData(result.getResult());
        return jsonResult;
    }


    @RequestMapping(value = "/create",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult create(Map<String,Object>dataMap, MultipartHttpServletRequest request, RecommendService recommendService){
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
            recommendService.setImg(url);
            log.info("门户推荐服务上传路径"+url);
        }catch (IOException e){
            e.printStackTrace();
            log.info("门户推荐服务上传图片失败");
            jsonResult.setMessage("门户推荐服务上传图片失败");
            jsonResult.setData(0);
            return jsonResult;
        }
        ServiceResult<Integer> serviceResult =recommendServiceService.saveRecommendService(recommendService);
        if (!serviceResult.getSuccess()) {
            dataMap.put("recommendService", recommendService);
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
        String index=request.getParameter("index");
        ServiceResult<RecommendService> serviceResult=recommendServiceService.getRecommendServiceById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        ServiceResult<List<PortalService>> result=portalServiceService.ListService(serviceResult.getResult().getParkId());
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        Map<String,String>query=new HashMap<>();
        ServiceResult<List<PortalService>> fwl=portalServiceService.servicePage(query,null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        ServiceResult<List<Park>>parkList=parkService.getParkList();
        dataMap.put("parks",parkList.getResult());
        dataMap.put("index",index);
        dataMap.put("fwx",result.getResult());
        dataMap.put("fwl",fwl.getResult());
        dataMap.put("service",serviceResult.getResult());
        return "admin/portal/index/service/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult update(RecommendService recommendService,Map<String,Object>dataMap,MultipartHttpServletRequest request)throws IOException{
        HttpJsonResult jsonResult=new HttpJsonResult();
        MultipartFile  serviceImg= request.getFile("serviceImg");
        if(serviceImg!=null&& serviceImg.getSize() > 0){
            String imgUrl = this.imgPath(serviceImg,request);
            recommendService.setImg(imgUrl);
        }
        ServiceResult<Integer> serviceResult=recommendServiceService.updateRecommendService(recommendService);
        if(!serviceResult.getSuccess()){
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=recommendServiceService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("推荐服务状态修改失败");
        }
        jsonResult.setData(1);
        return jsonResult;
    }
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=recommendServiceService.del(id);
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
