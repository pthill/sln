package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.ParkAdvantage;

import java.util.List;
import java.util.Map;

public interface IParkAdvantageService {
    /**
     * 根据id取得park_advantage对象
     * @param  parkAdvantageId
     * @return
     */
    ServiceResult<ParkAdvantage> getParkAdvantageById(Integer parkAdvantageId);

    /**
     * 保存park_advantage对象
     * @param  parkAdvantage
     * @return
     */
    ServiceResult<Integer> saveParkAdvantage(ParkAdvantage parkAdvantage);

    /**
     * 更新park_advantage对象
     * @param  parkAdvantage
     * @return
     */
    ServiceResult<Integer> updateParkAdvantage(ParkAdvantage parkAdvantage);


    ServiceResult<Integer> onOrOff(Integer id,String state);


    ServiceResult<List<ParkAdvantage>> page(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<Integer> del(Integer id);

    //门户园区优势
    ServiceResult<List<ParkAdvantage>>getByParkId(Integer parkId);
}
