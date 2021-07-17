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
-- Table structure for table `CLIENTE`
--

DROP TABLE IF EXISTS `CLIENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CLIENTE` (
  `cpf` varchar(14) NOT NULL,
  `nome` varchar(60) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CLIENTE`
--

LOCK TABLES `CLIENTE` WRITE;
/*!40000 ALTER TABLE `CLIENTE` DISABLE KEYS */;
INSERT INTO `CLIENTE` VALUES ('084.830.885-92','Filipe Silva de Jesus','filipe25322@gmail.com','5f4dcc3b5aa765d61d8327deb882cf99'),('111.111.111-11','Elenilson','zeref@gmail.com','25d55ad283aa400af464c76d713c07ad'),('123.123.123-12','Vitor Pereira','vitororeide2009@gmail.com','3eb535e74a0c3a6591ed6953c8642bfd'),('123.452.345-61','Thalita Reis','thalitareis@gmail.com','08d432f2445f4c4b07dadaa6b1adc3d3'),('123.456.789-10','Geraldino','ternurinha@uol.com','25d55ad283aa400af464c76d713c07ad'),('171.717.317-37','Lucas da Silva','lucas@paripe','dc53fc4f621c80bdc2fa0329a6123708'),('222.222.222-22','Antonielson','antoniol.elson@gmail.com','e10adc3949ba59abbe56e057f20f883e'),('312.312.312-31','Reinilson Bispo','neghoguitar@gmail.com','95bbfa2d045153cf2d21522b15e5e198'),('414.811.590-38','Cristiano Ronaldo','cris@juventus.com.br','2acd5147e6a1d433927d4fd73833ab55'),('434.343.434-34','Lucas da Silva de Jesus','llukita@paripe','97727503e5c289c47a5833409f06b656'),('847.324.394-93','natanael','natanael@fazendacoutos','5f4dcc3b5aa765d61d8327deb882cf99'),('999.999.999-99','Abadias Neto','netabadias@uol.com','e10adc3949ba59abbe56e057f20f883e');
/*!40000 ALTER TABLE `CLIENTE` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-11  2:30:27
