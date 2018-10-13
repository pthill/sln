package com.sln.service.seller;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.seller.SellerUserLoginLog;

import java.util.List;
import java.util.Map;

/**
 * 商家管理员用户管理
 *                       
 * @Filename: ISellerUserService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerUserService {

    /**
     * 根据id取得系统管理员表
     * @param  sellerUserId
     * @return
     */
    ServiceResult<SellerUser> getSellerUserById(Integer sellerUserId);

    /**
     * 保存系统管理员表
     * @param  sellerUser
     * @return
     */
    ServiceResult<Integer> saveSellerUser(SellerUser sellerUser);

    /**
    * 更新系统管理员表
    * @param  sellerUser
    * @return
    */
    ServiceResult<Integer> updateSellerUser(SellerUser sellerUser);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<SellerUser>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);



    /**
     * 根据用户名取商家用户
     * @param name
     * @return
     */
    ServiceResult<List<SellerUser>> getSellerUserByName(String name);

    /**
     * 保存管理员表登录日志
     * @param  sellerUserLoginLog
     * @return
     */
    ServiceResult<Boolean> saveSellerUserLoginLog(SellerUserLoginLog sellerUserLoginLog);
    /***
     * 根据角色id获取类型
     * @param roleId
     * @return
     */
    ServiceResult<Integer> getRoleTypeById(Integer roleId);
}