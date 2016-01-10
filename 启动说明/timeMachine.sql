# CREATE DATABASE jfinal_demo DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
# IDEA不支持一键执行，所以先手动创建数据库再执行sql语句
USE timeMachine;

#博客表  demo
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(200) NOT NULL,
  `content` mediumtext NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


INSERT INTO `blog` VALUES ('1', 'JFinal Demo Title here', 'JFinal Demo Content here');
INSERT INTO `blog` VALUES ('2', 'test 1', 'test 1');
INSERT INTO `blog` VALUES ('3', 'test 2', 'test 2');
INSERT INTO `blog` VALUES ('4', 'test 3', 'test 3');
INSERT INTO `blog` VALUES ('5', 'test 4', 'test 4');

#用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `email_verify` int(1) DEFAULT '0',
  `head_photo` varchar(100) DEFAULT NULL,
  `sex` int(1) DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  `signature` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `liveness` int(11) DEFAULT '0',
  `contribution` int(11) DEFAULT '0',
  `authority` int(1) DEFAULT '0',
  `del_status` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#第三方登录表
DROP TABLE IF EXISTS `wb_login`;
CREATE TABLE `wb_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(64) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `nickname` varchar(64) DEFAULT NULL,
  `head_photo` varchar(128) DEFAULT NULL,
  `type` varchar(64) DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#用户表
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(50) DEFAULT NULL,
  `password` varchar(64) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `email_verify` int(1) DEFAULT '0',
  `head_photo` varchar(100) DEFAULT NULL,
  `sex` int(1) DEFAULT '0',
  `birthday` date DEFAULT NULL,
  `status` int(1) DEFAULT '0',
  `last_login_time` datetime DEFAULT NULL,
  `signature` varchar(100) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL,
  `liveness` int(11) DEFAULT '0',
  `contribution` int(11) DEFAULT '0',
  `authority` int(1) DEFAULT '0',
  `del_status` int(1) DEFAULT '0',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;