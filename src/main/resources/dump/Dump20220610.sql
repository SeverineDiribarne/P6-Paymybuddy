CREATE DATABASE  IF NOT EXISTS `db-paymybuddy` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `db-paymybuddy`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: db-paymybuddy
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `login` varchar(200) NOT NULL,
  `password` varchar(64) NOT NULL,
  `customer_id` int NOT NULL,
  `enabled` tinyint NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_id_idx` (`customer_id`),
  CONSTRAINT `customer_id_fk` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,'aurelie.dupont@gmail.com','$2a$04$fAbLP.BsMBPShfDTuzgJguhFjY2fu7sIkrPu8QIbUsjKDYvE6zUKi',1,1),(2,'harry.martin@gmail.com','$2a$04$OAWSOgzYIttuXStk0yh1U.edq9nZF0mXLpFm7seSjPfWiVl2Gxi.a',2,1),(3,'marion.deltiny@gmail.com','$2a$04$AKpFEeAxJtyBPNeTgzQJieSliGBlocjU9O.dPG5QjUQE2VeuVBdii',3,1),(4,'marina.dupond@gmail.com','$2a$04$iCiu/jEG2X7hnwSyBjjYj.SVxuDZsztJ52E0WcEidybUQDaQrRl1e',4,1),(5,'olivier.herriberry@gmail.com','$2a$04$HYNUdSrEXB2m29i21FK3NOpryEQfDOHTuBb21cdhdezTi9eCTPyD2',5,1),(6,'pierre.albane@gmail.com','$2a$04$yYLLacHk7Kf2HQoIPVV7YOxKffcydbSXhX3AyypdTyNOhxIyHEGk.',6,1),(7,'severine.diribarne@gmail.com','$2a$04$jvbSkdM8ZOUrX5Ko9xOLS.mi.Q2WX26jVlhC/Knjj2UuqGbzek.eG',7,1),(16,'enzo.smith@gmail.com','$2a$10$/0Y.Z03g35juqtNWASIQkuwPTmX3eyDUCD6t/LBsSGGvavUaPeUt6',19,1),(17,'babou.bibi@gmail.com','$2a$04$VcZ2CSeHXNOWiSicKZxjtu8.PSteIF14Xgo652NP8VliY6VeQZJwe',20,1);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bank_operation`
--

DROP TABLE IF EXISTS `bank_operation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bank_operation` (
  `operationId` int NOT NULL AUTO_INCREMENT,
  `operationDate` date NOT NULL,
  `operationDescription` varchar(45) NOT NULL,
  `operationAmount` double DEFAULT NULL,
  `bank_accountId` int NOT NULL,
  PRIMARY KEY (`operationId`),
  KEY `bank_account_id_fk_idx` (`bank_accountId`),
  CONSTRAINT `bank_account_id_fk` FOREIGN KEY (`bank_accountId`) REFERENCES `bankaccount` (`bankAccount_id`)
) ENGINE=InnoDB AUTO_INCREMENT=81 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bank_operation`
--

LOCK TABLES `bank_operation` WRITE;
/*!40000 ALTER TABLE `bank_operation` DISABLE KEYS */;
INSERT INTO `bank_operation` VALUES (1,'2022-03-15','Payment from Bank to App',150,1),(2,'2022-03-24','Payment from Bank to App',300,3),(11,'2022-04-21','Payment from Bank to App',50,1),(12,'2022-04-21','Payment from Bank to App',75,1),(13,'2022-04-21','Payment from Bank to App',25,1),(14,'2022-04-21','Payment from Bank to App',40,1),(15,'2022-04-21','Payment from Bank to App',50,1),(16,'2022-04-21','Payment from Bank to App',100,1),(17,'2022-04-21','Payment from Bank to App',100,1),(18,'2022-04-21','Payment from Bank to App',75,1),(19,'2022-04-21','Payment from App to Bank',-50,1),(20,'2022-04-21','Payment from App to Bank',-50,1),(38,'2022-05-20','Payment from Bank to App',1,10),(41,'2022-05-20','Payment from Bank to App',1,11),(42,'2022-05-20','Payment from Bank to App',10,11),(44,'2022-05-20','Payment from Bank to App',100,10),(46,'2022-05-20','Payment from Bank to App',10,1),(47,'2022-05-20','Payment from Bank to App',250,11),(48,'2022-05-20','Payment from Bank to App',100,10),(49,'2022-05-20','Payment from App to Bank',-10,10),(50,'2022-05-22','Payment from Bank to App',10,11),(51,'2022-05-22','Payment from App to Bank',-10,11),(52,'2022-05-22','Payment from Bank to App',50,11),(53,'2022-06-02','Payment from App to Bank',-10,1),(54,'2022-06-02','Payment from Bank to App',10,1),(55,'2022-01-01','Payment from Bank to App',500,2),(56,'2022-01-01','Payment from Bank to App',300,4),(57,'2022-01-01','Payment from Bank to App',400,5),(58,'2022-01-01','Payment from Bank to App',420,6),(59,'2022-01-01','Payment from Bank to App',550,7),(60,'2022-06-03','Payment from Bank to App',5,1),(61,'2022-06-03','Payment from App to Bank',-5,1),(73,'2022-06-09','Payment from Bank to App',100,10),(74,'2022-06-10','Payment from Bank to App',50,4),(75,'2022-06-10','Payment from App to Bank',-100,2),(76,'2022-06-10','Payment from App to Bank',-100,3),(77,'2022-06-10','Payment from App to Bank',-75,5),(78,'2022-06-10','Payment from App to Bank',-75,6),(79,'2022-06-10','Payment from Bank to App',50,10),(80,'2022-06-10','Payment from Bank to App',30,11);
/*!40000 ALTER TABLE `bank_operation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bankaccount`
--

DROP TABLE IF EXISTS `bankaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bankaccount` (
  `bankAccount_id` int NOT NULL AUTO_INCREMENT,
  `bankAccountName` varchar(45) DEFAULT NULL,
  `iban` varchar(45) DEFAULT NULL,
  `bic` varchar(45) DEFAULT NULL,
  `swift` varchar(45) DEFAULT NULL,
  `customer_id` int NOT NULL,
  PRIMARY KEY (`bankAccount_id`),
  KEY `user_id_idx` (`customer_id`),
  CONSTRAINT `customer_id_fk2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bankaccount`
--

LOCK TABLES `bankaccount` WRITE;
/*!40000 ALTER TABLE `bankaccount` DISABLE KEYS */;
INSERT INTO `bankaccount` VALUES (1,'BNP','1234567899','FRBBPLML','456FRTZ',1),(2,'Credit Lyonnais ','6519327690','FRDACLDG','7539GOI',2),(3,'CIC','1068423912','FRASLMOP','598134A',3),(4,'Societe Generale','4068957623','FRPPLSMT','794625K',4),(5,'Credit Agricole','1228964378','FRMEYNSK','864FT91',5),(6,'Credit Lyonnais','5924817553','FRKPFBUI','Q864T23',6),(7,'Credit Agricole','7076351077','FRJKDSBV','Z771S29',7),(10,'CIC','1098426739','FRMVOXUQ','K682T53',19),(11,'BNP','1234567890','FRPOFRNC','G456D78',20);
/*!40000 ALTER TABLE `bankaccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `connection`
--

