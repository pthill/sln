package com.sln.service.cart;

import java.util.List;

import com.sln.core.ServiceResult;
import com.sln.entity.cart.Cart;
import com.sln.entity.member.MemberAddress;
import com.sln.vo.cart.CartInfoVO;

/**
 * 购物车service
 *                       
 * @Filename: ICartService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface ICartService {

    /**
     * 添加到购物车
     * @param cart
     * @return 购物车ID
     */
    ServiceResult<Integer> addToCart(Cart cart);

    /**
     * 根据登录用户取得购物车信息，用于购物车列表页，所有数据都从写库获取
     * @param memberId
     * @param memberAddress 用户选择的地址信息，用于计算运费，如果是null表示不计算运费
     * @param source 来源，1、pc；2、H5；3、Android；4、IOS；
     * @param useType 使用类型：1、购物车用（取所有购物车记录），2、订单结算用（取用户勾选的记录）
     * @return
     */
    public ServiceResult<CartInfoVO> getCartInfoByMId(Integer memberId, MemberAddress memberAddress,
                                                      Integer source, Integer useType,Integer cartSource);

    /**
     * 根据购物车id更新商城购物车，只更新商品数量
     * @param id
     * @param number
     * @return
     */
    ServiceResult<Boolean> updateCartNumber(Integer id, Integer number);

    /**
     * 批量删除购物车商品
     * @param ids
     * @return
     */
    ServiceResult<Boolean> deleteCartByIds(List<Integer> ids);

    /**
     * 根据id取得商城购物车
     * @param  cartId
     * @return
     */
    ServiceResult<Cart> getCartById(Integer cartId);

    /**
     * 系统定时任务清除7天之前添加的购物车数据
     * @return
     */
    ServiceResult<Boolean> jobClearCart();

    /**
     * 根据用户ID获取用户购物车数量
     * @param memberId
     * @return
     */
    ServiceResult<Integer> getCartNumberByMId(Integer memberId,Integer source);

    /**
     * 根据用户ID、购物车ID修改购物车的选中状态，cartId为0时表示全部选中或不选中
     * @param memberId
     * @param cartId
     * @param checked
     * @return
     */
    ServiceResult<Boolean> updateChecked(Integer memberId, Integer cartId, Integer checked);

    /**
     * 根据用户ID取得此用户购物车中最后加入的一个商品
     * @param memberId
     * @return
     */
    ServiceResult<Cart> getCartByLastOne(Integer memberId);
}