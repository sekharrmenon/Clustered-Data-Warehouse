CREATE DATABASE  IF NOT EXISTS `datawarehouse` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `datawarehouse`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: datawarehouse
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `currency_count`
--

DROP TABLE IF EXISTS `currency_count`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency_count` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `from_currency` varchar(100) DEFAULT NULL,
  `count` bigint(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `improper_deal`
--

DROP TABLE IF EXISTS `improper_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `improper_deal` (
  `deal_unique_id` varchar(30) NOT NULL,
  `from_currency` varchar(100) DEFAULT NULL,
  `to_currency` varchar(100) DEFAULT NULL,
  `deal_amount` double DEFAULT '0',
  `date` datetime DEFAULT NULL,
  `file_name` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`deal_unique_id`),
  UNIQUE KEY `deal_unique_id_UNIQUE` (`deal_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proper_deal`
--

DROP TABLE IF EXISTS `proper_deal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proper_deal` (
  `deal_unique_id` varchar(30) NOT NULL,
  `from_currency` varchar(100) DEFAULT NULL,
  `to_currency` varchar(100) DEFAULT NULL,
  `file_name` varchar(300) DEFAULT NULL,
  `deal_amount` double DEFAULT '0',
  `date` datetime DEFAULT NULL,
  PRIMARY KEY (`deal_unique_id`),
  UNIQUE KEY `deal_unique_id_UNIQUE` (`deal_unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-26 16:00:18
