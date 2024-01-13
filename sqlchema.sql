-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema flixsa
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema flixsa
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `flixsa` DEFAULT CHARACTER SET utf8 ;
USE `flixsa` ;

-- -----------------------------------------------------
-- Table `flixsa`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20) NULL,
  `profile_image` VARCHAR(255) NULL,
  `password` VARCHAR(255) NOT NULL,
  `country` VARCHAR(50) NULL,
  `state` VARCHAR(50) NULL,
  `city` VARCHAR(50) NULL,
  `street` VARCHAR(50) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`cinemas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`cinemas` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `phone` VARCHAR(20) NULL,
  `location` POINT NULL,
  `country` VARCHAR(50) NULL,
  `state` VARCHAR(50) NULL,
  `city` VARCHAR(50) NULL,
  `postal_code` VARCHAR(20) NULL,
  `street` VARCHAR(50) NULL,
  `profile_image` VARCHAR(255) NULL,
  `about` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`staff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`staff` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(50) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `cinema_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_staff_cinemas1_idx` (`cinema_id` ASC) VISIBLE,
  CONSTRAINT `fk_staff_cinemas1`
    FOREIGN KEY (`cinema_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  `permissions` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`staffroles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`staffroles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `staff_id` BIGINT NOT NULL,
  `role_id` BIGINT NOT NULL,
  `userrolescol` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_userroles_staff_idx` (`staff_id` ASC) VISIBLE,
  INDEX `fk_userroles_roles1_idx` (`role_id` ASC) VISIBLE,
  CONSTRAINT `fk_userroles_staff`
    FOREIGN KEY (`staff_id`)
    REFERENCES `flixsa`.`staff` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_userroles_roles1`
    FOREIGN KEY (`role_id`)
    REFERENCES `flixsa`.`roles` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`movies`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`movies` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `backdrop_path` VARCHAR(255) NULL,
  `original_title` VARCHAR(50) NULL,
  `overview` TEXT NULL,
  `original_language` VARCHAR(45) NULL,
  `popularity` DECIMAL(20,10) NULL,
  `poster_path` VARCHAR(255) NULL,
  `release_date` VARCHAR(20) NULL,
  `title` VARCHAR(50) NULL,
  `vote_average` DECIMAL(2,2) NULL,
  `vote_count` INT NULL,
  `users_id` BIGINT NOT NULL,
  `cinemas_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movies_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_movies_cinemas1_idx` (`cinemas_id` ASC) VISIBLE,
  CONSTRAINT `fk_movies_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `flixsa`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movies_cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`genres`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`genres` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `users_id` BIGINT NOT NULL,
  `movies_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_genres_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_genres_movies1_idx` (`movies_id` ASC) VISIBLE,
  CONSTRAINT `fk_genres_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `flixsa`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_genres_movies1`
    FOREIGN KEY (`movies_id`)
    REFERENCES `flixsa`.`movies` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`snackcategories`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`snackcategories` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`snacks`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`snacks` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `availability` TINYINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `price` DECIMAL(10,2) NULL,
  `quanity` INT NULL,
  `cinemas_id` BIGINT NOT NULL,
  `snackcategories_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_snacks_cinemas1_idx` (`cinemas_id` ASC) VISIBLE,
  INDEX `fk_snacks_snackcategories1_idx` (`snackcategories_id` ASC) VISIBLE,
  CONSTRAINT `fk_snacks_cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_snacks_snackcategories1`
    FOREIGN KEY (`snackcategories_id`)
    REFERENCES `flixsa`.`snackcategories` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`movieprices`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`movieprices` (
  `id` BIGINT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `movies_id` BIGINT NOT NULL,
  `cinemas_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_movieprices_movies1_idx` (`movies_id` ASC) VISIBLE,
  INDEX `fk_movieprices_cinemas1_idx` (`cinemas_id` ASC) VISIBLE,
  CONSTRAINT `fk_movieprices_movies1`
    FOREIGN KEY (`movies_id`)
    REFERENCES `flixsa`.`movies` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_movieprices_cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`orders`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`orders` (
  `id` BIGINT NOT NULL,
  `name` VARCHAR(50) NOT NULL,
  `total_amount` DECIMAL(10,2) NULL,
  `users_id` BIGINT NOT NULL,
  `cinemas_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_orders_users1_idx` (`users_id` ASC) VISIBLE,
  INDEX `fk_orders_cinemas1_idx` (`cinemas_id` ASC) VISIBLE,
  CONSTRAINT `fk_orders_users1`
    FOREIGN KEY (`users_id`)
    REFERENCES `flixsa`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_orders_cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`tickets` (
  `id` BIGINT NOT NULL,
  `movies_id` BIGINT NOT NULL,
  `orders_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_tickets_movies1_idx` (`movies_id` ASC) VISIBLE,
  INDEX `fk_tickets_orders1_idx` (`orders_id` ASC) VISIBLE,
  CONSTRAINT `fk_tickets_movies1`
    FOREIGN KEY (`movies_id`)
    REFERENCES `flixsa`.`movies` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_tickets_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `flixsa`.`orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `flixsa`.`transactions`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `flixsa`.`transactions` (
  `id` BIGINT NOT NULL,
  `orders_id` BIGINT NOT NULL,
  `cinemas_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_transactions_orders1_idx` (`orders_id` ASC) VISIBLE,
  INDEX `fk_transactions_cinemas1_idx` (`cinemas_id` ASC) VISIBLE,
  CONSTRAINT `fk_transactions_orders1`
    FOREIGN KEY (`orders_id`)
    REFERENCES `flixsa`.`orders` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_transactions_cinemas1`
    FOREIGN KEY (`cinemas_id`)
    REFERENCES `flixsa`.`cinemas` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
