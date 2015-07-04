<?php
session_start();
?>
<!DOCTYPE HTML>
<html>
<head>
<meta charset="UTF-8">
<meta name=viewport content="width=device-width, initial-scale=1">
<style type="text/css">body {font-family: "Helvetica Neue", Helvetica, STHeiTi, sans-serif;}span{float:right;}</style>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
<script type="text/javascript">
function AutoResizeImage(maxWidth,maxHeight,objImg){
var img = new Image();
img.src = objImg.src;
var hRatio;
var wRatio;
var Ratio = 1;
var w = img.width;
var h = img.height;
wRatio = maxWidth / w;
hRatio = maxHeight / h;
if (maxWidth ==0 && maxHeight==0){
Ratio = 1;
}else if (maxWidth==0){//
if (hRatio<1) Ratio = hRatio;
}else if (maxHeight==0){
if (wRatio<1) Ratio = wRatio;
}else if (wRatio<1 || hRatio<1){
Ratio = (wRatio<=hRatio?wRatio:hRatio);
}
if (Ratio<1){
w = w * Ratio;
h = h * Ratio;
}
objImg.height = h;
objImg.width = w;
}
</script>
</head>
<body>
<div data-role="page">
<div data-role="header">
<a href="home.php" data-ajax="false" data-role="button" data-icon="back">返回</a>
<h1>随便看看</h1>
<a href="post.html" data-role="button" data-icon="plus" data-ajax="false">发布</a>
</div>
<div data-role="content">
<?php
require 'config.php';
$query="SELECT * FROM post";
$result=mysql_query($query);
$c=0;
while($row=mysql_fetch_array($result))
{
	echo "<h4>".$row['username']."</h4>";
	echo "<p>".$row['content']."</p>";
	$query="SELECT * FROM photo WHERE postid='{$row['id']}'";
	$res=mysql_query($query);
	while($photo=mysql_fetch_array($res))
	{
	if($photo['link'])
	{echo '<img border="0" width="0" height="0" onload="AutoResizeImage(100,100,this)" alt="100 X 100" src='.$photo['link'].">";}
	}
	if($row['audio'])
	{echo "<audio controls='controls' src=".$row['audio']."></audio>";}
	echo "<p style='text-align:right'>".$row['time']."</p>";
	echo "<hr>";
}
?>
</div>
</div>

</body>