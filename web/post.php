<?php
session_start();
require 'config.php';
if($_POST['addinfo'])
{
	$query="INSERT INTO post(username,content) VALUES ('{$_SESSION['username']}','{$_POST['addinfo']}')";
	mysql_query($query);
	$_SESSION['postid']=mysql_insert_id();
}
echo $_SESSION['username'];
if($_POST['photo'])
{
	$query="INSERT photo(username,link,postid) VALUES('{$_SESSION['username']}','{$_POST['photo']}','{$_SESSION['postid']}')";
	mysql_query($query);
}
if($_POST['audio'])
{
	$query="UPDATE post SET audio='{$_POST['audio']}' WHERE id='{$_SESSION['postid']}'";	
	mysql_query($query);
}
mysql_close();
?>