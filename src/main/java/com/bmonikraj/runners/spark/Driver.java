package com.bmonikraj.runners.spark;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
@Scope(scopeName = "prototype")
public class Driver implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Driver.class);

    @Value("${word.file}")
    private String wordFile;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private JavaSparkContext javaSparkContext;

    public Map<String, Long> getCount() throws IOException {
        File file = new File(wordFile);
        String content = new String(Files.readAllBytes(file.toPath()));
        List<String> wordList = Arrays.asList(content.split(" "));
        JavaRDD<String> words = javaSparkContext.parallelize(wordList);
        Map<String, Long> wordCounts = words.countByValue();
        return wordCounts;
    }

    @Override
    public void run() {
        logger.info("Spark Driver started, name : {}", Thread.currentThread().getName());
        try{
            logger.info("Word Count : {}", this.getCount());
        } catch (Exception exception) {
            logger.error("Exception in executing Spark Driver worker thread");
            exception.printStackTrace();
        }
    }
}
