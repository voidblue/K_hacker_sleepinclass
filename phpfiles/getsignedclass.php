<?php  

$link=mysqli_connect("54.250.164.132","root","root", "k_hack" );  
if (!$link)  
{  
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  

mysqli_set_charset($link,"utf8"); 


$sql="SELECT * from signinfo, studenttable WHERE signinfo.studentcode = studenttable.studentcode";

$result=mysqli_query($link,$sql);
$data = array();   
if($result){  
    
    while($row=mysqli_fetch_array($result)){
        array_push($data, 
            array('classcode'=>$row[0],
            'studentcode'=>$row[1]
        ));
    }

    echo "<pre>"; print_r($data); echo '</pre>';

}  
else{  
    echo "SQL문 처리중 에러 발생 : "; 
    echo mysqli_error($link);
} 


 
mysqli_close($link);  
   
?>
