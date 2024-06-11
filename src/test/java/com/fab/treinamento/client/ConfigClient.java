package com.fab.treinamento.client;

import com.fab.treinamento.base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ConfigClient extends BaseTest {

    public static Response getPersonTotal(){
        return RestAssured.given()
                .spec(requestSpecification)
                .when()
                .get("/api/person/v1")
                .then()
                .extract()
                .response();
    }
}
