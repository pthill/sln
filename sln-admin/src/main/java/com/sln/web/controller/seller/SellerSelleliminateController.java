package com.sln.web.controller.seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.compain.ComplainRegister;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerEliminate;
import com.sln.service.seller.ISellerEliminateService;
import com.sln.service.seller.ISellerService;
import com.sln.util.RedisClient;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 商家淘汰
 * @author luoyong
 */
@Controller
@RequestMapping(value = "admin/seller/eliminate")
public class SellerSelleliminateController extends BaseController {
	@Resource
	private ISellerEliminateService sellerEliminateService;
	@Resource
	private RedisClient redisClient;
	@Resource
	private ISellerService sellerService;

	/**
	 * 默认进入的页面
	 * @param dataMap
	 * @return
	 */
	@RequestMapping(value = "", method = { RequestMethod.GET })
	public String init(Map<String, Object> dataMap) {
		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/eliminate/selleliminatelist";
	}

	/**
	 * 进入商家列表页面
	 */
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	@ResponseBody
	// 本数据从商家管理表中获取
	public HttpJsonResult<List<Seller>> registerlist(
			HttpServletRequest request, Map<String, Object> dataMap,
			ModelMap modelmap) {
		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		queryMap.put("adminId", WebAdminSession.getAdminUser(request).getId().toString());
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		ServiceResult<List<Seller>> serviceResult = sellerService.getSellEliminate(queryMap, pager);
		
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

	/**
	 * 设置商家淘汰机制resourceful
	 */
	/*@RequestMapping(value = "resource", method = { RequestMethod.GET })
	public String resource() {
		return "admin/seller/eliminate/editresourceful";
	}*/

	//添加数据回显的淘汰机制
	@RequestMapping(value = "resource", method = { RequestMethod.GET })
	public String resource(ModelMap modelmap) {
		List<ComplainRegister> comid = redisClient.getBeanList("tempid", ComplainRegister.class);
		List<SellerEliminate> sellerEliminate=sellerEliminateService.getSellerEliminateList();
		modelmap.put("one", sellerEliminate.get(0));
		modelmap.put("two", sellerEliminate.get(1));
		modelmap.put("three", sellerEliminate.get(2));
		modelmap.put("four", sellerEliminate.get(3));
		return "admin/seller/eliminate/editresourceful";
	}
	
	/**
	 * 接收前端传递表单参数
	 * @param create.html
	 */
	@RequestMapping(value = "create.html", method = { RequestMethod.POST })
	public String ReciveParam(HttpServletRequest request,Map<String, Object> datamap) {
		List<SellerEliminate> list = new ArrayList<>();
		String[] parameter1 = request.getParameterValues("onetipValue");
		String[] parameter2 = request.getParameterValues("oneeliminateValue");
		String[] parameter3 = request.getParameterValues("onewarnValue");
		String[] lim_id = request.getParameterValues("lim_id");
		    for (int i = 0; i < parameter1.length; i++) {
			SellerEliminate sellerEliminate1 = new SellerEliminate();
			sellerEliminate1.setKindType(i + 1);
			sellerEliminate1.setTipValue(Integer.parseInt(parameter1[i]));
			sellerEliminate1.setEliminateValue(Integer.parseInt(parameter2[i]));
			sellerEliminate1.setWarnValue(Integer.parseInt(parameter3[i]));
			sellerEliminate1.setId(Integer.parseInt(lim_id[i]));
			list.add(sellerEliminate1);
		}
		/*
		 * 插入之前先判断，有数据就批量更新，没有就批量插入
		 */
		Integer count = sellerEliminateService.getcount();
		if (count > 0) {
			sellerEliminateService.updateBatch(list);
		} else {
			sellerEliminateService.insertlist(list);
		}
		datamap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
		return "admin/seller/eliminate/selleliminatelist";
	}
	
}
