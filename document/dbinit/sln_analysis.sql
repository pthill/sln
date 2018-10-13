# ************************************************************
# Sequel Pro SQL dump
# Version 4499
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 123.56.164.182 (MySQL 5.5.46)
# Database: sln_analysis
# Generation Time: 2016-11-25 08:13:51 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table browse_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `browse_log`;

CREATE TABLE `browse_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `site_cookie` varchar(50) NOT NULL DEFAULT '' COMMENT 'cookie埋点',
  `session_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'session标识',
  `user_agent` text NOT NULL COMMENT '客户端详细信息',
  `ip_address` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `accessed_page` varchar(200) NOT NULL DEFAULT '' COMMENT '访问的URL',
  `url_referer` varchar(200) NOT NULL DEFAULT '' COMMENT '前一个URL',
  `create_time` datetime NOT NULL COMMENT '访问时间',
  `browse_name` varchar(200) NOT NULL DEFAULT '' COMMENT '浏览器名称',
  `browser_version` varchar(200) NOT NULL DEFAULT '' COMMENT '浏览器版本',
  `member_id` int(11) NOT NULL COMMENT '用户ID，用户没有登录ID为0',
  `ebi` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table browse_log_mobile
# ------------------------------------------------------------

DROP TABLE IF EXISTS `browse_log_mobile`;

CREATE TABLE `browse_log_mobile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `site_cookie` varchar(50) NOT NULL DEFAULT '' COMMENT 'cookie埋点',
  `session_id` varchar(50) NOT NULL DEFAULT '' COMMENT 'session标识',
  `user_agent` text NOT NULL COMMENT '客户端详细信息',
  `ip_address` varchar(50) NOT NULL DEFAULT '' COMMENT 'IP地址',
  `accessed_page` varchar(200) NOT NULL DEFAULT '' COMMENT '访问的URL',
  `url_referer` varchar(200) NOT NULL DEFAULT '' COMMENT '前一个URL',
  `create_time` datetime NOT NULL COMMENT '访问时间',
  `browse_name` varchar(200) NOT NULL DEFAULT '' COMMENT '浏览器名称',
  `browser_version` varchar(200) NOT NULL DEFAULT '' COMMENT '浏览器版本',
  `member_id` int(11) NOT NULL COMMENT '用户ID，用户没有登录ID为0',
  `ebi` varchar(200) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



# Dump of table product_look_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `product_look_log`;

CREATE TABLE `product_look_log` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `site_cookie` varchar(50) NOT NULL DEFAULT '' COMMENT 'cookie埋点',
  `member_id` int(11) NOT NULL COMMENT '用户ID，没有登录ID为0',
  `product_id` int(11) NOT NULL COMMENT '商品ID',
  `create_time` varchar(20) NOT NULL DEFAULT '' COMMENT '访问时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
