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


CREATE TABLE `Laptop` (
  `lid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `pid` INT NOT NULL,
  `gid` INT NOT NULL,
  `lChassId` INT NOT NULL,
  `inches` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`lid`)
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
  `chassImage` VARCHAR(256) UNIQUE,
  PRIMARY KEY (`chassId`)
);

CREATE TABLE `laptopChassis` (
  `lChassId` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `lChassName` VARCHAR(256) NOT NULL UNIQUE,
  `lChassWeight` INT NOT NULL,
  `lChassCost` INT NOT NULL,
  `lChassImage` VARCHAR(256) UNIQUE,
  PRIMARY KEY (`lChassId`)
);


CREATE TABLE `Graphics` (
  `gid` INT NOT NULL UNIQUE AUTO_INCREMENT,
  `gName` VARCHAR(256) NOT NULL UNIQUE,
  `gManufacturer` VARCHAR(256) NOT NULL,
  `gMemory` INT NOT NULL,
  `gCost` INT NOT NULL,

  PRIMARY KEY (`gid`)
);

CREATE TABLE `OrderTable`(
  `oid` VARCHAR(256) NOT NULL UNIQUE,
  `oCustomerName` VARCHAR(256) NOT NULL,
  `oCustomerEmail`VARCHAR(256) NOT NULL,
  `oItems` VARCHAR(256) NOT NULL,
  `oStatus` VARCHAR(256) NOT NULL,
  PRIMARY KEY (`oid`)
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
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`,`chassImage`)
VALUES("Obsidian",20,80,"/Images/obsidian.png");
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`,`chassImage`)
VALUES("Eclipse",8,40,"/Images/eclipse.png");
INSERT INTO `Chassis`(`chassName`,`chassWeight`,`chassCost`,`chassImage`)
VALUES("H500",11,45,"/Images/h500.png");

-- CHASSIS FOR LAPTOPS
INSERT INTO `laptopChassis`(`lChassName`,`lChassWeight`,`lChassCost`,`lChassImage`)
VALUES("Chromebook",3,25,"/Images/chromebook.png");

INSERT INTO `laptopChassis`(`lChassName`,`lChassWeight`,`lChassCost`,`lChassImage`)
VALUES("ZenBook",2,35,"/Images/zenbook.png");

INSERT INTO `laptopChassis`(`lChassName`,`lChassWeight`,`lChassCost`,`lChassImage`)
VALUES("IdeaPad",1,45,"/Images/ideapad.png");



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

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",1,3,"Casual");
INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(4,2,3);

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Desktop",3,1,"Hades");
INSERT INTO `Desktop`(`pid`,`gid`,`chassId`)
VALUES(5,1,2);

-- Laptops
INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Laptop",1,1,"Chromebook");
INSERT INTO `Laptop`(`pid`,`gid`,`lChassId`,`Inches`)
VALUES(6,1,1,"15,6");

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Laptop",2,2,"Zenbook");
INSERT INTO `Laptop`(`pid`,`gid`,`lChassId`,`Inches`)
VALUES(7,2,2,"14");

INSERT INTO `Products`(`pName`,`mid`,`cid`,`pType`)
VALUE("Laptop",3,3,"IdeaPad");
INSERT INTO `Laptop`(`pid`,`gid`,`lChassId`,`Inches`)
VALUES(8,3,3,"11,5");