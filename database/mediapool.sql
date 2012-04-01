-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Erstellungszeit: 01. Apr 2012 um 09:47
-- Server Version: 5.1.53
-- PHP-Version: 5.3.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `mediapool`
--

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `media`
--

CREATE TABLE IF NOT EXISTS `media` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Daten für Tabelle `media`
--

INSERT INTO `media` (`id`, `Name`) VALUES
(2, 'Dies ist ein Test.');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mediaattributedefs`
--

CREATE TABLE IF NOT EXISTS `mediaattributedefs` (
  `mediaTypeId` int(12) NOT NULL,
  `attributeName` char(50) NOT NULL,
  `attributeType` char(20) NOT NULL,
  `attributeOrder` int(11) NOT NULL,
  `attributeMandatory` enum('MANDATORY','RECOMMENDED','NOTHING') NOT NULL,
  PRIMARY KEY (`mediaTypeId`,`attributeName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `mediaattributedefs`
--

INSERT INTO `mediaattributedefs` (`mediaTypeId`, `attributeName`, `attributeType`, `attributeOrder`, `attributeMandatory`) VALUES
(1, 'duration', 'Integer', 1, 'MANDATORY');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mediaattributes`
--

CREATE TABLE IF NOT EXISTS `mediaattributes` (
  `mediaid` int(11) NOT NULL,
  `attributeName` char(50) NOT NULL,
  `attributeValue` varchar(255) NOT NULL,
  PRIMARY KEY (`mediaid`,`attributeName`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Daten für Tabelle `mediaattributes`
--

INSERT INTO `mediaattributes` (`mediaid`, `attributeName`, `attributeValue`) VALUES
(2, 'duration', '12345');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `mediatypes`
--

CREATE TABLE IF NOT EXISTS `mediatypes` (
  `mediaTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `mediaTypeName` varchar(100) NOT NULL,
  PRIMARY KEY (`mediaTypeId`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Daten für Tabelle `mediatypes`
--

INSERT INTO `mediatypes` (`mediaTypeId`, `mediaTypeName`) VALUES
(1, 'Movie');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
