<?php
$con = mysqli_connect("localhost", "cellular_offir", "offirbraude123", "cellular_offir")   or die("cannot connect");  


/*	$event_name = $_POST["event_name"];
    $event_description = $_POST["event_description"];
	 $event_date = $_POST["event_date"];
	$event_time = $_POST["event_time"];
	$max_members = $_POST["max_members"]; 
	$ack_needed = $_POST["ack_needed"];
	$category_name = $_POST["category_name"];
	$event_location = $_POST["event_location"]; */
	


	
	$event_name = (isset($_POST['event_name']) ? $_POST['event_name'] : '');
	$event_description = (isset($_POST['event_description']) ? $_POST['event_description'] : '');
	$event_date = (isset($_POST['event_date']) ? $_POST['event_date'] : '');
	$event_time = (isset($_POST['event_time']) ? $_POST['event_time'] : '');
	$max_members = (isset($_POST['max_members']) ? $_POST['max_members'] : '');
	$ack_needed = (isset($_POST['ack_needed']) ? $_POST['ack_needed'] : '');
	$category_name = (isset($_POST['category_name']) ? $_POST['category_name'] : '');
	$event_location = (isset($_POST['event_location']) ? $_POST['event_location'] : '');
	$moderator_id = (isset($_POST['moderator_id']) ? $_POST['moderator_id'] : '');
	
	echo $moderator_id;
	//get category_id to events table
	$result = mysqli_query( $con, "SELECT category_request.request_id FROM category_request, event, user 
													WHERE category_request.category_name = event.category_name AND user.user_id = event.moderator_id");
	if(mysqli_num_rows($result)) {
	  while($row = mysqli_fetch_row($result)) {
			$category_id = $row[0];
	  }
	} else {
	  echo "no request_id";
	}	  
	
    
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
	$result = mysqli_query( $con, "SELECT event.event_id
													FROM event,user, category_request
														WHERE user.user_id = event.moderator_id  AND category_request.request_id = event.category_id  AND event.category_name = category_request.category_name
														ORDER BY event.event_id DESC
														LIMIT 1");
if(mysqli_num_rows($result)) {
  while($row = mysqli_fetch_row($result)) {
		$event_id = $row[0];
		echo $event_id;
		echo "\n";
		
		
  }
} else {
  echo "no event_id \n";
}

//put paramters in user_in_event table
	$moderator = "moderator";
   $statement2 = mysqli_prepare($con, 	
	"INSERT INTO user_in_event (user_id, event_id, role) VALUES (?, ?, ?)")																						
	or die(mysqli_error($con));
	
	
	
	
	  mysqli_stmt_bind_param($statement2, "iis", $moderator_id, $event_id, $moderator)
	or die(mysqli_error($con));
	
    mysqli_stmt_execute($statement2)
	or die(mysqli_error($con)); 
	
    
    $response = array(); 
    $response["success"] = true;  
    
    echo json_encode($response);
	
	mysqli_close($con)
	or die(mysqli_error($con));
?>