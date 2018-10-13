package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.PortalActive;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalActiveWriteDao {

    Integer insert(PortalActive portalActive);

    Integer update(PortalActive portalActive);

    Integer del(Integer id);



}
