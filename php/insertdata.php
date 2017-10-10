<?php
	include "koneksi.php";
	//session_start();

	header('Content-Type: application/json');
	$linkshort 	= generateRandomString(5);
	$linkasli 	= $_POST['linkasli'];
	
	if (empty($linkasli)) {
		$response['success'] = 0;
		$response['message'] = "Kolom isian tidak boleh kosong";

		die(json_encode($response));
	} else {

		if (substr($linkasli, 0, 6) != 'http://') {
			$linkasli = 'http://'. $linkasli;
		}

		$nama = "1";
		$query = mysqli_query($konek, "INSERT INTO shortlink (id,user_id,link_asli,link_short) VALUES(0,'".$nama."', '$linkasli', '$linkshort')");
		
		if ($query) {
			$response['success'] = 1;
			$response['message'] = "Data berhasil di simpan bosss";
			$response['shortlink'] = "http://localhost/shortlink/".$linkshort;
			die(json_encode($response));
		} else{ 
			$response['success'] = 1;
			$response['message'] = "error simpan";
			$response['shortlink'] = "error";
			die(json_encode($response));
		}	
	}

	function generateRandomString($length = 10) {
	    $characters = '0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ';
	    $charactersLength = strlen($characters);
	    $randomString = '';
	    for ($i = 0; $i < $length; $i++) {
	        $randomString .= $characters[rand(0, $charactersLength - 1)];
	    }
	    return $randomString;
	}