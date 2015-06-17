/*
SQLyog Ultimate v11.24 (32 bit)
MySQL - 5.6.20-log : Database - bysj
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bysj` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `bysj`;

/*Table structure for table `t_attach` */

DROP TABLE IF EXISTS `t_attach`;

CREATE TABLE `t_attach` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `FILETYPE` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `FILEPATH` varchar(255) DEFAULT NULL,
  `FILENAME` varchar(255) DEFAULT NULL,
  `downloadCount` bigint(20) DEFAULT NULL,
  `fileImages` varchar(255) DEFAULT NULL,
  `fromName` varchar(255) DEFAULT NULL,
  `FROMID` bigint(20) DEFAULT NULL,
  `FROMSUBID` bigint(20) DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK272A3470162CFABA` (`CREATEUSER`),
  CONSTRAINT `FK272A3470162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_attach` */

/*Table structure for table `t_bbsblock` */

DROP TABLE IF EXISTS `t_bbsblock`;

CREATE TABLE `t_bbsblock` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `BLOCKRULES` text,
  `DESCRIPTION` text,
  `PARENT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKB37D53A56B6114EC` (`PARENT`),
  CONSTRAINT `FKB37D53A56B6114EC` FOREIGN KEY (`PARENT`) REFERENCES `t_bbsblock` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbsblock` */

/*Table structure for table `t_bbscontent` */

DROP TABLE IF EXISTS `t_bbscontent`;

CREATE TABLE `t_bbscontent` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `CREATETIME` datetime DEFAULT NULL,
  `MODIFYTIME` datetime DEFAULT NULL,
  `READTIMES` bigint(20) DEFAULT NULL,
  `TOP` bit(1) DEFAULT NULL,
  `ACCESS` bit(1) DEFAULT NULL,
  `ATTACHIDS` varchar(255) DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  `BBSBLOCK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK3755991162CFABA` (`CREATEUSER`),
  KEY `FK375599174E0FC7C` (`BBSBLOCK`),
  CONSTRAINT `FK3755991162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK375599174E0FC7C` FOREIGN KEY (`BBSBLOCK`) REFERENCES `t_bbsblock` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbscontent` */

/*Table structure for table `t_bbscontentcomments` */

DROP TABLE IF EXISTS `t_bbscontentcomments`;

CREATE TABLE `t_bbscontentcomments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` text,
  `CREATETIME` datetime DEFAULT NULL,
  `MODIFYTIME` datetime DEFAULT NULL,
  `ACCESS` bit(1) DEFAULT NULL,
  `BBSCONTENT` bigint(20) DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  `BBSBLOCK` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK8E8D0685162CFABA` (`CREATEUSER`),
  KEY `FK8E8D068534908BD4` (`BBSCONTENT`),
  KEY `FK8E8D068574E0FC7C` (`BBSBLOCK`),
  CONSTRAINT `FK8E8D0685162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK8E8D068534908BD4` FOREIGN KEY (`BBSCONTENT`) REFERENCES `t_bbscontent` (`ID`),
  CONSTRAINT `FK8E8D068574E0FC7C` FOREIGN KEY (`BBSBLOCK`) REFERENCES `t_bbsblock` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_bbscontentcomments` */

/*Table structure for table `t_classdetail` */

DROP TABLE IF EXISTS `t_classdetail`;

CREATE TABLE `t_classdetail` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `ATTACHIDS` varchar(255) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  `COURSECLASS` bigint(20) DEFAULT NULL,
  `COURSE` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK5DF7AC5E162CFABA` (`CREATEUSER`),
  KEY `FK5DF7AC5E39B8B65E` (`COURSE`),
  KEY `FK5DF7AC5E5538F732` (`COURSECLASS`),
  CONSTRAINT `FK5DF7AC5E162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK5DF7AC5E39B8B65E` FOREIGN KEY (`COURSE`) REFERENCES `t_course` (`ID`),
  CONSTRAINT `FK5DF7AC5E5538F732` FOREIGN KEY (`COURSECLASS`) REFERENCES `t_courseclass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_classdetail` */

