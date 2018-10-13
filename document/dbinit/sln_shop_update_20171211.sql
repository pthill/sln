# ************************************************************
# Database Update
# Version 20171211
# by: zhangmin
# Database: hhyg_shop
# Generation Time: 2017-12-11 21:30
# ************************************************************

# by yangmingxin
# ------------------------------------------------------------

--ALTER TABLE `system_roles`
--ADD COLUMN `role_type`  varchar(2) NULL AFTER `role_code`;

ALTER TABLE `seller_roles`
ADD COLUMN `role_type`  varchar(2) NULL AFTER `status`;

CREATE TABLE `operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(50) NOT NULL COMMENT '编码',
  `name` varchar(2) NOT NULL COMMENT '业务名称',
  `status` varchar(2) DEFAULT NULL COMMENT '状态',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  `park_id` int(11) NOT NULL COMMENT '园区id',
  `description` varchar(100) DEFAULT NULL COMMENT '介绍',
  `company` varchar(100) DEFAULT NULL COMMENT '公司',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
)

ALTER TABLE `system_resources`
MODIFY COLUMN `scope`  varchar(2) NULL DEFAULT NULL COMMENT '应用范围:0-平台 1-平台和业务管理方 2-商家 3-商家和供应商' AFTER `status`;

CREATE TABLE `seller_park_operation` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`seller_id`  int(11) NULL ,
`park_id`  int(11) NULL ,
`operation_id`  int(11) NULL ,
PRIMARY KEY (`id`)
);

ALTER TABLE `product`
ADD COLUMN `supplier_id`  int(11) NULL COMMENT '供应商';


ALTER TABLE `seller_apply`
ADD column  `card_merchant_number` varchar(100) NULL after `update_time`;
ALTER TABLE `seller_apply`
ADD COLUMN  `park_operation`  varchar(100) NULL AFTER `card_merchant_number`;

ALTER TABLE `seller_user`
ADD column  supplier_id  tinyint(11) NULL after `update_time`;
ALTER TABLE `seller_user`
ADD COLUMN  `suppler_name`  varchar(50) NULL AFTER  supplier_id ;

ALTER TABLE `operation`
MODIFY COLUMN `name`  varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '业务名称' AFTER `code`;

CREATE TABLE `portal_menu`(
`id`  int(11) NOT NULL AUTO_INCREMENT  ,
`name`  varchar(20) NOT NULL DEFAULT '' COMMENT '名称' ,
`order`  int(11) NULL COMMENT '排序' ,
`abbreviation`  varchar(20) NULL COMMENT '简称' ,
`code`  varchar(10) NOT NULL COMMENT '编号' ,
`url`  varchar(50) NOT NULL ,
`state`  varchar(2) NOT NULL COMMENT '状态' ,
PRIMARY KEY (`id`)
);


CREATE TABLE `portal_menu_park` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`portal_menu_id`  int(11) NOT NULL COMMENT '门户菜单' ,
`park_id`  int(11) NOT NULL COMMENT '园区' ,
PRIMARY KEY (`id`)
);

CREATE TABLE `supplier` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(200) NOT NULL COMMENT '供应商名称',
  `adress` varchar(200) NOT NULL COMMENT '地址',
  `supplier_type` varchar(50) NOT NULL COMMENT '供应商类型',
  `contacts_name` varchar(100) NOT NULL COMMENT '负责人名称',
  `contacts_tel` varchar(100) NOT NULL COMMENT '负责人电话',
  `bank_of_accounts` varchar(100) NOT NULL COMMENT '开户银行',
  `benk_account` varchar(100) NOT NULL COMMENT '银行账号',
  `alipay` varchar(100) DEFAULT NULL COMMENT '支付宝',
  `weChat` varchar(100) DEFAULT NULL COMMENT '微信',
  `user_id` int(11) NOT NULL COMMENT '创建人id',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `seller_id` int(11) NOT NULL COMMENT '商户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

# by hulongqiao
# ------------------------------------------------------------

--园区管理表
create table park(
	id int(11) NOT NULL AUTO_INCREMENT,
	park_code varchar(50) NOT NULL COMMENT '编码',
	park_name varchar(50) NOT NULL COMMENT '名称',
	park_addr varchar(100) NOT NULL COMMENT '地址',
	longitude decimal(10,4) NOT NULL COMMENT '经度',
	latitude  decimal(10,4) NOT NULL COMMENT '纬度',
	state  int(11) NOT NULL COMMENT '状态 1为停用，2为启用',
	createa_time datetime NOT NULL COMMENT '创建时间',
	update_time datetime NOT NULL COMMENT '修改时间',
	describe varchar(100) NOT NULL COMMENT '描述',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='园区管理表';


--发货单
create table order_delivery(
	id int(11) NOT NULL AUTO_INCREMENT,
	order_sn varchar(50) NOT NULL COMMENT 		'订单编号',
	delivery_sn varchar(50) NOT NULL COMMENT 	'发货单编号',
	create_time datetime NOT NULL COMMENT		'创建时间',
	order_time datetime NOT NULL COMMENT		'下单时间',
	supplier_id int NOT NULL COMMENT		'供应商Id',
	seller_id int  NOT NULL COMMENT			'商家ID',
	member_id int NOT NULL COMMENT			'会员Id',
	member_name varchar(20) COMMENT			'会员名称',
	member_phone varchar(20) COMMENT		'会员电话',
	state int NOT NULL COMMENT			'状态 1：未发货，2：已发货',
	receiving_address varchar(100) NOT NULL COMMENT '收货地址',
	update_time datetime NOT NULL COMMENT		'确认时间',
	waybill_number varchar(50) COMMENT '运单号',
	logistics int COMMENT '物流公司Id',
	logistics_name varchar(30) COMMENT '物流公司名称',
	invoice_status int COMMENT '发票状态0、不要发票；1、单位；2个人',
	invoice_title varchar(255) COMMENT '发票抬头',	
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='发货单';


alter table seller_user add supplier_id int COMMENT '供应商ID';
alter table seller_user add supplier_name varchar(50) COMMENT '供应商名称';
ALTER TABLE orders_product add supplier_id int;



--供应商退货单
create table supplier_Return(
	id int(11) NOT NULL AUTO_INCREMENT,
	order_sn varchar(50) NOT NULL COMMENT 		'订单编号',
	return_sn varchar(50) NOT NULL COMMENT 		'退货单编号',
	seller_id int  not null comment             '商家id',
	create_time datetime not null  comment      '创建时间',
	update_time datetime  comment      			'收货时间',
	return_state int not null  comment          '退货状态',
	supplier_id int not null   comment          '供应商ID',
	member_id int comment    					'用户id',
	member_name varchar(50) comment    			'用户名',
	back_id int comment                         '退货申请单id',
	PRIMARY KEY (`id`)
)ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='供应商退货单';

--供应商换货单
create table supplier_exchange(
	id int(11) NOT NULL AUTO_INCREMENT,
	exchange_sn varchar(50) NOT NULL COMMENT 						 '换货单号',
	order_sn varchar(50) NOT NULL COMMENT 		    			 '订单编号',
	product_name varchar(50) not null comment       		 '商品名称',
	product_id   int  not null    comment                '商品ID',
	member_name varchar(50)   not null  comment          '用户名',
	member_id    int  not null   comment                 '用户ID',
	remark    varchar(255) comment                       '问题描述',
	exchange_number int not null   comment               '换货数量',
	exchange_state int not null comment                  '换货状态',
	create_time datetime not null    comment             '创建时间',
	receipt_time datetime   comment                      '收货时间',
	deliver_time datetime   comment                      '发货时间',
	return_time  datetime   comment                      '退还时间',
	exchange_id  int not null  comment                   '换货申请单ID',
	supplier_id int COMMENT 							 '供应商ID',
	PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='供应商换货单';

ALTER table member_product_back modify column order_product_id int null;
ALTER table member_product_back modify column product_id int null;
alter table member_product_back modify column number int null DEFAULT '0';

ALTER TABLE `portal_menu`
ADD COLUMN `is_show`  varchar(2) NOT NULL AFTER `state`;

--门户管理活动管理
CREATE TABLE `portal_active` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`title`  varchar(30) NULL COMMENT '活动标题' ,
`order`  int(11) NOT NULL COMMENT '排序' ,
`park_id`  int(11) NOT NULL COMMENT '园区' ,
`address`  varchar(100) NULL COMMENT '地址' ,
`state`  varchar(2) NULL COMMENT '状态' ,
`start_time`  datetime NULL COMMENT '开始时间' ,
`end_time`  datetime NULL COMMENT '结束时间' ,
`status`  varchar(2) NOT NULL COMMENT '启用禁用' ,
`create_time`  datetime NULL ,
`update_time`  datetime NULL ,
PRIMARY KEY (`id`)
);

ALTER TABLE `portal_active`
ADD COLUMN `remark`  varchar(200) NULL COMMENT '描述' AFTER `update_time`,
ADD COLUMN `img`  varchar(100) NULL COMMENT '图片大小为300*300' AFTER `remark`


--门户管理服务管理
CREATE TABLE `portal_service` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`service_name`  varchar(20) NOT NULL COMMENT '服务名称' ,
`order`  tinyint(10) NOT NULL COMMENT '排序' ,
`abbreviation`  varchar(20) NULL COMMENT '简称' ,
`code`  varchar(10) NOT NULL COMMENT '编号' ,
`type`  varchar(2) NOT NULL COMMENT '类型，服务项还是服务类' ,
`pid`  int(11) NULL COMMENT '服务项id' ,
`menu_id`  int(11) NOT NULL COMMENT '一级菜单id' ,
`state`  varchar(2) NOT NULL COMMENT '状态启用禁用' ,
`is_show`  varchar(2) NOT NULL COMMENT '是否显示' ,
`img`  varchar(100) NULL COMMENT '图片' ,
`high_light`  varchar(2) NULL COMMENT '是否高亮' ,
`create_time`  datetime NULL COMMENT '创建时间' ,
`update_time`  datetime NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
;

--门户首页轮播表

CREATE TABLE `index_banner` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) NULL COMMENT '名称' ,
`order`  int(11) NULL COMMENT '排序' ,
`abbreviation`  varchar(20) NULL COMMENT '简称' ,
`type`  varchar(2) NOT NULL COMMENT '类型' ,
`url`  varchar(50) NULL COMMENT 'url' ,
`start_time`  datetime NOT NULL COMMENT '开始时间' ,
`end_time`  datetime NOT NULL COMMENT '结束时间' ,
`state`  varchar(2) NULL COMMENT '状态' ,
`create_time`  datetime NULL COMMENT '创建时间' ,
`update_time`  datetime NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
;
--门户管理园区优势表
CREATE TABLE `park_advantage` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`order`  int(11) NOT NULL COMMENT '排序' ,
`park_id`  int(11) NOT NULL COMMENT '园区' ,
`title`  varchar(20) NULL COMMENT '标题' ,
`state`  varchar(2) NOT NULL COMMENT '状态' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL COMMENT '更新时间' ,
`remark`  varchar(200) NULL COMMENT '备注' ,
PRIMARY KEY (`id`)
)
;

--门户快速入口
CREATE TABLE `quick_enter` (
`id`  int NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) NULL COMMENT '名称' ,
`order`  int(11) NULL COMMENT '排序' ,
`url`  int(11) NULL COMMENT '链接' ,
`state`  varchar(2) NULL COMMENT '状态' ,
`create_time`  datetime NULL ,
`update_time`  datetime NULL ,
PRIMARY KEY (`id`)
)
;

--门户电商活动
CREATE TABLE `shop_active` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`name`  varchar(20) NOT NULL COMMENT '名称' ,
`img`  varchar(200) NULL COMMENT '图片' ,
`type`  varchar(2) NULL COMMENT '活动类型' ,
`state`  varchar(2) NULL COMMENT '状态' ,
`status`  varchar(2) NULL COMMENT '启用禁用' ,
`url`  varchar(50) NULL COMMENT 'url' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`update_time`  datetime NULL COMMENT '更新时间' ,
PRIMARY KEY (`id`)
)
;
ALTER TABLE `index_banner`
ADD COLUMN `img`  varchar(100) NULL COMMENT '图片' AFTER `end_time`;

ALTER TABLE `index_banner`
ADD COLUMN `park_id`  int(11) NOT NULL AFTER `update_time`;

ALTER TABLE `park`
ADD COLUMN `city`  varchar(20) NULL AFTER `tel`;

--推荐服务列表
CREATE TABLE `recommend_service` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`service_id`  int(11) NOT NULL ,
`order`  int(11) NOT NULL ,
`state`  varchar(2) NOT NULL ,
`img`  varchar(100) NOT NULL ,
`create_time`  datetime NULL ,
`update_time`  datetime NULL ,
PRIMARY KEY (`id`)
)
;

ALTER TABLE `recommend_service`
ADD COLUMN `park_id`  int(11) NULL AFTER `state`;

ALTER TABLE `quick_enter`
ADD COLUMN `park_id`  int(11) NULL AFTER `update_time`;





--商家表添加“是否自营”字段
ALTER TABLE `seller` ADD COLUMN `is_self` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否自营，0：不是，1：是';

--分类表添加：分类类型、服务费比例、京东分类ID
ALTER TABLE `product_cate` ADD COLUMN `type` tinyint(4) NOT NULL DEFAULT '1' COMMENT '分类类型，1：平台，2：京东';
ALTER TABLE `product_cate` ADD COLUMN `service_rate` decimal(5,2) COMMENT '服务费比例';
ALTER TABLE `product_cate` ADD COLUMN `jd_cat_id` int COMMENT '京东分类ID';


--商品分类服务费比例开关表
CREATE TABLE `product_cate_service_switch` (
`id`  tinyint(11) NOT NULL AUTO_INCREMENT ,
`state`  tinyint(2) NOT NULL DEFAULT '1' COMMENT '状态,0:未使用，1：使用' ,
`create_id`  int NOT NULL COMMENT '创建人ID',
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`update_id`  int NULL COMMENT '更新人ID',
`update_time`  datetime NULL COMMENT '更新时间' ,
`remark`  varchar(200) NULL COMMENT '备注' ,
PRIMARY KEY (`id`)
);

--京东分类信息表
DROP TABLE IF EXISTS `jd_category`;
CREATE TABLE `jd_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `catId` int(11) NOT NULL COMMENT '分类ID',
  `parentId` int(11) DEFAULT NULL COMMENT '父类ID',
  `name` varchar(50) DEFAULT NULL COMMENT '分类名称',
  `catClass` int(11) NOT NULL COMMENT '0：一级分类；1：二级分类；2：三级分类',
  `state` int(11) NOT NULL COMMENT '1：有效；0：无效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=207537 DEFAULT CHARSET=utf8 COMMENT='京东分类信息表';

