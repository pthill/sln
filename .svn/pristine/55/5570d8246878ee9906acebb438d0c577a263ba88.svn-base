package com.sln.core;

import java.lang.reflect.Type;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Json转换工具
 *                       
 * @Filename: JsonUtil.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public final class JsonUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.LogManager
        .getLogger(JsonUtil.class);

    /**
     * 将JSON字符串反序列化为Java对象。
     * @param json JSON字符串
     * @return
     * <li>json字符串为空时返回null；
     * <li>json字符串为无效JSON格式时，会记录日志，返回null；
     */
    public static final <T> T fromJson(String json) {
        if (StringUtil.isEmpty(json))
            return null;

        try {
            Type type = new TypeToken<T>() {
            }.getType();
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (Exception e) {
            log.warn("Invalidate json format:" + json, e);
            return null;
        }
    }

    /**
     * 将Java对象序列化成JSON字符串。
     * @param obj
     * @return
     */
    public static final String toJson(Object obj) {
        if (obj == null)
            return null;
        try {
            GsonBuilder gb = new GsonBuilder();
            gb.setDateFormat("yyyy-MM-dd HH:mm:ss");
            return gb.create().toJson(obj);
        } catch (Exception e) {
            log.warn("Can not serialize object to json", e);
            return null;
        }
    }
    
    /**
     * URL地址转为json格式
     * @param url URL地址
     * @return JSONObject
     * @Author: zhangmin
     * @Email: manction@qq.com
     */
    public static JSONObject urlToJson(String url) {
        // 判断是否为空
        if(StringUtil.isEmpty(url) || url.indexOf("&") == -1){
            return null;
        }
        
        // 截取URL参数
        if (url.indexOf("?") > -1) {
            url = url.substring(url.indexOf("?") + 1);
        } 
        
        JSONObject jo = new JSONObject();
        String[] para = url.split("&");
        for (String string : para) {
            String[] item = string.split("=");
            if(item.length == 2){
                jo.put(item[0], item[1]);
            }else{
                jo.put(item[0], "");
            }
        }        
        return jo;
    }
    
    public static void main(String[] args) {
        String url = "http://www.hhyg.com?account=2368&thirdorderid=20171219153826&toaccount=1000000&tranamt=1&actulamt=1&orderid=P2017121915382754000034&orderdesc=海核云谷下单测试&praram1=海核云谷PC端订单&rzdate=2017-12-19 15:42:31&jydate=2017-12-19 15:42:31&state=1&thirdsystem=mobileby&sno=144105&payname=一卡通&name=陈建强";
        System.out.println(JsonUtil.urlToJson(url));
    }
}