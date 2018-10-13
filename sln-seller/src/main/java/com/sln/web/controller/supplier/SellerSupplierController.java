package com.sln.web.controller.supplier;

import com.sln.core.*;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.supplier.Supplier;
import com.sln.service.supplier.ISupplierService;
import com.sln.web.util.WebSellerSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/***
 * 供应商
 *                       
 * @Filename: SellerSupplierController.java
 * @Version: 1.0
 * @Author: pangsm
 * @Email: anna_p@yeah.net
 *
 */
@Controller
@RequestMapping(value = "seller/system/supplier")
public class SellerSupplierController {
    @Resource
    private ISupplierService supplierService;

    /**
     * 默认
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "seller/supplier/supplierlist";
    }

    /**
     * 供应商列表
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody
    HttpJsonResult<List<Supplier>> list(HttpServletRequest request, Map<String, Object> dataMap) {
        HttpJsonResult<List<Supplier>> jsonResult = new HttpJsonResult<List<Supplier>>();
        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        queryMap.put("q_sellerId", String.valueOf(sellerUser.getSellerId()));
        ServiceResult<List<Supplier>> serviceResult = supplierService.getSupplierList(queryMap,
            pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    /***
     * 显示供应商页面
     * @param request
     * @param dataMap
     * @return
     */

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(HttpServletRequest request, Map<String, Object> dataMap) {
        return "seller/supplier/supplieradd";
    }

    /***
     * 添加供应商
     * @param request
     * @param dataMap
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(Supplier supplier, MultipartHttpServletRequest request,
                                         Map<String, Object> dataMap) throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        supplier.setSellerId(sellerUser.getSellerId());
        supplier.setUserId(WebSellerSession.getSellerUser(request).getId());
        ServiceResult<Boolean> serviceResult = supplierService.save(supplier,sellerUser.getSellerId());
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /***
     * 显示供应商页面
     * @param request
     * @param id
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(HttpServletRequest request,
                       @RequestParam(value = "id", required = true) Integer id,
                       Map<String, Object> dataMap) {
        ServiceResult<Supplier> serviceResult = supplierService.getById(id);
        if (!serviceResult.getSuccess()) {
            return "seller/500";
        }
        Supplier supplierResult = serviceResult.getResult();
        if (supplierResult == null) {
            return "seller/404";
        }

        dataMap.put("supplier", supplierResult);
        return "seller/supplier/supplieredit";
    }


    /**
     * 修改供应商
     * @param supplier
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(Supplier supplier, MultipartHttpServletRequest request)
                                                                                                throws IOException {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        ServiceResult<Supplier> selResult = supplierService.getById(supplier.getId());
        if (!selResult.getSuccess()) {
            return new HttpJsonResult<Object>(selResult.getMessage());
        }
        Supplier supplierResult = selResult.getResult();
        if (supplierResult == null) {
            return new HttpJsonResult<Object>("获取供应商信息失败，请重试。");
        }
        supplier.setSellerId(sellerUser.getSellerId());
        ServiceResult<Boolean> serviceResult = supplierService.update(supplier);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "isNameExist",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult isNameExist(String name,HttpServletRequest request){
        HttpJsonResult jsonResult=new HttpJsonResult();
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        ServiceResult<Integer>serviceResult=supplierService.isNameExist(name,sellerUser.getSellerId());
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Object>("检查名称是否重复出错，请重试");
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    @RequestMapping(value = "isSellerTypeExist",method = {RequestMethod.POST})
    @ResponseBody
    public HttpJsonResult isSellerTypeExist(HttpServletRequest request){
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        HttpJsonResult jsonResult=new HttpJsonResult();
        ServiceResult<Integer>serviceResult=supplierService.isSellerTypeExist(sellerUser.getSellerId());
        if (!serviceResult.getSuccess()) {
            jsonResult = new HttpJsonResult<Object>("判断商家供应是否存在时出错，请重试");
        }
        jsonResult.setData(serviceResult.getResult());
        return jsonResult;
    }

    /**
     * 删除供应商
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "del", method = RequestMethod.POST)
    @ResponseBody
    public HttpJsonResult<Object> del(HttpServletRequest request, @RequestParam("id") Integer id) {
        SellerUser sellerUser = WebSellerSession.getSellerUser(request);
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        if (null == sellerUser) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/seller/login.html");
            return jsonResult;
        }
        ServiceResult<Boolean> serviceResult = supplierService.del(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /***
     * 启用供应商
     * @param request
     * @param 用户id
     * @return
     */
    @RequestMapping(value = "up", method = RequestMethod.POST)
    @ResponseBody
    public HttpJsonResult<Object> up(HttpServletRequest request, @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();

        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setState(Supplier.STATUS_1);
        ServiceResult<Boolean> serviceResult = supplierService.updateSupplier(supplier);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 停用供应商
     * @param request
     * @param 用户id
     * @return
     */
    @RequestMapping(value = "down", method = RequestMethod.POST)
    public @ResponseBody
    HttpJsonResult<Object> down(HttpServletRequest request, @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setState(Supplier.STATUS_0);
        ServiceResult<Boolean> serviceResult = supplierService.updateSupplier(supplier);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }


    /****
     * 根据供应商id获取供应商
     * @param request
     * @param dataMap
     * @return
     */
    @RequestMapping(value = "getSupplierById", method = { RequestMethod.POST })
    public @ResponseBody
    ServiceResult<Supplier> getSupplierById(HttpServletRequest request,
                                            Map<String, Object> dataMap, Integer supplierId) {
        ServiceResult<Supplier> serviceResult = supplierService.getById(supplierId);
        if (!serviceResult.getSuccess()) {
            serviceResult.setMessage(serviceResult.getMessage());
        }
        return serviceResult;

    }
}
