#库表索引检查、创建
#CREATE UNIQUE INDEX index_name ON table_name (column_name);
#ALTER TABLE table_name ADD INDEX index_name (column_list);

#字典表
#SHOW INDEX from  code_master ;
#desc code_master ;

#系统配置表
#SHOW INDEX from config ;
#desc config ;

#会员等级配置表
#SHOW INDEX from member_grade_config ;
#desc member_grade_config ;

#会员经验值和积分规则
#SHOW INDEX from member_rule ;
#desc member_rule ;
#select * from member_rule;

#省市区表
#SHOW INDEX from regions ;
#desc regions ;

/*
搜索敏感词是2016最新的词库，请勿删除，搜索的时候设置敏感词过滤，会检索这个表进行过滤
*/
#搜索敏感词过滤
#SHOW INDEX from search_keyword ;
#desc search_keyword ;

/*
设置热门关键词、关键词是否过滤、定时更新索引的时间，数据不能删除，可以在后台进行设置
*/
#搜索设置
#SHOW INDEX from search_setting ;
#desc search_setting ;


#商家表
#SHOW INDEX from seller ;
#desc seller ;
#ALTER TABLE seller ADD INDEX index_seller_memberId(member_id);

#商家用户表
#SHOW INDEX from seller_user ;
#desc seller_user ;
ALTER TABLE seller_user ADD INDEX index_sellerUser_sellerId(seller_id);

#商家申请表
#SHOW INDEX from seller_apply ;
#desc seller_apply ;


#商家角色表
#SHOW INDEX from seller_roles ;
#desc seller_roles ;
ALTER TABLE seller_roles ADD INDEX index_sellerRoles_sellerId(seller_id);

#商家角色资源对应表
#SHOW INDEX from seller_resources_roles ;
#desc seller_resources_roles ;
ALTER TABLE seller_resources_roles ADD INDEX index_sellerResRoles_resId(resources_id);

#系统管理员表
#SHOW INDEX from system_admin ;
#desc system_admin ;

#资源表
#SHOW INDEX from system_resources ;
#desc system_resources ;
ALTER TABLE system_resources ADD INDEX index_systemRes_pid(pid);

#角色表
#SHOW INDEX from system_roles ;
#desc system_roles ;

#角色资源对应表
#desc system_resources_roles;

#文章分类
#desc news_type;
ALTER TABLE news_type ADD INDEX index_news_pid(pid);

#新闻资讯
#desc news;
ALTER TABLE news ADD INDEX index_news_typeId(type_id);
####################################################################################################

#集合竞价
#desc act_bidding;
ALTER TABLE act_bidding ADD INDEX index_actBidding_productId(product_id);
ALTER TABLE act_bidding ADD INDEX index_actBidding_sellerId(seller_id);

#集合竞价首页轮播图
#desc act_bidding_banner;

#阶梯价格表
#desc act_bidding_price;

#集合竞价分类
#desc  act_bidding_type;

#限时抢购首页轮播图
#desc act_flash_banner;

#限时抢购活动表
#desc act_flash_sale;

#限时抢购活动参加日志表
#desc act_flash_sale_log;

#限时抢购活动商品表
#desc act_flash_sale_product;
ALTER TABLE act_flash_sale_product ADD INDEX index_actFlashSaleP_productId(product_id);
ALTER TABLE act_flash_sale_product ADD INDEX index_actFlashSaleP_sellerId(seller_id);

#限时抢购活动阶段表
#desc act_flash_sale_stage;

#满减活动表
#desc act_full;
ALTER TABLE act_full ADD INDEX index_actFull_sellerId(seller_id);

#满减活动参加日志表
#desc act_full_log;

#团购
#desc act_group;
ALTER TABLE act_group ADD INDEX index_actGroup_productId(product_id);

#团购首页轮播图
#desc act_group_banner;

#团购分类
#desc act_group_type;

#积分商城
#desc act_integral;
ALTER TABLE act_integral ADD INDEX index_actIntegral_productId(product_id);
ALTER TABLE act_integral ADD INDEX index_actIntegral_sellerId(seller_id);

#积分商城轮播图
#desc act_integral_banner;

#积分商城分类
#desc act_integral_type;

