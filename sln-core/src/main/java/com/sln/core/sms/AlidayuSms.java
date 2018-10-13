package com.sln.core.sms;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.json.JSONObject;

import com.sln.core.RandomUtil;
import com.sln.core.SlnConfig;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里大于短信服务接口
 *                       
 * @Filename: AlidayuSms.java
 * @Version: 1.0
 * @Author: zhangmin
 * @Email: manction@qq.com
 *
 */
public class AlidayuSms {

    protected static Logger log = LogManager.getLogger(AlidayuSms.class);
    
    /**
     * 发送短信验证码
     * @param mobile 短信接收号码，多个号码以英文逗号分隔
     * @param verifyCode 验证码
     * @return
     */
    public static boolean sendVerifyCode(String mobile, String verifyCode){
        return AlidayuSms.sendSms(mobile, verifyCode, SlnConfig.ALIDAYU_SMS_USER_REGISTER_TEMPLATECODE);
    }
    
    /**
     * 发送绑定手机验证码
     * @param mobile
     * @param verifyCode
     * @return
     */
    public static boolean sendBindingCode(String mobile, String verifyCode){
    	return AlidayuSms.sendSms(mobile, verifyCode, SlnConfig.ALIDAYU_SMS_RESET_BINDING_TEMPLATECODE);
    }
    
    /**
     * 发送重置密码
     * @param mobile 短信接收号码，多个号码以英文逗号分隔
     * @param pwd 重置密码
     * @return
     */
    public static boolean sendResetPwd(String mobile, String pwd){
        return AlidayuSms.sendSms(mobile, pwd, SlnConfig.ALIDAYU_SMS_RESET_PASSWORD_TEMPLATECODE);
    }

    /**
     * 发送忘记密码验证码
     * @param mobile 短信接收号码，多个号码以英文逗号分隔
     * @param code 验证码
     * @return
     */
    public static boolean sendForgetPassword(String mobile,String code){
        return AlidayuSms.sendForGetSms(mobile,code,SlnConfig.ALIDAYU_SMS_FORGET_PASSWORD_TEMPLATECODE);
    }


