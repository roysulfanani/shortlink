<?php
$host = "localhost";
$user = "root";
$pass = "";
$db   = "shortlink";

$konek =mysqli_connect($host, $user, $pass, $db);// or die ('Koneksi Gagal!');
//mysql_select_db($db);//or die('koneksi gagal');

if ($konek) {
	//echo "koneksi sukses";	
}else{
	echo mysqli_errno($konek);
}
?>