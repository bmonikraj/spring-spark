package com.bmonikraj.configuration;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SparkConfiguration {

    @Value("${spark.app.name}")
    private String sparkAppName;

    @Value("${spark.master}")
    private String sparkMaster;

    @Bean
    public SparkConf conf(){
        return new SparkConf().setAppName(sparkAppName).setMaster(sparkMaster);
    }

    @Bean
    public JavaSparkContext sc(){
        return new JavaSparkContext(this.conf());
    }
}
