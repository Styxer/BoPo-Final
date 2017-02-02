<?php
$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
	$user_id = $_POST["user_id"];
    $name = $_POST["name"];
	$email = $_POST["email"];
	$birthday = $_POST["birthday"];
	$phone_number = $_POST["phone_number"];
	$address = $_POST["address"];
	$image = $_POST["image"];
    
    $statement = mysqli_prepare($con, "UPDATE user SET name = ?, email = ?, birthday = ?, phone_number = ?, address = ?, image = ? WHERE user_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement, "sssssss", $name, $email, $birthday, $phone_number, $address, $image, $user_id);
    $result=mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
	
	
    mysqli_error($con);
    $response = array();  
    
    if ($result) {
        $response['success'] = true;  
    }
	else $response['success'] = false;  
    
     echo json_encode($response);

	 mysqli_close($con);

?>
