/*
Navicat MySQL Data Transfer

DataSet Server         : localhost
DataSet Server Version : 50717
DataSet Host           : localhost:3306
DataSet Database       : optocon

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-09-27 11:24:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `adic`
-- ----------------------------
DROP TABLE IF EXISTS `adic`;
CREATE TABLE `adic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `a` (`a`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of adic
-- ----------------------------

-- ----------------------------
-- Table structure for `child`
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `a` varchar(20) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pid` (`pid`),
  CONSTRAINT `child_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `parent` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child
-- ----------------------------
INSERT INTO `child` VALUES ('1', '22', '1');
INSERT INTO `child` VALUES ('2', '223', '1');
INSERT INTO `child` VALUES ('3', 'wegweg', '2');

-- ----------------------------
-- Table structure for `parent`
-- ----------------------------
DROP TABLE IF EXISTS `parent`;
CREATE TABLE `parent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of parent
-- ----------------------------
INSERT INTO `parent` VALUES ('1', 'aa');
INSERT INTO `parent` VALUES ('2', '3334');

-- ----------------------------
-- Table structure for `test`
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `createtime` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of test
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `realname` varchar(20) DEFAULT NULL COMMENT '真实姓名',
  `phone` varchar(11) DEFAULT NULL COMMENT '电话号码',
  `deleted` int(11) NOT NULL DEFAULT '0',
  `version` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=132 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'xxx', '13974551144', '0', '5');
INSERT INTO `user` VALUES ('6', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '1', '0');
INSERT INTO `user` VALUES ('7', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('8', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('9', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('10', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('11', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('12', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('13', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('14', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('15', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('16', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('17', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('18', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('19', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('20', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('21', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('22', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('23', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('24', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('25', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('26', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('27', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('28', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('29', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('30', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('31', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('32', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('33', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('34', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('35', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('36', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('37', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('38', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('39', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('40', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('41', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('42', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('43', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('44', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('45', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('46', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('47', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('48', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('49', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('50', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('51', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('52', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('53', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('54', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('55', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('56', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('57', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('58', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('59', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('60', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('61', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('62', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('63', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('64', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('65', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('66', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('67', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('68', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('69', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('70', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('71', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('72', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('73', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('74', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('75', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('76', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('77', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('78', 'zqw27', '49217377-2a6b-497b-b3c8-b41d1f67a0a5', '221', '121444', '0', null);
INSERT INTO `user` VALUES ('79', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('80', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('81', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('82', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('83', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('84', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('85', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('86', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('87', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('88', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('89', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('90', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('91', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('92', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('93', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('94', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('95', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('96', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('97', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('98', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('99', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('100', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('102', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('103', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('104', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('105', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('106', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('107', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('108', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('109', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('110', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('111', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('112', 'zqw82', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('113', 'admin', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('114', 'admin11', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('115', 'admin8', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('116', 'admin9', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('117', 'admin15', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('118', 'admin7', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('119', 'admin4', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('120', 'admin14', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('121', 'admin9', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('122', 'admin0', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('123', 'admin15', '0ad569ea-5387-438c-a7b2-763bcd142518', 'xxx', '121444', '0', null);
INSERT INTO `user` VALUES ('124', 'zqw9', '6b4c5ddc-68d3-4297-8efb-e2460e0d646c', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('125', 'zqw71', 'a66ca7d5-59e7-4e7a-bc68-3433f578bd7e', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('126', 'zqw93', '72738c09-6edc-449c-b94b-27455029ed43', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('127', 'zqw66', 'ad9dd7f8-ff71-4806-badb-3b95a6ce4e12', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('128', 'zqw18', '54bd0f74-352d-489f-9502-7175e487fae3', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('129', 'zqw30', '6270a6a4-ebe7-4ea3-a7de-1a336379efaa', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('130', 'zqw50', '68e6e65e-fc09-493c-a59a-ad05ea7e76da', '221', '13654784578', '0', null);
INSERT INTO `user` VALUES ('131', 'zqw39', 'c28cfaf4-7131-4529-9ef1-1a3ded6ec86a', '221', '13654784578', '0', null);

-- ----------------------------
-- View structure for `v1`
-- ----------------------------
DROP VIEW IF EXISTS `v1`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v1` AS select `parent`.`id` AS `id`,`parent`.`name` AS `name` from `parent` ;
