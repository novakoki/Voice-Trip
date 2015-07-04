<?php
require 'config.php';
$query = "UPDATE user_info SET nickname='{$_POST['nickname']}',gender='{$_POST['gender']}',intro='{$_POST['intro']}' WHERE username='{$_POST['user0']}'";
@mysql_query($query) or die('新增错误：'.mysql_error());
echo 1;
mysql_close();
?>