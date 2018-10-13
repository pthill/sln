package com.sln.web.controller.product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.ServiceResult;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.product.Product;
import com.sln.entity.search.SearchSetting;
import com.sln.service.product.IProductFrontService;
import com.sln.service.search.ISearchSettingService;
import com.sln.vo.search.SearchProductVO;
import com.sln.web.controller.BaseController;

/**
 * 搜索商品
 *                       
 * @Filename: ProductSearchController.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Controller
public class ProductSearchController extends BaseController {

    @Resource
    private IProductFrontService  productFrontService;

    @Resource
    private ISearchSettingService searchSettingService;

    public static SolrClient getSolrClient() {
        String solrUrl = DomainUrlUtil.getSLN_SOLR_URL();
        String solrServer = DomainUrlUtil.getSLN_SOLR_SERVER();
        return new HttpSolrClient(solrUrl + "/" + solrServer);
    }

    @RequestMapping(value = "/search.html", method = RequestMethod.GET)
    public String search(HttpServletRequest request, String keyword, Map<String, Object> stack) {
        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));
        if ("".equals(keyword)) {
            List<String> keywords = getKeywords();
            if (keywords.size() > 0) {
                keyword = keywords.get(0);
            } else {
                keyword = "sln";
            }
        }
        keyword = StringUtil.stringFilterSpecial(keyword);

        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        //关键词过滤
        SearchSetting searchSetting = serviceResult.getResult();
        if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
            ServiceResult<Integer> serviceResultKeyword = searchSettingService
                .getSearchKeywordsByKeyword(keyword);
            int countKeyword = serviceResultKeyword.getResult();
            if (countKeyword > 0) {
                return "redirect:/error.html";
            }
        }

        String searchKeyword = "(" + keyword + ")";
        int count = 0;
        SolrClient client = getSolrClient();
        SolrQuery query = new SolrQuery();

        int start = 0, size = ConstantsEJS.DEFAULT_PAGE_SIZE;

        query.setQuery(queryKeyWord(searchKeyword));

        query.set("start", start);
        query.set("rows", size);
        // query.set("sort", SearchProductVO.SORT_ + " desc"); // 排序

        query.setHighlight(true);
        query.setHighlightSimplePre("<font color=\"red\">");
        query.setHighlightSimplePost("</font>");
        query.setParam("hl.fl", SearchProductVO.NAME1_);

        List<Product> products = new ArrayList<Product>();
        QueryResponse response = null;
        try {
            response = client.query(query);
            SolrDocumentList docs = response.getResults();
            count = new Integer(docs.getNumFound() + "");
            for (SolrDocument doc : docs) {
                String id = (String) doc.getFieldValue(SearchProductVO.ID_);
                Product product = queryResult(response, doc, id);
                products.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        stack.put("keywordNumber", 1);
        stack.put("count", count);
        stack.put("keyword", keyword);
        stack.put("producListVOs", products);
        return "h5/product/productsearch";
    }

    private Product queryResult(QueryResponse response, SolrDocument doc, String id) {
        Product product = new Product();
        product.setId(new Integer(id));

        Object object = response.getHighlighting().get(id).get(SearchProductVO.NAME1_);
        if (object != null) {
            product.setName1(object.toString().replace("[", "").replace("]", ""));
        } else {
            product.setName1(doc.getFieldValue(SearchProductVO.NAME1_).toString());
        }
        //                product.setName1(response.getHighlighting().get(id).get(SearchProductVO.NAME1_)
        //                    .toString().replace("[", "").replace("]", ""));

        product.setMasterImg(doc.getFieldValue(SearchProductVO.MASTERIMG_).toString());
        product.setMallPcPrice(
            new BigDecimal(doc.getFieldValue(SearchProductVO.MALLPCPRICE_).toString()));
        product.setMalMobilePrice(
            new BigDecimal(doc.getFieldValue(SearchProductVO.MALMOBILEPRICE_).toString()));
        product.setProductStock(
            new Integer(doc.getFieldValue(SearchProductVO.PRODUCTSTOCK_).toString()));
        product.setCommentsNumber(
            new Integer(doc.getFieldValue(SearchProductVO.COMMENTSNUMBER_).toString()));
        product.setSellerId(new Integer(doc.getFieldValue(SearchProductVO.SELLERID_).toString()));
        return product;
    }

    /**
     * 拼装查询条件
     * @param searchKeyword
     * @return
     */
    private String queryKeyWord(String searchKeyword) {
        StringBuilder sb = new StringBuilder();
        sb.append(SearchProductVO.CONTENT_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.NAME1_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.BRAND_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.SELLER_);
        sb.append(":");
        sb.append(searchKeyword);
        sb.append(" OR ");
        sb.append(SearchProductVO.CATE_);
        sb.append(":");
        sb.append(searchKeyword);
        return sb.toString();
    }

    /**
     * 返回json结果
     * @param request
     * @param stack
     * @return
     */
    @RequestMapping(value = "/searchJson.html", method = RequestMethod.GET)
    public @ResponseBody HttpJsonResult<List<Product>> searchJson(HttpServletRequest request,
                                                                  Map<String, Object> stack) {
        HttpJsonResult<List<Product>> jsonResult = new HttpJsonResult<List<Product>>();
        String keyword = request.getParameter("keyword");
        String pageNumStr = request.getParameter("pageNum");
        int pageNum = ConvertUtil.toInt(pageNumStr, 1);

        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }

        //关键词过滤
        SearchSetting searchSetting = serviceResult.getResult();
        if (searchSetting.getKeywordFilter().intValue() == SearchSetting.KEYWORD_FILTER_2) {
            ServiceResult<Integer> serviceResultKeyword = searchSettingService
                .getSearchKeywordsByKeyword(keyword);
            int countKeyword = serviceResultKeyword.getResult();
            if (countKeyword > 0) {
                return jsonResult;
            }
        }

        String searchKeyword = "(" + keyword + ")";
        int count = 0;
        SolrClient client = getSolrClient();
        SolrQuery query = new SolrQuery();

        int start = 0, size = 0;
        start = (pageNum - 1) * ConstantsEJS.DEFAULT_PAGE_SIZE;
        size = ConstantsEJS.DEFAULT_PAGE_SIZE;

        query.setQuery(queryKeyWord(searchKeyword));

        query.set("start", start);
        query.set("rows", size);
        query.set("sort", SearchProductVO.SORT_ + " desc");

        query.setHighlight(true);
        query.setHighlightSimplePre("<font color=\"red\">");
        query.setHighlightSimplePost("</font>");
        query.setParam("hl.fl", SearchProductVO.NAME1_);

        List<Product> products = new ArrayList<Product>();
        QueryResponse response = null;
        try {
            response = client.query(query);
            SolrDocumentList docs = response.getResults();
            count = new Integer(docs.getNumFound() + "");
            for (SolrDocument doc : docs) {
                String id = (String) doc.getFieldValue(SearchProductVO.ID_);
                Product product = queryResult(response, doc, id);
                products.add(product);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        jsonResult.setRows(products);
        jsonResult.setTotal(products.size());
        return jsonResult;
    }

    /**
     * 调整到搜索页面
     * @param request
     * @param keyword
     * @param stack
     * @return
     */
    @RequestMapping(value = "/search-index.html", method = RequestMethod.GET)
    public String searchIndex(HttpServletRequest request, Map<String, Object> stack) {
        List<String> keywords = getKeywords();
        stack.put("keywords", keywords);
        stack.put("keywordNumber", 0);
        stack.put("pagesize", ConstantsEJS.DEFAULT_PAGE_SIZE);
        return "h5/product/productsearch";
    }

    /**
     * 获取设置的查询关键字
     * @return
     */
    private List<String> getKeywords() {
        ServiceResult<SearchSetting> serviceResult = searchSettingService
            .getSearchSettingById(ConstantsEJS.SEARCHSETTINGID);
        if (!serviceResult.getSuccess()) {
            if (ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR.equals(serviceResult.getCode())) {
                throw new RuntimeException(serviceResult.getMessage());
            } else {
                throw new BusinessException(serviceResult.getMessage());
            }
        }
        SearchSetting searchSetting = serviceResult.getResult();
        String keyword = searchSetting.getKeyword();

        keyword = StringUtil.trim(StringUtil.nullSafeString(keyword));

        List<String> keywords = new ArrayList<String>();
        if (!"".equals(keyword)) {
            String[] strings = keyword.split(",");
            for (String string : strings) {
                string = StringUtil.trim(StringUtil.nullSafeString(string));
                if (!"".equals(string)) {
                    keywords.add(string);
                }
            }
        }
        return keywords;
    }

}
