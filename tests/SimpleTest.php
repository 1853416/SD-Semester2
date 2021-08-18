<?php

require __DIR__ . "/../src/Simple.php";
require __DIR__ .'/../vendor/autoload.php';

class SimpleTest extends PHPUnit_Framework_TestCase
{

    public function testDivide()
    {
        $simple = new Simple(10);
        $result = $simple->divide(2);

        $this->assertEquals(5, $result);
    }    
}

?>
