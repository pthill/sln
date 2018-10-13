package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductAttr;
import com.sln.entity.product.ProductPicture;

/**
 * 商品服务
 *                       
 * @Filename: IProductService.java
 * @Version: 1.0
 * @Author: 陈万海
 * @Email: chenwanhai@sina.com
 *
 */
public interface IProductService {

    /**
     * 获取cateId分类下的推荐商品
     * @param cateId
     * @return
     */
    ServiceResult<List<Product>> getByCateIdTop(Integer cateId, Integer limit);

    /**
     * 获取size个商家推荐商品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    ServiceResult<List<Product>> getSellerRecommendProducts(Integer sellerId, Integer size);

    /**
     * 获取size个商家新品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    ServiceResult<List<Product>> getSellerNewProducts(Integer sellerId, Integer size);

    /**
     * 查询商家所有在售商品（商家列表页）
     * @param sellerId
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param pager
     * @return
     */
    ServiceResult<List<Product>> getProductForSellerList(Integer sellerId, Integer sort,
                                                         Integer sellerCateId, PagerInfo pager);

    /**
     * 获取size个推荐商品
     * @param size
     * @return
     */
    ServiceResult<List<Product>> getRecommendProducts(Integer size);

    /**
     * 保存商品信息
     * @param map 
     * @param product 商品
     * @param productPictureList 商品图片
     * @param productAttrList 商品属性
     * @return
     */
    ServiceResult<Boolean> saveProduct(Map<String, String[]> parammap, Product product,
                                       List<ProductPicture> productPictureList,
                                       List<ProductAttr> productAttrList);

    /**
    * 更新商品表
    * @param  product
    * @return
    */
    ServiceResult<Boolean> updateProduct(Map<String, String[]> parammap, Product product,
                                         List<ProductPicture> productPictureList,
                                         List<ProductAttr> productAttrList);

    /**
    * 删除商品表
    * @param  id
     * @param integer 
    * @return
    */
    ServiceResult<Boolean> delProduct(Integer id, Integer sellerId);

    /**
    * 根据id取得商品表（从写库读取）
    * @param productId
    * @return
    */
    ServiceResult<Product> getProductById(Integer productId);

    /**
    * 分页
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<Product>> getProductsByRole(Map<String, String> queryMap, PagerInfo pager);

    ServiceResult<List<Product>> getProductByRoleForSeller(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 以商家id获取所有商品
     * @param integer
     * @return
     */
    ServiceResult<List<Product>> getProductsBySellerId(Integer integer);

    ServiceResult<Integer> updateByIds(Map<String, Object> map, List<Integer> string2IntegerList);

    /**
     * 根据商品ID修改状态
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> updateProductState(Integer id, Integer state);

    /**
     * 根据商品ID修改推荐状态
     * @param id
     * @param isTop
     * @return
     */
    ServiceResult<Boolean> updateProductRecommend(Integer id, Integer isTop);

    /**
     * 以商品id字符串获取商品
     * @param ids
     * @return
     */
    ServiceResult<List<Product>> getProductsByIds(List<Integer> ids);

    /**
     * 查询最大的商品
     * @return
     */
    ServiceResult<Product> getProductByMax();

    //    /**
    //     * 更新单个商品
    //     * @param map 
    //     * @param product
    //     * @param attrList 
    //     * @param picList 
    //     * @return
    //     */
    //    ServiceResult<Boolean> updateProduct(Product product);

    /**
     * 新增商品时唯一性校验
     * @param sellerId
     * @param productCode
     * @param productId 商品ID，新增时传0，修改时传实际ID
     * @return
     */
    ServiceResult<Boolean> isUnique(Integer sellerId, String productCode, Integer productId);
    
    ServiceResult<List<Product>> pageProduct(Map<String, String> queryMap, PagerInfo pager);
    
    /**
     * 同步京东商品信息
     */
    ServiceResult<Integer> syJdProduct();
    
    /**
     * 处理京东商品推送消息
     * @param access_token 访问京东的令牌
     * @param messageList 推送消息
     * @return
     */
    ServiceResult<Boolean> handleJDProductMessage(AccessToken token ,Map<Integer,List<JDMessage>> messageList);
    
}