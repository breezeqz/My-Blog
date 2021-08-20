/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50731
 Source Host           : localhost:3306
 Source Schema         : my_blog_db

 Target Server Type    : MySQL
 Target Server Version : 50731
 File Encoding         : 65001

 Date: 20/08/2021 16:08:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for file_folder
-- ----------------------------
DROP TABLE IF EXISTS `file_folder`;
CREATE TABLE `file_folder`  (
  `file_folder_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件夹ID',
  `file_folder_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件夹名称',
  `parent_folder_id` int(11) NULL DEFAULT 0 COMMENT '父文件夹ID',
  `file_store_id` int(11) NULL DEFAULT NULL COMMENT '所属文件仓库ID',
  `time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`file_folder_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for file_store
-- ----------------------------
DROP TABLE IF EXISTS `file_store`;
CREATE TABLE `file_store`  (
  `file_store_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件仓库ID',
  `user_id` int(11) NULL DEFAULT NULL COMMENT '主人ID',
  `current_size` int(11) NULL DEFAULT 0 COMMENT '当前容量（单位KB）',
  `max_size` int(11) NULL DEFAULT 1048576 COMMENT '最大容量（单位KB）',
  `permission` int(11) NULL DEFAULT 0 COMMENT '仓库权限，0可上传下载、1不允许上传可以下载、2不可以上传下载',
  PRIMARY KEY (`file_store_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for generator_test
-- ----------------------------
DROP TABLE IF EXISTS `generator_test`;
CREATE TABLE `generator_test`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `test` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试字段',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for jdbc_test
-- ----------------------------
DROP TABLE IF EXISTS `jdbc_test`;
CREATE TABLE `jdbc_test`  (
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称'
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for my_file
-- ----------------------------
DROP TABLE IF EXISTS `my_file`;
CREATE TABLE `my_file`  (
  `my_file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
  `my_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `file_store_id` int(11) NULL DEFAULT NULL COMMENT '文件仓库ID',
  `my_file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '/' COMMENT '文件存储路径',
  `download_time` int(11) NULL DEFAULT 0 COMMENT '下载次数',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `parent_folder_id` int(11) NULL DEFAULT NULL COMMENT '父文件夹ID',
  `size` int(11) NULL DEFAULT NULL COMMENT '文件大小',
  `type` int(11) NULL DEFAULT NULL COMMENT '文件类型',
  `postfix` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件后缀',
  PRIMARY KEY (`my_file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_user`;
CREATE TABLE `tb_admin_user`  (
  `admin_user_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '管理员id',
  `login_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆名称',
  `login_password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员登陆密码',
  `nick_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员显示昵称',
  `locked` tinyint(4) NULL DEFAULT 0 COMMENT '是否锁定 0未锁定 1已锁定无法登陆',
  PRIMARY KEY (`admin_user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_blog
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog`;
CREATE TABLE `tb_blog`  (
  `blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标题',
  `blog_sub_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客自定义路径url',
  `blog_cover_image` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客封面图',
  `blog_content` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客内容',
  `blog_category_id` int(11) NOT NULL COMMENT '博客分类id',
  `blog_category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客分类(冗余字段)',
  `blog_tags` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '博客标签',
  `blog_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-草稿 1-发布',
  `blog_views` bigint(20) NOT NULL DEFAULT 0 COMMENT '阅读量',
  `enable_comment` tinyint(4) NOT NULL DEFAULT 0 COMMENT '0-允许评论 1-不允许评论',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_blog_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_category`;
CREATE TABLE `tb_blog_category`  (
  `category_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分类表主键',
  `category_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类的名称',
  `category_icon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类的图标',
  `category_rank` int(11) NOT NULL DEFAULT 1 COMMENT '分类的排序值 被使用的越多数值越大',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 27 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_blog_comment
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_comment`;
CREATE TABLE `tb_blog_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `blog_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '关联的blog主键',
  `commentator` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论者名称',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论人的邮箱',
  `website_url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '网址',
  `comment_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论内容',
  `comment_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '评论提交时间',
  `commentator_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '评论时的ip地址',
  `reply_body` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '回复内容',
  `reply_create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '回复时间',
  `comment_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否审核通过 0-未审核 1-审核通过',
  `is_deleted` tinyint(4) NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_blog_tag
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_tag`;
CREATE TABLE `tb_blog_tag`  (
  `tag_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '标签表主键id',
  `tag_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标签名称',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0=否 1=是',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_blog_tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `tb_blog_tag_relation`;
CREATE TABLE `tb_blog_tag_relation`  (
  `relation_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '关系表id',
  `blog_id` bigint(20) NOT NULL COMMENT '博客id',
  `tag_id` int(11) NOT NULL COMMENT '标签id',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 306 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_config
-- ----------------------------
DROP TABLE IF EXISTS `tb_config`;
CREATE TABLE `tb_config`  (
  `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的名称',
  `config_value` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '配置项的值',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`config_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_link
-- ----------------------------
DROP TABLE IF EXISTS `tb_link`;
CREATE TABLE `tb_link`  (
  `link_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '友链表主键id',
  `link_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '友链类别 0-友链 1-推荐 2-个人网站',
  `link_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站名称',
  `link_url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站链接',
  `link_description` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '网站描述',
  `link_rank` int(11) NOT NULL DEFAULT 0 COMMENT '用于列表排序',
  `is_deleted` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除 0-未删除 1-已删除',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`link_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tb_test
-- ----------------------------
DROP TABLE IF EXISTS `tb_test`;
CREATE TABLE `tb_test`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `test_info` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '测试内容',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for temp_file
-- ----------------------------
DROP TABLE IF EXISTS `temp_file`;
CREATE TABLE `temp_file`  (
  `file_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '临时文件ID',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件大小',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间：4小时后删除',
  `file_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件在FTP上的存放路径',
  PRIMARY KEY (`file_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户的openid',
  `file_store_id` int(11) NULL DEFAULT NULL COMMENT '文件仓库ID',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '	0000@qq.com' COMMENT '用户邮箱',
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `register_time` datetime NULL DEFAULT NULL COMMENT '注册时间',
  `image_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '头像地址',
  `role` int(11) NULL DEFAULT 1 COMMENT '用户角色,0管理员，1普通用户',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
