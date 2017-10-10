<?php
include "koneksi.php";

$shortlink = $_GET['params'];

$sql = "SELECT * FROM shortlink WHERE link_short = '$shortlink'";

$query = mysqli_query($konek, $sql);

$data =  mysqli_fetch_array($query);

if (!empty($data['link_asli'])) {
	$linkasli = $data['link_asli'];
} else {
	$linkasli = "http://google.com";
}

mysqli_free_result($query);

header("location:$linkasli");
?>