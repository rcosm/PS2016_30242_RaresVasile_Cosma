CREATE DATABASE  IF NOT EXISTS `ps02`;
USE `ps02`;

-- shows
DROP TABLE IF EXISTS `shows`;

CREATE TABLE `shows` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `administration` varchar(45) NOT NULL,
  `distribution` varchar(45) NOT NULL,
  `premiere` date NOT NULL,
  `nrOfTickets` int(11) NOT NULL,
  PRIMARY KEY (`id`)
);

-- tickets
DROP TABLE IF EXISTS `tickets`;

CREATE TABLE `tickets` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idShow` int(11) NOT NULL,
  `row` int(11) NOT NULL,
  `nr` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idShow_idx` (`idShow`),
  CONSTRAINT `idShow` FOREIGN KEY (`idShow`) REFERENCES `shows` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- users
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`)
);