--京东商品池子表
DROP TABLE IF EXISTS `jd_commoditypool`;
CREATE TABLE `jd_commoditypool` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL COMMENT '池子名称',
  `page_num` int(11) NOT NULL COMMENT '池子编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3019 DEFAULT CHARSET=utf8 COMMENT='京东商品池子表';


--京东商品表
DROP TABLE IF EXISTS `jd_product`;
CREATE TABLE `jd_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sku` varchar(20) NOT NULL COMMENT 'sku',
  `name` varchar(100) NOT NULL COMMENT '名称',
  `price` decimal(11,4) DEFAULT NULL COMMENT '协议价格',
  `jdprice` decimal(11,4) DEFAULT NULL COMMENT '京东价格',
  `param` longtext COMMENT '规格参数',
  `introduction` longtext COMMENT '详细介绍',
  `imagePath` varchar(100) DEFAULT NULL COMMENT '主图地址',
  `skuState` int(11) DEFAULT NULL COMMENT '上下架状态',
  `updateState` int(11) DEFAULT NULL COMMENT '修改状态 1:新增 2：更新 3删除 4同步',
  `catId` int(11) NOT NULL COMMENT '分类id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=76869 DEFAULT CHARSET=utf8 COMMENT='京东商品表';


--京东临时SKUID表
DROP TABLE IF EXISTS `jd_temporaryskuid`;
CREATE TABLE `jd_temporaryskuid` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `skuId` varchar(20) NOT NULL COMMENT '临时skuid',
  `state` int(11) NOT NULL COMMENT '1表示未导入详情，2表示已导入详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=738567 DEFAULT CHARSET=utf8 COMMENT='京东临时SKUID表';

