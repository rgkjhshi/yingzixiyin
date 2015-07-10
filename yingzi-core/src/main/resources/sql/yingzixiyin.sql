set names utf8;

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `open_id` varchar(50) NOT NULL COMMENT '微信用户的标识id',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码',
  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
  `is_bind` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否绑定手机, 0:未绑定, 1:已绑定',
  `collected` varchar(255) NOT NULL DEFAULT '' COMMENT '收藏的咨询师id列表, 如:1,2,3,',
  `visited` varchar(255) NOT NULL DEFAULT '' COMMENT '咨询过的咨询师id列表, 如:1,2,3,',
  PRIMARY KEY (`id`),
  KEY `idx_open_id` (open_id),
  KEY `idx_phone` (phone)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息';

CREATE TABLE `admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL DEFAULT '' COMMENT '密码',
  PRIMARY KEY (`id`),
  KEY `idx_username` (username)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='管理员信息';

CREATE TABLE `consultant` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(20) NOT NULL DEFAULT '123456' COMMENT '密码',
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '电话号码',
  `email` varchar(20) NOT NULL DEFAULT '' COMMENT '邮箱',
  `alipay` varchar(100) NOT NULL DEFAULT '' COMMENT '支付宝账号',
  `nickname` varchar(50) NOT NULL COMMENT '昵称',
  `name` varchar(10) NOT NULL DEFAULT '' COMMENT '姓名',
  `gender` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别, 0:未知, 1:男, 2:女',
  `age` int(4) NOT NULL DEFAULT 25 COMMENT '年龄',
  `address` varchar(100) NOT NULL DEFAULT '' COMMENT '地址',
  `professional` varchar(255) NOT NULL DEFAULT '' COMMENT '专业背景',
  `background` varchar(255) NOT NULL DEFAULT '' COMMENT '受训背景',
  `book_time` varchar(100) NOT NULL DEFAULT '' COMMENT '可预约时间',
  `price` decimal(13, 2) NOT NULL DEFAULT 59.00 COMMENT '咨询单价',
  `introduce` varchar(255) NOT NULL DEFAULT '' COMMENT '个人简介',
  `signature` varchar(255) NOT NULL DEFAULT '' COMMENT '个人签名',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '头像url',
  `range_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '咨询师可咨询的范围类型',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '咨询师状态，0:初始状态, 1:审核通过, 2:审核不通过',
  PRIMARY KEY (`id`),
  KEY `idx_username` (username),
  KEY `idx_email` (email),
  KEY `idx_phone` (phone)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='咨询师信息';

CREATE TABLE `record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(20) NOT NULL COMMENT '咨询者id',
  `consultant_id` bigint(20) NOT NULL COMMENT '咨询师id',
  `consult_type` tinyint(4) NOT NULL DEFAULT 1 COMMENT '咨询类型, 1:线上咨询, 2:线下预约',
  `is_paid` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否付款, 0:未付款, 1:已付款',
  `is_replied` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否回应，0:未回应, 1:已经回应',
  `is_completed` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否结束，0:未结束, 1:已结束',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (user_id),
  KEY `idx_consultant_id` (consultant_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='咨询记录';

CREATE TABLE `message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `record_id` bigint(20) NOT NULL COMMENT '咨询问题id',
  `message` varchar(1024) NOT NULL DEFAULT '' COMMENT '消息内容',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `is_read` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已读，0:未读, 1:已读',
  PRIMARY KEY (`id`),
  KEY `idx_record_id` (record_id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='消息表';
