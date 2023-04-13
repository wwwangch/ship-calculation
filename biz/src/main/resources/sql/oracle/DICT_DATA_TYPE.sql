/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.100.33_1521_XE
 Source Server Type    : Oracle
 Source Server Version : 110200
 Source Host           : 192.168.100.33:1521
 Source Schema         : GANSU

 Target Server Type    : Oracle
 Target Server Version : 110200
 File Encoding         : 65001

 Date: 03/12/2021 15:07:42
*/


-- ----------------------------
-- Table structure for DICT_DATA_TYPE
-- ----------------------------
DROP TABLE "GANSU"."DICT_DATA_TYPE";
CREATE TABLE "GANSU"."DICT_DATA_TYPE" (
  "ID" VARCHAR2(255 BYTE) ,
  "DICT_DATA_TYPE" VARCHAR2(255 BYTE) ,
  "DICT_DATA_KEY" VARCHAR2(255 BYTE) ,
  "DICT_DATA_VALUE" VARCHAR2(255 BYTE) ,
  "DICT_DATA_DESC" VARCHAR2(255 BYTE) ,
  "CREATE_TIME" DATE ,
  "CREATE_BY" VARCHAR2(255 BYTE) ,
  "UPDATE_TIME" DATE ,
  "UPDATE_BY" VARCHAR2(255 BYTE) 
)
TABLESPACE "SYSTEM"
LOGGING
NOCOMPRESS
PCTFREE 10
INITRANS 1
STORAGE (
  INITIAL 65536 
  NEXT 1048576 
  MINEXTENTS 1
  MAXEXTENTS 2147483645
  FREELISTS 1
  FREELIST GROUPS 1
  BUFFER_POOL DEFAULT
)
PARALLEL 1
NOCACHE
DISABLE ROW MOVEMENT
;

