<?php  
 include "koneksi.php";

 header('Content-Type: application/json');
 $hasil         = mysqli_query($konek, "SELECT * FROM shortlink WHERE user_id = 0") or die(mysqli_error());
 $json_response = array();
 
if(mysqli_num_rows($hasil)> 0){
 while ($row = mysqli_fetch_array($hasil)) {
 	 $response['shortlink'] = $row;
     $json_response[] = $response;
 }
 echo json_encode($json_response);
} 
?>