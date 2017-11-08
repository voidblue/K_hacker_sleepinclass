<?php

$link=mysqli_connect("localhost","root","root", "k-hack" );
if (!$link)
{
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();
}

mysqli_set_charset($link,"utf8");


$sql="SELECT * from attendanttable";

$result=mysqli_query($link,$sql);
$data = array();
if($result){

    while($row=mysqli_fetch_array($result)){
        array_push($data,
            array('classcode'=>$row[0],
            'date'=>$row[1],
            'studentcode'=>$row[2],
          	'ischecked'=>$row[3]
        ));
    }

    header('Content-Type: application/json; charset=utf8');
    $json = json_encode(array("attendant"=>$data), JSON_PRETTY_PRINT+JSON_UNESCAPED_UNICODE);
    echo $json;

}
else{
    echo "SQL문 처리중 에러 발생 : ";
    echo mysqli_error($link);
}



mysqli_close($link);

?>
