package com.example.springbootpractice.dao;

import com.example.springbootpractice.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {

    private static List<Person> dataBase = new ArrayList<Person>();

    @Override
    public int insertPerson(UUID uuid, Person person) {

        dataBase.add(new Person(uuid, person.getName()));
        return 69;
    }

    @Override
    public int updatePersonById(UUID uuid, Person person) {


        return selectPersonById(uuid)
                .map(currentPerson -> {
                    int indexOfPersonToUpdate = dataBase.indexOf(currentPerson);

                    if (indexOfPersonToUpdate >= 0) {
                        dataBase.set(indexOfPersonToUpdate, new Person(uuid, person.getName()));
                        return 1;
                    }   else {
                        return 0;
                    }
                })


                .orElse(0);


    }

    @Override
    public List<Person> selectAllPerson() {
        return dataBase;
    }

    @Override
    public int deletePersonById(UUID uuid) {
        Optional<Person> optionalPerson = selectPersonById(uuid);

        if (optionalPerson.isPresent()) {
            dataBase.remove(optionalPerson.get());
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public Optional<Person> selectPersonById(UUID uuid) {

        return dataBase.stream()
                .filter(person -> person.getId().equals(uuid))
                .findFirst();

    }
}