package com.example.springbootpractice.service;

import com.example.springbootpractice.dao.PersonDao;
import com.example.springbootpractice.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("fakeDao") PersonDao personDao) {
        this.personDao = personDao;
    }

    public int addPerson(Person person) {
        return personDao.insertPerson(person);
    }

    public List<Person> getAllPersons() {
        return personDao.selectAllPerson();
    }

    public Optional<Person> selectPersonById(UUID uuid) {
        return personDao.selectPersonById(uuid);
    }

    public int updatePersonById(UUID uuid, Person person) {
        return personDao.updatePersonById(uuid, person);
    }

    public int deletePersonById(UUID uuid) {
        return personDao.deletePersonById(uuid);
    }
}
