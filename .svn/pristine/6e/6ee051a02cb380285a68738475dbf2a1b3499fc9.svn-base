package com.sln.web.controller.seller;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.entity.compain.ComplainRegister;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerParkOperation;
import com.sln.entity.system.Regions;
import com.sln.service.operate.IParkService;
import com.sln.service.seller.ISellerApplyService;
import com.sln.service.seller.ISellerService;
import com.sln.service.system.IRegionsService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 管理端商家申请审核controller
 *                       
 * @Filename: AdminSellerAuditController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "admin/seller/audit")
public class AdminSellerAuditController extends BaseController {

    private static Logger log = LogManager.getLogger(AdminSellerAuditController.class);

    @Resource(name = "sellerApplyService")
    private ISellerApplyService sellerApplyService;

    @Resource(name = "sellerService")
    private ISellerService      sellerService;

    @Resource
    private IRegionsService regionsService;
    @Resource(name = "parkService")
    private IParkService    parkService;

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(@ModelAttribute("message")String message,HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        dataMap.put("message",message);
        return "admin/seller/audit/sellerapplylist";
    }

    /**
     * 商家申请列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerApply>> list(HttpServletRequest request,
                                                                Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        queryMap.put("roleId",WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        ServiceResult<List<SellerApply>> serviceResult = sellerApplyService.getSellerApplysByRoleId(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        HttpJsonResult<List<SellerApply>> jsonResult = new HttpJsonResult<List<SellerApply>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /**
     * 删除商家申请
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "delete", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> delete(HttpServletResponse response,
                                                        Map<String, Object> dataMap, Integer id,
                                                        Integer userId) throws Exception {

        ServiceResult<Boolean> serviceResult = sellerApplyService.deleteSellerApply(id, userId);
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }

        return jsonResult;
    }

    /**
     * 跳往审核页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "audit.html", method = { RequestMethod.GET })
    public String audit(HttpServletRequest request,Map<String, Object> dataMap, Integer id) throws Exception {
        ServiceResult<SellerApply> sr = sellerApplyService.getSellerApplyById(id);
        dataMap.put("app", sr.getResult());
        dataMap.put("stringUtil", new StringUtil());
        
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("roleId", WebAdminSession.getAdminUser(request).getRoleId().toString());
        queryMap.put("adminId",WebAdminSession.getAdminUser(request).getId().toString());
        queryMap.put("q_isContributing",Seller.IS_CONTRIBUTING1+"");
        ServiceResult<List<Seller>> serviceResult = sellerService.getSellersByRoleId(queryMap, null);
       
        dataMap.put("contributingList", serviceResult.getResult());
        
        ServiceResult<Seller> selleResult =  sellerService.getSellerByMemberId(sr.getResult().getUserId());
        dataMap.put("seller", selleResult.getResult());
        return "admin/seller/audit/sellerapplyaudit";
    }

    /**
     * 审核通过
     * @param request
     * @param response
     * @param dataMap
     * @param id
     */
    @RequestMapping(value = "pass", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<Boolean> pass(HttpServletRequest request,
                                                      HttpServletResponse response,
                                                      Map<String, Object> dataMap, Seller seller) {

        ServiceResult<Boolean> serviceResult = sellerApplyService.auditSellerApply(seller,
            SellerApply.STATE_2_DONE, WebAdminSession.getAdminUser(request).getId());
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 申请被驳回
     * @param request
     * @param response
     * @param dataMap
     * @param id
     */
    @RequestMapping(value = "reject", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> reject(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Map<String, Object> dataMap, Integer id) {
    	Seller seller = new Seller();
    	seller.setSellerApplyId(id);
        ServiceResult<Boolean> serviceResult = sellerApplyService.auditSellerApply(seller,
            SellerApply.STATE_4_FAIL, WebAdminSession.getAdminUser(request).getId());
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 跳往审核页面
     * @param dataMap
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) throws Exception {
        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        dataMap.put("provinceList", provinceResult.getResult());
        dataMap.put("parkList",parkService.getOperationsGroupByParkId());
        return "admin/seller/audit/sellerapplyadd";
    }

    /**
     * information查看审核通过的入驻商家详情
     * @param sellerId
     * @param dataMap
     * @return
     */
  	@RequestMapping(value = "information", method = { RequestMethod.GET })
  	public String information(@RequestParam(value = "id", required = true) Integer sellerId,Map<String, Object> dataMap) {
  		ServiceResult<SellerApply> serviceResult = sellerApplyService.getSellerApplyById(sellerId);
  		if (!serviceResult.getSuccess()) {
  			throw new RuntimeException(serviceResult.getMessage());
  		}
  		SellerApply result = serviceResult.getResult();
  		dataMap.put("sellerApply", serviceResult.getResult());
  		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
  		
  		Seller seller = sellerService.getSellerByMemberId(serviceResult.getResult().getUserId()).getResult();
  		if(null != seller.getSubjectId() && seller.getSubjectId() != 0) {
  			seller = sellerService.getSellerById(seller.getSubjectId()).getResult();
  			dataMap.put("subjectName", seller.getSellerName());
  		}
  		
  		return "admin/seller/audit/information";
  	}
  	
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult create(SellerApply sellerApply,MultipartHttpServletRequest request,
                         Map<String, Object> dataMap) throws IOException {
        //parkoperation商家所属园区业务管理方的json数组
        HttpJsonResult jsonResult = new HttpJsonResult ();
        log.info("开始商家申请的保存");
        String parkoperation = request.getParameter("parkoperation");
        Map<String, String> param = new HashMap<String, String>();
        param.put("seller", "apply");
        MultipartFile  bussinessLicense= request.getFile("up_bussinessLicenseImage");
        String bli = this.imgPath(bussinessLicense,request);
        log.info("营业执照图片上传路径"+bli);
        sellerApply.setBussinessLicenseImage(bli);
        MultipartFile personCardUp=request.getFile("up_personCardUp");
        String puw = this.imgPath(personCardUp,request);
        log.info("身份证正面上传路径"+puw);
        sellerApply.setPersonCardUp(puw);
        MultipartFile personCardDown = request.getFile("up_personCardDown");
        String pdw = this.imgPath(personCardDown,request);
        log.info("身份证反面上传路径"+pdw);
        sellerApply.setPersonCardDown(pdw);
        sellerApply.setState(SellerApply.STATE_1_SEND);
        sellerApply.setType(2);
        String userName = request.getParameter("userName");
        String sellerName = request.getParameter("sellerName");
        if (StringUtil.isEmpty(userName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家账号不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            dataMap.put("parkList",parkService.getOperationsGroupByParkId());
            jsonResult.setBackUrl("admin/seller/audit/sellerapplyadd");
            jsonResult.setMessage("新增失败,商家账号为空");
            jsonResult.setData(0);
            return jsonResult;
        }
        if (StringUtil.isEmpty(sellerName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家店铺名称不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            dataMap.put("parkList",parkService.getOperationsGroupByParkId());
            jsonResult.setMessage("新增失败,商家店铺名称不能为空");
            jsonResult.setBackUrl("admin/seller/audit/sellerapplyadd");
            jsonResult.setData(0);
            return jsonResult;
        }
        String ip = WebUtil.getIpAddr(request);
        ServiceResult<Boolean> serviceResult = sellerApplyService.saveSellerApplyForAdmin(sellerApply, userName, sellerName, ip,parkoperation);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("userName", userName);
                dataMap.put("sellerName", sellerName);
                dataMap.put("sellerApply", sellerApply);
                dataMap.put("message", serviceResult.getMessage());
                ServiceResult<List<Regions>> provinceResult = regionsService
                        .getRegionsByParentId(0);
                dataMap.put("provinceList", provinceResult.getResult());
                dataMap.put("parkList",parkService.getOperationsGroupByParkId());
                jsonResult.setBackUrl("admin/seller/audit/sellerapplyadd");
                jsonResult.setData(0);
                jsonResult.setMessage("保存失败"+serviceResult.getMessage());
                return jsonResult;
            }
        }
        jsonResult.setData(1);
        jsonResult.setMessage("保存成功");
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer sellerApplyId, Map<String, Object> dataMap) {
        ServiceResult<SellerApply> serviceResult = sellerApplyService
            .getSellerApplyById(sellerApplyId);

        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
                dataMap.put("message", serviceResult.getMessage());
                return "admin/seller/audit/sellerapplylist";
            }
        }
        SellerApply sellerApply = serviceResult.getResult();
        dataMap.put("sellerApply", sellerApply);
        ServiceResult<Seller> sellerResult = sellerService
            .getSellerByMemberId(sellerApply.getUserId());
        if (!sellerResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sellerResult.getCode())) {
                throw new RuntimeException(sellerResult.getMessage());
            } else {
                dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
                dataMap.put("message", sellerResult.getMessage());
                return "admin/seller/audit/sellerapplylist";
            }
        }
        Seller seller = sellerResult.getResult();
        if (seller != null) {
            dataMap.put("userName", seller.getName());
            dataMap.put("sellerName", seller.getSellerName());
        }
        ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
        dataMap.put("provinceList", provinceResult.getResult());

