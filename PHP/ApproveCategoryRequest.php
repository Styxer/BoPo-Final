<?php
    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo");   
	$category_name = $_POST["category_name"]; 
    $user_id = $_POST["user_id"]; 
	
    $statement = mysqli_prepare($con, "INSERT INTO category (category_name) VALUES (?)")
	or die(mysqli_error($con));

    mysqli_stmt_bind_param($statement, "s",$category_name)

	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
	
    $response = array(); 
    $response["success"] = true;  
    $response["category_name"] = $category_name;
	$response["user_id"] = $user_id;
	
    echo json_encode($response);
?>


