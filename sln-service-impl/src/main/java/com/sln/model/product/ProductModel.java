package com.sln.model.product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.util.HtmlUtils;

import com.alibaba.fastjson.JSON;
import com.sln.core.ConstantsEJS;
import com.sln.core.PagerInfo;
import com.sln.core.StringUtil;
import com.sln.core.exception.BusinessException;
import com.sln.core.freemarkerutil.DomainUrlUtil;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.bean.AccessToken;
import com.sln.core.jd.bean.JDMessage;
import com.sln.core.jd.bean.JdProductDto;
import com.sln.core.jd.util.JDApiResult;
import com.sln.core.jd.util.JDConfig;
import com.sln.core.jd.util.JDMessageType;
import com.sln.dao.shop.read.jd.JdProductReadDao;
import com.sln.dao.shop.read.product.ProductBrandReadDao;
import com.sln.dao.shop.read.product.ProductCateReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.read.seller.SellerParkOperationReadDao;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.read.seller.SellerRolesReadDao;
import com.sln.dao.shop.read.seller.SellerUserReadDao;
import com.sln.dao.shop.read.supplier.SupplierReadDao;
import com.sln.dao.shop.read.system.SystemAdminReadDao;
import com.sln.dao.shop.write.product.ProductAttrWriteDao;
import com.sln.dao.shop.write.product.ProductBrandWriteDao;
import com.sln.dao.shop.write.product.ProductCateWriteDao;
import com.sln.dao.shop.write.product.ProductGoodsWriteDao;
import com.sln.dao.shop.write.product.ProductNormAttrOptWriteDao;
import com.sln.dao.shop.write.product.ProductPictureWriteDao;
import com.sln.dao.shop.write.product.ProductWriteDao;
import com.sln.dao.shop.write.seller.SellerCateWriteDao;
import com.sln.dao.shop.write.system.SystemRolesWriteDao;
import com.sln.entity.jd.JdProduct;
import com.sln.entity.product.Product;
import com.sln.entity.product.ProductAttr;
import com.sln.entity.product.ProductCate;
import com.sln.entity.product.ProductGoods;
import com.sln.entity.product.ProductNormAttrOpt;
import com.sln.entity.product.ProductPicture;
import com.sln.entity.seller.Seller;
import com.sln.entity.seller.SellerCate;
import com.sln.entity.seller.SellerParkOperation;
import com.sln.entity.seller.SellerRoles;
import com.sln.entity.seller.SellerUser;
import com.sln.entity.supplier.Supplier;
import com.sln.entity.system.SystemAdmin;
import com.sln.entity.system.SystemRoles;
import com.sln.util.FrontProductPictureUtil;

@Service(value = "productModel")
public class ProductModel {
    private static Logger                log = LogManager.getLogger(ProductModel.class);
    @Resource
    private SupplierReadDao              supplierReadDao;
   
    @Resource
    private ProductWriteDao              productWriteDao;
    @Resource
    private ProductReadDao               productReadDao;
    @Resource
    private ProductPictureWriteDao       productPictureWriteDao;
    @Resource
    private ProductAttrWriteDao          productAttrWriteDao;
    @Resource
    private ProductGoodsWriteDao         productGoodsWriteDao;
    @Resource(name = "transactionManager")
    private DataSourceTransactionManager transactionManager;
    @Resource
    private ProductNormAttrOptWriteDao productNormAttrOptWriteDao;
    @Resource
    private SystemAdminReadDao         systemAdminReadDao;
    @Resource
    private SystemRolesWriteDao        systemRolesWriteDao;
    @Resource
    private SellerParkOperationReadDao  sellerParkOperationReadDao;
    @Resource
    private ProductBrandWriteDao         productBrandWriteDao;
    @Resource
    private ProductCateWriteDao          productCateWriteDao;
    @Resource
    private SellerCateWriteDao           sellerCateWriteDao;
    @Resource
    private SellerRolesReadDao          sellerRolesReadDao;
    @Resource
    private SellerReadDao                sellerReadDao;
    @Resource
    private SellerUserReadDao            sellerUserReadDao;
    @Resource
    private JdProductReadDao             jdProductReadDao;
    @Resource
    private ProductCateReadDao           productCateReadDao;
    @Resource
    private ProductBrandReadDao          productBrandReadDao;
    @Resource
    private ProductGoodsModel            productGoodsModel;
    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil    frontProductPictureUtil;

    public List<Product> getByCateIdTop(Integer cateId, Integer limit) {
        List<Product> list = productReadDao.getByCateIdTop(cateId, limit);
        // 处理图片路径
        for (int i = 0; i < list.size(); i++) {
            String img = list.get(i).getMasterImg();
            if (null != list.get(i).getSource() && list.get(i).getSource() == 2) {
                list.get(i).setMasterImg(JDConfig.IMAGE_PATH_160 + img);
            }else{
                list.get(i).setMasterImg(DomainUrlUtil.SLN_IMAGE_RESOURCES + img);
            }
        }
        return list;
    }

