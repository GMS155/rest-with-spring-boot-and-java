package com.fab.treinamento.controller;

import com.fab.treinamento.model.Person;
import com.fab.treinamento.modelV2.PersonV2;
import com.fab.treinamento.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/person/v1")
@Tag(name = "API Person", description = "teste")
public class GreetingController {

    @Autowired
    private PersonServices service;

    @ApiResponse(responseCode = "200", description = "Lista de usuários recuperada com sucesso",
            content =
            @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = Person.class)))
    )
    @ApiResponse(responseCode = "404", description = "Nenhum usuário encontrado", content = @Content)
    @Operation(summary = "Obter todos os usuários", description = "Obtém uma lista de todos os usuários cadastrados no sistema")
    //@ApiOperation(value="Endpoint buscar pessoas")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Person> findAll() throws Exception {

        return service.findAll();
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Person findById(@Parameter(description = "ID do usuário", required = true) @PathVariable(value = "id") Long id) throws Exception {

        if (service.isNumeric(id)) {
            throw new UnsupportedOperationException("Digite um número");
        }

        Link selfLink = WebMvcLinkBuilder.linkTo(GreetingController.class).slash("person").withSelfRel();
        //person1.add(selfLink);

        return service.findById(id).add(selfLink);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Person create(@RequestBody Person person) {

        return service.create(person);
    }

    @PostMapping(value = "/v2", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public PersonV2 createV2(@RequestBody PersonV2 person) {

        return service.createV2(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Person update(@RequestBody Person person) {

        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) throws Exception {

        if (service.isNumeric(id)) {
            throw new UnsupportedOperationException("Digite um número");
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
