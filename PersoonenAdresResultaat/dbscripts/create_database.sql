-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: rsvier
-- ------------------------------------------------------
-- Server version	5.6.23


--
-- Table structure for table `adres`
--

DROP TABLE IF EXISTS `adres`;
CREATE TABLE `adres` (
  `id` int(11) NOT NULL,
  `straatnaam` varchar(100) NOT NULL,
  `huisnummer` int(11) NOT NULL,
  `toevoeging` varchar(5) DEFAULT NULL,
  `postcode` varchar(10) NOT NULL,
  `woonplaats` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);

--
-- Table structure for table `persoon`
--

DROP TABLE IF EXISTS `persoon`;
CREATE TABLE `persoon` (
  `id` int(11) NOT NULL,
  `voornaam` varchar(100) NOT NULL,
  `achternaam` varchar(100) NOT NULL,
  `tussenvoegsel` varchar(10) DEFAULT NULL,
  `geboortedatum` varchar(20) NOT NULL,
  `adres_id` int(11) NULL,
  PRIMARY KEY (`id`)
);
--
-- Table structure for table `resultaat`
--

DROP TABLE IF EXISTS `resultaat`;
CREATE TABLE `resultaat` (
  `id` int(11) NOT NULL,
  `modulenaam` varchar(100) NOT NULL,
  `resultaat` float NOT NULL,
  `voldoende` tinyint(1) NOT NULL,
  `persoon_id` int(11) NULL,
  PRIMARY KEY (`id`)
);
