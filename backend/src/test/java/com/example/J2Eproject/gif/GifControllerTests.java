package com.example.J2Eproject.gif;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GifControllerTests {

    @Test
    void should_create_gif() {
        GifDTO gif = new GifDTO("Test2", "http://test.com");

        given()
                .contentType(ContentType.JSON)
                .body(gif)
                .when()
                .post("/gif")
                .then()
                .statusCode(201)
                .header("Location", notNullValue());


    }
}
