/*
 Navicat Premium Data Transfer

 Source Server         : bbb
 Source Server Type    : MySQL
 Source Server Version : 50617
 Source Host           : localhost:3306
 Source Schema         : student-system

 Target Server Type    : MySQL
 Target Server Version : 50617
 File Encoding         : 65001

 Date: 16/05/2019 14:28:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `DomNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Rooms` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `Location` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `StuSex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('c19', '100', '山上', '男');
INSERT INTO `dormitory` VALUES ('c1', '50', '山脚', '女');
INSERT INTO `dormitory` VALUES ('c8', '50', '饭堂旁边', '女');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `major` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `num` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('计算机', '1');
INSERT INTO `major` VALUES ('珠宝', '2');
INSERT INTO `major` VALUES ('演艺', '3');
INSERT INTO `major` VALUES ('经济', '4');

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room`  (
  `DomNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Floor` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '',
  `RoomNum` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Beds` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Price` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `Members` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of room
-- ----------------------------
INSERT INTO `room` VALUES ('c19', '1', '100', '4', '1000', '彭于晏');
INSERT INTO `room` VALUES ('c19', '1', '100', '4', '1000', '小陈');
INSERT INTO `room` VALUES ('c1', '1', '100', '4', '1000', '香香');

-- ----------------------------
-- Table structure for stus
-- ----------------------------
DROP TABLE IF EXISTS `stus`;
CREATE TABLE `stus`  (
  `stuid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `stuname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '姓名',
  `stusex` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '性别',
  `stuage` int(255) NOT NULL COMMENT '年龄',
  `studept` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系别',
  PRIMARY KEY (`stuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of stus
-- ----------------------------
INSERT INTO `stus` VALUES ('002', '彭于晏', '男', 21, '演员');
INSERT INTO `stus` VALUES ('004', '小陈', '男', 20, '计算机');
INSERT INTO `stus` VALUES ('006', '香香', '女', 20, '珠宝');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `accout` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `quan` int(255) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`accout`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001', '123', 8, '陈博士');
INSERT INTO `user` VALUES ('002', '222', 0, '彭于晏');
INSERT INTO `user` VALUES ('004', '004', 0, '小陈');
INSERT INTO `user` VALUES ('006', '006', 0, '香香');
INSERT INTO `user` VALUES ('007', '123', 4, '雪姨');

SET FOREIGN_KEY_CHECKS = 1;