--京东Access Token表
DROP TABLE IF EXISTS `access_token`;
CREATE TABLE `access_token` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(50) DEFAULT NULL COMMENT 'UID',
  `access_token` varchar(50) DEFAULT NULL COMMENT 'Access_token',
  `refresh_token` varchar(50) DEFAULT NULL COMMENT 'Refresh_token',
  `time` datetime DEFAULT NULL COMMENT '当前时间（京东返回）',
  `expires_in` int(11) DEFAULT NULL COMMENT 'Access_token的过期时间，秒级别,有效期24小时',
  `refresh_token_expires` bigint(20) DEFAULT NULL COMMENT 'Access_token的过期时间，毫秒级别,有效期24小时',
  `state` int(11) DEFAULT NULL COMMENT '状态 1：正常，2：失效，3：请求失败',
  `result_message` varchar(255) DEFAULT NULL COMMENT '失败原因',
  `create_time` datetime DEFAULT NULL COMMENT '请求时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='京东Access Token表';


INSERT INTO `seller` (`member_id`, `name`, `seller_name`, `seller_logo`, `seller_grade`, `score_service`, `score_deliver_goods`, `score_description`, `product_number`, `collection_number`, `create_time`, `sale_money`, `order_count`, `order_count_over`, `seller_keyword`, `seller_des`, `audit_status`, `store_slide`, `is_self`) VALUES ('2', 'JD', '京东', NULL, '1', '0', '0', '0', '0', '0', '2017-12-27 23:33:24', '0.00', '0', '0', ' ', ' ', '2', NULL, '1');

