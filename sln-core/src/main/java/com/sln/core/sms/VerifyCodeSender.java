package com.sln.core.sms;

import java.util.HashMap;
import java.util.Map;

import com.sln.core.RandomUtil;
import com.sln.core.SlnConfig;

/**
 * 短信验证码发送服务
 *                       
 * @Filename: SMSSender.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihl_0@126.com
 *
 */
public class VerifyCodeSender extends AbstractSMSSender {
    private String mobile;

    public VerifyCodeSender(String mobile) {
        this.mobile = mobile;
        this.verifyCode = RandomUtil.randomNumber(6);
        this.content = SlnConfig.SMS_VERIFY_CODE.replace("@", this.verifyCode);
    }

    @Override
    protected Map<String, String> setSMSParamMap() {
        Map<String, String> param = new HashMap<String, String>();
        param.put("url", SlnConfig.SMS_URL);
        param.put("mobile", mobile);
        param.put("content", content);
        return param;
    }

}
