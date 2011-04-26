-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.49-1ubuntu8.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema db_airline
--

CREATE DATABASE IF NOT EXISTS db_airline;
USE db_airline;

--
-- Definition of table `db_airline`.`airtype`
--

DROP TABLE IF EXISTS `db_airline`.`airtype`;
CREATE TABLE  `db_airline`.`airtype` (
  `air_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `air_code` varchar(20) NOT NULL,
  `air_F` varchar(20) DEFAULT NULL,
  `air_Fname` varchar(20) DEFAULT NULL,
  `air_Fnumber` smallint(4) DEFAULT NULL,
  `air_C` varchar(20) DEFAULT NULL,
  `air_Cname` varchar(20) DEFAULT NULL,
  `air_Cnumber` smallint(4) DEFAULT NULL,
  `air_Y` varchar(20) DEFAULT NULL,
  `air_Yname` varchar(20) DEFAULT NULL,
  `air_Ynumber` smallint(4) DEFAULT NULL,
  `air_totalnumber` smallint(4) NOT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`air_autoid`),
  UNIQUE KEY `air_code` (`air_code`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`airtype`
--

/*!40000 ALTER TABLE `airtype` DISABLE KEYS */;
LOCK TABLES `airtype` WRITE;
INSERT INTO `db_airline`.`airtype` VALUES  (1,'hzu','F','F',100,'C','C',120,'Y','Y',125,345,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `airtype` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`bank`
--

DROP TABLE IF EXISTS `db_airline`.`bank`;
CREATE TABLE  `db_airline`.`bank` (
  `ban_account` decimal(20,0) NOT NULL,
  `ban_pwd` decimal(20,0) NOT NULL,
  `ban_money` double NOT NULL,
  PRIMARY KEY (`ban_account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`bank`
--

/*!40000 ALTER TABLE `bank` DISABLE KEYS */;
LOCK TABLES `bank` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `bank` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`bookinformation`
--

DROP TABLE IF EXISTS `db_airline`.`bookinformation`;
CREATE TABLE  `db_airline`.`bookinformation` (
  `boo_autoid` bigint(8) NOT NULL AUTO_INCREMENT,
  `com_code` varchar(20) NOT NULL,
  `cus_id` varchar(20) NOT NULL,
  `boo_everyday` varchar(20) NOT NULL,
  `boo_no` varchar(20) NOT NULL,
  `boo_baddress` varchar(20) NOT NULL,
  `boo_aaddress` varchar(20) NOT NULL,
  `boo_btime` datetime NOT NULL,
  `boo_atime` datetime NOT NULL,
  `boo_berth` varchar(20) NOT NULL,
  `boo_number` smallint(4) NOT NULL,
  `boo_fare` double NOT NULL,
  `boo_time` datetime NOT NULL,
  `flag_pay` tinyint(1) NOT NULL,
  `flag_type` tinyint(1) NOT NULL,
  `flag_pass` tinyint(1) NOT NULL,
  PRIMARY KEY (`boo_autoid`),
  KEY `com_code` (`com_code`),
  KEY `cus_id` (`cus_id`),
  KEY `boo_everyday` (`boo_everyday`),
  KEY `boo_no` (`boo_no`),
  KEY `boo_baddress` (`boo_baddress`),
  KEY `boo_btime` (`boo_btime`),
  KEY `boo_aaddress` (`boo_aaddress`),
  KEY `boo_atime` (`boo_atime`),
  CONSTRAINT `bookinformation_ibfk_1` FOREIGN KEY (`com_code`) REFERENCES `flightcompany` (`com_code`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`bookinformation`
--

/*!40000 ALTER TABLE `bookinformation` DISABLE KEYS */;
LOCK TABLES `bookinformation` WRITE;
INSERT INTO `db_airline`.`bookinformation` VALUES  (38,'CC','2','星期六','123','惠州','深圳','2011-04-20 00:00:00','2011-04-20 07:30:00','经济舱',94,304.128,'2011-04-23 20:22:03',1,1,1),
 (39,'CA','321','星期二','112','惠州','广州','2011-04-25 02:30:00','2011-04-25 03:30:00','商务舱',124,680.4,'2011-05-25 14:04:05',1,0,0),
 (40,'CA','4212','星期二','112','惠州','广州','2011-04-25 02:30:00','2011-04-25 03:30:00','经济舱',112,491.4,'2011-05-25 14:04:43',1,0,0),
 (47,'CC','1','星期六','123','惠州','深圳','2011-04-20 00:00:00','2011-04-20 07:30:00','经济舱',93,304.128,'2011-04-26 21:08:31',1,1,1),
 (48,'CC','1','星期六','123','惠州','深圳','2011-04-20 00:00:00','2011-04-20 07:30:00','经济舱',93,304.128,'2011-04-26 21:08:41',1,1,1),
 (49,'CC','1','星期六','123','惠州','深圳','2011-04-20 00:00:00','2011-04-20 07:30:00','经济舱',93,304.128,'2011-04-26 21:09:35',1,1,0);
UNLOCK TABLES;
/*!40000 ALTER TABLE `bookinformation` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`customer`
--

DROP TABLE IF EXISTS `db_airline`.`customer`;
CREATE TABLE  `db_airline`.`customer` (
  `cus_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `cus_account` varchar(20) NOT NULL,
  `cus_pwd` varchar(20) NOT NULL,
  `cus_id` varchar(20) NOT NULL,
  `cus_sex` varchar(2) NOT NULL,
  `cus_telnumber` varchar(20) NOT NULL,
  `cus_email` varchar(50) NOT NULL,
  `cus_time` datetime NOT NULL,
  `cus_integral` int(4) DEFAULT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`cus_autoid`),
  UNIQUE KEY `cus_id` (`cus_id`),
  KEY `cus_account` (`cus_account`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`customer`
--

/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
LOCK TABLES `customer` WRITE;
INSERT INTO `db_airline`.`customer` VALUES  (1,'1','1','1','1','1','1','2000-09-09 00:00:00',1001791,1),
 (2,'2','2','2','2','2','2','2000-09-09 00:00:00',1001487,1),
 (3,'3','3','3','3','3','3','2000-09-09 00:00:00',1001487,1),
 (4,'cao','summerdir','441523198808267033','男','15820707083','summerdir@gmail.com','2011-04-26 21:23:40',0,1),
 (5,'','','s','男','','','2011-04-26 21:40:26',0,1),
 (6,'','','','男','','','2011-04-26 21:40:35',0,1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`faretype`
--

DROP TABLE IF EXISTS `db_airline`.`faretype`;
CREATE TABLE  `db_airline`.`faretype` (
  `far_comcode` varchar(20) NOT NULL,
  `far_goldscore` int(11) DEFAULT NULL,
  `far_golddiscount` double DEFAULT NULL,
  `far_silscore` int(11) DEFAULT NULL,
  `far_sildiscount` double DEFAULT NULL,
  `far_broscore` int(11) DEFAULT NULL,
  `far_brodiscount` double DEFAULT NULL,
  PRIMARY KEY (`far_comcode`),
  KEY `far_goldscore` (`far_goldscore`),
  KEY `far_golddiscount` (`far_golddiscount`),
  KEY `far_silscore` (`far_silscore`),
  KEY `far_sildiscount` (`far_sildiscount`),
  KEY `far_broscore` (`far_broscore`),
  KEY `far_brodiscount` (`far_brodiscount`),
  KEY `far_comcode` (`far_comcode`),
  CONSTRAINT `faretype_ibfk_1` FOREIGN KEY (`far_comcode`) REFERENCES `flightcompany` (`com_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`faretype`
--

/*!40000 ALTER TABLE `faretype` DISABLE KEYS */;
LOCK TABLES `faretype` WRITE;
INSERT INTO `db_airline`.`faretype` VALUES  ('CA',10000,0.9,8500,0.95,7000,0.93),
 ('CC',1384,0.88,1200,0.9,1100,0.95);
UNLOCK TABLES;
/*!40000 ALTER TABLE `faretype` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`flightcompany`
--

DROP TABLE IF EXISTS `db_airline`.`flightcompany`;
CREATE TABLE  `db_airline`.`flightcompany` (
  `com_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `com_code` varchar(20) NOT NULL,
  `com_name` varchar(50) NOT NULL,
  `com_address` varchar(50) NOT NULL,
  `com_register` date NOT NULL,
  `com_information` varchar(10000) NOT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`com_autoid`),
  UNIQUE KEY `com_code` (`com_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`flightcompany`
--

/*!40000 ALTER TABLE `flightcompany` DISABLE KEYS */;
LOCK TABLES `flightcompany` WRITE;
INSERT INTO `db_airline`.`flightcompany` VALUES  (1,'CA','惠州学院','惠州学院','2011-04-07','加油！​',1),
 (3,'CC','A','A','2011-04-09','123',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `flightcompany` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`flightinformation`
--

DROP TABLE IF EXISTS `db_airline`.`flightinformation`;
CREATE TABLE  `db_airline`.`flightinformation` (
  `fli_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `com_code` varchar(20) NOT NULL,
  `air_code` varchar(20) NOT NULL,
  `fli_everyday` varchar(20) NOT NULL,
  `fli_no` varchar(20) NOT NULL,
  `fli_discount` double NOT NULL,
  `fli_baddress` varchar(20) NOT NULL,
  `fli_aaddress` varchar(20) NOT NULL,
  `fli_btime` datetime NOT NULL,
  `fli_atime` datetime NOT NULL,
  `fli_Fnumber` smallint(4) DEFAULT NULL,
  `fli_Cnumber` smallint(4) DEFAULT NULL,
  `fli_Ynumber` smallint(4) DEFAULT NULL,
  `fli_Ffare` double DEFAULT NULL,
  `fli_Cfare` double DEFAULT NULL,
  `fli_Yfare` double DEFAULT NULL,
  `fli_refundtime` varchar(20) NOT NULL,
  `fli_refund` varchar(20) NOT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`fli_autoid`),
  KEY `com_code` (`com_code`),
  KEY `air_code` (`air_code`),
  KEY `fli_everyday` (`fli_everyday`),
  KEY `fli_no` (`fli_no`),
  KEY `fli_baddress` (`fli_baddress`),
  KEY `fli_btime` (`fli_btime`),
  KEY `fli_aaddress` (`fli_aaddress`),
  KEY `fli_atime` (`fli_atime`),
  CONSTRAINT `flightinformation_ibfk_1` FOREIGN KEY (`com_code`) REFERENCES `flightcompany` (`com_code`),
  CONSTRAINT `flightinformation_ibfk_2` FOREIGN KEY (`air_code`) REFERENCES `airtype` (`air_code`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`flightinformation`
--

/*!40000 ALTER TABLE `flightinformation` DISABLE KEYS */;
LOCK TABLES `flightinformation` WRITE;
INSERT INTO `db_airline`.`flightinformation` VALUES  (5,'CC','hzu','星期六','123',0.8,'惠州','深圳','2011-04-20 00:00:00','2011-04-20 07:30:00',95,92,127,123,432,543,'正常','运行',1),
 (6,'CA','hzu','星期二','112',0.9,'惠州','广州','2011-04-23 02:30:00','2011-04-23 03:30:00',100,111,123,342,546,756,'正常','运行',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `flightinformation` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`hotelinformation`
--

DROP TABLE IF EXISTS `db_airline`.`hotelinformation`;
CREATE TABLE  `db_airline`.`hotelinformation` (
  `hotel_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `hotel_name` varchar(20) NOT NULL,
  `hotel_level` int(11) NOT NULL,
  `hotel_tel` varchar(20) DEFAULT NULL,
  `hotel_city` varchar(20) NOT NULL,
  `hotel_address` varchar(20) NOT NULL,
  `hotel_page` varchar(20) DEFAULT NULL,
  `hotel_picture` varchar(20) DEFAULT NULL,
  `flag` int(10) unsigned NOT NULL,
  PRIMARY KEY (`hotel_autoid`),
  KEY `hotel_level` (`hotel_level`),
  KEY `hotel_city` (`hotel_city`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`hotelinformation`
--

/*!40000 ALTER TABLE `hotelinformation` DISABLE KEYS */;
LOCK TABLES `hotelinformation` WRITE;
INSERT INTO `db_airline`.`hotelinformation` VALUES  (1,'s',3,'s','s','s','s','s',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `hotelinformation` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`manager`
--

DROP TABLE IF EXISTS `db_airline`.`manager`;
CREATE TABLE  `db_airline`.`manager` (
  `man_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `man_account` varchar(20) NOT NULL,
  `man_pwd` varchar(20) NOT NULL,
  `man_id` varchar(20) NOT NULL,
  `man_sex` varchar(2) NOT NULL,
  `man_telnumber` varchar(20) NOT NULL,
  `man_email` varchar(50) NOT NULL,
  `man_register` date NOT NULL,
  `flag` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`man_autoid`),
  UNIQUE KEY `man_account` (`man_account`),
  UNIQUE KEY `man_id` (`man_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`manager`
--

/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
LOCK TABLES `manager` WRITE;
INSERT INTO `db_airline`.`manager` VALUES  (1,'admin','admin','1234','b','123','96514181@qq.com','2010-11-03',1),
 (2,'admin1','admin1','5678','b','123','514181@qq.com','2010-11-03',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`membership`
--

DROP TABLE IF EXISTS `db_airline`.`membership`;
CREATE TABLE  `db_airline`.`membership` (
  `comment_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` varchar(20) NOT NULL,
  `customer_name` varchar(20) NOT NULL,
  `comment_content` varchar(1000) NOT NULL,
  `comment_time` datetime NOT NULL,
  `comment_IP` varchar(20) NOT NULL,
  `flag` tinyint(1) NOT NULL,
  PRIMARY KEY (`comment_autoid`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `membership_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`cus_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`membership`
--

/*!40000 ALTER TABLE `membership` DISABLE KEYS */;
LOCK TABLES `membership` WRITE;
INSERT INTO `db_airline`.`membership` VALUES  (1,'1','1','航程网','2011-04-25 14:54:12','::1',1),
 (2,'1','1','你好我不好呵','2011-04-25 14:56:36','::1',1),
 (3,'1','1','hgfhgfh','2011-04-26 21:42:37','113.81.35.0',1);
UNLOCK TABLES;
/*!40000 ALTER TABLE `membership` ENABLE KEYS */;


--
-- Definition of table `db_airline`.`refundrecord`
--

DROP TABLE IF EXISTS `db_airline`.`refundrecord`;
CREATE TABLE  `db_airline`.`refundrecord` (
  `ref_autoid` int(11) NOT NULL AUTO_INCREMENT,
  `boo_autoid` bigint(8) DEFAULT NULL,
  `boo_number` smallint(4) DEFAULT NULL,
  PRIMARY KEY (`ref_autoid`),
  UNIQUE KEY `boo_autoid` (`boo_autoid`),
  CONSTRAINT `refundrecord_ibfk_1` FOREIGN KEY (`boo_autoid`) REFERENCES `bookinformation` (`boo_autoid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `db_airline`.`refundrecord`
--

/*!40000 ALTER TABLE `refundrecord` DISABLE KEYS */;
LOCK TABLES `refundrecord` WRITE;
INSERT INTO `db_airline`.`refundrecord` VALUES  (7,48,93);
UNLOCK TABLES;
/*!40000 ALTER TABLE `refundrecord` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