/*Table structure for table `t_classdetailcomments` */

DROP TABLE IF EXISTS `t_classdetailcomments`;

CREATE TABLE `t_classdetailcomments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` text,
  `CREATETIME` datetime DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  `PARENTS` bigint(20) DEFAULT NULL,
  `CLASSDETAIL` bigint(20) DEFAULT NULL,
  `COURSECLASS` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKED6A3C5288F1E8A` (`CLASSDETAIL`),
  KEY `FKED6A3C52F8C054DE` (`PARENTS`),
  KEY `FKED6A3C52162CFABA` (`CREATEUSER`),
  KEY `FKED6A3C52280A54F0` (`ID`),
  KEY `FKED6A3C525538F732` (`COURSECLASS`),
  CONSTRAINT `FKED6A3C52162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FKED6A3C52280A54F0` FOREIGN KEY (`ID`) REFERENCES `t_classdetailcomments` (`ID`),
  CONSTRAINT `FKED6A3C525538F732` FOREIGN KEY (`COURSECLASS`) REFERENCES `t_courseclass` (`id`),
  CONSTRAINT `FKED6A3C5288F1E8A` FOREIGN KEY (`CLASSDETAIL`) REFERENCES `t_classdetail` (`ID`),
  CONSTRAINT `FKED6A3C52F8C054DE` FOREIGN KEY (`PARENTS`) REFERENCES `t_classdetailcomments` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_classdetailcomments` */

/*Table structure for table `t_course` */

DROP TABLE IF EXISTS `t_course`;

CREATE TABLE `t_course` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `COURSELEVEL` bigint(20) DEFAULT NULL,
  `DESCRIPTION` text,
  `TEACHERTEAM` text,
  `TEACHCONTENT` text,
  `TEACHMETHOD` text,
  `TEACHENVIRONMENT` text,
  `TEACHOUTLINE` text,
  PRIMARY KEY (`ID`),
  KEY `FK2A4E26465630DB4A` (`COURSELEVEL`),
  CONSTRAINT `FK2A4E26465630DB4A` FOREIGN KEY (`COURSELEVEL`) REFERENCES `t_courselevel` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_course` */

/*Table structure for table `t_courseclass` */

DROP TABLE IF EXISTS `t_courseclass`;

CREATE TABLE `t_courseclass` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `COURSECLASSIMAGE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` text,
  `CRAETETIME` datetime DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  `course` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK69E086D2162CFABA` (`CREATEUSER`),
  KEY `FK69E086D239B8B65E` (`course`),
  CONSTRAINT `FK69E086D2162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK69E086D239B8B65E` FOREIGN KEY (`course`) REFERENCES `t_course` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_courseclass` */

/*Table structure for table `t_courselevel` */

DROP TABLE IF EXISTS `t_courselevel`;

CREATE TABLE `t_courselevel` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(255) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_courselevel` */

insert  into `t_courselevel`(`ID`,`NAME`,`DESCRIPTION`) values (1,'本科','本科阶段课程'),(2,'高中','高中阶段课程'),(3,'职教','职业教育');

/*Table structure for table `t_news` */

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `HOT` bit(1) DEFAULT NULL,
  `SOURCE` varchar(255) DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `READTIMES` bigint(20) DEFAULT NULL,
  `IMAGES` varchar(255) DEFAULT NULL,
  `NEWSKEY` varchar(255) DEFAULT NULL,
  `ACCESS` bit(1) DEFAULT NULL,
  `COMMENTSCOUNT` bigint(20) DEFAULT NULL,
  `CREATETIME` datetime DEFAULT NULL,
  `MODIFYTIME` datetime DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKCB5163BE162CFABA` (`CREATEUSER`),
  CONSTRAINT `FKCB5163BE162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

/*Table structure for table `t_newscomments` */

