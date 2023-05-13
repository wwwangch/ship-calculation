/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : calculation

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 09/05/2023 18:16:00
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bulb_flat
-- ----------------------------
DROP TABLE IF EXISTS `bulb_flat`;
CREATE TABLE `bulb_flat`  (
  `profile_id` int NOT NULL AUTO_INCREMENT COMMENT '剖面ID，主键，自动递增',
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  `height` double NULL DEFAULT NULL COMMENT '高度',
  `width` double NULL DEFAULT NULL COMMENT '宽度',
  `thickness` double NULL DEFAULT NULL COMMENT '厚度',
  `sectional_area` double NULL DEFAULT NULL COMMENT '剖面积',
  `moment_of_inertia` double NULL DEFAULT NULL COMMENT '惯性矩',
  `centroid_position` double NULL DEFAULT NULL COMMENT '形心位置',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`profile_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bulb_flat
-- ----------------------------
INSERT INTO `bulb_flat` VALUES (1, 'xxxx', 13, 12, 12, 21, 12, 12, '2023-04-13 21:37:11', '2023-04-18 16:03:53');
INSERT INTO `bulb_flat` VALUES (3, 'x', 23, 23, 45, 23, 35, 53, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (4, 'xx', 23, 42, 42, 324, 23, 5, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (5, 'xxx', 42, 32, 42, 34, 534, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (6, 'xxxx', 42, 24, 53, 4, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (7, 'xxxxx', 42, 2, 4, 5, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (8, 'x', 23, 23, 45, 23, 35, 53, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (9, 'xx', 23, 42, 42, 324, 23, 5, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (10, 'xxx', 42, 32, 42, 34, 534, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (11, 'xxxx', 42, 24, 53, 4, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (12, 'xxxxx', 42, 2, 4, 5, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (13, '42', 42, 42, 42, 3, 42, 42, '2023-04-19 08:47:28', NULL);
INSERT INTO `bulb_flat` VALUES (14, '23.32', 24.21, 10.11, 1, 1, 1, 1, '2023-05-06 16:40:46', NULL);
INSERT INTO `bulb_flat` VALUES (15, '23.32', 24.21, 10.11, 1, 1, 1, 1, '2023-05-06 16:42:36', NULL);
INSERT INTO `bulb_flat` VALUES (16, '111', 1.2, 1, 1, 1, 1, 1, '2023-05-06 16:43:23', NULL);

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '项目名称',
  `calculation_specification` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '计算类型 0-通用 1-大船 2-DQ',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`project_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '按实际不符', '0', '哈a a哈', '2023-04-11 16:53:40', '2023-04-11 16:54:19');
INSERT INTO `project` VALUES (4, '222', '0', NULL, '2023-04-18 16:44:34', '2023-04-18 16:44:44');
INSERT INTO `project` VALUES (5, 'test', '1', NULL, '2023-04-20 10:38:40', NULL);
INSERT INTO `project` VALUES (6, '1', '1', '1', '2023-04-26 13:52:10', '2023-04-26 13:52:17');

