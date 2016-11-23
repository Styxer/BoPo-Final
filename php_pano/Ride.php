<?php
 
/**
 * A class file to connect to database
 */
class Ride {
    
    public $db;
    public $id_ride;
    public $id_user;
    public $date;
    public $time;
    public $waze;
    public $action;

// Create connection
    // constructor
    function __construct() {
    include_once 'Db_Connect.php';
    //Initiate the class
    $this->db = new DB();

    $this->id_ride = isset($_POST['idRide'])?$_POST['idRide']: null;
    $this->date = isset($_POST['date'])?$_POST['date']: null;
    $this->time = isset($_POST['time'])?$_POST['time']: null;
    $this->waze = isset($_POST['waze'])?$_POST['waze']: null;
    $this->id_user = isset($_POST['idUsers'])?$_POST['idUsers']: null;
    $this->action = isset($_POST['action'])?$_POST['action']: null;
    $sql = "";
    $response = array();
    $result;
    switch ($this->action) {
        //insert ride
    case 1:
        $names = array(
            'idUsers' => $this->id_user,
            'date' => $this->date,
            'time' => $this->time,
            'waze' => $this->waze
        );
        $check_column = 'idUsers';
        $exists = $this->db->exists( 'Ride', $check_column,  $names );
        if( $exists == false){
            $result = $this->db->insert( 'Ride', $names );
        }
   break;
    case 0:
        $update = array(
        'date' => $this->date,
        'time' => $this->time, 
        'waze' => $this->waze
            );
        //Add the WHERE clauses
        $where_clause = array(
            'idRide' => $this->id_ride
        );
        $result = $this->db->update( 'Ride', $update, $where_clause, 1 );
        break;
    case -1:
        $delete = array(
            'idRide' => $this->id_ride
        );
        $result = $this->db->delete( 'Ride', $delete, 1 );
        break;
    case 2:
        if(isset($_POST['time'])){
        $sql = "Select R.time as Rtime ,U.fullname as Ufullname,U.waze as Uwaze,U.comments as Ucomments, U.telephone as Utelephone From Users as U , Ride As R WHERE R.idUsers = U.idUsers AND R.date = '".$this->date."' AND R.waze = '".$this->waze."' AND (R.time >= '".$this->time."' -1)  AND (R.time <= '".$this->time."' + 1) ORDER BY R.time ASC ";

        }else {
        $sql ="Select R.time as Rtime ,U.fullname as Ufullname,U.waze as Uwaze,U.comments as Ucomments, U.telephone as Utelephone  From Users as U , Ride As R WHERE R.idUsers = U.idUsers AND R.date = '".$this->date."' AND R.waze = '".$this->waze."' ORDER BY R.time ASC";
        }
        $result = $this->db->get_results( $sql );
    break;
    case 3:
        $sql ="Select * From Ride WHERE idUsers = '".$this->id_user."' AND date >= '".$this->date."' ORDER BY date ASC";
        $result = $this->db->get_results( $sql );
    break;
    default:
        echo $this->action."-action not perfom";
    }
    $response["data"] = $result;
    $response["action"] = $this->action;
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
        //$this->db->disconnect();
    }

 
}
 new Ride();
?>