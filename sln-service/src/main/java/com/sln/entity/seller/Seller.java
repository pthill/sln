package com.sln.entity.seller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家表
 * <p>Table: <strong>seller</strong>
 * <p><table class="er-mapping" cellspacing=0 cellpadding=0 style="border:solid 1 #666;padding:3px;">
 *   <tr style="background-color:#ddd;Text-align:Left;">
 *     <th nowrap>属性名</th><th nowrap>属性类型</th><th nowrap>字段名</th><th nowrap>字段类型</th><th nowrap>说明</th>
 *   </tr>
 *   <tr><td>id</td><td>{@link java.lang.Integer}</td><td>id</td><td>int</td><td>id</td></tr>
 *   <tr><td>memberId</td><td>{@link java.lang.Integer}</td><td>member_id</td><td>int</td><td>用户ID</td></tr>
 *   <tr><td>name</td><td>{@link java.lang.String}</td><td>name</td><td>varchar</td><td>用户名</td></tr>
 *   <tr><td>sellerName</td><td>{@link java.lang.String}</td><td>seller_name</td><td>varchar</td><td>店铺名称</td></tr>
 *   <tr><td>sellerLogo</td><td>{@link java.lang.String}</td><td>seller_logo</td><td>varchar</td><td>店铺logo</td></tr>
 *   <tr><td>sellerGrade</td><td>{@link java.lang.Integer}</td><td>seller_grade</td><td>int</td><td>店铺等级</td></tr>
 *   <tr><td>scoreService</td><td>{@link java.lang.String}</td><td>score_service</td><td>varchar</td><td>店铺评分服务</td></tr>
 *   <tr><td>scoreDeliverGoods</td><td>{@link java.lang.String}</td><td>score_deliver_goods</td><td>varchar</td><td>店铺评分发货</td></tr>
 *   <tr><td>scoreDescription</td><td>{@link java.lang.String}</td><td>score_description</td><td>varchar</td><td>店铺评分描述</td></tr>
 *   <tr><td>productNumber</td><td>{@link java.lang.Integer}</td><td>product_number</td><td>int</td><td>商品数量</td></tr>
 *   <tr><td>collectionNumber</td><td>{@link java.lang.Integer}</td><td>collection_number</td><td>int</td><td>店铺收藏</td></tr>
 *   <tr><td>createTime</td><td>{@link java.util.Date}</td><td>create_time</td><td>datetime</td><td>创建时间</td></tr>
 *   <tr><td>saleMoney</td><td>{@link java.math.BigDecimal}</td><td>sale_money</td><td>decimal</td><td>店铺总销售金额</td></tr>
 *   <tr><td>orderCount</td><td>{@link java.lang.Integer}</td><td>order_count</td><td>int</td><td>店铺总订单量</td></tr>
 *   <tr><td>orderCountOver</td><td>{@link java.lang.Integer}</td><td>order_count_over</td><td>int</td><td>店铺完成订单量</td></tr>
 *   <tr><td>sellerKeyword</td><td>{@link java.lang.String}</td><td>seller_keyword</td><td>varchar</td><td>SEO关键字</td></tr>
 *   <tr><td>sellerDes</td><td>{@link java.lang.String}</td><td>seller_des</td><td>varchar</td><td>SEO店铺描述</td></tr>
 *   <tr><td>auditStatus</td><td>{@link java.lang.Integer}</td><td>audit_status</td><td>tinyint</td><td>审核状态 1、待审核；2、审核通过；3、冻结</td></tr>
 *   <tr><td>storeSlide</td><td>{@link java.lang.String}</td><td>store_slide</td><td>text</td><td>店铺轮播图 </td></tr>
 *   <tr><td>businessState</td><td>{@link java.lang.Integer}</td><td>business_state</td><td>int</td><td>状态</td></tr>
 *   <tr><td>reportCount</td><td>{@link java.lang.Integer}</td><td>report_count</td><td>int</td><td>举报数量</td></tr>
 *   <tr><td>compainCount</td><td>{@link java.lang.Integer}</td><td>compain_count</td><td>int</td><td>投诉数量</td></tr>
 *   <tr><td>comparkMark</td><td>{@link java.lang.String}</td><td>compark_mark</td><td>varchar</td><td>综合评分</td></tr>
 *   <tr><td>isContributing</td><td>{@link java.lang.Integer}</td><td>is_contributing</td><td>int</td><td>是否是特约商户 0:否  1:是</td></tr>
 *   <tr><td>subjectId</td><td>{@link java.lang.Integer}</td><td>subject_id</td><td>int</td><td>结算主体ID </td></tr>
 * </table>
 *
 */
