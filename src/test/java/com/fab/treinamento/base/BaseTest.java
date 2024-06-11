package com.fab.treinamento.base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class BaseTest {

    protected static RequestSpecification requestSpecification;

    public BaseTest() {
        preCondition();
    }

    public static void preCondition() {

        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri("http://localhost:8080")
                .build();

        enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
