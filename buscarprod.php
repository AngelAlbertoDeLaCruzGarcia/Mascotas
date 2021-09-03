<?php
require_once('Configuracion.php');

//$nombre=$_POST['nombre'];


$myArray = array();
if ($result = $conexion->query("SELECT * FROM tblproductos WHERE vchnombre='a'")) {

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