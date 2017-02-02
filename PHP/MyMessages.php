<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	$user_id = $_POST['user_id'];

	
    $sql = "SELECT message.message_id, message.user_id,  message.sender_id, message.event_id, message.category_name, message.title, message.description, message.status 
	        FROM message WHERE message.user_id = '".$user_id."' ORDER BY status DESC";
			

    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("message_id"=>$row["message_id"],"user_id"=>$row["user_id"], "sender_id"=>$row["sender_id"],"event_id"=>$row["event_id"],"category_name"=>$row["category_name"],
			          "title"=>$row["title"], "description"=>$row["description"],"status"=>$row["status"],));
		}
       
    
    echo json_encode($response);
	
	
    mysqli_close($con);
	

    ?>