package com.sln.service.impl.jd;

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
import com.sln.core.jd.bean.AccessToken;
import com.sln.entity.jd.JdCategory;
import com.sln.model.jd.JdCategoryModel;
import com.sln.service.jd.IJdCategoryService;

@Service(value = "jdCategoryService")
public class JdCategoryServiceImpl implements IJdCategoryService {
	private static Logger      log = LogManager.getLogger(JdCategoryServiceImpl.class);
	
	@Resource
	private JdCategoryModel jdCategoryModel;

	@Override
	public ServiceResult<Integer> batchInsertCategory(AccessToken token) {
		ServiceResult<Integer> result = new ServiceResult<Integer>();
        try {
        	//同步京东分类信息
        	jdCategoryModel.batchInsertCategory(token);
        	//对京东分类信息进行查漏补缺 
        	//对三级分类查漏补缺
        	jdCategoryModel.checkUpCat(token, JdCategory.CATCLASS_2);
        	//对二级分类查漏补缺
        	jdCategoryModel.checkUpCat(token, JdCategory.CATCLASS_1);
            result.setResult(1);
        } catch (Exception e) {
            log.error("同步京东分类信息异常" + e);
            result.setSuccess(false);
            result.setMessage("同步京东分类信息异常");
        }
        return result;
	}
	
	/**
	 * 根据pid获取分类列表
	 * @param pid
	 * @return
	 */
	@Override
    public ServiceResult<List<JdCategory>> getByPid(Integer pid) {
        ServiceResult<List<JdCategory>> serviceResult = new ServiceResult<List<JdCategory>>();
        try {
            serviceResult.setResult(jdCategoryModel.getByPid(pid));
        } catch (BusinessException e) {
        	log.error("根据pid获取分类列表时出现异常：" + e);
            serviceResult.setMessage("获取分类列表异常，请联系系统管理员！");
            serviceResult.setSuccess(Boolean.FALSE);
        } catch (Exception e) {
        	log.error("根据pid获取分类列表时出现异常：" + e);
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR,
                ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
        }
        return serviceResult;
    }

    /**
     * 查询京东商品分类列表
     * @param  map
     * @return
     */
    @Override
    public ServiceResult<List<JdCategory>> getJdCategoryList(Map<String, Object> queryMap, PagerInfo pager) {
        ServiceResult<List<JdCategory>> serviceResult = new ServiceResult<List<JdCategory>>();
        serviceResult.setPager(pager);
        try {
        	 Integer start = 0, size = 0;
             if (pager != null) {
                 pager.setRowsCount(jdCategoryModel.getCount(queryMap));
                 start = pager.getStart();
                 size = pager.getPageSize();
             }
            serviceResult.setResult(jdCategoryModel.getJdCategoryList(queryMap, start, size));
        } catch (BusinessException e) {
        	log.error("查询京东商品分类列表时出现异常：" + e);
            serviceResult.setSuccess(false);
            serviceResult.setMessage("查询京东商品分类列表时出现异常,请联系系统管理员！");
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("查询京东商品分类列表时出现异常：" + e);
        }
        return serviceResult;
    }
    
    /**
     * 根据京东分类id取得分类信息
     * @param catId
     * @return
     */
	@Override
	public ServiceResult<JdCategory> getByCatId(Integer catId) {
		ServiceResult<JdCategory> serviceResult = new ServiceResult<JdCategory>();
        try {
            serviceResult.setResult(jdCategoryModel.getByCatId(catId));
        } catch (BusinessException e) {
        	log.error("查询京东商品分类时出现异常：" + e);
            serviceResult.setSuccess(false);
            serviceResult.setMessage("查询京东商品分类时出现异常,请联系系统管理员！");
        } catch (Exception e) {
            serviceResult.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, ConstantsEJS.SERVICE_RESULT_EXCEPTION_SYSERROR);
            log.error("查询京东商品分类时出现异常：" + e);
        }
        return serviceResult;
	}
}