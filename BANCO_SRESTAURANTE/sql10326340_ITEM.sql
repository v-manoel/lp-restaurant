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
-- Table structure for table `ITEM`
--

DROP TABLE IF EXISTS `ITEM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEM` (
  `id_item` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `preco` double NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `fornecedor` varchar(18) DEFAULT NULL,
  `tp_item` tinyint(4) NOT NULL,
  PRIMARY KEY (`id_item`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEM`
--

LOCK TABLES `ITEM` WRITE;
/*!40000 ALTER TABLE `ITEM` DISABLE KEYS */;
INSERT INTO `ITEM` VALUES (1,'Coca cola',3.59,NULL,'12.312.322/1321-32',1),(2,'miojo',1.59,'miojo, ovo',NULL,2),(3,'BATATA RUFFLES ORIGINAL',7.99,'Batata Ruffles Sabor Original',NULL,2),(4,'FEIJAO TROPEIRO COM CHURRASCO',22.99,'Feijão, arroz, churrasco de carne, frango e linguiça',NULL,2),(5,'LOMBO',79.99,'Lombo de Porco',NULL,2),(6,'BISTECA SUÍNA',7.99,'Bisteca de Porco',NULL,2),(7,'NHOQUE',17.99,'1 dente de alho finamente picado, um punhado de salsa e cebolinha',NULL,2),(8,'PETITINGA',22.59,'petitinga pescada na hora, farinha de trigo',NULL,2),(9,'CERVEJA SCHIN LITRÃO',5.99,NULL,'97.104.031/0001-24',1),(10,'SCHIN 600ML',4.99,NULL,'97.104.031/0001-24',1),(11,'CERVEJA ITAIPAVA 600ML',7.99,NULL,'63.719.229/0001-09',1);
/*!40000 ALTER TABLE `ITEM` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-11  2:30:22
