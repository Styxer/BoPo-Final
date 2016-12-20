<?php


$sql = "SELECT category_name FROM category_request";
  $con = mysqli_connect("localhost", "cellular_offir", "offirbraude123", "cellular_offir")
  or die(mysqli_error($con));
  
  $res = mysqli_query($con,$sql);
  $result = array();
  
  while($row = mysqli_fetch_array($res)){
	  array_push($result,array(
		'category_name' =>$row['category_name']
		));
  }
  
  echo json_encode (array('result'=>$result));
  
  mysqli_close($con);

?>