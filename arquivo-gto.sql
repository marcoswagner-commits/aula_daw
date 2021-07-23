-- MySQL dump 10.13  Distrib 8.0.11, for macos10.13 (x86_64)
--
-- Host: localhost    Database: gestao_obra
-- ------------------------------------------------------
-- Server version	8.0.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `itens`
--

DROP TABLE IF EXISTS `itens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `itens` (
  `codigo_item` int(11) NOT NULL AUTO_INCREMENT,
  `complemento_item` varchar(255) DEFAULT NULL,
  `descricao_item` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo_item`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itens`
--

LOCK TABLES `itens` WRITE;
/*!40000 ALTER TABLE `itens` DISABLE KEYS */;
INSERT INTO `itens` VALUES (1,'','Material Básico'),(2,'','Material de Acabamento'),(3,'','Material Pintura'),(4,'','Mão-de-Obra'),(5,'','Locações');
/*!40000 ALTER TABLE `itens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lancamentos`
--

DROP TABLE IF EXISTS `lancamentos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `lancamentos` (
  `codigo_lanc` int(11) NOT NULL AUTO_INCREMENT,
  `descricao_lanc` varchar(255) NOT NULL,
  `documento_lanc` varchar(255) DEFAULT NULL,
  `observacoes_lanc` varchar(255) DEFAULT NULL,
  `valor_lanc` double DEFAULT NULL,
  `item_codigo_item` int(11) DEFAULT NULL,
  `obra_codigo_obra` int(11) DEFAULT NULL,
  `subitem_codigo_subitem` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_lanc`),
  KEY `FKm93uuawklim827uqkx8kkwsrs` (`item_codigo_item`),
  KEY `FK19vcpakbq3i657m27gdwh9l06` (`obra_codigo_obra`),
  KEY `FK2f8fw1reylxudvxsrr6xvs6v8` (`subitem_codigo_subitem`),
  CONSTRAINT `FK19vcpakbq3i657m27gdwh9l06` FOREIGN KEY (`obra_codigo_obra`) REFERENCES `obras` (`codigo_obra`),
  CONSTRAINT `FK2f8fw1reylxudvxsrr6xvs6v8` FOREIGN KEY (`subitem_codigo_subitem`) REFERENCES `subitens` (`codigo_subitem`),
  CONSTRAINT `FKm93uuawklim827uqkx8kkwsrs` FOREIGN KEY (`item_codigo_item`) REFERENCES `itens` (`codigo_item`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lancamentos`
--

LOCK TABLES `lancamentos` WRITE;
/*!40000 ALTER TABLE `lancamentos` DISABLE KEYS */;
INSERT INTO `lancamentos` VALUES (1,'Parte Elétrica','','',150.5,1,1,1),(2,'Contra-Piso','','',250,1,1,2),(3,'Piso','','',280,2,1,4),(4,'Piso','','',290,2,2,5),(5,'Contra-Piso','','',350,3,2,6),(6,'Contra-Piso','','',150,4,3,8),(7,'Contra-Piso','','',50,4,3,8),(8,'Contra-Piso','','',1250,3,4,7),(9,'Contra-Piso','','',2500,4,4,2),(10,'Contra-Piso','','',25,4,4,8);
/*!40000 ALTER TABLE `lancamentos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obras`
--

DROP TABLE IF EXISTS `obras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `obras` (
  `codigo_obra` int(11) NOT NULL AUTO_INCREMENT,
  `complemento_obra` varchar(255) DEFAULT NULL,
  `descricao_obra` varchar(255) NOT NULL,
  `localizacao_obra` varchar(255) NOT NULL,
  `proprietario_codigo_prop` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_obra`),
  KEY `FKetgahjoaht0ev14i53peu2vb` (`proprietario_codigo_prop`),
  CONSTRAINT `FKetgahjoaht0ev14i53peu2vb` FOREIGN KEY (`proprietario_codigo_prop`) REFERENCES `proprietarios` (`codigo_prop`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obras`
--

LOCK TABLES `obras` WRITE;
/*!40000 ALTER TABLE `obras` DISABLE KEYS */;
INSERT INTO `obras` VALUES (1,'','Sobrado com 4 suites','Rua Dona Olimpia, 1414, Vila Fátima',1),(2,'','Casa geminada','Rua 15, 1515, Setor Hermosa',2),(3,'','Casa com 3 quartos','Rua 16, 1616, Setor Brisas',3),(4,'','Casa de Alto Padrão','Rua 17, 1717, 1414, Setor Cohacol',3);
/*!40000 ALTER TABLE `obras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permissions`
--

DROP TABLE IF EXISTS `permissions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `permissions` (
  `id_permission` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_permission`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permissions`
--

LOCK TABLES `permissions` WRITE;
/*!40000 ALTER TABLE `permissions` DISABLE KEYS */;
INSERT INTO `permissions` VALUES (1,'ADMIN'),(2,'USUARIO'),(3,'ADMIN'),(4,'USUARIO'),(5,'ADMIN'),(6,'USUARIO');
/*!40000 ALTER TABLE `permissions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proprietarios`
--

DROP TABLE IF EXISTS `proprietarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `proprietarios` (
  `codigo_prop` int(11) NOT NULL AUTO_INCREMENT,
  `cpf_prop` varchar(255) NOT NULL,
  `email_prop` varchar(255) NOT NULL,
  `nome_prop` varchar(255) NOT NULL,
  PRIMARY KEY (`codigo_prop`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proprietarios`
--

LOCK TABLES `proprietarios` WRITE;
/*!40000 ALTER TABLE `proprietarios` DISABLE KEYS */;
INSERT INTO `proprietarios` VALUES (1,'123','marcoswagner@gmail.com','Marcos'),(2,'456','jose@gto','Jose'),(3,'789','maria@gto','Maria');
/*!40000 ALTER TABLE `proprietarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subitens`
--

DROP TABLE IF EXISTS `subitens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `subitens` (
  `codigo_subitem` int(11) NOT NULL AUTO_INCREMENT,
  `complemento_subitem` varchar(255) DEFAULT NULL,
  `descricao_subitem` varchar(255) NOT NULL,
  `item_codigo_item` int(11) DEFAULT NULL,
  PRIMARY KEY (`codigo_subitem`),
  KEY `FKjr6lp2isle7cmy8w434f5d31f` (`item_codigo_item`),
  CONSTRAINT `FKjr6lp2isle7cmy8w434f5d31f` FOREIGN KEY (`item_codigo_item`) REFERENCES `itens` (`codigo_item`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subitens`
--

LOCK TABLES `subitens` WRITE;
/*!40000 ALTER TABLE `subitens` DISABLE KEYS */;
INSERT INTO `subitens` VALUES (1,'','Fio Elétrico',1),(2,'','Cimento',1),(3,'','Tijolo',1),(4,'','Argamassa',2),(5,'','Porcelanato',2),(6,'','Massa Corrida',3),(7,'','Tinta',3),(8,'','Pedreiro',4);
/*!40000 ALTER TABLE `subitens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_permission`
--

DROP TABLE IF EXISTS `user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `user_permission` (
  `id_user` int(11) NOT NULL,
  `id_permission` int(11) NOT NULL,
  KEY `FKipug9u6xcdgam1h707x2i1cr3` (`id_permission`),
  KEY `FKprpp02ivhe66b5nrc0a3a4lk8` (`id_user`),
  CONSTRAINT `FKipug9u6xcdgam1h707x2i1cr3` FOREIGN KEY (`id_permission`) REFERENCES `permissions` (`id_permission`),
  CONSTRAINT `FKprpp02ivhe66b5nrc0a3a4lk8` FOREIGN KEY (`id_user`) REFERENCES `users` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_permission`
--

LOCK TABLES `user_permission` WRITE;
/*!40000 ALTER TABLE `user_permission` DISABLE KEYS */;
INSERT INTO `user_permission` VALUES (1,1),(1,2),(2,2);
/*!40000 ALTER TABLE `user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8 ;
CREATE TABLE `users` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `account_non_expired` bit(1) DEFAULT NULL,
  `account_non_locked` bit(1) DEFAULT NULL,
  `credentials_non_expired` bit(1) DEFAULT NULL,
  `enabled` bit(1) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'','','','','Marcos Wagner','$2a$16$O.DnTC/fVNJA8yTH7.oKHuB9XCH91qaFAllbNT/UhUnRftmk4qlVC','marcos_admin'),(2,'','','','','Marcos Ribeiro','$2a$16$O/S4x2NSTwcElnn2DePShOEBqSjyC68a8lmNHJ3PhNVm.hicQ2r6u','marcos_user');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-07-22 23:17:25
