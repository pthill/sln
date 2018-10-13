package com.sln.service.product;

import java.util.List;
import java.util.Map;

import com.sln.core.PagerInfo;
import com.sln.core.ServiceResult;
import com.sln.entity.product.ProductBrand;

/**
 * 品牌管理
 * 
 * @Version: 1.0
 * @Author: zhaozhx
 * @Email: zhaozhx@sina.cn
 */
public interface IProductBrandService {

    /**
     * 保存品牌信息
     * 用途：商家中心添加品牌信息、平台添加品牌信息
     * @param brand
     * @return
     */
    ServiceResult<Boolean> save(ProductBrand brand);

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    ServiceResult<ProductBrand> getById(Integer id);

    /**
     * 根据id查询品牌信息
     * @param id
     * @return
     */
    ServiceResult<List<ProductBrand>> getByIds(String id);

    /**
     * 分页查询品牌信息
     * @param queryMap
     * @param pager
     * @return
     */
    ServiceResult<List<ProductBrand>> page(Map<String, String> queryMap, PagerInfo pager);

    /**
     * 根据productTypeId获取该类型下所有的品牌
     * @param typeId
     * @return
     */
    ServiceResult<List<ProductBrand>> getBrandByTypeId(Integer typeId);

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    ServiceResult<Boolean> update(ProductBrand brand);

    /**
     * 审核商家提交的品牌信息，修改 statue 字段
     * @param id
     * @param state
     * @return
     */
    ServiceResult<Boolean> audit(Integer id, Integer state, Integer userId);

    /**
     * 删除品牌信息，逻辑删除
     * @param id
     * @return
     */
    ServiceResult<Boolean> del(Integer id);

    /**
     * 取出所有可用的品牌
     * @return
     */
    ServiceResult<List<ProductBrand>> listNoPage();

    /**
     * 获取推荐的品牌
     * @return
     */
    ServiceResult<List<ProductBrand>> getHotBrands();

    /**
     * 获取所有品牌，按首字母分组
     * @return
     */
    ServiceResult<Map<String, List<ProductBrand>>> getBrandsLetterGrouping();

    /**
     * 当天未审核品牌数
     * @return
     */
    ServiceResult<Integer> getUndoBrand();

}
