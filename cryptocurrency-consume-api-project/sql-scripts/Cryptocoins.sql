CREATE DATABASE  IF NOT EXISTS `mydb`;
USE `mydb`;

-- MySQL Workbench Forward Engineering

-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP TABLE IF EXISTS `cryptocoin`;
-- CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`Cryptocoin`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `cryptocoin` (
  `id` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `symbol` VARCHAR(45) NOT NULL,
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
ENGINE = InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;




-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
