package com.sln.dao.shop.write.cart;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.cart.Cart;

@Repository
public interface CartWriteDao {

    Integer update(Cart cart);

    /**
     * 添加购物车时，对已存在的货品增加数量
     * @param id
     * @param count
     * @return
     */
    Integer addCount(@Param("id") Integer id, @Param("count") Integer count);

    Integer delete(Integer id);

    Integer deleteByIds(@Param("ids") List<Integer> ids);

    /**
     * 根据用户ID和使用类型取购物车数据
     * @param memberId 用户ID
     * @param useType 使用类型：1、购物车用（取所有购物车记录），2、订单结算用（只取用户勾选的记录）
     * * @param source 1、商城商品  2、积分商品
     * @return
     */
    List<Cart> getByMemberId(@Param("memberId") Integer memberId,
                             @Param("useType") Integer useType,@Param("source") Integer source);

    /**
     * 根据会员ID 删除购物车内信息，固定一个条件checked=1
     * @param memberId
     * @param source  1:商城商品 2:积分商品
     * @return
     */
    Integer deleteByMemberId(@Param("memberId")Integer memberId,@Param("source") Integer source);

    Integer jobClearCart(@Param("clearTime") String clearTime);

    Cart get(java.lang.Integer id);

    Integer insert(Cart cart);

    /**
     * 根据用户ID、购物车ID修改购物车的选中状态
     * @param memberId
     * @param cartId
     * @param checked
     * @return
     */
    Integer updateChecked(@Param("memberId") Integer memberId, @Param("cartId") Integer cartId,
                          @Param("checked") Integer checked);

    /**
     * 根据用户ID修改购物车的选中状态
     * @param memberId
     * @param checked
     * @return
     */
    Integer updateCheckedAll(@Param("memberId") Integer memberId,
                             @Param("checked") Integer checked);

    /**
     * 根据用户ID和货品ID获取购物车
     * @param memberId
     * @param productGoodsId
     * @return
     */
    List<Cart> getByMIdAndGoodId(@Param("memberId") Integer memberId,
                                 @Param("productGoodsId") Integer productGoodsId,@Param("source") Integer source);

}
