package com.sln.dao.shop.read.operate;

import com.sln.entity.operate.OperationManager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface OperationManagerReadDao {
    OperationManager get(Integer id);

    OperationManager getByNameAndParkId(@Param("parkId") Integer parkId,@Param("name")String name);

    Integer getManagersCount(@Param("queryMap") Map<String, String> queryMap);

    List<OperationManager> getManagers(@Param("queryMap") Map<String, String> queryMap,
                                       @Param("start") Integer start, @Param("size") Integer size);
    Integer isCodeExists(@Param("code") String code,@Param("id")Integer id);

    List<OperationManager> getManagersByParkId(Integer parkId);

    Integer isNameExists(@Param("name") String name,@Param("parkId") String parkId,@Param("id")Integer id);
}
