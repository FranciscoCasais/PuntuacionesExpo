-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema puntajesExpo
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema puntajesExpo
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `puntajesExpo`; 
CREATE SCHEMA IF NOT EXISTS `puntajesExpo` ;
USE `puntajesExpo` ;

-- -----------------------------------------------------
-- Table `puntajesExpo`.`Juego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puntajesExpo`.`Juego` (
  `idJuego` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `descripcion` VARCHAR(45) NULL,
  PRIMARY KEY (`idJuego`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puntajesExpo`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puntajesExpo`.`Usuario` (
  `idUsuario` INT NOT NULL,
  `nombre` VARCHAR(45) NULL,
  `puntajeTotal` INT NULL,
  `direccionFoto` VARCHAR(100) NULL,
  PRIMARY KEY (`idUsuario`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `puntajesExpo`.`PuntajeJuego`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `puntajesExpo`.`PuntajeJuego` (
  `idPuntaje` INT NOT NULL,
  `puntajeJuego` INT NULL,
  `Juego_idJuego` INT NOT NULL,
  `Usuario_idUsuario` INT NOT NULL,
  PRIMARY KEY (`idPuntaje`, `Juego_idJuego`, `Usuario_idUsuario`),
  INDEX `fk_PuntajeJuego_Juego_idx` (`Juego_idJuego` ASC) VISIBLE,
  INDEX `fk_PuntajeJuego_Usuario1_idx` (`Usuario_idUsuario` ASC) VISIBLE,
  CONSTRAINT `fk_PuntajeJuego_Juego`
    FOREIGN KEY (`Juego_idJuego`)
    REFERENCES `puntajesExpo`.`Juego` (`idJuego`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_PuntajeJuego_Usuario1`
    FOREIGN KEY (`Usuario_idUsuario`)
    REFERENCES `puntajesExpo`.`Usuario` (`idUsuario`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

delimiter //
create procedure updatePuntos (in idUsuario_ int)
begin
declare puntosTotales int default 0;
select sum(puntajeJuego) into puntosTotales from PuntajeJuego where Usuario_idUsuario = idUsuario_;
update Usuario set puntajeTotal = puntosTotales where idUsuario = idUsuario_;
end//
delimiter ;

delimiter //
create trigger updatePuntosUpdateTrigger after update on PuntajeJuego
for each row
begin
call updatePuntos(new.Usuario_idUsuario);
end//
delimiter ;

delimiter //
create trigger updatePuntosInsertTrigger after insert on PuntajeJuego
for each row
begin
call updatePuntos(new.Usuario_idUsuario);
end//
delimiter ;
