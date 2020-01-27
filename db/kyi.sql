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

INSERT INTO users(surname,email,password,position) VALUES ("admin","admin@example.com","admin","2");

SELECT * FROM users;
SELECT * FROM products;

