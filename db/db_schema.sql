CREATE DATABASE IF NOT EXISTS `rate_utp_db` charset = utf8;

USE `rate_utp_db`;

CREATE TABLE IF NOT EXISTS users (
  user_id INT AUTO_INCREMENT PRIMARY KEY, 
  username VARCHAR(255) UNIQUE NOT NULL, 
  `password` VARCHAR(255) NOT NULL, 
  first_name  VARCHAR(255) NOT NULL, 
  last_name  VARCHAR(255) NOT NULL, 
  email  VARCHAR(255) NOT NULL, 
  `profile` ENUM ('student', 'professor') NOT NULL
);
