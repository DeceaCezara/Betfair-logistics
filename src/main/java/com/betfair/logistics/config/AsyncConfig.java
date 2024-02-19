package com.betfair.logistics.config;

import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class AsyncConfig {

    //Cand va fi instantiat? =>o singura data
    @Bean
    public ExecutorService executor(){
        return new ThreadPoolExecutor(4,
                4,
                10,
                java.util.concurrent.TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.AbortPolicy());
    }


    @Bean
    public ExecutorService smallExecutor(){
        return new ThreadPoolExecutor(2,
                2,
                10,
                java.util.concurrent.TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new ThreadPoolExecutor.AbortPolicy());
    }
}
