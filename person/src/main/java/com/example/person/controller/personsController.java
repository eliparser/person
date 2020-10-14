package com.example.person.controller;

import com.example.person.api.PersonsApi;
import com.example.person.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/persons")
public class personsController implements PersonsApi {

    public HashMap<String,Person> personList = new HashMap<>();

    public personsController() {
        init();
    }
    public void init(){
        Person eli = new Person().id("111111111").age(26).name("eli parser");
        Person messi = new Person().id("000000000").age(26).name("lionel messi");
        Person yuval = new Person().id("222222222").age(26).name("yuval keren");

        this.personList.put("111111111", eli);
        this.personList.put("000000000", messi);
        this.personList.put("222222222", yuval);
    }

    @Override
    @PostMapping
    public ResponseEntity<Void> addPerson(@RequestBody Person person) {
        personList.put(person.getName(),person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    @GetMapping("")
    public ResponseEntity<List<Person>> getAllPersons() {
        return new ResponseEntity<>(new ArrayList<>(personList.values()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable("id") String id) {
        return new ResponseEntity<>(personList.get(id), HttpStatus.OK);
    }
}
