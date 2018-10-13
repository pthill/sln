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
import com.sln.entity.message.Message;
import com.sln.entity.message.MessageRecord;
import com.sln.entity.message.MessageType;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.message.IMessageRecordService;
import com.sln.service.message.IMessageTypeService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 消息发送记录相关action
 *                       
 * @Filename: MessageRecord.java
 * @Version: 1.0
 * 
 */
@Controller
@RequestMapping(value = "/admin/messageRecord")
public class MessageRecordController extends BaseController {

	  @Resource
	  private IMessageRecordService messageRecordService;
	  
	  
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
	        return "/admin/operate/message/messagerecordlist";
	    }
	
	    /**
	     * gridDatalist数据
	     * @param request
	     * @param dataMap
	     * @return
	     */
	    @RequestMapping(value = "list", method = { RequestMethod.GET })
	    public @ResponseBody HttpJsonResult<List<MessageRecord>> list(HttpServletRequest request,
	                                                                 ModelMap dataMap) {
	        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
	        queryMap.put("q_isMessageTemplate", String.valueOf(Message.IS_MESSAGE_TEMPLATE1));
	        
	        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
	        ServiceResult<List<MessageRecord>> serviceResult = messageRecordService.page(queryMap, pager);
	        if (!serviceResult.getSuccess()) {
	            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
	                throw new RuntimeException(serviceResult.getMessage());
	            } else {
	                throw new BusinessException(serviceResult.getMessage());
	            }
	        }

	        HttpJsonResult<List<MessageRecord>> jsonResult = new HttpJsonResult<List<MessageRecord>>();
	        jsonResult.setRows(serviceResult.getResult());
	        jsonResult.setTotal(pager.getRowsCount());

	        return jsonResult;
	    }
	   
}
