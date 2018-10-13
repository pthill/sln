package com.sln.service.product;

import java.security.Provider.Service;
import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductCateJd;
import com.sln.entity.seller.SellerManageCate;
import com.sln.entity.system.SystemAdmin;
import com.sln.vo.product.FrontProductCateVO;
import com.sln.vo.product.ProductCateVO;

public interface IProductCateService {
    /**
    * 保存商品分类
    * @param  productCate
    * @return
    */
    ServiceResult<Boolean> saveProductCate(ProductCate productCate);

    /**
    * 更新商品分类
    * @param  productCate
    * @return
    */
    ServiceResult<Boolean> updateProductCate(ProductCate productCate);

    /**
    * 删除商品分类
    * @param  id
    * @return
    */
    ServiceResult<Boolean> delProductCate(Integer id);

    /**
    * 根据id取得商品分类
    * @param productCateId
    * @return
    */
    ServiceResult<ProductCate> getProductCateById(Integer productCateId);

    /**
    * 分页查询
    * @param queryMap
    * @param pager
    * @return
    */
    ServiceResult<List<ProductCateVO>> pageProductCate(Map<String, String> queryMap,
                                                       PagerInfo pager);

    /**
     * 根据pid查询商品分类信息
     * @param pid
     * @return
     */
    ServiceResult<List<ProductCate>> getByPid(Integer pid);

    /**
     * 商家发布商品时根据商家id获取商家一级分类信息
     * @param sellerId
     * @return
     */
    ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId);

    /**
     * 商家发布商品时根据商家id和上级分类id获取商家下级分类信息
     * @param sellerId
     * @param pid
     * @return
     */
    ServiceResult<List<ProductCate>> getCateBySellerId(Integer sellerId, Integer pid);

    /**
     * 根据商品分类id，获取分类路径名称 例如：数码办公 >电脑整机 >平板电脑
     * @param productCateId
     * @return
     */
    ServiceResult<String> getCatePathStrById(Integer productCateId);

    /**
     * 将该商家申请置为审核通过
     * @param id
     */
    SellerManageCate getSellerManageCate(Integer id);

    Boolean updateSellerManageCate(SellerManageCate cate);

    //
    /**
     * 根据pid查询商品分类信息
     * @param pid
     * @param seller
     * @return
     */
    ServiceResult<List<ProductCateVO>> getByPidAndSeller(Integer pid, Integer seller);

    /**
     * 取得所有显示状态的商品分类
     * @param  map
     * @return
     */
    ServiceResult<List<FrontProductCateVO>> getProductCateList(Map<String, Object> queryMap);

    ServiceResult<List<ProductCate>> getProductCate(Map<String, Object> param);
    
    /**
     * 同步JD分类
     */
    ServiceResult<Integer> syJdCate();
    
    /**
     * 添加京东分类到平台（有关联的则更新）
     * @param jdCatIds
     * @param systemAdmin
     * @return
     */
    ServiceResult<Boolean> addJdCategory(String jdCatIds,SystemAdmin systemAdmin);
    
    /**
     * 分页查询平台分类和京东分类关系信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductCateJd>> pageProductCateJd(Map<String, String> queryMap,
                                                        PagerInfo pager);
     
    /**
     * 批量新增平台分类和京东分类关系信息
     * @param productCateId
     * @param jdCategoryIds
     * @return
     */
     ServiceResult<Boolean> batchInsertProductCateJd(Integer productCateId,String jdCategoryIds);
     
     /**
      * 验证平台的京东分类是否与JD分类表建立关系
      */
     ServiceResult<Integer>VerifJDCate();
}