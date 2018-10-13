package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.PortalMenuParkReadDao;
import com.sln.dao.shop.read.portal.PortalMenuReadDao;
import com.sln.dao.shop.read.portal.PortalServiceReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.portal.PortalMenuParkWriteDao;
import com.sln.dao.shop.write.portal.PortalMenuWriteDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.portal.PortalMenu;
import com.sln.entity.portal.PortalMenuPark;
import com.sln.entity.portal.PortalService;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Component
public class PortalMenuModel {
    private static Logger log = LogManager.getLogger(PortalMenuModel.class);

    @Resource
    private PortalMenuWriteDao           portalMenuWriteDao;
    @Resource
    private PortalMenuReadDao            portalMenuReadDao;
    @Resource
    private PortalMenuParkWriteDao       portalMenuParkWriteDao;
    @Resource
    private PortalMenuParkReadDao        portalMenuParkReadDao;
    @Resource
    private DataSourceTransactionManager transactionManager;
    @Resource
    private PortalServiceReadDao         portalServiceReadDao;
    @Resource
    private SystemAdminReadDao           systemAdminReadDao;
    @Resource
    private SystemRolesWriteDao          systemRolesWriteDao;
    /**
     * 根据id取得portal_menu对象
     * @param  portalMenuId
     * @return
     */
    public PortalMenu getPortalMenuById(Integer portalMenuId) {
        return portalMenuReadDao.get(portalMenuId);
    }

    /**
     * 保存portal_menu对象,新增时默认为禁用状态，不显示
     * @param  portalMenu
     * @return
     */
    public Boolean savePortalMenu(PortalMenu portalMenu,String[] parkIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            portalMenu.setState("0");
            portalMenu.setIsShow("0");
            Integer row = portalMenuWriteDao.insert(portalMenu);
            if (row == 0) {
                throw new BusinessException("保存菜单失败！");
            }
            List<PortalMenuPark> portalMenuParks=new ArrayList<>();
            for(int i=0;i<parkIds.length;i++){
                PortalMenuPark portalMenuPark=new PortalMenuPark();
                portalMenuPark.setPortalMenuId(portalMenu.getId());
                portalMenuPark.setParkId(Integer.parseInt(parkIds[i]));
                portalMenuParks.add(portalMenuPark);
            }
            row=portalMenuParkWriteDao.batchSave(portalMenuParks);
            if (row == 0) {
                throw new BusinessException("保存菜单失败！");
            }
            transactionManager.commit(status);
            return true;
        }catch (Exception e){
            transactionManager.rollback(status);
            throw e;
        }
    }

    /**
     * 更新portal_menu对象
     * @param  portalMenu
     * @return
     */
    public Boolean updatePortalMenu(PortalMenu portalMenu,String[] parkIds) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            Integer row = portalMenuWriteDao.update(portalMenu);
            if (row == 0) {
                throw new BusinessException("修改菜单失败！");
            }
            row=portalMenuParkWriteDao.deleteById(portalMenu.getId());
            if (row == 0) {
                throw new BusinessException("修改菜单失败！");
            }
            if(parkIds!=null&&parkIds.length>=1){
                List<PortalMenuPark> portalMenuParks=new ArrayList<>();
                for(int i=0;i<parkIds.length;i++){
                    PortalMenuPark portalMenuPark=new PortalMenuPark();
                    portalMenuPark.setPortalMenuId(portalMenu.getId());
                    portalMenuPark.setParkId(Integer.parseInt(parkIds[i]));
                    portalMenuParks.add(portalMenuPark);
                }
                row=portalMenuParkWriteDao.batchSave(portalMenuParks);
                if (row == 0) {
                    throw new BusinessException("修改菜单失败！");
                }
            }
            transactionManager.commit(status);
            return true;
        }catch (Exception e){
            transactionManager.rollback(status);
            throw e;
        }

    }

    /**
     * 查询页面列表
     */
    public List<PortalMenu> getPage(Map<String, String> queryMap, Integer size, Integer start){
        String adminId = queryMap.get("adminId");
        SystemAdmin admin = systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        if (systemRoles.getRoleType().equals("1")) {//当前角色是平台角色查询所有菜单
            return portalMenuReadDao.getPage(queryMap, size, start);
        }else{
            queryMap.put("parkId",admin.getParkId().toString());
            return portalMenuReadDao.getPageForOperation(queryMap,size,start);
        }

    }

    public List<PortalMenuPark> getParksById(Integer menuId){
        return portalMenuParkReadDao.getByportalMenuId(menuId);
    }

    public int getPageCount(Map<String, String> queryMap){
        String adminId = queryMap.get("adminId");
        SystemAdmin admin = systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        if (systemRoles.getRoleType().equals("1")) {//当前角色是平台角色查询所有菜单
            return portalMenuReadDao.getPageCount(queryMap);
        }else{
            return portalMenuReadDao.getPageCountForOperation(queryMap);
        }
    }

    public Integer isCodeExist(String code,Integer id){
        return portalMenuReadDao.isCodeExist(code,id);
    }

    public Integer isOrderExist(Integer order,Integer id){
        return portalMenuReadDao.isOrderExist(order,id);
    }

    public Integer isAbbreviationExist(String abbreviation,Integer id){
        return portalMenuReadDao.isAbbreviationExist(abbreviation,id);
    }

    public Integer del(Integer id){
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try{
            List<PortalService> list=portalServiceReadDao.getServicesByMenuId(id);
            if(list!=null&&list.size()>0){
                throw new BusinessException("该菜单下已存在服务项不能删除");
            }else{
                Integer row = portalMenuWriteDao.del(id);
                if (row == 0) {
                    throw new BusinessException("删除菜单失败！");
                }
                row=portalMenuParkWriteDao.deleteById(id);
                if (row == 0) {
                    throw new BusinessException("删除失败！");
                }
                transactionManager.commit(status);
                return 1;
            }
        }catch (Exception e){
            transactionManager.rollback(status);
            throw e;
        }
    }

    /*此方法用于更新状态和显示和不显示*/
    public Integer update(PortalMenu PortalMenu){
        return portalMenuWriteDao.update(PortalMenu);
    }

    public List<PortalMenu> getMenusByParkId(Integer parkId){
        return portalMenuReadDao.getMenusByParkId(parkId);
    }
}
