package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.QuickEnterReadDao;
import com.sln.dao.shop.write.portal.QuickEnterWriteDao;
import com.sln.entity.portal.QuickEnter;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class QuickEnterModel {
    private static Logger log = LogManager.getLogger(QuickEnterModel.class);
    @Resource
    private QuickEnterReadDao  quickEnterReadDao;
    @Resource
    private QuickEnterWriteDao quickEnterWriteDao;


    public QuickEnter getQuickEnterById(Integer id) {
        return quickEnterReadDao.get(id);
    }


    public Integer saveQuickEnter(QuickEnter quickEnter) {
        int countName=quickEnterReadDao.isNameExist(quickEnter.getName(),null);
        if(countName>0){
            throw new BusinessException("新增失败，名称存在失败");
        }
        int countOrder=quickEnterReadDao.isOrderExist(quickEnter.getOrder(),null);
        if(countOrder>0){
            throw new BusinessException("新增失败，排序存在重复");
        }
        quickEnter.setState("0");
        return quickEnterWriteDao.insert(quickEnter);
    }

    public Integer updateQuickEnter(QuickEnter quickEnter) {
        int countName=quickEnterReadDao.isNameExist(quickEnter.getName(),quickEnter.getId());
        if(countName>0){
            throw new BusinessException("修改失败，名称存在失败");
        }
        int countOrder=quickEnterReadDao.isOrderExist(quickEnter.getOrder(),quickEnter.getId());
        if(countOrder>0){
            throw new BusinessException("修改失败，排序存在重复");
        }
        quickEnter.setState("0");
        return quickEnterWriteDao.update(quickEnter);
    }

    public List<QuickEnter> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return quickEnterReadDao.getPage(queryMap, size, start);
    }

    public int getPageCount(Map<String, String> queryMap){
        return quickEnterReadDao.getPageCount(queryMap);
    }

    public Integer del(Integer id){
        return quickEnterWriteDao.del(id);
    }

    /*此方法用于更新状态和显示和不显示*/
    public Integer update(QuickEnter quickEnter){
        return quickEnterWriteDao.update(quickEnter);
    }
}
