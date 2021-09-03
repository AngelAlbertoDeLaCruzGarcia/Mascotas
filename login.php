<?php
require_once('Configuracion.php');

$nom_user=$_POST['usuario'];
$nom_pass=$_POST['password'];


$myArray = array();
if ($result = $conexion->query("SELECT * FROM tblusuarios WHERE vchuser='$nom_user' AND vchpassword='$nom_pass'")) {

    while($row = $result->fetch_array(MYSQLI_ASSOC)) {
            $myArray[] = $row;
    }
    if(empty($myArray))
    {
    	unset($myArray);
    }
    
    echo json_encode($myArray,JSON_UNESCAPED_UNICODE);
}

$result->close();
$conexion->close();


?>