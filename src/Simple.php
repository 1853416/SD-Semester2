<?php
class Simple 
{
    private $number; 

    public function __construct($number)
    {
        $this->number = $number;
    }

    public function divide($divisor)
    {

        return $this->number / $divisor;
    }
}

?>
