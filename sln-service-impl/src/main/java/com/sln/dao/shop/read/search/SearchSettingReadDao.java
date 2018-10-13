package com.sln.dao.shop.read.search;

import org.springframework.stereotype.Repository;

import com.sln.entity.search.SearchSetting;

@Repository
public interface SearchSettingReadDao {

    SearchSetting get(java.lang.Integer id);

}