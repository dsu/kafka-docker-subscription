package com.example.publicapp.controller;

import com.example.publicapp.TestConfiguration;
import com.example.publicapp.model.Subscription;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = TestConfiguration.class)
public class SubscriptionControllerTest {

    @LocalServerPort
    private int port;
    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + port + "/api/v1/subscriptions";
    }


    @Test
    public void postValid() {

        Subscription subscription = new Subscription(1, LocalDate.now(), "email@domain.com", false);
        given()
                .baseUri(url)
                .body(subscription)
                .post().then().assertThat().statusCode(HttpStatus.OK.value()).and().body("consent", is(Boolean.FALSE));

    }

    @Test
    public void postInvalid() {
        Subscription subscription = new Subscription(1, LocalDate.now(), "fakeemail", false);
        given()
                .baseUri(url)
                .body(subscription)
                .post().then().assertThat().statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    public void tryToGet() {
        Response response = given().get(url);
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), response.getStatusCode());
    }

    private RequestSpecification given() {
        return RestAssured.given().contentType(ContentType.JSON);

    }

}