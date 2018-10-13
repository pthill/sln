package com.sln.model.seller;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.seller.SellerUserReadDao;
import com.sln.dao.shop.write.seller.SellerUserLoginLogWriteDao;
import com.sln.dao.shop.write.seller.SellerUserWriteDao;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.seller.SellerUserLoginLog;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component(value = "sellerUserModel")
public class SellerUserModel {

    @Resource
    private SellerUserWriteDao         sellerUserWriteDao;
    @Resource
    private SellerUserReadDao          sellerUserReadDao;
    @Resource
    private SellerUserLoginLogWriteDao sellerUserLoginLogWriteDao;

    /**
    * 根据id取得系统管理员表
    * @param  sellerUserId
    * @return
    */
    public SellerUser getSellerUserById(Integer sellerUserId) {
        return sellerUserReadDao.get(sellerUserId);
    }

    /**
     * 保存系统管理员表
     * @param  sellerUser
     * @return
     */
    public Integer saveSellerUser(SellerUser sellerUser) {
        return sellerUserWriteDao.insert(sellerUser);
    }

    /**
    * 更新系统管理员表
    * @param  sellerUser
    * @return
    */
    public Integer updateSellerUser(SellerUser sellerUser) {
        return sellerUserWriteDao.update(sellerUser);
    }

    public Integer pageCount(Map<String, String> queryMap) {
        return sellerUserReadDao.getCount(queryMap);
    }

    /**
     * 数据过滤，根据sellerId查询属于该店铺的下供应商账号和商家账号
     * 系统管理员表
     * @param
     * @return
     */
    public List<SellerUser> page(Map<String, String> queryMap, Integer start, Integer size) {
        return sellerUserReadDao.page(queryMap, start, size);
    }

    public Boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除系统管理员表[" + id + "]时出错");
        return this.sellerUserWriteDao.del(id) > 0;
    }

    public SellerUser getSellerUserByNamePwd(String name, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("name", name);
        map.put("password", password);
        return sellerUserReadDao.getByNamePwd(map);
    }

    public List<SellerUser> getSellerUserByName(String name) {
        return sellerUserReadDao.getByName(name);
    }

    /**
     * 保存商家管理员登录日志
     * @param sellerUserLoginLog
     * @return
     */
    public boolean saveSellerUserLoginLog(SellerUserLoginLog sellerUserLoginLog) {
        return sellerUserLoginLogWriteDao.insert(sellerUserLoginLog) > 0;
    }

    /****
     * 根据角色id获取角色类型
     * @param roleId
     * @return
     */
    public Integer getRoleTypeById(Integer roleId) {
        return sellerUserReadDao.getRoleTypeById(roleId);
    }

}
