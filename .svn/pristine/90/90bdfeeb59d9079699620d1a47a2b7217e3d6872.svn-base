package com.sln.model.news;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.PagerInfo;
import com.sln.dao.shop.read.news.NewsReadDao;
import com.sln.dao.shop.write.news.NewsWriteDao;
import com.sln.entity.news.News;
import com.sln.entity.news.NewsType;

@Component(value = "newsModel")
public class NewsModel {

    @Resource
    private NewsReadDao  newsReadDao;

    @Resource
    private NewsWriteDao newsWriteDao;

    /**
     * 首页新闻类型<br>
     * 该方法只会查询最多六条数据
     * @return
     */
    public List<NewsType> getNewsType() {
        return newsReadDao.getNewsType(0, 6);
    }

    /**
     * @param queryMap
     * @param pager
     * @return
     */
    public List<News> getNewsByType(int typeId, PagerInfo pager) {

        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(newsReadDao.countNewsByTypeId(typeId));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        List<News> newslist = newsReadDao.getNewsByTypeId(typeId, start, size);
        return newslist;
    }

    public News get(Integer id) throws IllegalAccessException, InvocationTargetException,
                                NoSuchMethodException {
        return newsReadDao.get(id);
    }

    @SuppressWarnings("unchecked")
    public List<NewsType> getNewsType4Article() {
        return newsReadDao.getNewsType(0, 6);
    }

    public List<News> getLastedNews() {
        return newsReadDao.getLastedNews(0, 4);
    }

    public int del(Integer id) {
        return newsWriteDao.del(id);
    }

    public List<News> page(Map<String, String> queryMap, Integer start, Integer size) {
        return newsReadDao.page(queryMap, start, size);
    }

    public int getCount(Map<String, String> queryMap) {
        return newsReadDao.getCount(queryMap);
    }

    public Integer update(News news) {
        return newsWriteDao.update(news);
    }

    public Integer save(News news) {
        return newsWriteDao.save(news);
    }

    public News getNewId(Integer newsId) {
        return newsWriteDao.get(newsId);
    }

}
