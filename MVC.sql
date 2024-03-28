DROP DATABASE IF EXISTS MVCDB;

CREATE DATABASE MVCDB;

USE MVCDB;

DROP TABLE IF EXISTS Orders;

DROP TABLE IF EXISTS Shoes;

DROP TABLE IF EXISTS CSR;

DROP TABLE IF EXISTS Customers;

CREATE TABLE Customers (
	customerId	int 		NOT NULL	AUTO_INCREMENT,
	userName	varchar(20)	NOT NULL,
	password	varchar(20)	NOT NULL,
    firstName	varchar(20)	NOT NULL,
    lastName	varchar(20)	NOT NULL, 
    address		varchar(50)	NOT NULL,
    postalCode	varchar(7)	NOT NULL,
    city		varchar(20)	NOT NULL,
	PRIMARY KEY (customerId)
);

CREATE TABLE CSR (
	employeeId	int			NOT NULL	AUTO_INCREMENT,
	userName	varchar(20)	NOT NULL,
	password	varchar(20)	NOT NULL,
    firstName	varchar(20)	NOT NULL,
    lastName	varchar(20)	NOT NULL,
	PRIMARY KEY (employeeId)
);

CREATE TABLE Shoes (
	itemId		int				NOT NULL	AUTO_INCREMENT,
    itemName	varchar(20)		NOT NULL,
    category	varchar(10)		NOT NULL,
    shoeSize	int				NOT NULL,
    price		decimal(7,2)	NOT NULL,
    PRIMARY KEY (itemId)
);

CREATE TABLE Orders (
	orderId		int			NOT NULL	AUTO_INCREMENT,
    customerId	int 		NOT NULL,
    itemId		int			NOT NULL,
    orderDate	date		NOT NULL,
    quantity	int			NOT NULL,
    status		varchar(20)	NOT NULL,
    PRIMARY KEY (orderId),
    FOREIGN KEY (customerId) REFERENCES Customers (customerId),
    FOREIGN KEY (itemId) REFERENCES Shoes (itemId)
);