package com.example.publicapp;

import com.example.publicapp.service.PrivateServiceFacade;
import com.example.publicapp.model.Subscription;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;


@ComponentScan(basePackageClasses = {})
@SpringBootApplication
public class TestConfiguration {
    public static void main(String[] args) {
        SpringApplication.run(TestConfiguration.class, args);
    }


    @Bean
    public PrivateServiceFacade facade() {
        return new PrivateServiceFacade(new RestTemplate(), "fakeurl") {
            public ResponseEntity<Subscription> callCreateSubscription(Subscription substriction) {
                return ResponseEntity.ok(substriction);
            }
        };
    }
}
