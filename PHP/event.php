<?php
    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") 
	or die(mysqli_error($con));
	
	$user_id = $_POST['user_id'];
    $event_id = $_POST['event_id'];
	$option = $_POST['option'];
	
	$ack_needed;
	
	if ($option == 1){///fetch events
	
	///////////////////////role/////////////////
	$result = mysqli_query( $con, "SELECT role FROM user_in_event WHERE user_id = '".$user_id."' AND event_id = '".$event_id."'")
 or die(mysqli_error($con));
													
													
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {
			$role = $row[0];
			
				
		//	echo "$role \n";
	  }
	} else {
	 // echo "no role\n";
	 $role = "unjoined";
	 //echo "$role \n";
	}	
	 ///////////////////////////////////////////////
	 
	 ///////////current_people in event/////////////////
	 	$result = mysqli_query( $con, "SELECT COUNT(event_id) FROM user_in_event WHERE event_id = '".$event_id."'")
 or die(mysqli_error($con));
													
													
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {
			$currentPeople = $row[0];
		//	echo "$currentPeople \n";
	  }
	} else {
	  echo "idiot\n";
	}	 
	 
	 
	 //////
	 
	$statement = mysqli_prepare($con, "SELECT * FROM event WHERE event_id = ? ")
	 or die(mysqli_error($con));	 
	 
	  mysqli_stmt_bind_param($statement, "i", $event_id)
	  or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
	
	    mysqli_stmt_store_result($statement);
	
    mysqli_stmt_bind_result($statement, $event_id, $event_location,$modreator_id, $category_name , $category_id
											, $ack_needed, $member_joined, $max_members, $event_time, $event_date, $event_description, $event_name)
	or die(mysqli_error($con));		
	
    mysqli_error($con);
    $response = array();
    $response['success'] = false;  
	
	    while(mysqli_stmt_fetch($statement) ){
        $response['success'] = true;  
     //   $response['event_id'] = $event_id;
		$response['event_location'] = $event_location;
       $response['category_name'] = $category_name;
       // $response['category_id'] = $category_id;
        $response['ack_needed'] = $ack_needed;
		$response['member_joined'] = $member_joined;
		$response['max_members'] = $max_members;
		$response['event_time'] = $event_time;
		$response['event_date'] = $event_date;
		$response['event_description'] = $event_description;
		$response['event_name'] = $event_name;
		$response['role'] = $role;
		$response['currentPeople'] = $currentPeople;
    }
    
     echo json_encode($response);		
		
	}//end fetch events
	
	else if($option == 2){// delete event
		$result = mysqli_query( $con, "DELETE FROM user_in_event where event_id = '".$event_id."'")
		or die(mysqli_error($con));
 
		//echo "row(s) delted from user in event \n";
 
		 $result = mysqli_query( $con, "DELETE FROM event where event_id = '".$event_id."'")
		 or die(mysqli_error($con));
 
	//	echo "row delted from user \n";
	 $response = array();
    $response['success'] = true;
	echo json_encode($response);	
		
	}//end delete event
	
	else if($option == 4){//join event
	
			$result = mysqli_query( $con, "SELECT ack_needed FROM event WHERE event_id = '".$event_id."'")
 or die(mysqli_error($con));
													
													
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {
			$ack_needed = $row[0];
			
	  }
	} else {
	  echo "no ack_needed\n";
	}	 
	
	if ($ack_needed === 'true')
		$role = 'waiting for ack';
	else if ($ack_needed === 'false')
		$role = 'participant';
	
	$statement = mysqli_prepare($con, "INSERT INTO user_in_event (user_id, event_id, role) VALUES (?, ? ,?)")
	or die(mysqli_error($con));
    mysqli_stmt_bind_param($statement, "iis",$user_id, $event_id, $role)
	or die(mysqli_error($con));
    mysqli_stmt_execute($statement);
    
    $response = array(); 
    $response["success"] = true;  
	$response["role"] = $role;  
    
    echo json_encode($response);
		
	}//end join event
	
	
	 mysqli_close($con);

	
	?>