-- MySQL dump 10.13  Distrib 8.0.39, for Win64 (x86_64)
--
-- Host: localhost    Database: pizzadb
-- ------------------------------------------------------
-- Server version	8.0.39

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `adress`
--

DROP TABLE IF EXISTS `adress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adress` (
  `adressId` int NOT NULL AUTO_INCREMENT,
  `postal` smallint DEFAULT NULL,
  `street` varchar(32) DEFAULT NULL,
  `number` int DEFAULT NULL,
  PRIMARY KEY (`adressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `courier`
--

DROP TABLE IF EXISTS `courier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `courier` (
  `courierID` int NOT NULL AUTO_INCREMENT,
  `postal` smallint DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  `timeLastDelivery` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`courierID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customerID` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(32) NOT NULL,
  `lastName` varchar(32) NOT NULL,
  `gender` char(1) DEFAULT NULL,
  `phone` bigint DEFAULT NULL,
  `adressID` int DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `email` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`customerID`),
  UNIQUE KEY `email` (`email`),
  KEY `adressID` (`adressID`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`adressID`) REFERENCES `adress` (`adressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `delivery` (
  `deliveryID` int NOT NULL AUTO_INCREMENT,
  `orderID` int DEFAULT NULL,
  `courierID` int DEFAULT NULL,
  PRIMARY KEY (`deliveryID`),
  KEY `orderID` (`orderID`),
  KEY `courierID` (`courierID`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`),
  CONSTRAINT `delivery_ibfk_2` FOREIGN KEY (`courierID`) REFERENCES `courier` (`courierID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `discountcode`
--

DROP TABLE IF EXISTS `discountcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discountcode` (
  `discountID` int NOT NULL AUTO_INCREMENT,
  `discount` int NOT NULL,
  `discountString` varchar(16) DEFAULT NULL,
  `isUsed` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ingredient`
--

DROP TABLE IF EXISTS `ingredient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredient` (
  `ingredientID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `dietary` varchar(16) DEFAULT NULL,
  `price` decimal(10,2) NOT NULL,
  PRIMARY KEY (`ingredientID`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `menuID` int DEFAULT NULL,
  `menuItemID` int DEFAULT NULL,
  KEY `menuItemID` (`menuItemID`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`menuItemID`) REFERENCES `menuitem` (`menuItemID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `menuitem`
--

DROP TABLE IF EXISTS `menuitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menuitem` (
  `menuItemID` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL,
  `type` varchar(8) NOT NULL,
  `description` text,
  PRIMARY KEY (`menuItemID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `customerID` int DEFAULT NULL,
  `orderTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `discountID` int DEFAULT NULL,
  `status` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `customerID` (`customerID`),
  KEY `discountID` (`discountID`),
  CONSTRAINT `fk_discount` FOREIGN KEY (`discountID`) REFERENCES `discountcode` (`discountID`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`discountID`) REFERENCES `discountcode` (`discountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `orderitem`
--

DROP TABLE IF EXISTS `orderitem`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orderitem` (
  `orderItemID` int NOT NULL AUTO_INCREMENT,
  `orderID` int DEFAULT NULL,
  `menuItemID` int DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`orderItemID`),
  KEY `menuItemID` (`menuItemID`),
  KEY `orderID` (`orderID`),
  CONSTRAINT `orderitem_ibfk_1` FOREIGN KEY (`menuItemID`) REFERENCES `menuitem` (`menuItemID`),
  CONSTRAINT `orderitem_ibfk_2` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recipe`
--

DROP TABLE IF EXISTS `recipe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recipe` (
  `menuItemID` int DEFAULT NULL,
  `ingredientID` int DEFAULT NULL,
  `amount` tinyint DEFAULT NULL,
  KEY `menuItemID` (`menuItemID`),
  KEY `ingredientID` (`ingredientID`),
  CONSTRAINT `recipe_ibfk_1` FOREIGN KEY (`menuItemID`) REFERENCES `menuitem` (`menuItemID`),
  CONSTRAINT `recipe_ibfk_2` FOREIGN KEY (`ingredientID`) REFERENCES `ingredient` (`ingredientID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-09 14:22:19
