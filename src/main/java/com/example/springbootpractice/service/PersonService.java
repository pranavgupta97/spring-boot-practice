package com.example.springbootpractice.service;

import com.example.springbootpractice.dao.PersonDao;
import com.example.springbootpractice.model.Person;

public class PersonService {

    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }
}
