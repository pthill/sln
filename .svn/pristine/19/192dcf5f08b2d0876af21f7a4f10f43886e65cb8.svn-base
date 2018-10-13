package com.sln.web.controller.system;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.Md5;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.echarts.util.EchartsDataProvider;
import com.sln.entity.member.MemberProductBack;
import com.sln.entity.member.MemberProductExchange;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.order.Orders;
import com.sln.entity.product.Product;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.seller.SellerUserLoginLog;
import com.sln.service.member.IMemberProductBackService;
import com.sln.service.member.IMemberProductExchangeService;
import com.sln.service.order.IAdminComplaintService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductService;
import com.sln.service.report.IStatisticsService;
import com.sln.service.seller.ISellerApplyService;
import com.sln.service.seller.ISellerResourcesRolesService;
import com.sln.service.seller.ISellerService;
import com.sln.service.seller.ISellerUserService;
import com.sln.service.system.ISystemNoticeService;
import com.sln.vo.seller.SellerComplaintVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

@Controller
@RequestMapping(value = "seller")
// @Scope("prototype")
public class SellerLoginController extends BaseController {

	@Resource
	private ISellerService sellerService;
	@Resource
	private ISellerUserService sellerUserService;
	@Resource
	private ISellerResourcesRolesService sellerResourcesRolesService;
	@Resource
	private ISystemNoticeService systemNoticeService;
	@Resource
	private ISellerApplyService sellerApplyService;
	@Resource
	private IStatisticsService statisticsService;
	@Resource
	private IProductService productService;
	@Resource(name = "ordersService")
	private IOrdersService orderService;
	@Resource(name = "adminComplaintServiceImpl")
	IAdminComplaintService sellerComplaintService;
	@Resource
	private IMemberProductBackService memberProductBackService;
	@Resource
	private IMemberProductExchangeService memberProductExchangeService;

	private Logger log = Logger.getLogger(this.getClass());

	@RequestMapping(value = "changethtme", method = { RequestMethod.GET })
	public String changethtme(Map<String, Object> dataMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SellerUser su = WebSellerSession.getSellerUser(request);
		if (isNull(su)) {
			return "redirect:/seller/login.html";
		}
		return "seller/changetheme";
	}

	@RequestMapping(value = "/login.html", method = { RequestMethod.GET })
	public String login(Map<String, Object> dataMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		log.info("======user login=======");
		String message = request.getParameter("message");
		dataMap.put("message", message);
		return "seller/login";
	}

