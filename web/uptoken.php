<?php
    require_once("qiniu/rs.php");
    $accessKey = '';
    $secretKey = '';
    $bucket = "";
    Qiniu_SetKeys($accessKey, $secretKey);
    $mac = new Qiniu_Mac($accessKey,$secretKey);
    if($_POST["putExtra"]){
        $extra = json_decode($_POST["putExtra"]);
        if($extra){
            $scope = $bucket.":".$extra->{'key'};
            $policy = new Qiniu_RS_PutPolicy($scope);
            $policy->Expires = 3600*24*30;
            echo $policy->token($mac);
        }
    }
?>