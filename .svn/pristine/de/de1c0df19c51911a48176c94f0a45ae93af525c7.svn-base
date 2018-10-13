package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductBrand;
import com.sln.entity.product.ProductCate;

/**
 * 商城所有关于商品的查询
 *                       
 * @Filename: IProductFrontService.java
 * @Version: 1.0
 * @Author: 王朋
 * @Email: wpjava@163.com
 *
 */
public interface IProductFrontService {

    /**
     * 根据商品分类查询商品
     * @param cateId 商品分类ID
     * @param mapCondition 传递的参数
     * @param stack 传出参数map集合
     */
    void getProducts(Integer cateId, Map<String, Object> mapCondition, Map<String, Object> stack);

    /**
     * 根据分类查询列表页推荐的头部信息
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateIdTop(int cateId);

    /**
     * 根据分类查询列表页推荐的左边商品
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateIdLeft(int cateId);

    /**
     * 查询二级分类下三级分类的商品，默认取5个
     * @param cateId 分类Id
     * @return
     */
    ServiceResult<List<Product>> getProductListByCateId2(int cateId);

    /**
     * 搜索页面推荐商品信息头部
     * @return
     */
    ServiceResult<List<Product>> getProductSearchByTop();

    /**
     * 搜索页面推荐商品信息左部
     * @return
     */
    ServiceResult<List<Product>> getProductSearchByLeft();

    /**
     * 用户中心首页推荐商品
     * @return
     */
    ServiceResult<List<Product>> getProductMemberByTop();

    /**
     * 根据商家商品分类查询商家列表页 商品信息
     * @param start 
     * @param size
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @param sort 排序  0:默认；1、价格从低到高；2、价格从高到底；3、销量从高到底；4、销量从低到高；5、上架时间新旧；6、上架时间旧新
     * @return
     */
    ServiceResult<List<Product>> getProductListBySellerCateId(int start, int size, int cateId,
                                                              int sellerId, int sort);

    /**
     * 根据商家商品分类统计商家商品
     * @param cateId 商家分类
     * @param sellerId 商家ID
     * @return
     */
    ServiceResult<Integer> getProductListBySellerCateIdCount(int cateId, int sellerId);

    /**
     * 根据品牌查询商品
     * @param brandId 品牌ID
     * @param sort 0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param doSelf 自营非自营
     * @param store 库存
     * @param pager
     * @return
     */
    ServiceResult<List<Product>> getProductsByBrandId(Integer brandId, Integer sort, Integer doself,
                                                      Integer store, PagerInfo pager);

    /**
     * 根据品牌查询销量最好的商品
     * @param brandId 品牌ID
     * @return
     */
    ServiceResult<List<Product>> getProductsByBrandIdTop(Integer brandId);

    /**
     * 根据商品分类路径查询商品信息
     * @param productCatePath 分类路径如：/1/2
     * @param sort 排序：0-默认，1-价格升序，2-价格降序，3-销量降序，4-评价降序
     * @param pager 分页信息
     * @return
     */
    ServiceResult<List<Product>> getProductByPath(String productCatePath, int sort,
                                                  PagerInfo pager);

    /**
     * 购物车推荐商品（购买了该商品的用户还购买了）
     * @param cateId 分类ID
     * @param number 数量
     * @return
     */
    ServiceResult<List<Product>> getProductsListCart(int cateId, int number);

    /**
     * 根据分类的名称查询分类
     * @param name
     * @return
     */
    ServiceResult<ProductCate> getProductCateByName(String name);

    /**
     * 根据品牌的名称查询
     * @param name
     * @return
     */
    ServiceResult<ProductBrand> getProductBrandByName(String name);

    /**
    * 根据id取得商品表（从读库读取）
    * @param productId
    * @return
    */
    ServiceResult<Product> getProductById(Integer productId);

    //定时任务将到期的商品下架
    ServiceResult<Boolean> jobProductDown();
}