    /**
     * 获取size个商家推荐商品
     * 
     * @param sellerId
     *            商家ID
     * @param size
     *            获取条数
     * @return
     */
    public List<Product> getSellerRecommendProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerRecommendProducts(sellerId, size);
        this.setProductMiddleImg(list);
        return list;
    }

    /**
     * 获取size个商家新品
     * 
     * @param sellerId
     *            商家ID
     * @param size
     *            获取条数
     * @return
     */
    public List<Product> getSellerNewProducts(Integer sellerId, Integer size) {
        List<Product> list = productReadDao.getSellerNewProducts(sellerId, size);
        this.setProductMiddleImg(list);
        return list;
    }

    /**
     * 查询商家所有在售商品（商家列表页）
     * 
     * @param sellerId
     * @param sort
     *            0:默认；1、销量从大到小；2、评价从多到少；3、价格从低到高；4、价格从高到低
     * @param sellerCateId
     * @param start
     * @param size
     * @return
     */
    public List<Product> getProductForSellerList(Integer sellerId, Integer sort,
                                                 Integer sellerCateId, Integer start,
                                                 Integer size) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        List<Product> products = productReadDao.getProductForSellerList(sellerId, sort,
            sellerCateId, start, size);
        this.setProductMiddleImg(products);
        return products;
    }

    public Integer getProductForSellerListCount(Integer sellerId, Integer sort,
                                                Integer sellerCateId) {
        // 排序支持0到4，其他一律按0处理
        if (sort == null || sort > 4) {
            sort = 0;
        }
        return productReadDao.getProductForSellerListCount(sellerId, sort, sellerCateId);
    }

    /**
     * 给商品设置中图路径
     * 
     * @param list
     */
    private void setProductMiddleImg(List<Product> list) {
        if (list != null && list.size() > 0) {
            for (Product product : list) {
                // 中图路径
                String productLeadMiddle = frontProductPictureUtil
                    .getproductLeadMiddle(product.getId());
                product.setImagePath(productLeadMiddle);
            }
        }
    }

    /**
     * 获取size个推荐商品
     * 
     * @return
     */
    public List<Product> getRecommendProducts(Integer size) {
        List<Product> list = productReadDao.getRecommendProducts(size);
        this.setProductMiddleImg(list);
        return list;
    }

    public Boolean saveProduct(Map<String, String[]> parammap, Product product,
                               List<ProductPicture> productPictureList,
                               List<ProductAttr> productAttrList) {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {

            // 1.check
            // 校验spu唯一，新增时商品ID为空
            Boolean unique = this.isUnique(product.getSellerId(), product.getProductCode(), 0);
            if (!unique) {
                throw new BusinessException("SPU不能重复，请重新输入");
            }
            // 校验sku唯一
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                // 使用write库防止数据不同步
                HashSet<String> set = new HashSet<String>();
                for (ProductGoods goods : product.getGoodsList()) {
                    // 校验与自己的重复
                    if (!set.add(goods.getSku())) {
                        throw new BusinessException("SKU[" + goods.getSku() + "]不能重复，请重新输入");
                    }
                    // 校验与数据库的重复
                    List<ProductGoods> prolist = productGoodsWriteDao
                        .getBySellerIdAndSku(product.getSellerId(), goods.getSku());
                    if (prolist != null && prolist.size() > 0) {
                        throw new BusinessException("SKU[" + goods.getSku() + "]已存在，请重新输入");
                    }
                }
            } else {
                // 校验与数据库的重复
                List<ProductGoods> prolist = productGoodsWriteDao
                    .getBySellerIdAndSku(product.getSellerId(), product.getSku());
                if (prolist != null && prolist.size() > 0) {
                    throw new BusinessException("SKU[" + product.getSku() + "]已存在，请重新输入");
                }
            }

            // 2.save product
            this.dbConstrainsProduct(product);
            if(product.getProductCode() == null || !"".equals(product.getProductCode())) {
            	product.setProductCode(productGoodsModel.getSku()+"");
            }
            productWriteDao.insert(product);
            // 3.save productPicture
            if (null != productPictureList && productPictureList.size() > 0) {
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }
            // 4.save productAttr
            if (null != productAttrList && productAttrList.size() > 0) {
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }
            // 5.save productGood
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                for (ProductGoods goods : product.getGoodsList()) {
                    goods.setProductId(product.getId());
                    this.dbConstrainsProductGood(goods);
                    goods.setSku(productGoodsModel.getSku()+"");
                    productGoodsWriteDao.insert(goods);
                }
            } else {
                // 默认货品
                ProductGoods goods = new ProductGoods();
                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(-1);
                goods.setActualSales(0);
                goods.setSku(product.getSku());
                goods.setState(ProductGoods.ENABLE);
                goods.setImages("");
                goods.setWeight(product.getWeight());
                goods.setLength(product.getLength());
                goods.setWidth(product.getWidth());
                goods.setHeight(product.getHeight());
                goods.setSku(productGoodsModel.getSku()+"");
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.insert(goods);
            }

            // 6.保存选中的sku信息
            // 只有启用规格且货品不为空时保存选中的sku规格
            if (product.getIsNorm() == Product.IS_NORM_2 && product.getGoodsList() != null) {
                saveSelectedNorm(parammap, product, 1);
            }

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl saveProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl saveProduct exception:", e);
            throw e;
        }
    }

    /**
     * 保存选择的规格属性
     * 
     * @param parammap
     * @param product
     * @param type
     * @throws Exception
     */
    private void saveSelectedNorm(Map<String, String[]> parammap, Product product, int type) {
        String normNum = getRequestValue(parammap, "normNum");
        String normAttrNum = getRequestValue(parammap, "normAttrNum");
        if (StringUtil.isEmpty(normNum) || Integer.valueOf(normNum) < 1)
            return;
        if (StringUtil.isEmpty(normAttrNum) || Integer.valueOf(normAttrNum) < 1)
            return;

        // 编辑
        if (type == 2) {
            // 当前商品选中的属性
            Map<String, Object> param = new HashMap<>();
            param.put("productId", product.getId() + "");
            param.put("sellerId", product.getSellerId() + "");
            List<ProductNormAttrOpt> optlist = productNormAttrOptWriteDao.page(param);

            for (int i = 0; i <= Integer.valueOf(normNum); i++) {
                for (int j = 0; j <= Integer.valueOf(normAttrNum); j++) {
                    if (optlist == null || optlist.size() < 1) {
                        // 可能是旧商品数据
                        createnew(parammap, product.getSellerId(), product, i, j);
                    } else {
                        // 更新图片
                        for (ProductNormAttrOpt opt : optlist) {
                            String attr = getRequestValue(parammap, "attr_id_" + i + "_" + j);
                            if (StringUtil.isEmpty(attr)
                                || opt.getAttrId().intValue() != Integer.valueOf(attr)) {
                                continue;
                            }
                            String img = getRequestValue(parammap, "image_" + i + "_" + j);
                            log.debug(opt.getId() + "更新图片：" + img);
                            if (!StringUtil.isEmpty(img)) {
                                opt.setImage(img);
                                productNormAttrOptWriteDao.update(opt);

                                // 更新对应货品图片
                                saveGoodsPic(opt, product.getGoodsList());
                            }
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i <= Integer.valueOf(normNum); i++) {
                for (int j = 0; j <= Integer.valueOf(normAttrNum); j++) {
                    String normid = getRequestValue(parammap, "norm_id_" + i + "_" + j);
                    // 只有用户点击某属性才会有此值
                    String attrid = getRequestValue(parammap, "attr_id_" + i + "_" + j);
                    // 没有选择此规格
                    if (StringUtil.isEmpty(normid) || StringUtil.isEmpty(attrid))
                        continue;
                    createnew(parammap, product.getSellerId(), product, i, j);
                }
            }
        }

    }

    private void saveGoodsPic(ProductNormAttrOpt opt, List<ProductGoods> pglist) {
        for (ProductGoods pg : pglist) {
            if (StringUtil.isEmpty(pg.getNormAttrId()))
                continue;
            String[] normattrids = pg.getNormAttrId().split(",");
            for (String attrid : normattrids) {
                if (Integer.valueOf(attrid).intValue() == Integer.valueOf(opt.getAttrId())
                    .intValue()) {
                    pg.setImages(opt.getImage());
                    productGoodsWriteDao.update(pg);
                    break;
                }
            }
        }
    }

    private String getRequestValue(Map<String, String[]> parammap, String key) {
        if (parammap == null || parammap.size() < 1)
            return null;
        String[] values = parammap.get(key);
        if (values == null) {
            return null;
        }
        return values[0];
    }

    private void createnew(Map<String, String[]> parammap, Integer sellerId, Product product, int i,
                           int j) {
        // 新增

        ProductNormAttrOpt opt = new ProductNormAttrOpt();
        opt.setSellerId(sellerId);
        opt.setCreateId(product.getCreateId());
        String colortype = getRequestValue(parammap, "type_attr_" + i + "_" + j);
        opt.setTypeAttr(!StringUtil.isEmpty(colortype) && "custom".equals(colortype) ? 2 : 1);
        opt.setCreateTime(new Date());
        // 类型固定图片
        opt.setType(2);
        // 属性值
        String attrid = getRequestValue(parammap, "attr_id_" + i + "_" + j);
        if (!StringUtil.isEmpty(attrid))
            opt.setAttrId(Integer.valueOf(attrid));

        String normid = getRequestValue(parammap, "norm_id_" + i + "_" + j);
        if (!StringUtil.isEmpty(normid))
            opt.setProductNormId(Integer.valueOf(normid));

        String normname = getRequestValue(parammap, "norm_name_" + i + "_" + j);
        if (!StringUtil.isEmpty(normname))
            opt.setProductNormName(normname);

        String image = getRequestValue(parammap, "image_" + i + "_" + j);
        if (!StringUtil.isEmpty(image))
            opt.setImage(image);

        String attrname = getRequestValue(parammap, "attr_name_" + i + "_" + j);
        if (!StringUtil.isEmpty(attrname))
            opt.setName(attrname);
        opt.setProductId(product.getId());
        productNormAttrOptWriteDao.save(opt);

        // 更新货品图片
        for (ProductGoods pg : product.getGoodsList()) {
            if (StringUtil.isEmpty(pg.getNormAttrId()))
                continue;
            String[] normattrids = pg.getNormAttrId().split(",");
            for (String normattid : normattrids) {
                if (Integer.valueOf(normattid).intValue() == Integer.valueOf(opt.getAttrId())
                    .intValue()) {
                    pg.setImages(opt.getImage());
                    productGoodsWriteDao.update(pg);
                    break;
                }
            }
        }

    }

    public Boolean updateProduct(Map<String, String[]> parammap, Product product,
                                 List<ProductPicture> productPictureList,
                                 List<ProductAttr> productAttrList) {
        // 如果商品被删除,取消推荐
        if (product != null && null != product.getState()
            && product.getState() == Product.STATE_5) {
            product.setIsTop(ConstantsEJS.PRODUCT_IS_TOP_1);
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = transactionManager.getTransaction(def);
        try {
            if (null == product)
                throw new BusinessException("更新商品失败，商品信息为空");
            if (null == product.getId() || 0 == product.getId())
                throw new BusinessException("更新商品失败，商品id为空");

            // spu不能重复
            Boolean unique = this.isUnique(product.getSellerId(), product.getProductCode(),
                product.getId());
            if (!unique) {
                throw new BusinessException("SPU已存在，请重新输入");
            }

            /** 更新商品 **/
            productWriteDao.update(product);

            /** 更新商品图片 **/
            if (null != productPictureList && productPictureList.size() > 0) {
                productPictureWriteDao.delByProductId(product.getId());
                for (ProductPicture picture : productPictureList) {
                    picture.setProductId(product.getId());
                    productPictureWriteDao.insert(picture);
                }
            }

            /** 更新商品 **/
            if (null != productAttrList && productAttrList.size() > 0) {
                productAttrWriteDao.delByProductId(product.getId());
                for (ProductAttr attr : productAttrList) {
                    attr.setProductId(product.getId());
                    productAttrWriteDao.insert(attr);
                }
            }

            /** 更新货品 **/
            if (null != product.getGoodsList() && product.getGoodsList().size() > 0) {
                // 使用write库防止数据不同步
                // 校验sku唯一
                HashSet<String> set = new HashSet<String>();
                for (ProductGoods goods : product.getGoodsList()) {
                    // goods.setNormName();颜色:黄色,尺码:XL
                    // normName:normAttrName,normName:normAttrName,
                    goods.setProductId(product.getId());
                    this.dbConstrainsProductGood(goods);
                    ProductGoods dbGood = productGoodsWriteDao
                        .getByProductIdAndAttrId(product.getId(), goods.getNormAttrId());
                    if (null != dbGood) {
                        goods.setId(dbGood.getId());
                        productGoodsWriteDao.update(goods);
                    } else {
                        productGoodsWriteDao.insert(goods);
                    }

                    // 校验与自己的重复
                    if (!set.add(goods.getSku())) {
                        throw new BusinessException("SKU[" + goods.getSku() + "]不能重复，请重新输入");
                    }
                    // 校验与数据库的重复
                    List<ProductGoods> prolist = productGoodsWriteDao
                        .getBySellerIdAndSku(product.getSellerId(), goods.getSku());
                    if (prolist != null && prolist.size() > 1) {
                        throw new BusinessException("SKU[" + goods.getSku() + "]已存在，请重新输入");
                    } else if (prolist != null && prolist.size() == 1) {
                        // 如果只有一个，则判断id是否一样
                        if (!goods.getId().equals(prolist.get(0).getId())) {
                            throw new BusinessException("SKU[" + goods.getSku() + "]已存在，请重新输入");
                        }
                    }
                }
            } else {

                // 默认货品
                ProductGoods goods = productGoodsWriteDao.getByProductId(product.getId());

                // 校验与数据库的重复
                List<ProductGoods> prolist = productGoodsWriteDao
                    .getBySellerIdAndSku(product.getSellerId(), product.getSku());
                if (prolist != null && prolist.size() > 1) {
                    throw new BusinessException("SKU[" + product.getSku() + "]已存在，请重新输入");
                } else if (prolist != null && prolist.size() == 1) {
                    // 如果只有一个，则判断id是否一样
                    if (!goods.getId().equals(prolist.get(0).getId())) {
                        throw new BusinessException("SKU[" + product.getSku() + "]已存在，请重新输入");
                    }
                }

                goods.setProductId(product.getId());
                goods.setMallPcPrice(product.getMallPcPrice());
                goods.setMallMobilePrice(product.getMalMobilePrice());
                goods.setProductStock(product.getProductStock());
                goods.setProductStockWarning(-1);
                goods.setSku(product.getSku());
                goods.setImages("");
                goods.setWeight(product.getWeight());
                goods.setLength(product.getLength());
                goods.setWidth(product.getWidth());
                goods.setHeight(product.getHeight());
                this.dbConstrainsProductGood(goods);
                productGoodsWriteDao.update(goods);
            }

            /** 更新选中的sku信息 */
            // 只有启用规格且货品不为空时保存选中的sku规格
            if (product.getIsNorm() == 2 && product.getGoodsList() != null) {
                saveSelectedNorm(parammap, product, 2);
            }

            transactionManager.commit(status);
            return true;
        } catch (Exception e) {
            transactionManager.rollback(status);
            log.error("ProductServiceImpl updateProduct param:" + JSON.toJSONString(product));
            log.error("ProductServiceImpl updateProduct exception:", e);
            throw e;
        }
    }

    public Boolean delProduct(Integer productId, Integer sellerId) {
        if (null == productId || 0 == productId) {
            throw new BusinessException("根据id删除商品表失败，id为空");
        }
        Product product = productWriteDao.get(productId);
        if (product == null) {
            throw new BusinessException("根据id删除商品表失败，商品不存在");
        }
        if (!product.getSellerId().equals(sellerId)) {
            throw new BusinessException("您无权限删除该商品。");
        }
        if (product.getState().intValue() == Product.STATE_6) {
            throw new BusinessException("已经上架的商品不能删除");
        }
        return productWriteDao.updateState(productId, Product.STATE_5) > 0;
    }

    public Product getProductById(Integer productId) {
        if (null == productId || 0 == productId) {
            throw new BusinessException("根据id获取商品表失败，id为空");
        }

        Product product = productWriteDao.get(productId);
        if (product.getIsNorm() != 2) {
            ProductGoods goods = productGoodsWriteDao.getByProductId(productId);
            product.setSku(goods.getSku());
            product.setWeight(goods.getWeight());
            product.setLength(goods.getLength());
            product.setWidth(goods.getWidth());
            product.setHeight(goods.getHeight());
        }
        return product;
    }
    /*平台端 商品列表进行数据隔离*/
    public Integer getProductCountByRole(Map<String, String> queryMap){
        String adminId=queryMap.get("adminId");
        SystemAdmin admin=systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        String state = queryMap.get("q_state");
        List<String> states = Arrays.asList(state.split(","));
        if(systemRoles.getRoleType().equals("1")){//当前角色是平台查看所有申请
            return productReadDao.productCountByRole(queryMap, null, states);
        }else{//当前角色是业务管理方查看当前业务管理方下的角色申请
            List<SellerParkOperation> sellers=sellerParkOperationReadDao.getSellerParkOperations(admin.getParkId(),admin.getOperationId());
            if(sellers!=null&&sellers.size()>0){
                return productReadDao.productCountByRole(queryMap, sellers, states);
            }else{
                return 0;
            }

        }
    }

    /*平台端 商品列表进行数据隔离*/
    public List<Product> getProductsByRole(Map<String, String> queryMap, Integer start, Integer size) {
        String adminId = queryMap.get("adminId");
        SystemAdmin admin = systemAdminReadDao.get(Integer.parseInt(adminId));
        SystemRoles systemRoles = systemRolesWriteDao.get(admin.getRoleId());
        String state = queryMap.get("q_state");
        List<String> states = Arrays.asList(state.split(","));
        if (systemRoles.getRoleType().equals("1")) {//当前角色是平台查看所有申请
            return productReadDao.getProductsByRole(queryMap, start, size, null, states);
        } else {//当前角色是业务管理方查看当前业务管理方下的商家下商品的列表
            List<SellerParkOperation> sellers = sellerParkOperationReadDao
                    .getSellerParkOperations(admin.getParkId(), admin.getOperationId());
            if (sellers != null && sellers.size() > 0) {
                return productReadDao.getProductsByRole(queryMap, start, size, sellers, states);
            } else {
                return new ArrayList<Product>();
            }
        }
    }

    /*商家端 商品列表进行数据隔离*/
    public Integer getProductCountByRoleForSeller(Map<String,String>queryMap){
        String id=queryMap.get("id");
        SellerUser user= sellerUserReadDao.get(Integer.parseInt(id));
        SellerRoles sellerRoles= sellerRolesReadDao.get(user.getRoleId());
        String state = queryMap.get("q_state");
        List<String> states = Arrays.asList(state.split(","));
        if(sellerRoles.getRoleType().equals("0")){//当前角色是商家角色
            //查询商家下的供应商发布的商品
            List<Supplier> suppliers=supplierReadDao.getSupplierBySellerId(user.getSellerId());
            if(suppliers!=null&&suppliers.size()>0){
                return productReadDao.productCountByRoleForSeller(queryMap,suppliers,states);
            }else{
                return 0;
            }
        }else{//当前角色是供应商角色只查询当前供应商发布的商品
            List<Supplier> suppliers=new ArrayList<Supplier>();
            Supplier supplier=new Supplier();
            supplier.setId(user.getSupplierId());
            suppliers.add(supplier);
            return productReadDao.productCountByRoleForSeller(queryMap, suppliers, states);
        }
    }

    /*商家端 商品列表进行数据隔离*/
    public List<Product> getProductsByRoleForSeller(Map<String, String> queryMap, Integer start, Integer size) {
        String id=queryMap.get("id");
        SellerUser user= sellerUserReadDao.get(Integer.parseInt(id));
        SellerRoles sellerRoles= sellerRolesReadDao.get(user.getRoleId());
        String state = queryMap.get("q_state");
        List<String> states = Arrays.asList(state.split(","));
        if (sellerRoles.getRoleType().equals("0")) {//当前角色是商家角色
            List<Supplier> suppliers=supplierReadDao.getSupplierBySellerId(user.getSellerId());
            if(suppliers!=null&&suppliers.size()>0){
                return productReadDao.getProductByRoleForSeller(queryMap,start,size,suppliers,states);
            }else{
                return new ArrayList<Product>();
            }
        } else {//当前角色是供应商角色
            List<Supplier> suppliers=new ArrayList<Supplier>();
            Supplier supplier=new Supplier();
            supplier.setId(user.getSupplierId());
            suppliers.add(supplier);
            return productReadDao.getProductByRoleForSeller(queryMap,start,size,suppliers, states);
        }
    }


    public List<Product> getProductsBySellerId(Integer sellerid) {
        if (sellerid == null)
            throw new BusinessException("没有商家");
        return productWriteDao.getProductsBySellerId(sellerid);
    }

    public Integer updateByIds(Map<String, Object> param, List<Integer> ids) {
        return productWriteDao.updateByIds(param, ids);
    }

    /**
     * 根据商品ID修改商品状态
     * 
     * @param id
     * @param state
     * @return
     */
    public boolean updateProductState(Integer id, Integer state) {
    	Product product = productWriteDao.get(id);
    	if(product.getIsWelfareProduct() == Product.IS_WELFARE_2) {
    		//如果是福利商品  审核后直接上架
    		return productWriteDao.updateState(id, Product.STATE_6) > 0;
    	}
		return productWriteDao.updateState(id, state) > 0;
    	
    }

    /**
     * 根据商品ID修改商品推荐状态
     * 
     * @param id
     * @param isTop
     * @return
     */
    public boolean updateProductRecommend(Integer id, Integer isTop) {
        return productWriteDao.updateRecommend(id, isTop) > 0;
    }

    /**
     * 以商品id字符串获取商品
     * 
     * @param ids
     * @return
     */
    public List<Product> getProductsByIds(List<Integer> ids) {
        return productReadDao.getProductsByIds(ids);
    }

    private void dbConstrainsProduct(Product product) {
        product.setName1(StringUtil.dbSafeString(product.getName1(), false, 200));
        product.setName2(StringUtil.dbSafeString(product.getName2(), false, 200));
        product.setKeyword(StringUtil.dbSafeString(product.getKeyword(), false, 200));
        product.setNormIds(StringUtil.dbSafeString(product.getNormIds(), false, 255));
        product.setNormName(StringUtil.dbSafeString(product.getNormName(), false, 255));
        product.setMasterImg(StringUtil.dbSafeString(product.getMasterImg(), false, 255));

    }

    private void dbConstrainsProductGood(ProductGoods productGoods) {
        productGoods
            .setNormAttrId(StringUtil.dbSafeString(productGoods.getNormAttrId(), false, 255));
        productGoods.setNormName(StringUtil.dbSafeString(productGoods.getNormName(), false, 255));
        productGoods.setSku(StringUtil.dbSafeString(productGoods.getSku(), false, 50));
        productGoods.setImages(StringUtil.dbSafeString(productGoods.getImages(), false, 255));
    }

    /**
     * 查询最大的商品
     * 
     * @return
     */
    public Product getProductByMax() {
        return productReadDao.getProductByMax();
    }

    //    public Integer updateProduct(Product pro) {
    //        return productWriteDao.update(pro);
    //    }

    /**
     * 根据商家ID和SPU判断是否已有商品存在
     * @param sellerId
     * @param productCode
     * @param productId 商品ID，新增时传0，修改时传实际ID
     * @return
     */
    public Boolean isUnique(Integer sellerId, String productCode, Integer productId) {
        productId = productId == null ? 0 : productId;
        Integer count = productWriteDao.getByIdAndSellerIdAndSpu(sellerId, productCode, productId);
        return count == 0;
    }
    public List<Product> productsByRole(Map<String, String> queryMap,PagerInfo pager) throws Exception {
        List<Product> list = new ArrayList<Product>();
       try {
            Integer start = 0, size = 0;
            String state = queryMap.get("q_state");
            if (!StringUtil.isEmpty(state) && state.indexOf(",") != -1) {
                if (pager != null) {
                    pager.setRowsCount(productWriteDao.count1(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productWriteDao.page1(queryMap, start, size);
            } else {
                if (pager != null) {
                    pager.setRowsCount(productWriteDao.count(queryMap));
                    start = pager.getStart();
                    size = pager.getPageSize();
                }
                list = productWriteDao.page(queryMap, start, size);
            }

           for (Product pro : list) {
                ProductCate pcate = productCateWriteDao.get(pro.getProductCateId());
                pro.setProductCateName(pcate == null ? null : pcate.getName());
                pro.setProductBrandName(
                    productBrandWriteDao.getById(pro.getProductBrandId()).getName());
                SellerCate cate = sellerCateWriteDao.get(pro.getSellerCateId());
                pro.setSellerCateName(cate == null ? null : cate.getName());

                Seller seller = sellerReadDao.get(pro.getSellerId());
                if (null != seller && !StringUtil.isEmpty(seller.getSellerName())) {
                    pro.setSeller(seller.getSellerName());
                }
            }

       } catch (Exception e) {
            throw new Exception(e);
        }
        return list;
      
    }
    
    
    /**
     * 同步京东商品表
     * @return
     */
    public int syJdProduct() throws Exception{
    	TransactionStatus status = null;
    	DefaultTransactionDefinition def = null;
    	try{
    		//首先已经同步的商品有没有在京东中取消授权的
    		List<Product> cancelPros = productReadDao.getCancelJdProduct();
    		//对取消授权的JD商品进行改为商品删除状态
    		def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
            status = transactionManager.getTransaction(def);
    		for (int i = 0; i < cancelPros.size(); i++) {
				productWriteDao.updateState(cancelPros.get(i).getId(), Product.STATE_5);
			}
    		transactionManager.commit(status);
    		//然后分页获取 京东商品数据 对平台已有的JD商品数据进行修改 如果没有则新增
    		int start = 0;
    		int size = 100;
    		while(true){
    			List<JdProduct> jdProducts = jdProductReadDao.getJdProductList(start, size);
    			if(jdProducts.size()<1){
    				break;
    			}
    			def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
                status = transactionManager.getTransaction(def);
    			for (int i = 0; i < jdProducts.size(); i++) {
    				Product product = new Product();
					JdProduct jdProduct = new JdProduct();
					jdProduct= jdProducts.get(i);
					ProductCate productCate = productCateReadDao.getCateByjdCatId(jdProduct.getCatId());
					if(productCate == null || productCate.getId() <1){
						continue;
					}
					product.setProductCateId(productCate.getId());
					product.setProductCatePath(productCate.getPath());
					product.setName1(jdProduct.getName() == null? "":jdProduct.getName());
					product.setName2("");
					product.setKeyword("");
					product.setProductBrandId(productBrandReadDao.getProductBrandByName("京东").getId());
					product.setIsSelf(Product.IS_SELF_1);
					product.setCostPrice(jdProduct.getPrice());
					product.setProtectedPrice(jdProduct.getPrice());
					product.setMarketPrice(jdProduct.getJdprice());
					
					//计算售卖价格
					if(jdProduct.getPrice() == null || jdProduct.getJdprice() ==null){
						continue;
					}
					BigDecimal price = jdProduct.getPrice();
					BigDecimal jdPrice = jdProduct.getJdprice();
					product.setMallPcPrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
					product.setMalMobilePrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
					product.setVirtualSales(0);
					product.setActualSales(0);
					product.setProductStock(0);
					product.setIsNorm(Product.IS_NORM_1);
					product.setNormIds("");
					product.setNormName("");
					product.setState(Product.STATE_6);
					product.setIsTop(Product.IS_TOP_1);
					product.setUpTime(new Date());
					product.setJdparam(jdProduct.getParam()== null ? "":jdProduct.getParam());
					product.setDescription(jdProduct.getIntroduction() == null ? "":jdProduct.getIntroduction());
					product.setPacking("");
					product.setSellerId(sellerReadDao.getSellerByName("京东").getId());
					product.setCreateId(1);
					product.setCreateTime(new Date());
					product.setUpdateTime(new Date());
					product.setSellerCateId(jdProduct.getCatId());
					product.setSellerIsTop(1);
					product.setSellerState(Product.SELLER_STATE_1);
					product.setCommentsNumber(0);
					product.setProductCateState(Product.PRODUCT_CATE_STATE_1);
					product.setIsInventedProduct(1);
					product.setMasterImg(jdProduct.getImagePath() == null ?"":jdProduct.getImagePath());
					product.setProductCode(jdProduct.getSku());
					product.setSupplierId(0);
					product.setSource(Product.SOURCE_2);
					product.setIsWelfareProduct(1);
					
					//计算数据库中是否已经有同步过此JD商品数据如果有则修改
					Product product2 = productReadDao.getProductByCode(product.getProductCode());
					if(product2 != null && product2.getId() !=0){
						//则修改
						product.setId(product2.getId());
						productWriteDao.update(product);
						
						//更改货品表中的价格 图片
						ProductGoods productGoods = new ProductGoods();
						productGoods.setMallMobilePrice(product.getMalMobilePrice());
						productGoods.setMallPcPrice(product.getMallPcPrice());
						productGoods.setImages(jdProduct.getImagePath() == null ?"":jdProduct.getImagePath());
						productGoods.setId(productGoodsWriteDao.getByProductId(product.getId()).getId());
						
						productGoodsWriteDao.update(productGoods);
						
					}else{
						//如果没有 则新增此商品数据
						productWriteDao.insert(product);
						ProductGoods productGoods = new ProductGoods();
						productGoods.setProductId(product.getId());
						productGoods.setNormAttrId("");
						productGoods.setNormName("");
						productGoods.setMallMobilePrice(product.getMallPcPrice());
						productGoods.setMallPcPrice(product.getMallPcPrice());
						productGoods.setProductStock(0);
						productGoods.setProductStockWarning(-1);
						productGoods.setActualSales(0);
						productGoods.setSku(product.getProductCode());
						productGoods.setImages(jdProduct.getImagePath() == null ?"":jdProduct.getImagePath());
						productGoods.setState(1);
						productGoodsWriteDao.insert(productGoods);
					}
				}
    			start+=size;
    			transactionManager.commit(status);
    		}
    	}catch(Exception e){
    		e.printStackTrace();
    		transactionManager.rollback(status);
    		throw new Exception(e);
    	}
    	return 1;
    }
    
    
    /**
     * 处理京东推送过来的商品相关消息
     * @param token 访问京东的令牌
     * @param messageList 消息集合
     * @return 处理是否成功
     */
    public Boolean handleJDProductMessage(AccessToken token ,Map<Integer,List<JDMessage>> messageList) throws Exception{
    	
    	if(messageList == null || messageList.size() == 0){
    		//未收到消息，默认处理成功
    		return true ;
    	}
    	
    	//处理商品池内商品新增或者删除
    	this.doJDProductUpdate(token,messageList.get(JDMessageType.PRODUCT_UPDATE));
    	
    	//处理商品上下架
    	this.doJDProductState(token,messageList.get(JDMessageType.PRODUCT_STATE));
    	
		//处理商品价格变动
    	this.doJDProductPrice(token,messageList.get(JDMessageType.PRODUCT_PRICE));
    	
    	//处理商品介绍及规格参数变动
    	this.doJDProductParam(token,messageList.get(JDMessageType.PRODUCT_PARAM));
    	
    	return true ;
    }
    
    /**
     * 处理商品价格变动消息
     * @param token 访问京东的令牌
     * @param priceList
     * @throws Exception
     */
    private void doJDProductPrice(AccessToken token,List<JDMessage> priceList) throws Exception{
    	if(priceList!=null && priceList.size() > 0){
    		TransactionStatus status = null;
        	DefaultTransactionDefinition def = null;
    		try {
    			//京东接口限制最高支持100个商品编码的拼接，故每一100条消息处理一次
    			int pageSize = 100 ;
    			int page = (priceList.size() / pageSize ) + 1 ;
    			for(int i = 0 ; i < page ; i ++){
    				//每次需要处理的消息
    				List<JDMessage> doList = priceList.subList(i*pageSize, (i + 1 ) *pageSize  > priceList.size() ? priceList.size()  : (i + 1 ) *pageSize);
    				//拼接SKU编码
    				StringBuffer skuBuffer = new StringBuffer();
    				for(int k = 0 ; k < doList.size() ; k ++){
    					skuBuffer.append(doList.get(k).getResult().get("skuId")).append(",");
    				}
    				if(skuBuffer.length() > 0 ){
    					skuBuffer.delete(skuBuffer.length()-1, skuBuffer.length());
    				}
    				//获取本次SKU的京东商品价格
    				JDApiResult<List<Map>> jdPriceResult = JdApi.getPrice(token.getAccess_token(), skuBuffer.toString());
    				if(jdPriceResult.isSuccess() && jdPriceResult.getResult()!=null && jdPriceResult.getResult().size() > 0){
    					//定义事务
    	    			def = new DefaultTransactionDefinition();;
    	    			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    	    			status = transactionManager.getTransaction(def);
    	    			
    	    			for(Map map : jdPriceResult.getResult()){
    	    				String skuId = map.get("skuId") == null ? null : String.valueOf(map.get("skuId"));
    	    				BigDecimal price = map.get("price") == null ? BigDecimal.ZERO : BigDecimal.valueOf(Double.valueOf(map.get("price").toString()));
    	    				BigDecimal jdPrice = map.get("jdPrice") == null ? BigDecimal.ZERO : BigDecimal.valueOf(Double.valueOf(map.get("jdPrice").toString()));;
    	    				if(StringUtil.isEmpty(skuId)){
    	    					continue ;
    	    				}
    	    				Product product = new Product();
    	    				product.setSku(skuId);
    	    				product.setCostPrice(price);
    						product.setProtectedPrice(price);
    						product.setMarketPrice(jdPrice);
    						if(price == null || jdPrice ==null){
    							continue;
    						}
    						JdProduct jdProduct = jdProductReadDao.getJdProductBySku(skuId);
    						if(jdProduct == null ){
    							continue ;
    						}
    						ProductCate productCate = productCateReadDao.getCateByjdCatId(jdProduct.getCatId());
    						if(productCate == null ){
    							continue ;
    						}
    						if(productCate.getServiceRate() == null ) {
    							productCate.setServiceRate(BigDecimal.ZERO);
    						}
    						product.setMallPcPrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
    						product.setMalMobilePrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
    						
    						//更新京东商品表价格信息
    						productWriteDao.updatePriceBySku(product);
    	    			}
    	    			
    	    			//提交事务
    	    			transactionManager.commit(status);
    				}
    			}
			} catch (Exception e) {
				if(status != null){
					//如有异常，回滚事务
					transactionManager.rollback(status);
				}
				//异常抛给业务方处理
				throw e ;
			}
    	}
    }
    
    /**
     * 处理商品上下架消息
     * @param token 访问京东的令牌
     * @param stateList
     * @throws Exception
     */
    private void doJDProductState(AccessToken token,List<JDMessage> stateList) throws Exception{
    	if(stateList!=null && stateList.size() > 0){
    		TransactionStatus status = null;
        	DefaultTransactionDefinition def = null;
    		try {
    			//京东接口限制最高支持100个商品编码的拼接，故每一100条消息处理一次
    			int pageSize = 100 ;
    			int page = (stateList.size() / pageSize ) + 1 ;
    			for(int i = 0 ; i < page ; i ++){
    				//每次需要处理的消息
    				List<JDMessage> doList = stateList.subList(i*pageSize, (i + 1 ) *pageSize  > stateList.size() ? stateList.size()  : (i + 1 ) *pageSize);
    				//拼接SKU编码
    				StringBuffer skuBuffer = new StringBuffer();
    				for(int k = 0 ; k < doList.size() ; k ++){
    					skuBuffer.append(doList.get(k).getResult().get("skuId")).append(",");
    				}
    				if(skuBuffer.length() > 0 ){
    					skuBuffer.delete(skuBuffer.length()-1, skuBuffer.length());
    				}
    				//获取本次SKU的京东上下架状态信息
    				JDApiResult<List<Map>> jdStateResult = JdApi.getSkuState(token.getAccess_token(), skuBuffer.toString());
    				if(jdStateResult.isSuccess() && jdStateResult.getResult()!=null && jdStateResult.getResult().size() > 0){
    					//定义事务
    	    			def = new DefaultTransactionDefinition();;
    	    			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    	    			status = transactionManager.getTransaction(def);
    	    			for(Map map : jdStateResult.getResult()){
    	    				String sku = map.get("sku") == null ? null : String.valueOf(map.get("sku"));
    	    				Integer state = map.get("state") == null ? 0 : Integer.valueOf(map.get("state").toString());
    	    				if(StringUtil.isEmpty(sku)){
    	    					continue ;
    	    				}
    	    				Product product = new Product();
    	    				product.setSku(sku);
    	    				product.setState(state.intValue() == 1 ? Product.STATE_6 : Product.STATE_7);//6、上架；7、下架
    	    				//更新京东商品表上下架信息
    						productWriteDao.updateStateBySku(product);
    	    			}
    	    			//提交事务
    	    			transactionManager.commit(status);
    				}
    			}
			} catch (Exception e) {
				if(status != null){
					//如有异常，回滚事务
					transactionManager.rollback(status);
				}
				//异常抛给业务方处理
				throw e ;
			}
    	}
    }
    
    /**
     * 处理商品池内商品新增或者删除消息
     * @param token 访问京东的令牌
     * @param updateList
     * @throws Exception
     */
    private void doJDProductUpdate(AccessToken token ,List<JDMessage> updateList) throws Exception{
    	if(updateList!=null && updateList.size() > 0){
    		TransactionStatus status = null;
        	DefaultTransactionDefinition def = null;
        	try {
        		//定义事务
    			def = new DefaultTransactionDefinition();;
    			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    			status = transactionManager.getTransaction(def);
    			
        		StringBuffer delBuffer = new StringBuffer();
    			for(int i = 0 ; i < updateList.size() ; i ++){
    				String skuId = updateList.get(i).getResult().getString("skuId");
    				String state = updateList.get(i).getResult().getString("state");
    				if(StringUtil.isEmpty(skuId)){//如果取到的商品编码为空，则跳过
    					continue ;
    				}
    				if(!StringUtil.isEmpty(state) && Integer.valueOf(state).intValue() == 2){//"state":"1添加，2删除"
    					delBuffer.append(skuId).append(",");
    				}else if (!StringUtil.isEmpty(state) && Integer.valueOf(state).intValue() == 1){//新添加的商品
    					//调用京东接口，获取商品详情
    					JDApiResult<JdProductDto> jdProductResult = JdApi.getDetailObjBySku(token.getAccess_token(), skuId, false);
    					if(jdProductResult.isSuccess() && jdProductResult.getResult()!=null){
	    					Product product = new Product();
	    					JdProductDto jdProductDto = jdProductResult.getResult();
	    					ProductCate productCate = productCateReadDao.getCateByjdCatId(jdProductDto.getCatId());
	    					if(productCate == null || productCate.getId() <1){
	    						continue;
	    					}
	    					product.setProductCateId(productCate.getId());
	    					product.setProductCatePath(productCate.getPath());
	    					product.setName1(jdProductDto.getName() == null? "":jdProductDto.getName());
	    					product.setName2("无");
	    					product.setKeyword("无");
	    					product.setProductBrandId(productBrandReadDao.getProductBrandByName("京东").getId());
	    					product.setIsSelf(Product.IS_SELF_1);
	    					product.setCostPrice(jdProductDto.getPrice());
	    					product.setProtectedPrice(jdProductDto.getPrice());
	    					product.setMarketPrice(jdProductDto.getJdprice());
	    					
	    					//计算售卖价格
	    					if(jdProductDto.getPrice() == null || jdProductDto.getJdprice() ==null){
	    						continue;
	    					}
	    					BigDecimal price = jdProductDto.getPrice();
	    					BigDecimal jdPrice = jdProductDto.getJdprice();
	    					product.setMallPcPrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
	    					product.setMalMobilePrice((jdPrice.subtract(price)).multiply(productCate.getServiceRate()).add(price));
	    					product.setVirtualSales(0);
	    					product.setActualSales(0);
	    					product.setProductStock(0);
	    					product.setIsNorm(Product.IS_NORM_1);
	    					product.setNormIds("");
	    					product.setNormName("");
	    					product.setState(Product.STATE_6);
	    					product.setIsTop(Product.IS_TOP_1);
	    					product.setUpTime(new Date());
	    					product.setJdparam(jdProductDto.getParam()== null ? "":jdProductDto.getParam());
	    					product.setDescription(jdProductDto.getIntroduction() == null ? "":jdProductDto.getIntroduction());
	    					product.setPacking("无");
	    					product.setSellerId(sellerReadDao.getSellerByName("京东").getId());
	    					product.setCreateId(1);
	    					product.setCreateTime(new Date());
	    					product.setUpdateTime(new Date());
	    					product.setSellerCateId(jdProductDto.getCatId());
	    					product.setSellerIsTop(1);
	    					product.setSellerState(Product.SELLER_STATE_1);
	    					product.setCommentsNumber(0);
	    					product.setProductCateState(Product.PRODUCT_CATE_STATE_1);
	    					product.setIsInventedProduct(1);
	    					product.setMasterImg(jdProductDto.getImagePath() == null ?"":jdProductDto.getImagePath());
	    					product.setProductCode(jdProductDto.getSku());
	    					product.setSupplierId(0);
	    					product.setSource(Product.SOURCE_2);
	    					product.setIsWelfareProduct(1);
	    					
	    					//如果没有 则新增此商品数据
							productWriteDao.insert(product);
							ProductGoods productGoods = new ProductGoods();
							productGoods.setProductId(product.getId());
							productGoods.setNormAttrId("");
							productGoods.setNormName("");
							productGoods.setMallMobilePrice(product.getMallPcPrice());
							productGoods.setMallPcPrice(product.getMallPcPrice());
							productGoods.setProductStock(0);
							productGoods.setProductStockWarning(-1);
							productGoods.setActualSales(0);
							productGoods.setSku(product.getProductCode());
							productGoods.setState(1);
							productGoods.setImages(jdProductDto.getImagePath() == null ?"":jdProductDto.getImagePath());
							productGoodsWriteDao.insert(productGoods);
    					}
    				}
    			}
    			//删除商品，逻辑删除
    			if(delBuffer.length() > 0 ){
    				delBuffer.delete(delBuffer.length() - 1, delBuffer.length());
    				productWriteDao.updateBatchDeleteBySkuIds(delBuffer.toString());
    			}
    			//提交事务
    			transactionManager.commit(status);
			} catch (Exception e) {
				if(status != null){
					//如有异常，回滚事务
					transactionManager.rollback(status);
				}
				//异常抛给业务方处理
				throw e ;
			}
    	}
    }
    
    /**
     * 处理商品介绍及规格参数变动消息
     * @param token 访问京东的令牌
     * @param paramList
     * @throws Exception
     */
    private void doJDProductParam(AccessToken token ,List<JDMessage> paramList) throws Exception{
    	if(paramList!=null && paramList.size() > 0){
    		TransactionStatus status = null;
        	DefaultTransactionDefinition def = null;
        	try {
        		//定义事务
    			def = new DefaultTransactionDefinition();;
    			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
    			status = transactionManager.getTransaction(def);
    			
    			for(int i = 0 ; i < paramList.size() ; i ++){
    				String skuId = paramList.get(i).getResult().getString("skuId");
    				if(StringUtil.isEmpty(skuId)){//如果取到的商品编码为空，则跳过
    					continue ;
    				}
    				//调用京东接口，获取商品详情
					JDApiResult<JdProductDto> jdProductResult = JdApi.getDetailObjBySku(token.getAccess_token(), skuId, false);
					if(jdProductResult.isSuccess() && jdProductResult.getResult()!=null){
						JdProductDto jdProductDto = jdProductResult.getResult();
						Product product = new Product() ;
						product.setSku(skuId);
						product.setProductCode(skuId);
						product.setDescription(jdProductDto.getIntroduction() != null?HtmlUtils.htmlUnescape(jdProductDto.getIntroduction()):null);
						product.setJdparam(jdProductDto.getParam()!= null? HtmlUtils.htmlUnescape(jdProductDto.getParam()):null);
						//更新商品参数急描述
						productWriteDao.updateParamInfoBySkuId(product);
					}
    			}
    					
    			//提交事务
        		transactionManager.commit(status);
			} catch (Exception e) {
				if(status != null){
					//如有异常，回滚事务
					transactionManager.rollback(status);
				}
				//异常抛给业务方处理
				throw e ;
			}
    	}
    }
    
}
