package com.sln.web.appapi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.HttpJsonResultForAjax;
import com.sln.core.Md5;
import com.sln.core.PagerInfo;
import com.sln.core.RandomUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.TimeUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.core.sms.bean.VerifyCodeResult;
import com.sln.dto.MemberDto;
import com.sln.entity.cart.Cart;
import com.sln.entity.coupon.Coupon;
import com.sln.entity.coupon.CouponUser;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.flash.ActFlashSaleStage;
import com.sln.entity.full.ActFull;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberAddress;
import com.sln.entity.member.MemberCollectionProduct;
import com.sln.entity.member.MemberCollectionSeller;
import com.sln.entity.mindex.MIndexBanner;
import com.sln.entity.mindex.MIndexFloor;
import com.sln.entity.mindex.MRecommend;
import com.sln.entity.operate.Config;
import com.sln.entity.order.Invoice;
import com.sln.entity.order.Orders;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductBrand;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductNorm;
import com.sln.entity.product.ProductNormAttr;
import com.sln.entity.product.ProductTypeAttr;
import com.sln.entity.search.SearchSetting;
import com.sln.entity.seller.Seller;
import com.sln.entity.single.ActSingle;
import com.sln.service.cart.ICartService;
import com.sln.service.member.IInvoiceService;
import com.sln.service.member.IMemberAddressService;
import com.sln.service.member.IMemberCollectionProductService;
import com.sln.service.member.IMemberCollectionSellerService;
import com.sln.service.member.IMemberService;
import com.sln.service.mindex.IMIndexService;
import com.sln.service.mindex.IMRecommendService;
import com.sln.service.operate.IConfigService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductCateService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.product.IProductGoodsService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.service.promotion.IActFullService;
import com.sln.service.promotion.IActSingleService;
import com.sln.service.promotion.ICouponService;
import com.sln.service.search.ISearchSettingService;
import com.sln.service.seller.ISellerService;
import com.sln.service.sms.ISenderService;
import com.sln.util.FrontProductPictureUtil;
import com.sln.vo.cart.CartInfoVO;
import com.sln.vo.member.FrontMemberProductBehaveStatisticsVO;
import com.sln.vo.order.OrderCommitVO;
import com.sln.vo.order.OrderCouponVO;
import com.sln.vo.order.OrderSuccessVO;
import com.sln.vo.product.FrontProductCateVO;
import com.sln.vo.product.ProductActVO;
import com.sln.vo.product.ProductTypeAttrVO;
import com.sln.vo.search.SearchProductVO;
import com.sln.web.controller.BaseController;
import com.sln.web.csrf.CsrfTokenManager;
import com.sln.web.util.CommUtil;
import com.sln.web.util.MySessionContext;
import com.sln.web.util.WebFrontSession;

/**
 * 移动app接口
 *
 * @Filename: AppController.java
 * @Version: 1.0
 * @Author: 胡龙巧
 * @Email: hulongqiao@sina.com
 *
 */
@Controller
@RequestMapping(value = "api")
public class AppController extends BaseController {
	private static Logger   log = LogManager.getLogger(AppController.class);
	
	 @Resource
	 private IMemberService memberService;
	 
	 @Resource
	 private IProductCateService productCateService;
	 
	 @Resource
	 private ICartService         cartService;
	 
	 @Resource
	 private IMIndexService              mIndexService;
	 
	 @Resource
	 private IMRecommendService          mRecommendService;
	 
	 @Resource
	 private ISearchSettingService searchSettingService;
	 
	 @Resource
	    private IProductFrontService    productFrontService;

	 @Resource
	    private FrontProductPictureUtil frontProductPictureUtil;
    
	 @Resource
	    private IProductGoodsService    productGoodsService;
	 
	 @Resource
	    private ISellerService          sellerService;
	 @Resource
	    private IActFlashSaleService    actFlashSaleService;
	 
	 @Resource
	    private IOrdersService          ordersService;
	 
	 @Resource
	    private IActSingleService       actSingleService;
	 
	 @Resource
	    private IActFullService         actFullService;
	    @Resource
	    private ICouponService          couponService;
	    
	    @Resource
	    private IMemberCollectionSellerService  memberCollectionSellerService;
	    
	    @Resource
	    private IMemberCollectionProductService memberCollectionProductService;
	    
	    @Resource
	    private IInvoiceService         invoiceService;
	    
	    @Resource
	    private IMemberAddressService   memberAddressService;
	    
	    @Resource
	    private IConfigService          configservice;
	    
	    @Resource
	    private ISenderService 			senderService;
    
    /**
     * app登录
     * @param request
     * @param response
     * @param dataMap
     * @return
     * @throws Exception 
     */
	 @RequestMapping(value = "appdologin",method = { RequestMethod.POST })
	   public @ResponseBody HttpJsonResult<MemberDto> loginSumbit(HttpServletRequest request,
	                                                             HttpServletResponse response) {
		 String verifyCode = request.getParameter("verifyCode");
	        //获得session中的验证码
		 String sessionId = request.getParameter("sessionId");
		 HttpSession session = MySessionContext.getSession(sessionId);
		 String verify_number = (String) session.getAttribute(ConstantsEJS.VERIFY_NUMBER_NAME);
	        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
	            return new HttpJsonResult<MemberDto>("验证码不正确！");
	        }
	        
	        
	        String ip = WebUtil.getIpAddr(request);
	        String userName = request.getParameter("userName");
	        String password = request.getParameter("password");
	        
	        
	        //判断数据来源
	        String ua =  request.getHeader("user-agent").toLowerCase();
	        
	        int source = 0;
	        if(ua.contains("Android")) {
	        	source = ConstantsEJS.SOURCE_3_ANDROID;
	        	} else if(ua.contains("iPhone")) {
	        		source=ConstantsEJS.SOURCE_4_IOS;
	        	}  else {
	        		source=ConstantsEJS.SOURCE_2_H5;
	        	}
	        
	        
		 ServiceResult<Member> serviceResult = memberService.memberLogin(userName, password, ip,source);
		 
		 HttpJsonResult<MemberDto> jsonResult = new HttpJsonResult<MemberDto>();
		 