INSERT INTO `product_brand` (`name`, `name_first`, `image`, `look_method`, `top`, `sort`, `create_id`, `create_time`, `update_id`, `update_time`, `state`) VALUES ('京东', 'J', '/images/brand/5dcd5bc8-5de4-4304-96fe-95c5f2aec311.png', '2', '1', '1', '1', '2017-12-27 23:36:39', '1', '2017-12-27 23:36:46', '2');

alter table product add source int DEFAULT 1 comment '来源 1：平台、2：京东';

alter table product add jdparam LONGTEXT COMMENT 'JD商品参数';

alter table product_cate MODIFY column name varchar(100) NOT NULL COMMENT '分类名称';

-- begin add by li.biao since 2017-12-29 11:29:47
INSERT INTO `product_type` (`id`, `name`, `sort`, `product_norm_ids`, `product_brand_ids`, `create_id`, `create_time`) VALUES ('40', '京东', '1', '', '67', '1', '2017-12-29 11:18:02');
update product_cate set product_type_id = 40 where product_type_id = 0 and type = 2 ;
-- end

ALTER TABLE `portal_active`
MODIFY COLUMN `remark`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '描述' AFTER `update_time`,
ADD COLUMN `description`  varchar(100) NULL COMMENT '简介' AFTER `img`;

