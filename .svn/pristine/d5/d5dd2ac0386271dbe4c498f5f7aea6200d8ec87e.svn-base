#字典表(数据字典表，里面的任何数据都不能删除)
#TRUNCATE TABLE code_master;

#系统配置表(初始化数据是ID为1的积分换算成人民币的比例，可以在后台中进行配置，不能删除；)
#TRUNCATE TABLE config;
#delete from config where id>1;

#会员等级配置表(不能删除，系统在启用之前在平台管理中进行配置，一般配置好之后不会更改，否则很可能会对数据的一致性造成影响)
#TRUNCATE TABLE member_grade_config;

/*
会员经验值和积分规则，数据已经初始化好，ID是1和2，ID不能修改，是系统初始化数据，送积分和经验值的方式有，
注册、登陆、订单评价、订单购买，可以在平台管理里面修改，同样一般在电商系统启动之前都会制定送积分和经验值的规则，规则可以动态调整，
可以在平台管理里面进行调整
*/
#会员经验值和积分规则
#TRUNCATE TABLE member_rule;
delete from member_rule where id>2;

#省市区表，数据也可以根据自己的需求自行导入
#TRUNCATE TABLE regions;

/*
搜索敏感词是2016最新的词库，请勿删除，搜索的时候设置敏感词过滤，会检索这个表进行过滤
*/
#搜索敏感词过滤
#TRUNCATE TABLE search_keyword;

/*
设置热门关键词、关键词是否过滤、定时更新索引的时间，数据不能删除，可以在后台进行设置
*/
#搜索设置
#TRUNCATE TABLE search_setting;


/*
seller、seller_user、seller_apply是商家表、商家用户表、商家申请表，ID为1的数据是平台自营的数据，请勿删除，用户名其他信息可以手动修改下
*/
#商家表
#TRUNCATE TABLE seller;
delete from seller where id>1;
#商家用户表
#TRUNCATE TABLE seller_user;
delete FROM	seller_user where id>1;
#商家申请表
#TRUNCATE TABLE seller_apply;
delete FROM	seller_apply where id>1;
/*
seller_roles和seller_resources_roles是商家角色表和商家角色资源对应表也要把平台自营相关的账户信息留下；
*/
#商家角色表
#TRUNCATE TABLE seller_roles;
delete from seller_roles where seller_id<>1;
#商家角色资源对应表
#TRUNCATE TABLE seller_resources_roles;
delete from seller_resources_roles where seller_roles_id not in (select id from seller_roles where seller_id=1)

#系统管理员表(清理数据的时候留下admin用户)
#TRUNCATE TABLE system_admin;
delete FROM system_admin where name<>'admin';

#资源表(所有系统的资源，不能删除，切记)
#TRUNCATE TABLE system_resources;

#角色表(admin对应的角色不能删)
#TRUNCATE TABLE system_roles;
delete from system_roles where id>1

#角色资源对应表
#TRUNCATE TABLE system_resources_roles;
delete FROM system_resources_roles where roles_id<>1;

#文章分类(新闻分类表和新闻表，建议根据自己的需求修改内容)
#TRUNCATE TABLE news_type;
delete from news_type where id>6;
#新闻资讯
#TRUNCATE TABLE news;
####################################################################################################

#集合竞价
TRUNCATE TABLE act_bidding;

#集合竞价首页轮播图
TRUNCATE TABLE act_bidding_banner;

#阶梯价格表
TRUNCATE TABLE act_bidding_price;

#集合竞价分类
TRUNCATE TABLE act_bidding_type;

#限时抢购首页轮播图
TRUNCATE TABLE act_flash_banner;

#限时抢购活动表
TRUNCATE TABLE act_flash_sale;

#限时抢购活动参加日志表
TRUNCATE TABLE act_flash_sale_log;

#限时抢购活动商品表
TRUNCATE TABLE act_flash_sale_product;

#限时抢购活动阶段表
TRUNCATE TABLE act_flash_sale_stage;

