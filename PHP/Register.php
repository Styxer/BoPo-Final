<?php
    $con = mysqli_connect("localhost", "cellular_offir", "offirbraude123", "cellular_offir");
    
    $name = $_POST["name"];
	$username = $_POST["username"];
    $age = $_POST["age"];    
    $password = $_POST["password"];
    $statement = mysqli_prepare($con, "INSERT INTO users (name, username, age, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "siss", $name, $username, $age, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>