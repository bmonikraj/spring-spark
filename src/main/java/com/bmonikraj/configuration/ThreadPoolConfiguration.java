package com.bmonikraj.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolConfiguration {

    @Value("${thread.name.prefix}")
    private String threadNamePrefix;

    @Value("${core.pool.size}")
    private Integer corePoolSize;

    @Value("${max.pool.size}")
    private Integer maxPoolSize;

    @Value("${queue.capacity}")
    private Integer queueCapacity;

    @Bean
    public ThreadPoolTaskExecutor threadPoolTaskExecutor(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(corePoolSize);
        threadPoolTaskExecutor.setMaxPoolSize(maxPoolSize);
        threadPoolTaskExecutor.setQueueCapacity(queueCapacity);
        threadPoolTaskExecutor.setThreadNamePrefix(threadNamePrefix);
        threadPoolTaskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

}
