package com.bmonikraj.runners.console;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope(scopeName = "prototype")
public class ConsolePrinter implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(ConsolePrinter.class);

    @Override
    public void run() {
        logger.info("ConsolePrinter started, name : {}", Thread.currentThread().getName());
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            logger.info("Printing current time : {}", simpleDateFormat.format(date));
        } catch (Exception exception) {
            logger.error("Exception in executing consolePrinter worker thread");
            exception.printStackTrace();
        }
    }
}
