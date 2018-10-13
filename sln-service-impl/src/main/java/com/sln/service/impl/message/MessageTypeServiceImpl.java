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
import com.sln.entity.message.MessageType;
import com.sln.model.message.MessageTypeModel;
import com.sln.service.message.IMessageTypeService;

@Service(value = "messageTypeService")
public class MessageTypeServiceImpl implements IMessageTypeService {
	private static Logger      log = LogManager.getLogger(MessageTypeServiceImpl.class);
	
	@Resource
	private MessageTypeModel messageTypeModel;
    
     /**
     * 根据id取得消息类型表
     * @param  messageTypeId
     * @return
     */
    @Override
    public ServiceResult<MessageType> getMessageTypeById(Integer messageTypeId) {
        ServiceResult<MessageType> result = new ServiceResult<MessageType>();
        try {
            result.setResult(messageTypeModel.getMessageTypeById(messageTypeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageTypeService][getMessageTypeById]根据id["+messageTypeId+"]取得消息类型表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageTypeService][getMessageTypeById]根据id["+messageTypeId+"]取得消息类型表时出现未知异常：",
                e);
        }
        return result;
    }
    
    /**
     * 保存消息类型表
     * @param  messageType
     * @return
     */
     @Override
     public ServiceResult<Integer> saveMessageType(MessageType messageType) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(messageTypeModel.saveMessageType(messageType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageTypeService][saveMessageType]保存消息类型表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageTypeService][saveMessageType]保存消息类型表时出现未知异常：",
                e);
        }
        return result;
     }
     
     /**
     * 更新消息类型表
     * @param  messageType
     * @return
     * @see com.slooong.sln.service.MessageTypeService#updateMessageType(MessageType)
     */
     @Override
     public ServiceResult<Integer> updateMessageType(MessageType messageType) {
     	ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(messageTypeModel.updateMessageType(messageType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
            log.error("[IMessageTypeService][updateMessageType]更新消息类型表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageTypeService][updateMessageType]更新消息类型表时出现未知异常：",
                e);
        }
        return result;
     }

	@Override
	public ServiceResult<List<MessageType>> page(Map<String, String> queryMap, PagerInfo pager) {
		ServiceResult<List<MessageType>> serviceResult = new ServiceResult<List<MessageType>>();
        try {
            if (pager != null) {
                pager.setRowsCount(messageTypeModel.count(queryMap));
                serviceResult.setPager(pager);
            }
            serviceResult.setResult(messageTypeModel.page(queryMap, pager));
        } catch (BusinessException e) {
        	serviceResult.setSuccess(false);
        	serviceResult.setMessage(e.getMessage());
            log.error("[IMessageTypeService][page]查询消息类型表时出现未知异常：" + e.getMessage());
        } catch (Exception e) {
        	serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IMessageTypeService][page]查询消息类型表时出现未知异常：",
                e);
        }
        return serviceResult;
	}
}