        ServiceResult<List<Regions>> companyCityResult = regionsService
            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
        dataMap.put("companyCityList", companyCityResult.getResult());

        ServiceResult<List<Regions>> bankCityResult = regionsService
            .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
        dataMap.put("bankCityList", bankCityResult.getResult());
        dataMap.put("parkList",parkService.getOperationsGroupByParkId().getResult());
        List<SellerParkOperation> list=sellerApplyService.getBySellerId(seller.getId()).getResult();
        String yw="";
        for(SellerParkOperation s:list){
            yw+=s.getParkId()+"_"+s.getOperationId()+";";
        }
        dataMap.put("operation",yw);
        return "admin/seller/audit/sellerapplyedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult update(SellerApply sellerApply,MultipartHttpServletRequest request,Map<String, Object> dataMap) throws IOException{
        HttpJsonResult jsonResult = new HttpJsonResult ();
        Map<String, String> param = new HashMap<String, String>();
        param.put("seller", "apply");
        //商家所属园区业务管理方的json数组
        String parkoperation = request.getParameter("parkoperation");
        //营业执照扫描件
        MultipartFile bussinessLicense = request.getFile("up_bussinessLicenseImage");
        if(bussinessLicense!=null&& bussinessLicense.getSize() > 0){
            String bli = this.imgPath(bussinessLicense,request);
            sellerApply.setBussinessLicenseImage(bli);
        }

