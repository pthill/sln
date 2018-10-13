package com.sln.service.impl.news;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.news.News;
import com.sln.entity.news.NewsType;
import com.sln.model.news.NewsModel;
import com.sln.service.news.INewsService;

@Service(value = "newsService")
public class NewsServiceImpl implements INewsService {
    private static Logger log = Logger.getLogger(NewsServiceImpl.class);

    @Resource
    private NewsModel     newsModel;

    /**
     * 首页新闻类型<br>
     * 该方法只会查询最多六条数据
     * @return
     */
    @Override
    public ServiceResult<List<NewsType>> getNewsType() {

        ServiceResult<List<NewsType>> serviceResult = new ServiceResult<List<NewsType>>();
        try {
            serviceResult.setResult(newsModel.getNewsType());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[NewsService][getNewsType]查询首页新闻类型发生异常:", e);
        }
        return serviceResult;

    }

    /**
     * @param queryMap
     * @param pager
     * @return
     */
    @Override
    public ServiceResult<List<News>> getNewsByType(int typeId, PagerInfo pager) {

        ServiceResult<List<News>> serviceResult = new ServiceResult<List<News>>();
        serviceResult.setPager(pager);
        try {
            List<News> list = newsModel.getNewsByType(typeId, pager);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[NewsService][getNewsByType]查询给定分类下所有新闻发生异常::", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<News> get(Integer id) {
        ServiceResult<News> serviceResult = new ServiceResult<News>();
        try {
            serviceResult.setResult(newsModel.get(id));
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[NewsService][get]查询新闻发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<NewsType>> getNewsType4Article() {
        ServiceResult<List<NewsType>> serviceResult = new ServiceResult<List<NewsType>>();
        try {
            serviceResult.setResult(newsModel.getNewsType4Article());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[NewsService][getNewsType4Article]查询首页新闻分类发生异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<News>> getLastedNews() {
        ServiceResult<List<News>> serviceResult = new ServiceResult<List<News>>();
        try {
            serviceResult.setResult(newsModel.getLastedNews());
        } catch (BusinessException be) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(be.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[NewsService][getLastedNews]查询最新新闻发生异常:", e);
        }
        return serviceResult;
    }

    /**
     * 根据id取得新闻资讯
     * @param  newsId
     * @return
     */
    @Override
    public ServiceResult<News> getNewsById(Integer newsId) {
        ServiceResult<News> result = new ServiceResult<News>();
        try {
            result.setResult(newsModel.getNewId(newsId));
        } catch (Exception e) {
            log.error("[NewsServiceImpl][getNewsById]根据id[" + newsId + "]取得新闻资讯时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("根据id[" + newsId + "]取得新闻资讯时出现未知异常");
        }
        return result;
    }

    /**
     * 保存新闻资讯
     * @param  news
     * @return
     */
    @Override
    public ServiceResult<Integer> saveNews(News news) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsModel.save(news));
        } catch (Exception e) {
            log.error("保存新闻资讯时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("保存新闻资讯时出现未知异常");
        }
        return result;
    }

    /**
    * 更新新闻资讯
    * @param  news
    * @return
    */
    @Override
    public ServiceResult<Integer> updateNews(News news) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();

        try {
            result.setResult(newsModel.update(news));
        } catch (Exception e) {
            log.error("更新新闻资讯时出现未知异常：" + e);
            result.setSuccess(false);
            result.setMessage("更新新闻资讯时出现未知异常");
        }
        return result;
    }

    @Override
    public ServiceResult<List<News>> page(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<News>> serviceResult = new ServiceResult<List<News>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(newsModel.getCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<News> list = newsModel.page(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            e.printStackTrace();
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[NewsServiceImpl][save] param1:" + JSON.toJSONString(queryMap) + " &param2:"
                      + JSON.toJSONString(pager));
            log.error("[NewsServiceImpl][page] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> del(Integer id) {

        ServiceResult<Boolean> sr = new ServiceResult<Boolean>();

        try {
            if (id == null)
                throw new BusinessException("删除文章[" + id + "]时出错");
            sr.setSuccess(this.newsModel.del(id) > 0);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[NewsServiceImpl][page] exception:" + e.getMessage());
        }
        return sr;
    }

}