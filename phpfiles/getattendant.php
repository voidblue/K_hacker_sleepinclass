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
            'studentcode'=>$row[1],
		'ischecked'=>$row[2],
		'date_date'=>$row[3],
		'date_day'=>$row[4],
		'date_hour'=>$row[5],
		'date_minute'=>$row[6]
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
