package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.IndexBanner;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalIndexBannerWriteDao {
    Integer insert(IndexBanner indexBanner);
    Integer update(IndexBanner indexBanner);
    Integer delete(Integer id);
}
