package com.sln.web.controller.order;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.HttpJsonResultForAjax;
import com.sln.core.Md5;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.entity.bidding.ActBidding;
import com.sln.entity.cart.Cart;
import com.sln.entity.coupon.CouponUser;
import com.sln.entity.flash.ActFlashSale;
import com.sln.entity.group.ActGroup;
import com.sln.entity.integral.ActIntegral;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberAddress;
import com.sln.entity.member.MemberSpecialIntegral;
import com.sln.entity.operate.Config;
import com.sln.entity.order.Invoice;
import com.sln.entity.order.Orders;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.seller.Seller;
import com.sln.service.cart.ICartService;
import com.sln.service.member.IInvoiceService;
import com.sln.service.member.IMemberAddressService;
import com.sln.service.member.IMemberService;
import com.sln.service.member.IMemberSpecialIntegralService;
import com.sln.service.operate.IConfigService;
import com.sln.service.order.IOrdersProductService;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductFrontService;
import com.sln.service.product.IProductGoodsService;
import com.sln.service.product.IProductService;
import com.sln.service.promotion.IActBiddingService;
import com.sln.service.promotion.IActFlashSaleService;
import com.sln.service.promotion.IActGroupService;
import com.sln.service.promotion.IActIntegralService;
import com.sln.service.promotion.ICouponService;
import com.sln.service.seller.ISellerService;
import com.sln.service.seller.ISellerTransportService;
import com.sln.vo.cart.CartInfoVO;
import com.sln.vo.cart.CartListVO;
import com.sln.vo.member.FrontCheckPwdVO;
import com.sln.vo.order.OrderCommitVO;
import com.sln.vo.order.OrderCouponVO;
import com.sln.vo.order.OrderSuccessVO;
import com.sln.web.controller.BaseController;
import com.sln.web.csrf.CsrfTokenManager;
import com.sln.web.util.CommUtil;
import com.sln.web.util.WebFrontSession;

