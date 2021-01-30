package com.bmonikraj.runners;

import com.bmonikraj.runners.console.ConsolePrinter;
import com.bmonikraj.runners.kafka.Producer;
import com.bmonikraj.runners.spark.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

@Component
public class RunnerScheduler {

    private static final Logger logger = LoggerFactory.getLogger(RunnerScheduler.class);

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private ConsolePrinter consolePrinter;

    @Autowired
    private Producer producer;

    @Autowired
    private Driver driver;

    @Scheduled(cron = "${console.cron.expression}")
    public void startConsolePrinter(){
        threadPoolTaskExecutor.execute(consolePrinter);
    }

    @Scheduled(cron = "${kafka.cron.expression}")
    public void startKafkaProducer(){
        threadPoolTaskExecutor.execute(producer);
    }

    @Scheduled(cron = "${spark.cron.expression}")
    public void startSparkDriver(){
        threadPoolTaskExecutor.execute(driver);
    }
}
