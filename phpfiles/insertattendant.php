<?php

$link=mysqli_connect("localhost","root","root", "k-hack" );
if (!$link)
{
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();
}

mysqli_set_charset($link,"utf8");

$classcode=isset($_POST['classcode']) ? $_POST['classcode'] : '';
$date=isset($_POST['date'] ? $_POST['date'] : '';
$sudentcode=isset($_POST['studentcode']) ? $_POST['date'] : '';
$ischecked=isset($_POST['ischecked']) ? $_POST['ischecked'] : '';

$sql="insert into attendanttable(classcode, date, studentcode, ischecked) values('$classcode','$date','$studenttable','$ischecked')";
if($classcode !="" and $date !="" and $studentcode !="" and $ $ischecked )
  $result=mysqli_query($link,$sql);
  if($result){
      echo "SQL문 처리";
  }else{
    echo "오류발생";
  }
else{
    echo "SQL문 처리중 에러 발생 : ";
    echo mysqli_error($link);
}



mysqli_close($link);

?>