-- 平台分类与京东分类关系表
DROP TABLE IF EXISTS `product_cate_jd`;
CREATE TABLE `product_cate_jd` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_cate` int(11) DEFAULT NULL COMMENT '平台分类ID',
  `jd_category_id` int(11) DEFAULT NULL COMMENT '京东分类ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) COMMENT='平台分类与京东分类关系表';

-- 修改平台分类与京东分类关系表字段名称
ALTER TABLE product_cate_jd CHANGE product_cate product_cate_id int(11);
ALTER TABLE product_cate_jd CHANGE jd_category_id jd_category_id int(11);


ALTER TABLE `park`
ADD COLUMN `area`  varchar(2) NULL COMMENT '所属区域' AFTER `city`;
# end
# ------------------------------------------------------------

-- 更新订单表，新增第三方订单号
ALTER TABLE `orders` ADD COLUMN `third_order_sn`  varchar(50) DEFAULT NULL COMMENT '第三方订单号，比如京东订单号、当当订单号等' AFTER `order_sn`;


#---商品缺货登记
CREATE TABLE `product_register` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `product_code` VARCHAR(50) DEFAULT NULL COMMENT '商品编号',
  `product_name` VARCHAR(30) DEFAULT NULL COMMENT '商品名称',
  `product_address` VARCHAR(50) DEFAULT NULL COMMENT '商品地址',
  `phone_number` INT(11) DEFAULT NULL COMMENT '手机号',
  `staff_id` VARCHAR(30) DEFAULT NULL COMMENT '员工编号',
  `staff_name` VARCHAR(20) DEFAULT NULL COMMENT '员工名称',
  `create_time` DATETIME DEFAULT NULL COMMENT '创建时间',
  `update_time` DATETIME DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) COMMENT '商品登记' ;

ALTER TABLE product_register ADD COLUMN product_statu VARCHAR (30) ;

---售后新增字段
alter table member_product_back add `product_back_sn` varchar(30) DEFAULT NULL COMMENT '退货申请单号';

alter table member_product_back add `afsServiceId` int COMMENT 'JD服务单号';

alter table member_product_exchange add `product_exchange_sn` varchar(30) DEFAULT NULL COMMENT '换货申请单号';

alter table member_product_exchange add `afsServiceId` int COMMENT 'JD服务单号';

alter table member_product_back add `source` int(11) DEFAULT '1' COMMENT '来源 1平台 2京东';
alter table member_product_exchange add `source` int(11) DEFAULT '1' COMMENT '来源 1平台 2京东';

--修改产品表字段类型
alter table product modify column description LONGTEXT COMMENT '商品描述信息';

-- begin add by li.biao 会员添加企业认证和员工号字段
alter table member add `is_staff` tinyint COMMENT '是否认证为企业内部员工（0，未认证；1，已认证）';
alter table member add `staff_no` varchar(50) COMMENT '员工工号';

--余额日志表
alter table member_balance_logs add `member_two_Id` int(11) DEFAULT NULL COMMENT '赠送者/接受者id';
alter table member_balance_logs add  `member_two_name` varchar(50) DEFAULT NULL COMMENT '赠送者名称/接受者名称';

alter table portal_active add `author` varchar(20) COMMENT '作者';

CREATE TABLE `member_welfare_send` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`company`  varchar(50) NULL COMMENT '公司名称' ,
`dept`  varchar(50) NULL COMMENT '部门名称' ,
`cost_name`  varchar(50) NULL COMMENT '费用名称' ,
`count_person`  int(11) NULL COMMENT '发放人数' ,
`create_user`  int(11) NULL COMMENT '创建人' ,
`create_time`  datetime NULL COMMENT '创建时间' ,
`send_time`  datetime NULL COMMENT '发送时间' ,
`send_status`  varchar(2) NULL COMMENT '发送状态' ,
PRIMARY KEY (`id`)
)
COMMENT='福利积分发放'
;


CREATE TABLE `member_welfare_send_detail` (
`id`  int(11) NOT NULL AUTO_INCREMENT ,
`welfare_id`  int(11) NOT NULL COMMENT '福利积分发送的id' ,
`staff_no`  varchar(20) NOT NULL COMMENT '员工号' ,
`name`  varchar(20) NOT NULL COMMENT '员工名称' ,
`tel`  varchar(11) NOT NULL COMMENT '员工电话' ,
`money`  int(11) NOT NULL COMMENT '发送额度' ,
`birthday`  varchar(20) NULL COMMENT '出生日期' ,
`start_time`  datetime NULL COMMENT '积分生效期' ,
`end_time`  datetime NULL COMMENT '积分失效期' ,
PRIMARY KEY (`id`)
)
COMMENT='福利积分发放详情'
;


ALTER TABLE `member_welfare_send`
ADD COLUMN `path`  varchar(50) NULL COMMENT '文件路径' AFTER `send_status`;

-- end 

--购物车加积分商品ID
alter table cart add `act_integral_id` int DEFAULT NULL COMMENT '积分商品ID';
alter table cart add `source` int  DEFAULT 1 COMMENT '商品类型 1商城商品 2积分商品';


ALTER TABLE `supplier` ADD COLUMN `remark`  VARCHAR(500) COMMENT '备注信息';




-- 商品库存记录表
DROP TABLE IF EXISTS `product_goods_stock_record`;
        CREATE TABLE product_goods_stock_record(
		id 			INT(11) NOT NULL AUTO_INCREMENT,
		product_goods_id	INT(11) NOT NULL COMMENT '货品id',
		sku 			VARCHAR(50) NOT NULL COMMENT '货品SKU',
		old_stock		INT(11) NOT NULL COMMENT '修改前库存',
		old_stock_warning	INT(11) COMMENT '修改前预警库存',
		old_mall_pc_price	DECIMAL(10,2) NOT NULL COMMENT '修改前商城价',
		old_mal_mobile_price    DECIMAL(10,2) NOT NULL COMMENT '修改前商城价Mobile',
		new_stock		INT(11) NOT NULL COMMENT '修改后库存',
		new_stock_warning	INT(11) COMMENT '修改后预警库存',
		new_mall_pc_price	DECIMAL(10,2) NOT NULL COMMENT '修改后商城价',
		new_mal_mobile_price    DECIMAL(10,2) NOT NULL COMMENT '修改后商城价Mobile',
		update_time		DATETIME	  COMMENT '修改时间',
		update_user_name	VARCHAR(50) NOT NULL COMMENT '修改人',
		PRIMARY KEY (id)
        ) ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='货品库存记录表';


-- 消息类型表   
DROP TABLE IF EXISTS message_type;
CREATE TABLE message_type
(
	id 		INT(11) NOT NULL AUTO_INCREMENT,
	type_name	VARCHAR(20) NOT NULL COMMENT '类型名称',
	type_no		INT(11)  NOT NULL COMMENT '类型编号',
	reception_type 	INT(1) NOT NULL  COMMENT '接收对象 0:所有人 1:用户 2:商户 3:供应商',
	state 		INT(1) DEFAULT 0 NOT NULL COMMENT '状态 0:启用 1:禁用',
	create_user_name VARCHAR(20) 		COMMENT '创建人名称',
	create_time  	DATETIME 		COMMENT '创建时间',
	PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='消息类型表';
        

--  消息主表		
DROP TABLE IF EXISTS message;
CREATE TABLE message 
(
	id 		INT(11) NOT NULL AUTO_INCREMENT,
	title 		VARCHAR(50) NOT NULL 	COMMENT '消息标题',
	content 	TEXT(255) NOT NULL 	COMMENT '消息内容',
	message_code	VARCHAR(10)	 	COMMENT '消息唯一标识',
	state   	INT(1) NOT NULL 	COMMENT '消息发送状态 0:未发送 1:已发送',
	message_type_id	INT(11) NOT NULL 	COMMENT '消息类型id',
	send_id	       	INT(11) 		COMMENT '发送人id',
	send_type      	INT(1) NOT NULL 	COMMENT '发送人类型 0:平台 1:商户 2:供应商',
	is_message_template INT DEFAULT 1 NOT NULL COMMENT '是否是消息模板 0:是  1:否',
	create_time  	DATETIME 		COMMENT '创建时间',
	PRIMARY KEY (id)
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='消息表';

-- 消息记录表
DROP TABLE IF EXISTS message_record;
CREATE TABLE message_record
(
	id 		INT(11) NOT NULL AUTO_INCREMENT,
	message_id	INT(11) NOT NULL          COMMENT '消息id',
	reception_id   	INT(11) NOT NULL 	  COMMENT '接收人id',
	content 	TEXT(255) NOT NULL 	COMMENT '消息内容',
	is_read 	INT(1) DEFAULT 0 NOT NULL COMMENT '是否已读  0:未读 1:已读',
	is_del		INT(1) DEFAULT 0 NOT NULL COMMENT '是否删除  0:正常 1:删除',
	create_time  	DATETIME  		COMMENT '发送时间',
	PRIMARY KEY (id)
	
)ENGINE=INNODB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='消息发送记录表';


--插入消息类型 数据
INSERT INTO `message_type` (`id`, `type_name`, `type_no`, `reception_type`, `state`, `create_user_name`, `create_time`) VALUES('1','订单消息','10001','1','0','admin','2018-01-17 11:18:46');
INSERT INTO `message_type` (`id`, `type_name`, `type_no`, `reception_type`, `state`, `create_user_name`, `create_time`) VALUES('2','服务消息','1002','1','0','admin','2018-01-18 16:47:54');
INSERT INTO `message_type` (`id`, `type_name`, `type_no`, `reception_type`, `state`, `create_user_name`, `create_time`) VALUES('3','系统消息','10002','1','0','admin','2018-01-19 10:05:02');

--插入消息模板 数据
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('1','订单发货通知','尊敬的会员，你于${time}在海核云谷电商商城购买的商品已发货，订单号：${orderSn}。','DDFHTZ','0','3','1','0','2018-01-19 11:34:17','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('2','订单取消通知','尊敬的用户，您于${startTime}在海核云谷电商商城购买的商品已经取消，订单号：${orderSn}，取消日期：${endTime}。','DDQXTZ','0','1','1','0','2018-01-19 16:39:39','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('3','缺货商品审核通过','尊敬的${memberName}用户 ，您提交的缺货商品名为<font color=\'red\'>${productName}</font>商品编号为：\r\n<font color=\'red\'>${productCode}</font>的商品捕获成功，您现在可对商品进行直接购买，感谢您对我们平台的支持！\r\n商品购买链接:<a target=\"_blank\" href=\"${productAddress}\">${productAddress}</a>','QHSPSHTG','0','2','1','0','2018-01-22 11:13:24','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('4','缺货商品审核失败','尊敬的${memberName}用户 您提交的缺货商品名为<font color=\'red\'>${productName}</font>商品编号为：\r\n<font color=\'red\'>${productCode}</font>的商品登记打回。 打回原因:${retroactionReason}</br>感谢您对我们平台的支持','QHSPSHSB','0','2','1','0','2018-01-22 11:14:50','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('5','福利积分到账通知','尊敬的海核云谷用户，工号${staffNo},姓名${name},您的${costName}${money}积分已经到账，请到个人中心查阅','FLJFFF','1','3','1','0','2018-01-26 15:00:33','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('6','福利积分短信模板','尊敬的海核云谷用户，工号${staffNo}，姓名${name}，您的${costName}${money}积分已经到账，请登录海核云谷查收，网站:www.hhyungu.com。由于您之前没有注册账户，系统已经帮你默认设置了账户，账户名: ${username},初始密码: ${password}','FLJFFFDX','0','3','1','0','2018-01-26 16:13:59','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('7','订单提交通知','尊敬的会员，你于${time}在海核云谷电商商城购买的商品下单成功，我们正努力给您备货中，感谢您的支持！','DDTJTZ','0','1','1','0','2018-01-30 10:15:48','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('8','订单签收通知','尊敬的会员，你于${time}在海核云谷电商商城购买的商品已签收，订单号：${orderSn}。','DDQSTZ','0','1','1','0','2018-01-30 11:46:35','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('9','退款申请通知','尊敬的会员，您于${time}发起订单：${orderSn}退款申请。','TKSQTZ','0','1','1','0','2018-01-30 14:42:22','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('10','退款成功通知','尊敬的会员，您于${time}发起订单：${orderSn}退款申请通过审核，款项将会在${day}个工作日原路返回你的账户。','TKCGTZ','0','1','1','0','2018-01-30 14:45:24','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('11','退款失败通知','尊敬的会员，您于${time}发起订单：${orderSn}退款申请失败，如有疑问，可直接联系平台客服或商家。','TKSBTZ','0','1','1','0','2018-01-30 14:46:58','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('12','换货申请通知','尊敬的会员，您于${time}发起订单：${orderSn}换货申请。','HHSQTZ','0','1','1','0','2018-01-30 14:48:32','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('13','换货成功通知','尊敬的会员，您于${time}发起订单：${orderSn}换货申请通过审核，如有疑问，可直接联系平台客服或商家。','HHCGTZ','0','1','1','0','2018-01-30 14:49:53','0');
INSERT INTO `message` (`id`, `title`, `content`, `message_code`, `state`, `message_type_id`, `send_id`, `send_type`, `create_time`, `is_message_template`) VALUES('14','换货失败通知','尊敬的会员，您于${time}发起订单：${orderSn}换货申请失败，如有疑问，可直接联系平台客服或商家。','HHSBTZ','0','1','1','0','2018-01-30 14:51:33','0');




-- 商品表加入福利商品字段 和发票税率
ALTER TABLE product ADD is_welfare_product  TINYINT (2) DEFAULT 1 NOT NULL COMMENT '是否加入福利商品 1:不加入 2:加入'
ALTER TABLE product ADD invoice_rate	DECIMAL(2,2) NOT NULL COMMENT '发票税率'

-- 订单表加入 是否福利订单
ALTER TABLE orders ADD is_welfare_order TINYINT(2) DEFAULT 1 NOT NULL COMMENT '是否福利订单  1:不是  2:是'

--  消息表 是否加入福利商品
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('INVOICE_RATE','17','17%','0','1','1','admin','2018-01-23 16:03:42',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('INVOICE_RATE','3','3%','0','1','1','admin','2018-01-23 16:03:30',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('ISREAD','1','已读','0','1','1','admin','2018-01-19 15:20:36','1','admin','2018-01-19 15:22:11');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('ISREAD','0','未读','0','1','1','admin','2018-01-19 15:20:29',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('IS_WELFARE_PRODUCT','2','福利商品','2','1','1','admin','2018-01-23 11:59:36','1','admin','2018-01-23 14:57:47');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('IS_WELFARE_PRODUCT','1','非福利商品','1','1','1','admin','2018-01-23 11:59:25','1','admin','2018-01-23 14:57:36');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('SEND_STATE','1','已发送','0','1','1','admin','2018-01-17 10:19:28',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('SEND_STATE','0','未发送','0','1','1','admin','2018-01-17 10:19:18',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('MESSAGE_IS_NORM','1','禁用','0','1','1','admin','2018-01-16 20:20:08','1','admin','2018-01-16 20:22:46');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('MESSAGE_IS_NORM','0','启用','0','1','1','admin','2018-01-16 20:19:52',NULL,NULL,NULL);
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('RECEPTION_TYPE','3','供应商','3','1','1','admin','2018-01-16 20:02:22','1','admin','2018-01-16 20:04:20');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('RECEPTION_TYPE','2','商户','2','1','1','admin','2018-01-16 20:02:11','1','admin','2018-01-16 20:04:12');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('RECEPTION_TYPE','1','会员','1','1','1','admin','2018-01-16 20:02:00','1','admin','2018-01-16 20:04:06');
INSERT INTO `code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES('RECEPTION_TYPE','0','所有人','0','1','1','admin','2018-01-16 20:01:35','1','admin','2018-01-16 20:04:00');

