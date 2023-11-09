CREATE DATABASE IF NOT EXISTS `shopping_cart`;

USE `shopping_cart`;

DROP TABLE IF EXISTS `articulos`;

CREATE TABLE IF NOT EXISTS `articulos` (
    `id_articulo` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(1000) NOT NULL,
    `precio` FLOAT(10,2) NOT NULL,
    `cantidad` INT NOT NULL,
    `fotografia` LONGBLOOB NOT NULL,
    PRIMARY KEY(`id_articulo`)
);

DROP TABLE IF EXISTS `carrito_compra`;

CREATE TABLE IF NOT EXISTS `carrito_compra`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `id_articulo` INT NOT NULL,
    `cantidad` INT NOT NULL,
    CONSTRAINT `carrito_compra_ibfk_1` FOREIGN KEY (`id_articulo`) REFERENCES `articulos` (`id_articulos`) ON DELETE CASCADE
);