#单品立减活动表
#desc act_single;
ALTER TABLE act_single ADD INDEX index_actSingle_sellerId(seller_id);

#单品立减活动参加日志表
#desc act_single_log;

#商城购物车
#desc cart;
ALTER TABLE cart ADD INDEX index_cart_memberId(member_id);
ALTER TABLE cart ADD INDEX index_cart_sellerId(seller_id);


#优惠券表
#desc coupon;
ALTER TABLE coupon ADD INDEX index_coupon_sellerId(seller_id);

#优惠券操作日志
#desc coupon_opt_log;

#优惠券用户表
#desc coupon_user;
ALTER TABLE coupon_user ADD INDEX index_couponUser_memberId(member_id);
ALTER TABLE coupon_user ADD INDEX index_couponUser_sellerId(seller_id);
ALTER TABLE coupon_user ADD INDEX index_couponUser_couponId(coupon_id);

#快递公司
#desc courier_company;

#
#desc index_banner;

#发票信息表
#desc invoice;

#限时抢购活动日志表
#desc log_act_flash_sale;

#限时抢购活动商品日志表
#desc log_act_flash_sale_product;

#限时抢购活动阶段日志表
#desc log_act_flash_sale_stage;

#满减活动日志表
#desc log_act_full;

#单品立减活动日志表
#desc log_act_single;

#优惠券日志表
#desc log_coupon;

#会员
#desc member;

#收货地址
#desc member_address;
ALTER TABLE member_address ADD INDEX index_memberAddress_memberId(member_id);

#会员提款申请
#desc member_apply_drawing;
ALTER TABLE member_apply_drawing ADD INDEX index_memberApplyDraw_memberId(member_id);

#会员账户余额变化日志表
#desc member_balance_logs;

#会员充值记录
#desc member_balance_pay_log;

#会员收藏商品表
#desc member_collection_product;
ALTER TABLE member_collection_product ADD INDEX index_memberCollPro_memberId(member_id);

#会员收藏商铺表
#desc member_collection_seller;
ALTER TABLE member_collection_seller ADD INDEX index_memberCollSeller_memberId(member_id);

#会员经验值年度递减日志表（每天执行完定时任务后记录）
#desc member_grade_down_logs;

#会员经验积分日志表
#desc member_grade_integral_logs;

#会员等级升级日志表
#desc member_grade_up_logs;

#会员登录日志
#desc member_login_logs;

#用户退货
#desc member_product_back;
ALTER TABLE member_product_back ADD INDEX index_memberProBack_sellerId(seller_id);

#用户换货
#desc member_product_exchange;

#会员签到日志
#desc member_sign_logs;

#微信联合登录
#desc member_wxsign;

#移动端首页轮播图
#desc m_index_banner;

#移动端首页楼层表
#desc m_index_floor;

#首页楼层数据表
#desc m_index_floor_data;

#m推荐商品表
#desc m_recommend;

#移动端商家首页轮播图
#desc m_seller_index_banner;
ALTER TABLE m_seller_index_banner ADD INDEX index_m_sellerIndexB_sellerId(seller_id);

#移动端商家首页楼层表
#desc m_seller_index_floor;
ALTER TABLE m_seller_index_floor ADD INDEX index_m_sellerIndexF_sellerId(seller_id);

#商家首页楼层数据表
#desc m_seller_index_floor_data;
ALTER TABLE m_seller_index_floor_data ADD INDEX index_m_sellerIndexD_sellerId(seller_id);

#合作伙伴
#desc news_partner;

#商家公告查看情况
#desc notice_click_situation;
ALTER TABLE notice_click_situation ADD INDEX index_noticeClickS_sellerId(seller_id);

#
#desc operation;

#订单
#desc orders;
ALTER TABLE orders ADD INDEX index_orders_sellerId(seller_id);
ALTER TABLE orders ADD INDEX index_orders_memberId(member_id);

#网单表
#desc orders_product;
ALTER TABLE orders_product ADD INDEX index_ordersPro_OrderId(orders_id);
ALTER TABLE orders_product ADD INDEX index_ordersPro_OrderSn(orders_sn);
ALTER TABLE orders_product ADD INDEX index_ordersPro_sellerId(seller_id);
ALTER TABLE orders_product ADD INDEX index_ordersPro_productId(product_id);

