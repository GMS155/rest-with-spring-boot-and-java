package com.fab.treinamento.controller;

import com.fab.treinamento.model.Person;
import com.fab.treinamento.modelV2.PersonV2;
import com.fab.treinamento.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
public class GreetingController {

    @Autowired
    private PersonServices service;
    private static final String template = "Hello, %s!";

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() throws Exception {

        return service.findAll();
    }

    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person findById(@PathVariable(value = "id") Long id) throws Exception {

        if (isNumeric(id)) {
            throw new UnsupportedOperationException("Digite um número");
        }
        return service.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person create(@RequestBody Person person) {

        return service.create(person);
    }

    @PostMapping(value = "/v2", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonV2 createV2(@RequestBody PersonV2 person) {

        return service.createV2(person);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@RequestBody Person person) {

        return service.update(person);
    }

    private boolean isNumeric(Long strNumber) {
        var id = String.valueOf(strNumber);
        if (id == null) {
            return true;
        }
        return !id.matches("[-+]?[0-9]");
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {

        if (isNumeric(id)) {
            throw new UnsupportedOperationException("Digite um número");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    //      } else {
    //           String number = strNumber.replaceAll(",", ".");
//            return number.matches("[-+]?[0-9]*\\.?[0-9]+");
//        }
//    }
//    @RequestMapping("/greeting")
//    public Greeting greeting(@RequestParam(value = "name", defaultValue = "Word") String name) {
//
//        return new Greeting(counter.incrementAndGet(), String.format(template, name));
//    }

    //    private Double convertToDouble(String strNumber) {
//        String number = strNumber.replaceAll(",", ".");
//        return Double.parseDouble(number);
//    }
}
