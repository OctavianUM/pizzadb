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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adress`
--

LOCK TABLES `adress` WRITE;
/*!40000 ALTER TABLE `adress` DISABLE KEYS */;
INSERT INTO `adress` VALUES (1,1001,'Maple Street',12),(2,1001,'Oak Avenue',34),(3,1001,'Maple Street',29),(4,1001,'Pine Road',56),(5,1002,'Elm Boulevard',78),(6,1002,'Elm Boulevard',69),(7,1002,'Birch Lane',23),(8,1002,'Cedar Court',45),(9,1002,'Spruce Drive',7),(10,1002,'Spruce Drive',26),(21,1003,'Paul-Henri Spaaklaan',1);
/*!40000 ALTER TABLE `adress` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `courier`
--

LOCK TABLES `courier` WRITE;
/*!40000 ALTER TABLE `courier` DISABLE KEYS */;
INSERT INTO `courier` VALUES (1,1002,'AVAILABLE',NULL),(2,1003,'AVAILABLE',NULL),(3,1001,'AVAILABLE',NULL),(4,1002,'AVAILABLE',NULL);
/*!40000 ALTER TABLE `courier` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'John','Doe','M',12345678,1,'1970-01-01','johndoe@example.com','c2713b62c903791bdefc5a6a99df04d4330de491bbc7a0ca6a5007337e4a6028'),(3,'jesse','hoydonckx','\0',123456789,1,'2005-05-01','email@example.com','5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8'),(4,'max','mad','\0',987654321,1,'2005-05-01','madmx','148de9c5a7a44d19e56cd9ae1a554bf67847afb0c58f6e12fa29ac7ddfca9940');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `discountcode`
--

DROP TABLE IF EXISTS `discountcode`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `discountcode` (
  `discountID` int NOT NULL AUTO_INCREMENT,
  `discount` int NOT NULL,
  PRIMARY KEY (`discountID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `discountcode`
--

