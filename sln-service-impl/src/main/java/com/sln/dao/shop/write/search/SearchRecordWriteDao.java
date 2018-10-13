package com.sln.dao.shop.write.search;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sln.entity.search.SearchRecord;

@Repository
public interface SearchRecordWriteDao {

    SearchRecord get(java.lang.Integer id);

    Integer insert(SearchRecord searchRecord);

    Integer update(SearchRecord searchRecord);

    int del(Integer id);

    List<SearchRecord> getSearchRecordsAll();
}