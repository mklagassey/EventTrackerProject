-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema jobsdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `jobsdb` ;

-- -----------------------------------------------------
-- Schema jobsdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `jobsdb` DEFAULT CHARACTER SET utf8 ;
USE `jobsdb` ;

-- -----------------------------------------------------
-- Table `company`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `company` ;

CREATE TABLE IF NOT EXISTS `company` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(1000) NULL,
  `size` VARCHAR(25) NULL,
  `phone` VARCHAR(15) NULL,
  `recruiting_for` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_company_company1_idx` (`recruiting_for` ASC),
  CONSTRAINT `fk_company_company1`
    FOREIGN KEY (`recruiting_for`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `location`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `location` ;

CREATE TABLE IF NOT EXISTS `location` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `country` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `category` ;

CREATE TABLE IF NOT EXISTS `category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job` ;

CREATE TABLE IF NOT EXISTS `job` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(250) NOT NULL,
  `name` VARCHAR(50) NULL,
  `pay` INT NULL,
  `skills` VARCHAR(200) NULL,
  `description` TEXT NULL,
  `company_id` INT NOT NULL,
  `location_id` INT NULL,
  `category_id` INT NULL,
  `posted` DATETIME NULL,
  `updated` DATETIME NULL,
  `active` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  INDEX `fk_job_company1_idx` (`company_id` ASC),
  INDEX `fk_job_location1_idx` (`location_id` ASC),
  INDEX `fk_job_category1_idx` (`category_id` ASC),
  CONSTRAINT `fk_job_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_category1`
    FOREIGN KEY (`category_id`)
    REFERENCES `category` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_seeker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_seeker` ;

CREATE TABLE IF NOT EXISTS `job_seeker` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(1000) NULL,
  `resume` TEXT NULL,
  `email` VARCHAR(45) NULL,
  `location_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_job_seeker_location1_idx` (`location_id` ASC),
  CONSTRAINT `fk_job_seeker_location1`
    FOREIGN KEY (`location_id`)
    REFERENCES `location` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_seeker_has_job`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_seeker_has_job` ;

