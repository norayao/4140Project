
CREATE SCHEMA IF NOT EXISTS Yinc DEFAULT CHARACTER SET utf8;
USE Yinc;

-- -----------------------------------------------------
-- Table `Part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Part` (
  `partID` VARCHAR(45) NOT NULL,
  `partName` VARCHAR(45) NOT NULL,
  `partDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`partID`),
  UNIQUE INDEX `partID_UNIQUE` (`partID` ASC));
insert into Part values('30001','yinc','sleiwe');

-- -----------------------------------------------------
-- Table `Warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse` (
  `warehouseID` INT NOT NULL AUTO_INCREMENT,
  `warehouseName` VARCHAR(45) NOT NULL,
  `warehouseLocation` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `warehouseID_UNIQUE` (`warehouseID` ASC),
  PRIMARY KEY (`warehouseID`));

insert into Warehouse values(40001,'y','dartmouth');
-- -----------------------------------------------------
-- Table `Stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Stock` (
  `current` INT NOT NULL,
  `minimum` VARCHAR(45) NOT NULL,
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
