package com.sln.web.controller.message;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.sln.entity.message.MessageRecord;
import com.sln.entity.message.MessageType;
import com.sln.entity.operate.SystemNotice;
import com.sln.entity.seller.SellerUser;
import com.sln.service.message.IMessageRecordService;
import com.sln.service.message.IMessageService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 商家消息
 *                       
 * @Filename: MessageController.java
 * @Version: 1.0
 * @Author: 唐振宇
 * @Email: 
 *
 */
@Controller
@RequestMapping(value = "seller/message")
public class MessageController extends BaseController {
    @Resource
    private IMessageRecordService messageRecordService;
    
    Logger                   log = Logger.getLogger(this.getClass());

    /**
     * 默认页面
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(HttpServletRequest request, Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        dataMap.put("queryMap", queryMap);
        return "seller/operate/message/messagelist";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<MessageRecord>> list(HttpServletRequest request,
                                                             Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        
        queryMap.put("q_receptionId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        queryMap.put("q_receptionType", String.valueOf(MessageType.RECEPTION_TYPE2));
        //queryMap.put("q_typeState", String.valueOf(MessageType.STATE0));
        
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
        jsonResult.setRows((List<MessageRecord>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 获取未读消息数量
     * @param dataMap
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getMessage", method = { RequestMethod.GET })
    public String getUnreadNotice(Map<String, Object> dataMap, HttpServletRequest request,
                                  HttpServletResponse response) throws Exception {
        SellerUser su = WebSellerSession.getSellerUser(request);
        if (isNull(su)) {
            return "redirect:/seller/login.html";
        }

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        pager.setPageSize(5);
        
        // 获取商家所有未读的消息 显示前5条
        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put("q_receptionId", String.valueOf(su.getId()));
        queryMap.put("q_receptionType", String.valueOf(MessageType.RECEPTION_TYPE2));
        queryMap.put("q_isDel", String.valueOf(MessageRecord.ISREAD0));
        queryMap.put("q_isRead", String.valueOf(MessageRecord.ISREAD0));
    	ServiceResult<List<MessageRecord>> messageResult = messageRecordService.page(queryMap, pager);
    	
        dataMap.put("count", pager.getRowsCount());
        //概要显示前五个
        
        // 获取所有未读消息的数量
        ServiceResult<Integer> unreadNum = messageRecordService.getUnreadNumByMessageTypeId(null, su.getId());
        dataMap.put("unreadNum", unreadNum.getResult());
        
        dataMap.put("messagelist", messageResult.getResult());
        return "seller/notice";
    }
    
    /**
     * 详情
     * @param request
     * @param dataMap
     * @param id
     * @return
     */
    @RequestMapping(value = "detail", method = { RequestMethod.GET })
    public String detail(HttpServletRequest request, ModelMap dataMap, Integer id) {
        if (id != null) {
        	ServiceResult<MessageRecord> serviceResult = 
        			messageRecordService.getMessageRecordById(id);
           
        	if (!serviceResult.getSuccess()) {
                if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                    throw new RuntimeException(serviceResult.getMessage());
                } else {
                    throw new BusinessException(serviceResult.getMessage());
                }
            }
        	
           
            dataMap.put("obj", serviceResult.getResult());
        }
        return "/seller/operate/message/messagedetail";
    }
    
    @RequestMapping(value = "setRead", method = { RequestMethod.GET })
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
}
