<?php
	require_once "conn.php";
	
	#query utk menampilkan data mahasiswa
	$sql = "SELECT * FROM mahasiswa";
	
	#eksekusi query
	$result = $conn -> query($sql);
	
	#cek jika data ada, gunakan num_rows
	if ($result -> num_rows > 0) #jika num_rows > 0, berarti ada
	{
		while($row = $result->fetch_assoc())
		{
			$array_mahasiswa [] = $row;
			//echo $row["id_login"] . " dan " . $row["password"] . "<br/>";
		}
	}
	$data =  json_encode($array_mahasiswa) ;
	
	//echo $data . "<hr>";
	
	echo "{\"mahasiswa\":" . $data . "}";
?>