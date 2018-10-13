package com.sln.web.controller.pay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sln.core.HttpClientUtil;
import com.sln.core.RandomUtil;
import com.sln.core.ServiceResult;
import com.sln.core.SlnConfig;
import com.sln.core.TimeUtil;
import com.sln.core.WebUtil;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.core.pay.ECardPay;
import com.sln.core.pay.bean.Order;
import com.sln.entity.order.Orders;
import com.sln.entity.product.Product;
import com.sln.service.order.IOrdersService;
import com.sln.service.product.IProductFrontService;
import com.sln.web.controller.BaseController;
import com.unionpay.acp.SDKUtil;

/**
 * 一卡通支付接口Controller
 */
@Controller
@RequestMapping(value = "pay")
public class ECardPayController extends BaseController {
    
    /**
     * 日志对象
     */
    private static Logger log = Logger.getLogger(ECardPayController.class);
    
    @Resource
    private IOrdersService  ordersService;
    
    @Resource
    private IProductFrontService productFrontService;
    
    /**
     * 一卡通支付服务器异步通知
     * @param request
     * @param response
     */
    @RequestMapping(value = "/eCardPayNotify.html", method = RequestMethod.POST)
    public void eCardPayNotify(HttpServletRequest request, HttpServletResponse response) {
        log.info("接收一卡通支付服务器异步通知");
        String params = WebUtil.getRequestParameters(request);
        log.info(params);
        
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        try {
            request.setCharacterEncoding(SlnConfig.ENCODE);
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        response.setCharacterEncoding(SlnConfig.ENCODE);
        
        String sign = request.getParameter("sign");//签名
        
        //签名为空返回错误
        if(null == sign){
            log.error("签名为空");
            try {
                response.getWriter().println("error");
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
            return;
        }
        try {            
            //存在+字符串说明是未经URL编码
            if(!(sign.indexOf("+") > -1)){
                sign = URLDecoder.decode(sign, SlnConfig.ENCODE);//对签名进行解码
            }
            //获取一卡通支付平台POST过来的反馈信息
            Order order = new Order();
            order.setTranamt(request.getParameter("tranamt"));//交易金额(分)
            order.setOrderid(request.getParameter("orderid"));//支付平台 订单号，支付平台支付成功以后的订单号
            order.setAccount(request.getParameter("account"));//员工账号            
            order.setSno(request.getParameter("sno"));//员工工号
            order.setToaccount(request.getParameter("toaccount"));//商户账号          
            order.setThirdsystem(request.getParameter("thirdsystem")); //系统注册ID，由支付平台分配给接入方的唯一一个ID
            order.setThirdorderid(request.getParameter("thirdorderid"));//第三方订单号，支付平台接入方生成唯一编号
            order.setState(request.getParameter("state"));//交易状态( 1 成功 0待支付 2取消)
            order.setOrderdesc(request.getParameter("orderdesc"));//商品名称
            order.setPraram1(request.getParameter("praram1"));//第三方系统备注字段，订单查询接口，对账原样返回
            order.setActulamt(request.getParameter("actulamt"));//实际交易金额(分) 
            order.setPayname(request.getParameter("payname"));//支付方式名称
            order.setName(request.getParameter("name"));//支付人姓名
            order.setSign(sign);
            
            if(null != request.getParameter("rzdate")){
                order.setRzdate(Timestamp.valueOf(request.getParameter("rzdate")));//支付平台入账时间，24小时制
            }
            if(null != request.getParameter("jydate")){
                order.setJydate(Timestamp.valueOf(request.getParameter("jydate"))); //交易日期 (支付平 台与其他 支付系统 实际支付 完成的时间)，24小时制
            }            
            
            SortedMap<String, String> sort = new TreeMap<String, String>();
            sort.put("tranamt", String.valueOf(order.getTranamt()));
            sort.put("orderid", order.getOrderid());
            sort.put("account", String.valueOf(order.getAccount()));
            sort.put("sno", order.getSno());
            sort.put("toaccount", String.valueOf(order.getToaccount()));
            sort.put("thirdsystem", order.getThirdsystem());
            sort.put("thirdorderid", order.getThirdorderid());
            sort.put("state", order.getState());
            sort.put("orderdesc", order.getOrderdesc());
            sort.put("praram1", order.getPraram1());
            
            String signsString = ECardPay.sign(sort);//对参数进行拼接、加密和签名
            String total_fee = new BigDecimal(order.getTranamt()).divide(BigDecimal.valueOf(100)).toString();//交易金额
            //String params = ECardPay.jointPara(sort).toString();//返回参数
            
            //验证签名
            if(!signsString.contains(order.getSign())){
                log.error("签名错误");
                //签名失败返回非pok字符串
                response.getWriter().println("error");
                return;
            }
            
            //交易成功
            if("1".equals(order.getState())){
                String code = null; // 支付方式代码
                String name = null; // 支付方式名称
                if (order.getPraram1().equals(SlnConfig.ECARDPAY_ORDERTYPE_PC)) {
                    code = Orders.PAYMENT_CODE_PCECARDPAY;
                    name = Orders.PAYMENT_NAME_PCECARDPAY;
                }else {
                    code = Orders.PAYMENT_CODE_H5ECARDPAY;
                    name = Orders.PAYMENT_NAME_H5ECARDPAY;
                }
                
                // 调用订单支付接口
                ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(order.getThirdorderid(), total_fee,code ,name, order.getOrderid(), params);
                log.info(orderPayResult.getMessage());
               
                response.getWriter().println("pok");
            }else{
                response.getWriter().println("error");
            }
            
        } catch (Exception e) {
            log.warn(e.getMessage());
            try {
                response.getWriter().println("error");
            } catch (IOException e1) {
                log.warn(e1.getMessage());
            }
        }
    }
    
    /**
     * 一卡通退款异步通知
     * @param request
     * @param response
     */
    @RequestMapping(value = "/eCardPayRefund.html", method = RequestMethod.POST)
    public void eCardPayRefund(HttpServletRequest request, HttpServletResponse response) {
                
        log.info("接收一卡通退款异步通知");
        log.info(WebUtil.getRequestParameters(request));
        
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        try {
            request.setCharacterEncoding(SlnConfig.ENCODE);
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        response.setCharacterEncoding(SlnConfig.ENCODE);
        
        String sign = request.getParameter("sign");//签名
        
        //签名为空返回错误
        if(null == sign){
            log.error("一卡通退款-签名错误");
            try {
                response.getWriter().println("error");
            } catch (IOException e) {
                log.warn(e.getMessage());
            }
            return;
        }
        try {            
            //存在+字符串说明是未经URL编码
            if(!(sign.indexOf("+") > -1)){
                sign = URLDecoder.decode(sign, SlnConfig.ENCODE);//对签名进行解码
            }
            //获取一卡通支付平台POST过来的反馈信息
            Order refOrder = new Order();
            //refOrder.setOutrefundorderid(request.getParameter("outrefundorderid"));//商户退款的订单号码
            refOrder.setProductBackSn(request.getParameter("productbacksn"));//退款申请单号
            refOrder.setOrderid(request.getParameter("orderid"));//支付平台订单号，支付平台支付成功以后的订单号
            refOrder.setTranamt(request.getParameter("tranamt"));//金额(分)
            //refOrder.setRefundstate(request.getParameter("state"));//退款状态( 1成功 2失败)
            //refOrder.setReforderid(request.getParameter("reforderid"));//支付平台返回退款的订单号
            //refOrder.setPrompt(request.getParameter("prompt"));//提示信息
            //refOrder.setPromptcode(request.getParameter("promptcode"));//提示代码
            refOrder.setSign(sign);//签名
                        
            SortedMap<String, String> sort = new TreeMap<String, String>();
            //sort.put("outrefundorderid", refOrder.getOutrefundorderid());
            sort.put("productbacksn", refOrder.getProductBackSn());
            sort.put("orderid", refOrder.getOrderid());
            sort.put("tranamt", refOrder.getTranamt());
            //sort.put("state", refOrder.getState());
            //sort.put("reforderid", refOrder.getReforderid());
            
            String signsString = ECardPay.sign(sort);//对参数进行拼接、加密和签名
            //验证签名
            if(!signsString.contains(refOrder.getSign())){
                //签名失败返回非pok字符串
                log.error("签名错误");
                response.getWriter().println("error");
                return;
            }else{
              //退款成功
                log.info("退款成功，退款申请单号:" + refOrder.getProductBackSn());
                response.getWriter().println("pok");
            }            
        } catch (Exception e) {
            log.warn(e.getMessage());
            try {
                response.getWriter().println("error");
            } catch (IOException e1) {
                log.warn(e1.getMessage());
            }
        }
    }
    
    /**
     * 一卡通页面跳转同步通知
     * @param request
     * @param response
     * @param stack
     * @return
     */
    @RequestMapping(value = "/eCardPayResult.html", method = RequestMethod.POST)
    public String eCardPayResult(HttpServletRequest request, HttpServletResponse response, Map<String, Object> stack) {
        log.info("接收一卡通页面跳转同步通知");
        String params = WebUtil.getRequestParameters(request);
        log.info(params);
        stack.put("msg", "抱歉，订单支付失败！");
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding(SlnConfig.ENCODE);
            response.setCharacterEncoding(SlnConfig.ENCODE);
            
            String sign = request.getParameter("sign");
            //签名为空返回错误
            if(null == sign){
                log.error("签名错误！");
            } else {
                //存在+字符串说明是未经URL编码
                if(!(sign.indexOf("+") > -1)){
                    sign = URLDecoder.decode(sign, SlnConfig.ENCODE);//对签名进行解码
                }
                //获取一卡通支付平台POST过来的反馈信息
                Order order = new Order();
                order.setTranamt(request.getParameter("tranamt"));//交易金额(分)
                order.setOrderid(request.getParameter("orderid"));//支付平台订单号，支付平台支付成功以后的订单号
                order.setAccount(request.getParameter("account"));//员工账号             
                order.setSno(request.getParameter("sno"));//员工工号
                order.setToaccount(request.getParameter("toaccount"));//商户账号         
                order.setThirdsystem(request.getParameter("thirdsystem")); //系统注册ID，由支付平台分配给接入方的唯一一个ID
                order.setThirdorderid(request.getParameter("thirdorderid"));//第三方订单号，支付平台接入方生成唯一编号
                order.setState(request.getParameter("state"));//交易状态( 1 成功 0待支付 2取消)
                order.setOrderdesc(request.getParameter("orderdesc"));//商品名称
                order.setPraram1(request.getParameter("praram1"));//第三方系统备注字段，订单查询接口，对账原样返回
                order.setActulamt(request.getParameter("actulamt"));//实际交易金额(分)        
                order.setPayname(request.getParameter("payname"));//支付方式名称
                order.setName(request.getParameter("name"));//支付人姓名
                order.setSign(sign);//签名
                
                //支付平台入账时间，24小时制
                if(null != request.getParameter("rzdate")){
                    order.setRzdate(Timestamp.valueOf(request.getParameter("rzdate")));
                }
                
                //交易日期 (支付平 台与其他 支付系统 实际支付 完成的时间)，24小时制
                if(null != request.getParameter("jydate")){
                    order.setJydate(Timestamp.valueOf(request.getParameter("jydate"))); 
                }            
                
                SortedMap<String, String> sort = new TreeMap<String, String>();
                sort.put("tranamt", order.getTranamt());
                sort.put("orderid", order.getOrderid());
                sort.put("account", order.getAccount());
                sort.put("sno", order.getSno());
                sort.put("toaccount", order.getToaccount());
                sort.put("thirdsystem", order.getThirdsystem());
                sort.put("thirdorderid", order.getThirdorderid());
                sort.put("state", order.getState());
                sort.put("orderdesc", order.getOrderdesc());
                sort.put("praram1", order.getPraram1());
                
                String signString = ECardPay.sign(sort);//对参数进行拼接、加密和签名
                String  total_fee = new BigDecimal(order.getTranamt()).divide(BigDecimal.valueOf(100)).toString();//交易金额
                //String params = ECardPay.jointPara(sort).toString();//返回参数
                
                //验证签名
                if(signString.contains(order.getSign())){
                  //交易成功
                    if("1".equals(order.getState())){
                        // 调用订单支付接口
                        ServiceResult<Boolean> orderPayResult = ordersService.orderPayAfter(order.getThirdorderid(), total_fee, Orders.PAYMENT_CODE_PCECARDPAY,Orders.PAYMENT_NAME_PCECARDPAY, order.getOrderid(), params);
                        
                        if(orderPayResult.getSuccess()){
                            
                          //发起京东下单
//                            JDOrder jdOrder = new JDOrder();
//                            jdOrder.setToken(JdApi.getAccessToken(null).getResult().getAccess_token());
//                            jdOrder.setThirdOrder(order.getThirdorderid());
//                            jdOrder.setSku("[{\"skuId\":639585, \"num\":1,\"bNeedAnnex\":true, \"bNeedGift\":true, \"price\":12}]");
//                            jdOrder.setName("陈建强");
//                            jdOrder.setProvince(19);
//                            jdOrder.setCity(1607);
//                            jdOrder.setCounty(3639);
//                            jdOrder.setTown(0);
//                            jdOrder.setAddress("广东省深圳市福田区深南中路2039号核电大厦1401室");
//                            jdOrder.setZip("");
//                            jdOrder.setPhone("");
//                            jdOrder.setMobile("18566286811");
//                            jdOrder.setEmail("262347503@qq.com");
//                            jdOrder.setRemark("");
//                            jdOrder.setInvoiceState(2);
//                            jdOrder.setInvoiceType(1);
//                            jdOrder.setSelectedInvoiceTitle(4);
//                            jdOrder.setCompanyName("");
//                            jdOrder.setInvoiceContent(1);
//                            jdOrder.setPaymentType(101);
//                            jdOrder.setIsUseBalance(0);
//                            jdOrder.setSubmitState(0);
//                            jdOrder.setInvoiceName("");
//                            jdOrder.setInvoicePhone("");
//                            jdOrder.setInvoiceProvice(19);
//                            jdOrder.setInvoiceCounty(3639);
//                            jdOrder.setInvoiceCity(1607);
//                            jdOrder.setInvoiceAddress("");
//                            jdOrder.setDoOrderPriceMode(1);
//                            jdOrder.setOrderPriceSnap("[ {\"price\":12, \"skuId\":639585} ]");
//                            jdOrder.setReservingDate(-1);
//                            jdOrder.setInstallDate(-1);
//                            jdOrder.setNeedInstall(false);
//                            jdOrder.setPromiseDate("");
//                            jdOrder.setPromiseTimeRange("");
//                            jdOrder.setPromiseTimeRangeCode(0);
//                            
//                            JDApiResult<Map> res = JdApi.submitOrder(jdOrder);
//                            String jdid = res.getResult().get("jdOrderId").toString();
//                            if(JdApi.confirmOrder(jdOrder.getToken(), jdid).isSuccess()){
//                                log.info("京东下单成功，订单号：" + jdid);
//                            }                             
                            stack.put("msg", "感谢您，订单支付成功！订单号：" + order.getThirdorderid());
                        }else{
                            stack.put("msg", "抱歉，订单支付失败！原因：" + orderPayResult.getMessage());
                        }
                    }
                }else{
                    log.error("签名错误！");
                }
            }            
        } catch (Exception e) {
            log.warn("一卡通页面跳转同步通知接口异常：" + e.getMessage());
        }
        
        ServiceResult<List<Product>> resultProduct = productFrontService.getProductsListCart(0, 10);
        stack.put("products", resultProduct.getResult());
        
        return "front/order/linepay";
    }
    
    @RequestMapping(value = "/eCardPayCreateOrder.html", method = RequestMethod.POST)
    public String eCardPayCreateOrder(HttpServletRequest request, HttpServletResponse response, Map<String, Object> dataMap) {
        log.info("调用一卡通下单付款接口");
        log.info(WebUtil.getRequestParameters(request));
        try {
            // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
            request.setCharacterEncoding(SlnConfig.ENCODE);
            response.setCharacterEncoding(SlnConfig.ENCODE);
            
            Order order = new Order();
            //order.setAccount("8572");
            //order.setSno("144105");
            order.setAccount("");
            order.setSno("");
            order.setOrderdesc("海核云谷商城订单");
            order.setOrdertype(SlnConfig.ECARDPAY_ORDERTYPE_PC);
            order.setPraram1("商城订单");
            order.setThirdorderid(TimeUtil.getCurrentTime());
            order.setThirdurl(DomainUrlUtil.ECARDPAY_PC_RETURN_URL);
            order.setToaccount("1000000");
            order.setTranamt("1");
            
            SortedMap<String, String> sort = new TreeMap<String, String>();
            sort.put("tranamt", order.getTranamt());//交易金额(分)
            sort.put("account", order.getAccount());//员工账号 
            sort.put("sno", order.getSno());//员工工号
            sort.put("toaccount", order.getToaccount());//商户账号
            sort.put("thirdsystem", SlnConfig.ECARDPAY_THIRDSYSTEM);//系统注册ID
            sort.put("thirdorderid", order.getThirdorderid());//第三方订单号
            sort.put("ordertype", order.getOrdertype());//接入类型
            sort.put("orderdesc", order.getOrderdesc());//商品名称
            sort.put("praram1", order.getPraram1());//第三方系统备注字段        
            String sign = ECardPay.sign(sort);//生成签名字符串
            sort.put("sign", URLEncoder.encode(sign, SlnConfig.ENCODE));//对签名进行URL编码
            sort.put("thirdurl", order.getThirdurl());//跳转URL
            
            //生成自动跳转的Html表单
            String sHtmlText = SDKUtil.createAutoFormHtml(DomainUrlUtil.ECARDPAY_CREATEORDER_URL, sort,SDKUtil.encoding_UTF8); 
            dataMap.put("paytext", sHtmlText);
            return "front/order/ecardpay";
        } catch (Exception e) {
            log.error("一卡通支付出现异常:" + e.getMessage());
            dataMap.put("info", "支付异常，请到我的订单重新支付，谢谢！");
            return "front/commons/error";
        }
    }
    
    @RequestMapping(value = "/slnResult.html", method = { RequestMethod.GET, RequestMethod.POST })
    public void slnResult(HttpServletRequest request, HttpServletResponse response) {
        log.info("调用海核云谷退款接口");
        log.info(WebUtil.getRequestParameters(request));
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        try {
            request.setCharacterEncoding(SlnConfig.ENCODE);
        } catch (UnsupportedEncodingException e) {
            log.warn(e.getMessage());
        }
        response.setCharacterEncoding(SlnConfig.ENCODE);
                
        SortedMap<String, String> sort = new TreeMap<String, String>();
        sort.put("productbacksn", RandomUtil.getSupplierReturnSn());
        sort.put("orderid", RandomUtil.getOrderSn());
        sort.put("tranamt", "1");
        //sort.put("state","1");
        //sort.put("reforderid", TimeUtil.getCurrentTime());
        
        StringBuffer para = ECardPay.jointPara(sort);//对参数进行拼接、加密和签名
        try {
            String result = HttpClientUtil.sendJsonPost("http://120.78.180.131:8807/pay/eCardPayRefund.html", para.toString());
            response.getWriter().println(result);
        } catch (Exception e) {
            log.warn("一卡通接口异常：" + e.getMessage());
            try {
                response.sendRedirect("front/order/linepay");
            } catch (IOException e1) {
                log.warn("一卡通接口异常：" + e1.getMessage());
            }
        }
    }
}
