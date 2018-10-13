package com.sln.entity.product;

import java.io.Serializable;
import java.util.List;

/**
* 货品表
* <p>Table: <strong>product_goods</strong>
    * <p>
<table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
    *
    <tr style="background-color:#ddd;Text-align:Left;">
        *
        <th nowrap>属性名</th>
        <th nowrap>属性类型</th>
        <th nowrap>字段名</th>
        <th nowrap>字段类型</th>
        <th nowrap>说明</th>
        *
    </tr>
    *
    <tr>
        <td>id</td>
        <td>{@link Integer}</td>
        <td>id</td>
        <td>int</td>
        <td>id</td>
    </tr>
    *
    <tr>
        <td>productId</td>
        <td>{@link Integer}</td>
        <td>product_id</td>
        <td>int</td>
        <td>商品ID</td>
    </tr>
	*   <tr><td>normAttrId</td><td>{@link String}</td><td>norm_attr_id</td><td>varchar</td><td>规格属性值ID，用逗号分隔</td></tr>
    *
    <tr>
        <td>normName</td>
        <td>{@link String}</td>
        <td>norm_name</td>
        <td>varchar</td>
        <td>规格值，用逗号分隔</td>
    </tr>
    *
    <tr>
        <td>mallPcPrice</td>
        <td>{@link java.math.BigDecimal}</td>
        <td>mall_pc_price</td>
        <td>decimal</td>
        <td>PC价格</td>
    </tr>
    *
    <tr>
        <td>mallMobilePrice</td>
        <td>{@link java.math.BigDecimal}</td>
        <td>mall_mobile_price</td>
        <td>decimal</td>
        <td>Mobile价格</td>
    </tr>
    *
    <tr>
        <td>productStock</td>
        <td>{@link Integer}</td>
        <td>product_stock</td>
        <td>int</td>
        <td>库存</td>
    </tr>
    *
    <tr>
        <td>productStockWarning</td>
        <td>{@link Integer}</td>
        <td>product_stock_warning</td>
        <td>int</td>
        <td>库存预警值</td>
    </tr>
    *
    <tr>
        <td>actualSales</td>
        <td>{@link Integer}</td>
        <td>actual_sales</td>
        <td>int</td>
        <td>所有规格销量相加等于商品总销量</td>
    </tr>
    *
    <tr>
        <td>sku</td>
        <td>{@link String}</td>
        <td>sku</td>
        <td>varchar</td>
        <td>sku</td>
    </tr>
    *
    <tr>
        <td>images</td>
        <td>{@link String}</td>
        <td>images</td>
        <td>varchar</td>
        <td>规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）</td>
    </tr>
    *
    <tr>
        <td>images</td>
        <td>{@link Integer}</td>
        <td>oldStock</td>
        <td>int</td>
        <td>上次库存</td>
    </tr>
    *
    <tr>
        <td>oldStockWarning</td>
        <td>{@link Integer}</td>
        <td>old_stock_warning</td>
        <td>int</td>
        <td>上次预警库存</td>
    </tr>
    *
    <tr>
        <td>updateUserName</td>
        <td>{@link String}</td>
        <td>update_user_name</td>
        <td>varchar</td>
        <td>修改人名称</td>
    </tr>
    *
</table>
*
*/
public class ProductGoods implements Serializable {

    /**
    *Comment for <code>serialVersionUID</code>
    */
    private static final long    serialVersionUID = -264584636750741280L;

    private Integer              id;

    private Integer              productId;                              //商品ID

    private String               normAttrId;                             //规格属性值ID，用逗号分隔

    private String               normName;                               //规格值，用逗号分隔

    private java.math.BigDecimal mallPcPrice;                            //PC价格

    private java.math.BigDecimal mallMobilePrice;                        //Mobile价格

    private Integer              productStock;                           //库存

    private Integer              productStockWarning;                    //库存预警值

    private Integer              actualSales;                            //所有规格销量相加等于商品总销量

    private String               sku;

    private String               images;                                 //规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）

    private Integer              state;                                  //是否启用 1-启用 0-不启用

    private java.math.BigDecimal weight;                                 //重量kg