INSERT INTO `hhyg_shop`.`code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES ('DEVLIVERY_STATE', '1', '未发货', '1', '1', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `hhyg_shop`.`code_master` (`code_div`, `code_cd`, `code_text`, `sort_order`, `use_yn`, `create_user_id`, `create_user`, `create_time`, `update_user_id`, `update_user`, `update_time`) VALUES ('DEVLIVERY_STATE', '2', '已发货', '2', '1', NULL, NULL, NULL, NULL, NULL, NULL);

INSERT INTO `code_master` VALUES ('EMAIL_CODE', '1', '1771452241@qq.com,m15972014239@163.com', 1, 1, 1, 'admin', '2018-1-31 18:49:09', NULL, NULL, NULL);
INSERT INTO `code_master` VALUES ('EMAIL_CODE', '2', '/data/upload', 0, 1, 1, 'admin', '2018-2-4 15:59:22', 1, 'admin', '2018-2-4 15:59:34');
INSERT INTO `code_master` VALUES ('EMAIL_CODE', '3', '/data/down', 4, 1, 1, 'admin', '2018-2-4 16:00:06', NULL, NULL, NULL);

ALTER TABLE `park_advantage`
MODIFY COLUMN `remark`  mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注' AFTER `update_time`;

---商品缺货登记
CREATE TABLE `product_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_code` varchar(50) DEFAULT NULL COMMENT '商品编号',
  `product_name` varchar(30) DEFAULT NULL COMMENT '商品名称',
  `product_address` varchar(50) DEFAULT NULL COMMENT '商品地址',
  `phone_number` varchar(20) DEFAULT NULL COMMENT '手机号',
  `staff_id` varchar(30) DEFAULT NULL COMMENT '员工编号',
  `staff_name` varchar(20) DEFAULT NULL COMMENT '员工名称',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `PRODUCT_REGI_STAT` varchar(10) DEFAULT NULL COMMENT '处理结果',
  `retroaction_reason` varchar(10) DEFAULT NULL COMMENT '反馈原因',
  `member_id` varchar(30) DEFAULT NULL COMMENT '会员编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8 COMMENT='商品登记';


ALTER TABLE product_register MODIFY retroaction_reason VARCHAR(300);


---新增的商家投诉登记记录
DROP TABLE IF EXISTS `complain_register`;
CREATE TABLE `complain_register` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `complain_seller` varchar(30) DEFAULT NULL COMMENT '投诉商家',
  `complain_type` varchar(20) DEFAULT NULL COMMENT '投诉类型 1-商品质量，2-物流配送，3-服务态度，4-商家欺诈，5-其他',
  `complain_person` varchar(20) DEFAULT NULL COMMENT '投诉用户名称',
  `complain_time` datetime DEFAULT NULL COMMENT '投诉时间',
  `create_person` varchar(20) DEFAULT NULL COMMENT '创建人',
  `update_time` varchar(20) DEFAULT NULL COMMENT '更新时间',
  `desceinfo` longtext NOT NULL COMMENT '详情',
  `seller_id` int(11) NOT NULL COMMENT '商家id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='投诉登记表';

---淘汰机制参数
CREATE TABLE `seller_eliminate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kind_type` int(11) DEFAULT NULL COMMENT '淘汰因素 1-完成订单量，2-综合评分，3-投诉，4-举报数量',
  `tip_value` int(11) DEFAULT NULL COMMENT '提示值',
  `warn_value` int(11) DEFAULT NULL COMMENT '警告值',
  `eliminate_value` int(11) DEFAULT NULL COMMENT '淘汰值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='淘汰机制表';

---商家表
CREATE TABLE `seller` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) NOT NULL COMMENT '用户ID',
  `name` varchar(50) NOT NULL COMMENT '用户名',
  `seller_name` varchar(200) NOT NULL COMMENT '店铺名称',
  `seller_logo` varchar(255) DEFAULT NULL COMMENT '店铺logo',
  `seller_grade` int(11) NOT NULL COMMENT '店铺等级',
  `score_service` varchar(20) NOT NULL COMMENT '店铺评分服务',
  `score_deliver_goods` varchar(20) NOT NULL COMMENT '店铺评分发货',
  `score_description` varchar(20) NOT NULL COMMENT '店铺评分描述',
  `product_number` int(11) NOT NULL COMMENT '商品数量',
  `collection_number` int(11) NOT NULL COMMENT '店铺收藏',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `sale_money` decimal(10,2) NOT NULL COMMENT '店铺总销售金额',
  `order_count` int(11) NOT NULL COMMENT '店铺总订单量',
  `order_count_over` int(11) NOT NULL COMMENT '店铺完成订单量',
  `seller_keyword` varchar(255) NOT NULL COMMENT 'SEO关键字',
  `seller_des` varchar(255) NOT NULL COMMENT 'SEO店铺描述',
  `audit_status` tinyint(4) NOT NULL COMMENT '审核状态 1、待审核；2、审核通过；3、冻结',
  `store_slide` text COMMENT '店铺轮播图 ',
  `is_self` tinyint(4) NOT NULL DEFAULT '0' COMMENT '是否自营，0：不是，1：是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 COMMENT='商家表';

ALTER TABLE seller ADD report_count INT DEFAULT 0 COMMENT '举报数量';
ALTER TABLE seller ADD compain_count INT DEFAULT 0 COMMENT '投诉数量';
ALTER TABLE seller ADD compark_mark VARCHAR(10) DEFAULT 5.0 COMMENT '综合评分';
ALTER TABLE seller ADD business_state INT DEFAULT 0 COMMENT '状态';


--退款批次
CREATE TABLE `job_product_back` (
`id`  int NOT NULL AUTO_INCREMENT ,
`pc`  varchar(20) NOT NULL COMMENT '批次' ,
`create_time`  datetime NOT NULL COMMENT '创建时间' ,
`count_product_back`  int NOT NULL COMMENT '退货总数量' ,
`count_money`  float NOT NULL COMMENT '总退款金额' ,
`status`  varchar(2)  NULL COMMENT '退款状态 1，已发送，2，部分退款，3，全部退款' ,
`finish_time`  datetime NULL COMMENT '完成时间' ,
PRIMARY KEY (`id`)
)
COMMENT='退款批次'
;
ALTER TABLE `job_product_back`
MODIFY COLUMN `count_money`  numeric(10,2) NOT NULL COMMENT '总退款金额' AFTER `count_product_back`;

ALTER TABLE `member_product_back`
ADD COLUMN `pc`  varchar(20) NULL COMMENT '批次' AFTER `source`;

ALTER TABLE `member_welfare_send_detail`
MODIFY COLUMN `start_time`  varchar(30) NULL DEFAULT NULL COMMENT '积分生效期' AFTER `birthday`,
MODIFY COLUMN `end_time`  varchar(30) NULL DEFAULT NULL COMMENT '积分失效期' AFTER `start_time`;

ALTER TABLE `portal_menu`
MODIFY COLUMN `url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL AFTER `code`;

