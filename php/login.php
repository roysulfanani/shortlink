<?php
include "koneksi.php";

header('Content-Type: application/json');
$username = $_POST['username'];
$password = md5($_POST['password']);

$query  = mysqli_query($konek, "SELECT * FROM users WHERE email = '$username' AND password = '$password' LIMIT 1");

if(mysqli_num_rows($query) > 0){
   	$response['message'] 		= "Login Sukses";
	$response['shortlink'] 		= "sukses";
	die(json_encode($response));
} else {
	$response['message'] 		= "login gagal";
	$response['shortlink'] 		= "gagal";
	die(json_encode($response));
}
?>