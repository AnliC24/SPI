/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : sjtc

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 25/07/2022 16:34:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_device_info
-- ----------------------------
DROP TABLE IF EXISTS `base_device_info`;
CREATE TABLE `base_device_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `device_type` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `login_status` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录状态',
  `network_connect` double(10, 0) NULL DEFAULT NULL COMMENT '当前设备与服务器的网络连接延迟',
  `device_address` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备组分为2侧，左右2侧的设备组不一致',
  `opt_process` int(10) NULL DEFAULT NULL COMMENT '当前设备所处工序-必填',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for base_warn_info
-- ----------------------------
DROP TABLE IF EXISTS `base_warn_info`;
CREATE TABLE `base_warn_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `warn_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_type` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `warn_level` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警级别',
  `warn_status` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警状态',
  `warn_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '告警类型',
  `warn_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '告警内容',
  `warn_log` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '告警日志',
  `send_count` int(11) NULL DEFAULT NULL COMMENT '推送条数',
  `success_send_count` int(11) NULL DEFAULT NULL COMMENT '成功推送条数',
  `warn_time` datetime(0) NULL DEFAULT NULL COMMENT '告警日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for detect_config_info
-- ----------------------------
DROP TABLE IF EXISTS `detect_config_info`;
CREATE TABLE `detect_config_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `detect_config_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置编号',
  `detect_config_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置名称',
  `detect_check_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测属性',
  `detect_check_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测值',
  `detect_check_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '属性类型',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for device_detect_map
-- ----------------------------
DROP TABLE IF EXISTS `device_detect_map`;
CREATE TABLE `device_detect_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `detect_config_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '检测配置编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for grease_nipple_info
-- ----------------------------
DROP TABLE IF EXISTS `grease_nipple_info`;
CREATE TABLE `grease_nipple_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for hk_camera_info
-- ----------------------------
DROP TABLE IF EXISTS `hk_camera_info`;
CREATE TABLE `hk_camera_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_data_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_data_dict`;
CREATE TABLE `sys_data_dict`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `dict_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典编码',
  `dict_value` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典值',
  `dict_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典类型',
  `dict_desc` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '字典描述',
  `dict_pid` int(11) NULL DEFAULT NULL COMMENT '上级字典编号',
  `dict_level` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典级别-系统级/客户级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_data_dict
-- ----------------------------
INSERT INTO `sys_data_dict` VALUES (1, '01', '超级管理员', 'role_type', '用户角色', NULL, 'SYS');
INSERT INTO `sys_data_dict` VALUES (2, '02', '作业人员', 'role_type', '用户角色', NULL, 'SYS');

-- ----------------------------
-- Table structure for turbine_inductance_info
-- ----------------------------
DROP TABLE IF EXISTS `turbine_inductance_info`;
CREATE TABLE `turbine_inductance_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_device_map
-- ----------------------------
DROP TABLE IF EXISTS `user_device_map`;
CREATE TABLE `user_device_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `user_password` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录密码',
  `user_status` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户状态',
  `role_type` varchar(12) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色状态',
  `user_email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系邮箱',
  `user_phone` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, '37e778b276064690b09596434e73a0fb', 'admin', '123654', '02', '01', '111111@gmail.com', '11111111', '2022-07-18 15:08:43', '2022-07-19 09:08:51');
INSERT INTO `user_info` VALUES (8, 'e988838438864ecf8f33d9fd471c3b84', 'opt1', '123456', '02', '02', '123333333@gmail.com', '1234567891', '2022-07-19 09:55:52', '2022-07-19 09:55:52');

-- ----------------------------
-- Table structure for user_relate_map
-- ----------------------------
DROP TABLE IF EXISTS `user_relate_map`;
CREATE TABLE `user_relate_map`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `user_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户编号',
  `user_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `super_relate_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属上级用户编号',
  `super_relate_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属上级用户名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_relate_map
-- ----------------------------
INSERT INTO `user_relate_map` VALUES (1, '37e778b276064690b09596434e73a0fb', 'admin', 'root', 'root');
INSERT INTO `user_relate_map` VALUES (6, 'e988838438864ecf8f33d9fd471c3b84', 'opt1', '37e778b276064690b09596434e73a0fb', 'admin');

-- ----------------------------
-- Table structure for wheel_clean_info
-- ----------------------------
DROP TABLE IF EXISTS `wheel_clean_info`;
CREATE TABLE `wheel_clean_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键编号',
  `device_id` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备编号',
  `device_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
