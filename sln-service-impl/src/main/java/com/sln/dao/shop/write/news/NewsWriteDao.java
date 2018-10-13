package com.sln.dao.shop.write.news;

import org.springframework.stereotype.Repository;

import com.sln.entity.news.News;

@Repository
public interface NewsWriteDao {

    News get(java.lang.Integer id);

    Integer save(News news);

    Integer update(News news);

    Integer del(Integer id);
}