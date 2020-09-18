package com.example.producer.service;

import com.example.producer.model.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.function.Consumer;


@Component
public class KafkaSender {

    protected static final Logger logger = LoggerFactory.getLogger(KafkaSender.class);

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.subscription.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, Subscription> kafkaTemplate;

    public void send(Subscription payload, Consumer<SendResult<String, Subscription>> callback, Runnable callbackOnError) {

        Message<Subscription> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic)
                .build();

        logger.debug("kafkaTemplate {}", kafkaTemplate);

        ListenableFuture<SendResult<String, Subscription>> future = kafkaTemplate.send(message);
        future.addCallback(
                new ListenableFutureCallback<SendResult<String, Subscription>>() {

                    @Override
                    public void onSuccess(final SendResult<String, Subscription> message) {
                        logger.info("sent message= " + message.getRecordMetadata() + " with offset= " + message.getRecordMetadata().offset());
                        callback.accept(message);
                    }

                    @Override
                    public void onFailure(final Throwable throwable) {
                        logger.error("unable to send message= ", throwable);
                        callbackOnError.run();
                    }
                });
        logger.info("Message: {} send", payload);
    }


}
