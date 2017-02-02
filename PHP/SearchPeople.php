<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	$name = $_POST['name'];
	
	$sql = "SELECT user.name, user.user_id, user.image FROM user
    WHERE user.name LIKE '%".$name."%'";
	
    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("name"=>$row["name"], "user_id"=>$row["user_id"], "image"=>$row["image"]));
		}
       
    
    echo json_encode($response);
	
	
    mysqli_close($con);
	

    ?>