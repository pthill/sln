package com.sln.web.controller.operate;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.operate.Park;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.operate.IParkService;
import com.sln.service.system.IRegionsService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.UploadUtil;
import com.sln.web.util.WebAdminSession;
import org.apache.log4j.Logger;
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
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "admin/operate/park")
public class ParkController extends BaseController {

	 	@Resource
	    private IParkService    parkService;
	 	@Resource
		private IRegionsService regionsService;
	    Logger    log = Logger.getLogger(this.getClass());
	    
	    /**
	     * 默认页面
	     * @param dataMap
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "", method = { RequestMethod.GET })
	    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
	        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        dataMap.put("queryMap", queryMap);
	        return "/admin/operate/park/list";
	    } 
	    
	    
	    /**
	     * gridDatalist数据
	     * @param request
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "list", method = { RequestMethod.GET })
		@ResponseBody
	    public  HttpJsonResult<List<Park>> list(HttpServletRequest request,
	                                                                Map<String, Object> dataMap) {
	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
	        ServiceResult<List<Park>> serviceResult = parkService.page(queryMap, pager);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }
	        HttpJsonResult<List<Park>> jsonResult = new HttpJsonResult<List<Park>>();
	        jsonResult.setRows((List<Park>) serviceResult.getResult());
	        jsonResult.setTotal(pager.getRowsCount());
	        return jsonResult;
	    }
	    
	    /**
	     * 新增页面
	     * @param dataMap
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "add", method = { RequestMethod.GET })
	    public String add(Map<String,Object> dataMap) {
			dataMap.put("provinceList", regionsService.getProvince());
	        return "admin/operate/park/add";
	    }
	    
	    /**
	     * 新增园区方法
	     * 
	     */
	    @RequestMapping(value = "create", method = { RequestMethod.POST })
		@ResponseBody
	    public HttpJsonResult create(MultipartHttpServletRequest request, Park park, Map<String, Object> dataMap){
			SystemAdmin user = WebAdminSession.getAdminUser(request);
			HttpJsonResult jsonResult = new HttpJsonResult ();
			if (null == user) {
				jsonResult.setMessage("登录超时，请重新登录");
				jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
				return jsonResult;
			}
			try{//获取上传图片
				MultipartFile  parkImg= request.getFile("parkImg");
				String bli = this.imgPath(parkImg,request);
				park.setImg(bli);
				log.info("上传路径"+bli);
			}catch (IOException e){
				e.printStackTrace();
				log.info("上传图片失败");
				jsonResult.setMessage("图片上传失败");
				jsonResult.setData(0);
				return jsonResult;
			}
			ServiceResult<Integer> serviceResult =parkService.insertPark(park);
			if (!serviceResult.getSuccess()) {
				dataMap.put("park", park);
				dataMap.put("message", serviceResult.getMessage());
				dataMap.put("provinceList", regionsService.getProvince());
				jsonResult.setMessage(serviceResult.getMessage());
				jsonResult.setData(0);
				return jsonResult;
			}
			jsonResult.setData(1);
			jsonResult.setMessage("新增成功");
			log.info("保存成功");
			return jsonResult;
	    }

	    @RequestMapping(value = "edit",method = {RequestMethod.GET})
		public String edit(Integer id,Map<String,Object> dataMap){
			ServiceResult<Park> serviceResult=parkService.getParkById(id);
			if (!serviceResult.getSuccess()) {
				if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
					throw new RuntimeException(serviceResult.getMessage());
				}
			}
			dataMap.put("cityList",regionsService.getRegionsByParentId(ConvertUtil.toInt(serviceResult.getResult().getProvince(), null)).getResult());
			dataMap.put("park",serviceResult.getResult());
			dataMap.put("provinceList", regionsService.getProvince());
			return "/admin/operate/park/edit";
		}

		@RequestMapping(value = "update",method = {RequestMethod.POST})
		@ResponseBody
		public HttpJsonResult update(Park park,Map<String,Object> dataMap,MultipartHttpServletRequest request)throws IOException{
			HttpJsonResult jsonResult = new HttpJsonResult ();
			MultipartFile  img= request.getFile("parkImg");
			if(img!=null&& img.getSize() > 0){
				String imgUrl = this.imgPath(img,request);
				park.setImg(imgUrl);
			}
			ServiceResult<Integer> serviceResult=parkService.updateParkById(park);
			if(!serviceResult.getSuccess()){
				dataMap.put("message", serviceResult.getMessage());
				dataMap.put("park",serviceResult.getResult());
				dataMap.put("provinceList", regionsService.getProvince());
				jsonResult.setMessage(serviceResult.getMessage());
				jsonResult.setData(0);
			}
			jsonResult.setData(1);
			jsonResult.setMessage("修改成功");
			return jsonResult;
		}
		/**
		 * 修改园区方法
		 */
		@RequestMapping(value = "doUpdate", method = { RequestMethod.GET })
		@ResponseBody
		public  ServiceResult<Integer> doUpdate(HttpServletRequest request,String ids,Integer state){
			ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
			serviceResult =parkService.batchUpdateParkById(ids, state);
			return serviceResult;
		}
	    /**
	     * 打开园区标记页面
	     */
	    @RequestMapping(value = "parkSign", method = { RequestMethod.GET })
	    public String parkSign(HttpServletRequest request,Integer id,Map<String, Object> dataMap){
	    	dataMap.put("id", id);
	    	return "admin/operate/park/parkSign";
	    }

	    /**
	     * 园区标记经纬度方法
	     */
	    @RequestMapping(value = "signPark", method = { RequestMethod.GET })
	    public @ResponseBody ServiceResult<Integer> signPark(HttpServletRequest request,Integer id,String lng,String lat){
	    	ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
	    	Park park = new Park();
	    	park.setId(id);
	    	park.setLongitude(new BigDecimal(lng));
	    	park.setLatitude(new BigDecimal(lat));
	    	serviceResult= parkService.updateParkById(park);
	    	return serviceResult;
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
