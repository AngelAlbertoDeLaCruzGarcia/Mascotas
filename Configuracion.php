<?php
	
	$_host = 'localhost';
	$_username = 'root';
	$_password = '';
	$_database = 'bdandroid';
	
	$conexion = new mysqli($_host, $_username, $_password, $_database);
			
	if ($conexion->connect_errno) {
		echo 'No se pudo conectar con el server';
		exit;
	}	
	
		
?>