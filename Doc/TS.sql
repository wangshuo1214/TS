/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80035 (8.0.35)
 Source Host           : localhost:3306
 Source Schema         : TS

 Target Server Type    : MySQL
 Target Server Version : 80035 (8.0.35)
 File Encoding         : 65001

 Date: 03/02/2024 21:51:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for CHECK_RECORD
-- ----------------------------
DROP TABLE IF EXISTS `CHECK_RECORD`;
CREATE TABLE `check_record` (
    `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
    `device_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备id',
    `check_user_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '巡检人员id',
    `check_time` datetime NOT NULL COMMENT '巡检时间',
    `valid_check_items` text COLLATE utf8mb4_general_ci COMMENT '合格项id集合',
    `device_up_time` datetime DEFAULT NULL COMMENT '设备开机时间',
    `device_down_time` datetime DEFAULT NULL COMMENT '设备停机时间',
    `check_description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '检查描述',
    `create_time` datetime NOT NULL COMMENT '创建时间',
    `update_time` datetime NOT NULL COMMENT '修改时间',
    `is_delete` char(1) NOT NULL COMMENT '删除标志',
    PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of CHECK_RECORD
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for DEVICE
-- ----------------------------
DROP TABLE IF EXISTS `DEVICE`;
CREATE TABLE `DEVICE` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备名称',
  `type` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备类型',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of DEVICE
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for DEVICE_PROBLEM
-- ----------------------------
DROP TABLE IF EXISTS `DEVICE_PROBLEM`;
CREATE TABLE `DEVICE_PROBLEM` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `device_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备id',
  `invalid_check_item_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '检查项id',
  `time` datetime NOT NULL COMMENT '巡检时间',
  `description` varchar(500) COLLATE utf8mb4_general_ci NOT NULL COMMENT '问题描述',
  `close_status` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '关闭状态',
  `close_user_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关闭用户id',
  `close_time` datetime DEFAULT NULL COMMENT '关闭时间',
  `close_description` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '关闭描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of DEVICE_PROBLEM
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for DICT_DATA
-- ----------------------------
DROP TABLE IF EXISTS `DICT_DATA`;
CREATE TABLE `DICT_DATA` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `dict_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `dict_type_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型id',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of DICT_DATA
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for DICT_TYPE
-- ----------------------------
DROP TABLE IF EXISTS `DICT_TYPE`;
CREATE TABLE `DICT_TYPE` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `dict_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典类型',
  `dict_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典名称',
  `remark` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of DICT_TYPE
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for LOG
-- ----------------------------
DROP TABLE IF EXISTS `LOG`;
CREATE TABLE `LOG` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `user_id` varchar(36) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `time` datetime NOT NULL COMMENT '操作时间',
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作内容',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of LOG
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for CHECK_ITEM
-- ----------------------------
DROP TABLE IF EXISTS `CHECK_ITEM`;
CREATE TABLE `CHECK_ITEM` (
  `uuid` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  `device_type` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '设备类型',
  `check_type` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '巡检类型',
  `content` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '检查内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of PROBLEM_LIST
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for USER
-- ----------------------------
DROP TABLE IF EXISTS `USER`;
CREATE TABLE `USER` (
  `uuid` varchar(36) COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名称',
  `login_name` varchar(100) COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '密码',
  `vx_id` varchar(36) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `is_delete` char(1) COLLATE utf8mb4_general_ci NOT NULL COMMENT '删除标志',
  PRIMARY KEY (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of USER
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
