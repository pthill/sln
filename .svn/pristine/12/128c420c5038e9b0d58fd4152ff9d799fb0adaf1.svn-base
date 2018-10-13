package com.sln.core.pay;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.sln.core.HttpClientUtil;
import com.sln.core.JsonUtil;
import com.sln.core.SlnConfig;
import com.sln.core.pay.bean.Order;
import com.sln.core.pay.util.TripleDES;

/**
 * 一卡通支付接口
 *                       
 * @Filename: ECardPay.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
public class ECardPay {
    
    /**
     * 日志对象
     */
    private static Logger log = Logger.getLogger(ECardPay.class);
    
    public static final String ORDER_DESC_PC = "海核云谷商城订单";
    public static final String ORDER_DESC_PHONE = "海核云谷手机订单";
    public static final String ORDER_DESC_BALANCE = "海核云谷用户余额充值";
    
    public static final String ORDER_TYPE_PC = "1";  //商城订单
    public static final String ORDER_TYPE_PHONE = "2"; //手机订单
    public static final String ORDER_TYPE_BALANCE = "3"; //余额充值
    public static final String ORDER_TYPE_JD = "4";  //京东订单
    public static final String ORDER_TYPE_DANGDANG = "5";    // 当当订单
    
    /**
     * 统一下单接口
     * @param order
     * @return
     */
    public static SortedMap<String, String> createOrder(Order order){
        log.info("调用一卡通统一下单接口");
        SortedMap<String, String> sort = new TreeMap<String, String>();
        try {
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
            log.info(ECardPay.jointPara(sort).toString());
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return sort;
    }
    
    /**
     * 单笔订单信息查询接口
     * @param orderQuery
     * @return 订单信息列表
     */
    public static List<Order> orderQuery(Order queryOrder) {
        log.info("调用一卡通单笔订单信息查询接口");
        List<Order> list = null;
        SortedMap<String, String> sort = new TreeMap<String, String>(); //按照键的升序排列
        sort.put("thirdorderid", queryOrder.getThirdorderid());         //接入方生成的订单号，可空
        sort.put("orderid", queryOrder.getOrderid());   //支付平台订单号，不可空
        sort.put("thirdsystem", SlnConfig.ECARDPAY_THIRDSYSTEM);                           //系统注册 ID
        
        StringBuffer para = jointPara(sort);//对参数进行拼接、加密和签名
        para.append("&state=" + queryOrder.getQuerystate());//状态
        
        String result;
        try {
            //result = HttpClientUtil.sendJsonPost(DomainUrlUtil.ECARDPAY_ORDERQUERY_URL, para.toString());
            result = HttpClientUtil.sendJsonPost("http://online.cgnpc.com.cn:10001/Order/OrderQuery", para.toString());
            log.info(result);
        } catch (Exception e) {
            log.warn(e.getMessage());
            return null;
        }
        
        //{"IsSucceed":false,"msg":[],"prompt":"签名错误"}
        //{"IsSucceed":true, "msg":[ "account=297&thirdorderid=2017030111124379297&toaccount=1000000&tranamt=100&actulamt=100&orderid=2017030111124411297&orderdesc=&praram1=&rzdate=2017-03-01 11:12:44&jydate=2017-03-01 11:12:44&state=1&thirdsystem=mobileby", "account=1491&thirdorderid=2017030111124379297&toaccount=1000013&tranamt=12&actulamt=12&orderid=20160721112654601491&orderdesc=&praram1=&rzdate=2016-07-21 11:26:54&jydate=2016-07-21 11:26:54&state=1&thirdsystem=mobileby" ], "prompt":"" }
        //{"IsSucceed":true,"msg":["account=8572\u0026thirdorderid=20171219153826\u0026toaccount=1000000\u0026tranamt=1\u0026actulamt=1\u0026orderid=P2017121915382754000034\u0026orderdesc=海核云谷下单测试\u0026praram1=海核云谷PC端订单\u0026rzdate=2017-12-19 15:42:31\u0026jydate=2017-12-19 15:42:31\u0026state=1\u0026thirdsystem=mobileby\u0026sno=144105\u0026payname=一卡通\u0026name=陈建强"],"prompt":""}
        
        //处理结果
        JSONObject jo = JSONObject.fromObject(result);
        boolean isSucceed = jo.getBoolean("IsSucceed");//处理状态
        String prompt = jo.getString("prompt");//提示信息
        if (!isSucceed) {
            log.warn("获取订单信息失败！原因：" + prompt);
        } else {
            JSONArray jsonArr = jo.getJSONArray("msg");//订单信息
            if (jsonArr.size() > 0) {
                list = new ArrayList<>();
                
                for (int i = 0; i < jsonArr.size(); i++) {
                    JSONObject msg = JsonUtil.urlToJson(jsonArr.getString(i));
                    Order order = new Order();
                    order.setTranamt(msg.getString("tranamt"));//交易金额(分)
                    order.setActulamt(msg.getString("actulamt"));//实际交易金额(分)
                    order.setState(msg.getString("state"));//交易状态( 1 成功 0待支付 2取消)
                    order.setAccount(msg.getString("account"));//员工账号 
                    order.setToaccount(msg.getString("toaccount"));//商户账号
                    order.setThirdsystem(msg.getString("thirdsystem"));//系统注册ID
                    order.setThirdorderid(msg.getString("thirdorderid"));//第三方订单号
                    order.setOrderdesc(msg.getString("orderdesc"));//商品名称
                    order.setPraram1(msg.getString("praram1"));//第三方系统备注字段
                    order.setRzdate(Timestamp.valueOf(msg.getString("rzdate")));//支付平台入账时间
                    order.setJydate(Timestamp.valueOf(msg.getString("jydate"))); //交易日期
                    list.add(order);
                    log.info("获取订单信息：" + msg.toString());
                }                
            } else {
                log.info("获取订单信息：" + prompt);
            }
        }
        return list;
    }
    
    /**
     * 退款接口
     * @param refOrder
     * @return 支付平台返回退款的订单号
     * @throws Exception
     */
    public static String refOrderMoney(Order refOrder) throws Exception{
        log.info("调用一卡通退款接口");
        String reforderid = null;
        SortedMap<String, String> sort = new TreeMap<String, String>(); //按照键的升序排列
        sort.put("outrefundorderid", refOrder.getOutrefundorderid()); //商户退款的订单号码
        sort.put("orderid", refOrder.getOrderid());//支付平台订单号，支付平台支付成功以后的订单号 
        sort.put("tranamt", refOrder.getTranamt());//金额(分)
        
        StringBuffer para = jointPara(sort);//对参数进行拼接、加密和签名
        para.append("&reftoaccount=" + refOrder.getToaccount());//退款商户号，无需签名，单商户下单可为空，订单是多商户需要传入退款商户
        
        //String result = HttpClientUtil.sendJsonPost(DomainUrlUtil.ECARDPAY_REFORDERMONEY_URL, para.toString());
        String result = HttpClientUtil.sendJsonPost("http://online.cgnpc.com.cn:10001/Order/RefOrderMoney", para.toString());
        result = URLDecoder.decode(result, SlnConfig.ENCODE);//解码
        log.info(result);
        
        //{"IsSucceed":false,"msg":"","prompt":"订单不存在无法进行退款","promptcode":"D0004"}
        //{"IsSucceed":false,"msg":"","prompt":"申请失败","promptcode":"D0008"}
        //{"IsSucceed":true,"msg":{"REFORDERID":"ref2018030916592551000003"},"prompt":"申请成功","promptcode":"D0009"}
        //System.out.println(para.toString());
        
        //处理结果
        JSONObject jo = JSONObject.fromObject(result);
        boolean isSucceed = jo.getBoolean("IsSucceed");//处理状态
        if(!isSucceed){
            String prompt = jo.getString("prompt");//提示信息
            String promptcode = jo.getString("promptcode");
            log.warn("退款失败！原因：" + prompt + " 提示代码:" + promptcode);
        }else{
            JSONObject msg = jo.getJSONObject("msg");
            reforderid = msg.getString("REFORDERID");//支付平台返回退款的订单号
            log.info("支付平台返回退款的订单号:" + reforderid);
        }
        return reforderid;
    }
    
    /**
     * 结算对账
     * 通过一卡通提供的FTP获取对账文件
     * 文件命名：系统注册ID-当前日期.txt
     * 文件内容样例：
     * #account=账号&thirdorderid=第三方订单号&toaccount=商户号&tranamt=金额(分)&orderid=支付平台订单号&reforderid=支付平台退款订单号&opertype=交易类型(详细见文档)&orderdesc=商品名称&praram1=第三方备注字段&sno=学工号&actulamt=实际交易金额(分)&state=交易状态&rzdate=支付平台入账日期&jydate=交易日期&thirdsystem=系统注册方&sign=签名数据^
     * #totalcount=总行数&totalmoney=总金额^
     */
    public static void getBill(){
        log.info("调用一卡通结算对账接口");
    }
    
    /**
     * 对参数进行拼接、加密和签名
     * @param sort
     * @return
     */
    public static StringBuffer jointPara(SortedMap<String, String> sort) {

        //输入验证
        if (null == sort || sort.size() == 0) {
            return null;
        }

        StringBuffer para = new StringBuffer();

        //拼接需要加密的参数
        for (Entry<String, String> entry : sort.entrySet()) {
            para.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        String jsonStr = para.deleteCharAt(para.length() - 1).toString();//删除最后一个&符号

        //3DES加密
        String requestDecrypt = TripleDES.encrypt(jsonStr);
        //System.out.println("3DES加密结果:" + requestDecrypt);
        //签名
        String sign = TripleDES.Sign(requestDecrypt);
        //System.out.println("RSA签名结果:" + sign);
        
        try {
            para.append("&sign=" + URLEncoder.encode(sign, SlnConfig.ENCODE));//URL编码
        } catch (UnsupportedEncodingException e) {
            para.append("&sign=" + sign);
        }
        
        return para;
    }
    
    /**
     * 加密和签名
     * @param sort
     * @return
     */
    public static String sign(SortedMap<String, String> sort) {

        //输入验证
        if (null == sort || sort.size() == 0) {
            return null;
        }

        StringBuffer para = new StringBuffer();

        //拼接需要加密的参数
        for (Entry<String, String> entry : sort.entrySet()) {
            para.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        String jsonStr = para.deleteCharAt(para.length() - 1).toString();//删除最后一个&符号

        //3DES加密
        String requestDecrypt = TripleDES.encrypt(jsonStr);
        //System.out.println("3DES加密结果:" + requestDecrypt);
        //签名
        String sign = TripleDES.Sign(requestDecrypt);
        //System.out.println("RSA签名结果:" + sign);
        
//        try {
//            //对签名进行URL编码
//            sign = URLEncoder.encode(sign, SlnConfig.ENCODE);
//        } catch (UnsupportedEncodingException e) {
//            log.warn(e.getMessage());
//        }
        
        return sign;
    }
    
    /**
     * 单商户查询接口
     * @param mercacc 商户账号
     * @return
     */
    public static boolean MercaccQuery(String mercacc){
        log.info("调用一卡通单商户查询接口");
        boolean isMercacc = false;
        SortedMap<String, String> sort = new TreeMap<String, String>(); //按照键的升序排列
        sort.put("mercacc", mercacc);         //商户账号
        sort.put("thirdsystem", SlnConfig.ECARDPAY_THIRDSYSTEM);                           //系统注册 ID
        
        StringBuffer para = jointPara(sort);//对参数进行拼接、加密和签名
        
        String result;
        try {
            String url = "http://online.cgnpc.com.cn:10001/Order/MercaccQuery";
            result = HttpClientUtil.sendJsonPost(url, para.toString());
            log.info(result);
            //{"IsSucceed":true,"msg":{"IsSucessed":true,"mercacc":"1000000"},"prompt":"数据成功"}
            //{"IsSucceed":false,"msg":[],"prompt":"无成功数据"}
            
            //处理结果
            JSONObject jo = JSONObject.fromObject(result);
            isMercacc = jo.getBoolean("IsSucceed");//处理状态
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return isMercacc;
    }
    
    public static String CreteteMerrQrcode(String toaccount, String timeout, String trjnnum, String thirdorderid){
        log.info("调用生成商户收款二维码接口");
        String qrcode = null;
        SortedMap<String, String> sort = new TreeMap<String, String>(); //按照键的升序排列
        sort.put("toaccount", toaccount);         //商户账号
        sort.put("thirdsystem", SlnConfig.ECARDPAY_THIRDSYSTEM);                           //系统注册 ID
        sort.put("timeout", timeout);         //有效时间，单位(秒)
        sort.put("trjnnum", trjnnum);         //交易金额，单位(分)
        sort.put("thirdorderid", thirdorderid);         //商户账号
        
        StringBuffer para = jointPara(sort);//对参数进行拼接、加密和签名
        
        String result;
        try {
            String url = "http://online.cgnpc.com.cn:10001/Order/CreteteMerrQrcode";
            result = HttpClientUtil.sendJsonPost(url, para.toString());
            log.info(result);
            //{"IsSucceed":false,"prompt":"未分配权限"}
            
            //处理结果
            JSONObject jo = JSONObject.fromObject(result);
            if (jo.getBoolean("IsSucceed")) {
                JSONObject obj = JSONObject.fromObject(jo.getString("obj"));
                qrcode = obj.getString("qrcode");
            }
        } catch (Exception e) {
            log.warn(e.getMessage());
        }
        return qrcode;
    }
    
    public static void main(String[] args) throws Exception {
        
        PropertyConfigurator.configure("E:/workspace/test/conf/log4j.properties"); 
        
        //2.单笔交易查询接口
//        Order query = new Order();
//        query.setOrderid("P2018030917285907000002"); //不可空
//        query.setQuerystate("all");;//all/success
//        query.setThirdorderid("18030917283263974208"); //可空
//        
//        try {
//            //单笔订单信息查询
//            ECardPay.orderQuery(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("单笔交易查询失败：" + e.getMessage());
//        }
        
        //一卡通退款接口
//        Order refOrder = new Order();
//        refOrder.setOutrefundorderid("18030917283263974208");
//        refOrder.setOrderid("P2018030917285907000002");
//        refOrder.setTranamt("1");
//        refOrder.setToaccount("1000000");
//        try {
//            //退款
//            ECardPay.refOrderMoney(refOrder);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("退款失败：" + e.getMessage());
//        }
        
//        SortedMap<String, String> sort = new TreeMap<String, String>();
//        sort.put("tranamt", "1");
//        sort.put("orderid", "P2017122222051983000048");
//        sort.put("account", "8572");
//        sort.put("sno", "144105");
//        sort.put("toaccount", "1000000");
//        sort.put("thirdsystem", "mobileby");
//        sort.put("thirdorderid", "17122221575382110358");
//        sort.put("state", "1");
//        sort.put("orderdesc", "海核云谷商城订单");
//        sort.put("praram1", "pc");
//        
//        String sign = ECardPay.sign(sort);//对参数进行拼接、加密和签名
//        System.out.println(URLDecoder.decode("I0vplSLoZQ1Z%2f2BOsaDerP2Xu9q%2bdx90yGq7ybuOA8apFMy8mI%2fEjHzErggYX9RS4HRI1S9EmxaRTN1go2Vb7ej11yIAPRWjtKqbF5HgCtJEQVgt7haoZjU5OvMUak4%2fpbSxiPsVstLfBCuNNgrZN7SnaYaCZne0TGBbH2Wuvno%3d",SlnConfig.ENCODE));
//        System.out.println(sign);
        //I0vplSLoZQ1Z%2F2BOsaDerP2Xu9q%2Bdx90yGq7ybuOA8apFMy8mI%2FEjHzErggYX9RS4HRI1S9EmxaRTN1go2Vb7ej11yIAPRWjtKqbF5HgCtJEQVgt7haoZjU5OvMUak4%2FpbSxiPsVstLfBCuNNgrZN7SnaYaCZne0TGBbH2Wuvno%3D
        //I0vplSLoZQ1Z%2f2BOsaDerP2Xu9q%2bdx90yGq7ybuOA8apFMy8mI%2fEjHzErggYX9RS4HRI1S9EmxaRTN1go2Vb7ej11yIAPRWjtKqbF5HgCtJEQVgt7haoZjU5OvMUak4%2fpbSxiPsVstLfBCuNNgrZN7SnaYaCZne0TGBbH2Wuvno%3d
        
        //8.单商户查询接口
//        try {
//            System.out.println(ECardPay.MercaccQuery("1000000"));
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("单商户查询失败：" + e.getMessage());
//        }
        
        //7.    生成商户二维码
//        try {
//            String toaccount = "1000000";
//            String timeout= "600";
//            String trjnnum= "1";
//            String thirdorderid= "18030917320597406938";
//            System.out.println(ECardPay.CreteteMerrQrcode(toaccount, timeout, trjnnum, thirdorderid));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        
//        SortedMap<String, String> sort = new TreeMap<String, String>();
//        sort.put("tranamt", "1");
//        sort.put("orderid", "P2018030917285907000002");
//        sort.put("account", "8572");
//        sort.put("sno", "144105");
//        sort.put("toaccount", "1000000");
//        sort.put("thirdsystem", "mobileby");
//        sort.put("thirdorderid", "18030917283263853204000901");
//        sort.put("state", "1");
//        sort.put("orderdesc", "海核云谷商城订单");
//        sort.put("praram1", "1");
//
//        String sign = ECardPay.sign(sort);//对参数进行拼接、加密和签名
//        sign = URLEncoder.encode(sign, SlnConfig.ENCODE);
//        System.out.println(sign);
        
    }
}
