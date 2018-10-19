DROP DATABASE IF EXISTS `ShopDB`;
CREATE DATABASE `ShopDB`;
USE `ShopDB`;

-- **********************************************
-- *                                            *
-- *                  Tables                    *
-- *                                            *
-- **********************************************

CREATE TABLE `Products` (
  `pid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `pName` VARCHAR(256) NOT NULL UNIQUE,
  PRIMARY KEY (`pid`)
);

CREATE TABLE `Desktop` (
  `did` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `pid` INT NOT NULL,
  `dType` VARCHAR(256) NOT NULL UNIQUE,
  `dCost` INT NOT NULL,
  `dQuantity` INT NOT NULL,
  PRIMARY KEY (`did`)
);

-- **********************************************
-- *                                            *
-- *                 Values                     *
-- *                                            *
-- **********************************************

 INSERT INTO `Products`(`pName`)
 VALUE("Desktop");

 INSERT INTO `Products`(`pName`)
 VALUE("Laptop"); 
 
 INSERT INTO `Products`(`pName`)
 VALUE("Server");

 INSERT INTO `Desktop`(`pid`,`dType`,`dCost`,`dQuantity`)
 VALUES(1,"Extreme",650,15);
 
 INSERT INTO `Desktop`(`pid`,`dType`,`dCost`,`dQuantity`)
 VALUES(1,"Ultimate",1000,10);