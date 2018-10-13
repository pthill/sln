package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.ShopActiveReadDao;
import com.sln.dao.shop.write.portal.ShopActiveWriteDao;
import com.sln.entity.portal.ShopActive;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class ShopActiveModel {
    @Resource
    private DataSourceTransactionManager transactionManager;
    private static Logger log = LogManager.getLogger(ShopActiveModel.class);

    @Resource
    private ShopActiveWriteDao shopActiveWriteDao;
    @Resource
    private ShopActiveReadDao  shopActiveReadDao;


    public ShopActive getById(Integer id) {
        return shopActiveReadDao.get(id);
    }


    public Integer saveShopActive(ShopActive shopActive) {
        int count=shopActiveReadDao.isNameExist(shopActive.getName(),null);
        if(count>0){
            throw new BusinessException("新增失败，名称存在重复");
        }
        shopActive.setState("1");
        shopActive.setStatus("0");
        return shopActiveWriteDao.insert(shopActive);
    }


    public Integer updateShopActive(ShopActive shopActive) {
        int count=shopActiveReadDao.isNameExist(shopActive.getName(),shopActive.getId());
        if(count>0){
            throw new BusinessException("修改失败，名称存在重复");
        }
        return shopActiveWriteDao.update(shopActive);
    }

    public List<ShopActive> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return shopActiveReadDao.getPage(queryMap, size, start);
    }

    public int getPageCount(Map<String, String> queryMap){
        return shopActiveReadDao.getPageCount(queryMap);
    }

    public Integer updateStatus(Integer id, String state){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        int count=0;
        try{
            if (id==null){
                throw new BusinessException("id不能为空");
            }
            ShopActive shopActive=shopActiveReadDao.get(id);
            if(shopActive==null){
                throw new BusinessException("当前对象不存在");
            }
            //有且只有一个启用状态
            if(state.equals("0")){
                 if(shopActive.getStatus().equals("1")){
                     throw new BusinessException("必须存在一个启用的数据");
                 }else {
                     shopActive.setStatus(state);
                     count=shopActiveWriteDao.update(shopActive);
                     if(count==0){
                         throw new BusinessException("状态更新失败");
                     }
                 }
            }else{
                 shopActive.setStatus("1");
                 count=shopActiveWriteDao.update(shopActive);
                 if(count==0){
                     throw new BusinessException("状态更新失败");
                 }
                 count=shopActiveWriteDao.updateState(shopActive.getId(),shopActive.getType());
                 if(count==0){
                     throw new BusinessException("状态更新失败");
                 }
            }
            transactionManager.commit(status);
            return count;
        }catch (BusinessException be){
            transactionManager.rollback(status);
            log.error("更新电商活动时出现未知异常：" + be);
            throw be;
        }catch (Exception e){
            transactionManager.rollback(status);
            log.error("更新电商活动时出现未知异常：" + e);
            throw e;
        }
    }
    public Integer update(ShopActive shopActive){
        return shopActiveWriteDao.update(shopActive);
    }

    public Integer del(Integer id){
        return shopActiveWriteDao.del(id);
    }

}