    private Integer              length;                                 //长度cm

    private Integer              width;                                  //宽度cm

    private Integer              height;                                 //高度cm


    public static final int      ENABLE           = 1;
    public static final int      DISABLE          = 0;
    
    
    /** 非数据库字段 */
    private String 				productName;							 //商品名称
    
    private String				sellerName;								 //商家名称
    
    private String              mobileImg;                              //移动端地址显示
    
    private String				updateSellerName;						 //修改商家名称
    
    private String 				html;									 //
    private String[]       		productLeadPicList;                      //货品图片集合

    /**
    * 获取id
    */
    public Integer getId() {
        return this.id;
    }

    /**
    * 设置id
    */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
    * 获取商品ID
    */
    public Integer getProductId() {
        return this.productId;
    }

    /**
    * 设置商品ID
    */
    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    /**
     * 获取规格属性值ID，用逗号分隔
     */
    public String getNormAttrId() {
        return this.normAttrId;
    }

    /**
     * 设置规格属性值ID，用逗号分隔
     */
    public void setNormAttrId(String normAttrId) {
        this.normAttrId = normAttrId;
    }

    /**
     * 获取规格值，用逗号分隔
     */
    public String getNormName() {
        return this.normName;
    }

    /**
    * 设置规格值，用逗号分隔
    */
    public void setNormName(String normName) {
        this.normName = normName;
    }

    /**
    * 获取PC价格
    */
    public java.math.BigDecimal getMallPcPrice() {
        return this.mallPcPrice;
    }

    /**
    * 设置PC价格
    */
    public void setMallPcPrice(java.math.BigDecimal mallPcPrice) {
        this.mallPcPrice = mallPcPrice;
    }

    /**
    * 获取Mobile价格
    */
    public java.math.BigDecimal getMallMobilePrice() {
        return this.mallMobilePrice;
    }

    /**
    * 设置Mobile价格
    */
    public void setMallMobilePrice(java.math.BigDecimal mallMobilePrice) {
        this.mallMobilePrice = mallMobilePrice;
    }

    /**
    * 获取库存
    */
    public Integer getProductStock() {
        return this.productStock;
    }

    /**
    * 设置库存
    */
    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    /**
    * 获取库存预警值
    */
    public Integer getProductStockWarning() {
        return this.productStockWarning;
    }

    /**
    * 设置库存预警值
    */
    public void setProductStockWarning(Integer productStockWarning) {
        this.productStockWarning = productStockWarning;
    }

    /**
    * 获取所有规格销量相加等于商品总销量
    */
    public Integer getActualSales() {
        return this.actualSales;
    }

    /**
    * 设置所有规格销量相加等于商品总销量
    */
    public void setActualSales(Integer actualSales) {
        this.actualSales = actualSales;
    }

    /**
    * 获取sku
    */
    public String getSku() {
        return this.sku;
    }

    /**
    * 设置sku
    */
    public void setSku(String sku) {
        this.sku = sku;
    }

    /**
    * 获取规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）
    */
    public String getImages() {
        return this.images;
    }

    /**
    * 设置规格图片（规格图片，用逗号隔开，和规格值对应，如果没有图片，那为空）
    */
    public void setImages(String images) {
        this.images = images;
    }

    @Override
    public int hashCode() {
        return (id == null) ? 0 : id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        ProductGoods other = (ProductGoods) obj;
        if (id == null || other.getId() == null) {
            return false;
        }
        return id.intValue() == other.getId().intValue();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public java.math.BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(java.math.BigDecimal weight) {
        this.weight = weight;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getSellerName() {
		return sellerName;
	}

	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}

	public String getUpdateSellerName() {
		return updateSellerName;
	}

	public void setUpdateSellerName(String updateSellerName) {
		this.updateSellerName = updateSellerName;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String[] getProductLeadPicList() {
		return productLeadPicList;
	}

	public void setProductLeadPicList(String[] productLeadPicList) {
		this.productLeadPicList = productLeadPicList;
	}

	public String getMobileImg() {
		return mobileImg;
	}

	public void setMobileImg(String mobileImg) {
		this.mobileImg = mobileImg;
	}
	
	
}