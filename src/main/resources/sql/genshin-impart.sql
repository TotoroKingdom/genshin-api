/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : genshin-impart

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 14/02/2023 00:14:10
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_card
-- ----------------------------
DROP TABLE IF EXISTS `t_card`;
CREATE TABLE `t_card`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `card_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '01角色 02武器',
  `card_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `stars` int(0) NULL DEFAULT NULL COMMENT '星级',
  `up` int(0) NULL DEFAULT NULL COMMENT '0-未up 1-up',
  `indefinite` int(0) NULL DEFAULT NULL COMMENT '0-限定 1-常驻',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '十连图片',
  `big_img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单抽图片',
  `deleted` int(0) NULL DEFAULT 1 COMMENT '1有效 0删除',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '祈愿记录表，记录当前命定值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_card
-- ----------------------------

-- ----------------------------
-- Table structure for t_card_wishes
-- ----------------------------
DROP TABLE IF EXISTS `t_card_wishes`;
CREATE TABLE `t_card_wishes`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `wishes_id` bigint(0) NULL DEFAULT NULL COMMENT '祈愿活动ID',
  `card_id` bigint(0) NULL DEFAULT NULL COMMENT '卡池ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_card_wishes
-- ----------------------------

-- ----------------------------
-- Table structure for t_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `permission_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `menu_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `parent_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父级菜单名称',
  `parent_id` bigint(0) NULL DEFAULT NULL COMMENT '父级菜单ID',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '显示顺序',
  `router_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路由地址',
  `component` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组件',
  `visible` int(0) NULL DEFAULT 1 COMMENT '1:显示 0：隐藏',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'icon图标',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_pray
-- ----------------------------
DROP TABLE IF EXISTS `t_pray`;
CREATE TABLE `t_pray`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `primogem` int(0) NULL DEFAULT 0 COMMENT '原石数量',
  `acquain_fate` int(0) NULL DEFAULT 0 COMMENT '纠缠之缘',
  `interwined_fate` int(0) NULL DEFAULT 0 COMMENT '相遇之缘',
  `masterless_starglitter` int(0) NULL DEFAULT 0 COMMENT '星辉',
  `masterless_stardust` int(0) NULL DEFAULT 0 COMMENT '星尘',
  `five_star_character_num` int(0) NULL DEFAULT 0 COMMENT '五星角色累计次数',
  `five_star_character_up` int(0) NULL DEFAULT 0 COMMENT '五星角色up 1：小保底',
  `four_star_character_num` int(0) NULL DEFAULT 0 COMMENT '四星角色累计次数',
  `four_star_character_up` int(0) NULL DEFAULT 0 COMMENT '四星角色up 1:  小保底',
  `five_star_weapon_num` int(0) NULL DEFAULT 0 COMMENT '五星武器累计次数',
  `five_star_weapon_up` int(0) NULL DEFAULT 0 COMMENT '五星武器up 1:小保底',
  `epitomized_path_card_id` bigint(0) NULL DEFAULT 0 COMMENT '定轨武器池ID',
  `epitomized_path_up` int(0) NULL DEFAULT 0 COMMENT '命定值',
  `four_star_weapon_num` int(0) NULL DEFAULT 0 COMMENT '四星武器抽奖次数',
  `four_star_weapon_up` int(0) NULL DEFAULT 0 COMMENT '四星武器up 1:小保底',
  `five_star_indefinite_num` int(0) NULL DEFAULT 0 COMMENT '常驻五星抽奖次数',
  `five_star_indefinite_up` int(0) NULL DEFAULT 0 COMMENT '常驻五星up 1:保底',
  `four_star_indefinite_num` int(0) NULL DEFAULT 0 COMMENT '常驻四星抽奖次数',
  `four_star_indefinite_up` int(0) NULL DEFAULT 0 COMMENT '常驻四星up 1:保底',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '祈愿记录表，记录当前命定值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pray
-- ----------------------------

-- ----------------------------
-- Table structure for t_pray_record
-- ----------------------------
DROP TABLE IF EXISTS `t_pray_record`;
CREATE TABLE `t_pray_record`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL COMMENT '用户ID',
  `wishes_id` bigint(0) NULL DEFAULT NULL COMMENT '祈愿活动ID',
  `wishes_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '00常驻 01新手 02角色 03武器',
  `card_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '卡池名称',
  `card_id` bigint(0) NULL DEFAULT NULL COMMENT '卡池ID',
  `card_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '01角色 02武器',
  `wish_time` datetime(0) NULL DEFAULT NULL COMMENT '祈愿时间',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '祈愿记录表，记录当前命定值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pray_record
-- ----------------------------

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `role_key` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色编码',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_role_permission`;
CREATE TABLE `t_role_permission`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(0) NULL DEFAULT NULL,
  `permission_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `username` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '状态 00未激活 01激活 02停用',
  `register_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '注册激活码',
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, NULL, 'admin', '$2a$10$9fkCsFMrzhKxX5cDWuyNBOsEJbpIqy..0R3tbHQlGOwrtto7OOFgS', '634581304@qq.com', '01', '1fe90a407473435da70b5426019c258b', NULL, '2023-02-13 23:03:19', '2023-02-13 23:03:19', NULL, NULL, 0);
INSERT INTO `t_user` VALUES (2, '钟离', 'admin', '$2a$10$R.qa5KGlQYXfPzMaIdRBDeCJBexqV7oYt3uJ7Nzk2WVr2dFH.wTWm', NULL, NULL, NULL, NULL, NULL, '2023-02-13 23:06:52', NULL, NULL, 0);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(0) NULL DEFAULT NULL,
  `role_id` bigint(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for t_wishes
-- ----------------------------
DROP TABLE IF EXISTS `t_wishes`;
CREATE TABLE `t_wishes`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `wishes_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '00-常驻 01-角色活动1 02-角色活动2 03-武器活动  ',
  `wishes_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动名称',
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '活动图片',
  `begin_time` datetime(0) NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  `create_by` bigint(0) NULL DEFAULT NULL COMMENT '创建人',
  `update_by` bigint(0) NULL DEFAULT NULL COMMENT '更新人',
  `version` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '祈愿记录表，记录当前命定值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_wishes
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
