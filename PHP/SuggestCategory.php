<?php
    $con = mysqli_connect("localhost", "cellular_offir", "offirbraude123", "cellular_offir");   
	$category_name = $_POST["category_name"]; 
	$user_id = $_POST["user_id"]; 
	
    $statement = mysqli_prepare($con, "INSERT INTO category_request (category_name,user_id) VALUES (?,?)")
	or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "si",$category_name,$user_id)
	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
    
    $response = array(); 
    $response["success"] = true;  
    
    echo json_encode($response);
?>

