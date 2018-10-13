package com.sln.web.controller.member;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.sln.entity.member.Member;
import com.sln.entity.product.ProductRegister;
import com.sln.service.product.IProductRegisterService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

/**
 * 客户服务    缺货登记反馈
 * @author luoyong
 *
 */
@Controller
@RequestMapping(value = "member")
public class CustomerServiceController extends BaseController {

	@Resource
	private IProductRegisterService productRegisterService;
	
	@RequestMapping(value = "/retroaction.html", method = RequestMethod.GET)
	public String fondCustomerRetroaction(HttpServletRequest request,Map<String, Object> dataMap){
		this.head(0,dataMap,request);
		
		//先从session中获取登陆用户，如果当前用户不存在先进行登陆
		  Member loginedUser = WebFrontSession.getLoginedUser(request);
		  if(loginedUser==null){
			  return "redirect:/login.html";
		  }
		  //获得当前用户的编号
		  dataMap.put("currentname", loginedUser.getName());
		  //根据当前用户编号查询该用户相关的登记反馈信息集合
		  List<ProductRegister> productRegisterList = productRegisterService.getProductRegisterByCurrentId(loginedUser.getId());
		  dataMap.put("productRegisterList", productRegisterList);
		  //return  "front/member/service/productretroaction";
		  return  "front/portal/service/productretroaction";
	}
}
