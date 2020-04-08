package com.example.springbootpractice.api;

import com.example.springbootpractice.model.Person;
import com.example.springbootpractice.service.PersonService;

public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    public void addPerson(Person person) {
        personService.addPerson(person);
    }
}
