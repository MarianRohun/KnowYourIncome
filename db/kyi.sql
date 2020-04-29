CREATE DATABASE IF NOT EXISTS knowyourincome
DEFAULT CHARACTER SET 'utf8'
DEFAULT COLLATE 'utf8_general_ci';
USE knowyourincome;

DROP TABLE users;
CREATE TABLE IF NOT EXISTS users (
u_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(30) NOT NULL,
surname varchar(30)NOT NULL,
email varchar(50) UNIQUE NOT NULL,
workedHours int NOT NULL DEFAULT 0,
password varchar(30) NOT NULL DEFAULT "",
position INT(1) NOT NULL,
profilePicture varchar(100),
theme varchar(10) NOT NULL DEFAULT "#b38b4d",
isEntry BOOLEAN DEFAULT 0
);
DROP TABLE shifts;
CREATE TABLE IF NOT EXISTS shifts (
s_id INT AUTO_INCREMENT PRIMARY KEY,
worker varchar(50) NOT NULL,
layoutX DOUBLE NOT NULL,
layoutY DOUBLE NOT NULL,
shiftColor varchar(50) NOT NULL,
shiftTime varchar (50) NOT NULL
);
DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (
p_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NOT NULL DEFAULT 0,
buyingPrice DOUBLE NOT NULL DEFAULT 0,
sellingPrice DOUBLE DEFAULT 0 ,
warranty DATE DEFAULT NULL
);

DROP TABLE orders;
CREATE TABLE IF NOT EXISTS orders (
o_id INT AUTO_INCREMENT PRIMARY KEY,
dateInit DATE NOT NULL
);

DROP TABLE `knowyourincome`.`orders_has_products`;
CREATE TABLE IF NOT EXISTS `knowyourincome`.`orders_has_products` (
  `orders_o_id` INT(11) NOT NULL,
  `products_p_id` INT(11) NOT NULL,
  orderedQuantity DOUBLE NOT NULL,
  PRIMARY KEY (`orders_o_id`, `products_p_id`),
  INDEX `fk_orders_has_products_products1_idx` (`products_p_id` ASC),
  INDEX `fk_orders_has_products_orders_idx` (`orders_o_id` ASC),
  CONSTRAINT `fk_orders_has_products_orders`
    FOREIGN KEY (`orders_o_id`)
    REFERENCES `knowyourincome`.`orders` (`o_id`),
  CONSTRAINT `fk_orders_has_products_products`
    FOREIGN KEY (`products_p_id`)
    REFERENCES `knowyourincome`.`products` (`p_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

DROP TABLE ordersHistory;
CREATE TABLE IF NOT EXISTS ordersHistory (
oh_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NOT NULL,
buyingPrice DOUBLE NOT NULL,
warranty DATE NOT NULL,
dateInit DATE NOT NULL
); 
CREATE TABLE IF NOT EXISTS soldunits (
s_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NULL,
sellingPrice DOUBLE NOT NULL,
date DATE NOT NULL,
cashier varchar(30) NOT NULL
);

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO users(name,surname,email,workedHours,password,position) VALUES ("Employee","Employee","employeekyi@gmail.com",123,"maturita2020",0),
("Employee2","Employee2","employeekyi2@gmail.com",143,"maturita2020",0);
INSERT INTO users(surname,email,password,position) VALUES ("owner","owner@example.com","owner","1");
INSERT INTO users(name,surname,email,password,position) VALUES ("Miroslav","Komar","komar@spse-po.sk","123","1");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Coca Cola 0,5L flaska","10",1,2,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Pivo Staropramen 11% flaska",35,1.50,2,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Fanta 0,5L flaska",3,1.30,1.90,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Fanta 0,5L flaska",20,0.69,1.90,"2020-08-31");

INSERT INTO orders(dateInit) VALUES ("2020-02-04");
INSERT INTO orders(dateInit) VALUES ("2020-02-06");
INSERT INTO orders(dateInit) VALUES ("2020-02-05");
INSERT INTO orders(dateInit) VALUES ("2020-02-08,");
INSERT INTO orders(dateInit) VALUES ("2020-02-10");
INSERT INTO orders(dateInit) VALUES ("2019-12-28");
INSERT INTO orders_has_products(orders_o_id,products_p_id,orderedQuantity) VALUES (4,1,3),(4,2,7),(2,1,6),(1,1,8),(1,2,9);
INSERT INTO orders_has_products(orders_o_id,products_p_id,orderedQuantity,deliverStatus) VALUES (7,3,20,1);
INSERT INTO ordersHistory (name,quantity,buyingPrice,warranty,dateInit) VALUES ("LOL",1,1,"2020-08-31","2014-12-30");



DELETE FROM products;
DELETE FROM orders_has_products;
DELETE FROM orders;
DELETE FROM ordersHistory;
Delete FROM soldunits;
DELETE FROM shifts;

select * from shifts;
SELECT * FROM users;
SELECT name, quantity* sellingPrice FROM products GROUP BY name;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM orders_has_products;


SELECT orders_o_id,products.name,products.buyingPrice,orders_has_products.orderedQuantity,products.warranty,orders.dateInit FROM orders_has_products JOIN products  ON (products_p_id = p_id) JOIN orders ON (orders_o_id = o_id) ORDER BY dateInit ASC;
UPDATE users SET isEntry = 1 where u_id =2;
UPDATE  products SET name = "", quantity = 1;
UPDATE shifts SET shiftColor = "black";
UPDATE users SET theme = "#b38b4d"; 
UPDATE shifts SET shiftTime = '20:20-13:30', shiftColor = '0xffffffff' WHERE layoutX = '87' AND layoutY = '84';
UPDATE orders_has_products JOIN orders ON (orders_o_id = o_id) JOIN products ON (products_p_id = p_id) SET deliverStatus = TRUE WHERE orders_o_id = 1 AND products_p_id = 1;

