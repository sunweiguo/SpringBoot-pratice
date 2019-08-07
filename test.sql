/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50713
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50713
File Encoding         : 65001

Date: 2019-08-07 17:47:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for c_general_data_transaction
-- ----------------------------
DROP TABLE IF EXISTS `c_general_data_transaction`;
CREATE TABLE `c_general_data_transaction` (
  `workid` varchar(32) NOT NULL COMMENT '工单流水号',
  `systemid` varchar(8) DEFAULT NULL COMMENT '业务系统编码',
  `correlateid` varchar(32) DEFAULT NULL COMMENT '工单关联性标识',
  `worktype` tinyint(4) NOT NULL COMMENT '工单类型',
  `pubstatus` tinyint(4) NOT NULL COMMENT 'KAFKA写入状态',
  `areano` varchar(10) DEFAULT NULL COMMENT '工单发布地区',
  `topic` varchar(255) NOT NULL COMMENT '工单发布topic',
  `data` text COMMENT '工单内容实体',
  `datamd5` char(32) DEFAULT NULL COMMENT '工单内容MD5校验值',
  `pubtime` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '工单发布时间',
  `datatype` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of c_general_data_transaction
-- ----------------------------
INSERT INTO `c_general_data_transaction` VALUES ('990000010015622261926000026', '99000001', '990000010015622261926000026', '0', '0', '00', 'test-producer-group', '{\"area_no\":\"00\",\"correlate_id\":\"990000010015622261926000026\",\"data\":{\"attrs\":{\"serviceGroupName\":\"eee\"},\"data_type\":0,\"database\":\"bmsdb\",\"extra\":{\"sql\":\"UPDATE I_SERVICEGROUP  SET serviceGroupName = \'eee\' WHERE  serviceGroupID = \'SGaj0036\'\"},\"primaries\":{\"serviceGroupID\":\"SGaj0036\"},\"table\":\"I_SERVICEGROUP\"},\"data_md5\":\"4C3019EF11503BFA6A6B9E4AE56454A1\",\"pub_time\":\"20190704154312\",\"system_id\":\"99000001\",\"work_id\":\"990000010015622261926000026\",\"work_type\":0}', '17C7F520F6C4B522852F8546E7A12508', '2019-08-04 12:27:26', '0');

-- ----------------------------
-- Table structure for c_general_data_transaction_status
-- ----------------------------
DROP TABLE IF EXISTS `c_general_data_transaction_status`;
CREATE TABLE `c_general_data_transaction_status` (
  `workid` varchar(32) NOT NULL COMMENT '工单流水号',
  `correlateid` varchar(32) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `areano` varchar(10) NOT NULL,
  `feedbackdate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `resultdescription` text,
  PRIMARY KEY (`workid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of c_general_data_transaction_status
-- ----------------------------
INSERT INTO `c_general_data_transaction_status` VALUES ('6666666632000015649216460510002', '990000010015622261926000026', '0', '320000', '2019-08-04 12:27:26', '数据解析入库成功。');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `class_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '小明', '20', '1');
INSERT INTO `t_user` VALUES ('2', '小芳', '18', '1');
INSERT INTO `t_user` VALUES ('3', '小华', '22', '2');
