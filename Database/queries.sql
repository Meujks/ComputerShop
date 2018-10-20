--DESKTOP SELECT 
SELECT 
CONCAT(graphics.gManufacturer,' ', graphics.gName,' ', graphics.gMemory,'Gb') 
AS 'GPU',
chassis.chassName 
AS 'Chassi',
CONCAT(processors.cManufacturer,' ',processors.cName,' ',processors.cCores,' ',processors.cSpeed,'Mhz') 
AS 'CPU',
CONCAT(memories.mName,' ',memories.mClassification,' ',memories.mSize,'Gb ',memories.mSpeed,'Mhz')
AS'RAM',
CONCAT(products.pName,' ', products.pType)
AS 'Product'
FROM products
INNER JOIN `desktop`
ON `desktop`.`pid`=`products`.`pid` 
INNER JOIN `graphics`
ON `graphics`.`gid` = `desktop`.`gid`
INNER JOIN `processors` 
ON `processors`.`cid` = `products`.`cid`
INNER JOIN `chassis`
ON `chassis`.`chassId` = `desktop`.`chassId`
INNER JOIN `memories`
ON `memories`.`mid` = `products`.`mid`;