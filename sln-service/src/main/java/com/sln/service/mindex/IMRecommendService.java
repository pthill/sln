package com.sln.service.mindex;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.mindex.MRecommend;

public interface IMRecommendService {

    /**
     * 根据id取得M端推荐商品
     * @param  pcRecommendId
     * @return
     */
    ServiceResult<MRecommend> getMRecommendById(Integer pcRecommendId);

    /**
     * 保存M端推荐商品
     * @param  pcRecommend
     * @return
     */
    ServiceResult<Boolean> saveMRecommend(MRecommend pcRecommend);

    /**
     * 更新M端推荐商品
     * @param pcRecommend
     * @return
     */
    ServiceResult<Boolean> updateMRecommend(MRecommend pcRecommend);

    /**
     * 删除M端推荐商品
     * @param  pcRecommend
     * @return
     */
    ServiceResult<Boolean> deleteMRecommend(Integer pcRecommendId);

    /**
     * 根据条件取得M端推荐商品
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<MRecommend>> getMRecommends(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 取得M端推荐商品（当前时间在展示时间范围内的推荐商品）<br>
     * <li>如果isPreview=true取所有的商品
     * <li>如果isPreview=false取使用状态的推荐商品
     * 
     * @param recommendType 推荐类型：固定为1：多惠部落
     * @param isPreview
     * @return
     */
    ServiceResult<List<MRecommend>> getMRecommendForView(Integer recommendType, Boolean isPreview,
                                                         PagerInfo pager);

}