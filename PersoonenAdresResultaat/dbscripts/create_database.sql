-- MySQL dump 10.13  Distrib 5.6.19, for osx10.7 (i386)
--
-- Host: 127.0.0.1    Database: rsvier
-- ------------------------------------------------------
-- Server version	5.6.23

-- Drop tables
 DROP TABLE IF EXISTS `resultaat`;
 DROP TABLE IF EXISTS `persoon`;
 DROP TABLE IF EXISTS `adres`;

--
-- Table structure for table `adres`
--
 
CREATE TABLE `adres` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `straatnaam` varchar(100) NOT NULL,
  `huisnummer` int(11) NOT NULL,
  `toevoeging` varchar(5) DEFAULT NULL,
  `postcode` varchar(10) NOT NULL,
  `woonplaats` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `Unique_Adres`
  	UNIQUE (straatnaam, huisnummer, toevoeging, postcode, woonplaats)
);

--
-- Table structure for table `persoon`
--

CREATE TABLE `persoon` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `voornaam` varchar(100) NOT NULL,
  `achternaam` varchar(100) NOT NULL,
  `tussenvoegsel` varchar(10) DEFAULT NULL,
  `geboortedatum` varchar(20) NOT NULL,
  `Adres_id` int(11) NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Persoon_Adres`
    FOREIGN KEY (`Adres_id`)
    REFERENCES `adres` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `Unique_Person`
  	UNIQUE (voornaam, achternaam, tussenvoegsel, geboortedatum, Adres_id)
);

--
-- Table structure for table `resultaat`
--


CREATE TABLE `resultaat` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `persoon_id` int(11) NULL, 
  `modulenaam` varchar(100) NOT NULL,
  `resultaat` float NOT NULL,
  `voldoende` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Resultaat_Persoon`
  	FOREIGN KEY (`persoon_id`)
  	REFERENCES `persoon` (`id`)
  	ON DELETE NO ACTION
  	ON UPDATE NO ACTION
);
