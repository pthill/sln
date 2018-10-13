package com.sln.service.impl.search;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.sln.core.ConstantsEJS;
import com.sln.core.ServiceResult;
import com.sln.core.exception.BusinessException;
import com.sln.model.search.SolrProductModel;
import com.sln.service.search.ISolrProductService;

/**
 * solr相关的操作
 *                       
 * @Filename: SolrProductServiceImpl.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
@Service(value = "solrProductService")
public class SolrProductServiceImpl implements ISolrProductService {

    private static Logger    log = LogManager.getLogger(SolrProductServiceImpl.class);

    @Resource
    private SolrProductModel solrProductModel;

    /**
     * 创建索引
     * @param solrUrl
     * @param solrServer
     * @return
     * @see com.sln.service.search.ISolrProductService#jobCreateIndexesSolr(java.lang.String, java.lang.String)
     */
    @Override
    public ServiceResult<Boolean> jobCreateIndexesSolr(String solrUrl, String solrServer) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(solrProductModel.jobCreateIndexesSolr(solrUrl, solrServer));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SolrProductServiceImpl][jobCreateIndexesSolr]系统定时创建索引出现未知异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SolrProductServiceImpl][jobCreateIndexesSolr]系统定时创建索引出现未知异常:", e);
        }
        return result;
    }

    /**
     * 更新敏感词的索引值
     * @return
     * @see com.sln.service.search.ISolrProductService#jobUpdateSearchRecordIndex()
     */
    @Override
    public ServiceResult<Boolean> jobUpdateSearchRecordIndex(String solrUrl, String solrServer) {
        ServiceResult<Boolean> result = new ServiceResult<Boolean>();
        try {
            result.setResult(solrProductModel.jobUpdateSearchRecordIndex(solrUrl, solrServer));
        } catch (BusinessException be) {
            result.setSuccess(false);
            result.setMessage(be.getMessage());
            log.error("[SolrProductServiceImpl][jobUpdateSearchRecordIndex]更新敏感词的索引值出现未知异常:"
                      + be.getMessage());
        } catch (Exception e) {
            result.setError(ConstantsEJS.SERVICE_RESULT_CODE_SYSERROR, "服务异常，请联系系统管理员。");
            log.error("[SolrProductServiceImpl][jobUpdateSearchRecordIndex]更新敏感词的索引值出现未知异常:", e);
        }
        return result;
    }

}
