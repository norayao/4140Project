CREATE SCHEMA IF NOT EXISTS front DEFAULT CHARACTER SET utf8;
USE front;

-- -----------------------------------------------------
-- Table `Customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Customer (
  customerID INT NOT NULL AUTO_INCREMENT,
  customerName VARCHAR(45) NOT NULL,
  customerContact VARCHAR(10) NOT NULL,
  customerAddress VARCHAR(99) NOT NULL,
  PRIMARY KEY (customerID),
insert into Customer values(10001,'Yihong Yao','989-8888','2393 robie st');


-- -----------------------------------------------------
-- Table `Invoice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Invoice (
  invoiceID INT NOT NULL AUTO_INCREMENT,
  invoiceTime DATETIME NOT NULL,
  customerID INT NOT NULL,
  partID VARCHAR(10) NOT NULL,
  partQuantity INT NOT NULL,
  partStatus BOOLEAN NULL,
  PRIMARY KEY (`invoiceID`),
  CONSTRAINT `fk_Invoice_Customer`
    FOREIGN KEY (`customerID`)
    REFERENCES `Customer` (`customerID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
insert into Invoice values(20001,curdate(),10001,'ISO-30001',30,true);



