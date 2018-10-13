package com.sln.web.controller.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sln.core.ConstantsEJS;
import com.sln.core.ConvertUtil;
import com.sln.core.HttpJsonResult;
import com.sln.core.PagerInfo;
import com.sln.core.PaginationUtil;
import com.sln.core.ServiceResult;
import com.sln.core.WebUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.entity.news.News;
import com.sln.entity.news.NewsType;
import com.sln.service.news.INewsService;
import com.sln.service.news.INewsTypeService;
import com.sln.web.controller.BaseController;

@Controller
public class NewsController extends BaseController {

    @Resource
    private INewsService     newsService;
    @Resource
    private INewsTypeService newsTypeService;

    /**
     * 首页footer
     * @param request
     * @param response
     * @param id
     */
    @RequestMapping(value = "news/footerNews.html", method = { RequestMethod.GET })
    public @ResponseBody HttpJsonResult<List<NewsType>> footerNews(HttpServletRequest request,
                                                HttpServletResponse response,
                                                Map<String, Object> dataMap) {
        HttpJsonResult<List<NewsType>> jsonResult = new HttpJsonResult<List<NewsType>>();

        ServiceResult<List<NewsType>> newstypes = newsService.getNewsType();
        List<NewsType> newstypesList = newstypes.getResult();
        for (NewsType newstype : newstypes.getResult()) {
            PagerInfo pager = new PagerInfo(5, 1);
            newstype.setNews(newsService.getNewsByType(newstype.getId(), pager).getResult());
        }

        jsonResult.setData(newstypesList);
        return jsonResult;
    }

    /**
     * 文章详细页面 
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "news/{id}.html", method = RequestMethod.GET)
    public String article(HttpServletRequest request, Map<String, Object> dataMap,
                          @PathVariable String id) {
        ServiceResult<News> sr = newsService.get(ConvertUtil.toInt(id, 0));
        if (!sr.getSuccess()) {
            throw new BusinessException(sr.getMessage());
        }
        News news = sr.getResult();
        if (!isNull(news.getContent())) {
            news.setContent(news.getContent().replace("${(domainUrlUtil.SLN_IMAGE_RESOURCES)!}",
                DomainUrlUtil.getSLN_IMAGE_RESOURCES()));
        }

        ServiceResult<List<NewsType>> serviceRrsult = newsService.getNewsType4Article();
        if (!serviceRrsult.getSuccess()) {
            throw new BusinessException(serviceRrsult.getMessage());
        }

        ServiceResult<List<News>> resultNews = newsService.getLastedNews();
        if (!resultNews.getSuccess()) {
            throw new BusinessException(resultNews.getMessage());
        }

        ServiceResult<NewsType> typeRlt = newsTypeService.getNewsTypeById(news.getTypeId());
        if (!typeRlt.getSuccess()) {
            throw new BusinessException(typeRlt.getMessage());
        }

        dataMap.put("news", news);
        dataMap.put("currType", typeRlt.getResult());
        dataMap.put("newsTypes", serviceRrsult.getResult());
        dataMap.put("lastedNews", resultNews.getResult());

        return "front/news/article";
    }

    /**
     * 文章列表页面
     * @param request
     * @param dataMap
     * @param typeid
     * @return
     */
    @RequestMapping(value = "news/type_{typeId}.html", method = { RequestMethod.GET })
    public String typeNews(HttpServletRequest request, Map<String, Object> dataMap,
                           @PathVariable Integer typeId) {
        ServiceResult<List<NewsType>> serviceRrsult = newsService.getNewsType4Article();
        if (!serviceRrsult.getSuccess()) {
            throw new BusinessException(serviceRrsult.getMessage());
        }

        ServiceResult<List<News>> resultNews = newsService.getLastedNews();
        if (!resultNews.getSuccess()) {
            throw new BusinessException(resultNews.getMessage());
        }

        PagerInfo pager = WebUtil.handlerPagerInfo(request, dataMap,
            ConstantsEJS.DEFAULT_PAGE_SIZE);

        ServiceResult<List<News>> list = newsService.getNewsByType(typeId, pager);
        dataMap.put("newslist", list.getResult());

        ServiceResult<NewsType> typeRlt = newsTypeService.getNewsTypeById(typeId);
        if (!typeRlt.getSuccess()) {
            throw new BusinessException(typeRlt.getMessage());
        }

        //分页对象
        PaginationUtil pm = new PaginationUtil(pager.getPageSize(),
            String.valueOf(pager.getPageIndex()), pager.getRowsCount(), request.getRequestURI());

        dataMap.put("currType", typeRlt.getResult());
        dataMap.put("newsTypes", serviceRrsult.getResult());
        dataMap.put("lastedNews", resultNews.getResult());
        dataMap.put("page", pm);

        return "front/news/articlelist";
    }

}
