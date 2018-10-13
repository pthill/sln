package com.sln.dao.analysis.read;

import org.springframework.stereotype.Repository;

import com.sln.entity.analysis.BrowseLogMobile;

@Repository
public interface BrowseLogMobileReadDao {

    BrowseLogMobile get(java.lang.Integer id);

}