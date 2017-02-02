<?php
$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
	$message_id = $_POST["message_id"];
 	$new_status = $_POST["new_status"];

	
    
    $statement = mysqli_prepare($con, "UPDATE message SET status = ? WHERE message_id = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement, "ss", $new_status, $message_id);
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
