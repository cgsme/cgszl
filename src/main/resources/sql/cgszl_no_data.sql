/*
SQLyog 企业版 - MySQL GUI v8.14 
MySQL - 5.5.56 : Database - cgszl
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cgszl` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cgszl`;

/*Table structure for table `t_articles` */

DROP TABLE IF EXISTS `t_articles`;

CREATE TABLE `t_articles` (
  `aid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '文章标识',
  `title` varchar(200) DEFAULT NULL COMMENT '文章标题',
  `slug` varchar(200) DEFAULT NULL COMMENT '缩略名（自定义访问路径）',
  `created` int(10) unsigned DEFAULT '0' COMMENT '创建时间',
  `modified` int(10) unsigned DEFAULT '0' COMMENT '最后修改时间',
  `content` longtext COMMENT '文章正文',
  `author_id` int(10) unsigned DEFAULT '0' COMMENT '作者标识',
  `type` varchar(16) DEFAULT 'post' COMMENT '文章类型',
  `status` varchar(16) DEFAULT NULL COMMENT '状态、默认已发布',
  `tags` varchar(200) DEFAULT NULL COMMENT '标签',
  `categories` varchar(200) DEFAULT NULL COMMENT '分类',
  `hits` int(10) unsigned DEFAULT '0' COMMENT '点击数',
  `comments_num` int(10) unsigned DEFAULT '0' COMMENT '评论数',
  `allow_comment` tinyint(1) DEFAULT '1' COMMENT '是否允许评论',
  `allow_ping` tinyint(1) DEFAULT '1' COMMENT '是否允许ping',
  `allow_feed` tinyint(1) DEFAULT '1' COMMENT '是否允许聚合',
  `like_num` int(10) unsigned DEFAULT '0' COMMENT '点赞数',
  `unlike_num` int(10) unsigned DEFAULT NULL COMMENT '反对数',
  `draft` tinyint(1) DEFAULT '0' COMMENT '是否草稿，默认不是',
  `delete_flag` tinyint(1) DEFAULT '0' COMMENT '是否删除（垃圾箱），默认未删除',
  PRIMARY KEY (`aid`),
  UNIQUE KEY `slug` (`slug`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

/*Table structure for table `t_attach` */

DROP TABLE IF EXISTS `t_attach`;

CREATE TABLE `t_attach` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '附件标识',
  `fname` varchar(100) NOT NULL DEFAULT '' COMMENT '附件名',
  `ftype` varchar(50) DEFAULT '' COMMENT '附件类型',
  `fkey` varchar(100) NOT NULL DEFAULT '' COMMENT '附件key',
  `author_id` int(10) DEFAULT NULL COMMENT '附件所有者标识',
  `created` int(10) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=122 DEFAULT CHARSET=utf8;

/*Table structure for table `t_comments` */

DROP TABLE IF EXISTS `t_comments`;

CREATE TABLE `t_comments` (
  `coid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '评论标识',
  `aid` int(10) unsigned DEFAULT '0' COMMENT '所属文章标识',
  `created` int(10) unsigned DEFAULT '0' COMMENT '创建时间',
  `author` varchar(200) DEFAULT NULL COMMENT '评论作者',
  `author_id` int(10) unsigned DEFAULT '0' COMMENT '评论所属用户标识',
  `owner_id` int(10) unsigned DEFAULT '0' COMMENT '评论所属内容作者id(文章作者id)',
  `mail` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `url` varchar(200) DEFAULT NULL COMMENT '网址',
  `ip` varchar(64) DEFAULT NULL COMMENT '评论者ip地址',
  `agent` varchar(200) DEFAULT NULL COMMENT '评论者客户端',
  `content` text COMMENT '评论内容',
  `type` varchar(16) DEFAULT 'comment' COMMENT '评论类型',
  `status` varchar(16) DEFAULT 'approved' COMMENT '评论状态(默认批准)',
  `parent` int(10) unsigned DEFAULT '0' COMMENT '父级评论',
  PRIMARY KEY (`coid`),
  KEY `cid` (`aid`),
  KEY `created` (`created`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Table structure for table `t_logs` */

DROP TABLE IF EXISTS `t_logs`;

CREATE TABLE `t_logs` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '日志标识',
  `action` varchar(100) DEFAULT NULL COMMENT '操作类型',
  `data` varchar(2000) DEFAULT NULL COMMENT '操作数据',
  `oper_user_id` int(10) DEFAULT NULL COMMENT '操作人员标识',
  `ip` varchar(20) DEFAULT NULL COMMENT '用户ip',
  `created` varchar(20) DEFAULT NULL COMMENT '日志创建时间',
  `result` tinyint(1) DEFAULT NULL COMMENT '操作结果',
  `process_time` int(11) DEFAULT NULL COMMENT '执行时间',
  `result_cn` varchar(20) DEFAULT NULL COMMENT '操作结果（中文）',
  `oper_user_name` varchar(20) DEFAULT NULL COMMENT '操作人员名称',
  `module` varchar(20) DEFAULT NULL COMMENT '操作模块',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=375 DEFAULT CHARSET=utf8;

/*Table structure for table `t_metas` */

DROP TABLE IF EXISTS `t_metas`;

CREATE TABLE `t_metas` (
  `mid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '类别（标签）标识',
  `name` varchar(200) DEFAULT NULL COMMENT '类别（标签）名称',
  `slug` varchar(200) DEFAULT NULL COMMENT '类别（标签）缩略名',
  `type` varchar(32) NOT NULL DEFAULT '' COMMENT '类别（标签）类型：分类、标签',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  `sort` int(10) unsigned DEFAULT '0' COMMENT '排序',
  `parent` int(10) unsigned DEFAULT '0' COMMENT '所属上级标识（父分类、父标签）',
  PRIMARY KEY (`mid`),
  KEY `slug` (`slug`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8;

/*Table structure for table `t_options` */

DROP TABLE IF EXISTS `t_options`;

CREATE TABLE `t_options` (
  `name` varchar(32) NOT NULL DEFAULT '' COMMENT '选项名（key）',
  `value` varchar(1000) DEFAULT '' COMMENT '值(value)',
  `description` varchar(200) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_relationships` */

DROP TABLE IF EXISTS `t_relationships`;

CREATE TABLE `t_relationships` (
  `aid` int(10) unsigned NOT NULL COMMENT '文章标识',
  `mid` int(10) unsigned NOT NULL COMMENT '分类标识（分类、标签）',
  PRIMARY KEY (`aid`,`mid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_userinfo` */

DROP TABLE IF EXISTS `t_userinfo`;

CREATE TABLE `t_userinfo` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) unsigned NOT NULL COMMENT '用户id',
  `tec_hobby` varchar(200) DEFAULT NULL COMMENT '技术性趣',
  `qq` varchar(50) DEFAULT NULL COMMENT 'qq账号',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `sex` varchar(10) DEFAULT '男' COMMENT '性别',
  `post` varchar(100) DEFAULT NULL COMMENT '职位',
  `address` varchar(100) DEFAULT NULL COMMENT '住址',
  `merry` varchar(10) DEFAULT '单身狗' COMMENT '婚姻状况',
  `gitHub` varchar(255) DEFAULT NULL COMMENT 'GitHub账号',
  `maxim` varchar(255) DEFAULT NULL COMMENT '座右铭',
  `introduction` varchar(255) DEFAULT '这个人很懒，什么都没留下' COMMENT '自我介绍',
  PRIMARY KEY (`id`),
  KEY `t_userinfo_t_users_uid_fk` (`uid`),
  CONSTRAINT `t_userinfo_t_users_uid_fk` FOREIGN KEY (`uid`) REFERENCES `t_users` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户详细信息';

/*Table structure for table `t_users` */

DROP TABLE IF EXISTS `t_users`;

CREATE TABLE `t_users` (
  `uid` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户标识',
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `email` varchar(200) DEFAULT NULL COMMENT '邮箱',
  `home_url` varchar(200) DEFAULT NULL COMMENT '主页地址',
  `screen_name` varchar(32) DEFAULT NULL COMMENT '页面显示名称',
  `created` int(10) unsigned DEFAULT '0' COMMENT '创建时间',
  `activated` int(10) unsigned DEFAULT '0' COMMENT '最后活动始时间',
  `logged` int(10) unsigned DEFAULT '0' COMMENT '最后登录时间',
  `group_name` varchar(16) DEFAULT 'visitor' COMMENT '用户组',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `name` (`username`),
  UNIQUE KEY `mail` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
