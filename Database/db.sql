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
  `pName` VARCHAR(256) NOT NULL,
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
  `mName` VARCHAR(256) NOT NULL,
  `mClassification` VARCHAR(256) NOT NULL,
  `mSpeed` INT NOT NULL,
  `mSize` INT NOT NULL,
  `mCost` INT NOT NULL,
  PRIMARY KEY (`mid`)
);

CREATE TABLE `Processors` (
  `cid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `cName` VARCHAR(256) NOT NULL,
  `cManufacturer` VARCHAR(256) NOT NULL,
  `cCores` VARCHAR(256) NOT NULL,
  `cSpeed` INT NOT NULL,
  `cCost` INT NOT NULL,
  PRIMARY KEY (`cid`)
);

CREATE TABLE `Chassis` (
  `chassId` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `chassName` VARCHAR(256) NOT NULL UNIQUE,
  `chassWeight` INT NOT NULL,
  `chassCost` INT NOT NULL,
  PRIMARY KEY (`chassId`)
);

CREATE TABLE `Graphics` (
  `gid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `gName` VARCHAR(256) NOT NULL UNIQUE,
  `gManufacturer` VARCHAR(256) NOT NULL,
  `gMemory` INT NOT NULL,
  `gCost` INT NOT NULL,

  PRIMARY KEY (`gid`)
);

-- **********************************************
-- *                                            *
-- *                 Values                     *
-- *                                            *
-- **********************************************

-- Memory RAM
INSERT INTO `Memories`(`mName`,`mClassification`,`mSpeed`,`mSize`,`mCost`)
VALUES("HyperX","DDR4",2400,16,100);
INSERT INTO `Memories`(`mName`,`mClassification`,`mSpeed`,`mSize`,`mCost`)
VALUES("Crucial","DDR4",1600,32,160);
INSERT INTO `Memories`(`mName`,`mClassification`,`mSpeed`,`mSize`,`mCost`)
VALUES("Kingston","DDR3",1600,8,60);


-- PROCESSORS
INSERT INTO `Processors`(`cName`,`cManufacturer`,`cCores`,`cSpeed`,`cCost`)
VALUES("i7 7800k","Intel","Hexa Core",3000,250);
INSERT INTO `Processors`(`cName`,`cManufacturer`,`cCores`,`cSpeed`,`cCost`)
VALUES("A5 Ryzen","AMD","Octa Core",4200,310);
INSERT INTO `Processors`(`cName`,`cManufacturer`,`cCores`,`cSpeed`,`cCost`)
VALUES("i7 6200k","Intel","Quad Core",3400,350);


-- CHASSI FOR DESKTOP
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`)
VALUES("Obsidian",20,80);
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`)
VALUES("Eclipse",8,40);
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`)
VALUES("H500",11,45);
-- GRAPHIC CARDS
INSERT INTO `Graphics`(`gName`,`gManufacturer`,`gMemory`,`gCost`)
VALUES("GTX 1080Ti","Nvidia",8192,900);
INSERT INTO `Graphics`(`gName`,`gManufacturer`,`gMemory`,`gCost`)
VALUES("GTX 980","Nvidia",6000,400);
INSERT INTO `Graphics`(`gName`,`gManufacturer`,`gMemory`,`gCost`)
VALUES("RX 580","AMD",4000,300);


-- Desktops
INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",1,1,"Ultimate");
INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(1,1,1);

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",2,2,"Extreme");
INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(2,2,2);

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",3,3,"Soldier");
INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(3,3,3);


