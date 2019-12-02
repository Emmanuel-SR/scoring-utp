CREATE DATABASE IF NOT EXISTS `scoring_utp_db` charset = utf8;

USE `scoring_utp_db`;

CREATE TABLE IF NOT EXISTS security_questions  (
  security_id INT AUTO_INCREMENT PRIMARY KEY, 
  security_text VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users (
  user_id INT AUTO_INCREMENT PRIMARY KEY, 
  username VARCHAR(255) UNIQUE NOT NULL, 
  `password` VARCHAR(255) NOT NULL, 
  first_name  VARCHAR(255) NOT NULL, 
  last_name  VARCHAR(255) NOT NULL, 
  email  VARCHAR(255) UNIQUE NOT NULL, 
  question_answer VARCHAR(255) NOT NULL, 
  security_id INT NOT NULL,
  FOREIGN KEY (security_id) REFERENCES security_questions(security_id) on update no action on delete no action
);

CREATE TABLE IF NOT EXISTS professors (
  professor_id INT AUTO_INCREMENT PRIMARY KEY,
  utp_code  VARCHAR(255) UNIQUE NOT NULL, 
  first_name  VARCHAR(255) NOT NULL, 
  last_name  VARCHAR(255) NOT NULL,
  faculty VARCHAR(255) NOT NULL  
);

CREATE TABLE IF NOT EXISTS scorings (
 scoring_id INT AUTO_INCREMENT PRIMARY KEY,
 course VARCHAR(255) NOT NULL,
 clarity  INT UNSIGNED NOT NULL,
 `help` INT UNSIGNED NOT NULL,
 facility INT UNSIGNED NOT NULL,
 recommend TINYINT NOT NULL,
 assistance TINYINT NOT NULL,
 `comment` TEXT NULL,
 student_score VARCHAR(255) NOT NULL,
 professor_id INT NOT NULL,
 user_id INT NOT NULL,
 CHECK (clarity<=5 AND clarity >= 0),
 CHECK ( `help` <=5 AND  `help`  >= 0),
 CHECK (facility<=5 AND facility >= 0),
 FOREIGN KEY (professor_id) REFERENCES professors(professor_id) on update cascade on delete cascade,
 FOREIGN KEY (user_id) REFERENCES users(user_id) on update cascade on delete cascade
);

