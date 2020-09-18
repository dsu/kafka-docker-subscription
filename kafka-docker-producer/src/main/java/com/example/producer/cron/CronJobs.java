package com.example.producer.cron;

import com.example.producer.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class CronJobs {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SubscriptionService subscriptionService;

    /**
     * Repeat every 15 minutes
     */
    @Scheduled(cron = "0 0/15 * * * *")
    public void resendSubscriptionsToKafka() {
        logger.debug("Try to resend the subscriptions to Kafka");
        subscriptionService.tryToResend();
    }

}
