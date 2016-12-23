/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.22-log : Database - helpdesk
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`helpdesk` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `helpdesk`;

/*Table structure for table `categoria` */

DROP TABLE IF EXISTS `categoria`;

CREATE TABLE `categoria` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `categoria` */

insert  into `categoria`(`codigo`,`nome`) values (125,'Dúvidas'),(126,'Treinamento'),(127,'Orçamento');

/*Table structure for table `cliente` */

DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `departamento_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKwk2rribdlbr4naifl0t98bwb` (`departamento_codigo`),
  CONSTRAINT `FKwk2rribdlbr4naifl0t98bwb` FOREIGN KEY (`departamento_codigo`) REFERENCES `departamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `cliente` */

insert  into `cliente`(`codigo`,`nome`,`departamento_codigo`) values (40,'Augusto',2),(42,'Gustavo',2),(43,'Teixeira',19),(44,'Marcos Aurélio',16),(45,'Murilo',1),(73,'Sebastião',72);

/*Table structure for table `departamento` */

DROP TABLE IF EXISTS `departamento`;

CREATE TABLE `departamento` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `atendimento` bit(1) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `departamento` */

insert  into `departamento`(`codigo`,`nome`,`atendimento`) values (1,'Tecnologia da Informação',''),(2,'Financeiro','\0'),(3,'Secretaria Academica','\0'),(16,'Direção Acadêmica','\0'),(17,'Biblioteca','\0'),(18,'Marketing','\0'),(19,'Direção Financeira','\0'),(24,'NPJ','\0'),(27,'FIES','\0'),(28,'Empresa Junior','\0'),(30,'Sala dos Professores','\0'),(31,'Clinica','\0'),(46,'Auditório','\0'),(72,'Compras','\0'),(132,'Manutenção Predial','');

/*Table structure for table `equipamento` */

DROP TABLE IF EXISTS `equipamento`;

CREATE TABLE `equipamento` (
  `codigo` bigint(20) NOT NULL,
  `dadosAdicionais` varchar(500) DEFAULT NULL,
  `garantia_final` date DEFAULT NULL,
  `garantia_inicial` date DEFAULT NULL,
  `modelo` varchar(40) DEFAULT NULL,
  `patrimonio` varchar(40) DEFAULT NULL,
  `serie` varchar(40) DEFAULT NULL,
  `situacao` varchar(40) NOT NULL,
  `departamento_codigo` bigint(20) NOT NULL,
  `marca_codigo` bigint(20) DEFAULT NULL,
  `tipoEquipamento_codigo` bigint(20) NOT NULL,
  `capacidade` varchar(255) DEFAULT NULL,
  `dataAquisicao` date DEFAULT NULL,
  `valorHora` decimal(6,2) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKb5e44pba6mypks0lkww4fe7yy` (`departamento_codigo`),
  KEY `FKn7vr45d8xg3l4qh7q6y33xscw` (`marca_codigo`),
  KEY `FK56ru5pjyr101shb2j57ntq9e7` (`tipoEquipamento_codigo`),
  CONSTRAINT `FK56ru5pjyr101shb2j57ntq9e7` FOREIGN KEY (`tipoEquipamento_codigo`) REFERENCES `tipo_equipamento` (`codigo`),
  CONSTRAINT `FKb5e44pba6mypks0lkww4fe7yy` FOREIGN KEY (`departamento_codigo`) REFERENCES `departamento` (`codigo`),
  CONSTRAINT `FKn7vr45d8xg3l4qh7q6y33xscw` FOREIGN KEY (`marca_codigo`) REFERENCES `marca` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `equipamento` */

insert  into `equipamento`(`codigo`,`dadosAdicionais`,`garantia_final`,`garantia_inicial`,`modelo`,`patrimonio`,`serie`,`situacao`,`departamento_codigo`,`marca_codigo`,`tipoEquipamento_codigo`,`capacidade`,`dataAquisicao`,`valorHora`) values (95,'',NULL,NULL,'36225','','','Ativo',72,5,89,NULL,NULL,'0.00'),(97,'',NULL,NULL,'M1132','','','Ativo',3,96,90,NULL,NULL,'0.00'),(98,'',NULL,NULL,'84UDD','','','Ativo',1,5,93,NULL,NULL,'0.00'),(99,'',NULL,NULL,'DD25F5','','','Ativo',24,11,92,NULL,NULL,'0.00'),(100,'',NULL,NULL,'ADDD','','','Ativo',31,14,94,NULL,NULL,'0.00'),(101,'',NULL,NULL,'4544F','','','Ativo',46,38,91,NULL,NULL,'0.00'),(113,'',NULL,NULL,'A08U4','','','Ativo',17,11,102,NULL,NULL,'0.00'),(116,'',NULL,NULL,'993','','','Ativo',1,5,89,NULL,NULL,'0.00');

/*Table structure for table `hibernate_sequence` */

DROP TABLE IF EXISTS `hibernate_sequence`;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `hibernate_sequence` */

insert  into `hibernate_sequence`(`next_val`) values (137);

/*Table structure for table `manutencao` */

DROP TABLE IF EXISTS `manutencao`;

