package com.example.publicapp.controller;

import com.example.publicapp.conf.SwgApi;
import com.example.publicapp.model.Subscription;
import com.example.publicapp.service.PrivateServiceFacade;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Api(value = "Subscription API", description = "")
@SwgApi
public class SubscriptionController {

    protected static final Logger logger = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private PrivateServiceFacade facade;

    @ApiOperation(value = "Add a subscription", code = 201)
    @PostMapping("/subscriptions")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Subscription> createSubscription(
            @ApiParam(value = "Subscription object store in database table", required = true)
            @Valid @RequestBody Subscription substriction) {
        return facade.callCreateSubscription(substriction);
    }


}
