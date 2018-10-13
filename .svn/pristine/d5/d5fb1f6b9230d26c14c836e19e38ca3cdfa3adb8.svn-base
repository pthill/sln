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
import com.sln.entity.member.Member;
import com.sln.entity.message.Message;
import com.sln.model.message.MessageModel;
import com.sln.service.message.IMessageService;

@Service(value = "messageService")
public class MessageServiceImpl implements IMessageService {
	private static Logger      log = LogManager.getLogger(MessageServiceImpl.class);
	
	@Resource
	private MessageModel messageModel;
    
     /**
     * 根据id取得消息记录表
     * @param  messageId
     * @return
     */
    @Override
    public ServiceResult<Message> getMessageById(Integer messageId) {
        ServiceResult<Message> result = new ServiceResult<Message>();
        try {
            result.setResult(messageModel.getMessageById(messageId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageService][getMessageById]根据id["+messageId+"]取得消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][getMessageById]根据id["+messageId+"]取得消息记录表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 根据messageCode取得消息记录表
     * @param  messageCode
     * @return
     */
    @Override
    public ServiceResult<Message> getMessageByMessageCode(String messageCode) {
        ServiceResult<Message> result = new ServiceResult<Message>();
        try {
            result.setResult(messageModel.getMessageByMessageCode(messageCode));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageService][getMessageByMessageCode]根据messageCode["+messageCode+"]取得消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][getMessageByMessageCode]根据messageCode["+messageCode+"]取得消息记录表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存消息记录表
     * @param  message
     * @return
     */
     @Override
     public ServiceResult<Integer> saveMessage(Message message) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(messageModel.saveMessage(message));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageService][saveMessage]保存消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][saveMessage]保存消息记录表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新消息记录表
     * @param  message
     * @return
     * @see com.slooong.sln.service.MessageService#updateMessage(Message)
     */
     @Override
     public ServiceResult<Integer> updateMessage(Message message) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(messageModel.updateMessage(message));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageService][updateMessage]更新消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][updateMessage]更新消息记录表时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<Message>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<Message>> serviceResult = new ServiceResult<List<Message>>();
        try {
            if (pager != null) {
                pager.setRowsCount(messageModel.count(queryMap));
                serviceResult.setPager(pager);
            }
            serviceResult.setResult(messageModel.page(queryMap, pager));
        } catch (BusinessException e) {
        	serviceResult.setSuccess(false);
        	serviceResult.setMessage(e.getMessage());
            log.error("[IMessageService][page]查询消息记录表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][page]查询消息记录表时出现未知异常：",
                e);
        }
        return serviceResult;
	}
		
	@Override
	public ServiceResult<Boolean> del(Integer id) {
	        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
	        try {
	            serviceResult.setResult(messageModel.del(id) > 0);
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            e.printStackTrace();
	            log.error("[IMessageService][del] exception:" + e.getMessage());
	        }
	        return serviceResult;
	}
	  
	@Override
	public ServiceResult<Boolean> sendMessageToMessage(Map<String, Object> queryMap, Integer memberId,
			String messageCode) {
		ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(messageModel.sendMessageToMember(queryMap, memberId, messageCode));
        } catch (BusinessException e) {
        	serviceResult.setSuccess(false);
        	serviceResult.setMessage(e.getMessage());
            log.error("[IMessageService][sendMessageToMessage]发送消息到用户时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageService][sendMessageToMessage]发送消息到用户时出现未知异常：",
                e);
        }
        return serviceResult;
	}
}