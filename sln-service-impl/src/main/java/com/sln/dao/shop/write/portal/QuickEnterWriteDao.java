package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.QuickEnter;

public interface QuickEnterWriteDao {
    Integer insert(QuickEnter quickEnter);
    Integer update(QuickEnter quickEnter);
    Integer del(Integer id);
}
