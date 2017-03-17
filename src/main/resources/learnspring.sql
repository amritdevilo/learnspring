-- MySQL dump 10.13  Distrib 5.7.14, for osx10.11 (x86_64)
--
-- Host: localhost    Database: learnspring
-- ------------------------------------------------------
-- Server version	5.7.14

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

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `learnspring` /*!40100 DEFAULT CHARACTER SET utf8 */;

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
INSERT INTO `hibernate_sequence` VALUES (1);
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
INSERT INTO `message` VALUES ('06b79b2a-86a4-4eae-b938-7c154b37784c','a39db9de-5490-499e-afcd-db5251d2cdeb','d92796c4-0c20-473e-98ba-95222dbf1a1d','933c40ed-c121-4cb7-951c-21253b9acea0','2017-03-16 09:12:50'),('28c904ea-80cd-4540-b6b7-f3baa30e2aa3','a39db9de-5490-499e-afcd-db5251d2cdeb','d92796c4-0c20-473e-98ba-95222dbf1a1d','90b941e0-8190-4fb7-84b7-37cfd8d2f5b1','2017-03-16 09:12:44'),('dd172e92-3bf4-4cc0-a4c9-a37e0abc6e5c','a39db9de-5490-499e-afcd-db5251d2cdeb','d92796c4-0c20-473e-98ba-95222dbf1a1d','f754953f-0426-4e95-a509-794d16e4aeb7','2017-03-16 09:12:54');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rating`
--

DROP TABLE IF EXISTS `rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rating` (
  `id` varchar(255) NOT NULL,
  `songId` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  `rating` float(1,1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `songId` (`songId`),
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
  `resource` varchar(255) NOT NULL,
  `userId` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `song_userid` (`link`,`userId`),
  KEY `song_ibfk_1` (`userId`),
  CONSTRAINT `song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `UserDetails` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `song`
--

LOCK TABLES `song` WRITE;
/*!40000 ALTER TABLE `song` DISABLE KEYS */;
INSERT INTO `song` VALUES ('2e98ebdf-a037-4761-a740-3956add7a6a7','are you sure','https://www.youtube.com/watch?v=AWTREwT-GQI','','d92796c4-0c20-473e-98ba-95222dbf1a1d'),('485e1d89-528c-4f48-a5cc-da7d5ef425f0','Mercury - Tere Bina','https://www.reverbnation.com/mercurybadsomeotherday/song/13614812-tere-bina','','d92796c4-0c20-473e-98ba-95222dbf1a1d'),('90b941e0-8190-4fb7-84b7-37cfd8d2f5b1','Mercury - Tere Bina','https://www.reverbnation.com/mercurybadsomeotherday/song/13614812-tere-bina','','a39db9de-5490-499e-afcd-db5251d2cdeb'),('933c40ed-c121-4cb7-951c-21253b9acea0','are you sure','https://www.youtube.com/watch?v=AWTREwT-GQI','youtube','a39db9de-5490-499e-afcd-db5251d2cdeb'),('9e0956bd-e7af-4c2b-8d1e-03ee42223bb9','GTA red lips','https://www.youtube.com/watch?v=qI1ondD4FGU','','d92796c4-0c20-473e-98ba-95222dbf1a1d'),('f754953f-0426-4e95-a509-794d16e4aeb7','GTA red lips','https://www.youtube.com/watch?v=qI1ondD4FGU','youtube','a39db9de-5490-499e-afcd-db5251d2cdeb');
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

-- Dump completed on 2017-03-17 22:06:49
