
CREATE SCHEMA IF NOT EXISTS Xinc DEFAULT CHARACTER SET utf8;
USE Xinc;

-- -----------------------------------------------------
-- Table `Part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Part` (
  `partID` VARCHAR(45) NOT NULL,
  `partName` VARCHAR(45) NOT NULL,
  `partDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`partID`));

insert into Part values('ISO-30001','xinc','skfhsh');

-- -----------------------------------------------------
-- Table `Warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse` (
  `warehouseID` INT NOT NULL,
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
    
    insert into Stock values(30,5,'ISO-30001',40001);