#满减活动表
TRUNCATE TABLE act_full;

#满减活动参加日志表
TRUNCATE TABLE act_full_log;

#团购
TRUNCATE TABLE act_group;

#团购首页轮播图
TRUNCATE TABLE act_group_banner;

#团购分类
TRUNCATE TABLE act_group_type;

#积分商城
TRUNCATE TABLE act_integral;

#积分商城轮播图
TRUNCATE TABLE act_integral_banner;

#积分商城分类
TRUNCATE TABLE act_integral_type;

#单品立减活动表
TRUNCATE TABLE act_single;

#单品立减活动参加日志表
TRUNCATE TABLE act_single_log;

#商城购物车
TRUNCATE TABLE cart;



#优惠券表
TRUNCATE TABLE coupon;

#优惠券操作日志
TRUNCATE TABLE coupon_opt_log;

#优惠券用户表
TRUNCATE TABLE coupon_user;

#快递公司
#TRUNCATE TABLE courier_company;
DELETE TABLE courier_company where id > 2 ;

#
TRUNCATE TABLE index_banner;

#发票信息表
TRUNCATE TABLE invoice;

#
TRUNCATE TABLE kt_member;

#限时抢购活动日志表
TRUNCATE TABLE log_act_flash_sale;

#限时抢购活动商品日志表
TRUNCATE TABLE log_act_flash_sale_product;

#限时抢购活动阶段日志表
TRUNCATE TABLE log_act_flash_sale_stage;

#满减活动日志表
TRUNCATE TABLE log_act_full;

#单品立减活动日志表
TRUNCATE TABLE log_act_single;

#优惠券日志表
TRUNCATE TABLE log_coupon;

#会员
TRUNCATE TABLE member;

#收货地址
TRUNCATE TABLE member_address;

#会员提款申请
TRUNCATE TABLE member_apply_drawing;

#会员账户余额变化日志表
TRUNCATE TABLE member_balance_logs;

#会员充值记录
TRUNCATE TABLE member_balance_pay_log;

#会员收藏商品表
TRUNCATE TABLE member_collection_product;

#会员收藏商铺表
TRUNCATE TABLE member_collection_seller;

#会员经验值年度递减日志表（每天执行完定时任务后记录）
TRUNCATE TABLE member_grade_down_logs;

#会员经验积分日志表
TRUNCATE TABLE member_grade_integral_logs;

#会员等级升级日志表
TRUNCATE TABLE member_grade_up_logs;

#会员登录日志
TRUNCATE TABLE member_login_logs;

#用户退货
TRUNCATE TABLE member_product_back;

#用户换货
TRUNCATE TABLE member_product_exchange;



#会员签到日志
TRUNCATE TABLE member_sign_logs;

#微信联合登录
TRUNCATE TABLE member_wxsign;

#移动端首页轮播图
TRUNCATE TABLE m_index_banner;

#移动端首页楼层表
TRUNCATE TABLE m_index_floor;

#首页楼层数据表
TRUNCATE TABLE m_index_floor_data;

#m推荐商品表
TRUNCATE TABLE m_recommend;

#移动端商家首页轮播图
TRUNCATE TABLE m_seller_index_banner;

#移动端商家首页楼层表
TRUNCATE TABLE m_seller_index_floor;

#商家首页楼层数据表
TRUNCATE TABLE m_seller_index_floor_data;

#合作伙伴
TRUNCATE TABLE news_partner;

#商家公告查看情况
TRUNCATE TABLE notice_click_situation;

#
TRUNCATE TABLE operation;

#订单
TRUNCATE TABLE orders;

#网单表
TRUNCATE TABLE orders_product;

#发货单
TRUNCATE TABLE order_delivery;

#发货单明细
TRUNCATE TABLE order_delivery_details;

#订单操作日志表
TRUNCATE TABLE order_log;

#订单第三方支付日志表
TRUNCATE TABLE order_pay_cash_log;

#订单支付日志表
TRUNCATE TABLE order_pay_log;

