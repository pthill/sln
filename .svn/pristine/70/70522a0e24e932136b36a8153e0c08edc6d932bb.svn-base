package com.sln.web.controller.operate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.SellerQq;
import com.sln.entity.seller.SellerUser;
import com.sln.service.seller.ISellerQqService;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebSellerSession;

/**
 * 商家客服qq
 *                       
 * @Filename: SellerQQController.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Controller
@RequestMapping(value = "seller/operate/sellerqq")
public class SellerQQController extends BaseController {
    @Resource
    private ISellerQqService sellerQqService;
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
        return "seller/operate/sellerqq/list";
    }

    /**
     * gridDatalist数据
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<SellerQq>> list(HttpServletRequest request,
                                                             Map<String, Object> dataMap) {
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        queryMap.put("sellerId", WebSellerSession.getSellerUser(request).getSellerId().toString());
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<SellerQq>> serviceResult = sellerQqService.page(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<SellerQq>> jsonResult = new HttpJsonResult<List<SellerQq>>();
        jsonResult.setRows((List<SellerQq>) serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());

        return jsonResult;
    }

    /**
     * 新增页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request) {
        return "seller/operate/sellerqq/edit";
    }

    /**
     * 编辑页面
     * @param request
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request, Map<String, Object> dataMap, String id) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        ServiceResult<SellerQq> np = this.sellerQqService.getById(Integer.valueOf(id));
        if(!np.getSuccess()){
        	return "seller/500";
        }
        SellerQq sellerQq = np.getResult();
        if(sellerQq == null){
        	return "seller/404";
        }
        if(!sellerQq.getSellerId().equals(sellerUser.getSellerId())){
        	return "seller/unauth";
        }
        dataMap.put("obj", sellerQq);
        return "seller/operate/sellerqq/edit";
    }

    /**
     * 添加分类
     * @param request
     * @param response
     * @param news
     * @return
     */
    @RequestMapping(value = "doAdd", method = { RequestMethod.POST })
    public String doAdd(HttpServletRequest request, HttpServletResponse response, SellerQq np) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        np.setCreateTime(new Date());
        if (np.getId() != null && np.getId() != 0) {
        	ServiceResult<SellerQq> dbSellerQqRequest = this.sellerQqService.getById(Integer.valueOf(np.getId()));
            if(!dbSellerQqRequest.getSuccess()){
            	return "seller/500";
            }
            SellerQq sellerQq = dbSellerQqRequest.getResult();
            if(sellerQq == null){
            	return "seller/404";
            }
            if(!sellerQq.getSellerId().equals(sellerUser.getSellerId())){
            	return "seller/unauth";
            }
            this.sellerQqService.update(np);
        } else {
            np.setCreateTime(new Date());
            np.setCreateId(WebSellerSession.getSellerUser(request).getId());
            np.setSellerId(WebSellerSession.getSellerUser(request).getSellerId());
            this.sellerQqService.save(np);
        }

        return "redirect:/seller/operate/sellerqq";
    }

    /**
     * 删除
     * @param request
     * @param response
     * @param np
     * @return
     */
    @RequestMapping(value = "del", method = { RequestMethod.GET })
    public String del(HttpServletRequest request, HttpServletResponse response, String id) {
    	SellerUser sellerUser = WebSellerSession.getSellerUser(request);
    	ServiceResult<SellerQq> dbSellerQqRequest = this.sellerQqService.getById(Integer.valueOf(id));
        if(!dbSellerQqRequest.getSuccess()){
        	return "seller/500";
        }
        SellerQq sellerQq = dbSellerQqRequest.getResult();
        if(sellerQq == null){
        	return "seller/404";
        }
        if(!sellerQq.getSellerId().equals(sellerUser.getSellerId())){
        	return "seller/unauth";
        }
        this.sellerQqService.del(Integer.valueOf(id));
        return "redirect:/seller/operate/sellerqq";
    }
}
