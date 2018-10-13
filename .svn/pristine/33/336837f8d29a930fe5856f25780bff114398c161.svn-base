package com.sln.service.operate;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.operate.Park;

import java.util.List;
import java.util.Map;

public interface IParkService {

    ServiceResult<Park> getParkById(Integer id);

	 /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<Park>> page(Map<String, String> queryMap, PagerInfo pager);
    
    /**
     * 新增园区
     * 
     */
    ServiceResult<Integer> insertPark(Park park);
    
    /**
     * 修改园区信息与状态
     */
    ServiceResult<Integer> updateParkById(Park park);
    
    /**
     * 批量修改园区信息与状态
     */
    ServiceResult<Integer> batchUpdateParkById(String ids,Integer state);

    /**
     * 取出所有园区信息
     */
    ServiceResult<List<Park>> getParkList();

    /**
     * 根据园区进行分组查询下面的业务管理方
     */
    ServiceResult<List<Park>>getOperationsGroupByParkId();

   /*
   * 根据区域取出园区
   * */
   ServiceResult<List<Park>>getParkByArea(String area);

   /*获取园区的区域*/
   ServiceResult<List<String>>getArea();
}