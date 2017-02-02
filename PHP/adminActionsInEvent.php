<?php

    $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") 
	or die(mysqli_error($con));
	
	$user_id = $_POST['user_id'];
    $event_id = $_POST['event_id'];
	
	$result = mysqli_query( $con, "DELETE FROM user_in_event where event_id = '".$event_id."'")
 or die(mysqli_error($con));
 
 echo "row(s) delted from user in event \n";
 
 $result = mysqli_query( $con, "DELETE FROM event where event_id = '".$event_id."'")
 or die(mysqli_error($con));
 
echo "row delted from user \n";
 
  mysqli_close($con);

?>