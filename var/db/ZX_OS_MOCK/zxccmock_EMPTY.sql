-- MySQL dump 10.13  Distrib 5.5.38, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: zxccmock
-- ------------------------------------------------------
-- Server version	5.5.38-0ubuntu0.14.04.1

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
-- Table structure for table `email_action_log`
--

DROP TABLE IF EXISTS `email_action_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `email_action_log` (
  `action_id` int(11) DEFAULT NULL,
  `to_address` varchar(20) DEFAULT NULL,
  `action_completed` binary(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email_action_log`
--

LOCK TABLES `email_action_log` WRITE;
/*!40000 ALTER TABLE `email_action_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `email_action_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resp_data`
--

DROP TABLE IF EXISTS `resp_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resp_data` (
  `resp_id` int(11) DEFAULT NULL,
  `resp_email` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resp_data`
--

LOCK TABLES `resp_data` WRITE;
/*!40000 ALTER TABLE `resp_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `resp_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `time_const`
--

DROP TABLE IF EXISTS `time_const`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `time_const` (
  `work_const_id` int(11) DEFAULT NULL,
  `const_value` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `time_const`
--

LOCK TABLES `time_const` WRITE;
/*!40000 ALTER TABLE `time_const` DISABLE KEYS */;
/*!40000 ALTER TABLE `time_const` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_const`
--

DROP TABLE IF EXISTS `work_const`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_const` (
  `work_const_id` int(11) DEFAULT NULL,
  `work_const_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_const`
--

LOCK TABLES `work_const` WRITE;
/*!40000 ALTER TABLE `work_const` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_const` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_data`
--

DROP TABLE IF EXISTS `work_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_data` (
  `work_id` int(11) DEFAULT NULL,
  `work_title` varchar(20) DEFAULT NULL,
  `resp_id` int(11) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `work_const_id` int(11) DEFAULT NULL,
  `next_work_id` int(11) DEFAULT NULL,
  `work_url` varchar(50) DEFAULT NULL,
  `work_status_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_data`
--

LOCK TABLES `work_data` WRITE;
/*!40000 ALTER TABLE `work_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_leaf`
--

DROP TABLE IF EXISTS `work_leaf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_leaf` (
  `work_parent_id` int(11) DEFAULT NULL,
  `work_leaf_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_leaf`
--

LOCK TABLES `work_leaf` WRITE;
/*!40000 ALTER TABLE `work_leaf` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_leaf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_status`
--

DROP TABLE IF EXISTS `work_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `work_status` (
  `status_id` int(11) DEFAULT NULL,
  `status_name` varchar(20) DEFAULT NULL,
  `status_alert_type` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_status`
--

LOCK TABLES `work_status` WRITE;
/*!40000 ALTER TABLE `work_status` DISABLE KEYS */;
/*!40000 ALTER TABLE `work_status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-08-04 17:55:31
