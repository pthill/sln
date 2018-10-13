package com.sln.web.controller.compain;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.compain.ComplainRegister;
import com.sln.entity.member.Member;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerComplaint;
import com.sln.service.compain.IComplainRegisterService;
import com.sln.service.member.IMemberService;
import com.sln.service.order.IAdminComplaintService;
import com.sln.service.seller.ISellerService;
import com.sln.util.RedisClient;
import com.sln.vo.seller.SellerComplaintVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 商家淘汰
 * @author luoyong
 */
@Controller
@RequestMapping(value = "admin/seller/compailnregister")
public class CompainRegisterController extends BaseController{
	@Resource
	private IComplainRegisterService complainRegisterService;
	@Resource
	private ISellerService sellerService;
	@Resource
	private IMemberService memberService;
	@Resource
	private RedisClient redisClient;
	@Resource
    private IAdminComplaintService adminComplaintService;

	/**
	 * 默认进入的页面
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String init(Map<String, Object> dataMap) {
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/compailnregister/compainlist";
	}

		/**
		 * 进入商家列表页面
		 */
		@RequestMapping(value = "listli", method = { RequestMethod.GET })
		@ResponseBody
		// 本数据从商家管理表中获取
		public HttpJsonResult<List<ComplainRegister>> listli(HttpServletRequest request, Map<String, Object> dataMap,ModelMap modelmap) {
			Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
			queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
			PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		
			ServiceResult<List<ComplainRegister>> serviceResult = complainRegisterService.getComplainRegister(queryMap, pager);
			//根据tempid获取数据，如果没有再从数据库中取，有的话就直接从缓存中获取
			if (!serviceResult.getSuccess()) {
				if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
					throw new RuntimeException(serviceResult.getMessage());
				} else {
					throw new BusinessException(serviceResult.getMessage());
				}
		}
	    HttpJsonResult<List<ComplainRegister>> jsonResult = new HttpJsonResult<List<ComplainRegister>>();
		jsonResult.setRows(serviceResult.getResult());
		jsonResult.setTotal(pager.getRowsCount());
		log.debug("jsonResult size=" + jsonResult.getTotal());
		return jsonResult;
	}
	
	//返回到弹出框页面dialog，从商家表里获取账号与商家名称
	@RequestMapping(value = "dialog", method ={RequestMethod.GET})
	@ResponseBody
	// 本数据从商家管理表中获取
	public HttpJsonResult<List<Seller>> dialog(HttpServletRequest request, Map<String, Object> dataMap,ModelMap modelmap){
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResult<List<Seller>> serviceResult = sellerService.getsellerNameAndName(queryMap, pager);
		if (!serviceResult.getSuccess()) {
			if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
				throw new RuntimeException(serviceResult.getMessage());
			} else {
				throw new BusinessException(serviceResult.getMessage());
			}
		}
		HttpJsonResult<List<Seller>> jsonResult = new HttpJsonResult<List<Seller>>();
		jsonResult.setRows(serviceResult.getResult());
		jsonResult.setTotal(pager.getRowsCount());
		log.debug("jsonResult size=" + jsonResult.getTotal());
		return jsonResult;
	}
		
	
	//返回弹出框页面memberdialog，从会员列表中获取会员
	@RequestMapping(value = "memberdialog", method = { RequestMethod.GET })
	@ResponseBody
	// 本数据从商家管理表中获取
	public HttpJsonResult<List<Member>> memberdialog(HttpServletRequest request, Map<String, Object> dataMap,ModelMap modelmap) {
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResult<List<Member>> serviceResult = memberService.getMembersNameAndRegisterTime(queryMap, pager);
		if (!serviceResult.getSuccess()) {
			if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
				throw new RuntimeException(serviceResult.getMessage());
			} else {
				throw new BusinessException(serviceResult.getMessage());
			}
		}
		HttpJsonResult<List<Member>> jsonResult = new HttpJsonResult<List<Member>>();
		jsonResult.setRows(serviceResult.getResult());
		jsonResult.setTotal(pager.getRowsCount());
		log.debug("jsonResult size=" + jsonResult.getTotal());
		return jsonResult;
	}
	
	
	//进入添加投诉页面add
	@RequestMapping(value = "goadd", method = { RequestMethod.GET })
	public String goadd() {
		return "admin/seller/compailnregister/add";
	}
	
	//进行添加create.html
	@RequestMapping(value = "create.html", method = { RequestMethod.POST })
	public String create(ComplainRegister complainRegister,HttpServletRequest request,Map<String, Object> dataMap){
		//获取当前登陆用户
		String adminName = WebAdminSession.getAdminUser(request).getName();
		complainRegister.setCreatePerson(adminName);
		complainRegisterService.saveComplainRegister(complainRegister);
		
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/compailnregister/compainlist";
	}
	
	//进入编辑页面goUpdate
	@RequestMapping(value = "goUpdate", method = { RequestMethod.GET })
	public String goUpdate(@RequestParam(value = "id", required = true) Integer productRegisterId,
			Map<String, Object> dataMap) {
		ServiceResult<ComplainRegister> serviceResult = complainRegisterService.getComplainRegisterById(productRegisterId);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMessage());
		}
		ComplainRegister result = serviceResult.getResult();
		dataMap.put("complainRegister", serviceResult.getResult());
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/compailnregister/update";
	}
	
	//进行更新操作update.html,更新成功跳转至列表页面
	@RequestMapping(value = "toUpdate", method = { RequestMethod.POST })
	public String toUpdate(ComplainRegister complainRegister,HttpServletRequest request,Map<String, Object> dataMap){
		String complainSeller = complainRegister.getComplainSeller();
		Integer sellerId=sellerService.getSellerIdBySellerName(complainSeller);
		//设置id并保存
		complainRegister.setSellerId(sellerId);
		//获取当前登陆用户
		String adminName = WebAdminSession.getAdminUser(request).getName();
		complainRegister.setCreatePerson(adminName);
		Date tiem=new Date();
		complainRegister.setUpdateTime(tiem);
		complainRegisterService.updateComplainRegister(complainRegister);
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/compailnregister/compainlist";
	}
	
	//registerinfo查看详情
	@RequestMapping(value = "registerinfo", method = { RequestMethod.GET })
	public String registerinfo(@RequestParam(value = "id", required = true) Integer productRegisterId,Map<String, Object> dataMap) {
		ServiceResult<ComplainRegister> serviceResult = complainRegisterService.getComplainRegisterById(productRegisterId);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMessage());
		}
		ComplainRegister result = serviceResult.getResult();
		dataMap.put("complainRegister", serviceResult.getResult());
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/compailnregister/registerinfo";
	}
	
	//执行删除操作delete
	@RequestMapping(value = "delete", method = { RequestMethod.GET })
    public String delete(@RequestParam(value = "id", required = true)Integer id,Map<String, Object> dataMap) throws Exception {
        ServiceResult<Boolean> serviceResult = complainRegisterService.deleteById(id);
        HttpJsonResult<Boolean> jsonResult = null;
        if (serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Boolean>();
        } else {
            jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
        }
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "admin/seller/compailnregister/compainlist";
    }
}
