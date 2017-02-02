<?php
    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
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

