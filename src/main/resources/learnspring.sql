-- MySQL dump 10.13  Distrib 5.7.17, for Linux (x86_64)
--
-- Host: localhost    Database: learnspring
-- ------------------------------------------------------
-- Server version	5.7.17-0ubuntu0.16.04.1

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
-- Current Database: `learnspring`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `learnspring` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `learnspring`;

--
-- Table structure for table `UserDetails`
--

DROP TABLE IF EXISTS `UserDetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserDetails` (
  `id` varchar(255) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstName` varchar(255) DEFAULT NULL,
  `lastName` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8ja5h2e3upra11pcu97lkq39u` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserDetails`
--

LOCK TABLES `UserDetails` WRITE;
/*!40000 ALTER TABLE `UserDetails` DISABLE KEYS */;
INSERT INTO `UserDetails` VALUES ('a39db9de-5490-499e-afcd-db5251d2cdeb','sample@gmail.com','Sample','Name','$2a$10$NEKyQG5KNm8C1mI/WKjJjOf2U.MRuYtiPnORSQbZ1EA7M847OVSVy'),('d92796c4-0c20-473e-98ba-95222dbf1a1d','prateeknischal@gmail.com','Prateek','Nischal','$2a$10$71/xwtLCqV.9KoU..CejqujvreU0dydrsOGRrg2y6BnxGlfyTRcne');
/*!40000 ALTER TABLE `UserDetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (16);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` varchar(255) NOT NULL,
  `fromId` varchar(255) NOT NULL,
  `toId` varchar(255) NOT NULL,
  `songId` varchar(255) NOT NULL,
  `ts` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `fromId` (`fromId`),
  KEY `toId` (`toId`),
  KEY `songId` (`songId`),
  CONSTRAINT `message_ibfk_1` FOREIGN KEY (`fromId`) REFERENCES `UserDetails` (`id`),
  CONSTRAINT `message_ibfk_2` FOREIGN KEY (`toId`) REFERENCES `UserDetails` (`id`),
  CONSTRAINT `message_ibfk_3` FOREIGN KEY (`songId`) REFERENCES `song` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES ('2e31f7c7-55e5-41da-be64-118d9f5b8490','d92796c4-0c20-473e-98ba-95222dbf1a1d','a39db9de-5490-499e-afcd-db5251d2cdeb','cebf7b64-7773-4652-954b-97d8bbd237ca','2017-03-18 15:33:15'),('5005c476-8378-43bd-a267-182ff05f8bd1','d92796c4-0c20-473e-98ba-95222dbf1a1d','d92796c4-0c20-473e-98ba-95222dbf1a1d','6de455de-dcf0-42fc-a011-31ae8486d6d4','2017-03-18 16:53:49');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` int(11) NOT NULL,
  `songId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `rating` float(2,1) DEFAULT '0.0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `songId_userId` (`songId`,`userId`),
  KEY `userId` (`userId`),
  CONSTRAINT `rating_ibfk_1` FOREIGN KEY (`songId`) REFERENCES `song` (`id`),
  CONSTRAINT `rating_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `UserDetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rating`
--

LOCK TABLES `rating` WRITE;
/*!40000 ALTER TABLE `rating` DISABLE KEYS */;
INSERT INTO `rating` VALUES (11,'6de455de-dcf0-42fc-a011-31ae8486d6d4','d92796c4-0c20-473e-98ba-95222dbf1a1d',4.0),(12,'33ba1868-c9ca-4100-a519-86395a0dbfeb','d92796c4-0c20-473e-98ba-95222dbf1a1d',4.0),(15,'cebf7b64-7773-4652-954b-97d8bbd237ca','a39db9de-5490-499e-afcd-db5251d2cdeb',4.5);
/*!40000 ALTER TABLE `rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `song`
--

DROP TABLE IF EXISTS `song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `song` (
  `id` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `link` varchar(1023) NOT NULL,
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_link` (`link`),
  KEY `song_ibfk_1` (`userId`),
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `UserDetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES ('33ba1868-c9ca-4100-a519-86395a0dbfeb','Bittersweet','https://www.youtube.com/watch?v=YysA79k4gfY','d92796c4-0c20-473e-98ba-95222dbf1a1d'),('6de455de-dcf0-42fc-a011-31ae8486d6d4','Memtrix - All you are','https://soundcloud.com/mrsuicidesheep/memtrix-all-you-are','d92796c4-0c20-473e-98ba-95222dbf1a1d'),('cebf7b64-7773-4652-954b-97d8bbd237ca','Here without you - Gon Haziri','https://soundcloud.com/sulfyderz/3-doors-down-here-without-you-sonik-gon-haziri-remix-boyce-avenue-cover','d92796c4-0c20-473e-98ba-95222dbf1a1d');
/*!40000 ALTER TABLE `song` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-19 22:39:10
