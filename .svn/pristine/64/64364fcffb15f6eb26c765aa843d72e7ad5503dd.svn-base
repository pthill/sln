package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.PortalIndexBannerReadDao;
import com.sln.dao.shop.write.portal.PortalIndexBannerWriteDao;
import com.sln.entity.portal.IndexBanner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class PortalIndexBannerModel {
    @Resource
    private PortalIndexBannerWriteDao indexBannerWriteDao;
    @Resource
    private PortalIndexBannerReadDao  indexBannerReadDao;


    public IndexBanner getIndexBannerById(Integer id) {
        return indexBannerReadDao.get(id);
    }

    public Integer getCount(Map<String, String> queryMap){
        return indexBannerReadDao.getPageCount(queryMap);
    }
    public List<IndexBanner> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return indexBannerReadDao.getPage(queryMap, size, start);
    }

    public Integer save(IndexBanner indexBanner) {
        int count=indexBannerReadDao.isNameExist(indexBanner.getName(),null,null);
        if(count>0){
            count=indexBannerReadDao.isNameExist(indexBanner.getName(),null,indexBanner.getParkId());
            if(count>0){
                throw new BusinessException("新增失败名称存在重复");
            }
        }
        count=indexBannerReadDao.isOrderExist(indexBanner.getOrder(),null,indexBanner.getParkId(),null);
        if(count>0){
            count=indexBannerReadDao.isOrderExist(indexBanner.getOrder(),null,indexBanner.getParkId(),indexBanner.getType());
            if(count>0){
                throw new BusinessException("新增失败排序存在重复");
            }
        }
        count=indexBannerReadDao.isAbbreviationExist(indexBanner.getAbbreviation(),null,null);
        if(count>0){
            count=indexBannerReadDao.isAbbreviationExist(indexBanner.getAbbreviation(),null,indexBanner.getParkId());
            if(count>0){
                throw new BusinessException("新增失败简拼存在重复");
            }
        }
        indexBanner.setState("0");
        return indexBannerWriteDao.insert(indexBanner);
    }
    public Integer update(IndexBanner indexBanner){
        int count=indexBannerReadDao.isNameExist(indexBanner.getName(),indexBanner.getId(),null);
        if(count>0){
            count=indexBannerReadDao.isNameExist(indexBanner.getName(),indexBanner.getId(),indexBanner.getParkId());
            if(count>0){
                throw new BusinessException("修改失败名称存在重复");
            }
        }
        count=indexBannerReadDao.isOrderExist(indexBanner.getOrder(),indexBanner.getId(),indexBanner.getParkId(),null);
        if(count>0){
            count=indexBannerReadDao.isOrderExist(indexBanner.getOrder(),indexBanner.getId(),indexBanner.getParkId(),indexBanner.getType());
            if(count>0){
                throw new BusinessException("新增失败排序存在重复");
            }
        }
        count=indexBannerReadDao.isAbbreviationExist(indexBanner.getAbbreviation(),indexBanner.getId(),null);
        if(count>0){
            count=indexBannerReadDao.isAbbreviationExist(indexBanner.getAbbreviation(),indexBanner.getId(),indexBanner.getParkId());
            if(count>0){
                throw new BusinessException("修改失败简拼存在重复");
            }
        }
        return indexBannerWriteDao.update(indexBanner);
    }

    public Integer del(Integer id){
        return indexBannerWriteDao.delete(id);
    }

    /*获取门户的轮播数据*/
    public List<IndexBanner> getBannerList(Integer parkId,String type){
        return indexBannerReadDao.getBannerList(parkId,type);
    }

}
