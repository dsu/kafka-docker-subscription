package com.example.producer.controller;

import com.example.producer.ProducerApp;
import com.example.producer.model.Subscription;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = ProducerApp.class)
@EmbeddedKafka
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SubscriptionServiceControllerTest {

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, "SUB");

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + port + "/private/api/v1/subscriptions";
    }

    @Test
    public void createSubscription()  {
        ResponseEntity<Subscription> response = postNewSubscription();
        assertThat(response.getBody().getId(), greaterThan(new Long(0)));
        assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
    }

    private ResponseEntity<Subscription> postNewSubscription() {
        Subscription sub = new Subscription(1, LocalDate.now(), "email@email.com", true);
        return post(sub);
    }

    @Test(expected = Exception.class)
    public void postTwoTheSameSubscriptions() {
        Subscription sub = new Subscription(1, LocalDate.now(), "email@email.com", true);
        ResponseEntity<Subscription> result = post(sub);
        assertThat(result.getStatusCode(), equalTo(HttpStatus.CREATED));
        post(sub);
    }

    private ResponseEntity<Subscription> post(Subscription sub) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Subscription> entity = new HttpEntity<Subscription>(sub, headers);
        return restTemplate
                .exchange(url, HttpMethod.POST, entity, Subscription.class);
    }


}