package com.sln.web.controller.system;

import java.util.Date;
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
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.message.MessageType;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.message.IMessageTypeService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 消息类型相关action
 *                       
 * @Filename: MessageType.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "/admin/messageType")
public class MessageTypeController extends BaseController {

	  @Resource
	  private IMessageTypeService messageTypeService;
	  
	  
	  /**
	     * 默认页面
	     * @param dataMap
	     * @return
	     * @throws Exception
	     */
	    @RequestMapping(value = "", method = { RequestMethod.GET })
	    public String index(HttpServletRequest request, ModelMap dataMap) throws Exception {
	        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        dataMap.put("queryMap", queryMap);
	        return "/admin/operate/message/messagetypelist";
	    }
	
	    /**
	     * gridDatalist数据
	     * @param request
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "list", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<List<MessageType>> list(HttpServletRequest request,
	                                                                 ModelMap dataMap) {
	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
	        ServiceResult<List<MessageType>> serviceResult = messageTypeService.page(queryMap, pager);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }

	        HttpJsonResult<List<MessageType>> jsonResult = new HttpJsonResult<List<MessageType>>();
	        jsonResult.setRows(serviceResult.getResult());
	        jsonResult.setTotal(pager.getRowsCount());

	        return jsonResult;
	    }
	    
	    @RequestMapping(value = "edit", method = { RequestMethod.GET })
	    public String edit(HttpServletRequest request, ModelMap dataMap, Integer id) {
	        if (id != null) {
	           MessageType messageType = messageTypeService.getMessageTypeById(id).getResult();
	            dataMap.put("obj", messageType);
	        }

	        return "/admin/operate/message/messagetypeedit";
	    }
	    
	    @RequestMapping(value = "editState", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<Boolean> editState(HttpServletRequest request,
										                HttpServletResponse response,
										                MessageType messageType){
	    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	    	jsonResult.setSuccess(false);
	    	try {
		    	ServiceResult<Integer> serviceResult = messageTypeService.updateMessageType(messageType);
		    	if(serviceResult.getSuccess()) {
		    		jsonResult.setSuccess(true);
		    	}
	    	}catch(Exception e) {
	    		jsonResult.setMessage(e.getMessage());
	            e.printStackTrace();
	    	}
	    	return jsonResult;
	    }
	    
	    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
	    public @ResponseBody HttpJsonResult<Boolean> doAdd(HttpServletRequest request,
	                                                       HttpServletResponse response,
	                                                       MessageType messageType) {
	        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
	        ServiceResult<Integer> serviceResult = null;
	        SystemAdmin admin = WebAdminSession.getAdminUser(request);
	        try {

	            if (messageType.getId() != null && 0 != messageType.getId()) {
	                serviceResult = messageTypeService.updateMessageType(messageType);
	            } else {
	            	messageType.setState(MessageType.STATE0);
	            	messageType.setCreateUserName(admin.getName());
	                serviceResult = messageTypeService.saveMessageType(messageType);
	            }
	            jsonResult.setData(serviceResult.getResult() > 0);
	        } catch (Exception e) {
	            jsonResult.setMessage(e.getMessage());
	            e.printStackTrace();
	        }
	        return jsonResult;
	    }
}
