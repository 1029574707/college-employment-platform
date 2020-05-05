/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3307
Source Database       : college-employment-platform

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-05-05 23:29:43
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `collegeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------
INSERT INTO `class` VALUES ('1', '三班', '1');

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '人文学院');
INSERT INTO `college` VALUES ('2', '物联网学院');
INSERT INTO `college` VALUES ('3', '机械学院');

-- ----------------------------
-- Table structure for `evaluation`
-- ----------------------------
DROP TABLE IF EXISTS `evaluation`;
CREATE TABLE `evaluation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `content` varchar(2048) NOT NULL,
  `type` int(11) NOT NULL,
  `associateId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluation
-- ----------------------------
INSERT INTO `evaluation` VALUES ('1', '2020-05-05 11:30:42', null, 'henhao', '1', '1');
INSERT INTO `evaluation` VALUES ('2', '2020-05-05 11:39:29', null, 'bucuo', '2', '1');

-- ----------------------------
-- Table structure for `job_info`
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tripartite` tinyint(4) NOT NULL DEFAULT '0',
  `contactName` varchar(255) NOT NULL,
  `contactPhone` varchar(255) NOT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `note` varchar(255) DEFAULT NULL,
  `studentId` varchar(12) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_info
-- ----------------------------
INSERT INTO `job_info` VALUES ('1', 'zd', '工程师', 'cd', '0', 'zz', '123', '111', '', '1', '2020-05-03 22:58:46', null);

-- ----------------------------
-- Table structure for `job_recruitment`
-- ----------------------------
DROP TABLE IF EXISTS `job_recruitment`;
CREATE TABLE `job_recruitment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(255) NOT NULL,
  `companyProperty` varchar(2048) NOT NULL,
  `position` varchar(255) NOT NULL,
  `personCount` int(11) NOT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `requires` varchar(255) NOT NULL,
  `salary` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `deadline` datetime DEFAULT NULL,
  `concatInfo` varchar(255) NOT NULL,
  `publisherId` varchar(8) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_recruitment
-- ----------------------------
INSERT INTO `job_recruitment` VALUES ('1', '大声道', '沽泡去', 'java', '111', null, '1', '111', '111', 'cd', '2020-04-05 00:00:00', '111', '0000', '2020-04-05 00:00:00', null);
INSERT INTO `job_recruitment` VALUES ('2', '大声道', '沽泡去', 'java', '11', null, '1', '111', '111', '111', '2020-05-05 00:00:00', '111', '0000', '2020-05-04 10:13:06', null);

-- ----------------------------
-- Table structure for `practice_diary`
-- ----------------------------
DROP TABLE IF EXISTS `practice_diary`;
CREATE TABLE `practice_diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `practiceId` int(11) NOT NULL,
  `evaluationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_diary
-- ----------------------------

-- ----------------------------
-- Table structure for `practice_info`
-- ----------------------------
DROP TABLE IF EXISTS `practice_info`;
CREATE TABLE `practice_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `beginTime` datetime DEFAULT NULL,
  `endTime` datetime DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `principalName` varchar(255) NOT NULL,
  `principalPhone` varchar(255) NOT NULL,
  `practiceStatus` tinyint(4) NOT NULL,
  `jobContent` varchar(1024) DEFAULT NULL,
  `studentId` varchar(12) NOT NULL,
  `fraction` int(11) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_info
-- ----------------------------
INSERT INTO `practice_info` VALUES ('3', '中国电子', 'cpp', '2020-04-01 00:00:00', null, '成都', '老王', '2222', '2', '摸鱼', '1', null, '2020-05-05 12:10:09', null, '1');
INSERT INTO `practice_info` VALUES ('4', '中国电ddd子', 'cpp', '2020-04-01 00:00:00', null, '成都', '老王', '2222', '1', '摸dfd鱼', '11', null, '2020-05-05 12:10:43', null, '1');

-- ----------------------------
-- Table structure for `practice_plan`
-- ----------------------------
DROP TABLE IF EXISTS `practice_plan`;
CREATE TABLE `practice_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `practiceId` int(11) NOT NULL,
  `evaluationId` int(11) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_plan
-- ----------------------------
INSERT INTO `practice_plan` VALUES ('3', '第一篇', '111232323', '1', '2020-05-05 12:12:12', '2020-05-05 22:04:21', '3', null, '2');
INSERT INTO `practice_plan` VALUES ('4', '第2篇', 'bucuo', '1', '2020-05-05 12:12:17', null, '3', null, '2');
INSERT INTO `practice_plan` VALUES ('5', '77', 'ddddd', '11', '2020-05-05 12:12:31', null, '4', null, '2');
INSERT INTO `practice_plan` VALUES ('6', '88', 'd低点的dddd', '11', '2020-05-05 12:12:38', null, '4', null, '2');
INSERT INTO `practice_plan` VALUES ('7', '88', 'd低点的dddd', '11', '2020-05-05 12:16:04', null, '4', null, '2');

-- ----------------------------
-- Table structure for `practice_report`
-- ----------------------------
DROP TABLE IF EXISTS `practice_report`;
CREATE TABLE `practice_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `practiceId` int(11) NOT NULL,
  `evaluationId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_report
-- ----------------------------

-- ----------------------------
-- Table structure for `user_admin`
-- ----------------------------
DROP TABLE IF EXISTS `user_admin`;
CREATE TABLE `user_admin` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_admin
-- ----------------------------
INSERT INTO `user_admin` VALUES ('0000', 'admin', '123456');

-- ----------------------------
-- Table structure for `user_student`
-- ----------------------------
DROP TABLE IF EXISTS `user_student`;
CREATE TABLE `user_student` (
  `id` varchar(12) NOT NULL,
  `name` varchar(255) NOT NULL,
  `collegeId` int(11) NOT NULL,
  `classId` int(11) NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '1',
  `phoneNumber` varchar(11) DEFAULT NULL,
  `teacherId` varchar(8) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_student
-- ----------------------------
INSERT INTO `user_student` VALUES ('1', 'wh', '1', '1', '1', '15928776666', '1', '1', '123456');
INSERT INTO `user_student` VALUES ('11', 'beichuli', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `user_student` VALUES ('2', '33', '1', '1', '1', '22', '1', '1', '22');

-- ----------------------------
-- Table structure for `user_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `user_teacher`;
CREATE TABLE `user_teacher` (
  `id` varchar(8) NOT NULL,
  `name` varchar(255) NOT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `collegeId` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_teacher
-- ----------------------------
INSERT INTO `user_teacher` VALUES ('1', 'zzz', '123', '741', '1', '1', '1');
