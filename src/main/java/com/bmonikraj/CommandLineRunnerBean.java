package com.bmonikraj;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerBean implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerBean.class);

    @Override
    public void run(String... args) throws Exception {
        String strArgs = Arrays.stream(args).collect(Collectors.joining(","));

        logger.info("Application started with following arguments: " + strArgs);
    }
}
