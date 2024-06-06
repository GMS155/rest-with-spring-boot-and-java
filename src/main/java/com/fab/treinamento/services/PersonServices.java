package com.fab.treinamento.services;

import com.fab.treinamento.exceptions.ResourceNotFoundException;
import com.fab.treinamento.model.Person;
import com.fab.treinamento.modelV2.PersonV2;
import com.fab.treinamento.repository.PersonRepository;
import com.fab.treinamento.repositoryV2.repository.PersonRepositoryV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class PersonServices {

    @Autowired
    PersonRepositoryV2 repositoryV2;

    @Autowired
    PersonRepository repository;

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    public List<Person> findAll() {

        return repository.findAll();
    }

    public Person findById(Long id) {

//        logger.info("finding one person!");
//        Person person = new Person();
//        person.setAddress("Rua Cedro");
//        person.setFirstName("Guilherme");
//        person.setLastName("Santos");
//        person.setGender("Male");

        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
    }

    public Person create(Person person) {
        logger.info("Creating one person!");
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person!");
        var entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id) {

        logger.info("Deleting one person!");
        repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        repository.deleteById(id);
    }

    public PersonV2 createV2(PersonV2 person) {
        logger.info("Creating one person with V2!");
        return repositoryV2.save(person);
    }

    public boolean isNumeric(Long strNumber) {
        var id = String.valueOf(strNumber);
        if (id == null) {
            return true;
        }
        return !id.matches("[-+]?[0-9]");
    }
}
