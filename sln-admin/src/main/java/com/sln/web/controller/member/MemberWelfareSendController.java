package com.sln.web.controller.member;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.member.MemberWelfareSend;
import com.sln.entity.seller.Seller;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.member.IMemberWelfareSendService;
import com.sln.service.seller.ISellerService;
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
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/admin/member/welfareSend")
public class MemberWelfareSendController {

    @Resource
    private IMemberWelfareSendService memberWelfareSendService;
    @Resource
    private ISellerService            sellerService;

    @RequestMapping(value = "",method = {RequestMethod.GET})
    public String init(Map<String, Object> dataMap){
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/member/welfare/list";
    }

    @RequestMapping(value = "/list",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult<List<MemberWelfareSend>> list(HttpServletRequest request,
                                                        Map<String,Object>dataMap){
        HttpJsonResult<List<MemberWelfareSend>> jsonResult=new HttpJsonResult<>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<MemberWelfareSend>> serviceResult = memberWelfareSendService.queryPage(queryMap, pager);
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

    //下载模板
    @RequestMapping(value = "/downModel",method = { RequestMethod.POST,RequestMethod.GET})
    public void downModel(HttpServletResponse response,HttpServletRequest request) throws IOException{
        response.setContentType("text/html;charset=UTF-8");
        BufferedInputStream in = null;
        BufferedOutputStream out = null;
        request.setCharacterEncoding("UTF-8");
        String rootpath = request.getSession().getServletContext().getRealPath("/");
        String fileName = "model.xlsx";
        try {
            File f = new File(rootpath + "template/" + fileName);
            response.setContentType("application/x-excel");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Disposition", "attachment; filename="+fileName);
            response.setHeader("Content-Length",String.valueOf(f.length()));
            in = new BufferedInputStream(new FileInputStream(f));
            out = new BufferedOutputStream(response.getOutputStream());
            byte[] data = new byte[1024];
            int len = 0;
            while (-1 != (len=in.read(data, 0, data.length))) {
                out.write(data, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }

    }

    @RequestMapping(value = "/toAdd",method = {RequestMethod.GET})
    public String toAdd(HttpServletRequest request,Map<String,Object>dataMap){
        ServiceResult<List<Seller>> serviceResult=sellerService.getsellerNameAndName(new HashMap<String, String>(),
                null);
        if(!serviceResult.getSuccess()){
            if(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())){
                throw new RuntimeException(serviceResult.getMessage());
            }else{
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        dataMap.put("sellers",serviceResult.getResult());
        String id=request.getParameter("id");
        if(id!=null&&!id.equals("")){
         ServiceResult<MemberWelfareSend> sendServiceResult=memberWelfareSendService.getMemberWelfareSendById(Integer.parseInt(id));
         if(!sendServiceResult.getSuccess()){
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sendServiceResult.getCode())) {
                 throw new RuntimeException(sendServiceResult.getMessage());
             } else {
                 throw new BusinessException(sendServiceResult.getMessage());
             }
         }
         dataMap.put("m",sendServiceResult.getResult());
        }
        return "admin/member/welfare/add";
    }


    @RequestMapping(value = "/add",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public HttpJsonResult fileUpload(@RequestParam("fileUpload")MultipartFile file,@RequestParam("sellerId")Integer sellerId,
                                     MultipartHttpServletRequest request,@RequestParam("pid")Integer pid){
        SystemAdmin adminUser = WebAdminSession.getAdminUser(request);
        HttpJsonResult jsonResult=new HttpJsonResult();
        if(file!=null&&file.getSize()>0){
            ServiceResult<Boolean> serviceResult=memberWelfareSendService.saveMemberWelfareSend(file,adminUser.getId(),pid,sellerId);
            jsonResult.setMessage(serviceResult.getMessage());
            if (!serviceResult.getSuccess()) {
                jsonResult.setData(0);
                return jsonResult;
            }else {
                jsonResult.setData(1);
                return jsonResult;
            }
        }else {
            jsonResult.setData(0);
            jsonResult.setMessage("上传文件为空,请重新上传");
            return jsonResult;
        }
    }

    @RequestMapping(value = "/send",method =RequestMethod.POST)
    @ResponseBody
    public HttpJsonResult send(Integer id,HttpServletRequest request){
        String ip = WebUtil.getIpAddr(request);
        HttpJsonResult httpJsonResult=new HttpJsonResult();
        ServiceResult<Boolean> serviceResult=memberWelfareSendService.sendWelfareSend(ip,id);
        httpJsonResult.setMessage(serviceResult.getMessage());
        if (!serviceResult.getSuccess()) {
            httpJsonResult.setData(0);
            return httpJsonResult;
        }
        httpJsonResult.setData(1);
        return httpJsonResult;
    }

    @RequestMapping(value = "/detail",method = {RequestMethod.GET})
    public String detail(Integer id,Map<String,Object>dataMap){
        ServiceResult<MemberWelfareSend> serviceResult=memberWelfareSendService.getMemberWelfareSendById(id);
        if(!serviceResult.getSuccess()){
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        dataMap.put("memberWelfareSend",serviceResult.getResult());
        return "admin/member/welfare/detail";
    }

    @RequestMapping(value = "/del",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult del(Integer id){
        HttpJsonResult httpJsonResult=new HttpJsonResult();
        ServiceResult<Integer> serviceResult=memberWelfareSendService.del(id);
        httpJsonResult.setMessage(serviceResult.getMessage());
        if (!serviceResult.getSuccess()) {
            httpJsonResult.setData(0);
            return httpJsonResult;
        }
        httpJsonResult.setData(1);
        return httpJsonResult;
    }

}