        MultipartFile personCardUp=request.getFile("up_personCardUp");
        if(personCardUp!=null&& personCardUp.getSize() > 0){
            String puw = this.imgPath(personCardUp,request);
            sellerApply.setPersonCardUp(puw);
        }
        MultipartFile personCardDown = request.getFile("up_personCardDown");
        if(personCardDown!=null&& personCardDown.getSize() > 0){
            String pdw = this.imgPath(personCardDown,request);
            sellerApply.setPersonCardDown(pdw);
        }
        String userName = request.getParameter("userName");
        String sellerName = request.getParameter("sellerName");
        if (StringUtil.isEmpty(userName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家账号不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            ServiceResult<List<Regions>> companyCityResult = regionsService
                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
            dataMap.put("companyCityList", companyCityResult.getResult());
            ServiceResult<List<Regions>> bankCityResult = regionsService
                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
            dataMap.put("bankCityList", bankCityResult.getResult());
            dataMap.put("parkList",parkService.getOperationsGroupByParkId());
            jsonResult.setData(0);
            jsonResult.setMessage("商家账号不能为空");
            jsonResult.setBackUrl("admin/seller/audit/sellerapplyedit");
            return  jsonResult;
        }
        if (StringUtil.isEmpty(sellerName, true)) {
            dataMap.put("userName", userName);
            dataMap.put("sellerName", sellerName);
            dataMap.put("sellerApply", sellerApply);
            dataMap.put("message", "商家店铺名称不能为空！");
            ServiceResult<List<Regions>> provinceResult = regionsService.getRegionsByParentId(0);
            dataMap.put("provinceList", provinceResult.getResult());
            ServiceResult<List<Regions>> companyCityResult = regionsService
                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
            dataMap.put("companyCityList", companyCityResult.getResult());
            ServiceResult<List<Regions>> bankCityResult = regionsService
                .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
            dataMap.put("bankCityList", bankCityResult.getResult());
            dataMap.put("parkList",parkService.getOperationsGroupByParkId());
            jsonResult.setData(0);
            jsonResult.setMessage("商家店铺名称不能为空");
            jsonResult.setBackUrl("admin/seller/audit/sellerapplyedit");
            return  jsonResult;
        }

        ServiceResult<Boolean> serviceResult = sellerApplyService
            .updateSellerApplyForAdmin(sellerApply, userName, sellerName,parkoperation);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                dataMap.put("userName", userName);
                dataMap.put("sellerName", sellerName);
                dataMap.put("sellerApply", sellerApply);
                dataMap.put("message", serviceResult.getMessage());
                ServiceResult<List<Regions>> provinceResult = regionsService
                    .getRegionsByParentId(0);
                dataMap.put("provinceList", provinceResult.getResult());
                ServiceResult<List<Regions>> companyCityResult = regionsService
                    .getRegionsByParentId(
                        ConvertUtil.toInt(sellerApply.getCompanyProvince(), null));
                dataMap.put("companyCityList", companyCityResult.getResult());
                ServiceResult<List<Regions>> bankCityResult = regionsService
                    .getRegionsByParentId(ConvertUtil.toInt(sellerApply.getBankProvince(), null));
                dataMap.put("bankCityList", bankCityResult.getResult());
                dataMap.put("parkList",parkService.getOperationsGroupByParkId());
                jsonResult.setData(0);
                jsonResult.setMessage(serviceResult.getMessage());
                jsonResult.setBackUrl("admin/seller/audit/sellerapplyedit");
                return  jsonResult;
            }
        }
        jsonResult.setData(1);
        jsonResult.setBackUrl("admin/seller/audit/sellerapplylist");
        jsonResult.setMessage("修改成功");
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