CREATE TABLE IF NOT EXISTS `job_seeker_has_job` (
  `job_seeker_id` INT NOT NULL,
  `job_id` INT NOT NULL,
  PRIMARY KEY (`job_seeker_id`, `job_id`),
  INDEX `fk_job_seeker_has_job_job1_idx` (`job_id` ASC),
  INDEX `fk_job_seeker_has_job_job_seeker_idx` (`job_seeker_id` ASC),
  CONSTRAINT `fk_job_seeker_has_job_job_seeker`
    FOREIGN KEY (`job_seeker_id`)
    REFERENCES `job_seeker` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_seeker_has_job_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `contact` ;

CREATE TABLE IF NOT EXISTS `contact` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `phone` VARCHAR(15) NULL,
  `title` VARCHAR(45) NULL,
  `position` VARCHAR(250) NULL,
  `notes` TEXT NULL,
  `company_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_contact_company1_idx` (`company_id` ASC),
  CONSTRAINT `fk_contact_company1`
    FOREIGN KEY (`company_id`)
    REFERENCES `company` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `job_has_contact`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `job_has_contact` ;

CREATE TABLE IF NOT EXISTS `job_has_contact` (
  `job_id` INT NOT NULL,
  `contact_id` INT NOT NULL,
  PRIMARY KEY (`job_id`, `contact_id`),
  INDEX `fk_job_has_contact_contact1_idx` (`contact_id` ASC),
  INDEX `fk_job_has_contact_job1_idx` (`job_id` ASC),
  CONSTRAINT `fk_job_has_contact_job1`
    FOREIGN KEY (`job_id`)
    REFERENCES `job` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_job_has_contact_contact1`
    FOREIGN KEY (`contact_id`)
    REFERENCES `contact` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS jobuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'jobuser'@'localhost' IDENTIFIED BY 'jobuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'jobuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `company`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `company` (`id`, `name`, `description`, `size`, `phone`, `recruiting_for`) VALUES (1, 'Garbage Inc', 'Picking up your trash like we were your mom', 'Medium', '18005551234', NULL);
INSERT INTO `company` (`id`, `name`, `description`, `size`, `phone`, `recruiting_for`) VALUES (2, 'Prestige Worldwide', 'The best and most fabulous company ever!', 'Huge', NULL, NULL);
INSERT INTO `company` (`id`, `name`, `description`, `size`, `phone`, `recruiting_for`) VALUES (3, 'Randomtech', 'Totally faceless but we know everything about you', 'Large', '18006666666', NULL);
INSERT INTO `company` (`id`, `name`, `description`, `size`, `phone`, `recruiting_for`) VALUES (4, 'Mom & Pop\'s corner shoppe', 'We put the \"e\" to sound fance', 'Small', '123456789', NULL);
INSERT INTO `company` (`id`, `name`, `description`, `size`, `phone`, `recruiting_for`) VALUES (5, 'Headhunters & Associates', 'Taking names and collecting noggins for the right price', 'Small', '18008008000', 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `location`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (1, 'remote', 'remote', 'remote');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (2, 'washington', 'district of columbia', 'united states');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (3, 'new york', 'new york', 'united states');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (4, 'los angeles', 'california', 'united states');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (5, 'austin', 'texas', 'united states');
INSERT INTO `location` (`id`, `city`, `state`, `country`) VALUES (6, 'denver', 'colorado', 'united states');

COMMIT;


-- -----------------------------------------------------
-- Data for table `category`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `category` (`id`, `name`) VALUES (1, 'programming');
INSERT INTO `category` (`id`, `name`) VALUES (2, 'sanitation');
INSERT INTO `category` (`id`, `name`) VALUES (3, 'entertainment');
INSERT INTO `category` (`id`, `name`) VALUES (4, 'sundries');
INSERT INTO `category` (`id`, `name`) VALUES (5, 'sales');

COMMIT;


-- -----------------------------------------------------
-- Data for table `job`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `job` (`id`, `title`, `name`, `pay`, `skills`, `description`, `company_id`, `location_id`, `category_id`, `posted`, `updated`, `active`) VALUES (1, 'junior java developer', '', 100000, 'java, sql', 'an exciting opportunity to make money and not starve', 3, 2, 1, '2021-08-01T18:44:17.048', NULL, DEFAULT);
INSERT INTO `job` (`id`, `title`, `name`, `pay`, `skills`, `description`, `company_id`, `location_id`, `category_id`, `posted`, `updated`, `active`) VALUES (2, 'garbage collector', NULL, 85000, 'lifting heavy things, smiling', 'one mans trash is another mans paycheck', 1, 3, 2, '2021-08-01T18:44:17.048', NULL, DEFAULT);
INSERT INTO `job` (`id`, `title`, `name`, `pay`, `skills`, `description`, `company_id`, `location_id`, `category_id`, `posted`, `updated`, `active`) VALUES (3, 'ceo', NULL, 1000000, 'being born to the right family', 'do you want to be in charge of the whole company? we\'ve got the job for you!', 2, 4, 3, '2021-08-01T18:44:17.048', NULL, DEFAULT);
INSERT INTO `job` (`id`, `title`, `name`, `pay`, `skills`, `description`, `company_id`, `location_id`, `category_id`, `posted`, `updated`, `active`) VALUES (4, 'secretary', NULL, 44000, 'typing, knowing things, keeping losers out of bosses office', 'if you can type 500 words a minute and have 50 years experience keeping undesirables out of bosses offices, please apply', 4, 5, 4, '2021-08-01T18:44:17.048', NULL, DEFAULT);
INSERT INTO `job` (`id`, `title`, `name`, `pay`, `skills`, `description`, `company_id`, `location_id`, `category_id`, `posted`, `updated`, `active`) VALUES (5, 'salesperson', NULL, 1000, 'smiling, shaking hands, selling stuff', 'succesfull company wants motivated seller to sell its stuff so it can be more succesfull', 3, 1, 5, '2021-08-01T18:44:17.048', NULL, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_seeker`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `job_seeker` (`id`, `first_name`, `last_name`, `title`, `description`, `resume`, `email`, `location_id`) VALUES (1, 'mick', 'lagassey', 'student', 'all around nice guy', NULL, 'mick@gmail.com', 1);
INSERT INTO `job_seeker` (`id`, `first_name`, `last_name`, `title`, `description`, `resume`, `email`, `location_id`) VALUES (2, 'joe', 'shmo', 'lackey', 'needs a job, like now', NULL, 'bob@job.now', 2);
INSERT INTO `job_seeker` (`id`, `first_name`, `last_name`, `title`, `description`, `resume`, `email`, `location_id`) VALUES (3, 'jane', 'doe', 'grad', 'gonna get that job', NULL, 'jane@smart.com', 4);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_seeker_has_job`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (1, 1);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (1, 3);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (2, 2);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (2, 4);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (1, 5);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (3, 1);
INSERT INTO `job_seeker_has_job` (`job_seeker_id`, `job_id`) VALUES (3, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `email`, `phone`, `title`, `position`, `notes`, `company_id`) VALUES (1, 'sara', 'smith', 'sara@headhunters.biz', '18882223333', 'recruiter', NULL, NULL, 5);
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `email`, `phone`, `title`, `position`, `notes`, `company_id`) VALUES (2, 'bob', 'boberson', 'bob@pww.com', '17776665555', 'chairman', 'decision maker', NULL, 2);
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `email`, `phone`, `title`, `position`, `notes`, `company_id`) VALUES (3, 'mom', 'jones', NULL, '12344325555', 'co-owner', 'partner', NULL, 4);
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `email`, `phone`, `title`, `position`, `notes`, `company_id`) VALUES (4, 'phil', 'landers', 'philanders@garbage.inc', '15558886363', 'boss', 'boss', NULL, 1);
INSERT INTO `contact` (`id`, `first_name`, `last_name`, `email`, `phone`, `title`, `position`, `notes`, `company_id`) VALUES (5, 'debby', 'nerderson', 'd@randomtech.com', NULL, 'team leader', 'hiring manager', NULL, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `job_has_contact`
-- -----------------------------------------------------
START TRANSACTION;
USE `jobsdb`;
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (1, 1);
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (1, 5);
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (2, 4);
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (3, 2);
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (4, 2);
INSERT INTO `job_has_contact` (`job_id`, `contact_id`) VALUES (5, 3);

COMMIT;

