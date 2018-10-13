package com.sln.web.controller.portal;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.portal.ShopActive;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.portal.IShopActiveService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping(value = "/admin/portal/shopActive")
public class ShopActiveController extends BaseController {

    @Resource
    private IShopActiveService shopActiveService;
    /*电商满减*/
    @RequestMapping(value = "/listMj", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<List<ShopActive>> listMj(HttpServletRequest request,Map<String,Object>dataMap) {
        HttpJsonResult<List<ShopActive>> jsonResult = new HttpJsonResult<List<ShopActive>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_type","1");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ShopActive>> serviceResult = shopActiveService.page(queryMap,pager);
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
    /*电商多惠部落*/
    @RequestMapping(value = "/listBk", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<List<ShopActive>> listBk(HttpServletRequest request,Map<String,Object>dataMap) {
        HttpJsonResult<List<ShopActive>> jsonResult = new HttpJsonResult<List<ShopActive>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("q_type","2");
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ShopActive>> serviceResult = shopActiveService.page(queryMap,pager);
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

    @RequestMapping(value = "/add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request,Map<String,String>dataMap) {
        String index=request.getParameter("index");
        dataMap.put("index",index);
        return "admin/portal/index/active/add";
    }

    @RequestMapping(value = "/create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult create(@RequestParam("fileupload")  MultipartFile[] file,ShopActive shopActive, Map<String, Object> dataMap,
                                 MultipartHttpServletRequest request) {
        HttpJsonResult jsonResult=new HttpJsonResult();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        StringBuffer url=new StringBuffer("");
        try{//获取上传图片
            if(file!=null&&file.length>0){
                for(int i=0;i<file.length;i++){
                    MultipartFile serviceImg=file[i];
                      url.append(this.imgPath(serviceImg,request)+",");
                }
            }
        }catch (IOException e){
            log.info("电商活动上传图片失败");
            e.printStackTrace();
            jsonResult.setMessage("电商活动上传图片失败");
            jsonResult.setData(0);
            return jsonResult;
        }
        shopActive.setImg(url.substring(0,url.length()-1));
        log.info("电商活动上传图片"+url);
        ServiceResult<Integer> serviceResult =shopActiveService.saveShopActive(shopActive);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.GET })
    public String edit(Integer id,Map<String,Object>dataMap,HttpServletRequest request) {
        String index=request.getParameter("index");
        ServiceResult<ShopActive> serviceResult=shopActiveService.getShopActiveById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        dataMap.put("index",index);
        dataMap.put("shop",serviceResult.getResult());
        return "admin/portal/index/active/edit";
    }

    @RequestMapping(value = "/update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult update(ShopActive shopActive,Map<String,Object>dataMap) throws IOException{
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<Integer> serviceResult=shopActiveService.updateShopActive(shopActive);
        if(!serviceResult.getSuccess()){
            dataMap.put("shopActive",shopActive);
            dataMap.put("message",serviceResult.getMessage());
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }
    //单个图片上传
    @RequestMapping(value = "/fileUpload", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult fileUpload(Integer id,MultipartHttpServletRequest request)throws IOException{
        HttpJsonResult jsonResult=new HttpJsonResult();
        MultipartFile  fileupload= request.getFile("fileupload");
        StringBuffer url=new StringBuffer("");
        ServiceResult<ShopActive>  serviceResult =shopActiveService.getShopActiveById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        ShopActive shopActive=serviceResult.getResult();
        if(fileupload!=null){
            String[] img=shopActive.getImg().split(",");
            if(img.length==0){
               url.append(this.imgPath(fileupload,request));
               shopActive.setImg(url.toString());
            }else {
                url.append(",");
                url.append(this.imgPath(fileupload,request));
                shopActive.setImg(shopActive.getImg()+url.toString());
            }
        }
        shopActiveService.updateShopActive(shopActive);
        if(!serviceResult.getSuccess()){
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }

    //单个图片删除
    @RequestMapping(value = "/deleteImg",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult deleteImg(Integer id,String path){
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<ShopActive>  serviceResult =shopActiveService.getShopActiveById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        StringBuffer url=new StringBuffer("");
        ShopActive shopActive=serviceResult.getResult();
        String[] img=shopActive.getImg().split(",");
        List<String> list=Arrays.asList(img);
        ArrayList<String> arrayList=new ArrayList(list);
        for (int i=0;i<arrayList.size();i++){
            if(arrayList.get(i).equals(path)){
                arrayList.remove(i);
            }
        }
        if(arrayList!=null && arrayList.size()!=0){
            if(arrayList.size()==1){
                for(String s:arrayList){
                    url.append(s);
                }
            }else{
                for(String s:arrayList){
                    url.append(",");
                    url.append(s);
                }
            }
        }
        shopActive.setImg(url.toString());
        shopActiveService.updateShopActive(shopActive);
        if(!serviceResult.getSuccess()){
            jsonResult.setMessage(serviceResult.getMessage());
            jsonResult.setData(0);
            return jsonResult;
        }
        jsonResult.setData(1);
        jsonResult.setMessage(serviceResult.getMessage());
        return jsonResult;
    }
    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<Integer> del(Integer id){
        HttpJsonResult<Integer> jsonResult=new HttpJsonResult<Integer>();
        ServiceResult<Integer> serviceResult=shopActiveService.del(id);
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
        ServiceResult<Integer>serviceResult=shopActiveService.onOrOff(id,state);
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
        log.info("构建好的路径是:"+path);
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
