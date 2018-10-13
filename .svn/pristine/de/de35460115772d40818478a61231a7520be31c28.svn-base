package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.ParkAdvantageReadDao;
import com.sln.dao.shop.write.portal.ParkAdvantageWriteDao;
import com.sln.entity.portal.ParkAdvantage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class ParkAdvantageModel {
    private static Logger log = LogManager.getLogger(ParkAdvantageModel.class);

    @Resource
    private ParkAdvantageReadDao  parkAdvantageReadDao;
    @Resource
    private ParkAdvantageWriteDao parkAdvantageWriteDao;

    public ParkAdvantage getById(Integer id) {
        return parkAdvantageReadDao.get(id);
    }

    public Integer saveParkAdvantage(ParkAdvantage parkAdvantage) {
        int count=parkAdvantageReadDao.isOrderExist(parkAdvantage.getOrder(),null);
        if(count>0){
            throw new BusinessException("新增失败，排序存在重复");
        }
        count=parkAdvantageReadDao.isTitleExist(parkAdvantage.getTitle(),null);
        if(count>0){
            throw new BusinessException("新增失败，标题存在重复");
        }
        parkAdvantage.setState("1");
        return parkAdvantageWriteDao.insert(parkAdvantage);
    }

    public Integer updateParkAdvantage(ParkAdvantage parkAdvantage) {
        int count=parkAdvantageReadDao.isOrderExist(parkAdvantage.getOrder(),parkAdvantage.getId());
        if(count>0){
            throw new BusinessException("修改失败，排序存在重复");
        }
        count=parkAdvantageReadDao.isTitleExist(parkAdvantage.getTitle(),parkAdvantage.getId());
        if(count>0){
            throw new BusinessException("修改失败，标题存在重复");
        }
        return parkAdvantageWriteDao.update(parkAdvantage);
    }

    public List<ParkAdvantage> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return parkAdvantageReadDao.getPage(queryMap, size, start);
    }

    public Integer getPageCount(Map<String, String> queryMap){
        return parkAdvantageReadDao.getPageCount(queryMap);
    }

    public Integer del(Integer id){
        return parkAdvantageWriteDao.del(id);
    }

    public List<ParkAdvantage> getByParkId(Integer parkId){
        return parkAdvantageReadDao.getByParkId(parkId);
    }
}
