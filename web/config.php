<?php
header('Content:Type=text/html;charset=utf8');
define('HOST','localhost');
define('USER','root');
define('PASSWORD','');
define('NAME','user');
$con=@mysql_connect(HOST,USER,PASSWORD) or die('Error!'.mysql_error());
mysql_select_db(NAME) or die(mysql_error());
mysql_query('SET NAMES UTF-8');
?>