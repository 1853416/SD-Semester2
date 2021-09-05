<?php
class Person
{
    public $db = null;
function __construct($db) 
    {
        $this->db = $db;
    }
public function greeting($id)
    {
        $friend = $this->db->getPersonByID($id);
        $friendName = $friend->name;
        return "Hello $friendName";
    }
}

// in our database we have a person with an ID of 2 and a name of "Bob"

// result should be "Hello Bob"