LOCK TABLES `discountcode` WRITE;
/*!40000 ALTER TABLE `discountcode` DISABLE KEYS */;
INSERT INTO `discountcode` VALUES (1,10);
/*!40000 ALTER TABLE `discountcode` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `ingredient`
--

LOCK TABLES `ingredient` WRITE;
/*!40000 ALTER TABLE `ingredient` DISABLE KEYS */;
INSERT INTO `ingredient` VALUES (1,'Pizza Dough','Vegetarian',1.50),(2,'Tomato Sauce','Vegetarian',1.00),(3,'Mozzarella Cheese','Vegetarian',3.00),(4,'Parmesan Cheese','Vegetarian',1.50),(5,'Pepperoni','Non-Vegetarian',1.25),(6,'Mushrooms','Vegetarian',0.60),(7,'Onions','Vegetarian',0.18),(8,'Bell Peppers','Vegetarian',0.50),(9,'Olives','Vegetarian',0.70),(10,'Sausage','Non-Vegetarian',1.00),(11,'Ham','Non-Vegetarian',1.00),(12,'Bacon','Non-Vegetarian',1.50),(13,'Spinach','Vegetarian',0.40),(14,'Basil','Vegetarian',0.08),(15,'Oregano','Vegetarian',0.02),(16,'Garlic','Vegetarian',0.03),(17,'Pineapple','Vegetarian',0.50),(18,'Anchovies','Non-Vegetarian',1.50),(19,'Ground Beef','Non-Vegetarian',1.25),(20,'Red Pepper Flakes','Vegetarian',0.02),(21,'Coca-Cola Fluid','Vegetarian',1.00),(22,'Sprite Fluid','Vegetarian',1.00),(23,'Fanta Fluid','Vegetarian',1.00),(24,'Dr Pepper Fluid','Vegetarian',1.00),(25,'Mountain Dew Fluid','Vegetarian',1.00),(26,'Flour','Vegetarian',0.50),(27,'Sugar','Vegetarian',0.20),(28,'Cocoa Powder','Vegetarian',0.75),(29,'Cream Cheese','Vegetarian',2.00),(30,'Graham Crackers','Vegetarian',1.00),(31,'Eggs','Vegetarian',0.15),(32,'Butter','Vegetarian',0.80),(33,'Coffee','Vegetarian',0.30),(34,'Apples','Vegetarian',0.60),(35,'Cinnamon','Vegetarian',0.10);
/*!40000 ALTER TABLE `ingredient` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (0,1),(0,2),(0,3),(0,4),(0,5),(0,6),(0,7),(0,8),(0,9),(0,10),(0,11),(0,12),(0,13),(0,14),(0,15),(0,16),(0,17),(0,18),(0,19),(0,20),(0,21);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `menuitem`
--

LOCK TABLES `menuitem` WRITE;
/*!40000 ALTER TABLE `menuitem` DISABLE KEYS */;
INSERT INTO `menuitem` VALUES (1,'Margherita Pizza','pizza','Classic cheese and tomato.'),(2,'Pepperoni Pizza','pizza','Spicy pepperoni slices.'),(3,'Vegetarian Pizza','pizza','Loaded with fresh veggies.'),(4,'Meat Lovers Pizza','pizza','All your favorite meats.'),(5,'Hawaiian Pizza','pizza','Ham and pineapple.'),(6,'BBQ Chicken Pizza','pizza','BBQ sauce with chicken.'),(7,'Four Cheese Pizza','pizza','Blend of four cheeses.'),(8,'Spinach and Feta Pizza','pizza','Spinach with feta cheese.'),(9,'Mediterranean Pizza','pizza','Olives and feta cheese.'),(10,'Buffalo Chicken Pizza','pizza','Spicy chicken with sauce.'),(11,'Anchovy Pizza','pizza','Topped with anchovies.'),(12,'Pepperoni and Mushroom Pizza','pizza','Pepperoni with mushrooms.'),(13,'Coca-Cola','drink','Classic cola flavor.'),(14,'Sprite','drink','Refreshing lemon-lime flavor.'),(15,'Fanta','drink','Sweet orange soda.'),(16,'Dr Pepper','drink','Unique blend of 23 flavors.'),(17,'Mountain Dew','drink','Citrus-flavored soda with a kick.'),(18,'Chocolate Cake','dessert','Rich and moist chocolate cake.'),(19,'Cheesecake','dessert','Creamy cheesecake with a graham cracker crust.'),(20,'Tiramisu','dessert','Classic Italian coffee-flavored dessert.'),(21,'Apple Pie','dessert','Warm apple pie with a flaky crust.');
/*!40000 ALTER TABLE `menuitem` ENABLE KEYS */;
UNLOCK TABLES;

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
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`customerID`) REFERENCES `customer` (`customerID`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`discountID`) REFERENCES `discountcode` (`discountID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `orderitem`
--

LOCK TABLES `orderitem` WRITE;
/*!40000 ALTER TABLE `orderitem` DISABLE KEYS */;
/*!40000 ALTER TABLE `orderitem` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `recipe`
--

LOCK TABLES `recipe` WRITE;
/*!40000 ALTER TABLE `recipe` DISABLE KEYS */;
INSERT INTO `recipe` VALUES (1,1,2),(1,2,1),(1,3,1),(1,14,1),(1,15,1),(2,1,2),(2,2,1),(2,3,1),(2,5,1),(3,1,2),(3,2,1),(3,3,1),(3,6,1),(3,7,1),(3,8,1),(3,13,1),(4,1,2),(4,2,1),(4,3,1),(4,5,1),(4,10,1),(4,12,1),(4,19,1),(5,1,2),(5,2,1),(5,3,1),(5,11,1),(5,17,1),(6,1,2),(6,3,1),(7,1,2),(7,2,1),(7,3,1),(7,4,1),(7,13,1),(8,1,2),(8,3,1),(8,13,1),(8,16,1),(9,1,2),(9,2,1),(9,3,1),(9,9,1),(9,8,1),(10,1,2),(10,3,1),(10,20,1),(11,1,2),(11,2,1),(11,3,1),(11,18,1),(11,16,1),(12,1,2),(12,2,1),(12,3,1),(12,5,1),(12,6,1),(13,21,1),(14,22,1),(15,23,1),(16,24,1),(17,25,1),(18,26,2),(18,27,1),(18,28,1),(18,31,3),(18,32,1),(19,29,2),(19,27,1),(19,31,2),(19,30,1),(19,32,1),(20,33,1),(20,27,1),(20,28,1),(20,31,2),(21,34,3),(21,27,1),(21,35,0),(21,26,1),(21,32,1);
/*!40000 ALTER TABLE `recipe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-30 14:31:29
