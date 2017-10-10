<?php
include "koneksi.php";
header('Content-Type: application/json');

	$nama 		= $_POST['nama_user'];
	$email 		= $_POST['email'];
	$pass 		= $_POST['password'];
	$repass 	= $_POST['repassword'];
	
	if (empty($nama or $email or $pass or $repass)) {
		$response['success'] = 0;
		$response['message'] = "Kolom isian tidak boleh kosong";

		die(json_encode($response));
	} else {

		if ($pass === $repass) {
			$password = md5($repass);

			//$nama = "1";
			$query = mysqli_query($konek, "INSERT INTO users (id,nama_user,email,password) VALUES(0,'$nama', '$email', '$password')");
			
			if ($query) {
				$response['success'] = 1;
				$response['message'] = "Akun anda berhasil terdaftar";
				$response['shortlink'] = "sukses";
				die(json_encode($response));
			} else{ 
				$response['success'] = 1;
				$response['message'] = "Gagal simpan";
				$response['shortlink'] = "gagal";
				die(json_encode($response));
			}	
		} else {
			$response['success'] = 0;
			$response['message'] = "passwrod dan re-password tidak sama";
			die(json_encode($response));
		}
	}
?>