#园区管理表
#TRUNCATE TABLE park;

#
#TRUNCATE TABLE park_advantage;

#PC端首页轮播图
TRUNCATE TABLE pc_index_banner;

#PC端首页楼层表
TRUNCATE TABLE pc_index_floor;

#PC端首页楼层分类表
TRUNCATE TABLE pc_index_floor_class;

#PC端首页楼层分类数据表
TRUNCATE TABLE pc_index_floor_data;

#PC端首页楼层碎屑表
TRUNCATE TABLE pc_index_floor_patch;

#PC端首页的一些图片
TRUNCATE TABLE pc_index_image;

#PC推荐商品表
TRUNCATE TABLE pc_recommend;

#PC端商家首页信息
TRUNCATE TABLE pc_seller_index;

#PC端商家首页轮播图
TRUNCATE TABLE pc_seller_index_banner;

#PC端商家推荐类型表
TRUNCATE TABLE pc_seller_recommend;

#PC端商家推荐数据表
TRUNCATE TABLE pc_seller_recommend_data;

#门户的数据暂时先不清理
#TRUNCATE TABLE portal_active;

#
#TRUNCATE TABLE portal_menu;

#
#TRUNCATE TABLE portal_menu_park;

#
#TRUNCATE TABLE portal_service;

#商品表
TRUNCATE TABLE product;

#商品咨询管理
TRUNCATE TABLE product_ask;

#商品对应属性表(保存商品时插入)
TRUNCATE TABLE product_attr;

#商品品牌
TRUNCATE TABLE product_brand;

#商品分类
TRUNCATE TABLE product_cate;

#商品评论管理
TRUNCATE TABLE product_comments;

#货品表
TRUNCATE TABLE product_goods;

#商品规格表
TRUNCATE TABLE product_norm;

#规格属性表
TRUNCATE TABLE product_norm_attr;

#商品选定的颜色规格
TRUNCATE TABLE product_norm_attr_opt;

#商品对应图片表
TRUNCATE TABLE product_picture;

#商品类型表
TRUNCATE TABLE product_type;

#商品类型属性表
TRUNCATE TABLE product_type_attr;

#
TRUNCATE TABLE quick_enter;

#搜索历史记录表
TRUNCATE TABLE search_logs;

#热搜词表
#TRUNCATE TABLE search_record;

#商家品牌申请表
TRUNCATE TABLE seller_apply_brand;

#店铺分类
TRUNCATE TABLE seller_cate;

#商家投诉管理
TRUNCATE TABLE seller_complaint;

#商家可以经营商品分类表
TRUNCATE TABLE seller_manage_cate;

#
TRUNCATE TABLE seller_park_operation;

#商家客服QQ
TRUNCATE TABLE seller_qq;

#卖家运费模板
TRUNCATE TABLE seller_transport;

#商家类型
TRUNCATE TABLE seller_type;

#商家类型修改日志表
TRUNCATE TABLE seller_type_logs;

#商家用户登录日志
TRUNCATE TABLE seller_user_login_log;

#结算表
TRUNCATE TABLE settlement;

#结算网单表
TRUNCATE TABLE settlement_op;

#
TRUNCATE TABLE shop_active;

#
TRUNCATE TABLE supplier;

#供应商换货单
TRUNCATE TABLE supplier_exchange;

#供应商退货单
TRUNCATE TABLE supplier_return;

#系统操作日志表
TRUNCATE TABLE system_logs;

#商城公告
TRUNCATE TABLE system_notice;

#京东相关业务的数据全部清理掉
#访问京东的token
TRUNCATE TABLE access_token ;

#京东分类
TRUNCATE TABLE jd_category ;

#京东商品池
TRUNCATE TABLE jd_commoditypool ;

#京东商品
TRUNCATE TABLE jd_product ;

#京东临时skuid
TRUNCATE TABLE  jd_temporaryskuid ;

#京东关联分类
TRUNCATE TABLE product_cate_jd;

#删除京东分类
delete from product_cate where type = 2 ;
