package com.sln.web.controller.product;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
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
import com.sln.entity.member.Member;
import com.sln.entity.product.ProductBrand;
import com.sln.entity.product.ProductRegister;
import com.sln.entity.seller.SellerApplyBrand;
import com.sln.service.message.IMessageService;
import com.sln.service.product.IProductRegisterService;
import com.sln.web.util.WebAdminSession;

@Controller
@RequestMapping(value = "admin/product/onregister")
public class ProductResigterController {

	@Resource
	private IProductRegisterService productRegisterService;
	@Resource
	private IMessageService messageService;

	private String baseUrl = "admin/product/manager/";
	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String init(Map<String, Object> dataMap) {
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/product/manager/listonregister";
	}

	/**
	 * 或取商品缺货列表registerlist
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	public HttpJsonResult<List<ProductRegister>> registerlist(
			HttpServletRequest request, Map<String, Object> dataMap) {
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId() .toString());
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResult<List<ProductRegister>> serviceResult = productRegisterService.getProductsRegister(queryMap, pager);
		if (!serviceResult.getSuccess()) {
			if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult
					.getCode())) {
				throw new RuntimeException(serviceResult.getMessage());
			} else {
				throw new BusinessException(serviceResult.getMessage());
			}
		}
		HttpJsonResult<List<ProductRegister>> jsonResult = new HttpJsonResult<List<ProductRegister>>();
		jsonResult.setRows(serviceResult.getResult());
		jsonResult.setTotal(pager.getRowsCount());
		log.debug("jsonResult size=" + jsonResult.getTotal());
		return jsonResult;
	}

	// 进入缺货通过编辑页面
	@RequestMapping(value = "pass", method = { RequestMethod.GET })
	public String edit(
			@RequestParam(value = "id", required = true) Integer productRegisterId,
			Map<String, Object> dataMap) {

		String path = "";
		// 平台商品审核
		ServiceResult<ProductRegister> serviceResult = productRegisterService
				.getProductRegisterById(productRegisterId);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMessage());
		}
		ProductRegister result = serviceResult.getResult();
		dataMap.put("ProductRegister", serviceResult.getResult());
		return path = "/admin/product/onregister/through";
	}

	// 进入打回编辑页面drawback
	@RequestMapping(value = "drawback", method = { RequestMethod.GET })
	public String drawback(@RequestParam(value = "id", required = true) Integer productRegisterId,Map<String, Object> dataMap) {
		String path = "";
		// 平台商品审核
		ServiceResult<ProductRegister> serviceResult = productRegisterService
				.getProductRegisterById(productRegisterId);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMessage());
		}
		ProductRegister result = serviceResult.getResult();
		dataMap.put("ProductRegister", serviceResult.getResult());
		return path = "/admin/product/onregister/dodrawback";
	}

	//执行打回操作后，跳转到列表页面refuse
	@RequestMapping(value = "refuse", method = { RequestMethod.GET })
	public String refuse(@RequestParam(value = "id", required = true) Integer id,@RequestParam(value = "reastion", required = true) String retroactionReason, Map<String, Object> datamap) {
		//获取打回原因
		datamap.put("id", id);
		datamap.put("retroactionReason", retroactionReason);
		//根据id跟新状态和操作时间
		productRegisterService.updateProductStateByIdre(datamap);
		//根据商品详情id获取会员id
		ProductRegister memberIdByProRegiste = productRegisterService.getMemberIdByProRegiste(id);
		String memberId = memberIdByProRegiste.getMemberId();
	
		
		
		//消息发送
		messageService.sendMessageToMessage(datamap, Integer.parseInt(memberId), "缺货登记打回");
		datamap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/product/manager/listonregister";
	}
	
	
	// 执行审核操作后，跳转到列表页Auditing
	@RequestMapping(value = "Auditing", method = { RequestMethod.GET })
	public String Auditing(@RequestParam(value = "id", required = true) Integer Id,HttpServletRequest request, Map<String, Object> datamap) {
		
		//根据id跟新状态和操作时间
		productRegisterService.updateProductStateById(Id);
		//根据商品详情id获取会员id
		ProductRegister memberIdByProRegiste = productRegisterService.getMemberIdByProRegiste(Id);
		String memberId = memberIdByProRegiste.getMemberId();
	
		//消息发送
		messageService.sendMessageToMessage(datamap, Integer.parseInt(memberId), "缺货补货成功");
		datamap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/product/manager/listonregister";
	}

	
	/**
	 * 跳转到商品登记页面register
	 */
	@RequestMapping(value = "onregister", method = { RequestMethod.GET })
	public String onregister(HttpServletRequest request,
			Map<String, Object> modelmap) {
		modelmap.put("q_useYn", "1");
		modelmap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		// modelmap.put("", "1");
		return baseUrl + "listonregister";
	}
	
	//查询反馈的详细信息registerinfo
	@RequestMapping(value = "registerinfo", method = { RequestMethod.GET })
	public String registerinfo(@RequestParam(value = "id", required = true) Integer productRegisterId,Map<String, Object> dataMap) {
		String path = "";
		// 平台商品审核
		ServiceResult<ProductRegister> serviceResult = productRegisterService
				.getProductRegisterById(productRegisterId);
		if (!serviceResult.getSuccess()) {
			throw new RuntimeException(serviceResult.getMessage());
		}
		ProductRegister result = serviceResult.getResult();
		dataMap.put("ProductRegister", serviceResult.getResult());
		return path = "/admin/product/onregister/registerinfo";
	}

}
