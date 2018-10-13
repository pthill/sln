package com.sln.dao.shop.write.member;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.member.MemberCollectionProduct;

/**
 * 会员收藏商品表
 * 
 * @Filename: MemberCollectionProductWriteDao.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
@Repository
public interface MemberCollectionProductWriteDao {

    MemberCollectionProduct get(java.lang.Integer id);

    Integer save(MemberCollectionProduct memberCollectionProduct);

    Integer update(MemberCollectionProduct memberCollectionProduct);

    /**
     * 根据会员ID和商品ID获取会员收藏商品
     * @param memberId
     * @param productId
     * @return
     */
    List<MemberCollectionProduct> getByMIdAndPId(@Param("memberId") Integer memberId,
                                                 @Param("productId") Integer productId);
}
