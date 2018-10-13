package com.sln.service.promotion;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.integral.ActIntegral;
import com.sln.entity.integral.ActIntegralType;

public interface IActIntegralService {

    /**
     * 根据id取得积分商城
     * @param  actIntegralId
     * @return
     */
    ServiceResult<ActIntegral> getActIntegralById(Integer actIntegralId);

    /**
     * 保存积分商城
     * @param  actIntegral
     * @return
     */
    ServiceResult<Integer> saveActIntegral(ActIntegral actIntegral);

    /**
    * 更新积分商城
    * @param  actIntegral
    * @return
    */
    ServiceResult<Integer> updateActIntegral(ActIntegral actIntegral);

    /**
     * 根据id取得积分商城分类
     * @param  actIntegralTypeId
     * @return
     */
    ServiceResult<ActIntegralType> getActIntegralTypeById(Integer actIntegralTypeId);

    /**
     * 保存积分商城分类
     * @param  actIntegralType
     * @return
     */
    ServiceResult<Integer> saveActIntegralType(ActIntegralType actIntegralType);

    /**
    * 更新积分商城分类
    * @param  actIntegralType
    * @return
    */
    ServiceResult<Integer> updateActIntegralType(ActIntegralType actIntegralType);

    /**
     * 查询积分商城分类
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActIntegralType>> getActIntegralTypes(Map<String, String> queryMap,
                                                             PagerInfo pager);

    /**
     * 删除积分商城分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> delActIntegralType(Integer id);

    /**
     * 启用积分商城分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditYesActIntegralType(Integer id);

    /**
     * 停用积分商城分类
     * @param id
     * @return
     */
    ServiceResult<Boolean> auditNoActIntegralType(Integer id);

    /**
     * 查询所有积分商城信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ActIntegral>> getActIntegrals(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 查询所有可用的积分商城分类
     * @return
     */
    ServiceResult<List<ActIntegralType>> getActIntegralTypesAll();

    /**
     * 更改积分商城的审核状态
     * @param id
     * @param state2
     * @return
     */
    ServiceResult<Boolean> updateActIntegralState(Integer id, int state2);

    /**
     * 更改积分商城的活动状态
     * @param id
     * @param activityState
     * @return
     */
    ServiceResult<Boolean> updateActIntegralActivityState(Integer id, int activityState);

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     */
    ServiceResult<Boolean> updateActIntegralStateAndAuditOpinion(Integer id, int state,
                                                                 String auditOpinion);

    /**
     * 积分商城前台查看
     * @param page 分页
     * @param type 积分商城类型
     * @param channel 渠道
     * @param grade 等级
     * @param sort 排序 0：默认；1、最新；2、销量；3、价格从低到高；4、价格从高到低
     * @return
     */
    ServiceResult<List<ActIntegral>> getActIntegralsFront(PagerInfo page, int type, int channel,
                                                          int grade, int sort,String keyword);

    /**
     * 查询所有可用的积分商城分类
     * @return
     */
    ServiceResult<List<ActIntegralType>> getActIntegralTypesFront();

    /**
     * 根据类型查询topNum条积分商城
     * @param type
     * @param topNum
     * @return
     */
    ServiceResult<List<ActIntegral>> getActIntegralsByType(Integer type, Integer topNum);
}