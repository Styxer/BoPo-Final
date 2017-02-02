<?php
    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo");   
	$request_id = $_POST["request_id"]; 
    $category_name = $_POST["category_name"]; 
	$user_id = $_POST["user_id"];
	
    $statement = mysqli_prepare($con, "DELETE FROM category_request WHERE request_id = ?")
	or die(mysqli_error($con));

    mysqli_stmt_bind_param($statement, "i",$request_id)

	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
	
    $response = array(); 
    $response["success"] = true;  
    $response["category_name"] = $category_name;
    $response["user_id"] = $user_id;
	 
    echo json_encode($response);
?>

