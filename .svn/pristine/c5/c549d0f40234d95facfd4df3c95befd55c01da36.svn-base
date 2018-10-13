package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.PortalServiceReadDao;
import com.sln.dao.shop.read.portal.RecommendServiceReadDao;
import com.sln.dao.shop.write.portal.RecommendServiceWriteDao;
import com.sln.entity.portal.RecommendService;
import com.sln.entity.portal.ShopActive;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class RecommendServiceModel {
    @Resource
    private RecommendServiceReadDao recommendServiceReadDao;
    @Resource
    private RecommendServiceWriteDao recommendServiceWriteDao;
    @Resource
    private PortalServiceReadDao portalServiceReadDao;

    public RecommendService getById(Integer id) {
        return recommendServiceReadDao.get(id);
    }

    public List<RecommendService> getByParkId(Integer parkId){
        return recommendServiceReadDao.getByParkId(parkId);
    }


    public Integer saveRecommendService(RecommendService recommendService) {
        int countOrder=recommendServiceReadDao.isOrderExist(recommendService.getOrder(),null);
        if(countOrder>0){
            throw new BusinessException("新增失败，排序存在重复");
        }
        int count=recommendServiceReadDao.isServiceExist(recommendService.getServiceId(),null);
        if(count>0){
            throw new BusinessException("新增失败，该服务已经是推荐服务了");
        }
        recommendService.setState("0");
        return recommendServiceWriteDao.insert(recommendService);
    }


    public Integer updateRecommendService(RecommendService recommendService) {
        int countOrder=recommendServiceReadDao.isOrderExist(recommendService.getOrder(),recommendService.getId());
        if(countOrder>0){
            throw new BusinessException("新增失败，排序存在重复");
        }
        int count=recommendServiceReadDao.isServiceExist(recommendService.getServiceId(),recommendService.getId());
        if(count>0){
            throw new BusinessException("新增失败，该服务已经是推荐服务了");
        }
        return recommendServiceWriteDao.update(recommendService);
    }

    public List<RecommendService> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return recommendServiceReadDao.getPage(queryMap, size, start);
    }

    public int getPageCount(Map<String, String> queryMap){
        return recommendServiceReadDao.getPageCount(queryMap);
    }

    public Integer update(RecommendService recommendService){
        return recommendServiceWriteDao.update(recommendService);
    }

    public Integer del(Integer id){
        return recommendServiceWriteDao.del(id);
    }
}
