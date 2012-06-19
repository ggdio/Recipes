CREATE DATABASE  IF NOT EXISTS `receitas` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `receitas`;
-- MySQL dump 10.13  Distrib 5.5.16, for Win32 (x86)
--
-- Host: 10.178.24.23    Database: receitas
-- ------------------------------------------------------
-- Server version	5.1.56-community

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
-- Table structure for table `tbl_usuario`
--

DROP TABLE IF EXISTS `tbl_usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_usuario` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(300) DEFAULT NULL,
  `login` varchar(300) DEFAULT NULL,
  `senha` varchar(300) DEFAULT NULL,
  `status` tinyint(1) DEFAULT NULL,
  `ultimoAcesso` date DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_usuario`
--

LOCK TABLES `tbl_usuario` WRITE;
/*!40000 ALTER TABLE `tbl_usuario` DISABLE KEYS */;
INSERT INTO `tbl_usuario` VALUES (1,'Usuário','login','ac39d1a68aa80690af5ac8798426d241',1,'2012-06-05',0);
/*!40000 ALTER TABLE `tbl_usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_receita`
--

DROP TABLE IF EXISTS `tbl_receita`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_receita` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(300) DEFAULT NULL,
  `descricao` varchar(300) DEFAULT NULL,
  `ingredientes` varchar(800) DEFAULT NULL,
  `categoria` int(11) NOT NULL,
  `preparo` varchar(800) DEFAULT NULL,
  `rendimento` int(11) DEFAULT NULL,
  `tempo` int(11) DEFAULT NULL,
  `imagem` varchar(300) DEFAULT NULL,
  `autor` int(11) NOT NULL,
  `tags` varchar(800) DEFAULT NULL,
  `data` date DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `categoria` (`categoria`),
  KEY `autor` (`autor`),
  CONSTRAINT `tbl_receita_ibfk_1` FOREIGN KEY (`categoria`) REFERENCES `tbl_categoria` (`codigo`),
  CONSTRAINT `tbl_receita_ibfk_2` FOREIGN KEY (`autor`) REFERENCES `tbl_usuario` (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_receita`
--

LOCK TABLES `tbl_receita` WRITE;
/*!40000 ALTER TABLE `tbl_receita` DISABLE KEYS */;
INSERT INTO `tbl_receita` VALUES (1,'Receita Filet Mignon','Descrição Alterada','Filet Mignon;Molho;Tempero;',1,'Assar',5,5,'foto',1,'carne;salgado;molho;','2012-06-05'),(4,'Receita Filet Frango','Frango Grelhado','peito de frango;temperos;',1,'Grelhar',2,10,'foto',1,'frango;salgado;grelhado;','2012-06-06'),(6,'Calabresa C/ Pimenta','Calabresa Apimentada','linguiça calabresa;pimenta vermelha;',1,'Fritar',2,20,'foto',1,'calabresa;','2012-06-06'),(7,'Teste','teste','teste;teste;',1,'teste',10,15,'',1,'asd;asd;','2012-06-06'),(8,'Teste','teste','teste;teste;',1,'teste',10,15,'',1,'asd;asd;','2012-06-06'),(9,'Teste','teste','teste;teste;',1,'teste',10,15,'',1,'asd;asd;','2012-06-06'),(10,'Teste','teste','teste;teste;',1,'teste',10,15,'',1,'asd;asd;','2012-06-06'),(11,'Teste','teste','teste;teste;',1,'teste',10,15,'',1,'asd;asd;','2012-06-06');
/*!40000 ALTER TABLE `tbl_receita` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_categoria`
--

DROP TABLE IF EXISTS `tbl_categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_categoria` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(300) DEFAULT NULL,
  `descricao` varchar(800) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_categoria`
--

LOCK TABLES `tbl_categoria` WRITE;
/*!40000 ALTER TABLE `tbl_categoria` DISABLE KEYS */;
INSERT INTO `tbl_categoria` VALUES (1,'Carnes','Todos os tipos de receitas de carnes'),(2,'Tortas(Salgadas)','Variados tipos de tortas salgadas'),(3,'Tortas(Doces)','Variados tipos de tortas doces'),(4,'Bolos','Diversos bolos delicioso');
/*!40000 ALTER TABLE `tbl_categoria` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-06-15 15:58:54
