/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : calculation

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 28/05/2023 00:21:04
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
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of bulb_flat
-- ----------------------------
INSERT INTO `bulb_flat` VALUES (22, 'x', 23, 23, 45, 23, 35, 53, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (23, 'xx', 23, 42, 42, 324, 23, 5, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (24, 'xxx', 42, 32, 42, 34, 534, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (25, 'xxxx', 42, 24, 53, 4, 53, 6, NULL, NULL);
INSERT INTO `bulb_flat` VALUES (26, 'xxxxx', 42, 2, 4, 5, 53, 6, NULL, NULL);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '参数表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of buoyancy_param
-- ----------------------------
INSERT INTO `buoyancy_param` VALUES (1, 1, 'ServiceImpl.cpp', 'd:/tempx/2023-05-12/C7jWlR4B/ServiceImpl.cpp', 'calculation.grpc.pb.cc', 'd:/tempx/2023-05-12/EzV49GUH/calculation.grpc.pb.cc', 12, 54, 54, '2023-05-22 11:03:47', '2023-05-22 11:03:47', 56, 12);
INSERT INTO `buoyancy_param` VALUES (2, 7, 'static0508-2.xlsx', 'd:/tempx/2023-05-13/l7SSDX94/static0508-2.xlsx', 'static0508-2.xlsx', 'd:/tempx/2023-05-13/5TgI9MhY/static0508-2.xlsx', 4.55, 4.55, 4.55, '2023-05-22 15:47:33', '2023-05-22 15:47:33', 0.005, 0.001);

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
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of buoyancy_result
-- ----------------------------
INSERT INTO `buoyancy_result` VALUES (1, 1, '[6334.0, 26500.0, 19169.0, 15724.0, 11478.0, 29358.0, 26962.0, 24464.0, 5705.0, 28145.0, 23281.0, 16827.0, 9961.0, 491.0, 2995.0, 11942.0, 4827.0, 5436.0, 32391.0, 14604.0, 3902.0]', '[{\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 0, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 1, \"floatxpos\": 0.5, \"floatforce\": 0.2}, {\"da\": 12.0, \"df\": 15.0, \"dm\": 23.0, \"biasf\": 15.0, \"biasx\": 23.0, \"index\": 2, \"floatxpos\": 0.5, \"floatforce\": 0.2}]');
INSERT INTO `buoyancy_result` VALUES (30, 2, '[0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]', '[{\"da\": 4.55, \"df\": 4.55, \"dm\": 4.55, \"biasf\": 0.0048964316188452464, \"biasx\": 0.001560318078655463, \"index\": 1, \"floatxpos\": -3.707796741302857, \"floatforce\": 4740.424623875726}, {\"da\": 4.564217012497937, \"df\": 4.564217012497937, \"dm\": 4.564217012497937, \"biasf\": 0.000002918386169288264, \"biasx\": 0.0013593469912381209, \"index\": 2, \"floatxpos\": -3.735128809191615, \"floatforce\": 4763.736097537886}, {\"da\": 4.59588839761597, \"df\": 4.529165360955323, \"dm\": 4.562526879285647, \"biasf\": 0.0009920007241136922, \"biasx\": 0.00004717063364194444, \"index\": 3, \"floatxpos\": -3.926415206175304, \"floatforce\": 4768.475643449497}]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '剖面表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES (2, NULL, 88.8, 'algorithm.json', 'd:/tempx/2023-05-11/06UI2lx4/algorithm.json', 777, '2023-05-11 14:31:05', '2023-05-11 14:33:07');
INSERT INTO `section` VALUES (3, NULL, 1, 'test', 'd:/tempx/2023-05-12/WY0eNDqJ/重点到了.jpg', 1, '2023-05-12 10:53:42', '2023-05-12 10:53:42');

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
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `pwbm` json NULL COMMENT '波峰抨击弯矩',
  `nwb` json NULL COMMENT '波谷抨击弯矩',
  PRIMARY KEY (`slam_load_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of slam_load
-- ----------------------------
INSERT INTO `slam_load` VALUES (2, 7, '[30932.0, 2154.0, 17189.0, 31329.0, 28692.0, 10555.0, 16549.0, 9512.0, 18060.0, 3753.0, 12423.0, 25996.0, 12529.0, 17437.0, 12949.0, 23195.0, 20416.0, 16105.0, 16282.0, 25734.0, 11701.0]', '[9832.0, 4169.0, 25721.0, 19976.0, 2368.0, 21425.0, 3434.0, 7441.0, 30145.0, 21718.0, 16139.0, 16279.0, 16687.0, 22549.0, 19866.0, 193.0, 3297.0, 28286.0, 24488.0, 12455.0, 18114.0]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of static_load
-- ----------------------------
INSERT INTO `static_load` VALUES (1, 7, '[10291.0, 4596.0, 19668.0, 53.0, 6900.0, 3728.0, 17807.0, 22813.0, 18935.0, 16519.0, 6224.0, 14989.0, 3093.0, 29314.0, 13458.0, 14798.0, 28009.0, 18538.0, 18190.0, 19815.0, 16202.0]', '[3602.0, 9374.0, 27348.0, 8281.0, 26418.0, 18127.0, 24648.0, 14310.0, 14309.0, 20600.0, 22798.0, 5844.0, 3195.0, 30523.0, 7448.0, 20580.0, 19589.0, 20472.0, 6038.0, 7958.0, 19156.0]', '[30836.0, 24021.0, 24484.0, 1999.0, 3788.0, 14893.0, 2421.0, 9514.0, 17451.0, 31556.0, 11008.0, 32702.0, 14343.0, 9503.0, 6618.0, 15281.0, 27157.0, 12292.0, 29657.0, 22888.0, 2634.0]', '[24350.0, 11020.0, 23199.0, 4734.0, 27938.0, 467.0, 22483.0, 6617.0, 7616.0, 5249.0, 30303.0, 32609.0, 20485.0, 1587.0, 25200.0, 19796.0, 20798.0, 23622.0, 24179.0, 6191.0, 11511.0]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wave_load
-- ----------------------------
INSERT INTO `wave_load` VALUES (1, 7, '[20055.0, 1416.0, 24389.0, 9789.0, 23757.0, 28692.0, 3753.0, 12949.0, 25734.0, 20053.0, 27982.0, 10466.0, 20649.0, 7391.0, 20159.0, 2668.0, 2625.0, 27593.0, 8360.0, 4667.0, 21694.0]', '[18875.0, 10021.0, 21003.0, 24182.0, 25721.0, 7441.0, 16687.0, 28286.0, 5786.0, 4313.0, 7129.0, 17253.0, 2168.0, 9314.0, 7518.0, 29213.0, 13061.0, 17222.0, 235.0, 2695.0, 4678.0]', '[20328.0, 21881.0, 5075.0, 15255.0, 9832.0, 21425.0, 16139.0, 193.0, 18114.0, 912.0, 481.0, 12044.0, 13186.0, 10202.0, 11833.0, 17192.0, 1543.0, 22725.0, 22413.0, 7285.0, 22658.0]', '[28433.0, 5699.0, 26869.0, 10285.0, 17189.0, 9512.0, 12529.0, 16105.0, 12263.0, 27756.0, 2161.0, 20024.0, 14018.0, 25824.0, 8177.0, 7627.0, 14181.0, 31286.0, 30833.0, 21624.0, 22593.0]', '[22646.0, 31998.0, 10712.0, 16423.0, 30932.0, 10555.0, 12423.0, 23195.0, 11701.0, 10808.0, 4144.0, 21659.0, 8313.0, 3625.0, 28070.0, 13985.0, 1924.0, 13031.0, 30974.0, 12550.0, 26302.0]', '[24272.0, 23844.0, 27892.0, 13401.0, 28617.0, 2368.0, 21718.0, 19866.0, 12455.0, 31185.0, 23646.0, 11173.0, 4745.0, 17958.0, 24372.0, 1763.0, 30527.0, 17505.0, 19187.0, 18896.0, 26576.0]', '[20142.0, 28476.0, 14688.0, 31426.0, 31329.0, 18060.0, 17437.0, 16282.0, 24355.0, 19558.0, 20450.0, 29510.0, 9905.0, 25874.0, 32270.0, 4099.0, 27432.0, 7900.0, 25760.0, 2125.0, 25484.0]', '[4886.0, 18651.0, 2510.0, 10585.0, 2154.0, 16549.0, 25996.0, 20416.0, 20671.0, 20945.0, 20222.0, 26439.0, 28022.0, 4414.0, 28297.0, 8480.0, 29972.0, 142.0, 29170.0, 13694.0, 22466.0]', '[29869.0, 3557.0, 17861.0, 27088.0, 19976.0, 30145.0, 22549.0, 24488.0, 4313.0, 28321.0, 5535.0, 26154.0, 18787.0, 29334.0, 17773.0, 4802.0, 31003.0, 13064.0, 19711.0, 28019.0, 23851.0]', '[26362.0, 10322.0, 2600.0, 5002.0, 4169.0, 3434.0, 16279.0, 3297.0, 31316.0, 1832.0, 23196.0, 26292.0, 4474.0, 26477.0, 7487.0, 3102.0, 11023.0, 8492.0, 14270.0, 140.0, 17371.0]');

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of weight
-- ----------------------------
INSERT INTO `weight` VALUES (1, 1, 'xxx', 'xx', NULL, NULL, '[{\"name\": \"qq\", \"weightItems\": [27595.0, 4041.0, 3602.0, 24350.0, 10291.0, 30836.0, 9374.0, 11020.0, 4596.0, 24021.0, 27348.0, 23199.0, 19668.0, 24484.0, 8281.0, 4734.0, 53.0, 1999.0, 26418.0, 27938.0]}, {\"name\": \"q\", \"weightItems\": [6900.0, 3788.0, 18127.0, 467.0, 3728.0, 14893.0, 24648.0, 22483.0, 17807.0, 2421.0, 14310.0, 6617.0, 22813.0, 9514.0, 14309.0, 7616.0, 18935.0, 17451.0, 20600.0, 5249.0]}, {\"name\": \"\", \"weightItems\": [16519.0, 31556.0, 22798.0, 30303.0, 6224.0, 11008.0, 5844.0, 32609.0, 14989.0, 32702.0, 3195.0, 20485.0, 3093.0, 14343.0, 30523.0, 1587.0, 29314.0, 9503.0, 7448.0, 25200.0]}]', '[13458.0, 6618.0, 20580.0, 19796.0, 14798.0, 15281.0, 19589.0, 20798.0, 28009.0, 27157.0, 20472.0, 23622.0, 18538.0, 12292.0, 6038.0, 24179.0, 18190.0, 29657.0, 7958.0, 6191.0]', '{\"xg\": 22888.0, \"displacement\": 19815.0}', NULL);
INSERT INTO `weight` VALUES (2, 7, 'load1.xlsx', 'D:\\wch\\work\\文档\\计算软件\\load.xlsx', NULL, NULL, '[{\"name\": \"电气\", \"weightItems\": [-1365.312336164412, 1078.3381828667295, 618.8658645276292, -0.389928698752228, -0.389928698752228, 1.2254901960784317, 1.6154188948306594, 1.6154188948306594, 2.261586452762923, 2.261586452762923, 2.261586452762923, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]}, {\"name\": \"船体\", \"weightItems\": [-2369.725083883821, 1037.1261271888434, 1593.592390164622, 203.6689341512006, 2.7065639089860554, 15.522875816993466, 12.816311908007409, 12.816311908007409, 17.942836671210372, 17.942836671210372, 17.942836671210372, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0]}, {\"name\": \"装备\", \"weightItems\": [-280.9364186851211, -301.0748269896193, 720.6120242214532, 12.134515570934257, -0.25735294117647084, -0.25735294117647084, -0.25735294117647084, -0.25735294117647084, -0.25735294117647084, 113.71323529411762, 67.53676470588233, 28.93382352941177, -28.051470588235293, -66.65441176470587, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292]}]', '[-4015.973838733354, 1814.3894830659533, 2933.070278913704, 215.41352102338263, 2.0592822690573565, 16.491013071895427, 14.174377861661595, 14.174377861661595, 19.947070182796825, 133.91765841809092, 87.74118782985563, 28.93382352941177, -28.051470588235293, -66.65441176470587, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292, 10.551470588235292]', '{\"xg\": 3.911545801526748, \"displacement\": 8384.000000000002}', '[{\"xg\": 16.899484536082507, \"name\": \"电气\", \"displacement\": 2328.000000000002}, {\"xg\": -2.8110878661087577, \"name\": \"船体\", \"displacement\": 3823.999999999999}, {\"xg\": 1.8826164874552167, \"name\": \"装备\", \"displacement\": 2232.0000000000005}]');

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
) ENGINE = MyISAM AUTO_INCREMENT = 66 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '表描述信息' ROW_FORMAT = DYNAMIC;

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
INSERT INTO `xxtable_definition` VALUES (6, 'buoyancy_param', '浮力计算', 'buoyancy_param', 'select * from buoyancy_param', NULL, NULL, NULL, NULL, NULL, 'param_id');

SET FOREIGN_KEY_CHECKS = 1;
