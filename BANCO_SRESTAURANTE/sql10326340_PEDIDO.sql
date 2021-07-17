-- MySQL dump 10.13  Distrib 5.7.29, for Linux (x86_64)
--
-- Host: sql10.freemysqlhosting.net    Database: sql10326340
-- ------------------------------------------------------
-- Server version	5.5.58-0ubuntu0.14.04.1

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
-- Table structure for table `PEDIDO`
--

DROP TABLE IF EXISTS `PEDIDO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PEDIDO` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_conta` int(11) NOT NULL,
  `valor` float NOT NULL,
  `status` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_PEDIDO_idx` (`id_conta`),
  CONSTRAINT `fk_PEDIDO` FOREIGN KEY (`id_conta`) REFERENCES `CONTA` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PEDIDO`
--

LOCK TABLES `PEDIDO` WRITE;
/*!40000 ALTER TABLE `PEDIDO` DISABLE KEYS */;
INSERT INTO `PEDIDO` VALUES (1,1,56.55,'Closed'),(2,5,63.57,'Closed'),(3,6,22.56,'Closed'),(4,18,45.55,'Closed'),(5,19,137.54,'Closed'),(6,20,39.56,'Closed'),(7,23,36.57,'Closed'),(8,24,107.97,'Opened'),(9,25,95.97,'Opened'),(10,25,68.12,'Closed'),(11,26,175.68,'Opened'),(12,26,96.35,'Closed'),(13,27,329.94,'Closed'),(14,28,70.48,'Closed'),(15,29,220.14,'Closed'),(16,30,7.99,'Closed'),(17,30,79.99,'Closed'),(19,31,1.59,'Closed'),(21,35,81.58,'Closed'),(22,36,28.72,'Closed'),(23,37,34.74,'Closed'),(24,40,8.58,'Closed'),(25,42,63.92,'Closed'),(26,43,5.99,'Closed'),(28,54,22.99,'Opened'),(29,57,28.96,'Opened'),(31,625,214.09,'Closed'),(33,626,21.97,'Closed'),(35,628,43.94,'Closed'),(38,629,97.98,'Closed'),(39,630,79.99,'Closed');
/*!40000 ALTER TABLE `PEDIDO` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-11  2:30:26
