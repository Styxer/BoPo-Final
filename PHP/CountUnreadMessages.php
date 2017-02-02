<?php

$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
	$user_id = $_POST["user_id"];

    $result = mysqli_query($con, "SELECT * FROM message WHERE message.user_id = '".$user_id."' AND message.status = 'unread'");
	$num_rows = $result->num_rows;
    $response = array();  
    
    if ($result) {
        $response['success'] = true;
		$response['unread_messages_number'] = $num_rows;  
    }
	else $response['success'] = false;  
    
     echo json_encode($response);

	 mysqli_close($con);
?>
