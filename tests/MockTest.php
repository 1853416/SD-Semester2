<?php

//require __DIR__ . '/Database.php';
require __DIR__ . '/../src/Person.php';
//use PHPUnit\Framework\TestCase;
class MockTest extends PHPUnit_Framework_TestCase
{
    public function test_greeting()
    {
        $dbMock = $this->getMockBuilder(Database::class)
            ->setMethods(['getPersonByID'])
            ->getMock();
$mockPerson = new stdClass();
$mockPerson->name = 'Bob';
        $dbMock->method('getPersonByID')->willReturn($mockPerson);
$test = new Person($dbMock);
        $this->assertEquals('Hello Bob', $test->greeting(2));
    }
}