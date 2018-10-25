SELECT 
CONCAT(`Graphics`.`gManufacturer`,' ', `Graphics`.`gName`,' ', `Graphics`.`gMemory`,'Gb -',`Graphics`.`gCost`,'€')
AS 'GPU',
CONCAT(`Chassis`.`chassName`,' - ',`Chassis`.`chassCost`,'€')
AS 'Chassi', 
CONCAT(`Processors`.`cManufacturer`,' ',`Processors`.`cName`,' ',`Processors`.`cCores`,' ',`Processors`.`cSpeed`,'Mhz - ',`Processors`.`cCost`,'€')
AS 'CPU',
CONCAT(`Memories`.`mName`,' ',`Memories`.`mClassification`,' ',`Memories`.`mSize`,'Gb ',`Memories`.`mSpeed`,'Mhz - ',`Memories`.`mCost`,'€')
AS'RAM',
`Products`.`pName`,`Products`.`pType`,
CONCAT(`Memories`.`mCost` + `Processors`.`cCost` + `Graphics`.`gCost`+ `Chassis`.`chassCost`) 
AS 'Cost',
`Chassis`.`chassImage`
AS 'path'
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