		 if (!serviceResult.getSuccess()) {
	                //throw new RuntimeException(serviceResult.getMessage());
	                jsonResult.setMessage(serviceResult.getMessage());
	                return jsonResult;
	        }else{
	        	 	Member member = serviceResult.getResult();
	    	        MemberDto memberDto = new MemberDto();
	    	        memberDto.setId(member.getId());
	    	        memberDto.setName(member.getName());
	    	        memberDto.setGrade(member.getGrade());
	    	        memberDto.setGradeValue(member.getGradeValue());
	    	        memberDto.setIntegral(member.getIntegral());
	    	        
	    	        // 登录送经验值积分
	    	        //memberService.memberLoginSendValue(member.getId(), member.getName());
	    	        // 存入session
	    	        WebFrontSession.putMemberSession(request, member);
	    	        memberDto.setSessionId(member.getSessionId());
	    	        jsonResult.setData(memberDto);
	    	        return jsonResult;
	        }

	       
	 }
	 
	 /**
	  * app分类接口
	  */
	 	@RequestMapping(value = "appcatelist", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<List<FrontProductCateVO>> getProductCateList(HttpServletRequest request, HttpServletResponse response,
	                                     Map<String, Object> dataMap) {
	        Map<String, Object> queryMap = new HashMap<String, Object>();
	        ServiceResult<List<FrontProductCateVO>> serviceResult = new ServiceResult<List<FrontProductCateVO>>();
	        serviceResult = productCateService.getProductCateList(queryMap);
	        //dataMap.put("cateList", serviceResult.getResult());
	        HttpJsonResult<List<FrontProductCateVO>> jsonResult = new HttpJsonResult<List<FrontProductCateVO>>();
	        jsonResult.setData(serviceResult.getResult());
	        return jsonResult;
	    }
	 
	 /**
	  * app首页接口
	  * 查看数据是否有返回，h5首页
	  */
	 @RequestMapping(value = "/appinitIndex", method = { RequestMethod.POST })
	 private  @ResponseBody HttpJsonResult<Map<String, Object>> appinitIndex() {
		 Map<String, Object> stack = new HashMap<String, Object>();
	        ServiceResult<List<MIndexBanner>> bannerResult = mIndexService.getMIndexBannerForView(true);
	        stack.put("banners", bannerResult.getResult());
	        
	        //将false改为true，true就是表示展示全部楼层信息
	        ServiceResult<List<MIndexFloor>> floorResult = mIndexService .getMIndexFloorsWithData(true);
	        stack.put("floors", floorResult.getResult());

	        // 首页固定取6个
	        PagerInfo pager = new PagerInfo(6, 1);
	        // 首页推荐
	        ServiceResult<List<MRecommend>> hotRecommendResult = mRecommendService.getMRecommendForView(MRecommend.RECOMMEND_TYPE_1, true, pager);
	        if (!hotRecommendResult.getSuccess()) {
	            log.error(hotRecommendResult.getMessage());
	        }
	        stack.put("hotList", hotRecommendResult.getResult());
	        HttpJsonResult<Map<String, Object>> httpJsonResult = new HttpJsonResult<Map<String, Object>>();
	        httpJsonResult.setData(stack);
	        return httpJsonResult;
	 }
	 
	 /**
	  * app购物车接口
	  */
	 /**
	     * 跳转到 我的购物车 
	     * @param request
	     * @param response
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "/appcardetail", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<CartInfoVO> toMyCart(HttpServletRequest request, HttpServletResponse response) {

	    	HttpJsonResult<CartInfoVO> jsonResult = new HttpJsonResult<CartInfoVO>();
	    	Integer memberId = 0;
	    	//验证用户id,如果无 则返回 10000 表示需要登录
	        if(request.getParameter("memberId") == null && "".equals(request.getParameter("memberId"))){
	        	jsonResult.setMessage("10000");
	        	return jsonResult;
	        }else{
	        	 memberId = Integer.valueOf(request.getParameter("memberId"));
	        }

	        //取购物车信息  产品价格 按照商家来区分
	        //查询购物车
	        ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(memberId, null,
	            ConstantsEJS.SOURCE_2_H5, 1,Cart.SOURCE_1);
	        jsonResult.setData(serviceResult.getResult());
	        return jsonResult;
	    }
	    
	    /**
	     * 短信接口
	     * @return
	     */
	 @RequestMapping(value = "appMessage", method = { RequestMethod.POST })
	 public @ResponseBody HttpJsonResult<String> appThink(HttpServletRequest request, HttpServletResponse response){
		 HttpJsonResult<String> httpJsonResult = new HttpJsonResult<String>();
		 httpJsonResult.setSuccess(false);
		 String iphoneNum = request.getParameter("iphoneNum");
		 
		 
	     
	     //判断用户名是否已存在
         ServiceResult<Boolean> serviceResult = memberService.isMobExists(iphoneNum);
         if (!serviceResult.getSuccess()) {
        	 
             httpJsonResult.setMessage("手机号校验出错，请重试");
             return httpJsonResult;
         }
         
	        
         if (serviceResult.getResult() != null && serviceResult.getResult() == Boolean.TRUE) {
        	 httpJsonResult.setMessage("手机号重复，请重新输入");
             return httpJsonResult;
         }
         
         ServiceResult<Object> result = senderService.sendVerifyCode(iphoneNum);
		 HttpSession session = request.getSession();
		 String smsCode =  "reg_smsCode";
	     String exp_time = "reg_exp_time";
	     String vc_count = "reg_vc_count";
         
		 if (result.getSuccess()) {
	            VerifyCodeResult vcr = (VerifyCodeResult) result.getResult();

	            //将信息放入当前会话
	            session.setAttribute(smsCode, vcr.getVerifyCode());
	            session.setAttribute(exp_time, new Date().getTime());
	            session.setAttribute(vc_count, session.getAttribute(vc_count) == null ? 0
	                : ((Integer) session.getAttribute(vc_count)) + 1);

	            //当天只能获取5次
	            if (session.getAttribute(exp_time) != null
	                && isCur((long) session.getAttribute(exp_time))
	                && ((Integer) session.getAttribute(vc_count)) >= ConstantsEJS.SMS_MAX_GIVEN_NUM) {
	            	httpJsonResult.setMessage("今日获取验证码次数过多,请明日再试");
	            	return httpJsonResult;
	            }
	            httpJsonResult.setSuccess(true);
	            httpJsonResult.setData(vcr.getVerifyCode());
	            log.debug("短信发送完毕：验证码：" + vcr.getVerifyCode() + "|" + vcr.toString());
	        } else {
	        	httpJsonResult.setMessage(result.getMessage());
	        }
		 return httpJsonResult;
	 }
	 
	    /**
	     * 是否当天
	     * @param attribute
	     * @return
	     */
	    private boolean isCur(long time) {
	        long now = new Date().getTime();
	        long diff = (now - time) / (1000 * 60 * 60 * 24);
	        return diff == 0;
	    }
	    
	 
	 /**
	  * app注册接口
	  */
	 @RequestMapping(value = "/appregister", method = { RequestMethod.POST })
	 public @ResponseBody HttpJsonResult<Boolean> appRegister(HttpServletRequest request, HttpServletResponse response){
		 HttpJsonResult<Boolean> httpJsonResult = new HttpJsonResult<Boolean>();
		 try{
			 String verifyCode = request.getParameter("verifyCode");
		        String userName = request.getParameter("userName");
		        String password = request.getParameter("password");
		        String passwordCfm = request.getParameter("password");
		        String smsCode = request.getParameter("smsCode");
		        String phone = request.getParameter("phone");

		        //获得session中的验证码
		        String sessionId = request.getParameter("sessionId");
				 HttpSession session = MySessionContext.getSession(sessionId);
				 String verify_number = (String) session.getAttribute(ConstantsEJS.VERIFY_NUMBER_NAME);
		        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
		            return new HttpJsonResult<Boolean>("验证码不正确");
		        }


		        /*if (session.getAttribute("reg_smsCode") == null
		            || !smsCode.equals(session.getAttribute("reg_smsCode"))) {
		            return new HttpJsonResult<Boolean>("手机验证码错误");
		        }*/

		        if (StringUtil.isEmpty(userName, true)) {
		            return new HttpJsonResult<Boolean>("用户名不能为空");
		        }
		        
		        if(!userName.matches("[A-Za-z0-9\\-]{6,16}")) {
		        	return new HttpJsonResult<Boolean>("用户名只能为6-16位的数字或字母");
		        }

		        if (StringUtil.isEmpty(password, true)) {
		            return new HttpJsonResult<Boolean>("登录密码不能为空");
		        }
		        
		        if(!password.matches("[A-Za-z0-9\\-]{6,20}")) {
		        	return new HttpJsonResult<Boolean>("密码只能为6-20位的数字或字母");
		        }

		        if (!password.equals(passwordCfm)) {
		            return new HttpJsonResult<Boolean>("确认密码不正确，请重新输入");
		        }
		        

		        //判断用户名是否已存在
		        ServiceResult<List<Member>> serviceResult = memberService.getMemberByName(userName);
		        if (!serviceResult.getSuccess()) {
		            return new HttpJsonResult<Boolean>("用户名校验出错，请重试");
		        }
		        if (serviceResult.getResult() != null && serviceResult.getResult().size() > 0) {
		            return new HttpJsonResult<Boolean>("用户名重复，请重新输入");
		        }
		        
		        //判断手机号码是否已存在
		        ServiceResult<Boolean> serviceResult1 = memberService.isMobExists(phone);
		        if(serviceResult1.getResult()) {
		        	return new HttpJsonResult<Boolean>("手机号码重复，请重新输入。");
		        }
		        

		        // 初始化会员信息
		        Member member = new Member();
		        member.setName(userName);
		        member.setPassword(Md5.getMd5String(password));
		        member.setGrade(Member.GRADE_1);
		        member.setGradeValue(0);
		        member.setMobile(phone);
		        member.setIntegral(0);
		        member.setLastLoginIp(WebUtil.getIpAddr(request));
		        member.setLoginNumber(0);
		        member.setPwdErrCount(0);
		        member.setSource(ConstantsEJS.SOURCE_2_H5);
		        member.setBalance(new BigDecimal(0.00));
		        member.setBalancePwd("");
		        member.setIsEmailVerify(ConstantsEJS.YES_NO_0);
		        member.setIsSmsVerify(ConstantsEJS.YES_NO_1);
		        member.setSmsVerifyCode("");
		        member.setEmailVerifyCode("");
		        member.setCanReceiveSms(1);
		        member.setCanReceiveEmail(1);
		        member.setStatus(Member.STATUS_1);
		        member.setEmail("");
		        ServiceResult<Member> registerResult = memberService.memberRegister(member);
		        if (!registerResult.getSuccess()) {
		            	httpJsonResult = new HttpJsonResult<Boolean>(registerResult.getMessage());
		            	return httpJsonResult;
		        }

		        //注册时赠送经验值及积分
		       memberService.memberRegistSendValue(member.getId(), member.getName());
		 }catch(Exception e){
			 httpJsonResult = new HttpJsonResult<Boolean>("注册异常");
		 }
		 return httpJsonResult;
	 }
	 
	  /**
	     * 重置密码
	     * @param request
	     * @param response
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "/appdoforgetpassword", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<String> doForgetpassword(HttpServletRequest request,
	                                                                  HttpServletResponse response){
	    	 HttpJsonResult<String> httpJsonResult = new HttpJsonResult<String>();
	    	 httpJsonResult.setSuccess(false);
	    	 try{
	    		 String name = request.getParameter("name");
	    	        if (StringUtil.isEmpty(name)) {
	    	        	httpJsonResult.setMessage("用户名为空");
	    	            return httpJsonResult;
	    	        }
	    	        String mobile = request.getParameter("mobile");
	    	        if (StringUtil.isEmpty(mobile)) {
	    	        	httpJsonResult.setMessage("手机号码不能为空！");
	    	            return httpJsonResult;
	    	        }
	    	        String verifyCode = request.getParameter("verifyCode");
	    	        if (StringUtil.isEmpty(verifyCode)) {
	    	        	httpJsonResult.setMessage("验证码不能为空！");
	    	        	return httpJsonResult;
	    	        }

	    	        //获得session中的验证码
	    	        String verify_number = WebFrontSession.getVerifyNumber(request);
	    	        if (verify_number == null || !verify_number.equalsIgnoreCase(verifyCode)) {
	    	            httpJsonResult.setMessage("验证码输入错误，请重试！");
	    	            return httpJsonResult;
	    	        }

	    	        // 根据name取会员信息
	    	        ServiceResult<List<Member>> memberResult = memberService.getMemberByName(name);
	    	        if (!memberResult.getSuccess()) {
	    	        	httpJsonResult.setMessage(memberResult.getMessage());
	    	            return httpJsonResult;
	    	        }
	    	        if (memberResult.getResult() == null || memberResult.getResult().size() == 0) {
	    	        	httpJsonResult.setMessage("您输入的用户名不存在，请重试！");
	    	            return httpJsonResult;
	    	        }
	    	        Member member = memberResult.getResult().get(0);

	    	        if (member.getIsSmsVerify() == null
	    	            || member.getIsSmsVerify().equals(Member.IS_SMS_VERIFY_0)
	    	            || StringUtil.isEmpty(member.getMobile(), true)) {
	    	        	httpJsonResult.setMessage("对不起，您没有绑定手机！");
	    	            return httpJsonResult;
	    	        }

	    	        if (!mobile.equals(member.getMobile())) {
	    	        	httpJsonResult.setMessage("对不起，您输入的手机号码与您绑定的号码不一致，请输入正确的手机号码！");
	    	            return httpJsonResult;
	    	        }

	    	        String newPwd = RandomUtil.randomNumber(6);

	    	        Member memberNew = new Member();
	    	        memberNew.setId(member.getId());
	    	        memberNew.setPassword(Md5.getMd5String(newPwd));
	    	        	
	    	        ServiceResult<Boolean> updateMember = memberService.updateMember(memberNew);
	    	        if (!updateMember.getSuccess()) {
	    	        	httpJsonResult.setMessage(updateMember.getMessage());
	    	        	return httpJsonResult;
	    	        }
	    	        httpJsonResult.setData(newPwd);
	    	        // 发送短信的方法，需要运营商提供请求URL，不要删除这段代码
	    	        /*SendSms send = new SendSms();
	    	        Boolean sendSms = send.sendSms(mobile, "感谢您使用密码找回功能，您的新密码是：" + newPwd + "，请及时更改您的密码。");
	    	        if (!sendSms) {
	    	            jsonResult = new HttpJsonResult<Boolean>("短信发送异常，请稍后重试！");
	    	        }*/
	    	        
	    	      //发送重置密码的短信
	    	        ServiceResult<Object> result = senderService.sendResetPwd(mobile,newPwd);
	    	        if(!result.getSuccess()) {
	    	        	httpJsonResult.setMessage("短信发送异常，请稍后重试！");
	   	    		 	return httpJsonResult;
	    	        }
	    	        httpJsonResult.setSuccess(true);
	    	        
	    	 }catch(Exception e){
	    		 httpJsonResult.setMessage("重置异常请稍后重试");
	    		 return httpJsonResult;
	    	 }
	    	 return httpJsonResult;
	    }
	    
	    public static final int WIDTH  = 73; //生成的图片的宽度
	    public static final int HEIGHT = 27; //生成的图片的高度
	    
	    @RequestMapping(value ="/verifyCode" , method = { RequestMethod.GET })
	    public void verify(HttpServletRequest request, HttpServletResponse response, String name,
	                       String type) throws IOException {
	    	log.info("开始生产验证码...");
	        String createTypeFlag = request.getParameter("createTypeFlag");
	        //在内存中创建一张图片
	        BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	        //得到图片
	        Graphics g = bi.getGraphics();
	        //设置图片的背影色
	        setBackGround(g);
	        //设置图片的边框
	        setBorder(g);
	        //在图片上画干扰线
	        drawRandomLine(g);
	        //写在图片上随机数
	        String random = "";
	        if (null != type && "1".equals(type)) {
	            random = drawRandomNum((Graphics2D) g, "ch");
	        } else if (null != type && "2".equals(type)) {
	            random = drawRandomNum((Graphics2D) g, "nl");
	        } else if (null != type && "3".equals(type)) {
	            random = drawRandomNum((Graphics2D) g, "n");
	        } else {
	            random = drawRandomNum((Graphics2D) g, createTypeFlag);
	        }
	        //将随机数存在session中
	        HttpSession session = request.getSession(true);
	        if (StringUtil.isEmpty(name, true)) {
	            session.setAttribute(ConstantsEJS.VERIFY_NUMBER_NAME, random);
	            MySessionContext.AddSession(session);
	        } else {
	            session.setAttribute(name, random);
	        }
	        //设置响应头通知浏览器以图片的形式打开
	        response.setContentType("image/jpeg");
	        //设置响应头控制浏览器不要缓存
	        response.setDateHeader("expries", -1);
	        response.setHeader("Cache-Control", "no-cache");
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("sessionId", session.getId());
	        //将图片写给浏览器
	        ImageIO.write(bi, "jpg", response.getOutputStream());
	        response.getOutputStream().flush();
	        response.getOutputStream().close();
	    }
	    
	    /**
	     * 设置图片的背景色
	     * @param g
	     */
	    private void setBackGround(Graphics g) {
	        // 设置颜色
	        g.setColor(Color.WHITE);
	        // 填充区域
	        g.fillRect(0, 0, WIDTH, HEIGHT);
	    }

	    /**
	     * 设置图片的边框， 可加可以不加
	     * @param g
	     */
	    private void setBorder(Graphics g) {
	        // 设置边框颜色
	        //        g.setColor(Color.BLUE);
	        //        // 边框区域
	        //        g.drawRect(1, 1, WIDTH - 2, HEIGHT - 2);
	    }

	    /**
	     * 在图片上画随机线条
	     * @param g
	     */
	    private void drawRandomLine(Graphics g) {
	        // 设置颜色
	        g.setColor(Color.GREEN);
	        // 设置线条个数并画线
	        for (int i = 0; i < 7; i++) {
	            int x1 = new Random().nextInt(WIDTH);
	            int y1 = new Random().nextInt(HEIGHT);
	            int x2 = new Random().nextInt(WIDTH);
	            int y2 = new Random().nextInt(HEIGHT);
	            g.drawLine(x1, y1, x2, y2);
	        }
	    }

	    /**
	     * 画随机字符
	     * @param g
	     * @param createTypeFlag
	     * @return
	     * String... createTypeFlag是可变参数，
	     */
	    private String drawRandomNum(Graphics2D g, String... createTypeFlag) {
	        // 设置颜色
	        g.setColor(Color.RED);
	        // 设置字体
	        g.setFont(new Font("宋体", Font.BOLD, 20));
	        //常用的中国汉字
	        String baseChineseChar = "\u7684\u4e00\u4e86\u662f\u6211\u4e0d\u5728\u4eba\u4eec\u6709\u6765\u4ed6\u8fd9\u4e0a\u7740\u4e2a\u5730\u5230\u5927\u91cc\u8bf4\u5c31\u53bb\u5b50\u5f97\u4e5f\u548c\u90a3\u8981\u4e0b\u770b\u5929\u65f6\u8fc7\u51fa\u5c0f\u4e48\u8d77\u4f60\u90fd\u628a\u597d\u8fd8\u591a\u6ca1\u4e3a\u53c8\u53ef\u5bb6\u5b66\u53ea\u4ee5\u4e3b\u4f1a\u6837\u5e74\u60f3\u751f\u540c\u8001\u4e2d\u5341\u4ece\u81ea\u9762\u524d\u5934\u9053\u5b83\u540e\u7136\u8d70\u5f88\u50cf\u89c1\u4e24\u7528\u5979\u56fd\u52a8\u8fdb\u6210\u56de\u4ec0\u8fb9\u4f5c\u5bf9\u5f00\u800c\u5df1\u4e9b\u73b0\u5c71\u6c11\u5019\u7ecf\u53d1\u5de5\u5411\u4e8b\u547d\u7ed9\u957f\u6c34\u51e0\u4e49\u4e09\u58f0\u4e8e\u9ad8\u624b\u77e5\u7406\u773c\u5fd7\u70b9\u5fc3\u6218\u4e8c\u95ee\u4f46\u8eab\u65b9\u5b9e\u5403\u505a\u53eb\u5f53\u4f4f\u542c\u9769\u6253\u5462\u771f\u5168\u624d\u56db\u5df2\u6240\u654c\u4e4b\u6700\u5149\u4ea7\u60c5\u8def\u5206\u603b\u6761\u767d\u8bdd\u4e1c\u5e2d\u6b21\u4eb2\u5982\u88ab\u82b1\u53e3\u653e\u513f\u5e38\u6c14\u4e94\u7b2c\u4f7f\u5199\u519b\u5427\u6587\u8fd0\u518d\u679c\u600e\u5b9a\u8bb8\u5feb\u660e\u884c\u56e0\u522b\u98de\u5916\u6811\u7269\u6d3b\u90e8\u95e8\u65e0\u5f80\u8239\u671b\u65b0\u5e26\u961f\u5148\u529b\u5b8c\u5374\u7ad9\u4ee3\u5458\u673a\u66f4\u4e5d\u60a8\u6bcf\u98ce\u7ea7\u8ddf\u7b11\u554a\u5b69\u4e07\u5c11\u76f4\u610f\u591c\u6bd4\u9636\u8fde\u8f66\u91cd\u4fbf\u6597\u9a6c\u54ea\u5316\u592a\u6307\u53d8\u793e\u4f3c\u58eb\u8005\u5e72\u77f3\u6ee1\u65e5\u51b3\u767e\u539f\u62ff\u7fa4\u7a76\u5404\u516d\u672c\u601d\u89e3\u7acb\u6cb3\u6751\u516b\u96be\u65e9\u8bba\u5417\u6839\u5171\u8ba9\u76f8\u7814\u4eca\u5176\u4e66\u5750\u63a5\u5e94\u5173\u4fe1\u89c9\u6b65\u53cd\u5904\u8bb0\u5c06\u5343\u627e\u4e89\u9886\u6216\u5e08\u7ed3\u5757\u8dd1\u8c01\u8349\u8d8a\u5b57\u52a0\u811a\u7d27\u7231\u7b49\u4e60\u9635\u6015\u6708\u9752\u534a\u706b\u6cd5\u9898\u5efa\u8d76\u4f4d\u5531\u6d77\u4e03\u5973\u4efb\u4ef6\u611f\u51c6\u5f20\u56e2\u5c4b\u79bb\u8272\u8138\u7247\u79d1\u5012\u775b\u5229\u4e16\u521a\u4e14\u7531\u9001\u5207\u661f\u5bfc\u665a\u8868\u591f\u6574\u8ba4\u54cd\u96ea\u6d41\u672a\u573a\u8be5\u5e76\u5e95\u6df1\u523b\u5e73\u4f1f\u5fd9\u63d0\u786e\u8fd1\u4eae\u8f7b\u8bb2\u519c\u53e4\u9ed1\u544a\u754c\u62c9\u540d\u5440\u571f\u6e05\u9633\u7167\u529e\u53f2\u6539\u5386\u8f6c\u753b\u9020\u5634\u6b64\u6cbb\u5317\u5fc5\u670d\u96e8\u7a7f\u5185\u8bc6\u9a8c\u4f20\u4e1a\u83dc\u722c\u7761\u5174\u5f62\u91cf\u54b1\u89c2\u82e6\u4f53\u4f17\u901a\u51b2\u5408\u7834\u53cb\u5ea6\u672f\u996d\u516c\u65c1\u623f\u6781\u5357\u67aa\u8bfb\u6c99\u5c81\u7ebf\u91ce\u575a\u7a7a\u6536\u7b97\u81f3\u653f\u57ce\u52b3\u843d\u94b1\u7279\u56f4\u5f1f\u80dc\u6559\u70ed\u5c55\u5305\u6b4c\u7c7b\u6e10\u5f3a\u6570\u4e61\u547c\u6027\u97f3\u7b54\u54e5\u9645\u65e7\u795e\u5ea7\u7ae0\u5e2e\u5566\u53d7\u7cfb\u4ee4\u8df3\u975e\u4f55\u725b\u53d6\u5165\u5cb8\u6562\u6389\u5ffd\u79cd\u88c5\u9876\u6025\u6797\u505c\u606f\u53e5\u533a\u8863\u822c\u62a5\u53f6\u538b\u6162\u53d4\u80cc\u7ec6";
	        //数字和字母的组合
	        String baseNumLetter = "0123456789ABCDEFGHJKLMNOPQRSTUVWXYZ";
	        //纯数字
	        String baseNum = "0123456789";
	        //纯字母
	        String baseLetter = "ABCDEFGHJKLMNOPQRSTUVWXYZ";
	        //createTypeFlag[0]==null表示没有传递参数
	        if (createTypeFlag.length > 0 && null != createTypeFlag[0]) {
	            if (createTypeFlag[0].equals("ch")) {
	                // 截取汉字
	                return createRandomChar(g, baseChineseChar);
	            } else if (createTypeFlag[0].equals("nl")) {
	                // 截取数字和字母的组合
	                return createRandomChar(g, baseNumLetter);
	            } else if (createTypeFlag[0].equals("n")) {
	                // 截取数字
	                return createRandomChar(g, baseNum);
	            } else if (createTypeFlag[0].equals("l")) {
	                // 截取字母
	                return createRandomChar(g, baseLetter);
	            }
	        } else {
	            // 默认截取数字和字母的组合
	            return createRandomChar(g, baseNumLetter);
	        }

	        return "";
	    }

	    /**
	     * 创建随机字符
	     * @param g
	     * @param baseChar
	     * @return 随机字符
	     */
	    private String createRandomChar(Graphics2D g, String baseChar) {
	        StringBuffer sb = new StringBuffer();
	        int x = 5;
	        String ch = "";
	        // 控制字数
	        for (int i = 0; i < 4; i++) {
	            // 设置字体旋转角度
	            int degree = new Random().nextInt() % 30;
	            ch = baseChar.charAt(new Random().nextInt(baseChar.length())) + "";
	            sb.append(ch);
	            // 正向角度
	            g.rotate(degree * Math.PI / 180, x, 20);
	            g.drawString(ch, x, 20);
	            // 反向角度
	            g.rotate(-degree * Math.PI / 180, x, 20);
	            x += 18;
	        }
	        return sb.toString();
	    }
	    
	    /**
	     * app关键字搜索
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "/appsearch", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Map<String, Object>> appSearch(HttpServletRequest request,HttpServletResponse response){
	    	
	    	String keyword = StringUtil.trim(request.getParameter("keyword"));
	    	int start = Integer.valueOf(StringUtil.trim(request.getParameter("start")));
	    	
	    	
	    	if ("".equals(keyword)) {
	            List<String> keywords = getKeywords();
	            if (keywords.size() > 0) {
	                keyword = keywords.get(0);
	            } else {
	                keyword = "sln";
	            }
	        }
	        keyword = StringUtil.stringFilterSpecial(keyword);

	        ServiceResult<SearchSetting> serviceResult = searchSettingService
	            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }

	        //关键词过滤
	        SearchSetting searchSetting = serviceResult.getResult();
	        if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
	            ServiceResult<Integer> serviceResultKeyword = searchSettingService
	                .getSearchKeywordsByKeyword(keyword);
	            int countKeyword = serviceResultKeyword.getResult();
	            if (countKeyword > 0) {
	                return new HttpJsonResult<Map<String,Object>>("搜索异常");
	            }
	        }

	        String searchKeyword = "(" + keyword + ")";
	        int count = 0;
	        SolrClient client = getSolrClient();
	        SolrQuery query = new SolrQuery();

	        int  size = ConstantsEJS.DEFAULT_PAGE_SIZE;

	        query.setQuery(queryKeyWord(searchKeyword));

	        query.set("start", start);
	        query.set("rows", size);
	        // query.set("sort", SearchProductVO.SORT_ + " desc"); // 排序

	        query.setHighlight(true);
	        query.setHighlightSimplePre("<font color=\"red\">");
	        query.setHighlightSimplePost("</font>");
	        query.setParam("hl.fl", SearchProductVO.NAME1_);

	        List<Product> products = new ArrayList<Product>();
	        QueryResponse queryResponse = null;
	        try {
	        	queryResponse = client.query(query);
	            SolrDocumentList docs = queryResponse.getResults();
	            count = new Integer(docs.getNumFound() + "");
	            for (SolrDocument doc : docs) {
	                String id = (String) doc.getFieldValue(SearchProductVO.ID_);
	                Product product = queryResult(queryResponse, doc, id);
	                product.setIsSelf(productFrontService.getProductById(product.getId()).getResult().getIsSelf());
	                products.add(product);
	            }
	        } catch (SolrServerException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        Map<String, Object> stack = new HashMap<String, Object>();
	        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
	        stack.put("keywordNumber", start);
	        stack.put("count", count);
	        stack.put("keyword", keyword);
	        stack.put("producListVOs", products);
	    	HttpJsonResult<Map<String, Object>> httpJsonResult = new HttpJsonResult<Map<String, Object>>();
	    	httpJsonResult.setData(stack);
	    	return httpJsonResult;
	    }
	    
	    /**
	     * 商品单品页
	     */
	    @RequestMapping(value = "appproduct", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Map<String, Object>> appproduct(HttpServletRequest request,HttpServletResponse response){
	    	
	    	Map<String, Object> map = new HashMap<String,Object>();
	    	
	    	
	    	Integer productId =Integer.valueOf(request.getParameter("productId"));
	        String goodIdStr = request.getParameter("goodId");
	        Integer goodId = ConvertUtil.toInt(goodIdStr, 0);

	        Member member = WebFrontSession.getLoginedUser(request);
	        
	        
	      //获得商品信息
	        ServiceResult<Product> productResult = productFrontService.getProductById(productId);
	        if (!productResult.getSuccess()) {
	            throw new BusinessException("获得商品信息失败！");
	        }
	        if (productResult.getResult() == null) {
	            throw new BusinessException("获得商品信息为空！");
	        }
	        Product product = productResult.getResult();
	        if (!isNull(product.getDescription())) {
	            product.setDescription(product.getDescription().replace(
	                "${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}", DomainUrlUtil.SLN_IMAGE_RESOURCES));
	        }
	        
	        map.put("product", product);
	        
	     // 取是否预览标志，1表示是预览
	        String preview = request.getParameter("preview");
	        if (!"1".equals(preview)) {
	            // 如果不是预览，则根据属性修改商品的状态
	            // 上架时间在现在之后
	            if (product.getUpTime().after(new Date())) {
	                product.setState(Product.STATE_7);
	            }
	            // 店铺被冻结
	            if (product.getSellerState().intValue() != Product.SELLER_STATE_1) {
	                product.setState(Product.STATE_7);
	            }
	        } else {
	            product.setState(Product.STATE_6);
	        }
	        
	      //获得产品对应的大图
	        List<String> productLeadPicList = frontProductPictureUtil.getByProductIds(productId);
	        map.put("productLeadPicList", productLeadPicList);

	        //获得用户评价数及占比
	        ServiceResult<FrontMemberProductBehaveStatisticsVO> serviceResult = new ServiceResult<FrontMemberProductBehaveStatisticsVO>();
	        serviceResult = memberService.getBehaveStatisticsByProductId(productId, member);
	        map.put("statisticsVO", serviceResult.getResult());

	        //获得货品信息,默认取第一个 包含某规格商品的库存及价格信息 
	        ServiceResult<List<ProductGoods>> goodsServiceResult = productGoodsService
	            .getGoodSByProductId(productId);
	        List<ProductGoods> goods = goodsServiceResult.getResult();

	        if (goods == null || goods.size() == 0) {
	            return new HttpJsonResult<>("货品信息为空。");
	        }
	        // 组装商品规格信息
	        List<ProductNorm> normList = new ArrayList<ProductNorm>();
	        Map<String, ProductNorm> normMap = new HashMap<String, ProductNorm>();
	        Map<String, ProductNormAttr> attrMap = new HashMap<String, ProductNormAttr>();
	        // 记录有效的规格组合
	        List<String> effectAttr = new ArrayList<>();
	        boolean def = false;
	        boolean defWithId = false;
	        for (ProductGoods good : goods) {
	            String normName = good.getNormName(); // 如：颜色,红色;内存,4G
	            String normAttrId = good.getNormAttrId(); // 如：1,17

	            if (StringUtil.isEmpty(normName, true)) {
	                // 规则属性为空则表示该商品没有启用规格（只有一个货品）
	                // 设置默认货品，只有一个货品时设定该货品
	                map.put("goods", good);
	                continue;
	            }

	            if (good.getState() == null || good.getState().intValue() == ProductGoods.DISABLE) {
	                // 货品没有启用则规格不参与组装
	                continue;
	            }

	            // 获取默认显示的货品，不管id为goodId的货品存在与否、启用与否，必须设置一个默认的货品
	            if (!defWithId) {
	                if (good.getId().equals(goodId)) {
	                    defWithId = true;
	                    def = true;
	                    // 如果有指定ID的货品，设定指定ID的货品为默认货品
	                    map.put("goods", good);
	                } else if (!def) {
	                    // 设置默认货品，有多个货品时，第一个有效货品为默认货品
	                    map.put("goods", good);
	                    def = true;
	                }
	            }

	            effectAttr.add(normAttrId);

	            String[] normNameSplit = normName.split(";");
	            String[] normAttrIdSplit = normAttrId.split(",");

	            if (normNameSplit.length > 0) {
	                // 循环
	                for (int i = 0; i < normNameSplit.length; i++) {
	                    String name = normNameSplit[i];

	                    // 得到类似：颜色,红色的值，颜色为规格名称，红色为规格的值
	                    String[] cellName = name.split(",");

	                    if (normMap.get(cellName[0]) == null) {
	                        // 如果规格map中没有当前规格，则添加规格和对应的规格值
	                        ProductNorm norm = new ProductNorm();
	                        norm.setName(cellName[0]);
	                        // 保存规则名称
	                        normList.add(norm);
	                        normMap.put(cellName[0], norm);
	                        // 保存规则值
	                        List<ProductNormAttr> attrList = new ArrayList<ProductNormAttr>();
	                        ProductNormAttr attr = new ProductNormAttr();
	                        attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
	                        attr.setName(cellName[1]);
	                        attrList.add(attr);
	                        norm.setAttrList(attrList);

	                        // 记录到map中防止重复添加
	                        attrMap.put(cellName[1], attr);
	                    } else {
	                        // 如果规格map中有当前规格，则不添加规格，对应的规格值再判断是否已经存在决定是否添加
	                        ProductNorm norm = normMap.get(cellName[0]);

	                        // 判断是否已有规则值，如果没有则添加，有则不再添加
	                        if (attrMap.get(cellName[1]) == null) {
	                            // 规则值xianchongfene-=oeui
	                            List<ProductNormAttr> attrList = norm.getAttrList();
	                            ProductNormAttr attr = new ProductNormAttr();
	                            attr.setId(ConvertUtil.toInt(normAttrIdSplit[i], 0));
	                            attr.setName(cellName[1]);
	                            attrList.add(attr);
	                            norm.setAttrList(attrList);
	                            // 记录到map中防止重复添加
	                            attrMap.put(cellName[1], attr);
	                        }
	                    }
	                }
	            }
	        }
	     // 有效货品包含的规格
	        map.put("norms", normList);
	        // 规格数量
	        map.put("normsNum", normList.size());
	        // 有效的规格组合
	        map.put("effectAttr", new Gson().toJson(effectAttr));
	        
	     // 获得商家信息、商家详细信息
	        ServiceResult<Seller> sellerServiceResult = sellerService
	            .getSellerById(productResult.getResult().getSellerId());
	        if (sellerServiceResult.getSuccess() && sellerServiceResult.getResult() != null) {
	            // 商家基本信息
	            Seller seller = sellerServiceResult.getResult();
	            map.put("seller", seller);
	        }

	        // 购物车数量
	        if (member != null && member.getId() != null) {
	            ServiceResult<Integer> cartResult = cartService.getCartNumberByMId(member.getId(),Cart.SOURCE_1);
	            map.put("cartNumber", cartResult.getResult());
	        }
	     // 获取类型参数：为1时表示打开限时抢购页面
	        String type = request.getParameter("type");
	        if (type != null && type.equals("1")) {
	        	 // 限时抢购时获取活动信息
	            // 根据商品ID、渠道取得当天该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
	            ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
	                .getTodayEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_3);

	            // 抢购活动信息，不为空则显示，为空则提示当前时间无抢购活动
	            ActFlashSale actFlashSale = flashSaleResult.getResult();
	            if (actFlashSale != null) {
	            	map.put("actFlashSale", actFlashSale);

	                // 记录正在进行的阶段，用于计算结束时间
	                ActFlashSaleStage underWayStage = null;
	                // 记录即将开始的阶段（商品有多个阶段即将开始时显示最近的）
	                ActFlashSaleStage comingStage = null;
	                // 记录阶段类型
	                int stageType = 0;

	                // 如果活动对象不为空，则返回结果中必定有阶段和活动商品
	                int hour = TimeUtil.getHour();
	                int minute = TimeUtil.getMinute();
	                int second = TimeUtil.getSecond();
	                for (ActFlashSaleStage stage : actFlashSale.getStageList()) {
	                    int startTime = stage.getStartTime();
	                    int endTime = stage.getEndTime();
	                    if (hour >= startTime && hour < endTime) {
	                        // 如果有阶段正在进行  优先显示正在进行的
	                    	map.put("actFlashSaleStage", stage);
	                        map.put("actFlashSaleProduct", stage.getProductList().get(0));
	                        map.put("stageType", ProductActVO.STAGE_TYPE_2);
	                        underWayStage = stage;
	                        stageType = ProductActVO.STAGE_TYPE_2;
	                        break;
	                    } else if (hour < startTime) {
	                        // 如果有阶段即将开始，显示最近的，如果有正在进行的则会覆盖
	                        if (comingStage == null
	                            || stage.getStartTime() < comingStage.getStartTime()) {
	                            // 如果循环当前阶段比上次记录的阶段要早，则显示当前阶段
	                        	map.put("actFlashSaleStage", stage);
	                            map.put("actFlashSaleProduct", stage.getProductList().get(0));
	                            map.put("stageType", ProductActVO.STAGE_TYPE_3);
	                            comingStage = stage;
	                            stageType = ProductActVO.STAGE_TYPE_3;
	                        }
	                    } else if (hour >= endTime) {
	                        // 如果有阶段已经结束，则判断是否有正在进行或即将开始，
	                        // 如果都没有，则保存结束的阶段，后续循环如果有即将开始或者正在进行的将会覆盖
	                        if (stageType == 0) {
	                        	map.put("actFlashSaleStage", stage);
	                            map.put("actFlashSaleProduct", stage.getProductList().get(0));
	                            map.put("stageType", ProductActVO.STAGE_TYPE_1);
	                            stageType = ProductActVO.STAGE_TYPE_1;
	                        }
	                    }
	                }

	                if (stageType == ProductActVO.STAGE_TYPE_2) {
	                    //计算结束倒计时
	                    int endTime = underWayStage.getEndTime();
	                    int hour1 = endTime - hour - 1;
	                    int minute1 = 60 - minute;
	                    int second1 = 60 - second;
	                    int countTime = hour1 * 3600 + minute1 * 60 + second1;
	                    map.put("countTime", countTime);
	                } else if (stageType == ProductActVO.STAGE_TYPE_3) {
	                    // 计算开始倒计时
	                    int startTime = comingStage.getStartTime();
	                    int hour1 = startTime - hour;
	                    int countTime = hour1 * 3600 - minute * 60 - second;
	                    map.put("countTime", countTime);
	                }
	            }else{
	            	// 如果获取限时抢购的结果为空，则加载商品的单品立减、订单满减、优惠券信息
		            ServiceResult<ActSingle> singleResult = actSingleService.getEffectiveActSingle(product.getSellerId(),
		                ConstantsEJS.CHANNEL_3, productId);
		            // 满减
		            ServiceResult<ActFull> fullResult = actFullService.getEffectiveActFull(product.getSellerId(),
		                ConstantsEJS.CHANNEL_3);
		            // 优惠券
		            ServiceResult<List<Coupon>> couponResult = couponService.getEffectiveCoupon(product.getSellerId(),
		                ConstantsEJS.CHANNEL_3);
		            
		            map.put("ActSingle",singleResult.getResult());
		            map.put("ActFull",fullResult.getResult());
		            map.put("Coupon",couponResult.getResult());
	            }
	        }
	        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String,Object>>();
	        jsonResult.setData(map);;
	    	return jsonResult;
	    }
	    
	    /**
	     * 提交订单 计算总金额 按店铺拆分订单
	     * @param request
	     * @param response
	     * @param map
	     * @return
	     * @throws IOException 
	     */
	    @RequestMapping(value = "appordercommit", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> appOrderSubmit(HttpServletRequest request,
	                                                                           HttpServletResponse response,
	                                                                           OrderCommitVO orderCommitVO) {
	        // 获取优惠券使用信息
	    	try{
	 	        Map<Integer, OrderCouponVO> sellerCouponMap = new HashMap<Integer, OrderCouponVO>();
	 	        String useCouponSellerIds = request.getParameter("useCouponSellerIds");
	 	        if (!StringUtil.isEmpty(useCouponSellerIds, true)) {
	 	            String[] split = useCouponSellerIds.split(",");
	 	            for (String sellerIdStr : split) {
	 	                if (!StringUtil.isEmpty(sellerIdStr, true)) {
	 	                    Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
	 	                    String couponTypeStr = request.getParameter("couponType" + sellerId);
	 	                    Integer couponType = ConvertUtil.toInt(couponTypeStr, 0);
	 	                    String couponSn = request.getParameter("couponSn" + sellerId);
	 	                    String couponPassword = request.getParameter("couponPassword" + sellerId);
	 	                    OrderCouponVO orderCouponVO = new OrderCouponVO();
	 	                    orderCouponVO.setSellerId(sellerId);
	 	                    orderCouponVO.setCouponType(couponType);
	 	                    orderCouponVO.setCouponSn(couponSn);
	 	                    orderCouponVO.setCouponPassword(couponPassword);
	 	                    sellerCouponMap.put(sellerId, orderCouponVO);
	 	                }
	 	            }
	 	        }
	 	        orderCommitVO.setSellerCouponMap(sellerCouponMap);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		log.error("app订单提交异常："+e.getMessage());
	    	}
	    	

	        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_1);
	    }

	    /**
	     * 提交订单方法
	     * @param request
	     * @param orderCommitVO
	     * @param orderType
	     * @return
	     */
	    private HttpJsonResultForAjax<OrderSuccessVO> commonSubmit(HttpServletRequest request,
	                                                               OrderCommitVO orderCommitVO,
	                                                               int orderType) {
	    	HttpJsonResultForAjax<OrderSuccessVO> jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>();
	    	try{
	        Member member = WebFrontSession.getLoginedUser(request);
	        if(member == null){
	        	jsonResult.setMessage("登录超时，请重新登录");
	        	return jsonResult;
	        }
	        orderCommitVO.setMemberId(member.getId());

	        if (orderCommitVO.getInvoiceStatus() == null) {
	            // 默认不开发票
	            orderCommitVO.setInvoiceStatus(Orders.INVOICE_STATUS_0);
	        }
	        // 设定IP地址
	        orderCommitVO.setIp(WebUtil.getIpAddr(request));
	        // 设定来源
	        orderCommitVO.setSource(ConstantsEJS.SOURCE_2_H5);
	        orderCommitVO.setRemark("");

	        // 提交订单
	        ServiceResult<OrderSuccessVO> serviceResult = null;
	        if (orderType == Orders.ORDER_TYPE_1) {
	            // 1、普通订单
	            serviceResult = ordersService.orderCommit(orderCommitVO);
	        } else if (orderType == Orders.ORDER_TYPE_2) {
	            // 2、限时抢购订单
	            serviceResult = ordersService.orderCommitForFlash(orderCommitVO);
	        } else if (orderType == Orders.ORDER_TYPE_3) {
	            // 3、团购订单
	            serviceResult = ordersService.orderCommitForGroup(orderCommitVO);
	        } else if (orderType == Orders.ORDER_TYPE_4) {
	            // 4、竞价定金订单
	            serviceResult = ordersService.orderCommitForBidding(orderCommitVO);
	        } else if (orderType == Orders.ORDER_TYPE_6) {
	            // 6、积分换购订单
	            serviceResult = ordersService.orderCommitForIntegral(orderCommitVO);
	        }

	     
	        if (!serviceResult.getSuccess()) {
	            jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>(null,
	                CsrfTokenManager.getTokenForSession(CsrfTokenManager.getMemkeyFromRequest(request),
	                    request.getSession()));
	            jsonResult.setMessage(serviceResult.getMessage());
	            return jsonResult;
	        }

	        //订单提交后返回结果
	        OrderSuccessVO orderSuccessVO = serviceResult.getResult();
	        /*if (orderSuccessVO.getIsPaid() && orderType != Orders.ORDER_TYPE_6) {
	            // 如果已经付过款，则调用下单送积分方法，积分换购订单不再送积分
	            for (Orders order : orderSuccessVO.getOrdersList()) {
	                memberService.memberOrderSendValue(member.getId(), member.getName(), order.getId());
	            }
	        }*/
	        //支付随机码 避免重复提交
	        String order_session = CommUtil.randomString(32);
	        // 存入session，支付时取出后与参数传入的对比，判断是否二次提交
	        MySessionContext.getSession(request.getParameter("sessionId")).setAttribute("order_session", order_session);
	        MySessionContext.getSession(request.getParameter("sessionId")).setAttribute("order_success_vo", orderSuccessVO);
	        orderSuccessVO.setPaySessionstr(order_session);

	        jsonResult.setData(orderSuccessVO);
	    	}catch(Exception e){
	    		e.printStackTrace();
	    		log.error("app订单提交异常："+e.getMessage());
	    	}
	        return jsonResult;
	    }
	    
	    
	    /**
	     * 添加物品到购物车
	     * @param request
	     * @param response
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "/appaddtocart", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> addCart(HttpServletRequest request) {

	        //Member member = WebFrontSession.getLoginedUser(request);
	    	Integer memberId = Integer.valueOf(request.getParameter("memberId"));
	        Integer productId = Integer.valueOf(request.getParameter("productId"));
	        Integer number = Integer.valueOf(request.getParameter("number"));
	        Integer productGoodId = Integer.valueOf(request.getParameter("productGoodId"));
	        
	        ServiceResult<Product> productResult = productFrontService.getProductById(productId);
	        if (!productResult.getSuccess() || productResult.getResult() == null) {
	            return new HttpJsonResult<Boolean>("添加购物车失败，获取商品信息失败！");
	        }
	        Product product = productResult.getResult();

	        ProductGoods goods = null;
	        if (productGoodId == null || productGoodId == 0) {
	            // 如果是货品是空，则可能是从列表页等不能选择货品的页面请求过来，随机取一个货品放入购物车
	            ServiceResult<List<ProductGoods>> goodsResult = productGoodsService
	                .getGoodSByProductId(productId);
	            if (goodsResult.getSuccess() && goodsResult.getResult() != null
	                && goodsResult.getResult().size() > 0) {
	                goods = goodsResult.getResult().get(0);
	            }
	        } else {
	            ServiceResult<ProductGoods> goodResult = productGoodsService
	                .getProductGoodsById(productGoodId);
	            goods = goodResult.getResult();
	        }
	        if (goods == null) {
	            return new HttpJsonResult<Boolean>("添加购物车失败，获取货品信息失败！");
	        }

	        Cart cart = new Cart();
	        cart.setMemberId(memberId);
	        cart.setProductGoodsId(goods.getId());
	        cart.setSpecId(goods.getNormAttrId());
	        cart.setSpecInfo(goods.getNormName());
	        cart.setCount(number);
	        cart.setProductId(product.getId());
	        cart.setSellerId(product.getSellerId());
	        cart.setSource(Cart.SOURCE_1);

	        ServiceResult<Integer> serviceResult = cartService.addToCart(cart);
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
	            }
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 列表页访问主函数，为了SEO，URL为数字的拼装
	     * 完整URL规则www.sln.com/0-cate-分页-排序-自营非自营－有货无货-品牌ID-0-0-0-
	     *                 filter1-filter2....html<br/>
	     *                 排序sort 0:默认排序；1销量；2评论；3价格从低到高；4、价格从高到低<br/>
	     *                 自营非自营：0所有商品；1自营商品<br/>
	     *                 有货无货：0所有商品；1有货商品
	     * @param visitPath
	     * @param request
	     * @param response
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "/app-{visitPath}", method = RequestMethod.POST)
	    public @ResponseBody HttpJsonResult<Map<String, Object>> productList(@PathVariable String visitPath, HttpServletRequest request,
	                              HttpServletResponse response) {
	        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String,Object>>();
	        Map<String, Object> stack = new HashMap<String, Object>();
	    	String[] arrVisitPath = visitPath.split("-");
	        int arrVisitPathLength = arrVisitPath.length;
	        if (arrVisitPathLength < 9) { //长度小于9url错误
	            return new HttpJsonResult<Map<String,Object>>("数据参数错误");
	        }
	        int cateId = ConvertUtil.toInt(arrVisitPath[0], 0); //分类
	        stack.put("cateId", cateId);
	        //存放参数
	        Map<String, Object> mapCondition = new HashMap<String, Object>();

	        int page = ConvertUtil.toInt(arrVisitPath[1], 1);//分页
	        mapCondition.put("page", page);
	        stack.put("pageNumber", page);

	        int sort = ConvertUtil.toInt(arrVisitPath[2], 0);//排序
	        mapCondition.put("sort", sort);

	        int doSelf = ConvertUtil.toInt(arrVisitPath[3], 0);//自营非自营
	        mapCondition.put("doSelf", doSelf);
	        stack.put("doSelf", doSelf);

	        int store = ConvertUtil.toInt(arrVisitPath[4], 0);//有货无货
	        mapCondition.put("store", store);
	        stack.put("store", store);

	        int brandId = ConvertUtil.toInt(arrVisitPath[5], 0);//品牌
	        mapCondition.put("brandId", brandId);
	        stack.put("brandId", brandId);

	        List<String> listFilters = new ArrayList<String>();
	        for (int i = 9; i < arrVisitPath.length; i++) {
	            listFilters.add(arrVisitPath[i]);
	        }

	        StringBuilder sbUrlPath = new StringBuilder("");
	        int listFiltersCount = listFilters.size();
	        for (int i = 0; i < listFiltersCount; i++) {
	            sbUrlPath.append(listFilters.get(i));
	            if (listFiltersCount != (i + 1)) {
	                sbUrlPath.append("-");
	            }
	        }
	        mapCondition.put("filterAll", sbUrlPath.toString());
	        StringBuilder sbUrlPathAll = new StringBuilder(urlProductList(cateId, page, sort, doSelf,
	            store, brandId));
	        if (sbUrlPath.toString() != null && !"".equals(sbUrlPath.toString())) {
	            sbUrlPathAll.append("-");
	            sbUrlPathAll.append(sbUrlPath.toString());
	        }

	        stack.put("urlPath", sbUrlPathAll.toString());

	        productFrontService.getProducts(cateId, mapCondition, stack);

	        if (brandId != 0) {
	            List<ProductBrand> ProductBrands = (List<ProductBrand>) stack.get("productBrands");
	            for (ProductBrand productBrand : ProductBrands) {
	                if (productBrand.getId().intValue() == brandId) {
	                    stack.put("productBrandName", productBrand.getName());
	                    break;
	                }
	            }
	        }

	        List<ProductTypeAttr> productTypeAttrs = (List<ProductTypeAttr>) mapCondition
	            .get("productTypeAttrsAll");
	        installProductAttr(stack, productTypeAttrs, listFilters);

	        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
	        
	        jsonResult.setData(stack);
	        return jsonResult;
	    }
	    
	    /**
	     * APP获得货品详情
	     */
	    @RequestMapping(value = "/appgetGoodsInfo", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<ProductGoods> getGoodsInfo(HttpServletRequest request,
	                                                                   HttpServletResponse response,
	                                                                   @RequestParam(value = "productId", required = true) Integer productId,
	                                                                   String normAttrId) {

	        Map<String, String> queryMap = new HashMap<String, String>();
	        //获得货品信息 包含某规格商品的库存及价格信息
	        queryMap.put("q_productId", productId + "");
	        queryMap.put("q_normAttrId", normAttrId);
	        ServiceResult<ProductGoods> serviceResult = productGoodsService
	            .getProductGoodsByCondition(queryMap);

	        HttpJsonResult<ProductGoods> jsonResult = new HttpJsonResult<ProductGoods>();
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                jsonResult = new HttpJsonResult<ProductGoods>(serviceResult.getMessage());
	            }
	        }
	        ProductGoods productGoods =  serviceResult.getResult();
	        if(productGoods.getImages() != null){
	        	productGoods.setProductLeadPicList(productGoods.getImages().split(","));
	        }
	        jsonResult.setData(productGoods);
	        return jsonResult;
	    }
	    
	    /**
	     * 获取热搜数据
	     * @param request
	     * @param keyword
	     * @param stack
	     * @return
	     */
	    @RequestMapping(value = "/appsearchIndex", method = RequestMethod.POST)
	    public @ResponseBody HttpJsonResult<Map<String, Object>> searchIndex(HttpServletRequest request) {
	        List<String> keywords = getKeywords();
	        HttpJsonResult<Map<String, Object>> jsonResult = new HttpJsonResult<Map<String,Object>>();
	        Map<String, Object> stack = new HashMap<String, Object>();
	        stack.put("keywords", keywords);
	        stack.put("keywordNumber", 0);
	        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
	        jsonResult.setData(stack);
	        return jsonResult;
	    }
	    
	    /**
	     * 用户在线领取优惠券
	     * @param request
	     * @param response
	     * @param couponId
	     * @return
	     */
	    @RequestMapping(value = "/appreveivecoupon", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> reveiveCoupon(HttpServletRequest request,
	                                                               HttpServletResponse response,
	                                                               @RequestParam(value = "couponId", required = true) Integer couponId) {
	        Member sessionMember = WebFrontSession.getLoginedUser(request);
	        
	        if(sessionMember ==null){
	        	return new HttpJsonResult<Boolean>("未登录，请登陆后在领取");
	        }
	        
	        ServiceResult<Boolean> receiveCoupon = couponService.receiveCoupon(couponId,
	            sessionMember.getId());
	        if (!receiveCoupon.getSuccess()) {
	            return new HttpJsonResult<Boolean>(receiveCoupon.getMessage());
	        }
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        return jsonResult;
	    }
	    
	    /**
	     * 拼装列表页访问的URL
	     * @param cateId 类别
	     * @param number 分页
	     * @param sort 排序
	     * @param doSelf 自营非自营
	     * @param store 有货无货
	     * @param brandId 品牌
	     * @return
	     */
	    private String urlProductList(int cateId, int number, int sort, int doSelf, int store,
	                                  int brandId) {
	        StringBuilder sb = new StringBuilder();
	        sb.append("0-");
	        sb.append(cateId);

	        sb.append("-");
	        sb.append(number);

	        sb.append("-");
	        sb.append(sort);

	        sb.append("-");
	        sb.append(doSelf);

	        sb.append("-");
	        sb.append(store);

	        sb.append("-");
	        sb.append(brandId);

	        sb.append("-0-0-0");
	        return sb.toString();
	    }
	    
	    /**
	     * 拼装列表页查询条件的VO对象
	     * @param stack
	     * @param productTypeAttrs
	     */
	    private void installProductAttr(Map<String, Object> stack,
	                                    List<ProductTypeAttr> productTypeAttrs, List<String> listFilters) {

	        List<ProductTypeAttrVO> productTypeAttrVOs = new ArrayList<ProductTypeAttrVO>();

	        for (ProductTypeAttr productTypeAttr : productTypeAttrs) {
	            ProductTypeAttrVO productTypeAttrVO = new ProductTypeAttrVO();
	            productTypeAttrVO.setId(productTypeAttr.getId());
	            productTypeAttrVO.setName(productTypeAttr.getName());
	            productTypeAttrVO.setValue(productTypeAttr.getValue());

	            if (listFilters != null && listFilters.size() > 0) {
	                for (int i = 0; i < listFilters.size(); i++) {
	                    String whereAll_ = listFilters.get(i);
	                    String[] whereAll_s = whereAll_.split("_");
	                    if (whereAll_s.length == 2) {
	                        int whereAll_0 = ConvertUtil.toInt(whereAll_s[0], 0);
	                        int whereAll_1 = ConvertUtil.toInt(whereAll_s[1], 0);
	                        if (productTypeAttr.getId().intValue() == whereAll_0) {
	                            productTypeAttrVO.setIsChoice(1);//1表示此分类依据被选中
	                            productTypeAttrVO.setIsChoiceIndex(whereAll_1); //选中的索引
	                            productTypeAttrVO.setChoiceAll(whereAll_); //应该去掉的条件
	                            break;
	                        }
	                    }
	                }
	            }

	            String value = productTypeAttr.getValue();
	            if (value != null && !"".equals(value)) {
	                List<String> values = new ArrayList<String>();
	                String[] str = value.split(",");
	                for (int i = 0; i < str.length; i++) {
	                    values.add(str[i]);
	                }
	                productTypeAttrVO.setValues(values);
	            }
	            productTypeAttrVOs.add(productTypeAttrVO);
	        }
	        stack.put("productTypeAttrVOsAll", productTypeAttrVOs);
	    }
	    
	    /**
	     * 获取设置的查询关键字
	     * @return
	     */
	    private List<String> getKeywords() {
	        ServiceResult<SearchSetting> serviceResult = searchSettingService
	            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }
	        SearchSetting searchSetting = serviceResult.getResult();
	        String keyword = searchSetting.getKeyword();

	        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));

	        List<String> keywords = new ArrayList<String>();
	        if (!"".equals(keyword)) {
	            String[] strings = keyword.split(",");
	            for (String string : strings) {
	                string = StringUtil.trim(StringUtil.nullSafeString(string));
	                if (!"".equals(string)) {
	                    keywords.add(string);
	                }
	            }
	        }
	        return keywords;
	    }
	    
	    @RequestMapping(value = "memberindex", method = { RequestMethod.POST })
	    public @ResponseBody  HttpJsonResultForAjax<Map<String, String>> memberIndex(HttpServletRequest request, HttpServletResponse response,
	                              Map<String, Object> dataMap) {
	    	Map<String, String> map = new HashMap<String, String>();
	    	 HttpJsonResultForAjax<Map<String, String> > jsonResult = new HttpJsonResultForAjax<Map<String, String>>();
	        Integer memberId = Integer.valueOf(request.getParameter("memberId"));
	        // 获取member对象
	        ServiceResult<Member> result = memberService.getMemberById(memberId);
	        dataMap.put("member", result.getResult());

	        //待支付订单数
	        ServiceResult<Integer> numResult = ordersService.getOrderNumByMIdAndState(memberId,
	            Orders.ORDER_STATE_1);
	        map.put("toBepaidOrders", numResult.getResult().toString());
	        //待收货订单数
	        numResult = ordersService.getOrderNumByMIdAndState(memberId, Orders.ORDER_STATE_4);
	        map.put("toBeReceivedOrders", numResult.getResult().toString());
	        jsonResult.setData(map);
	        return jsonResult;
	    }
	    
	    /**
	     * 收藏店铺
	     * @return
	     */
	    
	    @RequestMapping(value = "appcollectionShop", method = { RequestMethod.POST })
	    public @ResponseBody  HttpJsonResult<Integer> collectionShop(HttpServletRequest request, HttpServletResponse response) {
	    	HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
	    	
	    	int sellerId = Integer.valueOf(request.getParameter("sellerId"));
	    	
	    	Member member = WebFrontSession.getLoginedUser(request);
	    	if(member == null){
	    		//如果没有登录则返回2
	    		jsonResult.setData(2);
	    		return jsonResult;
	    	}

	        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
	            .collectionSeller(sellerId, member.getId());

	        if (!serviceResult.getSuccess()) {
	                jsonResult = new HttpJsonResult<Integer>(serviceResult.getMessage());
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 取消关注店铺
	     * @return
	     */
	    @RequestMapping(value = "/appcancelCollectionShop", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Integer> cancelCollectionShop(HttpServletRequest request,
	                                                                      HttpServletResponse response) {
	    	HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
	    	int sellerId = Integer.valueOf(request.getParameter("sellerId"));
	        Member member = WebFrontSession.getLoginedUser(request);
	        
	        if(member ==null){
	        	//如果没有登录则返回2
	    		jsonResult.setData(2);
	    		return jsonResult;
	        }
	        
	        ServiceResult<Boolean> serviceResult = memberCollectionSellerService
	            .cancelCollectionSeller(sellerId, member.getId());

	        if (!serviceResult.getSuccess()) {
	                jsonResult = new HttpJsonResult<Integer>(serviceResult.getMessage());
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 更新单条购物车购买数量
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "/appupdateCartById", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> updateCartById(HttpServletRequest request,
	                                                                HttpServletResponse response) {

	    	Member member = WebFrontSession.getLoginedUser(request);
	    	if(member == null){
	    		return new HttpJsonResult<Boolean>("请先登录！");
	    	}
	        String cartIdStr = request.getParameter("id");
	        Integer cartId = ConvertUtil.toInt(cartIdStr, 0);
	        String countStr = request.getParameter("count");
	        Integer count = ConvertUtil.toInt(countStr, 0);

	        ServiceResult<Boolean> serviceResult = cartService.updateCartNumber(cartId, count);
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
	            }
	        }
	        return jsonResult;
	    }

	    
	    /**
	     * 购物车页面ajax更新购物车列表
	     * @param request
	     * @param response
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "/appgetcartinfo", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Map<String, Object>> getCartInfo(HttpServletRequest request, HttpServletResponse response
	                              ) {
	        Member member = WebFrontSession.getLoginedUser(request);
	        HttpJsonResult<Map<String, Object>> httpJsonResult = new HttpJsonResult<Map<String,Object>>();
	        Map<String, Object> dataMap = new HashMap<String, Object>();
	        //取购物车信息  产品价格 按照商家来区分
	        //查询购物车
	        ServiceResult<CartInfoVO> serviceResult = cartService.getCartInfoByMId(member.getId(), null,
	            ConstantsEJS.SOURCE_2_H5, 1,Cart.SOURCE_1);
	        dataMap.put("cartInfoVO", serviceResult.getResult());
	        httpJsonResult.setData(dataMap);
	        return httpJsonResult;
	    }
	    
	    /**
	     * 单条购物车数据选中或不选中
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "/appcartchecked", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> cartChecked(HttpServletRequest request,
	                                                             HttpServletResponse response,
	                                                             @RequestParam(value = "id", required = true) Integer id,
	                                                             @RequestParam(value = "checked", required = true) Integer checked) {
	        Member member = WebFrontSession.getLoginedUser(request);
	        ServiceResult<Boolean> serviceResult = cartService.updateChecked(member.getId(), id,
	            checked);

	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
	            }
	        }
	        return jsonResult;
	    }
	    
	    public static SolrClient getSolrClient() {
	        String solrUrl = DomainUrlUtil.getSLN_SOLR_URL();
	        String solrServer = DomainUrlUtil.getSLN_SOLR_SERVER();
	        return new HttpSolrClient(solrUrl + "/" + solrServer);
	    }
	    
	    /**
	     * 关注商品
	     * @param request
	     * @param response
	     * @param map
	     * @return
	     * @throws IOException 
	     */
	    @RequestMapping(value = "/appdocollectproduct", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Integer> collectionProduct(HttpServletRequest request,
	                                                                                  HttpServletResponse response,
	                                                                                  @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
	        Member member = WebFrontSession.getLoginedUser(request);
	        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
	        if(member ==null){
	        	//如果没有登录则返回1
	    		jsonResult.setData(2);
	    		return jsonResult;
	        }
	        
	        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();
	        serviceResult = memberCollectionProductService.saveMemberCollectionProduct(productId,
	            member.getId());

	        
	        if (!serviceResult.getSuccess()) {
	            	jsonResult.setData(0);
	            	jsonResult.setMessage(serviceResult.getMessage());
		    		return jsonResult;
	        }else{
	        	jsonResult.setData(1);
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 取消收藏商品
	     * @param request
	     * @param response
	     * @param map
	     * @return
	     * @throws IOException 
	     */
	    @RequestMapping(value = "/appcancelcollectproduct", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Integer> cancelCollectionProduct(HttpServletRequest request,
	                                                                                         HttpServletResponse response,
	                                                                                         @RequestParam(value = "productId", required = true) Integer productId) throws Exception {
	        Member member = WebFrontSession.getLoginedUser(request);
	        
	        HttpJsonResult<Integer> jsonResult = new HttpJsonResult<Integer>();
	        if(member ==null){
	        	//如果没有登录则返回1
	    		jsonResult.setData(2);
	    		return jsonResult;
	        }

	        ServiceResult<MemberCollectionProduct> serviceResult = new ServiceResult<MemberCollectionProduct>();

	        serviceResult = memberCollectionProductService.cancelCollectionProduct(productId,
	            member.getId());

	        if (!serviceResult.getSuccess()) {
            	jsonResult.setData(0);
            	jsonResult.setMessage(serviceResult.getMessage());
	        }else{
	        	jsonResult.setData(1);
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
	     * @param request
	     * @param response
	     * @param map
	     * @return
	     * @throws IOException 
	     */
	    @RequestMapping(value = "appOrderSubmit", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Map<String, Object>> toOrderSubmit(HttpServletRequest request,
	                                HttpServletResponse response) {
	        Member member = WebFrontSession.getLoginedUser(request);
	        
	        HttpJsonResult<Map<String, Object>> httpJsonResult = new HttpJsonResult<Map<String,Object>>();
	        Map<String, Object> map = new HashMap<String, Object>();
	        MemberAddress memberAddress = null;

	        Integer addressId = ConvertUtil.toInt(request.getParameter("addressId"), 0);
	        if (addressId > 0) {
	            ServiceResult<MemberAddress> memberAddressResult = memberAddressService
	                .getMemberAddressById(addressId);
	            memberAddress = memberAddressResult.getResult();
	        }

	        if (memberAddress == null) {
	            // 收货地址信息
	            ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
	                .getMemberAddressByMId(member.getId());
	            // 获取默认收货地址，如果没有取第一个
	            if (serviceResult.getSuccess()) {
	                List<MemberAddress> addressList = serviceResult.getResult();
	                if (addressList != null && addressList.size() > 0) {
	                    memberAddress = addressList.get(0);
	                    for (MemberAddress address : addressList) {
	                        if (address.getState() == MemberAddress.STATE_1) {
	                            memberAddress = address;
	                            break;
	                        }
	                    }
	                }
	            }
	        }

	        map.put("address", memberAddress);
	        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
	        OrderCommitVO orderCommitVO = new OrderCommitVO();
	        orderCommitVO.setInvoiceType("");
	        orderCommitVO.setInvoiceTitle("");
	        orderCommitVO.setPaymentName(Orders.PAYMENT_NAME_ONLINE);
	        orderCommitVO.setPaymentCode(Orders.PAYMENT_CODE_ONLINE);
	        map.put("orderCommitVO", orderCommitVO);

	        // 取购物车信息  产品价格 按照商家来区分
	        // 查询购物车
	        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
	            memberAddress, ConstantsEJS.SOURCE_2_H5, 2,Cart.SOURCE_1);
	        map.put("cartInfoVO", cartServiceResult.getResult());
	        
	        //验证购物车是否为空
	        if(null == cartServiceResult.getResult()) {
	        	cartServiceResult.setSuccess(false);
	        	httpJsonResult.setMessage("请选择需要结算的商品");
	        	return httpJsonResult;
	        }
	        
	        // 获取发票信息
	        ServiceResult<List<Invoice>> invoiceResult = invoiceService.getInvoiceByMId(member.getId());
	        map.put("invoiceList", invoiceResult.getResult());

	        //取会员余额信息
	        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
	        if (memberResult.getResult() == null) {
	            httpJsonResult.setMessage("会员信息获取失败。");
	            return httpJsonResult;
	        }
	        map.put("member", memberResult.getResult());

	        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
	        if (configById.getResult() != null) {
	            Config config = configById.getResult();
	            if (config.getIntegralScale() > 0) {
	                map.put("config", config);
	            }
	        }
	        httpJsonResult.setData(map);
	        return httpJsonResult;
	    }
	    
	    /**
	     * 删除购物车数据
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "/appdeleteCartById", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> deleteCartById(HttpServletRequest request,
	                                                                HttpServletResponse response) {
	        
	    	
	    	int id = ConvertUtil.toInt(request.getParameter("id"), 0);
	        if (id == 0) {
	            return new HttpJsonResult<Boolean>("请选择商品后再点击删除！");
	        }
	        List<Integer> ids = new ArrayList<Integer>();
	        ids.add(id);
	        ServiceResult<Boolean> serviceResult = cartService.deleteCartByIds(ids);
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
	            }
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 获取用户当前可用的已绑定的优惠券
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "appgetsellercoupon", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<List<CouponUser>> getSellerCoupon(HttpServletRequest request,
	                                                                          HttpServletResponse response) {

	        Member member = WebFrontSession.getLoginedUser(request);
	        
	        if(member == null){
	        	return new HttpJsonResult<List<CouponUser>>("登录超时，请重新登录");
	        }
	        
	        Integer sellerId = ConvertUtil.toInt(request.getParameter("sellerId"), 0);

	        ServiceResult<List<CouponUser>> serviceResult = couponService
	            .getEffectiveByMemberIdAndSellerId(member.getId(), sellerId);

	        HttpJsonResult<List<CouponUser>> jsonResult = new HttpJsonResult<List<CouponUser>>();
	        if (!serviceResult.getSuccess()) {
	            jsonResult = new HttpJsonResult<List<CouponUser>>(serviceResult.getMessage());
	        }
	        jsonResult.setData(serviceResult.getResult());
	        return jsonResult;
	    }
	    
	    /**
	     * 检查优惠券的可用性
	     * @param request
	     * @param response
	     * @return
	     */
	    @RequestMapping(value = "appchecksellercoupon", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<CouponUser> checkSellerCoupon(HttpServletRequest request,
	                                                                      HttpServletResponse response) {

	        Member member = WebFrontSession.getLoginedUser(request);

	        String orderAmount = request.getParameter("orderAmount");
	        String couponTypeStr = request.getParameter("couponType");
	        Integer couponType = ConvertUtil.toInt(couponTypeStr, 0);
	        String couponSn = request.getParameter("couponSn");
	        String couponPassword = request.getParameter("couponPassword");
	        Integer sellerId = ConvertUtil.toInt(request.getParameter("sellerId"), 0);

	        ServiceResult<CouponUser> couponUserRlt = couponService
	            .getCouponUserOnlyByCouponSn(couponSn);
	        if (!couponUserRlt.getSuccess()) {
	            return new HttpJsonResult<CouponUser>(couponUserRlt.getMessage());
	        }
	        if (couponUserRlt.getResult() == null) {
	            return new HttpJsonResult<CouponUser>("优惠券不存在，请确认是否输入正确。");
	        }
	        CouponUser couponUser = couponUserRlt.getResult();

	        Integer memberId = member.getId();

	        if (!sellerId.equals(couponUser.getSellerId())) {
	            return new HttpJsonResult<CouponUser>(
	                "优惠券【" + couponUser.getCouponSn() + "】只能购买" + couponUser.getSellerName() + "的商品。");
	        }

	        if (couponType == OrderCouponVO.COUPON_TYPE_1) {
	            // 检查优惠券所属用户
	            if (!memberId.equals(couponUser.getMemberId())) {
	                return new HttpJsonResult<CouponUser>(
	                    "优惠券【" + couponUser.getCouponSn() + "】不是属于您的优惠券，不能使用。");
	            }
	        } else if (couponType == OrderCouponVO.COUPON_TYPE_2) {
	            // 校验密码
	            if (couponUser.getPassword() == null
	                || !couponUser.getPassword().equals(Md5.getMd5String(couponPassword))) {
	                return new HttpJsonResult<CouponUser>(
	                    "优惠券【" + couponUser.getCouponSn() + "】密码不对，请重新输入。");
	            }
	            // 检查优惠券所属用户
	            if (couponUser.getMemberId() > 0 && !couponUser.getMemberId().equals(memberId)) {
	                return new HttpJsonResult<CouponUser>(
	                    "优惠券【" + couponUser.getCouponSn() + "】不是属于您的优惠券，不能使用。");
	            }
	        }

	        // 优惠券可使用次数
	        if (couponUser.getCanUse() < 1) {
	            return new HttpJsonResult<CouponUser>(
	                "优惠券【" + couponUser.getCouponSn() + "】已使用过，不能再次使用。");
	        }

	        // 优惠券用户关联的优惠券信息校验
	        // 适用最低金额校验
	        if (couponUser.getMinAmount().compareTo(new BigDecimal(orderAmount)) > 0) {
	            return new HttpJsonResult<CouponUser>(
	                "优惠券【" + couponUser.getCouponSn() + "】最低适用订单金额不得小于" + couponUser.getMinAmount()
	                                                  + "元。");
	        }
	        // 优惠券使用时间校验
	        if (couponUser.getUseStartTime().after(new Date())) {
	            return new HttpJsonResult<CouponUser>(
	                "优惠券【" + couponUser.getCouponSn() + "】还没有到可使用时间。");
	        }
	        if (couponUser.getUseEndTime().before(new Date())) {
	            return new HttpJsonResult<CouponUser>("优惠券【" + couponUser.getCouponSn() + "】已过期。");
	        }

	        // 使用渠道校验
	        if (couponUser.getChannel().intValue() != ConstantsEJS.CHANNEL_1
	            && ConstantsEJS.CHANNEL_2 != couponUser.getChannel().intValue()) {
	            String channelStr = couponUser.getChannel().intValue() == ConstantsEJS.CHANNEL_2 ? "电脑端"
	                : "移动端";
	            return new HttpJsonResult<CouponUser>(
	                "优惠券【" + couponUser.getCouponSn() + "】只能在" + channelStr + "使用。");
	        }

	        HttpJsonResult<CouponUser> jsonResult = new HttpJsonResult<CouponUser>();
	        jsonResult.setData(couponUser);
	        return jsonResult;
	    }
	    
	    
	    /**
	     * 拼装查询条件
	     * @param searchKeyword
	     * @return
	     */
	    private String queryKeyWord(String searchKeyword) {
	        StringBuilder sb = new StringBuilder();
	        sb.append(SearchProductVO.CONTENT_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        sb.append(" OR ");
	        sb.append(SearchProductVO.NAME1_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        sb.append(" OR ");
	        sb.append(SearchProductVO.SELLER_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        sb.append(" OR ");
	        sb.append(SearchProductVO.BRAND_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        sb.append(" OR ");
	        sb.append(SearchProductVO.SELLER_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        sb.append(" OR ");
	        sb.append(SearchProductVO.CATE_);
	        sb.append(":");
	        sb.append(searchKeyword);
	        return sb.toString();
	    }
	    
	    private Product queryResult(QueryResponse response, SolrDocument doc, String id) {
	        Product product = new Product();
	        product.setId(new Integer(id));

	        Object object = response.getHighlighting().get(id).get(SearchProductVO.NAME1_);
	        if (object != null) {
	            product.setName1(object.toString().replace("[", "").replace("]", ""));
	        } else {
	            product.setName1(doc.getFieldValue(SearchProductVO.NAME1_).toString());
	        }
	        //                product.setName1(response.getHighlighting().get(id).get(SearchProductVO.NAME1_)
	        //                    .toString().replace("[", "").replace("]", ""));

	        product.setMasterImg(doc.getFieldValue(SearchProductVO.MASTERIMG_).toString());
	        product.setMallPcPrice(
	            new BigDecimal(doc.getFieldValue(SearchProductVO.MALLPCPRICE_).toString()));
	        product.setMalMobilePrice(
	            new BigDecimal(doc.getFieldValue(SearchProductVO.MALMOBILEPRICE_).toString()));
	        product.setProductStock(
	            new Integer(doc.getFieldValue(SearchProductVO.PRODUCTSTOCK_).toString()));
	        product.setCommentsNumber(
	            new Integer(doc.getFieldValue(SearchProductVO.COMMENTSNUMBER_).toString()));
	        product.setSellerId(new Integer(doc.getFieldValue(SearchProductVO.SELLERID_).toString()));
	        return product;
	    }
}
