package com.example.publicapp.service;

import com.example.publicapp.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PrivateServiceFacade {

    protected static final Logger logger = LoggerFactory.getLogger(PrivateServiceFacade.class);

    private String serviceUrl;

    private RestTemplate restTemplate;


    @Autowired
    public PrivateServiceFacade(RestTemplate restTemplate, @Value("${service.url}") String serviceUrl) {
        this.restTemplate = restTemplate;
        this.serviceUrl = serviceUrl;
    }

    public ResponseEntity<Subscription> callCreateSubscription(Subscription substriction) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Subscription> entity = new HttpEntity<Subscription>(substriction, headers);
        logger.debug("Calling a private service {}", serviceUrl);
        ResponseEntity<Subscription> response = restTemplate
                .exchange(serviceUrl, HttpMethod.POST, entity, Subscription.class);
        logger.debug("Private service response {}", response);
        return response;
    }
}
