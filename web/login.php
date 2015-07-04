<?php
session_start();
$_SESSION['username']=$_POST['user'];
require 'config.php';
$_pass=sha1($_POST['pass']);
$query="SELECT * FROM user_info WHERE username='{$_POST['user']}' AND password='{$_pass}'";
$res=mysql_query($query) or die('Database error!');
if($row=mysql_fetch_array($res)){
echo 1;
}
else{
echo 0;}
mysql_close();
?>