package com.sln.service.news;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.news.NewsPartner;

public interface INewsPartnerService {

    /**
     * 根据id取得合作伙伴
     * @param  newsPartnerId
     * @return
     */
    ServiceResult<NewsPartner> getById(Integer newsPartnerId);

    /**
     * 保存合作伙伴
     * @param  newsPartner
     * @return
     */
    ServiceResult<Integer> save(NewsPartner newsPartner);

    /**
    * 更新合作伙伴
    * @param  newsPartner
    * @return
    */
    ServiceResult<Integer> update(NewsPartner newsPartner);

    /**
     * 分页查询
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<NewsPartner>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);
}