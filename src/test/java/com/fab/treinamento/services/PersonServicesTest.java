package com.fab.treinamento.services;

import com.fab.treinamento.repository.PersonRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.mockito.Mockito.when;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class PersonServicesTest {

    SoftAssertions softAssertions = new SoftAssertions();
    MockTest input;

    @InjectMocks
    private PersonServices services;

    @Mock
    PersonRepository personRepository;

    @BeforeEach
    void setUp() {
        // input = new MockPe
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {

        MockTest mockTest = new MockTest();
        var mock = mockTest.getPerson();
        mock.setId(7L);
        when(personRepository.findById(7L)).thenReturn(Optional.of(mock));

        var result = services.findById(7L);

        assertAll("Validação",
                () -> assertEquals(result.getId(), mock.getId(), "Valida Id"),
                () -> assertEquals(result.getFirstName(), mock.getFirstName(), "valida nome"));

    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void createV2() {
    }
}