-- ----------------------------
-- Table structure for section
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section`  (
  `section_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int NULL DEFAULT NULL COMMENT '项目id',
  `x_coordinate` double NULL DEFAULT NULL COMMENT '校核剖面位置X',
  `section_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '剖面文件名称',
  `section_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '剖面文件路径',
  `component_span` double NULL DEFAULT NULL COMMENT '剖面构件跨距a',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`section_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of section
-- ----------------------------

-- ----------------------------
-- Table structure for ship_param
-- ----------------------------
DROP TABLE IF EXISTS `ship_param`;
CREATE TABLE `ship_param`  (
  `param_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL,
  `ship_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '船舶类型',
  `waterline_length` double NULL DEFAULT NULL COMMENT '设计水线长(m)',
  `moulded_breadth` double NULL DEFAULT NULL COMMENT '型宽(m)',
  `moulded_depth` double NULL DEFAULT NULL COMMENT '型深(m)',
  `designed_draft` double NULL DEFAULT NULL COMMENT '设计吃水(m)',
  `navigation_area` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '航行区域 0-无限 1-有限',
  `extreme_displacement` double NULL DEFAULT NULL COMMENT '极限波浪工况排水量',
  `extreme_portrait_gravity` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '极限波浪重心纵向位置',
  `cruising_displacement` double NULL DEFAULT NULL COMMENT '巡航工况排水量',
  `cruising_portrait_gravity` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '巡航纵向重心纵向位置',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ship_param
-- ----------------------------
INSERT INTO `ship_param` VALUES (1, 1, 'QZJ', 32, 423, 24, 242, '0', NULL, NULL, NULL, NULL, '2023-04-20 10:41:52', NULL);
INSERT INTO `ship_param` VALUES (2, 4, 'HM', 1, 1, 1, 1, '0', NULL, NULL, NULL, NULL, '2023-04-20 15:01:07', NULL);
INSERT INTO `ship_param` VALUES (3, 4, 'HM', 1, 1, 1, 1, '0', NULL, NULL, NULL, NULL, '2023-04-20 15:11:44', NULL);
INSERT INTO `ship_param` VALUES (4, 1, 'QZJ', 32, 423, 24, 242, '0', NULL, NULL, NULL, NULL, '2023-04-26 14:29:16', NULL);
INSERT INTO `ship_param` VALUES (5, 1, 'QZJ', 32, 423, 24, 242, '0', NULL, NULL, NULL, NULL, '2023-05-06 16:50:17', NULL);

-- ----------------------------
-- Table structure for t_profile
-- ----------------------------
DROP TABLE IF EXISTS `t_profile`;
CREATE TABLE `t_profile`  (
  `profile_id` int NOT NULL AUTO_INCREMENT COMMENT '剖面ID，主键，自动递增',
  `keel_height` double NULL DEFAULT NULL COMMENT '腹板高度(mm)',
  `keel_thickness` double NULL DEFAULT NULL COMMENT '腹板厚度(mm)',
  `deck_width` double NULL DEFAULT NULL COMMENT '面板宽度(mm)',
  `deck_thickness` double NULL DEFAULT NULL COMMENT '面板厚度(mm)',
  `sectional_area` double NULL DEFAULT NULL COMMENT '剖面积(cm²)',
  `moment_of_inertia` double NULL DEFAULT NULL COMMENT '惯性矩(cm⁴)',
  `centroid_position` double NULL DEFAULT NULL COMMENT '形心位置(cm)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '修改时间',
  `model` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '型号',
  PRIMARY KEY (`profile_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_profile
-- ----------------------------
INSERT INTO `t_profile` VALUES (1, 12, 12, 12, 12, 12, 12, 12, '2023-04-13 21:32:14', NULL, 'xx');
INSERT INTO `t_profile` VALUES (4, 23, 23, 45, 5323, 5345, 35, 53, NULL, NULL, 'q');
INSERT INTO `t_profile` VALUES (5, 23, 42, 42, 353235, 3, 23, 1, NULL, NULL, 'qq');
INSERT INTO `t_profile` VALUES (6, 42, 32, 42, 53, 5, 5, 6, NULL, NULL, 'qqq');
INSERT INTO `t_profile` VALUES (7, 42, 24, 53, 354, 3, 5, 6, NULL, NULL, 'qqqq');
INSERT INTO `t_profile` VALUES (8, 42, 2, 4, 355, 3, 5, 33, NULL, NULL, 'qqqqq');

-- ----------------------------
-- Table structure for xxcolumn_definition
-- ----------------------------
DROP TABLE IF EXISTS `xxcolumn_definition`;
CREATE TABLE `xxcolumn_definition`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增唯一id',
  `tableIdentity` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '表名',
  `field` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列名',
  `header` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '列显示中文名称',
  `sequence` int NULL DEFAULT NULL COMMENT '序号',
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
  `minLength` int NULL DEFAULT NULL COMMENT '最小长度',
  `maxLength` int NULL DEFAULT NULL COMMENT '最大长度',
  `distinct` bit(1) NULL DEFAULT NULL COMMENT '是否不允许重复',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表描述信息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxcolumn_definition