ALTER TABLE `index_banner`
MODIFY COLUMN `url`  varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'url' AFTER `type`;

alter table portal_service
add COLUMN url varchar(100) null COMMENT '跳转url' after update_time;


-- 订单商品表  增加字段 福利订单 使用余额数量
ALTER TABLE orders_product ADD act_money DECIMAL(10,2) DEFAULT 0.00 COMMENT '福利订单 使用余额数量 ';

alter table shop_active MODIFY COLUMN img VARCHAR(200);

ALTER TABLE `product`
ADD COLUMN `down_time`  datetime NULL COMMENT '下架时间' AFTER `invoice_rate`;

ALTER TABLE `product_comments`
MODIFY COLUMN `content`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评价内容' AFTER `grade`;

--商家统计模块添加发票状态字段
ALTER TABLE settlement ADD invoice_status INT DEFAULT 0 COMMENT '发票状态';

/*保存福利积分上传的公司和部门*/
CREATE TABLE `welfare_company` (
`id`  int(11) NOT NULL AUTO_INCREMENT,
`company`  varchar(255) NULL COMMENT '公司' ,
`dept`  varchar(255) NULL COMMENT '部门' ,
`create_time`  datetime NULL COMMENT '创建时间' ,
PRIMARY KEY (`id`)
)
;

