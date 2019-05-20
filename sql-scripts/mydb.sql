-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb`;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`cryptocoin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cryptocoin` (
  `id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `symbol` VARCHAR(50) NOT NULL,
  `coin_rank` INT NOT NULL AUTO_INCREMENT,
  `price_usd` DOUBLE NOT NULL,
  `price_btc` DOUBLE NOT NULL,
  `24h_volume_usd` DOUBLE NOT NULL,
  `market_cap_usd` DOUBLE NOT NULL,
  `available_supply` DOUBLE NOT NULL,
  `total_supply` DOUBLE NOT NULL,
  `max_supply` DOUBLE,
  `percent_change_1h` DOUBLE NOT NULL,
  `percent_change_24h` DOUBLE NOT NULL,
  `percent_change_7d` DOUBLE NOT NULL,
  PRIMARY KEY (`coin_rank`))
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARACTER SET = utf8;


-- -----------------------------------------------------
-- Table `mydb`.`user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`user_roles`;
CREATE TABLE IF NOT EXISTS `mydb`.`user_roles` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;

INSERT INTO `user_roles` (role)
VALUES 
('USER'),('ADMIN');


-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users`;
CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `username` VARCHAR(50) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `password` CHAR(80) NOT NULL,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`username`))
  ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;
  
INSERT INTO `users` (username,password,first_name,last_name,email)
VALUES
('mirkelor','$2a$10$.tnqAohozdvoT2jjjSEbcOGiswkp4k68woMpMZxADmQfQGRO5LMeO','Hasan','Efe','mirkelor@gmail.com'),
('natalia','$2a$10$jxiQivfCwvn7pPtJr0kwfOvDMc1yei.lKkjWsa7iqyYTdLzb9R4cq','Natalia','Batman','natalia@gmail.com');

-- -----------------------------------------------------
-- Table `mydb`.`users_has_user_roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users_has_user_roles`;
CREATE TABLE IF NOT EXISTS `mydb`.`users_has_user_roles` (
  `users_username` VARCHAR(50) NOT NULL,
  `user_roles_role_id` INT NOT NULL,
  PRIMARY KEY (`users_username`, `user_roles_role_id`),
  INDEX `fk_users_has_user_roles_user_roles1_idx` (`user_roles_role_id` ASC),
  INDEX `fk_users_has_user_roles_users_idx` (`users_username` ASC),
  CONSTRAINT `fk_users_has_user_roles_user_roles1`
    FOREIGN KEY (`user_roles_role_id`)
    REFERENCES `mydb`.`user_roles` (`role_id`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_user_roles_users`
    FOREIGN KEY (`users_username`)
    REFERENCES `mydb`.`users` (`username`)
	ON DELETE NO ACTION
    ON UPDATE NO ACTION)
    DEFAULT CHARACTER SET = utf8;
    
INSERT INTO `users_has_user_roles` (users_username,user_roles_role_id)
VALUES 
('mirkelor', 1),
('mirkelor', 2),
('natalia', 1);

-- -----------------------------------------------------
-- Table `mydb`.`users_has_cryptocoin`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users_has_cryptocoin`;
CREATE TABLE IF NOT EXISTS `mydb`.`users_has_cryptocoin` (
  `users_username` VARCHAR(50) NOT NULL,
  `cryptocoin_coin_rank` INT NOT NULL,
  PRIMARY KEY (`users_username`, `cryptocoin_coin_rank`),
  INDEX `fk_users_has_cryptocoin_cryptocoin1_idx` (`cryptocoin_coin_rank` ASC),
  INDEX `fk_users_has_cryptocoin_users1_idx` (`users_username` ASC),
  CONSTRAINT `fk_users_has_cryptocoin_users1`
    FOREIGN KEY (`users_username`)
    REFERENCES `mydb`.`users` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_has_cryptocoin_cryptocoin1`
    FOREIGN KEY (`cryptocoin_coin_rank`)
    REFERENCES `mydb`.`cryptocoin` (`coin_rank`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
