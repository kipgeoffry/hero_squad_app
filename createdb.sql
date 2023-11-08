DROP DATABASE IF EXISTS hero_squad;
CREATE DATABASE hero_squad;
USE hero_squad;

SET NAMES utf8 ;
SET character_set_client = utf8mb4 ;

CREATE TABLE causes (
  cause_id int NOT NULL AUTO_INCREMENT,
  cause VARCHAR(100) NOT NULL,
  created_at DATETIME,
  PRIMARY KEY(cause_id)
);

CREATE TABLE weaknesses (
  weakness_id int NOT NULL AUTO_INCREMENT,
  weakness VARCHAR(100) NOT NULL,
  points int,
  created_at DATETIME,
  PRIMARY KEY(weakness_id)
);

CREATE TABLE powers(
  power_id int NOT NULL AUTO_INCREMENT,
  power_name VARCHAR(100) NOT NULL,
  points int NOT NULL,
  created_at DATETIME,
  PRIMARY KEY(power_id)
);

CREATE TABLE heroes (
  hero_id int NOT NULL AUTO_INCREMENT,
  hero_name VARCHAR(100) NOT NULL,
  age int,
  weakness_id int,
  power_id int,
  created_at DATETIME,
  PRIMARY KEY(hero_id),
  KEY FK_weakness_id (weakness_id),
  KEY FK_power_id (power_id),
  CONSTRAINT FK_weakness_id FOREIGN KEY (weakness_id) REFERENCES weaknesses (weakness_id) ON UPDATE CASCADE,
  CONSTRAINT FK_power_id FOREIGN KEY (power_id) REFERENCES powers (power_id)  ON UPDATE CASCADE
);

CREATE TABLE squads (
  squad_id int NOT NULL AUTO_INCREMENT,
  squad_name VARCHAR(100) NOT NULL,
  squad_max_size int NOT NULL,
  cause_id int,
  created_at DATETIME,
  PRIMARY KEY(squad_id),
  KEY FK_cause_id (cause_id),
  CONSTRAINT FK_cause_id FOREIGN KEY (cause_id) REFERENCES causes (cause_id)  ON UPDATE CASCADE
);

CREATE TABLE squad_membership (
  squad_id int,
  hero_id int,
  squad_total int,
  created_at DATETIME,
  KEY FK_hero_id (hero_id),
  KEY FK_squad_id (squad_id),
  CONSTRAINT FK_hero_id FOREIGN KEY (hero_id) REFERENCES heroes (hero_id)  ON UPDATE CASCADE,
  CONSTRAINT FK_squad_id FOREIGN KEY (squad_id) REFERENCES squads (squad_id)  ON UPDATE CASCADE
  );

