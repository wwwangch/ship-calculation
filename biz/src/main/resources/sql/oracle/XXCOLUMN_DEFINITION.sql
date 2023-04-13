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

 Date: 03/12/2021 15:09:06
*/


-- ----------------------------
-- Table structure for XXCOLUMN_DEFINITION
-- ----------------------------
DROP TABLE "GANSU"."XXCOLUMN_DEFINITION";
CREATE TABLE "GANSU"."XXCOLUMN_DEFINITION" (
  "ID" VARCHAR2(255 BYTE) ,
  "TABLEIDENTITY" VARCHAR2(255 BYTE) ,
  "FIELD" VARCHAR2(255 BYTE) ,
  "HEADER" VARCHAR2(255 BYTE) ,
  "SEQUENCE" VARCHAR2(255 BYTE) ,
  "EDITABLE" VARCHAR2(255 BYTE) ,
  "SORTABLE" VARCHAR2(255 BYTE) ,
  "TYPE" VARCHAR2(255 BYTE) ,
  "SELECTURL" VARCHAR2(255 BYTE) ,
  "SEARCH" VARCHAR2(255 BYTE) ,
  "SEARCHTYPE" VARCHAR2(255 BYTE) ,
  "LINK" VARCHAR2(255 BYTE) ,
  "ADDABLE" VARCHAR2(255 BYTE) ,
  "HIDDEN" VARCHAR2(255 BYTE) ,
  "REFTABLE" VARCHAR2(255 BYTE) ,
  "REFCOLUMN" VARCHAR2(255 BYTE) ,
  "REFVALUE" VARCHAR2(255 BYTE) ,
  "REQUIRED" VARCHAR2(255 BYTE) ,
  "REG" VARCHAR2(255 BYTE) ,
  "MINLENGTH" VARCHAR2(255 BYTE) ,
  "MAXLENGTH" VARCHAR2(255 BYTE) ,
  "DISTINCT" VARCHAR2(255 BYTE) 
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
-- Records of XXCOLUMN_DEFINITION
-- ----------------------------
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('1', 'f_xw_task_info', '_id', 'ES内置ID', '1', '0', NULL, 'text', NULL, '0', 'exact', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('2', 'f_xw_task_info', 'pripid', '主体身份代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('3', 'f_xw_task_info', 'entname', '企业名称', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('4', 'f_xw_task_info', 'uniscid', '统一社会信用代码', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('5', 'f_xw_task_info', 'district', '区域', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('6', 'f_xw_task_info', 'empnum', '人数', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('7', 'f_xw_task_info', 'vendinc', '营业收入', '7', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('8', 'f_xw_task_info', 'serdishonrea', '是否列严', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('9', 'f_xw_task_info', 'spcfocrea', '是否列异', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('10', 'f_xw_task_info', 'scores', '得分', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11', 'f_xw_task_info', 'hotfields', '重点领域', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('12', 'f_xw_task_info', 'estdate', '注册日期', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('13', 'f_xw_task_info', 'regcap', '注册资本', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('14', 'f_xw_task_info', 'industryco', '行业代码', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('15', 'f_xw_task_info', 'ds_type', '电商门类', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('16', 'f_xw_task_info', 'regstate', '企业状态', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('17', 'f_xw_task_info', 'revdate', '注吊销日期', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('18', 'f_xw_task_info', 'item_flag', '是否合格', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('19', 'empnum_top10', 'entname', '企业名称', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('20', 'empnum_top10', 'empnum', '从业人数（人）', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('21', 'vendinc_top10', 'entname', '企业名称', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('22', 'vendinc_top10', 'vendinc', '营业收入（万元）', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('23', 'empnum_top10', 'ranking', '排名', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('24', 'vendinc_top10', 'ranking', '排名', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11132', 'role', 'role_id', 'ID', '1', '0', '0', 'text', NULL, '0', 'exact', '0', '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, '0');
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11133', 'role', 'role_name', '名称', '2', '1', '0', 'text', NULL, '1', 'like', '0', '1', '0', NULL, NULL, NULL, '0', NULL, '2', '50', '0');
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11134', 'role', 'role_create_time', '创建时间', '3', '0', '0', 'daterange', NULL, '1', 'range', '0', '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, '0');
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11135', 'role', 'role_update_time', '更新时间', '4', '0', NULL, 'daterange', NULL, '1', 'range', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11136', 'user', 'user_id', 'ID', '1', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11137', 'user', 'user_name', '用户名', '2', '1', NULL, 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '1', NULL, '2', '50', NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11138', 'user', 'user_real_name', '真实姓名', '3', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '1', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11139', 'user', 'user_pwd', '密码', '4', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11140', 'user', 'user_tel', '手机号码', '5', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11141', 'user', 'user_email', '邮箱', '6', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', '^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$', NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11142', 'user', 'user_status', '用户状态', '7', '1', NULL, 'select', NULL, '1', 'exact', NULL, '1', '0', 'user_status', 'user_status_name', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11143', 'user', 'user_create_time', '创建时间', '8', '0', NULL, 'daterange', NULL, '1', 'range', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11144', 'user', 'user_update_time', '最后修改时间', '9', '0', NULL, 'daterange', NULL, '1', 'range', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11145', 'user', 'user_role', '用户角色', '7', '0', NULL, 'multiSelect', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11146', 'user', 'user_role_edit', '用户角色', '10', '1', NULL, 'multiSelect', '/combobox/role', '0', NULL, NULL, '1', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11147', 'user', 'user_org', '组织机构', '7', '0', NULL, 'selectCascader', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11148', 'user', 'user_org_edit', '组织机构', '11', '1', NULL, 'selectCascader', '/combobox/org/tree', '0', NULL, NULL, '1', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11149', 'dict', 'id', '字典id', '1', '0', '0', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11150', 'dict', 'key', '字典键', '2', '0', '1', 'text', NULL, '0', 'exact', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11151', 'dict', 'value', '字典值', '3', '0', '0', 'text', NULL, '0', 'like', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11152', 'dict', 'type', '字典类型', '4', '0', '0', 'text', NULL, '0', 'extra', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11153', 'dict', 'description', '字典描述', '5', '0', '0', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11154', 'param', 'param_name', '参数名称', '1', '1', '0', 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11155', 'param', 'param_key', '参数键名', '2', '1', '1', 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11156', 'param', 'param_value', '参数键值', '3', '1', '1', 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11157', 'param', 'param_type', '参数类型', '4', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11158', 'param', 'create_by', '创建者', '5', '0', '0', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11159', 'param', 'create_time', '创建时间', '6', '0', '0', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11160', 'param', 'update_by', '更新者', '7', '0', '0', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11161', 'param', 'update_time', '更新时间', '8', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11162', 'param', 'param_desc', '参数描述', '9', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11163', 'log_info', 'id', '序号', '1', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '1', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11164', 'log_info', 'log_type', '日志类型', '2', '0', NULL, 'text', NULL, '1', 'exact', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11165', 'log_info', 'operate_user', '操作用户', '3', '0', '0', 'text', NULL, '1', 'exact', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11166', 'log_info', 'operate_type', '操作类型', '4', '0', NULL, 'text', NULL, '1', 'exact', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11167', 'log_info', 'request_url', '请求地址', '5', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11168', 'log_info', 'operate_time', '操作时间', '6', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11169', 'log_info', 'request_took_time', '响应时间', '7', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11170', 'ws_data', 'id', 'ID', '1', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11171', 'ws_data', 'type', '类型', '2', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11172', 'log_info', 'log_desc', '日志详情', '8', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11173', 'ws_data', 'msg_id', '消息id', '3', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11174', 'ws_data', 'user_identify', '用户标识', '4', '0', NULL, 'text', NULL, '1', 'like', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11175', 'ws_data', 'data', '数据', '5', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11176', 'ws_data', 'destination', '订阅路径', '6', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11177', 'ws_data', 'create_time', '推送时间', '7', '0', NULL, 'daterange', NULL, '1', 'range', NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11178', 'dict_data', 'dict_label', '字典名称', '1', '1', '1', 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11179', 'dict_data', 'dict_data_type', '数据类型', '2', '1', '1', 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11180', 'dict_data', 'dict_type', '字典类型', '3', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11181', 'dict_data', 'create_time', '创建时间', '4', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11182', 'dict_data', 'create_by', '创建者', '5', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11183', 'dict_data', 'update_time', '更新时间', '6', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11184', 'dict_data', 'update_by', '更新者', '7', '0', '1', 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11185', 'dict_data', 'dict_desc', '字典描述', '8', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11186', 'dict_data_type', 'dict_data_type', '数据类型', '1', '1', '0', 'text', NULL, '1', 'exact', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11187', 'dict_data_type', 'dict_data_key', '数据键', '2', '1', '1', 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11188', 'dict_data_type', 'dict_data_value', '数据值', '3', '1', NULL, 'text', NULL, '1', 'like', NULL, '1', '0', NULL, NULL, NULL, '0', NULL, '1', NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11189', 'dict_data_type', 'create_time', '创建时间', '5', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11190', 'dict_data_type', 'create_by', '创建者', '6', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11191', 'dict_data_type', 'update_time', '更新时间', '7', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11192', 'dict_data_type', 'update_by', '更新者', '8', '0', NULL, 'text', NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11193', 'dict_data_type', 'dict_data_desc', '单位', '4', '1', NULL, 'text', NULL, '0', NULL, NULL, '1', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11221', 'dianti_statistic_data', 'label', '列名', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11197', 'f_dianti_relevant_units_xq', 'entname', '企业名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11198', 'f_dianti_relevant_units_xq', 'uniscid', '统一社会信用代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11199', 'f_dianti_relevant_units_xq', 'district', '区域', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11216', 'f_dianti_link_xq', 'sydwmc', '电梯使用单位', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11215', 'f_dianti_link_xq', 'elvator_id', '使用登记证编号', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11214', 'f_dianti_statistics_xq', 'm_dianti_14', '运行时长', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11213', 'f_dianti_statistics_xq', 'elvator_type_name', '电梯类别', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11210', 'f_dianti_statistics_xq', 'sbbh', '证书编号', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11211', 'f_dianti_statistics_xq', 'sydwmc', '使用单位名称', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11212', 'f_dianti_statistics_xq', 'relevant_units_code', '电梯使用地点', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11207', 'f_dianti_relevant_units_xq', 'm_dianti_08', '从业人员数量（人）', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11208', 'f_dianti_relevant_units_xq', 'm_dianti_09', '维保电梯数量（台）', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11217', 'f_dianti_link_xq', 'relevant_units_code', '电梯使用地点', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11218', 'f_dianti_link_xq', 'elvator_brand', '电梯品牌', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11219', 'f_dianti_link_xq', 'wbdw', '维保单位', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11220', 'f_dianti_link_xq', 'bksc', '被困时长(分钟)', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11222', 'dianti_statistic_data', 'value', '数值', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', 'dict_data_type', 'm_dianti_4', NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11223', 'dianti_statistic_data', 'rate', '同比', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11224', 'dianti_statistic_data', 'trend', '趋势', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11225', 'f_cater_busine_info', 'entname', '企业名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11226', 'f_cater_busine_info', 'uniscid', '统一社会信用代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11227', 'f_cater_busine_info', 'estdate', '成立日期', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11228', 'f_cater_busine_info', 'regcap', '营业收入(万元)', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11229', 'f_cater_busine_info', 'district_name', '区域', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11230', 'f_cater_busine_info', 'industryco_name', '行业', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11231', 'f_drug_info', 'entname', '企业名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11232', 'f_drug_info', 'uniscid', '统一社会信用代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11233', 'f_drug_info', 'estdate', '成立日期', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11234', 'f_drug_info', 'vendinc', '营业收入(万元)', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11235', 'f_drug_info', 'district', '区域', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11236', 'f_drug_info', 'opermode_name', '经营类型', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11237', 'equipment_info', 'value', '数值', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11238', 'equipment_info', 'rate', '同比', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11239', 'equipment_info', 'trend', '趋势', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11240', 'equipment_info', 'label', '列名', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11241', 'relevant_company', 'entname', '企业名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11242', 'relevant_company', 'uniscid', '统一社会信用代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11243', 'relevant_company', 'district', '地区', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11244', 'relevant_company', 'empnum', '从业人数', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11245', 'relevant_company', 'vendinc', '收入', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11255', 'proqua_into', 'trend', '趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11254', 'proqua_into', 'rate', '同比', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11250', 'emergency', 'district', '地区', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11251', 'emergency', 'equipmentName', '设备名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11252', 'emergency', 'useUnits', '使用单位', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11253', 'proqua_into', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11256', 'proqua_into', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11257', 'emergency', 'usePlace', '使用场所', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11258', 'emergency', 'emyUnits', '应急单位', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11259', 'emergency', 'faultDate', '困人发生时间', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11260', 'emergency', 'faultCase', '困人原因', '7', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11261', 'emergency', 'arriveTime', '到达现场耗时-分钟', '8', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11262', 'pricechang_into', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11267', 'proqua_general', 'pq010102001', '检验企业数', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11263', 'pricechang_into', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11266', 'pricechang_into', 'rate', '同比', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11264', 'pricechang_info', 'trend', '趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11268', 'proqua_general', 'pq010102001_Increase', '检验企业数同比增幅', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11269', 'proqua_general', 'pq010102002', '检验不合格企业数', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11270', 'proqua_general', 'pq010102002_Increase', '检验不合格企业数同比增幅', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11271', 'proqua_general', '"pq010102002 / pq010102001', '企业问题发现率', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11272', 'cater_busine', 'entname', '企业名称', '1', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11273', 'cater_busine', 'uniscid', '统一社会信用代码', '2', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11274', 'cater_busine', 'estdate', '成立日期', '3', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11275', 'cater_busine', 'regcap', '营业收入', '4', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11276', 'cater_busine', 'district_name', '区域', '5', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11277', 'cater_busine', 'industryco_name', '行业', '6', '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11281', 'pro_warning_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11280', 'pro_warning_info', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11282', 'pro_warning_info', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11283', 'pro_warning_list', 'entname', '企业名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11284', 'pro_warning_list', 'district', '地区', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11285', 'pro_warning_list', 'estdate', '成立日期', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11288', 'pro_warning_list', 'warning_info', '预警信息', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11287', 'pro_warning_list', 'pro_risk_name', '预警规则', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11289', 'zscq_warning_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11290', 'zscq_warning_info', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11291', 'zscq_warning_info', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11292', 'zscq_warning_list', 'entname', '企业名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11293', 'zscq_warning_list', 'district', '地区', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11294', 'zscq_warning_list', 'estdate', '成立日期', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11295', 'zscq_warning_list', 'warning_info', '预警信息', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11296', 'zscq_warning_list', 'zscq_risk_name', '预警规则', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11297', 'price_region_info', 'year_dt', '年份', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11298', 'price_region_info', 'district', '地区', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11299', 'price_region_info', 'm_price_value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11302', 'pro_quality_checkInfo', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11303', 'pro_quality_checkInfo', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11304', 'pro_quality_checkInfo', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11306', 'price_case_general', 'quart_dt', '季度', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11307', 'price_case_general', 'm_case', '指标代码', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11308', 'price_case_general', 'm_case_name', '指标名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11309', 'price_case_general', 'm_case_unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11310', 'price_case_general', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11311', 'price_case_general', 'rate', '同比', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11312', 'price_trend_info', 'quart_dt', '季度', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11317', 'price_trend_info', 'm_case_unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11319', 'price_pie_info', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11315', 'price_trend_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11320', 'price_pie_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11318', 'price_trend_info', 'rate', '同比', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11321', 'price_pie_info', 'rate', '占比', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11322', 'cater_bussup_info', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11323', 'cater_bussup_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11325', 'cater_bussup_info', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11326', 'cater_bussup_daily', 'label', '地区', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11327', 'cater_bussup_daily', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11328', 'cater_bussup_trend', 'label', '季度', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11329', 'cater_bussup_trend', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11330', 'patent_economy_rate', 'label', '月份', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11331', 'patent_economy_rate', 'label', '月份', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11332', 'patent_economy_rate', 'value', '增长率', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11333', 'patent_economy_rate', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11334', 'cater_compl_info', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11335', 'cater_compl_info', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11336', 'cater_compl_info', 'rate', '同比增长', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11337', 'cater_compl_info', 'up', '上升趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11338', 'cater_compl_info', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11339', 'cater_compl_trend', 'label', '列名', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11340', 'cater_compl_trend', 'code', '指标', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11341', 'cater_compl_trend', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11342', 'cater_compl_trend', 'rate', '同比增长', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11343', 'cater_compl_trend', 'up', '上升趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11344', 'cater_compl_trend', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11345', 'cater_inspec_map', 'label', '地区', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11347', 'cater_inspec_map', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11348', 'cater_inspec_map', 'rate', '同比增长', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11349', 'cater_inspec_map', 'up', '上升趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11351', 'cater_inspec_problem', 'label', '检验检测问题类型', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11352', 'cater_inspec_problem', 'value', '数值', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11353', 'cater_inspec_problem', 'rate', '同比增长', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11354', 'cater_inspec_problem', 'up', '上升趋势', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11355', 'cater_inspec_problem', 'code', '检验检测问题类型代码', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11356', 'price_info_monitor', '_id', '序号', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11357', 'price_info_monitor', 'trade_code_1', '一级商品code', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11358', 'price_info_monitor', 'trade_name_1', '一级商品名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11359', 'price_info_monitor', 'trade_code_2', '商品code', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11360', 'price_info_monitor', 'trade_name_2', '商品名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11361', 'price_info_monitor', 'spec', '规格', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11362', 'price_info_monitor', 'unit', '单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11363', 'price_info_monitor', 'pr010101001', '本月价格', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11364', 'price_info_monitor', 'pr010101001l', '上月价格', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11365', 'price_info_monitor', 'pr010101001y', '同比月价格', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11366', 'price_info_monitor', 'pr010101002', '与上期比(±%)', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11367', 'price_info_monitor', 'pr010101003', '与去年同期比(±%)', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11368', 'cater_warning_list', 'entname', '餐饮单位名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11369', 'cater_warning_list', 'estdate', '成立时间', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11370', 'cater_warning_list', 'district_name', '区域', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11374', 'cater_warning_list', 'warning_info', '预警信息', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11373', 'cater_warning_list', 'cater_risk_name', '预警规则', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11375', 'tsg_warning_list', 'equName', '设备名称', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11376', 'tsg_warning_list', 'equNumber', '设备使用登记证编号', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11377', 'tsg_warning_list', 'useUnits', '设备使用单位', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11378', 'tsg_warning_list', 'regName', '注册登记机构', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11379', 'tsg_warning_list', 'district', '区域', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11380', 'tsg_warning_list', 'warningCase', '预警原因', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
INSERT INTO "GANSU"."XXCOLUMN_DEFINITION" VALUES ('11381', 'tsg_warning_list', 'warningInfo', '预警信息', NULL, '0', NULL, NULL, NULL, '0', NULL, NULL, '0', '0', NULL, NULL, NULL, '0', NULL, NULL, NULL, NULL);
