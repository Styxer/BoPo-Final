<?php 
		
	 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	$user_id = $_GET["user_id"];
	$query = "Select * from user where user_id between ($user_id+1) and ($user_id+21)";
	$result = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($result)) {
			
		$array[] = $row;	
	}
	header('Content-Type:Application/json');
	echo json_encode($array);
  
 ?>