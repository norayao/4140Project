CREATE SCHEMA IF NOT EXISTS Xinc DEFAULT CHARACTER SET utf8;
USE Xinc;

-- -----------------------------------------------------
-- Table `Part`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Part` (
  `partID` VARCHAR(45) NOT NULL,
  `partName` VARCHAR(45) NOT NULL,
  `partDescription` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`partID`),
  UNIQUE INDEX `partID_UNIQUE` (`partID` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `Warehouse`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Warehouse` (
  `warehouseID` INT NOT NULL,
  `warehouseName` VARCHAR(45) NOT NULL,
  `warehouseLocation` VARCHAR(45) NOT NULL,
  UNIQUE INDEX `warehouseID_UNIQUE` (`warehouseID` ASC) VISIBLE,
  PRIMARY KEY (`warehouseID`));


-- -----------------------------------------------------
-- Table `Stock`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Stock` (
  `current` INT NOT NULL,
  `minimum` VARCHAR(45) NOT NULL,
  `partID` INT NOT NULL,
  `warehouseID` INT NOT NULL,
  PRIMARY KEY (`partID`, `warehouseID`),
  INDEX `fk_Stock_Warehouse1_idx` (`warehouseID` ASC) VISIBLE,
  CONSTRAINT `fk_Stock_Part1`
    FOREIGN KEY (`partID`)
    REFERENCES `mydb`.`Part` (`partID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Stock_Warehouse1`
    FOREIGN KEY (`warehouseID`)
    REFERENCES `mydb`.`Warehouse` (`warehouseID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
