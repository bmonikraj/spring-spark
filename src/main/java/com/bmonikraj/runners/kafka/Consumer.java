package com.bmonikraj.runners.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    private static final Logger logger = LoggerFactory.getLogger(Consumer.class);

    @KafkaListener(
            topics = "${kafka.topic}",
            groupId = "${kafka.group.id}"
    )
    public void listener(String message){
        logger.info("Consumer started");
        try {
            logger.info("Message received : {}", message);
        } catch (Exception exception) {
            logger.error("Exception in executing Consumer worker thread");
            exception.printStackTrace();
        }
    }

}
