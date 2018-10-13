package com.sln.service.impl.message;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageRecord;
import com.sln.model.message.MessageRecordModel;
import com.sln.service.message.IMessageRecordService;

@Service(value = "messageRecordService")
public class MessageRecordServiceImpl implements IMessageRecordService {
	private static Logger      log = LogManager.getLogger(MessageRecordServiceImpl.class);
	
	@Resource
	private MessageRecordModel messageRecordModel;
    
     /**
     * 根据id取得消息发送记录表
     * @param  messageRecordId
     * @return
     */
    @Override
    public ServiceResult<MessageRecord> getMessageRecordById(Integer messageRecordId) {
        ServiceResult<MessageRecord> result = new ServiceResult<MessageRecord>();
        try {
            result.setResult(messageRecordModel.getMessageRecordById(messageRecordId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageRecordService][getMessageRecordById]根据id["+messageRecordId+"]取得消息发送记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][getMessageRecordById]根据id["+messageRecordId+"]取得消息发送记录表时出现未知异常：",
                e);
        }
        return result;
    }
    

	@Override
	public ServiceResult<Boolean> sendMessage(Message message) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(messageRecordModel.sendMessage(message));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageRecordService][sendMessage]发送消息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][sendMessage]发送消息时出现未知异常：",
                e);
        }
        return result;
	}


	@Override
	public ServiceResult<List<MessageRecord>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<MessageRecord>> serviceResult = new ServiceResult<List<MessageRecord>>();
        try {
            if (pager != null) {
                pager.setRowsCount(messageRecordModel.count(queryMap));
                serviceResult.setPager(pager);
            }
            serviceResult.setResult(messageRecordModel.page(queryMap, pager));
        } catch (BusinessException e) {
        	serviceResult.setSuccess(false);
        	serviceResult.setMessage(e.getMessage());
            log.error("[IMessageRecordService][page]查询消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][page]查询消息记录表时出现未知异常：",
                e);
        }
        return serviceResult;
	}


	@Override
	public ServiceResult<Boolean> setRead(Integer id) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(messageRecordModel.setRead(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageRecordService][setRead]设置消息已读时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][setRead]设置消息已读时出现未知异常：",
                e);
        }
        return result;
	}


	@Override
	public ServiceResult<Boolean> delete(Integer id) {
		ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(messageRecordModel.delete(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageRecordService][delete]删除消息时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][delete]删除消息时出现未知异常：",
                e);
        }
        return result;
	}


	@Override
	public ServiceResult<Integer> getUnreadNumByMessageTypeId(Integer messageTypeId, Integer memberId) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(messageRecordModel.getUnreadNumByMessageTypeId(messageTypeId, memberId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageRecordService][getUnreadNumByMessageTypeId]获取未读消息数量时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageRecordService][getUnreadNumByMessageTypeId]获取未读消息数量时出现未知异常：",
                e);
        }
        return result;
	}
}