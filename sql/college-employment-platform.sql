/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3307
Source Database       : college-employment-platform

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-05-11 23:04:43
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('1', '物联网');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluation
-- ----------------------------
INSERT INTO `evaluation` VALUES ('1', '2020-05-09 09:22:47', '2020-05-09 09:25:16', 'after', '2', '2');

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
  `imgUrl` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_info
-- ----------------------------
INSERT INTO `job_info` VALUES ('1', 'qq', 'qq', 'w', '1', 'ff', 'ff', 'ff', 's', '1', '2020-05-08 23:33:00', null, null);

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
INSERT INTO `job_recruitment` VALUES ('1', '中电', '11', 'i', '12', null, '1', 'abc', '3000', 'ffg', '2020-05-09 21:36:55', '方法', 'admin', '2020-05-09 21:37:08', null);
INSERT INTO `job_recruitment` VALUES ('2', '中电11', '1122', 'i', '12', null, '2', 'abc', '5000', 'ffg', '2020-05-09 21:36:55', '方法', '1', '2020-05-09 22:04:11', null);

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
  `date` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_diary
-- ----------------------------
INSERT INTO `practice_diary` VALUES ('2', 'hhhhhh', '1', '2020-05-08 23:36:39', '2020-05-08 23:37:42', '1', '1', '2020-06-05');
INSERT INTO `practice_diary` VALUES ('3', '111', '1', '2020-05-08 23:39:19', null, '1', null, '2020-09-08');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_info
-- ----------------------------
INSERT INTO `practice_info` VALUES ('1', 'dd', 'f', null, null, 'w', 'f', 'v', '2', 'qq', '1', null, '2020-05-08 23:33:56', null, '1');

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
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_plan
-- ----------------------------
INSERT INTO `practice_plan` VALUES ('1', '1234', 'n那你爸爸', '1', '2020-05-10 23:31:29', null, '1', null, '1', null);

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
  `date` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_report
-- ----------------------------
INSERT INTO `practice_report` VALUES ('1', 're', '很好的方法是的', '1', '2020-05-09 15:26:45', null, '1', '1', '2020-09-08');

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
INSERT INTO `user_admin` VALUES ('admin', 'admin', 'admin');

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
INSERT INTO `user_student` VALUES ('1', 'wh', '1', '1', '1', '766558', '1', 'jj', '1');

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
INSERT INTO `user_teacher` VALUES ('1', 'hh', '123', '1', '1', '1', '1');
