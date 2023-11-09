DROP database IF exists `database_shopping_cart`;

CREATE DATABASE IF NOT EXISTS `database_shopping_cart`;

USE `database_shopping_cart`;

DROP TABLE IF EXISTS `articulos`;

CREATE TABLE IF NOT EXISTS `articulos` (
    `id_articulo` INT NOT NULL AUTO_INCREMENT,
    `description` VARCHAR(1000) NOT NULL,
    `precio` FLOAT NOT NULL,
    `cantidad` INT NOT NULL,
    `fotografia` LONGBLOB NOT NULL,
    PRIMARY KEY(`id_articulo`)
);

DROP TABLE IF EXISTS `carrito_compra`;

CREATE TABLE IF NOT EXISTS `carrito_compra`(
    `id` INT NOT NULL AUTO_INCREMENT,
    `cantidad` INT NOT NULL,
    `id_articulo` INT NOT NULL,
    PRIMARY KEY(`id`),
    FOREIGN KEY (`id_articulo`) REFERENCES `articulos` (`id_articulo`) ON DELETE CASCADE
);
