package com.sln.web.controller.product;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.entity.product.ProductRegister;
import com.sln.service.product.IProductRegisterService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.MemberSession;
import com.sln.web.util.WebFrontSession;

/**
 * @author luoyong param 商品缺货登记
 */
@Controller
public class ProductChanageController extends BaseController {

	@Resource
	private IProductRegisterService productRegisterService;

	/**
	 * @param request
	 * @param 进入到添加页面
	 * @return
	 */
	@RequestMapping(value = "/product/stock.html", method = RequestMethod.GET)
	public String add(HttpServletRequest request) {
		WebFrontSession fontSession = new WebFrontSession();
		MemberSession memberSession = fontSession.getMemberSession(request);
		if (memberSession == null) {
			return "redirect:/login.html";
		}
		return "front/product/productstockout";
	}

	/**
	 * 进行缺货商品的登记
	 */
	// create.html
	@RequestMapping(value = "/product/create.html", method = RequestMethod.POST)
	public String create(ProductRegister productregister, HttpServletRequest request) {
		WebFrontSession fontSession = new WebFrontSession();
		MemberSession memberSession = fontSession.getMemberSession(request);
		Integer id = memberSession.getMember().getId();
		Date date = new Date();
		productregister.setProductRegiStat("1");
		productregister.setCreateTime(date);
		productregister.setMemberId(id.toString());
		productRegisterService.saveProductRegister(productregister);
		return "redirect:/index.html";
	}
}
