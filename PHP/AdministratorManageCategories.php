<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	//$event_name = $_POST['event_name'];

    $sql = "SELECT category_request.user_id, category_request.category_name, category_request.category_status FROM category_request";

    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("category_name"=>$row["category_name"],"user_id"=>$row["user_id"],"category_status"=>$row["category_status"] ));
		}
       
    echo json_encode($response);
    mysqli_close($con);
    ?>