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

 Date: 03/12/2021 15:07:53
*/


-- ----------------------------
-- Table structure for LOG_INFO
-- ----------------------------
DROP TABLE "GANSU"."LOG_INFO";
CREATE TABLE "GANSU"."LOG_INFO" (
  "ID" VARCHAR2(255 BYTE) ,
  "LOG_TYPE" VARCHAR2(255 BYTE) ,
  "REQUEST_URL" VARCHAR2(255 BYTE) ,
  "OPERATE_TYPE" VARCHAR2(255 BYTE) ,
  "OPERATE_USER" VARCHAR2(255 BYTE) ,
  "LOG_DESC" VARCHAR2(255 BYTE) ,
  "OPERATE_TIME" DATE ,
  "REQUEST_TOOK_TIME" VARCHAR2(255 BYTE) 
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
-- Records of LOG_INFO
-- ----------------------------
INSERT INTO "GANSU"."LOG_INFO" VALUES ('1', '系统', '/gansu/dictDataType/data', '修改', 'unknown', '操作成功:修改字典类型数据', TO_DATE('1900-01-20 16:37:09', 'SYYYY-MM-DD HH24:MI:SS'), '37');
INSERT INTO "GANSU"."LOG_INFO" VALUES ('2', '系统', '/gansu/dictDataType/data', '修改', 'unknown', '操作成功:修改字典类型数据', TO_DATE('1900-01-20 16:37:19', 'SYYYY-MM-DD HH24:MI:SS'), '30');
