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

 Date: 31/05/2023 16:33:54
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
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bulb_flat
-- ----------------------------
INSERT INTO `bulb_flat` VALUES (22, 'x', 23, 23, 45, 23, 35, 53, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (23, 'xx', 23, 42, 42, 324, 23, 5, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (24, 'xxx', 42, 32, 42, 34, 534, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (25, 'xxxx', 42, 24, 53, 4, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (26, 'xxxxx', 42, 2, 4, 5, 53, 6, NULL, NULL);

-- ----------------------------
-- Table structure for bulkhead
-- ----------------------------
DROP TABLE IF EXISTS `bulkhead`;
CREATE TABLE `bulkhead`  (
  `bulkhead_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int NULL DEFAULT NULL COMMENT '项目id',
  `bulkhead_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '各层甲板高度文件名称',
  `bulkhead_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '各层甲板高度文件路径',
  `collision_bulkhead` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否防撞舱壁',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`bulkhead_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bulkhead
-- ----------------------------
INSERT INTO `bulkhead` VALUES (2, NULL, 'algorithm.json', 'd:/tempx/2023-05-11/06UI2lx4/algorithm.json', '777', '2023-05-11 14:31:05', '2023-05-11 14:33:07');
INSERT INTO `bulkhead` VALUES (3, NULL, 'test', 'd:/tempx/2023-05-12/WY0eNDqJ/重点到了.jpg', '1', '2023-05-12 10:53:42', '2023-05-12 10:53:42');

-- ----------------------------
-- Table structure for bulkhead_compartment
-- ----------------------------
DROP TABLE IF EXISTS `bulkhead_compartment`;
CREATE TABLE `bulkhead_compartment`  (
  `compartment_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int NULL DEFAULT NULL COMMENT '项目id',
  `bulkhead_id` int NULL DEFAULT NULL COMMENT '舱壁id',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  `strip_plate_width` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '带板宽度',
  `strength_material_specification` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '扶强材规格',
  `liquid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '是否液舱',
  `plate_thickness` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '板厚',
  `material` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '材料',
  `plate_width` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '板宽',
  `compartment` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '区间',
  `height_above` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '距基线高度',
  PRIMARY KEY (`compartment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bulkhead_compartment
-- ----------------------------

-- ----------------------------
-- Table structure for buoyancy_param
-- ----------------------------
DROP TABLE IF EXISTS `buoyancy_param`;
CREATE TABLE `buoyancy_param`  (
  `param_id` int NOT NULL AUTO_INCREMENT COMMENT '参数 ID',
  `project_id` int NULL DEFAULT NULL,
  `buoyancy_curve_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浮力文件名',
  `buoyancy_curve_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '浮力文件路径',
  `bonjung_curve_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邦戎曲线 Excel 文件名',
  `bonjung_curve_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邦戎曲线 Excel 文件路径',
  `draft_forward` double NULL DEFAULT NULL COMMENT '艏吃水（m）',
  `draft_aft` double NULL DEFAULT NULL COMMENT '艉吃水（m）',
  `draft_mean` double NULL DEFAULT NULL COMMENT '平均吃水（m）',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `precision_displacement` double NULL DEFAULT NULL,
  `precision_gravity` double NULL DEFAULT NULL,
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of buoyancy_param
-- ----------------------------
INSERT INTO `buoyancy_param` VALUES (1, 1, 'ServiceImpl.cpp', 'd:/tempx/2023-05-12/C7jWlR4B/ServiceImpl.cpp', 'calculation.grpc.pb.cc', 'd:/tempx/2023-05-12/EzV49GUH/calculation.grpc.pb.cc', 12, 54, 54, '2023-05-22 11:03:47', '2023-05-22 11:03:47', 56, 12);
INSERT INTO `buoyancy_param` VALUES (3, 7, 'static0508-2.xlsx', 'd:/tempx/2023-05-29/Gp29G24L/static0508-2.xlsx', 'static0508-2.xlsx', 'd:/tempx/2023-05-29/tsV08h2p/static0508-2.xlsx', 1, 1, 1, '2023-05-31 09:26:38', '2023-05-31 09:26:38', 1, 1);

-- ----------------------------
-- Table structure for buoyancy_result
-- ----------------------------
DROP TABLE IF EXISTS `buoyancy_result`;
CREATE TABLE `buoyancy_result`  (
  `result_id` int NOT NULL AUTO_INCREMENT,
  `param_id` int NULL DEFAULT NULL,
  `blist` json NULL,
  `calrst` json NULL,
  PRIMARY KEY (`result_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 33 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of buoyancy_result
-- ----------------------------
INSERT INTO `buoyancy_result` VALUES (1, 1, '[6334.0, 26500.0, 19169.0, 15724.0, 11478.0, 29358.0, 26962.0, 24464.0, 5705.0, 28145.0, 23281.0, 16827.0, 9961.0, 491.0, 2995.0, 11942.0, 4827.0, 5436.0, 32391.0, 14604.0, 3902.0]', '[{\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 0, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 1, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 2, \"floatxpos\": 0.5, \"floatforce\": 0.2}]');
INSERT INTO `buoyancy_result` VALUES (30, 2, '[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]', '[{\"da\": 4.55, \"df\": 4.55, \"dm\": 4.55, \"biasf\": 0.0048964316188452464, \"biasx\": 0.001560318078655463, \"index\": 1, \"floatxpos\": -3.707796741302857, \"floatforce\": 4740.424623875726}, {\"da\": 4.564217012497937, \"df\": 4.564217012497937, \"dm\": 4.564217012497937, \"biasf\": 0.000002918386169288264, \"biasx\": 0.0013593469912381209, \"index\": 2, \"floatxpos\": -3.735128809191615, \"floatforce\": 4763.736097537886}, {\"da\": 4.59588839761597, \"df\": 4.529165360955323, \"dm\": 4.562526879285647, \"biasf\": 0.0009920007241136922, \"biasx\": 0.00004717063364194444, \"index\": 3, \"floatxpos\": -3.926415206175304, \"floatforce\": 4768.475643449497}]');
INSERT INTO `buoyancy_result` VALUES (32, 3, '[10178.0, 13579.0, 25058.0, 27577.0, 12750.0, 14007.0, 23729.0, 24081.0, 2995.0, 2678.0, 24676.0, 27753.0, 20899.0, 11784.0, 15565.0, 3093.0, 13608.0, 6172.0, 11243.0, 29929.0, 7514.0]', '[{\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 0, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 1, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 2, \"floatxpos\": 0.5, \"floatforce\": 0.2}]');

-- ----------------------------
-- Table structure for cal_section
-- ----------------------------
DROP TABLE IF EXISTS `cal_section`;
CREATE TABLE `cal_section`  (
  `cal_section_id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `project_id` int NULL DEFAULT NULL COMMENT '项目id',
  `section_id` int NULL DEFAULT NULL COMMENT '剖面id',
  `profile_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '载重文件路径',
  `profile_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '载重文件名称',
  `first_moment0` double NULL DEFAULT NULL COMMENT '初始静矩',
  `interia0` double NULL DEFAULT NULL COMMENT '惯性矩',
  `zaxis_h` double NULL DEFAULT NULL COMMENT '中拱时的中和轴',
  `first_mom_h` double NULL DEFAULT NULL COMMENT '中拱时静矩',
  `interia_h` double NULL DEFAULT NULL COMMENT '中拱时惯性矩',
  `zaxis_s` double NULL DEFAULT NULL COMMENT '中垂时的中和轴',
  `first_mom_s` double NULL DEFAULT NULL COMMENT '中垂时静矩',
  `interia_s` double NULL DEFAULT NULL COMMENT '中垂时惯性矩',
  `rib_number` double NULL DEFAULT NULL COMMENT '肋位号',
  PRIMARY KEY (`cal_section_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cal_section
-- ----------------------------

-- ----------------------------
-- Table structure for dist
-- ----------------------------
DROP TABLE IF EXISTS `dist`;
CREATE TABLE `dist`  (
  `dist_id` int NOT NULL AUTO_INCREMENT COMMENT '应力计算ID',
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `section_id` int NULL DEFAULT NULL COMMENT '剖面ID',
  `extreme_h` double NULL DEFAULT NULL COMMENT '中拱极限弯矩',
  `extreme_s` double NULL DEFAULT NULL COMMENT '中垂极限弯矩',
  PRIMARY KEY (`dist_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dist
-- ----------------------------
INSERT INTO `dist` VALUES (1, 1, 2, 1.15, 2.22);

-- ----------------------------
-- Table structure for girder_strength
-- ----------------------------
DROP TABLE IF EXISTS `girder_strength`;
CREATE TABLE `girder_strength`  (
  `girder_strength_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL,
  `kua_chang` double NULL DEFAULT NULL COMMENT '构件跨距',
  `girder_distance` double NULL DEFAULT NULL COMMENT '剖面位置x',
  `sigma1_s_h` double NULL DEFAULT NULL COMMENT '中拱支座处',
  `sigma1_mid_h` double NULL DEFAULT NULL COMMENT '中拱跨中处',
  `sigma1_s_s` double NULL DEFAULT NULL COMMENT '中垂支座处',
  `sigma1_mid_s` double NULL DEFAULT NULL COMMENT '中垂跨中处',
  `stress2_s_h` json NULL COMMENT '中拱不同桁材处的支座处的sigma2',
  `stress2_mid_h` json NULL COMMENT '中拱不同桁材处的跨中处的sigma2',
  `stress3_up_h` json NULL COMMENT '中拱骨材上纤维处的应力',
  `stress3_down_h` json NULL COMMENT '中拱骨材下纤维处的应力',
  `stress4_up_h` json NULL COMMENT '中拱板格的上表面',
  `stress4down_h` json NULL COMMENT '中拱板格的下表面',
  `stress2_s_s` json NULL COMMENT '中垂不同桁材处的支座处的sigma2',
  `stress2_mid_s` json NULL COMMENT '中垂不同桁材处的跨中处的sigma2',
  `stress3_up_s` json NULL COMMENT '中垂骨材上纤维处的应力',
  `stress3_down_s` json NULL COMMENT '中垂骨材下纤维处的应力',
  `stress4_up_s` json NULL COMMENT '中垂板格的上表面',
  `stress4down_s` json NULL COMMENT '中垂板格的下表面',
  PRIMARY KEY (`girder_strength_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of girder_strength
-- ----------------------------
INSERT INTO `girder_strength` VALUES (1, 1, 1, 2, 2, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1, '按实际不符', '0', '哈a a哈', '2023-04-11 16:53:40', '2023-04-11 16:54:19');
INSERT INTO `project` VALUES (7, '测试', '0', NULL, '2023-05-13 14:30:07', NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES (2, NULL, 88.8, 'algorithm.json', 'd:/tempx/2023-05-11/06UI2lx4/algorithm.json', 777, '2023-05-11 14:31:05', '2023-05-11 14:33:07');
INSERT INTO `section` VALUES (3, NULL, 1, 'test', 'd:/tempx/2023-05-12/WY0eNDqJ/重点到了.jpg', 1, '2023-05-12 10:53:42', '2023-05-12 10:53:42');
INSERT INTO `section` VALUES (4, 1, 1, '1', 'd:/tempx/2023-05-30/7C853l65/jaffree-2022.06.03.jar', 1, '2023-05-30 10:03:27', '2023-05-30 10:03:27');

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
  `station` int NULL DEFAULT NULL,
  `check_type` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '校验方式',
  `displacement` double NULL DEFAULT NULL COMMENT '排水量',
  `portrait_gravity` double NULL DEFAULT NULL COMMENT '重心纵向位置',
  `create_time` datetime NULL DEFAULT NULL,
  `update_time` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`param_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ship_param
-- ----------------------------
INSERT INTO `ship_param` VALUES (2, 4, 'HM', 1, 1, 1, 1, '0', NULL, NULL, NULL, NULL, '2023-04-20 15:01:07', NULL);
INSERT INTO `ship_param` VALUES (13, 1, 'HM', 23, 42, 32, 5345, '1', NULL, '1', 53, 53, '2023-05-12 18:24:15', NULL);
INSERT INTO `ship_param` VALUES (14, 7, 'QZJ', 136, 17, 10, 4.55, '0', NULL, '0', 4763.75, -3.92, '2023-05-13 14:30:50', NULL);

-- ----------------------------
-- Table structure for slam_load
-- ----------------------------
DROP TABLE IF EXISTS `slam_load`;
CREATE TABLE `slam_load`  (
  `slam_load_id` int NOT NULL AUTO_INCREMENT COMMENT '冲击负荷ID',
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `speed` double NULL DEFAULT NULL,
  `pwbm` json NULL COMMENT '波峰抨击弯矩',
  `nwb` json NULL COMMENT '波谷抨击弯矩',
  PRIMARY KEY (`slam_load_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of slam_load
-- ----------------------------
INSERT INTO `slam_load` VALUES (5, 7, 40, '[25313.0, 5589.0, 5938.0, 6410.0, 6234.0, 9961.0, 6493.0, 25269.0, 28869.0, 14700.0, 26264.0, 16215.0, 7815.0, 3039.0, 29288.0, 1954.0, 20710.0, 24774.0, 29815.0, 6541.0, 1679.0]', '[32685.0, 6698.0, 12722.0, 19037.0, 31461.0, 12508.0, 3959.0, 1515.0, 24937.0, 58.0, 13971.0, 15117.0, 24555.0, 18330.0, 30212.0, 28082.0, 16085.0, 24484.0, 8380.0, 25951.0, 18115.0]');
INSERT INTO `slam_load` VALUES (6, 1, 10, '[31244.0, 6608.0, 11647.0, 9535.0, 3264.0, 23243.0, 22015.0, 189.0, 19812.0, 9523.0, 24474.0, 29891.0, 19854.0, 25697.0, 17780.0, 12931.0, 3340.0, 899.0, 8483.0, 7492.0, 28252.0]', '[11369.0, 21794.0, 9252.0, 17432.0, 7208.0, 3497.0, 27649.0, 26841.0, 16100.0, 30648.0, 19851.0, 28633.0, 27200.0, 9990.0, 4919.0, 22578.0, 32544.0, 13487.0, 22525.0, 5538.0, 6193.0]');

-- ----------------------------
-- Table structure for static_load
-- ----------------------------
DROP TABLE IF EXISTS `static_load`;
CREATE TABLE `static_load`  (
  `static_load_id` int NOT NULL AUTO_INCREMENT COMMENT '静态负荷ID',
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `nvec` json NULL COMMENT '未修正的静水剪力',
  `mvec` json NULL COMMENT '未修正的弯矩',
  `nvec_m` json NULL COMMENT '修正的静水剪力',
  `mvec_m` json NULL COMMENT '修正的弯矩',
  PRIMARY KEY (`static_load_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of static_load
-- ----------------------------
INSERT INTO `static_load` VALUES (1, 7, '[2154.0, 31329.0, 10555.0, 9512.0, 3753.0, 25996.0, 17437.0, 23195.0, 16105.0, 25734.0, 20671.0, 24355.0, 10808.0, 27756.0, 27982.0, 20222.0, 20450.0, 21659.0, 20024.0, 20649.0, 28022.0]', '[30932.0, 17189.0, 28692.0, 16549.0, 18060.0, 12423.0, 12529.0, 12949.0, 20416.0, 16282.0, 11701.0, 12263.0, 20053.0, 20945.0, 19558.0, 4144.0, 2161.0, 10466.0, 26439.0, 29510.0, 8313.0]', '[25721.0, 2368.0, 3434.0, 30145.0, 16139.0, 16687.0, 19866.0, 3297.0, 24488.0, 18114.0, 5786.0, 31185.0, 1832.0, 28321.0, 481.0, 7129.0, 11173.0, 26292.0, 26154.0, 13186.0, 2168.0]', '[4169.0, 19976.0, 21425.0, 7441.0, 21718.0, 16279.0, 22549.0, 193.0, 28286.0, 12455.0, 31316.0, 4313.0, 912.0, 4313.0, 23646.0, 23196.0, 5535.0, 12044.0, 17253.0, 4745.0, 4474.0]');
INSERT INTO `static_load` VALUES (2, 1, '[15834.0, 18529.0, 13392.0, 26979.0, 20193.0, 31276.0, 11159.0, 3449.0, 27008.0, 18503.0, 12074.0, 28761.0, 16683.0, 2741.0, 10396.0, 2599.0, 27032.0, 3517.0, 18882.0, 12105.0, 9916.0]', '[25011.0, 31497.0, 18805.0, 13549.0, 9277.0, 21497.0, 6582.0, 26489.0, 9072.0, 10208.0, 32607.0, 12611.0, 12890.0, 19932.0, 6813.0, 20615.0, 4680.0, 32584.0, 8670.0, 3523.0, 17095.0]', '[1840.0, 31540.0, 13210.0, 30971.0, 1620.0, 19790.0, 20418.0, 12924.0, 27967.0, 15370.0, 13722.0, 11056.0, 13716.0, 4954.0, 15460.0, 20136.0, 14387.0, 17006.0, 25249.0, 29621.0, 15678.0]', '[1560.0, 2785.0, 28791.0, 21578.0, 73.0, 13826.0, 13578.0, 159.0, 10380.0, 6477.0, 14196.0, 19019.0, 31163.0, 25452.0, 2862.0, 10904.0, 9198.0, 7240.0, 241.0, 31758.0, 7296.0]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of t_profile
-- ----------------------------
INSERT INTO `t_profile` VALUES (9, 23, 23, 45, 5323, 5345, 35, 53, NULL, NULL, 'q');
INSERT INTO `t_profile` VALUES (10, 23, 42, 42, 353235, 3, 23, 1, NULL, NULL, 'qq');
INSERT INTO `t_profile` VALUES (11, 42, 32, 42, 53, 5, 5, 6, NULL, NULL, 'qqq');
INSERT INTO `t_profile` VALUES (12, 42, 24, 53, 354, 3, 5, 6, NULL, NULL, 'qqqq');
INSERT INTO `t_profile` VALUES (13, 42, 2, 4, 355, 3, 5, 33, NULL, NULL, 'qqqqq');

-- ----------------------------
-- Table structure for wave_load
-- ----------------------------
DROP TABLE IF EXISTS `wave_load`;
CREATE TABLE `wave_load`  (
  `wave_load_id` int NOT NULL AUTO_INCREMENT COMMENT '波浪负荷ID',
  `project_id` int NULL DEFAULT NULL COMMENT '项目ID',
  `wave_height` double NULL DEFAULT NULL,
  `mbb` json NULL COMMENT '中拱附加浮力',
  `nwvec_h` json NULL COMMENT '波峰未修正的静水剪力',
  `mwvec_h` json NULL COMMENT '波峰未修正的弯矩',
  `nwvec_m_h` json NULL COMMENT '波峰修正的静水剪力',
  `mwvec_m_h` json NULL COMMENT '波峰修正的弯矩',
  `bdelta_s` json NULL COMMENT '中垂附加浮力',
  `nwvec_s` json NULL COMMENT '波谷未修正的静水剪力',
  `mwvec_s` json NULL COMMENT '波谷未修正的弯矩',
  `nwvec_m_s` json NULL COMMENT '波谷修正的静水剪力',
  `mwvec_m_s` json NULL COMMENT '波谷修正的弯矩',
  PRIMARY KEY (`wave_load_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of wave_load
-- ----------------------------
INSERT INTO `wave_load` VALUES (1, 7, 30, '[12268.0, 26809.0, 248.0, 1052.0, 21196.0, 29458.0, 20448.0, 19897.0, 10940.0, 23146.0, 475.0, 30524.0, 16336.0, 2505.0, 7085.0, 14640.0, 7585.0, 30300.0, 19426.0, 5764.0, 25547.0]', '[23481.0, 29996.0, 16439.0, 27918.0, 1146.0, 29292.0, 8762.0, 4449.0, 20675.0, 8638.0, 905.0, 28350.0, 30714.0, 28664.0, 9876.0, 1941.0, 11064.0, 31172.0, 30910.0, 4460.0, 27594.0]', '[9318.0, 4084.0, 7491.0, 25269.0, 7462.0, 6602.0, 13882.0, 25828.0, 29560.0, 8714.0, 15615.0, 12549.0, 18337.0, 13337.0, 9152.0, 29174.0, 16216.0, 19695.0, 28510.0, 874.0, 21577.0]', '[1012.0, 29152.0, 7958.0, 10866.0, 18392.0, 14600.0, 9567.0, 16925.0, 792.0, 27663.0, 9464.0, 14333.0, 28164.0, 9970.0, 23826.0, 20289.0, 9166.0, 17968.0, 23552.0, 474.0, 7950.0]', '[5602.0, 23556.0, 13712.0, 7479.0, 26633.0, 30807.0, 540.0, 13851.0, 15147.0, 26946.0, 20528.0, 17469.0, 1278.0, 10004.0, 18373.0, 18819.0, 31450.0, 9573.0, 10422.0, 1364.0, 9789.0]', '[4806.0, 24114.0, 14708.0, 6995.0, 28486.0, 10647.0, 18352.0, 31418.0, 24520.0, 16398.0, 11521.0, 14161.0, 19737.0, 19135.0, 19281.0, 6249.0, 3805.0, 13509.0, 26268.0, 15495.0, 26122.0]', '[26630.0, 25345.0, 29704.0, 32498.0, 3925.0, 20799.0, 18397.0, 229.0, 31754.0, 24515.0, 28962.0, 10910.0, 19949.0, 30568.0, 7572.0, 5565.0, 16074.0, 26031.0, 25391.0, 26821.0, 754.0]', '[24307.0, 21293.0, 30114.0, 26423.0, 12022.0, 27830.0, 4575.0, 24230.0, 1655.0, 19569.0, 12570.0, 31923.0, 7636.0, 2623.0, 8194.0, 32611.0, 2609.0, 13161.0, 8779.0, 28255.0, 30195.0]', '[21136.0, 1054.0, 24722.0, 17659.0, 13037.0, 7278.0, 22336.0, 658.0, 22361.0, 15075.0, 19557.0, 22925.0, 31591.0, 25608.0, 28396.0, 8419.0, 16893.0, 27358.0, 4182.0, 6972.0, 16343.0]', '[31907.0, 12290.0, 5131.0, 18238.0, 12158.0, 4098.0, 28315.0, 26816.0, 25162.0, 21188.0, 13234.0, 330.0, 12393.0, 16337.0, 28652.0, 943.0, 1615.0, 15589.0, 30774.0, 14902.0, 25605.0]');
INSERT INTO `wave_load` VALUES (2, 1, 30, '[788.0, 21363.0, 22867.0, 4693.0, 8405.0, 5686.0, 23941.0, 24307.0, 21293.0, 30114.0, 26423.0, 12022.0, 27830.0, 4575.0, 24230.0, 1655.0, 19569.0, 12570.0, 31923.0, 7636.0, 2623.0]', '[26113.0, 5071.0, 9676.0, 26667.0, 28666.0, 29309.0, 4806.0, 24114.0, 14708.0, 6995.0, 28486.0, 10647.0, 18352.0, 31418.0, 24520.0, 16398.0, 11521.0, 14161.0, 19737.0, 19135.0, 19281.0]', '[23977.0, 28.0, 26153.0, 4686.0, 8826.0, 6021.0, 32745.0, 23481.0, 29996.0, 16439.0, 27918.0, 1146.0, 29292.0, 8762.0, 4449.0, 20675.0, 8638.0, 905.0, 28350.0, 30714.0, 28664.0]', '[10008.0, 1885.0, 15629.0, 1528.0, 29087.0, 20415.0, 12268.0, 26809.0, 248.0, 1052.0, 21196.0, 29458.0, 20448.0, 19897.0, 10940.0, 23146.0, 475.0, 30524.0, 16336.0, 2505.0, 7085.0]', '[18132.0, 25184.0, 14295.0, 30080.0, 6816.0, 11662.0, 31762.0, 1012.0, 29152.0, 7958.0, 10866.0, 18392.0, 14600.0, 9567.0, 16925.0, 792.0, 27663.0, 9464.0, 14333.0, 28164.0, 9970.0]', '[23073.0, 1723.0, 11333.0, 3309.0, 29421.0, 1340.0, 17124.0, 31907.0, 12290.0, 5131.0, 18238.0, 12158.0, 4098.0, 28315.0, 26816.0, 25162.0, 21188.0, 13234.0, 330.0, 12393.0, 16337.0]', '[15790.0, 1071.0, 2598.0, 7864.0, 19964.0, 29873.0, 5602.0, 23556.0, 13712.0, 7479.0, 26633.0, 30807.0, 540.0, 13851.0, 15147.0, 26946.0, 20528.0, 17469.0, 1278.0, 10004.0, 18373.0]', '[28689.0, 7200.0, 20825.0, 12249.0, 27726.0, 6064.0, 27531.0, 26630.0, 25345.0, 29704.0, 32498.0, 3925.0, 20799.0, 18397.0, 229.0, 31754.0, 24515.0, 28962.0, 10910.0, 19949.0, 30568.0]', '[12941.0, 21974.0, 28650.0, 26679.0, 27681.0, 17902.0, 9318.0, 4084.0, 7491.0, 25269.0, 7462.0, 6602.0, 13882.0, 25828.0, 29560.0, 8714.0, 15615.0, 12549.0, 18337.0, 13337.0, 9152.0]', '[29956.0, 24778.0, 32168.0, 10116.0, 7516.0, 14721.0, 28423.0, 21136.0, 1054.0, 24722.0, 17659.0, 13037.0, 7278.0, 22336.0, 658.0, 22361.0, 15075.0, 19557.0, 22925.0, 31591.0, 25608.0]');

-- ----------------------------
-- Table structure for weight
-- ----------------------------
DROP TABLE IF EXISTS `weight`;
CREATE TABLE `weight`  (
  `weight_id` int NOT NULL AUTO_INCREMENT,
  `project_id` int NULL DEFAULT NULL,
  `loading_file_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `loading_file_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `code` int NULL DEFAULT NULL,
  `message` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `weight_distributions` json NULL COMMENT '各个分项得重量分布',
  `all_dirs` json NULL COMMENT '当前校核工况下的站间重量分布',
  `all_rst` json NULL COMMENT '总体重量分布 排水量和重心纵向坐标',
  `sub_gravities` json NULL,
  PRIMARY KEY (`weight_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of weight
-- ----------------------------
INSERT INTO `weight` VALUES (1, 1, 'download?path=xx', 'xx', NULL, NULL, '[{\"name\": \"项目-哈哈1\", \"weightItems\": [20267.0, 17713.0, 19793.0, 18634.0, 25472.0, 20972.0, 22830.0, 24901.0, 28442.0, 5177.0, 13877.0, 25770.0, 702.0, 14364.0, 1381.0, 16590.0, 8823.0, 2237.0, 8023.0, 32179.0]}, {\"name\": \"项目-哈哈2\", \"weightItems\": [16595.0, 20169.0, 2327.0, 12042.0, 31310.0, 28182.0, 11058.0, 7926.0, 9487.0, 1670.0, 32528.0, 5651.0, 2258.0, 7213.0, 9860.0, 25783.0, 21286.0, 2742.0, 8610.0, 4472.0]}, {\"name\": \"项目-哈哈\", \"weightItems\": [7128.0, 18434.0, 5841.0, 20718.0, 3503.0, 14867.0, 24865.0, 10938.0, 1881.0, 9257.0, 22750.0, 28614.0, 18598.0, 28458.0, 2661.0, 26063.0, 32756.0, 20807.0, 20278.0, 19489.0]}]', '[19435.0, 6365.0, 32075.0, 7586.0, 1386.0, 7833.0, 8360.0, 13330.0, 26048.0, 8928.0, 29492.0, 12433.0, 23840.0, 6766.0, 1735.0, 19810.0, 11599.0, 11837.0, 21892.0, 31982.0]', '{\"xg\": 29352.0, \"displacement\": 7328.0}', NULL);
INSERT INTO `weight` VALUES (2, 7, 'download?path=D:\\wch\\work\\文档\\计算软件\\load.xlsx', 'D:\\wch\\work\\文档\\计算软件\\load.xlsx', NULL, NULL, '[{\"name\": \"项目-哈哈\", \"weightItems\": [12052.0, 27350.0, 1150.0, 16941.0, 21724.0, 13966.0, 3430.0, 31107.0, 30191.0, 18007.0, 11337.0, 15457.0, 12287.0, 27753.0, 10383.0, 14945.0, 8909.0, 32209.0, 9758.0, 24221.0]}, {\"name\": \"项目-哈哈\", \"weightItems\": [18588.0, 6422.0, 24946.0, 27506.0, 13030.0, 16413.0, 29168.0, 900.0, 32591.0, 18762.0, 1655.0, 17410.0, 6359.0, 27624.0, 20537.0, 21548.0, 6483.0, 27595.0, 4041.0, 3602.0]}, {\"name\": \"项目-哈哈\", \"weightItems\": [24350.0, 10291.0, 30836.0, 9374.0, 11020.0, 4596.0, 24021.0, 27348.0, 23199.0, 19668.0, 24484.0, 8281.0, 4734.0, 53.0, 1999.0, 26418.0, 27938.0, 6900.0, 3788.0, 18127.0]}]', '[467.0, 3728.0, 14893.0, 24648.0, 22483.0, 17807.0, 2421.0, 14310.0, 6617.0, 22813.0, 9514.0, 14309.0, 7616.0, 18935.0, 17451.0, 20600.0, 5249.0, 16519.0, 31556.0, 22798.0]', '{\"xg\": 6224.0, \"displacement\": 30303.0}', NULL);

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
) ENGINE = MyISAM AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表描述信息' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `xxcolumn_definition` VALUES (9, 'ship_param', 'waterline_length', '设计水线长', 4, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (10, 'ship_param', 'moulded_breadth', '型宽', 5, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (11, 'ship_param', 'moulded_depth', '型深', 6, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (12, 'ship_param', 'designed_draft', '设计吃水', 7, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (13, 'ship_param', 'navigation_area', '航行区域', 8, b'1', b'0', 'select', '/ship/combobox/area', b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"0\":\"无限航区\",\"1\":\"有限航区\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (14, 'ship_param', 'displacement', '排水量', 10, b'1', b'0', 'number', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (15, 'ship_param', 'portrait_gravity', '重心纵向位置', 11, b'1', b'0', 'number', '', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (16, 'ship_param', 'check_type', '校验类型', 9, b'0', b'0', 'select', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"0\":\"极限工况校验\",\"1\":\"巡航工况校验\"}', b'1', NULL, NULL, NULL, NULL);
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
INSERT INTO `xxcolumn_definition` VALUES (42, 'section', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (43, 'section', 'x_coordinate', '校核剖面位置X', 3, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (44, 'section', 'section_file_name', '剖面文件名称', 5, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (45, 'section', 'section_file_path', '剖面文件路径', 6, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (46, 'section', 'component_span', '剖面构件跨距a', 7, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (47, 'section', 'create_time', '创建时间', 8, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (48, 'section', 'update_time', '更新时间', 9, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (49, 'section', 'section_file', '剖面文件', 4, b'1', b'0', 'file', NULL, b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (50, 'buoyancy_param', 'param_id', '参数id', 1, b'0', b'1', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (51, 'buoyancy_param', 'buoyancy_curve_file_name', '静水力文件名称', 3, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (52, 'buoyancy_param', 'buoyancy_curve_file_path', '静水力文件路径', 4, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (53, 'buoyancy_param', 'bonjung_curve_file_name', '邦戎曲线文件名称', 6, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (54, 'buoyancy_param', 'bonjung_curve_file_path', '邦戎曲线文件路径', 7, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (55, 'buoyancy_param', 'draft_forward', '艏吃水（m）', 8, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (56, 'buoyancy_param', 'draft_aft', '艉吃水（m）', 9, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (57, 'buoyancy_param', 'draft_mean', '平均吃水（m）', 10, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (58, 'buoyancy_param', 'create_time', '创建时间', 13, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (59, 'buoyancy_param', 'update_time', '更新时间', 14, b'0', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (60, 'buoyancy_param', 'buoyancy_curve_file', '静水力文件', 2, b'1', b'0', 'file', NULL, b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (61, 'buoyancy_param', 'bonjung_curve_file', '邦戎曲线文件', 5, b'1', b'0', 'file', NULL, b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (62, 'buoyancy_param', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (63, 'buoyancy_param', 'precision_displacement', '排水量精度', 11, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (64, 'buoyancy_param', 'precision_gravity', '重心纵向精度', 12, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (66, 'bulkhead', 'bulkhead_id', '横舱壁id', 1, b'0', b'1', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (67, 'bulkhead', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (69, 'bulkhead', 'bulkhead_file_name', '各层甲板高度文件名称', 3, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (70, 'bulkhead', 'bulkhead_file_path', '各层甲板高度文件路径', 4, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (71, 'bulkhead', 'collision_bulkhead', '是否防撞舱壁', 5, b'1', b'0', 'select', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"是\":\"是\",\"否\":\"否\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (72, 'bulkhead', 'section_file', '剖面文件', 6, b'1', b'0', 'file', NULL, b'0', NULL, NULL, b'1', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (73, 'bulkhead_compartment', 'compartment_id', '舱壁区间id', 1, b'0', b'1', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (74, 'bulkhead_compartment', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (75, 'bulkhead_compartment', 'bulkhead_id', '横舱壁id', 3, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (76, 'bulkhead_compartment', 'strip_plate_width', '带板宽度', 4, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (77, 'bulkhead_compartment', 'strength_material_specification', '扶强材规格', 5, b'1', b'0', 'selectCascader', '/compartment/getCascader', b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (78, 'bulkhead_compartment', 'liquid', '是否液舱', 6, b'1', b'0', 'select', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, '{\"是\":\"是\",\"否\":\"否\"}', b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (79, 'bulkhead_compartment', 'plate_thickness', '板厚', 7, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (80, 'bulkhead_compartment', 'material', '材料', 8, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (81, 'bulkhead_compartment', 'plate_width', '板宽', 9, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (82, 'bulkhead_compartment', 'compartment', '区间', 10, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (83, 'dist', 'dist_id', '应力分布ID', 1, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (84, 'dist', 'project_id', '项目id', 2, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'1', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (85, 'dist', 'section_id', '剖面id', 3, b'0', b'0', 'number', NULL, b'1', 'exact', NULL, b'0', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (86, 'dist', 'extreme_h', '中拱极限弯矩', 4, b'1', b'0', 'number', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (87, 'dist', 'extreme_s', '中垂极限弯矩', 5, b'0', b'0', 'number', NULL, b'0', NULL, NULL, b'0', b'0', NULL, NULL, NULL, b'0', NULL, NULL, NULL, NULL);
INSERT INTO `xxcolumn_definition` VALUES (88, 'bulkhead_compartment', 'height_above', '距基线高度', 11, b'1', b'0', 'text', NULL, b'0', NULL, NULL, b'1', b'0', NULL, NULL, NULL, b'1', NULL, NULL, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of xxtable_definition
-- ----------------------------
INSERT INTO `xxtable_definition` VALUES (1, 'project', '项目管理', 'project', 'select * from project', NULL, NULL, NULL, NULL, NULL, 'project_id');
INSERT INTO `xxtable_definition` VALUES (2, 'ship_param', '船舶参数', 'ship_param', 'select * from ship_param', NULL, NULL, NULL, NULL, NULL, 'param_id');
INSERT INTO `xxtable_definition` VALUES (3, 't_profile', 'T型材', 't_profile', 'select * from t_profile', NULL, NULL, NULL, NULL, NULL, 'profile_id');
INSERT INTO `xxtable_definition` VALUES (4, 'bulb_flat', '扁球钢', 'bulb_flat', 'select * from bulb_flat', NULL, NULL, NULL, NULL, NULL, 'profile_id');
INSERT INTO `xxtable_definition` VALUES (5, 'section', '剖面', 'section', 'select * from section', NULL, NULL, NULL, NULL, NULL, 'section_id');
INSERT INTO `xxtable_definition` VALUES (6, 'buoyancy_param', '浮力计算', 'buoyancy_param', 'select * from buoyancy_param', NULL, NULL, NULL, NULL, NULL, 'param_id');
INSERT INTO `xxtable_definition` VALUES (8, 'bulkhead', '横舱壁', 'bulkhead', 'select * from bulkhead', NULL, NULL, NULL, NULL, NULL, 'bulkhead_id');
INSERT INTO `xxtable_definition` VALUES (9, 'bulkhead_compartment', '横舱壁区间', 'bulkhead_compartment', 'select * from bulkhead_compartment', NULL, NULL, NULL, NULL, NULL, 'compartment_id');
INSERT INTO `xxtable_definition` VALUES (10, 'dist', '应力分布', 'dist', 'select * from dist', NULL, NULL, NULL, NULL, NULL, 'dist_id');

SET FOREIGN_KEY_CHECKS = 1;
