package com.example.consumer.service;

import com.example.consumer.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    protected static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public void sendTheEmail(Subscription sub)  {
        logger.info("Sending an e-mail for {}",sub);
    }
}
