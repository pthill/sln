package com.sln.service.member;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.member.MemberCollectionProduct;
import com.sln.vo.member.FrontMemberCollectionProductVO;

/**
 * 用户收藏商品
 *                       
 * @Filename: IMemberCollectionProductService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IMemberCollectionProductService {

    /**
     * 根据id取得会员收藏商品表
     * @param  memberCollectionProductId
     * @return
     */
    ServiceResult<MemberCollectionProduct> getMemberCollectionProductById(Integer memberCollectionProductId);

    /**
     * 根据会员id取得会员收藏商品表
     * @param request
     * @param pager
     * @return
     */
    ServiceResult<List<FrontMemberCollectionProductVO>> getCollectionProductByMemberId(Map<String, Object> queryMap,
                                                                                       Integer memberId,
                                                                                       PagerInfo pager);

    /**
     * 保存会员收藏商品表
     * @param productId
     * @param request
     * @return
     */
    public ServiceResult<MemberCollectionProduct> saveMemberCollectionProduct(Integer productId,
                                                                              Integer memberId);

    /**
     * 取消收藏商品
     * @param productId
     * @param request
     * @return
     */
    public ServiceResult<MemberCollectionProduct> cancelCollectionProduct(Integer productId,
                                                                          Integer memberId);

}