<?php

	$name = $_POST["name"];
    $image = $_POST["image"];

	$decodedImage = base64_decode("$image");
	$result = file_put_contents("/home/a4238731/public_html/pictures/" . $name . ".JPG", $decodedImage);

    $response = array(); 
    
    if ($result) {
        $response['success'] = true;  
    }
	else $response['success'] = false;  
    
     echo json_encode($response);
?>
