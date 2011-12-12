CREATE TABLE `Filme` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Fach` int(4) unsigned zerofill DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Gesehen` varchar(5) DEFAULT NULL,
  `Cover` varchar(5) DEFAULT NULL,
  `Medium` varchar(5) DEFAULT NULL,
  `Quali` varchar(5) DEFAULT NULL,
  `Wertung` varchar(10) DEFAULT NULL,
  `Genre` varchar(15) DEFAULT NULL,
  `coverUrl` varchar(255) DEFAULT NULL,
  `jdoDetachedState` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1071 DEFAULT CHARSET=latin1