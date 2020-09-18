package com.example.producer.service;

import com.example.producer.repository.SubstrictionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.producer.model.Subscription;
import com.example.producer.model.SubscriptionInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubscriptionService {

    protected static final Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

    private SubstrictionRepository repository;
    private KafkaSender sender;
    private ObjectMapper mapper;


    @Autowired
    public SubscriptionService(SubstrictionRepository repository, KafkaSender sender, ObjectMapper mapper) {
        this.repository = repository;
        this.sender = sender;
        this.mapper = mapper;
    }

    public Subscription saveSubscription(Subscription sub) throws JsonProcessingException {
        Subscription save = saveIntoDb(sub);
        sendToKafka(save);
        return save;
    }

    /**
     * Try to resend subscriptions that failed to be send to Kafka
     */
    public void tryToResend() {
        Set<Subscription> subscriptions = repository.findByStatus(SubscriptionInterface.SubscriptionStatus.NOT_SEND);
        logger.info("Trying to resend {} subscriptions to Kafka", subscriptions);
        subscriptions.forEach(this::sendToKafka);
    }

    public void setSendToKafkaStatus(Subscription sub, SubscriptionInterface.SubscriptionStatus status) {
        sub.setStatus(status);
        Subscription save = repository.save(sub);
        logger.info("Subscription status has changed {}", save);
    }

    private Subscription saveIntoDb(Subscription sub) {
        logger.info("Saving the record to the database");
        return repository.save(sub);
    }

    private void sendToKafka(Subscription sub) {
        logger.info("Sending a message to Kafka");
        sender.send(sub,
                msg -> setSendToKafkaStatus(sub, SubscriptionInterface.SubscriptionStatus.SEND_TO_KAFKA),
                () -> setSendToKafkaStatus(sub, SubscriptionInterface.SubscriptionStatus.NOT_SEND));
    }

    public List<Subscription> findAll() {
        return repository.findAll();
    }
}
