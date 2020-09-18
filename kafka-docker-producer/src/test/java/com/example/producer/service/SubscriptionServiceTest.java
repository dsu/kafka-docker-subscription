package com.example.producer.service;

import com.example.producer.model.Subscription;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.producer.TestConfiguration;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.EmbeddedKafkaRule;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfiguration.class)
@EmbeddedKafka
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.AUTO_CONFIGURED)
public class SubscriptionServiceTest {

    private static Logger logger = LoggerFactory.getLogger(SubscriptionServiceTest.class);

    @ClassRule
    public static EmbeddedKafkaRule embeddedKafka = new EmbeddedKafkaRule(1, true, "SUB");

    @Autowired
    SubscriptionService service;

    @Test
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    public void saveSubscription() throws JsonProcessingException {
        List<Subscription> allEmpty = service.findAll();
        assertThat(allEmpty.size(), is(0));
        Subscription sub = new Subscription(1, LocalDate.now(), "email@email.com", true);
        Subscription subscription = service.saveSubscription(sub);
        assertThat(subscription.getId(), greaterThan(new Long(0)));
    }

    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    @Test
    public void saveAndList() throws JsonProcessingException {
        List<Subscription> allEmpty = service.findAll();
        assertThat(allEmpty.size(), is(0));
        saveSubscription();
        List<Subscription> all = service.findAll();
        assertThat(all.size(), is(1));

    }
}