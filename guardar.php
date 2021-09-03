<?php
require_once('Configuracion.php');

$nom_user=$_POST['usuario'];
$nom_pass=$_POST['password'];

$searchUser=mysqli_query($conexion,"SELECT vchuser FROM tblusuarios WHERE vchuser='$nom_user'");
$rows=($searchUser);

if(mysqli_num_rows($rows)==0)
{
    $myArray = array();
    if ($result = $conexion->query("INSERT INTO tblusuarios VALUES('$nom_user','$nom_pass')")) {
    
        while($row = $result->fetch_array(MYSQLI_ASSOC)) {
                $myArray[] = $row;
        }
        if(empty($myArray))
        {
        	unset($myArray);
        }
        
        echo json_encode($myArray,JSON_UNESCAPED_UNICODE);
    }
		
}

$result->close();
$conexion->close();