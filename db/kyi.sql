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
profilePicture varchar(100)
);

DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (
p_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NOT NULL,
buyingPrice DOUBLE NOT NULL,
sellingPrice DOUBLE DEFAULT NULL,
warranty DATE NOT NULL
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
  orderedQuantity INT NOT NULL,
  deliverStatus BOOLEAN NOT NULL DEFAULT FALSE,
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

SET SQL_SAFE_UPDATES = 0;
SET FOREIGN_KEY_CHECKS=0;

INSERT INTO users(name,surname,email,workedHours,password,position) VALUES ("Employee","Employee","employeekyi@gmail.com",123,"maturita2020",0),
("Employee2","Employee2","employeekyi2@gmail.com",143,"maturita2020",0);
INSERT INTO users(surname,email,password,position) VALUES ("owner","owner@example.com","owner","1");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Coca Cola 0,5L flaska","10",1,2,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Pivo Staropramen 11% flaska",35,1.50,2,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Fanta 0,5L flaska",20,1.30,1.90,"2020-08-31");

INSERT INTO orders(dateInit) VALUES ("2020-02-04");
INSERT INTO orders(dateInit) VALUES ("2020-02-06");
INSERT INTO orders(dateInit) VALUES ("2020-02-05");
INSERT INTO orders(dateInit) VALUES ("2020-02-08,");
INSERT INTO orders(dateInit) VALUES ("2020-02-10");
INSERT INTO orders(dateInit) VALUES ("2019-12-28");
INSERT INTO orders_has_products(orders_o_id,products_p_id,orderedQuantity) VALUES (4,1,3),(4,2,7),(2,1,6),(1,1,8),(1,2,9);
INSERT INTO orders_has_products(orders_o_id,products_p_id,orderedQuantity,deliverStatus) VALUES (7,3,20,1);



DELETE FROM products;
DELETE FROM orders_has_products WHERE products_p_id = 3;
DELETE FROM orders;

SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM orders_has_products;

SELECT products.name,products.buyingPrice,orders_has_products.orderedQuantity,products.warranty,orders.dateInit FROM orders_has_products JOIN products  ON (products_p_id = p_id) JOIN orders ON (orders_o_id = o_id) ORDER BY dateInit ASC;


UPDATE orders_has_products JOIN orders ON (orders_o_id = o_id) JOIN products ON (products_p_id = p_id) SET deliverStatus = TRUE WHERE orders_o_id = 1 AND products_p_id = 1;

DELETE FROM orders_has_products WHERE orders_o_id = 1;
DELETE FROM orders WHERE dateInit = "2019-12-28";
