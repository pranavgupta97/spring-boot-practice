package com.example.springbootpractice.api;

import com.example.springbootpractice.model.Person;
import com.example.springbootpractice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/v1/person")
@RestController
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping
    public void addPerson(@Valid @NotNull @RequestBody Person person) {
        personService.addPerson(person);
    }

    @GetMapping()
    public ResponseEntity<List<Person>> getAllPersons() {
        Optional<List<Person>> optionalPersonList = Optional.ofNullable(personService.getAllPersons());

        return optionalPersonList.map(
                person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> updatePerson(@PathVariable("id") UUID uuid, @Valid @NotNull @RequestBody Person person) {

        if (personService.updatePersonById(uuid, person) == 1) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }


        else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") UUID uuid) {
        Optional<Person> optionalPerson = personService.selectPersonById(uuid);

        return optionalPerson.map(
                person -> new ResponseEntity<>(person, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deletePersonById(@PathVariable("id") UUID uuid) {

        if (personService.deletePersonById(uuid) == 1) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }   else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
