package com.bmonikraj.runners.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import java.util.UUID;

@Component
@Scope(scopeName = "prototype")
public class Producer implements Runnable{

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    @Value("${kafka.topic}")
    private String kafkaTopic;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message){
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send(kafkaTopic, message);

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Unable to send message due to = {}", throwable.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                logger.info("Sent Message=[{}] with offset=[{}]", message, stringStringSendResult.getRecordMetadata().offset());
            }
        });
    }

    @Override
    public void run() {
        logger.info("Producer started, name : {}", Thread.currentThread().getName());
        try {
            this.sendMessage(UUID.randomUUID().toString());
        } catch (Exception exception) {
            logger.error("Exception in executing Producer worker thread");
            exception.printStackTrace();
        }
    }
}
