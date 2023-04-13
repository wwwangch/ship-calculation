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

 Date: 03/12/2021 15:07:26
*/


-- ----------------------------
-- Table structure for DICT_DATA
-- ----------------------------
DROP TABLE "GANSU"."DICT_DATA";
CREATE TABLE "GANSU"."DICT_DATA" (
  "ID" VARCHAR2(255 CHAR) ,
  "DICT_TYPE" VARCHAR2(255 CHAR) ,
  "DICT_DATA_TYPE" VARCHAR2(255 CHAR) ,
  "DICT_LABEL" VARCHAR2(255 CHAR) ,
  "DICT_DESC" VARCHAR2(255 CHAR) ,
  "CREATE_TIME" DATE ,
  "CREATE_BY" VARCHAR2(255 CHAR) ,
  "UPDATE_TIME" DATE ,
  "UPDATE_BY" VARCHAR2(255 CHAR) 
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
-- Records of DICT_DATA
-- ----------------------------
INSERT INTO "GANSU"."DICT_DATA" VALUES ('1', '系统类', 'enum_test', '枚举测试', '测试枚举更新测试', TO_DATE('2021-03-01 17:29:29', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-01 17:31:06', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA" VALUES ('2', '业务类', 'user_status', '用户状态', NULL, TO_DATE('2021-03-05 12:12:03', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2021-03-05 14:55:11', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown');
INSERT INTO "GANSU"."DICT_DATA" VALUES ('27', '业务类', 'm_tsg_01', '特种期末以及新设户数', '特种期末以及新设户数', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('28', '业务类', 'm_tsg_02', '各单位期末户数', '各单位期末户数', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('29', '业务类', 'm_tsg_03', '在用设备', '在用设备', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('30', '业务类', 'tsg_type_code_1', '特种设备一级类型', '特种设备一级类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('31', '业务类', 'district_code_2', '甘肃省下市级单位', '甘肃省下市级单位', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('32', '业务类', 'm_dianti', '电梯运行概况', '电梯运行概况', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('33', '业务类', 'm_tsg_04', '特种设备检验情况', NULL, NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('34', '业务类', 'dianti_type_01', '电梯二级字典1', '电梯二级字典1', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('36', '业务类', 'dianti_type_011', '电梯三级字典1', '电梯三级字典1', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('37', '业务类', 'dianti_type_012', '电梯三级字典2', '电梯三级字典2', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('38', '业务类', 'dianti_type_013', '电梯三级字典3', '电梯三级字典3', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('40', '业务类', 'dianti_type_014', '电梯三级字典4', '电梯三级字典4', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('41', '业务类', 'm_canyin_1', '餐饮经营许可分析', '餐饮经营许可分析', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('42', '业务类', 'm_canyin_2', '餐饮日常监管', '餐饮日常监管', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('43', '业务类', 'd_relevant_units', '相关单位类型', '相关单位类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('44', '业务类', 'busine_pro_name', '经营项目类别', '经营项目类别', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('45', '业务类', 'm_canyin_3', '餐饮检验检测分析', '餐饮监测检验分析', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('46', '业务类', 'busine_type_info', '餐饮经营许可业态类型', '餐饮经营许可业态类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('47', '业务类', 'spot_check_code', '检验检测问题类型分析', '检验检测问题类型分析', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('48', '业务类', 'm_canyin_4', '餐饮投诉举报分析', '餐饮投诉举报分析', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('49', '业务类', 'knowledge_1', '知识产权概况前四', '知识产权概况前四', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('50', '业务类', 'knowledge_2', '知识产权概况后四', '知识产权概况后四', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('51', '业务类', 'cater_risk_code', '餐饮预警规则', '餐饮预警规则', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('52', '业务类', 'localadm_code_2', '甘肃省市级市场监督管理局', '甘肃省市级市场监督管理局', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('53', '业务类', 'm_case', '价格监管案件相关指标', '价格监管案件相关指标', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('54', '业务类', 't_case_code', '商品价格案件类型', '商品价格案件类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('55', '业务类', 's_case_code', '服务价格案件类型', '服务价格案件类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('56', '业务类', 'gov_case_code', '国家机关收费案件类型', '国家机关收费案件类型', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('57', '业务类', 'prilleg_code_1', '价格违法行为类型一级', '价格违法行为类型一级', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('58', '业务类', 'prilleg_code_2', '价格违法行为类型二级', '价格违法行为类型二级', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('59', '业务类', 'prillicit_code_1', '不正当竞争案件类型一级', '不正当竞争案件类型一级', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('60', '业务类', 'prillicit_code_2', '不正当竞争案件类型二级', '不正当竞争案件类型二级', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('61', '业务类', 'prillicit_code_3', '不正当竞争案件类型三级', '不正当竞争案件类型三级', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('62', '业务类', 'trapped_info', '困人原因', '困人原因', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('63', '业务类', 'place_of_use', '困人电梯使用场所', '困人电梯使用场所', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('64', '业务类', 'regorg_code_1', '甘肃省市场监督管理局', '甘肃省市场监督管理局', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('65', '业务类', 'regorg_code_2', '甘肃省市级市场监督管理局', '甘肃省市级市场监督管理局', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('66', '业务类', 'pro_risk_code', '产品预警规则', '产品预警规则', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('67', '业务类', 'zscq_risk_code', '知识产权预警规则', '知识产权预警规则', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('68', '业务类', 'm_price_02', '居民消费价格指数', '居民消费价格指数', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('69', '业务类', 'm_product_02_case', '产品质量日常监管分析相关指标', '产品质量日常监管分析相关指标', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."DICT_DATA" VALUES ('70', '业务类', 'zscqCode', '知识产权统计分析维度', '知识产权统计分析维度', NULL, NULL, NULL, NULL);
