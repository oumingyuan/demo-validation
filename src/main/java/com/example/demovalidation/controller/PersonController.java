package com.example.demovalidation.controller;

import com.example.demovalidation.model.Person;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class PersonController {
    @PostMapping("/person")
    public Person savePerson(@Valid @RequestBody Person person) {
        return person;
    }

    @PostMapping("/person1")
    public Person savePerson1(@Valid @RequestBody Person person) {
        return person;
    }


    @PostMapping("/person2")
    public Person savePerson2(@Valid @RequestBody Person person) {

        int i = 1 / 0;
        return person;
    }
}
