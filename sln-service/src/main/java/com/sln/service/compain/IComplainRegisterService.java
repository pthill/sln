package com.sln.service.compain;
import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.compain.ComplainRegister;

public interface IComplainRegisterService {

	/**
     * 根据id取得投诉登记表
     * @param  complainRegisterId
     * @return
     */
    ServiceResult<ComplainRegister> getComplainRegisterById(Integer complainRegisterId);
    
    /**
     * 保存投诉登记表
     * @param  complainRegister
     * @return
     */
     ServiceResult<Integer> saveComplainRegister(ComplainRegister complainRegister);
     
     /**
     * 更新投诉登记表
     * @param  complainRegister
     * @return
     */
     ServiceResult<Integer> updateComplainRegister(ComplainRegister complainRegister);

     /*
      * 获取列表
      */
	ServiceResult<List<ComplainRegister>> getComplainRegister(Map<String, String> queryMap, PagerInfo pager);

	//删除操作
	ServiceResult<Boolean> deleteById(Integer id);
}