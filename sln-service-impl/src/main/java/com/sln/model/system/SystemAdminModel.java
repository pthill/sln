package com.sln.model.system;

import com.sln.core.ConstantsEJS;
import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.operate.ParkReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.system.CodeWriteDao;
import com.sln.dao.shop.write.system.SystemAdminWriteDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.system.SystemAdmin;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "systemAdminModel")
public class SystemAdminModel {

    @Resource
    private SystemAdminReadDao  systemAdminReadDao;
    @Resource
    private SystemAdminWriteDao systemAdminWriteDao;
    @Resource
    private SystemRolesWriteDao systemRolesWriteDao;
    @Resource
    private ParkReadDao parkReadDao;
    @Resource
    private CodeWriteDao codeWriteDao;
    /**
    * 根据id取得系统管理员表
    * @param  systemAdminId
    * @return
    */
    public SystemAdmin getSystemAdminById(Integer systemAdminId) {
        return systemAdminWriteDao.get(systemAdminId);
    }

    /**
     * 保存系统管理员表
     * @param  systemAdmin
     * @return
     */
    public Integer saveSystemAdmin(SystemAdmin systemAdmin) {
        int count=systemAdminReadDao.isNameExist(systemAdmin.getName(),null);
        if(count>0){
            throw new BusinessException("账号名称已存在请更换！");
        }
        return systemAdminWriteDao.save(systemAdmin);
    }

    /**
    * 更新系统管理员表
    * @param  systemAdmin
    * @return
    */

    public Integer updateSystemAdmin(SystemAdmin systemAdmin) {
        int count=systemAdminReadDao.isNameExist(systemAdmin.getName(),systemAdmin.getId());
        if(count>0){
            throw new BusinessException("账号名称已存在请更换！");
        }
        return systemAdminWriteDao.update(systemAdmin);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return systemAdminWriteDao.getCount(queryMap);
    }

    public List<SystemAdmin> page(Map<String, String> queryMap, Integer start, Integer size) {
        List<SystemAdmin> list = systemAdminWriteDao.page(queryMap, start, size);
        for (SystemAdmin admin : list) {
            admin.setRoleName(systemRolesWriteDao.get(admin.getRoleId()).getRolesName());
            if(admin.getParkId()==null||admin.getParkId()==0){
            }else{
                admin.setParkName(parkReadDao.getParkById(admin.getParkId()).getParkName());
                admin.setOperationName(codeWriteDao.getCode(ConstantsEJS.OPERATION_NAME,admin.getOperationId().toString()).getCodeText());
            }
        }
        return list;
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除系统管理员表[" + id + "]时出错");
        return this.systemAdminWriteDao.del(id) > 0;
    }

    public SystemAdmin getSystemAdminByNamePwd(String name, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("password", password);
        return systemAdminWriteDao.getByNamePwd(map);
    }

    /**
     * 根据用户名取用户
     * @param name
     * @return
     */
    public List<SystemAdmin> getSystemAdminByName(String name) {
        return systemAdminReadDao.getByName(name);
    }

}
