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

 Date: 03/12/2021 15:09:54
*/


-- ----------------------------
-- Table structure for ORG_USER
-- ----------------------------
DROP TABLE "GANSU"."ORG_USER";
CREATE TABLE "GANSU"."ORG_USER" (
  "ORG_ID" VARCHAR2(255 BYTE) ,
  "USER_ID" VARCHAR2(255 BYTE) 
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
-- Records of ORG_USER
-- ----------------------------
INSERT INTO "GANSU"."ORG_USER" VALUES ('9', '20');
INSERT INTO "GANSU"."ORG_USER" VALUES ('9', '23');
INSERT INTO "GANSU"."ORG_USER" VALUES ('35', '28');
INSERT INTO "GANSU"."ORG_USER" VALUES ('14', '39');
INSERT INTO "GANSU"."ORG_USER" VALUES ('9', '40');
INSERT INTO "GANSU"."ORG_USER" VALUES ('35', '41');
INSERT INTO "GANSU"."ORG_USER" VALUES ('32', '42');
INSERT INTO "GANSU"."ORG_USER" VALUES ('8', '46');
