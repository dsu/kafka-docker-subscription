package com.example.consumer;

import com.example.consumer.model.Subscription;
import com.example.consumer.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

public class MessageListener {

    protected static final Logger logger = LoggerFactory.getLogger(MessageListener.class);

    @Autowired
    private EmailService service;

    @KafkaListener(topics = "${spring.kafka.subscription.topic}",containerFactory = "subscriptionListenerContainerFactory")
    public void listen(Subscription message) {
        System.out.println( "new message: " + message);service.sendTheEmail(message);
    }


}
