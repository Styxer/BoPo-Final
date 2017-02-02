<?php
    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo");   
	$user_id = $_POST["user_id"]; 
    $sender_id = $_POST["sender_id"];
	$event_id = $_POST["event_id"];
	$title = $_POST["title"];
	$description = $_POST["description"];
	
    $statement = mysqli_prepare($con, "INSERT INTO message (user_id, sender_id,event_id,title,description) VALUES (?,?,?,?,?)")
	or die(mysqli_error($con));

    mysqli_stmt_bind_param($statement, "sssss",$user_id, $sender_id,$event_id,$title,$description)

	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
	
    $response = array(); 
    $response["success"] = true;  
   // $response["category_name"] = $category_name;
    echo json_encode($response);
?>