    /**
     * 发送短信
     * @param mobile 短信接收号码，多个号码以英文逗号分隔
     * @param content 短信内容
     * @param smsTemplateCode 短信模板ID
     * @return
     */
    public static boolean sendSms(String mobile, String content, String smsTemplateCode){
        //实例化淘宝接口客户端
        TaobaoClient client = new DefaultTaobaoClient(DomainUrlUtil.ALIDAYU_SMS_URL, SlnConfig.ALIDAYU_SMS_APPKEY, SlnConfig.ALIDAYU_SMS_SECRET);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("hhyg");//公共回传参数
        request.setSmsType("normal");//短信类型
        request.setSmsFreeSignName(SlnConfig.ALIDAYU_SMS_SIGNNAME);//短信签名
        request.setSmsParamString("{\"code\":\""+content+"\",\"product\":\"" + SlnConfig.ALIDAYU_SMS_SIGNNAME +"\"}");//短信模板变量
        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setSmsTemplateCode(smsTemplateCode);//短信模板ID
        AlibabaAliqinFcSmsNumSendResponse response;
        boolean result = false;
        log.info("AlidayuSms.sendSms mobile:" + mobile + " content:" + content);
        try {
            //响应结果：{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"220111312714522162^0","msg":"OK","success":true},"request_id":"ztau3fbluo8s"}}
            //异常结果：{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.SMS_SIGNATURE_ILLEGAL","sub_msg":"短信签名不合法","request_id":"eonhgml538q8"}}
            response = client.execute(request);
            String responseMsg = response.getBody();
            log.info(responseMsg);//记录接口信息到日志
            
            String sendResponse = "alibaba_aliqin_fc_sms_num_send_response";//响应结果
            
            if(responseMsg.contains(sendResponse)){
                boolean isSucceed = new JSONObject(responseMsg).getJSONObject(sendResponse).getJSONObject("result").getBoolean("success");//处理状态
                if(isSucceed){
                    result = true;
                    log.info("短信发送成功！");
                }else{
                    log.warn("短信发送失败！");
                }
            }else{
                log.warn("短信发送异常！");
            }
        } catch (ApiException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public static boolean sendForGetSms(String mobile, String content, String smsTemplateCode){
        //实例化淘宝接口客户端
        TaobaoClient client = new DefaultTaobaoClient(DomainUrlUtil.ALIDAYU_SMS_URL, SlnConfig.ALIDAYU_SMS_APPKEY, SlnConfig.ALIDAYU_SMS_SECRET);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("hhyg");//公共回传参数
        request.setSmsType("normal");//短信类型
        request.setSmsFreeSignName(SlnConfig.ALIDAYU_SMS_SIGNNAME);//短信签名
        request.setSmsParamString("{\"code\":\""+content+"\"}");//短信模板变量
        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setSmsTemplateCode(smsTemplateCode);//短信模板ID
        AlibabaAliqinFcSmsNumSendResponse response;
        boolean result = false;
        log.info("AlidayuSms.sendSms mobile:" + mobile + " content:" + content);
        try {
            //响应结果：{"alibaba_aliqin_fc_sms_num_send_response":{"result":{"err_code":"0","model":"220111312714522162^0","msg":"OK","success":true},"request_id":"ztau3fbluo8s"}}
            //异常结果：{"error_response":{"code":15,"msg":"Remote service error","sub_code":"isv.SMS_SIGNATURE_ILLEGAL","sub_msg":"短信签名不合法","request_id":"eonhgml538q8"}}
            response = client.execute(request);
            String responseMsg = response.getBody();
            log.info(responseMsg);//记录接口信息到日志

            String sendResponse = "alibaba_aliqin_fc_sms_num_send_response";//响应结果

            if(responseMsg.contains(sendResponse)){
                boolean isSucceed = new JSONObject(responseMsg).getJSONObject(sendResponse).getJSONObject("result").getBoolean("success");//处理状态
                if(isSucceed){
                    result = true;
                    log.info("短信发送成功");
                }else{
                    log.warn("短信发送失败");
                }
            }else{
                log.warn("短信发送异常");
            }
        } catch (ApiException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 福利积分发送短信
     * @param mobile 短信接收号码，多个号码以英文逗号分隔
     * @param smsTemplateCode 短信模板ID
     * @return
     */
    public static boolean sendOLDWelfareSend(String mobile,String costName,String money,String smsTemplateCode){
        //实例化淘宝接口客户端
        TaobaoClient client = new DefaultTaobaoClient(DomainUrlUtil.ALIDAYU_SMS_URL, SlnConfig.ALIDAYU_SMS_APPKEY, SlnConfig.ALIDAYU_SMS_SECRET);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("hhyg");//公共回传参数
        request.setSmsType("normal");//短信类型
        request.setSmsFreeSignName(SlnConfig.ALIDAYU_SMS_SIGNNAME);//短信签名
        request.setSmsParamString("{\"costName\":\""+costName+"\",\"money\":\"" + money+"\"}");//短信模板变量

        //短信模板变量
        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setSmsTemplateCode(smsTemplateCode);//短信模板ID
        AlibabaAliqinFcSmsNumSendResponse response;
        boolean result = false;
        try {
            response = client.execute(request);
            String responseMsg = response.getBody();
            log.info(responseMsg);//记录接口信息到日志
            String sendResponse = "alibaba_aliqin_fc_sms_num_send_response";//响应结果
            if(responseMsg.contains(sendResponse)){
                boolean isSucceed = new JSONObject(responseMsg).getJSONObject(sendResponse).getJSONObject("result").getBoolean("success");//处理状态
                if(isSucceed){
                    result = true;
                    log.info("短信发送成功");
                }else{
                    log.warn("短信发送失败");
                }
            }else{
                log.warn("短信发送异常！");
            }
        } catch (ApiException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    public static boolean sendNEWWelfareSend(String mobile,String costName,String money,String username,String password,String paypass,String smsTemplateCode){
        //实例化淘宝接口客户端
        TaobaoClient client = new DefaultTaobaoClient(DomainUrlUtil.ALIDAYU_SMS_URL, SlnConfig.ALIDAYU_SMS_APPKEY, SlnConfig.ALIDAYU_SMS_SECRET);
        AlibabaAliqinFcSmsNumSendRequest request = new AlibabaAliqinFcSmsNumSendRequest();
        request.setExtend("hhyg");//公共回传参数
        request.setSmsType("normal");//短信类型
        request.setSmsFreeSignName(SlnConfig.ALIDAYU_SMS_SIGNNAME);//短信签名
        request.setSmsParamString("{\"costName\":\""+costName+"\",\"money\":\"" + money +"\","
                                  + "\"username\":\"" + username +"\",\"password\":\""+password+"\","+"\"paypass\":\""+paypass+"\"}");
        //短信模板变量
        request.setRecNum(mobile);//短信接收号码。支持单个或多个手机号码，传入号码为11位手机号码，不能加0或+86。群发短信需传入多个号码，以英文逗号分隔，一次调用最多传入200个号码。
        request.setSmsTemplateCode(smsTemplateCode);//短信模板ID
        AlibabaAliqinFcSmsNumSendResponse response;
        boolean result = false;
        try {
            response = client.execute(request);
            String responseMsg = response.getBody();
            log.info(responseMsg);//记录接口信息到日志
            String sendResponse = "alibaba_aliqin_fc_sms_num_send_response";//响应结果
            if(responseMsg.contains(sendResponse)){
                boolean isSucceed = new JSONObject(responseMsg).getJSONObject(sendResponse).getJSONObject("result").getBoolean("success");//处理状态
                if(isSucceed){
                    result = true;
                    log.info("短信发送成功");
                }else{
                    log.warn("短信发送失败！");
                }
            }else{
                log.warn("短信发送异常！");
            }
        } catch (ApiException e) {
            log.warn(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    public static void main(String[] args) {
        // 加载日志资源
        //PropertyConfigurator.configure("E:/workspace/test/conf/log4j.properties");
        AlidayuSms.sendVerifyCode("15972014239,13148813460", RandomUtil.randomNumber(4));
        //AlidayuSms.sendResetPwd("13510529052,13148813460", RandomUtil.randomNumber(6));
    }
}
