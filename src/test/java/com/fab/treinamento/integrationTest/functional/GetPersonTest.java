package com.fab.treinamento.integrationTest.functional;

import com.fab.treinamento.RestWithSpringBootAndJavaApplication;
import com.fab.treinamento.base.BaseTest;
import com.fab.treinamento.client.ConfigClient;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;


@ContextConfiguration(classes = {RestWithSpringBootAndJavaApplication.class})
@SpringBootTest
public class GetPersonTest extends BaseTest {

    @Test
    public void ConsultaPerson_CasoDeSucesso() {

        Response response = ConfigClient.getPersonTotal();


        assertAll("Validação",
                () -> assertNotNull(response.getBody().jsonPath(), "Valida lista de person"));
        // () -> assertEquals(result.getFirstName(), mock.getFirstName(), "valida nome"));
    }
}

