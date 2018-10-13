package com.sln.web.controller.pay;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sln.entity.product.Product;
import com.sln.service.product.IProductFrontService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.config.AlipayConfig;
import com.alipay.util.AlipaySubmit;
import com.sln.core.ServiceResult;
import com.sln.core.SlnConfig;
import com.sln.core.StringUtil;
import com.sln.core.TimeUtil;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.core.pay.ECardPay;
import com.sln.core.pay.bean.Order;
import com.sln.entity.member.Member;
import com.sln.service.order.IOrdersService;
import com.sln.vo.order.OrderSuccessVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.CommUtil;
import com.sln.web.util.WebFrontSession;
import com.unionpay.acp.SDKConfig;
import com.unionpay.acp.SDKUtil;

/**
 * 跳转到支付
 *                       
 * @Filename: PayIndexController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class PayIndexController extends BaseController {

    @Resource
    private IOrdersService       ordersService;
    @Resource
    private IProductFrontService productFrontService;

    @RequestMapping(value = "/payindex.html", method = RequestMethod.GET)
    public String payindex(HttpServletRequest request, HttpServletResponse response,
                           Map<String, Object> dataMap) {

        String optionsRadios = request.getParameter("optionsRadios");

        if (StringUtil.isEmpty(optionsRadios, true)) {
            dataMap.put("info", "请选择要支付的订单，谢谢！");
            return "h5/commons/error";
        }
        Member member = WebFrontSession.getLoginedUser(request);
        if (member == null) {
            dataMap.put("info", "用户Session过期，请重新登录");
            return "h5/commons/error";
        }

        //String paySessionstr = request.getParameter("paySessionstr");
        // String payType = request.getParameter("payType");
        String fromType = request.getParameter("fromType");
        String selectOrderBalance = request.getParameter("selectOrderBalance");
        String balancePassword = request.getParameter("balancePassword");
        boolean isBalancePay = "on".equals(selectOrderBalance) ? true : false;

        //        System.out.println("paySessionstr:" + paySessionstr);
        //        System.out.println("selectOrderBalance:" + selectOrderBalance);
        //        System.out.println("balancePassword:" + balancePassword);
        //        System.out.println("isBalancePay:" + isBalancePay);
        //String order_session = CommUtil
        //    .null2String(WebFrontSession.geDefSession(request,false).getAttribute("order_session"));
        //if (!order_session.equals(paySessionstr)) {
          //  dataMap.put("info", "session异常，请稍后再试，谢谢！");
         //   return "h5/commons/error";
        //}
        // 清除支付标志
        WebFrontSession.geDefSession(request,false).removeAttribute("order_session");

        // 支付订单号，根据结算来源确定值，取paySn或者orderSn其中一个，二者之一必须有值
        String paySn = "";
        // 是否是支付订单号
        boolean isPaySn = true;

        // 如果是从下单结算也跳转过来且勾选了使用余额，则支付密码从session中取
        if ("1".equals(fromType)) {
            OrderSuccessVO orderSuccessVO = (OrderSuccessVO) WebFrontSession.geDefSession(request,false)
                .getAttribute("order_success_vo");
            WebFrontSession.geDefSession(request,false).removeAttribute("order_success_vo");
            if (orderSuccessVO == null) {
                dataMap.put("info", "session异常，请到订单中心支付订单，谢谢！");
                return "h5/commons/error";
            }

            // 支付订单号，下单后直接跳转的时候传支付订单号
            paySn = request.getParameter("paySn");
            isPaySn = true;
        } else {
            // 订单号，从订单列表页跳转支付时传订单号
            paySn = request.getParameter("orderSn");
            isPaySn = false;
        }

        if ("integral".equals(optionsRadios)) {//其他支付在这添加
            ServiceResult<OrderSuccessVO> orderPayResult = ordersService.orderPayByIntegral(isPaySn, paySn,OrderSuccessVO.BANLANCEPAYVO_5,
                    balancePassword, member);
            if (!orderPayResult.getSuccess()) {
                dataMap.put("info", orderPayResult.getMessage());
                return "h5/commons/error";
            }
            OrderSuccessVO orderSuccessVO = orderPayResult.getResult();
            if (orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_4
                || orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_5) {
                ServiceResult<List<Product>> resultProduct = productFrontService
                        .getProductsListCart(0, 10);
                dataMap.put("products", resultProduct.getResult());
                return "h5/order/linepay";
            } else {
                dataMap.put("info", "积分或余额不足，支付失败，请充值后到我的订单重新支付");
                return "h5/commons/error";
            }
        }
        // 调用订单支付接口
        ServiceResult<OrderSuccessVO> orderPayResult = ordersService.orderPayBefore(isPaySn, paySn,
                isBalancePay, balancePassword, member);
        if (!orderPayResult.getSuccess()) {
            dataMap.put("info", orderPayResult.getMessage());
            return "h5/commons/error";
        }

        OrderSuccessVO orderSuccessVO = orderPayResult.getResult();
        //System.out.println("payOrderAllsVO = orderSuccessVO.getPayOrderAllsVO();: " + orderSuccessVO.getPayOrderAllsVO());

        String orderPaySn = orderSuccessVO.getPaySn();
        //需要支付的总金额
        BigDecimal payOrderAllsVO;
        if (orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_2) { //余额支付够付款，扣除余额，更改订单状态，跳转到支付成功页面
            return "h5/order/linepay";
        } else if (orderSuccessVO.getBanlancePayVO() == OrderSuccessVO.BANLANCEPAYVO_3) {
            payOrderAllsVO = orderSuccessVO.getPayOrderAllsVO()
                .subtract(orderSuccessVO.getBanlancePayMoneyVO());
        } else {
            payOrderAllsVO = orderSuccessVO.getPayOrderAllsVO();
        }

        //System.out.println("payOrderAllsVO:" + payOrderAllsVO);
        //System.out.println("orderPaySn:" + orderPaySn);

        if ("ecardpay".equals(optionsRadios)) {//一卡通支付
            log.info("调用一卡通支付接口，H5商城，订单号：" + orderPaySn);
            try {
                // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
                //request.setCharacterEncoding(SlnConfig.ENCODE);
                //response.setCharacterEncoding(SlnConfig.ENCODE);
                
                Order order = new Order();
                //order.setAccount("8572");
                //order.setSno("144105");
                order.setAccount("");
                order.setSno("");
                order.setOrderdesc(ECardPay.ORDER_DESC_PHONE);
                order.setOrdertype(SlnConfig.ECARDPAY_ORDERTYPE_PHONE);
                order.setPraram1(ECardPay.ORDER_TYPE_PHONE);
                order.setThirdorderid(orderPaySn);//订单号
                order.setThirdurl(DomainUrlUtil.ECARDPAY_PHONE_RETURN_URL);
                order.setToaccount("1000000");//测试商户号
                order.setTranamt(payOrderAllsVO.multiply(BigDecimal.valueOf(100)).toString());//金额，以分为单位
                                
                //生成自动跳转的Html表单
                String sHtmlText = SDKUtil.createAutoFormHtml(DomainUrlUtil.ECARDPAY_CREATEORDER_URL, ECardPay.createOrder(order),SDKUtil.encoding_UTF8); 
                dataMap.put("paytext", sHtmlText);
                return "h5/order/ecardpay";
            } catch (Exception e) {
                log.error("一卡通支付出现异常:" + e.getMessage());
                dataMap.put("info", "支付异常，请到我的订单重新支付，谢谢！");
                return "h5/commons/error";
            }
        } else if ("alipay".equals(optionsRadios)) {//支付宝付款
            //System.out.println("-----1-------");
            try {
                //支付类型
                String payment_type = "1";
                //必填，不能修改
                //服务器异步通知页面路径
                String notify_url = DomainUrlUtil.getSLN_URL_RESOURCES()
                                    + "/alipay_result_notify.html";
                                    //需http://格式的完整路径，不能加?id=123这类自定义参数

                //页面跳转同步通知页面路径
                String return_url = DomainUrlUtil.getSLN_URL_RESOURCES() + "/alipay_result.html";

                //商户订单号
                String out_trade_no = orderPaySn;
                //                String out_trade_no = new Date().getTime() + "";
                //商户网站订单系统中唯一订单号，必填

                //订单名称
                String subject = SlnConfig.ALIPAY_ALL_SUBJECT;
                //必填

                // 付款金额
                // TODO 测试支付，生产环境删掉测试的total_fee
                // String total_fee = "0.01";
                // 生产环境支付金额
                String total_fee = payOrderAllsVO.toString();

                //必填

                //商品展示地址
                String show_url = DomainUrlUtil.getSLN_URL_RESOURCES() + "/index.html";
                //必填，需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

                //订单描述
                String body = "";
                //选填

                //超时时间
                String it_b_pay = "";
                //选填

                //钱包token
                String extern_token = "";
                //选填

                //////////////////////////////////////////////////////////////////////////////////
                //System.out.println("-----2-------");
                //把请求参数打包成数组
                Map<String, String> sParaTemp = new HashMap<String, String>();
                sParaTemp.put("service", "alipay.wap.create.direct.pay.by.user");
                sParaTemp.put("partner", AlipayConfig.partner);
                sParaTemp.put("seller_id", AlipayConfig.seller_id);
                sParaTemp.put("_input_charset", AlipayConfig.input_charset);
                sParaTemp.put("payment_type", payment_type);
                sParaTemp.put("notify_url", notify_url);
                sParaTemp.put("return_url", return_url);
                sParaTemp.put("out_trade_no", out_trade_no);
                sParaTemp.put("subject", subject);
                sParaTemp.put("total_fee", total_fee);
                sParaTemp.put("show_url", show_url);
                sParaTemp.put("body", body);
                sParaTemp.put("it_b_pay", it_b_pay);
                sParaTemp.put("extern_token", extern_token);
                sParaTemp.put("app_pay", "Y");
                //System.out.println("-----3-------");
                //建立请求
                String sHtmlText = AlipaySubmit.buildRequest(sParaTemp, "get", "确认");
                //System.out.println(sHtmlText);
                //System.out.println("-----4-------");
                dataMap.put("paytext", sHtmlText);
                return "h5/order/alipay";
            } catch (Exception e) {
            }
        } else if ("unionpay".equals(optionsRadios)) { //银联支付
            try {
                String merId = SlnConfig.UNIONPAY_MERID; //商家ID

                payOrderAllsVO = payOrderAllsVO.multiply(new BigDecimal(100));
                String txnAmt = payOrderAllsVO.toString().split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉

                Map<String, String> requestData = new HashMap<String, String>();

                /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
                requestData.put("version", SDKUtil.version); //版本号，全渠道默认值
                requestData.put("encoding", SDKUtil.encoding_UTF8); //字符集编码，可以使用UTF-8,GBK两种方式
                requestData.put("signMethod", "01"); //签名方法，只支持 01：RSA方式证书加密
                requestData.put("txnType", "01"); //交易类型 ，01：消费
                requestData.put("txnSubType", "01"); //交易子类型， 01：自助消费
                requestData.put("bizType", "000201"); //业务类型，B2C网关支付，手机wap支付
                requestData.put("channelType", "07"); //渠道类型，这个字段区分B2C网关支付和手机wap支付；07：PC,平板  08：手机

                /***商户接入参数***/
                requestData.put("merId", merId); //商户号码，请改成自己申请的正式商户号或者open上注册得来的777测试商户号
                requestData.put("accessType", "0"); //接入类型，0：直连商户 

                requestData.put("orderId", orderPaySn); //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则     
                requestData.put("txnTime", TimeUtil.getCurrentTime()); //订单发送时间，取系统时间，格式为YYYYMMDDhhmmss，必须取当前时间，否则会报txnTime无效
                requestData.put("currencyCode", "156"); //交易币种（境内商户一般是156 人民币）        
                requestData.put("txnAmt", txnAmt); //交易金额，单位分，不要带小数点
                requestData.put("reqReserved", SlnConfig.UNIONPAY_REQRESERVED); //请求方保留域，透传字段（可以实现商户自定义参数的追踪）本交易的后台通知,对本交易的交易状态查询交易、对账文件中均会原样返回，商户可以按需上传，长度为1-1024个字节     

                //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”按钮的时候将异步通知报文post到该地址
                //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
                //异步通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                requestData.put("frontUrl",
                    DomainUrlUtil.getSLN_URL_RESOURCES() + "/unionpay_result.html");

                //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
                //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
                //注意:1.需设置为外网能访问，否则收不到通知    2.http https均可  3.收单后台通知后需要10秒内返回http200或302状态码 
                //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
                //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
                requestData.put("backUrl",
                    DomainUrlUtil.getSLN_URL_RESOURCES() + "/unionpay_result_notify.html");

                //////////////////////////////////////////////////
                //
                //       报文中特殊用法请查看 PCwap网关跳转支付特殊用法.txt
                //
                //////////////////////////////////////////////////

                /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
                Map<String, String> submitFromData = SDKUtil.signData(requestData,
                    SDKUtil.encoding_UTF8); //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

                String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl(); //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
                //System.out.println("requestFrontUrl:" + requestFrontUrl);
                String html = SDKUtil.createAutoFormHtml(requestFrontUrl, submitFromData,
                    SDKUtil.encoding_UTF8); //生成自动跳转的Html表单

                //System.out.println("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
                //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
                //                response.getWriter().write(html);
                dataMap.put("paytext", html);
                return "h5/order/alipay";
            } catch (Exception e) {
                log.error("unionpay支付出现异常" + e);
            }
        } else if ("wxpay".equals(optionsRadios)) {
            payOrderAllsVO = payOrderAllsVO.multiply(new BigDecimal(100));
            String txnAmt = payOrderAllsVO.toString().split("\\.")[0]; //付款金额，单位为分，不能有小数点，去掉
            //微信支付
            //共账号及商户相关参数
            String appid = SlnConfig.WXPAY_APPID;
            String backUri = DomainUrlUtil.getSLN_URL_RESOURCES() + "/wxpay/topay";

            backUri = backUri + "?orderPaySn=" + orderPaySn + "&describe="
                      + CommUtil.substring(orderSuccessVO.getProductName(), 20) + "&money="
                      + txnAmt;
            //URLEncoder.encode 后可以在backUri 的url里面获取传递的所有参数
            try {
                backUri = URLEncoder.encode(backUri, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            //scope 参数视各自需求而定，这里用scope=snsapi_base 不弹出授权页面直接授权目的只获取统一支付接口的openid
            String url = SlnConfig.WXPAY_OAUTH2_URL;
            url = url.replace("APPID", appid).replace("REDIRECT_URI", backUri)
                .replace("SCOPE", SlnConfig.WXPAY_SCOPE_BASE)
                .replace("STATE", SlnConfig.WXPAY_STATE);
            return "redirect:" + url;

        } else {//其他支付在这添加

        }
        return "";
    }

}
