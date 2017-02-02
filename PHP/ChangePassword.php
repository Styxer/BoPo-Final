<?php
$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
	$user_id = $_POST["user_id"];
    $password = $_POST["password"];

	
    
    $statement = mysqli_prepare($con, "UPDATE user SET password = ? WHERE user_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement, "ss", $password, $user_id);
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