/*福利积分明指定商家(可有可无)*/
ALTER TABLE `member_welfare_send`
ADD COLUMN `seller_id`  int(11) NULL COMMENT '商家id' AFTER `path`;

/*福利积分明细冗余商家*/
ALTER TABLE `member_welfare_send_detail`
ADD COLUMN `seller_name`  varchar(30) NULL COMMENT '冗余商家名称' AFTER `end_time`;


/*专项积分对应表*/
CREATE TABLE `member_special_integral` (
`id`  int(11) NOT NULL AUTO_INCREMENT,
`member_id`  int(11) NOT NULL COMMENT '会员id' ,
`seller_id`  int(11) NOT NULL COMMENT '商家id' ,
`value`  int(11) NOT NULL COMMENT '专项积分值' ,
`start_time`  datetime NULL COMMENT '积分开始时间' ,
`end_time`  datetime  NULL COMMENT '积分结束时间' ,
`create_time`  datetime NULL ,
`update_time`  datetime NULL ,
PRIMARY KEY (`id`)
)
COMMENT='专项积分对应商家表'
;
INSERT INTO `code_master` VALUES
('INTEGRAL_TYPE', '1', '通用积分', 0, 1, 1, 'admin', '2018-3-6 11:09:56', NULL, NULL, NULL);
INSERT INTO `code_master` VALUES
('INTEGRAL_TYPE', '2', '专项积分', 2, 1, 1, 'admin', '2018-3-6 11:10:31', NULL, NULL, NULL);

ALTER TABLE `seller`
MODIFY COLUMN `subject_id`  int(11) NULL COMMENT '结算主体ID ' AFTER `is_contributing`;

create table sku_sequence(
	sku int COMMENT 'SKU 序列 '
);

insert into sku_sequence (sku) values (1000000);

INSERT INTO `hhyg_shop`.`system_resources` (`id`, `pid`, `url`, `content`, `create_time`, `type`, `status`, `scope`, `res_id`, `res_icon`) VALUES ('836', '528', '/admin/promotion/unitactintegral,/admin/promotion/unitactintegral/list,/admin/promotion/unitactintegral/print,/admin/promotion/unitactintegral/detailist,/admin/promotion/unitactintegral/importlist,/admin/promotion/unitactintegral/impordetaillist', '单位积分消费', '2018-03-15 21:21:09', '2', '1', '1', NULL, NULL);
INSERT INTO `hhyg_shop`.`system_resources` (`id`, `pid`, `url`, `content`, `create_time`, `type`, `status`, `scope`, `res_id`, `res_icon`) VALUES ('837', '836', '/admin/promotion/unitactintegral/list', '查询', '2018-03-15 21:21:38', '2', '1', '1', NULL, NULL);
INSERT INTO `hhyg_shop`.`system_resources` (`id`, `pid`, `url`, `content`, `create_time`, `type`, `status`, `scope`, `res_id`, `res_icon`) VALUES ('838', '836', '/admin/promotion/unitintegral/print', '导出', '2018-03-15 21:22:28', '2', '1', '1', NULL, NULL);
INSERT INTO `hhyg_shop`.`system_resources` (`id`, `pid`, `url`, `content`, `create_time`, `type`, `status`, `scope`, `res_id`, `res_icon`) VALUES ('839', '836', '/admin/promotion/unitintegral/exportDetail', '导出明细', '2018-03-16 11:19:33', '2', '1', '1', NULL, NULL);

