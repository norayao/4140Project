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
  PRIMARY KEY (customerID));
INSERT INTO `Customer` (`customerID`, `customerName`, `customerContact`, `customerAddress`) VALUES
(10001, 'Yihong Yao', '989-8888', '2393 robie st'),
(10002, 'Yu Zhang', '480-2500', '4563 robie st'),
(10003, 'Jiaqi Guo', '920-6666', '1115 tower rd'),
(10004, 'Harry Potter', '950-3456', '1070 spring garden rd'),
(10005, 'Peter Zan', '968-1234', '1090 robie st'),
(10006, 'Jessie Liu', '483-2457', '2566 Shopping center'),
(10007, 'Ruby Wang', '989-7777', '2090 robie st'),
(10008, 'Lura Zhang', '488-8534', '2233 tower rd'),
(10009, 'Alex Potter', '989-5612', '2037 university ave'),
(10010, 'Felix Zhang', '986-0021', '961 summer rd');


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
insert into Invoice values(20001,curdate(),10001,'30001',30,true);


-- -----------------------------------------------------
-- Table `Logs`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Logs` (
  `logID` INT NOT NULL AUTO_INCREMENT,
  `logOperation` VARCHAR(45) NOT NULL,
  `logState` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`logID`));


