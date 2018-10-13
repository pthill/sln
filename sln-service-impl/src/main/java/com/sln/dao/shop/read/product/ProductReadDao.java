package com.sln.dao.shop.read.product;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.sln.entity.seller.SellerParkOperation;
import com.sln.entity.supplier.Supplier;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.sln.entity.product.Product;

@Repository
public interface ProductReadDao {

    /**
     * 获取size个商家推荐商品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    List<Product> getSellerRecommendProducts(@Param("sellerId") Integer sellerId,
                                             @Param("size") Integer size);

    /**
     * 获取size个商家新品
     * @param sellerId 商家ID
     * @param size 获取条数
     * @return
     */
    List<Product> getSellerNewProducts(@Param("sellerId") Integer sellerId,
                                       @Param("size") Integer size);

    /**
     * 查询商家所有在售商品（商家列表页）
     * @param sellerId
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param start
     * @param size
     * @return
     */
    List<Product> getProductForSellerList(@Param("sellerId") Integer sellerId,
                                          @Param("sort") Integer sort,
                                          @Param("sellerCateId") Integer sellerCateId,
                                          @Param("start") Integer start,
                                          @Param("size") Integer size);

    Integer getProductForSellerListCount(@Param("sellerId") Integer sellerId,
                                         @Param("sort") Integer sort,
                                         @Param("sellerCateId") Integer sellerCateId);

    /**
     * 获取size个推荐商品
     * @param size 获取条数
     * @return
     */
    List<Product> getRecommendProducts(@Param("size") Integer size);

    Product get(@Param("id") Integer id);

    /**
     * 根据类型查询出最大的
     * @param cateId
     * @return
     */
    List<Product> getByCateIdTop(@Param("cateId") Integer cateId,
                                 @Param("limitSize") Integer limitSize);

    /**
     * 根据商家id查询产品
     * @param sellerId 商家id
     * @return
     */
    List<Product> queryList(Map<String, Object> map);

    /**
     * 根据商家id查询产品
     * @param sellerId 商家id
     * @return
     */
    Integer queryCount(Map<String, Object> map);

    /**
     * 获取左边的内容
     * @return
     */
    List<Product> getByCateIdLeft(@Param("cateId") Integer cateId);

    List<Product> getProductsBySellerId(Integer sellerid);

    /**
     * 根据类型查询ID
     * @param cateId 分类ID
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param doSelf 自营非自营
     * @param store 库存
     * @return
     */
    List<Product> getByCateId(@Param("cateId") Integer cateId, @Param("sort") Integer sort,
                              @Param("doSelf") Integer doSelf, @Param("store") Integer store);

    /**
     * 分页查询所有在售的商品
     * @return
     */
    List<Product> getProducts(@Param("start") Integer start, @Param("size") Integer size);

    /**
     * 统计所有在售的商品
     * @return
     */
    Integer getProductsCount();

    /**
     * 查询商品表比dateIndex时间大的所有商品
     * @param dateIndex 上次索引更新的时间
     * @return
     */
    List<Product> getProductsUpdateTime(@Param("dateIndex") Date dateIndex);

    /**
     * 根据商家ID统计所有在售的商品
     * @return
     */
    Integer getUpProductCountBySellerId(Integer sellerId);

    /**
    * 查询商家列表页 商品信息
    * @param start 
    * @param size
    * @param cateString 商家分类的In 集合
    * @param sellerId 商家ID
    * @param sort 排序 0:默认；1、价格从低到高；2、价格从高到底；3、销量从高到底；4、销量从低到高；5、上架时间新旧；6、上架时间旧新
    * @return
    */
    List<Product> getProductListBySellerCateId(@Param("start") int start, @Param("size") int size,
                                               @Param("cateString") String cateString,
                                               @Param("sellerId") int sellerId,
                                               @Param("sort") int sort);

    /**
     * 根据商家商品分类统计商家商品
     * @param cateString 商家分类
     * @param sellerId 商家ID
     * @return
     */
    Integer getProductListBySellerCateIdCount(@Param("cateString") String cateString,
                                              @Param("sellerId") int sellerId);

    /**
     * 以商品id字符串获取商品
     * @param ids
     * @return
     */
    List<Product> getProductsByIds(@Param("list") List<Integer> ids);

    /**
     * 查询最大的商品
     * @return
     */
    Product getProductByMax();

    /**
     * 根据品牌查询商品
     * @param brandId 品牌ID
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param doSelf 自营非自营
     * @param store 库存
     * @return
     */
    List<Product> getByBrandId(@Param("brandId") Integer brandId, @Param("sort") Integer sort,
                               @Param("doSelf") Integer doSelf, @Param("store") Integer store,
                               @Param("start") Integer start, @Param("size") Integer size);

    /**
     * 根据品牌查询商品数量
     * @param brandId 品牌ID
     * @param doSelf 自营非自营
     * @param store 库存
     * @return
     */
    Integer getByBrandIdCount(@Param("brandId") Integer brandId, @Param("doSelf") Integer doSelf,
                              @Param("store") Integer store);

    /**
     * 根据品牌查询商品
     * @param brandId 品牌ID
     * @return
     */
    List<Product> getByBrandIdTop(@Param("brandId") Integer brandId);

    /**
     * 根据商品分类路径查询商品数量
     * @param productCatePath 分类路径如：/1/2
     * @return
     */
    Integer getProductByPathCount(@Param("productCatePath") String productCatePath);

    /**
     * 根据商品分类路径查询商品信息
     * @param productCatePath 分类路径如：/1/2
     * @param sort 排序：0-默认，1-价格升序，2-价格降序，3-销量降序，4-评价降序
     * @param start
     * @param size
     * @return
     */
    List<Product> getProductByPath(@Param("productCatePath") String productCatePath,
                                   @Param("sort") int sort, @Param("start") int start,
                                   @Param("size") int size);

    /**
     * 获取商品数量
     * @param transportId
     * @return
     */
    Integer getProNumByTransportId(@Param("transportId") Integer transportId);

    /**
     * 根据运费模板id和商品状态获取数量
     * @param id
     * @param state
     * @return
     */
    Integer getProNumByTransportIdAndState(@Param("transportId") Integer transportId,
                                           @Param("state") Integer state);

    /**
     * 查询当前业务管理方下的商品列表
     * @param queryMap
     * @return
     */
    List<Product> getProductsByRole(@Param("queryMap")Map<String,String> queryMap, @Param("start")Integer start,
                             @Param("size")Integer size, @Param("sellers")List<SellerParkOperation> list,
                             @Param("states")List<String> state);
    Integer productCountByRole(@Param("queryMap")Map<String,String> queryMap, @Param("sellers")List<SellerParkOperation> list,
                               @Param("states")List<String> state);

    List<Product> getProductByRoleForSeller(@Param("queryMap")Map<String,String> queryMap, @Param("start")Integer start,
                                            @Param("size")Integer size, @Param("suppliers")List<Supplier> list,
                                            @Param("states")List<String> state);
    Integer productCountByRoleForSeller(@Param("queryMap")Map<String,String> queryMap, @Param("suppliers")List<Supplier> list,
                               @Param("states")List<String> state);
    
    /**
     * 查询已经同步的商品有没有在京东中取消授权的
     * @return
     */
    List<Product> getCancelJdProduct();
    
    /**
     * 根据product_code查询是否已经同步过京东商品
     */
    Product getProductByCode(@Param("productCode") String productCode);
}
