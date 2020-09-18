package com.example.producer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.producer.model.Subscription;
import com.example.producer.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/private/api/v1")
@Api(value = "Subscription API", description = "")
public class SubscriptionServiceController {

    protected static final Logger logger = LoggerFactory.getLogger(SubscriptionServiceController.class);

    private SubscriptionService service;

    @Autowired
    public SubscriptionServiceController(SubscriptionService service) {
        this.service = service;
    }

    @ApiOperation(value = "Add a subscription", code = 201)
    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public Subscription createSubscription(
            @ApiParam(value = "Subscription object store in database table", required = true)
            @Valid @RequestBody Subscription substriction) throws JsonProcessingException {
        logger.debug("Saving an object to db {}", service);
        Subscription subscription = service.saveSubscription(substriction);
        logger.debug("Saved an object to db {}", subscription);
        return subscription;
    }

    @GetMapping("/subscriptions")
    public List<Subscription> getSubscriptions() {
        return service.findAll();
    }
}