/**
 * 购物流程-订单<br>
 * 本controller中得请求都需要登录才能访问
 * 
 * @Filename: FrontOrdersController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
public class OrdersController extends BaseController {

    @Resource
    private IOrdersService          ordersService;
    @Resource
    private IOrdersProductService   ordersProductService;
    @Resource
    private ICartService            cartService;
    @Resource
    private IMemberAddressService   memberAddressService;
    @Resource
    private IInvoiceService         invoiceService;
    @Resource
    private IMemberService          memberService;
    @Resource
    private IConfigService          configservice;
    @Resource
    private ICouponService          couponService;
    @Resource
    private IActFlashSaleService    actFlashSaleService;
    @Resource
    private ISellerTransportService sellerTransportService;
    @Resource
    private ISellerService          sellerService;
    @Resource
    private IProductGoodsService    productGoodsService;
    @Resource
    private IActGroupService        actGroupService;
    @Resource
    private IActBiddingService      actBiddingService;
    @Resource
    private IActIntegralService     actIntegralService;
    @Resource
    private IProductFrontService    productFrontService;
    @Resource
    private IProductService         productService;
    @Resource
    private IMemberSpecialIntegralService memberSpecialIntegralService;

    /**
     * 跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @param type 2:为福利订单
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/info.html", method = { RequestMethod.GET })
    public String toOrderSubmit(HttpServletRequest request, ModelMap map,
                                HttpServletResponse response) {
        Member member = WebFrontSession.getLoginedUser(request);
        
        Integer cartSource = ConvertUtil.toInt(request.getParameter("cartSource"), Cart.SOURCE_1);
        // 收货地址信息
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddressByMId(member.getId());
        List<MemberAddress> addressList = null;
        MemberAddress defaultAddress = null;
        // 是否有默认地址
        String hasDefaultAdd = "no";
        // 获取默认收货地址，如果没有取第一个
        if (serviceResult.getSuccess()) {
            addressList = serviceResult.getResult();
            if (addressList != null && addressList.size() > 0) {
                defaultAddress = addressList.get(0);
                for (MemberAddress address : addressList) {
                    if (address.getState() == MemberAddress.STATE_1) {
                        defaultAddress = address;
                        hasDefaultAdd = "yes";
                        break;
                    }
                }
            }
        }
        map.put("hasDefaultAdd", hasDefaultAdd);
        map.put("addressList", addressList);
        map.put("defaultAddress", defaultAddress);
        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
        OrderCommitVO orderCommitVO = new OrderCommitVO();
        orderCommitVO.setInvoiceType("");
        orderCommitVO.setInvoiceTitle("");
        orderCommitVO.setPaymentName(Orders.PAYMENT_NAME_ONLINE);
        orderCommitVO.setPaymentCode(Orders.PAYMENT_CODE_ONLINE);
        
        if(null != cartSource && cartSource == Product.IS_WELFARE_2) {
    		map.put("type", cartSource);
    		orderCommitVO.setPaymentCode(OrderCommitVO.WELFARE);
	    }
        
        
        // 取购物车信息  产品价格 按照商家来区分
        // 查询购物车
        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
            defaultAddress, ConstantsEJS.SOURCE_1_PC, 2,cartSource);
        
        map.put("cartInfoVO", cartServiceResult.getResult());
        map.put("orderCommitVO", orderCommitVO);
        
        // 获取发票信息
        ServiceResult<List<Invoice>> invoiceResult = invoiceService.getInvoiceByMId(member.getId());
        map.put("invoiceList", invoiceResult.getResult());

        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            map.put("info", "会员信息获取失败。");
            return "front/commons/error";
        }
        map.put("member", memberResult.getResult());

        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                map.put("config", config);
            }
        }
        return "front/order/shoporder";
    }

    /**
     * 根据地址ID计算新的运费信息
     * @param request
     * @param response
     * @param map
     * @param addressId
     * @return
     */
    @RequestMapping(value = "order/calculateTransFee.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<CartInfoVO> calculateTransFee(HttpServletRequest request,
                                                                      HttpServletResponse response,
                                                                      ModelMap map,
                                                                      Integer addressId) {
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<MemberAddress> memberAddressRlt = memberAddressService
            .getMemberAddressById(addressId);
        if (!memberAddressRlt.getSuccess() || memberAddressRlt.getResult() == null) {
            return new HttpJsonResult<>("收获地址信息获取失败！");
        }
        // 查询购物车
        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
            memberAddressRlt.getResult(), ConstantsEJS.SOURCE_1_PC, 2,Cart.SOURCE_1);
        if (!cartServiceResult.getSuccess() || cartServiceResult.getResult() == null) {
            return new HttpJsonResult<>("计算运费信息失败！");
        }
        HttpJsonResult<CartInfoVO> jsonResult = new HttpJsonResult<CartInfoVO>();
        jsonResult.setData(cartServiceResult.getResult());
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
    @RequestMapping(value = "order/ordercommit.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmit(HttpServletRequest request,
                                                                           HttpServletResponse response,
                                                                           OrderCommitVO orderCommitVO,
                                                                           ModelMap map) {
        // 获取优惠券使用信息
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
        
        if(orderCommitVO.getPaymentCode().equals(OrderCommitVO.WELFARE)) {
        	return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_9);
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
        Member member = WebFrontSession.getLoginedUser(request);
        orderCommitVO.setMemberId(member.getId());

        if (orderCommitVO.getInvoiceStatus() == null) {
            // 默认不开发票
            orderCommitVO.setInvoiceStatus(Orders.INVOICE_STATUS_0);
        }
        // 设定IP地址
        orderCommitVO.setIp(WebUtil.getIpAddr(request));
        // 设定来源
        orderCommitVO.setSource(ConstantsEJS.SOURCE_1_PC);
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
        } else if(orderType == Orders.ORDER_TYPE_9) {
        	// 9、福利积分订单
        	serviceResult = ordersService.orderCommitForWelfare(orderCommitVO);
        }

        HttpJsonResultForAjax<OrderSuccessVO> jsonResult = new HttpJsonResultForAjax<OrderSuccessVO>();
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
        request.getSession(false).setAttribute("order_session", order_session);
        request.getSession(false).setAttribute("order_success_vo", orderSuccessVO);
        orderSuccessVO.setPaySessionstr(order_session);

        jsonResult.setData(orderSuccessVO);

        return jsonResult;
    }

    /**
     * 跳转到提交订单成功页面 （货到付款）
     * @param request
     * @param response
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "order/success.html", method = { RequestMethod.GET })
    public String toOrderSuccess(HttpServletRequest request, HttpServletResponse response,
                                 Map<String, Object> dataMap,
                                 @RequestParam(value = "paySn", required = true) String paySn) {
        Member member = WebFrontSession.getLoginedUser(request);
        //根据支付订单号查询订单信息
        ServiceResult<OrderSuccessVO> serviceResult = ordersService.getOrdersByPaySn(paySn,
            member.getId());
        dataMap.put("ordervo", serviceResult.getResult());

        ServiceResult<List<Product>> resultProduct = productFrontService.getProductsListCart(0, 10);
        dataMap.put("products", resultProduct.getResult());

        return "front/order/ordersuccess";
    }

    /**
     * 跳转到支付页面 （在线支付）
     * @param request
     * @param response
     * @param dataMap
     * @param paySn 支付交易号（下单后直接跳转结算页时有值）
     * @param orderSn 订单号（从订单列表页跳转而来时有值）
     * @param paySessionstr
     * @param type 4:福利订单 积分足够 5:福利订单 积分不足 使用余额抵扣
     * @return
     */
    @RequestMapping(value = "order/pay.html", method = { RequestMethod.GET })
    public String toPayfor(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap, String paySn, String orderSn,
                           String paySessionstr,Integer type) {

        if (StringUtil.isEmpty(paySn, true) && StringUtil.isEmpty(orderSn, true)) {
            dataMap.put("info", "请选择要支付的订单，谢谢！");
            return "front/commons/error";
        }
        Member member = WebFrontSession.getLoginedUser(request);
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
       
        /*存储余额记录
        member.setTempBalance(memberResult.getResult().getBalance());
        memberService.updateMember(member);
        */
        if (memberResult.getResult() == null) {
            dataMap.put("info", "会员信息获取失败。");
            return "front/commons/error";
        }
        dataMap.put("member", memberResult.getResult());
        
        // 如果paySessionstr不为空，则是从下单后直接跳转过来，否则是从订单列表页跳转而来
        if (!StringUtil.isEmpty(paySessionstr, true)) {

            String order_session = CommUtil
                .null2String(request.getSession(false).getAttribute("order_session"));
            if (!order_session.equals(paySessionstr)) {
                dataMap.put("info", "请使用正常方式支付订单，谢谢！");
                return "front/commons/error";
            }
            OrderSuccessVO orderSuccessVO = (OrderSuccessVO) request.getSession(false)
                .getAttribute("order_success_vo");
            if (orderSuccessVO == null) {
                dataMap.put("info", "session异常，请到订单中心支付订单，谢谢！");
                return "front/commons/error";
            }

            dataMap.put("fromType", 1);

            dataMap.put("paySessionstr", paySessionstr);
            // 下单后直接跳转支付页面传的是支付订单号，此时处理的订单可能是多个（支付订单号一样的多个订单）
            dataMap.put("paySn", paySn);
            dataMap.put("payForOrderSn", orderSuccessVO.getPayForOrderSn());
            dataMap.put("payAmount", orderSuccessVO.getPayAmount());
            dataMap.put("orderSuccessVO", orderSuccessVO);
        } else {

            dataMap.put("fromType", 2);

            // 支付随机码 避免重复提交
            String order_session = CommUtil.randomString(32);
            request.getSession(false).setAttribute("order_session", order_session);

            dataMap.put("paySessionstr", order_session);

            ServiceResult<OrderSuccessVO> orderSuccessVOResult = ordersService
                .getOrdersByOrderSn(orderSn, member.getId());
            if (!orderSuccessVOResult.getSuccess()) {
                dataMap.put("info", "订单信息获取出错，请稍后再试！");
                return "front/commons/error";
            }
            OrderSuccessVO orderSuccessVO = orderSuccessVOResult.getResult();
            
            if(null != orderSuccessVOResult.getResult() && null != orderSuccessVOResult.getResult().getOrdersList()) {
            	Orders order = orderSuccessVOResult.getResult().getOrdersList().get(0);
            	if(order.getIsWelfareOrder() == Product.IS_WELFARE_2) {
            		type = OrderSuccessVO.BANLANCEPAYVO_5;
            		if(memberResult.getResult().getIntegral() > orderSuccessVO.getPayAmount().intValue()) {
            			type = OrderSuccessVO.BANLANCEPAYVO_4;
            		}
            		
            	}
            }
            dataMap.put("orderSuccessVO", orderSuccessVO);
            // 从订单列表跳转来的支付传的是订单号，在点击支付的时候设定支付订单号为订单号
            // 此时处理的支付只有一个订单
            dataMap.put("orderSn", orderSn);
            dataMap.put("payForOrderSn", orderSuccessVO.getPayForOrderSn());
            dataMap.put("payAmount", orderSuccessVO.getPayAmount());
        }
        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
            	dataMap.put("config", config);
            }
        }
        if(null != type && (type == OrderSuccessVO.BANLANCEPAYVO_5 || type == OrderSuccessVO.BANLANCEPAYVO_4)){	//如果积分不够 则进入余额支付页面
        	dataMap.put("type", Product.IS_WELFARE_2);
        }
        return "front/order/payfor";
    }

       
    /**
     * 判断 余额支付密码是否正确
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/checkbalancepwd.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<FrontCheckPwdVO> checkcheckBalancePwd(HttpServletRequest request,
                                                                              HttpServletResponse response,
                                                                              @RequestParam(value = "balancePwd", required = true) String balancePwd) {

        Member member = WebFrontSession.getLoginedUser(request);

        ServiceResult<FrontCheckPwdVO> serviceResult = new ServiceResult<FrontCheckPwdVO>();
        serviceResult = memberService.checkcheckBalancePwd(balancePwd, member.getId());

        HttpJsonResult<FrontCheckPwdVO> jsonResult = new HttpJsonResult<FrontCheckPwdVO>();
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<FrontCheckPwdVO>(serviceResult.getMessage());
            }
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 获取用户当前可用的已绑定的优惠券
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "order/getsellercoupon.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<CouponUser>> getSellerCoupon(HttpServletRequest request,
                                                                          HttpServletResponse response) {

        Member member = WebFrontSession.getLoginedUser(request);

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
    @RequestMapping(value = "order/checksellercoupon.html", method = { RequestMethod.GET })
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

    // -------------------------限时抢购开始--------------------------------------------
    /**
     * 限时抢购时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/flashsale-{visitPath}.html", method = { RequestMethod.GET })
    public String flashSale(@PathVariable String visitPath, HttpServletRequest request,
                            ModelMap map, HttpServletResponse response) {

        // visitPath为1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 3) { //长度不等于3url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            null, 1, Orders.ORDER_TYPE_2);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "front/order/shoporderflashsale";
    }

    /**
     * 限时抢购提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforflash.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForFlash(HttpServletRequest request,
                                                                                   HttpServletResponse response,
                                                                                   OrderCommitVO orderCommitVO,
                                                                                   ModelMap map) {
        // 数量默认1
        orderCommitVO.setNumber(1);
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_2);
    }

    // -------------------------限时抢购结束--------------------------------------------

    // -------------------------活动公共方法开始-----------------------------------------
    /**
     * 根据地址ID计算新的运费信息(为限时抢购结算页使用)
     * @param request
     * @param response
     * @param map
     * @param addressId
     * @return
     */
    @RequestMapping(value = "order/calculateTransFeeForActivity.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResult<BigDecimal> calculateTransFeeForActivity(HttpServletRequest request,
                                                                                 HttpServletResponse response,
                                                                                 ModelMap map,
                                                                                 Integer productId,
                                                                                 Integer productGoodsId,
                                                                                 Integer addressId,
                                                                                 Integer number) {
        // 获取地址
        ServiceResult<MemberAddress> memberAddressRlt = memberAddressService
            .getMemberAddressById(addressId);
        if (!memberAddressRlt.getSuccess() || memberAddressRlt.getResult() == null) {
            return new HttpJsonResult<>("收获地址信息获取失败！");
        }
        //获取商品
        ServiceResult<Product> productResult = productService.getProductById(productId);
        if (!productResult.getSuccess() || productResult.getResult() == null) {
            return new HttpJsonResult<>("商品信息获取失败！");
        }

        //获取货品
        ServiceResult<ProductGoods> productGoodsResult = productGoodsService
            .getProductGoodsById(productGoodsId);
        if (!productGoodsResult.getSuccess() || productGoodsResult.getResult() == null) {
            return new HttpJsonResult<>("货品信息获取失败！");
        }
        // 计算运费
        ServiceResult<BigDecimal> transFeeRlt = sellerTransportService.calculateTransFee(
            productResult.getResult(), productGoodsResult.getResult(), number,
            memberAddressRlt.getResult().getCityId());
        if (!transFeeRlt.getSuccess()) {
            return new HttpJsonResult<>(transFeeRlt.getMessage());
        }
        BigDecimal transFee = transFeeRlt.getResult();
        transFee = transFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : transFee;

        HttpJsonResult<BigDecimal> jsonResult = new HttpJsonResult<BigDecimal>();
        jsonResult.setData(transFee);
        return jsonResult;
    }

    /**
     * 活动时跳转到活动订单结算页
     * @param request
     * @param map
     * @param productId 商品ID
     * @param productGoodsId 货品ID
     * @param sellerId 商家ID
     * @param activityId 活动ID（限时抢购、团购、集合竞价、积分换购）
     * @param number 购买数量
     * @param orderType 订单类型：Orders.ORDER_TYPE_2、限时抢购订单，Orders.ORDER_TYPE_3、团购订单，Orders.ORDER_TYPE_4、竞价定金订单，Orders.ORDER_TYPE_6、积分换购订单
     * @return 返回String字符串，如果字符串长度大于0，表示出错跳转到错误提示页面，返回空表示正常执行
     */
    private String toOrderForActivity(HttpServletRequest request, ModelMap map, Integer productId,
                                      Integer productGoodsId, Integer sellerId, Integer activityId,
                                      Integer number, Integer orderType) {

        Member member = WebFrontSession.getLoginedUser(request);
        // 收货地址信息
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddressByMId(member.getId());
        List<MemberAddress> addressList = null;
        MemberAddress defaultAddress = null;
        // 是否有默认地址
        String hasDefaultAdd = "no";
        // 获取默认收货地址，如果没有取第一个
        if (serviceResult.getSuccess()) {
            addressList = serviceResult.getResult();
            if (addressList != null && addressList.size() > 0) {
                defaultAddress = addressList.get(0);
                for (MemberAddress address : addressList) {
                    if (address.getState() == MemberAddress.STATE_1) {
                        defaultAddress = address;
                        hasDefaultAdd = "yes";
                        break;
                    }
                }
            }
        }
        
        map.put("hasDefaultAdd", hasDefaultAdd);
        map.put("addressList", addressList);
        map.put("defaultAddress", defaultAddress);
        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
        OrderCommitVO orderCommitVO = new OrderCommitVO();
        orderCommitVO.setInvoiceType("");
        orderCommitVO.setInvoiceTitle("");
        orderCommitVO.setPaymentName(Orders.PAYMENT_NAME_ONLINE);
        orderCommitVO.setPaymentCode(Orders.PAYMENT_CODE_ONLINE);
        map.put("orderCommitVO", orderCommitVO);

        map.put("number", number);
        
        // 取商家信息
        ServiceResult<Seller> sellerRlt = sellerService.getSellerById(sellerId);
        if (!sellerRlt.getSuccess()) {
            map.put("info", sellerRlt.getMessage());
            return "front/commons/error";
        }
        map.put("seller", sellerRlt.getResult());
        
        

        // 取商品信息
        ServiceResult<Product> productRlt = productFrontService.getProductById(productId);
        if (!productRlt.getSuccess()) {
            map.put("info", productRlt.getMessage());
            return "front/commons/error";
        }
        map.put("product", productRlt.getResult());

        // 取货品信息
        ServiceResult<ProductGoods> goodRlt = productGoodsService
            .getProductGoodsById(productGoodsId);
        if (!goodRlt.getSuccess()) {
            map.put("info", goodRlt.getMessage());
            return "front/commons/error";
        }
        map.put("productGoods", goodRlt.getResult());

        if (orderType == Orders.ORDER_TYPE_2) {
            // 2、限时抢购订单
            // 根据商品ID、渠道取得当前时间点的该商品参加的限时抢购活动、阶段、活动商品信息（上架的活动，如果有多个，只取最新的一个）
            ServiceResult<ActFlashSale> flashSaleResult = actFlashSaleService
                .getCurrEffectiveActFlashSale(productId, ConstantsEJS.CHANNEL_2);
            if (!flashSaleResult.getSuccess()) {
                map.put("info", flashSaleResult.getMessage());
                return "front/commons/error";
            }

            // 抢购活动信息，不为空则显示，为空则提示当前时间无抢购活动
            ActFlashSale actFlashSale = flashSaleResult.getResult();
            if (actFlashSale != null) {
                map.put("actFlashSale", actFlashSale);
                map.put("actFlashSaleStage", actFlashSale.getStageList().get(0));
                map.put("actFlashSaleProduct",
                    actFlashSale.getStageList().get(0).getProductList().get(0));
            } else {
                return "redirect:/error.html";
            }
        } else if (orderType == Orders.ORDER_TYPE_3) {
            // 3、团购订单
            // 获取团购信息
            ServiceResult<ActGroup> actGroupResult = actGroupService.getActGroupById(activityId);
            if (!actGroupResult.getSuccess()) {
                map.put("info", actGroupResult.getMessage());
                return "front/commons/error";
            }
            ActGroup actGroup = actGroupResult.getResult();
            if (actGroup != null) {
                map.put("actGroup", actGroup);
            } else {
                return "redirect:/error.html";
            }
        } else if (orderType == Orders.ORDER_TYPE_4) {
            // 4、竞价定金订单
            // 获取集合竞价信息
            ServiceResult<ActBidding> actBiddingResult = actBiddingService
                .getActBiddingById(activityId);
            if (!actBiddingResult.getSuccess()) {
                map.put("info", actBiddingResult.getMessage());
                return "front/commons/error";
            }
            ActBidding actBidding = actBiddingResult.getResult();
            if (actBidding != null) {
                map.put("actBidding", actBidding);
            } else {
                return "redirect:/error.html";
            }
        } else if (orderType == Orders.ORDER_TYPE_6) {
        	ServiceResult<Integer> msiResult = 
            		memberSpecialIntegralService.getValueByMemberId(member.getId());
            
            Integer specialIntegral = 0;
            if(msiResult.getSuccess() && null != msiResult.getResult()) {
        		specialIntegral = msiResult.getResult();
            }
            Integer sellerSpecialIntegral = 0;
            ServiceResult<List<MemberSpecialIntegral>>  specialIntegrals = 
            memberSpecialIntegralService.getMemberSpecialIntegralByMemberId(member.getId(), sellerId);
            for(MemberSpecialIntegral m : specialIntegrals.getResult()) {
            	sellerSpecialIntegral += m.getValue();
            }
            
            map.put("sellerSpecialIntegral", sellerSpecialIntegral);
            map.put("specialIntegral", specialIntegral);
            
            // 6、积分换购订单
            // 获取积分换购信息
            ServiceResult<ActIntegral> actIntegralResult = actIntegralService
                .getActIntegralById(activityId);
            if (!actIntegralResult.getSuccess()) {
                map.put("info", actIntegralResult.getMessage());
                return "front/commons/error";
            }
            ActIntegral actIntegral = actIntegralResult.getResult();
            if (actIntegral != null) {
                map.put("actIntegral", actIntegral);
                if(goodRlt.getResult().getImages() == null || "".equals(goodRlt.getResult().getImages())){
                	goodRlt.getResult().setImages(actIntegral.getImage());
                }
                map.put("productGoods", goodRlt.getResult());
            } else {
                return "redirect:/error.html";
            }
        }

        if (orderType != Orders.ORDER_TYPE_6) {
            // 计算运费，积分换购不需要计算运费、不开发票
            ServiceResult<BigDecimal> transFeeRlt = sellerTransportService.calculateTransFee(
                productRlt.getResult(), goodRlt.getResult(), number, defaultAddress.getCityId());
            if (!transFeeRlt.getSuccess()) {
                map.put("info", "运费计算失败，请稍后再试。");
                return "front/commons/error";
            }
            BigDecimal transFee = transFeeRlt.getResult();
            map.put("transFee",
                transFee.compareTo(BigDecimal.ZERO) < 1 ? BigDecimal.ZERO : transFee);

            // 获取发票信息
            ServiceResult<List<Invoice>> invoiceResult = invoiceService
                .getInvoiceByMId(member.getId());
            map.put("invoiceList", invoiceResult.getResult());
        }

        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            map.put("info", "会员信息获取失败。");
            return "front/commons/error";
        }
        map.put("member", memberResult.getResult());

        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                map.put("config", config);
            }
        }

        return "";
    }
    
    @RequestMapping(value = "order/jifenCart.html", method = { RequestMethod.GET })
    public String jifenCart(HttpServletRequest request, ModelMap map,HttpServletResponse response){
    	
    	Member member = WebFrontSession.getLoginedUser(request);
        // 收货地址信息
        ServiceResult<List<MemberAddress>> serviceResult = memberAddressService
            .getMemberAddressByMId(member.getId());
        List<MemberAddress> addressList = null;
        MemberAddress defaultAddress = null;
        // 是否有默认地址
        String hasDefaultAdd = "no";
        // 获取默认收货地址，如果没有取第一个
        if (serviceResult.getSuccess()) {
            addressList = serviceResult.getResult();
            if (addressList != null && addressList.size() > 0) {
                defaultAddress = addressList.get(0);
                for (MemberAddress address : addressList) {
                    if (address.getState() == MemberAddress.STATE_1) {
                        defaultAddress = address;
                        hasDefaultAdd = "yes";
                        break;
                    }
                }
            }
        }
        map.put("hasDefaultAdd", hasDefaultAdd);
        map.put("addressList", addressList);
        map.put("defaultAddress", defaultAddress);
        // 构建默认值 ，默认在线支付。收货地址为默认地址，发票默认为不开发票
        OrderCommitVO orderCommitVO = new OrderCommitVO();
        orderCommitVO.setInvoiceType("");
        orderCommitVO.setInvoiceTitle("");
        orderCommitVO.setPaymentName(Orders.PAYMENT_NAME_INTEGRAL);
        orderCommitVO.setPaymentCode(OrderCommitVO.WELFARE);
        
        // 取购物车信息  产品价格 按照商家来区分
        // 查询购物车
        ServiceResult<CartInfoVO> cartServiceResult = cartService.getCartInfoByMId(member.getId(),
            defaultAddress, ConstantsEJS.SOURCE_1_PC, 2,Cart.SOURCE_2);
        
        Integer specialIntegral = 0;
        
        ServiceResult<Integer> msiResult = 
        		memberSpecialIntegralService.getValueByMemberId(member.getId());
        
        if(msiResult.getSuccess() && null != msiResult.getResult()) {
    		specialIntegral = msiResult.getResult();
        }
        Integer sellerSpecialIntegral = 0;
        if(null != cartServiceResult.getResult() && null != cartServiceResult.getResult().getCartListVOs()) {
        	List<CartListVO> cartListVo = cartServiceResult.getResult().getCartListVOs();
        	for(CartListVO  cartVo : cartListVo) {
        		if(null != cartVo.getCartList()) {
        			sellerSpecialIntegral += cartVo.getSpecialIntegral();
        			for(Cart cart : cartVo.getCartList()) {
        				ActIntegral actIntegral = actIntegralService.getActIntegralById(cart.getActIntegralId()).getResult();
        				if(null != actIntegral) {
        					cart.getProduct().setImagePath(actIntegral.getImage());
        				}
        			}
        		}
        	}
        	cartServiceResult.getResult().setCartListVOs(cartListVo);
        }
        
        map.put("sellerSpecialIntegral", sellerSpecialIntegral);
        map.put("specialIntegral", specialIntegral);
        map.put("cartInfoVO", cartServiceResult.getResult());
        map.put("orderCommitVO", orderCommitVO);
        
        //取会员余额信息
        ServiceResult<Member> memberResult = memberService.getMemberById(member.getId());
        if (memberResult.getResult() == null) {
            map.put("info", "会员信息获取失败。");
            return "front/commons/error";
        }
        map.put("member", memberResult.getResult());

        ServiceResult<Config> configById = configservice.getConfigById(ConstantsEJS.CONFIG_ID);
        if (configById.getResult() != null) {
            Config config = configById.getResult();
            if (config.getIntegralScale() > 0) {
                map.put("config", config);
            }
        }
    	
    	return "front/order/cartshoporderintegral";
    }

    // -------------------------活动公共方法结束-----------------------------------------

    // -------------------------团购开始-----------------------------------------------

    /**
     * 团购时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/tuan-{visitPath}.html", method = { RequestMethod.GET })
    public String tuan(@PathVariable String visitPath, HttpServletRequest request, ModelMap map,
                       HttpServletResponse response) {

        // visitPath为1-1-1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID，第四个未团购ID，第五个为购买数量
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 5) { //长度不等于5url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        String actGroupIdStr = arrVisitPath[3];
        String numberStr = arrVisitPath[4];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
        Integer actGroupId = ConvertUtil.toInt(actGroupIdStr, 0);
        Integer number = ConvertUtil.toInt(numberStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            actGroupId, number, Orders.ORDER_TYPE_3);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "front/order/shopordergroup";
    }

    /**
     * 团购提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforgroup.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForGroup(HttpServletRequest request,
                                                                                   HttpServletResponse response,
                                                                                   OrderCommitVO orderCommitVO,
                                                                                   ModelMap map) {
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_3);
    }

    // -------------------------团购结束-----------------------------------------------

    // -------------------------集合竞价开始-----------------------------------------------
    /**
     * 竞价定金订单时跳转到提交订单页面 计算总金额,运费、货品小计，按店铺拆分订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/bidding-{visitPath}.html", method = { RequestMethod.GET })
    public String bidding(@PathVariable String visitPath, HttpServletRequest request, ModelMap map,
                          HttpServletResponse response) {

        // visitPath为1-1-1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID，第四个为竞价活动ID，第五个为购买数量
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 5) { //长度不等于5url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        String actBiddingIdStr = arrVisitPath[3];
        String numberStr = arrVisitPath[4];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
        Integer actBiddingId = ConvertUtil.toInt(actBiddingIdStr, 0);
        Integer number = ConvertUtil.toInt(numberStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            actBiddingId, number, Orders.ORDER_TYPE_4);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "front/order/shoporderbidding";
    }

    /**
     * 竞价定金提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforbidding.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForBidding(HttpServletRequest request,
                                                                                     HttpServletResponse response,
                                                                                     OrderCommitVO orderCommitVO,
                                                                                     ModelMap map) {
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_4);
    }

    // -------------------------集合竞价结束-----------------------------------------------

    // -------------------------积分换购开始-----------------------------------------------

    /**
     * 积分换购时跳转到提交订单页面 计算总金额
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/jifen-{visitPath}.html", method = { RequestMethod.GET })
    public String integral(@PathVariable String visitPath, HttpServletRequest request, ModelMap map,
                           HttpServletResponse response) {

        // visitPath为1-1-1-1-1形式，第一个为商品ID，第二个为货品ID，第三个为商家ID，第四个未积分换购ID，第五个为购买数量
        String[] arrVisitPath = visitPath.split("-");
        int arrVisitPathLength = arrVisitPath.length;
        if (arrVisitPathLength != 5) { //长度不等于5url错误
            return "redirect:/error.html";
        }

        String productIdStr = arrVisitPath[0];
        String productGoodsIdStr = arrVisitPath[1];
        String sellerIdStr = arrVisitPath[2];
        String actIntegralIdStr = arrVisitPath[3];
        String numberStr = arrVisitPath[4];
        Integer productId = ConvertUtil.toInt(productIdStr, 0);
        Integer productGoodsId = ConvertUtil.toInt(productGoodsIdStr, 0);
        Integer sellerId = ConvertUtil.toInt(sellerIdStr, 0);
        Integer actIntegralId = ConvertUtil.toInt(actIntegralIdStr, 0);
        Integer number = ConvertUtil.toInt(numberStr, 0);

        String errorUrl = this.toOrderForActivity(request, map, productId, productGoodsId, sellerId,
            actIntegralId, number, Orders.ORDER_TYPE_6);
        if (!StringUtil.isEmpty(errorUrl, true)) {
            // 如果出错跳到出错页面
            return errorUrl;
        }

        return "front/order/shoporderintegral";
    }
    

    /**
     * 积分换购提交订单
     * @param request
     * @param response
     * @param map
     * @return
     * @throws IOException 
     */
    @RequestMapping(value = "order/ordercommitforintegral.html", method = { RequestMethod.POST })
    public @ResponseBody HttpJsonResultForAjax<OrderSuccessVO> orderSubmitForIntegral(HttpServletRequest request,
                                                                                      HttpServletResponse response,
                                                                                      OrderCommitVO orderCommitVO,
                                                                                      ModelMap map) {
        return this.commonSubmit(request, orderCommitVO, Orders.ORDER_TYPE_6);
    }

    // -------------------------积分换购结束-----------------------------------------------
}