public class Seller implements Serializable {

    /**
     *Comment for <code>serialVersionUID</code>
     */
    private static final long serialVersionUID     = 8389697070371397294L;

    /** 商家状态：待审核  */
    public static final int   AUDIT_STATE_1_SEND   = 1;
    /** 商家状态：审核通过  */
    public static final int   AUDIT_STATE_2_DONE   = 2;
    /** 商家状态：冻结  */
    public static final int   AUDIT_STATE_3_FREEZE = 3;
    /** 是否是特约商户：否  */
    public static final int   IS_CONTRIBUTING0 = 0;
    /** 是否是特约商户：是  */
    public static final int   IS_CONTRIBUTING1 = 1;

    private Integer           id;                                         //id
    private Integer           memberId;                                   //用户ID
    private String            name;                                       //用户名
    private String            sellerName;                                 //店铺名称
    private String            sellerLogo;                                 //店铺logo
    private Integer           sellerGrade;                                //店铺等级
    private String            scoreService;                               //店铺评分服务
    public String getScoreService() {
		return scoreService;
	}

	private String            scoreDeliverGoods;                          //店铺评分发货
    private String            scoreDescription;                           //店铺评分描述
    private Integer           productNumber;                              //商品数量
    private Integer           collectionNumber;                           //店铺收藏
    private Date              createTime;                                 //创建时间
    private BigDecimal        saleMoney;                                  //店铺总销售金额
    private Integer           orderCount;                                 //店铺总订单量
    private Integer           orderCountOver;                             //店铺完成订单量
    private String            sellerKeyword;                              //SEO关键字
    private String            sellerDes;                                  //SEO店铺描述
    private Integer           auditStatus;                                //审核状态 1、待审核；2、审核通过；3、冻结
    private String            storeSlide;                                 //店铺轮播图
    private Integer           isSelf;                                     //是否自营， 0、否；1、是
    private Integer			  isContributing;							  //是否是特约商户 0:否  1:是
    private Integer			  subjectId;								  //结算主体ID 

    // --------额外属性（entity对应表结构之外的属性） start------------------------------
    private String            memberName;                                 // 申请人member表的name
    private String            company;
    private Integer			  sellerApplyId;							  // 商家申请ID
    private String 			  subjectName;								  // 结算主体名称
    // --------额外属性（entity对应表结构之外的属性） end--------------------------------
	
    private Integer businessState;
	
	private Integer reportCount;
	
	private String comparkMark;
	
	public String getComparkMark() {
		return comparkMark;
	}

	public void setComparkMark(String comparkMark) {
		this.comparkMark = comparkMark;
	}

	private Integer compainCount;
	/**
	 * 获取状态
	 * @return
	 */
 	public Integer getBusinessState() {
		return businessState;
	}

 	/**
 	 * 设置状态
 	 * @param businessState
 	 */
	public void setBusinessState(Integer businessState) {
		this.businessState = businessState;
	}

	/**
	 * 获取举报数量
	 * @return
	 */
	public Integer getReportCount() {
		return reportCount;
	}

	/**
	 * 设置举报数量
	 * @param reportCount
	 */
	public void setReportCount(Integer reportCount) {
		this.reportCount = reportCount;
	}

	/**
	 * 获取投诉数量
	 * @return
	 */
	public Integer getCompainCount() {
		return compainCount;
	}

	/**
	 * 设置投诉数量
	 * @param compainCount
	 */
	public void setCompainCount(Integer compainCount) {
		this.compainCount = compainCount;
	}

 	
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
     * 获取用户ID
     */
    public Integer getMemberId() {
        return this.memberId;
    }

    /**
     * 设置用户ID
     */
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    /**
     * 获取用户名
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置用户名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取店铺名称
     */
    public String getSellerName() {
        return this.sellerName;
    }

