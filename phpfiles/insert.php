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
$classname=isset($_POST['classname']) ? $_POST['classname'] : '';
$time=isset($_POST['time']) ? $_POST['time'] : '';
$classroom=isset($_POST['classroom']) ? $_POST['classroom'] : '';
$beaconMajor=isset($_POST['beaconMajor']) ? $_POST['beaconMajor'] : '';
$classcode=isset($_POST['classcode']) ? $_POST['classcode'] : '';


if ($classname !="" and $time !="" and $classroom !="" and $beaconMajor !="" and $classcode !="" ){

    $sql="insert into classtable(classname,time,classroom,beaconMajor,classcode) values('$classname','$time','$classroom','$beaconMajor','$classcode')";
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
         classname: <input type = "text" name = "classname" />
         time: <input type = "text" name = "time" />
         classroom: <input type = "text" name = "classroom" />
         beaconMajor: <input type = "text" name = "beaconMajor" />
         classcode: <input type = "text" name = "classcode" />
         <input type = "submit" />
      </form>

   </body>
</html>
<?php
}
?>

