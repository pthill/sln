package com.sln.service.portal;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.portal.RecommendService;

import java.util.List;
import java.util.Map;

public interface IRecommendServiceService {

    /**
     * 根据id取得recommend_service对象
     * @param  recommendServiceId
     * @return
     */
    ServiceResult<RecommendService> getRecommendServiceById(Integer recommendServiceId);

    /*获取园区下的推荐服务*/
    ServiceResult<List<RecommendService>> getByParkId(Integer parkId);

    /**
     * 保存recommend_service对象
     * @param  recommendService
     * @return
     */
    ServiceResult<Integer> saveRecommendService(RecommendService recommendService);

    /**
     * 更新recommend_service对象
     * @param  recommendService
     * @return
     */
    ServiceResult<Integer> updateRecommendService(RecommendService recommendService);

    ServiceResult<Integer> onOrOff(Integer id,String state);

    ServiceResult<Integer>del(Integer id);

    ServiceResult<List<RecommendService>> page(Map<String, String> queryMap, PagerInfo pager);
}