DROP TABLE IF EXISTS `t_newscomments`;

CREATE TABLE `t_newscomments` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `CONTENT` text,
  `CREATETIME` datetime DEFAULT NULL,
  `ACCESS` bit(1) DEFAULT NULL,
  `NEWS` bigint(20) DEFAULT NULL,
  `PARENT` bigint(20) DEFAULT NULL,
  `createUser` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK76C693B2162CFABA` (`createUser`),
  KEY `FK76C693B2C3FF0F2A` (`ID`),
  KEY `FK76C693B2C4B175CE` (`NEWS`),
  KEY `FK76C693B288AA0AB9` (`PARENT`),
  CONSTRAINT `FK76C693B2162CFABA` FOREIGN KEY (`createUser`) REFERENCES `t_user` (`ID`),
  CONSTRAINT `FK76C693B288AA0AB9` FOREIGN KEY (`PARENT`) REFERENCES `t_newscomments` (`ID`),
  CONSTRAINT `FK76C693B2C3FF0F2A` FOREIGN KEY (`ID`) REFERENCES `t_newscomments` (`ID`),
  CONSTRAINT `FK76C693B2C4B175CE` FOREIGN KEY (`NEWS`) REFERENCES `t_news` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_newscomments` */

/*Table structure for table `t_notification` */

DROP TABLE IF EXISTS `t_notification`;

CREATE TABLE `t_notification` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(255) DEFAULT NULL,
  `CONTENT` text,
  `CREATETIME` datetime DEFAULT NULL,
  `MODIFYTIME` datetime DEFAULT NULL,
  `TYPE` varchar(255) DEFAULT NULL,
  `READTIMES` bigint(20) DEFAULT NULL,
  `ntfImage` varchar(255) DEFAULT NULL,
  `NITIFICATIONKEY` varchar(255) DEFAULT NULL,
  `CREATEUSER` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK88B836D6162CFABA` (`CREATEUSER`),
  CONSTRAINT `FK88B836D6162CFABA` FOREIGN KEY (`CREATEUSER`) REFERENCES `t_user` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_notification` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `REALNAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `LOGINNAME` varchar(255) DEFAULT NULL,
  `USERQQ` varchar(255) DEFAULT NULL,
  `PHONENUMBER` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `HEADIMAGE` varchar(255) DEFAULT NULL,
  `ACCESS` bit(1) DEFAULT NULL,
  `CONFIRM` bit(1) DEFAULT NULL,
  `USERTYPE` varchar(255) DEFAULT NULL,
  `DESCRIPTION` text,
  `REGISTERTIME` date DEFAULT NULL,
  `LASTLOGINTIME` date DEFAULT NULL,
  `LOCATION` varchar(255) DEFAULT NULL,
  `BIRTHLOCATION` varchar(255) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `PERSONALPAGE` varchar(255) DEFAULT NULL,
  `PERSONALTITLE` varchar(255) DEFAULT NULL,
  `PERSONALFAVORITE` varchar(255) DEFAULT NULL,
  `BBSCOUNT` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`ID`,`REALNAME`,`NICKNAME`,`PASSWORD`,`LOGINNAME`,`USERQQ`,`PHONENUMBER`,`EMAIL`,`HEADIMAGE`,`ACCESS`,`CONFIRM`,`USERTYPE`,`DESCRIPTION`,`REGISTERTIME`,`LASTLOGINTIME`,`LOCATION`,`BIRTHLOCATION`,`BIRTHDAY`,`GENDER`,`PERSONALPAGE`,`PERSONALTITLE`,`PERSONALFAVORITE`,`BBSCOUNT`) values (1,NULL,NULL,'4QrcOUm6Wau+VuBX8g+IPg==','admin',NULL,NULL,'admin@admin.com','commons/img/user.png','','\0','admin',NULL,'2015-05-15','2015-05-15',NULL,NULL,NULL,NULL,NULL,NULL,NULL,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
