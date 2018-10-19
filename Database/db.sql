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
  `pType` VARCHAR(256) NOT NULL UNIQUE,
  `pCost` INT NOT NULL,
  `pQuantity` INT NOT NULL,
  PRIMARY KEY (`pid`)
);

-- **********************************************
-- *                                            *
-- *                 Values                     *
-- *                                            *
-- **********************************************

 INSERT INTO `Products`(`pName`,`pType`, `pCost`, `pQuantity`)
 VALUE("Desktop","Extreme", 550, 100);

 INSERT INTO `Products`(`pName`,`pType`, `pCost`, `pQuantity`)
 VALUE("Laptop","SlimBook", 700, 50);

  INSERT INTO `Products`(`pName`,`pType`, `pCost`, `pQuantity`)
 VALUE("Server","PowerStation", 1200, 10);