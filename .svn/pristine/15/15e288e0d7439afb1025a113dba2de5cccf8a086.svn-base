package com.sln.web.controller.product;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.HttpJsonResultForAjax;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.jd.JdCategory;
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductCateJd;
import com.sln.entity.product.ProductCateServiceSwitch;
import com.sln.entity.product.ProductType;
import com.sln.entity.seller.SellerManageCate;
import com.sln.entity.seller.SellerTypeLogs;
import com.sln.entity.system.ResourceTree;
import com.sln.entity.system.SystemAdmin;
import com.sln.service.jd.IJdCategoryService;
import com.sln.service.product.IProductCateService;
import com.sln.service.product.IProductCateServiceSwitchService;
import com.sln.service.product.IProductTypeService;
import com.sln.service.product.ISellerTypeLogsService;
import com.sln.vo.product.ProductCateVO;
import com.sln.web.controller.BaseController;
import com.sln.web.util.WebAdminSession;

/**
 * 商品分类
 */
@Controller
@RequestMapping(value = "admin/product/cate")
public class CateController extends BaseController {
    private String                 baseUrl = "admin/product/cate/";

    @Resource
    private IProductCateService    productCateService;
    @Resource
    private IProductTypeService    productTypeService;
    @Resource
    private ISellerTypeLogsService sellerTypeLogsService;
    @Resource
    private IProductCateServiceSwitchService productCateServiceSwitchService;
    @Resource
    private IJdCategoryService jdCategoryService;

    @RequestMapping(value = "", method = { RequestMethod.GET })
    public String index(Map<String, Object> dataMap) throws Exception {
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        
        ProductCateServiceSwitch pcss = productCateServiceSwitchService.get().getResult();
        dataMap.put("serviceSwitch",0);
        if(pcss!=null){
        	 //0:未使用，1：使用
        	 dataMap.put("serviceSwitch", ConvertUtil.toInt(pcss.getState(), 0));
        }
        return baseUrl + "catelist";
    }

    @RequestMapping(value = "audit", method = { RequestMethod.GET })
    public String audit(Map<String, Object> dataMap) throws Exception {
        dataMap.put("q_useYn", "1");
        dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return baseUrl + "auditlist";
    }

