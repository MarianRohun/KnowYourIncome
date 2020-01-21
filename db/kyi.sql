CREATE DATABASE IF NOT EXISTS knowyourincome
DEFAULT CHARACTER SET 'utf8'
DEFAULT COLLATE 'utf8_general_ci';
USE knowyourincome;

CREATE TABLE IF NOT EXISTS users (
u_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(30) NOT NULL,
surname varchar(30)NOT NULL,
email varchar(50) UNIQUE NOT NULL,
workedHours int NOT NULL,
password varchar(30) NOT NULL,
position INT(1) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
p_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(50) NOT NULL,
quantity INT NOT NULL,
buyingPrice DOUBLE NOT NULL,
sellingPrice DOUBLE NOT NULL,
dateOfDelivery DATE NOT NULL,
warranty DATE NOT NULL
);

SET SQL_SAFE_UPDATES = 0;

INSERT INTO users(name,surname,email,workedHours,password,position) VALUES ("Employee","Employee","employeekyi@gmail.com",123,"maturita2020",0),
("Employee2","Employee2","employeekyi2@gmail.com",143,"maturita2020",0);

DROP TABLE users;
SELECT * FROM users;
SELECT * FROM products;

