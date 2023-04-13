/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.100.88
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 192.168.100.88:3306
 Source Schema         : quick-frame-samples

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 05/09/2021 19:00:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for datatest
-- ----------------------------
DROP TABLE IF EXISTS `datatest`;
CREATE TABLE `datatest`  (
  `data` datetime(6) NULL DEFAULT NULL COMMENT '时间'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of datatest
-- ----------------------------
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:12:53.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:13:08.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:16:49.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:18:48.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:20:08.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:20:44.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:21:16.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:21:22.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:22:52.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:25:06.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 00:00:00.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:25:55.000000');
INSERT INTO `datatest` VALUES ('2021-04-20 10:25:54.000000');

-- ----------------------------
-- Table structure for dict_data
-- ----------------------------
DROP TABLE IF EXISTS `dict_data`;
CREATE TABLE `dict_data`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '业务类',
  `dict_data_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_data
-- ----------------------------
INSERT INTO `dict_data` VALUES (1, '系统类', 'enum_test', '枚举测试', '测试枚举更新测试', '2021-03-01 17:29:29', 'unknown', '2021-03-01 17:31:06', 'unknown');
INSERT INTO `dict_data` VALUES (2, '业务类', 'user_status', '用户状态', '', '2021-03-05 12:12:03', 'admin', '2021-03-05 14:55:11', 'unknown');

-- ----------------------------
-- Table structure for dict_data_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_data_type`;
CREATE TABLE `dict_data_type`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `dict_data_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_data_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_data_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `dict_data_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_data_type
-- ----------------------------
INSERT INTO `dict_data_type` VALUES (1, 'enum_test', '0', '无效', '无效状态11', '2021-03-01 17:40:24', 'unknown', '2021-03-05 11:09:53', 'unknown');
INSERT INTO `dict_data_type` VALUES (2, 'enum_test', '1', '有效', '有效状态', '2021-03-01 17:40:24', 'unknown', '', '');
INSERT INTO `dict_data_type` VALUES (4, 'user_status', '0', '无效', '无效用户', NULL, NULL, '2021-03-05 14:55:26', 'unknown');
INSERT INTO `dict_data_type` VALUES (5, 'user_status', '1', '有效', '有效用户', NULL, NULL, NULL, NULL);
INSERT INTO `dict_data_type` VALUES (6, 'enum_test', '12', '1', '1', '2021-03-05 14:21:39', 'unknown', '2021-03-05 14:26:23', 'unknown');
INSERT INTO `dict_data_type` VALUES (7, 'enum_test', '1', '1', '1', '2021-03-05 14:23:58', 'unknown', '2021-03-05 16:02:07', 'unknown');
INSERT INTO `dict_data_type` VALUES (9, 'enum_test', '3', '1', '3', '2021-03-05 15:29:40', 'unknown', '2021-03-05 16:01:59', 'unknown');
INSERT INTO `dict_data_type` VALUES (10, 'enum_test', '4', '4', '4', '2021-03-05 16:07:26', 'unknown', NULL, NULL);
INSERT INTO `dict_data_type` VALUES (11, 'enum_test', '5', '5', '5', '2021-03-05 16:07:40', 'unknown', NULL, NULL);

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence`  (
  `next_val` bigint(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES (1);

-- ----------------------------
-- Table structure for job_execution_log
-- ----------------------------
DROP TABLE IF EXISTS `job_execution_log`;
CREATE TABLE `job_execution_log`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hostname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sharding_item` int(0) NOT NULL,
  `execution_source` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `failure_cause` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `is_success` int(0) NOT NULL,
  `start_time` timestamp(0) NULL DEFAULT NULL,
  `complete_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for job_status_trace_log
-- ----------------------------
DROP TABLE IF EXISTS `job_status_trace_log`;
CREATE TABLE `job_status_trace_log`  (
  `id` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `job_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `original_task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `task_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `slave_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `execution_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `sharding_item` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `message` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `creation_time` timestamp(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `TASK_ID_STATE_INDEX`(`task_id`, `state`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `log_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `log_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `operate_time` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_took_time` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 47 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu`  (
  `menu_id` int(0) NOT NULL AUTO_INCREMENT,
  `menu_pid` int(0) NULL DEFAULT NULL,
  `menu_page` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '前端的路径',
  `menu_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `menu_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`menu_id`) USING BTREE,
  UNIQUE INDEX `menu_name`(`menu_name`) USING BTREE,
  INDEX `menu_ibfk_1`(`menu_pid`) USING BTREE,
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`menu_pid`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES (27, NULL, '/pages/index', NULL, '2021-03-11 12:25:01', '首页');
INSERT INTO `menu` VALUES (28, NULL, '/pages/systemmanage', NULL, '2021-03-11 13:47:14', '系统管理');
INSERT INTO `menu` VALUES (29, NULL, '/pages/systemmanagegateway', '2021-03-10 11:07:19', '2021-03-10 11:07:19', '原系统管理');
INSERT INTO `menu` VALUES (30, 28, '/pages/systemmanage/usermanage', NULL, '2021-03-11 13:47:26', '用户管理');
INSERT INTO `menu` VALUES (31, 28, '/pages/systemmanage/organizationmanage', NULL, '2021-03-11 13:26:58', '组织机构管理');
INSERT INTO `menu` VALUES (32, 28, '/pages/systemmanage/menumanage', NULL, '2021-03-11 13:27:35', '菜单管理');
INSERT INTO `menu` VALUES (33, 28, '/pages/systemmanage/rolemanage', NULL, '2021-03-11 13:28:05', '角色管理');
INSERT INTO `menu` VALUES (34, 28, '/pages/systemmanage/paramset', NULL, '2021-03-11 13:28:26', '参数设置');
INSERT INTO `menu` VALUES (35, 28, '/pages/systemmanage/dictionarymanage', NULL, '2021-03-11 13:28:43', '字典管理');
INSERT INTO `menu` VALUES (36, 28, '/pages/systemmanage/systemMonitor', NULL, '2021-03-11 13:29:01', '系统监控');
INSERT INTO `menu` VALUES (37, 36, '/pages/systemmanage/systemMonitor/physicalsource', NULL, '2021-03-11 13:29:25', '物理资源监控');
INSERT INTO `menu` VALUES (38, 36, '/pages/systemmanage/systemMonitor/jvmmonitor', '2021-03-10 11:30:11', '2021-03-10 11:30:11', '虚拟机监控');
INSERT INTO `menu` VALUES (39, 36, '/pages/systemmanage/systemMonitor/portDocument', NULL, '2021-03-11 13:31:02', '接口文档');
INSERT INTO `menu` VALUES (40, 36, '/pages/systemmanage/systemMonitor/DataMonitoring', '2021-03-10 11:30:50', '2021-03-10 11:30:50', '数据监控');
INSERT INTO `menu` VALUES (41, 36, '/pages/systemmanage/systemMonitor/systemLog', '2021-03-10 11:31:10', '2021-03-10 11:31:10', '系统日志');
INSERT INTO `menu` VALUES (42, 36, '/pages/systemmanage/systemMonitor/logManagement', '2021-03-10 11:31:28', '2021-03-10 11:31:28', '访问日志');
INSERT INTO `menu` VALUES (43, 36, '/pages/systemmanage/systemMonitor/WSController', '2021-03-10 11:31:46', '2021-03-10 11:31:46', '消息管理');
INSERT INTO `menu` VALUES (44, 29, '/pages/systemmanagegateway/usermanagegateway', NULL, '2021-03-10 11:33:21', '用户管理-网关');
INSERT INTO `menu` VALUES (45, 29, '/pages/systemmanagegateway/serviceconfiggateway', '2021-03-10 11:33:38', '2021-03-10 11:33:38', '服务配置-网关');
INSERT INTO `menu` VALUES (46, 29, '/pages/systemmanagegateway/menumanagegateway', '2021-03-10 11:34:01', '2021-03-10 11:34:01', '菜单管理-网关');
INSERT INTO `menu` VALUES (47, 29, '/pages/systemmanagegateway/rolemanagegateway', '2021-03-10 11:34:18', '2021-03-10 11:34:18', '角色管理-网关');
INSERT INTO `menu` VALUES (48, 28, '/pages/systemmanage/monitoring/1', NULL, '2021-03-12 09:53:42', '纤维生产企业监测');

-- ----------------------------
-- Table structure for menu_opration
-- ----------------------------
DROP TABLE IF EXISTS `menu_opration`;
CREATE TABLE `menu_opration`  (
  `menu_id` int(0) NOT NULL,
  `op_id` int(0) NOT NULL,
  PRIMARY KEY (`menu_id`, `op_id`) USING BTREE,
  INDEX `menu_opration_ibfk_2`(`op_id`) USING BTREE,
  CONSTRAINT `menu_opration_ibfk_1` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `menu_opration_ibfk_2` FOREIGN KEY (`op_id`) REFERENCES `opration` (`op_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of menu_opration
-- ----------------------------
INSERT INTO `menu_opration` VALUES (28, 1);
INSERT INTO `menu_opration` VALUES (30, 1);
INSERT INTO `menu_opration` VALUES (31, 1);
INSERT INTO `menu_opration` VALUES (32, 1);
INSERT INTO `menu_opration` VALUES (33, 1);
INSERT INTO `menu_opration` VALUES (34, 1);
INSERT INTO `menu_opration` VALUES (35, 1);
INSERT INTO `menu_opration` VALUES (36, 1);
INSERT INTO `menu_opration` VALUES (37, 1);
INSERT INTO `menu_opration` VALUES (39, 1);
INSERT INTO `menu_opration` VALUES (48, 1);
INSERT INTO `menu_opration` VALUES (28, 2);
INSERT INTO `menu_opration` VALUES (30, 2);
INSERT INTO `menu_opration` VALUES (31, 2);
INSERT INTO `menu_opration` VALUES (32, 2);
INSERT INTO `menu_opration` VALUES (33, 2);
INSERT INTO `menu_opration` VALUES (34, 2);
INSERT INTO `menu_opration` VALUES (35, 2);
INSERT INTO `menu_opration` VALUES (36, 2);
INSERT INTO `menu_opration` VALUES (39, 2);
INSERT INTO `menu_opration` VALUES (48, 2);
INSERT INTO `menu_opration` VALUES (28, 3);
INSERT INTO `menu_opration` VALUES (30, 3);
INSERT INTO `menu_opration` VALUES (31, 3);
INSERT INTO `menu_opration` VALUES (32, 3);
INSERT INTO `menu_opration` VALUES (33, 3);
INSERT INTO `menu_opration` VALUES (34, 3);
INSERT INTO `menu_opration` VALUES (35, 3);
INSERT INTO `menu_opration` VALUES (36, 3);
INSERT INTO `menu_opration` VALUES (37, 3);
INSERT INTO `menu_opration` VALUES (39, 3);
INSERT INTO `menu_opration` VALUES (28, 4);
INSERT INTO `menu_opration` VALUES (30, 4);
INSERT INTO `menu_opration` VALUES (31, 4);
INSERT INTO `menu_opration` VALUES (32, 4);
INSERT INTO `menu_opration` VALUES (33, 4);
INSERT INTO `menu_opration` VALUES (34, 4);
INSERT INTO `menu_opration` VALUES (35, 4);
INSERT INTO `menu_opration` VALUES (36, 4);
INSERT INTO `menu_opration` VALUES (39, 4);
INSERT INTO `menu_opration` VALUES (28, 5);
INSERT INTO `menu_opration` VALUES (30, 5);
INSERT INTO `menu_opration` VALUES (31, 5);
INSERT INTO `menu_opration` VALUES (32, 5);
INSERT INTO `menu_opration` VALUES (33, 5);
INSERT INTO `menu_opration` VALUES (34, 5);
INSERT INTO `menu_opration` VALUES (35, 5);
INSERT INTO `menu_opration` VALUES (36, 5);
INSERT INTO `menu_opration` VALUES (37, 5);
INSERT INTO `menu_opration` VALUES (39, 5);
INSERT INTO `menu_opration` VALUES (28, 6);
INSERT INTO `menu_opration` VALUES (30, 6);
INSERT INTO `menu_opration` VALUES (31, 6);
INSERT INTO `menu_opration` VALUES (32, 6);
INSERT INTO `menu_opration` VALUES (33, 6);
INSERT INTO `menu_opration` VALUES (34, 6);
INSERT INTO `menu_opration` VALUES (35, 6);
INSERT INTO `menu_opration` VALUES (36, 6);
INSERT INTO `menu_opration` VALUES (39, 6);
INSERT INTO `menu_opration` VALUES (28, 7);
INSERT INTO `menu_opration` VALUES (30, 7);
INSERT INTO `menu_opration` VALUES (31, 7);
INSERT INTO `menu_opration` VALUES (32, 7);
INSERT INTO `menu_opration` VALUES (33, 7);
INSERT INTO `menu_opration` VALUES (34, 7);
INSERT INTO `menu_opration` VALUES (35, 7);
INSERT INTO `menu_opration` VALUES (36, 7);
INSERT INTO `menu_opration` VALUES (37, 7);
INSERT INTO `menu_opration` VALUES (39, 7);
INSERT INTO `menu_opration` VALUES (28, 8);
INSERT INTO `menu_opration` VALUES (30, 8);
INSERT INTO `menu_opration` VALUES (31, 8);
INSERT INTO `menu_opration` VALUES (32, 8);
INSERT INTO `menu_opration` VALUES (33, 8);
INSERT INTO `menu_opration` VALUES (34, 8);
INSERT INTO `menu_opration` VALUES (35, 8);
INSERT INTO `menu_opration` VALUES (36, 8);
INSERT INTO `menu_opration` VALUES (39, 8);
INSERT INTO `menu_opration` VALUES (28, 9);
INSERT INTO `menu_opration` VALUES (30, 9);
INSERT INTO `menu_opration` VALUES (31, 9);
INSERT INTO `menu_opration` VALUES (32, 9);
INSERT INTO `menu_opration` VALUES (33, 9);
INSERT INTO `menu_opration` VALUES (34, 9);
INSERT INTO `menu_opration` VALUES (35, 9);
INSERT INTO `menu_opration` VALUES (36, 9);
INSERT INTO `menu_opration` VALUES (39, 9);
INSERT INTO `menu_opration` VALUES (28, 10);
INSERT INTO `menu_opration` VALUES (30, 10);
INSERT INTO `menu_opration` VALUES (31, 10);
INSERT INTO `menu_opration` VALUES (32, 10);
INSERT INTO `menu_opration` VALUES (33, 10);
INSERT INTO `menu_opration` VALUES (34, 10);
INSERT INTO `menu_opration` VALUES (35, 10);
INSERT INTO `menu_opration` VALUES (36, 10);
INSERT INTO `menu_opration` VALUES (37, 10);
INSERT INTO `menu_opration` VALUES (39, 10);
INSERT INTO `menu_opration` VALUES (28, 11);
INSERT INTO `menu_opration` VALUES (30, 11);
INSERT INTO `menu_opration` VALUES (31, 11);
INSERT INTO `menu_opration` VALUES (32, 11);
INSERT INTO `menu_opration` VALUES (33, 11);
INSERT INTO `menu_opration` VALUES (34, 11);
INSERT INTO `menu_opration` VALUES (35, 11);
INSERT INTO `menu_opration` VALUES (36, 11);
INSERT INTO `menu_opration` VALUES (37, 11);
INSERT INTO `menu_opration` VALUES (39, 11);
INSERT INTO `menu_opration` VALUES (28, 12);
INSERT INTO `menu_opration` VALUES (30, 12);
INSERT INTO `menu_opration` VALUES (31, 12);
INSERT INTO `menu_opration` VALUES (32, 12);
INSERT INTO `menu_opration` VALUES (33, 12);
INSERT INTO `menu_opration` VALUES (34, 12);
INSERT INTO `menu_opration` VALUES (35, 12);
INSERT INTO `menu_opration` VALUES (36, 12);
INSERT INTO `menu_opration` VALUES (39, 12);
INSERT INTO `menu_opration` VALUES (28, 13);
INSERT INTO `menu_opration` VALUES (30, 13);
INSERT INTO `menu_opration` VALUES (31, 13);
INSERT INTO `menu_opration` VALUES (32, 13);
INSERT INTO `menu_opration` VALUES (33, 13);
INSERT INTO `menu_opration` VALUES (34, 13);
INSERT INTO `menu_opration` VALUES (35, 13);
INSERT INTO `menu_opration` VALUES (36, 13);
INSERT INTO `menu_opration` VALUES (37, 13);
INSERT INTO `menu_opration` VALUES (39, 13);
INSERT INTO `menu_opration` VALUES (28, 14);
INSERT INTO `menu_opration` VALUES (30, 14);
INSERT INTO `menu_opration` VALUES (31, 14);
INSERT INTO `menu_opration` VALUES (32, 14);
INSERT INTO `menu_opration` VALUES (33, 14);
INSERT INTO `menu_opration` VALUES (34, 14);
INSERT INTO `menu_opration` VALUES (35, 14);
INSERT INTO `menu_opration` VALUES (36, 14);
INSERT INTO `menu_opration` VALUES (39, 14);

-- ----------------------------
-- Table structure for opration
-- ----------------------------
DROP TABLE IF EXISTS `opration`;
CREATE TABLE `opration`  (
  `op_id` int(0) NOT NULL AUTO_INCREMENT,
  `op_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作名称',
  `op_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `op_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`op_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opration
-- ----------------------------
INSERT INTO `opration` VALUES (1, '菜单管理-查看', '2021-02-22 17:52:23', '2021-02-22 17:52:23');
INSERT INTO `opration` VALUES (2, '菜单管理-编辑', '2021-02-22 17:57:44', '2021-02-22 17:57:44');
INSERT INTO `opration` VALUES (3, '用户管理-查看', '2021-02-22 17:57:53', '2021-02-22 17:57:53');
INSERT INTO `opration` VALUES (4, '用户管理-编辑', '2021-02-22 17:58:01', '2021-02-22 17:58:01');
INSERT INTO `opration` VALUES (5, '组织机构管理-查看', '2021-02-22 17:58:14', '2021-02-22 17:58:14');
INSERT INTO `opration` VALUES (6, '组织机构管理-编辑', '2021-02-22 17:58:22', '2021-02-22 17:58:22');
INSERT INTO `opration` VALUES (7, '角色管理-查看', '2021-02-22 17:58:31', '2021-02-22 17:58:31');
INSERT INTO `opration` VALUES (8, '角色管理-编辑', '2021-02-22 17:58:42', '2021-02-22 17:58:42');
INSERT INTO `opration` VALUES (9, '参数管理-编辑', '2021-03-10 09:43:10', '2021-03-10 09:43:10');
INSERT INTO `opration` VALUES (10, '参数管理-查看', '2021-03-10 09:43:27', '2021-03-10 09:43:27');
INSERT INTO `opration` VALUES (11, '字典管理-查看', '2021-03-10 09:52:43', '2021-03-10 09:52:43');
INSERT INTO `opration` VALUES (12, '字典管理-编辑', '2021-03-10 09:52:45', '2021-03-10 09:52:45');
INSERT INTO `opration` VALUES (13, '字典类型管理-查看', '2021-03-10 09:52:52', '2021-03-10 09:52:52');
INSERT INTO `opration` VALUES (14, '字典类型管理-编辑', '2021-03-10 09:53:03', '2021-03-10 09:53:03');

-- ----------------------------
-- Table structure for opration_resource
-- ----------------------------
DROP TABLE IF EXISTS `opration_resource`;
CREATE TABLE `opration_resource`  (
  `op_id` int(0) NOT NULL,
  `resource_id` int(0) NOT NULL,
  PRIMARY KEY (`op_id`, `resource_id`) USING BTREE,
  INDEX `opration_resource_ibfk_2`(`resource_id`) USING BTREE,
  CONSTRAINT `opration_resource_ibfk_1` FOREIGN KEY (`op_id`) REFERENCES `opration` (`op_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `opration_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `resource` (`resource_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of opration_resource
-- ----------------------------
INSERT INTO `opration_resource` VALUES (1, 1);
INSERT INTO `opration_resource` VALUES (2, 2);
INSERT INTO `opration_resource` VALUES (3, 3);
INSERT INTO `opration_resource` VALUES (4, 4);
INSERT INTO `opration_resource` VALUES (4, 5);
INSERT INTO `opration_resource` VALUES (5, 6);
INSERT INTO `opration_resource` VALUES (6, 7);
INSERT INTO `opration_resource` VALUES (7, 8);
INSERT INTO `opration_resource` VALUES (3, 9);
INSERT INTO `opration_resource` VALUES (7, 10);
INSERT INTO `opration_resource` VALUES (8, 11);
INSERT INTO `opration_resource` VALUES (8, 12);
INSERT INTO `opration_resource` VALUES (10, 13);
INSERT INTO `opration_resource` VALUES (10, 14);
INSERT INTO `opration_resource` VALUES (9, 15);
INSERT INTO `opration_resource` VALUES (9, 16);
INSERT INTO `opration_resource` VALUES (11, 17);
INSERT INTO `opration_resource` VALUES (11, 18);
INSERT INTO `opration_resource` VALUES (12, 19);
INSERT INTO `opration_resource` VALUES (12, 20);
INSERT INTO `opration_resource` VALUES (13, 21);
INSERT INTO `opration_resource` VALUES (13, 22);
INSERT INTO `opration_resource` VALUES (14, 23);
INSERT INTO `opration_resource` VALUES (14, 24);

-- ----------------------------
-- Table structure for org
-- ----------------------------
DROP TABLE IF EXISTS `org`;
CREATE TABLE `org`  (
  `org_id` int(0) NOT NULL AUTO_INCREMENT,
  `org_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '组织机构名称',
  `org_pid` int(0) NULL DEFAULT NULL COMMENT '上级组织机构',
  `org_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '组织机构描述',
  `org_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `org_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`org_id`) USING BTREE,
  UNIQUE INDEX `org_name`(`org_name`) USING BTREE,
  INDEX `org_ibfk_1`(`org_pid`) USING BTREE,
  CONSTRAINT `org_ibfk_1` FOREIGN KEY (`org_pid`) REFERENCES `org` (`org_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org
-- ----------------------------
INSERT INTO `org` VALUES (3, '软件所', NULL, NULL, NULL, '2021-03-11 13:40:52');
INSERT INTO `org` VALUES (4, '天基实验室', 3, '天基综合信息实验室', NULL, '2021-03-11 13:40:42');
INSERT INTO `org` VALUES (6, '二部', 4, NULL, NULL, '2021-03-11 13:40:59');
INSERT INTO `org` VALUES (7, '三部', 4, NULL, NULL, '2021-03-11 13:41:33');
INSERT INTO `org` VALUES (8, '推演组', 7, NULL, NULL, '2021-03-11 13:41:40');
INSERT INTO `org` VALUES (9, '大数据智能应用', 7, NULL, NULL, '2021-03-11 13:41:46');
INSERT INTO `org` VALUES (10, '激光方针组', 7, NULL, NULL, '2021-03-11 13:41:53');
INSERT INTO `org` VALUES (14, '组1', 7, '111', NULL, '2021-03-11 13:42:06');
INSERT INTO `org` VALUES (21, '组yyyy', 14, 'w333', '2021-02-21 13:37:52', '2021-02-21 13:37:52');
INSERT INTO `org` VALUES (22, '组zzzz', 14, 'w333', '2021-02-21 13:38:20', '2021-02-21 13:38:20');
INSERT INTO `org` VALUES (23, '组mmmm', 14, 'w333', '2021-02-21 13:39:32', '2021-02-21 13:39:32');
INSERT INTO `org` VALUES (24, '组nnnn', 14, 'w333', '2021-02-21 13:39:57', '2021-02-21 13:39:57');
INSERT INTO `org` VALUES (25, '组aaa', 14, 'w333', '2021-02-21 13:40:25', '2021-02-21 13:40:25');
INSERT INTO `org` VALUES (26, '组bbb', 14, 'w333', '2021-02-21 13:42:54', '2021-02-21 13:42:54');
INSERT INTO `org` VALUES (27, '组ccc', 14, 'w333', '2021-02-21 13:44:24', '2021-02-21 13:44:24');
INSERT INTO `org` VALUES (28, '组ddd', 14, 'w333', '2021-02-21 13:45:15', '2021-02-21 13:45:15');
INSERT INTO `org` VALUES (29, '组eeee', 14, 'w333', '2021-02-21 13:49:41', '2021-02-21 13:49:41');
INSERT INTO `org` VALUES (30, '组ffff', 14, 'w333', '2021-02-21 13:51:24', '2021-02-21 13:51:24');
INSERT INTO `org` VALUES (31, '组hhh', 7, 'w333', NULL, '2021-02-21 13:56:32');
INSERT INTO `org` VALUES (32, '测试数据修改', 6, NULL, NULL, '2021-02-24 11:31:14');
INSERT INTO `org` VALUES (35, '一部', 4, '11', NULL, '2021-03-11 13:42:12');
INSERT INTO `org` VALUES (36, '组gggg', 14, 'w333', '2021-02-26 10:10:36', '2021-02-26 10:10:36');
INSERT INTO `org` VALUES (37, '组xxx', NULL, '测试描述', NULL, '2021-03-09 13:43:01');

-- ----------------------------
-- Table structure for org_role
-- ----------------------------
DROP TABLE IF EXISTS `org_role`;
CREATE TABLE `org_role`  (
  `org_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`org_id`, `role_id`) USING BTREE,
  INDEX `org_role_ibfk_2`(`role_id`) USING BTREE,
  CONSTRAINT `org_role_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `org` (`org_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `org_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_role
-- ----------------------------
INSERT INTO `org_role` VALUES (3, 2);
INSERT INTO `org_role` VALUES (4, 2);
INSERT INTO `org_role` VALUES (6, 2);
INSERT INTO `org_role` VALUES (7, 2);
INSERT INTO `org_role` VALUES (8, 2);
INSERT INTO `org_role` VALUES (9, 2);
INSERT INTO `org_role` VALUES (10, 2);
INSERT INTO `org_role` VALUES (14, 2);
INSERT INTO `org_role` VALUES (32, 2);
INSERT INTO `org_role` VALUES (3, 4);
INSERT INTO `org_role` VALUES (4, 4);
INSERT INTO `org_role` VALUES (6, 4);
INSERT INTO `org_role` VALUES (7, 4);
INSERT INTO `org_role` VALUES (8, 4);
INSERT INTO `org_role` VALUES (9, 4);
INSERT INTO `org_role` VALUES (10, 4);
INSERT INTO `org_role` VALUES (14, 4);
INSERT INTO `org_role` VALUES (37, 4);
INSERT INTO `org_role` VALUES (3, 5);
INSERT INTO `org_role` VALUES (4, 5);
INSERT INTO `org_role` VALUES (6, 5);
INSERT INTO `org_role` VALUES (7, 5);
INSERT INTO `org_role` VALUES (8, 5);
INSERT INTO `org_role` VALUES (9, 5);
INSERT INTO `org_role` VALUES (10, 5);
INSERT INTO `org_role` VALUES (14, 5);
INSERT INTO `org_role` VALUES (35, 5);
INSERT INTO `org_role` VALUES (37, 5);
INSERT INTO `org_role` VALUES (3, 7);
INSERT INTO `org_role` VALUES (4, 7);
INSERT INTO `org_role` VALUES (6, 7);
INSERT INTO `org_role` VALUES (7, 7);
INSERT INTO `org_role` VALUES (8, 7);
INSERT INTO `org_role` VALUES (9, 7);
INSERT INTO `org_role` VALUES (10, 7);
INSERT INTO `org_role` VALUES (14, 7);
INSERT INTO `org_role` VALUES (3, 11);
INSERT INTO `org_role` VALUES (4, 11);
INSERT INTO `org_role` VALUES (6, 11);
INSERT INTO `org_role` VALUES (7, 11);
INSERT INTO `org_role` VALUES (8, 11);
INSERT INTO `org_role` VALUES (9, 11);
INSERT INTO `org_role` VALUES (10, 11);
INSERT INTO `org_role` VALUES (14, 11);

-- ----------------------------
-- Table structure for org_user
-- ----------------------------
DROP TABLE IF EXISTS `org_user`;
CREATE TABLE `org_user`  (
  `org_id` int(0) NOT NULL,
  `user_id` int(0) NOT NULL,
  PRIMARY KEY (`org_id`, `user_id`) USING BTREE,
  INDEX `org_user_ibfk_2`(`user_id`) USING BTREE,
  CONSTRAINT `org_user_ibfk_1` FOREIGN KEY (`org_id`) REFERENCES `org` (`org_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `org_user_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of org_user
-- ----------------------------
INSERT INTO `org_user` VALUES (9, 20);
INSERT INTO `org_user` VALUES (9, 23);
INSERT INTO `org_user` VALUES (35, 28);
INSERT INTO `org_user` VALUES (14, 39);
INSERT INTO `org_user` VALUES (9, 40);
INSERT INTO `org_user` VALUES (35, 41);
INSERT INTO `org_user` VALUES (32, 42);
INSERT INTO `org_user` VALUES (8, 46);

-- ----------------------------
-- Table structure for param
-- ----------------------------
DROP TABLE IF EXISTS `param`;
CREATE TABLE `param`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `param_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '业务类',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `create_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `update_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `param_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of param
-- ----------------------------
INSERT INTO `param` VALUES (1, '系统监控-访问日志-保留时长', 'sys.log.holdPeriod', '1d', '系统类', 'admin', '2021-02-26 10:51:57', 'unknown', '2021-02-26 14:43:18', '根据该参数清除之前的访问日志数据');
INSERT INTO `param` VALUES (5, '系统监控-测试', 'sys.test.a', 'test', '系统类', 'test', '2021-02-26 15:31:33', 'admin', '2021-03-05 10:37:19', '根据该参数清除之前的访问日志数据');
INSERT INTO `param` VALUES (6, '系统监控-访问日志-hello', 'sys.test.aaa', 'hello iscas', '业务类', 'unknown', '2021-03-01 10:18:48', 'unknown', '2021-03-01 13:42:13', 'hello world');
INSERT INTO `param` VALUES (10, '测试数据', '12222', '12', '业务类', 'unknown', '2021-03-05 14:02:57', 'unknown', '2021-03-05 14:03:03', '111');
INSERT INTO `param` VALUES (12, '测试数据2', 'kkk', 'vvv', '业务类', 'unknown', '2021-05-17 09:54:56', NULL, NULL, 'descddd');
INSERT INTO `param` VALUES (13, 'test3', 'test3', 'test3', '业务类', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `param` VALUES (14, 'test3', 'test3', 'test3', '业务类', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`  (
  `resource_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `resource_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '后端URL',
  `resource_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '资源描述',
  `resource_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `resource_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES (1, '/menu', '获取菜单树', '2021-02-22 17:49:58', '2021-02-22 17:51:39');
INSERT INTO `resource` VALUES (2, '/menu/node/*', '编辑菜单', '2021-02-22 17:50:39', '2021-02-22 17:54:52');
INSERT INTO `resource` VALUES (3, '/user/header', '获取用户表头', '2021-02-22 17:53:52', '2021-02-22 17:53:52');
INSERT INTO `resource` VALUES (4, '/user/del', '删除用户', '2021-02-22 17:54:15', '2021-02-22 17:54:34');
INSERT INTO `resource` VALUES (5, '/user/data', '编辑用户', '2021-02-22 17:54:32', '2021-02-22 17:54:32');
INSERT INTO `resource` VALUES (6, '/org', '获取组织机构树', '2021-02-22 17:55:09', '2021-02-22 17:55:09');
INSERT INTO `resource` VALUES (7, '/org/node/*', '编辑组织机构', '2021-02-22 17:55:30', '2021-02-22 17:55:30');
INSERT INTO `resource` VALUES (8, '/role/header', '获取角色表头', '2021-02-22 17:56:20', '2021-02-22 17:56:20');
INSERT INTO `resource` VALUES (9, '/user', '获取用户', '2021-02-22 17:56:36', '2021-02-22 17:56:36');
INSERT INTO `resource` VALUES (10, '/role', '获取角色', '2021-02-22 17:56:54', '2021-02-22 17:56:54');
INSERT INTO `resource` VALUES (11, '/role/del', '删除角色', '2021-02-22 17:57:11', '2021-02-22 17:57:11');
INSERT INTO `resource` VALUES (12, '/role/data', '编辑角色', '2021-02-22 17:57:25', '2021-02-22 17:57:25');
INSERT INTO `resource` VALUES (13, '/param/getHeader', '获取参数表头', '2021-03-10 09:37:43', '2021-03-10 09:48:38');
INSERT INTO `resource` VALUES (14, '/param/getData', '获取参数', '2021-03-10 09:38:21', '2021-03-10 09:48:46');
INSERT INTO `resource` VALUES (15, '/param/del', '删除参数', '2021-03-10 09:38:48', '2021-03-10 09:38:48');
INSERT INTO `resource` VALUES (16, '/param/data', '编辑参数', '2021-03-10 09:39:19', '2021-03-10 09:39:19');
INSERT INTO `resource` VALUES (17, '/dictData/getHeader', '获取字典数据表头', '2021-03-10 09:46:00', '2021-03-10 09:50:08');
INSERT INTO `resource` VALUES (18, '/dictData/getData', '获字典数据', '2021-03-10 09:46:04', '2021-03-10 09:50:10');
INSERT INTO `resource` VALUES (19, '/dictData/del', '删除字典数据', '2021-03-10 09:46:09', '2021-03-10 09:50:12');
INSERT INTO `resource` VALUES (20, '/dictData/data', '编辑字典数据', '2021-03-10 09:46:14', '2021-03-10 09:50:15');
INSERT INTO `resource` VALUES (21, '/dictDataType/getHeader', '获取字典类型数据表头', '2021-03-10 09:46:00', '2021-03-10 09:50:08');
INSERT INTO `resource` VALUES (22, '/dictDataType/getData', '获字典类型数据', '2021-03-10 09:46:04', '2021-03-10 09:50:10');
INSERT INTO `resource` VALUES (23, '/dictDataType/del', '删除字典类型数据', '2021-03-10 09:46:09', '2021-03-10 09:50:12');
INSERT INTO `resource` VALUES (24, '/dictDataType/data', '编辑字典类型数据', '2021-03-10 09:46:14', '2021-03-10 09:50:15');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` int(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `role_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`role_id`) USING BTREE,
  UNIQUE INDEX `role_name`(`role_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (2, '测试角色1', '2021-02-22 10:23:19', '2021-02-25 09:29:22');
INSERT INTO `role` VALUES (4, '普通用户11', '2021-02-22 14:35:01', '2021-02-25 09:29:22');
INSERT INTO `role` VALUES (5, '普通用户12', '2021-02-22 14:35:20', '2021-02-25 09:29:23');
INSERT INTO `role` VALUES (7, '普通用户13', '2021-02-22 14:41:27', '2021-02-25 09:29:24');
INSERT INTO `role` VALUES (11, 'super', NULL, NULL);
INSERT INTO `role` VALUES (12, '测试角色2', '2021-09-02 09:33:34', '2021-09-02 09:33:34');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu`  (
  `role_id` int(0) NOT NULL,
  `menu_id` int(0) NOT NULL,
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE,
  INDEX `role_menu_ibfk_2`(`menu_id`) USING BTREE,
  CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`menu_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES (11, 27);
INSERT INTO `role_menu` VALUES (12, 27);
INSERT INTO `role_menu` VALUES (2, 28);
INSERT INTO `role_menu` VALUES (4, 28);
INSERT INTO `role_menu` VALUES (11, 28);
INSERT INTO `role_menu` VALUES (11, 29);
INSERT INTO `role_menu` VALUES (2, 30);
INSERT INTO `role_menu` VALUES (4, 30);
INSERT INTO `role_menu` VALUES (11, 30);
INSERT INTO `role_menu` VALUES (11, 31);
INSERT INTO `role_menu` VALUES (4, 32);
INSERT INTO `role_menu` VALUES (11, 32);
INSERT INTO `role_menu` VALUES (4, 33);
INSERT INTO `role_menu` VALUES (11, 33);
INSERT INTO `role_menu` VALUES (4, 34);
INSERT INTO `role_menu` VALUES (11, 34);
INSERT INTO `role_menu` VALUES (4, 35);
INSERT INTO `role_menu` VALUES (11, 35);
INSERT INTO `role_menu` VALUES (4, 36);
INSERT INTO `role_menu` VALUES (5, 36);
INSERT INTO `role_menu` VALUES (11, 36);
INSERT INTO `role_menu` VALUES (2, 37);
INSERT INTO `role_menu` VALUES (4, 37);
INSERT INTO `role_menu` VALUES (5, 37);
INSERT INTO `role_menu` VALUES (11, 37);
INSERT INTO `role_menu` VALUES (4, 38);
INSERT INTO `role_menu` VALUES (5, 38);
INSERT INTO `role_menu` VALUES (11, 38);
INSERT INTO `role_menu` VALUES (4, 39);
INSERT INTO `role_menu` VALUES (5, 39);
INSERT INTO `role_menu` VALUES (11, 39);
INSERT INTO `role_menu` VALUES (4, 40);
INSERT INTO `role_menu` VALUES (11, 40);
INSERT INTO `role_menu` VALUES (4, 41);
INSERT INTO `role_menu` VALUES (5, 41);
INSERT INTO `role_menu` VALUES (11, 41);
INSERT INTO `role_menu` VALUES (4, 42);
INSERT INTO `role_menu` VALUES (5, 42);
INSERT INTO `role_menu` VALUES (11, 42);
INSERT INTO `role_menu` VALUES (4, 43);
INSERT INTO `role_menu` VALUES (5, 43);
INSERT INTO `role_menu` VALUES (11, 43);
INSERT INTO `role_menu` VALUES (11, 44);
INSERT INTO `role_menu` VALUES (11, 45);
INSERT INTO `role_menu` VALUES (11, 46);
INSERT INTO `role_menu` VALUES (11, 47);
INSERT INTO `role_menu` VALUES (11, 48);

-- ----------------------------
-- Table structure for role_opration
-- ----------------------------
DROP TABLE IF EXISTS `role_opration`;
CREATE TABLE `role_opration`  (
  `role_id` int(0) NOT NULL COMMENT '角色ID',
  `op_id` int(0) NOT NULL COMMENT '操作标识ID',
  PRIMARY KEY (`role_id`, `op_id`) USING BTREE,
  INDEX `op_id`(`op_id`) USING BTREE,
  CONSTRAINT `role_opration_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `role_opration_ibfk_2` FOREIGN KEY (`op_id`) REFERENCES `opration` (`op_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for shedlock
-- ----------------------------
DROP TABLE IF EXISTS `shedlock`;
CREATE TABLE `shedlock`  (
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `lock_until` timestamp(3) NOT NULL,
  `locked_at` timestamp(3) NOT NULL,
  `locked_by` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shedlock
-- ----------------------------
INSERT INTO `shedlock` VALUES ('shedLockTest', '2021-09-05 19:00:10.005', '2021-09-05 19:00:00.014', 'DESKTOP-4OHH3FF');

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES (1, '1');
INSERT INTO `test` VALUES (2, '2');
INSERT INTO `test` VALUES (3, '3');
INSERT INTO `test` VALUES (4, '4');
INSERT INTO `test` VALUES (5, '5');

-- ----------------------------
-- Table structure for test_mp_ar
-- ----------------------------
DROP TABLE IF EXISTS `test_mp_ar`;
CREATE TABLE `test_mp_ar`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test_mp_ar
-- ----------------------------
INSERT INTO `test_mp_ar` VALUES (47, '111');
INSERT INTO `test_mp_ar` VALUES (49, '111');
INSERT INTO `test_mp_ar` VALUES (50, '111');
INSERT INTO `test_mp_ar` VALUES (51, '111');
INSERT INTO `test_mp_ar` VALUES (52, '111');
INSERT INTO `test_mp_ar` VALUES (53, '111');
INSERT INTO `test_mp_ar` VALUES (54, '111');
INSERT INTO `test_mp_ar` VALUES (55, '111');
INSERT INTO `test_mp_ar` VALUES (56, '1121');
INSERT INTO `test_mp_ar` VALUES (57, '1121');
INSERT INTO `test_mp_ar` VALUES (60, '1121');
INSERT INTO `test_mp_ar` VALUES (61, '1121');
INSERT INTO `test_mp_ar` VALUES (63, '1121');
INSERT INTO `test_mp_ar` VALUES (64, '1121');
INSERT INTO `test_mp_ar` VALUES (65, '1121');
INSERT INTO `test_mp_ar` VALUES (66, '1121');
INSERT INTO `test_mp_ar` VALUES (70, 'test1');
INSERT INTO `test_mp_ar` VALUES (71, '111');

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `user_real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `user_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `user_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话号码',
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_status` smallint(0) NULL DEFAULT 1 COMMENT '0 停用 1 启用',
  `user_create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `user_update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `user_name`(`user_name`) USING BTREE,
  INDEX `user_ibfk_1`(`user_status`) USING BTREE,
  CONSTRAINT `user_info_ibfk_1` FOREIGN KEY (`user_status`) REFERENCES `user_status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 48 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'zhangsan', '张三', '763f2de02b9028330f49663c392377a9b01f02279f49a864', '13879145689', '', 1, '2021-02-22 14:49:26', '2021-03-10 10:57:08');
INSERT INTO `user_info` VALUES (17, 'zhaoliu', '赵六3', 'f4b11258c837f1df56532362485d3287a43ba19b78505731', '15867890043', '12334@qq.com', 1, '2021-02-22 14:51:21', '2021-03-11 13:34:52');
INSERT INTO `user_info` VALUES (18, 'wangwu', '王五', '16ab61a92562b46b2959370c76fd28b9247692314f70d76f', '13879145689', '123@qq.com', 1, '2021-02-22 15:30:35', '2021-02-22 15:30:35');
INSERT INTO `user_info` VALUES (20, 'wangwu2', '王五2', '813299228e58646424a20b2115e306d88238469f24f1ac0e', '13879145689', '123@qq.com', 1, '2021-02-22 15:33:08', '2021-02-22 15:33:08');
INSERT INTO `user_info` VALUES (22, 'user1', '用户1', 'b8787f176263d95689f5f480e2b865b0a751b91d1632ba30', '158784579512', '12334@qq.com', 1, '2021-02-22 15:36:39', '2021-02-22 15:43:16');
INSERT INTO `user_info` VALUES (23, 'user2', '用户2', '06d388e18c2bc3da81b4609769617d64a594f60501a2ff1c', '15878457951', '12334@qq.com', 1, '2021-02-22 16:05:17', '2021-02-24 10:48:07');
INSERT INTO `user_info` VALUES (28, 'test1', '测试1', 'f6c58dc75423004f6c46bb7248d79424b743c41605859279', NULL, NULL, 1, '2021-02-24 15:33:35', '2021-02-24 15:33:35');
INSERT INTO `user_info` VALUES (34, 'admin', NULL, '139e7b60c568e39341379230b7493529e95b797534105a01', NULL, NULL, 1, '2021-02-25 13:24:36', '2021-02-25 13:24:36');
INSERT INTO `user_info` VALUES (35, 'wangwu8', '王五8', '88344550ed3225184e66742ce3416500ee9ab54194384645', '13879145689', '123@qq.com', 1, '2021-02-25 14:19:14', '2021-02-25 14:19:14');
INSERT INTO `user_info` VALUES (37, 'wangwu9', '王五9', 'a6270cc1820568c52949419056566fe9879a50a41b351094', '13879145689', '123@qq.com', 1, '2021-02-25 14:21:02', '2021-02-25 14:21:02');
INSERT INTO `user_info` VALUES (38, 'wangwu10', '王五10', '694698f7bb61e2bc67c0e396e6842e76fe4d317010c9562e', '13879145689', '123@qq.com', 1, '2021-02-25 14:21:42', '2021-02-25 14:21:42');
INSERT INTO `user_info` VALUES (39, 'wangwu12', '王五12', 'a8e724c9565262f43bf30f8fa3bd25e7d30b624762836695', '13879145689', '123@qq.com', 1, '2021-02-25 14:22:31', '2021-02-25 14:22:31');
INSERT INTO `user_info` VALUES (40, 'test', '测试数据', 'd8d765133e9462c36f280c4bb31a5cd9ff78621c0790a80a', NULL, NULL, 1, '2021-03-01 14:36:09', '2021-03-01 14:36:09');
INSERT INTO `user_info` VALUES (41, 'test5', 'test5', 'e3a129b3c24556d58112d77ec4725e12e73217460a96117c', NULL, NULL, 0, '2021-03-01 14:40:32', '2021-03-01 14:40:32');
INSERT INTO `user_info` VALUES (42, 'test1234', '测试', '525175b3fb3aa5c40f100e60806a87371236638968c0be04', '', 'lyr@163.com', 1, '2021-03-05 10:49:56', '2021-03-05 14:48:45');
INSERT INTO `user_info` VALUES (43, 'lisi02', 'aaa', '89b781950d41b35e17935961534357005f0e40413da22274', '18811451234', '1030355111@qq.com', 1, '2021-03-10 17:56:37', '2021-03-10 17:56:37');
INSERT INTO `user_info` VALUES (46, 'test22', '测试用户1', '967491e2fd67c8a75621865e927b6b970b3c08db1de4911f', '15263524152', '123456@163.com', 1, '2021-03-11 13:38:15', '2021-03-11 13:38:15');
INSERT INTO `user_info` VALUES (47, 'test33', '测试3', '852097269d9fc2cd69c85583527985b74f7874ea3205fb84', '18596857485', '123456@163.com', 1, '2021-03-11 13:43:54', '2021-03-11 13:43:54');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `user_id` int(0) NOT NULL,
  `role_id` int(0) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE,
  INDEX `user_role_ibfk_2`(`role_id`) USING BTREE,
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user_info` (`user_id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (17, 2);
INSERT INTO `user_role` VALUES (20, 2);
INSERT INTO `user_role` VALUES (22, 2);
INSERT INTO `user_role` VALUES (23, 2);
INSERT INTO `user_role` VALUES (39, 2);
INSERT INTO `user_role` VALUES (46, 2);
INSERT INTO `user_role` VALUES (47, 2);
INSERT INTO `user_role` VALUES (1, 4);
INSERT INTO `user_role` VALUES (40, 4);
INSERT INTO `user_role` VALUES (43, 4);
INSERT INTO `user_role` VALUES (40, 5);
INSERT INTO `user_role` VALUES (41, 5);
INSERT INTO `user_role` VALUES (20, 7);
INSERT INTO `user_role` VALUES (28, 7);
INSERT INTO `user_role` VALUES (42, 7);
INSERT INTO `user_role` VALUES (46, 7);
INSERT INTO `user_role` VALUES (34, 11);

-- ----------------------------
-- Table structure for user_status
-- ----------------------------
DROP TABLE IF EXISTS `user_status`;
CREATE TABLE `user_status`  (
  `id` smallint(0) NOT NULL,
  `user_status_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_status
-- ----------------------------
INSERT INTO `user_status` VALUES (0, '不启用');
INSERT INTO `user_status` VALUES (1, '启用');

-- ----------------------------
-- Table structure for ws_data
-- ----------------------------
DROP TABLE IF EXISTS `ws_data`;
CREATE TABLE `ws_data`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `msg_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `user_identify` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `persistent` bit(1) NULL DEFAULT b'1',
  `data` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
  `destination` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `ack` bit(1) NULL DEFAULT b'0',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10749 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ws_data
-- ----------------------------
INSERT INTO `ws_data` VALUES (10549, 'BUSINESS', 'caf54a78-38f8-40b9-9664-154fafef9949', 'admin', b'1', '测试点对点数据，持久化1630759800029', '/queue/message', b'0', '2021-09-04 20:50:00');
INSERT INTO `ws_data` VALUES (10550, 'BUSINESS', '1f84b262-e912-4a16-94a7-61ecf83ca83e', 'admin', b'1', '测试点对点数据，持久化1630759800007', '/queue/message', b'0', '2021-09-04 20:50:00');
INSERT INTO `ws_data` VALUES (10551, 'BUSINESS', '8f99f618-5e90-443b-a43b-10ef9037531c', 'admin', b'1', '测试点对点数据，持久化1630759830005', '/queue/message', b'0', '2021-09-04 20:50:29');
INSERT INTO `ws_data` VALUES (10552, 'BUSINESS', 'fb4327a0-edd0-448e-9d12-ef76756bf41e', 'admin', b'1', '测试点对点数据，持久化1630759830007', '/queue/message', b'0', '2021-09-04 20:50:30');
INSERT INTO `ws_data` VALUES (10553, 'BUSINESS', '9865d3e4-befb-44de-aba0-82fd99e61a4d', 'admin', b'1', '测试点对点数据，持久化1630759860004', '/queue/message', b'0', '2021-09-04 20:50:59');
INSERT INTO `ws_data` VALUES (10554, 'BUSINESS', '7def2ff7-93cf-4b5e-a636-9459b6a6ea27', 'admin', b'1', '测试点对点数据，持久化1630759860006', '/queue/message', b'0', '2021-09-04 20:51:00');
INSERT INTO `ws_data` VALUES (10555, 'BUSINESS', 'cbbbbf57-bfa8-4870-854d-cd7766e42f4a', 'admin', b'1', '测试点对点数据，持久化1630759890003', '/queue/message', b'0', '2021-09-04 20:51:29');
INSERT INTO `ws_data` VALUES (10556, 'BUSINESS', '30ca83c2-6286-4856-9e5e-5bf1ac20ef55', 'admin', b'1', '测试点对点数据，持久化1630759890004', '/queue/message', b'0', '2021-09-04 20:51:30');
INSERT INTO `ws_data` VALUES (10557, 'BUSINESS', 'b1ea8e08-3957-4bf5-9ca7-4b14b5cd84e6', 'admin', b'1', '测试点对点数据，持久化1630759920009', '/queue/message', b'0', '2021-09-04 20:52:00');
INSERT INTO `ws_data` VALUES (10558, 'BUSINESS', '1cb053ea-f493-4208-b965-a80420fd0baa', 'admin', b'1', '测试点对点数据，持久化1630759920037', '/queue/message', b'0', '2021-09-04 20:52:00');
INSERT INTO `ws_data` VALUES (10559, 'BUSINESS', '16c92bcf-4448-436b-a2f1-f255c2c89c08', 'admin', b'1', '测试点对点数据，持久化1630759950003', '/queue/message', b'0', '2021-09-04 20:52:29');
INSERT INTO `ws_data` VALUES (10560, 'BUSINESS', 'db5c1f83-ce9a-4116-b16a-1e6362b9691a', 'admin', b'1', '测试点对点数据，持久化1630759950006', '/queue/message', b'0', '2021-09-04 20:52:30');
INSERT INTO `ws_data` VALUES (10561, 'BUSINESS', 'bbb7e8f7-9917-4ea2-acff-e573a09476ff', 'admin', b'1', '测试点对点数据，持久化1630759980006', '/queue/message', b'0', '2021-09-04 20:52:59');
INSERT INTO `ws_data` VALUES (10562, 'BUSINESS', 'ecf77b36-8efb-4d00-b307-30632087f171', 'admin', b'1', '测试点对点数据，持久化1630759980005', '/queue/message', b'0', '2021-09-04 20:53:00');
INSERT INTO `ws_data` VALUES (10563, 'BUSINESS', '989bc3ac-f149-42ce-a747-24f97b3f7a10', 'admin', b'1', '测试点对点数据，持久化1630760010003', '/queue/message', b'0', '2021-09-04 20:53:29');
INSERT INTO `ws_data` VALUES (10564, 'BUSINESS', 'af8adcca-6966-4faa-bd3b-dc119178435b', 'admin', b'1', '测试点对点数据，持久化1630760010017', '/queue/message', b'0', '2021-09-04 20:53:30');
INSERT INTO `ws_data` VALUES (10565, 'BUSINESS', '1f7a80e4-f7be-4642-b8e4-7457ff47dbd7', 'admin', b'1', '测试点对点数据，持久化1630760040010', '/queue/message', b'0', '2021-09-04 20:53:59');
INSERT INTO `ws_data` VALUES (10566, 'BUSINESS', '8544979a-3c22-4614-a718-fc4b8b56f3c3', 'admin', b'1', '测试点对点数据，持久化1630760040018', '/queue/message', b'0', '2021-09-04 20:54:00');
INSERT INTO `ws_data` VALUES (10567, 'BUSINESS', '29d67c69-45a3-44dd-bb2e-47ccecb6f6d5', 'admin', b'1', '测试点对点数据，持久化1630760070003', '/queue/message', b'0', '2021-09-04 20:54:29');
INSERT INTO `ws_data` VALUES (10568, 'BUSINESS', 'b366b1ab-d169-4799-bb03-098748bbef9c', 'admin', b'1', '测试点对点数据，持久化1630760070016', '/queue/message', b'0', '2021-09-04 20:54:30');
INSERT INTO `ws_data` VALUES (10569, 'BUSINESS', '3392b120-8a3e-4075-bb1d-ccd6e238d300', 'admin', b'1', '测试点对点数据，持久化1630760100004', '/queue/message', b'0', '2021-09-04 20:54:59');
INSERT INTO `ws_data` VALUES (10570, 'BUSINESS', '281c9c8f-0ee8-49f4-ac55-83eb87596f11', 'admin', b'1', '测试点对点数据，持久化1630760100014', '/queue/message', b'0', '2021-09-04 20:55:00');
INSERT INTO `ws_data` VALUES (10571, 'BUSINESS', '96e8b359-a69e-40f0-8fe3-df27b7f16847', 'admin', b'1', '测试点对点数据，持久化1630760130003', '/queue/message', b'0', '2021-09-04 20:55:29');
INSERT INTO `ws_data` VALUES (10572, 'BUSINESS', '2a92217d-8b9e-4551-8bdb-3af543c9cb1a', 'admin', b'1', '测试点对点数据，持久化1630760130014', '/queue/message', b'0', '2021-09-04 20:55:30');
INSERT INTO `ws_data` VALUES (10573, 'BUSINESS', '7fdd0035-4881-45a3-8e43-52a1ab7c62f9', 'admin', b'1', '测试点对点数据，持久化1630760160004', '/queue/message', b'0', '2021-09-04 20:55:59');
INSERT INTO `ws_data` VALUES (10574, 'BUSINESS', 'daa71fcb-3bb4-40e5-bd75-c7dce7877251', 'admin', b'1', '测试点对点数据，持久化1630760160014', '/queue/message', b'0', '2021-09-04 20:56:00');
INSERT INTO `ws_data` VALUES (10575, 'BUSINESS', '2c101dc1-6653-4a5f-9f8b-4108e48dafdb', 'admin', b'1', '测试点对点数据，持久化1630760190009', '/queue/message', b'0', '2021-09-04 20:56:29');
INSERT INTO `ws_data` VALUES (10576, 'BUSINESS', '27d9fc21-80fc-40d4-8997-45f59bfe55aa', 'admin', b'1', '测试点对点数据，持久化1630760190011', '/queue/message', b'0', '2021-09-04 20:56:30');
INSERT INTO `ws_data` VALUES (10577, 'BUSINESS', '4814e774-f5b3-4b83-b5d0-722f2e5bdc4d', 'admin', b'1', '测试点对点数据，持久化1630760220004', '/queue/message', b'0', '2021-09-04 20:56:59');
INSERT INTO `ws_data` VALUES (10578, 'BUSINESS', '4f241d01-ac0f-4e1d-8bef-5e099e94f493', 'admin', b'1', '测试点对点数据，持久化1630760220014', '/queue/message', b'0', '2021-09-04 20:57:00');
INSERT INTO `ws_data` VALUES (10579, 'BUSINESS', '0bdc1b80-c485-47eb-9625-5ba8a7a1c052', 'admin', b'1', '测试点对点数据，持久化1630760250004', '/queue/message', b'0', '2021-09-04 20:57:29');
INSERT INTO `ws_data` VALUES (10580, 'BUSINESS', '4ea29964-dbf5-49ab-992f-ddb91803e576', 'admin', b'1', '测试点对点数据，持久化1630760250012', '/queue/message', b'0', '2021-09-04 20:57:30');
INSERT INTO `ws_data` VALUES (10581, 'BUSINESS', '07c98096-c7f9-4795-beb9-eb568612e740', 'admin', b'1', '测试点对点数据，持久化1630760280002', '/queue/message', b'0', '2021-09-04 20:57:59');
INSERT INTO `ws_data` VALUES (10582, 'BUSINESS', '97fdb851-36f2-40a6-9e0e-8ee601ea0854', 'admin', b'1', '测试点对点数据，持久化1630760280026', '/queue/message', b'0', '2021-09-04 20:58:00');
INSERT INTO `ws_data` VALUES (10583, 'BUSINESS', '2a1b20ec-594e-4771-b574-e60dd7489a4f', 'admin', b'1', '测试点对点数据，持久化1630760310009', '/queue/message', b'0', '2021-09-04 20:58:29');
INSERT INTO `ws_data` VALUES (10584, 'BUSINESS', '7297a370-db49-4f13-a429-38ad8c6a66cb', 'admin', b'1', '测试点对点数据，持久化1630760310014', '/queue/message', b'0', '2021-09-04 20:58:30');
INSERT INTO `ws_data` VALUES (10585, 'BUSINESS', '3e5458c1-ef6f-45af-bf00-22001f1cbb54', 'admin', b'1', '测试点对点数据，持久化1630760340003', '/queue/message', b'0', '2021-09-04 20:58:59');
INSERT INTO `ws_data` VALUES (10586, 'BUSINESS', '8d23ddb8-2056-4435-b7ca-d28542b3d489', 'admin', b'1', '测试点对点数据，持久化1630760340007', '/queue/message', b'0', '2021-09-04 20:59:00');
INSERT INTO `ws_data` VALUES (10587, 'BUSINESS', 'a7679a3f-c3ef-43ec-b979-539aa83f2516', 'admin', b'1', '测试点对点数据，持久化1630760370003', '/queue/message', b'0', '2021-09-04 20:59:29');
INSERT INTO `ws_data` VALUES (10588, 'BUSINESS', 'd2252072-2718-47f6-b25e-e9ef33386d45', 'admin', b'1', '测试点对点数据，持久化1630760370006', '/queue/message', b'0', '2021-09-04 20:59:30');
INSERT INTO `ws_data` VALUES (10589, 'BUSINESS', 'bbebab34-b4ec-4afd-968d-e24dd1c9485c', 'admin', b'1', '测试点对点数据，持久化1630760400004', '/queue/message', b'0', '2021-09-04 20:59:59');
INSERT INTO `ws_data` VALUES (10590, 'BUSINESS', '6f37840f-908a-44fc-b1cc-2c9988ce0742', 'admin', b'1', '测试点对点数据，持久化1630760400026', '/queue/message', b'0', '2021-09-04 21:00:00');
INSERT INTO `ws_data` VALUES (10591, 'BUSINESS', '69d0eec3-18fa-41c0-98f3-5c034878d566', 'admin', b'1', '测试点对点数据，持久化1630760430006', '/queue/message', b'0', '2021-09-04 21:00:29');
INSERT INTO `ws_data` VALUES (10592, 'BUSINESS', '3fa9953f-c3b1-4533-b57f-fc64185e4fe5', 'admin', b'1', '测试点对点数据，持久化1630760430006', '/queue/message', b'0', '2021-09-04 21:00:30');
INSERT INTO `ws_data` VALUES (10593, 'BUSINESS', '27356c73-7006-49a9-a990-a8e8eb88a1eb', 'admin', b'1', '测试点对点数据，持久化1630760460005', '/queue/message', b'0', '2021-09-04 21:00:59');
INSERT INTO `ws_data` VALUES (10594, 'BUSINESS', '87cb3bdc-6336-4dc0-9a53-9f0f048b97ef', 'admin', b'1', '测试点对点数据，持久化1630760460005', '/queue/message', b'0', '2021-09-04 21:01:00');
INSERT INTO `ws_data` VALUES (10595, 'BUSINESS', 'd5fa7b5b-789c-463b-a0d4-ea6d9e209542', 'admin', b'1', '测试点对点数据，持久化1630760490004', '/queue/message', b'0', '2021-09-04 21:01:29');
INSERT INTO `ws_data` VALUES (10596, 'BUSINESS', 'eca152ee-97d4-4554-b471-1bb3a835bf99', 'admin', b'1', '测试点对点数据，持久化1630760490020', '/queue/message', b'0', '2021-09-04 21:01:30');
INSERT INTO `ws_data` VALUES (10597, 'BUSINESS', 'e1b7867d-6be5-444a-bd9d-99c308e1a144', 'admin', b'1', '测试点对点数据，持久化1630760520007', '/queue/message', b'0', '2021-09-04 21:02:00');
INSERT INTO `ws_data` VALUES (10598, 'BUSINESS', '966f4f38-c763-4a21-868b-00c615b720b4', 'admin', b'1', '测试点对点数据，持久化1630760520104', '/queue/message', b'0', '2021-09-04 21:02:00');
INSERT INTO `ws_data` VALUES (10599, 'BUSINESS', '03a3fb41-d535-4e89-81f5-54ef0af65e89', 'admin', b'1', '测试点对点数据，持久化1630760550008', '/queue/message', b'0', '2021-09-04 21:02:29');
INSERT INTO `ws_data` VALUES (10600, 'BUSINESS', '9c3a9977-a4c2-41f7-b327-2ed1f0ceb96e', 'admin', b'1', '测试点对点数据，持久化1630760550006', '/queue/message', b'0', '2021-09-04 21:02:30');
INSERT INTO `ws_data` VALUES (10601, 'BUSINESS', 'e44f0432-6978-4292-bde7-aa9a96d951e2', 'admin', b'1', '测试点对点数据，持久化1630760580005', '/queue/message', b'0', '2021-09-04 21:02:59');
INSERT INTO `ws_data` VALUES (10602, 'BUSINESS', '58be78ea-8cd2-4063-894e-754163cccaed', 'admin', b'1', '测试点对点数据，持久化1630760580016', '/queue/message', b'0', '2021-09-04 21:03:00');
INSERT INTO `ws_data` VALUES (10603, 'BUSINESS', '03b3611b-af0a-44a9-9d2d-cdc26e025eaf', 'admin', b'1', '测试点对点数据，持久化1630760610002', '/queue/message', b'0', '2021-09-04 21:03:29');
INSERT INTO `ws_data` VALUES (10604, 'BUSINESS', '61b89d6f-9d22-49c3-aabd-7d177c3cdf54', 'admin', b'1', '测试点对点数据，持久化1630760610003', '/queue/message', b'0', '2021-09-04 21:03:30');
INSERT INTO `ws_data` VALUES (10605, 'BUSINESS', 'a08abf7a-c56e-4c59-8c1e-5d3cd16d5977', 'admin', b'1', '测试点对点数据，持久化1630760640007', '/queue/message', b'0', '2021-09-04 21:03:59');
INSERT INTO `ws_data` VALUES (10606, 'BUSINESS', '08f5919e-ad51-4a24-9b57-0702f46fae87', 'admin', b'1', '测试点对点数据，持久化1630760640004', '/queue/message', b'0', '2021-09-04 21:04:00');
INSERT INTO `ws_data` VALUES (10607, 'BUSINESS', '039b9579-7a2e-4666-8619-73a30ca8792f', 'admin', b'1', '测试点对点数据，持久化1630760670004', '/queue/message', b'0', '2021-09-04 21:04:29');
INSERT INTO `ws_data` VALUES (10608, 'BUSINESS', 'b40a5aea-3cc8-465c-b421-02652ce832c3', 'admin', b'1', '测试点对点数据，持久化1630760670018', '/queue/message', b'0', '2021-09-04 21:04:30');
INSERT INTO `ws_data` VALUES (10609, 'BUSINESS', '5c6dedc9-ccaf-498e-814e-86301bd9027a', 'admin', b'1', '测试点对点数据，持久化1630760700006', '/queue/message', b'0', '2021-09-04 21:04:59');
INSERT INTO `ws_data` VALUES (10610, 'BUSINESS', '3d80d8ca-19ea-4322-a64d-5bcddb0048f9', 'admin', b'1', '测试点对点数据，持久化1630760700013', '/queue/message', b'0', '2021-09-04 21:05:00');
INSERT INTO `ws_data` VALUES (10611, 'BUSINESS', 'a879e33d-622b-478e-bcaa-988da52f7667', 'admin', b'1', '测试点对点数据，持久化1630760730007', '/queue/message', b'0', '2021-09-04 21:05:29');
INSERT INTO `ws_data` VALUES (10612, 'BUSINESS', 'f6f60014-3ebd-4bce-ac8a-cb16ead23ea7', 'admin', b'1', '测试点对点数据，持久化1630760730015', '/queue/message', b'0', '2021-09-04 21:05:30');
INSERT INTO `ws_data` VALUES (10613, 'BUSINESS', '0fbb9d01-52f3-4027-a0b7-03ea22a994ca', 'admin', b'1', '测试点对点数据，持久化1630760760005', '/queue/message', b'0', '2021-09-04 21:05:59');
INSERT INTO `ws_data` VALUES (10614, 'BUSINESS', 'c05584ed-5115-430d-9123-91cb048dd895', 'admin', b'1', '测试点对点数据，持久化1630760760012', '/queue/message', b'0', '2021-09-04 21:06:00');
INSERT INTO `ws_data` VALUES (10615, 'BUSINESS', 'f4fe7bd4-9b9e-4a3b-b05e-3f77a894248a', 'admin', b'1', '测试点对点数据，持久化1630760790003', '/queue/message', b'0', '2021-09-04 21:06:29');
INSERT INTO `ws_data` VALUES (10616, 'BUSINESS', 'af185d49-fd9d-40b5-a491-866c5665fc4b', 'admin', b'1', '测试点对点数据，持久化1630760790008', '/queue/message', b'0', '2021-09-04 21:06:30');
INSERT INTO `ws_data` VALUES (10617, 'BUSINESS', 'f88c7d6e-4d54-458b-8850-ec69cf0ac01e', 'admin', b'1', '测试点对点数据，持久化1630760820003', '/queue/message', b'0', '2021-09-04 21:06:59');
INSERT INTO `ws_data` VALUES (10618, 'BUSINESS', 'c5a15a0c-27ab-4020-826c-581312943883', 'admin', b'1', '测试点对点数据，持久化1630760820014', '/queue/message', b'0', '2021-09-04 21:07:00');
INSERT INTO `ws_data` VALUES (10619, 'BUSINESS', '367a2e0d-2b88-489a-8c42-2d45bc49ba86', 'admin', b'1', '测试点对点数据，持久化1630760850004', '/queue/message', b'0', '2021-09-04 21:07:29');
INSERT INTO `ws_data` VALUES (10620, 'BUSINESS', 'dd84d23b-c6d0-430a-a968-f64890f935da', 'admin', b'1', '测试点对点数据，持久化1630760850008', '/queue/message', b'0', '2021-09-04 21:07:30');
INSERT INTO `ws_data` VALUES (10621, 'BUSINESS', '9ab9b1dc-e5bf-4f2a-a653-2f79c3c7b9b0', 'admin', b'1', '测试点对点数据，持久化1630760880006', '/queue/message', b'0', '2021-09-04 21:07:59');
INSERT INTO `ws_data` VALUES (10622, 'BUSINESS', '36491e24-0c28-4e05-b507-92b7bff12e48', 'admin', b'1', '测试点对点数据，持久化1630760880014', '/queue/message', b'0', '2021-09-04 21:08:00');
INSERT INTO `ws_data` VALUES (10623, 'BUSINESS', '2b47198c-74f2-4e97-aa2c-fae38b83732f', 'admin', b'1', '测试点对点数据，持久化1630760910003', '/queue/message', b'0', '2021-09-04 21:08:29');
INSERT INTO `ws_data` VALUES (10624, 'BUSINESS', '8531f35a-72f6-48f3-803f-87032d35eabf', 'admin', b'1', '测试点对点数据，持久化1630760910014', '/queue/message', b'0', '2021-09-04 21:08:30');
INSERT INTO `ws_data` VALUES (10625, 'BUSINESS', 'c58e7288-c198-4d70-9102-cd4550af7d32', 'admin', b'1', '测试点对点数据，持久化1630760940008', '/queue/message', b'0', '2021-09-04 21:08:59');
INSERT INTO `ws_data` VALUES (10626, 'BUSINESS', 'f1e0fc7b-2d13-4632-92a3-3fe691c864d7', 'admin', b'1', '测试点对点数据，持久化1630760940010', '/queue/message', b'0', '2021-09-04 21:09:00');
INSERT INTO `ws_data` VALUES (10627, 'BUSINESS', '975df348-1153-4ee9-aa0b-ea4b6e82dda3', 'admin', b'1', '测试点对点数据，持久化1630760970003', '/queue/message', b'0', '2021-09-04 21:09:29');
INSERT INTO `ws_data` VALUES (10628, 'BUSINESS', '82b47451-8b50-48ae-9bda-09c3d51240cd', 'admin', b'1', '测试点对点数据，持久化1630760970015', '/queue/message', b'0', '2021-09-04 21:09:30');
INSERT INTO `ws_data` VALUES (10629, 'BUSINESS', '0c3098c0-48ee-4d3c-a8a3-dff52567bcf9', 'admin', b'1', '测试点对点数据，持久化1630761000004', '/queue/message', b'0', '2021-09-04 21:09:59');
INSERT INTO `ws_data` VALUES (10630, 'BUSINESS', 'e671d135-10af-4ab7-ab89-c205b73cd6be', 'admin', b'1', '测试点对点数据，持久化1630761000016', '/queue/message', b'0', '2021-09-04 21:10:00');
INSERT INTO `ws_data` VALUES (10631, 'BUSINESS', 'e9de3a5a-350e-437f-b7ce-e83e090a30c3', 'admin', b'1', '测试点对点数据，持久化1630761030004', '/queue/message', b'0', '2021-09-04 21:10:29');
INSERT INTO `ws_data` VALUES (10632, 'BUSINESS', '3e391e55-4efb-4979-8b1c-c178e232453d', 'admin', b'1', '测试点对点数据，持久化1630761030010', '/queue/message', b'0', '2021-09-04 21:10:30');
INSERT INTO `ws_data` VALUES (10633, 'BUSINESS', '0853471e-bf9d-4b73-a843-a0711b85723f', 'admin', b'1', '测试点对点数据，持久化1630761060005', '/queue/message', b'0', '2021-09-04 21:10:59');
INSERT INTO `ws_data` VALUES (10634, 'BUSINESS', '350bd488-a47a-461f-8bd1-e0abfe81f2a5', 'admin', b'1', '测试点对点数据，持久化1630761060010', '/queue/message', b'0', '2021-09-04 21:11:00');
INSERT INTO `ws_data` VALUES (10635, 'BUSINESS', 'adceba27-827e-4e71-8fa7-bb50163cda10', 'admin', b'1', '测试点对点数据，持久化1630761090005', '/queue/message', b'0', '2021-09-04 21:11:29');
INSERT INTO `ws_data` VALUES (10636, 'BUSINESS', 'fbe8be5b-6a0d-402b-a788-f90848479b31', 'admin', b'1', '测试点对点数据，持久化1630761090009', '/queue/message', b'0', '2021-09-04 21:11:30');
INSERT INTO `ws_data` VALUES (10637, 'BUSINESS', '73e6ec03-55c9-4c3a-b0d8-4fd4de66e0dc', 'admin', b'1', '测试点对点数据，持久化1630761120004', '/queue/message', b'0', '2021-09-04 21:11:59');
INSERT INTO `ws_data` VALUES (10638, 'BUSINESS', 'bad894bf-4bd3-4957-ae0b-6890d001e45a', 'admin', b'1', '测试点对点数据，持久化1630761120015', '/queue/message', b'0', '2021-09-04 21:12:00');
INSERT INTO `ws_data` VALUES (10639, 'BUSINESS', '6a71febc-35be-422b-9831-f0e1eebd34f9', 'admin', b'1', '测试点对点数据，持久化1630761150004', '/queue/message', b'0', '2021-09-04 21:12:29');
INSERT INTO `ws_data` VALUES (10640, 'BUSINESS', '70b91429-99c2-4c25-9fca-84b1c051c1b3', 'admin', b'1', '测试点对点数据，持久化1630761150015', '/queue/message', b'0', '2021-09-04 21:12:30');
INSERT INTO `ws_data` VALUES (10641, 'BUSINESS', '153f70db-8a0f-45c1-a08c-d9fc8ae3a540', 'admin', b'1', '测试点对点数据，持久化1630761180005', '/queue/message', b'0', '2021-09-04 21:12:59');
INSERT INTO `ws_data` VALUES (10642, 'BUSINESS', '6bb36813-92de-48db-ad67-56807449cef5', 'admin', b'1', '测试点对点数据，持久化1630761180010', '/queue/message', b'0', '2021-09-04 21:13:00');
INSERT INTO `ws_data` VALUES (10643, 'BUSINESS', 'd6dc5182-db7c-49fc-adfb-1011da50a0e7', 'admin', b'1', '测试点对点数据，持久化1630761210006', '/queue/message', b'0', '2021-09-04 21:13:29');
INSERT INTO `ws_data` VALUES (10644, 'BUSINESS', '3d61243f-cddb-4c7e-a37e-f0dd887236f5', 'admin', b'1', '测试点对点数据，持久化1630761210013', '/queue/message', b'0', '2021-09-04 21:13:30');
INSERT INTO `ws_data` VALUES (10645, 'BUSINESS', '7e406f66-ad98-4359-9b7f-755ab6151747', 'admin', b'1', '测试点对点数据，持久化1630761240003', '/queue/message', b'0', '2021-09-04 21:13:59');
INSERT INTO `ws_data` VALUES (10646, 'BUSINESS', 'c2ee8aee-9876-49e9-b162-b9d77c7436a8', 'admin', b'1', '测试点对点数据，持久化1630761240012', '/queue/message', b'0', '2021-09-04 21:14:00');
INSERT INTO `ws_data` VALUES (10647, 'BUSINESS', '50c35d30-16de-4383-9e0a-1a98850e83de', 'admin', b'1', '测试点对点数据，持久化1630761270003', '/queue/message', b'0', '2021-09-04 21:14:29');
INSERT INTO `ws_data` VALUES (10648, 'BUSINESS', '89702ce2-6936-4b9b-a231-89f4eabc10dc', 'admin', b'1', '测试点对点数据，持久化1630761270014', '/queue/message', b'0', '2021-09-04 21:14:30');
INSERT INTO `ws_data` VALUES (10649, 'BUSINESS', '6adcb2b6-ebcb-4848-947f-89d6b6dc525a', 'admin', b'1', '测试点对点数据，持久化1630761300004', '/queue/message', b'0', '2021-09-04 21:14:59');
INSERT INTO `ws_data` VALUES (10650, 'BUSINESS', '72c8ce82-5c9a-4b87-bff6-10fc96339264', 'admin', b'1', '测试点对点数据，持久化1630761300015', '/queue/message', b'0', '2021-09-04 21:15:00');
INSERT INTO `ws_data` VALUES (10651, 'BUSINESS', '0aac5e19-b453-4cde-b1b0-e12eca2e4306', 'admin', b'1', '测试点对点数据，持久化1630761330004', '/queue/message', b'0', '2021-09-04 21:15:29');
INSERT INTO `ws_data` VALUES (10652, 'BUSINESS', '6024a448-b0bc-4c60-a5b9-c0de81e8553d', 'admin', b'1', '测试点对点数据，持久化1630761330016', '/queue/message', b'0', '2021-09-04 21:15:30');
INSERT INTO `ws_data` VALUES (10653, 'BUSINESS', 'f67d7dae-95d8-42f8-b69d-32745c4ebd20', 'admin', b'1', '测试点对点数据，持久化1630761360004', '/queue/message', b'0', '2021-09-04 21:15:59');
INSERT INTO `ws_data` VALUES (10654, 'BUSINESS', '2356ba1d-ac14-4fa6-98f4-a1344e83a3df', 'admin', b'1', '测试点对点数据，持久化1630761360006', '/queue/message', b'0', '2021-09-04 21:16:00');
INSERT INTO `ws_data` VALUES (10655, 'BUSINESS', '02f0d023-1d0c-4a50-8e23-b33c058f8d8c', 'admin', b'1', '测试点对点数据，持久化1630761390006', '/queue/message', b'0', '2021-09-04 21:16:29');
INSERT INTO `ws_data` VALUES (10656, 'BUSINESS', '3e799b63-adde-4a52-92f4-326361f7b980', 'admin', b'1', '测试点对点数据，持久化1630761390006', '/queue/message', b'0', '2021-09-04 21:16:30');
INSERT INTO `ws_data` VALUES (10657, 'BUSINESS', '964273cc-3cea-4743-b0da-ca427f63c1ab', 'admin', b'1', '测试点对点数据，持久化1630761420004', '/queue/message', b'0', '2021-09-04 21:16:59');
INSERT INTO `ws_data` VALUES (10658, 'BUSINESS', 'a8117209-da76-4f08-922e-6bd7558d029d', 'admin', b'1', '测试点对点数据，持久化1630761420009', '/queue/message', b'0', '2021-09-04 21:17:00');
INSERT INTO `ws_data` VALUES (10659, 'BUSINESS', '7ee15fff-8fa5-454d-9585-a39eb53572e0', 'admin', b'1', '测试点对点数据，持久化1630761450004', '/queue/message', b'0', '2021-09-04 21:17:29');
INSERT INTO `ws_data` VALUES (10660, 'BUSINESS', '17fdcf22-35e6-4251-822d-03a6e303ec39', 'admin', b'1', '测试点对点数据，持久化1630761450004', '/queue/message', b'0', '2021-09-04 21:17:30');
INSERT INTO `ws_data` VALUES (10661, 'BUSINESS', 'ea21b295-41e1-4cd2-9dfc-25dbd72423aa', 'admin', b'1', '测试点对点数据，持久化1630761480005', '/queue/message', b'0', '2021-09-04 21:17:59');
INSERT INTO `ws_data` VALUES (10662, 'BUSINESS', '9de12054-f7a1-431b-8ecb-c9788899fcf3', 'admin', b'1', '测试点对点数据，持久化1630761480008', '/queue/message', b'0', '2021-09-04 21:18:00');
INSERT INTO `ws_data` VALUES (10663, 'BUSINESS', '49e64534-546c-48a2-b331-e5cc04a8c24e', 'admin', b'1', '测试点对点数据，持久化1630761510003', '/queue/message', b'0', '2021-09-04 21:18:29');
INSERT INTO `ws_data` VALUES (10664, 'BUSINESS', '7ad77ead-39e2-4c17-80a5-3fadb3d7a74a', 'admin', b'1', '测试点对点数据，持久化1630761510003', '/queue/message', b'0', '2021-09-04 21:18:30');
INSERT INTO `ws_data` VALUES (10665, 'BUSINESS', '66889838-ff52-4989-bad0-d9caffe143c3', 'admin', b'1', '测试点对点数据，持久化1630761540004', '/queue/message', b'0', '2021-09-04 21:18:59');
INSERT INTO `ws_data` VALUES (10666, 'BUSINESS', '6be22979-b157-437e-acf0-252e99e9a9a4', 'admin', b'1', '测试点对点数据，持久化1630761540007', '/queue/message', b'0', '2021-09-04 21:19:00');
INSERT INTO `ws_data` VALUES (10667, 'BUSINESS', 'd3595bec-592e-4b95-93ce-f6b0e11b64cb', 'admin', b'1', '测试点对点数据，持久化1630761570007', '/queue/message', b'0', '2021-09-04 21:19:29');
INSERT INTO `ws_data` VALUES (10668, 'BUSINESS', '17560824-612d-4e9e-8ffb-0add839ed6d3', 'admin', b'1', '测试点对点数据，持久化1630761570008', '/queue/message', b'0', '2021-09-04 21:19:30');
INSERT INTO `ws_data` VALUES (10669, 'BUSINESS', '47d34c70-eb20-4606-a860-39bc13b5dfc9', 'admin', b'1', '测试点对点数据，持久化1630761600004', '/queue/message', b'0', '2021-09-04 21:19:59');
INSERT INTO `ws_data` VALUES (10670, 'BUSINESS', '4f86148e-f481-4be1-824c-f1e6f1f94a0e', 'admin', b'1', '测试点对点数据，持久化1630761600004', '/queue/message', b'0', '2021-09-04 21:20:00');
INSERT INTO `ws_data` VALUES (10671, 'BUSINESS', 'a7a6dc47-7d8e-4859-a64e-70ba6fd3ea53', 'admin', b'1', '测试点对点数据，持久化1630761630003', '/queue/message', b'0', '2021-09-04 21:20:29');
INSERT INTO `ws_data` VALUES (10672, 'BUSINESS', '70744e9c-fd04-480b-a393-8b950d474ae6', 'admin', b'1', '测试点对点数据，持久化1630761630006', '/queue/message', b'0', '2021-09-04 21:20:30');
INSERT INTO `ws_data` VALUES (10673, 'BUSINESS', '425237f3-3621-448f-b6cb-29ef075162c8', 'admin', b'1', '测试点对点数据，持久化1630761660003', '/queue/message', b'0', '2021-09-04 21:20:59');
INSERT INTO `ws_data` VALUES (10674, 'BUSINESS', '969467b2-3ada-45d8-bc14-872ef0b31c47', 'admin', b'1', '测试点对点数据，持久化1630761660008', '/queue/message', b'0', '2021-09-04 21:21:00');
INSERT INTO `ws_data` VALUES (10675, 'BUSINESS', 'ecf96d9d-558d-4ea4-a35c-c560374d633e', 'admin', b'1', '测试点对点数据，持久化1630761690006', '/queue/message', b'0', '2021-09-04 21:21:29');
INSERT INTO `ws_data` VALUES (10676, 'BUSINESS', 'af798fb1-ba9e-4cf4-b107-7af38e30826a', 'admin', b'1', '测试点对点数据，持久化1630761690006', '/queue/message', b'0', '2021-09-04 21:21:30');
INSERT INTO `ws_data` VALUES (10677, 'BUSINESS', '43694378-39d8-4747-82c4-62cccbb8fe90', 'admin', b'1', '测试点对点数据，持久化1630761720005', '/queue/message', b'0', '2021-09-04 21:21:59');
INSERT INTO `ws_data` VALUES (10678, 'BUSINESS', '11eb991c-b6fe-4b86-b688-a8cfba6f8da4', 'admin', b'1', '测试点对点数据，持久化1630761720010', '/queue/message', b'0', '2021-09-04 21:22:00');
INSERT INTO `ws_data` VALUES (10679, 'BUSINESS', '6c408225-0244-4604-83e8-45db7357abfd', 'admin', b'1', '测试点对点数据，持久化1630761750002', '/queue/message', b'0', '2021-09-04 21:22:29');
INSERT INTO `ws_data` VALUES (10680, 'BUSINESS', '07ac193c-0c48-4606-99c5-f9b3abbd4e77', 'admin', b'1', '测试点对点数据，持久化1630761750010', '/queue/message', b'0', '2021-09-04 21:22:30');
INSERT INTO `ws_data` VALUES (10681, 'BUSINESS', 'dc6c27a3-0443-4b77-8fc7-73fb7c51bc32', 'admin', b'1', '测试点对点数据，持久化1630761780009', '/queue/message', b'0', '2021-09-04 21:22:59');
INSERT INTO `ws_data` VALUES (10682, 'BUSINESS', '859ed072-39f7-4eb5-9ce2-c8037c4973c2', 'admin', b'1', '测试点对点数据，持久化1630761780008', '/queue/message', b'0', '2021-09-04 21:23:00');
INSERT INTO `ws_data` VALUES (10683, 'BUSINESS', 'e6985bec-03b5-4195-8ecf-f7c7ccb3cea8', 'admin', b'1', '测试点对点数据，持久化1630761810009', '/queue/message', b'0', '2021-09-04 21:23:29');
INSERT INTO `ws_data` VALUES (10684, 'BUSINESS', 'dea3552c-7aea-4144-9ce9-17665a840f84', 'admin', b'1', '测试点对点数据，持久化1630761810010', '/queue/message', b'0', '2021-09-04 21:23:30');
INSERT INTO `ws_data` VALUES (10685, 'BUSINESS', 'c51e4508-c637-4b5a-a6fa-4f0bab20d49e', 'admin', b'1', '测试点对点数据，持久化1630761840005', '/queue/message', b'0', '2021-09-04 21:23:59');
INSERT INTO `ws_data` VALUES (10686, 'BUSINESS', '8ea5c3b7-a682-44aa-9384-79c2415b5336', 'admin', b'1', '测试点对点数据，持久化1630761840011', '/queue/message', b'0', '2021-09-04 21:24:00');
INSERT INTO `ws_data` VALUES (10687, 'BUSINESS', '4cdfb491-3351-4bcf-bef9-16a05570d88a', 'admin', b'1', '测试点对点数据，持久化1630761870003', '/queue/message', b'0', '2021-09-04 21:24:29');
INSERT INTO `ws_data` VALUES (10688, 'BUSINESS', '838d8317-69f3-408e-a8d9-252633b42652', 'admin', b'1', '测试点对点数据，持久化1630761870006', '/queue/message', b'0', '2021-09-04 21:24:30');
INSERT INTO `ws_data` VALUES (10689, 'BUSINESS', '4702de23-32c3-4a60-a062-db277558fa7a', 'admin', b'1', '测试点对点数据，持久化1630761900007', '/queue/message', b'0', '2021-09-04 21:24:59');
INSERT INTO `ws_data` VALUES (10690, 'BUSINESS', 'ad5d1124-3506-4220-a9ed-dcb65fb55a1d', 'admin', b'1', '测试点对点数据，持久化1630761900014', '/queue/message', b'0', '2021-09-04 21:25:00');
INSERT INTO `ws_data` VALUES (10691, 'BUSINESS', 'a574a136-103d-4154-8c08-a9a546de9bf7', 'admin', b'1', '测试点对点数据，持久化1630761930004', '/queue/message', b'0', '2021-09-04 21:25:29');
INSERT INTO `ws_data` VALUES (10692, 'BUSINESS', 'b9368c42-72f6-4abf-af90-836a852f2fb4', 'admin', b'1', '测试点对点数据，持久化1630761930012', '/queue/message', b'0', '2021-09-04 21:25:30');
INSERT INTO `ws_data` VALUES (10693, 'BUSINESS', 'a582cd82-3b80-4fd8-8316-008fb603cb2e', 'admin', b'1', '测试点对点数据，持久化1630761960004', '/queue/message', b'0', '2021-09-04 21:25:59');
INSERT INTO `ws_data` VALUES (10694, 'BUSINESS', 'f352ab34-03d8-4f64-8a6b-bad0d5c87162', 'admin', b'1', '测试点对点数据，持久化1630761960009', '/queue/message', b'0', '2021-09-04 21:26:00');
INSERT INTO `ws_data` VALUES (10695, 'BUSINESS', '35eb8a98-4861-40cd-bb09-2d0357ba4451', 'admin', b'1', '测试点对点数据，持久化1630761990006', '/queue/message', b'0', '2021-09-04 21:26:29');
INSERT INTO `ws_data` VALUES (10696, 'BUSINESS', '1442b23a-6e24-4c10-8ab3-6f15fb5fab07', 'admin', b'1', '测试点对点数据，持久化1630761990011', '/queue/message', b'0', '2021-09-04 21:26:30');
INSERT INTO `ws_data` VALUES (10697, 'BUSINESS', 'f5726c7b-6844-47cd-b9a4-b9d00df05dc8', 'admin', b'1', '测试点对点数据，持久化1630762020004', '/queue/message', b'0', '2021-09-04 21:26:59');
INSERT INTO `ws_data` VALUES (10698, 'BUSINESS', '833e77ac-3162-4f4a-a355-d2f71f8836b2', 'admin', b'1', '测试点对点数据，持久化1630762020010', '/queue/message', b'0', '2021-09-04 21:27:00');
INSERT INTO `ws_data` VALUES (10699, 'BUSINESS', '2d55f70f-e56f-4064-b4bc-74ce42aab583', 'admin', b'1', '测试点对点数据，持久化1630762050003', '/queue/message', b'0', '2021-09-04 21:27:29');
INSERT INTO `ws_data` VALUES (10700, 'BUSINESS', '9fb336e7-1032-4ef8-ad93-1408b7ed0268', 'admin', b'1', '测试点对点数据，持久化1630762050008', '/queue/message', b'0', '2021-09-04 21:27:30');
INSERT INTO `ws_data` VALUES (10701, 'BUSINESS', 'd91c81bb-f26c-423d-b820-87080c940875', 'admin', b'1', '测试点对点数据，持久化1630762080004', '/queue/message', b'0', '2021-09-04 21:27:59');
INSERT INTO `ws_data` VALUES (10702, 'BUSINESS', 'a20bc930-7b97-42a3-894e-68a28d4a5ded', 'admin', b'1', '测试点对点数据，持久化1630762080009', '/queue/message', b'0', '2021-09-04 21:28:00');
INSERT INTO `ws_data` VALUES (10703, 'BUSINESS', '37d002c6-5d09-44bc-9057-6f50552f5777', 'admin', b'1', '测试点对点数据，持久化1630762110003', '/queue/message', b'0', '2021-09-04 21:28:29');
INSERT INTO `ws_data` VALUES (10704, 'BUSINESS', 'fe81f0ec-eabb-4f23-ad61-923643078e98', 'admin', b'1', '测试点对点数据，持久化1630762110008', '/queue/message', b'0', '2021-09-04 21:28:30');
INSERT INTO `ws_data` VALUES (10705, 'BUSINESS', '879a38c0-5238-4a93-91b3-55e6fdf24f89', 'admin', b'1', '测试点对点数据，持久化1630762140009', '/queue/message', b'0', '2021-09-04 21:28:59');
INSERT INTO `ws_data` VALUES (10706, 'BUSINESS', '176dfac0-c4bb-4998-a2c2-d3d5304e7027', 'admin', b'1', '测试点对点数据，持久化1630762140015', '/queue/message', b'0', '2021-09-04 21:29:00');
INSERT INTO `ws_data` VALUES (10707, 'BUSINESS', 'bd60714e-d3e5-453a-bc68-6d2df02641d3', 'admin', b'1', '测试点对点数据，持久化1630762170003', '/queue/message', b'0', '2021-09-04 21:29:29');
INSERT INTO `ws_data` VALUES (10708, 'BUSINESS', 'bb2b50db-47b6-410e-aa56-508d30ce7a45', 'admin', b'1', '测试点对点数据，持久化1630762170013', '/queue/message', b'0', '2021-09-04 21:29:30');
INSERT INTO `ws_data` VALUES (10709, 'BUSINESS', 'e8351334-e30f-4de5-aea6-80cfb1262ed4', 'admin', b'1', '测试点对点数据，持久化1630762200003', '/queue/message', b'0', '2021-09-04 21:29:59');
INSERT INTO `ws_data` VALUES (10710, 'BUSINESS', '188826e8-96ef-4eb1-8fb7-5d1d61adb613', 'admin', b'1', '测试点对点数据，持久化1630762200007', '/queue/message', b'0', '2021-09-04 21:30:00');
INSERT INTO `ws_data` VALUES (10711, 'BUSINESS', '3a9544f4-e307-4ff4-ba52-51f81e091e7f', 'admin', b'1', '测试点对点数据，持久化1630762230003', '/queue/message', b'0', '2021-09-04 21:30:29');
INSERT INTO `ws_data` VALUES (10712, 'BUSINESS', '83062259-8be4-4d01-bec7-3a4c70d7e310', 'admin', b'1', '测试点对点数据，持久化1630762230010', '/queue/message', b'0', '2021-09-04 21:30:30');
INSERT INTO `ws_data` VALUES (10713, 'BUSINESS', '90d8a747-d72a-4c29-9265-c102348d730d', 'admin', b'1', '测试点对点数据，持久化1630762260009', '/queue/message', b'0', '2021-09-04 21:30:59');
INSERT INTO `ws_data` VALUES (10714, 'BUSINESS', 'f20c89e3-4f41-493a-ab8e-5990bd77482b', 'admin', b'1', '测试点对点数据，持久化1630762260011', '/queue/message', b'0', '2021-09-04 21:31:00');
INSERT INTO `ws_data` VALUES (10715, 'BUSINESS', '0ba066ce-2b82-48bc-8f9d-d08ee55d6954', 'admin', b'1', '测试点对点数据，持久化1630762290003', '/queue/message', b'0', '2021-09-04 21:31:29');
INSERT INTO `ws_data` VALUES (10716, 'BUSINESS', 'd1f958ef-f575-4702-8db8-59f514932dfe', 'admin', b'1', '测试点对点数据，持久化1630762290012', '/queue/message', b'0', '2021-09-04 21:31:30');
INSERT INTO `ws_data` VALUES (10717, 'BUSINESS', '3512a774-f830-4e5c-8208-8f83ed11549a', 'admin', b'1', '测试点对点数据，持久化1630762320005', '/queue/message', b'0', '2021-09-04 21:31:59');
INSERT INTO `ws_data` VALUES (10718, 'BUSINESS', '8104baee-02a7-47bc-8060-866ed35c1b9d', 'admin', b'1', '测试点对点数据，持久化1630762320015', '/queue/message', b'0', '2021-09-04 21:32:00');
INSERT INTO `ws_data` VALUES (10719, 'BUSINESS', 'b3e67a38-5cd0-4ece-817d-3fc0653e9c58', 'admin', b'1', '测试点对点数据，持久化1630762350008', '/queue/message', b'0', '2021-09-04 21:32:29');
INSERT INTO `ws_data` VALUES (10720, 'BUSINESS', '16b662f7-3bd1-4b74-9a16-ffe5a449e239', 'admin', b'1', '测试点对点数据，持久化1630762350014', '/queue/message', b'0', '2021-09-04 21:32:30');
INSERT INTO `ws_data` VALUES (10721, 'BUSINESS', '7e132870-589b-49ba-b57c-dc2bc631083e', 'admin', b'1', '测试点对点数据，持久化1630762380005', '/queue/message', b'0', '2021-09-04 21:32:59');
INSERT INTO `ws_data` VALUES (10722, 'BUSINESS', 'fd6367ad-1148-47f0-9904-42077af5ad90', 'admin', b'1', '测试点对点数据，持久化1630762380015', '/queue/message', b'0', '2021-09-04 21:33:00');
INSERT INTO `ws_data` VALUES (10723, 'BUSINESS', '66cc411a-f411-4e2d-be47-badf75806347', 'admin', b'1', '测试点对点数据，持久化1630762410005', '/queue/message', b'0', '2021-09-04 21:33:29');
INSERT INTO `ws_data` VALUES (10724, 'BUSINESS', '19fcd8dd-045d-4e6d-9654-86b6f760df60', 'admin', b'1', '测试点对点数据，持久化1630762410012', '/queue/message', b'0', '2021-09-04 21:33:30');
INSERT INTO `ws_data` VALUES (10725, 'BUSINESS', 'c618f972-7fc5-411e-9279-83c68af69f2b', 'admin', b'1', '测试点对点数据，持久化1630762440011', '/queue/message', b'0', '2021-09-04 21:33:59');
INSERT INTO `ws_data` VALUES (10726, 'BUSINESS', '030c795e-425c-4ec9-bf32-49d555d564cb', 'admin', b'1', '测试点对点数据，持久化1630762440015', '/queue/message', b'0', '2021-09-04 21:34:00');
INSERT INTO `ws_data` VALUES (10727, 'BUSINESS', '7fb2a042-0118-4bb5-816a-874b957759e0', 'admin', b'1', '测试点对点数据，持久化1630762470006', '/queue/message', b'0', '2021-09-04 21:34:29');
INSERT INTO `ws_data` VALUES (10728, 'BUSINESS', '5e68b28d-1aea-443d-b260-865c01c80741', 'admin', b'1', '测试点对点数据，持久化1630762470014', '/queue/message', b'0', '2021-09-04 21:34:30');
INSERT INTO `ws_data` VALUES (10729, 'BUSINESS', '0be82035-aa55-4d96-a03e-8cf90fca8cff', 'admin', b'1', '测试点对点数据，持久化1630762500005', '/queue/message', b'0', '2021-09-04 21:34:59');
INSERT INTO `ws_data` VALUES (10730, 'BUSINESS', '3fa88866-d21c-4227-8130-8013df5addd4', 'admin', b'1', '测试点对点数据，持久化1630762500017', '/queue/message', b'0', '2021-09-04 21:35:00');
INSERT INTO `ws_data` VALUES (10731, 'BUSINESS', '88a29654-cf2c-4907-bd03-db01c93c77fd', 'admin', b'1', '测试点对点数据，持久化1630762530009', '/queue/message', b'0', '2021-09-04 21:35:29');
INSERT INTO `ws_data` VALUES (10732, 'BUSINESS', '25c4436d-c020-43ab-a98f-e96e9127ff46', 'admin', b'1', '测试点对点数据，持久化1630762530018', '/queue/message', b'0', '2021-09-04 21:35:30');
INSERT INTO `ws_data` VALUES (10733, 'BUSINESS', '6ccbbd83-74c3-4254-9453-8049124181eb', 'admin', b'1', '测试点对点数据，持久化1630762560005', '/queue/message', b'0', '2021-09-04 21:35:59');
INSERT INTO `ws_data` VALUES (10734, 'BUSINESS', '87b40aef-79d5-47df-953b-4e1f1580001e', 'admin', b'1', '测试点对点数据，持久化1630762560007', '/queue/message', b'0', '2021-09-04 21:36:00');
INSERT INTO `ws_data` VALUES (10735, 'BUSINESS', '5d5c4ca1-cac2-4430-b388-8fd9d5ff03a5', 'admin', b'1', '测试点对点数据，持久化1630762590004', '/queue/message', b'0', '2021-09-04 21:36:29');
INSERT INTO `ws_data` VALUES (10736, 'BUSINESS', 'f06d8a07-f308-41b8-ac0a-c6cc6e65d460', 'admin', b'1', '测试点对点数据，持久化1630762590010', '/queue/message', b'0', '2021-09-04 21:36:30');
INSERT INTO `ws_data` VALUES (10737, 'BUSINESS', '6806155f-ecca-45db-8a2c-a5129006ebb2', 'admin', b'1', '测试点对点数据，持久化1630762620005', '/queue/message', b'0', '2021-09-04 21:36:59');
INSERT INTO `ws_data` VALUES (10738, 'BUSINESS', '2a144fee-9447-4e2e-ba1c-3e8701f2adc6', 'admin', b'1', '测试点对点数据，持久化1630762620009', '/queue/message', b'0', '2021-09-04 21:37:00');
INSERT INTO `ws_data` VALUES (10739, 'BUSINESS', 'f769ab4d-511c-45c5-91a4-a37adda5551c', 'admin', b'1', '测试点对点数据，持久化1630762650011', '/queue/message', b'0', '2021-09-04 21:37:29');
INSERT INTO `ws_data` VALUES (10740, 'BUSINESS', 'ea2da806-4ed5-4c76-b7e8-2d98614d679b', 'admin', b'1', '测试点对点数据，持久化1630762650009', '/queue/message', b'0', '2021-09-04 21:37:30');
INSERT INTO `ws_data` VALUES (10741, 'BUSINESS', 'a5195dbb-d83e-4e00-901d-8560e5979ac4', 'admin', b'1', '测试点对点数据，持久化1630762680003', '/queue/message', b'0', '2021-09-04 21:37:59');
INSERT INTO `ws_data` VALUES (10742, 'BUSINESS', '0ce300b4-7685-4c1c-ae1b-389e9d6d6bbb', 'admin', b'1', '测试点对点数据，持久化1630762680012', '/queue/message', b'0', '2021-09-04 21:38:00');
INSERT INTO `ws_data` VALUES (10743, 'BUSINESS', 'eddad5c6-b947-4ed9-b3ac-5357abacf9b0', 'admin', b'1', '测试点对点数据，持久化1630762710004', '/queue/message', b'0', '2021-09-04 21:38:29');
INSERT INTO `ws_data` VALUES (10744, 'BUSINESS', '71537fe9-ab1e-4612-9228-5e1b1b8e8c45', 'admin', b'1', '测试点对点数据，持久化1630762710013', '/queue/message', b'0', '2021-09-04 21:38:30');
INSERT INTO `ws_data` VALUES (10745, 'BUSINESS', 'c3016267-cba0-4354-aaf5-c13ca6852ef1', 'admin', b'1', '测试点对点数据，持久化1630762740005', '/queue/message', b'0', '2021-09-04 21:38:59');
INSERT INTO `ws_data` VALUES (10746, 'BUSINESS', '4879c0f1-097f-4a41-b80b-b367860833b9', 'admin', b'1', '测试点对点数据，持久化1630762740017', '/queue/message', b'0', '2021-09-04 21:39:00');
INSERT INTO `ws_data` VALUES (10747, 'BUSINESS', '71e8f27d-e811-42f2-a1a1-72ab58e215f6', 'admin', b'1', '测试点对点数据，持久化1630762770004', '/queue/message', b'0', '2021-09-04 21:39:29');
INSERT INTO `ws_data` VALUES (10748, 'BUSINESS', '58d74252-1d56-4f50-92e5-cca8029e6d87', 'admin', b'1', '测试点对点数据，持久化1630762770015', '/queue/message', b'0', '2021-09-04 21:39:30');

-- ----------------------------
-- Table structure for xxcolumn_definition
-- ----------------------------
DROP TABLE IF EXISTS `xxcolumn_definition`;
CREATE TABLE `xxcolumn_definition`  (
  `id` int(0) NOT NULL AUTO_INCREMENT COMMENT '自增唯一id',
  `tableIdentity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表名',
  `field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列显示中文名称',
  `sequence` int(0) NULL DEFAULT NULL COMMENT '序号',
  `editable` bit(1) NULL DEFAULT b'0' COMMENT '修改时是否允许编辑',
  `sortable` bit(1) NULL DEFAULT NULL COMMENT '1为可排序，0为不可排序',
  `type` char(16) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列类型，用于前台展示（String,Integer,Float,Double,Date）',
  `selectUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下拉列表的URL',
  `search` bit(1) NULL DEFAULT b'0' COMMENT '是否提供检索',
  `searchType` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'like/exat  like模糊，默认exat全匹配，prefix：前缀匹配   range：范围查询',
  `link` bit(1) NULL DEFAULT NULL,
  `addable` bit(1) NULL DEFAULT b'0' COMMENT '新增时是否需要添加(如果对表进行编辑、新增操作设为TRUE)',
  `hidden` bit(1) NULL DEFAULT b'0' COMMENT '是否在表格中隐藏，新增、编辑时应该不隐藏',
  `refTable` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联表',
  `refColumn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关联列',
  `refValue` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '固定comobox值，通过半角逗号分隔',
  `required` bit(1) NULL DEFAULT b'0' COMMENT '是否必填',
  `reg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '正则校验规则',
  `minLength` int(0) NULL DEFAULT NULL COMMENT '最小长度',
  `maxLength` int(0) NULL DEFAULT NULL COMMENT '最大长度',
  `distinct` bit(1) NULL DEFAULT NULL COMMENT '是否不允许重复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 11194 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表描述信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxcolumn_definition
-- ----------------------------
INSERT INTO `xxcolumn_definition` VALUES (11132, 'role', 'role_id', 'ID', 1, b'0', b'0', 'text', NULL, b'0', 'exact', b'0', b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, b'0');
INSERT INTO `xxcolumn_definition` VALUES (11133, 'role', 'role_name', '名称', 2, b'1', b'0', 'text', NULL, b'1', 'like', b'0', b'1', b'0', NULL, NULL, NULL, b'0', NULL, 2, 50, b'0');
INSERT INTO `xxcolumn_definition` VALUES (11134, 'role', 'role_create_time', '创建时间', 3, b'0', b'0', 'daterange', NULL, b'1', 'range', b'0', b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, b'0');
INSERT INTO `xxcolumn_definition` VALUES (11135, 'role', 'role_update_time', '更新时间', 4, b'0', NULL, 'daterange', NULL, b'1', 'range', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11136, 'user', 'user_id', 'ID', 1, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11137, 'user', 'user_name', '用户名', 2, b'1', NULL, 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, 2, 50, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11138, 'user', 'user_real_name', '真实姓名', 3, b'1', NULL, 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11139, 'user', 'user_pwd', '密码', 4, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11140, 'user', 'user_tel', '手机号码', 5, b'1', NULL, 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', '', NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11141, 'user', 'user_email', '邮箱', 6, b'1', NULL, 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', '^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$', NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11142, 'user', 'user_status', '用户状态', 7, b'1', NULL, 'select', NULL, b'1', 'exact', NULL, b'1', b'0', 'user_status', 'user_status_name', '', b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11143, 'user', 'user_create_time', '创建时间', 8, b'0', NULL, 'daterange', NULL, b'1', 'range', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11144, 'user', 'user_update_time', '最后修改时间', 9, b'0', NULL, 'daterange', NULL, b'1', 'range', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11145, 'user', 'user_role', '用户角色', 7, b'0', NULL, 'multiSelect', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11146, 'user', 'user_role_edit', '用户角色', 10, b'1', NULL, 'multiSelect', '/combobox/role', b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11147, 'user', 'user_org', '组织机构', 7, b'0', NULL, 'selectCascader', '', b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11148, 'user', 'user_org_edit', '组织机构', 11, b'1', NULL, 'selectCascader', '/combobox/org/tree', b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11149, 'dict', 'id', '字典id', 1, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11150, 'dict', 'key', '字典键', 2, b'0', b'1', 'text', NULL, b'0', 'exact', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11151, 'dict', 'value', '字典值', 3, b'0', b'0', 'text', NULL, b'0', 'like', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11152, 'dict', 'type', '字典类型', 4, b'0', b'0', 'text', NULL, b'0', 'extra', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11153, 'dict', 'description', '字典描述', 5, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11154, 'param', 'param_name', '参数名称', 1, b'1', b'0', 'text', NULL, b'0', '', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11155, 'param', 'param_key', '参数键名', 2, b'1', b'1', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11156, 'param', 'param_value', '参数键值', 3, b'1', b'1', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11157, 'param', 'param_type', '参数类型', 4, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11158, 'param', 'create_by', '创建者', 5, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11159, 'param', 'create_time', '创建时间', 6, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11160, 'param', 'update_by', '更新者', 7, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11161, 'param', 'update_time', '更新时间', 8, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11162, 'param', 'param_desc', '参数描述', 9, b'1', NULL, 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11163, 'log_info', 'id', '序号', 1, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11164, 'log_info', 'log_type', '日志类型', 2, b'0', NULL, 'text', NULL, b'1', 'exact', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11165, 'log_info', 'operate_user', '操作用户', 3, b'0', b'0', 'text', NULL, b'1', 'exact', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11166, 'log_info', 'operate_type', '操作类型', 4, b'0', NULL, 'text', NULL, b'1', 'exact', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11167, 'log_info', 'request_url', '请求地址', 5, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11168, 'log_info', 'operate_time', '操作时间', 6, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11169, 'log_info', 'request_took_time', '响应时间', 7, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11170, 'ws_data', 'id', 'ID', 1, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11171, 'ws_data', 'type', '类型', 2, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11172, 'log_info', 'log_desc', '日志详情', 8, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11173, 'ws_data', 'msg_id', '消息id', 3, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11174, 'ws_data', 'user_identify', '用户标识', 4, b'0', NULL, 'text', NULL, b'1', 'like', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11175, 'ws_data', 'data', '数据', 5, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11176, 'ws_data', 'destination', '订阅路径', 6, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11177, 'ws_data', 'create_time', '推送时间', 7, b'0', NULL, 'daterange', NULL, b'1', 'range', NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11178, 'dict_data', 'dict_label', '字典名称', 1, b'1', b'1', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11179, 'dict_data', 'dict_data_type', '数据类型', 2, b'1', b'1', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11180, 'dict_data', 'dict_type', '字典类型', 3, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11181, 'dict_data', 'create_time', '创建时间', 4, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11182, 'dict_data', 'create_by', '创建者', 5, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11183, 'dict_data', 'update_time', '更新时间', 6, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11184, 'dict_data', 'update_by', '更新者', 7, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11185, 'dict_data', 'dict_desc', '字典描述', 8, b'1', NULL, 'text', NULL, b'0', '', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11186, 'dict_data_type', 'dict_data_type', '数据类型', 1, b'1', b'0', 'text', NULL, b'1', 'exact', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11187, 'dict_data_type', 'dict_data_key', '数据键', 2, b'1', b'1', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11188, 'dict_data_type', 'dict_data_value', '数据值', 3, b'1', NULL, 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, 1, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11189, 'dict_data_type', 'create_time', '创建时间', 4, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11190, 'dict_data_type', 'create_by', '创建者', 5, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11191, 'dict_data_type', 'update_time', '更新时间', 6, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11192, 'dict_data_type', 'update_by', '更新者', 7, b'0', NULL, 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11193, 'dict_data_type', 'dict_data_desc', '描述', 8, b'1', NULL, 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xxtable_definition
-- ----------------------------
DROP TABLE IF EXISTS `xxtable_definition`;
CREATE TABLE `xxtable_definition`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `tableIdentity` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格业务名称，前后台会话标识',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格标题描述',
  `tableName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '对应的数据库中的数据表名，用于删除、更新、插入',
  `sql` varchar(1023) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过滤条件，如userName=‘张三’ AND userId = #{userId} AND sessionId = #{sessionId}',
  `checkbox` bit(1) NULL DEFAULT NULL,
  `backInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `frontInfo` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表格上方注释，如：注：XXX',
  `viewType` char(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'muti/single //显示方式，multi多行，single 单行',
  `cellEditable` bit(1) NULL DEFAULT NULL COMMENT ' true/fasle 表格是否在单元格编辑',
  `primaryKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主键',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11113 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xxtable_definition
-- ----------------------------
INSERT INTO `xxtable_definition` VALUES (11105, 'dict', '字典', 'dict', 'select * from dict', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO `xxtable_definition` VALUES (11106, 'param', '参数设置', 'param', 'select * from param', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO `xxtable_definition` VALUES (11107, 'log_info', '访问日志', 'log_info', 'select * from log_info', b'0', '', '', '', b'0', 'id');
INSERT INTO `xxtable_definition` VALUES (11108, 'role', '角色', 'role', 'select * from role', NULL, NULL, NULL, NULL, NULL, 'role_id');
INSERT INTO `xxtable_definition` VALUES (11109, 'user', '用户', 'user_info', 'select * from user_info', NULL, NULL, NULL, NULL, NULL, 'user_id');
INSERT INTO `xxtable_definition` VALUES (11110, 'ws_data', '消息', 'ws_data', 'select * from ws_data', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO `xxtable_definition` VALUES (11111, 'dict_data', '字典管理', 'dict_data', 'select * from dict_data', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO `xxtable_definition` VALUES (11112, 'dict_data_type', '字典数据', 'dict_data_type', 'select * from dict_data_type', NULL, NULL, NULL, NULL, NULL, 'id');

SET FOREIGN_KEY_CHECKS = 1;
