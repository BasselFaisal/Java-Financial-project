CREATE DATABASE  IF NOT EXISTS `financial` ;

USE `financial`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fristname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `mobile` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enable` boolean ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MANAGER');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`role_id`),
  
  KEY `FK_USER_idx` (`user_id`),
  
  CONSTRAINT `FK_USER` FOREIGN KEY (`user_id`) 
  REFERENCES `users` (`id`) 
  ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_ROLE` FOREIGN KEY (`role_id`) 
  REFERENCES `roles` (`id`) 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `wallet`;

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `balance` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `user_wallet`;

CREATE TABLE `user_wallet` (
  `user_id` int(11) NOT NULL,
  `wallet_id` int(11) NOT NULL,
  
  PRIMARY KEY (`user_id`,`wallet_id`),
  
  KEY `FK_USER_idx` (`user_id`),
  
  CONSTRAINT `FK_USER_1` FOREIGN KEY (`user_id`) 
  REFERENCES `users` (`id`) 
  ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_WALLET` FOREIGN KEY (`wallet_id`) 
  REFERENCES `wallet` (`id`) 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `transaction`;

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  -- `transactiondate` datetime default null,
  `amount` decimal DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `transaction_wallet`;

CREATE TABLE `transaction_wallet` (
  `wallet_id` int(11) NOT NULL,
  `transaction_id` int(11) NOT NULL,

  PRIMARY KEY (`wallet_id`,`transaction_id`),
  
  KEY `FK_USER_idx` (`wallet_id`),
  
  CONSTRAINT `FK_WALLET_1` FOREIGN KEY (`wallet_id`) 
  REFERENCES `wallet` (`id`) 
  ON UPDATE NO ACTION,
    
  CONSTRAINT `FK_TRANSACTION` FOREIGN KEY (`transaction_id`) 
  REFERENCES `transaction` (`id`) 
  ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;