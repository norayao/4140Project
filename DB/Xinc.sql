
CREATE SCHEMA IF NOT EXISTS Xinc DEFAULT CHARACTER SET utf8;
USE Xinc;

-- -----------------------------------------------------
-- Table `Part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Part` (
  `partID` VARCHAR(45) NOT NULL,
  `partName` VARCHAR(45) NOT NULL,
  `partPrice` VARCHAR(10) NOT NULL,
  `partDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`partID`));

insert into Part values('30001','unknown','10.5','skfhsh');
insert into Part values('30002','analog machine','30.0','blue');
insert into Part values('30003','charging machine','4.5','white');
insert into Part values('30004','calculator','15.0','red');
insert into Part values('30005','ruler','2.0','black');
insert into Part values('30006','gear','1.0','green');
insert into Part values('30007','araloop','14.0','grey');
insert into Part values('30008','bottle','30','yellow');
-- -----------------------------------------------------
-- Table `Warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse` (
  `warehouseID` INT NOT NULL AUTO_INCREMENT,
  `warehouseName` VARCHAR(45) NOT NULL,
  `warehouseLocation` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`warehouseID`));

insert into Warehouse values(40001,'x','halifax');

-- -----------------------------------------------------
-- Table `Stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Stock` (
  `current` INT NOT NULL,
  `minimum` INT NOT NULL,
  `partID` VARCHAR(45) NOT NULL,
  `warehouseID` INT NOT NULL,
  PRIMARY KEY (`partID`, `warehouseID`),
  INDEX `fk_Stock_Warehouse1_idx` (`warehouseID` ASC),
  CONSTRAINT `fk_Stock_Part1`
    FOREIGN KEY (`partID`)
    REFERENCES `Part` (`partID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_Warehouse1`
    FOREIGN KEY (`warehouseID`)
    REFERENCES `Warehouse` (`warehouseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
    insert into Stock values(30,5,'30001',40001);
    insert into Stock values(20,3,'30002',40001);
    insert into Stock values(45,3,'30003',40001);
    insert into Stock values(25,3,'30004',40001);
    insert into Stock values(20,3,'30005',40001); 
    insert into Stock values(10,3,'30006',40001);
    insert into Stock values(12,3,'30007',40001);
    insert into Stock values(20,3,'30008',40001); 

