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

 Date: 03/12/2021 15:08:59
*/


-- ----------------------------
-- Table structure for WS_DATA
-- ----------------------------
DROP TABLE "GANSU"."WS_DATA";
CREATE TABLE "GANSU"."WS_DATA" (
  "ID" VARCHAR2(255 BYTE) ,
  "TYPE" VARCHAR2(255 BYTE) ,
  "MSG_ID" VARCHAR2(255 BYTE) ,
  "USER_IDENTIFY" VARCHAR2(255 BYTE) ,
  "PERSISTENT" VARCHAR2(255 BYTE) ,
  "DATA" VARCHAR2(255 BYTE) ,
  "DESTINATION" VARCHAR2(255 BYTE) ,
  "ACK" VARCHAR2(255 BYTE) ,
  "CREATE_TIME" DATE 
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
-- Records of WS_DATA
-- ----------------------------
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a86832c4-6077-427d-9de0-d5ed465ae12e', 'admin', '1', '测试点对点数据，持久化1638513930017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '64289d79-cbda-4218-b3ed-aed6a83d7e7f', 'admin', '1', '测试点对点数据，持久化1638513960004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '83adbc10-0b9c-43b0-a7b9-594eaf0c8293', 'admin', '1', '测试点对点数据，持久化1638513990014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e916a607-f01a-4700-919c-d046b69415d3', 'admin', '1', '测试点对点数据，持久化1638514020009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '73115c42-3e50-496d-895a-6a845b2b7333', 'admin', '1', '测试点对点数据，持久化1638514050007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2964cf12-b22b-42e8-9489-233cb1e52bcd', 'admin', '1', '测试点对点数据，持久化1638514080018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a071331e-ba83-4318-97b6-f041f68e067a', 'admin', '1', '测试点对点数据，持久化1638514170075', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '788e20dd-90b3-4e52-937a-5db4bb1ebaf8', 'admin', '1', '测试点对点数据，持久化1638514200014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'd08e32f2-ea4d-4ebc-96bf-628b3c26f8fb', 'admin', '1', '测试点对点数据，持久化1638514230011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3cb0054d-455e-4159-995d-08041fd49efe', 'admin', '1', '测试点对点数据，持久化1638514260020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '0b32f813-8062-412a-b8e1-554481da064b', 'admin', '1', '测试点对点数据，持久化1638514290013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '58dcd76a-3939-41b7-8eee-16616f4e8cb0', 'admin', '1', '测试点对点数据，持久化1638514320989', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '77bebf2f-0cab-4c89-90c6-cddbdf9fb067', 'admin', '1', '测试点对点数据，持久化1638514350021', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2e3bb07a-7fb8-41e7-9138-479b2d91d4d3', 'admin', '1', '测试点对点数据，持久化1638514380015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1abb54f6-7484-4d32-b6cf-245353d92996', 'admin', '1', '测试点对点数据，持久化1638514410020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '34121f22-08c6-4519-befe-55b118c57faf', 'admin', '1', '测试点对点数据，持久化1638514440010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'd5c2cadf-beb2-4cb7-94e8-7044db68a8e4', 'admin', '1', '测试点对点数据，持久化1638514470020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '20077575-0cd1-4213-bc2b-9ab64226b305', 'admin', '1', '测试点对点数据，持久化1638514500008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '6f2060bc-8039-4baf-ab18-0b8ee915928f', 'admin', '1', '测试点对点数据，持久化1638514530016', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '63ce2a0d-4c80-4cd2-afbb-b0ebd36c887a', 'admin', '1', '测试点对点数据，持久化1638514560007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1bfb2acc-262a-47f1-b91b-faa2be5a62ac', 'admin', '1', '测试点对点数据，持久化1638514608449', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'f5f57598-1daf-4fb0-8449-a55c9fd09b0e', 'admin', '1', '测试点对点数据，持久化1638514620010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '22582f43-b065-40aa-a0e1-31803b4516ab', 'admin', '1', '测试点对点数据，持久化1638514874089', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ca060ac9-c121-4bf9-89e8-e921bd6fc1bf', 'admin', '1', '测试点对点数据，持久化1638514890018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ac3c3362-4bad-4a27-a814-feeb1dce3598', 'admin', '1', '测试点对点数据，持久化1638514920005', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3f7e1ada-92f8-4aa1-b94a-748d2c9cd3b3', 'admin', '1', '测试点对点数据，持久化1638514950021', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1d58e00a-f2a6-4d4d-b2b9-f551cf58c2fd', 'admin', '1', '测试点对点数据，持久化1638515026166', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e3e0017a-10ed-4d35-b2dd-217104a7541f', 'admin', '1', '测试点对点数据，持久化1638515070044', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e8d64427-5743-4466-9183-fb1fe5297b99', 'admin', '1', '测试点对点数据，持久化1638515100019', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '784981bb-50ec-4784-a11c-c573458d289c', 'admin', '1', '测试点对点数据，持久化1638515130010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '9f2794e9-c99b-4bad-ae81-991114729afc', 'admin', '1', '测试点对点数据，持久化1638515160018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '36bf9710-5556-4a23-831a-2564b8a1f926', 'admin', '1', '测试点对点数据，持久化1638515190009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e884f78b-d7bd-43b1-97be-07eb41697850', 'admin', '1', '测试点对点数据，持久化1638515220008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '864bd849-5e6b-4110-9017-bf372c1d9c75', 'admin', '1', '测试点对点数据，持久化1638515250018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '84fb4ccc-ba95-4e94-a579-e2dfa2fd5e4c', 'admin', '1', '测试点对点数据，持久化1638515280010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '58279c64-d53e-46ad-9449-68416e7fec18', 'admin', '1', '测试点对点数据，持久化1638515310019', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4f32a49e-3014-4269-9458-06663f46cbc1', 'admin', '1', '测试点对点数据，持久化1638512190018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4ca0c280-4144-47fd-b8c7-6eeef160dd08', 'admin', '1', '测试点对点数据，持久化1638512220012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a0533fa6-ae89-4b7f-a383-f7b2fd0bbfc4', 'admin', '1', '测试点对点数据，持久化1638512250011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '19d446c2-6a2a-4815-bf98-617183314a50', 'admin', '1', '测试点对点数据，持久化1638512280018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '698696a6-e64f-49d8-9aaa-b7f279677285', 'admin', '1', '测试点对点数据，持久化1638512310007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '74dfe2db-878f-46fd-a225-7167848ac05f', 'admin', '1', '测试点对点数据，持久化1638512340021', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '8bc326b2-e3da-47d8-b626-f741d3f7ec7a', 'admin', '1', '测试点对点数据，持久化1638512370015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '92faffed-2421-424e-9a96-c3b3a2d96612', 'admin', '1', '测试点对点数据，持久化1638512400011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'cfd2f6e6-b6fd-46c0-8141-b387eaf61384', 'admin', '1', '测试点对点数据，持久化1638512430005', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b489c921-2c3e-4206-8b70-a7b9ba69c8d1', 'admin', '1', '测试点对点数据，持久化1638512460014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b27c4db1-9a01-4dc2-ba95-4872cad499bf', 'admin', '1', '测试点对点数据，持久化1638512490009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'bbc0296b-208c-460a-8a62-5462a18fa619', 'admin', '1', '测试点对点数据，持久化1638512520011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b4db9510-2f4c-429a-bb8e-1662824865c1', 'admin', '1', '测试点对点数据，持久化1638512550011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '5ed9d883-1faf-41ee-a014-714e2da11178', 'admin', '1', '测试点对点数据，持久化1638512580008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a963a3e3-4437-4cc5-a0f3-2ca68e99ca85', 'admin', '1', '测试点对点数据，持久化1638512610006', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4aeea8cf-4829-4e9f-8d49-d7950dfe0955', 'admin', '1', '测试点对点数据，持久化1638512640007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '321e0e70-e1d4-4308-82c7-7f17211b409d', 'admin', '1', '测试点对点数据，持久化1638512670003', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '428edb46-e2ad-4c96-a7f6-85c653e698e9', 'admin', '1', '测试点对点数据，持久化1638512700017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'dac04eb0-b4d8-4c19-8a75-3a52e7124943', 'admin', '1', '测试点对点数据，持久化1638512730017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3f3c2bf1-9c0d-4ac0-a6e0-e0f81572ec35', 'admin', '1', '测试点对点数据，持久化1638512760015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '8169eff8-7034-4cbf-840b-a7e580f7df8d', 'admin', '1', '测试点对点数据，持久化1638512790015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ba25f5c1-d643-4e60-b4ca-f25c8e70f7d3', 'admin', '1', '测试点对点数据，持久化1638512820005', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'aa2c0c40-d9f4-404e-934a-3fa93ca4c37f', 'admin', '1', '测试点对点数据，持久化1638512850018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2576cc45-7ddd-49e8-8a08-1e9bc2407277', 'admin', '1', '测试点对点数据，持久化1638512880018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'eb10ad8d-9d7b-4121-afcd-e2abffda8320', 'admin', '1', '测试点对点数据，持久化1638512910017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '61cfaece-17e0-45bd-bd72-e0cd71fff579', 'admin', '1', '测试点对点数据，持久化1638512940018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a48f8997-91c4-40e4-9a9b-286e051e45d6', 'admin', '1', '测试点对点数据，持久化1638512970008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '87ef33af-9590-4cf3-afa0-8b56f67f1071', 'admin', '1', '测试点对点数据，持久化1638513000013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'fd4784bf-78aa-4287-b110-94fca7c8a32e', 'admin', '1', '测试点对点数据，持久化1638513030007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '5326c35b-d2f2-4e36-aed9-a1c5907f541f', 'admin', '1', '测试点对点数据，持久化1638513060009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'bf81a26d-18ba-4a99-a46f-ed11c2763a78', 'admin', '1', '测试点对点数据，持久化1638513090014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '980b45d3-08e5-4b5d-814d-705c23c468cb', 'admin', '1', '测试点对点数据，持久化1638513120013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '8c465943-8d6e-4c05-bb72-e40e348aad33', 'admin', '1', '测试点对点数据，持久化1638513150018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '7959cc2f-80aa-4767-ae2c-ae1ef1a9b313', 'admin', '1', '测试点对点数据，持久化1638513180016', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '53bc86fa-e5fb-4dfc-820e-16b652c52434', 'admin', '1', '测试点对点数据，持久化1638513210014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b9a8fcbd-e97c-44ac-84ec-b89b58aae55e', 'admin', '1', '测试点对点数据，持久化1638513240014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'f99e64a1-7bbe-47b6-8acd-58b27cacecfd', 'admin', '1', '测试点对点数据，持久化1638513270012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '6fdd57ae-bc07-40d9-9f57-5f20eab45ca1', 'admin', '1', '测试点对点数据，持久化1638513300017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c2772e9f-84f3-430b-becd-97d787472679', 'admin', '1', '测试点对点数据，持久化1638513330019', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '78a44244-d1c5-4fc9-bc07-e0b505538dae', 'admin', '1', '测试点对点数据，持久化1638513360018', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ceafed96-9bf7-4eac-981e-bbca9d8c84f2', 'admin', '1', '测试点对点数据，持久化1638513390009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '8c2d3440-1b33-41eb-a963-aebd874da9f6', 'admin', '1', '测试点对点数据，持久化1638513420013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3e5655b4-8805-48af-b679-2246faefd250', 'admin', '1', '测试点对点数据，持久化1638513450012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '7091740f-295f-4094-b67e-cef438d3c516', 'admin', '1', '测试点对点数据，持久化1638513480015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '0a33a9f3-d653-4117-8e6e-a48e1d4a2df9', 'admin', '1', '测试点对点数据，持久化1638513510015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1162fa66-1b4c-4408-8a5c-e94570e5076c', 'admin', '1', '测试点对点数据，持久化1638513540004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'f6ba4a2a-513a-4b34-9b80-c3939327962a', 'admin', '1', '测试点对点数据，持久化1638513570004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3b00eec7-b2d2-4efb-a698-5e4fe7e90fca', 'admin', '1', '测试点对点数据，持久化1638513600017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '80603806-ddc3-44c4-a073-94e0ea288ce2', 'admin', '1', '测试点对点数据，持久化1638513630004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3e1c188c-af8f-498f-9949-761d6de87569', 'admin', '1', '测试点对点数据，持久化1638513660007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '839b3f7c-a1c3-4e80-a0e9-e09201a8c18a', 'admin', '1', '测试点对点数据，持久化1638513690004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4a4b231f-5326-4f60-9d71-d244139971b0', 'admin', '1', '测试点对点数据，持久化1638513720007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '9f9499f4-f25b-410e-8492-53bc7ec483d4', 'admin', '1', '测试点对点数据，持久化1638513750007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2cbc6263-c009-4e55-8403-558b0230b418', 'admin', '1', '测试点对点数据，持久化1638513780014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '98676631-732b-4a57-adba-d9a9187af073', 'admin', '1', '测试点对点数据，持久化1638513810014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4ba5b411-eed3-4858-a4c5-4037781398a9', 'admin', '1', '测试点对点数据，持久化1638513840014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4b12ee53-e525-40a0-a8c5-2ebbc6536d1a', 'admin', '1', '测试点对点数据，持久化1638513870015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c861ee90-96dd-4a09-9304-bf29523667fa', 'admin', '1', '测试点对点数据，持久化1638513900004', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b60f26b5-a9c9-4067-a358-f50e47119ba4', 'admin', '1', '测试点对点数据，持久化1638510360005', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '5e99146b-e033-476d-9754-2d43eb2f5534', 'admin', '1', '测试点对点数据，持久化1638510390020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '7e28e2e4-1c04-414b-812b-a5a4587956a6', 'admin', '1', '测试点对点数据，持久化1638510420020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e6dc6a13-6e22-4125-b217-d8f7159314ff', 'admin', '1', '测试点对点数据，持久化1638510450020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '327b5d77-23d5-445d-ad36-2f907812f78c', 'admin', '1', '测试点对点数据，持久化1638510480013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '95a19164-7b46-4ad6-92d1-9cbf93bfbc5b', 'admin', '1', '测试点对点数据，持久化1638510510013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ae95d309-135f-42dc-9edd-025d2c4834e0', 'admin', '1', '测试点对点数据，持久化1638510540012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1ad30a3e-dc45-4486-a0a3-d493b182a22d', 'admin', '1', '测试点对点数据，持久化1638510570007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2c11afbb-5c7c-4a70-bc2c-383dc8277c11', 'admin', '1', '测试点对点数据，持久化1638510600014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '46461f4e-adc5-4015-8a2f-85bae8a2439f', 'admin', '1', '测试点对点数据，持久化1638510630007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'd61b528e-c8d0-4d26-a165-c05318ab7c04', 'admin', '1', '测试点对点数据，持久化1638510660020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '7df4ea76-753c-4c8d-a9da-d9e0a7f8fc86', 'admin', '1', '测试点对点数据，持久化1638510690008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '946107bc-7735-489a-8947-5817fa1d707f', 'admin', '1', '测试点对点数据，持久化1638510720019', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'bc5c1ad8-623b-45a6-b5b4-854b71d94e6c', 'admin', '1', '测试点对点数据，持久化1638510750014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'aa764655-73d6-4e54-b7cb-88bf71bc7248', 'admin', '1', '测试点对点数据，持久化1638510810046', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '8a7bac0c-38a2-47db-9d87-7561a6dd02a4', 'admin', '1', '测试点对点数据，持久化1638510840014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ed6dd6e5-7ff2-4a72-9b0e-35cd158fd29f', 'admin', '1', '测试点对点数据，持久化1638510930049', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '6fe0be49-b141-422c-91ed-6c7bc332f35e', 'admin', '1', '测试点对点数据，持久化1638510960015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '63c03d7f-0869-4a78-9b4b-756a083739f2', 'admin', '1', '测试点对点数据，持久化1638510990009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b749c9e0-84a8-4413-a31d-4db7384186aa', 'admin', '1', '测试点对点数据，持久化1638511020009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '25a378a0-7e72-488b-8551-27801dc4996c', 'admin', '1', '测试点对点数据，持久化1638511050013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '5f8cf602-f747-40b2-b36a-cf42f4cfabf4', 'admin', '1', '测试点对点数据，持久化1638511080012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c500a938-14f9-4bbe-b3d3-d3c0a22407f9', 'admin', '1', '测试点对点数据，持久化1638511110012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2ca51263-c3a9-4c95-a262-2a97ba518550', 'admin', '1', '测试点对点数据，持久化1638511140012', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '5559981d-fb0a-4b25-8986-c5ede1414f2f', 'admin', '1', '测试点对点数据，持久化1638511170008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c8092c84-c69a-4687-9f14-68aa4e713b56', 'admin', '1', '测试点对点数据，持久化1638511200021', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '14969820-db47-4470-87c7-41cbcee9af49', 'admin', '1', '测试点对点数据，持久化1638511230008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '57f089cd-323d-422b-a5fc-ae6a495367d4', 'admin', '1', '测试点对点数据，持久化1638511260008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e317f7c7-772c-4692-9914-9babbe4e035d', 'admin', '1', '测试点对点数据，持久化1638511290003', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'f89b41a9-0f75-4c54-83ce-5d08180a4cd8', 'admin', '1', '测试点对点数据，持久化1638511320013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '9de66096-0b23-46a1-8236-a8171ec51437', 'admin', '1', '测试点对点数据，持久化1638511350014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c05c0567-40fc-43d3-aa28-6d627d3eb844', 'admin', '1', '测试点对点数据，持久化1638511380014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b4900e1c-706c-4941-b5c6-6c54305428be', 'admin', '1', '测试点对点数据，持久化1638511410015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a595459a-79bc-4725-8ea4-bbe3c3628a65', 'admin', '1', '测试点对点数据，持久化1638511440015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ebe7f880-6b5c-4873-985e-86672c429816', 'admin', '1', '测试点对点数据，持久化1638511470005', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'd64068ac-9851-483b-96c4-62ff9d496157', 'admin', '1', '测试点对点数据，持久化1638511500020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '2d8106e2-5b21-4add-a2ab-f7f43a361514', 'admin', '1', '测试点对点数据，持久化1638511560041', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '44a30e69-a342-400b-b90f-b04b7251086c', 'admin', '1', '测试点对点数据，持久化1638511590009', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'aca2d50b-57ec-44b1-adf0-1b5bf617b3c0', 'admin', '1', '测试点对点数据，持久化1638511650059', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '6ecdf087-db46-4c19-a12e-8b5dea633b69', 'admin', '1', '测试点对点数据，持久化1638511680007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '57537222-eda4-48ee-9eab-a451f7ef12f3', 'admin', '1', '测试点对点数据，持久化1638511710008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '1378a22f-3b3e-4b58-8c43-f09e72aef9dc', 'admin', '1', '测试点对点数据，持久化1638510300043', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'ccf517be-2677-4cd9-a5b6-638461003814', 'admin', '1', '测试点对点数据，持久化1638510330017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'c97c66da-8fd2-4991-8fbd-ca4b84fc4098', 'admin', '1', '测试点对点数据，持久化1638511740014', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'a05727d3-7426-4d35-8392-9fe565d47c09', 'admin', '1', '测试点对点数据，持久化1638511770011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '4555873c-63f4-4df0-8db0-e48c7d50fc1e', 'admin', '1', '测试点对点数据，持久化1638511800013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '17971831-f06d-4b41-937d-33481b4572d9', 'admin', '1', '测试点对点数据，持久化1638511830008', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '49abf36f-2b48-4ee5-8eb8-7020f50af1ff', 'admin', '1', '测试点对点数据，持久化1638511860007', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'e581a58d-e607-4849-b76c-19cd464b91a7', 'admin', '1', '测试点对点数据，持久化1638511890015', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '98a607af-fb80-45ac-80e7-e80febfef3f7', 'admin', '1', '测试点对点数据，持久化1638511920011', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'b97864f7-1e68-4368-ad1b-ff5080a1d496', 'admin', '1', '测试点对点数据，持久化1638511950003', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '334eccab-368b-48c7-9306-71183643e31d', 'admin', '1', '测试点对点数据，持久化1638511980020', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '3ce324d8-1fcd-4042-9956-22fdc4800ece', 'admin', '1', '测试点对点数据，持久化1638512010013', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'dd38eb0b-9657-4c19-a753-74db7a9149e9', 'admin', '1', '测试点对点数据，持久化1638512040010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', 'f4442cc5-0a78-4221-bdf8-cae78d9ca3e9', 'admin', '1', '测试点对点数据，持久化1638512070017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '829b5350-1c8a-46e4-b9d3-4b4b951cef78', 'admin', '1', '测试点对点数据，持久化1638512100010', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '39b74485-b49b-4355-b6bb-6d2ccde687ca', 'admin', '1', '测试点对点数据，持久化1638512130017', '/queue/message', '0', NULL);
INSERT INTO "GANSU"."WS_DATA" VALUES (NULL, 'BUSINESS', '9912833e-606e-4494-83eb-2f6870f1fc1c', 'admin', '1', '测试点对点数据，持久化1638512160012', '/queue/message', '0', NULL);
