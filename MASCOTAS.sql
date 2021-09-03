/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.3.15-MariaDB : Database - bd_mascotas
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`bd_mascotas` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `bd_mascotas`;

/*Table structure for table `tbl_categorias` */

DROP TABLE IF EXISTS `tbl_categorias`;

CREATE TABLE `tbl_categorias` (
  `ID_Cat` int(11) NOT NULL AUTO_INCREMENT,
  `Categoria` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Cat`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_categorias` */

/*Table structure for table `tbl_compras` */

DROP TABLE IF EXISTS `tbl_compras`;

CREATE TABLE `tbl_compras` (
  `ID_Compra` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Prov` int(11) DEFAULT NULL,
  `Fecha` datetime DEFAULT current_timestamp(),
  `Total` float DEFAULT 0,
  PRIMARY KEY (`ID_Compra`),
  KEY `ID_Prov` (`ID_Prov`),
  CONSTRAINT `tbl_compras_ibfk_1` FOREIGN KEY (`ID_Prov`) REFERENCES `tbl_proveedores` (`ID_Prov`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_compras` */

/*Table structure for table `tbl_comprasdetalle` */

DROP TABLE IF EXISTS `tbl_comprasdetalle`;

CREATE TABLE `tbl_comprasdetalle` (
  `ID_CompraD` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Compra` int(11) NOT NULL,
  `ID_Prod` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `PrecioC` float NOT NULL,
  `Subtotal` float GENERATED ALWAYS AS (`PrecioC` * `Cantidad`) VIRTUAL,
  `Fecha` datetime DEFAULT current_timestamp(),
  PRIMARY KEY (`ID_CompraD`),
  KEY `ID_Prod` (`ID_Prod`),
  KEY `ID_Compra` (`ID_Compra`),
  CONSTRAINT `tbl_comprasdetalle_ibfk_1` FOREIGN KEY (`ID_Prod`) REFERENCES `tbl_productos` (`ID_Producto`) ON DELETE CASCADE,
  CONSTRAINT `tbl_comprasdetalle_ibfk_2` FOREIGN KEY (`ID_Compra`) REFERENCES `tbl_compras` (`ID_Compra`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_comprasdetalle` */

/*Table structure for table `tbl_consultas` */

DROP TABLE IF EXISTS `tbl_consultas`;

CREATE TABLE `tbl_consultas` (
  `ID_Consulta` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre_mascota` varchar(50) DEFAULT NULL,
  `Peso` float DEFAULT NULL,
  `Sexo` enum('M','F') DEFAULT NULL,
  `ID_Raza` int(11) DEFAULT NULL,
  `Edad` int(11) DEFAULT NULL,
  `Especie` varchar(60) DEFAULT NULL,
  `Fecha_Consulta` datetime DEFAULT current_timestamp(),
  `Contenido` text DEFAULT NULL,
  `Fecha_Prox_Consulta` date DEFAULT NULL,
  `ID_Mascota` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Consulta`),
  KEY `ID_Raza` (`ID_Raza`),
  KEY `ID_Mascota` (`ID_Mascota`),
  CONSTRAINT `tbl_consultas_ibfk_1` FOREIGN KEY (`ID_Raza`) REFERENCES `tbl_razas` (`ID_Raza`) ON DELETE CASCADE,
  CONSTRAINT `tbl_consultas_ibfk_2` FOREIGN KEY (`ID_Mascota`) REFERENCES `tbl_mascotas` (`ID_Mascota`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_consultas` */

/*Table structure for table `tbl_dueno_mas` */

DROP TABLE IF EXISTS `tbl_dueno_mas`;

CREATE TABLE `tbl_dueno_mas` (
  `ID_Dueno` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `Direccion` varchar(50) DEFAULT NULL,
  `Ubicacion_geog` varchar(50) DEFAULT NULL,
  `Telefono` char(12) DEFAULT NULL,
  `Email` varchar(70) DEFAULT NULL,
  PRIMARY KEY (`ID_Dueno`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_dueno_mas` */

insert  into `tbl_dueno_mas`(`ID_Dueno`,`Nombre`,`Direccion`,`Ubicacion_geog`,`Telefono`,`Email`) values (1,'Angel','a','','a','a'),(2,'Angel','a',NULL,'a','a');

/*Table structure for table `tbl_estado_vnta` */

DROP TABLE IF EXISTS `tbl_estado_vnta`;

CREATE TABLE `tbl_estado_vnta` (
  `ID_Estado` int(11) NOT NULL AUTO_INCREMENT,
  `Estado` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`ID_Estado`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_estado_vnta` */

/*Table structure for table `tbl_marcas` */

DROP TABLE IF EXISTS `tbl_marcas`;

CREATE TABLE `tbl_marcas` (
  `ID_Marca` int(11) NOT NULL AUTO_INCREMENT,
  `Marca` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_Marca`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_marcas` */

/*Table structure for table `tbl_mascotas` */

DROP TABLE IF EXISTS `tbl_mascotas`;

CREATE TABLE `tbl_mascotas` (
  `ID_Mascota` int(11) NOT NULL AUTO_INCREMENT,
  `Foto` varchar(30) DEFAULT NULL,
  `Matricula` varchar(10) DEFAULT NULL,
  `Nombre_Paciente` varchar(80) DEFAULT NULL,
  `Edad` date DEFAULT NULL,
  `Sexo` char(1) DEFAULT NULL,
  `Especie` varchar(50) DEFAULT NULL,
  `ID_Raza` int(11) DEFAULT NULL,
  `ID_Dueno` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID_Mascota`),
  KEY `ID_Raza` (`ID_Raza`),
  KEY `ID_Dueno` (`ID_Dueno`),
  CONSTRAINT `tbl_mascotas_ibfk_1` FOREIGN KEY (`ID_Raza`) REFERENCES `tbl_razas` (`ID_Raza`) ON DELETE CASCADE,
  CONSTRAINT `tbl_mascotas_ibfk_2` FOREIGN KEY (`ID_Dueno`) REFERENCES `tbl_dueno_mas` (`ID_Dueno`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_mascotas` */

/*Table structure for table `tbl_ofertas` */

DROP TABLE IF EXISTS `tbl_ofertas`;

CREATE TABLE `tbl_ofertas` (
  `ID_Oferta` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Marca` int(11) DEFAULT NULL,
  `ID_Categoria` int(11) DEFAULT NULL,
  `Porc_Descuento` float NOT NULL,
  `Fecha_Inicio` datetime DEFAULT current_timestamp(),
  `Fecha_Final` datetime NOT NULL,
  PRIMARY KEY (`ID_Oferta`),
  KEY `ID_Marca` (`ID_Marca`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `tbl_ofertas_ibfk_1` FOREIGN KEY (`ID_Marca`) REFERENCES `tbl_marcas` (`ID_Marca`),
  CONSTRAINT `tbl_ofertas_ibfk_2` FOREIGN KEY (`ID_Categoria`) REFERENCES `tbl_categorias` (`ID_Cat`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_ofertas` */

/*Table structure for table `tbl_productos` */

DROP TABLE IF EXISTS `tbl_productos`;

CREATE TABLE `tbl_productos` (
  `ID_Producto` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(80) DEFAULT NULL,
  `fechaing` datetime DEFAULT current_timestamp(),
  `ID_Marca` int(11) DEFAULT NULL,
  `ID_Categoria` int(11) DEFAULT NULL,
  `Descripcion` text DEFAULT NULL,
  `PrecioV` float DEFAULT 0,
  `GananciaActual` float DEFAULT 0,
  `Existencia` int(11) DEFAULT 0,
  `Imagen` varchar(50) DEFAULT NULL,
  `GananciacDesc` float DEFAULT 0,
  `Descuento` int(11) DEFAULT 0,
  `IdentOferta` int(11) DEFAULT 0,
  PRIMARY KEY (`ID_Producto`),
  KEY `ID_Marca` (`ID_Marca`),
  KEY `ID_Categoria` (`ID_Categoria`),
  CONSTRAINT `tbl_productos_ibfk_1` FOREIGN KEY (`ID_Marca`) REFERENCES `tbl_marcas` (`ID_Marca`) ON DELETE CASCADE,
  CONSTRAINT `tbl_productos_ibfk_2` FOREIGN KEY (`ID_Categoria`) REFERENCES `tbl_categorias` (`ID_Cat`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_productos` */

/*Table structure for table `tbl_proveedores` */

DROP TABLE IF EXISTS `tbl_proveedores`;

CREATE TABLE `tbl_proveedores` (
  `ID_Prov` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(60) NOT NULL,
  `Empresa` varchar(50) NOT NULL,
  `Direccion` char(30) DEFAULT NULL,
  `Ciudad` varchar(40) DEFAULT NULL,
  `Telefono` char(13) DEFAULT NULL,
  PRIMARY KEY (`ID_Prov`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_proveedores` */

/*Table structure for table `tbl_razas` */

DROP TABLE IF EXISTS `tbl_razas`;

CREATE TABLE `tbl_razas` (
  `ID_Raza` int(11) NOT NULL AUTO_INCREMENT,
  `Costo_Raza` float DEFAULT NULL,
  `Nombre_Raza` varchar(50) DEFAULT NULL,
  `Imagen` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_Raza`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `tbl_razas` */

insert  into `tbl_razas`(`ID_Raza`,`Costo_Raza`,`Nombre_Raza`,`Imagen`) values (1,10,'Chihuahua','Android/imgRazas/chihuahuas.jpg'),(2,10,'Pastor Aleman','Android/imgRazas/pastor-aleman.jpg'),(3,5,'Pitbul','Android/imgRazas/pitbul.jpg');

/*Table structure for table `tbl_servicios` */

DROP TABLE IF EXISTS `tbl_servicios`;

CREATE TABLE `tbl_servicios` (
  `ID_Servicio` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo_serv` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`ID_Servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_servicios` */

/*Table structure for table `tbl_tam_mascota` */

DROP TABLE IF EXISTS `tbl_tam_mascota`;

CREATE TABLE `tbl_tam_mascota` (
  `ID_Tam` int(11) NOT NULL AUTO_INCREMENT,
  `Tam` varchar(50) DEFAULT NULL,
  `Costo_Tam` float DEFAULT NULL,
  PRIMARY KEY (`ID_Tam`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_tam_mascota` */

/*Table structure for table `tbl_usuarios` */

DROP TABLE IF EXISTS `tbl_usuarios`;

CREATE TABLE `tbl_usuarios` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(50) DEFAULT NULL,
  `APaterno` varchar(50) DEFAULT NULL,
  `AMaterno` varchar(50) DEFAULT NULL,
  `Email` varchar(70) DEFAULT NULL,
  `Telefono` char(10) DEFAULT NULL,
  `Usuario` varchar(30) NOT NULL,
  `Pass` varchar(30) NOT NULL,
  `Tipo_user` enum('cajero','administrador') DEFAULT 'cajero',
  PRIMARY KEY (`id_user`),
  UNIQUE KEY `Usuario` (`Usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_usuarios` */

/*Table structure for table `tbl_ventas` */

DROP TABLE IF EXISTS `tbl_ventas`;

CREATE TABLE `tbl_ventas` (
  `ID_Venta` int(11) NOT NULL AUTO_INCREMENT,
  `Fecha` datetime DEFAULT current_timestamp(),
  `Total` float NOT NULL DEFAULT 0,
  `Usuario` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`ID_Venta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_ventas` */

/*Table structure for table `tbl_ventasdetalle` */

DROP TABLE IF EXISTS `tbl_ventasdetalle`;

CREATE TABLE `tbl_ventasdetalle` (
  `ID_VentaD` int(11) NOT NULL AUTO_INCREMENT,
  `ID_Venta` int(11) DEFAULT NULL,
  `ID_Prod` int(11) NOT NULL,
  `Cantidad` int(11) NOT NULL,
  `Subtotal` float DEFAULT NULL,
  PRIMARY KEY (`ID_VentaD`),
  KEY `ID_Prod` (`ID_Prod`),
  KEY `ID_Venta` (`ID_Venta`),
  CONSTRAINT `tbl_ventasdetalle_ibfk_1` FOREIGN KEY (`ID_Prod`) REFERENCES `tbl_productos` (`ID_Producto`) ON DELETE CASCADE,
  CONSTRAINT `tbl_ventasdetalle_ibfk_2` FOREIGN KEY (`ID_Venta`) REFERENCES `tbl_ventas` (`ID_Venta`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_ventasdetalle` */

/*Table structure for table `tbl_vnta_servicios` */

DROP TABLE IF EXISTS `tbl_vnta_servicios`;

CREATE TABLE `tbl_vnta_servicios` (
  `ID_vnta_serv` int(11) NOT NULL AUTO_INCREMENT,
  `Tipo_serv` int(11) DEFAULT NULL,
  `ID_Dueno` int(11) DEFAULT NULL,
  `ID_Raza` int(11) DEFAULT NULL,
  `ID_Tam` int(11) DEFAULT NULL,
  `Total` float DEFAULT 0,
  `ID_Estado_vnta` int(11) DEFAULT 4,
  `Fecha_de_solicitud` datetime DEFAULT NULL,
  `Fecha_de_atencion` date DEFAULT NULL,
  `Hora_de_atencion` time DEFAULT NULL,
  PRIMARY KEY (`ID_vnta_serv`),
  KEY `ID_Estado_vnta` (`ID_Estado_vnta`),
  KEY `Tipo_serv` (`Tipo_serv`),
  KEY `ID_Dueno` (`ID_Dueno`),
  KEY `ID_Raza` (`ID_Raza`),
  KEY `ID_Tam` (`ID_Tam`),
  CONSTRAINT `tbl_vnta_servicios_ibfk_1` FOREIGN KEY (`ID_Estado_vnta`) REFERENCES `tbl_estado_vnta` (`ID_Estado`) ON DELETE CASCADE,
  CONSTRAINT `tbl_vnta_servicios_ibfk_2` FOREIGN KEY (`Tipo_serv`) REFERENCES `tbl_servicios` (`ID_Servicio`) ON DELETE CASCADE,
  CONSTRAINT `tbl_vnta_servicios_ibfk_3` FOREIGN KEY (`ID_Dueno`) REFERENCES `tbl_dueno_mas` (`ID_Dueno`) ON DELETE CASCADE,
  CONSTRAINT `tbl_vnta_servicios_ibfk_4` FOREIGN KEY (`ID_Raza`) REFERENCES `tbl_razas` (`ID_Raza`) ON DELETE CASCADE,
  CONSTRAINT `tbl_vnta_servicios_ibfk_5` FOREIGN KEY (`ID_Tam`) REFERENCES `tbl_tam_mascota` (`ID_Tam`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `tbl_vnta_servicios` */

/* Trigger structure for table `tbl_comprasdetalle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trgCOMPRAP` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trgCOMPRAP` AFTER INSERT ON `tbl_comprasdetalle` FOR EACH ROW BEGIN
	DECLARE Subtotal float;
	UPDATE tbl_Productos set GananciaActual=new.PrecioC*0.3, PrecioV=new.PrecioC*1.3, 
    Existencia=new.Cantidad+Existencia where ID_Producto=new.ID_Prod;
	set @Subtotal:=(SELECT Subtotal from tbl_ComprasDetalle where ID_Prod=new.ID_Prod);
	UPDATE tbl_Compras set Total=Total+@Subtotal where ID_Compra=new.ID_Compra;
END */$$


DELIMITER ;

/* Trigger structure for table `tbl_ofertas` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trgOfertas` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trgOfertas` AFTER INSERT ON `tbl_ofertas` FOR EACH ROW BEGIN
	IF(new.Porc_Descuento>0)
		THEN
			IF(new.Fecha_Final>=new.Fecha_Inicio)
				THEN
					IF(new.Porc_Descuento<=50)
						THEN
							IF(CURRENT_TIMESTAMP>=new.Fecha_Inicio and CURRENT_TIMESTAMP<=new.Fecha_Final)
								THEN
								UPDATE tbl_Productos set IdentOferta=1, GananciacDesc=GananciacActual-GananciaActual*new.Porc_Descuento/100, PrecioV=PrecioC+GananciaActual, Descuento=GananciaActual-(GananciacActual-GananciaActual*new.Porc_Descuento/100), PrecioV=PrecioV-Descuento where ID_Marca=new.ID_Marca;
                                UPDATE tbl_Productos set IdentOferta=1, GananciacDesc=GananciacActual-GananciaActual*new.Porc_Descuento/100, PrecioV=PrecioC+GananciaActual, Descuento=GananciaActual-(GananciacActual-GananciaActual*new.Porc_Descuento/100), PrecioV=PrecioV-Descuento where ID_Categoria=new.ID_Categoria;
							ELSE
								UPDATE tbl_Productos set IdentOferta=0, GananciacDesc=0, Descuento=0, PrecioV=PrecioC+GananciaActual where ID_Marca=new.ID_Marca;
                                UPDATE tbl_Productos set IdentOferta=0, GananciacDesc=0, Descuento=0, PrecioV=PrecioC+GananciaActual where ID_Categoria=new.ID_Categoria;
							end if;
					end if;
			end if;
	end if;
	IF(new.Porc_Descuento<=0 or new.Porc_Descuento>50 or new.Fecha_Final<new.Fecha_Inicio or new.Fecha_Inicio<CURRENT_TIMESTAMP or new.Fecha_Final<CURRENT_TIMESTAMP)
		THEN
			DELETE from tbl_Ofertas where ID_Oferta=new.ID_Oferta;
	end if;
END */$$


DELIMITER ;

/* Trigger structure for table `tbl_ventasdetalle` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `trgVentaP` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `trgVentaP` AFTER INSERT ON `tbl_ventasdetalle` FOR EACH ROW BEGIN
	DECLARE precioV float;
	UPDATE tbl_Productos set Existencia=Existencia-new.Cantidad where ID_Producto=new.ID_Prod;
    set @precioV:=(SELECT PrecioV from tbl_Productos where ID_Producto=new.ID_Prod);
    UPDATE tbl_ventasdetalle set Subtotal=@PrecioV*new.Cantidad where ID_VentaD=new.ID_VentaD;
    UPDATE tbl_Ventas set Total=Total+Subtotal;
END */$$


DELIMITER ;

/* Procedure structure for procedure `PA_CLIVEM_SOLICITUDSERV` */

/*!50003 DROP PROCEDURE IF EXISTS  `PA_CLIVEM_SOLICITUDSERV` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`localhost` PROCEDURE `PA_CLIVEM_SOLICITUDSERV`( IN ID_Serv int)
BEGIN
	Select * from tbl_vnta_servicios where ID_vnta_serv = ID_Serv;
END */$$
DELIMITER ;

/*Table structure for table `view_clivem_clientesmascota` */

DROP TABLE IF EXISTS `view_clivem_clientesmascota`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_clientesmascota` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_clientesmascota` */;

/*!50001 CREATE TABLE  `view_clivem_clientesmascota`(
 `ID_Dueno` int(11) ,
 `Nombre` varchar(50) ,
 `Direccion` varchar(50) ,
 `Telefono` char(12) ,
 `ID_Mascota` int(11) ,
 `ID_Raza` int(11) ,
 `Nombre_Raza` varchar(50) ,
 `Nombre_Paciente` varchar(80) ,
 `Especie` varchar(50) ,
 `Matricula` varchar(10) ,
 `Sexo` char(1) ,
 `Edad` date ,
 `Foto` varchar(30) 
)*/;

/*Table structure for table `view_clivem_compras` */

DROP TABLE IF EXISTS `view_clivem_compras`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_compras` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_compras` */;

/*!50001 CREATE TABLE  `view_clivem_compras`(
 `ID_Compra` int(11) ,
 `ID_CompraD` int(11) ,
 `ID_Producto` int(11) ,
 `ID_Prov` int(11) ,
 `NombreProd` varchar(80) ,
 `Nombre` varchar(60) ,
 `PrecioC` float ,
 `Subtotal` float ,
 `Cantidad` int(11) ,
 `Total` float ,
 `Fecha` datetime 
)*/;

/*Table structure for table `view_clivem_consultas` */

DROP TABLE IF EXISTS `view_clivem_consultas`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_consultas` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_consultas` */;

/*!50001 CREATE TABLE  `view_clivem_consultas`(
 `ID_Consulta` int(11) ,
 `Nombre_mascota` varchar(50) ,
 `Peso` float ,
 `Sexo` enum('M','F') ,
 `Nombre_Raza` varchar(50) ,
 `Edad` int(11) ,
 `Especie` varchar(60) ,
 `Fecha_Consulta` datetime ,
 `Contenido` text ,
 `Fecha_Prox_Consulta` date ,
 `ID_Mascota` int(11) 
)*/;

/*Table structure for table `view_clivem_productos` */

DROP TABLE IF EXISTS `view_clivem_productos`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_productos` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_productos` */;

/*!50001 CREATE TABLE  `view_clivem_productos`(
 `ID_Producto` int(11) ,
 `fechaing` datetime ,
 `Nombre` varchar(80) ,
 `Marca` varchar(100) ,
 `Categoria` varchar(100) ,
 `Descripcion` text ,
 `PrecioV` float ,
 `Imagen` varchar(50) ,
 `Existencia` int(11) ,
 `GananciaActual` float ,
 `IdentOferta` int(11) ,
 `GananciacDesc` float ,
 `Descuento` int(11) 
)*/;

/*Table structure for table `view_clivem_solcserv` */

DROP TABLE IF EXISTS `view_clivem_solcserv`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_solcserv` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_solcserv` */;

/*!50001 CREATE TABLE  `view_clivem_solcserv`(
 `ID_vnta_serv` int(11) ,
 `Tipo_serv` int(11) ,
 `Nombre` varchar(50) ,
 `Direccion` varchar(50) ,
 `Ubicacion_geog` varchar(50) ,
 `Telefono` char(12) ,
 `Email` varchar(70) ,
 `Nombre_Raza` varchar(50) ,
 `Costo_Raza` float ,
 `Tam` varchar(50) ,
 `Costo_Tam` float ,
 `Total` float ,
 `ID_Estado` int(11) ,
 `Estado` varchar(80) 
)*/;

/*Table structure for table `view_clivem_ventas` */

DROP TABLE IF EXISTS `view_clivem_ventas`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_ventas` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_ventas` */;

/*!50001 CREATE TABLE  `view_clivem_ventas`(
 `ID_Venta` int(11) ,
 `ID_VentaD` int(11) ,
 `ID_Producto` int(11) ,
 `Nombre` varchar(80) ,
 `PrecioV` float ,
 `Cantidad` int(11) ,
 `Existencia` int(11) ,
 `Fecha` datetime ,
 `Subtotal` float 
)*/;

/*Table structure for table `view_clivem_vntaservicios` */

DROP TABLE IF EXISTS `view_clivem_vntaservicios`;

/*!50001 DROP VIEW IF EXISTS `view_clivem_vntaservicios` */;
/*!50001 DROP TABLE IF EXISTS `view_clivem_vntaservicios` */;

/*!50001 CREATE TABLE  `view_clivem_vntaservicios`(
 `ID_vnta_serv` int(11) ,
 `Tipo_serv` int(11) ,
 `Nombre` varchar(50) ,
 `Direccion` varchar(50) ,
 `Ubicacion_geog` varchar(50) ,
 `Telefono` char(12) ,
 `Email` varchar(70) ,
 `Nombre_Raza` varchar(50) ,
 `Costo_Raza` float ,
 `Tam` varchar(50) ,
 `Costo_Tam` float ,
 `Total` float ,
 `ID_Estado` int(11) ,
 `Estado` varchar(80) 
)*/;

/*View structure for view view_clivem_clientesmascota */

/*!50001 DROP TABLE IF EXISTS `view_clivem_clientesmascota` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_clientesmascota` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_clientesmascota` AS select `tbl_dueno_mas`.`ID_Dueno` AS `ID_Dueno`,`tbl_dueno_mas`.`Nombre` AS `Nombre`,`tbl_dueno_mas`.`Direccion` AS `Direccion`,`tbl_dueno_mas`.`Telefono` AS `Telefono`,`tbl_mascotas`.`ID_Mascota` AS `ID_Mascota`,`tbl_mascotas`.`ID_Raza` AS `ID_Raza`,`tbl_razas`.`Nombre_Raza` AS `Nombre_Raza`,`tbl_mascotas`.`Nombre_Paciente` AS `Nombre_Paciente`,`tbl_mascotas`.`Especie` AS `Especie`,`tbl_mascotas`.`Matricula` AS `Matricula`,`tbl_mascotas`.`Sexo` AS `Sexo`,`tbl_mascotas`.`Edad` AS `Edad`,`tbl_mascotas`.`Foto` AS `Foto` from ((`tbl_dueno_mas` join `tbl_mascotas` on(`tbl_dueno_mas`.`ID_Dueno` = `tbl_mascotas`.`ID_Dueno`)) join `tbl_razas` on(`tbl_mascotas`.`ID_Raza` = `tbl_razas`.`ID_Raza`)) */;

/*View structure for view view_clivem_compras */

/*!50001 DROP TABLE IF EXISTS `view_clivem_compras` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_compras` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_compras` AS select `tbl_compras`.`ID_Compra` AS `ID_Compra`,`tbl_comprasdetalle`.`ID_CompraD` AS `ID_CompraD`,`tbl_productos`.`ID_Producto` AS `ID_Producto`,`tbl_proveedores`.`ID_Prov` AS `ID_Prov`,`tbl_productos`.`Nombre` AS `NombreProd`,`tbl_proveedores`.`Nombre` AS `Nombre`,`tbl_comprasdetalle`.`PrecioC` AS `PrecioC`,`tbl_comprasdetalle`.`Subtotal` AS `Subtotal`,`tbl_comprasdetalle`.`Cantidad` AS `Cantidad`,`tbl_compras`.`Total` AS `Total`,`tbl_compras`.`Fecha` AS `Fecha` from (((`tbl_productos` join `tbl_comprasdetalle` on(`tbl_productos`.`ID_Producto` = `tbl_comprasdetalle`.`ID_Prod`)) join `tbl_compras` on(`tbl_comprasdetalle`.`ID_Compra` = `tbl_compras`.`ID_Compra`)) join `tbl_proveedores` on(`tbl_compras`.`ID_Prov` = `tbl_proveedores`.`ID_Prov`)) */;

/*View structure for view view_clivem_consultas */

/*!50001 DROP TABLE IF EXISTS `view_clivem_consultas` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_consultas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_consultas` AS select `tbl_consultas`.`ID_Consulta` AS `ID_Consulta`,`tbl_consultas`.`Nombre_mascota` AS `Nombre_mascota`,`tbl_consultas`.`Peso` AS `Peso`,`tbl_consultas`.`Sexo` AS `Sexo`,`tbl_razas`.`Nombre_Raza` AS `Nombre_Raza`,`tbl_consultas`.`Edad` AS `Edad`,`tbl_consultas`.`Especie` AS `Especie`,`tbl_consultas`.`Fecha_Consulta` AS `Fecha_Consulta`,`tbl_consultas`.`Contenido` AS `Contenido`,`tbl_consultas`.`Fecha_Prox_Consulta` AS `Fecha_Prox_Consulta`,`tbl_consultas`.`ID_Mascota` AS `ID_Mascota` from (`tbl_consultas` join `tbl_razas` on(`tbl_consultas`.`ID_Raza` = `tbl_razas`.`ID_Raza`)) */;

/*View structure for view view_clivem_productos */

/*!50001 DROP TABLE IF EXISTS `view_clivem_productos` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_productos` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_productos` AS select `tbl_productos`.`ID_Producto` AS `ID_Producto`,`tbl_productos`.`fechaing` AS `fechaing`,`tbl_productos`.`Nombre` AS `Nombre`,`tbl_marcas`.`Marca` AS `Marca`,`tbl_categorias`.`Categoria` AS `Categoria`,`tbl_productos`.`Descripcion` AS `Descripcion`,`tbl_productos`.`PrecioV` AS `PrecioV`,`tbl_productos`.`Imagen` AS `Imagen`,`tbl_productos`.`Existencia` AS `Existencia`,`tbl_productos`.`GananciaActual` AS `GananciaActual`,`tbl_productos`.`IdentOferta` AS `IdentOferta`,`tbl_productos`.`GananciacDesc` AS `GananciacDesc`,`tbl_productos`.`Descuento` AS `Descuento` from ((`tbl_productos` join `tbl_marcas` on(`tbl_productos`.`ID_Marca` = `tbl_marcas`.`ID_Marca`)) join `tbl_categorias` on(`tbl_productos`.`ID_Categoria` = `tbl_categorias`.`ID_Cat`)) */;

/*View structure for view view_clivem_solcserv */

/*!50001 DROP TABLE IF EXISTS `view_clivem_solcserv` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_solcserv` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_solcserv` AS select `view_clivem_vntaservicios`.`ID_vnta_serv` AS `ID_vnta_serv`,`view_clivem_vntaservicios`.`Tipo_serv` AS `Tipo_serv`,`view_clivem_vntaservicios`.`Nombre` AS `Nombre`,`view_clivem_vntaservicios`.`Direccion` AS `Direccion`,`view_clivem_vntaservicios`.`Ubicacion_geog` AS `Ubicacion_geog`,`view_clivem_vntaservicios`.`Telefono` AS `Telefono`,`view_clivem_vntaservicios`.`Email` AS `Email`,`view_clivem_vntaservicios`.`Nombre_Raza` AS `Nombre_Raza`,`view_clivem_vntaservicios`.`Costo_Raza` AS `Costo_Raza`,`view_clivem_vntaservicios`.`Tam` AS `Tam`,`view_clivem_vntaservicios`.`Costo_Tam` AS `Costo_Tam`,`view_clivem_vntaservicios`.`Total` AS `Total`,`view_clivem_vntaservicios`.`ID_Estado` AS `ID_Estado`,`view_clivem_vntaservicios`.`Estado` AS `Estado` from `view_clivem_vntaservicios` where `view_clivem_vntaservicios`.`ID_Estado` = 4 or `view_clivem_vntaservicios`.`ID_Estado` = 2 */;

/*View structure for view view_clivem_ventas */

/*!50001 DROP TABLE IF EXISTS `view_clivem_ventas` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_ventas` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_ventas` AS select `tbl_ventas`.`ID_Venta` AS `ID_Venta`,`tbl_ventasdetalle`.`ID_VentaD` AS `ID_VentaD`,`tbl_productos`.`ID_Producto` AS `ID_Producto`,`tbl_productos`.`Nombre` AS `Nombre`,`tbl_productos`.`PrecioV` AS `PrecioV`,`tbl_ventasdetalle`.`Cantidad` AS `Cantidad`,`tbl_productos`.`Existencia` AS `Existencia`,`tbl_ventas`.`Fecha` AS `Fecha`,`tbl_ventasdetalle`.`Subtotal` AS `Subtotal` from ((`tbl_productos` join `tbl_ventasdetalle` on(`tbl_productos`.`ID_Producto` = `tbl_ventasdetalle`.`ID_Prod`)) join `tbl_ventas` on(`tbl_ventasdetalle`.`ID_Venta` = `tbl_ventas`.`ID_Venta`)) */;

/*View structure for view view_clivem_vntaservicios */

/*!50001 DROP TABLE IF EXISTS `view_clivem_vntaservicios` */;
/*!50001 DROP VIEW IF EXISTS `view_clivem_vntaservicios` */;

/*!50001 CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_clivem_vntaservicios` AS select `tbl_vnta_servicios`.`ID_vnta_serv` AS `ID_vnta_serv`,`tbl_vnta_servicios`.`Tipo_serv` AS `Tipo_serv`,`tbl_dueno_mas`.`Nombre` AS `Nombre`,`tbl_dueno_mas`.`Direccion` AS `Direccion`,`tbl_dueno_mas`.`Ubicacion_geog` AS `Ubicacion_geog`,`tbl_dueno_mas`.`Telefono` AS `Telefono`,`tbl_dueno_mas`.`Email` AS `Email`,`tbl_razas`.`Nombre_Raza` AS `Nombre_Raza`,`tbl_razas`.`Costo_Raza` AS `Costo_Raza`,`tbl_tam_mascota`.`Tam` AS `Tam`,`tbl_tam_mascota`.`Costo_Tam` AS `Costo_Tam`,`tbl_vnta_servicios`.`Total` AS `Total`,`tbl_estado_vnta`.`ID_Estado` AS `ID_Estado`,`tbl_estado_vnta`.`Estado` AS `Estado` from (((((`tbl_vnta_servicios` join `tbl_servicios` on(`tbl_vnta_servicios`.`Tipo_serv` = `tbl_servicios`.`ID_Servicio`)) join `tbl_dueno_mas` on(`tbl_vnta_servicios`.`ID_Dueno` = `tbl_dueno_mas`.`ID_Dueno`)) join `tbl_razas` on(`tbl_vnta_servicios`.`ID_Raza` = `tbl_razas`.`ID_Raza`)) join `tbl_tam_mascota` on(`tbl_vnta_servicios`.`ID_Tam` = `tbl_tam_mascota`.`ID_Tam`)) join `tbl_estado_vnta` on(`tbl_vnta_servicios`.`ID_Estado_vnta` = `tbl_estado_vnta`.`ID_Estado`)) */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
