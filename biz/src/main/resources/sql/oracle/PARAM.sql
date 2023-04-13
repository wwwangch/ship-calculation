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

 Date: 03/12/2021 15:08:14
*/


-- ----------------------------
-- Table structure for PARAM
-- ----------------------------
DROP TABLE "GANSU"."PARAM";
CREATE TABLE "GANSU"."PARAM" (
  "ID" VARCHAR2(255 BYTE) ,
  "PARAM_NAME" VARCHAR2(255 BYTE) ,
  "PARAM_KEY" VARCHAR2(255 BYTE) ,
  "PARAM_VALUE" VARCHAR2(255 BYTE) ,
  "PARAM_TYPE" VARCHAR2(255 BYTE) ,
  "CREATE_BY" VARCHAR2(255 BYTE) ,
  "CREATE_TIME" DATE ,
  "UPDATE_BY" VARCHAR2(255 BYTE) ,
  "UPDATE_TIME" DATE ,
  "PARAM_DESC" VARCHAR2(255 BYTE) 
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
-- Records of PARAM
-- ----------------------------
INSERT INTO "GANSU"."PARAM" VALUES ('1', '系统监控-访问日志-保留时长', 'sys.log.holdPeriod', '1d', '系统类', 'admin', TO_DATE('2021-02-26 10:51:57', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-02-26 14:43:18', 'SYYYY-MM-DD HH24:MI:SS'), '根据该参数清除之前的访问日志数据');
INSERT INTO "GANSU"."PARAM" VALUES ('5', '系统监控-测试', 'sys.test.a', 'test', '系统类', 'test', TO_DATE('2021-02-26 15:31:33', 'SYYYY-MM-DD HH24:MI:SS'), 'admin', TO_DATE('2021-03-05 10:37:19', 'SYYYY-MM-DD HH24:MI:SS'), '根据该参数清除之前的访问日志数据');
INSERT INTO "GANSU"."PARAM" VALUES ('6', '系统监控-访问日志-hello', 'sys.test.aaa', 'hello iscas', '业务类', 'unknown', TO_DATE('2021-03-01 10:18:48', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-01 13:42:13', 'SYYYY-MM-DD HH24:MI:SS'), 'hello world');
INSERT INTO "GANSU"."PARAM" VALUES ('10', '测试数据', '12222', '12', '业务类', 'unknown', TO_DATE('2021-03-05 14:02:57', 'SYYYY-MM-DD HH24:MI:SS'), 'unknown', TO_DATE('2021-03-05 14:03:03', 'SYYYY-MM-DD HH24:MI:SS'), '111');
INSERT INTO "GANSU"."PARAM" VALUES ('11', '下载文件路径', 'biz.download.filePath', 'D:\download\file', '业务类', 'admin', TO_DATE('2021-03-20 15:03:19', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, '下载文件保存的路径');
INSERT INTO "GANSU"."PARAM" VALUES ('12', NULL, NULL, NULL, '业务类', 'unknown', TO_DATE('2021-05-17 09:51:46', 'SYYYY-MM-DD HH24:MI:SS'), NULL, NULL, NULL);
