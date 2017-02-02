<?php
$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
	$user_id = $_POST["user_id"];

    $statement1 = mysqli_prepare($con, "DELETE FROM user WHERE user_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement1, "s",$user_id);
    $result1=mysqli_stmt_execute($statement1);
    
    mysqli_stmt_store_result($statement1);
	
    $statement2 = mysqli_prepare($con, "DELETE FROM user_in_event WHERE user_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement2, "s",$user_id);
    $result2=mysqli_stmt_execute($statement2);
    
    mysqli_stmt_store_result($statement2);

    $statement3 = mysqli_prepare($con, "DELETE FROM user_in_ride WHERE user_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement3, "s",$user_id);
    $result3=mysqli_stmt_execute($statement3);
    
    mysqli_stmt_store_result($statement3);

	
     mysqli_error($con);
    $response = array();  
    
    if ($result1 && $result2 && $result3) {
        $response['success'] = true;  
    }
	else $response['success'] = false; 
    
     echo json_encode($response);

	 mysqli_close($con);

?>
