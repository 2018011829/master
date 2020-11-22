/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50701
Source Host           : localhost:3306
Source Database       : tiantian

Target Server Type    : MYSQL
Target Server Version : 50701
File Encoding         : 65001

Date: 2020-11-22 08:18:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `child`
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL DEFAULT 'child',
  `grade` smallint(6) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of child
-- ----------------------------

-- ----------------------------
-- Table structure for `parents`
-- ----------------------------
DROP TABLE IF EXISTS `parents`;
CREATE TABLE `parents` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` char(11) NOT NULL,
  `password` varchar(15) DEFAULT NULL,
  `nickname` varchar(20) NOT NULL DEFAULT 'tiantian8808',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of parents
-- ----------------------------
INSERT INTO `parents` VALUES ('1', '19831127142', 'li0816', 'tiantian8808');
INSERT INTO `parents` VALUES ('2', '15091826027', 'lg0816', 'lg');

-- ----------------------------
-- Table structure for `pcrelation`
-- ----------------------------
DROP TABLE IF EXISTS `pcrelation`;
CREATE TABLE `pcrelation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `childId` int(11) NOT NULL,
  `parentId` int(11) NOT NULL,
  `relationId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of pcrelation
-- ----------------------------

-- ----------------------------
-- Table structure for `relation`
-- ----------------------------
DROP TABLE IF EXISTS `relation`;
CREATE TABLE `relation` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of relation
-- ----------------------------
