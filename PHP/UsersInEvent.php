<?php 
		
	$con =mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo")  or die(mysqli_error($con));
	
		$user_id = $_GET['user_id'];
	$event_id = $_GET['event_id'];
	
	
		$query = "SELECT COUNT(event_id) FROM user_in_event WHERE event_id = '".$event_id."'";
		
	$result = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($result)) {
			
		$num_of_rows = $row['COUNT(event_id)'];	
	}
	 json_encode($num_of_rows);
	
	$num_of_rows = intval($num_of_rows);

	
	//echo "$num_of_rows";
	
	$query = "SELECT user_in_event.role, user_in_event.user_id, user.name, user.email, user.birthday, user.phone_number, user.address, user.image
					FROM user LEFT JOIN user_in_event 
					ON user_in_event.user_id = user.user_id
					WHERE user_in_event.event_id  = '".$event_id."' 
					LIMIT $num_of_rows";
					
	
		$result = mysqli_query($con,$query)
		 or die(mysqli_error($con));
		 
		// print_r($query);
		 
		while ($row = mysqli_fetch_assoc($result)) { 
			$response[] = $row;	
		}
		
			

	
	header('Content-Type:Application/json');
	echo json_encode($response);
	/*	
$query = "SELECT user_in_event.role, user.name , user.email, user.phone_number, user.address, user.image
				FROM user_in_event,user WHERE user_in_event.event_id  IN ($event_id)";
	$result = mysqli_query($con,$query);
	while ($row = mysqli_fetch_assoc($result)) {
			
		$array[] = $row;	
	}
	
	$response = array_unique($array, SORT_REGULAR);
	echo json_encode($response);
	$ids = implode(",", (array)$array);
	$sql = "SELECT user_id,name,email,birthday,phone_number,address,image FROM user WHERE user_id IN ($ids)";
	  $result = mysqli_query($con,$sql)
	or die(mysqli_error($con));
	
	
	
	 while($row = mysqli_fetch_assoc($result))    
		{
					$types[] = $row;
			
		}
		
	echo json_encode($types);	
	
	
	//$user_id = $_POST['user_id'];
	$event_id = $_POST['event_id'];

	
    $result = mysqli_query($con,"SELECT user_id,role from user_in_event WHERE event_id =  '".$event_id."'")
	or die(mysqli_error($con));
	
	$response = array();
	
	
	
    while($row = mysqli_fetch_assoc($result))    
		{
			 array_push($response,array("role"=>$row["role"]));
			  $user_id[] = $row['user_id'];
			  //$roles[] = $row;
			
		}
		//  $ids = join(',', array_map('intval', $user_id));  
		$ids = implode(",", $user_id);
		 $json_user_id_information=json_encode($user_id);
		// $json_roles_information=json_encode($roles);
		//  unset($roles[0]);
		//  echo json_encode($user_id);
		// echo "$json_user_id_information\n";
		// echo "$json_roles_information\n";
	
 
	$sql = "SELECT user_id,name,email,birthday,address,image FROM user WHERE user_id IN ($ids)";

   //print_r($ids);
   $result = mysqli_query($con,$sql)
	or die(mysqli_error($con));
	
	
	
	 while($row = mysqli_fetch_assoc($result))    
		{
					$types[] = $row;
			
		}
		
	echo json_encode($types);	
		
	
	//echo  $json_user_information;
//	echo $json_roles_information
	
	
 
	
    //$json_user_information = array_merge_recursive(json_decode($json_user_information,true), json_decode($json_roles_information,true));
   
	//$json_user_information =  array_unique($json_user_information, SORT_STRING);
	

	//echo json_encode($json_user_information);	
	
	
	echo array_map(merge_arrays_by_index,(json_decode($json_user_information)), json_decode($json_roles_information));
	
	function merge_arrays_by_index($x, $y){
	
	$temp = array(); $temp[] = $x;
	if(is_scalar($y)) {	
		$temp[] = $y;
	}
	else{
		foreach($y as $k => $v){
			$temp[] = $v;
		}
	}	
return $temp;
} */
	
	
	
    mysqli_close($con);
	