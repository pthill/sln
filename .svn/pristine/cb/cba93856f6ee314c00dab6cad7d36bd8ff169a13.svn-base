package com.sln.service.impl.seller;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerParkOperation;
import com.sln.model.seller.SellerApplyModel;
import com.sln.service.seller.ISellerApplyService;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service(value = "sellerApplyService")
public class SellerApplyServiceImpl implements ISellerApplyService {

    private static Logger    log = LogManager.getLogger(SellerApplyServiceImpl.class);

    @Resource
    private SellerApplyModel sellerApplyModel;

    @Override
    public ServiceResult<Boolean> updateSellerApply(SellerApply sellerApply) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.updateSellerApply(sellerApply));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][updateSellerApply]修改商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error(
                "[SellerApplyService][updateSellerApply] param:" + JSON.toJSONString(sellerApply));
            log.error("[SellerApplyService][updateSellerApply]修改商家申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApply>> getSellerApplys(Map<String, String> queryMap,
                                                            PagerInfo pager) {
        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerApplyModel.getSellerApplysCount(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }

            List<SellerApply> list = sellerApplyModel.getSellerApplys(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[SellerApplyService][getSellerApplys]根据条件分页查询商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyService][getSellerApplys] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerApplyService][getSellerApplys] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApply>> getSellerApplysByRoleId(Map<String, String> queryMap, PagerInfo pager) {
        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
        try {
            Integer start = 0, size = 0;
            if (pager != null) {
                pager.setRowsCount(sellerApplyModel.getSellerApplyCountByRole(queryMap));
                start = pager.getStart();
                size = pager.getPageSize();
            }
            List<SellerApply> list = sellerApplyModel.getSellerApplysByRole(queryMap, start, size);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[SellerApplyService][getSellerApplysByRoleId]根据条件分页查询商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyService][getSellerApplysByRoleId] param1:" + JSON.toJSONString(queryMap)
                      + " &param2:" + JSON.toJSONString(pager));
            log.error("[SellerApplyService][getSellerApplysByRoleId] exception:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId) {

        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.delete(id, memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][deleteSellerApply]删除商家申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerApply> getSellerApplyById(Integer sellerApplyId) {
        ServiceResult<SellerApply> serviceResult = new ServiceResult<SellerApply>();
        try {
            serviceResult.setResult(sellerApplyModel.getSellerApplyById(sellerApplyId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][getSellerApplyById]根据id[" + sellerApplyId
                      + "]取得商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][getSellerApplyById]根据id[" + sellerApplyId
                      + "]取得商家申请表时出现未知异常：",
                e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> saveSellerApply(SellerApply sellerApply) {
        ServiceResult<Integer> serviceResult = new ServiceResult<Integer>();
        try {
            serviceResult.setResult(sellerApplyModel.saveSellerApply(sellerApply));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][saveSellerApply]保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApply]保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<SellerApply> getSellerApplyByUser(Integer memberId) {
        ServiceResult<SellerApply> serviceResult = new ServiceResult<SellerApply>();
        try {
            serviceResult.setResult(sellerApplyModel.getSellerApplyByUser(memberId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][getSellerApplyByUser]根据用户ID获取其商家入驻申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> auditSellerApply(Seller seller, Integer state,
                                                   Integer optUserId) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult
                .setResult(sellerApplyModel.auditSellerApply(seller, state, optUserId));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error("[SellerApplyService][auditSellerApply]审核商家入驻申请时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][auditSellerApply]审核商家入驻申请时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                          String sellerName, String ip,String parkoperation) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.saveSellerApplyForAdmin(sellerApply, userName, sellerName, ip,parkoperation));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApplyForAdmin]平台保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerApplyForAdmin(SellerApply sellerApply,
                                                            String userName, String sellerName,String parkoperation) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.updateSellerApplyForAdmin(sellerApply, userName, sellerName,parkoperation));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][updateSellerApplyForAdmin]平台修改商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][updateSellerApplyForAdmin]平台修改商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> saveSellerApplyForFront(SellerApply sellerApply, String userName,
                                                          String sellerName, Integer memberId,String parkoperation) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(sellerApplyModel.saveSellerApplyForFront(sellerApply, userName,
                sellerName, memberId,parkoperation));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][saveSellerApplyForFront]用户端保存商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Boolean> updateSellerApplyForFront(SellerApply sellerApply, String userName,
                                                            String sellerName,String parkoperation) {
        ServiceResult<Boolean> serviceResult = new ServiceResult<Boolean>();
        try {
            serviceResult.setResult(
                sellerApplyModel.updateSellerApplyForFront(sellerApply, userName, sellerName));
        } catch (BusinessException e) {
            serviceResult.setSuccess(false);
            serviceResult.setMessage(e.getMessage());
            log.error(
                "[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现异常：" + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SellerApplyService][updateSellerApplyForFront]用户端修改商家申请表时出现未知异常：", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<List<SellerApply>> getSellerApplyByCompany(String company) {
        ServiceResult<List<SellerApply>> serviceResult = new ServiceResult<List<SellerApply>>();
        try {
            List<SellerApply> list = sellerApplyModel.getSellerApplyByCompany(company);
            serviceResult.setResult(list);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
            log.error("[SellerApplyService][getSellerApplysByCompany]根据公司名称查询入驻申请时出现异常："
                      + e.getMessage());
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("[SellerApplyService][getSellerApplysByCompany] param1:" + company);
            log.error("[SellerApplyService][getSellerApplysByCompany] 根据公司名称查询入驻申请时出现未知异常:", e);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult<Integer> getSellerApplyCount() {
        ServiceResult<Integer> serviceResult = new ServiceResult<>();
        try {
            Integer res = sellerApplyModel.getSellerApplyCount();
            serviceResult.setResult(res);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }
    /***
     * 获取园区和业务
     */
	@Override
	public ServiceResult<List<Map<String, Object>>> getAddresAndBusiness() {
		 ServiceResult<List<Map<String,Object>>> serviceResult = new ServiceResult<List<Map<String,Object>>>();
	        try {
	        	List<Map<String,Object>> addresAndBusiness = sellerApplyModel.getAddresAndBusiness();
	            serviceResult.setResult(addresAndBusiness);
	        } catch (BusinessException e) {
	            serviceResult.setMessage(e.getMessage());
	            serviceResult.setSuccess(Boolean.FALSE);
	        } catch (Exception e) {
	            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
	                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
	            e.printStackTrace();
	        }

		return serviceResult;
	}

    @Override
    public ServiceResult<List<SellerParkOperation>> getBySellerId(Integer sellerId) {
        ServiceResult<List<SellerParkOperation>> serviceResult = new ServiceResult<List<SellerParkOperation>>();
        try {
            List<SellerParkOperation> sellerList =  sellerApplyModel.getBySellerId(sellerId);
            serviceResult.setResult(sellerList);
        } catch (BusinessException e) {
            serviceResult.setMessage(e.getMessage());
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                    ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            e.printStackTrace();
        }
        return serviceResult;
    }

}
