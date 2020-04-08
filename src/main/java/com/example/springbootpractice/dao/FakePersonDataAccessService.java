package com.example.springbootpractice.dao;

import com.example.springbootpractice.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> dataBase = new ArrayList<Person>();

    @Override
    public int insertPerson(UUID uuid, Person person) {

        dataBase.add(new Person(uuid, person.getName()));
        return 69;
    }
}
