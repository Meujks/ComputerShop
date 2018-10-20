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
  `mid` INT NOT NULL,
  `cid` INT NOT NULL,
  `pType` VARCHAR(256) NOT NULL UNIQUE,
  PRIMARY KEY (`pid`)
);

CREATE TABLE `Desktop` (
  `did` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `pid` INT NOT NULL,
  `gid` INT NOT NULL,
  `chassId` INT NOT NULL,
  PRIMARY KEY (`did`)
);

CREATE TABLE `Memories` (
  `mid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `mName` VARCHAR(256) NOT NULL UNIQUE,
  `mClassification` VARCHAR(256) NOT NULL UNIQUE,
  `mSpeed` INT NOT NULL UNIQUE,
  `mSize` INT NOT NULL UNIQUE,
  `mCost` INT NOT NULL UNIQUE,
  PRIMARY KEY (`mid`)
);

CREATE TABLE `Processors` (
  `cid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `cName` VARCHAR(256) NOT NULL UNIQUE,
  `cManufacturer` VARCHAR(256) NOT NULL UNIQUE,
  `cCores` VARCHAR(256) NOT NULL UNIQUE,
  `cSpeed` INT NOT NULL UNIQUE,
  `cCost` INT NOT NULL UNIQUE,
  PRIMARY KEY (`cid`)
);

CREATE TABLE `Chassis` (
  `chassId` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `chassName` VARCHAR(256) NOT NULL UNIQUE,
  `chassWeight` INT NOT NULL UNIQUE,
  `chassCost` INT NOT NULL UNIQUE,
  PRIMARY KEY (`chassId`)
);

CREATE TABLE `Graphics` (
  `gid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `gName` VARCHAR(256) NOT NULL UNIQUE,
  `gManufacturer` VARCHAR(256) NOT NULL UNIQUE,
  `gMemory` INT NOT NULL UNIQUE,
  `gCost` INT NOT NULL UNIQUE,

  PRIMARY KEY (`gid`)
);

-- **********************************************
-- *                                            *
-- *                 Values                     *
-- *                                            *
-- **********************************************

INSERT INTO `Memories`(`mName`,`mClassification`,`mSpeed`,`mSize`,`mCost`)
VALUES("HyperX","DDR4",2400,16,100);

INSERT INTO `Processors`(`cName`,`cManufacturer`,`cCores`,`cSpeed`,`cCost`)
VALUES("i7 6200k","Intel","Quad Core",3400,350);


INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`)
VALUES("Obsidian",34,80);


INSERT INTO `Graphics`(`gName`,`gManufacturer`,`gMemory`,`gCost`)
VALUES("GTX 1080Ti","Nvidia",8192,900);

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",1,1,"Standard");

INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(1,1,1);
