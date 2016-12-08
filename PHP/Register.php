<?php
    $con = mysqli_connect("localhost", "cellular_offir", "offirbraude123", "cellular_offir");   
	$role = $_POST["role"];
    $name = $_POST["name"];
	 $email = $_POST["email"];
	$birthday = $_POST["birthday"];
	$password = $_POST["password"]; 
	$phone_number = $_POST["phone_number"];
	$address = $_POST["address"];
       
	
    
	
    $statement = mysqli_prepare($con, "INSERT INTO user (role, name, email, birthday, password, phone_number, address) VALUES (?, ? ,?, ?, ?, ?, ?)")
	or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "sssssss",$role, $name, $email, $birthday, $password, $phone_number, $address)
	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
    
    $response = array(); 
    $response["success"] = true;  
    
    echo json_encode($response);
	
	mysqli_close($con);
?>

