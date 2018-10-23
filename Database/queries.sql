SELECT 
CONCAT(`Graphics`.`gManufacturer`,' ', `Graphics`.`gName`,' ', `Graphics`.`gMemory`,'Gb')
AS 'GPU',
`Chassis`.`chassName`
AS 'Chassi', 
CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz')
AS 'CPU',
CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz')
AS'RAM',
`Products`.`pName`,`Products`.`pType`
FROM `Products`
INNER JOIN `Desktop`
ON `Desktop`.`pid`=`Products`.`pid`
INNER JOIN `Graphics`
ON `Graphics`.`gid` = `Desktop`.`gid`
INNER JOIN `Processors`
ON `Processors`.`cid` = `Products`.`cid`
INNER JOIN `Chassis`
ON `Chassis`.`chassId` = `Desktop`.`chassId`
INNER JOIN `Memories`
ON `Memories`.`mid` = `Products`.`mid`;