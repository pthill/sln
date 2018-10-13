package com.sln.model.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sln.core.exception.BusinessException;
import com.sln.dao.shop.read.order.OrdersProductReadDao;
import com.sln.dao.shop.read.product.ProductGoodsReadDao;
import com.sln.dao.shop.read.product.ProductReadDao;
import com.sln.dao.shop.read.seller.SellerReadDao;
import com.sln.dao.shop.write.order.OrdersProductWriteDao;
import com.sln.dto.ProductDayDto;
import com.sln.entity.order.OrdersProduct;
import com.sln.entity.product.Product;
import com.sln.entity.seller.Seller;
import com.sln.util.FrontProductPictureUtil;

@Component(value = "ordersProductModel")
public class OrdersProductModel {

    @Resource
    private OrdersProductWriteDao   ordersProductWriteDao;
    @Resource
    private OrdersProductReadDao    ordersProductReadDao;
    @Resource
    private SellerReadDao           sellerReadDao;
    @Resource
    private ProductGoodsReadDao     productGoodsReadDao;
    @Resource
    private ProductReadDao          produceReadDao;
    
    /**
     * 产品图片获取工具类
     */
    @Resource
    private FrontProductPictureUtil frontProductPictureUtil;

    /**
    * 根据id取得网单表
    * @param  ordersProductId
    * @return
    */
    public OrdersProduct getOrdersProductById(Integer ordersProductId) {
        return ordersProductWriteDao.get(ordersProductId);
    }

    /**
     * 保存网单表
     * @param  ordersProduct
     * @return
     */
    public Integer saveOrdersProduct(OrdersProduct ordersProduct) {
        return ordersProductWriteDao.insert(ordersProduct);
    }

    /**
    * 更新网单表
    * @param  ordersProduct
    * @return
    */
    public Integer updateOrdersProduct(OrdersProduct ordersProduct) {
        return ordersProductWriteDao.update(ordersProduct);
    }

    public boolean del(Integer id) {
        if (id == null)
            throw new BusinessException("删除网单表[" + id + "]时出错");
        return ordersProductWriteDao.del(id) > 0;
    }

    /**
     * 根据订单号获取网单
     * @param orderId
     * @return
     */
    public List<OrdersProduct> getOrdersProductByOId(Integer orderId) {
        return ordersProductReadDao.getByOrderId(orderId);
    }

    /**
     * 根据id 取得网单信息
     * @param request
     * @return
     */
    public OrdersProduct getOrdersProductWithImgById(Integer orderProductId) {
        if (orderProductId == null || orderProductId == 0) {
            throw new BusinessException("网单id为空，请重试！");
        }

        OrdersProduct productvo = ordersProductReadDao.get(orderProductId);
        if (productvo != null) {
            String productLeadLittle = frontProductPictureUtil
                .getproductLeadLittle(productvo.getProductId());
           String image =  productGoodsReadDao.get(productvo.getProductGoodsId()).getImages();
            if(image != null && !"".equals(image)) {
            	productLeadLittle = image;
            }
            Product product = produceReadDao.get(productvo.getProductId());
            productvo.setProductCode(product.getProductCode());
            productvo.setSource(product.getSource());
            productvo.setProductLeadLittle(productLeadLittle);
        }
        return productvo;
    }

    /**
     * 统计商品每日销量
     * @param queryMap
     * @return
     */
    public List<ProductDayDto> getProductDayDto(Map<String, String> queryMap) {
        List<ProductDayDto> list = ordersProductReadDao.getProductDayDto(queryMap);
        if (list != null && list.size() > 0) {
            Map<Integer, Seller> map = new HashMap<Integer, Seller>();
            for (ProductDayDto productDayDto : list) {
                Seller seller = map.get(productDayDto.getSellerId());
                if (seller == null) {
                    seller = sellerReadDao.get(productDayDto.getSellerId());
                    map.put(productDayDto.getSellerId(), seller);
                }
                if (seller != null) {
                    productDayDto.setSellerName(seller.getSellerName());
                }
            }
        }
        return list;
    }
    
    /**
     *根据订单编号，供应商ID获取网单数据 
     */
    public List<OrdersProduct> getByOrderSn(String orderSn,Integer supplierId){
    		return ordersProductReadDao.getByOrderSn(orderSn, supplierId);
    }
    
    /**
     * 根据orderId 获取网单信息包括图片
     */
    
    public List<OrdersProduct> getOrdersProductListWithImgById(Integer ordersId) {
        if (ordersId == null || ordersId == 0) {
            throw new BusinessException("订单id为空，请重试！");
        }

        List<OrdersProduct> list = ordersProductReadDao.getByOrderId(ordersId);
        for (int i = 0; i < list.size(); i++) {
        	String productLeadLittle = frontProductPictureUtil
                    .getproductLeadLittle(list.get(i).getProductId());
        	list.get(i).setProductLeadLittle(productLeadLittle);
		}
        return list;
    }
    
    
}
