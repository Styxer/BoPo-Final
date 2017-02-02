<?php
 
 $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect"); 
	
	$category_name = $_POST['category_name'];

    $sql = "SELECT event.event_name, event.event_location, event.event_date, event.event_time, event.event_id FROM event 
	WHERE event.category_name LIKE '%".$category_name."%'";

    $result = mysqli_query($con,$sql);
	$response = array();

    while($row = mysqli_fetch_array($result))    
		{
			array_push($response,array("event_name"=>$row["event_name"],"event_location"=>$row["event_location"],"event_date"=>$row["event_date"],"event_time"=>$row["event_time"], "event_id"=>$row["event_id"]));
		}
       
    echo json_encode($response);
    mysqli_close($con);
    ?>