package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.operate.Park;
import com.sln.entity.portal.IndexBanner;
import com.sln.entity.portal.PortalService;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.operate.IParkService;
import com.sln.service.portal.IPortalIndexBannerService;
import com.sln.service.portal.IPortalServiceService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
@RequestMapping(value = "admin/portal/index")
public class PortalIndexController extends BaseController {
     @Resource
     private IPortalIndexBannerService indexBannerService;
     @Resource
     private IParkService parkService;
     @Resource
     private IPortalServiceService portalServiceService;

     @RequestMapping(value = "",method = { RequestMethod.GET})
     public String init(@ModelAttribute("message")String message,Map<String, Object> dataMap,
                        @ModelAttribute("index")String index, HttpServletRequest request){
         if(index==null||index.equals("")){
              index=request.getParameter("index");
              if(index==null||index.equals("")){
                  index="0";
              }
         }
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         //获取服务类
         ServiceResult<List<PortalService>> servicePage = portalServiceService.servicePage(queryMap,pager);
         //获取服务项
         ServiceResult<List<PortalService>> listServiceResult=portalServiceService.ListService(null);
         dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
         dataMap.put("services",servicePage.getResult());
         dataMap.put("list",listServiceResult.getResult());
         dataMap.put("message",message);
         dataMap.put("index",index);
         return "/admin/portal/index/banner/list";
     }
     @RequestMapping(value = "list",method = {RequestMethod.POST})
     @ResponseBody
     public HttpJsonResult<List<IndexBanner>> list(HttpServletRequest request,
                                                   Map<String, Object> dataMap){
         HttpJsonResult<List<IndexBanner>> jsonResult=new HttpJsonResult<List<IndexBanner>>();
         Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<IndexBanner>> serviceResult = indexBannerService.page(queryMap,pager);
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
     public String add(HttpServletRequest request,Map<String,Object>dataMap){
         String index=request.getParameter("index");
         ServiceResult<List<Park>> serviceResult=parkService.getParkList();
         if (!serviceResult.getSuccess()) {
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             }
         }
         dataMap.put("parks",serviceResult.getResult());
         dataMap.put("index",index);
         return "/admin/portal/index/banner/add";
     }

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult create(IndexBanner indexBanner, Map<String, Object> dataMap, MultipartHttpServletRequest request) {
        HttpJsonResult jsonResult=new HttpJsonResult();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        try{//获取上传图片
            MultipartFile bannerImg= request.getFile("bannerImg");
            String url = this.imgPath(bannerImg,request);
            indexBanner.setImg(url);
            log.info("上传路径"+url);
        }catch (IOException e){
            e.printStackTrace();
            log.info("首页轮播上传图片失败");
            jsonResult.setMessage("首页轮播上传图片失败");
            jsonResult.setData(0);
            return jsonResult;
        }
        ServiceResult<Integer> serviceResult = indexBannerService.saveIndexBanner(indexBanner);
        if (!serviceResult.getSuccess()) {
            dataMap.put("indexBanner", indexBanner);
            dataMap.put("message", serviceResult.getMessage());
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        log.info("保存成功");
        return jsonResult;
    }

    @RequestMapping("/edit")
    public String edit(Integer id,Map<String,Object> dataMap,HttpServletRequest request){
         String index=request.getParameter("index");
         ServiceResult<IndexBanner> serviceResult=indexBannerService.getIndexBannerById(id);
         if(!serviceResult.getSuccess()){
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             }
         }
        dataMap.put("index",index);
        dataMap.put("indexBanner",serviceResult.getResult());
        return "admin/portal/index/banner/edit";
    }

    @RequestMapping(value = "/update",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult update(IndexBanner indexBanner,Map<String,Object> dataMap,MultipartHttpServletRequest request)throws IOException{
        HttpJsonResult jsonResult=new HttpJsonResult();
        MultipartFile  bannerImg= request.getFile("bannerImg");
        if(bannerImg!=null&& bannerImg.getSize() > 0){
            String imgUrl = this.imgPath(bannerImg,request);
            indexBanner.setImg(imgUrl);
        }
        ServiceResult<Integer> serviceResult=indexBannerService.updateIndexBanner(indexBanner);
        if(!serviceResult.getSuccess()){
            dataMap.put("indexBanner",indexBanner);
            dataMap.put("message", serviceResult.getMessage());
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
        ServiceResult<Integer>serviceResult=indexBannerService.del(id);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>(serviceResult.getMessage());
        }
        jsonResult.setMessage("删除成功");
        jsonResult.setData(1);
        return jsonResult;
    }

    @RequestMapping(value = "/onOrOff",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> onOrOff(Integer id,String state){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer>serviceResult=indexBannerService.onOrOff(id,state);
        if(!serviceResult.getSuccess()){
            jsonResult=new HttpJsonResult<Integer>("更新状态失败！");
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
        log.info("构建好的路径是 "+path);
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