DROP TABLE IF EXISTS `connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `connection` (
  `connectionId` int NOT NULL AUTO_INCREMENT,
  `connectionSource` int NOT NULL,
  `connectionRecipient` int NOT NULL,
  PRIMARY KEY (`connectionId`),
  KEY `connexionSource_fk_idx` (`connectionSource`),
  KEY `connexionDestinataire_fk_idx` (`connectionRecipient`),
  CONSTRAINT `connectionRecipient_fk` FOREIGN KEY (`connectionRecipient`) REFERENCES `customer` (`id`),
  CONSTRAINT `connectionSource_fk` FOREIGN KEY (`connectionSource`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `connection`
--

LOCK TABLES `connection` WRITE;
/*!40000 ALTER TABLE `connection` DISABLE KEYS */;
INSERT INTO `connection` VALUES (1,1,3),(2,2,4),(3,2,5),(4,2,6),(5,3,1),(6,3,5),(7,4,2),(8,4,5),(9,4,6),(10,5,2),(11,5,3),(12,5,4),(13,5,6),(14,6,2),(15,6,4),(16,6,5),(20,1,19),(22,19,1),(24,19,20),(25,20,19),(28,20,6),(29,6,20);
/*!40000 ALTER TABLE `connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lastName` varchar(100) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `email` varchar(200) NOT NULL,
  `balance` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Dupont','Aurélie','aurelie.dupont@gmail.com',452.39000000000004),(2,'Martin','Harry','harry.martin@gmail.com',465.17499999999995),(3,'Deltiny','Marion','marion.deltiny@gmail.com',311.33504999999997),(4,'Dupond','Marina','marina.dupond@gmail.com',390.57750000000004),(5,'Herriberry','Olivier','olivier.herriberry@gmail.com',310.5),(6,'Albane','Pierre','pierre.albane@gmail.com',285.34499999999997),(7,'Diribarne','Severine','severine.diribarne@gmail.com',550),(19,'Smith','Enzo','enzo.smith@gmail.com',304.466),(20,'Bibi','Babou','babou.bibi@gmail.com',332.34255);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `transferId` int NOT NULL AUTO_INCREMENT,
  `transferDate` date DEFAULT NULL,
  `connection` int NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `amount` double DEFAULT NULL,
  `transfer_type` int NOT NULL,
  PRIMARY KEY (`transferId`),
  KEY `transfer_type_fk_idx` (`transfer_type`),
  KEY `connection_fk_idx` (`connection`),
  CONSTRAINT `connection_fk` FOREIGN KEY (`connection`) REFERENCES `connection` (`connectionId`),
  CONSTRAINT `transfer_type_fk` FOREIGN KEY (`transfer_type`) REFERENCES `transfer_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,'2021-12-16',1,'Ticket de cinéma',-7.5,2),(2,'2021-12-16',5,'Ticket de cinéma',7.5,1),(3,'2022-01-02',7,'Restaurant',-52.8,2),(4,'2022-01-02',2,'Restaurant',52.8,1),(5,'2022-01-04',16,'Boissons',-10.6,2),(6,'2022-01-04',13,'Boissons',10.6,1),(7,'2022-01-08',4,'Pizza',-10,2),(8,'2022-01-08',14,'Pizza',10,1),(9,'2022-01-10',9,'Boissons',-15,2),(10,'2022-01-10',15,'Boissons',15,1),(11,'2022-01-11',3,'Billard',-10.5,2),(12,'2022-01-11',10,'Billard',10.5,1),(13,'2022-01-16',14,'Canoé cayak',-38,2),(14,'2022-01-16',4,'Canoé cayak',38,1),(15,'2021-12-27',5,'Ticket de cinéma',-15.4,2),(16,'2021-12-27',1,'Ticket de cinéma',15.4,1),(17,'2022-01-09',11,'Billard',-20,2),(18,'2022-01-09',6,'Billard',20,1),(19,'2022-01-07',12,'Restaurant',-78.9,2),(20,'2022-01-07',8,'Restaurant',78.9,1),(21,'2022-01-11',1,'Glaces',-15,2),(22,'2022-01-11',5,'Glaces',15,1),(23,'2022-01-04',6,'Restaurant',-52,2),(24,'2022-01-04',11,'Restaurant',52,1),(25,'2022-01-14',16,'Escalade',-40,2),(26,'2022-01-14',13,'Escalade',40,1),(27,'2021-12-29',1,'patinoire',-25.5,2),(28,'2021-12-29',5,'patinoire',25.5,1),(29,'2022-02-25',1,'Boissons',-10,2),(30,'2022-02-25',5,'Boissons',10,1),(31,'2022-02-25',11,'Cadeau Livre',-12.99,2),(32,'2022-02-25',6,'Cadeau Livre',12.99,1),(33,'2022-02-26',1,'Restaurant',-36,2),(34,'2022-02-26',5,'Restaurant',36,1),(35,'2022-03-04',1,'Pizza',-24.3,2),(36,'2022-03-04',5,'Pizza',24.3,1),(42,'2022-04-05',1,'pizza',-12,2),(43,'2022-04-05',1,'pizza',-12,2),(44,'2022-04-04',1,'pizza',-9,2),(45,'2022-04-04',5,'pizza',9,1),(46,'2022-04-07',1,'Boisson',-7,2),(47,'2022-04-07',5,'Boisson',7,1),(48,'2022-04-24',1,'patinoire',-20,2),(49,'2022-04-24',5,'patinoire',20,1),(50,'2022-05-20',20,'pizza',-10,2),(51,'2022-05-20',22,'pizza',10,1),(59,'2022-05-22',20,'glace',-5,2),(60,'2022-05-22',22,'glace',5,1),(61,'2022-05-23',24,'restaurant',-25,2),(62,'2022-05-23',25,'restaurant',25,1),(105,'2022-06-03',1,'glace',-4.5,2),(106,'2022-06-03',5,'glace',4.5,1),(121,'2022-06-08',25,'glace',-5.5,2),(122,'2022-06-08',24,'glace',5.5,1),(123,'2022-06-09',28,'snack',-12.99,2),(124,'2022-06-09',29,'snack',12.99,1),(125,'2022-06-08',8,'glace',-4.5,2),(126,'2022-06-08',12,'glace',4.5,1),(127,'2022-06-08',2,'boisson',-3.5,2),(128,'2022-06-08',7,'boisson',3.5,1),(129,'2022-06-09',2,'patinoire',-21.5,2),(130,'2022-06-09',7,'patinoire',21.5,1),(131,'2022-06-08',5,'snack',-12.99,2),(132,'2022-06-08',1,'snack',12.99,1),(133,'2022-06-09',10,'billard',-20,2),(134,'2022-06-09',3,'billard',20,1),(135,'2022-06-10',15,'snack',-9,2),(136,'2022-06-10',9,'snack',9,1),(137,'2022-06-09',22,'restaurant',-46.8,2),(138,'2022-06-09',20,'restaurant',46.8,1),(139,'2022-06-10',25,'Kart',-15,2),(140,'2022-06-10',24,'Kart',15,1);
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer_type`
--

DROP TABLE IF EXISTS `transfer_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer_type` (
  `id` int NOT NULL,
  `type` tinyint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer_type`
--

LOCK TABLES `transfer_type` WRITE;
/*!40000 ALTER TABLE `transfer_type` DISABLE KEYS */;
INSERT INTO `transfer_type` VALUES (1,1),(2,0);
/*!40000 ALTER TABLE `transfer_type` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-10  0:51:00
