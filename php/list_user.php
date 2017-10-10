<!-- <?php
include ('koneksi.php');

$query = "select * from users";
$result = mysqli_query($connect, $query);

$data = array();

while ($temp=mysqli_fetch_array($result, MYSQLI_ASSOC)) {
	array_push($data, $temp);
}

echo json_encode($data);
?> -->