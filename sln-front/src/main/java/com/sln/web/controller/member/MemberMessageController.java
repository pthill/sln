package com.sln.web.controller.member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.annotation.processing.Messager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.entity.member.Member;
import com.sln.entity.member.MemberAddress;
import com.sln.entity.message.MessageRecord;
import com.sln.entity.message.MessageType;
import com.sln.service.message.IMessageRecordService;
import com.sln.service.message.IMessageTypeService;
import com.sln.service.news.INewsService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebFrontSession;

@Controller
public class MemberMessageController extends BaseController {

    @Resource
    private IMessageRecordService     messageRecordService;
    @Resource
    private IMessageTypeService	messageTypeService;
    
    @RequestMapping(value = "member/message.html", method = { RequestMethod.GET })
    public String message(HttpServletRequest request, Map<String, Object> dataMap,HttpServletResponse response,Integer id) {
    	Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        Member member = WebFrontSession.getLoginedUser(request);
        
        queryMap.put("q_receptionId", String.valueOf(member.getId()));
        queryMap.put("q_receptionType", String.valueOf(MessageType.RECEPTION_TYPE1));
        queryMap.put("q_isDel", String.valueOf(MessageRecord.ISREAD0));
        if(!isNull(id)) {
        	queryMap.put("q_messageTypeId", String.valueOf(id));
        }
    	ServiceResult<List<MessageRecord>> serviceResult = messageRecordService.page(queryMap, pager);
    	
    	 String url = request.getRequestURI() + "";
         if (!isNull(id)) {
             url = url + "?id=" + id;
             dataMap.put("messageTypeId", id);
         }
         
    	//分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(serviceResult.getPager().getPageIndex()), serviceResult.getPager().getRowsCount(), url);
        
        dataMap.put("page", pm);
    	dataMap.put("messageList", serviceResult.getResult());
    	return "front/member/message/message-list";
    }
    
    @RequestMapping(value = "message/getMessageType.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MessageType>> getMessageType(HttpServletRequest request, Map<String, Object> dataMap,HttpServletResponse response) {
    	HttpJsonResult<List<MessageType>> jsonResult = new HttpJsonResult<List<MessageType>>();
    	Member member = WebFrontSession.getLoginedUser(request);
    	
    	Map<String,String> queryMap = new HashMap<String,String>();
    	queryMap.put("q_state",String.valueOf(MessageType.STATE0));
    	queryMap.put("memberId", member.getId().toString());
    	queryMap.put("q_receptionType", MessageType.RECEPTION_TYPE1+"");
    	
    	ServiceResult<List<MessageType>> serviceResult = messageTypeService.page(queryMap, null);
    	if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<List<MessageType>>(serviceResult.getMessage());
            }
        }
    	jsonResult.setData(serviceResult.getResult());
    	return jsonResult;
    }
    
    /**
     * 跳转到消息类型列表
     * @param request
     * @param dataMap
     * @param response
     * @return
     */
    @RequestMapping(value = "message/messageList.html", method = { RequestMethod.GET })
    public String tomessage(HttpServletRequest request, Map<String, Object> dataMap,HttpServletResponse response,Integer id) {
    	
    	this.head(0,dataMap,request);
    	
    	Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,8);
    	Member member = WebFrontSession.getLoginedUser(request);
    	
    	if(null == member){
			  return "redirect:/login.html";
		  }
    	
    	//获取消息类型列表
    	Map<String,String> queryMap1 = new HashMap<String,String>();
    	queryMap1.put("q_state",String.valueOf(MessageType.STATE0));
    	queryMap1.put("memberId", member.getId().toString());
    	queryMap1.put("q_receptionType", MessageType.RECEPTION_TYPE1+"");
    	ServiceResult<List<MessageType>> serviceResult = messageTypeService.page(queryMap1, null);
    	
    	// 获取所有未读消息数量
    	ServiceResult<Integer> unreadNum = messageRecordService.getUnreadNumByMessageTypeId(null, member.getId());
        dataMap.put("unreadNum", unreadNum.getResult());
        
        queryMap.put("q_receptionId", String.valueOf(member.getId()));
        queryMap.put("q_receptionType", String.valueOf(MessageType.RECEPTION_TYPE1));
        queryMap.put("q_isDel", String.valueOf(MessageRecord.ISREAD0));
        queryMap.put("q_typeState", String.valueOf(MessageType.STATE0));
        
        if(!isNull(id) && id != 0) {
        	queryMap.put("q_messageTypeId", String.valueOf(id));
        	
        	
        }
    	ServiceResult<List<MessageRecord>> messageResult = messageRecordService.page(queryMap, pager);
    	
    	dataMap.put("messageTypeId",ConvertUtil.toInt(id, 0));
        dataMap.put("page",pager);
    	dataMap.put("messageList", messageResult.getResult());
    	dataMap.put("messageTypeList", serviceResult.getResult());
    	return "front/portal/message-list";
    }
    
    @RequestMapping(value = "message/setRead.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> setRead(HttpServletRequest request, Map<String, Object> dataMap,HttpServletResponse response,Integer id) {
    	HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
    	
    	
    	
    	ServiceResult<Boolean> serviceResult = messageRecordService.setRead(id);
    	if (!serviceResult.getSuccess()) {
    		jsonResult.setData(false);
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                jsonResult = new HttpJsonResult<Boolean>(serviceResult.getMessage());
            }
        }
    	jsonResult.setData(serviceResult.getResult());
    	return jsonResult;
    }
    
    @RequestMapping(value = "message/del.html", method = { RequestMethod.GET })
    public String del(HttpServletRequest request, Map<String, Object> dataMap,HttpServletResponse response,
    																Integer id,Integer messageTypeId) {
    	ServiceResult<Boolean> serviceResult = messageRecordService.delete(id);
    	if(!isNull(messageTypeId)) {
    		return "redirect:/member/message.html?id="+messageTypeId;
    	}
    	return "redirect:/member/message.html";
    }
    
    
}
