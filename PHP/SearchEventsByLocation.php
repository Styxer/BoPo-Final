<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	$event_location = $_POST['event_location'];

    $sql = "SELECT event.event_name, event.event_location, event.event_date, event.event_time, event.event_id FROM event 
	WHERE event.event_location LIKE '%".$event_location."%'";

    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("event_name"=>$row["event_name"],"event_location"=>$row["event_location"],"event_date"=>$row["event_date"],"event_time"=>$row["event_time"],"event_id"=>$row["event_id"]));
		}
       
    echo json_encode($response);
    mysqli_close($con);
    ?>