package com.sln.model.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.sln.core.jd.JdApi;
import com.sln.core.jd.util.JDApiResult;
import com.sln.core.jd.util.JDConfig;
import com.sln.dao.shop.read.product.ProductPictureReadDao;
import com.sln.entity.product.ProductPicture;

@Component(value = "frontProductPictureModel")
public class FrontProductPictureModel {

    @Resource
    private ProductPictureReadDao productPictureReadDao;

    /**
    * 根据id取得商品对应图片表
    * @param  productPictureId
    * @return
    */
    public ProductPicture getProductPictureById(Integer productPictureId) {
        return productPictureReadDao.get(productPictureId);
    }

    /**
     * 根据id取得商品对应图片表
     * @param  productId
     * @return
     */
    public List<ProductPicture> getByProductIds(Integer productId) {
        return productPictureReadDao.getByProductId(productId);
    }

    /**
     * 根据商品ID获取商品的主图
     * @param productId
     * @return
     */
    public ProductPicture getproductLead(Integer productId) {
        return productPictureReadDao.getproductLead(productId);
    }
    
    /**
     * 根据SKU获取所有图片信息
     */
    public List<String> getSkuImage(String token,String sku) throws Exception{
        JDApiResult<List<Map>> res = JdApi.getSkuImage(token, sku);
        List<String> list = new ArrayList<String>();
        if(res.isSuccess()){
            //[{"id":18553616,"isPrimary":1,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t2374/3/2524235412/202057/1a8027a1/56e1448dNd05fa92d.jpg","yn":1,"type":0,"modified":"2017-09-30 10:34:26"},{"id":18553617,"isPrimary":0,"orderSort":1,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t2416/332/2540124476/193425/5cc72633/56e1447fN98940af0.jpg","yn":1,"type":0,"modified":"2016-03-10 18:03:05"},{"id":18553618,"isPrimary":0,"orderSort":2,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t2299/238/1899173220/227108/a57d0b63/56e14495Na4cabb41.jpg","yn":1,"type":0,"modified":"2016-03-10 18:03:05"},{"id":18553619,"isPrimary":0,"orderSort":3,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t1921/248/2580005205/187792/caf91801/56e1449bN05dcd65e.jpg","yn":1,"type":0,"modified":"2016-03-10 18:03:05"},{"id":18553620,"isPrimary":0,"orderSort":4,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t2038/16/2529896846/143954/ab0d6a9d/56e144a0N086fbd1d.jpg","yn":1,"type":0,"modified":"2016-03-10 18:03:05"},{"id":18553621,"isPrimary":0,"orderSort":5,"created":"2016-03-10 18:03:05","skuId":1842804,"path":"jfs/t2260/247/2590264279/280743/5d90e448/56e144a5N3e2f001c.jpg","yn":1,"type":0,"modified":"2016-03-10 18:03:05"}]
            JSONArray array = JSON.parseArray(res.getResult().get(0).get(sku).toString());
            JSONObject jo = null;
            for (int i = 0; i < array.size(); i++) {
                jo = JSONObject.fromObject(array.get(i));
                list.add(JDConfig.IMAGE_PATH_350 + jo.get("path"));
            }
        }
        return list;
    }
}
