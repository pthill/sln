package com.sln.dao.analysis.write;

import org.springframework.stereotype.Repository;

import com.sln.entity.analysis.BrowseLogMobile;

@Repository
public interface BrowseLogMobileWriteDao {

    BrowseLogMobile get(java.lang.Integer id);

    Integer insert(BrowseLogMobile browseLog);

    Integer update(BrowseLogMobile browseLog);
}