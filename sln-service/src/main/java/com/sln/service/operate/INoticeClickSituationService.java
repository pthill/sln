package com.sln.service.operate;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.operate.NoticeClickSituation;

public interface INoticeClickSituationService {

    /**
     * 根据id取得商家公告查看情况
     * @param  noticeClickSituationId
     * @return
     */
    ServiceResult<NoticeClickSituation> getNoticeClickSituationById(Integer noticeClickSituationId);

    /**
     * 保存商家公告查看情况
     * @param  noticeClickSituation
     * @return
     */
    ServiceResult<Integer> saveNoticeClickSituation(NoticeClickSituation noticeClickSituation);

    /**
    * 更新商家公告查看情况
    * @param  noticeClickSituation
    * @return
    */
    ServiceResult<Integer> updateNoticeClickSituation(NoticeClickSituation noticeClickSituation);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<NoticeClickSituation>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}