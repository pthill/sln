package com.sln.service.sms;

import java.util.Map;

import com.sln.core.ServiceResult;

/**
 * 消息发送统一接口
 *                       
 * @Filename: ISenderService.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihailong@javamalls.com
 *
 */
public interface ISenderService {

    /**
     * 发送短信,此方法不会返回短信发送结果
     * @param map
     * @return
     */
    ServiceResult<Boolean> sendSMS(Map<String, Object> map);

    /**
     * 发送模板短信,返回发送结果
     * @param map
     * @return
     */
    ServiceResult<Object> sendSMSWidthCallable(Map<String, Object> map);

    /**
     * 发送验证码,并将结果返回
     * @param mobile
     * @return
     */
    ServiceResult<Object> sendVerifyCode(String mobile);

    /**
     * 忘记密码发送验证码,并将结果返回
     * @param mobile
     * @return
     */
    ServiceResult<Object> sendForGetPassWordCode(String mobile);
    /**
     * 发送绑定手机验证码,并将结果返回
     * @param mobile
     * @return
     */
    ServiceResult<Object> sendBindingCode(String mobile);
    
    
    /**
     * 发送重置密码短信
     * @param mobile
     * @return
     */
    ServiceResult<Object> sendResetPwd(String mobile,String newPwd);
    
    
}
