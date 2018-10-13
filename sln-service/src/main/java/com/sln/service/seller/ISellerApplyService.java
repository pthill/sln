package com.sln.service.seller;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerApply;
import com.sln.entity.seller.SellerParkOperation;

import java.util.List;
import java.util.Map;

/**
 * 商家申请
 *                       
 * @Filename: ISellerApplyService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ISellerApplyService {

    /**
     * 根据id取得商家申请表
     * @param  sellerApplyId
     * @return
     */
    ServiceResult<SellerApply> getSellerApplyById(Integer sellerApplyId);

    /**
     * 保存商家申请表
     * @param  sellerApply
     * @return
     */
    ServiceResult<Integer> saveSellerApply(SellerApply sellerApply);

    /**
     * 修改商家申请
     * @param sellerApply
     * @return
     */
    ServiceResult<Boolean> updateSellerApply(SellerApply sellerApply);

    /**
     * 删除该商家申请,同时删除该商家账号
     * @param id
     * @return
     */
    ServiceResult<Boolean> deleteSellerApply(Integer id, Integer memberId);

    /**
     * 根据条件分页查询商家申请，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerApply>> getSellerApplys(Map<String, String> queryMap, PagerInfo pager);
    /**
     * 根据条件和当前用户的角色分页查询商家申请，PagerInfo传null取全部数据
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<SellerApply>> getSellerApplysByRoleId(Map<String, String> queryMap, PagerInfo pager);
    /**
     * 根据用户ID获取其商家入驻申请
     * @param userid
     * @return
     */
    ServiceResult<SellerApply> getSellerApplyByUser(Integer memberId);

    /**
     * 审核商家申请，审核通过同步更新商家状态为审核通过
     * @param sellerApplyId 商家申请ID
     * @param state 申请状态
     * @param optUserId 操作人ID
     * @return
     */
    ServiceResult<Boolean> auditSellerApply(Seller seller, Integer state,
                                            Integer optUserId);

    /**
     * 
     * 保存商家申请(平台添加商家申请用)<br>
     * <li>新增一个普通用户表（兼容用户端申请）
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @param ip 操作IP
     * @return
     */
    ServiceResult<Boolean> saveSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                   String sellerName, String ip,String parkoperation);

    /**
     * 
     * 修改商家申请(平台修改商家申请用)
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @return
     */
    ServiceResult<Boolean> updateSellerApplyForAdmin(SellerApply sellerApply, String userName,
                                                     String sellerName,String parkoperation);

    /**
     * 
     * 保存商家申请(用户端商家申请用)<br>
     * <li>添加商家申请表数据
     * <li>添加商家数据
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @param memberId
     * @return
     */
    ServiceResult<Boolean> saveSellerApplyForFront(SellerApply sellerApply, String userName,
                                                   String sellerName, Integer memberId,String parkoperation);

    /**
     * 
     * 修改商家申请(用户端修改商家申请用)
     * 
     * @param sellerApply 商家申请
     * @param userName 商家登录名称
     * @param sellerName 商家店铺名称
     * @return
     */
    ServiceResult<Boolean> updateSellerApplyForFront(SellerApply sellerApply, String userName,
                                                     String sellerName,String parkoperation);

    /**
     * 根据公司名称查询入驻申请
     * @param company
     * @return
     */
    ServiceResult<List<SellerApply>> getSellerApplyByCompany(String company);

    /**
     * 当天商家申请数
     * @return
     */
    ServiceResult<Integer> getSellerApplyCount();
    /**
     * 获取园区和业务
     * @return
     */
    ServiceResult<List<Map<String,Object>>> getAddresAndBusiness();

    /*获取商家所属业务管理方*/
    ServiceResult<List<SellerParkOperation>> getBySellerId(Integer sellerId);
}