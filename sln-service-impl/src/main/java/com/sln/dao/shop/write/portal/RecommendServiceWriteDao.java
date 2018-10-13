package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.RecommendService;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendServiceWriteDao {
    Integer insert(RecommendService recommendService);

    Integer update(RecommendService recommendService);

    Integer del(Integer id);
}