    /**
     * 商品分类服务费比例启停
     * @param request
     */
    @RequestMapping(value = "serviceSwitch", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> serviceSwitch(HttpServletRequest request) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
        	 SystemAdmin user = WebAdminSession.getAdminUser(request);
        	 ProductCateServiceSwitch pcss = new ProductCateServiceSwitch();
        	 pcss.setCreateId(user.getId());
        	 productCateServiceSwitchService.updateProductCateServiceSwitch(pcss);
        } catch (Exception e) {
            if (e instanceof BusinessException)
                jsonResult.setMessage(e.getMessage());
            else
                e.printStackTrace();
        }
        return jsonResult;
    }
    
    /**
     * 商家分类申请审核
     * @param res
     * @param id
     * @param type
     */
    @RequestMapping(value = "auditPass", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<Boolean> auditPass(HttpServletResponse res, String ids,
                                                           Integer type) {
        HttpJsonResult<Boolean> jsonResult = new HttpJsonResult<Boolean>();
        try {
            if (isNull(ids))
                throw new BusinessException("数据错误");
            for (String id : ids.split(",")) {
                SellerManageCate cate = productCateService.getSellerManageCate(Integer.valueOf(id));
                if (type == ConstantsEJS.SELLER_MANAGE_CATE_STATE_2)
                    cate.setState(ConstantsEJS.SELLER_MANAGE_CATE_STATE_2);
                else if (type == ConstantsEJS.SELLER_MANAGE_CATE_STATE_3)
                    cate.setState(ConstantsEJS.SELLER_MANAGE_CATE_STATE_3);

                productCateService.updateSellerManageCate(cate);
            }
        } catch (Exception e) {
            if (e instanceof BusinessException)
                jsonResult.setMessage(e.getMessage());
            else
                e.printStackTrace();
        }
        return jsonResult;
    }

    @RequestMapping(value = "list", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductCateVO>> list(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = WebUtil.handlerQueryMap(request);
        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductCateVO>> serviceResult = productCateService
            .pageProductCate(queryMap, pager);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        HttpJsonResult<List<ProductCateVO>> jsonResult = new HttpJsonResult<List<ProductCateVO>>();
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(pager.getRowsCount());
        return jsonResult;
    }

    @RequestMapping(value = "getByPid", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<ProductCate> getByPid(@RequestParam(value = "id", required = true) Integer pid) {
        HttpJsonResult<List<ProductCate>> jsonResult = new HttpJsonResult<List<ProductCate>>();
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(pid);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return serviceResult.getResult();
    }

    @RequestMapping(value = "productCaseTree", method = { RequestMethod.GET })
    public @ResponseBody List<ResourceTree> productCaseTree(HttpServletRequest request,
                                                            @RequestParam(value = "id", required = true) Integer pid) {
        List<ResourceTree> tree = new ArrayList<ResourceTree>();
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(pid);
        generateTree(tree, serviceResult.getResult());
        return tree;
    }

    /**
     * 递归生成树
     * @param treelist
     * @param data
     * @return
     */
    private List<ResourceTree> generateTree(List<ResourceTree> treelist, List<ProductCate> data) {
        for (ProductCate sr : data) {
            ResourceTree tree = new ResourceTree();
            tree.setId(sr.getId());
            tree.setText(sr.getName());
            tree.setChildren(generateTree(new ArrayList<ResourceTree>(),
                productCateService.getByPid(sr.getId()).getResult()));
            tree.setState(tree.getChildren().size() > 0 ? "closed" : "open");
            treelist.add(tree);
        }
        return treelist;
    }

    @RequestMapping(value = "add", method = { RequestMethod.GET })
    public String add(Map<String, Object> dataMap) {
        ServiceResult<List<ProductCate>> serviceResult = productCateService.getByPid(0);
        if (!serviceResult.getSuccess()) {
            log.error("");
        }
        dataMap.put("productCates", serviceResult.getResult());
        return baseUrl + "cateadd";
    }

    @RequestMapping(value = "create", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> create(ProductCate cate, HttpServletRequest request,
                                         Map<String, Object> dataMap) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        cate.setCreateId(user.getId());
        cate.setCreater(user.getName());
        cate.setUpdateId(user.getId());
        cate.setStatus(1);
        cate.setType(1);
        //设置path
        cate.setPath(buildPath(cate.getPid()));
        ServiceResult<Boolean> serviceResult = productCateService.saveProductCate(cate);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
            return jsonResult;
        }
        return jsonResult;
    }

    @RequestMapping(value = "edit", method = { RequestMethod.GET })
    public String edit(Integer id, Map<String, Object> dataMap) {
        ServiceResult<ProductCate> serviceResult = productCateService.getProductCateById(id);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            }
        }
        /**1.商品分类*/
        dataMap.put("cate", serviceResult.getResult());

        /**2.商品类型名称*/
        ServiceResult<ProductType> result = productTypeService
            .getProductTypeById(serviceResult.getResult().getProductTypeId());
        if (result.getSuccess() && result.getResult() != null) {
            dataMap.put("typeName", result.getResult().getName());
        }

        /**3.构造上级分类*/
        String[] cateIds = serviceResult.getResult().getPath().split("/");
        if (cateIds.length == 0) {
            //只构造出一级分类
            ServiceResult<List<ProductCate>> result1 = productCateService.getByPid(0);//一级分类

            //删除当前分类
            List<ProductCate> list = result1.getResult();
            Iterator<ProductCate> iterator = list.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getId() == id)
                    iterator.remove();
            }
            dataMap.put("B", list);
        }
        if (cateIds.length > 0) {
            Map<String, Object> cateMap = new HashMap<String, Object>();
            for (int i = 0; i < cateIds.length - 1; i++) {
                ServiceResult<List<ProductCate>> result1 = new ServiceResult<List<ProductCate>>();
                if (StringUtil.isEmpty(cateIds[i])) {
                    result1 = productCateService.getByPid(0);//一级分类
                } else {
                    result1 = productCateService.getByPid(Integer.valueOf(cateIds[i]));
                }
                if (result1.getSuccess() && result1.getResult() != null) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("cateId", cateIds[i + 1]);
                    map.put("cateList", result1.getResult());
                    if (i == 0) {
                        cateMap.put("B", map);//大类
                    } else if (i == 1) {
                        cateMap.put("M", map);//中类
                    } else {
                        cateMap.put("S", map);//小类
                    }
                }
            }
            dataMap.put("parentCate", cateMap);
        }
        /**4.构造商家类型修改日志表*/
        ServiceResult<List<SellerTypeLogs>> logResult = sellerTypeLogsService
            .getSellerTypeLogsByCateId(id);
        if (logResult.getSuccess() && logResult.getResult() != null) {
            dataMap.put("log", logResult.getResult());
        }
        return baseUrl + "cateedit";
    }

    @RequestMapping(value = "update", method = { RequestMethod.POST })
    @ResponseBody
    public HttpJsonResult<Object> update(ProductCate cate, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        cate.setUpdateId(user.getId());
        cate.setUpdater(user.getName());
        cate.setUpdateTime(new Date());
        cate.setPath(buildPath(cate.getPid()));
        ServiceResult<Boolean> serviceResult = productCateService.updateProductCate(cate);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping(value = "del", method = RequestMethod.POST)
    public @ResponseBody HttpJsonResult<Object> del(HttpServletRequest request,
                                                    @RequestParam("id") Integer id) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResult<Object>();
        ServiceResult<Boolean> serviceResult = productCateService.delProductCate(id);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }

    /**
     * 根据pid构造上级path
     * @param pid
     * @return
     */
    private String buildPath(Integer pid) {
        String path = "";
        ServiceResult<ProductCate> result = productCateService.getProductCateById(pid);
        if (result.getResult() == null) {
            path = "/";//一级分类
        } else {
            if ("/".equals(result.getResult().getPath())) {
                path = result.getResult().getPath() + pid;
            } else {
                path = result.getResult().getPath() + "/" + pid;
            }
        }
        return path;
    }

    /**
     * 跳转到京东类分关联页面
     * @param cateId
     * @param dataMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "jdCateList", method = { RequestMethod.GET })
    public String jdCateList(Integer cateId,Map<String, Object> dataMap) throws Exception {
    	//查询所有一级京东分类
    	ServiceResult<List<JdCategory>> serviceResult = jdCategoryService.getByPid(0);
    	if(serviceResult.getSuccess() && !CollectionUtils.isEmpty(serviceResult.getResult())){
    		dataMap.put("pJdCateList", serviceResult.getResult());
    	}
    	cateId =  ConvertUtil.toInt(cateId, 0);
    	if(cateId!=0){
    		ServiceResult<ProductCate> pcResult = productCateService.getProductCateById(cateId);
    		dataMap.put("cate", pcResult.getResult());
    		
    		Map<String,String> queryMap = new HashMap<>();
    		queryMap.put("productCateId", cateId.toString());
    		ServiceResult<List<ProductCateJd>> pcjResult = productCateService.pageProductCateJd(queryMap, null);
    		dataMap.put("pcjList", pcjResult.getResult());
    	}
    	dataMap.put("cateId", cateId);//平台分类ID
    	dataMap.put("pageSize", ConstantsEJS.DEFAULT_PAGE_SIZE_10);
        return baseUrl + "jdCateList";
    }
    
    @RequestMapping(value = "getJdCateList", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<JdCategory>> getRefJdCate(HttpServletRequest request, Map<String, Object> dataMap) {
    	 Map<String, Object> queryMap = new HashMap<>();
    	 Integer firstCateId = ConvertUtil.toInt(request.getParameter("q_firstCate"), 0);
    	 Integer secondCateId = ConvertUtil.toInt(request.getParameter("q_secondCate"), 0);
    	 String state = request.getParameter("q_state");
    	 queryMap.put("state", state);
		 if(firstCateId!=0 && secondCateId!=0){	
			 queryMap.put("parentId", secondCateId);
		 }else if(firstCateId!=0){
			 queryMap.put("firstCateId", firstCateId);
		 }
		 
		 //查询三级分类
		 queryMap.put("catClass", "2"); 
		 
         PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
         ServiceResult<List<JdCategory>> serviceResult = jdCategoryService.getJdCategoryList(queryMap, pager);
         if (!serviceResult.getSuccess()) {
             if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                 throw new RuntimeException(serviceResult.getMessage());
             } else {
                 throw new BusinessException(serviceResult.getMessage());
             }
         }

         HttpJsonResult<List<JdCategory>> jsonResult = new HttpJsonResult<List<JdCategory>>();
         jsonResult.setRows(serviceResult.getResult());
         jsonResult.setTotal(pager.getRowsCount());
         return jsonResult;
    }
    
    /**
     * 根据父id，获取子分类
     * @param pid
     * @return
     */
    @RequestMapping(value = "getJdCateByPid", method = { RequestMethod.POST, RequestMethod.GET })
    public @ResponseBody List<JdCategory> getJdCateByPid(@RequestParam(value = "id", required = true) Integer pid) {
        HttpJsonResult<List<JdCategory>> jsonResult = new HttpJsonResult<List<JdCategory>>();
        if(pid == null || pid == 0){
        	return null;
        }
        ServiceResult<List<JdCategory>> serviceResult = jdCategoryService.getByPid(pid);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        jsonResult.setRows(serviceResult.getResult());
        jsonResult.setTotal(serviceResult.getResult().size());
        return serviceResult.getResult();
    }
    
    
    @RequestMapping(value = "addRelJd", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<Object> addRelJD(ProductCate cate, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        cate.setType(ProductCate.TYPE_JD);  //将分类类型设置成京东
        cate.setUpdateId(user.getId());
        cate.setUpdater(user.getName());
        cate.setUpdateTime(new Date());
        ServiceResult<Boolean> serviceResult = productCateService.updateProductCate(cate);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "addJdCate", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<Object> addJDCate(String jdCatIds, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        if(StringUtils.isBlank(jdCatIds)){
        	jsonResult.setMessage("请至少选择一行数据!");
        	return jsonResult;
        }
        
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        ServiceResult<Boolean> serviceResult = productCateService.addJdCategory(jdCatIds, user);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    @RequestMapping(value = "addProductCateJd", method = { RequestMethod.GET })
    @ResponseBody
    public HttpJsonResult<Object> addProductCateJd(String productCateId,String jdCatIds, HttpServletRequest request) {
        HttpJsonResult<Object> jsonResult = new HttpJsonResultForAjax<Object>();
        if(StringUtils.isEmpty(productCateId)){
        	jsonResult.setMessage("参数不正确，刷新页面后重新操作!");
        	return jsonResult;
        }
        if(StringUtils.isBlank(jdCatIds)){
        	jsonResult.setMessage("请至少选择一行数据!");
        	return jsonResult;
        }
        
        SystemAdmin user = WebAdminSession.getAdminUser(request);
        if (null == user) {
            jsonResult.setMessage("登录超时，请重新登录");
            jsonResult.setBackUrl(DomainUrlUtil.getSLN_URL_RESOURCES() + "/admin/login");
            return jsonResult;
        }
        ServiceResult<Boolean> serviceResult = productCateService.batchInsertProductCateJd(ConvertUtil.toInt(productCateId,0), jdCatIds);
        if (!serviceResult.getSuccess()) {
            jsonResult.setMessage(serviceResult.getMessage());
        }
        return jsonResult;
    }
    
    
    @RequestMapping(value = "queryProductCateJd", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<ProductCateJd>> queryProductCateJd(HttpServletRequest request,
                                                                  HttpServletResponse response,
                                                                  Map<String, Object> dataMap) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("productCateId", request.getParameter("productCateId"));
        
        //PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap);
        ServiceResult<List<ProductCateJd>> serviceResult = productCateService.pageProductCateJd(queryMap, null);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        List<ProductCateJd> list = serviceResult.getResult();
        HttpJsonResult<List<ProductCateJd>> jsonResult = new HttpJsonResult<List<ProductCateJd>>();
        jsonResult.setRows(list);
        jsonResult.setTotal(list==null?0:list.size());
        return jsonResult;
    }
}
