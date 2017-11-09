<?php

error_reporting(E_ALL);
ini_set('display_errors',1);

$link=mysqli_connect("localhost","root","root","k-hack");
if (!$link)
{
   echo "MySQL 접속 에러 : ";
   echo mysqli_connect_error();
   exit();
}


mysqli_set_charset($link,"utf8");

//POST 값을 읽어온다.
$classcode=isset($_POST['classcode']) ? $_POST['classcode'] : '';
$date=isset($_POST['date']) ? $_POST['date'] : '';
$studentcode=isset($_POST['studentcode']) ? $_POST['studentcode'] : '';
$ischecked=isset($_POST['ischecked']) ? $_POST['ischecked'] : '';


if ($classcode !="" and $date !="" and $studentcode !="" and $ischecked !=""){

    $sql="insert into attendanttable(classcode,date,studentcode,ischecked) values('$classcode','$date','$studentcode','$ischecked')";
    $result=mysqli_query($link,$sql);

    if($result){
       echo "SQL문 처리 성공";
    }
    else{
       echo "SQL문 처리중 에러 발생 : ";
       echo mysqli_error($link);
    }

} else {
    echo "데이터를 입력하세요 ";
}


mysqli_close($link);
?>

<?php

$android = strpos($_SERVER['HTTP_USER_AGENT'], "Android");

if (!$android){
?>

<html>
   <body>

      <form action="<?php $_PHP_SELF ?>" method="POST">
         classcode: <input type = "text" name = "classcode" />
         date: <input type = "text" name = "date" />
         studentcode: <input type = "text" name = "studentcode" />
         ischecked: <input type = "text" name = "ischecked" />\
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}
?>