CREATE TABLE `manutencao` (
  `codigo` bigint(20) NOT NULL,
  `dataManutencao` date NOT NULL,
  `dataProxima` date NOT NULL,
  `problema` varchar(500) DEFAULT NULL,
  `servico` varchar(500) DEFAULT NULL,
  `tipo` varchar(40) NOT NULL,
  `equipamento_codigo` bigint(20) NOT NULL,
  `usuario_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKsfdqcgylgseklxp1hdfx5b7kc` (`equipamento_codigo`),
  KEY `FKdyhl2mv8sx6hga84d25dyxotq` (`usuario_codigo`),
  CONSTRAINT `FKdyhl2mv8sx6hga84d25dyxotq` FOREIGN KEY (`usuario_codigo`) REFERENCES `usuario` (`codigo`),
  CONSTRAINT `FKsfdqcgylgseklxp1hdfx5b7kc` FOREIGN KEY (`equipamento_codigo`) REFERENCES `equipamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `manutencao` */

insert  into `manutencao`(`codigo`,`dataManutencao`,`dataProxima`,`problema`,`servico`,`tipo`,`equipamento_codigo`,`usuario_codigo`) values (111,'2016-12-17','2016-12-22','','','Corretiva',95,74),(112,'2016-12-17','2016-12-22','','','Preventiva',98,74),(117,'2016-11-17','2016-12-23','das',' a sds dsad','Corretiva',116,115),(118,'2016-12-17','2016-12-22','adasd','asdsda','Emergência',101,74),(124,'2016-12-06','2016-12-22','Teste','Teste','Emergência',97,74);

/*Table structure for table `marca` */

DROP TABLE IF EXISTS `marca`;

CREATE TABLE `marca` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `marca` */

insert  into `marca`(`codigo`,`nome`) values (5,'Samsumg'),(11,'Dell'),(14,'LG'),(38,'Lenovo'),(96,'HP');

/*Table structure for table `nivel` */

DROP TABLE IF EXISTS `nivel`;

CREATE TABLE `nivel` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `visualizacao` varchar(40) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nivel` */

insert  into `nivel`(`codigo`,`nome`,`visualizacao`) values (128,'Básico','Ambos'),(129,'Avançado','Atendente'),(130,'Intermediário','Ambos');

/*Table structure for table `ticket` */

DROP TABLE IF EXISTS `ticket`;

CREATE TABLE `ticket` (
  `codigo` bigint(20) NOT NULL,
  `assunto` varchar(40) NOT NULL,
  `dataAbertura` date NOT NULL,
  `prioridade` varchar(40) NOT NULL,
  `solicitacao` varchar(600) NOT NULL,
  `status` varchar(40) NOT NULL,
  `categoria_codigo` bigint(20) DEFAULT NULL,
  `cliente_codigo` bigint(20) DEFAULT NULL,
  `departamento_codigo` bigint(20) NOT NULL,
  `equipamento_codigo` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK3o70uatdnll719rh72avavx1p` (`categoria_codigo`),
  KEY `FKksbanky3dlt5q96411pqf0x0b` (`cliente_codigo`),
  KEY `FKpl58cb9163rrtrleeifb20cyt` (`departamento_codigo`),
  KEY `FKhi7n07ds9ppbb40x7eeinef43` (`equipamento_codigo`),
  CONSTRAINT `FK3o70uatdnll719rh72avavx1p` FOREIGN KEY (`categoria_codigo`) REFERENCES `categoria` (`codigo`),
  CONSTRAINT `FKhi7n07ds9ppbb40x7eeinef43` FOREIGN KEY (`equipamento_codigo`) REFERENCES `equipamento` (`codigo`),
  CONSTRAINT `FKksbanky3dlt5q96411pqf0x0b` FOREIGN KEY (`cliente_codigo`) REFERENCES `cliente` (`codigo`),
  CONSTRAINT `FKpl58cb9163rrtrleeifb20cyt` FOREIGN KEY (`departamento_codigo`) REFERENCES `departamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `ticket` */

insert  into `ticket`(`codigo`,`assunto`,`dataAbertura`,`prioridade`,`solicitacao`,`status`,`categoria_codigo`,`cliente_codigo`,`departamento_codigo`,`equipamento_codigo`) values (134,'Teste','2016-12-23','Urgente','Teste','Pendente',NULL,NULL,1,NULL),(135,'sas','2016-12-23','Normal','sadasadsds','Pendente',NULL,NULL,1,NULL),(136,'assdsad sa','2016-12-23','Baixa','adasdsadsadsa','Pendente',NULL,NULL,1,NULL);

/*Table structure for table `tipo_equipamento` */

DROP TABLE IF EXISTS `tipo_equipamento`;

CREATE TABLE `tipo_equipamento` (
  `codigo` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `tipo_equipamento` */

insert  into `tipo_equipamento`(`codigo`,`nome`) values (89,'Ar-Condicionado'),(90,'Impressora'),(91,'Notebook'),(92,'Computador'),(93,'Televisão'),(94,'Lâmpada'),(102,'Catraca'),(119,'Monitor'),(120,'Telefone'),(121,'Elevador'),(122,'Gerador'),(123,'No-Break');

/*Table structure for table `usuario` */

DROP TABLE IF EXISTS `usuario`;

CREATE TABLE `usuario` (
  `codigo` bigint(20) NOT NULL,
  `ativo` bit(1) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(32) DEFAULT NULL,
  `departamento_codigo` bigint(20) NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FKi6h2sy0bvs1btrr2nr11wcnei` (`departamento_codigo`),
  CONSTRAINT `FKi6h2sy0bvs1btrr2nr11wcnei` FOREIGN KEY (`departamento_codigo`) REFERENCES `departamento` (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `usuario` */

insert  into `usuario`(`codigo`,`ativo`,`email`,`nome`,`senha`,`departamento_codigo`) values (74,'','','Jailson','202cb962ac59075b964b07152d234b70',1),(115,'','','Fontinele',NULL,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
