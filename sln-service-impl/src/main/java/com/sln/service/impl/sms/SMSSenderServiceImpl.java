package com.sln.service.impl.sms;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.RandomUtil;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.core.sms.AlidayuSms;
import com.sln.core.sms.bean.VerifyCodeResult;
import com.sln.model.sms.SMSSendModel;
import com.sln.service.impl.order.AdminComplaintServiceImpl;
import com.sln.service.sms.ISenderService;

/**
 * 短信发送服务
 *                       
 * @Filename: SMSSender.java
 * @Version: 1.0
 * @Author: zhaihl
 * @Email: zhaihailong@javamalls.com
 *
 */
@Service(value = "senderService")
public class SMSSenderServiceImpl implements ISenderService {
    private static Logger log = LogManager.getLogger(AdminComplaintServiceImpl.class);
    @Resource(name = "SMSSendModel")
    private SMSSendModel  SMSSendModel;

    @Override
    public ServiceResult<Boolean> sendSMS(Map<String, Object> map) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            SMSSendModel.sendSMS(map);
            result.setResult(Boolean.TRUE);
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setSuccess(false);
                result.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                log.error("短信发送出现未知异常：" + e);
                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
                result.setMessage("短信发送出现未知异常");
            }
        }
        return result;
    }

    @Override
    public ServiceResult<Object> sendSMSWidthCallable(Map<String, Object> map) {
        ServiceResult<Object> result = new ServiceResult<Object>();
        try {
            result.setResult(SMSSendModel.sendSMSWidthCallable(map));
        } catch (Exception e) {
            if (e instanceof BusinessException) {
                result.setSuccess(false);
                result.setMessage(e.getMessage());
            } else {
                e.printStackTrace();
                log.error("短信发送出现未知异常：" + e);
                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
                result.setMessage("短信发送出现未知异常");
            }
        }
        return result;
    }

//    @Override
//    public ServiceResult<Object> sendVerifyCode(String mobile) {
//        ServiceResult<Object> result = new ServiceResult<Object>();
//        try {
//            Map<String, Object> param = new HashMap<String, Object>();
//            param.put("mobile", mobile);
//            result.setResult(SMSSendModel.sendVerifyCode(param));
//        } catch (Exception e) {
//            if (e instanceof BusinessException) {
//                result.setSuccess(false);
//                result.setMessage(e.getMessage());
//                log.debug(e.getMessage());
//            } else {
//                e.printStackTrace();
//                log.error("短信发送出现未知异常：" + e);
//                result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
//                result.setMessage("短信发送出现未知异常");
//            }
//        }
//        return result;
//    }
    
    public ServiceResult<Object> sendVerifyCode(String mobile) {
        ServiceResult<Object> result = new ServiceResult<Object>();
        // 验证码
        String verifyCode = RandomUtil.randomNumber(6);
        // 获取短信发送状态
        boolean isSend = AlidayuSms.sendVerifyCode(mobile, verifyCode);
        if(isSend) {
            VerifyCodeResult vc = new VerifyCodeResult();
            vc.setVerifyCode(verifyCode);
            result.setResult(vc);
        }else{
            result.setSuccess(false);
            result.setMessage("短信发送失败！");
        }
        return result;
    }

    @Override
    public ServiceResult<Object> sendForGetPassWordCode(String mobile) {
        ServiceResult<Object> result = new ServiceResult<Object>();
        // 验证码
        String code = RandomUtil.randomNumber(6);
        result.setResult(false);
        boolean isSend = AlidayuSms.sendForgetPassword(mobile, code);
        if(isSend) {
            VerifyCodeResult vc = new VerifyCodeResult();
            vc.setVerifyCode(code);
            result.setResult(vc);
        }else{
            result.setSuccess(false);
            result.setMessage("短信发送失败");
        }
        return result;
    }

    @Override
	public ServiceResult<Object> sendResetPwd(String mobile,String newPwd) {
		ServiceResult<Object> result = new ServiceResult<Object>();
		result.setResult(false);
		boolean  isSend = AlidayuSms.sendResetPwd(mobile,newPwd);
		if(isSend){
			result.setSuccess(true);
		}
		return result;
	}

	@Override
	public ServiceResult<Object> sendBindingCode(String mobile) {
		ServiceResult<Object> result = new ServiceResult<Object>();
        // 验证码
        String verifyCode = RandomUtil.randomNumber(6);
        // 获取短信发送状态
        boolean isSend = AlidayuSms.sendBindingCode(mobile, verifyCode);
        if(isSend) {
            VerifyCodeResult vc = new VerifyCodeResult();
            vc.setVerifyCode(verifyCode);
            result.setResult(vc);
        }else{
            result.setSuccess(false);
            result.setMessage("短信发送失败！");
        }
        return result;
	}
}
