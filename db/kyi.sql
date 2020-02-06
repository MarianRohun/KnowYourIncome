CREATE DATABASE IF NOT EXISTS knowyourincome
DEFAULT CHARACTER SET 'utf8'
DEFAULT COLLATE 'utf8_general_ci';
USE knowyourincome;

CREATE TABLE IF NOT EXISTS users (
u_id INT AUTO_INCREMENT PRIMARY KEY,
surname varchar(30) UNIQUE NOT NULL,
email varchar(50) UNIQUE NOT NULL,
password varchar(30) NOT NULL,
position INT(1) NOT NULL
);

DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (
p_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NOT NULL,
buyingPrice DOUBLE NOT NULL,
sellingPrice DOUBLE NOT NULL,
warranty DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS orders (
o_id INT AUTO_INCREMENT PRIMARY KEY,
dateInit DATE
);

DROP TABLE `knowyourincome`.`orders_has_products`;
CREATE TABLE IF NOT EXISTS `knowyourincome`.`orders_has_products` (
  `orders_o_id` INT(11) NOT NULL,
  `products_p_id` INT(11) NOT NULL,
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

INSERT INTO users(surname,email,password,position) VALUES ("employee","employee@example.com","employee","0");
INSERT INTO users(surname,email,password,position) VALUES ("owner","owner@example.com","owner","1");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Coca Cola 0,5L flaska","10",1,2,"2020-08-31");
INSERT INTO products(name,quantity,buyingPrice,sellingPrice,warranty) VALUES ("Pivo Staropramen 11% flaska",35,1.50,2,"2020-08-31");

INSERT INTO orders(dateInit) VALUES ("2020-02-04");
INSERT INTO orders(dateInit) VALUES ("2020-02-06");
INSERT INTO orders(dateInit) VALUES ("2020-02-07");
INSERT INTO orders(dateInit) VALUES ("2020-02-08");

INSERT INTO orders_has_products(orders_o_id,products_p_id) VALUES (4,2);


DELETE FROM products;

SELECT * FROM users;
SELECT * FROM products;
SELECT * FROM orders;
SELECT * FROM orders_has_products;

SELECT products.name,products.quantity,products.buyingPrice,products.sellingPrice,products.warranty,orders.dateInit FROM orders_has_products JOIN products  ON (products_p_id = p_id) JOIN orders ON (orders_o_id = o_id) WHERE orders_o_id = '1';


