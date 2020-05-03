/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3307
Source Database       : college-employment-platform

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2020-05-03 16:06:21
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `class`
-- ----------------------------
DROP TABLE IF EXISTS `class`;
CREATE TABLE `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `collegeId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class
-- ----------------------------

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for `job_info`
-- ----------------------------
DROP TABLE IF EXISTS `job_info`;
CREATE TABLE `job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `tripartite` tinyint(4) NOT NULL,
  `contactName` varchar(10) NOT NULL,
  `contactPhone` varchar(11) NOT NULL,
  `contactEmail` varchar(255) DEFAULT NULL,
  `note` varchar(255) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_info
-- ----------------------------

-- ----------------------------
-- Table structure for `job_recruitment`
-- ----------------------------
DROP TABLE IF EXISTS `job_recruitment`;
CREATE TABLE `job_recruitment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `companyName` varchar(30) NOT NULL,
  `companyProperty` varchar(2048) NOT NULL,
  `position` varchar(255) NOT NULL,
  `personCount` int(11) NOT NULL,
  `imgUrl` varchar(255) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `require` varchar(255) NOT NULL,
  `salary` int(11) NOT NULL,
  `address` varchar(255) NOT NULL,
  `deadline` datetime NOT NULL,
  `concatInfo` varchar(255) NOT NULL,
  `publisherId` varchar(8) NOT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of job_recruitment
-- ----------------------------

-- ----------------------------
-- Table structure for `practice_diary`
-- ----------------------------
DROP TABLE IF EXISTS `practice_diary`;
CREATE TABLE `practice_diary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `evaluation` varchar(2048) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_diary
-- ----------------------------

-- ----------------------------
-- Table structure for `practice_info`
-- ----------------------------
DROP TABLE IF EXISTS `practice_info`;
CREATE TABLE `practice_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company` varchar(30) NOT NULL,
  `position` varchar(20) NOT NULL,
  `beginTime` datetime NOT NULL,
  `endTime` datetime DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `principalName` varchar(20) NOT NULL,
  `principalPhone` varchar(11) NOT NULL,
  `practiceStatus` tinyint(4) NOT NULL,
  `jobContent` varchar(1024) DEFAULT NULL,
  `studentId` varchar(12) NOT NULL,
  `fraction` int(3) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_info
-- ----------------------------

-- ----------------------------
-- Table structure for `practice_plan`
-- ----------------------------
DROP TABLE IF EXISTS `practice_plan`;
CREATE TABLE `practice_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `evaluation` varchar(2048) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of practice_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `practice_report`
-- ----------------------------
DROP TABLE IF EXISTS `practice_report`;
CREATE TABLE `practice_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `content` varchar(2048) NOT NULL,
  `studentId` varchar(12) NOT NULL,
  `evaluation` varchar(2048) DEFAULT NULL,
  `createTime` datetime NOT NULL,
  `updateTime` datetime DEFAULT NULL,
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
  `name` varchar(20) NOT NULL,
  `collegeId` int(11) NOT NULL,
  `classId` int(11) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `teacherId` varchar(8) NOT NULL,
  `email` varchar(20) DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_student
-- ----------------------------

-- ----------------------------
-- Table structure for `user_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `user_teacher`;
CREATE TABLE `user_teacher` (
  `id` varchar(8) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `email` varchar(20) DEFAULT NULL,
  `collegeId` int(11) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_teacher
-- ----------------------------
