
#咨询师数据构造

INSERT INTO `consultant` (`id`, `phone`, `email`, `password`, `alipay`, `nickname`, `name`, `gender`, `age`, `address`, `professional`, `background`, `book_time`, `price`, `introduce`, `signature`, `avatar`, `range_type`, `status`) VALUES
(1, '13121435540', '313832830@qq.com', '123456', '313832830@qq.com', '会飞的猪', '张大梅', 0, 18, '北京市海淀区西土城路1号', '北大青鸟出身', '专治各种不服，心里变态狂', '每周六 13:00-15:00', 59.00, '咨询师来自牛逼天堂', '活着不如死了', '', 1, 2);

INSERT INTO `consultant` (`id`, `phone`, `email`, `password`, `alipay`, `nickname`, `name`, `gender`, `age`, `address`, `professional`, `background`, `book_time`, `price`, `introduce`, `signature`, `avatar`, `range_type`, `status`) VALUES
(2, '13121455545', '313832831@qq.com', '123456', '313832831@qq.com', '会飞的<。)#)))≦', '张大梅', 0, 18, '北京市海淀区西土城路1号', '北大青鸟出身', '专治各种不服，心里变态狂', '每周六 13:00-15:00', 59.00, '咨询师来自牛逼天堂', '活着不如死了', '', 1, 2);

INSERT INTO `consultant` (`id`, `phone`, `email`, `password`, `alipay`, `nickname`, `name`, `gender`, `age`, `address`, `professional`, `background`, `book_time`, `price`, `introduce`, `signature`, `avatar`, `range_type`, `status`) VALUES
(3, '131261435546', '313832832@qq.com', '123456', '313832832@qq.com', '会飞的大梅', '张大梅', 0, 18, '北京市海淀区西土城路1号', '北大青鸟出身', '专治各种不服，心里变态狂', '每周六 13:00-15:00', 59.00, '咨询师来自牛逼天堂', '活着不如死了', '', 1, 2);

INSERT INTO `consultant` (`id`, `phone`, `email`, `password`, `alipay`, `nickname`, `name`, `gender`, `age`, `address`, `professional`, `background`, `book_time`, `price`, `introduce`, `signature`, `avatar`, `range_type`, `status`) VALUES
(4, '13125435547', '313832833@qq.com', '123456', '313832833@qq.com', '骑着猪去赶集', '张大梅', 0, 18, '北京市海淀区西土城路1号', '北大青鸟出身', '专治各种不服，心里变态狂', '每周六 13:00-15:00', 59.00, '咨询师来自牛逼天堂', '活着不如死了', '', 1, 2);

INSERT INTO `consultant` (`id`, `phone`, `email`, `password`, `alipay`, `nickname`, `name`, `gender`, `age`, `address`, `professional`, `background`, `book_time`, `price`, `introduce`, `signature`, `avatar`, `range_type`, `status`) VALUES
(5, '13127435548', '313832834@qq.com', '123456', '313832834@qq.com', '狗的寂寞', '张大梅', 0, 18, '北京市海淀区西土城路1号', '北大青鸟出身', '专治各种不服，心里变态狂', '每周六 13:00-15:00', 59.00, '咨询师来自牛逼天堂', '活着不如死了', '', 1, 2);

#管理员数据构造

INSERT INTO `admin` VALUES (1, '13718340933', 'lee123');
INSERT INTO `admin` VALUES (2, '13718340934', 'lee123');
INSERT INTO `admin` VALUES (3, '13718340935', 'lee123');
INSERT INTO `admin` VALUES (4, '13718340936', 'lee123');

#构造咨询记录

INSERT INTO `record` VALUES (1, 1, 1, 1, 1, 1, 0, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (2, 1, 2, 1, 1, 1, 0, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (3, 1, 1, 1, 1, 1, 0, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (4, 2, 3, 1, 1, 1, 0, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (5, 1, 1, 1, 1, 1, 1, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (6, 2, 4, 1, 1, 1, 1, '2015-8-8 14:56:08');
INSERT INTO `record` VALUES (7, 1, 1, 1, 1, 1, 1, '2015-8-8 14:56:08');

#构造用户记录
INSERT INTO `user` VALUES (1, 'oGBeWt-feUNN9UJzt7YHJM3VnzKc', '13718340933', '', 0, '1,2', '2,3,4');
INSERT INTO `user` VALUES (2, 'oGBeWt3HkUfi3C0k7kCVNpGdv6Kf', '13718340934', '', 0, '', '');
INSERT INTO `user` VALUES (3, 'oGBeWt-feUNN9UJzt7YHJM3VnzKc', '13718340935', '', 0, '', '');
INSERT INTO `user` VALUES (4, 'oGBeWt3HlUfi3C0k7pCVNpGmv6Kg', '13718340936', '', 0, '', '');


#构造消息数据记录

INSERT INTO `message` VALUES (1, 1, ' 你好啊，有什么可以帮助你的吗？', '13121435540', '13718340933', '2015-8-8 15:02:01', 0);
INSERT INTO `message` VALUES (2, 2, ' 你好啊，有什么可以帮助你的吗？', '13718340933', '13121455545', '2015-8-8 15:02:01', 0);
INSERT INTO `message` VALUES (3, 3, ' 你好啊，有什么可以帮助你的吗？', '13121435540', '131261435546', '2015-8-8 15:02:01', 0);
INSERT INTO `message` VALUES (4, 1, ' 你好啊，有什么可以帮助你的吗？', '13718340933', '13121435540', '2015-8-8 15:02:01', 0);
INSERT INTO `message` VALUES (5, 1, ' 你好啊，有什么可以帮助你的吗？', '13121435540', '13718340933', '2015-8-8 15:02:01', 0);
INSERT INTO `message` VALUES (6, 2, ' 你好啊，有什么可以帮助你的吗？', '13718340933', '13121455545', '2015-8-8 15:02:01', 0);