<?php
  $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo")   or die("cannot connect");  


/*	$event_name = $_POST["event_name"];
    $event_description = $_POST["event_description"];
	 $event_date = $_POST["event_date"];
	$event_time = $_POST["event_time"];
	$max_members = $_POST["max_members"]; 
	$ack_needed = $_POST["ack_needed"];
	$category_name = $_POST["category_name"];
	$event_location = $_POST["event_location"]; */
	


	
	/*$event_name = (isset($_POST['event_name']) ? $_POST['event_name'] : '');
	$event_description = (isset($_POST['event_description']) ? $_POST['event_description'] : '');
	$event_date = (isset($_POST['event_date']) ? $_POST['event_date'] : '');
	$event_time = (isset($_POST['event_time']) ? $_POST['event_time'] : '');
	$max_members = (isset($_POST['max_members']) ? $_POST['max_members'] : '');
	$ack_needed = (isset($_POST['ack_needed']) ? $_POST['ack_needed'] : '');
	$category_name = (isset($_POST['category_name']) ? $_POST['category_name'] : '');
	$event_location = (isset($_POST['event_location']) ? $_POST['event_location'] : '');
	$moderator_id = (isset($_POST['moderator_id']) ? $_POST['moderator_id'] : '');
	$choice = (isset($_POST['choice']) ? $_POST['choice'] : '');*/
	
	$event_name = $_POST['event_name'];
    $event_description = $_POST['event_description'];
	$event_date = $_POST['event_date'];
    $event_time = $_POST['event_time'];
	$max_members = $_POST['max_members'];
	$ack_needed = $_POST['ack_needed'];
    $category_name = $_POST['category_name'];
	$event_location = $_POST['event_location'];
    $moderator_id = $_POST['moderator_id'];
	 $choice = $_POST['choice'];
	 
	  
	 $category_id = getCategoryId($con, $category_name, $moderator_id);
	 //echo "$category_id/n";

	  
	
	//get category_id to events table
	//$result = mysqli_query( $con, "SELECT category_request.request_id FROM category_request, event, user 
									//				WHERE category_request.category_name = '".$category_name."' AND user.user_id = '".$moderator_id."'");
								//	
								//echo "$moderator_id \n $category_name  \n";
								
								
	if ($choice == 1){ //create new event			

	global $category_id; 
	
    
	// put paramters in event tabe
    $statement = mysqli_prepare($con, 	
	"INSERT INTO event (event_name, event_description, event_date,
									event_time, max_members, ack_needed, 
									 category_id ,category_name, moderator_id,event_location) 
																						VALUES (?, ?, ?, ? ,?, ?, ?, ?, ?, ?)")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement, "ssssssisis", $event_name, $event_description, $event_date, 
																				$event_time, $max_members, $ack_needed, 
																				$category_id, $category_name, $moderator_id,$event_location)
	or die(mysqli_error($con));
    mysqli_stmt_execute($statement)
	or die(mysqli_error($con)); 
	
	//put event id in user_in_event table
		//get category_id from events

				
	$event_id_create = getEventId($con, $moderator_id, $category_id);
	
//put paramters in user_in_event table
	$moderator = "moderator";
   $statement2 = mysqli_prepare($con, 	
	"INSERT INTO user_in_event (user_id, event_id, role) VALUES (?, ?, ?)")																						
	or die(mysqli_error($con));	
	
	
	  mysqli_stmt_bind_param($statement2, "iis", $moderator_id, $event_id_create, $moderator)
	or die(mysqli_error($con));
	
    mysqli_stmt_execute($statement2)
	or die(mysqli_error($con)); 
	
    
    $response = array(); 
    $response["success"] = true;  
    
    echo json_encode($response);							

	}			///end create new event

	else if ($choice == 2){//update event details
		
		global $category_id;
		 $event_id = getEventId($con, $moderator_id, $category_id);
		
		//echo "$event_id\n";
	
		echo "$category_id\n";
		
		$result = mysqli_query($con, "UPDATE event SET event_name = '".$event_name."' , event_description = '".$event_description."' , event_time = '".$event_time."' 
									, event_date = '".$event_date."' , max_members = '".$max_members."' 
																			, category_name = '".$category_name."' , event_location = '".$event_location."' 
																			WHERE moderator_id = '".$moderator_id."' AND event_id = '".$event_id."'   AND category_id = '".$category_id."' ")
													
	or die(mysqli_error($con)); 	
	
	  /*$statement = mysqli_prepare($con, "UPDATE event SET event_name = ?, event_description = ?, event_time = ?, event_date = ?, max_members = ?
																			, category_name = ?, event_location = ?	WHERE moderator_id = ? ")
	or die(mysqli_error($con));		

	
    mysqli_stmt_bind_param($statement, "issssssss", $event_id, $event_name, $event_description, $event_time, $event_date, $max_members,$category_name,
																				$event_location ,$moderator_id)
	or die(mysqli_error($con));			
	
    $result=mysqli_stmt_execute($statement)
	or die(mysqli_error($con));	
	
    
    mysqli_stmt_store_result($statement)
	or die(mysqli_error($con)); */
	
	    
	
	
    mysqli_error($con);
    $response = array();  
    
    if ($result) {
        $response['success'] = true;  

    }
	else $response['success'] = false;  
    
     echo json_encode($response);
	

		
	}		//end update event details
	
	
	
	 function getCategoryId($con, $category_name, $moderator_id){
	
		$result = mysqli_query( $con, "SELECT category.category_id FROM category, user 
													WHERE category.category_name = '".$category_name."' AND user.user_id = '".$moderator_id."'")
	or die(mysqli_error($con));
													
													
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {
			$category_id = $row[0];
	  }
	} else {
	  echo "no category_id\n";
	}	  
	return $category_id;
}

	function  getEventId($con, $moderator_id, $category_id){
		
	$result = mysqli_query( $con, "SELECT event_id	FROM event WHERE moderator_id = '".$moderator_id."' AND category_id = '".$category_id."' ")
													
	or die(mysqli_error($con)); 														
														
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {			
			
			$event_id  =  $row[0];
			
		
	  }
	} else {
	  echo "no event_id \n";
	
	}
	return $event_id;
}												

	
	mysqli_close($con)
	or die(mysqli_error($con));
?>