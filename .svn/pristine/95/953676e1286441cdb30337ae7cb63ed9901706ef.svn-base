package com.sln.model.portal;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.portal.PortalServiceReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.portal.PortalServiceWriteDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.portal.PortalService;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class PortalServiceModel {
    private static Logger log = LogManager.getLogger(PortalServiceModel.class);

    @Resource
    private PortalServiceReadDao  portalServiceReadDao;
    @Resource
    private PortalServiceWriteDao portalServiceWriteDao;
    @Resource
    private SystemAdminReadDao    systemAdminReadDao;
    @Resource
    private SystemRolesWriteDao   systemRolesWriteDao;

    /**
     * 根据id取得portal_service对象
     * @param  portalServiceId
     * @return
     */
    public PortalService getPortalServiceById(Integer portalServiceId) {
        return portalServiceReadDao.get(portalServiceId);
    }

    /**
     * 保存portal_service对象
     * @param  portalService
     * @return
     */
    public Integer savePortalService(PortalService portalService) {
        portalService.setIsShow("0");
        portalService.setState("0");
        int countName=portalServiceReadDao.countName(null,portalService.getServiceName(),portalService.getType());
        if(countName>0){
            throw new BusinessException("服务项名称存在重复");
        }
        int countCode=portalServiceReadDao.countCode(null,portalService.getCode(),portalService.getType());
        if(countCode>0){
            throw new BusinessException("编号存在重复");
        }
        int countAbbreviation=portalServiceReadDao.countAbbreviation(null,portalService.getAbbreviation(),portalService.getType());
        if(countAbbreviation>0){
            throw new BusinessException("简称存在重复");
        }
        if(portalService.getPid()==null||portalService.getPid().equals(0)){
            //新增服务项
            return portalServiceWriteDao.insert(portalService);
        }else{
            //新增服务类(服务类的菜单必须和所属服务项一致)
           PortalService service=this.getPortalServiceById(portalService.getPid());
           if(service==null){
               throw new BusinessException("保存出现异常");
           }
            portalService.setMenuId(service.getMenuId());
            portalService.setAbbreviation(service.getAbbreviation()+"-"+portalService.getAbbreviation());
            portalService.setCode(service.getCode()+"-"+portalService.getCode());
        }
        return portalServiceWriteDao.insert(portalService);
    }

    /**
     * 更新portal_active对象
     * @param  portalService
     * @return
     */
    public Integer updatePortalService(PortalService portalService) {
        int countName=portalServiceReadDao.countName(portalService.getId(),portalService.getServiceName(),portalService.getType());
        if(countName>0){
            throw new BusinessException("服务项名称存在重复");
        }
        int countCode=portalServiceReadDao.countCode(portalService.getId(),portalService.getCode(),portalService.getType());
        if(countCode>0){
            throw new BusinessException("编号存在重复");
        }
        int countAbbreviation=portalServiceReadDao.countAbbreviation(portalService.getId(),portalService.getAbbreviation(),portalService.getType());
        if(countAbbreviation>0){
            throw new BusinessException("简称存在重复");
        }
        if(portalService.getPid()==null||portalService.getPid().equals(0)){//如果当前是服务项
            List<PortalService> list=portalServiceReadDao.gerServicesByPid(portalService.getId());
            for(PortalService p:list){//更改归属于该服务项下的服务类的简称和编号
                String code=p.getCode().split("-")[1];
                String abbreviation=p.getAbbreviation().split("-")[1];
                p.setCode(portalService.getCode()+"-"+code);
                p.setAbbreviation(portalService.getAbbreviation()+"-"+abbreviation);
                p.setMenuId(portalService.getMenuId());
                portalServiceWriteDao.update(p);
            }
        }else{//如果当前是服务类
            PortalService service=this.getPortalServiceById(portalService.getPid());
            if(service==null){
                throw new BusinessException("更新出现异常");
            }
            portalService.setAbbreviation(service.getAbbreviation()+"-"+portalService.getAbbreviation());
            portalService.setCode(service.getCode()+"-"+portalService.getCode());
            portalService.setMenuId(service.getMenuId());
        }
        return portalServiceWriteDao.update(portalService);
    }

    public List<PortalService> getPage(Map<String, String> queryMap, Integer size, Integer start){
        String adminId = queryMap.get("adminId");
        SystemAdmin admin = systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        if (systemRoles.getRoleType().equals("1")) {//当前角色是平台角色查询所有服务
            return portalServiceReadDao.getPage(queryMap, size, start);
        }else{
            queryMap.put("parkId",admin.getParkId().toString());
            return portalServiceReadDao.getPageForOperation(queryMap,size,start);
        }

    }

    public Integer del(Integer id){
        PortalService portalService=portalServiceReadDao.get(id);
        if(portalService.getType().equals("2")){//当前是服务类
            return portalServiceWriteDao.del(id);
        }else{//当前是服务项
            List<PortalService> list=portalServiceReadDao.gerServicesByPid(id);
            if(list!=null&&list.size()>0){
                throw new BusinessException("该服务项已存在服务类不能删除");
            }else{
                return portalServiceWriteDao.del(id);
            }
        }
    }

    public List<PortalService> servicePage(Map<String, String> queryMap, Integer size, Integer start){
        return portalServiceReadDao.getServicePage(queryMap,size,start);
    }

    public int getServicePageCount(Map<String,String>queryMap){
        return portalServiceReadDao.getServiceCount(queryMap);
    }

    public int getPageCount(Map<String, String> queryMap){
        String adminId = queryMap.get("adminId");
        SystemAdmin admin = systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        if (systemRoles.getRoleType().equals("1")) {//当前角色是平台角色查询所有服务
            return portalServiceReadDao.getPageCount(queryMap);
        }else{
            return portalServiceReadDao.getPageCountForOperation(queryMap);
        }

    }

    /*此方法用于更新状态和显示和不显示*/
    public Integer update(PortalService portalService){
        return portalServiceWriteDao.update(portalService);
    }

    public List<PortalService> serviceList(Integer parkId){
        return portalServiceReadDao.getServices(parkId);
    }

    /*门户菜单获取菜单下的服务项和服务类状态为启用和(禁用且为显示)*/
    public List<PortalService> getServicesByMenuId(Integer menuId){
        return portalServiceReadDao.getServicesByMenuId(menuId);
    }
}
