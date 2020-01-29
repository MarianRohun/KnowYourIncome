CREATE DATABASE IF NOT EXISTS knowyourincome
DEFAULT CHARACTER SET 'utf8'
DEFAULT COLLATE 'utf8_general_ci';
USE knowyourincome;

CREATE TABLE IF NOT EXISTS users (
u_id INT AUTO_INCREMENT PRIMARY KEY,
name varchar(30) NOT NULL,
surname varchar(30) NOT NULL,
email varchar(50) UNIQUE NOT NULL,
workedHours int NOT NULL DEFAULT 0,
password varchar(30) NOT NULL DEFAULT "",
position INT(1) NOT NULL,
profilePicture varchar(100)
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

INSERT INTO users(name,surname,email,workedHours,position) VALUES ("Employee","Employee","employeekyi@gmail.com",123,0), ("Employee2","Employee2","employeekyi2@gmail.com",123,0);
INSERT INTO users(name,surname,email,password,position) VALUES ("Owner","Owner","ownerkyi@gmail.com","maturita2020",1);
DROP TABLE users;


DROP TABLE users;
SELECT * FROM users;
SELECT * FROM products;

