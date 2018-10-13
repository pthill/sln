package com.sln.dao.shop.write.portal;

import com.sln.entity.portal.PortalService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalServiceWriteDao {

    Integer insert(PortalService portalService);

    Integer update(PortalService portalService);

    Integer del(@Param("id") Integer id);
}
