package com.sln.service.operate;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.operate.OperationManager;

import java.util.List;
import java.util.Map;

public interface IOperationManagerService {
    /**
     * 根据id取得业务管理方
     * @param  id
     * @return
     */
    ServiceResult<OperationManager> getOperationMemberById(Integer id);

    ServiceResult<OperationManager> getByNameAndParkId(Integer parkId,String name);

    /**
     * 保存业务管理方表
     * @param  manager
     * @return
     */
    ServiceResult<Integer> save(OperationManager manager);

    /**
     * 更新业务管理方表
     * @param  manager
     * @return
     */
    ServiceResult<Boolean> update(OperationManager manager);

    /**
     * 更新业务管理方状态
     * @param  id
     * @param  status
     * @return
     */
    ServiceResult<Boolean> updateStatus(Integer id,String status);

    ServiceResult<List<OperationManager>> getOperationManagers(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 编号code是否存在
     * @param code
     * @return
     */

    /**
     * 查看某一园区下面的业务管理方
     * @param parkId
     * @return
     */
    ServiceResult<List<OperationManager>> getManagersByParkId(Integer parkId);
}
