package com.sln.service.impl.promotion;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.integral.ActIntegral;
import com.sln.entity.integral.ActIntegralType;
import com.sln.model.promotion.ActIntegralModel;
import com.sln.service.promotion.IActIntegralService;

@Service(value = "actIntegralService")
public class ActIntegralServiceImpl implements IActIntegralService {
    private static Logger    log = LogManager.getLogger(ActIntegralServiceImpl.class);

    @Resource
    private ActIntegralModel actIntegralModel;

    /**
    * 根据id取得积分商城
    * @param  actIntegralId
    * @return
    */
    @Override
    public ServiceResult<ActIntegral> getActIntegralById(Integer actIntegralId) {
        ServiceResult<ActIntegral> result = new ServiceResult<ActIntegral>();
        try {
            result.setResult(actIntegralModel.getActIntegralById(actIntegralId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralService][getActIntegralById]根据id[" + actIntegralId
                      + "]取得积分商城时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存积分商城
     * @param  actIntegral
     * @return
     */
    @Override
    public ServiceResult<Integer> saveActIntegral(ActIntegral actIntegral) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actIntegralModel.saveActIntegral(actIntegral));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralService][saveActIntegral]保存积分商城时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新积分商城
    * @param  actIntegral
    * @return
    * @see com.sln.service.ActIntegralService#updateActIntegral(ActIntegral)
    */
    @Override
    public ServiceResult<Integer> updateActIntegral(ActIntegral actIntegral) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actIntegralModel.updateActIntegral(actIntegral));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralService][updateActIntegral]更新积分商城时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 根据id取得积分商城分类
     * @param  actIntegralTypeId
     * @return
     */
    @Override
    public ServiceResult<ActIntegralType> getActIntegralTypeById(Integer actIntegralTypeId) {
        ServiceResult<ActIntegralType> result = new ServiceResult<ActIntegralType>();
        try {
            result.setResult(actIntegralModel.getActIntegralTypeById(actIntegralTypeId));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegralTypeById]根据id[" + actIntegralTypeId
                      + "]取得积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 保存积分商城分类
     * @param  actIntegralType
     * @return
     */
    @Override
    public ServiceResult<Integer> saveActIntegralType(ActIntegralType actIntegralType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actIntegralModel.saveActIntegralType(actIntegralType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][saveActIntegralType]保存积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
    * 更新积分商城分类
    * @param  actIntegralType
    * @return
    * @see com.sln.service.ActIntegralTypeService#updateActIntegralType(ActIntegralType)
    */
    @Override
    public ServiceResult<Integer> updateActIntegralType(ActIntegralType actIntegralType) {
        ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
            result.setResult(actIntegralModel.updateActIntegralType(actIntegralType));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][updateActIntegralType]更新积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询积分商城分类
     * @param queryMap
     * @param pager
     * @return
     * @see com.sln.service.promotion.IActIntegralService#getActIntegralTypes(java.util.Map, com.sln.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActIntegralType>> getActIntegralTypes(Map<String, String> queryMap,
                                                                    PagerInfo pager) {
        ServiceResult<List<ActIntegralType>> result = new ServiceResult<List<ActIntegralType>>();
        result.setPager(pager);
        try {
            result.setResult(actIntegralModel.getActIntegralTypes(queryMap, pager));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegralTypes]查询积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 删除积分商城分类
     * @param id
     * @return
     * @see com.sln.service.promotion.IActIntegralService#delActIntegralType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> delActIntegralType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.delActIntegralType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][delActIntegralType]删除积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 启用积分商城分类
     * @param id
     * @return
     * @see com.sln.service.promotion.IActIntegralService#auditYesActIntegralType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditYesActIntegralType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.auditYesActIntegralType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][auditYesActIntegralType]启用积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 停用积分商城分类
     * @param id
     * @return
     * @see com.sln.service.promotion.IActIntegralService#auditNoActIntegralType(java.lang.Integer)
     */
    @Override
    public ServiceResult<Boolean> auditNoActIntegralType(Integer id) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.auditNoActIntegralType(id));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][auditNoActIntegralType]停用积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有积分商城信息
     * @param queryMap
     * @param pager
     * @return
     * @see com.sln.service.promotion.IActIntegralService#getActIntegrals(java.util.Map, com.sln.core.PagerInfo)
     */
    @Override
    public ServiceResult<List<ActIntegral>> getActIntegrals(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<ActIntegral>> result = new ServiceResult<List<ActIntegral>>();
        result.setPager(pager);
        try {
            result.setResult(actIntegralModel.getActIntegrals(queryMap, pager));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegrals]查询积分商城信息时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的积分商城分类
     * @return
     * @see com.sln.service.promotion.IActIntegralService#getActIntegralTypesAll()
     */
    @Override
    public ServiceResult<List<ActIntegralType>> getActIntegralTypesAll() {
        ServiceResult<List<ActIntegralType>> result = new ServiceResult<List<ActIntegralType>>();
        try {
            result.setResult(actIntegralModel.getActIntegralTypesAll());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegralTypesAll]查询所有可用的积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更改积分商城的状态
     * @param id
     * @param state
     * @return
     * @see com.sln.service.promotion.IActIntegralService#updateActIntegralState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActIntegralState(Integer id, int state) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.updateActIntegralState(id, state));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][updateActIntegralState]更改积分商城的状态时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 更改积分商城的活动状态
     * @param id
     * @param activityState
     * @return
     * @see com.sln.service.promotion.IActIntegralService#updateActIntegralActivityState(java.lang.Integer, int)
     */
    @Override
    public ServiceResult<Boolean> updateActIntegralActivityState(Integer id, int activityState) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.updateActIntegralActivityState(id, activityState));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActIntegralTypeService][updateActIntegralActivityState]更改积分商城的活动状态时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 平台管理员审核，审核通过之后更改状态，并填写审核意见
     * @param id
     * @param state
     * @param auditOpinion
     * @return
     * @see com.sln.service.promotion.IActIntegralService#updateActIntegralStateAndAuditOpinion(java.lang.Integer, int, java.lang.String)
     */
    @Override
    public ServiceResult<Boolean> updateActIntegralStateAndAuditOpinion(Integer id, int state,
                                                                        String auditOpinion) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(actIntegralModel.updateActIntegralStateAndAuditOpinion(id, state,
                auditOpinion));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error(
                "[IActIntegralTypeService][updateActIntegralStateAndAuditOpinion]平台管理员审核，审核通过之后更改状态，并填写审核意见出现未知异常：",
                e);
        }
        return result;
    }

    /**
     * 积分商城前台查看列表页
     * @param page
     * @param type
     * @param channel
     * @param grade
     * @param sort
     * @return
     * @see com.sln.service.promotion.IActIntegralService#getActIntegralsFront(com.sln.core.PagerInfo, int, int, int, int)
     */
    @Override
    public ServiceResult<List<ActIntegral>> getActIntegralsFront(PagerInfo pager, int type,
                                                                 int channel, int grade, int sort,String keyword) {
        ServiceResult<List<ActIntegral>> result = new ServiceResult<List<ActIntegral>>();
        result.setPager(pager);
        try {
            result.setResult(actIntegralModel.getActIntegralsFront(pager, type, channel, grade,
                sort,keyword));
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegrals]查询积分商城信息时出现未知异常：", e);
        }
        return result;
    }

    /**
     * 查询所有可用的积分商城分类
     * @return
     * @see com.sln.service.promotion.IActIntegralService#getActIntegralTypesFront()
     */
    @Override
    public ServiceResult<List<ActIntegralType>> getActIntegralTypesFront() {
        ServiceResult<List<ActIntegralType>> result = new ServiceResult<List<ActIntegralType>>();
        try {
            result.setResult(actIntegralModel.getActIntegralTypesFront());
        } catch (BusinessException e) {
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegralTypesFront]查询所有可用的积分商城分类时出现未知异常：", e);
        }
        return result;
    }

    @Override
    public ServiceResult<List<ActIntegral>> getActIntegralsByType(Integer type, Integer topNum) {
        ServiceResult<List<ActIntegral>> result = new ServiceResult<List<ActIntegral>>();
        try {
            result.setResult(actIntegralModel.getActIntegralsByType(type, topNum));
        } catch (BusinessException e) {
            result.setSuccess(false);
            result.setMessage(e.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[IActIntegralTypeService][getActIntegralsByType]根据类型查询topNum条积分商城时出现未知异常：",
                e);
        }
        return result;
    }
}