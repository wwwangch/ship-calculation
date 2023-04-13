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

 Date: 03/12/2021 15:09:14
*/


-- ----------------------------
-- Table structure for XXTABLE_DEFINITION
-- ----------------------------
DROP TABLE "GANSU"."XXTABLE_DEFINITION";
CREATE TABLE "GANSU"."XXTABLE_DEFINITION" (
  "ID" VARCHAR2(255 BYTE) ,
  "TABLEIDENTITY" VARCHAR2(255 BYTE) ,
  "TITLE" VARCHAR2(255 BYTE) ,
  "TABLENAME" VARCHAR2(255 BYTE) ,
  "SQL" VARCHAR2(255 BYTE) ,
  "CHECKBOX" VARCHAR2(255 BYTE) ,
  "BACKINFO" VARCHAR2(255 BYTE) ,
  "FRONTINFO" VARCHAR2(255 BYTE) ,
  "VIEWTYPE" VARCHAR2(255 BYTE) ,
  "CELLEDITABLE" VARCHAR2(255 BYTE) ,
  "PRIMARYKEY" VARCHAR2(255 BYTE) 
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
-- Records of XXTABLE_DEFINITION
-- ----------------------------
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('1', 'f_xw_task_info', '纤维企业明细表', 'f_xw_task_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('2', 'empnum_top10', '重点企业从业人数Top10', 'f_xw_task_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('3', 'vendinc_top10', '重点企业营业收入Top10', 'f_xw_task_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11105', 'dict', '字典', 'dict', 'select * from dict', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11106', 'param', '参数设置', 'param', 'select * from param', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11107', 'log_info', '访问日志', 'log_info', 'select * from log_info', '0', NULL, NULL, NULL, '0', 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11108', 'role', '角色', 'role', 'select * from role', NULL, NULL, NULL, NULL, NULL, 'role_id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11109', 'user', '用户', 'user_info', 'select * from user_info', NULL, NULL, NULL, NULL, NULL, 'user_id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11110', 'ws_data', '消息', 'ws_data', 'select * from ws_data', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11111', 'dict_data', '字典管理', 'dict_data', 'select * from dict_data', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11112', 'dict_data_type', '字典数据', 'dict_data_type', 'select * from dict_data_type', NULL, NULL, NULL, NULL, NULL, 'id');
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11113', 'f_dianti_relevant_units_xq', '重点电梯相关单位名单', 'f_dianti_relevant_units_xq', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11114', 'f_dianti_statistics_xq', '电梯统计分析详情表', 'f_dianti_statistics_xq', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11115', 'f_dianti_link_xq', '电梯使用环节分析详情表', 'f_dianti_link_xq', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11116', 'f_cater_busine_info', '餐饮行业详情表', 'f_cater_busine_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11117', 'f_drug_info', '药店经营详情表', 'f_drug_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11118', 'equipment_info', '特种设备信息', 'equipment_info', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXTABLE_DEFINITION" VALUES ('11119', 'relevant_company', '相关单位', 'relevant_company', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
