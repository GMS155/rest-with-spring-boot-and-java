package com.fab.treinamento.services;

import com.fab.treinamento.model.Person;

public class MockTest {

    Person person = new Person();

    public Person getPerson() {
        person.setAddress("Rua Cedro");
        person.setFirstName("Guilherme");
        person.setLastName("Santos");
        person.setGender("Male");

        return person;
    }
}
