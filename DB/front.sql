CREATE SCHEMA IF NOT EXISTS front DEFAULT CHARACTER SET utf8;
USE front;

-- -----------------------------------------------------
-- Table `mydb`.`Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Customer (
  customerID INT NOT NULL,
  customerName VARCHAR(45) NOT NULL,
  customerContact VARCHAR(10) NOT NULL,
  customerAddress VARCHAR(99) NOT NULL,
  PRIMARY KEY (customerID),
  UNIQUE INDEX customerID_UNIQUE (customerID ASC));


-- -----------------------------------------------------
-- Table `mydb`.`Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Invoice (
  invoiceID INT NOT NULL,
  invoiceTime DATETIME NOT NULL,
  customerID INT NOT NULL,
  PRIMARY KEY (`invoiceID`),
  INDEX `fk_Invoice_Customer_idx` (`customerID` ASC) VISIBLE,
  UNIQUE INDEX `invoiceID_UNIQUE` (`invoiceID` ASC) VISIBLE,
  UNIQUE INDEX `customerID_UNIQUE` (`customerID` ASC) VISIBLE,
  CONSTRAINT `fk_Invoice_Customer`
    FOREIGN KEY (`customerID`)
    REFERENCES `mydb`.`Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Line`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Line` (
  `lineID` INT NOT NULL,
  `lineNo` INT NOT NULL,
  `linetTimestamp` VARCHAR(45) NOT NULL,
  `partID` VARCHAR(10) NOT NULL,
  `partQuantity` INT NOT NULL,
  `partCompany` VARCHAR(45) NULL,
  `invoiceID` INT NOT NULL,
  PRIMARY KEY (`lineID`, `invoiceID`),
  UNIQUE INDEX `lineID_UNIQUE` (`lineID` ASC) VISIBLE,
  INDEX `invoiceID_idx` (`invoiceID` ASC) VISIBLE,
  CONSTRAINT `invoiceID`
    FOREIGN KEY (`invoiceID`)
    REFERENCES `mydb`.`Invoice` (`invoiceID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




