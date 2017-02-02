<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	//$role = $_POST['role'];
	//$user_id = $_POST['user_id'];

	
	
    $sql = "SELECT category_request.category_name, category_request.category_status, category_request.user_id, category_request.request_id 
			FROM category_request";

	
    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("category_name"=>$row["category_name"],"category_status"=>$row["category_status"],"user_id"=>$row["user_id"],"request_id"=>$row["request_id"]));
		}
       
    
    echo json_encode($response);
	
	
    mysqli_close($con);
	

    ?>