-- ----------------------------
-- Records of DICT_DATA_TYPE
-- ----------------------------
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('1', 'enum_test', '0', '无效', '无效状态11', TO_DATE('2021-03-01 17:40:24', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-05 11:09:53', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('2', 'enum_test', '1', '有效', '有效状态', TO_DATE('2021-03-01 17:40:24', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('4', 'user_status', '0', '无效', '无效用户', NULL, NULL, TO_DATE('2021-03-05 14:55:26', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('5', 'user_status', '1', '有效', '有效用户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('6', 'enum_test', '12', '1', '1', TO_DATE('2021-03-05 14:21:39', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-05 14:26:23', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('7', 'enum_test', '1', '1', '1', TO_DATE('2021-03-05 14:23:58', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-05 16:02:07', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('9', 'enum_test', '3', '1', '3', TO_DATE('2021-03-05 15:29:40', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-05 16:01:59', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('10', 'enum_test', '4', '4', '4', TO_DATE('2021-03-05 16:07:26', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('11', 'enum_test', '5', '5', '5', TO_DATE('2021-03-05 16:07:40', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('216', 'd_reg_dep', '1', '省法院', NULL, TO_DATE('2021-05-08 10:39:31', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('217', 'd_reg_dep', '10', '省财政厅', NULL, TO_DATE('2021-05-08 10:39:41', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('218', 'd_reg_dep', '11', '省人社厅', NULL, TO_DATE('2021-05-08 10:39:50', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('219', 'd_reg_dep', '12', '省国土资源厅', NULL, TO_DATE('2021-05-08 10:39:58', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('220', 'd_reg_dep', '13', '省住房城乡建设厅', NULL, TO_DATE('2021-05-08 10:40:07', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('221', 'd_reg_dep', '14', '省水利厅', NULL, TO_DATE('2021-05-08 10:40:18', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('222', 'd_reg_dep', '15', '省林业厅', NULL, TO_DATE('2021-05-08 10:40:30', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('223', 'd_reg_dep', '16', '省安监局', NULL, TO_DATE('2021-05-08 10:40:38', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('224', 'd_reg_dep', '17', '省文化保护中心', NULL, TO_DATE('2021-05-08 10:40:47', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('225', 'd_reg_dep', '18', '省政府金融办', NULL, TO_DATE('2021-05-08 10:40:56', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('226', 'd_reg_dep', '19', '省商务厅', NULL, TO_DATE('2021-05-08 10:41:04', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('227', 'd_reg_dep', '20', '省交通运输厅', NULL, TO_DATE('2021-05-08 10:41:14', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('228', 'd_reg_dep', '2', '省发改委', NULL, TO_DATE('2021-05-08 10:41:22', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('229', 'd_reg_dep', '21', '省卫生计生委', NULL, TO_DATE('2021-05-08 10:41:42', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('230', 'd_reg_dep', '22', '省环保厅', NULL, TO_DATE('2021-05-08 10:41:51', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('231', 'd_reg_dep', '23', '省地税局', NULL, TO_DATE('2021-05-08 10:42:01', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('232', 'd_reg_dep', '24', '省质监局', NULL, TO_DATE('2021-05-08 10:42:28', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('233', 'd_reg_dep', '25', '省旅发委', NULL, TO_DATE('2021-05-08 10:42:43', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('234', 'd_reg_dep', '26', '省食品药品监督管理局', NULL, TO_DATE('2021-05-08 10:42:55', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('235', 'd_reg_dep', '27', '省农业厅', NULL, TO_DATE('2021-05-08 10:43:05', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('236', 'd_reg_dep', '29', '省新闻出版广电局', NULL, TO_DATE('2021-05-08 10:43:19', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('237', 'd_reg_dep', '3', '省工信委', NULL, TO_DATE('2021-05-08 10:43:45', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('238', 'd_reg_dep', '30', '省邮政管理局', NULL, TO_DATE('2021-05-08 10:43:57', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('239', 'd_reg_dep', '31', '省银监局', NULL, TO_DATE('2021-05-08 10:44:11', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('240', 'd_reg_dep', '32', '省统计局', NULL, TO_DATE('2021-05-08 10:44:20', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('241', 'd_reg_dep', '37', '省通信管理局', NULL, TO_DATE('2021-05-08 10:44:36', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('242', 'd_reg_dep', '38', '省工商局', NULL, TO_DATE('2021-05-08 10:44:46', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('243', 'd_reg_dep', '4', '省教育厅', NULL, TO_DATE('2021-05-08 10:44:56', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('244', 'd_reg_dep', '40', '省粮食局', NULL, TO_DATE('2021-05-08 10:45:04', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('245', 'd_reg_dep', '5', '省科技厅', NULL, TO_DATE('2021-05-08 10:45:12', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('246', 'd_reg_dep', '6', '省公安厅', NULL, TO_DATE('2021-05-08 10:45:20', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('247', 'd_reg_dep', '7', '省文化厅', NULL, TO_DATE('2021-05-08 10:45:29', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('248', 'd_reg_dep', '8', '省国税局', NULL, TO_DATE('2021-05-08 10:45:38', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('249', 'd_reg_dep', '9', '省民政厅', NULL, TO_DATE('2021-05-08 10:45:45', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('250', 'm_tsg_01', 'm_tsg_special01', '特种设备生产及充装单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('251', 'm_tsg_01', 'm_tsg_special02', '特种设备生产及充装单位本年新设户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('252', 'm_tsg_01', 'm_tsg_special03', '特种设备使用单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('253', 'm_tsg_01', 'm_tsg_special04', '特种设备使用单位本年新设户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('254', 'm_tsg_02', 'm_tsg_units01', '设计单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('255', 'm_tsg_02', 'm_tsg_units02', '制造单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('256', 'm_tsg_02', 'm_tsg_units03', '安装改造维修单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('257', 'm_tsg_02', 'm_tsg_units04', '充装单位期末户数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('258', 'm_tsg_03', 'm_tsg_001', '在用锅炉', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('259', 'm_tsg_03', 'm_tsg_002', '在用电梯', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('260', 'm_tsg_03', 'm_tsg_003', '在用压力管道', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('261', 'm_tsg_03', 'm_tsg_004', '在用起重机械', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('262', 'm_tsg_03', 'm_tsg_005', '在用客运索道', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('263', 'm_tsg_03', 'm_tsg_006', '在用压力容器', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('264', 'm_tsg_03', 'm_tsg_007', '在用厂（场）内专用机动车', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('265', 'm_tsg_03', 'm_tsg_008', '在用大型游乐设施', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('266', 'm_tsg_03', 'm_tsg_009', '在用气瓶', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('267', 'tsg_type_code_1', '1000', '锅炉', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('268', 'tsg_type_code_1', '2000', '压力容器', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('269', 'tsg_type_code_1', '3000', '电梯', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('270', 'tsg_type_code_1', '4000', '起重机械', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('271', 'tsg_type_code_1', '5000', '场（厂）内专用机动车辆', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('272', 'tsg_type_code_1', '6000', '大型游乐设施', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('273', 'tsg_type_code_1', '7000', '压力管道元件', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('274', 'tsg_type_code_1', '8000', '压力管道', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('275', 'tsg_type_code_1', 'F000', '安全附件', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('276', 'tsg_type_code_1', '9000', '客运索道', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('277', 'tsg_type_code_2', '2300', '气瓶', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('278', 'district_code_2', '620100', '兰州', '兰州', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('279', 'district_code_2', '620200', '嘉峪关', '嘉峪关', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('280', 'district_code_2', '620300', '金昌', '金昌', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('281', 'district_code_2', '620400', '白银', '白银', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('282', 'district_code_2', '620500', '天水', '天水', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('283', 'district_code_2', '620600', '武威', '武威', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('286', 'district_code_2', '620700', '张掖', '张掖', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('287', 'district_code_2', '620800', '平凉', '平凉', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('288', 'district_code_2', '620900', '酒泉', '酒泉', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('289', 'district_code_2', '621000', '庆阳', '庆阳', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('290', 'district_code_2', '621100', '定西', '定西', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('291', 'district_code_2', '621200', '陇南', '陇南', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('292', 'district_code_2', '622900', '临夏回族自治州', '临夏回族自治州', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('293', 'district_code_2', '623000', '甘南藏族自治州', '甘南藏族自治州', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('294', 'district_code_2', '623100', '甘肃矿区', '甘肃矿区', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('295', 'district_code_2', '623200', '东风矿场', '东风矿场', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('296', 'district_code_2', '629999', '其他', '其他', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('298', 'm_dianti', 'm_dianti_01', '本年累计电梯应急处置次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('299', 'm_dianti', 'm_dianti_02', '本年累计电梯困人次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('300', 'm_dianti', 'm_dianti_03', '本年累计解救受困人数', '人', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('301', 'm_dianti', 'm_dianti_04', '本月新增电梯应急处置次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('302', 'm_dianti', 'm_dianti_05', '本月新增困人次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('303', 'm_dianti', 'm_dianti_06', '本年应急到达超30分钟次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('304', 'm_tsg_04', 'ts010101019', '应检验设备数量', '台(套)', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('305', 'm_tsg_04', 'ts010101020', '实际检验设备数量', '台(套)', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('306', 'm_tsg_04', 'ts010101021', '超期检验设备数量', '台(套)', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('307', 'm_tsg_04', 'ts010101020/ts010101019', '设备检验率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('308', 'dianti_type_01', '3100', '曳引与强制驱动电梯', '曳引与强制驱动电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('309', 'dianti_type_01', '3200', '液压驱动电梯', '液压驱动电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('310', 'dianti_type_01', '3300', '自动扶梯与自动人行道', '自动扶梯与自动人行道', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('311', 'dianti_type_01', '3400', '其它类型电梯', '其它类型电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('312', 'dianti_type_011', '3110', '曳引驱动乘客电梯', '曳引驱动乘客电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('313', 'dianti_type_011', '3120', '曳引驱动载货电梯', '曳引驱动载货电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('314', 'dianti_type_011', '3130', '强制驱动载货电梯', '强制驱动载货电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('316', 'dianti_type_012', '3210', '液压乘客电梯', '液压乘客电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('317', 'dianti_type_012', '3220', '液压载货电梯', '液压载货电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('318', 'dianti_type_013', '3310', '自动扶梯', '自动扶梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('319', 'dianti_type_013', '3320', '自动人行道', '自动人行道', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('320', 'dianti_type_014', '3410', '防爆电梯', '防爆电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('321', 'dianti_type_014', '3420', '消防员电梯', '消防员电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('322', 'dianti_type_014', '3430', '杂物电梯', '杂物电梯', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('323', 'm_canyin_2', 'cy010102001', '餐饮日常检查次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('324', 'm_canyin_2', 'cy010102002', '餐饮日常检查主体户数', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('325', 'm_canyin_2', 'cy010102003', '餐饮检查发现问题次数', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('326', 'm_canyin_2', 'cy010102004', '餐饮检查发现问题户数', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('327', 'm_canyin_1', 'cy010101001', '餐饮许可期末实有量', '张', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('328', 'm_canyin_1', 'cy010101002', '餐饮许可新登记量', '张', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('329', 'm_canyin_1', 'cy010101003', '餐饮许可注销量', '张', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('330', 'm_canyin_2', 'cy010102003/cy010102001', '餐饮检查问题发现率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('331', 'd_relevant_units', 'N01', '设计单位', '设计单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('332', 'd_relevant_units', 'N02', '制造单位', '制造单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('333', 'd_relevant_units', 'N03', '安装改造维修单位', '安装改造维修单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('334', 'd_relevant_units', 'N04', '使用单位', '使用单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('335', 'busine_pro_name', '01', '预包装食品销售', '预包装食品销售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('336', 'busine_pro_name', '02', '散装类食品销售', '散装类食品销售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('337', 'busine_pro_name', '03', '特殊类食品销售', '特殊类食品销售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('338', 'busine_pro_name', '04', '其他类食品销售', '其他类食品销售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('339', 'busine_pro_name', '05', '热食类食品制售', '热食类食品制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('340', 'busine_pro_name', '06', '冷食类食品制售', '冷食类食品制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('341', 'busine_pro_name', '07', '生食类食品制售', '生食类食品制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('342', 'busine_pro_name', '08', '糕点类食品制售', '糕点类食品制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('343', 'busine_pro_name', '09', '自制饮品类制售', '自制饮品类制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('344', 'busine_pro_name', '10', '其他类食品制售', '其他类食品制售', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('345', 'busine_type_info', 'N01', '餐饮服务经营者', '餐饮经营服务者', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('346', 'busine_type_info', 'N0101', '特大型餐馆', '特大型餐馆', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('347', 'busine_type_info', 'N0102', '大型餐馆', '大型餐馆', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('348', 'busine_type_info', 'N0103', '中型餐馆', '中型餐馆', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('349', 'busine_type_info', 'N0104', '小型餐馆', '小型餐馆', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('350', 'busine_type_info', 'N0105', '小餐饮单位', '小餐饮单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('351', 'busine_type_info', 'N0106', '中央厨房', '中央厨房', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('352', 'busine_type_info', 'N0107', '集体用餐配送单位', '集体用餐配送单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('353', 'busine_type_info', 'N0108', '其他餐饮', '其他餐饮', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('354', 'busine_type_info', 'N02', '单位食堂', '单位食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('355', 'busine_type_info', 'N0201', '学校食堂', '学校食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('356', 'busine_type_info', 'N0202', '托幼机构食堂', '托幼机构食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('357', 'busine_type_info', 'N0203', '机关企事业单位食堂', '机关企事业单位食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('358', 'busine_type_info', 'N0204', '工地食堂', '工地食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('359', 'busine_type_info', 'N0205', '其他食堂', '其他食堂', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('360', 'm_product_02', 'pq010102001', '检验企业数', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('361', 'm_product_02', 'pq010102002', '检验不合格企业数', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('362', 'm_canyin_3', 'cy010103001', '餐饮检验检测量', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('363', 'm_canyin_3', 'cy010103002', '餐饮检验检测问题量', '次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('364', 'm_canyin_3', 'cy_hgl', '检测合格率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('367', 'product_spotcheck_1', 'pq010101001', '检验产品数', '批次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('368', 'product_spotcheck_1', 'pq010101002', '检验不合格产品数', '批次', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('369', 'spot_check_code', 'N01', '糖精钠超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('371', 'spot_check_code', 'N02', '铝的残留量超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('372', 'spot_check_code', 'N03', '亚硝酸盐超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('373', 'spot_check_code', 'N04', '胭脂红超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('374', 'spot_check_code', 'N05', '铬超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('375', 'spot_check_code', 'N06', '罂粟碱超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('376', 'spot_check_code', 'N07', '吗啡超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('377', 'spot_check_code', 'N08', '可待因超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('378', 'spot_check_code', 'N09', '那可丁超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('379', 'spot_check_code', 'N10', '黄曲霉毒素B1超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('380', 'spot_check_code', 'N11', '大肠菌群超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('381', 'spot_check_code', 'N12', '游离性余氯超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('382', 'spot_check_code', 'N13', '阴离子合成洗涤剂超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('383', 'spot_check_code', 'N14', '脱氢乙酸超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('384', 'spot_check_code', 'N15', '甜蜜素超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('385', 'spot_check_code', 'N16', '酸价超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('386', 'spot_check_code', 'N17', '铅超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('387', 'spot_check_code', 'N18', '克百威超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('388', 'spot_check_code', 'N19', '菌落总数超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('389', 'spot_check_code', 'N20', '过氧化值超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('390', 'spot_check_code', 'N21', '腐霉利超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('391', 'spot_check_code', 'N22', '毒死蜱超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('392', 'spot_check_code', 'N23', '蒂巴因超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('393', 'spot_check_code', 'N24', '挥发性盐基氮超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('394', 'spot_check_code', 'N25', '镉超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('395', 'spot_check_code', 'N26', '吸虫囊蚴超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('396', 'spot_check_code', 'N27', '线虫幼虫超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('397', 'spot_check_code', 'N28', '绦虫裂头蚴超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('398', 'spot_check_code', 'N29', '苯甲酸超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('399', 'spot_check_code', 'N30', '山梨酸超标', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('400', 'spot_check_code', 'N31', '沙门氏菌', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('402', 'm_canyin_4', 'cy010104001', '餐饮服务投诉量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('403', 'm_canyin_4', 'cy010104002', '餐饮服务举报量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('404', 'knowledge_1', 'z010101001', '知识产权数量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('405', 'knowledge_1', 'z010101002', '专利有效量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('406', 'knowledge_1', 'z010101003', '商标有效量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('407', 'knowledge_1', 'z010101004', '著作权累计登记量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('408', 'knowledge_2', 'z010102001', '专利企业数量', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('409', 'knowledge_2', 'z010102002', '著作权企业数量', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('410', 'knowledge_2', 'z010102003', '商标企业数量', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('411', 'knowledge_2', 'z010102004', '商标代理机构数量', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('412', 'cater_risk_code', '01', '餐饮经营许可过期风险', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('413', 'cater_risk_code', '02', '餐饮无证经营风险', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('414', 'cater_risk_code', '03', '餐饮投诉举报风险', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('415', 'localadm_code_2', '620100', '兰州市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('416', 'localadm_code_2', '620200', '嘉峪关市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('417', 'localadm_code_2', '620300', '金昌市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('418', 'localadm_code_2', '620400', '白银市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('419', 'localadm_code_2', '620500', '天水市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('420', 'localadm_code_2', '620600', '武威市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('421', 'localadm_code_2', '620700', '张掖市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('422', 'localadm_code_2', '620800', '平凉市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('423', 'localadm_code_2', '620900', '酒泉市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('424', 'localadm_code_2', '621000', '庆阳市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('425', 'localadm_code_2', '621100', '定西市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('426', 'localadm_code_2', '621200', '陇南市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('427', 'localadm_code_2', '622900', '临夏回族自治州市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('428', 'localadm_code_2', '623000', '甘南藏族自治州市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('429', 'localadm_code_2', '623100', '甘肃矿区工商行政管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('430', 'localadm_code_2', '623200', '东风场区工商行政管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('432', 'm_price_02', 'pr010109001', '居民消费价格指数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('433', 'm_price_02', 'pr010109002', '食品烟酒类居民消费价格指数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('434', 'm_price_02', 'pr010109003', '衣着类居民消费价格指数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('435', 'm_price_02', 'pr010109004', '居住类居民消费价格指数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('436', 'm_price_02', 'pr010109005', '生活用品及服务类居民消费价格指数', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('437', 'pro_risk_code', '01', '1年内问题发现率超过20%', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('438', 'pro_risk_code', '02', '1年内问题发现次数超过3次', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('439', 'm_case', 'pr010102001', '案件数量', '件', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('440', 'm_case_noUse', 'pr010102002', '案值', '万元', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('441', 'm_case', 'pr010102003', '罚款金额', '万元', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('442', 'm_case', 'pr010102004', '没收金额', '万元', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('443', 't_case_code', '01', '粮食等农产品', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('444', 't_case_code', '02', '化肥等农资', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('445', 't_case_code', '03', '电力', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('446', 't_case_code', '04', '成品油、天然气', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('447', 't_case_code', '05', '水', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('448', 't_case_code', '06', '药品', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('449', 't_case_code', '07', '房地产', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('450', 't_case_code', '08', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('451', 's_case_code', '01', '交通运输', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('452', 's_case_code', '02', '邮政通信', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('453', 's_case_code', '03', '教育', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('454', 's_case_code', '04', '医疗服务', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('455', 's_case_code', '05', '物业管理', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('456', 's_case_code', '06', '停车场服务', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('457', 's_case_code', '07', '社会中介机构服务', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('458', 's_case_code', '08', '金融', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('459', 's_case_code', '09', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('460', 'gov_case_code', '01', '公安部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('461', 'gov_case_code', '02', '自然资源部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('462', 'gov_case_code', '03', '住房城乡建设部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('463', 'gov_case_code', '04', '交通运输部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('464', 'gov_case_code', '05', '工业和信息化部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('465', 'gov_case_code', '06', '农业农村部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('466', 'gov_case_code', '07', '卫生健康部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('467', 'gov_case_code', '08', '药品监管部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('468', 'gov_case_code', '09', '知识产权部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('469', 'gov_case_code', '10', '法院部门', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('470', 'prilleg_code_1', '11', '违反明码标价规定行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('471', 'prilleg_code_1', '12', '不正当价格行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('472', 'prilleg_code_1', '13', '不执行政府定价、政府指导价；不执行法定价格干预措施、紧急措施', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('473', 'prilleg_code_2', '1201', '价格串通行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('474', 'prilleg_code_2', '1202', '价格倾销行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('475', 'prilleg_code_2', '1203', '哄抬价格行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('476', 'prilleg_code_2', '1204', '价格欺诈行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('477', 'prilleg_code_2', '1205', '价格歧视行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('478', 'prilleg_code_2', '1206', '变相提价压价行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('479', 'prilleg_code_2', '1207', '牟取暴利行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('480', 'prilleg_code_2', '1208', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('481', 'prillicit_code_1', '11', '侵犯知识产权不正当竞争', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('482', 'prillicit_code_1', '12', '商业贿赂', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('483', 'prillicit_code_1', '13', '虚假或引人误解的商业宣传', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('484', 'prillicit_code_1', '14', '不正当有奖销售', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('485', 'prillicit_code_1', '15', '商业诋毁', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('486', 'prillicit_code_1', '16', '网络新型不正当竞争行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('487', 'prillicit_code_1', '17', '妨害监督检查部门依照本法履行职责，拒绝、阻碍调查', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('488', 'prillicit_code_1', '18', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('489', 'prillicit_code_2', '1101', '混淆行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('490', 'prillicit_code_2', '1102', '侵犯商业秘密', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('491', 'prillicit_code_2', '1201', '贿赂交易相对方工作人员', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('492', 'prillicit_code_2', '1202', '贿赂受交易相对方委托的单位或个人', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('493', 'prillicit_code_2', '1203', '贿赂影响交易的单位或者个人', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('494', 'prillicit_code_2', '1301', '对商品性能、功能、质量、销售状况、用户评价、曾获荣誉作虚假或引人误解的商业宣传', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('495', 'prillicit_code_2', '1302', '通过组织虚假交易等方式，帮助其他经营者进行虚假或引人误解的商业宣传', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('496', 'prillicit_code_2', '1401', '所设奖的种类、兑奖条件、奖金金额或者奖品等有奖销售信息不明确，影响兑奖', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('497', 'prillicit_code_2', '1402', '采用谎称有奖或者故意让内定人员中奖的欺骗方式进行有奖销售', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('498', 'prillicit_code_2', '1403', '抽奖式的有奖销售，最高奖的金额超过五万元', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('499', 'prillicit_code_2', '1501', '编造、传播虚假信息或者误导性信息，损害竞争对手的商业信誉、商品声誉 ', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('500', 'prillicit_code_2', '1601', '未经其他经营者同意，在其合法提供的网络产品或者服务中，插入链接、强制进行目标跳转', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('501', 'prillicit_code_2', '1602', '误导、欺骗、强迫用户修改、关闭、卸载其他经营者合法提供的网络产品或者服务', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('502', 'prillicit_code_2', '1603', '恶意对其他经营者合法提供的网络产品或者服务实施不兼容', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('503', 'prillicit_code_2', '1604', '其他妨碍、破坏其他经营者合法提供的网络产品或者服务正常运行的行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('504', 'prillicit_code_3', '110101', '擅自使用与他人有一定影响的商品名称、包装、装潢等相同或者近似的标识', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('505', 'prillicit_code_3', '110102', '擅自使用他人有一定影响的企业名称（包括简称、字号等）、社会组织名称（包括简称等）、姓名（包括笔名、艺名、译名等）', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('506', 'prillicit_code_3', '110103', '擅自使用他人有一定影响的域名主体部分、网站名称、网页等', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('507', 'prillicit_code_3', '110104', '其他足以引人误认为是他人商品或者与他人存在特定联系的混淆行为', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('508', 'prillicit_code_3', '110201', '以盗窃、贿赂、欺诈、胁迫、电子侵入或者其他不正当手段获取权利人的商业秘密', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('509', 'prillicit_code_3', '110202', '披露、使用或者允许他人使用以前项手段获取的权利人的商业秘密', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('510', 'prillicit_code_3', '110203', '违反保密义务或者违反权利人有关保守商业秘密的要求，披露、使用或者允许他人使用其所掌握的商业秘密', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('511', 'prillicit_code_3', '110204', '教唆、引诱、帮助他人违反保密义务或者违反权利人有关保守商业秘密的要求，获取、披露、使用或者允许他人使用权利人的商业秘密', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('512', 'prillicit_code_3', '110205', '第三人明知或者应知商业秘密权利人的员工、前员工或者其他单位、个人实施本条第一款所列违法行为，仍获取、披露、使用或者允许他人使用', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('513', 'trapped_info', '01', '停电', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('514', 'trapped_info', '02', '机电故障', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('515', 'trapped_info', '03', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('516', 'place_of_use', '01', '机关', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('517', 'place_of_use', '02', '住宅', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('518', 'place_of_use', '03', '医院', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('519', 'place_of_use', '04', '学校', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('520', 'place_of_use', '05', '商场', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('521', 'place_of_use', '06', '宾馆饭店', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('522', 'place_of_use', '07', '机场车站', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('523', 'place_of_use', '08', '公园景区', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('524', 'place_of_use', '09', '餐饮娱乐', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('525', 'place_of_use', '10', '企业', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('526', 'place_of_use', '11', '展馆', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('527', 'place_of_use', '12', '其他', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('528', 'regorg_code_2', '620100', '兰州市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('529', 'regorg_code_2', '620200', '嘉峪关市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('530', 'regorg_code_2', '620300', '金昌市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('531', 'regorg_code_2', '620400', '白银市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('532', 'regorg_code_2', '620500', '天水市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('533', 'regorg_code_2', '620600', '武威市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('534', 'regorg_code_2', '620700', '张掖市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('535', 'regorg_code_2', '620800', '平凉市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('536', 'regorg_code_2', '620900', '酒泉市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('537', 'regorg_code_2', '621000', '庆阳市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('538', 'regorg_code_2', '621100', '定西市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('539', 'regorg_code_2', '621200', '陇南市市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('540', 'regorg_code_2', '622900', '临夏回族自治州市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('541', 'regorg_code_2', '623000', '甘南藏族自治州市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('542', 'regorg_code_2', '623100', '甘肃矿区工商行政管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('543', 'regorg_code_2', '623200', '东风场区工商行政管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('544', 'regorg_code_1', '620000', '甘肃省市场监督管理局', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('545', 'zscq_risk_code', 'N01', '近一年商标申请量超过50件', NULL, NULL, NULL, TO_DATE('2021-06-24 16:37:09', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('546', 'zscq_risk_code', 'N02', '近一年有知识产权侵权处罚', NULL, NULL, NULL, TO_DATE('2021-06-24 16:37:19', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('547', 'm_product_02_case', 'problem_rate', '企业问题发现率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('548', 'm_product_02_case', 'check_num', '检验企业数', '户', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('549', 'zscq_eco_code_1', 'z010103001', '专利申请增长率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('550', 'zscq_eco_code_1', 'z010103002', '商标申请增长率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('551', 'zscq_eco_code_1', 'z010103003', '著作权登记增长率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('552', 'zscq_eco_code_1', 'z010103004', '财政收入增长率', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('553', 'zscq_eco_code_2', 'z010104001', '企业平均营收', '万元', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('554', 'zscq_eco_code_2', 'z010104002', '企业盈利比例', '%', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('555', 'zscqCode', 'z010102001', '专利企业数量', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('556', 'zscqCode', 'z010102002', '著作权企业数量', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('557', 'zscqCode', 'z010102003', '商标企业数量', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA_TYPE" VALUES ('558', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
