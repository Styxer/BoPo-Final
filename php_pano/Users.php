<?php
 
/**
 * A class file to connect to database
 */
class Users {
   
    public $db ;
    public $id_user;
    public $telephone;
    public $pass;
    public $fullname;
    public $waze;
    public $comments;
    public $action;

// Create connection
    // constructor
    function __construct() {
    include_once 'Db_Connect.php';
    //Initiate the class
    $this->db = new DB();
    $this->id_user = isset($_POST['idUsers'])?$_POST['idUsers']:null;
    $this->telephone = isset($_POST['telephone'])?$_POST['telephone']:null;
    $this->pass = isset($_POST['pass'])?$_POST['pass']:null;
    $this->fullname = isset($_POST['fullname'])?$_POST['fullname']:null;
    $this->waze = isset($_POST['waze'])?$_POST['waze']:null;
    $this->comments = isset($_POST['comments'])?$_POST['comments']:null;

    $this->action = isset($_POST['action'])?$_POST['action']:null;
    $response = array();
    $result;
    switch ($this->action) {
        //check telephone
    case 4:
        $sql = "SELECT * FROM Users WHERE telephone = '".$this->telephone."' ";
        $result = $this->db->get_results( $sql );
   break;
        //check Login
    case 1:
        $sql = "SELECT * FROM Users WHERE telephone = '".$this->telephone."' AND pass = '".$this->pass."' ";
        $result = $this->db->get_results($sql);
   break;
    //update User 
    case 0:

        $update = array(
        'fullname' => $this->fullname,
        'waze' => $this->waze, 
        'comments' => $this->comments
            );
        //Add the WHERE clauses
        $where_clause = array(
            'idUsers' => $this->id_user
        );
        $result = $this->db->update( 'Users', $update, $where_clause, 1 );
        echo $update.$where_clause;
        
        break;


    //register
    case 2:
        $names = array(
            'telephone' => $this->telephone
        );
        $check_column = '*';
        $result = $this->db->exists( 'Users', $check_column,  $names );

        $names = array(
            'telephone' => $this->telephone,
            'pass' => $this->pass, 
            'fullname' => $this->fullname,
            'waze' => $this->waze,
            'comments' => $this->comments
        );
        if($result==false){
            $result = $this->db->insert( 'Users', $names );
        }
        
        break;
        //change password
    case 3: 
         $update = array(
        'pass' => $this->pass
            );
        //Add the WHERE clauses
        $where_clause = array(
            'idUsers' => $this->id_user
        );
        $result = $this->db->update( 'Users', $update, $where_clause, 1 );
        break;
    default:
        echo $this->action."-action not perfom";
    }
    $response["action"] = $this->action;
    $response = $result["0"];
    
        if ($result) {
         // successfully updated
        $response["success"] = 1;
        $response["message"] = "Product successfully updated.";
        }  
        else {
            // successfully updated
            $response["success"] = 0;
            $response["message"] = "Product NOT updated.";
        } 
       
    header('Content-Type: application/json');
        // echoing JSON response
    echo json_encode($response);
    }
 
    // destructor
    function __destruct() {
        //$this->db->close();
    }

 
}
 new Users();
?>