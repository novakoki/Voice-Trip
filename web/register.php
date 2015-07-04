<?php
session_start();
require 'config.php';
$result = mysql_query("SELECT * FROM user_info");
$c=1;
while($row = mysql_fetch_array($result))
{
	if($_POST['user']==$row['username'])
	{
		$c=0;
		break;
	}
}
if($c==0)
{
	echo 0;
}
else{
$query = "INSERT INTO user_info(username, password,email) VALUES('{$_POST['user']}', sha1('{$_POST['pass']}'),'{$_POST['email']}')";
@mysql_query($query) or die('新增错误：'.mysql_error());
$_SESSION['username']=$_POST['user'];
echo 1;
}
mysql_close();
?>