-- ----------------------------
INSERT INTO `xxcolumn_definition` VALUES (1, 'project', 'project_id', '项目id', 1, b'0', b'1', 'text', '', b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (2, 'project', 'calculation_specification', '计算规范', 3, b'1', b'0', 'select', '/project/combobox/calcSpec', b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"0\":\"通用规范\",\"1\":\"大船准则\",\"2\":\"DQ准则\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (3, 'project', 'remark', '备注', 4, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, 100, NULL);
INSERT INTO `xxcolumn_definition` VALUES (4, 'project', 'create_time', '创建时间', 5, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (5, 'project', 'update_time', '更新时间', 6, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (6, 'ship_param', 'param_id', '参数id', 1, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (7, 'ship_param', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (8, 'ship_param', 'ship_type', '船舶类型', 3, b'1', b'0', 'select', '/ship/combobox/type', b'1', NULL, NULL, b'1', b'0', NULL, NULL, '{\"ZQJ\":\"ZQJ\",\"HM\":\"HM\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (9, 'ship_param', 'waterline_length', '设计水线长', 4, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (10, 'ship_param', 'moulded_breadth', '型宽', 5, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11, 'ship_param', 'moulded_depth', '型深', 6, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (12, 'ship_param', 'designed_draft', '设计吃水', 7, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (13, 'ship_param', 'navigation_area', '航行区域', 8, b'1', b'0', 'select', '/ship/combobox/area', b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"0\":\"无限航区\",\"1\":\"有限航区\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (14, 'ship_param', 'extreme_displacement', '极限波浪工况排水量', 9, b'1', b'0', 'text', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (15, 'ship_param', 'extreme_portrait_gravity', '极限波浪工况重心纵向位置', 10, b'1', b'0', 'text', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (16, 'ship_param', 'cruising_displacement', '巡航工况排水量', 11, b'1', b'0', 'text', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (17, 'ship_param', 'cruising_portrait_gravity', '巡航工况重心纵向位置', 12, b'1', b'0', 'text', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (18, 't_profile', 'profile_id', '型材id', 1, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (19, 't_profile', 'model', '型号', 2, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (20, 't_profile', 'keel_height', '腹板高度(mm)', 3, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (21, 't_profile', 'keel_thickness', '腹板厚度(mm)', 4, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (22, 't_profile', 'deck_width', '面板宽度(mm)', 5, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (23, 't_profile', 'deck_thickness', '面板厚度(mm)', 6, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (24, 't_profile', 'sectional_area', '剖面积(cm²)', 7, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (25, 't_profile', 'moment_of_inertia', '惯性矩(cm⁴)', 8, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (26, 't_profile', 'centroid_position', '形心位置(cm)', 9, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (27, 't_profile', 'create_time', '创建时间', 10, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (28, 't_profile', 'update_time', '更新时间', 11, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (29, 'bulb_flat', 'profile_id', '型材id', 1, b'0', b'1', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (30, 'bulb_flat', 'model', '型号', 2, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (31, 'bulb_flat', 'height', '高度(mm)', 3, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (32, 'bulb_flat', 'width', '宽度(mm)', 4, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (33, 'bulb_flat', 'thickness', '厚度(mm)', 5, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (35, 'bulb_flat', 'sectional_area', '剖面积(cm²)', 6, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (36, 'bulb_flat', 'moment_of_inertia', '惯性矩(cm⁴)', 7, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (37, 'bulb_flat', 'centroid_position', '形心位置(cm)', 8, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (38, 'bulb_flat', 'create_time', '创建时间', 9, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (39, 'bulb_flat', 'update_time', '更新时间', 10, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (40, 'project', 'project_name', '项目名称', 2, b'1', b'0', 'text', NULL, b'1', 'like', NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (41, 'section', 'section_id', '剖面id', 1, b'0', b'1', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (42, 'section', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'1', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (43, 'section', 'x_coordinate', '校核剖面位置X', 3, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (44, 'section', 'section_file_name', '剖面文件名称', 5, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (45, 'section', 'section_file_path', '剖面文件路径', 6, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (46, 'section', 'component_span', '剖面构件跨距a', 7, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (47, 'section', 'create_time', '创建时间', 8, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (48, 'section', 'update_time', '更新时间', 9, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (49, 'section', 'section_file', '剖面文件', 4, b'1', b'0', 'file', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for xxtable_definition
-- ----------------------------
DROP TABLE IF EXISTS `xxtable_definition`;
CREATE TABLE `xxtable_definition`  (
  `id` int NOT NULL AUTO_INCREMENT,
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
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxtable_definition
-- ----------------------------
INSERT INTO `xxtable_definition` VALUES (1, 'project', '项目管理', 'project', 'select * from project', NULL, NULL, NULL, NULL, NULL, 'project_id');
INSERT INTO `xxtable_definition` VALUES (2, 'ship_param', '船舶参数', 'ship_param', 'select * from ship_param', NULL, NULL, NULL, NULL, NULL, 'param_id');
INSERT INTO `xxtable_definition` VALUES (3, 't_profile', 'T型材', 't_profile', 'select * from t_profile', NULL, NULL, NULL, NULL, NULL, 'profile_id');
INSERT INTO `xxtable_definition` VALUES (4, 'bulb_flat', '扁球钢', 'bulb_flat', 'select * from bulb_flat', NULL, NULL, NULL, NULL, NULL, 'profile_id');
INSERT INTO `xxtable_definition` VALUES (5, 'section', '剖面', 'section', 'select * from section', NULL, NULL, NULL, NULL, NULL, 'section_id');

SET FOREIGN_KEY_CHECKS = 1;
