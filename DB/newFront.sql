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
-- Table `Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Invoice (
  invoiceID INT NOT NULL,
  invoiceTime DATETIME NOT NULL,
  customerID INT NOT NULL,
  partID VARCHAR(10) NOT NULL,
  partQuantity INT NOT NULL,
  partStatus BOOLEAN NULL,
  PRIMARY KEY (`invoiceID`),
  INDEX `fk_Invoice_Customer_idx` (`customerID` ASC),
  UNIQUE INDEX `invoiceID_UNIQUE` (`invoiceID` ASC),
  UNIQUE INDEX `customerID_UNIQUE` (`customerID` ASC),
  CONSTRAINT `fk_Invoice_Customer`
    FOREIGN KEY (`customerID`)
    REFERENCES `Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);




