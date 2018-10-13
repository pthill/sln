package com.sln.dao.shop.write.system;

import com.sln.entity.system.SystemResources;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SystemResourcesWriteDao {

    SystemResources get(java.lang.Integer id);

    Integer save(SystemResources systemResources);

    Integer update(SystemResources systemResources);

    Integer getCount(Map<String, String> queryMap);

    List<SystemResources> page(Map<String, String> queryMap);

    List<SystemResources> list(Map<String, Object> queryMap);

    Integer del(Integer id);

    List<SystemResources> getByPidAndType(Map<String, Object> queryMap);

	List<SystemResources> getSystemResourceSlist(Map<String, Object> param);


}
