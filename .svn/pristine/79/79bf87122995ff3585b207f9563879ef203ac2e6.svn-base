package com.sln.web.controller.system;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageType;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.message.IMessageRecordService;
import com.sln.service.message.IMessageService;
import com.sln.service.message.IMessageTypeService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 消息管理相关action
 *                       
 * @Filename: Message.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "/admin/messageManager")
public class MessageManagerController extends BaseController {

	  @Resource
	  private IMessageService messageService;
	  @Resource
	  private IMessageRecordService messageRecordService;
	  @Resource
	  private IMessageTypeService messageTypeService;
	  
	  
	  /**
	     * 默认页面
	     * @param dataMap
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "", method = { RequestMethod.GET })
	    public String index(HttpServletRequest request, ModelMap dataMap,Integer type) throws Exception {
	        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        dataMap.put("queryMap", queryMap);
	        if(null != type && type == 0) {
	        	return "/admin/operate/message/messagetemplatelist";
	        }
	        
	        ServiceResult<List<MessageType>> serviceResult = messageTypeService.page(new HashMap<String, String>(), new PagerInfo());
	        if(null != serviceResult.getResult()) {
	        	dataMap.put("messageTypeList", serviceResult.getResult());
	        }
	        
	        return "/admin/operate/message/messagelist";
	    }
	
	    /**
	     * gridDatalist数据
	     * @param request
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "list", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<List<Message>> list(HttpServletRequest request,
	                                                                 ModelMap dataMap) {
	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
	        ServiceResult<List<Message>> serviceResult = messageService.page(queryMap, pager);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }

	        HttpJsonResult<List<Message>> jsonResult = new HttpJsonResult<List<Message>>();
	        jsonResult.setRows(serviceResult.getResult());
	        jsonResult.setTotal(pager.getRowsCount());

	        return jsonResult;
	    }
	    
	    /**
	     * gridDatalist数据
	     * @param request
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "getMessageByMessageCode", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<Boolean> getMessageByMessageCode(HttpServletRequest request,
	                                                                 ModelMap dataMap,String messageCode) {
	    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        ServiceResult<Message> serviceResult = messageService.getMessageByMessageCode(messageCode);
	        if (!serviceResult.getSuccess()) {
	        	jsonResult.setSuccess(false);
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }
	        if(null != serviceResult.getResult()) {
	        	jsonResult.setSuccess(false);
	        }
	        return jsonResult;
	    }
	    
	    @RequestMapping(value = "edit", method = { RequestMethod.GET })
	    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
	    	Map<String, String> queryMap = new HashMap<String, String>();
	    	queryMap.put("q_state", String.valueOf(MessageType.STATE0));
	    	
	    	ServiceResult<List<MessageType>> result = messageTypeService.page(queryMap, null);
	    	if(result.getSuccess() && null != result ) {
	    		dataMap.put("messageTypeList", result.getResult());
	    	}
	        if (id != null) {
	        	Message message = messageService.getMessageById(id).getResult();
	            dataMap.put("obj", message);
	        }
	        return "/admin/operate/message/messageedit";
	    }
	    
	    @RequestMapping(value = "templateEdit", method = { RequestMethod.GET })
	    public String templateEdit(HttpServletRequest request, ModelMap dataMap, Integer id) {
	    	Map<String, String> queryMap = new HashMap<String, String>();
	    	queryMap.put("q_state", String.valueOf(MessageType.STATE0));
	    	
	    	ServiceResult<List<MessageType>> result = messageTypeService.page(queryMap, null);
	    	if(result.getSuccess() && null != result ) {
	    		dataMap.put("messageTypeList", result.getResult());
	    	}
	        if (id != null) {
	        	Message message = messageService.getMessageById(id).getResult();
	            dataMap.put("obj", message);
	        }
	        return "/admin/operate/message/messagetemplateedit";
	    }
	    
	    /**
	     * 删除
	     * @param request
	     * @param response
	     * @param cate
	     */
	    @RequestMapping(value = "del", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<Boolean> del(HttpServletRequest request,
	                                                     HttpServletResponse response, Integer id) {
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        try {
	            ServiceResult<Boolean> sr = messageService.del(id);
	            if (!sr.getSuccess()) {
	                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
	                    throw new RuntimeException(sr.getMessage());
	                } else {
	                    throw new BusinessException(sr.getMessage());
	                }
	            }
	        } catch (Exception e) {
	            jsonResult.setMessage(e.getMessage());
	            e.printStackTrace();
	        }
	        return jsonResult;
	    }
	    
	    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
	                                                       HttpServletResponse response,
	                                                       Message message) {
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        ServiceResult<Integer> serviceResult = null;
	        SystemAdmin admin = WebAdminSession.getAdminUser(request);
	        try {

	            if (message.getId() != null && 0 != message.getId()) {
	                serviceResult = messageService.updateMessage(message);
	            } else {
	            	message.setState(Message.STATE0);
	            	message.setSendType(Message.SEND_TYPE0);
	            	message.setSendId(admin.getId());
	                serviceResult = messageService.saveMessage(message);
	            }
	            jsonResult.setData(serviceResult.getResult() > 0);
	        } catch (Exception e) {
	            jsonResult.setMessage(e.getMessage());
	            e.printStackTrace();
	        }
	        return jsonResult;
	    }
	    
	    /**
	     * 发送短信
	     * @param request
	     * @param dataMap
	     * @param id
	     * @return
	     */
	    @RequestMapping(value = "sendMessage", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<Boolean> sendMessage(HttpServletRequest request,
                														ModelMap dataMap,Integer id){
	    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	    	try {
	    		Message message = new Message();
		    	message.setId(id);
	            ServiceResult<Boolean> sr = messageRecordService.sendMessage(message);
	            if (!sr.getSuccess()) {
	                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(sr.getCode())) {
	                    throw new RuntimeException(sr.getMessage());
	                } else {
	                    throw new BusinessException(sr.getMessage());
	                }
	            }
	        } catch (Exception e) {
	            jsonResult.setMessage(e.getMessage());
	            e.printStackTrace();
	        }
	    	return jsonResult;
	    }
}
