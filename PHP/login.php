<?php
$con = mysqli_connect("mysql1.000webhost.com", "a4238731_BoPo", "123qwe", "a4238731_BoPo") or die("cannot connect");  
   $email = $_POST['email'];
    $password = $_POST['password'];
	
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE email = ? AND password = ?")
	or die(mysqli_error($con));
	
    mysqli_stmt_bind_param($statement, "ss", $email, $password);
    mysqli_stmt_execute($statement);
    
    mysqli_stmt_store_result($statement);
	
    mysqli_stmt_bind_result($statement, $user_id, $role, $name, $email, $password, $birthday, $rating, $gender, $join_date, $phone_number, $isLoggedIn, $address, $image)
	or die(mysqli_error($con));
	
    mysqli_error($con);
    $response = array();
    $response['success'] = false;  
    
    while(mysqli_stmt_fetch($statement) ){
        $response['success'] = true;  
        $response['user_id'] = $user_id;
	$response['role'] = $role;
        $response['name'] = $name;
        $response['email'] = $email;
        $response['password'] = $password;
        $response['birthday'] = $birthday;
	$response['phone_number'] = $phone_number;
	$response['address'] = $address;
        $response['image'] = $image;
    }
    
     echo json_encode($response);

	 mysqli_close($con);

?>