	/**
	 * 订单概况
	 * 
	 * @param dataMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "orderOverview", method = { RequestMethod.GET })
	public String orderOverview(Map<String, Object> dataMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SellerUser su = WebSellerSession.getSellerUser(request);
		if (isNull(su)) {
			return "redirect:/seller/login.html";
		}
		// 默认当前年
		Calendar cal = Calendar.getInstance();
		Calendar cur = Calendar.getInstance();
		cur.clear();
		cur.set(Calendar.YEAR, cal.get(Calendar.YEAR));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		// 今年第一天
		String startTime = sdf.format(cur.getTime());
		// 当前时间
		String endTime = sdf.format(cal.getTime());

		Map<String, String> map = new HashMap<String, String>();
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("q_sellerId", WebSellerSession.getSellerUser(request)
				.getSellerId() + "");
		ServiceResult<List<Object>> serviceResult = statisticsService
				.getOrderOverviewData(map);
		if (!serviceResult.getSuccess()) {
			if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult
					.getCode())) {
				throw new RuntimeException(serviceResult.getMessage());
			} else {
				throw new BusinessException(serviceResult.getMessage());
			}
		}
		dataMap.put(
				"option",
				EchartsDataProvider.getOrderOverviewData(
						serviceResult.getResult(), startTime, endTime, true));
		return "seller/orderoverview";
	}

	/**
	 * 销售统计
	 * 
	 * @param dataMap
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "saleOverview", method = { RequestMethod.GET })
	public String saleOverview(Map<String, Object> dataMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SellerUser su = WebSellerSession.getSellerUser(request);
		if (isNull(su)) {
			return "redirect:/seller/login.html";
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());

		// 默认按年统计
		String model = "month";

		// 当前时间
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;

		dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

		Map<String, String> map = new HashMap<String, String>();
		if (model.equals("year"))
			map.put("year", year + "");
		if (model.equals("month")) {
			year = cal.get(Calendar.YEAR);
			map.put("year", year + "");
			map.put("month", month + "");
		}
		map.put("s_status", "3,4,5");
		map.put("q_sellerId", WebSellerSession.getSellerUser(request).getId()
				+ "");
		map.put("model", model);
		ServiceResult<Map<String, List<Object>>> serviceResult = statisticsService
				.getSaleStatistics(map);
		if (!serviceResult.getSuccess()) {
			if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult
					.getCode())) {
				throw new RuntimeException(serviceResult.getMessage());
			} else {
				throw new BusinessException(serviceResult.getMessage());
			}
		}

		Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
		dataMap.put("queryMap", queryMap);

		dataMap.put("option", EchartsDataProvider.getStoreSaleData(
				serviceResult.getResult(), model, true));
		dataMap.put("currentYear", year);
		dataMap.put("currentMonth", month < 10 ? "0" + month : month);
		dataMap.put("model", model);
		return "seller/saleoverview";
	}

	@RequestMapping(value = "/exit", method = { RequestMethod.GET })
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		HttpSession session = request.getSession();
		Enumeration<?> em = session.getAttributeNames();
		// 清空session
		while (em.hasMoreElements()) {
			session.removeAttribute(em.nextElement().toString());
		}
		// 清除cookie
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			cookie.setMaxAge(0);
		}
		// 重定向
		return "redirect:/seller/login.html";
	}

	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/doLogin", method = { RequestMethod.POST })
	public String doLogin(Map<String, Object> dataMap,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String verifyCode = request.getParameter("verifyCode");
		String verify_number = WebSellerSession.getVerifyNumber(request);

		if (name == null) {
			dataMap.put("message", "用户名不能为空");
			return "seller/login";
		}
		if (password == null) {
			dataMap.put("message", "用户名不能为空");
			return "seller/login";
		}

		if (verify_number == null
				|| !verify_number.equalsIgnoreCase(verifyCode)) {
			dataMap.put("message", "验证码不正确");
			return "seller/login";
		}

		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, Md5
				.getMd5String(password).toCharArray());
		// token.setRememberMe(true);
		try {
			subject.login(token);
			SellerUser sellerUser = (SellerUser) subject.getPrincipal();

			if (sellerUser.getState().intValue() != SellerUser.STATE_NORM) {
				throw new Exception("账户冻结，不能登录！");
			}

			ServiceResult<Seller> serviceResult = sellerService
					.getSellerById(sellerUser.getSellerId());
			if (!serviceResult.getSuccess()) {
				throw new Exception(serviceResult.getMessage());
			}

			Seller seller = serviceResult.getResult();
			if (seller == null) {
				throw new Exception("所属店铺信息获取失败，请重试！");
			}
			if (seller.getAuditStatus().equals(Seller.AUDIT_STATE_1_SEND)) {
				throw new Exception("所属店铺未被审核通过！");
			}
			sellerUser.setSeller(seller);

			String ip = WebUtil.getIpAddr(request);
			SellerUserLoginLog sellerUserLoginLog = new SellerUserLoginLog();
			sellerUserLoginLog.setCreateTime(new Date());
			sellerUserLoginLog.setLoginIp(ip);
			sellerUserLoginLog.setUserId(sellerUser.getId());
			sellerUserLoginLog.setUserName(sellerUser.getName());
			ServiceResult<Boolean> LoginLogRlt = sellerUserService
					.saveSellerUserLoginLog(sellerUserLoginLog);
			if (!LoginLogRlt.getSuccess() || LoginLogRlt.getResult() == null) {
				throw new Exception(LoginLogRlt.getMessage());
			}

			WebSellerSession.putSellerUser(request, sellerUser);

		} catch (UnknownAccountException e) {
			log.error("账号不存在：{}", e);
			dataMap.put("message", "账号不存在");
			return "seller/login";
		} catch (DisabledAccountException e) {
			log.error("账号未启用：{}", e);
			dataMap.put("message", "账号未启用");
			return "seller/login";
		} catch (IncorrectCredentialsException e) {
			log.error("密码错误：{}", e);
			dataMap.put("message", "账号或密码错误");
			return "seller/login";
		} catch (RuntimeException e) {
			log.error("未知错误,请联系管理员：{}", e);
			dataMap.put("message", "未知错误,请联系管理员");
			return "seller/login";
		} catch (Exception e) {
			// 其他异常时退出
			subject.logout();
			log.error("未知错误,请联系管理员：{}", e);
			dataMap.put("message", e.getMessage());
			return "seller/login";
		}

		return "redirect:/seller/index.html";
	}

	@RequestMapping(value = "/index.html", method = { RequestMethod.GET })
	public String index(HttpServletRequest request,
			HttpServletResponse response, ModelMap dataMap) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		SellerUser user = WebSellerSession.getSellerUser(request);
		if (!subject.isAuthenticated() || isNull(user)) {
			response.getWriter().print(
					"<script>top.window.location.href='"
							+ request.getContextPath()
							+ "/seller/login.html'</script>");
			return null;
		}
		Seller seller = user.getSeller();

		// 首页公告
		PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
		Map<String, String> queryMap = new HashMap<String, String>();
		pager.setPageSize(4);// 只显示4条
		ServiceResult<List<SystemNotice>> sr = systemNoticeService.page(
				queryMap, pager);
		dataMap.put("noticelist", sr.getResult());

		SellerApply apply = sellerApplyService.getSellerApplyByUser(
				seller.getMemberId()).getResult();
		if (!isNull(apply))
			seller.setCompany(apply.getCompany());
		dataMap.put("user", user);

		// 订单
		Map<String, String> param = new HashMap<>();
		param.put("q_sellerId", seller.getId() + "");
		ServiceResult<List<Orders>> orders = orderService
				.getOrders(param, null);

		// 待确认订单数
		int uncnf = 0;
		// 待发货订单数
		int undev = 0;

		for (Orders od : orders.getResult()) {
			if (od.getOrderState().intValue() == Orders.ORDER_STATE_2) {
				uncnf++;
			} else if (od.getOrderState().intValue() == Orders.ORDER_STATE_3) {
				undev++;
			}
		}

		// 退货
		param.clear();
		param.put("sellerId", WebSellerSession.getSellerUser(request)
				.getSellerId().toString());
		ServiceResult<List<MemberProductBack>> mpb = memberProductBackService
				.page(param, null);

		// 换货
		ServiceResult<List<MemberProductExchange>> mpe = memberProductExchangeService
				.getMemberProductExchanges(param, null);

		// 用户投诉
		ServiceResult<List<SellerComplaintVO>> scv = sellerComplaintService
				.page(param, null);

		// 待售商品(刚创建、提交审核、审核通过、下架)
		param.clear();
		// 1、刚创建；2、提交审核；3、审核通过；4、申请驳回；7、下架
		param.put("q_state", "1,2,3,4,7");
		param.put("q_sellerId", seller.getId() + "");
		ServiceResult<List<Product>> pro = productService.pageProduct(param,
				null);

		dataMap.put("uncnf", uncnf);
		dataMap.put("undev", undev);
		dataMap.put("backcount", mpb.getResult() == null ? 0 : mpb.getResult().size());
		dataMap.put("expcount", mpe.getResult() == null ? 0 : mpe.getResult().size());
		dataMap.put("compcount", scv.getResult() == null ? 0 : scv.getResult().size());
		dataMap.put("unsalecount",pro.getResult() == null ? 0 : scv.getResult().size());

		return "seller/index";
	}

	/**
	 * 访问无权限URL时跳转路径
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/unauth.html", method = { RequestMethod.GET })
	public String unAuth() throws Exception {
		return "seller/unauth";
	}

}
