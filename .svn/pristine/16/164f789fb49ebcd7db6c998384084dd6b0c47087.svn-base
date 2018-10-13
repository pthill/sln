package com.sln.service.news;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.news.News;
import com.sln.entity.news.NewsType;

public interface INewsService {

    /**
     * 首页新闻分类
     * @return
     */
    ServiceResult<List<NewsType>> getNewsType();

    /**
     * 获取给定分类下所有新闻
     * @param id
     * @return
     */
    ServiceResult<List<News>> getNewsByType(int typeId, PagerInfo pager);

    ServiceResult<News> get(Integer id);

    /**
     * 首页新闻分类
     * @return
     */
    ServiceResult<List<NewsType>> getNewsType4Article();

    /**
     * 最新新闻
     * @return
     */
    ServiceResult<List<News>> getLastedNews();

    /**
     * 根据id取得新闻资讯
     * @param  newsId
     * @return
     */
    ServiceResult<News> getNewsById(Integer newsId);

    /**
     * 保存新闻资讯
     * @param  news
     * @return
     */
    ServiceResult<Integer> saveNews(News news);

    /**
    * 更新新闻资讯
    * @param  news
    * @return
    */
    ServiceResult<Integer> updateNews(News news);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<News>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}