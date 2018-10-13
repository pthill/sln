package com.sln.model.operate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import com.sln.core.PagerInfo;
import com.sln.dao.shop.write.operate.NoticeClickSituationWriteDao;
import com.sln.entity.operate.NoticeClickSituation;

@Component
public class NoticeClickSituationModel {

    @Resource
    private NoticeClickSituationWriteDao noticeClickSituationWriteDao;
    @Resource
    private DataSourceTransactionManager transactionManager;

    /**
     * 根据id取得商家公告查看情况
     * @param  noticeClickSituationId
     * @return
     */
    public NoticeClickSituation getNoticeClickSituationById(Integer noticeClickSituationId) {
        return noticeClickSituationWriteDao.get(noticeClickSituationId);
    }

    /**
     * 保存商家公告查看情况
     * @param  noticeClickSituation
     * @return
     */
    public Integer saveNoticeClickSituation(NoticeClickSituation noticeClickSituation) {
        this.dbConstrains(noticeClickSituation);
        return noticeClickSituationWriteDao.save(noticeClickSituation);
    }

    /**
    * 更新商家公告查看情况
    * @param  noticeClickSituation
    * @return
    */
    public Integer updateNoticeClickSituation(NoticeClickSituation noticeClickSituation) {
        this.dbConstrains(noticeClickSituation);
        return noticeClickSituationWriteDao.update(noticeClickSituation);
    }

    private void dbConstrains(NoticeClickSituation noticeClickSituation) {
    }

    public List<NoticeClickSituation> page(Map<String, String> queryMap,
                                           PagerInfo pager) throws Exception {
        Map<String, Object> param = new HashMap<String, Object>(queryMap);
        Integer start = 0, size = 0;
        if (pager != null) {
            pager.setRowsCount(noticeClickSituationWriteDao.getCount(param));
            start = pager.getStart();
            size = pager.getPageSize();
        }
        param.put("start", start);
        param.put("size", size);
        List<NoticeClickSituation> list = noticeClickSituationWriteDao.page(param);
        return list;
    }

    public Integer del(Integer id) {
        return noticeClickSituationWriteDao.del(id);
    }

}