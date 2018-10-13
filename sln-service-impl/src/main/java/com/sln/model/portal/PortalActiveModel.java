package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.PortalActiveReadDao;
import com.sln.dao.shop.write.portal.PortalActiveWriteDao;
import com.sln.entity.portal.PortalActive;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class PortalActiveModel {
    private static Logger log = LogManager.getLogger(PortalActiveModel.class);

    @Resource
    private PortalActiveWriteDao portalActiveWriteDao;
    @Resource
    private PortalActiveReadDao portalActiveReadDao;

    /**
     * 根据id取得portal_active对象
     * @param  portalActiveId
     * @return
     */
    public PortalActive getPortalActiveById(Integer portalActiveId) {
        return portalActiveReadDao.get(portalActiveId);
    }

    /**
     * 保存portal_active对象
     * @param  portalActive
     * @return
     */
    public Integer savePortalActive(PortalActive portalActive) {
        int count=portalActiveReadDao.isTitleExist(portalActive.getTitle(),null,null);
        if(count>0){
            count=portalActiveReadDao.isTitleExist(portalActive.getTitle(),null,portalActive.getParkId());
            if(count>0){
                throw new BusinessException("新增失败，活动标题存在重复");
            }
        }
        count=portalActiveReadDao.isOrderExist(portalActive.getOrder(),null,null);
        if(count>0){
            count=portalActiveReadDao.isOrderExist(portalActive.getOrder(),null,portalActive.getParkId());
            if(count>0){
                throw new BusinessException("新增失败，排序存在重复");
            }

        }
        return portalActiveWriteDao.insert(portalActive);
    }

    /**
     * 更新portal_active对象
     * @param  portalActive
     * @return
     */
    public Integer updatePortalActive(PortalActive portalActive) {
        int count=portalActiveReadDao.isTitleExist(portalActive.getTitle(),portalActive.getId(),null);
        if(count>0){
            count=portalActiveReadDao.isTitleExist(portalActive.getTitle(),portalActive.getId(),portalActive.getParkId());
            if(count>0){
                throw new BusinessException("修改失败，活动标题存在重复");
            }
        }
        count=portalActiveReadDao.isOrderExist(portalActive.getOrder(),portalActive.getId(),null);
        if(count>0){
            count=portalActiveReadDao.isOrderExist(portalActive.getOrder(),portalActive.getId(),portalActive.getParkId());
            if(count>0){
                throw new BusinessException("修改失败，排序存在重复");
            }
        }
        return portalActiveWriteDao.update(portalActive);
    }

    public Integer del(Integer id){
        return portalActiveWriteDao.del(id);
    }

    public List<PortalActive> getPage(Map<String, String> queryMap, Integer size, Integer start){
        return portalActiveReadDao.getPage(queryMap, size, start);
    }

    public int getPageCount(Map<String, String> queryMap){
        return portalActiveReadDao.getPageCount(queryMap);
    }

    /*此方法用于更新状态和显示和不显示*/
    public Integer update(PortalActive portalActive){
        return portalActiveWriteDao.update(portalActive);
    }

    /*展示在门户的活动页*/
    public List<PortalActive> activeList(Integer parkId){
        return portalActiveReadDao.getList(parkId);
    }

}
