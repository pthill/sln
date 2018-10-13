package com.sln.service.system;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.operate.SystemNotice;

public interface ISystemNoticeService {

    /**
     * 根据id取得商城公告
     * @param  systemNoticeId
     * @return
     */
    ServiceResult<SystemNotice> getSystemNoticeById(Integer systemNoticeId);

    /**
     * 保存商城公告
     * @param  systemNotice
     * @return
     */
    ServiceResult<Integer> saveSystemNotice(SystemNotice systemNotice);

    /**
    * 更新商城公告
    * @param  systemNotice
    * @return
    */
    ServiceResult<Integer> updateSystemNotice(SystemNotice systemNotice);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SystemNotice>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 公告详情
     * @param id
     * @param integer 
     * @return
     */
    ServiceResult<SystemNotice> toDetail(Integer id, Integer sellerid);

    /**
     * 未读公告
     * @param sellerId
     * @param pager 
     * @return
     */
    ServiceResult<List<SystemNotice>> getUnreadNotice(Integer sellerId, PagerInfo pager);
}