#发货单
#desc order_delivery;
ALTER TABLE order_delivery ADD INDEX index_ordersPro_OrderSn(order_sn);
ALTER TABLE order_delivery ADD INDEX index_ordersPro_sellerId(seller_id);

#发货单明细
#desc order_delivery_details;

#订单操作日志表
#desc order_log;

#订单第三方支付日志表
#desc order_pay_cash_log;

#订单支付日志表
#desc order_pay_log;
ALTER TABLE order_pay_log ADD INDEX index_ordersPayLog_ordersId(orders_id);
ALTER TABLE order_pay_log ADD INDEX index_ordersPayLog_memberId(member_id);

#园区管理表
#desc park;

#
#desc park_advantage;

#PC端首页轮播图
#desc  pc_index_banner;

#PC端首页楼层表
#desc pc_index_floor;

#PC端首页楼层分类表
#desc pc_index_floor_class;

#PC端首页楼层分类数据表
#desc pc_index_floor_data;

#PC端首页楼层碎屑表
#desc pc_index_floor_patch;

#PC端首页的一些图片
#desc  pc_index_image;

#PC推荐商品表
#desc pc_recommend;

#PC端商家首页信息
#desc pc_seller_index;

#PC端商家首页轮播图
#desc pc_seller_index_banner;

#PC端商家推荐类型表
#desc pc_seller_recommend;

#PC端商家推荐数据表
#desc pc_seller_recommend_data;

#门户的数据暂时先不清理
#desc portal_active;

#
#desc portal_menu;

#
#desc portal_menu_park;

#
#desc portal_service;

#商品表
#desc product;

#商品咨询管理
#desc product_ask;

#商品对应属性表(保存商品时插入)
#desc product_attr;
ALTER TABLE product_attr ADD INDEX index_productAttr_producId(product_id);

#商品品牌
#desc product_brand;

#商品分类
#desc product_cate;

#商品评论管理
#desc  product_comments;

#货品表
#desc product_goods;

#商品规格表
#desc product_norm;

#规格属性表
#desc product_norm_attr;

#商品选定的颜色规格
#desc product_norm_attr_opt;

#商品对应图片表
#desc product_picture;

#商品类型表
#desc product_type;

#商品类型属性表
#desc product_type_attr;

#
#desc quick_enter;

#搜索历史记录表
#desc  search_logs;

#热搜词表
#desc search_record;

#商家品牌申请表
#desc seller_apply_brand;

#店铺分类
#desc seller_cate;
ALTER TABLE seller_cate ADD INDEX index_sellerCate_sellerId(seller_id);

#商家投诉管理
#desc seller_complaint;

#商家可以经营商品分类表
#desc seller_manage_cate;
ALTER TABLE seller_manage_cate ADD INDEX index_sellerManageCate_sellerId(seller);

#
#desc seller_park_operation;

#商家客服QQ
#desc seller_qq;

#卖家运费模板
#desc seller_transport;

#商家类型
#desc seller_type;

#商家类型修改日志表
#desc seller_type_logs;

#商家用户登录日志
#desc seller_user_login_log;

#结算表
#desc settlement;
ALTER TABLE settlement ADD INDEX index_settlement_sellerId(seller_id);

#结算网单表
#desc settlement_op;
ALTER TABLE settlement_op ADD INDEX index_settlementOp_sellerId(seller_id);
ALTER TABLE settlement_op ADD INDEX index_settlementOp_ordersSn(orders_sn);
ALTER TABLE settlement_op ADD INDEX index_settlementOp_ordersId(orders_id);

#
#desc shop_active;

#
#desc supplier;

#供应商换货单
#desc supplier_exchange;

#供应商退货单
#desc supplier_return;

#系统操作日志表
#desc system_logs;

#商城公告
#desc ystem_notice;

#京东相关业务的数据全部清理掉
#访问京东的token
#desc access_token ;

#京东分类
#desc jd_category ;

#京东商品池
#desc jd_commoditypool ;

#京东商品
#desc jd_product ;

#京东临时skuid
#desc jd_temporaryskuid ;

#京东关联分类
#desc product_cate_jd;



