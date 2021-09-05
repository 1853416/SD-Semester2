<?php
class RemoteConnect{

    public function connectToServer($serverName=null){
        $fp = fsockopen($serverName,80);
        return ($fp) ? true : false;
    }

//     public function returnSampleObject(){
//         return $this;
//    }
 
}
?>