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

 Date: 03/12/2021 15:08:52
*/


-- ----------------------------
-- Table structure for USER_STATUS
-- ----------------------------
DROP TABLE "GANSU"."USER_STATUS";
CREATE TABLE "GANSU"."USER_STATUS" (
  "ID" VARCHAR2(255 BYTE) ,
  "USER_STATUS_NAME" VARCHAR2(255 BYTE) 
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
-- Records of USER_STATUS
-- ----------------------------
INSERT INTO "GANSU"."USER_STATUS" VALUES ('0', '不启用');
INSERT INTO "GANSU"."USER_STATUS" VALUES ('1', '启用');
