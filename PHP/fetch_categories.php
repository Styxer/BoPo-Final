<?php


$sql = "SELECT category_name FROM category";
  $con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") 
  or die("cannot connect");  
  
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