    /**
     * 设置店铺名称
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    /**
     * 获取店铺等级
     */
    public Integer getSellerGrade() {
        return this.sellerGrade;
    }

    /**
     * 设置店铺等级
     */
    public void setSellerGrade(Integer sellerGrade) {
        this.sellerGrade = sellerGrade;
    }

    /**
     * 获取店铺评分服务
     */
    public String coreService() {
        return this.scoreService;
    }

    /**
     * 设置店铺评分服务
     */
    public void setScoreService(String scoreService) {
        this.scoreService = scoreService;
    }

    /**
     * 获取店铺评分发货
     */
    public String getScoreDeliverGoods() {
        return this.scoreDeliverGoods;
    }

    /**
     * 设置店铺评分发货
     */
    public void setScoreDeliverGoods(String scoreDeliverGoods) {
        this.scoreDeliverGoods = scoreDeliverGoods;
    }

    /**
     * 获取店铺评分描述
     */
    public String getScoreDescription() {
        return this.scoreDescription;
    }

    /**
     * 设置店铺评分描述
     */
    public void setScoreDescription(String scoreDescription) {
        this.scoreDescription = scoreDescription;
    }

    /**
     * 获取商品数量
     */
    public Integer getProductNumber() {
        return this.productNumber;
    }

    /**
     * 设置商品数量
     */
    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * 获取店铺收藏
     */
    public Integer getCollectionNumber() {
        return this.collectionNumber;
    }

    /**
     * 设置店铺收藏
     */
    public void setCollectionNumber(Integer collectionNumber) {
        this.collectionNumber = collectionNumber;
    }

    /**
     * 获取创建时间
     */
    public java.util.Date getCreateTime() {
        return this.createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(java.util.Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取店铺总销售金额
     */
    public java.math.BigDecimal getSaleMoney() {
        return this.saleMoney;
    }

    /**
     * 设置店铺总销售金额
     */
    public void setSaleMoney(java.math.BigDecimal saleMoney) {
        this.saleMoney = saleMoney;
    }

    /**
     * 获取店铺总订单量
     */
    public Integer getOrderCount() {
        return this.orderCount;
    }

    /**
     * 设置店铺总订单量
     */
    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
     * 获取店铺完成订单量
     */
    public Integer getOrderCountOver() {
        return this.orderCountOver;
    }

    /**
     * 设置店铺完成订单量
     */
    public void setOrderCountOver(Integer orderCountOver) {
        this.orderCountOver = orderCountOver;
    }

    /**
     * 获取SEO关键字
     */
    public String getSellerKeyword() {
        return this.sellerKeyword;
    }

    /**
     * 设置SEO关键字
     */
    public void setSellerKeyword(String sellerKeyword) {
        this.sellerKeyword = sellerKeyword;
    }

    /**
     * 获取SEO店铺描述
     */
    public String getSellerDes() {
        return this.sellerDes;
    }

    /**
     * 设置SEO店铺描述
     */
    public void setSellerDes(String sellerDes) {
        this.sellerDes = sellerDes;
    }

    /**
     * 获取状态 1、待审核；2、审核通过；3、冻结
     */
    public Integer getAuditStatus() {
        return this.auditStatus;
    }

    /**
     * 设置状态 1、待审核；2、审核通过；3、冻结
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取店铺logo
     */
    public String getSellerLogo() {
        return sellerLogo;
    }

    /**
     * 设置店铺logo
     */
    public void setSellerLogo(String sellerLogo) {
        this.sellerLogo = sellerLogo;
    }

    /**
     * 获取店铺轮播图 
     */
    public String getStoreSlide() {
        return this.storeSlide;
    }

    /**
     * 设置店铺轮播图 
     */
    public void setStoreSlide(String storeSlide) {
        this.storeSlide = storeSlide;
    }

    public Integer getIsSelf() {
		return isSelf;
	}

	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}

	public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

	public Integer getIsContributing() {
		return isContributing;
	}

	public void setIsContributing(Integer isContributing) {
		this.isContributing = isContributing;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Integer getSellerApplyId() {
		return sellerApplyId;
	}

	public void setSellerApplyId(Integer sellerApplyId) {
		this.sellerApplyId = sellerApplyId;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
}